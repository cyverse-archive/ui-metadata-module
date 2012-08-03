package org.iplantc.core.metadata.client.property;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.metadata.client.JSONMetaDataObject;
import org.iplantc.core.metadata.client.validation.MetaDataValidator;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Metadata property.
 * 
 * @author amuir
 * 
 */
public class Property extends JSONMetaDataObject {
    public static String VALUE = "value"; //$NON-NLS-1$
    public static String VALIDATOR = "validator"; //$NON-NLS-1$
    public static String ORDER = "order"; //$NON-NLS-1$
    public static String DATA_OBJECT = "data_object"; //$NON-NLS-1$
    public static String OMIT_IF_BLANK = "omit_if_blank"; //$NON-NLS-1$

    private String value;
    private MetaDataValidator validator;
    private int order;
    private DataObject dataObject;
    private boolean omit_if_blank;

    /**
     * Instantiate from a JSONObject.
     * 
     * @param json instantiating object.
     */
    public Property(JSONObject json) {
        super(json);
    }

    /**
     * Create an empty Property.
     */
    public Property() {
        super("{}"); //$NON-NLS-1$
    }

    private void parseValidator(final JSONObject json) {
        JSONObject jsonValidator = JsonUtil.getObject(json, VALIDATOR);

        if (jsonValidator != null) {
            validator = new MetaDataValidator(jsonValidator);
        }
    }

    private void parseType(final JSONObject json) {
        type = JsonUtil.getString(json, TYPE);
    }

    private void parseOrder(final JSONObject json) {
        JSONValue val = json.get(ORDER);

        if (val != null) {
            JSONNumber jsonOrder = val.isNumber();
            if (jsonOrder != null) {
                order = (int)jsonOrder.doubleValue();
            }
        }
    }

    private void parseDataObject(JSONObject json) {
        JSONObject jsonDataObject = JsonUtil.getObject(json, DATA_OBJECT);

        if (jsonDataObject != null) {
            dataObject = new DataObject(jsonDataObject);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeParse() {
        super.beforeParse();

        // set property as unordered by default
        order = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(JSONObject json) {
        super.parseData(json);

        parseValidator(json);
        parseType(json);
        parseOrder(json);
        parseDataObject(json);
        parseOmitFlag(json);
        setValue(JsonUtil.getString(json, VALUE));
    }

    private void parseOmitFlag(JSONObject json) {
        JSONValue val = json.get(OMIT_IF_BLANK);

        if (val != null) {
            JSONBoolean jsonFlag = val.isBoolean();
            if (jsonFlag != null) {
                setOmit_if_blank(jsonFlag.booleanValue());
            }
        }
        
    }

    /**
     * Set property value.
     * 
     * @param value a string identifying the value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Retrieve property value.
     * 
     * @return string representation of the object's value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set property validator.
     * 
     * param validator
     */
    public void setValidator(MetaDataValidator validator) {
        this.validator = validator;
    }

    /**
     * Retrieve property validator.
     * 
     * @return null if no validator has been set.
     */
    public MetaDataValidator getValidator() {
        return validator;
    }

    /**
     * Sets the order of this property within all properties of a tool.
     * 
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the order of this property within all properties of a tool.
     * 
     * @return This property's tool order. A negative value is considered un-ordered.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Gets the DataObject of an input or output property.
     * 
     * @return An input or output DataObject.
     */
    public DataObject getDataObject() {
        return dataObject;
    }

    /**
     * Gets the DataObject of an input or output property.
     * 
     * @return An input or output DataObject.
     */
    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * Returns a JSON representation of the object. This method is the counterpart to the
     * Property(JSONObject) constructor.
     * 
     * @return a JSON object
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();

        if (value != null) {
            json.put(VALUE, new JSONString(value));
        }
        if (type != null) {
            json.put(TYPE, new JSONString(type));
        }
        if (validator != null) {
            json.put(VALIDATOR, validator.toJson());
        }
        
        json.put(ORDER, new JSONNumber(order));
        json.put(OMIT_IF_BLANK, JSONBoolean.getInstance(omit_if_blank));

        if (DataObject.INPUT_TYPE.equalsIgnoreCase(type)
                || DataObject.OUTPUT_TYPE.equalsIgnoreCase(type) && dataObject != null) {
            json.put(DATA_OBJECT, dataObjectToJson());
        }

        return json;
    }

    protected JSONObject dataObjectToJson() {
        dataObject.setName(getLabel());
        dataObject.setLabel(getLabel());
        dataObject.setCmdSwitch(getName());
        dataObject.setDescription(getDescription());
        dataObject.setType(getType());
        dataObject.setOrder(getOrder());
        dataObject.setVisible(isVisible());

        return dataObject.toJson();
    }

    /**
     * @param omit_if_blank the omit_if_blank to set
     */
    public void setOmit_if_blank(boolean omit_if_blank) {
        this.omit_if_blank = omit_if_blank;
    }

    /**
     * @return the omit_if_blank
     */
    public boolean isOmit_if_blank() {
        return omit_if_blank;
    }
}

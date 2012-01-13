package org.iplantc.core.metadata.client;

import org.iplantc.core.jsonutil.JsonUtil;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * Metadata object that can be built from either a JSON string or JSONObject.
 * 
 * @author amuir
 * 
 */
public class JSONMetaDataObject extends MetaDataObject {
    public static String ID = "id"; //$NON-NLS-1$
    public static String NAME = "name"; //$NON-NLS-1$
    public static String LABEL = "label"; //$NON-NLS-1$
    public static String DESCRIPTION = "description"; //$NON-NLS-1$
    public static String TYPE = "type"; //$NON-NLS-1$
    public static String IS_VISIBLE = "isVisible"; //$NON-NLS-1$

    /**
     * Constructs an instance of the object given JSON.
     * 
     * @param json a string containing JSON data.
     */
    protected JSONMetaDataObject(final String json) {
        beforeParse();

        if (JsonUtil.isValidJson(json)) {
            JSONObject objJson = JsonUtil.getObject(json);
            if (objJson != null) {
                parseData(objJson);
            }
        }
    }

    /**
     * Constructs an instance of the given a JSON object.
     * 
     * @param json an object modeling JSON data.
     */
    protected JSONMetaDataObject(final JSONObject json) {
        beforeParse();

        parseData(json);
    }

    /**
     * Performs an operation prior to JSON data being parsed.
     */
    protected void beforeParse() {
        // by default... do nothing before parsing
    }

    /**
     * Executes parsing of the JSON data into key/values pairings.
     * 
     * The expected keys are: id, name, desc, type, propertytype
     * 
     * @param json an object modeling JSON data.
     */
    protected void parseData(final JSONObject json) {
        if (json != null) {
            setId(JsonUtil.getString(json, ID));
            setName(JsonUtil.getString(json, NAME));
            setLabel(JsonUtil.getString(json, LABEL));
            setDescription(JsonUtil.getString(json, DESCRIPTION));
            setType(JsonUtil.getString(json, TYPE));
            setVisible(JsonUtil.getBoolean(json, IS_VISIBLE, true));
        }
    }

    /**
     * Returns a JSON representation of the object. This method is the counterpart to the
     * JSONMetaDataObject(JSONObject) constructor.
     * 
     * @return a JSON object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        if (getId() != null) {
            json.put(ID, new JSONString(getId()));
        }

        if (getName() != null) {
            json.put(NAME, new JSONString(getName()));
        }

        if (getLabel() != null) {
            json.put(LABEL, new JSONString(getLabel()));
        }

        if (getDescription() != null) {
            json.put(DESCRIPTION, new JSONString(getDescription()));
        }

        if (getType() != null) {
            json.put(TYPE, new JSONString(getType()));
        }

        json.put(IS_VISIBLE, JSONBoolean.getInstance(isVisible()));

        return json;
    }
}
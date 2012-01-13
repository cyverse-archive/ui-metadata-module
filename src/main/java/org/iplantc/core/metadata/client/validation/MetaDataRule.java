package org.iplantc.core.metadata.client.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.iplantc.core.jsonutil.JsonUtil;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * Metadata rule definition.
 * 
 * @author amuir
 * 
 */
public class MetaDataRule extends BaseModelData {
    public final static String TYPE_PROPERTY = "type"; //$NON-NLS-1$
    public final static String PARAMS_PROPERTY = "params"; //$NON-NLS-1$
    public static final String DISPLAY = "display"; //$NON-NLS-1$
    public static final String NAME = "name"; //$NON-NLS-1$
    public static final String VALUE = "value"; //$NON-NLS-1$
    public static final String IS_DEFAULT = "isDefault"; //$NON-NLS-1$

    private static final long serialVersionUID = 2598716603395285386L;

    /**
     * Creates a rule with no parameters.
     * 
     * @param type a valid rule type such as "IntRange"
     */
    public MetaDataRule(String type) {
        setType(type);
        setParams(new ArrayList<JSONValue>());
    }

    /**
     * Instantiate from a JSONObject.
     * 
     * @param json instantiating object.
     */
    public MetaDataRule(final JSONObject json) {
        setParams(new ArrayList<JSONValue>());

        parseData(json);
    }

    private void parseParams(final JSONArray arr) {
        List<JSONValue> paramValues = new ArrayList<JSONValue>();

        if (arr != null) {
            JSONValue param;

            for (int i = 0,len = arr.size(); i < len; i++) {
                param = arr.get(i);

                paramValues.add(param);
            }
        }
        setParams(paramValues);
    }

    private void parseData(final JSONObject json) {
        if (json != null) {
            Set<String> keys = json.keySet();

            Iterator<String> iter = keys.iterator();

            if (iter.hasNext()) {
                String type = iter.next();
                parseParams(JsonUtil.getArray(json, type));
                setType(type);
            }
        }
    }

    /**
     * Set rule type.
     * 
     * @param type string representation of type.
     */
    public void setType(String type) {
        set(TYPE_PROPERTY, type);
    }

    /**
     * Retrieve rule type.
     * 
     * @return string representation of type.
     */
    public String getType() {
        return get(TYPE_PROPERTY);
    }

    /**
     * Retrieve parameter count.
     * 
     * @return number of parameters.
     */
    public int getNumParams() {
        return getParams().size();
    }

    /**
     * Retrieve paramaters.
     * 
     * @return list of parameters.
     */
    public List<JSONValue> getParams() {
        ParameterList params = (ParameterList)get(PARAMS_PROPERTY);
        return params.values;
    }

    /**
     * Set paramaters.
     * 
     * @params list of parameters.
     */
    public void setParams(List<JSONValue> values) {
        set(PARAMS_PROPERTY, new ParameterList(values));
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray paramArray = JsonUtil.buildArray(getParams());
        json.put(getType(), paramArray);
        return json;
    }

    public MetaDataRule clone() {
        return new MetaDataRule(toJSON());
    }

    /**
     * Contains a list of JSONValues; overrides toString() to make it suitable for use in a grid column.
     * 
     * @author hariolf
     * 
     */
    private class ParameterList {
        List<JSONValue> values;

        ParameterList(List<JSONValue> values) {
            this.values = values;
        }

        @Override
        public String toString() {
            return Arrays.toString(values.toArray());
        }
    }
}

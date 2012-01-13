package org.iplantc.core.metadata.client.contracts;

import java.util.ArrayList;
import java.util.List;

import org.iplantc.core.metadata.client.JSONMetaDataObject;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * Contract object to enforce relationships between wizard widgets.
 * 
 * @author amuir
 * 
 */
public class MetaDataContract extends JSONMetaDataObject {
    private List<String> params;

    /**
     * Instantiate from JSONObject.
     * 
     * @param json instantiating object.
     */
    public MetaDataContract(final JSONObject json) {
        super(json);
        initParams(json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeParse() {
        params = new ArrayList<String>();
    }

    private void parseParamsFromJsonArray(final JSONArray arr) {
        if (arr != null) {
            for (int i = 0,len = arr.size(); i < len; i++) {
                params.add(arr.get(i).isString().stringValue());
            }
        }
    }

    private void initParams(final JSONObject json) {
        JSONValue val = json.get("params");

        if (val != null) {
            JSONArray jsonRules = val.isArray();

            parseParamsFromJsonArray(jsonRules);
        }
    }

    /**
     * Retrieve contract parameters.
     * 
     * @return list of contract parameters.
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * Retrieve parameter count.
     * 
     * @return number of parameters.
     */
    public int getNumParams() {
        return params.size();
    }
}

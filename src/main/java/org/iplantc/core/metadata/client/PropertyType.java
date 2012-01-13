package org.iplantc.core.metadata.client;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class PropertyType extends BaseModelData {
    private static final long serialVersionUID = -8067165423027703868L;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "value_type";

    public PropertyType(JSONObject json) {
        set(ID, json.get(ID).isString().stringValue());
        set(NAME, json.get(NAME).isString().stringValue());
        set(DESCRIPTION, json.get(DESCRIPTION).isString().stringValue());
        set(TYPE, json.get(TYPE).isString().stringValue());
    }

    private String getString(final String key) {
        if (get(key) != null) {
            return get(key).toString();
        } else {
            return "";
        }
    }

    public String getId() {
        return getString(ID);
    }

    public String getName() {
        return getString(NAME);
    }

    public String getDescription() {
        return getString(DESCRIPTION);
    }

    public String getType() {
        return getString(TYPE);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put(ID, new JSONString(getId()));
        json.put(NAME, new JSONString(getName()));
        json.put(DESCRIPTION, new JSONString(getDescription()));
        json.put(TYPE, new JSONString(getType()));

        return json;
    }
}

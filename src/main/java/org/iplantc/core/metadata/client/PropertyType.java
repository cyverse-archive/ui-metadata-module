package org.iplantc.core.metadata.client;

import org.iplantc.core.jsonutil.JsonUtil;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class PropertyType extends BaseModelData {
    private static final long serialVersionUID = -8067165423027703868L;
    public static final String ID = "id"; //$NON-NLS-1$
    public static final String NAME = "name"; //$NON-NLS-1$
    public static final String DESCRIPTION = "description"; //$NON-NLS-1$
    public static final String TYPE = "value_type"; //$NON-NLS-1$

    public PropertyType(JSONObject json) {
        set(ID, JsonUtil.getString(json, ID));
        set(NAME, JsonUtil.getString(json, NAME));
        set(DESCRIPTION, JsonUtil.getString(json, DESCRIPTION));
        set(TYPE, JsonUtil.getString(json, TYPE));
    }

    public String getId() {
        return get(ID);
    }

    public String getName() {
        return get(NAME);
    }

    public String getDescription() {
        return get(DESCRIPTION);
    }

    public String getType() {
        return get(TYPE);
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

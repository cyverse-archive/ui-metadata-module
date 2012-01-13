package org.iplantc.core.metadata.client.property.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.metadata.client.JSONMetaDataObject;
import org.iplantc.core.metadata.client.property.Property;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * Collection of property groups.
 * 
 * @author amuir
 * 
 */
public class PropertyGroupContainer extends JSONMetaDataObject {
    public static final String GROUPS = "groups"; //$NON-NLS-1$

    private List<PropertyGroup> groups;

    /**
     * Instantiate from a JSON string.
     * 
     * @param json JSON representation of a property group container.
     */
    public PropertyGroupContainer(final String json) {
        super(json);
    }

    /**
     * Instantiate from a JSON object.
     * 
     * @param json JSON representation of a property group container.
     */
    public PropertyGroupContainer(final JSONObject objJson) {
        super(objJson);
    }

    /**
     * Create an empty PropertyGroupContainer.
     */
    protected PropertyGroupContainer() {
        super("{}"); //$NON-NLS-1$
    }

    private void parseGroupsFromJsonArray(final JSONArray arr) {
        if (arr != null) {
            for (int i = 0,len = arr.size(); i < len; i++) {
                JSONObject group = JsonUtil.getObjectAt(arr, i);
                if (group != null) {
                    groups.add(new PropertyGroup(group));
                }

            }
        }
    }

    private void buildGroups(final JSONObject json) {
        JSONValue val = json.get(GROUPS);

        if (val != null) {
            JSONArray jsonProperties = val.isArray();

            parseGroupsFromJsonArray(jsonProperties);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeParse() {
        super.beforeParse();
        groups = new ArrayList<PropertyGroup>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(final JSONObject json) {
        super.parseData(json);
        buildGroups(json);
    }

    /**
     * Retrieve group count.
     * 
     * @return number of groups.
     */
    public int getNumGroups() {
        return groups.size();
    }

    /**
     * Retrieve property groups.
     * 
     * @return list of groups.
     */
    public List<PropertyGroup> getGroups() {
        return groups;
    }

    /**
     * Adds a new property group to the container.
     * 
     * @param group a property group
     */
    public void add(PropertyGroup group) {
        groups.add(group);
    }

    /**
     * Inserts a new property group to the container at the given index.
     * 
     * @param group a property group
     */
    public void insert(int index, PropertyGroup group) {
        groups.add(index, group);
    }

    /**
     * Adds a new property group collection to the container at the given index.
     * 
     * @param propertyGroupCollection a collection of property groups
     */
    public void insertGroups(int index, Collection<PropertyGroup> propertyGroupCollection) {
        groups.addAll(index, propertyGroupCollection);
    }

    /**
     * Removes a property group from the container.
     * 
     * @param group a property group
     */
    public void remove(PropertyGroup group) {
        groups.remove(group);
    }

    /**
     * Removes a collection of property models from the container.
     * 
     * @param propertyCollection a collection of property models
     */
    public void removeAll(Collection<JSONMetaDataObject> propertyCollection) {
        groups.removeAll(propertyCollection);
    }

    /**
     * Returns all properties from all groups in the container.
     */
    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<Property>();
        for (PropertyGroup group : groups) {
            properties.addAll(group.getProperties());
        }
        return properties;
    }

    /**
     * Returns a JSON representation of the object. This method is the counterpart to the
     * PropertyGroupContainer(String) constructor.
     * 
     * @return a JSON object
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();

        List<JSONValue> jsonProperties = new ArrayList<JSONValue>();
        for (PropertyGroup group : groups) {
            jsonProperties.add(group.toJson());
        }

        json.put(GROUPS, JsonUtil.buildArray(jsonProperties));

        return json;
    }
}

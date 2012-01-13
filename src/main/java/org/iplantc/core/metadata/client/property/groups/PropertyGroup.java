package org.iplantc.core.metadata.client.property.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.metadata.client.JSONMetaDataObject;
import org.iplantc.core.metadata.client.property.Property;
import org.iplantc.core.metadata.client.util.MetaDataUtil;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * Collection of properties.
 * 
 * @author amuir
 * 
 */
public class PropertyGroup extends PropertyGroupContainer {
    public static final String PROPERTIES = "properties"; //$NON-NLS-1$

    private List<JSONMetaDataObject> properties;

    /**
     * Create an empty PropertyGroup.
     */
    public PropertyGroup() {
    }

    /**
     * Instantiate from a JSON string.
     * 
     * @param json instantiating object.
     */
    public PropertyGroup(final String json) {
        super(json);
    }

    /**
     * Instantiate from a JSON object.
     * 
     * @param json JSON representation of a property group.
     */
    public PropertyGroup(JSONObject json) {
        super(json);
    }

    private void addProperty(final Property property) {
        if (property != null) {
            MetaDataUtil.convertMustContainValues(property);
            properties.add(property);
        }
    }

    private void parseProperties(final JSONObject json) {
        JSONArray jsonProperties = JsonUtil.getArray(json, PROPERTIES);

        if (jsonProperties != null) {
            for (int i = 0,len = jsonProperties.size(); i < len; i++) {
                JSONObject jsonProperty = JsonUtil.getObjectAt(jsonProperties, i);

                addProperty(new Property(jsonProperty));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeParse() {
        super.beforeParse();

        properties = new ArrayList<JSONMetaDataObject>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(final JSONObject json) {
        super.parseData(json);

        if (json != null) {
            parseProperties(json);
        }
    }

    /**
     * Retrieve stored properties and property groups.
     * 
     * @return A List containing Property and PropertyGroup elements.
     */
    public List<JSONMetaDataObject> getElements() {
        return properties;
    }

    /**
     * Retrieve stored properties.
     * 
     * @return property list
     */
    @Override
    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<Property>();
        for (JSONMetaDataObject element : getElements()) {
            if (element instanceof Property) {
                properties.add((Property)element);
            }
        }
        return properties;
    }

    /**
     * Retrieve a property or property group by name.
     * 
     * @param name name of property to retrieve.
     * @return null on failure
     */
    public JSONMetaDataObject getElement(final String name) {
        JSONMetaDataObject ret = null; // assume failure

        if (name != null) {
            for (JSONMetaDataObject property : properties) {
                // did we find the desired property
                if (property.getName().equals(name)) {
                    ret = property;
                    break;
                }
            }
        }

        return ret;
    }

    /**
     * Retrieve property count.
     * 
     * @return number of properties.
     */
    public int getNumProperties() {
        return properties.size();
    }

    /**
     * Adds a new property to the container.
     * 
     * @param property a property
     */
    public void add(JSONMetaDataObject property) {
        properties.add(property);
    }

    /**
     * Adds a new property to the container at the given index.
     * 
     * @param property a property
     */
    public void insert(int index, JSONMetaDataObject property) {
        properties.add(index, property);
    }

    /**
     * Adds a property collection to the container at the given index.
     * 
     * @param propertyCollection a collection of property models
     */
    public void insertProperties(int index, Collection<JSONMetaDataObject> propertyCollection) {
        properties.addAll(index, propertyCollection);
    }

    /**
     * Removes a property from the container.
     * 
     * @param property a property
     */
    public void remove(JSONMetaDataObject property) {
        properties.remove(property);
    }

    /**
     * Removes a collection of property models from the container.
     * 
     * @param propertyCollection a collection of property models
     */
    @Override
    public void removeAll(Collection<JSONMetaDataObject> propertyCollection) {
        super.removeAll(propertyCollection);

        properties.removeAll(propertyCollection);
    }

    /**
     * Returns a JSON representation of the object. This method is the counterpart to the
     * PropertyGroup(String) constructor.
     * 
     * @return a JSON object
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        List<JSONValue> jsonProperties = new ArrayList<JSONValue>();
        for (JSONMetaDataObject property : properties) {
            jsonProperties.add(property.toJson());
        }
        json.put(PROPERTIES, JsonUtil.buildArray(jsonProperties));
        return json;
    }
}

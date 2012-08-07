package org.iplantc.core.metadata.client.property;

import java.util.Collection;

/**
 * An enum to represent property types. The constructor takes a "value_type" string, which must match a
 * value returned by the property-types service, as well a display string for displaying to the user in
 * the UI.
 * 
 * @author sriram
 * 
 */
public enum PropertyTypeCategory {
    INPUT(DataObject.INPUT_TYPE, DataObject.INPUT_TYPE),
    STRING("String", "String"),
    NUMBER("Number", "Number"),
    BOOLEAN("Boolean", "Boolean"),
    OUTPUT(DataObject.OUTPUT_TYPE, DataObject.OUTPUT_TYPE);

    private String valueType;
    private String displayText;

    private PropertyTypeCategory(String valueType, String displayText) {
        this.valueType = valueType;
        this.displayText = displayText;
    }

    /**
     * check if the Strings passed in contains the type category
     * 
     * @param categoryStrings
     * @return
     */
    public boolean isOneOf(Collection<String> categoryStrings) {
        for (String s : categoryStrings) {
            if (s.equals(valueType)) {
                return true;
            }
        }
        return false;
    }

    public String getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return displayText;
    }
}

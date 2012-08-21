package org.iplantc.core.metadata.client.property;

import java.util.Collection;

import org.iplantc.core.metadata.client.I18N;

/**
 * An enum to represent property types. The constructor takes a "value_type" string, which must match a
 * value returned by the property-types service, as well a display string for displaying to the user in
 * the UI.
 * 
 * @author sriram
 * 
 */
public enum PropertyTypeCategory {
    INPUT(DataObject.INPUT_TYPE, I18N.DISPLAY.propertyCategoryInput()),
    STRING("String", I18N.DISPLAY.propertyCategoryString()), //$NON-NLS-1$
    NUMBER("Number", I18N.DISPLAY.propertyCategoryNumber()), //$NON-NLS-1$
    BOOLEAN("Boolean", I18N.DISPLAY.propertyCategoryBoolean()), //$NON-NLS-1$
    ENV("EnvironmentVariable", I18N.DISPLAY.propertyCategoryEnvironmentVariable()), //$NON-NLS-1$
    OUTPUT(DataObject.OUTPUT_TYPE, I18N.DISPLAY.propertyCategoryOutput());

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

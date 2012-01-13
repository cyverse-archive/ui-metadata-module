package org.iplantc.core.metadata.client.property;

import java.util.Collection;

/**
 * An enum to represent property types
 * 
 * @author sriram
 *
 */
public enum PropertyTypeCategory {
    INPUT(DataObject.INPUT_TYPE), 
    STRING("String"), 
    NUMBER("Number"), 
    BOOLEAN("Boolean"), 
    OUTPUT(DataObject.OUTPUT_TYPE);

    private String displayText;

    private PropertyTypeCategory(String displayText) {
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
            if (s.toUpperCase().equals(name())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return displayText;
    }
}

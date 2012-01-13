package org.iplantc.core.metadata.client.property;

import org.iplantc.de.client.I18N;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * A model that represents a Property meta data
 * 
 * @author sriram
 *
 */
@SuppressWarnings("serial")
public class PropertyData extends BaseModelData implements Comparable<PropertyData> {
    public static String LABEL = "label"; //$NON-NLS-1$
    public static String ORDER = "order"; //$NON-NLS-1$
    public static String CMD_SWITCH = "cmdSwitch"; //$NON-NLS-1$
    public static String PROPERTY = "property"; //$NON-NLS-1$

    private static String PROPERTY_TYPE_BOOLEAN = "Flag"; //$NON-NLS-1$

    private final Property property;

    /**
     * create a new instance of PropertyData
     * 
     * @param property
     */
    public PropertyData(Property property) {
        this.property = property;

        set(PROPERTY, property);
        set(ORDER, getOrder());
        set(CMD_SWITCH, getCmdSwitch());
        set(LABEL, getLabel());
    }

    /**
     * get the Property meta data object
     * 
     * @return Property object
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Gets a string describing this parameter.
     * 
     * @return A label for this parameter.
     */
    public String getLabel() {
        if (property != null) {
            String dataObjectLabel = getDataObjectLabel();
            if (!stringIsNullOrEmpty(dataObjectLabel)) {
                // return a DataObject type label.
                return dataObjectLabel;
            }

            String cmdOption = property.getName();
            if (!stringIsNullOrEmpty(cmdOption)) {
                // return a label with the command line argument followed by the default value.
                cmdOption = cmdOption.trim();

                if (PROPERTY_TYPE_BOOLEAN.equals(property.getType())) {
                    // return a Boolean type label that only displays the cmd option.
                    return cmdOption;
                }

                return cmdOption + " " + getDefaultValueLabel(); //$NON-NLS-1$
            }

            if (property.getLabel() != null) {
                // return the property's label.
                return property.getLabel();
            }
        }

        // return an empty string by default.
        return ""; //$NON-NLS-1$
    }

    private String getDataObjectLabel() {
        String propertyType = property.getType();

        if (DataObject.INPUT_TYPE.equals(propertyType) || DataObject.OUTPUT_TYPE.equals(propertyType)) {
            // return a DataObject type label.
            if (property.getDataObject() != null) {
                return property.getDataObject().getCmdLinePreviewLabel();
            }
        }

        return null;
    }

    private String getDefaultValueLabel() {
        if (stringIsNullOrEmpty(property.getValue())) {
            return "(" + I18N.DISPLAY.valueParenS() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        return property.getValue();
    }

    private boolean stringIsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Gets the command line order for this parameter, starting with 1, or -1 if this parameter is
     * unordered.
     * 
     * @return The command line order of this parameter.
     */
    public int getOrder() {
        return property == null ? -1 : property.getOrder();
    }

    /**
     * Sets the command line order for this parameter.
     * 
     * @param order
     */
    public void setOrder(int order) {
        if (property != null) {
            property.setOrder(order);
        }

        set(ORDER, order);
    }

    /**
     * Gets the command line option or switch for this parameter.
     * 
     * @return This parameter's command line switch.
     */
    public String getCmdSwitch() {
        return property == null ? "" : property.getName(); //$NON-NLS-1$
    }

    /**
     * Sets the command line option or switch for this parameter.
     * 
     * @param cmdSwitch
     */
    public void setCmdSwitch(String cmdSwitch) {
        if (property != null) {
            property.setName(cmdSwitch);
        }

        set(CMD_SWITCH, cmdSwitch);
    }

    @Override
    public int compareTo(PropertyData property) {
        if (property == null) {
            return 1;
        }

        Integer order = getOrder();

        return order.compareTo(property.getOrder());
    }

}

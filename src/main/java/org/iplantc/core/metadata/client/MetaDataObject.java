package org.iplantc.core.metadata.client;

/**
 * Base metadata object for driving the UI.
 * 
 * @author amuir
 * 
 */
public class MetaDataObject {
    protected String id = new String();
    protected String name = new String();
    protected String desc = new String();
    protected String type = new String();
    protected String label = new String();
    protected boolean isVisible;

    /**
     * Default constructor.
     */
    protected MetaDataObject() {
    }

    /**
     * Retrieve id.
     * 
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set value for id.
     * 
     * @param id a string identifying the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieve name.
     * 
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set value for name.
     * 
     * @param name a string identifying the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve description.
     * 
     * @return description.
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Set value for description
     * 
     * @param description a string identifying the description.
     */
    public void setDescription(String description) {
        desc = description;
    }

    /**
     * Retrieve type.
     * 
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set value for type.
     * 
     * @param type a string representing the type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieve our label.
     * 
     * @return string representing label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set our label.
     * 
     * @param label label string.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Retrieve visible flag.
     * 
     * @return true if object is visible.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Set object's visibility.
     * 
     * @param isVisible true if object is visible.
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}

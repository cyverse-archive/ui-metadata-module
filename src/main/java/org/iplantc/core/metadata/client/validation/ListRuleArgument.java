package org.iplantc.core.metadata.client.validation;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * An AutoBean interface defining a MustContain rule argument for TreeSelection properties.
 * 
 * @author psarando
 * 
 */
public interface ListRuleArgument {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getValue();

    public void setValue(String value);

    public String getDisplay();

    public void setDisplay(String display);

    public String getDescription();

    public void setDescription(String description);

    @PropertyName("isDefault")
    public boolean isDefault();

    @PropertyName("isDefault")
    public void setDefault(boolean isDefault);
}

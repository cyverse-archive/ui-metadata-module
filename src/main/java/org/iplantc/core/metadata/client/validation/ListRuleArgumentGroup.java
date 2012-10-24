package org.iplantc.core.metadata.client.validation;

import java.util.List;

import org.iplantc.core.metadata.client.I18N;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * An AutoBean interface for ListRuleArgument groups.
 * 
 * @author psarando
 * 
 */
public interface ListRuleArgumentGroup extends ListRuleArgument {

    public enum CheckCascade {
        TRI(I18N.DISPLAY.treeSelectorCascadeTri()),
        PARENTS(I18N.DISPLAY.treeSelectorCascadeParent()),
        CHILDREN(I18N.DISPLAY.treeSelectorCascadeChildren()),
        NONE(I18N.DISPLAY.treeSelectorCascadeNone());

        private final String display;

        private CheckCascade(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }

    public List<ListRuleArgumentGroup> getGroups();

    public void setGroups(List<ListRuleArgumentGroup> groups);

    public List<ListRuleArgument> getArguments();

    public void setArguments(List<ListRuleArgument> arguments);

    @PropertyName("isSingleSelect")
    public boolean isSingleSelect();

    @PropertyName("isSingleSelect")
    public void setSingleSelect(boolean isSingleSelect);

    public CheckCascade getSelectionCascade();

    public void setSelectionCascade(CheckCascade cascade);
}

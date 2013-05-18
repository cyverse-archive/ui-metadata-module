package org.iplantc.core.metadata.client.validation;

import java.util.List;

import org.iplantc.core.resources.client.messages.I18N;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * An AutoBean interface for ListRuleArgument groups.
 *
 * @author psarando
 *
 */
public interface ListRuleArgumentGroup extends ListRuleArgument {

    public enum CheckCascade {
        TRI(Tree.CheckCascade.TRI, I18N.DISPLAY.treeSelectorCascadeTri()),
        PARENTS(Tree.CheckCascade.PARENTS, I18N.DISPLAY.treeSelectorCascadeParent()),
        CHILDREN(Tree.CheckCascade.CHILDREN, I18N.DISPLAY.treeSelectorCascadeChildren()),
        NONE(Tree.CheckCascade.NONE, I18N.DISPLAY.treeSelectorCascadeNone());

        private final Tree.CheckCascade cascade;
        private final String display;

        private CheckCascade(Tree.CheckCascade cascade, String display) {
            this.cascade = cascade;
            this.display = display;
        }

        public Tree.CheckCascade getTreeCheckCascade() {
            return cascade;
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

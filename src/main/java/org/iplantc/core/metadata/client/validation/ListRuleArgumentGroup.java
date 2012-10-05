package org.iplantc.core.metadata.client.validation;

import java.util.List;

/**
 * An AutoBean interface for ListRuleArgument groups.
 * 
 * @author psarando
 * 
 */
public interface ListRuleArgumentGroup extends ListRuleArgument {

    public List<ListRuleArgumentGroup> getGroups();

    public void setGroups(List<ListRuleArgumentGroup> groups);

    public List<ListRuleArgument> getArguments();

    public void setArguments(List<ListRuleArgument> arguments);
}

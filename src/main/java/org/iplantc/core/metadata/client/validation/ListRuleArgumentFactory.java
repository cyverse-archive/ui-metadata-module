package org.iplantc.core.metadata.client.validation;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * An AutoBeanFactory for ListRuleArguments.
 * 
 * @author psarando
 * 
 */
public interface ListRuleArgumentFactory extends AutoBeanFactory {

    public AutoBean<ListRuleArgumentGroup> group();

    public AutoBean<ListRuleArgument> argument();
}

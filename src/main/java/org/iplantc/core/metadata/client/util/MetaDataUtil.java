package org.iplantc.core.metadata.client.util;

import java.util.List;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.metadata.client.property.Property;
import org.iplantc.core.metadata.client.validation.MetaDataRule;
import org.iplantc.core.metadata.client.validation.MetaDataValidator;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class MetaDataUtil {
    public static void convertMustContainValues(Property property) {
        MetaDataRule ruleMustContain = getMustContainRule(property);

        if (ruleMustContain == null) {
            return;
        }

        // Check for old MustContain rule comma separated value fields in the property's name.
        String[] nameList = null;
        if (property.getName() != null) {
            nameList = property.getName().split(","); //$NON-NLS-1$

            // clear out the old name
            property.setName(""); //$NON-NLS-1$
        }

        List<JSONValue> params = ruleMustContain.getParams();
        for (int index = 0,ruleCount = params.size(); index < ruleCount; index++) {
            JSONValue jsonRule = params.get(index);
            JSONObject rule = jsonRule.isObject();

            // Check for old MustContain rules and convert them into a compatible format.
            if (jsonRule.isString() != null || jsonRule.isNumber() != null) {
                String name;
                String value = ""; //$NON-NLS-1$

                String displayString;
                if (jsonRule.isString() != null) {
                    displayString = jsonRule.isString().stringValue();
                } else {
                    displayString = jsonRule.isNumber().toString();
                }

                if (nameList != null && nameList.length == ruleCount) {
                    // nameList must be a list of values and the rules are their display strings.
                    name = nameList[index].trim();

                    // check if this option can be broken down further into an opt-value pair.
                    String[] argList = name.split("\\s+|="); //$NON-NLS-1$
                    if (argList.length > 1) {
                        // value is everything after the first space, pipe, or equal.
                        value = name.substring(argList[0].length() + 1);
                        name = argList[0];
                    }
                } else {
                    // The MustContain rule strings are both the display and value strings.
                    name = displayString;
                }

                rule = new JSONObject();
                rule.put(MetaDataRule.DISPLAY, jsonRule);
                rule.put(MetaDataRule.NAME, new JSONString(name));
                rule.put(MetaDataRule.VALUE, new JSONString(value));
                rule.put(MetaDataRule.IS_DEFAULT, JSONBoolean.getInstance(false));
            }

            params.set(index, rule);

            if (JsonUtil.getBoolean(rule, MetaDataRule.IS_DEFAULT, false)
                    && (property.getValue() == null || property.getValue().isEmpty())) {
                property.setValue(rule.toString());
            }
        }
    }

    /**
     * @return The first MustContain rule found in the property validator.
     */
    protected static MetaDataRule getMustContainRule(Property property) {
        if (property != null) {
            MetaDataValidator validator = property.getValidator();

            if (validator != null && validator.getRules() != null) {
                for (MetaDataRule rule : validator.getRules()) {
                    if (rule.getType().equalsIgnoreCase("MustContain")) { //$NON-NLS-1$
                        return rule;
                    }
                }
            }
        }

        return null;
    }

}

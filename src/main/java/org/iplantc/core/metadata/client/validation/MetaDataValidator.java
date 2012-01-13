package org.iplantc.core.metadata.client.validation;

import java.util.ArrayList;
import java.util.List;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.metadata.client.JSONMetaDataObject;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * Metadata validator definition.
 * 
 * @author amuir
 * 
 */
public class MetaDataValidator extends JSONMetaDataObject {
    private boolean isRequired;
    private List<MetaDataRule> rules;

    /**
     * Instantiate a validator that contains no rules.
     */
    public MetaDataValidator() {
        super("{}");
    }

    /**
     * Instantiate from a JSONObject.
     * 
     * @param json instantiating object.
     */
    public MetaDataValidator(JSONObject json) {
        super(json);
    }

    public void addRule(MetaDataRule rule) {
        if (rule != null) {
            rules.add(rule);
        }
    }

    public void removeRule(MetaDataRule rule) {
        rules.remove(rule);
    }

    private void parseRulesFromJsonArray(JSONArray arr) {
        if (arr != null) {
            for (int i = 0,len = arr.size(); i < len; i++) {
                JSONObject objProperty = JsonUtil.getObjectAt(arr, i);

                addRule(new MetaDataRule(objProperty));
            }
        }
    }

    private void buildRules(JSONObject json) {
        JSONValue val = json.get("rules");

        if (val != null) {
            JSONArray jsonRules = val.isArray();

            parseRulesFromJsonArray(jsonRules);
        }
    }

    private void setRequired(JSONObject json) {
        if (json != null) {
            JSONValue val = json.get("required");

            if (val != null) {
                isRequired = val.isBoolean().booleanValue();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeParse() {
        super.beforeParse();

        rules = new ArrayList<MetaDataRule>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseData(JSONObject json) {
        super.parseData(json);

        setRequired(json);
        buildRules(json);
    }

    /**
     * Retrieve required flag.
     * 
     * @return required flag.
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Set validator's required flag.
     * 
     * @param isRequired true if field requires user input
     */
    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * Retrieve rules.
     * 
     * @return list of rules.
     */
    public List<MetaDataRule> getRules() {
        return rules;
    }

    /**
     * Retrieve rule count.
     * 
     * @return number of rules associated with this validator.
     */
    public int getNumRules() {
        return rules.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("required", JSONBoolean.getInstance(isRequired));

        List<JSONValue> ruleArray = new ArrayList<JSONValue>();
        for (MetaDataRule rule : rules) {
            ruleArray.add(rule.toJSON());
        }
        json.put("rules", JsonUtil.buildArray(ruleArray));
        return json;
    }

    @Override
    public String toString() {
        return getName();
    }
}

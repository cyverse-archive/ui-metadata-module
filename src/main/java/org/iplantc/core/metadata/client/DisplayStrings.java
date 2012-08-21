package org.iplantc.core.metadata.client;

import org.iplantc.de.client.CommonDisplayStrings;

/**
 * Defines a set of common metadata display strings.
 * 
 * @author psarando
 * 
 */
public interface DisplayStrings extends CommonDisplayStrings {

    /**
     * Localized text for the Property Category "String".
     * 
     * @return string representing the text
     */
    String propertyCategoryString();

    /**
     * Localized text for the Property Category "Number".
     * 
     * @return string representing the text
     */
    String propertyCategoryNumber();

    /**
     * Localized text for the Property Category "Boolean".
     * 
     * @return string representing the text
     */
    String propertyCategoryBoolean();

    /**
     * Localized text for the Property Category "Environment Variable".
     * 
     * @return string representing the text
     */
    String propertyCategoryEnvironmentVariable();

    /**
     * Localized text for the Property Category "Input".
     * 
     * @return string representing the text
     */
    String propertyCategoryInput();

    /**
     * Localized text for the Property Category "Output".
     * 
     * @return string representing the text
     */
    String propertyCategoryOutput();
}

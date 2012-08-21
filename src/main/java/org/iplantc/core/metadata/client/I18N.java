package org.iplantc.core.metadata.client;

import com.google.gwt.core.client.GWT;

/**
 * Provides static access to localized strings.
 * 
 * @author psarando
 * 
 */
public class I18N {
    // Strings displayed in the UI
    public static final DisplayStrings DISPLAY = (DisplayStrings)GWT.create(DisplayStrings.class);
}

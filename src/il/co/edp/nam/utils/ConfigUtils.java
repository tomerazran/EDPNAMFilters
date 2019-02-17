package il.co.edp.nam.utils;

import javax.servlet.FilterConfig;

public class ConfigUtils {

	/**
     * This method returns the parameter's value if it exists, or defaultValue
     * if not.
     *
     * @param filterConfig  The configuration for the filter
     *
     * @param name          The parameter's name
     *
     * @param defaultValue  The default value to return if the parameter does
     *                      not exist
     *
     * @return The parameter's value or the default value if the parameter does
     *         not exist
     */
    public static String getInitParameter(FilterConfig filterConfig, String name, String defaultValue) {

        if (filterConfig == null) {
            return defaultValue;
        }

        String value = filterConfig.getInitParameter(name);
        if (value != null) {
            return value;
        }

        return defaultValue;
    }

    
}

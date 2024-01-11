package com.expeditors.householddataprocessing.utils;

/**
 * The common utilities.
 */
public class CommonUtils {

    /**
     * Returns the string with the object wrapped in double quotes.
     *
     * @param obj the object
     * @return the string with the object wrapped in double quotes
     */
    public static String quote(Object obj) {
        return "\"" + obj.toString() + "\"";
    }
}

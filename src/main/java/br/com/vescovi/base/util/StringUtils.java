package br.com.vescovi.base.util;

public class StringUtils {

    public static String onlyNumbers(String value){
        if (value == null){
            return null;
        }
        return value.replaceAll("[^\\d]", "");
    }

}

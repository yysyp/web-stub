package com.ddm.formbean;

/**
 * Created by yunpeng.song on 6/20/2018.
 */


public class FormBeanUtils
{
//    /**
//     * A utility function, takes a standard JavaBean property
//     * name and converts it to a nice US English spacing.
//     *
//     * For example, firstName = First Name   */
//    public static String formatName(String in)
//    {
//        String result = new String();
//
//        for (int i = 0; i < in.length(); i++)
//        {
//            if (Character.isUpperCase(in.charAt(i)))
//            { result = result + (" "); }
//            result = result + (in.charAt(i) + "");
//        }
//
//        String result2 = new String();
//
//        for (int i = 0; i < result.length(); i++)
//        {
//            if (Character.isDigit(result.charAt(i)))
//            { result2 = result2 + (" "); }
//            result2 = result2 + (result.charAt(i) + "");
//        }
//
//        char titleChar = result2.charAt(0);
//        String result3 = Character.toUpperCase(titleChar) + "";
//
//        result3 = result3
//                + (result2.substring(1, result2.length()));
//
//        return result3;
//    }
//
//    /**
//     * A utility method, used to add an attribute in the form
//     * attribute='value' with a space afterward.
//     */
//    public static void appendAttribute(StringBuffer in,
//                                       String attribute, String value)
//    {
//        in.append(attribute);
//        in.append("='");
//        in.append(value);
//        in.append("' ");
//    }
//
//    static private boolean convertersInitialized = false;
//
//    static public void initConverters()
//    {
//        if (!convertersInitialized)
//        {
//            // No-args constructor gets the version that throws
//            // exceptions
//            Converter myConverter = new IntegerConverter();
//
//            // Convert the primitive values
//            ConvertUtils.register(myConverter, Integer.TYPE);
//
//            // Convert the object version
//            ConvertUtils.register(myConverter, Integer.class);
//            convertersInitialized = true;
//        }
//    }
}
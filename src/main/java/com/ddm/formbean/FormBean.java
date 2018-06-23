package com.ddm.formbean;

/**
 * Created by yunpeng.song on 6/20/2018.
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



public class FormBean
{
//    private HashMap errors = new HashMap();
//
//    // The class refers to the compiled version of the class
//    private Class baseClass;
//    private DynaClass dynaClass;
//
//    // The object refers to the runtime (in-memory) version
//    private Object baseObject;
//    private DynaBean dynaObject;
//
//    private DynaProperty[] properties;
//
//    // Used to help format the resulting text boxes
//    private int displayStringLength = 40;
//    private int maxStringLength = 100;
//
//    /** For creation forms */
//    public FormBean(Class myClass)
//    {
//        baseClass = myClass;
//        dynaClass = WrapDynaClass.createDynaClass(baseClass);
//        properties = dynaClass.getDynaProperties();
//        try
//        {
//            baseObject = myClass.newInstance();
//            dynaObject = new WrapDynaBean(baseObject);
//        } catch (Exception e)
//        {
//            System.err
//                    .println("FATAL ERROR: Unable to instantiate "
//                            + dynaClass.getName());
//            e.printStackTrace();
//        }
//    }
//
//    /** For update forms */
//    public FormBean(Class myClass, Object myObject)
//    {
//        baseObject = myObject;
//        dynaObject = new WrapDynaBean(baseObject);
//        baseClass = myClass;
//        dynaClass = WrapDynaClass.createDynaClass(baseClass);
//        properties = dynaClass.getDynaProperties();
//    }
//
//    /** Converts the object into a simple HTML form. */
//    public String toHTMLForm()
//    {
//        StringBuffer output = new StringBuffer();
//
//        for (int i = 0; i < properties.length; i++)
//        {
//            String currentProperty = properties[i].getName();
//            if (currentProperty.compareTo("class") != 0)
//            {
//
//                // Start the row
//                output.append("<tr>");
//
//                // The cell for the label
//                output.append("<td>");
//                output.append(FormBeanUtils
//                        .formatName(currentProperty));
//                output.append("</td>");
//
//                // The cell for the input form element
//                output.append("<td>");
//                output.append("<input ");
//                FormBeanUtils.appendAttribute(output, "name",
//                        currentProperty);
//
//                // The cell for the current value, if there is
//                // one
//                if (this.dynaObject.get(currentProperty) != null)
//                {
//                    FormBeanUtils.appendAttribute(output,
//                            "value", this.dynaObject.get(
//                                    currentProperty).toString());
//                }
//
//                // Finish the input cell
//                FormBeanUtils.appendAttribute(output, "size",
//                        displayStringLength + "");
//                FormBeanUtils.appendAttribute(output,
//                        "maxlength", maxStringLength + "");
//                output.append(">");
//                output.append("</td>");
//
//                // This cell displays any errors for this
//                // property
//                output.append("<td class='error'>");
//                if (errors.containsKey(currentProperty))
//                {
//                    output.append(errors.get(currentProperty)
//                            .toString());
//                }
//                output.append("&nbsp;</td>");
//
//                // Finish up this row
//                output.append("</tr>");
//            }
//        }
//
//        return output.toString();
//    }
//
//    /**
//     * Returns true if all of the values pass validation.
//     * Otherwise, returns false (the user should therefore be
//     * prompted to correct the errors).
//     *
//     * The incoming Map should contain a set of values, where the
//     * incoming values are a single key String and the values are
//     * String[] objects.
//     */
//    public boolean updateValue(Map in)
//    {
//        // Initialize the converters - we want format exceptions.
//        FormBeanUtils.initConverters();
//
//        boolean isGoodUpdate = true;
//
//        for (int i = 0; i < properties.length; i++)
//        {
//            String key = properties[i].getName();
//            Object value = in.get(key);
//            try
//            {
//                BeanUtils.setProperty(baseObject, key, value);
//            } catch (Exception e)
//            {
//                if (value != null)
//                {
//                    errors.put(key, "Value of "
//                            + FormBeanUtils.formatName(key)
//                            + " may not be '"
//                            + ((String[]) value)[0].toString()
//                            + "'");
//                } else
//                    errors.put(key, "Value may not be null");
//                isGoodUpdate = false;
//            }
//        }
//
//        return isGoodUpdate;
//    }
//
//    /**
//     * Returns true if all of the values pass validation.
//     * Otherwise, returns false (the user should therefore be
//     * prompted to correct the errors).
//     */
//    public boolean updateValue(HttpServletRequest request)
//    {
//        Map in = request.getParameterMap();
//        return this.updateValue(in);
//    }
//
//    /**
//     * Note that this particular design allows you to test your
//     * bean programmatically, outside of the context of a web
//     * application server.
//     */
//    public static void main(String[] args)
//    {
//        FormBean myFormBean = new FormBean(User.class);
//        System.out.println(myFormBean.toHTMLForm());
//
//        User myUser = new User();
//        myUser.setClearance(5);
//        myUser.setFirstName("Bob");
//        myUser.setLastName("Smith");
//
//        myFormBean = new FormBean(User.class, new WrapDynaBean(myUser));
//        System.out.println(myFormBean.toHTMLForm());
//
//        Map myMap = new HashMap();
//        myMap.put("firstName", new String[] { "Ralph"});
//        myMap.put("lastName", new String[] { "Bingo"});
//        myMap.put("clearance", new String[] { "5"});
//        myFormBean.updateValue(myMap);
//        System.out.println(myFormBean.toHTMLForm());
//
//        myMap.remove("clearance");
//        myMap.put("clearance", new String[] { "invalid"});
//        myFormBean.updateValue(myMap);
//        System.out.println(myFormBean.toHTMLForm());
//    }
}

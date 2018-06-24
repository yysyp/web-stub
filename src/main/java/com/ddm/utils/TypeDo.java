package com.ddm.utils;


public class TypeDo {

	/**
	 * 
	 * @param c
	 * @return
	 */
		public static boolean isSimpleTypeByClass(Class c) {
			if(c.isPrimitive()) return true;
			if(Byte.class.equals(c)
					||Short.class.equals(c)
					||Character.class.equals(c)
					||Integer.class.equals(c)
					||Long.class.equals(c)
					||Float.class.equals(c)
					||Double.class.equals(c)
					//
					||Number.class.equals(c)
					||String.class.equals(c)
					||java.util.Date.class.equals(c)
					) return true;
			return false;
		}
		
		/**
		 * 
		 * @param o
		 * @return
		 */
		public static boolean isSimpleType(Object o) {
			if(		o instanceof Boolean
					||o instanceof Byte
					||o instanceof Short
					||o instanceof Character
					||o instanceof Integer
					||o instanceof Long
					||o instanceof Float
					||o instanceof Double
					//
					||o instanceof Number
					||o instanceof String
					||o instanceof java.util.Date
					) return true;
			return false;
		}

}

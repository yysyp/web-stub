package com.ddm.utils;


public class EscapeDo {

	/**
	 * 
	 * @param fileName
	 * @param ch
	 * @return
	 */
	public static String escapeWinFileName(String fileName, String ch) {
		String[] fileNameInvalidChars = {"\\", "/", ":", "*", "?", "\"", "<", ">", "|"};
		for(int i = 0, n = fileNameInvalidChars.length; i < n; i++) {
			if(fileNameInvalidChars[i].equals(ch.trim())) {
				ch = " "; 
				break;
			}
		}
		for(int i = 0, n = fileNameInvalidChars.length; i < n; i++) {
			fileName = fileName.replaceAll(escapeRegex(fileNameInvalidChars[i]), ch);
		}
		return fileName;
	}
	
	/**
	 * escapeRegex(s)
	 * @param s
	 * @return
	 */
	public static String escapeRegex(String s) {
		String[] chs = { "$", "^", "*", "(", ")", "{", "}", "[", "]", "|", "\\", ".", "?" };
		char biasChar = '\\';
		if (s == null || s.trim().equals(""))
			return s;
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = s.length(); i < n; i++) {
			String c = s.charAt(i) + "";
			boolean finded = false;
			for (int j = 0, size = chs.length; j < size; j++) {
				if (chs[j].equals(c)) {
					finded = true;
					break;
				}
			}
			if (finded) {
				buf.append(biasChar);
			}
			buf.append(c);
		}
		return buf.toString();
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String transFistCharToUpper(String s) {
		if(s == null) return null;
		return Character.toUpperCase(s.charAt(0))+s.substring(1);
	}
	
	
	public static String escapeJSONString(Object o) {
		//String hhhh;
		String s = o+"";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\\':
			case '"':
				sb.append('\\');
				sb.append(c);
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				/*if (c < ' ' || (c >= '\u0080' && c < '\u00a0')
						|| (c >= '\u2000' && c < '\u2100')) {
					hhhh = "000" + Integer.toHexString(c);
					sb.append("\\u" + hhhh.substring(hhhh.length() - 4));
				} else {
					sb.append(c);
				}*/
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String escapeXmlString(String s) {
		if (s == null) return s;
		s = s.replaceAll("&", "&amp;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("'", "&apos;");
		s = s.replaceAll("\"", "&quot;");
		return s;
	}
	
}

package com.ddm.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class PrintDo {

	public static String printMapListCode(Object obj) {
		PrintStream out = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			out = new PrintStream(baos);
			if(obj instanceof Map) {
				printMapCode(out, (Map) obj, null);
			} else {
				printListCode(out, (List) obj, null);
			}
			out.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return baos.toString();
	}
	
	private static String getDateFmtString(String fmt) {
		if (fmt == null || fmt.trim().equals(""))
			fmt = "yyyyMMddHHmmss";
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		return df.format(date);
	}
	
	
	private static String getFieldString(Object value) {
		if(value == null) return "null";
		String text = "\""+value+"\"";
		if(value instanceof Boolean) {
			text = "new Boolean(\""+value+"\")";
		} else if(value instanceof Byte) {
			text = "new Byte(\""+value+"\")";
		} else if(value instanceof Character) {
			text = "new Character(\""+value+"\")";
		} else if(value instanceof Integer) {
			text = "new Integer(\""+value+"\")";
		} else if(value instanceof Long) {
			text = "new Long(\""+value+"\")";
		} else if(value instanceof Float) {
			text = "new Float(\""+value+"\")";
		} else if(value instanceof Double) {
			text = "new Double(\""+value+"\")";
		} else if(value instanceof BigDecimal) {
			text = "new BigDecimal(\""+value+"\")";
		} else if(value instanceof Date) {
			text = "new java.util.Date(\""+value+"\")";
		}
		return text;
	}
	private static void printMapCode(PrintStream out, Map map, String name) {
		Object key = null;
		Object value = null;
		if(name == null || name.trim().equals("")) {
			name = "resultMap";
			out.println("Map "+name+" = new HashMap();");
		}
		for(Iterator it = map.keySet().iterator(); it.hasNext();) {
			key = it.next();
			value = map.get(key);
			if(value instanceof Map) {
				String cname = name.charAt(0)+"_"+key;
				out.println("Map "+cname+" = new HashMap();");
				out.println(name+".put(\""+key+"\", "+cname+");");
				printMapCode(out, (Map)value, cname);
			} else if(value instanceof List) {
				String cname = name.charAt(0)+"_"+key;
				out.println("List "+cname+" = new ArrayList();");
				out.println(name+".put(\""+key+"\", "+cname+");");
				printListCode(out, (List)value, cname);
			} else {
				out.println(name+".put(\""+key+"\", "+getFieldString(value)+");");
			}
		}
	}
	private static void printListCode(PrintStream out, List list, String name) {
		if(name == null || name.trim().equals("")) {
			name = "resultList";
			out.println("List "+name+" = new ArrayList();");
		}
		Object value = null;
		for(Iterator it = list.iterator(); it.hasNext();) {
			value = it.next();
			if(value instanceof Map) {
				String cname = name+"_L";
				out.println("Map "+cname+" = new HashMap();");
				out.println(name+".add("+cname+");");
				printMapCode(out, (Map)value, cname);
			} else if(value instanceof List) {
				String cname = name+"_L";
				out.println("List "+cname+" = new ArrayList();");
				out.println("r.add("+cname+");");
				printListCode(out, (List)value, cname);
			} else {
				out.println(name+".add("+getFieldString(value)+");");
			}
		}
	}

	
	public static String getExceptionStackTrace(Throwable e) {
		if (e == null)
			return "";
		java.io.PrintWriter logpw = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			logpw = new java.io.PrintWriter(bos);
			e.printStackTrace(logpw);
			bos.close();
			logpw.close();
			return new String(bos.toByteArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (Exception e1) {
			}
			try {
				logpw.close();
			} catch (Exception e1) {
			}
		}
		return "";
	}
	
	public static String getNewLineChar() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream out = new PrintStream(baos);
			
			out.println();
			String s = baos.toString();
			baos.close();
		return s;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getStackInfo() {
		Throwable ex = new Throwable();
		StackTraceElement[] stackElements = ex.getStackTrace();
		StringBuffer buf = new StringBuffer();
		if (stackElements != null) {
			for (int i = 0; i < stackElements.length; i++) {
				buf.append(stackElements[i].getClassName());
				buf.append(".");
				buf.append(stackElements[i].getMethodName());
				buf.append("(");
				buf.append(stackElements[i].getFileName());
				buf.append(":");
				buf.append(stackElements[i].getLineNumber());
				buf.append(")");
				buf.append("\n");
			}
		}
		return buf.toString();
	}
	
	public static String transFistCharToUpper(String s) {
		if(s == null) return null;
		return Character.toUpperCase(s.charAt(0))+s.substring(1);
	}
	
	/**
	 * 
	 * @param file
	 * @param out
	 * @param indentString
	 * @throws Exception
	 */
	public static void spFmtXmlDoc(String file, PrintStream out, String indentString)throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document doc = db.parse(new File(file));
		Element e = doc.getDocumentElement();
		_spFmtXmlDoc(out, e, 0, 1, indentString, true);
	}
	public static void spFmtXmlDoc(InputStream in, PrintStream out, String indentString)throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document doc = db.parse(in);
		Element e = doc.getDocumentElement();
		_spFmtXmlDoc(out, e, 0, 1, indentString, true);
	}
	private static void _spFmtXmlDoc(PrintStream out, Node node, int indentNumber, int step, String indentString, boolean isLast) {
		if(node == null || Node.TEXT_NODE == node.getNodeType()) {
			if(node.toString().trim().equals("")) { 
				return;
			}
		}
		for(int i = 0; i < indentNumber; i++,out.print(indentString));
		if(node.getNodeType() == Node.COMMENT_NODE) {
			out.println("<!--"+node.getNodeValue()+"-->");
			return;
		}
		if(node.getNodeType() == Node.TEXT_NODE) {
			out.print(node);
			return;
		}
		if(node.getNodeType() == Node.CDATA_SECTION_NODE) {
			out.println();for(int i = 0; i < indentNumber; i++,out.print(indentString));
			out.println("<![CDATA["+node.getNodeValue()+"]]>");
			return;
		}
		
		//3
		NodeList nl = node.getChildNodes();
		if(nl.getLength() == 0) {//31
			if(node.getAttributes() == null || node.getAttributes().getLength() == 0) {
				out.print("<"+node.getNodeName()+"></"+node.getNodeName()+">");
				
			} else {
				out.print("<"+node.getNodeName()+node.getAttributes()+"></"+node.getNodeName()+">");
			}
			if(!isLast) out.println("");
			return;
		} else if(nl.getLength() == 1 && nl.item(0) != null && nl.item(0).getNodeType() == Node.TEXT_NODE) {//32
			if(node.getAttributes() == null || node.getAttributes().getLength() == 0) {
				out.print("<"+node.getNodeName()+">"+nl.item(0).getNodeValue()+"</"+node.getNodeName()+">");
			} else {
				out.print("<"+node.getNodeName()+node.getAttributes()+">"+nl.item(0).getNodeValue()+"</"+node.getNodeName()+">");
			}
			if(!isLast) out.println("");
			return;
		}
		
		//33
		if(node.getAttributes() == null || node.getAttributes().getLength() == 0) {
			out.println("<"+node.getNodeName()+">");
		} else {
			out.println("<"+node.getNodeName()+node.getAttributes()+">");
		}
		
		for(int i = 0, n = nl.getLength(); i < n; i++) {
			Node cNode = nl.item(i);
			_spFmtXmlDoc(out, cNode, indentNumber+step, step, indentString, i+1 >= n);
		}
		
		out.println();
		for(int i = 0; i < indentNumber; i++,out.print(indentString));
		out.print("</"+node.getNodeName()+">");
		if(!isLast) out.println("");
	}
	
	
	/**
	 * Fetch data in [startAtRow, startAtRow+pageSize) Ex: [1, 10) -> [11, 10) -> [21, 10)
	 * 
	 * @param sqlSelect
	 * @param sqlOrderBy
	 * @param startAtRow
	 * @param pageSize
	 * @return
	 */
	public static String getPagingSql(String sqlSelect, String sqlOrderBy,
			int startAtRow, int pageSize) {
		
		if(sqlOrderBy != null && !sqlOrderBy.trim().equals("")) {
			if(!sqlOrderBy.toUpperCase().trim().equals("ORDER BY")) {
				sqlOrderBy = " ORDER BY " + sqlOrderBy;
			}
		}
		
		return (pageSize < 0) ? " SELECT * FROM ( " + sqlSelect
				+ " )" + sqlOrderBy
				: "SELECT PAGINGT2.* FROM ( SELECT PAGINGT1.*, ROWNUM AS RN FROM ( "
						+ sqlSelect
						+ sqlOrderBy
						+ " ) PAGINGT1 WHERE ROWNUM < "
						+ (startAtRow + pageSize)
						+ " ) PAGINGT2 WHERE PAGINGT2.RN >= "
						+ startAtRow
						+ sqlOrderBy + " ";
	}
	
	
	public static String getStringByObject(Object o) {
		if(o == null) return "";
		if(o instanceof String
				|| o instanceof StringBuffer) return o+"";
		if(o instanceof Date) {
			java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(o);
		}
		if(TypeDo.isSimpleType(o)) return o+"";
		if(o instanceof List) return ""+o;
		if(o instanceof Set) return ""+o;
		if(o instanceof Map) return ""+o;
		
		Map dtoMap = null;
		try {
			java.beans.PropertyDescriptor[] des = java.beans.Introspector
					.getBeanInfo(o.getClass()).getPropertyDescriptors();
			dtoMap = new java.util.TreeMap();
			java.beans.PropertyDescriptor pd = null;
			java.lang.reflect.Method rm = null;
			for (int i = 0, l = des.length; i < l; i++) {
				pd = des[i];
				rm = pd.getReadMethod();
				if (rm == null)
					continue;
				try {
					dtoMap.put(pd.getName(), rm.invoke(o, new Object[0]));
				} catch (Exception e) {}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "{"+dtoMap+"}";
	}
	
	public static String convertMapListSetToXml(Object mls) {
		return convertMapListSetToXml(mls, null);
	}
	public static String convertMapListSetToXml(Object mls, String encode) {
		if(encode == null) encode = "UTF-8";
		StringBuffer buf = new StringBuffer();
		buf.append("<?xml version=\"1.0\" encoding=\""+encode+"\"?>\n<root>");
		convertMapListSetToXml(mls, buf, 1, true);
		buf.append("\n</root>");
		return buf.toString();
	}
	/**
	 * <pre>
	 * <?xml version="1.0" encoding="UTF-8" ?>
	 * "    "
	 * </pre>
	 * @param mls
	 * @param buf
	 * @param t
	 */
	public static void convertMapListSetToXml(Object mls, StringBuffer buf, int t, boolean escapeXml) {
		//StringBuffer buf = new StringBuffer();
		StringBuffer sp = new StringBuffer();
		sp.append('\n');
		for(int i = 0; i < t; i++) {
			sp.append("    ");
		}
		if(mls instanceof Map) {
			Map m = (Map) mls;
			Object key = null;
			Object value = null;
			for(Iterator it = m.keySet().iterator(); it.hasNext();) {
				key = it.next();
				value = m.get(key);
				String s = String.valueOf(key);
				if(escapeXml) {
					s = EscapeDo.escapeXmlString(s);
				}
				buf.append(sp.toString()+"<"+s+">");
				//buf.append(convertMapListSetToXml(value, t+1));
				convertMapListSetToXml(value, buf, t+1, escapeXml);
				if(value instanceof Map || value instanceof List || value instanceof Set) {
					buf.append(sp.toString());
				}
				buf.append("</"+s+">");
			}
		} else if(mls instanceof List) {
			List l = (List) mls;
			Object value = null;
			for(int i = 0, n = l.size(); i < n; i++) {
				value = l.get(i);
				//buf.append(sp.toString()+"<list-"+i+">");
				//buf.append(convertMapListSetToXml(value, t+1));
				if(!(value instanceof Map || value instanceof List || value instanceof Set)) {
					buf.append(sp.toString());
				}
				convertMapListSetToXml(value, buf, t, escapeXml);
				//buf.append("</list-"+i+">");
			}
		} else if(mls instanceof Set) {
			Set s = (Set) mls;
			int i = 0;
			Object value = null;
			for(Iterator it = s.iterator(); it.hasNext();) {
				value = it.next();
				//buf.append(sp.toString()+"<set-"+i+">");
				//buf.append(convertMapListSetToXml(value, t+1));
				if(!(value instanceof Map || value instanceof List || value instanceof Set)) {
					buf.append(sp.toString());
				}
				convertMapListSetToXml(value, buf, t, escapeXml);
				//buf.append("</set-"+i+">");
				i++;
			}
		} else {
			String s = String.valueOf(mls);
			if(escapeXml) {
				s = EscapeDo.escapeXmlString(s);
			}
			buf.append(s);
		}
		//return buf.toString();
	}
	

}

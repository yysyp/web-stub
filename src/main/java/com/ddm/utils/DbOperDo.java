package com.ddm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DbOperDo {

	// update
	public static boolean execute(Connection con, String sql, Object a[]) throws RuntimeException {
		PreparedStatement stmt = null;
		boolean f = false;
		try {
			stmt = con.prepareStatement(sql);
			if (a != null) {
				for (int i = 0; i < a.length; i++) {
					if (a[i] instanceof java.util.Date) {
						stmt.setObject(i + 1, TimeDo.getTimeStamp(a[i], null));
					} else {
						stmt.setObject(i + 1, a[i]);
					}
				}
			}
			f = stmt.execute();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeStmt(stmt);
		}
		return f;
	}

	/**
	 * //////////////////////////////////////////////////DB Begin//////////////////////////////////////////////////
	 * 
	 */
	public static List queryData(Connection con, String sql) {
		return queryData(con, sql, null);
	}

	public static List queryData(Connection con, String sql, Object a[]) throws RuntimeException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// List tb = null;

		try {
			stmt = con.prepareStatement(sql);
			if (a != null) {
				for (int i = 0; i < a.length; i++) {
					if (a[i] instanceof java.util.Date) {
						stmt.setObject(i + 1, TimeDo.getTimeStamp(a[i], null));
					} else {
						stmt.setObject(i + 1, a[i]);
					}
				}
			}
			rs = stmt.executeQuery();
			return populate2Rows(rs);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeRs(rs);
			closeStmt(stmt);
			// closeCon(con);
		}
		// return tb;
	}

	public static List populate2Rows(ResultSet rs) throws RuntimeException {
		List tb = new ArrayList();
		if (rs == null)
			return tb;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();

			RsRow row = null;
			int rowCount = 0;

			while (rs.next()) {
				row = new RsRow(colnum);
				rowCount++;
				for (int c = 0; c < colnum; c++) {
					row.setData(rowCount, c, rsmd.getColumnLabel(c + 1), rs.getObject(c + 1));
				}
				tb.add(row);
			}

			if (tb.isEmpty()) {// is resultset is empty then add a row.
				RsRow title = new RsRow(colnum);
				for (int c = 0; c < colnum; c++) {
					title.setData(-1, c, rsmd.getColumnLabel(c + 1), null);
					// rsmd.getColumnType(column)
				}
				tb.add(title);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return tb;
	}

	public static List<Map<String, Object>> populate2TreeMaps(ResultSet rs) throws RuntimeException {
		List<Map<String, Object>> tb = new ArrayList();
		if (rs == null)
			return tb;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();

			Map<String, Object> row = null;

			while (rs.next()) {
				row = new TreeMap();
				for (int c = 0; c < colnum; c++) {
					row.put(rsmd.getColumnLabel(c + 1), rs.getObject(c + 1));
				}
				tb.add(row);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return tb;
	}

	public static void closeRs(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
		}
	}

	public static void closeStmt(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
		}
	}

	public static void closeCon(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
		}
	}

	public static void closeRsStmtCon(ResultSet rs, PreparedStatement stmt, Connection con) {
		closeRs(rs);
		closeStmt(stmt);
		closeCon(con);
	}

	public static class RsRow {

		int rowIndex = 1;

		private Object[] data = null;

		private Map mapData = null;

		// RsRow(){}
		RsRow(int columnCount) {
			data = new Object[columnCount];
			mapData = new TreeMap();// sort by key
		}

		void setData(int rowIndex, int column, String columnLabel, Object o) {
			this.rowIndex = rowIndex;
			data[column] = o;
			mapData.put(columnLabel, data[column]);
		}

		public Object getData(int column) {
			return data[column];
		}

		public Object getData(String columnLabel) {
			return mapData.get(columnLabel);
		}

		public Object[] getData() {
			return data;
		}

		public Map getMapData() {
			return mapData;
		}

		public int getRowIndex() {
			return this.rowIndex;
		}

		public String toString() {
			// return Tus.getNewLineChar() + rowIndex + " " + labelmap;
			return "<" + rowIndex + ">:" + mapData;
		}
	}

	public static String getTableScheme(Connection con, String tableName) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List tb = null;

		StringBuffer buf = new StringBuffer();
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE 1 = 2";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			buf.append(" CREATE TABLE " + tableName);
			buf.append("\n (\n");

			int colnum = rsmd.getColumnCount();
			for (int i = 0; i < colnum; i++) {
				// fields.put(rsmd.getColumnName(i+1), rsmd.getColumnLabel(i+1));
				buf.append(rsmd.getColumnName(i + 1));

				buf.append("  ");
				buf.append(rsmd.getColumnTypeName(i + 1));
				if (rsmd.isAutoIncrement(i + 1)) {
					buf.append(" /*** isAutoIncrement */ ");
				}

				if (i + 1 < colnum)
					buf.append(", \n");

			}
			buf.append("\n ); \n");

		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeRs(rs);
			closeStmt(stmt);
			// closeCon(con);
		}
		return buf.toString();
	}

	//
	public static Map getTabelFields(Connection con, String tableName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List tb = null;
		Map fields = new HashMap();
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE 1 = 2";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();

			for (int i = 0; i < colnum; i++) {
				fields.put(rsmd.getColumnName(i + 1), rsmd.getColumnLabel(i + 1));
			}
			return fields;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeRs(rs);
			closeStmt(stmt);
			// closeCon(con);
		}
	}

	public static String getInsertSql(String tableName, Map map) {
		try {

			StringBuffer insSql = new StringBuffer();
			insSql.append("INSERT INTO ");
			insSql.append(tableName);
			insSql.append(" (");

			String key = null;
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				insSql.append(key);
				if (it.hasNext()) {
					insSql.append(", ");
				}
			}

			insSql.append(") ");
			insSql.append("VALUES (");

			for (int i = 0, n = map.keySet().size(); i < n; i++) {
				insSql.append("?");
				if (i + 1 < n) {
					insSql.append(", ");
				}
			}

			insSql.append(")");

			return insSql.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object[] getInsertSqlPA(Map map) {
		Object[] pa = new Object[map.keySet().size()];
		int i = 0;
		Object value = null;
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			value = map.get(it.next());
			pa[i++] = value;
		}
		return pa;
	}

	public static String getCreateTableByDat(String tableName, Map map) {
		try {
			StringBuffer createSql = new StringBuffer();
			createSql.append("CREATE TABLE IF NOT EXISTS ");
			createSql.append(tableName);
			createSql.append(" (");

			String key = null;
			Object val = null;
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				key = (String) it.next();
				createSql.append(key);
				createSql.append(" ");
				val = map.get(key);
				createSql.append(getHDBType(val));

				if (it.hasNext()) {
					createSql.append(", ");
				}
			}

			createSql.append(")");

			return createSql.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getHDBType(Object o) {
		if (o instanceof Boolean) {
			return "BOOLEAN";
		}

		if (o instanceof Character) {
			return "CHAR(255)";
		}

		if (o instanceof Byte || o instanceof Short || o instanceof Integer) {
			return "INTEGER";
		}

		if (o instanceof Long) {
			return "BIGINT";
		}

		if (o instanceof Float || o instanceof Double || o instanceof Number) {
			return "DOUBLE";
		}

		if (o instanceof java.util.Date) {
			return "DATE";// return "DATETIME";
		}

		return "VARCHAR";
	}

	public static boolean isTableExists(Connection con, String tableName) {
		ResultSet rs = null;
		try {
			rs = con.getMetaData().getTables(null, null, tableName.toUpperCase(), null);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeRs(rs);
		}
	}

	public static boolean saveMap2Db(Connection con, String tableName, Map map) {
		try {
			String insert = getInsertSql(tableName, map);
			Object[] pa = getInsertSqlPA(map);

			execute(con, insert, pa);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// closeRs(rs);
		}
	}

	public static boolean saveMap2DbX(Connection con, String tableName, Map map) {
		try {
			if (!isTableExists(con, tableName)) {
				String create = getCreateTableByDat(tableName, map);
				execute(con, create, null);
			}
			return saveMap2Db(con, tableName, map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// closeRs(rs);
		}
	}

	public static List getDb2ListMap(Connection con, String sql, Object[] a) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List tb = new ArrayList();

		try {
			stmt = con.prepareStatement(sql);
			if (a != null) {
				for (int i = 0; i < a.length; i++) {
					if (a[i] instanceof java.util.Date) {
						stmt.setObject(i + 1, TimeDo.getTimeStamp(a[i], null));
					} else {
						stmt.setObject(i + 1, a[i]);
					}
				}
			}
			rs = stmt.executeQuery();

			if (rs == null)
				return tb;
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();

			Map row = null;

			while (rs.next()) {
				row = new HashMap();

				for (int c = 0; c < colnum; c++) {
					row.put(rsmd.getColumnName(c + 1), rs.getObject(c + 1));
				}
				tb.add(row);
			}
			return tb;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeRs(rs);
			closeStmt(stmt);
			// closeCon(con);
		}
	}
}

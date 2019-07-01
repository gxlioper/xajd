package xgxt.DAO;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xgxt.utils.CustomException;

public class ZjjdDbCenterDAO {
    
    private static String oracleUrlToConnect = ""; 
    private static String userName = ""; 
    private static String password = ""; 
    
    public void closeAllStmtAndRs(ResultSet rs, PreparedStatement pstmt,
			Statement stat, CallableStatement cstmt) {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {// PreparedStatement
				pstmt.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void setDbParameter(ServletContext servletContext) throws ParserConfigurationException, SAXException, IOException {
    	String config = "/WEB-INF/zjgs/dbCenter.xml";
    	String realPath = servletContext.getRealPath(config);
    	File f = new File(realPath);    
 	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
 	    DocumentBuilder builder = factory.newDocumentBuilder();    
 	    Document doc = builder.parse(f);
 	    NodeList docList = doc.getElementsByTagName("db-url").item(0).getChildNodes();
 	    String url = docList.item(1).getFirstChild().getNodeValue();
 	    userName   = docList.item(3).getFirstChild().getNodeValue();
 	    password = docList.item(5).getFirstChild().getNodeValue();
 	    oracleUrlToConnect =  "jdbc:oracle:thin:@"+url;
	}
    
    public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public String[] getOneRs(String sql, String[] inputValue,
			String[] outputValue) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String[] result = new String[outputValue.length];
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(oracleUrlToConnect,userName,password);
			// System.out.println("conn = " + conn.hashCode());
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				for (int i = 0; i < outputValue.length; i++) {
					result[i] = rs.getString(outputValue[i]);
				}
			} else {
				result = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			result = null;
		} catch (ClassNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			closeConnection(conn);
		}
		return result;
	}

}

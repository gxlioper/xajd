package xgxt.wjsc;

import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import oracle.sql.CLOB;
import xgxt.DAO.DAO;
import xgxt.utils.CustomException;
import xgxt.utils.String.StringUtils;

public class WjscDao extends DAO {
	private DataSource db = null;

	private Connection conn = null;

	private PreparedStatement stmt = null;

	@SuppressWarnings("unused")
	private Statement stat = null;
	
	private ResultSet rs = null;
	
	public WjscDao(){
		super();
		db = super.getDb();
	}
	//保存上传文件
	@SuppressWarnings("deprecation")
	public boolean saveFile(String[] colVal) throws Exception {
		boolean flag = false;
		String sql = "insert into wjsc_scwjxxb (wjh,wjm,wjscsm,wjsclj,wjffsj,wjffbm,ffr,jsr,jsrxm,wdrxm,wjjszdm,wjjszmc,ydrxm) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String[] temp = new String[]{colVal[0],colVal[1],colVal[2],colVal[3],colVal[4],colVal[5],colVal[6]," "," "," ",colVal[10],colVal[11]," "};
		try{
			conn = transcationManager.getConnection();;
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < temp.length; i++) {
				stmt.setString(i + 1, temp[i]);
			}
			stmt.executeUpdate();
			sql = "select jsr,jsrxm,wdrxm from wjsc_scwjxxb where wjh = ? for update";
			stmt.clearBatch();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, colVal[0]);
			rs = stmt.executeQuery();
			if(rs.next()) {
				CLOB clob1 = null;
				CLOB clob2 = null;
				CLOB clob3 = null;
				if (rs.getClob(1).getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob(1).getClass().getMethod("getVendorObj",new Class[]{});
		    		clob1 = (oracle.sql.CLOB)method.invoke(rs.getClob(1));
		    		clob2 = (oracle.sql.CLOB)method.invoke(rs.getClob(2));
		    		clob3 = (oracle.sql.CLOB)method.invoke(rs.getClob(3));
		 	    }else{
			 	   	 clob1 = (CLOB) rs.getClob(1);
					 clob2 = (CLOB) rs.getClob(2);
					 clob3 = (CLOB) rs.getClob(3);
		 	    }
			
				Writer outstream1 = clob1.getCharacterOutputStream();
				Writer outstream2 = clob2.getCharacterOutputStream();
				Writer outstream3 = clob3.getCharacterOutputStream();
				outstream1.write(colVal[7]);				
				outstream2.write(colVal[8]);				
				outstream3.write(colVal[9]);
				outstream1.close();
				outstream2.close();
				outstream3.close();
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			//closeConnection(conn);
		}
		return flag;
	}
	

	public List<CLOB> getClobList(String sql, String[] inputValue, String colName) throws Exception{
		
		List<CLOB> list = new ArrayList<CLOB>();;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				CLOB clob=null;
				if (rs.getClob(colName).getClass().getName().startsWith("weblogic")){
		    		Method method = rs.getClob(colName).getClass().getMethod("getVendorObj",new Class[]{});
		    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob(colName));
		 	    }else{
		 	    	clob = (CLOB) rs.getClob(colName);
		 	    }
				
				i++;
				list.add(clob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
		} finally {
			closeAllStmtAndRs(rs, stmt, null, null);
			//closeConnection(conn);
		}
		return list;
	}
	
	//修改已读和未读用户
	@SuppressWarnings("deprecation")
	public boolean saveOneFileModi(String sql,String[] writeValue,String[] values) throws Exception {
		boolean flag = false;
		try{
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String sql1 = "update wjsc_scwjxxb set wdrxm=' ',ydrxm=' ' where wjh=?";
			stmt = conn.prepareStatement(sql1);
			for (int i = 0; i < values.length; i++) {
				stmt.setString(i + 1, values[i]);
			}
			stmt.execute();
			conn.commit();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				stmt.setString(i + 1, values[i]);
			}
			rs = stmt.executeQuery();
			int num = rs.getMetaData().getColumnCount();
			if(rs.next()) {
				for(int i=0;i<num;i++){
					CLOB clob=null;
					if (rs.getClob(i+1).getClass().getName().startsWith("weblogic")){
			    		Method method = rs.getClob(i+1).getClass().getMethod("getVendorObj",new Class[]{});
			    		clob = (oracle.sql.CLOB)method.invoke(rs.getClob(i+1));
			 	    }else{
			 	    	clob = (CLOB) rs.getClob(i+1);
			 	    }
					
					Writer outstream = clob.getCharacterOutputStream();
					outstream.write(writeValue[i]);
					outstream.close();
				}
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			conn.rollback();
		} finally {
			conn.commit();
			closeAllStmtAndRs(rs, stmt, null, null);
			//closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 查询文件接收列表
	 * @param yhm
	 * @return
	 */
	public List<HashMap<String, String>> queryWjjsList(String yhm) {
		DAO dao = DAO.getInstance();
		return dao
				.getList(
						"select wjh,wjm,wjffsj from (select wjh,wjm,wjffsj from VIEW_SCWJXX where jsr like ? or (jsr is null and wjjszdm='0000')  order by wjffsj desc) where rownum < 7",
						new String[] { StringUtils.isNotNull(yhm) ? "%" + yhm
								+ "%" : "" }, new String[] { "wjh", "wjffsj" });
	}
}

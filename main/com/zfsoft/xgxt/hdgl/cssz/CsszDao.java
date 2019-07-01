/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.cssz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * @className	： CsszDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-16 上午09:27:40
 * @version 	V1.0 
 */

public class CsszDao extends SuperDAOImpl<CsszForm> {

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-16 上午09:28:01
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-16 上午09:28:01
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-16 上午09:28:01
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(com.zfsoft.xgxt.hdgl.cssz.CsszForm.class);
		super.setKey("id");
		super.setTableName("xg_dekt_shlcszb");		
	}
	
	/**
	 * @description	： 获取参数设置列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午09:31:47
	 * @return
	 */
	public List<HashMap<String,String>> getCsszList(){
		String sql = "select * from xg_dekt_shlcszb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @description	： 插入
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午09:50:28
	 * @param t
	 * @return
	 * @throws SQLException 
	 */
	public boolean insert(CsszForm t) throws SQLException{
		List<String[]> list = t.getParamList();
		String sql = "insert into xg_dekt_shlcszb (lx,splc) values(?,?)";
		return dao.runBatchBoolean(sql, list);
	}
	
	/**
	 * @description	： 更新
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午09:49:26
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public boolean delete() throws Exception{
		String sql = "delete from xg_dekt_shlcszb";
		return dao.runUpdate(sql, new String[]{});
	}
	
	
}

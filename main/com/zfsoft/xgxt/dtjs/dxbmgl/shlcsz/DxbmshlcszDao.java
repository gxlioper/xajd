package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @功能描述：党校报名审核流程设置dao
 * @author：杨珩 【1346】
 * @date：2017-11-1 上午09:16:53 
 */
public class DxbmshlcszDao extends SuperDAOImpl<DxbmshlcszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxbmshlcszForm t) throws Exception {
		
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DxbmshlcszForm t, User user) throws Exception {
		
		return null;
	}
	
	/** 
	 * @功能描述：获取当前审核流
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 上午10:17:12 
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String dxbmshlcszCx() throws Exception{
		String sql=" select shl from xg_dtjs_dxbmshlb";
		return dao.getOneRs(sql, new String[]{}, "shl");
	}
	
	/** 
	 * @功能描述：保存审核流
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 上午09:59:50 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean dxbmshlcszBc(DxbmshlcszForm model) throws Exception{
		String sql="insert into xg_dtjs_dxbmshlb(shl) values(?)";
		return dao.runUpdate(sql,new String[]{model.getShl()});
	}
	
	/** 
	 * @功能描述：删除审核刘流
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 上午09:59:38 
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean dxbmshlcszSc() throws Exception{
		String sql = " delete from xg_dtjs_dxbmshlb ";
		return dao.runUpdate(sql, new String[]{});
	}

	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_dtjs_dxbmshlb");
		this.setClass(DxbmshlcszForm.class);
	}
}

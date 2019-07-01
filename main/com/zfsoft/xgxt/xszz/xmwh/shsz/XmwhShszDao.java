/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.shsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-审核调整奖项设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhShszDao extends SuperDAOImpl<XmwhShszForm> {

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhShszForm model)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述:高级查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhShszForm model,
			User user) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @描述:根据xmdm查询设置的记录
	 * @作者：ligl
	 * @日期：2013-4-24 上午10:03:45
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("查询参数为空！");
		}
		String sql = "select * from xg_xszz_new_zzxmshtzb where xmdm=?";
		String[] input = {xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}	
	
	/**
	 * 
	 * @描述:根据xmdm返回项目代码、名称
	 * @作者：ligl
	 * @日期：2013-4-24 上午10:03:45
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKshxm(String xmdm) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("查询参数为空！");
		}
		String sql = " select a.kshxmdm dm,b.xmmc mc,b.sqxn sqxn,b.sqxq sqxq  from  xg_xszz_new_zzxmshtzb a,xg_xszz_new_zzxmdmb b" ;
		sql += 	" where a.xmdm=? and a.kshxmdm=b.xmdm ";
		String[] input = {xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}
	
	/**
	 * 
	 * @描述:设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午07:20:02
	 * @修改记录: 
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runShsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;
		
		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_xszz_new_zzxmshtzb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_xszz_new_zzxmshtzb(xmdm,kshxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
				}
			}
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmshtzb");
		super.setKey("xmdm");
	}

}

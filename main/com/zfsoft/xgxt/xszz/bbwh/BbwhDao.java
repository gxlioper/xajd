/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.bbwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述：报表
 * @作者： ligl
 * @时间： 2013-6-3 上午09:40:35
 * @版本： V1.0
 * @修改记录:
 */
public class BbwhDao extends SuperDAOImpl<BbwhForm> {

	/**
	 * 
	 * @描述:获取报表数据
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAll() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_xszz_new_zzxmbbdmb ");
		return dao.getListNotOut(sb.toString(), null);
	}

	/**
	 * 
	 * @描述:获取报表单条记录
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_xszz_new_zzxmbbdmb ");
		sb.append(" where bbdm=?");
		String[] inputValue = { bbdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmbbdmb");
		super.setKey("bbdm");
		super.setClass(BbwhForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述: 查询登记表相关信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-5 下午02:10:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxLists(String bblx){
		
		String[] input = {bblx};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from xg_xszz_new_zzxmbbdmb t1 ");
		sql.append("left join xg_xszz_new_bbdytpb t2 on t1.bbdm=t2.bbdm and t2.dyym='1' where t1.bblx=? ");
		
		return dao.getListNotOut(sql.toString(), input);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-5 下午03:52:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBbxxList(String bbdm) {
		String[] input = new String[]{bbdm};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from xg_xszz_new_zzxmbbdmb t1 ");
		sql.append("left join xg_xszz_new_bbdytpb t2 on t1.bbdm=t2.bbdm where t1.bbdm=? order by t2.dyym");
		return dao.getListNotOut(sql.toString(), input);
	}
	
	
	
}

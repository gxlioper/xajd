/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午10:00:02 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-12 上午10:00:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglDAO extends SuperDAOImpl<XsxxKzxxglForm> {

	/**
	 * 
	 * @描述: 根据学号获取学生扩展信息(结果表记录)
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 上午08:59:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm 返回类型 
	 * @throws
	 */
	public XsxxKzxxglForm getXskzxxByXh(String xh) throws Exception{
		String sql = "select * from ZFXG_XSXX_KZXX_JG where xh = ? ";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{xh});
		for (HashMap<String, String> i : listNotOut) {
			String d1 = i.get("sqid");
			String d2 = i.get("sqsj");
			String d3 = i.get("czr");
			String d5 = i.get("xh");
			XsxxKzxxglForm model = new XsxxKzxxglForm();
			model.setXh(d5);
			model.setCzr(d3);
			model.setSqsj(d2);
			model.setSqid(d1);
			return model;
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 根据学号查询该学生的申请记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-12 上午10:33:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<XsxxKzxxglForm> 返回类型 
	 * @throws
	 */
	public List<XsxxKzxxglForm> getModleList(String xh) throws Exception {
		String sql = "select * from ZFXG_XSXX_KZXX_SQ where xh = ? order by sqsj desc";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{xh});
		List<XsxxKzxxglForm> modelList = new ArrayList<XsxxKzxxglForm>();
		for (HashMap<String, String> i : listNotOut) {
			String d1 = i.get("sqid");
			String d2 = i.get("sqsj");
			String d3 = i.get("splc");
			String d4 = i.get("shzt");
			String d5 = i.get("xh");
			XsxxKzxxglForm model = new XsxxKzxxglForm();
			model.setXh(d5);
			model.setShzt(d4);
			model.setSplc(d3);
			model.setSqsj(d2);
			model.setSqid(d1);
			modelList.add(model);
		}
		return modelList;
	}
	
	public XsxxKzxxglForm getLatestModel(String xh) throws Exception{
		String sql = "select * from (select rownum r, a.* from (select a.* from ZFXG_XSXX_KZXX_SQ a where a.xh = ? order by sqsj desc) a ) where r = 1";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{xh});
		XsxxKzxxglForm modelList = null;
		for (HashMap<String, String> i : listNotOut) {
			modelList = new XsxxKzxxglForm();
			String d1 = i.get("sqid");
			String d2 = i.get("sqsj");
			String d3 = i.get("splc");
			String d4 = i.get("shzt");
			String d5 = i.get("xh");
			modelList.setXh(d5);
			modelList.setShzt(d4);
			modelList.setSplc(d3);
			modelList.setSqsj(d2);
			modelList.setSqid(d1);
		}
		return modelList;
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append("  from (select a.sqid,");
		sql.append("               a.sqsj,");
		sql.append("               a.splc,");
		sql.append("               a.shzt,");
		sql.append("			   decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','','无需审核',a.shzt) shztmc,");
		sql.append("               b.xh,");
		sql.append("               b.xm,");
		sql.append("               b.xymc,");
		sql.append("               b.xydm,");
		sql.append("               b.zymc,");
		sql.append("               b.zydm,");
		sql.append("               b.bjdm,");
		sql.append("               b.bjmc,");
		sql.append("              b.xb");
		sql.append("          from ZFXG_XSXX_KZXX_SQ a");
		sql.append("          left join view_xsjbxx b");
		sql.append("            on a.xh = b.xh");
		sql.append("         order by a.sqsj desc) a where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxKzxxglForm t)
			throws Exception {
		return null;
	}
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		setClass(XsxxKzxxglForm.class);
		setKey("SQID");
		setTableName("ZFXG_XSXX_KZXX_SQ");
	}


}

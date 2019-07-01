/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:35:20 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:35:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkSqDao extends SuperDAOImpl<XnwxdkSqModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkSqModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkSqModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,t1.sfzh,t1.xz,t1.sjhm,t1.xmsj sjdh,t1.dzyx,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '已退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append(" t.shzt) shztmc,");
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjk_sqb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.xh " +
				" left join xqdzb t2 on t.xq = t2.xqdm " +
				") t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XnwxdkSqModel.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_wxjk_sqb");
	}
	
	/**
	 * 
	 *保存增加结果
	 */
	public boolean saveZsjg(XyzsSqForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
    	sql.append("insert into xg_gygl_xyzsgl_sqb(sqbh,xh,sqsjstart,sqsjend,sqsj,shzt,splc,xxdz,lxdh,parentslxdy,xl,zwjzyy,bz,xn) values(xyzsgl_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	boolean flag = dao.runUpdate2(sql.toString(),new String[]{model.getXh(),model.getSqsjstart(),model.getSqsjend(),model.getSqsj(),model.getShzt(),model.getSplc(),model.getXxdz(),model.getLxdh(),model.getParentslxdy(),model.getXl(),model.getZwjzyy(),model.getBz(),model.getXn()},"xg_gygl_xyzsgl_sqb",user);
		return flag;
	}
	
	/*
	 * 导出申请表的时候获取学生基本信息以及住宿申请信息
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t3.xlmc,");
		sql.append(" substr(t2.sqsjstart, 0, 4) || '年' || substr(t2.sqsjstart, 5, 2) || '月' ||substr(t2.sqsjstart, 7, 2) || '日' ||'到'||substr(t2.sqsjend, 0, 4) || '年' || substr(t2.sqsjend, 5, 2) || '月' ||substr(t2.sqsjend, 7, 2) || '日' as sqsj ");
		sql.append("from xg_gygl_xyzsgl t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh join xldmb t3 on t2.xl = t3.xldm)");
		sql.append(" t where sqbh = ?  ");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}
	
	/**
	 *获取学历代码和学历名称map,给下拉框使用
     *
	 */
	public List<HashMap<String, String>> getXl(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select xldm,xlmc from xldmb");
		return dao.getList(sql.toString(),new String[] {},new String[] { "xldm", "xlmc" });
	}
	
	/**
	 * 查看时显示学历名称
	 */
	public HashMap<String, String> getXlCk(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select t1.xldm,t1.xlmc from  xg_gygl_xyzsgl_sqb t");
    	sql.append(" join xldmb t1 on t.xl = t1.xldm  where t.sqbh = ?");
    	return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}
	
	/**
	 *查看时显示申请校外住宿的原因
	 */
	public HashMap<String, String> getXyZsyy(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select t1.dm,t1.mc from  xg_gygl_xyzsgl_sqb t");
    	sql.append(" join xg_gygl_xyzsgl_zwjzyydmb t1 on t.zwjzyy = t1.dm  where t.sqbh = ?");
    	return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}
	
	//获取审批流
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_zdgxh_wxjk_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	//获取审批流
	public String getJesx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select jesx from xg_zdgxh_wxjk_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "jesx");
	}
	
	//获取spid
	public String getSqbh(XnwxdkSqModel model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sqid from xg_zdgxh_wxjk_sqb where xh = ? and  xn = ? and xq = ?");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "sqid");
	}
	
	//获取审核状态名称
	public String getShztMc(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select decode(t2.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t2.shzt) shztmc from xg_gygl_xyzsgl_sqb t2 where sqbh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getSqbh()}, "shztmc");
	}

}

/**
 * @部门:学工产品事业部
 * @日期：2016-5-9 上午10:45:33 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-5-9 上午10:45:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjySqDao extends SuperDAOImpl<HjjySqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjySqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjySqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t4.xqmc,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc,t3.jyyymc");
		sql.append(",decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc ");
		sql.append("from XG_RCSW_HZSF_HJJYSQB t1 left join view_xsxxb t2 on t1.xh=t2.xh left join XG_RCSW_HZSF_JYYYDMB t3 on t1.JYYYDM = t3.JYYYDM ");
		sql.append(" left join xqdzb t4 on t1.xq=t4.xqdm) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	public HashMap<String, String> getJyxx(String sqid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.xqmc, t3.jyyymc");
		sql.append(" from XG_RCSW_HZSF_HJJYSQB t1 left join XG_RCSW_HZSF_JYYYDMB t3 on t1.jyyydm=t3.jyyydm left join xqdzb t2 on t1.xq=t2.xqdm");
		sql.append(" where t1.sqid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_RCSW_HZSF_HJJYCSSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	/**
	 * 
	 * @描述:是否归还或已申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-5-9 下午02:26:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveGhJl(HjjySqForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num from(select xh from XG_RCSW_HZSF_HJJYSQB where xh=?  and sqid<> nvl(?,'-1') union all");
		sql.append(" select xh from XG_RCSW_HZSF_HJJYJGB where xh=? and jyzt='0')");
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getSqid(),model.getXh()}, "num");
		return Integer.parseInt(num)>0;
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HjjySqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_HZSF_HJJYSQB");

	}

}

/**
 * @部门:学工产品事业部
 * @日期：2016-3-22 下午06:13:37 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.sq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-22 下午06:13:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybsqDao extends SuperDAOImpl<YxybsqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybsqForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String bmdm = user.getUserDep();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.xqmc,substr(t1.yf, 0, 4) nian,substr(t1.yf, 6, 2) yue,t3.xm mz,t4.bmmc xymc,  ");
		sql.append(" decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc");
		sql.append(" from xg_bjzyy_xqyb_yxyb_sq t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.xydm = t4.bmdm ");
		if(isYxUser(user)){
			sql.append(" where t1.xydm = '" + bmdm +"'");
		}
		sql.append(" ) a where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(YxybsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_xqyb_yxyb_sq");
		
	}
	
	public List<HashMap<String,String>> getXylist(User user){
		String sql = null;
		if("xx".equalsIgnoreCase(user.getUserStatus())){
			sql = "select distinct xydm,xymc from view_njxyzybj";
		}else{
			sql = "select distinct xydm,xymc from view_njxyzybj where xydm = '" +user.getUserDep()+ "'";
		}		 
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	
	public List<String> getYueFenByXn() throws SQLException {		
		StringBuffer sql = new StringBuffer();
		sql.append("select ny from ( ");
		sql.append("select case when to_number(lv)>8 then substr(dqxn,1,4)||'-'||replace(lpad(lv,2),' ','0') else ");
		sql.append("substr(dqxn,6,9)||'-'||replace(lpad(lv,2),' ','0') end ny ");
		sql.append("from xtszb,(select LEVEL lv from dual CONNECT BY LEVEL <= 12)) order by ny");
		return dao.getList(sql.toString(), new String[]{}, "ny");
	}
	
	/** 
	 * @描述:是否有记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午03:35:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(YxybsqForm form){
		StringBuilder sql = new StringBuilder().append(" select count(1) num from xg_bjzyy_xqyb_yxyb_sq where xn = ? and xq = ? and yf = ? and xydm = ? ");
		if(form.getSqid()!=null){
			sql.append(" and sqid <> '"+ form.getSqid() +"'");
		}
		String sqll = "select count(1) numm from xg_bjzyy_xqyb_yxyb_jg where xn = ? and xq = ? and yf = ? and xydm = ?";
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXn(),form.getXq(),form.getYf(),form.getXydm()}, "num");
		String numm = dao.getOneRs(sqll, new String[]{form.getXn(),form.getXq(),form.getYf(),form.getXydm()}, "numm");
		return Integer.valueOf(num) + Integer.valueOf(numm) > 0;
	}
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_bjzyy_xqyb_yxyb_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public Map<String,String> getSqxx(YxybsqForm form){
		String sql = "select a.*,b.xqmc,c.xm mz,d.bmmc xymc from xg_bjzyy_xqyb_yxyb_sq a left join xqdzb b on a.xq = b.xqdm left join fdyxxb c on a.txr = c.zgh left join zxbz_xxbmdm d on a.xydm = d.bmdm where a.sqid = ? ";
		return dao.getMapNotOut(sql, new String[]{form.getSqid()});
	}
	
	//是否为院级用户
	public boolean isYxUser(User user){
		String dept = user.getUserDep();
		String sql = "select bmlb from zxbz_xxbmdm where bmdm = ? ";
		String bmlb = dao.getOneRs(sql, new String[]{dept}, "bmlb");
		if("5".equalsIgnoreCase(bmlb)){
			return true;
		}else{
			return false;
		}
	}
	
}

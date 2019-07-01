/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 下午05:23:30 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.jg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-24 下午05:23:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybjgDao extends SuperDAOImpl<YxybjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybjgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String bmdm = user.getUserDep();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.xqmc,substr(t1.yf, 0, 4) nian,substr(t1.yf, 6, 2) yue,t3.xm mz,t4.bmmc xymc ");
		sql.append(" from xg_bjzyy_xqyb_yxyb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.xydm = t4.bmdm ");
		if(new YxybsqDao().isYxUser(user)){
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
		super.setClass(YxybjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_bjzyy_xqyb_yxyb_jg");		
	}
	
	public boolean isHaveRecordForjg(YxybjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_bjzyy_xqyb_yxyb_jg where xq = ? and xn = ? and yf = ? and xydm = ? ");
		if("update".equalsIgnoreCase(form.getType())){
			sql.append(" and jgid <> '" + form.getJgid() + "'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXq(),form.getXn(),form.getYf(),form.getXydm()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public boolean deleteForSq(YxybjgForm form) throws Exception{
		String sql = "delete from xg_bjzyy_xqyb_yxyb_jg where xq = ? and xn = ? and yf = ? and xydm = ? ";
		return dao.runUpdate(sql, new String[]{form.getXq(),form.getXn(),form.getYf(),form.getXydm()});	
	}
	
	public boolean delByLclyywid(String lclyywid) throws Exception{
		String sql = "delete from xg_bjzyy_xqyb_yxyb_jg where lylcywid = ?";		
		return dao.runUpdate(sql, new String[]{lclyywid});		
	}
	
	public Map<String,String> getJgxx(YxybjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xqmc,t3.bmmc xymc,t4.xm mz from xg_bjzyy_xqyb_yxyb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join zxbz_xxbmdm t3 on t1.xydm = t3.bmdm ");
		sql.append(" left join fdyxxb t4 on t1.txr = t4.zgh ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getJgid()});
	}
	
	/** 
	 * @描述:获取合并导出list(北京中医药个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-30 下午04:40:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(YxybjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String bmdm = user.getUserDep();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select * from ( ");
		sql.append(" select t1.*,t2.xqmc,substr(t1.yf, 0, 4) nian,substr(t1.yf, 6, 2) yue,t3.xm mz,t4.bmmc xymc ");
		sql.append(" from xg_bjzyy_xqyb_yxyb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.xydm = t4.bmdm ");
		if(new YxybsqDao().isYxUser(user)){
			sql.append(" where t1.xydm = '" + bmdm +"'");
		}
		sql.append(" ) group by nian,yue,xymc,xn,xq,yf,jgid,xqmc,mz,bygzkzqk,xsgzrd,xssxdt,xstsjgzjy,txsj,txr,sjly,lylcywid,xydm order by nian,yue,xymc) a where 1=1 ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
}

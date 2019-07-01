/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:30:13 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道结果
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:30:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdjgDao extends SuperDAOImpl<LstdjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LstdjgForm t)
			throws Exception {
		return null;
	}

	/**
	 * 结果查询
	 */
	public List<HashMap<String, String>> getPageList(LstdjgForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.jgid,t1.xh,t1.xn,t1.xq,(select xqmc from xqdzb t4 where t1.xq=t4.xqdm) xqmc,t1.lxdm, ");
		sql.append(" t1.sjly,t1.sqsj,t1.sqly,t2.xm, t2.xb,t2.xymc,t2.zymc, ");
		sql.append(" t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj,t3.lxmc from xg_rcsw_lstd_jgb t1 ");
		sql.append("  left join view_xsbfxx t2 on t1.xh=t2.xh left join xg_rcsw_lstd_lxwhb t3 on t1.lxdm=t3.lxdm ) a where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_lstd_jgb");
		super.setKey("jgid");
	}
	
	/**
	 * 
	 * @描述:判断结果表当中的学年是否已申请
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午10:37:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(LstdjgForm model) {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select count(1) num from xg_rcsw_lstd_jgb where xh= ? and xn= ? and xq= ? ");
		params.add(model.getXh());
		params.add(model.getXn());
		params.add(model.getXq());
		
		if(!StringUtils.isBlank(model.getJgid())){
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");

	}
	
	
	/**
	 * 
	 * @描述:流程通过的数据不可以修改
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午10:44:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_lstd_jgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
		
	}
	
	
	/**
	 * 
	 * @描述:结果单个查询
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 上午10:48:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	
	public Map<String, String> viewOneLstdjgList(String jgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.xh,t1.lxdm,t1.sqly,t1.sqsj,t1.bz,t2.lxmc ");
		sql.append(" from xg_rcsw_lstd_jgb  t1 left join xg_rcsw_lstd_lxwhb t2  on t1.lxdm = t2.lxdm  ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{jgId});
	}
	
	
	public HashMap<String, String> getBbjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_lstd_jgb a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}

	/**
	 * @throws Exception  
	 * @描述:根据学号学年
	 * @作者：cq [工号：785]
	 * @日期：2014-12-4 上午11:19:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int delForXhxnxq(String xh, String xn, String xq) throws Exception {
		
		String sql = "delete from xg_rcsw_lstd_jgb where xh = ? and xn = ? and xq = ?" ; 
		
		return dao.runDelete(sql, new String[]{xh,xn,xq});
	}
	
}

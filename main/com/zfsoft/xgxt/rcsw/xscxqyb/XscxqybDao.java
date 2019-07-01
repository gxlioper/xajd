/**
 * @部门:学工产品事业部
 * @日期：2016-3-17 上午11:04:18 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生处学情月报
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-3-17 上午11:04:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XscxqybDao extends SuperDAOImpl<XscxqybForm>{
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XscxqybForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(XscxqybForm t,User user)
		throws Exception {
			String searchTj = SearchService.getSearchTj(t.getSearchModel());	
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			sql.append(" from ( select t.jgid,");
			sql.append(" t.xn, ");
			sql.append(" t.xq, ");
			sql.append(" t.yf, substr(yf,1,4)nf,substr(yf,6,2) yyf, ");
			sql.append(" t.bygzkzqk, ");
			sql.append(" t.xsgzrd, ");
			sql.append(" t.xssxdt, ");
			sql.append(" t.xstsjgzjy, ");
			sql.append(" t.txsj, ");
			sql.append(" t.txr,t1.xqmc,t2.xm");
			sql.append(" from xg_bjzyy_xqyb_xscyb t left join xqdzb t1 on t1.xqdm = t.xq left join yhb t2 on t2.yhm = t.txr) a");
			sql.append(" where 1 = 1 ");
			sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

		@Override
		protected void setTableInfo() {
			super.setTableName("xg_bjzyy_xqyb_xscyb");
			super.setKey("jgid");
			super.setClass(XscxqybForm.class);
			}
/**
 * @描述:TODO(获得当前学期名称)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:27:33
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param model
 * @return
 * String 返回类型 
 * @throws
 */
	public String getCurrentXqmc(XscxqybForm model) {
		StringBuilder sql = new StringBuilder(" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
/**
 * @描述:TODO(同一学年学期，相同的操作人是否存在同样月份的记录)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:27:01
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param form
 * @return
 * String 返回类型 
 * @throws
 */
	public String checkExistForSave(XscxqybForm form){	
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String jgid =  form.getJgid();
		sql.append(" select count(1) num from xg_bjzyy_xqyb_xscyb ");
		sql.append(" where xn = ? and xq = ?  and txr = ? and yf = ? ");
		params.add(form.getXn());
		params.add(form.getXq());
		params.add(form.getTxr());
		params.add(form.getYf());
		if(!StringUtils.isEmpty(jgid)){
			sql.append(" and jgid <> ? ");
			params.add(jgid);
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
/**
 * @描述:TODO(查看所需用SQL)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:25:39
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param jgid
 * @return
 * HashMap<String,String> 返回类型 
 * @throws
 */
	public HashMap<String,String> getXxck (String jgid){
		String sql = " select * from (select a.*,b.xqmc,c.xm from xg_bjzyy_xqyb_xscyb a left join xqdzb b on a.xq = b.xqdm left join yhb c on a.txr = c.yhm) where jgid = ? ";
		return dao.getMapNotOut(sql, new String[]{jgid});	
	}
/**
 * @描述:导出查询
 * @作者：孟威[工号：1186]
 * @日期：2016-3-24 下午02:03:56
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param t
 * @param user
 * @return
 * @throws Exception
 * List<HashMap<String,String>> 返回类型 
 * @throws
 */
	public List<HashMap<String, String>> getXscxqybdcList(XscxqybForm t, User user)
		throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from ( select t.jgid,");
		sql.append(" t.xn, ");
		sql.append(" t.xq, ");
		sql.append(" t.yf, substr(yf,1,4)nf,substr(yf,6,2) yyf, ");
		sql.append(" t.bygzkzqk, ");
		sql.append(" t.xsgzrd, ");
		sql.append(" t.xssxdt, ");
		sql.append(" t.xstsjgzjy, ");
		sql.append(" t.txsj, ");
		sql.append(" t.txr,t1.xqmc,t2.xm");
		sql.append(" from xg_bjzyy_xqyb_xscyb t left join xqdzb t1 on t1.xqdm = t.xq left join yhb t2 on t2.yhm = t.txr) a");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2016-7-18 下午03:45:54 
 */  
package com.zfsoft.xgxt.xszz.xfbzmd;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-资助管理-学费补助名单查询
 * @类功能描述: 老师可以直接在系统里查看到所有符合学费补助条件的同学名单，信息包括：学号、姓名、学院（大二的还算在学园），
 * 			       同时还能看到每个同学符合学费补助的原因，同时支持导出。
 * 			       导出的EXCLE字段有 序号、学号、学院名称、备注（每个学生符合申请学费补助的条件） 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-7-18 下午03:45:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XfbzmdDao  extends SuperDAOImpl<XfbzmdForm>{
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XfbzmdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根	
	}
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	/**
	 * 查询
	 * 作者：MengWei
	 * 时间：2016-07-18
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XfbzmdForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,xz, ");
		sql.append(" case when a.nj = e.dqnd then d.xymc else ''end zxzyqrqxy, ");
		sql.append(" b.sfdq || b.sfgc || b.lszn || b.ylzd1 || case when c.xh is not null then '特困' else '' end || decode(jg, '520328', '贵州湄潭', '') || case when mzmc not like '汉族' then mzmc else '' end bz ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join (select xh,sfdq,decode(sfgc, '1', '残疾', '') sfgc,decode(lszn, '1', '烈士子女', '') lszn,decode(ylzd1, '1', '优抚对象子女', '') ylzd1 ");
		sql.append(" from xg_xszz_new_jtqkdcb where sfdq in ('孤儿') or sfgc = '1' or lszn = '1' or ylzd1 = '1') b on a.xh = b.xh ");
		sql.append(" left join (select distinct xh from view_knsjgb_fqxrd  where rddc = '2' and xh in (select xh from view_xsbfxx where mzmc not like '汉族' and sfzx = '在校')) c on a.xh = c.xh ");
		sql.append(" left join view_njxyzybj_all d on a.zxzyqrqbjdm = d.bjdm ");
		sql.append(" left join (select substr(dqxn, 6, 4) dqnd from xtszb) e on 1=1 ");
		sql.append(" where (a.jg = '520328' or b.xh is not null or c.xh is not null) and (to_number(a.nj) + to_number(a.xz)) > e.dqnd ");
		sql.append(" and a.xh in (select xh from view_knsjgb_fqxrd ) ");
		sql.append(" order by a.xymc, a.nj, a.xh ");
		sql.append(" ) a where 1 = 1 ");
	    sql.append(searchTjByUser);
	    sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
}

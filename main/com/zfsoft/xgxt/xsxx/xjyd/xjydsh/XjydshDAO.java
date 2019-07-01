/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:15:17 
 */  
package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @模块名称: 学生信息
 * @类功能描述:学籍异动审核
 * @作者： qilm
 * @时间： 2013-9-29
 * @版本： V1.0
 */
public class XjydshDAO extends SuperDAOImpl<XjydshForm> {

	@Override
	protected void setTableInfo() {
		super.setKey("xjydsqid");
		super.setTableName("xg_xsxx_xjydsqb");
		super.setClass(XjydshForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjydshForm model)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjydshForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a","xydm", "bjdm");
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append("	select * from ( ");
		sql.append("	select t1.xjydsqid, ");
		sql.append("         t1.xh, ");
		sql.append("         t1.splcid splc, ");
		sql.append("         t1.shzt, ");
		sql.append("         t1.ydlbdm, ");
		sql.append("         t1.xn, ");
		sql.append("         t1.xq, ");
		sql.append("         t1.sqsj, ");
		sql.append("         t1.sqly, ");
		sql.append("         t2.xm, ");
		sql.append("         t2.xb, ");
		sql.append("         (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("         (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("         (select distinct bjmc ");
		sql.append("            from view_njxyzybj_all ");
		sql.append("           where bjdm = t1.ydqbjdm ");
		sql.append("             and nj = t1.ydqnj) ydqbjmc, ");
		sql.append("         (select xjlbmc from dm_xjlb where xjlbdm = t1.ydqxjlb) ydqxjlbmc, ");
		sql.append("         t1.ydqnj, ");
		sql.append("         t1.ydqxydm, ");
		sql.append("         t1.ydqzydm, ");
		sql.append("         t1.ydqbjdm, ");
		sql.append("         t1.ydqnj nj, ");
		sql.append("         t1.ydqxydm xydm, ");
		sql.append("         t1.ydqzydm zydm, ");
		sql.append("         t1.ydqbjdm bjdm, ");
		sql.append("         t1.ydqxjlb, ");
		sql.append("         t1.ydhnj, ");
		sql.append("         t1.ydhxydm, ");
		sql.append("         t1.ydhzydm, ");
		sql.append("         t1.ydhbjdm, ");
		sql.append("         t1.ydhxjlb, ");
		sql.append("         (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("         (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("         (select distinct bjmc ");
		sql.append("            from view_njxyzybj_all ");
		sql.append("           where bjdm = t1.ydhbjdm ");
		sql.append("             and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("         (select xjlbmc from dm_xjlb where xjlbdm = t1.ydhxjlb) ydhxjlbmc, ");
		sql.append("         (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("         b.gwid, ");
		sql.append("         b.shsj, ");
		sql.append("         b.guid shid, ");
		sql.append("         c.mc || '[' || decode(b.shzt, 0, '待审核', 1, '通过', 2, '不通过', 3, '退回', 4, '需重审', '5','审核中') || ']' shztmc,c.gwz, ");
		sql.append("         row_number() over(partition by t1.xjydsqid order by b.shsj desc) rn ");
		sql.append("    from XG_XSXX_XJYDSQB t1 ");
		sql.append("    left join view_xsxxb t2 ");
		sql.append("      on t1.xh = t2.xh ");
		sql.append("    left join xg_xtwh_shztb b ");
		sql.append("      on t1.xjydsqid = b.ywid ");
		sql.append("    left join xg_xtwh_spgw c ");
		sql.append("      on b.gwid = c.id ");
		sql.append("    left join xg_xtwh_spgwyh d ");
		sql.append("      on c.id = d.spgw ");
		sql.append("    left join xg_xtwh_spbz e ");
		sql.append("      on e.splc = t1.splcid ");
		sql.append("     and e.spgw = d.spgw ");
		
		sql.append("  where d.spyh = '"+user.getUserName()+"' and t1.shzt<>9 and b.shzt<>9 ");
		String shlx = model.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}		
		sql.append(" order by b.shsj desc )a ");
		sql.append(" where rn = 1 "); //取出最後一級
		sql.append(" )a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @描述: 是否是最后一级岗位
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-11 下午03:35:52
	 * @param splcid
	 * @return
	 * ArrayList<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public ArrayList<HashMap<String, String>> getSplcgw(String splcid) {
		String sql="select * from xg_xtwh_spbz where splc=? order by xh";
		return (ArrayList<HashMap<String, String>>) dao.getListNotOut(sql, new String[]{splcid});
	}

}

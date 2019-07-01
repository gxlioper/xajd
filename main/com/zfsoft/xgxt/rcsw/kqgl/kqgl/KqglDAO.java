/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午02:43:59 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class KqglDAO extends SuperDAOImpl<KqglForm> {


	@Override
	public List<HashMap<String, String>> getPageList(KqglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(KqglForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select a.*,c.xm,b.bjmc,b.zydm,b.xydm,b.zymc,b.xymc,b.nj,");
		sql.append(" decode(c.xb,'1','男','2','女',c.xb) xb,e.mc qkjblbmc,  ");
		sql.append(" f.mc ybqkjbmc,d.mc kqlbmc,g.mc dqztmc from xg_rcsw_kqgl_kqgljgb a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm  ");
		sql.append(" left join xsxxb c on a.xh=c.xh left join ");
		sql.append(" xg_rcsw_kqgl_kqlbdmb d on a.kqlbdm=d.dm  ");
		sql.append(" left join xg_rcsw_kqgl_qkjblbdmb e on a.qkjblbdm=e.dm");
		sql.append(" left join xg_rcsw_kqgl_ybqkjbdmb f on a.ybqkjbdm=f.dm");
		sql.append(" left join xg_rcsw_kqgl_mqzkdmb g on a.dqztdm=g.dm");
		sql.append(" ) s1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);	
	}
    
	public KqglForm getModel(String id) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select a.*,c.xm,b.bjmc,b.zydm,b.xydm,b.zymc,b.xymc,b.nj,");
		sql.append(" decode(c.xb,'1','男','2','女',c.xb) xb,e.mc qkjblbmc,  ");
		sql.append(" f.mc ybqkjbmc,d.mc kqlbmc,g.mc dqztmc from xg_rcsw_kqgl_kqgljgb a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm  ");
		sql.append(" left join xsxxb c on a.xh=c.xh left join ");
		sql.append(" xg_rcsw_kqgl_kqlbdmb d on a.kqlbdm=d.dm  ");
		sql.append(" left join xg_rcsw_kqgl_qkjblbdmb e on a.qkjblbdm=e.dm");
		sql.append(" left join xg_rcsw_kqgl_ybqkjbdmb f on a.ybqkjbdm=f.dm");
		sql.append(" left join xg_rcsw_kqgl_mqzkdmb g on a.dqztdm=g.dm");
		sql.append(" )  where id = ? ");
		return getModel(sql.toString(),new String[]{id});
		
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_kqgl_kqgljgb");
		super.setKey("id");
		super.setClass(KqglForm.class);
	}
	
	/**
	 * 
	 * @描述:验证考勤结果是否存在
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-8-31 下午04:42:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isKqjgExists(KqglForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_rcsw_kqgl_kqgljgb ");
		sql.append(" where xh = ? and kqrq = ? and kqlbdm=?");
		String num = dao.getOneRs(sql.toString(),new String[]{model.getXh(),
				model.getKqrq(),model.getKqlbdm()}, "num");
		return Integer.parseInt(num) > 0 ? true:false;
	}
    
	/**
	 * 
	 * @描述:获取List
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-8-31 下午04:45:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tablename
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getList(String tablename){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct dm,mc from ");
		sql.append( tablename );
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}

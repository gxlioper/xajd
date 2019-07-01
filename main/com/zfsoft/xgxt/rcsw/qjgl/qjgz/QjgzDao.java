/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;

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
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QjgzDao extends SuperDAOImpl<QjgzForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjgzForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select d.qjgzid,d.kssj,d.jssj,d.splcid,");
		sql.append(" d.ssxydm,");
		sql.append(" decode(d.ssxydm,'qx','全校',z.bmmc) ssxymc,");
		//查询请假区间
		sql.append("(d.kssj||'~'||d.jssj||'天')qjqj,");
		//查询审核流程
		sql.append(" decode(e.lcxx, '', '无需审核', mc || '：' || replace(lcxx, ';', '->')) lcxx,x.qjlxmc,d.qjlxid, ");
		sql.append(" decode(d.open,'1','开启','关闭') openzt");
		sql.append(" from xg_rcsw_qjgl_qjgz d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh )) lcxx, xh,");
		sql.append(" row_number() over (partition by splc order by xh desc ) as ddd");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splcid = e.splc ");
		sql.append("  left join xg_rcsw_qjgl_qjlx x");
		sql.append("  on d.qjlxid = x.qjlxid");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = d.ssxydm");

		sql.append(" where 1 = 1 ");
		if(!"xx".equals(user.getUserStatus())){
			sql.append(" and (ssxydm = 'qx' or ssxydm='"+user.getUserDep()+"' )");
		}
		sql.append(" ");
		sql.append(searchTj);
		//这段原来就有，action那边原来走的是不带user的，这个不知道干嘛用的
		/*sql.append(" select d.qjgzid,");
		sql.append(" d.qjlxid,");
		sql.append(" d.kssj,");
		sql.append(" d.jssj,");
		sql.append(" d.splcid,");
		sql.append(" d.ssxydm,");
		sql.append(" decode(d.ssxydm,'qx','全校',z.bmmc) ssxymc,");
		sql.append(" (d.kssj || '~' || d.jssj || '天') qjqj,");
		sql.append(" decode(e.lcxx,");
		sql.append("  '',");
		sql.append("  '无需审核',");
		sql.append(" mc || '：' || replace(lcxx, ';', '->')) lcxx,");
		sql.append(" x.qjlxmc");
		sql.append(" from xg_rcsw_qjgl_qjgz d");
		sql.append(" left join (select splc, mc, lcxx");
		sql.append(" from (select splc,");
		sql.append(" a.mc,");
		sql.append(" to_char(WM_CONCAT(c.mc)");
		sql.append(" over(partition by splc");
		sql.append(" order by xh)) lcxx,");
		sql.append(" xh,");
		sql.append(" row_number() over(partition by splc order by xh desc) as ddd");
		sql.append(" from xg_xtwh_splc a,");
		sql.append(" xg_xtwh_spbz b,");
		sql.append(" xg_xtwh_spgw c");
		sql.append(" where djlx = 'rcsw'");
		sql.append(" and a.id = b.splc");
		sql.append(" and b.spgw = c.id) b");
		sql.append(" where ddd = 1) e");
		sql.append(" on d.splcid = e.splc");
		sql.append(" left join xg_rcsw_qjgl_qjlx x");
		sql.append(" on d.qjlxid = x.qjlxid");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = d.ssxydm");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);*/
		return this.getPageList(t, sql.toString(), inputV);
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjgzid");
		this.setTableName("xg_rcsw_qjgl_qjgz");
		this.setClass(QjgzForm.class);
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjgzForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select d.qjgzid,d.kssj,d.jssj,d.splcid,");
		sql.append(" d.ssxydm,");
		sql.append(" decode(d.ssxydm,'qx','全校',z.bmmc) ssxymc,");
		//查询请假区间
		sql.append("(d.kssj||'~'||d.jssj||'天')qjqj,");
		//查询审核流程
		sql.append(" decode(e.lcxx, '', '无需审核', mc || '：' || replace(lcxx, ';', '->')) lcxx,x.qjlxmc,d.qjlxid, ");
		sql.append(" decode(d.open,'1','开启','关闭') openzt");
		sql.append(" from xg_rcsw_qjgl_qjgz d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh )) lcxx, xh,");
		sql.append(" row_number() over (partition by splc order by xh desc ) as ddd");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splcid = e.splc ");
		sql.append("  left join xg_rcsw_qjgl_qjlx x");
		sql.append("  on d.qjlxid = x.qjlxid");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = d.ssxydm");
		sql.append(" where 1 = 1 ");
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:是否使用中
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:12:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUse(String qjgzid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from xg_rcsw_qjgl_qjsq t,xg_rcsw_qjgl_qjgz m where m.kssj<t.qjts and t.qjts<=m.jssj and m.qjgzid=?");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{qjgzid});
		return null==map||map.size()<=0?false:true;
	}
	
	/**
	 * 
	 * @描述: 获取请假类型List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-29 上午11:19:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlxList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_rcsw_qjgl_qjlx");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public HashMap<String, String> getInfo(QjgzForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,decode(a.ssxydm,'qx','全校',z.bmmc) ssxymc from XG_RCSW_QJGL_QJGZ a ");
		sql.append(" left join ZXBZ_XXBMDM z");
		sql.append(" on z.bmdm = a.ssxydm");
		sql.append(" where a.qjgzid=?");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getQjgzid()});
	}
}

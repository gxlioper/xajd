/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 下午05:29:28 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——考情管理——管理模块
 * @类功能描述: 考情维护Dao
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 下午05:29:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqwhDao extends SuperDAOImpl<KqwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqwhForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (select a.*,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',a.shzt) shztmc, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc, ");
		sql.append(" translate(to_number(a.yf),'0123456789','零一二三四五六七八九')||'月' toyf, ");
		sql.append("  '第'||translate(to_number(a.zc),'0123456789','零一二三四五六七八九')||'周' tozc, ");
		sql.append(" round((to_number(cqrs)-to_number(kkrs))/to_number(cqrs)*100,1) cql from ( ");
		sql.append(" select a.*,(select count(distinct xh) from XG_RCSW_KQGL_ZJSYKQXXB t1 ");
		sql.append(" where a.id = t1.kqid and bjcs <> '0') bjrs, (select count(distinct xh)");
		sql.append("  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid and sjcs <> '0') sjrs, ");
		sql.append(" (select count(distinct xh)  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid");
		sql.append("   and kkjs <> '0') kkrs from ");
		sql.append(" xg_rcsw_kqgl_zjsykqjgb a ) a left join view_njxyzybj_all");
		sql.append(" b on a.bjdm=b.bjdm left join xqdzb c on a.xq=c.xqdm ) s1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}


	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_kqgl_zjsykqjgb");
		super.setKey("id");
		super.setClass(KqwhForm.class);
	}
	
	/**
	 * 获取kqwhform
	 */
	public KqwhForm getModel(String id) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.bjmc,c.xqmc,b.xydm,b.zydm, ");
		sql.append(" translate(to_number(a.yf),'0123456789','零一二三四五六七八九')||'月' toyf, ");
		sql.append("  '第'||translate(to_number(a.zc),'0123456789','零一二三四五六七八九')||'周' tozc, ");
		sql.append(" round((to_number(cqrs)-to_number(kkrs))/to_number(cqrs)*100,1) cql from ( ");
		sql.append(" select a.*,(select count(distinct xh) from XG_RCSW_KQGL_ZJSYKQXXB t1 ");
		sql.append(" where a.id = t1.kqid and bjcs <> '0') bjrs, (select count(distinct xh)");
		sql.append("  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid and sjcs <> '0') sjrs, ");
		sql.append(" (select count(distinct xh)  from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid");
		sql.append("   and kkjs <> '0') kkrs from ");
		sql.append(" xg_rcsw_kqgl_zjsykqjgb a ) a left join view_njxyzybj_all");
		sql.append(" b on a.bjdm=b.bjdm left join xqdzb c on a.xq=c.xqdm ) where id=? ");
		return getModel(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @描述:获取当前学年、学期、月份最大周次
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 上午10:57:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDqZc(){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(zc) zc  from xg_rcsw_kqgl_zjsykqjgb where xn=? ");
		sql.append(" and xq=? and yf = ?");
		return dao.getOneRs(sql.toString(), new String[]{Base.currXn,Base.currXq,
			DateUtils.getMonth()}, "zc");
	}

	/** 
	 * @描述:获取考情info
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午02:16:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKqinfo(String kqid){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm from XG_RCSW_KQGL_ZJSYKQXXB a left ");
		sql.append(" join view_xsbfxx b on a.xh=b.xh where kqid=? ");
		return dao.getListNotOut(sql.toString(), new String[]{kqid});
	}

	/** 
	 * @描述:删除考情信息
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午02:22:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delKqInfo(String kqid) throws SQLException {
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[]{kqid});
		String sql = "delete from XG_RCSW_KQGL_ZJSYKQXXB where kqid=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @描述:保存考情信息
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午02:27:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKqInfo(List<String[]> params) throws SQLException {
		String sql = "insert into XG_RCSW_KQGL_ZJSYKQXXB(id,kqid,xh,bjcs,sjcs,kkjs) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @描述:查询有多少条记录可以提交
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午07:40:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> isCanSubmit(String values, KqwhForm t, User user) throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String[] value = values.split(",");
		
		sql.append("select * from (select a.*, ");
		sql.append("decode(a.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',a.shzt) shztmc, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc from xg_rcsw_kqgl_zjsykqjgb a ");
		sql.append("left join  view_njxyzybj_all b on a.bjdm = b.bjdm left join xqdzb c on a.xq = c.xqdm) s1 ");
		sql.append("where 1=1 and (shzt in ('0','3') or shzt is null) ");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);
	}


	/** 
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午08:40:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submit(String values, KqwhForm t, User user) throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String[] value = values.split(",");
		
		sql.append("update xg_rcsw_kqgl_zjsykqjgb set shzt= ?,splc= ?,jlr=?, jlsj=? where id in ( ");
		sql.append("select id from (select a.*, ");
		sql.append("decode(a.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',a.shzt) shztmc, ");
		sql.append("b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc from xg_rcsw_kqgl_zjsykqjgb a ");
		sql.append("left join  view_njxyzybj_all b on a.bjdm = b.bjdm left join xqdzb c on a.xq = c.xqdm) s1 ");
		sql.append("where 1=1 and (shzt in ('0','3') or shzt is null)");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(")");
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{t.getShzt(),t.getSplc(),t.getJlr(),t.getJlsj()}, inputV);
		
		return dao.runUpdate(sql.toString(), both);
		
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2015-6-17 下午02:42:56 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.zjsy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
* @系统名称: 学生工作管理系统
* @模块名称: 日常事务
* @类功能描述: 浙江商业考勤管理
* @作者： ChenQ[工号:856]
* @时间： 2015-6-17 下午02:43:37 
* @版本： V1.0
* @修改记录: 类修改者-修改日期-修改说明  
*/

public class ZjsyKqDAO extends SuperDAOImpl<ZjsyKqForm> {
	
    private final String MonthWithXx = "monthwithxx"; 
    private final String MonthWithXy = "monthwithxy"; 
    private final String ZcWithXx = "zcwithxx"; 
    
	@Override
	public List<HashMap<String, String>> getPageList(ZjsyKqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(ZjsyKqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (select a.*,b.nj,b.xymc,b.xydm,b.zydm,b.bjmc,c.xqmc,  ");
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
		super.setClass(ZjsyKqForm.class);
	}
   

	
	public ZjsyKqForm getModel(String id) throws Exception{
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
	
	public List<HashMap<String,String>> getExpoertData(ZjsyKqForm model, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1", "xydm", "bjdm");
		String[] ids = searchTj.split("= ?");
		int a = 0;
		int b = 0;
		//取学年学期旷课总数条件
		for(int i=0;i<ids.length;i++){
			if(ids[i].indexOf("xn")!=-1){
                 a = i;
			}
			if(ids[i].indexOf("xq")!=-1){
                 b = i;
			}
		}
		String xnTj = inputV[a];
		String xqTj = inputV[b];
		
		StringBuilder sql = new StringBuilder();
		if(MonthWithXx.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select xydm, xymc, xn,xq, yf, round(avg(cqrs), 0) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs, replace(wm_concat(distinct kkxq), ';', ',') kkxq,replace(wm_concat(bz), ';', ',') bz  ");
			sql.append("from (select *from (select tt1.*,d.kkxq from (select a.*, b.xydm, b.bjmc,b.nj, b.xymc,b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm ) tt1  left join ( ");
			sql.append(" select xn,xq,yf,xydm, replace(wm_concat(xm || kkjs || '共' || kkzs), ");
			sql.append("  ';', ',') kkxq from ( select c.*,e.xm,d.kkzs from (select xn, xq, ");
			sql.append("  yf, xydm, xh, sum(kkjs) kkjs from (select c.xh, c.kkjs, m.xn, m.xq, ");
			sql.append("  m.yf, n.xydm from XG_RCSW_KQGL_ZJSYKQXXB c left join xg_rcsw_kqgl_zjsykqjgb m ");
			sql.append(" on c.kqid = m.id left join view_njxyzybj_all n  on m.bjdm=n.bjdm ");
			sql.append("  where c.kkjs <> '0') group by xn, xq, yf, xydm, xh)  ");
			sql.append("  c left join view_xsbfxx e on c.xh = e.xh left join (select xh,  ");
			sql.append("  sum(kkjs) kkzs from XG_RCSW_KQGL_ZJSYKQXXB ");
			sql.append(" where kqid in (select id from xg_rcsw_kqgl_zjsykqjgb where xn ='");
			sql.append(xnTj);
			sql.append("' and xq='");
			sql.append(xqTj);
			sql.append("') group by xh ) d on c.xh = d.xh)  group by xn,xq,yf,xydm ) d");
			sql.append(" on tt1.xn=d.xn and tt1.xq=d.xq and tt1.yf=d.yf and tt1.xydm=d.xydm ) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xydm, xymc, xn, xq, yf ) t1 ");
		}else if(MonthWithXy.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select bjdm, bjmc, xn,xq, yf, round(avg(cqrs), 0) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs, replace(wm_concat(distinct kkxq), ';', ',') kkxq,replace(wm_concat(bz), ';', ',') bz  ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs,d.kkxq from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm   left join ( ");
			sql.append(" select xn,xq,yf,bjdm, replace(wm_concat(xm || kkjs || '共' || kkzs), ");
			sql.append("  ';', ',') kkxq from ( select c.*,e.xm,d.kkzs from (select xn, xq, ");
			sql.append("  yf, bjdm, xh, sum(kkjs) kkjs from (select c.xh, c.kkjs, m.xn, m.xq, ");
			sql.append("  m.yf, m.bjdm from XG_RCSW_KQGL_ZJSYKQXXB c left join xg_rcsw_kqgl_zjsykqjgb m ");
			sql.append("  on c.kqid = m.id where c.kkjs <> '0') group by xn, xq, yf, bjdm, xh)  ");
			sql.append("  c left join view_xsbfxx e on c.xh = e.xh left join (select xh,  ");
			sql.append("  sum(kkjs) kkzs from XG_RCSW_KQGL_ZJSYKQXXB ");
			sql.append(" where kqid in (select id from xg_rcsw_kqgl_zjsykqjgb where xn ='");
			sql.append(xnTj);
			sql.append("' and xq='");
			sql.append(xqTj);
			sql.append("') group by xh ) d on c.xh = d.xh)  group by xn,xq,yf,bjdm ) d");
			sql.append(" on a.xn=d.xn and a.xq=d.xq and a.yf=d.yf and a.bjdm=d.bjdm");
			sql.append(" ) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by bjdm, bjmc, xn, xq, yf  ) t1 ");
		}else if(ZcWithXx.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select xydm, xymc, xn,xq, yf,zc, round(avg(cqrs), 0) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs, replace(wm_concat(kkxq), ';', ',') kkxq,replace(wm_concat(bz), ';', ',') bz  ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,b.xymc,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs,d.kkxq from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm   left join (select kqid,  ");
			sql.append("  replace(wm_concat(xm || kkjs || '共' || kkzs), ';', ',') kkxq  from (select c.*, d.kkzs, e.xm ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB c  left join (select xh, sum(kkjs) kkzs  from XG_RCSW_KQGL_ZJSYKQXXB ");
			sql.append(" where kqid in (select id from xg_rcsw_kqgl_zjsykqjgb where xn ='");
			sql.append(xnTj);
			sql.append("' and xq='");
			sql.append(xqTj);
			sql.append("') group by xh) d on c.xh = d.xh left join view_xsbfxx e  on c.xh = e.xh  where kkjs <> '0') ");
			sql.append(" group by kqid) d  on a.id = d.kqid) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xydm, xymc, xn, xq, yf,zc ) t1 ");
		}else{
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select bjdm, bjmc, xn,xq, yf,zc, round(avg(cqrs), 0) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs, replace(wm_concat(kkxq), ';', ',') kkxq,replace(wm_concat(bz), ';', ',') bz  ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs,d.kkxq from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm   left join (select kqid,  ");
			sql.append("  replace(wm_concat(xm || kkjs || '共' || kkzs), ';', ',') kkxq  from (select c.*, d.kkzs, e.xm ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB c  left join (select xh, sum(kkjs) kkzs  from XG_RCSW_KQGL_ZJSYKQXXB ");
			sql.append(" where kqid in (select id from xg_rcsw_kqgl_zjsykqjgb where xn ='");
			sql.append(xnTj);
			sql.append("' and xq='");
			sql.append(xqTj);
			sql.append("' ) group by xh) d on c.xh = d.xh left join view_xsbfxx e  on c.xh = e.xh  where kkjs <> '0') ");
			sql.append(" group by kqid) d  on a.id = d.kqid) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by bjdm, bjmc, xn, xq, yf,zc ) t1 ");
		}
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	public List<HashMap<String,String>> getSzExpoertData(ZjsyKqForm model, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		String[] ids = searchTj.split("= ?");
		int a = 0;
		for(int i=0;i<ids.length;i++){
			int zcnum = ids[i].indexOf("zc");
			if(zcnum!=-1){
				a = i;
				break;
			}
		}
		int sz = Integer.parseInt(inputV[a])-1;
		inputV[a] = String.valueOf(sz);
		if(ZcWithXx.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select xydm, xymc, xn,xq, yf,zc, round(avg(cqrs), 0) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs, replace(wm_concat(kkxq), ';', ',') kkxq,replace(wm_concat(bz), ';', ',') bz  ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,b.xymc,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs,d.kkxq from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm   left join (select kqid,  ");
			sql.append("  replace(wm_concat(xm || kkjs || '共' || kkzs), ';', ',') kkxq  from (select c.*, d.kkzs, e.xm ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB c  left join (select xh, sum(kkjs) kkzs  from XG_RCSW_KQGL_ZJSYKQXXB ");
			sql.append("group by xh) d on c.xh = d.xh left join view_xsbfxx e  on c.xh = e.xh  where kkjs <> '0') ");
			sql.append(" group by kqid) d  on a.id = d.kqid) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xydm, xymc, xn, xq, yf,zc ) t1 ");
		}
		return dao.getListNotOut(sql.toString(), inputV);
	}
	public List<HashMap<String,String>> getSzHjExpoertData(ZjsyKqForm model, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		String[] ids = searchTj.split("= ?");
		int a = 0;
		for(int i=0;i<ids.length;i++){
			int zcnum = ids[i].indexOf("zc");
			if(zcnum!=-1){
				a = i;
				break;
			}
		}
		int sz = Integer.parseInt(inputV[a])-1;
		inputV[a] = String.valueOf(sz);
		if(ZcWithXx.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select xn,xq, yf,zc, sum(cqrs) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs,'' kkxq,''bz ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm ) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xn, xq, yf,zc ) t1 ");
		}
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	//获取合计数据
	public List<HashMap<String,String>> getHjExpoertData(ZjsyKqForm model, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "s1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		if(MonthWithXx.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 2) cql ");
			sql.append(" from (select xn,xq, yf, sum(cqrs) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs,'' kkxq,''bz ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm ) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xn, xq, yf ) t1 ");
		}else if(MonthWithXy.equals(model.getType())){
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select xydm,xn,xq, yf, sum(cqrs) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs,'' kkxq,''bz ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm  ) s1 where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xydm,xn, xq, yf ) t1 ");
		}else if(ZcWithXx.equals(model.getType())){
				sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
				sql.append(" from (select xn,xq, yf,zc, sum(cqrs) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
				sql.append("sum(kkrs) kkrs,'' kkxq,''bz ");
				sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
				sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
				sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
				sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs from xg_rcsw_kqgl_zjsykqjgb a ");
				sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm ) s1 where 1 = 1 ");
				sql.append(searchTjByUser);
				sql.append(" ");
				sql.append(searchTj);
				sql.append(" ) group by xn, xq, yf,zc ) t1 ");
		}else{
			sql.append("select t1.*,round((to_number(cqrs) - to_number(kkrs)) / to_number(cqrs) * 100, 1) cql ");
			sql.append(" from (select xydm,xn,xq, yf,zc, sum(cqrs) cqrs,sum(bjrs) bjrs,sum(sjrs) sjrs, ");
			sql.append("sum(kkrs) kkrs,'' kkxq,''bz ");
			sql.append("from (select *from (select a.*, b.xydm, b.bjmc,b.nj, b.zydm,(select count(distinct xh) from   ");
			sql.append("XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and bjcs <> '0') bjrs,(select count(distinct xh)  ");
			sql.append(" from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and sjcs <> '0') sjrs, (select count(distinct xh) ");
			sql.append("from XG_RCSW_KQGL_ZJSYKQXXB t1 where a.id = t1.kqid  and kkjs <> '0') kkrs from xg_rcsw_kqgl_zjsykqjgb a ");
			sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm  ) s1  where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ) group by xydm,xn, xq, yf,zc ) t1 ");
		}
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	public List<HashMap<String,String>> getMonthZcXyData(ZjsyKqForm model, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,translate(to_number(t.yf), '0123456789', '零一二三四五六七八九') || '月' toyf,");
		sql.append(" '第' ||translate(to_number(t.zc), '0123456789', '零一二三四五六七八九') || '周' tozc, ");
		sql.append("decode(t.xq, '01', '第一学期', '第二学期') xqmc from (select a.*, b.xydm, b.xymc,b.nj,b.zydm ");
		sql.append(" from xg_rcsw_kqgl_zjsykqjgb a left join view_njxyzybj_all b ");
		sql.append(" on a.bjdm = b.bjdm) t where rownum = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	public boolean cshKqdj(ZjsyKqForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_rcsw_kqgl_zjsykqjgb ");
		sql.append("select sys_guid() id,?,?,?,?,a.bjdm,b.cqrs,'' bz from view_njxyzybj a left join ");
		sql.append("(select bjdm,count(1) cqrs from view_xsjbxx b group by bjdm) b on a.bjdm=b.bjdm ");
		sql.append(" where not exists (select 1 from xg_rcsw_kqgl_zjsykqjgb b where a.bjdm=b.bjdm ");
		sql.append(" and xn=? and xq=? and yf=? and zc=? ) ");
		return dao.runUpdate(sql.toString(), new String[]{model.getXn(),model.getXq(),
			model.getYf(),model.getZc(),model.getXn(),model.getXq(),
			model.getYf(),model.getZc()});
	}
	
	public List<HashMap<String, String>> getXsxxList(ZjsyKqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.bjdm='" + model.getBjdm() + "'");
		if(xhs.length>0){
		sql.append(" and a.xh not in(");
		for (int i = 0; i < xhs.length; i++) {
			if(i!=0){
				sql.append(", ");
			}
			sql.append("'"+xhs[i]+"' ");
		}
		sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}
	
	public boolean saveKqInfo(List<String[]> params) throws SQLException {
		String sql = "insert into XG_RCSW_KQGL_ZJSYKQXXB(id,kqid,xh,bjcs,sjcs,kkjs) values(?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean delKqInfo(String kqid) throws SQLException {
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[]{kqid});
		String sql = "delete from XG_RCSW_KQGL_ZJSYKQXXB where kqid=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public List<HashMap<String,String>> getKqinfo(String kqid){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm from XG_RCSW_KQGL_ZJSYKQXXB a left ");
		sql.append(" join view_xsbfxx b on a.xh=b.xh where kqid=? ");
		return dao.getListNotOut(sql.toString(), new String[]{kqid});
	}
	public String getDqZc(){
		StringBuilder sql = new StringBuilder();
		sql.append("select max(zc) zc  from xg_rcsw_kqgl_zjsykqjgb where xn=? ");
		sql.append(" and xq=? and yf = ?");
		return dao.getOneRs(sql.toString(), new String[]{Base.currXn,Base.currXq,
			DateUtils.getMonth()}, "zc");
	}
}

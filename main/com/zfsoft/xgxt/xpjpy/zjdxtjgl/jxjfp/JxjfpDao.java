/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-24 下午04:09:59 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.jxjfp;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.tjcx.TjcxModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 奖学金名额分配一览表
 * @作者： MengWei[工号:1186]
 * @时间： 2018-7-24 下午04:08:47 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxjfpDao extends SuperDAOImpl<JxjfpForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JxjfpForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JxjfpForm model, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		/*主表为项目申请表*/
		super.setTableName("xg_zjdx_pjpy_xmsq");
		super.setKey("id");
		super.setClass(JxjfpForm.class);
	}
	
	
	/**
	 * 
	 */
	public List<HashMap<String, String>> getJxjmefpList(JxjfpForm model, User user,List<HashMap<String, String>> pjxmList) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "bmdm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select bmdm,xn,");
		sql.append("       (select bmmc from zxbz_xxbmdm b where a.bmdm = b.bmdm) bmmc,");
		for (int i = 0; i < pjxmList.size(); i++) {
			sql.append("    nvl(case when substr(max(jx"+i+"), 0, 1) = '.' or substr(max(jx"+i+"), 0, 1) = '/' then '0' || max(jx"+i+") else max(jx"+i+") end, '0/0') jx"+i+",");
		}
		sql.append("       nvl(sum(jje), 0) jje,");
		sql.append("       nvl(sum(bmtzje), 0) bmtzje,");
		sql.append("       zrs,sx");
		sql.append("  from (select t1.bmdm, t6.xn,");
		for (int i = 0; i < pjxmList.size(); i++) {
			sql.append("           case when t2.xmdm = '"+pjxmList.get(i).get("xmdm")+"' then nvl(t1.zd3, 0) || '/' || case when t1.zd1 is null then '0' else t1.zzme end else '' end jx"+i+",");
		}
		sql.append("               t1.fpbl,");
		sql.append("               nvl(t1.zd3 * xmje, 0) jje,");
		sql.append("               case when t1.zd1 is null then '0' else t1.zzme end * xmje bmtzje,");
		sql.append("               t2.xmje, nvl(t6.zrs, 0) zrs, t3.sx");
		sql.append("          from xg_pjpy_new_rsszb t1");
		sql.append("          left join xg_zjdx_pjpy_pjxmb t2");
		sql.append("            on t1.xmdm = t2.xmdm");
		sql.append("          left join xg_xtwh_bmsxb t3");
		sql.append("            on t1.bmdm = t3.bmdm");
		sql.append("          left join (select t5.xymc, t4.xn, t5.xydm bmdm, nvl(count(1), 0) zrs");
		sql.append("                      from xg_zjdx_pjpy_cpmdb t4");
		sql.append("                      left join view_njxyzybj_all t5");
		sql.append("                        on t4.bjdm = t5.bjdm");
		sql.append("                     where t4.xn ='"+model.getXn()+"'");
		sql.append("                       and t5.xydm is not null");
		sql.append("                       and t5.nj is not null");
		sql.append("                       and t5.zydm is not null");
		sql.append("                     group by t5.xymc, t5.xydm, t4.xn) t6");
		sql.append("            on t1.bmdm = t6.bmdm");
		sql.append("         where t1.fpbl is not null");
		sql.append("           and t2.xn ='"+model.getXn()+"') a");
		sql.append(" where bmdm in (select bmdm from zxbz_xxbmdm where bmlb = '5')");
		sql.append(" group by bmdm, zrs, sx, xn");
		sql.append(" order by to_number(sx))t where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/**
	 * @描述: 需统计项目
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-24 下午05:42:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjxmList(JxjfpForm model){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_zjdx_pjpy_pjxmb where ");
		sql.append("xmdm in (select xmdm from xg_pjpy_new_rsszb where fpbl is not null) and xn = ? order by to_number(xssx) ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXn()});
	}
	
	/**
	 * @描述: 浙大发放汇总导出存储过程
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-27 上午08:48:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param dqxn
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean computeFfhz(String xn ,String dqxn ) throws Exception{
		return dao.runProcuder("{call pro_zjdx_pjpy_hjhz(?,?)}", new String[]{xn,dqxn});
	}
	
	/**
	 * @描述: 发放汇总导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-11 下午03:06:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFfhzList(JxjfpForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xymc,xydm,xh,xm,bcffje,jjze,bz from ( ");
		sql.append(" select c.xymc,c.xydm,a.xh,e.xm,a.xn,d.sx, ");
		sql.append(" (case when gjjxjje > 0 and GJJXJJE > ZKZJXJJE and GJJXJJE > WSJXJZE then (jjze - (select xmje from xg_pjpy_new_pjxmb where xmmc = '国家奖学金' and rownum=1)) / 2 ");
		sql.append(" when gjjxjje > 0 and ZKZJXJJE > GJJXJJE and ZKZJXJJE > wsjxjze then (jjze - (select xmje from xg_pjpy_new_pjxmb where xmmc = '国家奖学金'and rownum=1)) / 2 ");
		sql.append(" when gjjxjje > 0 and wsjxjze >= GJJXJJE and wsjxjze > ZKZJXJJE then (jjze - (select xmje from xg_pjpy_new_pjxmb where xmmc = '国家奖学金'and rownum=1)) / 2 ");
		sql.append(" else jjze / 2 end) bcffje, a.jjze, (case ");
		sql.append(" when GJJXJJE > 0 and GJJXJJE > ZKZJXJJE and GJJXJJE > WSJXJZE then '国家奖学金已发放' ");
		sql.append(" when ZKZJXJJE > 0 and ZKZJXJJE > WSJXJZE and gjjxjje > 0 then '竺可桢、国家奖学金(国家奖学金已发放)' ");
		sql.append(" when ZKZJXJJE > 0 and ZKZJXJJE > WSJXJZE then '竺可桢奖学金' ");
		sql.append(" when WSJXJZE > 0 and WSJXJZE >= GJJXJJE and gjjxjje > 0 then '国家奖学金已发放' else '无' end) BZ ");
		sql.append(" from xg_zjdx_pjpy_jxjhzb a left join xg_zjdx_pjpy_cpmdb b on a.xh = b.xh and a.xn = b.xn ");
		sql.append(" left join view_njxyzybj_all c on b.bjdm = c.bjdm left join xg_xtwh_bmsxb d on c.xydm=d.bmdm  ");
		sql.append(" left join view_xsbfxx e on a.xh = e.xh");
		sql.append(" ) a where 1=1");
		sql.append(" and exists (select 1 from xg_zjdx_pjpy_cpmdb b where a.xh = b.xh and a.xn = b.xn) ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by to_number(a.sx) asc,xm");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 国家奖学金汇总导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-11 下午03:09:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGjjxjList(JxjfpForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh,t5.xm,t1.xmmc,t1.lxdm,t1.xzdm,t1.xmje,");
		sql.append("nvl(t3.nj, t5.nj) nj,");
		sql.append("nvl(t3.xydm, t5.xydm) xydm,");
		sql.append("nvl(t3.xymc, t5.xymc) xymc,");
		sql.append("nvl(t3.zydm, t5.zydm) zydm,");
		sql.append("nvl(t3.zymc, t5.zymc) zymc,");
		sql.append("nvl(t3.bjdm, t5.bjdm) bjdm,");
		sql.append("nvl(t3.bjmc, t5.bjmc) bjmc ");
		sql.append("from xg_zjdx_pjpy_pjjgb t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t4 on t1.xmdm = t4.xmdm ");
		sql.append("left join view_xsbfxx t5 on t1.xh = t5.xh ");
		sql.append("left join xg_xtwh_bmsxb t6 on t3.xydm = t6.bmdm ");
		sql.append("where 1 = 1 ");
		sql.append("and t1.xmmc = '国家奖学金' and t1.xn = ? ");
		sql.append("and exists (select 1 from xg_zjdx_pjpy_cpmdb t6 where t1.xh = t6.xh and t1.xn = t6.xn)");
		sql.append("order by to_number(t6.sx),nj,xymc,zymc,bjmc,xh");
		return dao.getListNotOut(sql.toString(), new String[]{t.getXn()});
	}
}

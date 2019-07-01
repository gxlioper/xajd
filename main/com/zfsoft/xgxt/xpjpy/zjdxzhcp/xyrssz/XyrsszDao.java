/**
 * @部门:学工产品事业部
 * @日期：2017-7-18 上午09:31:13 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz.RsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 学院人数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-18 上午09:31:13 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyrsszDao extends SuperDAOImpl<XyrsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyrsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	/**
	 * 页面查询列表，分页
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XyrsszForm model, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.xmdm,a.xn,a.xzdm,a.lxdm,a.xmmc,a.ywmc,a.xmje,a.shlc,a.xmsm,a.sqkg,a.sqkssj,a.sqjssj,a.shkg,a.shkssj,a.shjssj,a.kgbz,a.djb, ");
		sql.append("a.sbb,a.rskzjb,a.rsfpfs,a.rsfpz,a.rsfpnj,a.zcfpm,to_number(a.xssx) xssx,b.lxmc,c.xzmc, ");
		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_zjdx_pjpy_jdszb e where a.xmdm = e.xmdm) jdsz, ");
		sql.append("(select case when count(*) > 0 then '1' else '0' end from xg_pjpy_new_xmtjb f where a.xmdm = f.xmdm) tjsz, ");
		sql.append("(select case when count(*) > 0 then sum(zzme) else 0 end from xg_pjpy_new_rsszb g where a.xmdm = g.xmdm and g.zzme is not null ");
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			sql.append(" and bmdm = '" + user.getUserDep() + "' ");
		}
		sql.append(" and zd1 is not null) rssz ");
		sql.append("from xg_zjdx_pjpy_pjxmb a, xg_zjdx_pjpy_xmlx b, xg_zjdx_pjpy_xmxz c ");
		sql.append("where a.lxdm = b.lxdm and a.xzdm = c.xzdm ");
		sql.append(")t where 1=1 ");
		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLxdm())) {
			params.add(model.getLxdm());
			sql.append(" and lxdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXzdm())) {
			params.add(model.getXzdm());
			sql.append(" and xzdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getSqkg())) {
			String sqkg = model.getSqkg();
			if(sqkg.equals("1")){
				params.add(model.getSqkg());
				sql.append(" and sqkg like '%'||?||'%' and");
				sql.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");	
			}else{
				params.add(model.getSqkg());
				sql.append(" and (sqkg like '%'||?||'%' or");
				sql.append(" (sysdate not between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')))");	
			}
		}
		sql.append(" and xn = (select xn from xg_zjdx_pjpy_csszb) ");
		sql.append(" and exists (select 1 from xg_pjpy_new_rsszb a where a.fpbl is not null and t.xmdm = a.xmdm ");
		if ("xy".equalsIgnoreCase(user.getUserStatus())){
			sql.append(" and '"+user.getUserDep()+"' in(select bmdm from xg_pjpy_new_rsszb where fpbl is not null)");
		}
		sql.append(")");
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(XyrsszForm.class);
		super.setTableName("xg_zjdx_pjpy_pjxmb");
		super.setKey("xmdm");
	}
	
	/**
	 * @描述: 人数设置查询
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-19 上午11:05:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getRsszList(XyrsszForm model,User user)
		throws Exception{
		String searchTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select M.*,case when O.ytjrs is null then 0 else O.ytjrs end ytjrs,N.fpbl,N.guid, ");
		sql.append("case when zd1 is null then '0' else zzme end ZZME, ");
		sql.append("nvl(N.zd3, 0) zd3,(case when N.ZZME is null then '0' else '1' end) sfysz from ( ");
		sql.append("select count(1) zrs, t2.xydm, t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  where exists (select 1 ");
		sql.append("from xg_zjdx_pjpy_csszb t3 where t1.xn = t3.xn) ");
		/*通过项目代码获取已经设置的年级,年级以逗号分割,针对学院情况*/
		String rskznj = new XmwhDao().getRsfpnj(model.getXmdm());
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			/*格式化rskznj，增加''，以便拼接sql语句*/
			sql.append(setRskznj(rskznj));
			sql.append(")");
		}
		sql.append("group by t2.xydm, t2.xymc) M ");
		sql.append("left join (select count(1) ytjrs, t2.xydm, t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm where t1.tjzt = '1' ");
		// 通过项目代码获取已经设置的年级,年级以逗号分割,针对学院情况
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
			sql.append(")");
		}
		sql.append("and t1.xn in (select xn from xg_pjpy_new_csszb where rownum = 1) group by t2.xydm, t2.xymc)O ON M.xydm = O.xydm ");
		sql.append("left join xg_pjpy_new_rsszb N on m.xydm = N.bmdm ");
		sql.append("and N.xmdm='");
		sql.append(model.getXmdm());
		sql.append("'");
		sql.append(") t where 1=1 ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append("and t.sfysz = '"+model.getSfysz()+"' ");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append("and t.xydm = '"+model.getXydm()+"' ");
		}
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), new String[]{});
	}
	
	/*
	 * @描述:格式化rskznj，增加''，以便拼接sql语句
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @param rskznj
	 * @return String 返回类型
	 * @throws
	 */
	private String setRskznj(String rskznj) {
		if (rskznj != null) {
			rskznj = rskznj.replaceAll(",", "','");
			rskznj = "'" + rskznj + "'";
		}
		return rskznj;
	}
	
	/**
	 * @描述: 按学院查询指定周期项目的获得名额
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-20 下午03:35:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZzmeByXy(String xmdm, String xn) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.xydm from xg_zjdx_pjpy_pjjgb t1");
		sql.append(" left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm");
		sql.append(" where t1.xn = ? and t1.xmdm = ?");
		sql.append(" group by t3.xydm");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}
	
	/**
	 * @描述: 通过学院代码取到学院名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-20 下午03:47:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXymc(String dm) throws Exception {
		String name = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select xymc name from view_njxyzybj_all where xydm = ? ");
		String[] input = { dm };
		String[] output = { "name" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				name = oneRs[0];
			}
		}
		return name;
	}
	
	public boolean runZzme(XyrsszForm model,User user) throws Exception {
		int[] result = null;
		
		StringBuffer sb = null;
		List<String> sqlList = new ArrayList<String>();
		String guid = null;
		String zzme = null;
		String ffbl = null;
		String nj = "";
		String bmdm = null;
		String rskzfw = null;
		String jsrs=null;
		String[] zzmes = model.getZzmes();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] fpbls = model.getFpbls();
		String[] guids = model.getGuids();
		String[] jsrsHid=model.getJsrsHid();
		
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				guid = guids[i];
				if (!StringUtil.isNull(guid)) {
					sqlList
							.add("delete from xg_pjpy_new_rsszb where guid='"
									+ guid + "'");
				}
			}
		}
		
		if (zzmes != null && zzmes.length > 0) {
			for (int i = 0; i < zzmes.length; i++) {
				zzme = zzmes[i];
				if (zzme != null) {
					zzme = zzme.trim();
				}
				try {
					zzme = Integer.parseInt(zzme) + "";
				} catch (Exception e) {
					zzme = "";
				}
				ffbl = fpbls[i];
				if (njs != null) {
					nj = njs[i];
				}
				if(jsrsHid!=null){
					jsrs=jsrsHid[i];
				}
				rskzfw = model.getRsfpfs();
					if (rskzfw.equals(Constants.RSKZFW_XY)) {
						bmdm = xydms[i];
					} 
				sb = new StringBuffer();
				sb.append("insert into xg_pjpy_new_rsszb");
				sb.append("(fpbl,bmdm,nj,xmdm,zzme,zd1,zd2,zd3)");
				sb.append(" values('" + ffbl + "','" + bmdm + "','" + nj + "','"+ model.getXmdm() + "','" + zzme + "','"+user.getUserName()+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+jsrs+"')");
				sqlList.add(sb.toString());
			}
		}
		
		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}
		return dao.checkBatch(result);
	}
	
	
	/**
	 * @描述: 奖学金金额上限验证
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-16 下午05:57:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(XyrsszForm model,User user) throws Exception{
		String searchTjByUser = SearchService.getSearchQxTjByUser(user, "t", "xydm", "");
		String csszSql = " select csz from xg_pjpy_new_cspzb where csdm = 'rsjsfs' ";
		String rsjsfs = dao.getOneRs(csszSql, new String[]{}, "csz");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XS_PJPY_NEW_JXJRSSZB,");
		sql.append("(SELECT nvl(sum(nvl(zd3,0)*xmje),0) jxjze,nvl(sum(zzme * xmje),0) jxsjze FROM (");
		sql.append(" SELECT  M.*,t5.xmje,t5.xmmc,t5.xmdm,CASE WHEN O.YTJRS IS NULL THEN 0 ELSE O.YTJRS  END YTJRS,N.zd3,N.FPBL,N.GUID,(case when N.ZD1 is null then '0' else N.ZZME end) ZZME,(CASE WHEN N.ZZME IS NULL THEN '0' ELSE '1' END) SFYSZ FROM (");
		sql.append(" select count(1) zrs , t2.xydm,t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm");
		sql.append(" where exists (select 1 from xg_zjdx_pjpy_csszb t3 where t1.xn=t3.xn)");
		/*通过项目代码获取已经设置的年级,年级以逗号分割,针对学院情况*/
		String rskznj = new XmwhDao().getRsfpnj(model.getXmdm());
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc ) M ");
		sql.append(" left join (select count(1) YTJRS , t2.xydm,t2.xymc from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) t1 ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where exists (select 1 from xg_zjdx_pjpy_csszb t3 ");
		sql.append(" left join xg_zjdx_pjpy_fstjjlb t4 on t3.xn = t4.xn ");
		sql.append(" where t4.tjzt='1' and t4.xydm in (select distinct y.xydm from xg_zjdx_pjpy_cpmdb z left join view_njxyzybj_all y on z.bjdm = y.bjdm where z.bjdm is not null and z.xn = t3.xn ) and t1.xn=t3.xn ) ");
		// 通过项目代码获取已经设置的年级,年级以逗号分割,针对学院情况
		if (rskznj != null && !rskznj.trim().equals("")) {
			sql.append(" and t2.NJ in(");
			sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
			sql.append(")");
		}
		sql.append(" group by t2.xydm,t2.xymc) O ON M.XYDM = O.XYDM");
		sql.append(" LEFT JOIN xg_pjpy_new_rsszb N ON M.XYDM=N.BMDM  and N.nj is null ");
		sql.append(" left join xg_zjdx_pjpy_pjxmb t5 on N.xmdm = t5.xmdm");
		sql.append(" where t5.xmmc in (select xmmc from XG_PJPY_NEW_JESXXMB) and t5.xn in (select xn from xg_zjdx_pjpy_csszb) )t");
		sql.append(" where 1=1 ");
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND t.SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND t.XYDM='"+model.getXydm()+"'");
		}
		sql.append(searchTjByUser);
		sql.append(")");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	public int getYszrs(XyrsszForm model)throws Exception {
		/*根据项目代码获得此项目的所有数据*/
		HashMap<String, String> data = new XmwhDao().getDataById(model.getXmdm());
		/*定义num*/
		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(zzme) num from xg_pjpy_new_rsszb where xmdm = ? ");
		String rsfgfs = data.get("rsfpfs");
		/*学院*/
		if(rsfgfs.equals(Constants.RSKZFW_XY)) {
			String rsfpnj = data.get("rsfpnj");
			if (rsfpnj != null && !rsfpnj.trim().equals("")) {
				sql.append(" and bmdm in(select distinct xydm from view_njxyzybj_all where nj in(");
				/*格式化rskznj，增加''，以便拼接sql语句*/
				sql.append(setRskznj(rsfpnj));
				sql.append("))");
			}
		}
		String[] input = { model.getXmdm() };
		String[] output = { "num" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				try {
					num = Integer.parseInt(oneRs[0]);
				} catch (Exception e) {
				}
			}
		}
		return num;
	}
}

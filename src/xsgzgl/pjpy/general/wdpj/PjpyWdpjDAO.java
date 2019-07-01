package xsgzgl.pjpy.general.wdpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyWdpjDAO extends BasicDAO {

	// ==================执行查询操作 begin==============================
	
	/**
	 * 获取评奖项目性质列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmxzList() {
		DAO dao = DAO.getInstance();
		String sql = " select xzdm dm,xzmc mc from xg_pjpy_xmxzb ";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 学生已申请评奖信息
	 * @param myForm
	 * @param user
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getWdpjByStu(PjpyGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		
		StringBuilder sql = new StringBuilder();
		
		String xh=user.getUserName();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		
		String pjnd=jbszForm.getPjnd();

		sql.append(" select rownum r,a.* from ( ");
		
		sql.append(" select a.*, ");
		
		// --------------------评奖项目类型 begin--------------------	
		sql.append(" case when b.xmlx='01' then '奖学金' else '荣誉称号' end xmlx, ");
		// --------------------评奖项目类型 end----------------------			
	
		// ----------------------------------评奖项目性质、范围子查询 begin---------		
		sql.append(" (select xzmc from xg_pjpy_xmxzb c where b.xmxz=c.xzdm)xzmc, ");
		sql.append(" (select fwmc from xg_pjpy_xmfwb c where b.xmfw=c.fwdm)fwmc ");
		// --------------------评奖项目性质、范围子查询 end---------			
		sql.append("  from( ");
		sql.append(" select a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje, ");
		
		// --------------------申请日期格式转换 begin----------------------------------
		sql.append(" substr(a.sqsj,1,4)||'年'||substr(a.sqsj,5,2)||'月'||substr(a.sqsj,7,2)||'日' sqsj,");
		// --------------------申请日期格式转换 end----------------------------------
		
		// --------------------将审核信息中 ，转换为 -> -------------------------
		sql.append(" a.xmdm,replace(max(a.shzt),',','->')shzt  ");
		
		sql.append("  from(select a.xh, a.pjxn, a.pjxq, a.pjnd, b.xmmc, b.xmje, c.sqsj, a.xmdm,  ");
		
		// ---------------------------审核信息行转列操作 begin -------------------------		
		sql.append(" to_char(WM_CONCAT(e.mc||'('||shxx||')')over(partition by a.pjxn,  ");
		sql.append(" a.pjxq,a.pjnd,a.xh,a.xmdm  order by d.xh))shzt ");
		
		// <<<<<<<<<<<<<<<<<<<需审核记录的统计 begin>>>>>>>>>>>>>>>>>>>>
		
		// -------------------审核信息行转列操作 end --------------------				
		sql.append(" from (select a.*,  ");
		// -------------------审核信息行转列操作 begin ----------------------		
		
		sql.append(" case when shzt='wsh' then '未审核' ");
		sql.append("  when shzt='tg' then '通过' ");
		sql.append("  when shzt='btg' then '不通过' ");
		sql.append("  when shzt='th' then '退回' ");
		sql.append("  when shzt='xcs' then '需重审' end shxx ");
		// -------------------审核信息行转列操作 end  -------------------------	
		sql.append(" from xg_pjpy_pjxmshb a )a, ");
		sql.append(" xg_pjpy_pjxmwhb b,xg_pjpy_pjxmsqb c,xg_xtwh_spbz d,xg_xtwh_spgw e   ");
		sql.append(" where a.xh =? and a.xmdm=b.xmdm and a.xh=c.xh   ");
		sql.append(" and a.xmdm=c.xmdm and a.pjxn=c.pjxn and   ");
		sql.append(" a.pjxq=c.pjxq and a.pjnd=c.pjnd and b.lcid=d.splc and a.xtgwid=e.id and d.spgw=e.id  ");
		sql.append(" and a.pjxn=? and a.pjxq=? and a.pjnd=? ");
		sql.append(" )a  group by a.xh,a.pjxn,a.pjxq,a.pjnd,a.xmmc,a.xmje,a.sqsj,a.xmdm  ");
		// <<<<<<<<<<<<<<<<<<<需审核记录的统计 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// <<<<<<<<<<<<<<<<<<<无需审核记录 begin >>>>>>>>>>>>>>>>>>>>>>>>
		sql.append(" union select xh, pjxn, pjxq, pjnd, xmmc, xmje, sqsj, xmdm,'无需审核' shzt  ");
		sql.append(" from (select a.xh,a.xmdm,a.pjxn,a.pjxq,  ");
		sql.append(" a.pjnd,b.xmmc, b.xmje,a.sqsj,b.sfsh  ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmwhb b  ");
		sql.append(" where  a.pjxn = b.pjxn  and a.pjxq = b.pjxq and a.pjnd = b.pjnd  ");
		sql.append(" and a.xmdm = b.xmdm and a.pjxn=? and a.pjxq=? and a.pjnd=?) a  ");
		sql.append(" where sfsh = 'no'  ");
		sql.append(" and xh= ?)a,xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm ");
		// <<<<<<<<<<<<<<<<<<<无需审核记录 end >>>>>>>>>>>>>>>>>>>>>>>>
		sql.append(" )a  ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {xh,pjxn,pjxq,pjnd,pjxn,pjxq,pjnd,xh},
				new String[] {"xmmc","sqsj","shzt","xmdm"}, myForm);

	}
	
	public List<HashMap<String,String>> getWdpjDetailByStu(String xmdm, String xh){
		
		DAO dao=DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.mc,a.xh plxh, b.shzt,b.shsj,b.shyj,b.sftj,b.shr,");
		sql.append(" (select xm from yhb c where b.shr=c.yhm)shrxm");
		sql.append(" from (select spgw, xh,b.mc");
		sql.append(" from xg_xtwh_spbz  a,xg_xtwh_spgw b ");
		sql.append(" where a.spgw=b.id and  splc =");
		sql.append(" (select lcid");
		sql.append(" from xg_pjpy_pjxmwhb ");
		sql.append(" where xmdm = ?)) a");
		sql.append(" left join");
		sql.append(" (select *");
		sql.append(" from xg_pjpy_pjxmshb");
		sql.append(" where xh = ? ");
		sql.append(" and xmdm =?) b on a.spgw = b.xtgwid");
		sql.append(" order by a.xh");

		return dao.getListNotOut(sql.toString(), new String[] {xmdm,xh,xmdm});

}
	// ==================执行查询操作 end==============================
	
	public ArrayList<String[]> getWdpjByTea(PjpyGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		String userName= user.getUserName();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		
		String pjnd=jbszForm.getPjnd();
		
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "f",
				"xydm", "bjdm");
		
		sql.append(" select rownum r,a.* from ( ");
		
		// ----------------------------项目信息汇总 begin------------------------------
		sql.append(" select a.xmdm,a.xmmc,a.xmxz,a.xmfw,");
		sql.append(" case when a.xmlx='01' then '奖学金' else '荣誉称号' end xmlx,");
		sql.append(" max(xshrs)xshrs,max(shtgrs)shtgrs,max(ksbrs)ksbrs from( ");
		sql.append(" select a.xmlx,a.xmdm,a.xmmc,a.xmxz,a.xmfw, ");
		sql.append(" to_char(WM_CONCAT(a.mc||'('||nvl(b.xshrs,0)||')') ");
		sql.append(" over( partition by a.xmdm  order by a.xh))xshrs, ");
		sql.append(" to_char(WM_CONCAT(a.mc||'('||nvl(c.shtgrs,0)||')') ");
		sql.append(" over( partition by a.xmdm  order by a.xh))shtgrs, ");
		sql.append(" d.qdrs ksbrs ");
		// ---------------------------项目信息汇总 end-------------------------------------
		
		sql.append(" from  ");
		sql.append(" (select a.xmlx,a.xmdm,a.xmmc,b.spgw,c.mc,b.xh, ");
		sql.append(" (select xzmc from xg_pjpy_xmxzb d where a.xmxz=d.xzdm)xmxz, ");
		sql.append(" (select fwmc from xg_pjpy_xmfwb d where a.xmfw=d.fwdm)xmfw ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c, ");
		sql.append("  xg_xtwh_spgwyh d  ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id  and c.id = d.spgw ");
		sql.append(" and d.spyh = ? and a.pjxn=? and a.pjxq=? and a.pjnd=? ) a ");
		 
		// ----------------------------未审核信息统计 begin------------------------------
		sql.append(" left join ( ");
		sql.append(" select a.xmdm,c.id,b.xh,nvl(count(1),0)xshrs ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c,  ");
		sql.append(" xg_xtwh_spgwyh d ,xg_pjpy_pjxmshb e,xg_view_pjpy_pjryk f ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id and c.id = d.spgw ");
		sql.append(" and d.spyh = ?  and c.id=e.xtgwid and a.pjxn=e.pjxn and e.xh=f.xh ");
		sql.append(searchTjByUser);
		sql.append(" and a.pjxq=e.pjxq and a.pjnd=e.pjnd  and a.xmdm=e.xmdm ");
		sql.append(" and (shzt='wsh' or shzt='xcs') and (exists(  select 1 from(  ");
		sql.append(" select c.id,c.mc,b.xh plxh,e.* ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c,  ");
		sql.append(" xg_xtwh_spgwyh d ,xg_pjpy_pjxmshb e ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id and c.id = d.spgw ");
		sql.append(" and c.id=e.xtgwid and a.pjxn=e.pjxn ");
		sql.append(" and a.pjxq=e.pjxq and a.pjnd=e.pjnd ");
		sql.append(" and a.xmdm=e.xmdm)f where b.xh=f.plxh+1 and f.shzt='tg' ");
		sql.append(" and e.xmdm=f.xmdm and e.pjxn=f.pjxn and   ");
		sql.append(" e.pjxq=f.pjxq and e.pjnd=f.pjnd and e.xh=f.xh ");
		sql.append(" )or b.xh=1)group by a.xmdm,c.id,c.mc,b.xh ");
		sql.append(" )b on a.xmdm=b.xmdm and a.spgw=b.id  ");
		// ----------------------------未审核信息统计 end------------------------------	
		
		// ----------------------------已审核信息统计 begin----------------------------
		sql.append(" left join(select a.xmdm,c.id,b.xh,c.mc,nvl(count(1),0)shtgrs ");
		sql.append(" from xg_pjpy_pjxmwhb a, xg_xtwh_spbz b, xg_xtwh_spgw c,  ");
		sql.append(" xg_xtwh_spgwyh d ,xg_pjpy_pjxmshb e,xg_view_pjpy_pjryk f ");
		sql.append(" where a.lcid = b.splc and b.spgw = c.id  and c.id = d.spgw ");
		sql.append(" and d.spyh =? and c.id=e.xtgwid  and a.pjxn=e.pjxn and e.xh=f.xh ");
		sql.append(searchTjByUser);
		sql.append(" and a.pjxq=e.pjxq and a.pjnd=e.pjnd and a.xmdm=e.xmdm ");
		sql.append(" and shzt='tg' ");
		sql.append(" group by c.id,c.mc,a.xmdm,b.xh)c  on a.xmdm=c.xmdm and a.spgw=c.id  ");
		// ----------------------------已审核信息统计 end ----------------------------
		
		searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"bmdm", "bmdm");
		// ----------------------------可申报人数 begin ------------------------------
		sql.append(" left join (");
		sql.append(" select xmdm,sum(qdrs)qdrs from( select * from xg_pjpy_rsszb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" )group by xmdm) d on a.xmdm=d.xmdm ");
		//----------------------------可申报人数 end ------------------------------
		sql.append(" )a group by a.xmdm,a.xmmc,a.xmxz,a.xmfw,a.xmlx ");
		
		sql.append(" )a  where 1=1 ");
		
		//sql.append(searchTjByUser);

		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {
				userName, pjxn, pjxq, pjnd, userName, userName }, new String[] {
				"xmmc", "xmlx", "xshrs", "shtgrs","ksbrs", "xmdm" }, myForm);

	}
}

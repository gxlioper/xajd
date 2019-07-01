/**
 * @部门:学工产品事业部
 * @日期：2017-7-18 下午06:01:16 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿人员dao(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2017-7-18 下午06:01:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyryDao extends SuperDAOImpl<QnzyryForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QnzyryForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QnzyryForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.id,a.hdid,a.xh,a.bmzt,nvl(a.gs,' ') gss,a.gsshzt,a.sftj,a.bmsj,decode(a.gsshzt,'0','未审核','1','通过','2','退回') gsshztmc,d.hddd,d.hdmc,");
		//杭州师范大学个性化
		if(Base.xxdm.equals("10346")){
			sql.append("a.pjjg,a.pjbz,");
		}
		sql.append(" b.xm,b.nj,b.xymc,nvl(b.sjhm,'') sjhm,b.xydm,b.zydm,b.zymc,b.bjdm,b.bjmc,c.mc fwlxmc");
		sql.append(" from xg_dekt_zyhdryb a");
		sql.append(" left join xg_dekt_zyhdfbb d on a.hdid = d.hdid");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_dekt_fwlxb c on d.fwlx = c.dm");
		sql.append(" where 1=1");
		String shlx = t.getShzt();
		if (shlx.equalsIgnoreCase("dsh")) {
			sql.append(" and a.gsshzt = '0' and a.sftj is not null and a.bmzt = '1' and d.shzt = '1'");
		} else {
			sql.append(" and (a.gsshzt = '1' or (a.gsshzt = '2' and a.sftj is null)) and d.shzt = '1'");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:结果列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-27 上午09:20:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJgPageList(QnzyryForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.id,a.hdid,a.xh,a.bmzt,a.gs,a.gsshzt,a.bmsj,d.hddd,d.hdmc,d.fwdx,d.fwlx,");
		//杭州师范大学个性化
		if(Base.xxdm.equals("10346")){
			sql.append("a.pjjg,a.pjbz,");
		}
		sql.append(" b.xm,b.nj,b.xymc,b.sjhm,b.xydm,b.zydm,b.zymc,b.bjdm,b.bjmc,b.xb,c.mc fwlxmc,");
		sql.append(" (case when e.xm is null then f.xm else e.xm end) fzrxm");
		sql.append(" from xg_dekt_zyhdryb a");
		sql.append(" left join xg_dekt_zyhdfbb d on a.hdid = d.hdid");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_dekt_fwlxb c on d.fwlx = c.dm");
		sql.append(" left join fdyxxb e on d.hdfzr = e.zgh");
		sql.append(" left join view_xsbfxx f on d.hdfzr = f.xh");
		sql.append(" where a.gsshzt = '1'");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
	      描述: 工时审核结果导出
	 */
	public List<HashMap<String,String>> getAllList(QnzyryForm t, User user) throws Exception {		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getJgPageList(t, user);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(QnzyryForm.class);
		super.setKey("id");
		super.setTableName("xg_dekt_zyhdryb");				
	}

	/** 
	 * @描述:报名记录数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-19 下午01:38:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public Integer countJl(QnzyryForm t){
		String sql = "select count(1) num from xg_dekt_zyhdryb where hdid = ? and xh = ?";
		String num = dao.getOneRs(sql, new String[]{t.getHdid(),t.getXh()}, "num");
		return Integer.valueOf(num);
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取项目人员列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 上午10:17:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXmryList(QnzyryForm form) throws Exception{
		StringBuilder sb = new StringBuilder();
		String[] mhcx = null;
		String[] bmzts = null;
		sb.append(" select a.id,a.hdid,a.xh,a.bmzt,nvl(a.gs,' ') gss,nvl(a.sftj,'0') sftj,a.gsshzt,a.bmsj,a.gsshyj,decode(a.bmzt,'0','未审核','1','通过','2','不通过') bmztmc,");
		sb.append(" b.xm,b.nj,b.xymc,nvl(b.sjhm,' ') sjhm");
		sb.append(" from xg_dekt_zyhdryb a");
		sb.append(" left join view_xsbfxx b on a.xh = b.xh");
		sb.append(" where a.hdid = ?");
		if(StringUtils.isNotNull(form.getMhcx())){
			sb.append(" and (a.xh like '%' || ? || '%' or b.xm like '%' || ? || '%')");
			mhcx = new String[]{form.getMhcx(),form.getMhcx()};
		}
		if(null != form.getBmzts() && form.getBmzts().length > 0){
			sb.append(" and a.bmzt in (");
			bmzts = new String[form.getBmzts().length];
			for(int i = 0; i<form.getBmzts().length; i++){
				sb.append("?");
				if(i != form.getBmzts().length - 1){
					sb.append(",");
				}
				bmzts[i] = form.getBmzts()[i];
			}
			sb.append(")");
		}
		return getPageList(form,sb.toString(),StringUtils.joinStrArr(new String[]{form.getHdid()},mhcx,bmzts));
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量审核报名状态(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 下午05:18:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plshBmzt(String[] ids,String bmzt) throws SQLException{
		boolean flg = true;
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set bmzt = ?,sftj = '1',gsshzt = '0' where id = ?");
		List<String[]> params = new ArrayList<String[]>();
		for(String id : ids){
			String[] param = new String[]{bmzt,id};
			params.add(param);
		}
		int[] num = dao.runBatch(sb.toString(), params);
		for (int i = 0; i < num.length; i++) {
			flg = (num[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;	
	}
	
	/** 
	 * @描述:判断已通过报名人数和限定人数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 下午05:49:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> countYtgbmAndXdrs(String hdid){
		String sql = "select (select count(1) from xg_dekt_zyhdryb b where a.hdid = b.hdid and b.bmzt='1') tgrs,a.xdrs from xg_dekt_zyhdfbb a where a.hdid = ?";
		return dao.getMapNotOut(sql, new String[]{hdid});
	}
	
	/** 
	 * @描述:获取志愿活动报名信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 上午10:14:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getZyhdbmxx(String id){
		StringBuilder sb = new StringBuilder();
		sb.append(" select t1.*,");
		sb.append(" t2.hdmc,t2.fwlx,t2.hddd,t2.fwdx,t2.xdrs,t2.hdkssj,t2.hdjssj,t2.hdfzr,t2.hdfbr,t2.hdfzrlxfs,t2.zzbm,t2.fjpath,t2.fbzt,t2.jbfwgs,t2.bmjzsj,t2.jbfwgs,t7.xymc,");
		sb.append(" t6.mc fwlxmc, (case when t3.xm is null then t4.xm else t3.xm end) fzrxm,");
		sb.append(" decode(t1.gsshzt,'0','未审核','1','通过','2','退回',t1.gsshzt) gsshztmc,t1.gsshyj,");
		sb.append(" t5.nj,t5.xydm,t5.xymc,t5.zydm,t5.zymc");
		sb.append(" from xg_dekt_zyhdryb t1");
		sb.append(" left join xg_dekt_zyhdfbb t2 on t1.hdid = t2.hdid");
		sb.append(" left join fdyxxb t3 on t2.hdfzr = t3.zgh");
		sb.append(" left join view_xsbfxx t4 on t2.hdfzr = t4.xh");
		sb.append(" left join view_xsbfxx t5 on t1.xh = t5.xh");
		sb.append(" left join xg_dekt_fwlxb t6 on t2.fwlx = t6.dm");
		sb.append(" left join (select a.hdid,replace(wm_concat(b.bmmc),';',',') xymc from xg_dekt_hdxyglb a,zxbz_xxbmdm b where a.xydm = b.bmdm group by a.hdid) t7 on t1.hdid = t7.hdid");
		sb.append(" where t1.id = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{id});
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 下午03:46:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param gsshzt
	 * @param gsshyj
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plsh(String[] ids,String gsshzt,String gsshyj,String sftj) throws SQLException{
		boolean flg = true;
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set gsshzt = ?,gsshyj = ?");
		if(null != sftj && sftj.equalsIgnoreCase("")){
			sb.append(",sftj = null");
		}
		if(StringUtils.isNotNull(sftj)){
			sb.append(",sftj = ?");
		}
		sb.append(" where id = ?");
		List<String[]> params = new ArrayList<String[]>();
		for(String id : ids){
			String[] str;
			if(StringUtils.isNotNull(sftj)){
				str = new String[]{gsshzt,gsshyj,sftj,id};
			}else{
				str = new String[]{gsshzt,gsshyj,id};
			}
			params.add(str);
		}
		int[] num = dao.runBatch(sb.toString(), params);
		for (int i = 0; i < num.length; i++) {
			flg = (num[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;	
	}
	
	/** 
	 * @描述:批量审核根据mapList(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午02:34:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plsh(List<HashMap<String,String>> list) throws SQLException{
		boolean flg = true;
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set gsshzt = ?,gsshyj = ?,fwjg = ?,gs = ?");
		sb.append(" where id = ?");
		List<String[]> params = new ArrayList<String[]>();
		for(HashMap<String,String> map: list){
			String str[] = new String[]{map.get("gsshzt"),map.get("gsshyj"),map.get("fwjg"),map.get("gs"),map.get("id")};
			params.add(str);
		}
		int[] num = dao.runBatch(sb.toString(), params);
		for (int i = 0; i < num.length; i++) {
			flg = (num[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;	
	}
	
	/** 
	 * @描述:修改人员(这里用一句话描述这个方法的作用)
	 * @作者：[工号：1282]
	 * @日期：2017-8-7 上午10:12:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean upDateRy(QnzyryForm form) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_dekt_zyhdryb set gsshzt = ?,gs = ?");
		if(form.getGsshzt().equals(QnzyryAction.GSSHWTG)){
			sql.append("sftj = null");
		}
		return dao.runUpdate(sql.toString(), new String[]{form.getGsshzt(),form.getGs()});
	}
	
	/** 
	 * @描述:根据人员分数id获取基本分数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午02:11:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJbfByIds(String[] ids){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.id,b.jbfwgs from xg_dekt_zyhdryb a left join xg_dekt_zyhdfbb b on a.hdid = b.hdid where a.id in(");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length -1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.getListNotOut(sb.toString(), ids);
	}
	
	/** 
	 * @描述:批量撤销(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-30 下午05:34:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plcx(List<HashMap<String,String>> list) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_dekt_zyhdryb set gsshzt = '0',gs = ?,fwjg = null,gsshyj = null where id  = ?");
		List<String[]> lists = new ArrayList<String[]>();
		for(int i = 0;i<list.size();i++){
			String[] str = new String[2];
			str[0] = list.get(i).get("jbfwgs");
			str[1] = list.get(i).get("id");
			lists.add(str);
		}
		return dao.runBatchBoolean(sb.toString(), lists);
	}
	
	/** 
	 * @描述:计算总学分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-10 下午02:53:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String countTotalXf(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,sum(nvl(xf,0.0)) xf from");
		sql.append(" (select a.xh,");
		sql.append(" (case when nvl(a.gs, 0) < 15 then 0");
		sql.append(" when nvl(a.gs, 0) >= 15 and nvl(a.gs, 0) <= 29 then 0.5");
		sql.append(" when nvl(a.gs, 0) >= 30 and nvl(a.gs, 0) <= 49 then 1.0");
		sql.append(" when nvl(a.gs, 0) >= 50 and nvl(a.gs, 0) <= 99 then 3.0");
		sql.append(" when nvl(a.gs, 0) >= 100 and nvl(a.gs, 0) <= 149 then 4.0");
		sql.append(" when nvl(a.gs, 0) >= 150 then 5.0");
		sql.append(" end) xf from xg_dekt_zyhdryb a where a.gsshzt = '1' and a.xh = ?) group by xh");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xf");
	}
}

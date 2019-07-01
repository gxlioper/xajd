/**
 * @部门:学工产品事业部
 * @日期：2017-5-22 下午05:55:52 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 评奖评优-我的评奖-奖项审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-22 下午05:42:24 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmshDao extends SuperDAOImpl<XmshForm>implements Constants{

	/**
	 * 
	 * @描述: 查询
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-24 上午10:39:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param pmfs
	 * @param zcxmList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAudingListSingle(XmshForm t,User user ,String pmfs ,List<HashMap<String,String>> zcxmList) 
			throws Exception{
		StringBuffer sql =  new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t","xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		sql.append("select * from ( ");
		sql.append("select t1.xh,t9.xm,decode(t9.xb, '1', '男', '2', '女') xb,t9.xz,t9.sfzh,t10.mzmc,t9.yhkh,t11.pyccmc,t12.nj,t12.xydm,t12.xymc, ");
		sql.append("t12.zydm,t12.zymc,t12.bjdm,t12.bjmc,t9.mz,t1.xn,t3.xmmc,t8.zd3 tzxmmc,t3.xmje,t3.lxdm,t3.xzdm,t4.xzmc,t5.lxmc, ");
		sql.append("t1.sqsj,t1.sqly,t2.shzt,t2.shsj,t1.splc,  ");
		sql.append(" '[' || t13.mc || ':' || decode(t2.shzt, '0', '待审核', '1', '通过', '2', '不通过', '3', '退回', '4', '需重审') || ']' shztmc, ");
		sql.append("t1.id, t1.xmdm,t2.guid shid,t2.gwid,t13.gwz,t13.mc gwmc, ");
		
		for(int i = 0 , j = zcxmList.size(); i < j; i++){
			sql.append("t15.fs").append(i).append(",t15.pm").append(i).append(",");
		}
		
		
		sql.append("row_number() over(partition by t2.ywid order by t2.shsj desc) as lvl ");
		sql.append("from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.id = t2.ywid ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t3 on t1.xmdm = t3.xmdm  ");
		sql.append("left join xg_zjdx_pjpy_xmxz t4 on t4.xzdm = t3.xzdm  ");
		sql.append("left join xg_zjdx_pjpy_xmlx t5 on t5.lxdm = t3.lxdm  ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t6 on t1.xh = t6.xh and t1.xn = t6.xn ");
		sql.append("left join (select * from (select t7.*,row_number() over(partition by t7.ywid order by t7.shsj desc) pdrn from xg_xtwh_shztb t7 ");
		sql.append("where t7.shsj is not null) where pdrn = 1) t8 on t1.id = t8.ywid and t8.zd2 = t3.xmdm ");
		sql.append("left join xsxxb t9 on t1.xh = t9.xh ");
		sql.append("left join mzdmb t10 on t9.mz = t10.mzdm ");
		sql.append("left join xg_xsxx_pyccdmb t11 on t11.pyccdm = t9.pycc ");
		sql.append("left join view_njxyzybj_all t12 on t12.bjdm = t6.bjdm ");
		sql.append("left join xg_xtwh_spgw t13 on t13.id = t2.gwid ");
		
		/*===============综测循环BEGIN===============*/
		sql.append("left join ( ");
		sql.append("select ");
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		sql.append("xh,xn from (select ");
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t16.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t16.fs else '0' end fs").append(i).append(",");
			sql.append(" case when t16.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t16.").append(pmfs).append(" else '' end pm")
			   .append(i).append(",");
		}
		sql.append(" t16.xh,t16.xn from xg_zjdx_pjpy_zcfsb t16 where 1=1 ");
		if(t.getSearchModel().getSearch_tj_xn()!=null){
			sql.append(" and t16.xn ='"+t.getSearchModel().getSearch_tj_xn()[0]+"' ");
		}
		sql.append(" ) group by xh,xn)t15 on t15.xh=t6.xh and t15.xn=t6.xn ");
		/*===============综测循环END===============*/
		
		sql.append(" where ");
		if(WSH.equals(t.getShzt())){
			sql.append("t3.shkg = '1' ");
			sql.append("and (sysdate between ");
			sql.append("to_date(nvl(t3.shkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and ");
			sql.append("to_date(nvl(t3.shjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')) and ");
			sql.append("t2.shzt in ('0', '4') and ");
		}else{
			sql.append("t2.shzt not in ('0','4') and ");
		}
		sql.append("t2.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' ) ");
		sql.append("and exists (select 1 ");
		sql.append("from xg_zjdx_pjpy_cpmdb t14 where t14.xh = t1.xh  and t14.xn = t1.xn) ");
		sql.append(") t where lvl = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(),inputValue);
	}
	
	/**
	 * @描述: 综测项目信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-23 下午05:31:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmList(String xn){
		StringBuffer sql =  new StringBuffer();
		sql.append("select xmdm dm, xn, xn || xmmc mc ");
		sql.append("from (select a.xmdm,a.xn,a.xmmc,a.fjdm,px, ");
		sql.append("case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, ");
		sql.append("decode(a.xmlx, '0', '等级', '1', '分值', a.xmlx) xmlx from xg_zjdx_pjpy_zcxmb a ");
		sql.append("left join (select xmdm, max(result) result from (select xmdm,mc,wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append("from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b on a.xmdm = b.xmdm ");
		sql.append("START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t ");
		sql.append("where (t.fjdm = 'top' or exists (select 1 ");
		sql.append("from xg_zjdx_pjpy_zcxmb t2 where t.fjdm = t2.xmdm and t2.fjdm = 'top')) and xn = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
	
	
	@Override
	protected void setTableInfo() {
		/*主表为项目申请表*/
		super.setClass(XmshForm.class);
		super.setTableName("xg_zjdx_pjpy_xmsq");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/** 
	 * @描述: 项目审核明细
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-24 下午02:29:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmshShmx(XmshForm t, User user) throws Exception {
		String shgwzByUser = SearchService.getShgwzByUser(user, "t10","xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t10.xydm || t1.xmdm key,t10.xymc,t10.xydm,t1.xmdm,t4.xmmc,");
		sql.append("nvl(t11.zzme, 0) zzme, ");
		sql.append("sum(decode(t1.shzt, '1', '1', '0')) ytgrs, ");
		sql.append("nvl(t11.zzme - sum(decode(t1.shzt, '1', '1', '0')), 0) syktgrs,");
		sql.append("sum(case when t2.shzt in ('0', '4') then  '1' else  '0' end) bcshrs ");
		sql.append("from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join (select * from (select a.*, row_number() over(partition by ywid order by shsj desc) rm from xg_xtwh_shztb a) where rm = 1) t2 on t1.id = t2.ywid ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t4 on t1.xmdm = t4.xmdm ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t5 on t1.xh = t5.xh and t1.xn = t5.xn ");
		sql.append("left join view_njxyzybj_all t10 on t5.bjdm = t10.bjdm ");
		sql.append("left join (select bmdm, case when zd1 is null then  '0' else zzme end zzme, xmdm from xg_pjpy_new_rsszb) t11 on t10.xydm = t11.bmdm and t11.xmdm = t1.xmdm ");
		sql.append("left join xg_xtwh_spgw t12 on t12.id = t2.gwid ");
		sql.append("left join xg_xtwh_bmsxb t13 on t10.xydm = t13.bmdm ");
		sql.append("where t1.xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) ");
		sql.append("and t4.shkg = '1' ");
		sql.append("and t10.xymc is not null ");
		sql.append("and sysdate between to_date(nvl(t4.shkssj, '2018-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and to_date(nvl(t4.shjssj, '2038-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ");
		sql.append("and t2.gwid in (select spgw from xg_xtwh_spgwyh where spyh = '"+user.getUserName()+"' ) ");
		sql.append("and exists (select 1 from xg_zjdx_pjpy_cpmdb t3 where t1.xh = t3.xh and t1.xn = t3.xn) ");
		sql.append(shgwzByUser);
		if(null != t.getXyXmdm() && !StringUtils.isNull(t.getXyXmdm().trim())){
			sql.append("and (t10.xymc like '%"+t.getXyXmdm()+"%' or t4.xmmc like '%"+t.getXyXmdm()+"%')");
		}
		if(null != t.getId() && !StringUtils.isNull(t.getId().trim())){
			String[] sqids = t.getId().split(",");
			sql.append("and t1.id in ('"); 
			for (int i = 0; i < sqids.length; i++) {
				if(i==0){
					sql.append(sqids[i]);
				}else{
					sql.append("','"+sqids[i]);
				}
			}
			sql.append("')");
		}
		sql.append("group by t10.xymc, t10.xydm, t4.xmmc, t1.xmdm, t11.zzme, t13.sx, t4.xssx ");
		sql.append("order by to_number(t13.sx), to_number(t4.xssx) ");
		return getPageList(t, sql.toString(),new String[]{});
	}
	
	/**
	 * @描述: 这里没考虑高级查询和用户权限范围，目前传过来的院系||项目代码全部审核
	 * 		    查询【申请ID、审核状态表的GWID、学号、审核状态表的LCID】
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午03:39:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZdshjgList (XmshForm t, User user) {
		List<String> params = new ArrayList<String>();
		String[] xyXmdm = t.getXyXmdm().split(",");
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, b.gwid, a.xh, b.lcid from xg_zjdx_pjpy_xmsq a ");
		sql.append("left join xg_xtwh_shztb b on a.id = b.ywid ");
		sql.append("left join xg_zjdx_pjpy_cpmdb c on a.xh = c.xh and a.xn = c.xn ");
		sql.append("left join view_njxyzybj_all d on c.bjdm = d.bjdm ");
		sql.append("where d.xydm||a.xmdm in (");
		for(int i = 0; i < xyXmdm.length; i++){
			if(i == 0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xyXmdm[i]);
		}
		sql.append(" ) ");
		if(StringUtils.isNotNull(t.getId())){
			String[] id = t.getId().split(",");
			sql.append(" and a.id in (");
			
			for (int i = 0; i < id.length; i++) {
				if(i==0){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(id[i]);
			}
			sql.append(")");
		}
		sql.append("and b.shzt='0'");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * @描述: 判断学号当前学年学期是否有未审核或者已通过的记录
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午04:40:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xmdm
	 * @param xh
	 * @param id
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkXhsqsh(String xn,String xmdm, String xh, String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_zjdx_pjpy_xmsq a ");
		sql.append("left join (select * from (select b.*, row_number() over(partition by ywid order by shsj desc) rn from xg_xtwh_shztb b) ");
		sql.append("where rn = 1) b on a.id = b.ywid left join xg_xtwh_spbz c on b.lcid = c.splc and b.gwid = c.spgw where a.shzt not in ('0', '2', '3') ");
		sql.append("and b.shzt in ('4', '0', '1') ");
		sql.append("and a.xh = ? and a.xn = ? and a.xmdm = ? and a.id <> ? and c.xh <> '1'");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xmdm,id}, "count");
	}
	
	/**
	 * @描述: 人数控制级别
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午04:58:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getRskzXh(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from xg_zjdx_pjpy_pjxmb a left join XG_XTWH_SPBZ b on a.shlc = b.splc and a.rskzjb = b.spgw where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xh");
	}
	
	/**
	 * @描述: 学生参评班级信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午05:27:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCpbjListByXh(String xh, String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xn,a.xh,a.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,b.nj from xg_zjdx_pjpy_cpmdb a ");
		sql.append("left join view_njxyzybj_all b on a.bjdm=b.bjdm  ");
		sql.append("where a.xn = ? and a.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	/**
	 * @描述: 更新申请记录
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 上午11:36:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param tzhxmdm
	 * @param shzt
	 * @param dqxmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSqjl(String sqid,String dqxmdm,String shzt) throws Exception{
		String sql = "update xg_zjdx_pjpy_xmsq set shzt=? ,xmdm = ? where id = ? ";
		return dao.runUpdate(sql, new String[]{shzt,dqxmdm,sqid});
	}
	
	/**
	 * @描述: 根据项目参数，查询最终人数等
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 上午11:54:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xh
	 * @param rskzfw
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getRsszOne(String xmdm, String xh,String xn) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.zzme from xg_pjpy_new_rsszb a, ");
		sb.append("(select a.*, b.nj, b.xydm, b.xymc, b.zydm, b.zymc, b.bjmc ");
		sb.append("from (select * from xg_zjdx_pjpy_cpmdb where bjdm is not null) a ");
		sb.append("left join view_njxyzybj_all b on a.bjdm = b.bjdm) b where 1=1 ");
		sb.append("and a.bmdm=b.xydm ");
		sb.append("and a.xmdm=? ");
		sb.append("and b.xh=? ");
		sb.append("and b.xn = ? ");
		String[] inputValue = new String[] {xmdm,xh,xn};
		String[] outputValue = new String[] { "zzme"};
		String sql = sb.toString();
		HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
		return map;
	}
	
	/**
	 * @描述: 人数控制 --按学院查询岗位已通过的人数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 下午03:10:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param gwid
	 * @param xmdm
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsByXy(String xn, String gwid, String xmdm, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from (select t1.id,t1.xh,t2.shzt,t2.gwid,t1.xn,t1.xmdm,t3.bjdm,t4.xydm,t4.nj,row_number() over(partition by t1.id, t2.gwid order by t2.shsj desc) lvl ");
		sql.append("from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.id = t2.ywid ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t3 on t1.xh = t3.xh and t1.xn = t3.xn ");
		sql.append("left join view_njxyzybj_all t4 on t3.bjdm = t4.bjdm) where lvl = 1 and shzt = '1' ");
		sql.append("and xn = ? and gwid = ? and xmdm = ? ");
		sql.append("and xydm = (select xydm from view_njxyzybj where bjdm = (select bjdm  from xg_zjdx_pjpy_cpmdb where xn = ? and xh = ?)) ");
		return dao.getOneRs(sql.toString(), new String[]{xn,gwid,xmdm,xn,xh}, "count");
	}
	
	/**
	 * @描述: 学生所在学院的总参评人数的35%(保留小数)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-19 上午11:40:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXyCprsAll(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select count * 0.35 yzzrs from (");
		sql.append("select count(*)count ");
		sql.append("from (select t2.xn, t2.xh, t2.bjdm, t3.xydm ");
		sql.append("from xg_zjdx_pjpy_cpmdb t2 ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm) t1 ");
		sql.append("where t1.xydm in (select t5.xydm ");
		sql.append("from xg_zjdx_pjpy_cpmdb t4 ");
		sql.append("left join view_njxyzybj_all t5 on t4.bjdm = t5.bjdm ");
		sql.append("where t4.xh = ? ");
		sql.append("and t4.xn = (select xn from xg_zjdx_pjpy_csszb)) ");
		sql.append("and t1.xn = (select xn from xg_zjdx_pjpy_csszb) ");
		sql.append(")");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "yzzrs");
	}
	
	/**
	 * @描述: 获取学生所在学院5个 标兵已通过次数
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-19 下午08:56:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBbYtgCs(String xh,String gwid){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)bbytgcs from ( ");
		sql.append("select t1.id,t1.xh,t1.xn,t1.xmdm,t2.bjdm,t3.xydm,t3.xymc,t4.xmmc,t5.gwid,t5.shzt, ");
		sql.append("row_number() over(partition by t1.id, t5.gwid order by t5.shsj desc) lvl ");
		sql.append("from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn ");
		sql.append("left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t4 on t1.xn = t4.xn and t1.xmdm = t4.xmdm ");
		sql.append("left join xg_xtwh_shztb t5 on t1.id = t5.ywid ) t ");
		sql.append("where lvl = 1 ");
		sql.append("and shzt = '1' ");
		sql.append("and t.xn = (select xn from xg_zjdx_pjpy_csszb) ");
		sql.append("and t.xmmc in ('社会工作标兵','创新创业标兵','公益服务标兵','对外交流标兵','文体活动标兵') ");
		sql.append("and t.xydm = (select xydm from xg_zjdx_pjpy_cpmdb t6 left join view_njxyzybj_all t7 on t6.bjdm = t7.bjdm where t6.xh = ? and t6.xn = (select xn from xg_zjdx_pjpy_csszb)) ");
		sql.append("and gwid = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh,gwid}, "bbytgcs");
	}
}

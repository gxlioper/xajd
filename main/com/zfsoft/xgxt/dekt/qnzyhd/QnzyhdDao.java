/**
 * @部门:学工产品事业部
 * @日期：2017-7-12 下午06:48:51 
 */  
package com.zfsoft.xgxt.dekt.qnzyhd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿活动dao(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-12 下午06:48:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyhdDao extends SuperDAOImpl<QnzyhdForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QnzyhdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QnzyhdForm t, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,row_number() over(partition by t.pxzt order by t.hdkssj desc) rn from (select t.*,(case when t.hdlsxm is null then t.hdxsxm else t.hdlsxm end) fzrxm,");
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			sql.append(" (case when t.xszt = '1' then '7'");
			sql.append(" when t.xszt = '0' and t.jxzt = '立即报名' then '2'");
			sql.append(" when t.xszt = '0' and t.jxzt = '正在进行中' then '3'");
			sql.append(" when t.xszt = '0' and t.jxzt = '报名已截止' then '5'");
			sql.append(" when t.xszt = '0' and t.jxzt = '人数已满' then '6'");
			sql.append(" when t.xszt = '0' and t.jxzt = '已结束' then '4' end) pxzt");
		}else{
			sql.append(" (case when t.jxzt = '立即报名' then '未开始'");
			sql.append(" when t.jxzt = '正在进行中' then '进行中'");
			sql.append(" when t.jxzt = '报名已截止' then '报名已截止'");
			sql.append(" when t.jxzt = '人数已满' then '人数已满'");
			sql.append(" when t.jxzt = '已结束' then '已结束' end) lsjxzt,");
			sql.append(" (case when t.jxzt = '立即报名' then '2'");
			sql.append(" when t.jxzt = '正在进行中' then '3'");
			sql.append(" when t.jxzt = '报名已截止' then '5'");
			sql.append(" when t.jxzt = '人数已满' then '6'");
			sql.append(" when t.jxzt = '已结束' then '4' end) pxzt");
		}
		sql.append(" from (");
		sql.append(" select t1.*,t2.mc fwlxmc,nvl(t10.num,'0') ybrs,t4.xm hdlsxm,t5.xm hdxsxm,t7.xymc,");
		sql.append(" (case when to_number(nvl(t10.num,'0')) >= to_number(t1.xdrs) then '人数已满'");
		sql.append(" when to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') > sysdate and t1.hdjssj is null and to_date(t1.bmjzsj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_number(nvl(t10.num,'0')) < to_number(t1.xdrs) then '立即报名'");
		sql.append(" when to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') < sysdate and t1.hdjssj is null and to_date(t1.bmjzsj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_number(nvl(t10.num,'0')) < to_number(t1.xdrs) then '正在进行中' ");
		sql.append(" when t1.hdjssj is not null and to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_date(t1.bmjzsj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_number(nvl(t10.num,'0')) < to_number(t1.xdrs) then '立即报名'");
		sql.append(" when t1.hdjssj is not null and to_date(t1.hdjssj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') < sysdate and to_date(t1.bmjzsj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_number(nvl(t10.num,'0')) < to_number(t1.xdrs) then '正在进行中'");
		sql.append(" when t1.hdjssj is not null and to_date(t1.hdjssj,'yyyy-MM-dd HH24:mi:ss') < sysdate and nvl(t10.num,'0') < to_number(t1.xdrs) then '已结束'");
		sql.append(" when to_date(t1.bmjzsj,'yyyy-MM-dd HH24:mi:ss') < sysdate and nvl(t10.num,'0') < to_number(t1.xdrs) then '报名已截止'");
		sql.append(" end) jxzt ");
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			sql.append(",(case when t6.xh is not null then '1' else '0' end) xszt");
		}
		sql.append(" from xg_dekt_zyhdfbb t1");
		sql.append(" left join xg_dekt_fwlxb t2 on t1.fwlx = t2.dm");
		sql.append(" left join fdyxxb t4 on t1.hdfzr = t4.zgh");
		sql.append(" left join view_xsbfxx t5 on t1.hdfzr = t5.xh");
		sql.append(" left join (select count(xh) num ,hdid from xg_dekt_zyhdryb group by hdid) t10 on t1.hdid = t10.hdid");
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			sql.append(" left join (select xh,hdid from xg_dekt_zyhdryb where xh = ?) t6 on t1.hdid = t6.hdid");
		}
		sql.append(" left join (select a.hdid,replace(wm_concat(b.bmmc),';',',') xymc from xg_dekt_hdxyglb a,zxbz_xxbmdm b where a.xydm = b.bmdm group by a.hdid) t7 on t1.hdid = t7.hdid");
		sql.append(" where t1.fbzt = '1' and t1.shzt = '1'");
		sql.append(" ) t) t where 1 = 1 ");
		String[] mhcxs = null;
		String[] sjs = null;
		String[] kssjs = null;
		String[] jssjs = null;
		//String[] userName = null;
		if(StringUtils.isNotNull(t.getMhcx())){
			sql.append(" and (t.hdmc like '%' || ? || '%' or t.hddd like '%' || ? || '%' or t.fwdx like '%' || ? || '%' or t.hdfzr like '%' || ? || '%' or t.hdlsxm like '%' || ? || '%' or t.hdxsxm like '%' || ? || '%' or t.zzbm like '%' || ? || '%')");
			mhcxs = new String[]{t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx()};
		}
		if(StringUtils.isNotNull(t.getHdkssj()) && StringUtils.isNotNull(t.getHdjssj())){
			sql.append(" and (t.hdkssj >= ? and t.hdjssj <= ?)");
			sjs = new String[]{t.getHdkssj(),t.getHdjssj()};
		}else if(StringUtils.isNotNull(t.getHdkssj()) && StringUtils.isNull(t.getHdjssj())){
			sql.append(" and (t.hdkssj >= ? )");
			kssjs = new String[]{t.getHdkssj()};
		}else if(StringUtils.isNotNull(t.getHdjssj()) && StringUtils.isNull(t.getHdkssj())){
			sql.append(" and (t.hdjssj <= ?)");
			kssjs = new String[]{t.getHdjssj()};
		}
//		if(user.getUserStatus().equalsIgnoreCase("stu")){
//			sql.append("and not exists (select 1 from xg_dekt_zyhdryb t6 where t.hdid = t6.hdid and t6.xh = ?) ");
//			userName = new String[]{user.getUserName()};
//		}
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			//sql.append(" order by to_number(t.xszt) desc,t.hdkssj desc");
			String bmdm = user.getUserDep();
			sql.append("and exists (select 1 from (select hdid from xg_dekt_hdxyglb where xydm = ?) b where t.hdid = b.hdid)");
			sql.append(" order by t.pxzt asc");
			return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{user.getUserName()},mhcxs,sjs,kssjs,jssjs,new String[]{bmdm}));
		}else{
			//sql.append(" order by t.hdkssj desc");
			sql.append(" order by t.pxzt asc");
			return getPageList(t, sql.toString(), StringUtils.joinStrArr(mhcxs,sjs,kssjs,jssjs));
		}
	}
	
	/** 
	 * @描述:获取已发布活动(根据用户身份判断)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-20 下午02:43:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYfbhdList(QnzyhdForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,row_number() over(partition by t.shzt order by t.hdkssj desc) rn,(case when t.hdlsxm is null then t.hdxsxm else t.hdlsxm end) fzrxm,");
		sql.append(" decode(t.fbzt,'1','已发布','未发布') fbztmc,");
		sql.append(" (case when t.hdfzr = ? then 'fzr' else 'fbr' end) ryzt,");//判断人员状态
		sql.append(" decode(t.shzt,'0','未审核','1','通过','2','不通过','3','退回','未审核') shztmc");
		sql.append(" from (");
		sql.append(" select t1.*,t2.mc fwlxmc,(select count(1) from xg_dekt_zyhdryb t3 where t1.hdid = t3.hdid) ybrs,t4.xm hdlsxm,t5.xm hdxsxm,t7.xymc,");
		sql.append(" (case when to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') > sysdate then '立即报名'");
		sql.append(" when to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') < sysdate and t1.hdjssj is null then '正在进行中' ");
		sql.append(" when t1.hdjssj is not null and to_date(t1.hdjssj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_date(t1.hdkssj,'yyyy-MM-dd HH24:mi:ss') < sysdate then '正在进行中'");
		sql.append(" when t1.hdjssj is not null and to_date(t1.hdjssj,'yyyy-MM-dd HH24:mi:ss') < sysdate then '已结束'");
		sql.append(" end) jxzt ");
		sql.append(" from xg_dekt_zyhdfbb t1 left join xg_dekt_fwlxb t2 on t1.fwlx = t2.dm");
		sql.append(" left join fdyxxb t4 on t1.hdfzr = t4.zgh");
		sql.append(" left join view_xsbfxx t5 on t1.hdfzr = t5.xh");
		sql.append(" left join (select a.hdid,replace(wm_concat(b.bmmc),';',',') xymc from xg_dekt_hdxyglb a,zxbz_xxbmdm b where a.xydm = b.bmdm group by a.hdid) t7 on t1.hdid = t7.hdid");
		sql.append(" ) t where 1 = 1 ");
		String[] yyr = new String[]{user.getUserName()};
		String[] mhcxs = null;
		String[] sjs = null;
		String[] kssjs = null;
		String[] jssjs = null;
		String[] userName = null;
		String [] shzt = null;
		if(StringUtils.isNotNull(t.getMhcx())){
			sql.append(" and (t.hdmc like '%' || ? || '%' or t.hddd like '%' || ? || '%' or t.fwdx like '%' || ? || '%' or t.hdfzr like '%' || ? || '%' or t.hdlsxm like '%' || ? || '%' or t.hdxsxm like '%' || ? || '%' or t.zzbm like '%' || ? || '%')");
			mhcxs = new String[]{t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx()};
		}
		if(StringUtils.isNotNull(t.getHdkssj()) && StringUtils.isNotNull(t.getHdjssj())){
			sql.append(" and (t.hdkssj >= ? and t.hdjssj <= ?)");
			sjs = new String[]{t.getHdkssj(),t.getHdjssj()};
		}else if(StringUtils.isNotNull(t.getHdkssj()) && StringUtils.isNull(t.getHdjssj())){
			sql.append(" and (t.hdkssj >= ? )");
			kssjs = new String[]{t.getHdkssj()};
		}else if(StringUtils.isNotNull(t.getHdjssj()) && StringUtils.isNull(t.getHdkssj())){
			sql.append(" and (t.hdjssj <= ?)");
			kssjs = new String[]{t.getHdjssj()};
		}		
		sql.append("and (t.hdfbr = ? or hdfzr = ?)");
		userName = new String[]{user.getUserName(),user.getUserName()};
		if(null != t.getShzt() && !t.getShzt().equals("")){
			sql.append(" and t.shzt = ?");
			shzt = new String[] {t.getShzt()};
		}
		sql.append(" order by t.shzt asc");
		//sql.append(" order by t.hdkssj desc");
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(yyr,mhcxs,sjs,kssjs,jssjs,userName,shzt));
	}
	
	/** 
	 * @描述:获取已参加活动列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-24 下午02:20:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYcjhdList(QnzyhdForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,nvl(t.sftj,'0') sftjzt,(case when t.hdlsxm is null then t.hdxsxm else t.hdlsxm end) fzrxm,");
		sql.append(" (case when to_date(t.bmjzsj,'yyyy-MM-dd HH24:mi:ss') > sysdate then '1' else '0' end) qxzt");
		sql.append(" from (");
		sql.append(" select t1.*,nvl(t2.jbfwgs,'0')gss,t2.bmjzsj,t2.hdmc,t2.fwlx,t2.hdfzrlxfs,t2.fjpath,t2.zzbm,t2.hddd,t2.fwdx,t2.xdrs,t7.xm,t6.mc fwlxmc,t2.hdfzr,t2.hdkssj,t2.hdjssj,(select count(1) from xg_dekt_zyhdryb t3 where t2.hdid = t3.hdid) ybrs,t4.xm hdlsxm,t5.xm hdxsxm,");
		sql.append(" decode(t1.bmzt,'0','未审核','1','通过','2','不通过','5','未审核',t1.bmzt) bmztmc,");
		sql.append(" decode(t1.gsshzt,'0','未审核','1','通过','2','退回','5','未审核','未审核') gsshztmc,");
		sql.append(" (case when to_date(t2.hdkssj,'yyyy-MM-dd HH24:mi:ss') > sysdate then 'noBegining'");
		sql.append(" when to_date(t2.hdkssj,'yyyy-MM-dd HH24:mi:ss') < sysdate and t2.hdjssj is null then 'onGoing' ");
		sql.append(" when t2.hdjssj is not null and to_date(t2.hdjssj,'yyyy-MM-dd HH24:mi:ss') > sysdate and to_date(t2.hdkssj,'yyyy-MM-dd HH24:mi:ss') < sysdate then 'onGoing'");
		sql.append(" when t2.hdjssj is not null and to_date(t2.hdjssj,'yyyy-MM-dd HH24:mi:ss') < sysdate then 'over'");
		sql.append(" end) jxzt ");
		sql.append(" from xg_dekt_zyhdryb t1");
		sql.append(" left join xg_dekt_zyhdfbb t2 on t1.hdid = t2.hdid");
		sql.append(" left join fdyxxb t4 on t2.hdfzr = t4.zgh");
		sql.append(" left join view_xsbfxx t5 on t2.hdfzr = t5.xh");
		sql.append(" left join xg_dekt_fwlxb t6 on t2.fwlx = t6.dm");
		sql.append(" left join view_xsbfxx t7 on t1.xh = t7.xh) t");
		sql.append(" where 1= 1");
		String[] mhcxs = null;
		String[] sjs = null;
		String[] kssjs = null;
		String[] jssjs = null;
		String[] gsshzt = null;
		String[] userName = null;
		if(StringUtils.isNotNull(t.getMhcx())){
			sql.append(" and (t.hdmc like '%' || ? || '%' or t.hddd like '%' || ? || '%' or t.fwdx like '%' || ? || '%' or t.hdfzr like '%' || ? || '%' or t.hdlsxm like '%' || ? || '%' or t.hdxsxm like '%' || ? || '%' or t.zzbm like '%' || ? || '%')");
			mhcxs = new String[]{t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx(),t.getMhcx()};
		}
		if(StringUtils.isNotNull(t.getHdkssj()) && StringUtils.isNotNull(t.getHdjssj())){
			sql.append(" and (t.hdkssj >= ? and t.hdjssj <= ?)");
			sjs = new String[]{t.getHdkssj(),t.getHdjssj()};
		}else if(StringUtils.isNotNull(t.getHdkssj()) && StringUtils.isNull(t.getHdjssj())){
			sql.append(" and (t.hdkssj >= ? )");
			kssjs = new String[]{t.getHdkssj()};
		}else if(StringUtils.isNotNull(t.getHdjssj()) && StringUtils.isNull(t.getHdkssj())){
			sql.append(" and (t.hdjssj <= ?)");
			kssjs = new String[]{t.getHdjssj()};
		}
		if(StringUtils.isNotNull(t.getGsshzt())){
			sql.append(" and t.gsshzt = ?");
			gsshzt = new String[]{t.getGsshzt()};
		}
		sql.append(" and (t.xh = ?)");
		userName = new String[]{user.getUserName()};
		sql.append(" order by t.hdkssj desc");
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(mhcxs,sjs,kssjs,jssjs,gsshzt,userName));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(QnzyhdForm.class);
		super.setKey("hdid");
		super.setTableName("xg_dekt_zyhdfbb");		
	}
	
	/** 
	 * @描述:获取服务类型(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-17 下午03:37:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getFwlxList(){
		String sql = "select dm,mc from xg_dekt_fwlxb order by mc";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/** 
	 * @描述:获取活动信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-19 上午09:27:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getQnzyhdInfo(String hdid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.hdid,a.hdmc,a.fwlx,a.hddd,a.fwdx,a.xdrs,a.hdkssj,a.hdjssj,a.hdfzr,a.hdfbr,a.hdfzrlxfs,a.hdxq,a.zzbm,a.fjpath,a.fbzt,a.bmjzsj,a.jbfwgs,e.xymc,");
		sql.append(" decode(a.fbzt,'1','取消发布','发布') fbztmc,");
		sql.append(" b.mc fwlxmc, (case when c.xm is null then d.xm else c.xm end) fzrxm,");
		sql.append(" (select count(1) from xg_dekt_zyhdryb e where a.hdid = e.hdid) ybrs,");
		sql.append(" (select count(1) from xg_dekt_zyhdryb f where a.hdid = f.hdid and bmzt = '1') ytgrs");
		sql.append(" from xg_dekt_zyhdfbb a");
		sql.append(" left join xg_dekt_fwlxb b on a.fwlx = b.dm");
		sql.append(" left join fdyxxb c on a.hdfzr = c.zgh");
		sql.append(" left join view_xsbfxx d on a.hdfzr = d.xh");
		sql.append(" left join (select a.hdid,replace(wm_concat(b.bmmc),';',',') xymc from xg_dekt_hdxyglb a,zxbz_xxbmdm b where a.xydm = b.bmdm group by a.hdid) e on a.hdid = e.hdid");
		sql.append(" where a.hdid = ?");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[]{hdid});
		CLOB clob = dao.getOneClob(sql.toString(), new String[] { hdid }, "hdxq");
		String msg = clob.getSubString(1L, (int) clob.length());
		map.put("hdxq", msg);
		return map;
	}
	
	/**
	 * @throws SQLException  
	 * @描述:插入关联学院(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-28 上午11:28:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertGlXy(QnzyhdForm t) throws SQLException{
		StringBuilder sb = new StringBuilder();
		sb.append("insert into xg_dekt_hdxyglb(hdid,xydm) values (?,?)");
		String[] xydms = t.getXydms();
		String hdid = t.getHdid();
		List<String[]> params = new ArrayList<String[]>();
		for(String xydm : xydms){
			String[] param = new String[2];
			param[0] = hdid;
			param[1] = xydm;
			params.add(param);
		}
		return dao.runBatchBoolean(sb.toString(), params);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:获取关联学院(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-28 下午02:45:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String> 返回类型 
	 * @throws 
	 */
	public List<String> getGlXy(QnzyhdForm t) throws SQLException{
		String sql = "select xydm from xg_dekt_hdxyglb where hdid = ?";
		return dao.getList(sql, new String[]{t.getHdid()}, "xydm");
	}
	
	/**
	 * @throws Exception  
	 * @描述:删除关联学院(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-28 下午03:12:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean scGlxy(QnzyhdForm t) throws Exception{
		String sb = "delete from xg_dekt_hdxyglb where hdid = ?";
		return dao.runDelete(sb, new String[]{t.getHdid()}) > 0;
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取发布审核list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午04:18:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getfbshList(QnzyhdForm t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.*,(case when e.xm is null then f.xm else e.xm end) hdfzrxm,c.mc,decode(a.shzt,'0','未审核','1','通过','2','不通过','3','退回','未审核') shztmc,c.mc fwlxmc,d.rs from xg_dekt_zyhdfbb a");
		sql.append(" left join xg_dekt_fwlxb c on a.fwlx = c.dm");
		sql.append(" left join (select count(xh) rs,hdid from xg_dekt_zyhdryb group by hdid) d on a.hdid = d.hdid");
		sql.append(" left join fdyxxb e on a.hdfzr = e.zgh");
		sql.append(" left join view_xsbfxx f on a.hdfzr = f.xh)");
		sql.append(" where 1=1");
		if(t.getShzt().equals("dsh")){
			sql.append(" and shzt = '0'");
		}else{
			sql.append(" and shzt <> '0'");
		}
		sql.append(searchTj);
		sql.append(" order by hdkssj asc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:撤销发布审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午07:09:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cxFbsh(String[] ids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" update xg_dekt_zyhdfbb set shzt = '0' where hdid in (");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.runUpdate(sb.toString(), ids);
	}
	
	/**
	 * @throws Exception  
	 * @描述:批量审核发布(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午07:48:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plshFb(String[] ids,QnzyhdForm t) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" update xg_dekt_zyhdfbb set shzt = ?,shyj = ? where hdid in (");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.runUpdate(sb.toString(), StringUtils.joinStrArr(new String[]{t.getShzt(),t.getShyj()} ,ids));
	}
}

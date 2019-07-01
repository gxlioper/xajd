/**
 * @部门:学工产品事业部
 * @日期：2013-10-28 上午10:56:12 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分上报审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-28 上午10:51:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfshDao extends SuperDAOImpl<CfshForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CfshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql=new StringBuilder();
		sql.append("select * from(select * from (");
		sql.append("select e.sydm1 sydm ,e.symc1 symc ,e.nj,e.xymc,e.xydm,e.zydm,e.bjdm,e.zymc,e.bjmc,e.zybj,e.zybjmc,a.xh,e.xm,e.xb,a.xn,a.xq,b.shsj,(select xqmc from xqdzb t1 where t1.xqdm=a.xq)xqmc,");
		sql.append(" (case when f.cfggw is not null then f.cfggw else (select cflbmc from xg_wjcf_cflbdmb t2 where t2.cflbdm=a.cflbdm) end) cflbmc,a.cflbdm,(select cfyymc from xg_wjcf_cfyydmb t2 where t2.cfyydm=a.cfyydm)cfyymc,a.cfyydm,f.cfsj,");
		sql.append("c.mc || '[' || decode(b.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它') || ']' shzt,c.gwz, ");
		sql.append("row_number()over(partition by a.cfid order by b.shsj desc) rn ,");
		sql.append("a.cfid ywid,a.kzzd1,a.splcid,b.gwid,b.guid shid ");
		sql.append(",a.kzzd4 cffwqx ");//处分发文权限（江西陶瓷）
		sql.append(" from xg_wjcf_wjcfsbb a left join xg_xtwh_shztb b on a.cfid=b.ywid left join xg_xtwh_spgw c on b.gwid=c.id left join xg_xtwh_spgwyh d on c.id=d.spgw ");
		sql.append(" left join view_xsjbxx e on a.xh=e.xh left join xg_wjcf_wjcfb f on a.cfid=f.cfid where d.spyh='"+user.getUserName()+"'  and b.shzt<>9 ");
		String shlx = t.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}

		sql.append(") a  where rn = 1) a where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}


	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("cfid");
		super.setTableName("xg_wjcf_wjcfsbb");
		super.setClass(CfshForm.class);

	}


	/** 
	 * @描述:(获取处分上报信息)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-28 下午03:20:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getCfsbxx(CfshForm model) {
		StringBuilder sql = new StringBuilder("select a.filepath,a.filepath2,a.filepath3,a.filepath4,a.cfid,xh,xn,a.xq,wjsj,cfyj,cflbdm,(select xqmc from xqdzb t where ");
		sql.append("a.xq=t.xqdm)xqmc,(case when b.cfggw is not null then b.cfggw else (select cflbmc from xg_wjcf_cflbdmb t where ");
		sql.append("t.cflbdm=a.cflbdm) end) cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,");
		sql.append("(select xm from fdyxxb t where t.zgh=a.sbb) sbbxm,wjssjg,bz,splcid,fjmc,b.cfwh,b.cfsj,b.cfdqsj,");
		sql.append("a.kzzd2,a.kzzd3,");//存在上报表中的处分发文信息
		sql.append("a.kzzd4,");//上报时的最初处分类别
		sql.append("a.kzzd5 ");//存在上报表中的处分到期时间
		sql.append("from xg_wjcf_wjcfsbb a left join (select cfid,cfwh,cfsj,cfdqsj,cfggw from xg_wjcf_wjcfb) b on a.cfid=b.cfid where a.cfid=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{model.getYwid()});
	}
	public HashMap<String, String> getCfsbxxForjg(CfshForm model) {
		String sql="select a.filepath,a.filepath2,a.filepath3,a.filepath4,b.ssfilepath,a.cfid,xh,xn,a.xq,wjsj,cfyj,cflbdm,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,"
				//+ "(case when a.cfggw is not null then a.cfggw else (select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm) end) "
				+ "(select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm) "
				+ "cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,wjssjg,a.cfwh,a.cfsj,a.cfdqsj,bz,b.splcid,a.fjmc,"
				+ "a.sswh,a.sssj,a.cfggw,(case when a.ssjg='wcycf' then '维持原处分' when a.ssjg= 'ggcf' then '更改处分' when a.ssjg='cxcf' "
				+ "then '撤销处分' else a.ssjg end) ssjg,a.jcwh,a.jcsj from xg_wjcf_wjcfb a left join xg_wjcf_wjcfssb b on b.cfid=a.cfid "
				+ "where a.cfid=?";
		
		return dao.getMapNotOut(sql, new String[]{model.getYwid()});
	}

	/** 
	 * @描述:(获取审批流程岗位)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午07:05:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * ArrayList<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public ArrayList<HashMap<String, String>> getSplcgw(CfshForm model) {
		String sql="select * from xg_xtwh_spbz where splc=? order by xh ";
		return (ArrayList<HashMap<String, String>>) dao.getListNotOut(sql, new String[]{model.getSplcid()});
	}


	/** 
	 * @描述:TODO(进入结果库)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午05:11:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertWjjgk(CfshForm form) throws Exception{
		// TODO 自动生成方法存根
		StringBuilder sql=new StringBuilder();
		sql.append("insert into xg_wjcf_wjcfb(filepath,filepath2,filepath3,filepath4,cfid,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,cfsj,cfdqsj,cfwh,wjsj,cfyj,sbb,sbsj,wjssjg,bz,fjmc,fj,cflsh,sjly,cfsbid) ");
		sql.append("select filepath,filepath2,filepath3,filepath4,cfid,xh,xn,xq,(select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm)cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,cflbdm,cfyydm,? cfsj,? cfdqsj,? cfwh,wjsj,cfyj,sbb,sbsj,wjssjg,bz,fjmc,fj,? cflsh, ? sjly,? cfsbid from xg_wjcf_wjcfsbb a where cfid=?");
		
		return dao.runUpdate(sql.toString(), new String[]{form.getCfsj(),form.getCfdqsj(),form.getCfwh(),form.getCflsh(),form.getSjly(),form.getYwid(),form.getYwid()});
	}
	/**
	 * 获取流水号
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsh(HashMap<String,String> map){
		StringBuffer sql= new StringBuffer();
		sql.append("select cflsh from xg_wjcf_wjcfb")
		.append(" where xn=? and xq=? and cflsh is not null order by cflsh desc");
		return dao.getListNotOut(sql.toString(), new String[]{map.get("xn"),map.get("xq")});
	}
	public HashMap<String,String> getSbxx(CfshForm model){
		String sql="select * from xg_wjcf_wjcfsbb where cfid=?";
		return dao.getMapNotOut(sql, new String[]{model.getYwid()});
		
	}

	/** 
	 * @描述:(是否有可回滚)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 上午10:24:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean sfkcx(String ywid) {
		String sql="select * from (select cfid from xg_wjcf_wjcfssb union all select cfid from xg_wjcf_wjcfjcshb) where cfid=?";
		List<HashMap<String, String>> rs=dao.getListNotOut(sql, new String[]{ywid});
		return rs.size()==0;
	}


	/** 
	 * @描述:(违纪结果库删除)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-29 上午10:55:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public int deleteJgk(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ywid};
		sql.append("delete from ");
		sql.append(" xg_wjcf_wjcfb ");
		sql.append(" where ");
		sql.append(" cfid = ? ");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		return dao.runDelete(sql.toString(), new String[]{ywid});
	}
	/**
	 * 
	 * @描述:判断是否在结果库中存在
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-9-18 下午04:36:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getJgk(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select xh from ");
		sql.append(" xg_wjcf_wjcfb ");
		sql.append(" where ");
		sql.append(" cfid = ? ");
		return dao.getOneRs(sql.toString(), new String[]{ywid},"xh");
	}


	/**
	 * 查询附件信息
	 * @param
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		return dao.getOneLong(sql, inputList, column);
	}


	/** 
	 * @描述:获取审核岗位的级别
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月27日 下午7:42:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @param gwid
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getShgwjb(String splcid, String gwid) {
		String sql = "SELECT XH FROM xg_xtwh_spbz WHERE SPLC = ? AND SPGW = ?";
		String shgwjb = dao.getOneRs(sql, new String[]{splcid,gwid}, "xh");
		return shgwjb;
	}


	/** 
	 * @描述:根据处分id取到最初发文权限，即kzzd4
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月15日 下午3:40:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getZcFwqxByCfid(String ywid) {
		String sql = "SELECT kzzd4 FROM XG_WJCF_WJCFSBB WHERE cfid = ?";
		String zcffqx = dao.getOneRs(sql, new String[]{ywid}, "kzzd4");
		return zcffqx;
	}

	public HashMap<String,String> getxsnl(String xh) {
		String sql = " select  floor(months_between(SYSDATE, to_date(a.csrq,'yyyy-mm-dd'))/ 12) as  xsnl from view_xsxxb a where a.xh =?";
		return dao.getMapNotOut(sql, new String[]{xh});
	}

	public HashMap<String,String> getbjrs(String bjdm) {
		String sql = " select count(a.xh) as bjrs from view_xsxxb a where a.bjdm =?";
		return dao.getMapNotOut(sql, new String[]{bjdm});
	}
}

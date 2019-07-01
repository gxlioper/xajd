/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午05:03:37 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 下午05:03:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsbDao extends SuperDAOImpl<XmsbForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(XmsbForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XmsbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,nvl(t6.xm,t7.xm) sbrxm,decode(t1.csms,'1','个人','2','团体') csmsmc, ");
		sql.append("case when '"+user.getUserName()+"' in (select yhm from xg_sztz_glyb) then 'true' when sbr ='"+user.getUserName()+"' then 'true' else 'false' end sfkxg,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc ");
		sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t6 on t1.sbr=t6.yhm left join xsxxb t7 on t1.sbr=t7.xh");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取项目奖项
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 下午02:03:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmjxList(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_sztz_xm_jx where xmdm=? order by to_number(xssx) asc");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	public List<HashMap<String, String>> getCyxyListForView(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.bmmc xymc from XG_SZTZ_XMCYXYB t1 left join zxbz_xxbmdm t2 on t1.xydm=t2.bmdm where t1.xmdm=? ");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	/**
	 * 
	 * @描述:获取项目信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午09:23:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXmxx(String xmdm) {
		StringBuffer sql = new StringBuffer();
		if("13627".equals(Base.xxdm)){
			sql.append("select t1.*,nvl(t8.xm,t10.xm) sbrxm,case when t1.sfsljx='1' then '是' else '否' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,a.mc bkgsmc ");
			sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
			sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm ");
			sql.append(" left join yhb t8 on t1.sbr=t8.yhm left join xsxxb t10 on t1.sbr=t10.xh left join XG_SZTZ_BKGS a on a.dm=t1.bkgs ");
			sql.append(" where t1.xmdm=?");
		}else{
			sql.append("select t1.*,nvl(t8.xm,t10.xm) sbrxm,case when t1.sfsljx='1' then '是' else '否' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc ");
			sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
			sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm ");
			sql.append(" left join yhb t8 on t1.sbr=t8.yhm left join xsxxb t10 on t1.sbr=t10.xh ");
			sql.append(" where t1.xmdm=?");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { xmdm });
	}
	/**
	 * 
	 * @描述:项目奖项信息批量保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 下午03:00:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean jxxxPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_sztz_xm_jx(jgid,xmdm,fjxf,jxmc,xssx) values(?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	public boolean cyxyPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into XG_SZTZ_XMCYXYB(xmdm,xydm) values(?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean delCyxy(String xmdm) throws Exception {
		String sql = "delete from XG_SZTZ_XMCYXYB where xmdm= ?";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
	/**
	 * 
	 * @描述:删除奖项信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 下午03:12:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXmjx(String xmdm) throws Exception {
		String sql = "delete from xg_sztz_xm_jx where xmdm= ?";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
	
	
	/**
	 * 
	 * @描述:获取审批流
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午11:03:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_sztz_xmsb_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
//	public XmsbForm getModel(XmsbForm model) throws Exception{
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select * from xg_sztz_xmsq t1 left join view_xg_qgzx_Sbbmdmdmb t2 on t1.Sbbmdm=t2.bmdm left join");
//		sql.append(" xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm where t1.xmdm=?");
//		return super.getModel(sql.toString(),new String[]{model.getXmdm()});
//	}
	
	
	/**
	 * 
	 * @描述:判断当前岗位是否有填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午11:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveSbJl(XmsbForm model,String czlx) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_sztz_xmsq where xn=? and xq=? and xmmc=?");
		if("update".equals(czlx)){
			sql.append(" and xmdm<>'"+model.getXmdm()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getXmmc()}, "num");
		return Integer.parseInt(num)>0;
	}
	/**
	 * 
	 * @描述:项目科目列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 上午10:24:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmkmList()throws Exception{
		String sql ="select * from xg_sztz_sskm";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:项目级别列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 上午10:24:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmjbList()throws Exception{
		String sql ="select * from xg_sztz_xmjb";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:获取部门列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午03:16:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select bmdm xydm,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append("from zxbz_xxbmdm ");
		sql.append(") order by xypy ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}
	/**
	 * 
	 * @描述: 获取部门名称
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午08:52:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String getBmmc(String bmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm,bmmc from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sql.toString(), new String[] { bmdm }, "bmmc");
	}
	/**
	 * 
	 * @描述:批量删除项目奖项
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-13 下午05:27:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delPlXmjx(List<String[]> params) throws Exception {
		String sql = "delete from xg_sztz_xm_jx where xmdm = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XmsbForm.class);
		super.setKey("xmdm");
		super.setTableName("xg_sztz_xmsq");

	}
	
	public List<HashMap<String, String>> getXmForJcfrd(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select jgid from xg_sztz_xs_sqjg where xmdm = ? and (ylzd1 is not null or ylzd3 is not null) union select jgid from xg_sztz_jcftz_jg where xmdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}
	
	/**
	 * @描述：板块归属列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年2月4日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getBkgsList()throws Exception{
		String sql ="select * from  XG_SZTZ_BKGS ";
		return dao.getListNotOut(sql, new String[]{});
	}
	
}

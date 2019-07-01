/**
 * @部门:学工产品事业部
 * @日期：2016-7-21 下午03:43:13 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-21 下午03:43:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjsqDao extends SuperDAOImpl<HjsqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select t1.*, t2.xm, t2.xb, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, ");
        sql.append(" decode(t1.shzt, '0', '未提交', '1', '通过', '2', '不通过', '3', '退回', '5', '审核中', t1.shzt) shztmc, ");
        sql.append(" (case t1.jsfs when '1' then '个人' when '2' then '团体' else t1.jsfs end) jsfsmc ");
        sql.append(" from xg_hjxxgl_sqb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_hjxxgl_jxlb t3 on t1.jxlbdm = t3.jxlbdm ");
        sql.append(" left join xg_hjxxgl_jxdj t4 on t1.jxdjdm = t4.jxdjdm left join xg_hjxxgl_jxmc t5 on t1.jxmcdm = t5.jxmcdm) t ");
        sql.append(" where 1 = 1 ");
        sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(HjsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_hjxxgl_sqb");
	}
	
	/**
	 * 
	 * @描述: 动态取值(奖项名次)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午01:57:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxmcList(String jxlbdm, String jsfs, String jxdjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.jxmcdm,t.jxmcmc from xg_hjxxgl_jxmc t ");
		
		sql.append("  where t.jxlbdm = '"+jxlbdm+"' and t.jsfs = '"+jsfs+"' and t.jxdjdm = '"+jxdjdm+"' "); 
		
		sql.append(" order by t.jxmcdm ");
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"jxmcdm","jxmcmc"});
	}
	
	/**
	 * 
	 * @描述: 动态取值(金额)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:20:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @param jxmcdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJe(String jxlbdm, String jsfs, String jxdjdm, String jxmcdm) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t.je from xg_hjxxgl_jxmc t ");
		
		sql.append("  where t.jxlbdm = '"+jxlbdm+"' and t.jsfs = '"+jsfs+"' and t.jxdjdm = '"+jxdjdm+"' and t.jxmcdm = '"+jxmcdm+"' "); 
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"je"});
	}
	
	/**
	 * 
	 * @描述: 获取splc
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:39:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_PJPY_XWHJCSSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @描述: 重复验证（增加）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 上午11:24:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String isExistByHjsqSave(HjsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_hjxxgl_sqb where xh=? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? and shzt <> '2' ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(), model.getJxlbdm(), model.getJsfs(), model.getJxdjdm(), model.getJxmcdm(), model.getHdsj()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @描述: 重复验证（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 上午11:25:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String isExistByHjsqUpdate(HjsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_hjxxgl_sqb where xh=? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? and shzt <> '2' and sqid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(), model.getJxlbdm(), model.getJsfs(), model.getJxdjdm(), model.getJxmcdm(), model.getHdsj(), model.getSqid()}, "num");
		return num;
		
	}
	
	/**
	 * 重写
	 */
	public HjsqForm getModel(String sqid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select t1.*, t2.xm, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, ");
        sql.append(" decode(t1.shzt, '0', '未提交', '1', '通过', '2', '不通过', '3', '退回', '5', '审核中', t1.shzt) shztmc, ");
        sql.append(" (case t1.jsfs when '1' then '个人' when '2' then '团体' else t1.jsfs end) jsfsmc ");
        sql.append(" from xg_hjxxgl_sqb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_hjxxgl_jxlb t3 on t1.jxlbdm = t3.jxlbdm ");
        sql.append(" left join xg_hjxxgl_jxdj t4 on t1.jxdjdm = t4.jxdjdm left join xg_hjxxgl_jxmc t5 on t1.jxmcdm = t5.jxmcdm) t ");
        sql.append(" where 1 = 1 and t.sqid = ? ");
        return getModel(sql.toString(), new String[] { sqid });
	}
}

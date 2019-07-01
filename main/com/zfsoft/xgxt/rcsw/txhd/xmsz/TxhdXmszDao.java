/**
 * @部门:学工产品事业部
 * @日期：2014-6-23 下午03:20:21 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——团学活动
 * @类功能描述: 团学活动Dao
 * @作者： cq [工号:785]
 * @时间： 2014-6-23 下午03:20:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmszDao extends SuperDAOImpl<TxhdXmszForm> {

	/**
	 * 项目查询list
	 */
	public List<HashMap<String, String>> getPageList(TxhdXmszForm t)
			throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc, ");
		sql.append("hdkssj||' 至 '||hdjssj hdsj from xg_rcsw_txhd_xmwh a where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmszForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc, ");
		sql.append("hdkssj||' 至 '||hdjssj hdsj from xg_rcsw_txhd_xmwh a where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	
	protected void setTableInfo() {

		super.setClass(TxhdXmszForm.class);
		super.setTableName("xg_rcsw_txhd_xmwh");
		super.setKey("xmdm");

	}

	/**
	 * 
	 * @描述:根据项目代码查询信息
	 * @作者：cq [工号：785]
	 * @日期：2014-6-23 下午04:27:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getInfoById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.*,b.xh,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc from xg_rcsw_txhd_xmwh a ");
		sb.append(" left join xg_xtwh_spbz b on a.shlc=b.splc and a.rskzjb=b.spgw where xmdm = ?");
		String[] inputValue = { xmdm };
		return dao.getMapNotOut(sb.toString(), inputValue);

	}

	/**
	 * 
	 * @描述:根据项目名称查询信息
	 * @作者：cq [工号：785]
	 * @日期：2014-6-23 下午04:27:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDateByName(String xmmc) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb
				.append(" select a.*,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc from xg_rcsw_txhd_xmwh a where xmmc = ? ");
		String[] inputValue = { xmmc };
		return dao.getMapNotOut(sb.toString(), inputValue);

	}
	
	
	/**
	 * 
	 * @描述:增加操作唯一性判断
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午01:42:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(TxhdXmszForm form) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_txhd_xmwh where xn=? and xq=? and xmmc=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { form.getXn(),form.getXq(),form.getXmmc() }, "num");
		return num;
	}

	
	
	/**
	 * @throws Exception  
	 * @描述:判断是否已申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午07:38:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isRalate(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		String sql = "select count(*)  from xg_rcsw_txhd_xmsq  ";
		sql += " where xmdm in("+values+")";
		int result = dao.getOneRsint(sql);
		return result > 0;
	}

	/** 
	 * @描述:根据项目代码获得项目名称
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午07:56:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getNameById(String xmdm) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmmc ");
		sb.append(" from  xg_rcsw_txhd_xmwh ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		String[] outputValue = { "xmmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (oneRs != null && oneRs.length > 0) {
			xmmc = oneRs[0];
		}
		return xmmc;
	}

}

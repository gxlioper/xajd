/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-13 上午10:29:21 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.jdsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 不可兼得设置
 * @作者： MengWei[工号:1186]
 * @时间： 2018-7-13 上午10:24:14 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdszDao extends SuperDAOImpl<JdszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_jdszb");
		super.setKey("xmdm");
		super.setClass(JdszForm.class);
	}
	
	/**
	 * @描述: 根据项目代码获得项目名称
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午02:25:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXmmcByXmdm(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xmmc from xg_zjdx_pjpy_pjxmb where xmdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xmmc");
	}
	
	/**
	 * @描述: 根据xmdm查询设置的记录
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午03:13:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm, String xn) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,c.xmdm fzbjdxmdm from xg_zjdx_pjpy_jdszb a ");
		sql.append("left join xg_pjpy_new_pjxmb b on a.bjdxmdm = b.xmdm ");
		sql.append("left join (select * from xg_zjdx_pjpy_pjxmb where xn = ?) c on b.xmmc = c.xmmc ");
		sql.append("where a.xmdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}
	
	/**
	 * @描述: 根据xmdm查询此项目是否被申请
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午03:19:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) count from xg_zjdx_pjpy_xmsq where xmdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}
	
	/**
	 * @描述: 获取勾选项目以外的所有项目(相同流程内的)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午05:37:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm,String xn) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm,xmmc from xg_zjdx_pjpy_pjxmb where ");
		
		if(StringUtils.isNotNull(xmdm)){
			sql.append("xmdm != ? and ");
		}
		sql.append("xn = '");
		sql.append(xn);
		sql.append("' ");
		
		if(StringUtils.isNotNull(xmdm)){
			sql.append("and shlc = (select shlc from xg_zjdx_pjpy_pjxmb where xmdm = ?) ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}
	
	/**
	 * @描述: 兼得设置保存
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-14 上午11:39:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xmdms
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runJdsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;

		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_zjdx_pjpy_jdszb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		sql = "delete from xg_zjdx_pjpy_jdszb where bjdxmdm='"+xmdm+"'";
		sqlList.add(sql);		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+arr[i]+"','"+xmdm+"')";
					sqlList.add(sql);
				}
			}
		}

		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
}

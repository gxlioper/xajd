/**
 * @部门:学工产品事业部
 * @日期：2013-8-20 上午09:29:57 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 特殊学生代码维护
 * @作者：CQ [工号：785]
 * @时间： 2013-8-20 上午09:29:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsDmDao extends SuperDAOImpl<TsxsDmForm> {

	/**
	 * 普通查询
	 */
	public List<HashMap<String, String>> getPageList(TsxsDmForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" select lxdm,lxmc,lxsm,case lxsx when '1' then '条件' when '2' then '范围' end lxsx from xg_pjpy_new_tslxdmb where 1=1  ");
		
		if(!StringUtil.isNull(t.getLxmc())){
			params.add(t.getLxmc());
			sql.append(" and lxmc like '%'||?||'%'");
		}
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * 获取 最大档次代码，用于生成
	 * @return
	 * @throws SQLException
	 */
	public int getMaxTsxsDm() throws SQLException{
		
		String sql = " select nvl(max(to_number(lxdm)),1) lxdm from xg_pjpy_new_tslxdmb ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:增加操作唯一性验证
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 上午11:36:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(TsxsDmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_tslxdmb where lxmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getLxmc()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @描述:修改唯一性操作验证
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 上午11:38:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(TsxsDmForm model) {
		String sql=" select count(1) num from xg_pjpy_new_tslxdmb where lxmc = ? and lxdm<>?";
		String num=dao.getOneRs(sql.toString(), new String[]{model.getLxmc(),model.getLxdm()}, "num");
		return num;
		
	}
	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsxsDmForm t, User user)
			throws Exception {
		//  自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_tslxdmb");
		super.setKey("lxdm");
	}
	
	
	/**
	 * 
	 * @描述:特殊学生类型list
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 上午11:44:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxsDmList() {
		String sql = " select lxdm,lxmc from xg_pjpy_new_tslxdmb order by lxdm ";
		return dao.getList(sql, new String[]{},new String[]{"lxdm","lxmc"});
	}
	
	/**
	 * 
	 * @描述:检验特殊学生表当中是否已有使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 上午11:53:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkDcForTsxsb( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.lxmc from xg_pjpy_new_tsxsb a, xg_pjpy_new_tslxdmb b where a.lxdm=to_char(b.lxdm) and a.lxdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "lxmc");
		return dcmc;
	}

}

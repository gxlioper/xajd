/**
 * @部门:学工产品事业部
 * @日期：2016-4-6 上午11:23:36 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.UtilForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-6 上午11:23:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CommUtil extends SuperDAOImpl<CommForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CommForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CommForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	public  boolean isNotExists(CommForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//进行申请表和结果表的重复验证,qb(全部)
		if(type.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_xs");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_jg");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?)");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("sq")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_xs");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_jg");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jgedit")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_jg");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			sql.append(" and xsid != ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getId());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	
    public boolean isNotExistBj(CommForm utilform){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> parameter = new ArrayList<String>();
    	boolean flag = true;
    	sql.append(" select count(1) flag from xg_xsxx_cxpy_pysb_bj where xn = ? and xq = ? and bjdm = ?");
    	parameter.add(utilform.getXn());
    	parameter.add(utilform.getXq());
    	parameter.add(utilform.getBjdm());
    	String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
//根据学号验证档案信息表中该学生数据是否存在
	public boolean isNotExistsDa(CommForm utilform) {
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> parameter = new ArrayList<String>();
    	boolean flag = true;
    	sql.append(" select count(1) flag from xg_xsxx_cqsxdaxxb where xh = ? ");
    	parameter.add(utilform.getXh());
    	String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	//根据学号验证档案信息表中该学生数据是否存在（重庆信息）
	public boolean isNotExistsCqxx(CommForm utilform) {
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> parameter = new ArrayList<String>();
    	boolean flag = true;
    	sql.append(" select count(1) flag from xg_xsxx_cqxxdaxxb where xh = ? ");
    	parameter.add(utilform.getXh());
    	String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
}

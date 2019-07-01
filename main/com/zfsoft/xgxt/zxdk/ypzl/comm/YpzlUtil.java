/**
 * @部门:学工产品事业部
 * @日期：2016-6-16 上午09:17:12 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.comm;

import java.util.ArrayList;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-6-16 上午09:17:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzlUtil extends SuperDAOImpl {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
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
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-16 上午09:18:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public  String getAutoGnmkmc(String path){
		StringBuilder sql = new StringBuilder();
		sql.append(" select gnmkmc from gnmkdmb where dyym = ? ");
		return dao.getOneRs(sql.toString(), new String[]{path}, "gnmkmc");
	}
	
	/**
	 * 
	 * @描述:华师大个性化判断，是否在同一天重复申请记录，是返回true,否返回false
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-17 上午10:34:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsExistsSameDate(String sqrq,String xh,String type){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		//申请和结果全部验证
		if("qb".equals(type)){
			sql.append(" select sum(num) num from");
			sql.append(" (select count(1) num");
			sql.append(" from xg_zdgxh_ypzl_sqb");
			sql.append("  where xh = ?");
			sql.append("  and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') = ?");
			sql.append(" union all");
			sql.append(" select count(1) num");
			sql.append(" from xg_zdgxh_ypzl_jgb");
			sql.append(" where xh = ?");
			sql.append(" and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') = ?) t");
			paralist.add(xh);
			paralist.add(sqrq);
			paralist.add(xh);
			paralist.add(sqrq);
			
		}else{//申请验证
			sql.append(" select count(1) num");
			sql.append(" from xg_zdgxh_ypzl_jgb");
			sql.append(" where xh = ?");
			sql.append(" and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') = ?");
			paralist.add(xh);
			paralist.add(sqrq);
		}
		return !("0").equals(dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "num")) ? true :false;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:华师大审核通过插入结果前删除结果表已存在的冗余数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-17 下午03:13:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delYpjgHsd(String sqrq,String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zdgxh_ypzl_jgb ");
		sql.append(" where xh = ?");
		sql.append(" and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')=?");
		sql.append(" ");
		return dao.runUpdate(sql.toString(), new String[]{xh,sqrq});
	}
	
}

/**
 * @部门:学工产品事业部
 * @日期：2013-11-21 上午09:21:44 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.bjdldm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优——代码维护
 * @类功能描述: 班级代码维护 
 * @作者：CQ [工号：785]
 * @时间： 2013-11-21 上午09:21:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjdldmDao extends SuperDAOImpl<BjdldmForm> {

	
	/**
	 * 普通查询
	 */
	public List<HashMap<String, String>> getPageList(BjdldmForm t)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" select lbdm, lbmc from xg_xtwh_bjdlb where 1=1 ");
		
		if(!StringUtil.isNull(t.getLbmc())){
			params.add(t.getLbmc());
			sql.append(" and lbmc like '%'||?||'%'");
		}
		
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	public List<HashMap<String, String>> getPageList(BjdldmForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	protected void setTableInfo() {
		super.setTableName("xg_xtwh_bjdlb");
		super.setKey("lbdm");

	}
	
	/**
	 * 
	 * @描述:操作唯一性验证
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 上午11:04:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(BjdldmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_xtwh_bjdlb where lbmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getLbmc()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述:判断结果是否已被使用
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 下午02:20:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkLbForBjdl( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.lbmc from xg_xtwh_bjlbb a, xg_xtwh_bjdlb b where a.lbdm=b.lbdm and a.lbdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "lbmc");
		return dcmc;
	}

}

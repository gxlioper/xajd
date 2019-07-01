/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:12:16 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-院设奖学金（浙江大学个性化！）
 * @类功能描述: TODO(资金来源代码) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:12:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhDao  extends SuperDAOImpl<DmwhForm>{
	
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.zjlydm, a.zjlymc from xg_zxdk_new_ysjxjdmb a where 1=1 ");
		if (!StringUtil.isNull(t.getZjlymc())){
			params.add(t.getZjlymc());
			sql.append(" and zjlymc like '%'||?||'%'");
		}
		sql.append(" order by to_number(zjlydm) ");
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}
	
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	protected void setTableInfo() {
		super.setTableName("xg_zxdk_new_ysjxjdmb");
		super.setKey("zjlydm");
	}
	
	/**
	 * @描述:TODO(查询资金来源名称是否已经存在)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午02:45:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String fftjCkeckExist(DmwhForm t){
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_zxdk_new_ysjxjdmb where zjlymc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{t.getZjlymc()}, "num");
		return num;
	}
	/**
	 * @描述:TODO(获取最大资金来源代码)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午02:48:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int maxZjlydm() throws SQLException{
		String sql = " select nvl(max(to_number(zjlydm)),1) zjlydm from xg_zxdk_new_ysjxjdmb ";
		return dao.getOneRsint(sql);
	}
	
	/**
	 * @描述:TODO(判断资金来源名称在院设奖学金中是否使用)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午03:55:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] tjcheckZjlymcForYsjxjjg(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.zjlymc from xg_zxdk_new_ysjxj a,xg_zxdk_new_ysjxjdmb b where a.zjly = b.zjlydm and a.zjly in (" +value +") ");
		String[] zjlymc = dao.getRs(sql.toString(), new String[]{}, "zjlymc");
		return zjlymc;
	}

}

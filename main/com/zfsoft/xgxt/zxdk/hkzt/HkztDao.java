/**
 * @部门:学工产品事业部
 * @日期：2014-11-5 上午09:40:15 
 */  
package com.zfsoft.xgxt.zxdk.hkzt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 还款状态
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-5 上午09:40:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HkztDao extends SuperDAOImpl<HkztModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_hkztb");
		super.setClass(HkztModel.class);
		super.setKey("dm");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HkztModel t)
			throws Exception {
		
		StringBuilder sql = new StringBuilder("select * from xg_zxdk_hkztb");
		List<String> params = new ArrayList<String>();
		
		if (!StringUtil.isNull(t.getMc())){
			sql.append(" where mc like '%'||?||'%'");
			params.add(t.getMc());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HkztModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_zxdk_hkztb t1 ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("dm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") and not exists (select 1 from xg_xtwh_shztb t2 where t1.dm=t2.zd2)");
		
		return dao.runDelete(sql.toString(), values);
	}

	
}

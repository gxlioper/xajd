/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 下午01:54:19 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2014-9-11 下午01:54:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JJalDao extends SuperDAOImpl<JJalForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JJalForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if(StringUtils.equals("1", t.getType())){
			sql.append("select t1.*,decode(t1.sffb , '1' , '已发布' , '0' , '未发布' , '') sffbmc from XSGGFW_JJFW_ANLI t1 where t1.sffb = '1' order by t1.jlrq desc ");
		}else if(StringUtils.equals("0", t.getType())){
			sql.append("select t1.*,decode(t1.sffb , '1' , '已发布' , '0' , '未发布' , '') sffbmc from XSGGFW_JJFW_ANLI t1 where t1.sffb = '0' order by t1.jlrq desc ");
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JJalForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JJalForm.class);
		super.setKey("sid");
		super.setTableName("XSGGFW_JJFW_ANLI");
	}

}

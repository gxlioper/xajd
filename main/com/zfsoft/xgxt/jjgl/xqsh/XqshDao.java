/**
 * @部门:学工产品事业部
 * @日期：2014-8-26 下午06:05:47 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-26 下午06:05:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqshDao extends SuperDAOImpl<XqshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XqshForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*, decode(t1.shzt,'0','未审核','1','通过','2','不通过') shztmc ,t2.xm znxm,t2.xb znxb,t2.nj znnj,");
		sql.append("t3.jjnjmc,t4.jjxkmc from XSGGFW_JJFW_JZJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_JZZNXXB t2 on t2.znid = t1.znid ");
		sql.append("left join XSGGFW_JJFW_JJNJDMB t3 on t1.jjnjdm = t3.jjnjdm ");
		sql.append("left join XSGGFW_JJFW_JJXKDMB   t4 on t1.jjxkdm = t4.jjxkdm ");
		
		if(StringUtils.equals("dsh", t.getType())){
			sql.append("where t1.shzt = '0' ");
		}else if(StringUtils.equals("ysh", t.getType())){
			sql.append("where t1.shzt <> '0' ");
		}
			
		if (!StringUtil.isNull(t.getSqr())){
			sql.append(" and t1.sqr like '%'||?||'%' ");
			params.add(t.getSqr());
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XqshForm t, User user)
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

}

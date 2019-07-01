/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:11:40 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:11:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszDao extends SuperDAOImpl<JcszForm>{

	
	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	public JcszForm getModel() throws Exception{
		String sql = "select * from XG_RCSW_ZWZXKQ_JCSZ where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:获取申请审核开关状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午02:19:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
		sql.append("case when t.shkg = 1 and sysdate between to_date(nvl(t.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.shjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end shkg ");
		sql.append(" from XG_RCSW_ZWZXKQ_JCSZ t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg","shkg"});
	}

	
	@Override
	protected void setTableInfo() {
		super.setClass(JcszForm.class);
		super.setKey("id");
		super.setTableName("XG_RCSW_ZWZXKQ_JCSZ");
		
	}

}

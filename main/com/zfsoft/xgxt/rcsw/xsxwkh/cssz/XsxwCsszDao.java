/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:28:56 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生行为考核参数设置
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午02:36:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxwCsszDao extends SuperDAOImpl<XsxwCsszForm> {


	@Override
	public List<HashMap<String, String>> getPageList(XsxwCsszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XsxwCsszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	public XsxwCsszForm getModel() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,case when sysdate between to_date(nvl(a.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end kgzt from xg_xsxwkh_csszb a");
		return super.getModel(sql.toString(), new String[]{});
	}

	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from xg_xsxwkh_csszb";
		return dao.runUpdate(sql, new String[]{});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XsxwCsszForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxwkh_csszb");
	}
}

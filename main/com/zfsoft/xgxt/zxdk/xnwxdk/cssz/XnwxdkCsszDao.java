/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:33:45 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:33:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkCsszDao extends SuperDAOImpl<XnwxdkCsszModel> {

	public List<HashMap<String, String>> getPageList(XnwxdkCsszModel t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkCsszModel t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	public XnwxdkCsszModel getModel() throws Exception{
		String sql = "select * from xg_zdgxh_wxjk_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:获取申请开关状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午02:52:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String getSqKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_zdgxh_wxjk_cssz t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},"sqkg");
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XnwxdkCsszModel.class);
		super.setKey("id");
		super.setTableName("xg_zdgxh_wxjk_cssz");
		
		
	}

}

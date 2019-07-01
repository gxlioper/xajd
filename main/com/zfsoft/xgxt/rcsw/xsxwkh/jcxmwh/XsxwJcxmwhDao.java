/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:14:33 
 */
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 奖惩项目维护
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午05:02:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XsxwJcxmwhDao extends SuperDAOImpl<XsxwJcxmwhForm> {

	/**
	 * 
	 * @描述:处分项目列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-2 下午05:02:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCfxmPageList(XsxwJcxmwhForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_XSXWKH_JCFDMB where lx='0'");
		if (!StringUtil.isNull(model.getMc())) {
			params.add(model.getMc());
			sql.append(" and mc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));

	}

	@Override
	public List<HashMap<String, String>> getPageList(XsxwJcxmwhForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_XSXWKH_JCFDMB where lx='1'");
		if (!StringUtil.isNull(model.getMc())) {
			params.add(model.getMc());
			sql.append(" and mc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));
	}


	@Override
	public List<HashMap<String, String>> getPageList(XsxwJcxmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_XSXWKH_JCFDMB");
		super.setKey("dm");
	}

}

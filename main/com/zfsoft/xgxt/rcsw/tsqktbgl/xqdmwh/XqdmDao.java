/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 下午02:19:29 
 */
package com.zfsoft.xgxt.rcsw.tsqktbgl.xqdmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-15 下午02:19:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XqdmDao extends SuperDAOImpl<XqdmForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XqdmForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				" select * from xg_bjzyy_xqyb_xqfl where 1=1 ");

		if (!StringUtil.isNull(t.getXqmc())) {
			params.add(t.getXqmc());
			sql.append(" and xqmc like '%'||?||'%'");
		}

		sql.append(" order by to_number(xqdm) ");

		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XqdmForm t, User user)
			throws Exception {
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XqdmForm.class);
		super.setKey("xqdm");
		super.setTableName("xg_bjzyy_xqyb_xqfl");
	}

	public boolean checkExistForAdd(XqdmForm form){
		String sql = "select count(1) num from xg_bjzyy_xqyb_xqfl where xqmc = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXqmc()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public boolean checkExistForUpdate(XqdmForm form,String oldxqdm){
		if(form.getXqdm().equalsIgnoreCase(oldxqdm)){
			String sql = "select count(1) num from xg_bjzyy_xqyb_xqfl where xqmc = ? and xqdm <> ?";
			String xqmcNum = dao.getOneRs(sql, new String[]{form.getXqmc(),form.getXqdm()}, "num");
			return Integer.parseInt(xqmcNum)>0;
		}else{
			String sqlll = "select count(1) num from xg_bjzyy_xqyb_xqfl where xqdm = ?";
			String num = dao.getOneRs(sqlll, new String[]{form.getXqdm()}, "num");
			if(Integer.parseInt(num)>0){
				return true;
			}else{
				String sqllll = "select count(1) num from xg_bjzyy_xqyb_xqfl where xqmc = ? and xqdm <> ?";
				String numm = dao.getOneRs(sqllll, new String[]{form.getXqmc(),oldxqdm}, "num");
				if(Integer.parseInt(numm)>0){
					return true;
				}else{
					return false;
				}
			}
		}
		
	}

	/**
	 * @描述:判断是否在申请和结果中存在
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-15 下午02:52:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkExistForsqjg(XqdmForm form) {
		String sql = ("select count(*) num from xg_bjzyy_tsqktb_sq where xqdm1 = ? or xqdm2 = ?");
		String num = dao.getOneRs(sql, new String[] { form.getXqdm(),
				form.getXqdm() }, "num");
		if (Integer.valueOf(num) > 0) {
			return true;
		}
		String sqll = ("select count(*) num from xg_bjzyy_tsqktb_jg where xqdm1 = ? or xqdm2 = ?");
		String numm = dao.getOneRs(sqll, new String[] { form.getXqdm(),
				form.getXqdm() }, "num");
		return Integer.valueOf(numm) > 0;

	}

	public int getMaxXqdm() throws SQLException {

		String sql = "select nvl(max(xqdm),1) xqdm from xg_bjzyy_xqyb_xqfl";

		return dao.getOneRsint(sql) + 1;
	}
	
	public boolean update(XqdmForm form,String oldXqdm) throws Exception{
		String sql = "update xg_bjzyy_xqyb_xqfl set xqdm = ?,xqmc = ? where xqdm = ?";
		return dao.runUpdate(sql, new String[]{form.getXqdm(),form.getXqmc(),oldXqdm});
	}

}

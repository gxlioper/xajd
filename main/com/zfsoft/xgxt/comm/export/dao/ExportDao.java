/**
 * @部门:学工产品事业部
 * @日期：2013-5-20 下午06:20:17 
 */
package com.zfsoft.xgxt.comm.export.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:
 * @类功能描述:
 * @时间： 2013-5-20 下午06:20:17
 * @版本： V1.0
 * @修改记录:
 */

public class ExportDao extends SuperDAOImpl<ExportModel> {

	/**
	 * 
	 * @描述:删除原记录
	 * @作者：ligl
	 * @日期：2013-5-22 下午03:03:45
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception int 返回类型
	 * @throws
	 */
	public int deleteConfig(ExportModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_xtwh_dcpzb  ");
		sb.append(" where dcclbh=? ");
		sb.append(" and zgh=? ");
		String[] input = { model.getDcclbh(), model.getZgh() };
		dao.runUpdate(sb.toString(), input);
		return 0;
	}

	/**
	 * 
	 * @描述:查询配置记录
	 * @作者：ligl
	 * @日期：2013-5-22 下午03:03:45
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception int 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getExportConfig(ExportModel model) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_xtwh_dcpzb  ");
		sb.append(" where dcclbh=? ");
		sb.append(" and zgh=? ");
		sb.append(" order by to_number(xssx) ");
		String[] input = { model.getDcclbh(), model.getZgh() };
		List<HashMap<String, String>> result = dao.getListNotOut(sb.toString(),
				input);
		return result;
	}

	/**
	 * 
	 * @描述:插入配置记录
	 * @作者：ligl
	 * @日期：2013-5-22 下午03:03:45
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception int 返回类型
	 * @throws
	 */
	public boolean insertConfig(List<HashMap<String, String>> list)
			throws Exception {
		int[] result = null;
		String dcclmc = null;
		StringBuilder sb = null;
		List<String> sqlList = new ArrayList<String>();
		if (list != null) {
			for (HashMap<String, String> map : list) {
				sb = new StringBuilder();
				dcclmc = map.get("dcclmc") == null ? "" : map.get("dcclmc");
				sb
						.append("insert into xg_xtwh_dcpzb(dcclbh,dcclmc,zd,zdmc,xssx,zgh,sfmrzd,zt) values('");
				sb.append(map.get("dcclbh"));
				sb.append("','");
				sb.append(dcclmc);
				sb.append("','");
				sb.append(map.get("zd"));
				sb.append("','");
				sb.append(map.get("zdmc"));
				sb.append("','");
				sb.append(map.get("xssx"));
				sb.append("','");
				sb.append(map.get("zgh"));
				sb.append("','");
				sb.append(map.get("sfmrzd"));
				sb.append("','");
				sb.append(map.get("zt"));
				sb.append("')");
				sqlList.add(sb.toString());
			}
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ExportModel t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ExportModel t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xtwh_dcpzb");
		super.setKey("dcclbh");
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2013-12-2 下午02:26:24 
 */
package com.zfsoft.xgxt.base.export.util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import xgxt.DAO.DAO;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 导入模块
 * @类功能描述: 便于对某表导入时进行配置
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-2 下午02:26:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ImportConfig {
	private DAO dao = DAO.getInstance();
	/**
	 * 
	 * @描述: 插入导入配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-21 上午10:35:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param tableName
	 * void 返回类型
	 */
	public void config(String key,String name, String tableName) {
		List<HashMap<String, String>> list = getTableConfig(tableName);
		StringBuffer sb = new StringBuffer();
		sb
				.append(" insert into ZFXG_DR_DRBMPZB(dryzbh,drmkdm,drmkmc,drbm,drbzwmc,zdm,zdmc,zdlx,yzcs,xssx,drmbzdmc,sfhbyz)");
		int i = 0;
		for (HashMap<String, String> hm : list) {
			sb.append(" select ");
			sb.append("'-1");
			sb.append("','");
			sb.append(key);
			sb.append("','");
			sb.append(name);
			sb.append("','");
			sb.append(tableName);
			sb.append("','");
			sb.append(name);
			//sb.append(hm.get("tablezwmc"));
			sb.append("','");
			sb.append(hm.get("column_name"));
			sb.append("','");
			sb.append(hm.get("comname"));
			sb.append("','");
			sb.append(hm.get("data_type"));
			sb.append("','");
			sb.append("无");
			sb.append("','");
			sb.append(i + 1);
			sb.append("','");
			sb.append(hm.get("comname"));
			sb.append("','");
			sb.append("0'");
			sb.append(" from dual");
			if (i + 1 != list.size()) {
				sb.append(" union all ");
			}
			i++;
		}
		try {
			Statement s = dao.getDb().getConnection().createStatement();
			i = s.executeUpdate(sb.toString());
		} catch (SQLException e) {
			throw new RuntimeException("自动设置导入配置错误!", e);
		}
	}
	/**
	 * 
	 * @描述: 自动配置导出
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-21 上午10:35:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param tableName
	 * void 返回类型
	 */
	public void configExport(String bh,String mc,String tableName) {
		List<HashMap<String, String>> list = getTableConfig(tableName);
		StringBuffer sb = new StringBuffer();
		sb
				.append(" insert into xg_xtwh_dcpzb(dcclbh,dcclmc,zd,zdmc,xssx,zgh,sfmrzd,zt)");
		int i = 0;
		for (HashMap<String, String> hm : list) {
			sb.append(" select '");
			sb.append(bh);
			sb.append("','");
			sb.append(mc);
			sb.append("','");
			sb.append(hm.get("column_name"));
			sb.append("','");
			sb.append(hm.get("comname"));
			sb.append("','");
			sb.append(i);
			sb.append("','");
			sb.append("public");
			sb.append("','");
			sb.append("1");
			sb.append("','");
			sb.append(1);
			sb.append("' from dual");
			if (i + 1 != list.size()) {
				sb.append(" union all ");
			}
			i++;
		}
		try {
			Statement s = dao.getDb().getConnection().createStatement();
			i = s.executeUpdate(sb.toString());
		} catch (SQLException e) {
			throw new RuntimeException("自动设置导入配置错误!", e);
		}
	}
	public List<HashMap<String, String>> getTableConfig(String tableName) {
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select a.table_name, c.column_name, c.data_type, b.comments comName,a.comments tableZwmc from");
		sb
				.append("  user_tab_comments a,user_col_comments b,user_tab_columns c");
		sb.append("  where a.table_name = b.table_name");
		sb.append("  and a.table_name = c.table_name");
		sb.append("  and c.column_name = b.column_name");
		sb.append("  and a.table_name=?");
		sb.append("  order by c.column_id");
		return dao.getListNotOut(sb.toString(), new String[] { tableName
				.toUpperCase() });
	}
}
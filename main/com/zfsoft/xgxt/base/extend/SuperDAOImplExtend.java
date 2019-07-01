/**
 * @部门:学工产品事业部
 * @日期：2014-3-31 上午10:31:39 
 */
package com.zfsoft.xgxt.base.extend;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: Dao扩展
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-31 上午10:31:39
 * @版本： V1.
 * @修改记录: 类修改者-修改日期-修改说明
 */

public abstract class SuperDAOImplExtend<T> extends SuperDAOImpl<T> {
	protected Class<T> cla;

	@Override
	protected void setClass(Class<T> c) {
		cla = c;
		super.setClass(c);
	}

	protected int deleteForSql(String sql, String[] param) {
		try {
			return dao.runDelete(sql, param);
		} catch (Exception e) {
			throw new RuntimeException("删除失败!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 批量保存
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-8 下午04:34:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param beans
	 * @return
	 * @throws Exception int[] 返回类型
	 */
	protected int[] batchSave(List<T> beans) throws Exception {
		Field[] fields = cla.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(this.getTableName());
		List<String> keys = new ArrayList<String>();
		List<String[]> params = new ArrayList<String[]>();
		List<String> values = new ArrayList<String>();
		for (T t : beans) {
			for (Field f : fields) {
				String fieldName = f.getName();
				if (columns.contains(fieldName.toUpperCase())) {
					String methodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					String value = null;
					try {
						value = (String) cla.getClass().getMethod(methodName,
								new Class[] {}).invoke(t);
					} catch (Exception e) {
						logger.error("调用get方法出错,methodName:" + methodName);
						throw e;
					}

					if (!StringUtil.isNull(value)) {
						keys.add(fieldName);
						values.add(value);
					}
				}
			}
			params.add(values.toArray(new String[] {}));
		}
		String sql = getInsertSql(keys, this.getTableName());
		logger.debug(sql);
		return dao.runBatch(sql, params);
	}
	protected String getInsertSql(List<String> keys, String tableName) {

		if (keys.isEmpty()) {
			logger.error("生成insert sql 出错！");
		}

		StringBuilder insertSql = new StringBuilder();

		insertSql.append("insert into ");
		insertSql.append(tableName);
		insertSql.append(" ( ");

		insertSql.append(keys.toString().replace("[", "").replace("]", ""));
		insertSql.append(") values (");

		for (int i = 0, n = keys.size(); i < n; i++) {
			insertSql.append("?");
			if (i != n - 1) {
				insertSql.append(",");
			}
		}
		insertSql.append(") ");
		return insertSql.toString();
	}

	/**
	 * 
	 * @描述: 按权限删除所有
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午11:17:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return int 返回类型
	 */
	protected int deleteAll(User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" delete ");
			sb.append(getTableName());
			sb.append(" where 1=1 ");
			sb.append(searchTjByUser);
			return dao.runDelete(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("删除失败!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 获取当前用户权限的班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午10:31:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBjListForZgh(User user) {
		StringBuffer sb = new StringBuffer();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		sb.append("select distinct a.bjdm,a.bjmc from view_xsxxb a where 1=1 ");
		sb.append(searchTjByUser);
		sb.append(" order by a.bjmc ");
		return dao.getListNotOut(sb.toString(), new String[] {  });
	}

	/**
	 * 
	 * @描述: 获取用户权限范围学院list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 下午12:00:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXyListForZgh(User user) {
		StringBuffer sb = new StringBuffer();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		sb.append("select distinct a.xydm,a.xymc from view_xsxxb a where 1=1 ");
		sb.append(searchTjByUser);
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 获取学院名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 下午12:00:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return String 返回类型
	 */
	public String getXymc(String xydm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select bmmc xymc from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sb.toString(), new String[] { xydm }, "xymc");
	}

	/**
	 * 
	 * @描述:根据学期代码获取学期名称 很多地方使用，每个dao都写次重复量太大
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-18 下午02:48:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return String 返回类型
	 * @throws
	 */
	public String getXqmcForXqdm(String xqdm) {
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[] { xqdm }, "xqmc");
	}
}

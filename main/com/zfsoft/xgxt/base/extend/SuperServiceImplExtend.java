/**
 * @部门:学工产品事业部
 * @日期：2014-3-6 上午09:58:29 
 */
package com.zfsoft.xgxt.base.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 扩展superservice
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-6 上午09:58:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SuperServiceImplExtend<T, E extends SuperDAOImplExtend<T>> extends
		SuperServiceImpl<T, E> {
	/**
	 * 
	 * @描述: 批量执行（带详细提示）
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 上午10:07:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 *            要执行的对应主键
	 * @param delete
	 *            执行业务接口
	 * @return
	 * @throws Exception
	 *             String[] 返回类型
	 */
	public Map<String, Object> batchExecute(String[] ids, IDelete delete) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> delId = new ArrayList<String>();// 可删除的id集合
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			boolean isHaveError = false;
			if (null == ids || ids.length <= 0) {
				return null;
			}
			for (String str : ids) {
				if (delete.isCanDelete(str)) {
					delId.add(str);// 记录删除id
				} else {
					isHaveError = true;
					list.add(delete.showMessage(str));
				}
			}
			int i = delId.size() > 0 ? delete.execute(delId
					.toArray(new String[] {})) : 0;
			map.put(IDelete._CGTS, String.valueOf(i));
			if (isHaveError) {
				map.put(IDelete._ERROE_OBJ, list);
			} else {
				map.put(IDelete._ERROE_OBJ, IDelete._UNHAVE_ERROE);
			}
			return map;
		} catch (Exception e) {
			throw new RuntimeException("批量执行失败!" + e.getMessage());
		}
	}
	public Map<String, Object> delete(String[] ids) throws RuntimeException {
		return batchExecute(ids, new IDelete() {
			public Map<String, String> showMessage(String pk) throws Exception {
				return null;
			}

			public boolean isCanDelete(String pk) throws Exception {
				return true;
			}

			public int execute(String[] ids) throws Exception {
				return runDelete(ids);
			}
		});
	}

	/**
	 * 
	 * @描述: 删除[支持不选全部删除]
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午11:17:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 *            ids字符串以 ‘,’号分隔，如果为空则全部删除
	 * @param user
	 *            当前操作用户
	 * @return
	 * @throws Exception int 返回类型
	 */
	public int runDelete(String value, User user) throws Exception {
		if (StringUtils.isNull(value)) {
			return dao.deleteAll(user);
		}
		return super.runDelete(value.split(","));
	}

	/**
	 * 
	 * @描述: 获取数据中所有指定列的值
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午03:51:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param colName
	 *            列明
	 * @param list
	 *            数据源
	 * @return String 逗号分隔列值
	 */
	public String getAllCols(String colName, List<HashMap<String, String>> list) {
		StringBuffer pks = new StringBuffer();
		int i = 0;
		for (HashMap<String, String> hm : list) {
			pks.append(hm.get(colName));
			if (i++ != list.size()) {
				pks.append(",");
			}
		}
		return pks.toString();
	}

	/**
	 * 
	 * @描述: 获取班级对应权限list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午10:33:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBjList(User user) {
		
		return dao.getBjListForZgh(user);
	}
	/**
	 * 
	 * @描述: 获取学院list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:55:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXyList(User user) {
		return dao.getXyListForZgh(user);
	}
	/**
	 * 
	 * @描述: 获取学院代码
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 下午12:13:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	/**
	 * 
	 * @描述: 获取学期名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午10:35:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型
	 */
	public String getXqmc(String xqdm) {
		return dao.getXqmcForXqdm(xqdm);
	}
}

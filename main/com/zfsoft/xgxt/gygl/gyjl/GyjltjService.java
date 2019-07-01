/**
 * @部门:学工产品事业部
 * @日期：2013-4-27 下午04:11:06 
 */
package com.zfsoft.xgxt.gygl.gyjl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 公寓纪律信息Service
 * @作者： zhangjw
 * @时间： 2013-4-27 下午04:09:18
 * @版本： V5.9.17
 * @修改记录:
 */

public class GyjltjService extends SuperServiceImpl<GyjltjForm, GyjltjDAO> {

	private GyjltjDAO dao = new GyjltjDAO();

	public GyjltjService() {
		super.setDao(dao);
	}

	/**
	 * @描述:获取导出结果集
	 * @作者：zhangjw
	 * @日期：2013-5-27 下午03:23:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<List<String>> 返回类型
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<List<String>> getGyjltj(User user, SearchModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 定义结果集
		List<List<String>> resultList = new ArrayList<List<String>>();
		String[][] yfStr = { { "9月", "09" }, { "10月", "10" }, { "11月", "11" },
				{ "12月", "12" }, { "1月", "01" }, { "2月", "02" },
				{ "3月", "03" }, { "4月", "04" }, { "5月", "05" }, { "6月", "06" },
				{ "7月", "07" }, { "8月", "08" }, { "总计", "qn" } };
		List<String> topList = getTopList(yfStr);
		resultList.add(topList);
		List<HashMap<String, String>> xswjList = dao.getXsWjlb(user, model);
		Map<String, String> tjmap = listToMap(dao.getXsWjTj());
		Map<String, String> dllbxx = listToMap(dao.getDllbxx());
		for (Map<String, String> map : xswjList) {
			List<String> xswj = new ArrayList<String>();
			String xh = "";
			String[] coms = { "rownum", "xymc", "zymc", "bjmc", "xh", "xm",
					"xb", "rxrq", "qsh" };
			for (String com : coms) {
				String val = map.get(com);
				if (com.equals("xh")) {
					xh = val;
				}
				xswj.add(val);
			}
			for (int i = 0; i < yfStr.length; i++) {
				Iterator<Entry<String, String>> ites = dllbxx.entrySet()
						.iterator();
				while (ites.hasNext()) {
					xswj.add(tjmap.get(xh + "," + yfStr[i][1] + ","
							+ ites.next().getKey()));
				}
			}
			resultList.add(xswj);
		}
		return resultList;
	}

	/**
	 * @描述:把list 转换成 Map
	 * @作者：zhangjw
	 * @日期：2013-5-27 下午03:43:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	private Map<String, String> listToMap(List<HashMap<String, String>> list) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Iterator<HashMap<String, String>> ite = list.iterator();
		while (ite.hasNext()) {
			HashMap<String, String> map = ite.next();
			resultMap.put(map.get("pk"), map.get("val"));
		}
		return resultMap;
	}

	/**
	 * @描述:获取导出报表表头
	 * @作者：zhangjw
	 * @日期：2013-4-27 下午05:18:02
	 * @修改记录:
	 * @return List<String> 返回类型
	 * @throws
	 */
	private List<String> getTopList(String[][] yfStr) {
		MessageResources message = MessageResources
				.getMessageResources("config.ApplicationResources");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// 获取大类列表
		Map<String, String> dllbxx = listToMap(dao.getDllbxx());
		String[] topStr = { "序号", Base.YXPZXY_KEY , "专业", "班级", "学号", "姓名", "性别", "入学日期",
				"房间号" };
		List<String> topList = new ArrayList<String>();
		for (int i = 0; i < topStr.length; i++) {
			topList.add(topStr[i]);
		}
		// 循环月份
		for (int i = 0; i < yfStr.length; i++) {
			Iterator<Entry<String, String>> ite = dllbxx.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, String> en = ite.next();
				// 动态添加表头
				topList.add(yfStr[i][0] + "" + en.getValue());
			}

		}
		return topList;
	}
}

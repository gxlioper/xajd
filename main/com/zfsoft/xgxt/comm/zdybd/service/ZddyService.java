package com.zfsoft.xgxt.comm.zdybd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.zdybd.dao.ZddyDao;
import com.zfsoft.xgxt.comm.zdybd.model.ZddyModel;
import com.zfsoft.xgxt.comm.zdybd.util.ZdybdCommon;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 字段定义
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class ZddyService extends SuperServiceImpl<ZddyModel, ZddyDao> {

	private ZddyDao dao = new ZddyDao();

	public ZddyService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:根据功能代码获取所有分类下的字段列表
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndm(String gndm)
			throws Exception {
		List<HashMap<String, String>> list = null;
		if (gndm == null) {
			list = new ArrayList<HashMap<String, String>>();
		} else {
			list = dao.getListByGndm(gndm);
			/*
			 * 1.固定值，格式为：1:男,2:女 2:数据库取值,“表名:代码,名称”,
			 * 3:类名全称#方法名|参数:代码,名称，其中，若有参数，则参数仅支持一个String类型；
			 */
			String szlx = null;
			String szz = null;
			String zdlx = null;
			String ssxJson = null;
			ZdybdCommon zdybdCommon = new ZdybdCommon();
			for (HashMap<String, String> map : list) {
				szlx = map.get("szlx");
				szz = map.get("szz");
				zdlx = map.get("zdlx");
				if (zdlx != null && zdlx.equals("22")) {
					if (ssxJson == null) {
						ssxJson = zdybdCommon.getSsxJson();
					}
					map.put("szz", ssxJson);
				}

				if (szlx == null) {
					continue;
				}
				if (szlx.trim().equals("1") || szlx.trim().equals("10")) {
					map.put("szz", zdybdCommon.dmmcToJson(szz));
				} else if (szlx.trim().equals("2") || szlx.trim().equals("20")) {
					map.put("szz", zdybdCommon.getSjkqz(szz));
				} else if (szlx.trim().equals("3") || szlx.trim().equals("30")) {
					map.put("szz", zdybdCommon.getFfqz(szz));
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @描述:根据功能代码获取所有分类下的字段列表
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfXszdsz(String gndm)
			throws Exception {
		String zddyid = null;
		List<HashMap<String, String>> list = getListByGndm(gndm);

		List<HashMap<String, String>> stuList = dao.getListByGndmOfXszdsz(gndm);
		if (stuList != null && stuList.size() > 0) {
			for (HashMap<String, String> stuMap : stuList) {
				zddyid = stuMap.get("zddyid");
				if (zddyid == null) {
					continue;
				}
				for (HashMap<String, String> map : list) {
					if (zddyid.equals(map.get("zddyid"))) {
						map.put("yxxg", stuMap.get("yxxg"));
						map.put("yxwk", stuMap.get("yxwk"));
						break;
					}
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @描述:根据功能代码获取所有分类下的字段列表，全改为仅显示状态
	 * @作者：ligl
	 * @日期：2013-12-9 上午10:41:52
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfXs(String gndm)
			throws Exception {
		List<HashMap<String, String>> list = getListByGndm(gndm);
		for (HashMap<String, String> map : list) {
			map.put("yxxg", "0");
			map.put("yxwk", "1");
		}
		return list;
	}

	/**
	 * 
	 * @描述:获取字段定义，简单显示，包含学生定义
	 * @作者：ligl
	 * @日期：2013-12-10 下午04:27:31
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getListByGndmOfSimple(String gndm)
			throws Exception {
		List<HashMap<String, String>> list = dao.getSimpleListByGndm(gndm);
		return list;
	}

	public boolean updateSimple(ZddyModel model) throws Exception {
		boolean result = true;
		String lb = model.getLb();
		String gndm = model.getGndm();
		String dataJson = model.getDataJson();
		List list = JsonUtil.jsonToList(dataJson, ZddyModel.class);
		if (lb != null && lb.equals("js")) {
			result = dao.updateSimple(list);
		} else {
			result = dao.deleteStuByGnmk(gndm);
			if (result) {
				result = dao.insertSimpleForXs(list);
			}
		}
		return result;
	}

}

package com.zfsoft.xgxt.comm.zdybd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.zdybd.dao.FlszDao;
import com.zfsoft.xgxt.comm.zdybd.model.FlszModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 分类设置
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class FlszService extends SuperServiceImpl<FlszModel, FlszDao> {

	private FlszDao dao = new FlszDao();

	public FlszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:根据功能代码获取分类列表数据
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
		}
		return list;
	}

	/**
	 * 
	 * @描述:根据功能代码获取分类列表数据
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<String> getSrcListByGndm(String gndm) throws Exception {
		List<String> resultList = new ArrayList<String>();
		List<HashMap<String, String>> list = getListByGndm(gndm);
		for (HashMap<String, String> flszMap : list) {
			if (flszMap == null) {
				continue;
			}
			String bdms = flszMap.get("bdms");
			if (bdms == null || (!bdms.trim().equals("2") && !bdms.trim().equals("4"))) {
				continue;
			}
			String bdszz = flszMap.get("bdszz");
			if (bdszz == null || bdszz.trim().equals("")) {
				continue;
			}
			String src = getSrc(bdszz);
			resultList.add(src);
		}
		return resultList;
	}

	/*
	 * 获取json串中的src
	 */
	@SuppressWarnings("unchecked")
	private String getSrc(String bdszz) {
		String src = null;
		bdszz = "{data:[{" + bdszz + "}]}";
		try {
			List list = JsonUtil.jsonToList(bdszz);
			Object object = null;
			if (list != null && list.size() > 0) {
				object = list.get(0);
				net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
				src = (String) bean.get("src");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return src;
	}

}

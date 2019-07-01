/**
 * @部门:学工产品事业部
 * @日期：2015-9-8 上午11:18:42 
 */  
package com.zfsoft.xgxt.xsxx.xygl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息new
 * @类功能描述: 校友管理 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-9-8 上午11:18:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyglService extends SuperServiceImpl<XyglForm, XyglDao> {
	
	private XyglDao dao = new XyglDao();
	
	public XyglService() {
		super.setDao(dao);
	}
	
	/**
	 * 校友管理信息查询（非在校生集合）
	 */
	public List<HashMap<String, String>> getXyglList(XyglForm model,
			User user) throws Exception {
		return dao.getPageList(model, user);
	}
	
	/**
	 * 删除
	 */
	public boolean delData(String keys) throws Exception {
		return dao.delData(keys);
	}
	
	/**
	 * 修改
	 */
	public boolean updateRecord(XyglForm myForm,
			HashMap<String, String> valueMap,
			HashMap<String, String> xsfzxxValueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// 籍贯
		String jg = valueMap.get("jg");
		HashMap<String, String> jgMap = getSsx(jg);
		valueMap.put("jgs", jgMap.get("sheng"));
		valueMap.put("jgshi", jgMap.get("shi"));
		valueMap.put("jgx", jgMap.get("xian"));

		boolean result = dao.updateInfo(valueMap);
		if (result) {
			result = dao.updateInfoXsfzxx(xsfzxxValueMap);
		}

		return result;
	}

	/*
	 * 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
	 */
	private HashMap<String, String> getSsx(String dm) {
		HashMap<String, String> ssx = new HashMap<String, String>();
		String sheng = "";
		String shi = "";
		String xian = "";
		if (dm != null && !dm.trim().equals("") && dm.length() >= 6) {
			String tmp0 = dm.substring(0, 2);
			sheng = tmp0 + "0000";
			String tmp1 = dm.substring(2, 4);
			if (!tmp1.equals("00")) {
				shi = tmp0 + tmp1 + "00";
			}
			String tmp2 = dm.substring(4, 6);
			if (!tmp2.equals("00")) {
				xian = dm;
			}
		}
		ssx.put("sheng", sheng);
		ssx.put("shi", shi);
		ssx.put("xian", xian);
		return ssx;
	}
	
	/**
	 * 根据学号查询信息
	 */
	public HashMap<String, String> getXsxxByXhForUpdate(String xh) {
		HashMap<String, String> map = dao.getXsxxByXhForUpdate(xh);
		return map;
	}
	
	/**
	 * 判断学号是否在校友管理中
	 */
	public String chkStuIsExistsXYGL(String xh) {
		return dao.chkStuIsExistsXYGL(xh);
	}
	
	
	public boolean saveRecord(XyglForm myForm,
			HashMap<String, String> valueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
		// 生源地
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		boolean result = dao.saveInfo(valueMap);
		if (result) {
			result = dao.saveXsqtxx(myForm);
		}
		return result;
	}
}

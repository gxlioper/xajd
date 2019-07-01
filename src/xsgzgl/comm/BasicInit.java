package xsgzgl.comm;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xsgzgl.comm.globals.GlobalsValue;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 基础Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class BasicInit {

	/**
	 * 获得学校拼音名称
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String getXxmc(String xxdm, String xxpymc) {
		if (Base.isNull(xxpymc)) {
			xxpymc = GlobalsValue.getXxpymc(xxdm);
		}
		return xxpymc;
	}

	/**
	 * 初始化牛B保存
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void init(String[] primary_key, String[] save_string_zd,
			String[] save_array_zd, String save_table,
			HttpServletRequest request) throws Exception {

		// =======================主键 begin=======================
		if (primary_key != null && primary_key.length > 0) {
			StringBuilder pk = new StringBuilder();
			for (int i = 0; i < primary_key.length; i++) {
				if (i != 0) {
					pk.append("!!luojw!!");
				}
				pk.append(primary_key[i]);
			}
			request.setAttribute("primary_key", pk);
		}
		// =======================主键 end=======================

		// =======================单一字段 begin=====================
		if (save_string_zd != null && save_string_zd.length > 0) {
			StringBuilder one = new StringBuilder();
			for (int i = 0; i < save_string_zd.length; i++) {
				if (i != 0) {
					one.append("!!luojw!!");
				}
				one.append(save_string_zd[i]);
			}
			request.setAttribute("save_string_zd", one);
		}
		// =======================单一字段 end=======================

		// =======================批量字段 begin=====================
		if (save_array_zd != null && save_array_zd.length > 0) {
			StringBuilder arr = new StringBuilder();
			for (int i = 0; i < save_array_zd.length; i++) {
				if (i != 0) {
					arr.append("!!luojw!!");
				}
				arr.append(save_string_zd[i]);
			}
			request.setAttribute("save_array_zd", arr);
		}
		// =======================批量字段 end=======================

		request.setAttribute("save_table", save_table);
	}
}

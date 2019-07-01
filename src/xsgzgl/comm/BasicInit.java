package xsgzgl.comm;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xsgzgl.comm.globals.GlobalsValue;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ����Init��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class BasicInit {

	/**
	 * ���ѧУƴ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String getXxmc(String xxdm, String xxpymc) {
		if (Base.isNull(xxpymc)) {
			xxpymc = GlobalsValue.getXxpymc(xxdm);
		}
		return xxpymc;
	}

	/**
	 * ��ʼ��ţB����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void init(String[] primary_key, String[] save_string_zd,
			String[] save_array_zd, String save_table,
			HttpServletRequest request) throws Exception {

		// =======================���� begin=======================
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
		// =======================���� end=======================

		// =======================��һ�ֶ� begin=====================
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
		// =======================��һ�ֶ� end=======================

		// =======================�����ֶ� begin=====================
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
		// =======================�����ֶ� end=======================

		request.setAttribute("save_table", save_table);
	}
}

package xsgzgl.pjpy.general.wdpj.jgcx;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_�����ѯ_Model
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

public class WdpjJgcxModel {

	// ����
	private String[] primary_key = new String[] { "xn", "xq", "xh", "xmmc" };

	// ����ֵ
	private String[] pkValue;

	// ��һ�ֶ�
	private String[] save_string_zd = new String[] { "xn", "xq", "xh", "xmlx",
			"xmmc", "xmje", "hdsj", "bz" };

	// �����ֶ�
	private String[] save_array_zd = new String[] {};

	// ����
	private String save_table = "xg_pjpy_pjlsxxb";

	// ��ѯ��
	private String search_table = "xg_view_pjpy_pjlsxx";

	// ��ѯ��ʾ�ֶ�
	private String[] search_zd = new String[] { "pk", "pjsj", "xh", "xm", "nj",
			"bjmc", "xmlxmc", "xmmc", "xmje" };

	// ��ϸ��ʾ�ֶ�
	private String[] detail_zd = new String[] { "xh", "xm", "nj", "xymc",
			"zymc", "bjmc", "xn", "xq", "xmmc", "xmlx", "xmje", "hdsj", "bz" };

	public String[] getPrimary_key() {
		return primary_key;
	}

	public void setPrimary_key(String[] primaryKey) {
		primary_key = primaryKey;
	}

	public String[] getSave_string_zd() {
		return save_string_zd;
	}

	public void setSave_string_zd(String[] saveStringZd) {
		save_string_zd = saveStringZd;
	}

	public String[] getSave_array_zd() {
		return save_array_zd;
	}

	public void setSave_array_zd(String[] saveArrayZd) {
		save_array_zd = saveArrayZd;
	}

	public String getSave_table() {
		return save_table;
	}

	public void setSave_table(String saveTable) {
		save_table = saveTable;
	}

	public String getSearch_table() {
		return search_table;
	}

	public void setSearch_table(String searchTable) {
		search_table = searchTable;
	}

	public String[] getSearch_zd() {
		return search_zd;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public String[] getDetail_zd() {
		return detail_zd;
	}

	public void setDetail_zd(String[] detailZd) {
		detail_zd = detailZd;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public void setSearch_zd(String[] searchZd) {
		search_zd = searchZd;
	}
}

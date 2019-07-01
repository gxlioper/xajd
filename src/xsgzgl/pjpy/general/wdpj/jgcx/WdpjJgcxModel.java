package xsgzgl.pjpy.general.wdpj.jgcx;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_结果查询_Model
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

public class WdpjJgcxModel {

	// 主键
	private String[] primary_key = new String[] { "xn", "xq", "xh", "xmmc" };

	// 主键值
	private String[] pkValue;

	// 单一字段
	private String[] save_string_zd = new String[] { "xn", "xq", "xh", "xmlx",
			"xmmc", "xmje", "hdsj", "bz" };

	// 批量字段
	private String[] save_array_zd = new String[] {};

	// 表名
	private String save_table = "xg_pjpy_pjlsxxb";

	// 查询表
	private String search_table = "xg_view_pjpy_pjlsxx";

	// 查询显示字段
	private String[] search_zd = new String[] { "pk", "pjsj", "xh", "xm", "nj",
			"bjmc", "xmlxmc", "xmmc", "xmje" };

	// 详细显示字段
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

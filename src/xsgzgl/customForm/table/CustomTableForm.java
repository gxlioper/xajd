package xsgzgl.customForm.table;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.customForm.model.TableContentModel;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.model.TablePkContentModel;
import xsgzgl.customForm.model.TableSearchContentModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class CustomTableForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/* 通用 */
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();

	TableModel tableModel;

	TableContentModel tableContentModel;

	TableSearchContentModel tableSearchContentModel;

	TablePkContentModel tablePkContentModel;

	private String id;// id

	private String yhm = "zf01";// 用户名

	private String gnmkdm;// 功能模块代码

	private String gnmkmc;// 功能模块名称

	private String dyym;// 路径

	private String tablename;// 表名

	private String shzt;// 是否需要审核

	private String dxq = "1";// 读写权

	private String create_time;// 创建时间

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getDxq() {
		return dxq;
	}

	public void setDxq(String dxq) {
		this.dxq = dxq;
	}

	public String getDyym() {
		return dyym;
	}

	public void setDyym(String dyym) {
		this.dyym = dyym;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}

	public String getGnmkmc() {
		return gnmkmc;
	}

	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public TableContentModel getTableContentModel() {
		return tableContentModel;
	}

	public void setTableContentModel(TableContentModel tableContentModel) {
		this.tableContentModel = tableContentModel;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public TableSearchContentModel getTableSearchContentModel() {
		return tableSearchContentModel;
	}

	public void setTableSearchContentModel(
			TableSearchContentModel tableSearchContentModel) {
		this.tableSearchContentModel = tableSearchContentModel;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public TablePkContentModel getTablePkContentModel() {
		return tablePkContentModel;
	}

	public void setTablePkContentModel(TablePkContentModel tablePkContentModel) {
		this.tablePkContentModel = tablePkContentModel;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}

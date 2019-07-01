package com.zfsoft.xgxt.base.export.service;

import java.io.File;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ExportConfigModel;
import com.zfsoft.xgxt.base.export.model.ExportModel;

/**
 * 公用导出接口
 * 
 * @author Penghui.Qu
 * 
 */
public interface IExportService {

	static final String SHEET_NAME = "sheet1";
	static final String PUBLIC_CONFIG = "public";
	static final String SELECT_ZT = "1";// 导出选中状态
	static final String UNSELECT_ZT = "0";// 导出未选中状态
	static final String TEMP_PATH_KEY = "export_temp_path";

	/**
	 * 获取导出文件
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public File getExportFile(ExportModel model) throws Exception;

	/**
	 * 查询导出配置
	 * 
	 * @param model
	 * @return
	 */
	public List<ExportConfigModel> getConfigList(ExportModel model);

	/**
	 * 保存导出配置
	 * 
	 * @param model
	 * @return boolean
	 */
	public boolean saveExportConfig(ExportModel model);

}

package com.zfsoft.xgxt.comm.export.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.comm.export.model.ExportModel;



/**
 * 公用导出接口
 * @author Penghui.Qu
 *
 */
public interface IExportService {

	static final String SHEET_NAME = "sheet1";
	static final String PUBLIC_CONFIG = "public";
	static final String SELECT_ZT = "1";//导出选中状态
	static final String UNSELECT_ZT = "0";//导出未选中状态
	static final String TEMP_PATH_KEY= "export_temp_path";
	
	/**
	 * 获取导出文件
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public File getExportFile(ExportModel model) throws Exception;

	
	
	
	/**
	 * 
	 * @描述: 获取导出文件
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-6 上午08:40:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param title 
	 * @param dataList
	 * @return
	 * File 返回类型
	 */
	public File getExportFile(Map<String,String> title ,List<HashMap<String,String>> dataList) throws Exception;
	
	
	
	
	
	/**
	 * 查询导出配置
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getConfigList(ExportModel model);
	
	
	/**
	 * 保存导出配置
	 * @param model
	 * @return boolean
	 */
	public boolean saveExportConfig(ExportModel model) throws Exception ;
	
	public File getExportExcelFile(ExportModel model,QueryDataService queryDataService) throws Exception ;

	
}

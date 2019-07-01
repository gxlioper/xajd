package com.zfsoft.xgxt.base.export.dao;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ExportConfigModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 公用导出
 * 
 * @author Penghui.Qu
 * 
 */
public interface IExportDao {

	/**
	 * 查询导出配置
	 * 
	 * @param model
	 * @return
	 */
	public List<ExportConfigModel> getExportConfig(ExportModel model);

	/**
	 * 插入导出配置
	 * 
	 * @param model
	 * @return
	 */
	public int insertConfig(List<HashMap<String, String>> list);

	/**
	 * 删除用户导出配置
	 * 
	 * @param model
	 * @return
	 */
	public int deleteConfig(ExportModel model);
}

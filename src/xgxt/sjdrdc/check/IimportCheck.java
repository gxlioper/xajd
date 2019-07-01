package xgxt.sjdrdc.check;

import java.util.List;

import xgxt.sjdrdc.ExportAndImportModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 数据导入检测接口
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: sjf</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-04-06</p>
 */
public interface IimportCheck {

	/**
	 * 检查excel导入数据，新的导入检测类都必须实现该接口 
	 * @param filePath
	 * @param realTable
	 * @param mappingItems
	 * @return
	 */
	public List<String[]> checkExcelData(ExportAndImportModel model);
}

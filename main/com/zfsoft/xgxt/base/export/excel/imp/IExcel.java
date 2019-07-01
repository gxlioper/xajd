package com.zfsoft.xgxt.base.export.excel.imp;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 定义Excel操作接口
 * 
 * @author Jiangdong.Yi 依赖jxl
 */
public interface IExcel {

	/**
	 * 根据ExcelFile 获取 数据列表 简单版
	 * 
	 * @param file
	 * @return
	 */
	public List<String[]> getDataList(File file) throws Exception;
	/**
	 * 
	 * @描述:根据Excel获取Excle文件数据（增加流支持）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-29 下午03:22:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @return
	 * @throws Exception
	 * List<String[]> 返回类型 
	 */
	public List<String[]> getDataList(InputStream is) throws Exception;
	/**
	 * 根据ExcelFile 获取 数据列表
	 * 
	 * @param file
	 *            Excel文件
	 * @param columnum
	 *            读取列数
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getDataList(File file, int columnum) throws Exception;

	/**
	 * 根据数据列表 获取ExcelFile 简单版
	 * 
	 * @param dataList
	 * @return
	 */
	public File getExcel(List<String[]> dataList, String filePath)
			throws Exception;
}

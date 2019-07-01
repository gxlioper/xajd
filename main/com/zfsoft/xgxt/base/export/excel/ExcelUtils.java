package com.zfsoft.xgxt.base.export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.export.excel.imp.ExcelImpl;
import com.zfsoft.xgxt.base.export.excel.imp.ExcelModel;
import com.zfsoft.xgxt.base.export.excel.imp.IExcel;
import com.zfsoft.xgxt.base.export.excel.template.ATemplateBuilder;

/**
 * Excel操作工具
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ExcelUtils {

	/**
	 * 创建简单版的Excel文件
	 * 
	 * @param dataList
	 *            数据列表
	 * @param filePath
	 *            文件路径包括文件的实体名称
	 * @return 返回Excel文件
	 * @throws Exception
	 */
	public static File createExcel(List<String[]> dataList, String filePath)
			throws Exception {
		if (dataList == null || filePath == null || "".equals(filePath)) {
			return null;
		}

		IExcel excel = new ExcelImpl();
		File file = null;
		file = excel.getExcel(dataList, filePath);
		return file;
	}

	/**
	 * 创建简单版的Excel文件
	 * 
	 * @param dataList
	 *            数据列表
	 * @param filePath
	 *            文件路径包括文件的实体名称
	 * @param templateBuilder
	 *            导入模板
	 * @return 返回Excel文件
	 * @throws Exception
	 */
	public static File createExcel(List<String[]> dataList, String filePath,
			ATemplateBuilder templateBuilder) throws Exception {
		if (dataList == null || filePath == null || "".equals(filePath)) {
			return null;
		}
		// 设置模板
		ExcelModel excelModel = new ExcelModel();
		excelModel.setTemplateBuilder(templateBuilder);

		// 加入个性化格式生成Excel
		IExcel excel = new ExcelImpl(excelModel);
		File file = null;
		file = excel.getExcel(dataList, filePath);
		return file;
	}

	/**
	 * 根据Excel获取Excle文件数据
	 * 
	 * @param file
	 *            Excel文件
	 * @return
	 */
	public static List<String[]> getDataList(File file) throws Exception {
		if (file == null) {
			return null;
		}
		return getDataList(new FileInputStream(file));
	}
	/**
	 * 
	 * @描述:根据Excel获取Excle文件数据（增加流支持）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-29 下午03:21:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @return
	 * @throws Exception
	 * List<String[]> 返回类型 
	 */
	public static List<String[]> getDataList(InputStream is) throws Exception {
		if (is == null) {
			return null;
		}
		IExcel excel = new ExcelImpl();
		List<String[]> list = null;
		list = excel.getDataList(is);
		return list;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("user.dir"));
		List<String[]> dataList=new ArrayList<String[]>();
		String[] s= new String[]{"aaa","bbb"};
		dataList.add(s);
		String filePath=System.getProperty("user.dir")+"/11/A.xls";
		ExcelUtils.createExcel(dataList, filePath);
		System.out.println(111);
	}
}

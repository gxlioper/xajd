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
 * Excel��������
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ExcelUtils {

	/**
	 * �����򵥰��Excel�ļ�
	 * 
	 * @param dataList
	 *            �����б�
	 * @param filePath
	 *            �ļ�·�������ļ���ʵ������
	 * @return ����Excel�ļ�
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
	 * �����򵥰��Excel�ļ�
	 * 
	 * @param dataList
	 *            �����б�
	 * @param filePath
	 *            �ļ�·�������ļ���ʵ������
	 * @param templateBuilder
	 *            ����ģ��
	 * @return ����Excel�ļ�
	 * @throws Exception
	 */
	public static File createExcel(List<String[]> dataList, String filePath,
			ATemplateBuilder templateBuilder) throws Exception {
		if (dataList == null || filePath == null || "".equals(filePath)) {
			return null;
		}
		// ����ģ��
		ExcelModel excelModel = new ExcelModel();
		excelModel.setTemplateBuilder(templateBuilder);

		// ������Ի���ʽ����Excel
		IExcel excel = new ExcelImpl(excelModel);
		File file = null;
		file = excel.getExcel(dataList, filePath);
		return file;
	}

	/**
	 * ����Excel��ȡExcle�ļ�����
	 * 
	 * @param file
	 *            Excel�ļ�
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
	 * @����:����Excel��ȡExcle�ļ����ݣ�������֧�֣�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-29 ����03:21:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @return
	 * @throws Exception
	 * List<String[]> �������� 
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

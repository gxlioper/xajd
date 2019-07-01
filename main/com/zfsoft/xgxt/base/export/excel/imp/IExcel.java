package com.zfsoft.xgxt.base.export.excel.imp;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * ����Excel�����ӿ�
 * 
 * @author Jiangdong.Yi ����jxl
 */
public interface IExcel {

	/**
	 * ����ExcelFile ��ȡ �����б� �򵥰�
	 * 
	 * @param file
	 * @return
	 */
	public List<String[]> getDataList(File file) throws Exception;
	/**
	 * 
	 * @����:����Excel��ȡExcle�ļ����ݣ�������֧�֣�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-29 ����03:22:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @return
	 * @throws Exception
	 * List<String[]> �������� 
	 */
	public List<String[]> getDataList(InputStream is) throws Exception;
	/**
	 * ����ExcelFile ��ȡ �����б�
	 * 
	 * @param file
	 *            Excel�ļ�
	 * @param columnum
	 *            ��ȡ����
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getDataList(File file, int columnum) throws Exception;

	/**
	 * ���������б� ��ȡExcelFile �򵥰�
	 * 
	 * @param dataList
	 * @return
	 */
	public File getExcel(List<String[]> dataList, String filePath)
			throws Exception;
}

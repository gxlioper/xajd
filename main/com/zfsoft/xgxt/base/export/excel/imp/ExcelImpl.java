package com.zfsoft.xgxt.base.export.excel.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.zfsoft.xgxt.base.export.excel.template.ATemplateBuilder;

/**
 * ����Excel��������ʵ����
 * 
 * @author Jiangdong.Yi
 * @update �Ų�·[982]
 * @todo �����¹�������
 */
public class ExcelImpl implements IExcel {
	// ����ExcelModel
	private ExcelModel excelModel = null;

	public ExcelImpl() {
		// ��ʼ��Ĭ�ϸ�ʽ
		excelModel = new ExcelModel();
	}

	public ExcelImpl(ExcelModel excelModel) {
		if (excelModel != null) {
			// ��ʼ����ʽ
			this.excelModel = excelModel;
		} else {
			// ��ʼ��Ĭ�ϸ�ʽ
			excelModel = new ExcelModel();
		}
	}
	/**
	 * ����ExcelFile ��ȡ �����б�(������֧��)
	 */
	public List<String[]> getDataList(InputStream is) throws Exception {
		if (is == null) {
			throw new IllegalArgumentException("�Ҳ����ļ�!");
		}
		// ������
		Workbook book = null;
		// ������
		Sheet sheet = null;
		// �����������ݼ�
		List<String[]> resultList = new ArrayList<String[]>();
		try {
			// ��ȡ�ļ�������
			book = Workbook.getWorkbook(is);
			// ��õ�һ�����������
			sheet = book.getSheet(0);
			// �õ���һ�е�һ�еĵ�Ԫ��
			// �õ�����
			int columnum = sheet.getColumns();
			// �õ�����
			int rownum = sheet.getRows();
			// ��Ԫ��
			Cell cell = null;
			// ȥ�հ��б�ʾ
			boolean isNullData = true;

			// �������ؽ��
			String[] rows = null;
			// ѭ�����ж�д
			for (int i = 0; i < rownum; i++) {
				isNullData = true;
				rows = new String[columnum];
				for (int j = 0; j < columnum; j++) {
					cell = sheet.getCell(j, i);
					rows[j] = cell.getContents();
					if (rows[j] != null && !"".equals(rows[j].trim())) {
						isNullData = false;
					}
				}
				if (!isNullData) {
					resultList.add(rows);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (book != null) {
				book.close();
			}
		}
		return resultList;
	}

	/**
	 * ����ExcelFile ��ȡ �����б� �򵥰�
	 * 
	 * @param file
	 * @return
	 */
	public List<String[]> getDataList(File file) throws Exception {
		if (file == null) {
			throw new IllegalArgumentException("�Ҳ����ļ�!");
		}
		return getDataList(new FileInputStream(file));
	}

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
	public List<String[]> getDataList(File file, int columnum) throws Exception {
		if (file == null) {
			throw new IllegalArgumentException("�Ҳ����ļ�!");
		}
		if (columnum == 0) {
			return null;
		}
		// ������
		Workbook book = null;
		// ������
		Sheet sheet = null;
		// �����������ݼ�
		List<String[]> resultList = new ArrayList<String[]>();
		try {
			// ��ȡ�ļ�������
			book = Workbook.getWorkbook(file);
			// ��õ�һ�����������
			sheet = book.getSheet(0);
			// �õ���һ�е�һ�еĵ�Ԫ��
			// �õ�����
			int rownum = sheet.getRows();
			// ��Ԫ��
			Cell cell = null;
			// ȥ�հ��б�ʾ
			boolean isNullData = true;

			// �������ؽ��
			String[] rows = null;
			// ѭ�����ж�д
			for (int i = 0; i < rownum; i++) {
				isNullData = true;
				rows = new String[columnum];
				for (int j = 0; j < columnum; j++) {
					cell = sheet.getCell(j, i);
					rows[j] = cell.getContents();
					if (rows[j] != null && !"".equals(rows[j].trim())) {
						isNullData = false;
					}
				}
				if (!isNullData) {
					resultList.add(rows);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (book != null) {
				book.close();
			}
		}
		return null;
	}

	/**
	 * ���������б� ��ȡExcelFile �򵥰�
	 * 
	 * @param dataList
	 * @return
	 */
	public File getExcel(List<String[]> dataList, String filePath)
			throws Exception {
		if (dataList == null || dataList.size() == 0) {
			throw new IllegalArgumentException("����Ϊ�գ����ܴ���Excel!");
		}

		// ����Excel�ļ�
		File file = cearetExcelFile(filePath);
		return cearetExcel(dataList, file);
	}

	/**
	 * ����Excel�ļ�
	 * 
	 * @param dataList
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private File cearetExcel(List<String[]> dataList, File file)
			throws Exception {
		if (dataList == null || dataList.size() == 0 || file == null) {
			return file;
		}
		// ���ļ�
		WritableWorkbook book = null;
		// ������Ϊ������
		WritableSheet sheet = null;
		// ǰ�������ݵ�Ԫ������
		Label label = null;
		// ��ȡ����������
		String sheetName = excelModel.getSheetName();
		try {
			book = Workbook.createWorkbook(file);
			sheet = book.createSheet(sheetName, 0);

			String[] rows = null;
			for (int i = 0; i < dataList.size(); i++) {
				rows = dataList.get(i);
				if (rows != null) {
					for (int j = 0; j < rows.length; j++) {
						label = new Label(j, i, rows[j]);
						// ������õĵ�Ԫ����ӵ���������
						sheet.addCell(label);

					}
				}
			}
			// ����ģ��
			if (excelModel.getTemplateBuilder() != null) {
				ATemplateBuilder templateBuilder = excelModel
						.getTemplateBuilder();
				// ���ù�����
				templateBuilder.setSheet(sheet);
				excelModel.getTemplateBuilder().getExcelTemplate();
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// д�����ݲ��ر��ļ�
			if (book != null) {
				book.write();
				book.close();
			}
		}
		return file;
	}

	/**
	 * ����Excel�ļ�
	 * 
	 * @param path
	 * @return
	 */
	private File cearetExcelFile(String filePath) {
		// �����ļ���� ����ʱĿ¼
		File tempFile = new File(filePath);

		if (!tempFile.exists()) {
			makeDir(tempFile.getParentFile());
		}
		return tempFile;
	}

	/**
	 * �����ļ�Ŀ¼
	 * 
	 * @param dir
	 */
	private static void makeDir(File file) {
		if (!file.getParentFile().exists()) {
			makeDir(file.getParentFile());
		}
		file.mkdir();
	}
}

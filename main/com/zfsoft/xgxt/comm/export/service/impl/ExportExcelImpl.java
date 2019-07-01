package com.zfsoft.xgxt.comm.export.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.dbf.DBFWriter;
import com.zfsoft.xgxt.base.util.dbf.JDBField;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.util.ExportComparator;

/**
 * ����ʵ��
 * 
 * @author Penghui.Qu
 * 
 */
public class ExportExcelImpl extends AbstractExportService {
	
	
	private static final int RAM_MAX_SIZE =5000;//�ڴ��м�¼��������
	private int start_index =0;
	private int start_row =0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zfsoft.service.svcinterface.IExportService#getExcel(com.zfsoft.dao
	 * .entities.ExportModel, java.io.OutputStream)
	 */
	public File getExportFile(ExportModel model) throws Exception {

		// �������Ϸ���
		if (null == model || StringUtil.isNull(model.getDcclbh())
				|| StringUtil.isNull(model.getZgh())) {
			throw new SystemException("Nonlicet params !");
		}


		// //////////////����dbf����////////////////////////////////////
		String wjgs = model.getExportWjgs();
		if (wjgs != null && wjgs.equals("dbf")) {
			return createDbfFile(model);
		}
		// /////////////////////////////////////////////////////////////
		// �����ļ���� ����ʱĿ¼

		File file = createFile();
		try {
			FileOutputStream stream = new FileOutputStream(file);
			// ����excel������
			WritableWorkbook wwb = Workbook.createWorkbook(stream);
			WritableSheet ws = wwb.createSheet(SHEET_NAME, 0);
			// ��ȡ��������
			List<HashMap<String, String>> configList = getConfigList(model);
			// ����ʾ˳������DAO��ȥ�������������to_numberʱSQL����
			Collections.sort(configList, new ExportComparator());
			// д��Excel
			writeExcel(model.getDataList(), configList, ws);
			wwb.write();
			wwb.close();
		} catch (Exception ex) {
			log.error("Export failed info:ID(" + model.getDcclbh() + "),User("
					+ model.getZgh() + ")", ex);
			throw new Exception(ex);
		}

		return file;
	}
	public File getExportExcelFile(ExportModel model,QueryDataService queryDataService) throws Exception {
		// �������Ϸ���
		if (null == model || StringUtil.isNull(model.getDcclbh())
				|| StringUtil.isNull(model.getZgh())) {
			throw new SystemException("Nonlicet params !");
		}
		// //////////////����dbf����////////////////////////////////////
		String wjgs = model.getExportWjgs();
		if (wjgs != null && wjgs.equals("dbf")) {
			return createDbfFile(model);
		}
		try {
			// ��ȡ��������
			List<HashMap<String, String>> configList = getConfigList(model);
			// ����ʾ˳������DAO��ȥ�������������to_numberʱSQL����
			Collections.sort(configList, new ExportComparator());
			// �����ļ���� ����ʱĿ¼
			File file=createExcelFile(configList);
			writeExcelPoI(model,queryDataService, configList, file);
			return file;
		} catch (Exception ex) {
			log.error("Export failed info:ID(" + model.getDcclbh() + "),User("
					+ model.getZgh() + ")", ex);
			throw new Exception(ex);
		}
	}

	/*
	 * ����dbf�ļ�
	 */
	private File createDbfFile(ExportModel model) throws Exception {

		String fileName = createFileName("dbf");
		File file = new File(fileName);
		
		// ��ȡ��������
		List<HashMap<String, String>> configList = getConfigList(model);

		HashMap<String, String> configMap = null;
		for (int i = configList.size() - 1; i >= 0; i--) {
			configMap = configList.get(i);
			if (UNSELECT_ZT.equals(configMap.get("zt"))) {
				configList.remove(i);
			}
		}
		// ����ʾ˳������DAO��ȥ�������������to_numberʱSQL����
		Collections.sort(configList, new ExportComparator());

		JDBField[] dbfField = new JDBField[configList.size()];

		String title = null;
		for (int i = 0; i < configList.size(); i++) {
			configMap = configList.get(i);
			title = configMap.get("zdmc");
			dbfField[i] = new JDBField(title, 'C', 20, 0);
		}
		DBFWriter dbfWriter = null;
		try {
			List<HashMap<String, String>> dataList = model.getDataList();
			Object[] aobj = null;
			// ���ݼ�д��
			if (dataList != null && dataList.size() > 0) {
				List<Object[]> aobjList = new ArrayList<Object[]>();
				for (int i = 0; i < dataList.size(); i++) {
					HashMap<String, String> dataMap = (HashMap<String, String>) dataList
							.get(i);
					aobj = new Object[configList.size()];
					for (int j = 0; j < configList.size(); j++) {
						String zd = configList.get(j).get("zd");
						String value = dataMap.get(zd);
						int valueLength = getStrLength(value);
						if (dbfField[j] != null && value != null
								&& dbfField[j].getLength() < valueLength) {					
							dbfField[j] = new JDBField(dbfField[j].getName(), dbfField[j].getType(),valueLength, dbfField[j].getDecimalCount());
							if(valueLength > dbfField[j].getLength()){
								value = value.substring(0, dbfField[j].getLength()/2);
							}
						}
						aobj[j] = value;
					}
					aobjList.add(aobj);
				}
				dbfWriter = new DBFWriter(fileName, dbfField);
				for (int i = 0; i < aobjList.size(); i++) {
					dbfWriter.addRecord(aobjList.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbfWriter != null) {
				try {
					dbfWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	// ������ʱ�ļ�
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// ���������ļ�
		File file = new File(tempDir.getPath() + "/"
				+ DateUtils.getTime() + ".xls");
		file.setWritable(true);
		return file;
	}
	
	private File createExcelFile(List<HashMap<String, String>> configList) {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// ���������ļ�
		File file = new File(tempDir.getPath() + "/"
				+ DateUtils.getTime() + ".xlsx");
		file.setWritable(true);
		int start_cell = 0;
		SXSSFWorkbook  workbook = null;
		OutputStream os = null;
		try {
			workbook = new SXSSFWorkbook(RAM_MAX_SIZE);//�ڴ��б��� 5000 �����ݣ������ڴ����������д�� Ӳ��  
			CellStyle style = workbook.createCellStyle();
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// ��ֱ����
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ˮƽ����
			//style.setFillBackgroundColor(HSSFColor.GREEN.index);//���ñ���ɫ
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//����ǰ�������ʽ
			style.setFillForegroundColor(HSSFColor.GREEN.index);//ǰ�����ɫ
			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 11);//�����С
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// ������Ӧ�õ���ǰ����ʽ
			style.setFont(font);
			Sheet sheet = workbook.createSheet();// ���Ҫ�����ĵ���sheet
			Row row = sheet.createRow(start_row);
			for (HashMap<String, String> map : configList) {
				if (SELECT_ZT.equals(map.get("zt"))) {
					String title = map.get("zdmc");
					Cell cell = row.createCell(start_cell);// ����һ����Ԫ��
					sheet.setColumnWidth(start_cell, title.getBytes().length*2*200);
					cell.setCellStyle(style);// ������ʽ
					cell.setCellValue(title);// д�����
					start_cell++;
				}
			}
		
			os = new FileOutputStream(file);
			workbook.write(os);
			
		
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		return file;
	}


	private String createFileName(String type) {
		String fileName = null;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		fileName = tempDir.getPath() + "/"
				+ DateUtils.getTime() + "." + type;

		return fileName;
	}


	/**
	 * д��EXCEL
	 * 
	 * @param dataList
	 * @param configList
	 * @param ws
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IOException 
	 */
	private void writeExcel(List<HashMap<String, String>> dataList,
			List<HashMap<String, String>> configList, WritableSheet ws)
			throws RowsExceededException, WriteException, SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {

		// ���ñ�ͷ����
		WritableCellFormat wcf = getCellFormat();
		// ��ͷд��
		int cellNum = 0;// ����ĵ�cellNum��
		for (HashMap<String, String> map : configList) {
			if (SELECT_ZT.equals(map.get("zt"))) {
				String title = map.get("zdmc");
				ws.addCell(new Label(cellNum, 0, title, wcf));
				ws.setColumnView(cellNum, title.getBytes().length * 2);
				cellNum++;
			}
		}

		// ���ݼ�д��
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> dataMap = (HashMap<String, String>) dataList
						.get(i);
				int j = 0;

				for (HashMap<String, String> map : configList) {
					if (SELECT_ZT.equals(map.get("zt"))) {
						String zd = map.get("zd");
						ws.addCell(new Label(j, i + 1, dataMap.get(zd)));
						j++;
					}
				}
			}

		}
	}
	
	private void writeExcelPoI(ExportModel model,QueryDataService queryDataService,List<HashMap<String,String>> configList, File file) {

		SXSSFWorkbook workbook = null;
		FileInputStream in = null;
		XSSFWorkbook xssbook = null;
		FileOutputStream stream=null;
		try{
		in = new FileInputStream(file.getPath());
		// ����excel������
		xssbook = new XSSFWorkbook(in);
		workbook = new SXSSFWorkbook(xssbook,RAM_MAX_SIZE);
		workbook.setCompressTempFiles(true);
		SXSSFSheet  sheet = (SXSSFSheet) workbook.getSheetAt(0);// ��ȡָ����sheet
		int rowConut = Integer.parseInt(model.getRowConut());
		//�ָ�List
			int part=rowConut/RAM_MAX_SIZE;
			int currentPage=0;
			for (int i = 0; i <part; i++) {
				currentPage++;
				OptionUtil.page=currentPage;
				List<HashMap<String, String>> dataList =queryDataService.queryDataList();
				writeData(dataList, sheet, configList);
			}
			if(rowConut!=part*RAM_MAX_SIZE){
				OptionUtil.page=currentPage+1;
				List<HashMap<String, String>> dataList =queryDataService.queryDataList();
				writeData(dataList, sheet, configList);
			}

		stream = new FileOutputStream(file);
		workbook.write(stream);
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//����д��sheet
	private void writeData(List<HashMap<String,String>> dataList, SXSSFSheet sheet,List<HashMap<String,String>> configList) throws IOException{
		// ���ݼ�д��
		if (dataList != null && dataList.size() > 0) {
			
			for (int i = 0; i < dataList.size(); i++) {
				Row dataRow = sheet.createRow(start_row+1); 
				HashMap<String, String> dataMap = (HashMap<String, String>) dataList
						.get(i);
				int j = 0;
				
				for (HashMap<String, String> map : configList) {
					if (SELECT_ZT.equals(map.get("zt"))) {
						String zd = map.get("zd");
						dataRow.createCell(j).setCellValue(dataMap.get(zd));
						//ws.addCell(new Label(j, i + 1, dataMap.get(zd)));
						j++;
					}
				}
				if(i%RAM_MAX_SIZE==0){//���ݵ����ڴ�������ֵ,ȫ��д��Ӳ��
					sheet.flushRows();
				}
				start_row++;
			}
			
		}
	}
	// ���ñ�ͷ����
	private WritableCellFormat getCellFormat() throws WriteException {
		WritableCellFormat wcf = new WritableCellFormat();
		WritableFont wf = new WritableFont(WritableFont.ARIAL);
		wf.setBoldStyle(WritableFont.BOLD);
		wf.setPointSize(10);
		wcf.setFont(wf);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setBackground(Colour.GREEN);
		wcf.setWrap(false);
		return wcf;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.comm.export.service.IExportService#getExportFile(
	 * java.util.HashMap, java.util.List)
	 */

	public File getExportFile(Map<String, String> titleMap,
			List<HashMap<String, String>> dataList) throws Exception {

		if (titleMap == null || titleMap.isEmpty()) {
			return null;
		}

		File file = createFile();
		FileOutputStream stream = new FileOutputStream(file);
		// ����excel������
		WritableWorkbook wwb = Workbook.createWorkbook(stream);
		WritableSheet ws = wwb.createSheet(SHEET_NAME, 0);
		// ���ñ�ͷ����
		WritableCellFormat wcf = getCellFormat();

		int cellNum = 0;// ����ĵ�cellNum��

		// ��ͷд��
		for (Entry<String, String> entry : titleMap.entrySet()) {
			String title = (String) entry.getValue();
			ws.addCell(new Label(cellNum, 0, title, wcf));
			ws.setColumnView(cellNum, title.getBytes().length * 2);
			cellNum++;
		}

		// ���ݼ�д��
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> dataMap = (HashMap<String, String>) dataList
						.get(i);
				int j = 0;

				for (Entry<String, String> entry : titleMap.entrySet()) {
					String key = (String) entry.getKey();
					ws.addCell(new Label(j, i + 1, dataMap.get(key)));
					j++;
				}
			}

		}

		wwb.write();
		wwb.close();

		return file;
	}

	/*
	 * ȡ�ַ������ȣ������������ַ�
	 */
	private int getStrLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* ��ȡ�ֶ�ֵ�ĳ��ȣ�����������ַ�����ÿ�������ַ�����Ϊ2������Ϊ1 */
		if(value != null){
			for (int i = 0; i < value.length(); i++) {
				/* ��ȡһ���ַ� */
				String temp = value.substring(i, i + 1);
				/* �ж��Ƿ�Ϊ�����ַ� */
				if (temp.matches(chinese)) {
					/* �����ַ�����Ϊ2 */
					valueLength += 2;
				} else {
					/* �����ַ�����Ϊ1 */
					valueLength += 1;
				}
			}			
		}
		return valueLength;
	}
	

}

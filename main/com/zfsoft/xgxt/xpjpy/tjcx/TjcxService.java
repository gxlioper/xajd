/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-4 ����06:12:54 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import jxl.Workbook;
import jxl.format.*;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.utils.date.MoneyFormat;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-4 ����06:12:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxService extends SuperServiceImpl<TjcxModel, TjcxDao> implements
	Constants {

	private final String sjbj = "ʮ�Ѱ༶";
	private final String xjbjt = "�Ƚ��༯��";
	private final String shxsbb = "����ѧ�����";
	private final String shxs = "����ѧ��";
	private final String yxxsgb = "����ѧ���ɲ�";
	private final String yxshgzz = "������Ṥ����";

	private TjcxDao dao = new TjcxDao();
	public TjcxService(){
		super.setDao(dao);
	}
	
	/**
	 * @throws WriteException 
	 * @throws Exception 
	 * 
	 * @���� �����������б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-2 ����01:55:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * void �������� 
	 * @throws
	 */
	public void exportHjmdtj(HjmdExportModel model , OutputStream os, User user) throws Exception{
		//ѧ��
		String xn = model.getXn();
		//ѧ��
		String xq = model.getXq();
		//ѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//��Ŀ����
		String[] xmlx = model.getXmlx();
		//ѧԺ����
		String[] xydm = model.getXydm();
		//��Ŀ����
		String[] xmxz = model.getXmxz();
		//��Ŀ����
		String[] xmmc = model.getXmmc();
		/*
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}*/
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("������", 0);
		
		// ѧԺ�б�
		List<String[]> xymcList = dao.getXyList(xn, xq, xmlx, xydm, xmxz, xmmc,user);
		// ����ѧ�������
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ������
		List<String[]> xmList = dao.getHjmdList(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//��Ŀ����
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		//��ѧ��������ʽ
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		//�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		if("1".equals(pjzq)){ 	
		// �����һ�еı�ͷ
			ws.addCell(new Label(0, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", xqmc, "ѧ��", lxmc, "���������"), jxjFormat));
		}else {
			ws.addCell(new Label(0, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "���������"), jxjFormat));
		}
		if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)){
			ws.mergeCells(0, 0,11, 0);
		}else{
			ws.mergeCells(0, 0,10, 0);
		}
		
		
		//xmList ������ 
		//jxjdmList ��Ŀ������
		//xymcList ѧԺ������
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				//����ѧ������д�뵽EXCEL��
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (��" +jxjdmArr[1] +"��)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//���� ��ʼ ����ڶ�����ѧԺ����д��EXCEL��---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						for (String[] xmArr : xmList) {//���� ��ʼ ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 3
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[2] + "��)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//�����������ѧԺ����Ļ��������
						writeHjmdhzExcel(ws, properties, xmByXyList,0);//��������д�뵽EXCEL��
					}
				}//���� ����---------------------------------------------------------
			}//һ������ʼ---------------------------------------------------------
		}
		
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}

	/**
	 * @����:���ݴ����ȡѧԺ���ƣ��Ͼ��ߵ�ְҵ����ѧУ����������-ͳ�ƹ���-������ͳ�ƣ���ѧ����ر�����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/16 9:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [search_tj_xy]
	 * @return: java.lang.String[]
	 */
	public String[] getXymcByDms(String[] xydms) throws SQLException {
		return dao.getXymcByDms(xydms);
	}


	private class JxjExportProperties{
		 int x_axis = 0;//X����
		 int row = 1;//�б�
		 int rowCellCount = 1;//ÿ�е�cell��������
		 int[] maxLength = {25,25,25,25,25,25,25};//Ĭ�ϵ��п�
		public int getX_axis() {
			return x_axis;
		}
		@SuppressWarnings("unused")
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	private void writeHjmdhzExcel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// ��ʼд������
		properties.x_axis = rowCellCount;// ��ʼ�½�ѧ�������
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				String cellContent = xmList.get(index - 1);
				if("12865".equals(Base.xxdm)){
					if (properties.rowCellCount == 11) {
						properties.row++;// ��10���ͻ���
						properties.x_axis = 0;
						properties.rowCellCount = 1;// ����ÿ�м���
					}
				}else{
					if (properties.rowCellCount == 8) {
						properties.row++;// ��10���ͻ���
						properties.x_axis = 0;
						properties.rowCellCount = 1;// ����ÿ�м���
					}
				}
				
				// �ж���������,Ȼ����ݳ��ȿ��ƺϲ���Ԫ�񣬼��������ȵ�����½��л���
//				if (cellContent.length() > 3) {
//					int xmLength = cellContent.length() / 3
//							+ (cellContent.length() % 3 == 0 ? 0 : 1);
//					int pre_x_axis = properties.x_axis;// �ϲ�ǰ��x��ֵ
//					if (pre_x_axis + xmLength > 10) {
//						properties.row++;// ��������������
//						properties.x_axis = rowCellCount;
//						properties.rowCellCount = 1;// ����ÿ�м���
//						pre_x_axis = properties.x_axis;
//					}
//					Label cell = new Label(++properties.x_axis, properties.row,
//							cellContent);
//					ws.addCell(cell);
//					ws.setColumnView(properties.x_axis, 7);
//					pre_x_axis++;
//					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis
//							+ xmLength - 1, properties.row);
//					properties.x_axis = pre_x_axis + xmLength - 1;
//					properties.rowCellCount++;
//				} else {
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
//				}
			}
		}
		properties.row += 1;// �½�ѧ����
	}
	
	/**
	 * @���� �����������б��Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-6 ����05:24:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * void �������� 
	 * @throws
	 */
	public void exportHjmdtj_11458(HjmdExportModel model , OutputStream os) throws Exception{
		// ����,12,LEFT
		WritableCellFormat s12LeftFormat = new WritableCellFormat();
		WritableFont s12LeftFont = new WritableFont(WritableFont.createFont("����"),12);
		s12LeftFormat.setFont(s12LeftFont);
		s12LeftFormat.setAlignment(Alignment.LEFT);
		// ����,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		// ����,12,RIGHT
		WritableCellFormat s12RightFormat = new WritableCellFormat();
		WritableFont s12RightFont = new WritableFont(WritableFont.createFont("����"),12);
		s12RightFormat.setFont(s12RightFont);
		s12RightFormat.setAlignment(Alignment.RIGHT);
		// ����,12,BOLD,LEFT
		WritableCellFormat s12LeftBoldFormat = new WritableCellFormat();
		WritableFont s12LeftBoldFont = new WritableFont(WritableFont.createFont("����"),12);
		s12LeftBoldFont.setBoldStyle(WritableFont.BOLD);
		s12LeftBoldFormat.setFont(s12LeftBoldFont);
		s12LeftBoldFormat.setAlignment(Alignment.LEFT);
		
		// �Ƚ������������
		String jtpjTj = "�Ƚ�����";
		//ѧ��
		String xn = model.getXn();
		//��Ŀ����
		String[] xmxz = model.getXmxz();
		//���л�ѧԺ
		List<HashMap<String,String>> xyAllList = dao.getAllXyList_11458(xn, xmxz);
		//��������excel�Ļ�ѧԺ
		List<HashMap<String,String>> xyLoopList = new ArrayList<HashMap<String,String>>();
		//ѧԺ����
		String[] xydmSearchArr = model.getXydm();
		if(xydmSearchArr == null){
			xyLoopList = xyAllList;
		}else{
			for (int i = 0; i < xydmSearchArr.length; i++) {
				for (int j = 0; j < xyAllList.size(); j++) {
					HashMap<String,String> temp = xyAllList.get(j);
					if(temp.get("xydm").equals(xydmSearchArr[i])){
						xyLoopList.add(temp);
					}
				}
			}
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		for (int m = 0; m < xyLoopList.size(); m++) {
			HashMap<String,String> xyMap = xyLoopList.get(m);
			String[] xydm = new String[] { xyMap.get("xydm") };
		
			WritableSheet ws = wwb.createSheet(xyMap.get("xymc") + "��ʾ����", m);
			
			// ѧԺ�б�
			List<String[]> xymcList = dao.getXyList_11458(xn, xmxz, xydm);
			// ������
			List<String[]> xmList = dao.getHjmdList_11458(xn, xmxz, xydm);
			// ��������ѧԺ����
			List<String[]> jtpjList = dao.getJtpjList_11458(jtpjTj, xn, xydm);
			
			// �м�����
			int midColumns  = xymcList.size() + 1;
			// ������
			int columns  = midColumns * 2;
			// ���õ�һ�п��
			ws.setColumnView(0, 17); 
			// ��1��
			WritableCellFormat s18BoldCenterFormat = new WritableCellFormat();
			WritableFont s18BoldCenterFont = new WritableFont(WritableFont.createFont("����"),18);
			s18BoldCenterFont.setBoldStyle(WritableFont.BOLD);
			s18BoldCenterFormat.setFont(s18BoldCenterFont);
			s18BoldCenterFormat.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0, 0, xn + "ѧ���Ƚ����塢���˹�ʾ����", s18BoldCenterFormat));
			ws.mergeCells(0, 0, (columns - 1), 0);
			// ��2��
			WritableCellFormat r2Format = new WritableCellFormat();
			WritableFont r2Font = new WritableFont(WritableFont.createFont("����"),18);
			r2Font.setBoldStyle(WritableFont.BOLD);
			r2Format.setFont(r2Font);
			r2Format.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0, 1, "", r2Format));
			// ��3��
			ws.addCell(new Label(0, 2, "ѧ    Ժ��", s12LeftFormat));
			ws.addCell(new Label(1, 2, xyMap.get("xymc"), s12LeftFormat));
			ws.mergeCells(1, 2, (columns - 1), 2);
			// ��4��
			ws.addCell(new Label(0, 3, "����ѧ�꣺", s12LeftFormat));
			ws.addCell(new Label(1, 3, xn + "ѧ��", s12LeftFormat));
			ws.mergeCells(1, 3, (columns - 1), 3);
			// ��5��
			ws.addCell(new Label(0, 4, "", s12LeftFormat));
			// ��6��
			ws.addCell(new Label(0, 5, "����ѧԺ������ǩ�֣�", s12LeftFormat));
			ws.addCell(new Label(midColumns, 5, "����ѧԺ���£�", s12LeftFormat));
			// ��7��
			ws.addCell(new Label(0, 6, "", s12LeftFormat));
			// ��8��
			ws.addCell(new Label(0, 7, "", s12LeftFormat));
			// ��9��
			// ����,12,CENTRE,�߿�
			WritableCellFormat s12CentreBorderFormat = new WritableCellFormat();
			WritableFont s12CentreBorderFont = new WritableFont(WritableFont.createFont("����"),12);
			s12CentreBorderFormat.setFont(s12CentreBorderFont);
			s12CentreBorderFormat.setAlignment(Alignment.CENTRE);
			s12CentreBorderFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			ws.addCell(new Label(0, 8, xmxz[0], s12CentreBorderFormat));
			ws.addCell(new Label(1, 8, jtpjTj, s12CentreBorderFormat));
			for (int i = 0; i < xymcList.size(); i++) {
				int temp =  2 + i * 2;
				ws.addCell(new Label(temp, 8, xymcList.get(i)[0], s12CentreBorderFormat));
				ws.mergeCells(temp, 8, (temp + 1), 8);
			}
			// ��10��
			ws.addCell(new Label(0, 9, "����", s12CentreBorderFormat));
			ws.addCell(new Label(1, 9, String.valueOf(jtpjList.size()), s12CentreBorderFormat));
			for (int i = 0; i < xymcList.size(); i++) {
				int temp =  2 + i * 2;
				ws.addCell(new Label(temp, 9, xymcList.get(i)[1], s12CentreBorderFormat));
				ws.mergeCells(temp, 9, (temp + 1), 9);
			}
			// =============== �Ƚ����� begin=======================
			// ����
			ws.addCell(new Label(0, 11, jtpjTj + "��", s12LeftBoldFormat));
			// ����
			int rowTemp = 12;
			int colTemp = 0;
			for (int i = 0; i < jtpjList.size(); i++) {
				ws.addCell(new Label(colTemp, rowTemp, jtpjList.get(i)[0], s12LeftFormat));
				colTemp++;
				if(colTemp == columns){
					rowTemp++;
					colTemp = 0;
				}
			}
			// =============== �Ƚ����� end=======================
			rowTemp += 2; // ��һ��
			// =============== ��Ŀ���� begin=======================
			boolean flag = false; // �жϣ��պõ��ڻ����ٽ�ֵ��������һ��û������ʱ
			for (int i = 0; i < xymcList.size(); i++) {
				// ����
				String xmmc = xymcList.get(i)[0];
				ws.addCell(new Label(0, rowTemp, xmmc + "��", s12LeftBoldFormat));
				// ����
				colTemp = 0;
				rowTemp++; // ��һ��
				for (int j = 0; j < xmList.size(); j++) {
					String xmmcXs = xmList.get(j)[1];
					String xmXs = xmList.get(j)[2];
					String bjmcXs = xmList.get(j)[3];
					if(xmmc.equals(xmmcXs)){
						ws.addCell(new Label(colTemp, rowTemp, bjmcXs, s12RightFormat));
						colTemp++;
						ws.addCell(new Label(colTemp, rowTemp, xmXs, s12CentreFormat));
						colTemp++;
						if(colTemp == columns){
							rowTemp++;
							colTemp = 0;
							flag = true;
						}else{
							flag = false;
						}
					}
				}
				if(flag){
					rowTemp += 1; // ��һ��
				}else{
					rowTemp += 2; // ��һ��
				}
			}
			// =============== ��Ŀ���� end=======================
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ��ѧ���������һ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-10 ����04:13:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getJxjmefpList(TjcxModel model, User user) throws Exception{
		
		//��ѯ��Ҫͳ�Ƶ�������Ŀ
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		CsszService  csszService = new CsszService();
		String rsjsfs = csszService.getCsz("rsjsfs");
		return dao.getJxjmefpList(model, user, pjxmList,rsjsfs);
	}

	
	/** 
	 * @����:�㽭��ѧ��ã���ͳ��������Ŀ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-10 ����04:39:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPjxmList(TjcxModel model) {
		return dao.getPjxmList(model);
	}
	
	/**
	 * �㽭��ѧ����֤���ӡ����ҳ��
	 */
	public List<HashMap<String, String>> viewZsdy(TjcxModel t, User user) throws Exception {
		return dao.viewZsdy(t, user);
	}
	/**
	 * �㽭��ѧ����֤���ӡ������ҳ��
	 */
	public List<HashMap<String, String>> viewZsdyAll(TjcxModel t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.viewZsdy(t, user);
	}
	/**
	 * ���ص���ģ��
	 */
	public void exportZsNew(TjcxModel t, User user, HttpServletResponse response, String filePath) throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(1);
		List<HashMap<String, String>> dataList = viewZsdyAll(t, user);
		
		//�޸Ĵ�ӡģ����ʾ����
		HSSFSheet firstSheet = workbook.getSheetAt(0);
		//replace ѧ������
		HSSFRow row4 = firstSheet.getRow(3);// ��ù������ĵ�4��
		HSSFCell cell44 = row4.getCell(3);// ��õ�4�еĵ�4����Ԫ��
		cell44.setCellValue(dataList.get(0).get("xm"));// ����Ԫ��ֵ
		//replace ѧ��
		HSSFCell cell46 = row4.getCell(5);// ��õ�4�еĵ�6����Ԫ��
		cell46.setCellValue(dataList.get(0).get("xh"));// ����Ԫ��ֵ
		//replace ѧ��
		HSSFRow row5 = firstSheet.getRow(4);// ��ù������ĵ�5��
		HSSFCell cell53 = row5.getCell(2);// ��õ�5�еĵ�3����Ԫ��
		cell53.setCellValue(" �� "+dataList.get(0).get("xn")+" ѧ �� �� �� �� �� �㣬�� �� ��");// ����Ԫ��ֵ
		//replace ����
		HSSFRow row6 = firstSheet.getRow(5);// ��ù������ĵ�6��
		HSSFCell cell64 = row6.getCell(3);// ��õ�6�еĵ�4����Ԫ��
		cell64.setCellValue(dataList.get(0).get("xmmc"));// ����Ԫ��ֵ
		//replace ����ƴ��
		HSSFRow row9 = firstSheet.getRow(8);// ��ù������ĵ�9��
		HSSFCell cell94 = row9.getCell(3);// ��õ�9�еĵ�4����Ԫ��
		cell94.setCellValue(dataList.get(0).get("xmpy"));// ����Ԫ��ֵ
		//replace ����Ӣ������
		HSSFRow row10 = firstSheet.getRow(9);// ��ù������ĵ�10��
		HSSFCell cell104 = row10.getCell(3);// ��õ�10�еĵ�4����Ԫ��
		cell104.setCellValue(dataList.get(0).get("xmywmc"));// ����Ԫ��ֵ
		//replace ѧ��
		HSSFRow row11 = firstSheet.getRow(10);// ��ù������ĵ�11��
		HSSFCell cell114 = row11.getCell(3);// ��õ�11�еĵ�4����Ԫ��
		cell114.setCellValue("   Awarded on   "+dataList.get(0).get("xn"));// ����Ԫ��ֵ
		//replace ��ǰʱ��
		HSSFRow row19 = firstSheet.getRow(18);// ��ù������ĵ�19��
		HSSFCell cell197 = row19.getCell(7);// ��õ�19�еĵ�7����Ԫ��	
		cell197.setCellValue(DateUtils.getYear()+"/"+DateUtils.getMonth());// ����Ԫ��ֵ

		
		// ����д��
		int row = 1;
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String, String> dataMap = dataList.get(m);
			HSSFRow hSSFRow = sheet.createRow(row);
			hSSFRow.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xm"));
			hSSFRow.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xh"));
			hSSFRow.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmmc"));
			hSSFRow.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmpy"));
			hSSFRow.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmywmc"));
			row++;
		}
		OutputStream os = response.getOutputStream();
		workbook.write(os);  
		os.flush();
		os.close();
	}

	/** 
	 * @����:ͳ�Ƶ������㽭��ѧ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-11 ����03:44:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getTjjgFile(TjcxModel model, User user) throws Exception{
		
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		CsszService  csszService = new CsszService();
		String rsjsfs = csszService.getCsz("rsjsfs");
		
		//����������ͷ
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("bmmc", "��������");
		
		for (int i = 0 , j = pjxmList.size() ; i < j ; i++){
			map.put("jx"+i, pjxmList.get(i).get("xmmc"));
		}
		
		map.put("jje", "�����");
		map.put("bmtzje", "�������");
		map.put("zrs", "ѧ������");
		map.put("ytjrs", "�۲����ύ");
		
		//��������
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getJxjmefpList(model, user, pjxmList,rsjsfs);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-16 ����02:29:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportHjmdtj_10335(HjmdExportModel model , OutputStream os, User user) throws Exception{
		//ѧ��
		String xn = model.getXn();
		//ѧ��
		String xq = model.getXq();
		//ѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//��Ŀ����
		String[] xmlx = model.getXmlx();
		//ѧԺ����
		String[] xydm = model.getXydm();
		//��Ŀ����
		String[] xmxz = model.getXmxz();
		//��Ŀ����
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(titleXmlx.toString() + "������", 0);
		ws.setColumnView(0, 2);
		ws.setRowView(0, 500);
		ws.setRowView(1, 500);
		ws.setRowView(2, 500);
		
		// ѧԺ�б�
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ����ѧ�������
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ������
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//��Ŀ��������
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		//��ѧ��������ʽ
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		
		//����+ѧԺ��ʽ
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		//�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		if("1".equals(pjzq)){ 	
		// �����һ�еı�ͷ
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", xqmc, "ѧ��", lxmc, "��������"), titlFormat));
		}else {
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"), titlFormat));
		}
		ws.mergeCells(1, 0,3, 1);
		
		//xmList ������ 
		//jxjdmList ��Ŀ������
		//xymcList ѧԺ������
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //�㽭��ѧ��ͷ������
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				//����ѧ������д�뵽EXCEL��
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc);
				resultCell.setCellFormat(jxxyFormat);
				ws.setRowView(properties.row, 500);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 3, properties.row);
				
				//���� ��ʼ ����ڶ�����ѧԺ����д��EXCEL��---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//���� ��ʼ ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[3]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++properties.row;
						xy+="("+xymcArr[2]+"��)";
						System.out.println("ѧԺ��"+xy);
						Label xymcCell = new Label(1,properties.row,xy );
						xymcCell.setCellFormat(jxxyFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 3, properties.row);
						//�����������ѧԺ����Ļ��������
						writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//��������д�뵽EXCEL��
					}
				}//���� ����---------------------------------------------------------
			}//һ������ʼ---------------------------------------------------------
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	/**
	 * XXϵ��ѧ����ܱ�����������óְҵ����ѧԺ��
	 */
	public void exportHjmdtj_12072(HjmdExportModel model , OutputStream os, User user) throws Exception{
		// ����,14,CENTRE,�߿�
		WritableCellFormat s14CentreFormat = new WritableCellFormat();
		WritableFont s14CentreFont = new WritableFont(WritableFont.createFont("����"),14);
		s14CentreFormat.setFont(s14CentreFont);
		s14CentreFormat.setAlignment(Alignment.CENTRE);
		s14CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		// ����,12,CENTRE,�߿�
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		s12CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		// ��߿�
		WritableCellFormat leftBorderFormat = new WritableCellFormat();
		leftBorderFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		// ����_GB2312,12,CENTRE,�߿�
		WritableCellFormat fs12CentreFormat = new WritableCellFormat();
		WritableFont fs12CentreFont = new WritableFont(WritableFont.createFont("����"),12);
		fs12CentreFormat.setFont(fs12CentreFont);
		fs12CentreFormat.setAlignment(Alignment.CENTRE);
		fs12CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		//ѧ��
		String xn = model.getXn();
		//ѧ��
		String xq = model.getXq();
		//ѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = "";
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//��Ŀ����
		String[] xmlx = model.getXmlx();
		//ѧԺ����
		String[] xydm = model.getXydm();
		//��Ŀ����
		String[] xmxz = model.getXmxz();
		//��Ŀ����
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		String xnTemp = xn.replace("-", "/");
		WritableSheet ws = wwb.createSheet("��ѧ����ܱ�����", 0);
		// ����Ŀ 
		List<HashMap<String,String>> hjxmList = dao.getHjxmList_12072(xn, xq, xmlx, xydm, xmxz, xmmc,user);
		List<HashMap<String,String>> bjList = dao.getBjList_12072(xn, xq, xmlx, xydm, xmxz, xmmc, hjxmList, user);
		int col = hjxmList.size() * 2 + 2;
		// ��1��
		ws.addCell(new Label(0, 0, "ϵ�𣺣����£�                             "+xnTemp+"ѧ���"+xqmc, s14CentreFormat));
		ws.mergeCells(0, 0, col, 0);
		ws.setRowView(0, 450);
		// ��2��
		ws.addCell(new Label(0, 1, "���", s12CentreFormat));
		ws.mergeCells(0, 1, 0, 2);
		ws.setColumnView(0, 8);
		ws.addCell(new Label(1, 1, "�༶", s12CentreFormat));
		ws.mergeCells(1, 1, 1, 2);
		ws.setColumnView(1, 17);
		for (int i = 0; i < hjxmList.size(); i++) {
			int colTemp = i * 2 + 2;
			HashMap<String, String> hjxmMap = hjxmList.get(i);
			ws.addCell(new Label(colTemp, 1, hjxmMap.get("xmmc"), s12CentreFormat));
			ws.mergeCells(colTemp, 1, colTemp + 1, 1);
			ws.addCell(new Label(colTemp, 2, "����", s12CentreFormat));
			ws.setColumnView(colTemp, 9);
			ws.addCell(new Label(colTemp + 1, 2, "���", s12CentreFormat));
			ws.setColumnView(colTemp + 1, 18);
		}
		ws.addCell(new Label(col, 1, "�༶С��", s12CentreFormat));
		ws.setColumnView(col, 28);
		ws.mergeCells(col, 1, 1, 2);
		ws.setRowView(1, 390);
		ws.setRowView(2, 390);
		// ��߿�
		ws.addCell(new Label(col+1, 1, " ", leftBorderFormat));
		ws.addCell(new Label(col+1, 2, " ", leftBorderFormat));
		// ��4��
		int row = 2;
		BigDecimal bjSumJe = new BigDecimal("0");
		BigDecimal sumJe = new BigDecimal("0");
		for (int j = 0; j < bjList.size(); j++) {
			bjSumJe = new BigDecimal("0");
			HashMap<String, String> bjMap = bjList.get(j);
			String bjmc = bjMap.get("bjmc");
			row++;
			ws.setRowView(row, 390);
			ws.addCell(new Label(0, row, String.valueOf(j + 1), s12CentreFormat));
			ws.addCell(new Label(1, row, bjmc, fs12CentreFormat));
			for (int i = 0; i < hjxmList.size(); i++) {
				int colTemp = i * 2 + 2;
				String bjxmrs = bjMap.get("bjxmrs"+i);
				if(bjxmrs == null){
					bjxmrs = "0";
				}
				String bjxmje = bjMap.get("bjxmje"+i);
				if(bjxmje == null){
					bjxmje = "0";
				}
				bjSumJe = bjSumJe.add(new BigDecimal(bjxmje));
				ws.addCell(new Label(colTemp, row, bjxmrs, fs12CentreFormat));
				ws.addCell(new Label(colTemp + 1, row, new BigDecimal(bjxmje).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), fs12CentreFormat));
			}
			ws.addCell(new Label(col, row, bjSumJe.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), s12CentreFormat));
			// ��߿�
			ws.addCell(new Label(col+1, row, " ", leftBorderFormat));
			sumJe = sumJe.add(bjSumJe);
		}
		
		HashMap<String,String> bjSum = dao.getBjSum_12072(xn, xq, xmlx, xydm, xmxz, xmmc, hjxmList, user);
		row++;
		ws.setRowView(row, 390);
		ws.addCell(new Label(0, row, "�ϼ�", s12CentreFormat));
		ws.mergeCells(0, row, 1, row);
		for (int i = 0; i < hjxmList.size(); i++) {
			int colTemp = i * 2 + 2;
			String bjxmrssum = bjSum.get("bjxmrssum"+i);
			if(bjxmrssum == null){
				bjxmrssum = "0";
			}
			String bjxmjesum = bjSum.get("bjxmjesum"+i);
			if(bjxmjesum == null){
				bjxmjesum = "0";
			}
			ws.addCell(new Label(colTemp, row, bjxmrssum, fs12CentreFormat));
			ws.addCell(new Label(colTemp + 1, row, new BigDecimal(bjxmjesum).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), fs12CentreFormat));
		}
		ws.addCell(new Label(col, row, sumJe.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString(), s12CentreFormat));
		// ��߿�
		ws.addCell(new Label(col+1, row, " ", leftBorderFormat));
		row++;
		ws.setRowView(row, 390);
		ws.addCell(new Label(0, row, "�ܼƣ������ "+MoneyFormat.format(sumJe.toString()), s14CentreFormat));
		ws.mergeCells(0, row, col, row);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ���Ի�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-17 ����01:32:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ws
	 * @param properties
	 * @param xmList
	 * @param rowCellCount
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	private void writeHjmdhzExcel_10335(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,List<String> xmXhList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// ��ʼд������
		properties.x_axis = rowCellCount;// ��ʼ�½�ѧ�������
		//������ʽ
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				int nameLength = 4; //�ϲ���Ԫ���ַ�������
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 4) {
					properties.row++;// ��3���ͻ���
					properties.x_axis = 0;
					properties.rowCellCount = 1;// ����ÿ�м���
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,
						cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 30);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		properties.row += 1;// �½�ѧ����
	}
	
	
	/**
	 * 
	 * @����:���Ż��ܵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-29 ����03:15:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File exportFfhz(TjcxModel model, User user) throws Exception{
		
		//ִ�����洢����
		dao.computeFfhz(model.getXn(), Base.currXn);
		
		//����������ͷ
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("xymc", "Ժϵ");
		map.put("xh", "ѧ��");
		map.put("xm", "����");
		map.put("bcffje", "���η��Ž��");
		map.put("jjze", "�ܽ��");
		map.put("bz", "��ע");
		
		//��������
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getFfhzList(model, user);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	public void exportGjjxjhz(TjcxModel model,OutputStream os, User user) throws Exception {
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(model.getXn());
		title.append("ѧ����ҽ�ѧ�𷢷Ż���");
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ܱ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		List<HashMap<String, String>> gjjxjList = dao.getGjjxjList(model);
		// ������
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.mergeCells(0, 0, 3, 1);
		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "ѧ��", wcf2));
		ws.addCell(new Label(2, 2, "����", wcf2));
		ws.addCell(new Label(3, 2, "���Ž��(Ԫ)", wcf2));
		if (gjjxjList != null && gjjxjList.size() > 0) {

			for (int i = 0; i < gjjxjList.size(); i++) {

				HashMap<String, String> map = gjjxjList.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 30);
				ws.setColumnView(2, 30);
				ws.setColumnView(3, 30);

				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xmje"), wcf2));
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @throws Exception  
	 * @����:������ݵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����05:35:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param lxmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getExport(TjcxModel model, User user,
			String lxmc) throws Exception {
		return dao.getExport(model,user,lxmc);
	}
	
	
	/**
	 * 
	 * @����:������ݵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����07:50:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File exportData(TjcxModel model, User user, String lxmc) throws Exception{
		
		//����������ͷ
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("rn", "���");
		map.put("cpxymc", "ѧԺ");
		map.put("xm", "����");
		map.put("xh", "ѧ��");
		map.put("xmmc", "����");
		map.put("xmpy", "����ƴ��");
		map.put("xmywmc", "����Ӣ������");
		
		//��������
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String, String>> dataList = getExport(model,user,lxmc);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	/**
	 * �Ϻ����ѧԺ���Ի���ѯ
	 */
	public List<HashMap<String, String>> getPageListShTyxy(TjcxModel t, User user)
	throws Exception{
		return dao.getPageListShTyxy(t, user);
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-5-9 ����04:28:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportHjmdtj_10335_njdc(HjmdExportModel model , OutputStream os, User user) throws Exception{
		//ѧ��
		String xn = model.getXn();
		//ѧ��
		String xq = model.getXq();
		//ѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//��Ŀ����
		String[] xmlx = model.getXmlx();
		//ѧԺ����
		String[] xydm = model.getXydm();
		//��Ŀ����
		String[] xmxz = model.getXmxz();
		//��Ŀ����
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(titleXmlx.toString() + "������", 0);
		ws.setColumnView(0, 2);
		ws.setRowView(0, 500);
		ws.setRowView(1, 500);
		ws.setRowView(2, 500);
		
		// ѧԺ�б�
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ����ѧ�������
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ������
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//��Ŀ��������
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		//��ѧ��������ʽ
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		
		//����+ѧԺ��ʽ
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		//�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		if("1".equals(pjzq)){ 	
		// �����һ�еı�ͷ
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", xqmc, "ѧ��", lxmc, "��������"), titlFormat));
		}else {
			ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"), titlFormat));
		}
		ws.mergeCells(1, 0,9, 1);
		
		//xmList ������ 
		//jxjdmList ��Ŀ������
		//xymcList ѧԺ������
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //�㽭��ѧ��ͷ������
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				//����ѧ������д�뵽EXCEL��
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc);
				resultCell.setCellFormat(jxxyFormat);
				ws.setRowView(properties.row, 500);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 9, properties.row);
				
				//���� ��ʼ ����ڶ�����ѧԺ����д��EXCEL��---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//���� ��ʼ ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[4]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++properties.row;
						xy+="("+xymcArr[2]+"��)";
						System.out.println("ѧԺ��"+xy);
						Label xymcCell = new Label(1,properties.row,xy );
						xymcCell.setCellFormat(jxxyFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 9, properties.row);
						//�����������ѧԺ����Ļ��������
						writeHjmdhzExcel_10335_njdc(ws, properties, xmByXyList,xmXhByXyList,0);//��������д�뵽EXCEL��
					}
				}//���� ����---------------------------------------------------------
			}//һ������ʼ---------------------------------------------------------
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ���Ի��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-17 ����01:32:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ws
	 * @param properties
	 * @param xmList
	 * @param rowCellCount
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	private void writeHjmdhzExcel_10335_njdc(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,List<String> xmXhList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// ��ʼд������
		properties.x_axis = rowCellCount;// ��ʼ�½�ѧ�������
		//������ʽ
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("����_GB2312"),13);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
 			for (int index = 1; index < xmList.size() + 1; index++) {
				int nameLength = 3; //�ϲ���Ԫ���ַ�������
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 10) {
					properties.row++;// ��9���ͻ���
					properties.x_axis = 0;
					properties.rowCellCount = 1;// ����ÿ�м���
				}
				
				//�����ֵ���������sql���д�������������
				if(strLength>2){
					xmContent=xmContent.replace(" ", "").substring(0, 1)+" "+xmContent.replace(" ", "").substring(1, 2);
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,
						cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 9);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		properties.row += 1;// �½�ѧ����
	}
	
	
    /**
     * 
     * @����: �������������word
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-7-14 ����01:50:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param response
     * @param model
     * @param user
     * @param path
     * @throws Exception
     * void �������� 
     * @throws
     */
	public void createWord(HttpServletResponse response,HjmdExportModel model,User user,String path) throws Exception{
		  Document document = new Document(PageSize.A4);
	        //����һ����д������document�������  
		  response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("fwmddc_zjdx.doc".getBytes(), "GBK") + "\"");
	        RtfWriter2.getInstance(document, response.getOutputStream());  
	        document.open();  
	        //������������  
	        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
	        //����������  
	        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
	        //����������  
	        Font contextFont = new Font(bfChinese,16,Font.NORMAL);  
	        Paragraph title = new Paragraph(); 
	      //ѧ��
			String xn = model.getXn();
			//ѧ��
			String xq = model.getXq();
			//ѧ������
			List<HashMap<String,String>> xqList = Base.getXqList();
			String xqmc = null;
			for (HashMap<String,String> map : xqList){
				
				if (map.get("xqdm").equals(model.getXq())){
					xqmc = map.get("xqmc");
					break;
				}
				
			}
			//��Ŀ����
			String[] xmlx = model.getXmlx();
			//ѧԺ����
			String[] xydm = model.getXydm();
			//��Ŀ����
			String[] xmxz = model.getXmxz();
			//��Ŀ����
			String[] xmmc = model.getXmmc();
			
			// ѧԺ�б�
			List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
			// ����ѧ�������
			List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
			// ������
			List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
			
			//��Ŀ��������
			PjdmService pjdmService = new PjdmService();
			List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
			String lxmc = null;
			for (int i = 0; i < xmlxmc.size(); i++) {
				if(i==0){
					lxmc=xmlxmc.get(i);
				}else{
					lxmc=lxmc+","+xmlxmc.get(i);
				}
			}
			
			
			//�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�
			CsszService csszService = new CsszService();
			String pjzq = csszService.getCsz("pjzq");
			 Phrase phrase = null;
			if("1".equals(pjzq)){ 	
				// �����һ�еı�ͷ
				phrase = new Phrase(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", xqmc, "ѧ��", lxmc, "��������"),titleFont);
			}else {
				phrase = new Phrase(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"),titleFont);
			}
	         
	        title.add(phrase);
	        //���ñ����ʽ���뷽ʽ  
	        title.setAlignment(Element.ALIGN_CENTER);
	        title.setSpacingAfter(20);
	        title.setIndentationLeft(5);
	        title.setIndentationRight(5);
	        document.add(title);  
	    	if (!xmList.isEmpty()) {
	    			Paragraph paraitra1 = null;
	    			int propertiesrow =3;
	    			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
	    				
	    				 
	    			
	    				String[] jxjdmArr = jxjdmList.get(i);
	    				String jxjmc = jxjdmArr[0];
	    				paraitra1 = new Paragraph(jxjmc,titleFont);
	    				paraitra1.setAlignment(Element.ALIGN_LEFT);
	    				document.add(paraitra1);
	    				
	    		      
	    				//���� ��ʼ ����ڶ�����ѧԺ����д��EXCEL��---------------------------------------------------------
	    				Paragraph paraitra2 = null;
	    				for (int index = 0; index < xymcList.size(); index++) {
	    					String[] xymcArr = xymcList.get(index);
	    					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
	    						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
	    						
	    						List<String> xmXhByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
	    						List<String> xmByXyList = new ArrayList<String>();
	    						for (String[] xmArr : xmList) {//���� ��ʼ ---------------------------------------------------------
	    							if (xmArr != null && xmArr.length >= 4
	    									&& xy.equalsIgnoreCase(xmArr[0])
	    									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
	    								xmByXyList.add(xmArr[2]);
	    								xmXhByXyList.add(xmArr[3]);
	    							}
	    						}
	    						if (xmByXyList == null || xmByXyList.size() <= 0) {
	    							continue;
	    						}
	    						//���ѧԺ����
	    						++propertiesrow;
	    						xy+="("+xymcArr[2]+"��)";
	    						paraitra2 = new Paragraph(xy,titleFont);
	    						paraitra2.setIndentationLeft(10);
	    						//paraitra2.setSpacingAfter(10);
	    						document.add(paraitra2);
//	    					    run2.setFontSize(32);
//	    				        run2.setFtcAscii(11);
//	    				        run2.setBold(true);
//	    						System.out.println("ѧԺ��"+xy);
//	    						Label xymcCell = new Label(1,properties.row,xy );
//	    						xymcCell.setCellFormat(jxxyFormat);
//	    						ws.addCell(xymcCell);
//	    						ws.mergeCells(1,properties.row, 3, properties.row);
	    						//�����������ѧԺ����Ļ��������
	    			//			writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//��������д�뵽EXCEL��
	    						int len = xmXhByXyList.size();
	    				//		int modlen = len % 3;
//	    						if(len >= 3){
	    							Paragraph para3 = null;
	    							Phrase phrase3 = null;
	    							//���������������ѭ��
	    							int count = 3;
	    							//�¼��б�־
//	    							boolean flag = true;
	    							//����ѭ������������Ϊһ��
	    							for (int j = 0; j < len; j=j+count) {
	    								para3 = new Paragraph();
	    								//����д�룬��������4���ַ��ĵ���Ϊһ�У�����ڶ������ֻ��ߵ��������ֳ���4���ַ�����д�룬����һ�У���д��
	    								for(int x=j;x<j+3;x++){
	    									if(x<len){
	    										if(x == j){
	    		    								phrase3 = new Phrase(xmXhByXyList.get(x),contextFont);
	    											if(xmByXyList.get(x).replaceAll(" ", "").length()>4){
	    												para3.add(phrase3);
	    												count=1;
	    												break;
	    											}else{
	    												count=3;
	    											}
	    											
	    										}else{
	    											if(xmByXyList.get(x).replaceAll(" ", "").length()>4){
	    												count=x-j;
	    												break;
	    											}else{	
	    												phrase3 = new Phrase("   "+xmXhByXyList.get(x),contextFont);
	    												count=3;
	    											}
	    										}
	    										para3.add(phrase3);
		    									
		    								}
	    									
	    								}
	    								
//	    								if(j+1<xmXhByXyList.size()){
//	    									phrase3 = new Phrase("     "+xmXhByXyList.get(j+1),contextFont);
//		    								para3.add(phrase3);
//	    								}
//	    								if(j+2<xmXhByXyList.size()){
//	    									phrase3 = new Phrase("     "+xmXhByXyList.get(j+2),contextFont);
//		    								para3.add(phrase3);
//	    								}
	    								para3.setIndentationLeft(10);
	    								document.add(para3);
									}
	    							para3 = new Paragraph("  ");
	    							//para3.setSpacingAfter(10);
	    							document.add(para3);
//	    						}
//	    						if(modlen != 0){
//	    							for (int j = len-modlen; j < xmXhByXyList.size(); j++) {
//										
//									}
//	    						}
	    					}
	    				}//���� ����---------------------------------------------------------
	    			}//һ������ʼ---------------------------------------------------------
	    		  
	    		}
	       
	        document.close();  
       
	}
	
	/**
	 * 
	 * @����: ����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-14 ����04:01:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param model
	 * @param user
	 * @param path
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void createWordNjdc(HttpServletResponse response,HjmdExportModel model,User user) throws Exception{
		Document document = new Document(PageSize.A4);  
        //����һ����д������document�������  
		//��Ӧͷ����
	    response.setHeader("Content-Disposition", "attachment;filename=\""
               + new String("njdc_zjdx.doc".getBytes(), "GBK") + "\"");
        RtfWriter2.getInstance(document, response.getOutputStream());
        //���ĵ�
        document.open();  
        //������������  
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
        //����������  
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        //����������  
        Font contextFont = new Font(bfChinese,13,Font.NORMAL);  
        
		//ѧ��
		String xn = model.getXn();
		//ѧ��
		String xq = model.getXq();
		//ѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		//��Ŀ����
		String[] xmlx = model.getXmlx();
		//ѧԺ����
		String[] xydm = model.getXydm();
		//��Ŀ����
		String[] xmxz = model.getXmxz();
		//��Ŀ����
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		// ѧԺ�б�
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ����ѧ�������
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		// ������
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		//��Ŀ��������
		PjdmService pjdmService = new PjdmService();
		List<String> xmlxmc = pjdmService.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		//�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�
		CsszService csszService = new CsszService();
		String pjzq = csszService.getCsz("pjzq");
		Paragraph title = null;
		if("1".equals(pjzq)){ 	
		// �����һ�еı�ͷ
			title = new Paragraph(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", xqmc, "ѧ��", lxmc, "��������"),titleFont); 
		}else {
			title = new Paragraph(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"),titleFont); 
		}
		title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		if (!xmList.isEmpty()) {
			Paragraph paraitra1 = null;
			int propertiesrow =3;
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				
				 
			
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				paraitra1 = new Paragraph(jxjmc,titleFont);
				paraitra1.setAlignment(Element.ALIGN_LEFT);
				document.add(paraitra1);
				
		      
				//���� ��ʼ ����ڶ�����ѧԺ����д��EXCEL��---------------------------------------------------------
				Paragraph paraitra2 = null;
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0]: "";
						
						List<String> xmXhByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						List<String> xmByXyList = new ArrayList<String>();
						for (String[] xmArr : xmList) {//���� ��ʼ ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 4
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
								xmXhByXyList.add(xmArr[3]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++propertiesrow;
						xy+="("+xymcArr[2]+"��)";
						paraitra2 = new Paragraph(xy,titleFont);
						paraitra2.setIndentationLeft(10);
						//paraitra2.setSpacingAfter(10);
						document.add(paraitra2);
//					    run2.setFontSize(32);
//				        run2.setFtcAscii(11);
//				        run2.setBold(true);
//						System.out.println("ѧԺ��"+xy);
//						Label xymcCell = new Label(1,properties.row,xy );
//						xymcCell.setCellFormat(jxxyFormat);
//						ws.addCell(xymcCell);
//						ws.mergeCells(1,properties.row, 3, properties.row);
						//�����������ѧԺ����Ļ��������
			//			writeHjmdhzExcel_10335(ws, properties, xmByXyList,xmXhByXyList,0);//��������д�뵽EXCEL��
						int len = xmXhByXyList.size();
				//		int modlen = len % 3;
//						if(len >= 3){
							Paragraph para3 = null;
							Phrase phrase3 = null;
							//���������������ѭ��
							int count = 9;
							//�¼��б�־
//							boolean flag = true;
							//����ѭ�����������Ϊһ��
							for (int j = 0; j < len; j=j+count) {
								para3 = new Paragraph();
								int strlen = 35;
								int realtotallen = 0;
								int realeverylen = 0;
								//����д�룬���浼��һ��9����������������ϳ��İ�һ��35���ַ��㣬�������35���ַ����ͻ�����һ�н�����д��
								for(int x=j;x<j+9;x++){
									if(x<len){
										if(x == j){
		    								phrase3 = new Phrase(xmByXyList.get(x),contextFont);
		    								realeverylen = xmByXyList.get(x).length();
		    								realtotallen =realtotallen+realeverylen;
											count=9;
											
										}else{
											realeverylen = xmByXyList.get(x).length();
											realtotallen =realtotallen+realeverylen;
											if(realtotallen >strlen){
												count=x-j;
												break;
											}else{
												phrase3 = new Phrase(" "+xmByXyList.get(x),contextFont);
												count=9;
											}
										}
										para3.add(phrase3);
    									
    								}
									
								}
								
//								if(j+1<xmXhByXyList.size()){
//									phrase3 = new Phrase("     "+xmXhByXyList.get(j+1),contextFont);
//    								para3.add(phrase3);
//								}
//								if(j+2<xmXhByXyList.size()){
//									phrase3 = new Phrase("     "+xmXhByXyList.get(j+2),contextFont);
//    								para3.add(phrase3);
//								}
								para3.setIndentationLeft(10);
								document.add(para3);
							}
							para3 = new Paragraph("  ");
							//para3.setSpacingAfter(10);
							document.add(para3);
					}
				}//���� ����---------------------------------------------------------
			}//һ������ʼ---------------------------------------------------------
		  
		}
		//�ر��ĵ�
		document.close();
	}

	/**
	 *  ����ѧ�ꡢѧԺ�����ѯ�����������ܱ��ļ�����.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-19 17:07
	 * @param model
	 * @return java.io.File[]
	 * @throw
	 */
	public File[] getPymdhzbFiles(TjcxModel model) {

		List<File> fileList = new ArrayList<File>();
		String xn = model.getXn();
		String [] xyArr = model.getXyArr()[0].split(",");

		for(String xydm:xyArr){
			Map<String,Object> data = new HashMap<String,Object>();

			//ѧԺ����
			String xymc = dao.getXymcByXydm(xydm);
			//ʮ�Ѱ༶
			List<HashMap<String,String>> sjbjList = dao.getBjpjList(xn,xydm,sjbj);
			//�Ƚ��༯��
			List<HashMap<String,String>> xjbjtList = dao.getBjpjList(xn,xydm,xjbjt);
			//����ѧ�����
			List<HashMap<String,String>> shxsbbList = dao.getGrpjList(xn,xydm,shxsbb);
			data.put("shxsbbCount",shxsbbList.size());
			shxsbbList = addBlankMapByCols(shxsbbList,5);
			//����ѧ��
			List<HashMap<String,String>> shxsList = dao.getGrpjList(xn,xydm,shxs);
			data.put("shxsCount",shxsList.size());
			shxsList = addBlankMapByCols(shxsList,5);
			//����ѧ���ɲ�
			List<HashMap<String,String>> yxxsgbList = dao.getGrpjList(xn,xydm,yxxsgb);
			data.put("yxxsgbCount",yxxsgbList.size());
			yxxsgbList = addBlankMapByCols(yxxsgbList,5);
			//������Ṥ����
			List<HashMap<String,String>> yxshgzzList = dao.getGrpjList(xn,xydm,yxshgzz);
			data.put("yxshgzzCount",yxshgzzList.size());
			yxshgzzList = addBlankMapByCols(yxshgzzList,5);

			data.put("xymc",xymc);
			data.put("sjbj",sjbjList);
			data.put("xjbjt",xjbjtList);
			data.put("shxsbb",shxsbbList);
			data.put("shxs",shxsList);
			data.put("yxxsgb",yxxsgbList);
			data.put("yxshgzz",yxshgzzList);

			String year = DateUtils.getYear();
			String month = DateUtils.getMonth();
			String day = DateUtils.getDayOfMonth();

			data.put("year",year);
			data.put("month",month);
			data.put("day",day);

			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", "yxpyhzmdword_10530.xml", xymc+"������ͳ��");

			if(wordFile != null){
				fileList.add(wordFile);
			}
		}

		return fileList.toArray(new File[]{});
	}

	/**
	 *  ��Ҫ�����հ�map��list.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-20 15:47
	 * @param rList
	 * @param cols
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	private List<HashMap<String,String>> addBlankMapByCols(List<HashMap<String,String>> rList,int cols){

		int size = rList.size();
		if(size>0&&size%cols!=0){
			int len = cols - size%cols;
			for (int i=0;i<len;i++){
				rList.add(new HashMap<String, String>());
			}
		}
		return rList;
	}

	public File getTjExcelFile(List<HashMap<String, String>> resultList,HashMap<String, String> infomap)
			throws Exception {
		//��Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);


		if(!file.exists()){
			file.createNewFile();
		}

		//����������
		WritableWorkbook  wwb = Workbook.createWorkbook(file);

		//������ظ�ʽ
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//���ñ�������
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		WritableFont zbFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//�����Ʊ���

		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		WritableCellFormat zb_cf = new WritableCellFormat(zbFont);//�������ĵ�Ԫ������
		zb_cf.setWrap(true);
		zb_cf.setAlignment(Alignment.LEFT);//�������ĵ�Ԫ��ˮƽ����
		zb_cf.setVerticalAlignment(VerticalAlignment.CENTRE);//���ô�ֱ����

		body_cf.setWrap(true);
		head_cf.setWrap(true);//�Զ�����
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷ��ֱ����
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ô�ֱ����
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
				jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		String xymc = "";
		if(!StringUtils.isNull(infomap.get("xymc"))){
			xymc = infomap.get("xymc");
		}
		String title = xymc+infomap.get("xn")+"ѧ��"+infomap.get("xqmc")+"��ĩ��ѧ��ͳ��";	//����

		//����������
		WritableSheet sheet = wwb.createSheet("sheet 1", 0);
		//���ø����п�
		sheet.setColumnView(0, 20);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 15);
		sheet.setColumnView(3, 15);
		sheet.setColumnView(4, 15);

		//�и�
		sheet.setRowView(0,800);
		sheet.setRowView(1,600);

		//�ϲ���Ԫ��
		//��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
		//�ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
		//������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��кţ�
		//���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ���
		sheet.mergeCells(0, 0, 4, 0);	//����

		//�������⼰��ͷ
		Label t_0_0 = new Label(0, 0,title,title_cf);
		Label h_0_1 = new Label(0,1,"����",head_cf);
		Label h_1_1 = new Label(1,1,"�༶",head_cf);
		Label h_2_1 = new Label(2,1,"����",head_cf);
		Label h_3_1 = new Label(3,1,"���",head_cf);
		Label h_4_1 = new Label(4,1,"�ϼ�",head_cf);

		sheet.addCell(t_0_0);
		sheet.addCell(h_0_1);
		sheet.addCell(h_1_1);
		sheet.addCell(h_2_1);
		sheet.addCell(h_3_1);
		sheet.addCell(h_4_1);
		/*һ��ʼ˼·�����⣬д�ľ޲���ˣ�����ģ�˭Ը���˭�İ�*/
		//����������Ԫ��
		int size = resultList.size();
		if(size>2){
			int zje = 0;
			int hjje = 0;
			int hjrs = 1;//������
			for(int j=0;j<size;j++){
				HashMap<String,String> map = resultList.get(j);
				Label bjmc = new Label(1, j+2, map.get("bjmc"), body_cf);		//�༶
				Label xm = new Label(2, j+2, map.get("xm"), body_cf);		//����
				String xmje = map.get("xmje") == null?"":map.get("xmje");
				Label je = new Label(3, j+2,xmje , body_cf);		//���
				int temp = 0;
				sheet.addCell(bjmc);
				sheet.addCell(xm);
				sheet.addCell(je);
				if(!StringUtils.isNull(xmje)){
					temp= Integer.valueOf(xmje);
					zje = zje+temp;
				}
				hjje = hjje+temp;
				if(j > 0){
					if(map.get("xmmc").equals(resultList.get(j-1).get("xmmc"))){

						hjrs++;
						if(j == (size-1)){
							sheet.mergeCells(0, j+3-hjrs, 0, j+2);//���������кϲ���Ԫ��
							sheet.mergeCells(4, j+3-hjrs, 4, j+2);//�ϼ��кϲ���Ԫ��
							Label xmmc = new Label(0, j+3-hjrs,
									map.get("xmmc")+"��"+hjrs+"�ˣ�", body_cf);//��Ŀ����
							Label hj = new Label(4, j+3-hjrs, String.valueOf(hjje), body_cf);//��Ŀ����
							sheet.addCell(xmmc);
							sheet.addCell(hj);
						}
					}else{
						hjje = hjje - temp;
						sheet.mergeCells(0, j+2-hjrs, 0, j+1);//���������кϲ���Ԫ��
						sheet.mergeCells(4, j+2-hjrs, 4, j+1);//�ϼ��кϲ���Ԫ��
						Label xmmc = new Label(0, j+2-hjrs,
								resultList.get(j-1).get("xmmc")+"��"+hjrs+"�ˣ�", body_cf);//��Ŀ����
						Label hj = new Label(4, j+2-hjrs, String.valueOf(hjje), body_cf);//��Ŀ����
						sheet.addCell(xmmc);
						sheet.addCell(hj);
						if(j == (size-1)) {
							Label xmmc_last = new Label(0, j+2,
									map.get("xmmc")+"��1�ˣ�", body_cf);//��Ŀ����
							Label hj_last = new Label(4, j+2, xmje, body_cf);//��Ŀ����
							sheet.addCell(xmmc_last);
							sheet.addCell(hj_last);
						}
						hjje=temp;
						hjrs=1;
					}

				}
			}
			sheet.setRowView(size+2,600);
			Label hj_label = new Label(3, size+2, "�ϼƣ�", title_cf);
			Label hjje_label = new Label(4, size+2, String.valueOf(zje), title_cf);
			sheet.addCell(hj_label);
			sheet.addCell(hjje_label);
			sheet.mergeCells(0, size+3, 3, size+3);//�Ʊ��кϲ���Ԫ��
			String zb = "�Ʊ�" + infomap.get("userName")+
					"                      ��ˣ�                              �쵼:";
			Label zb_label = new Label(0, size+3, zb, zb_cf);
			sheet.addCell(zb_label);
		}
		if(size == 1){
			HashMap<String,String> map = resultList.get(0);
			Label bjmc = new Label(1, 2, map.get("bjmc"), body_cf);		//�༶
			Label xm = new Label(2, 2, map.get("xm"), body_cf);		//����
			String xmje = map.get("xmje") == null?"":map.get("xmje");
			Label je = new Label(3, 2,xmje , body_cf);		//���
			Label xmmc = new Label(0, 2,
					map.get("xmmc")+"��1�ˣ�", body_cf);//��Ŀ����
			Label hj = new Label(4, 2, String.valueOf(xmje), body_cf);//�ϼ�
			sheet.addCell(xmmc);
			sheet.addCell(hj);
			sheet.addCell(bjmc);
			sheet.addCell(xm);
			sheet.addCell(je);
			sheet.setRowView(size+2,600);
			Label hj_label = new Label(3, size+2, "�ϼƣ�", title_cf);
			Label hjje_label = new Label(4, size+2, String.valueOf(xmje), title_cf);
			sheet.addCell(hj_label);
			sheet.addCell(hjje_label);
			sheet.mergeCells(0, size+3, 3, size+3);//�Ʊ��кϲ���Ԫ��
			String zb = "�Ʊ�" + infomap.get("userName")+
					"                      ��ˣ�                              �쵼:";
			Label zb_label = new Label(0, size+3, zb, zb_cf);
			sheet.addCell(zb_label);
		}

		//�������Ϊ��
		if(resultList == null||resultList.size()==0){
			//����������
			WritableSheet sheetNull = wwb.createSheet("���ε�������Ϊ��", 0);
			sheetNull.setColumnView(0, 15);
			Label msg = new Label(0, 0,"�������ݣ�");
			sheetNull.addCell(msg);
		}

		wwb.write();
		wwb.close();

		return file;
	}

	public File getLqExcelFile(List<HashMap<String, String>> resultList,HashMap<String, String> infomap)
			throws Exception {
		//��Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		//����������
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		//������ظ�ʽ
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//���ñ�������
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		WritableFont zbFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//�����Ʊ���

		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		WritableCellFormat zb_cf = new WritableCellFormat(zbFont);//�������ĵ�Ԫ������
		zb_cf.setWrap(true);
		zb_cf.setAlignment(Alignment.LEFT);//�������ĵ�Ԫ��ˮƽ����
		zb_cf.setVerticalAlignment(VerticalAlignment.CENTRE);//���ô�ֱ����

		body_cf.setWrap(true);
		head_cf.setWrap(true);//�Զ�����
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷ��ֱ����
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ô�ֱ����
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
				jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		String xymc = "";
		if(!StringUtils.isNull(infomap.get("xymc"))){
			xymc = "(" + infomap.get("xymc")+")";
		}
		String title = infomap.get("xn")+infomap.get("xqmc")+"ѧ����ѧ����ȡ��"+xymc;	//����

		//����������
		WritableSheet sheet = wwb.createSheet("sheet 1", 0);
		//���ø����п�
		sheet.setColumnView(0, 10);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 15);
		sheet.setColumnView(3, 15);
		sheet.setColumnView(4, 25);

		//�и�
		sheet.setRowView(0,800);

		//�ϲ���Ԫ��
		//��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
		//�ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
		//������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��кţ�
		//���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ���
		sheet.mergeCells(0, 0, 4, 0);	//����
		sheet.mergeCells(0, 1, 0, 2);	//��ͷ
		sheet.mergeCells(1, 1, 1, 2);	//��ͷ
		sheet.mergeCells(2, 1, 2, 2);	//��ͷ
		sheet.mergeCells(4, 1, 4, 2);	//��ͷ

		//�������⼰��ͷ
		Label t_0_0 = new Label(0, 0,title,title_cf);
		Label h_0_1 = new Label(0,1,"��",head_cf);
		Label h_1_1 = new Label(1,1,"�༶",head_cf);
		Label h_2_1 = new Label(2,1,"����",head_cf);
		Label h_3_1 = new Label(3,1,"����",head_cf);
		Label h_3_2 = new Label(3,2,"���",head_cf);
		Label h_4_1 = new Label(4,1,"��ȡ��ǩ��",head_cf);

		sheet.addCell(t_0_0);
		sheet.addCell(h_0_1);
		sheet.addCell(h_1_1);
		sheet.addCell(h_2_1);
		sheet.addCell(h_3_1);
		sheet.addCell(h_3_2);
		sheet.addCell(h_4_1);
		//����������Ԫ��
		int size = resultList.size();
		if(size>0){
			int bjrs = 1;
			int hj = 0;
			for(int j=0;j<size;j++){
				HashMap<String,String> map = resultList.get(j);
				Label xh = new Label(0, j+3, String.valueOf(j+1), body_cf);		//���
				Label xm = new Label(2, j+3, map.get("xm"), body_cf);		//����
				String xmje = map.get("xmje") == null?"":map.get("xmje");
				Label je = new Label(3, j+3,xmje , body_cf);		//���
				sheet.addCell(xh);
				sheet.addCell(xm);
				sheet.addCell(je);
				if(!StringUtils.isNull(xmje))
					hj = hj+Integer.valueOf(xmje);
				if(j<size-1){
					if(map.get("bjdm").equals(resultList.get(j+1).get("bjdm"))){
						bjrs++;
						if((j+1) == (size-1)){
							sheet.mergeCells(1, j+5-bjrs, 1, j+4);
							sheet.mergeCells(4, j+5-bjrs, 4, j+4);
							Label bjmc = new Label(1, j+5-bjrs, resultList.get(j).get("bjmc"), body_cf);//�༶
							Label lqrqm = new Label(4, j+5-bjrs, "", body_cf);//��ȡ��ǩ��
							sheet.addCell(bjmc);
							sheet.addCell(lqrqm);
						}
					}else{
						sheet.mergeCells(1, j+4-bjrs, 1, j+3);
						sheet.mergeCells(4, j+4-bjrs, 4, j+3);
						Label bjmc = new Label(1, j+4-bjrs, map.get("bjmc"), body_cf);//�༶
						Label lqrqm = new Label(4, j+4-bjrs, "", body_cf);//��ȡ��ǩ��
						sheet.addCell(bjmc);
						sheet.addCell(lqrqm);
						if((j+1) == (size-1)){
							Label bjmc_last = new Label(1, size+2, resultList.get(j+1).get("bjmc"), body_cf);//�༶
							Label lqrqm_last = new Label(4, size+2, "", body_cf);//��ȡ��ǩ��
							sheet.addCell(bjmc_last);
							sheet.addCell(lqrqm_last);
						}
						bjrs=1;
					}
				}

			}
			sheet.mergeCells(0, size+3, 2, size+3);//�ϼ��кϲ���Ԫ��
			sheet.mergeCells(3, size+3, 4, size+3);//�ϼ��кϲ���Ԫ��
			sheet.mergeCells(0, size+4, 3, size+4);//�Ʊ��кϲ���Ԫ��
			Label hj_label = new Label(0, size+3, "�ϼƣ�", head_cf);
			Label hjje_label = new Label(3, size+3, String.valueOf(hj), head_cf);
			sheet.addCell(hj_label);
			sheet.addCell(hjje_label);
			String zb = "�Ʊ�" + infomap.get("userName")+
					"                                     ��ˣ�";
			Label zb_label = new Label(0, size+4, zb, zb_cf);
			Label sj_label = new Label(4, size+5, infomap.get("sj"), zb_cf);
			sheet.addCell(zb_label);
			sheet.addCell(sj_label);
		}

		//�������Ϊ��
		if(resultList == null||resultList.size()==0){
			//����������
			WritableSheet sheetNull = wwb.createSheet("���ε�������Ϊ��", 0);
			sheetNull.setColumnView(0, 15);
			Label msg = new Label(0, 0,"�������ݣ�");
			sheetNull.addCell(msg);
		}

		wwb.write();
		wwb.close();

		return file;
	}
}

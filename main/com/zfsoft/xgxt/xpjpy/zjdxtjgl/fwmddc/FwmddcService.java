/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-2-6 ����05:33:05 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.fwmddc;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.tjcx.HjmdExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ������������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-2-6 ����05:33:05 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FwmddcService extends SuperServiceImpl<FwmddcForm, FwmddcDao>{
	
	private FwmddcDao dao = new FwmddcDao();
	
	@SuppressWarnings("deprecation")
	public FwmddcService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-25 ����03:48:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportHjmdtj_10335(HjmdExportModel model , OutputStream os, User user) throws Exception{
		
		/**ȡѧ��*/
		String xn = model.getXn();
		/**ȡѧ��*/
		String xq = model.getXq();
		/**ȡѧ������*/
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for(HashMap<String,String> map : xqList){
			if(map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		/**��Ŀ����*/
		String[] xmlx = model.getXmlx();
		/**ѧԺ����*/
		String[] xydm = model.getXydm();
		/**��Ŀ����*/
		String[] xmxz = model.getXmxz();
		/**��Ŀ����*/
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
		
		/**ѧԺ���ơ���Ŀ���ơ�����*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**��Ŀ���ơ���Ŀ��ʾ��š�����*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**������*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**��Ŀ��������*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**��ѧ��������ʽ*/
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		/**����+ѧԺ��ʽ*/
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"), titlFormat));
		ws.mergeCells(1, 0,3, 1);
		
		/**xmList ������*/
		/**jxjdmList ��Ŀ������*/
		/**xymcList ѧԺ������*/
		/**�����һ����ѧ�����ƺͻ�����*/
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //�㽭��ѧ��ͷ������
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				/*����ѧ������д�뵽EXCEL��*/
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
		
		/**��ͻ������*/
		ExcelMethods.submitWritableWorkbookOperations(wwb);
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
	
	private void writeHjmdhzExcel_10335(WritableSheet ws,JxjExportProperties properties, List<String> xmList,List<String> xmXhList,int rowCellCount) 
		throws Exception {
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
	 * @����: �������������word
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-28 ����03:42:53
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
		/**����һ����д������document�������  */
	    response.setHeader("Content-Disposition", "attachment;filename=\"" + new String("fwmddc_zjdx.doc".getBytes(), "GBK") + "\"");
	    RtfWriter2.getInstance(document, response.getOutputStream());  
        document.open();  
        /**������������ */
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        /**����������*/
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        /**����������*/
        Font contextFont = new Font(bfChinese,16,Font.NORMAL);  
        Paragraph title = new Paragraph(); 
        /**ѧ��*/
		String xn = model.getXn();
		/**ѧ��*/
		String xq = model.getXq();
		/**ѧ������*/
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		/**��Ŀ����*/
		String[] xmlx = model.getXmlx();
		/**ѧԺ����*/
		String[] xydm = model.getXydm();
		/**��Ŀ����*/
		String[] xmxz = model.getXmxz();
		/**��Ŀ����*/
		String[] xmmc = model.getXmmc();
		
		/**ѧԺ�б�*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**����ѧ�������*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**������*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**��Ŀ��������*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�*/
		Phrase phrase = null;
		phrase = new Phrase(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"),titleFont);
		
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
    						document.add(paraitra2);
    						int len = xmXhByXyList.size();
    							Paragraph para3 = null;
    							Phrase phrase3 = null;
    							/*���������������ѭ��*/
    							int count = 3;
    							/*����ѭ������������Ϊһ��*/
    							for (int j = 0; j < len; j=j+count) {
    								para3 = new Paragraph();
    								/*����д�룬��������4���ַ��ĵ���Ϊһ�У�����ڶ������ֻ��ߵ��������ֳ���4���ַ�����д�룬����һ�У���д��*/
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

    								para3.setIndentationLeft(10);
    								document.add(para3);
								}
    							para3 = new Paragraph("  ");
    							document.add(para3);
    					}
    				}//���� ����---------------------------------------------------------
    			}//һ������ʼ---------------------------------------------------------
    		  
    		}
		
		document.close();
	}
	
	/**
	 * @����: �������word
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-28 ����04:51:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param response
	 * @param model
	 * @param user
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
		/**��Ŀ����*/
		String[] xmlx = model.getXmlx();
		/**ѧԺ����*/
		String[] xydm = model.getXydm();
		/**��Ŀ����*/
		String[] xmxz = model.getXmxz();
		/**��Ŀ����*/
		String[] xmmc = model.getXmmc();
		
		StringBuilder titleXmlx = new StringBuilder();
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				titleXmlx.append(", ");
			titleXmlx.append(xmlx[i]);
		}
		/**ѧԺ�б�*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**����ѧ�������*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**������*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**��Ŀ��������*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**�����������ڣ�ѧ��������Ҫ��ӡ��ѧ�ڣ������ã�*/
		Paragraph title = null;
		title = new Paragraph(StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"),titleFont);
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
						/*���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����*/
						List<String> xmXhByXyList = new ArrayList<String>();
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
						document.add(paraitra2);
						int len = xmXhByXyList.size();
							Paragraph para3 = null;
							Phrase phrase3 = null;
							/*���������������ѭ��*/
							int count = 9;
							/*�¼��б�־*/
							/*����ѭ�����������Ϊһ��*/
							for (int j = 0; j < len; j=j+count) {
								para3 = new Paragraph();
								int strlen = 35;
								int realtotallen = 0;
								int realeverylen = 0;
								/*����д�룬���浼��һ��9����������������ϳ��İ�һ��35���ַ��㣬�������35���ַ����ͻ�����һ�н�����д��*/
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
								
								para3.setIndentationLeft(10);
								document.add(para3);
							}
							para3 = new Paragraph("  ");
							document.add(para3);
					}
				}//���� ����---------------------------------------------------------
			}//һ������ʼ---------------------------------------------------------
		  
		}
		//�ر��ĵ�
		document.close();
	}
	
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 018-2-28 ����04:59:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportHjmdtj_10335_njdc(HjmdExportModel model , OutputStream os, User user) throws Exception{
		
		/**ѧ��*/
		String xn = model.getXn();
		/**ѧ��*/
		String xq = model.getXq();
		/**ѧ������*/
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		/**��Ŀ����*/
		String[] xmlx = model.getXmlx();
		/**ѧԺ����*/
		String[] xydm = model.getXydm();
		/**��Ŀ����*/
		String[] xmxz = model.getXmxz();
		/**��Ŀ����*/
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
		
		/**ѧԺ�б�*/
		List<String[]> xymcList = dao.getXyList_10335(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**����ѧ�������*/
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlx, xydm, xmxz, xmmc, user);
		/**������*/
		List<String[]> xmList = dao.getHjmdList_10335(xn, xq, xmlx, xydm, xmxz, xmmc);
		
		/**��Ŀ��������*/
		List<String> xmlxmc = dao.getXmlxmc(xmlx);
		String lxmc = null;
		for (int i = 0; i < xmlxmc.size(); i++) {
			if(i==0){
				lxmc=xmlxmc.get(i);
			}else{
				lxmc=lxmc+","+xmlxmc.get(i);
			}
		}
		
		/**��ѧ��������ʽ*/
		WritableCellFormat titlFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		titlFormat.setFont(jxjFont);
		titlFormat.setAlignment(Alignment.CENTRE);
		
		
		/**����+ѧԺ��ʽ*/
		WritableCellFormat jxxyFormat = new WritableCellFormat();
		WritableFont jxxyFont = new WritableFont(WritableFont.createFont("����_GB2312"),16);
		jxxyFont.setBoldStyle(WritableFont.BOLD);
		jxxyFormat.setFont(jxjFont);
		jxxyFormat.setAlignment(Alignment.LEFT);
		
		ws.addCell(new Label(1, 0, StringUtils.joinStr(StandardOperation.getXxmc(), xn, "ѧ��", lxmc, "��������"), titlFormat));
		ws.mergeCells(1, 0,9, 1);
		
		/**xmList ������ */
		/**jxjdmList ��Ŀ������*/
		/**xymcList ѧԺ������*/
		/**�����һ����ѧ�����ƺͻ�����*/
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			properties.row =properties.row+2; //�㽭��ѧ��ͷ������
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				/**����ѧ������д�뵽EXCEL��*/
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
	 * @����: ���Ի��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-28 ����05:09:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ws
	 * @param properties
	 * @param xmList
	 * @param xmXhList
	 * @param rowCellCount
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	private void writeHjmdhzExcel_10335_njdc(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,List<String> xmXhList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		/**��ʼд������*/
		properties.row++;
		/**��ʼ�½�ѧ�������*/
		properties.x_axis = rowCellCount;
		/**������ʽ*/
		WritableCellFormat xmFormat = new WritableCellFormat();
		WritableFont xmFont = new WritableFont(WritableFont.createFont("����_GB2312"),13);
		xmFont.setBoldStyle(WritableFont.NO_BOLD);
		xmFormat.setFont(xmFont);
		xmFormat.setAlignment(Alignment.LEFT);
		
		if (xmList != null) {
 			for (int index = 1; index < xmList.size() + 1; index++) {
 				/**�ϲ���Ԫ���ַ�������*/
				int nameLength = 3;
				int Column = 0;
				String cellContent = xmXhList.get(index - 1);
				String xmContent = xmList.get(index - 1);
				int strLength = xmContent.replace(" ", "").length();
				if(strLength>nameLength){
					Column = 1;
				}
				
				properties.rowCellCount+=Column;
				
				if (properties.rowCellCount >= 10) {
					/**��9���ͻ���*/
					properties.row++;
					properties.x_axis = 0;
					/**����ÿ�м���*/
					properties.rowCellCount = 1;
				}
				
				/**�����ֵ���������sql���д�������������*/
				if(strLength>2){
					xmContent=xmContent.replace(" ", "").substring(0, 1)+" "+xmContent.replace(" ", "").substring(1, 2);
				}
				
				Label cell = new Label(++properties.x_axis, properties.row,cellContent);
				cell.setCellFormat(xmFormat);
				ws.mergeCells(properties.x_axis, properties.row, properties.x_axis+Column, properties.row);
				ws.addCell(cell); 
				ws.setRowView(properties.row, 500);
				ws.setColumnView(properties.x_axis, 9);
				properties.rowCellCount++;
				properties.x_axis+=(Column);
			}
		}
		/**�½�ѧ����*/
		properties.row += 1;
	}
}
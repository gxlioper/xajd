/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-19 ����09:41:56 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

import java.io.OutputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.gygl.qsdsgl.qsdskh.QsdskhForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ���ҵ�ʦά��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-19 ����09:41:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QsdswhService extends SuperServiceImpl<QsdswhForm, QsdswhDao> {

	public QsdswhService(){
		setDao(new QsdswhDao());
	}
	/** 
	 * ��ѯ������Ϣ��ͷ
	 */
	public String[] getQsxxTopTr(){
		String[] topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","ѧ��","����","רҵ����","�༶"};
		return topTr;
	}
	
	/** 
	 * ��ѯ������Ϣ��ͷ(���ݴ�ѧ)
	 */
	public String[] getQsxxTopTrForWzdx(){
		String[] topTr = new String[]{"ѧ��","����","ѧԺ","רҵ","�༶","��λ��"};
		return topTr;
	}
	
	/** 
	 * ��ѯ������Ϣ���
	 */
	public List<String[]> getQsxxList(String lddm, String qsh) throws Exception{
		return dao.getQsxxList(lddm, qsh);
	}
	
	public List<HashMap<String, String>> getxsxx(String lddm, String qsh) throws Exception{
		return dao.getxsxx(lddm, qsh);
	}

	/**
	 * 
	 * ��ȡ���ҵ�ʦ��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����09:39:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsdsxx(QsdswhForm model){
		
		return dao.getQsdsxx(model);
	}
	
	/**
	 * ��������������ҵ�ʦ��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����10:00:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQsdsxx(QsdswhForm model) throws Exception{
		return dao.saveQsdsxx(model);
	}
	/**
	 * @����: ���������ж��Ƿ����ͬ����¼��
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-11-11 ����09:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistLddm(QsdswhForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
	}
	/**
	 * 
	 * ɾ����Ϣ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����11:55:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		return dao.deleteDsdsxxPl(pks);
	}
	
	/**
	 * */
	public List<HashMap<String,String>> getQsdsxxListByXh(String xh){
		return dao.getQsdsxxListByXh(xh);
	}
	
	/**
	 * @���� ��ʦ���˵���
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * void �������� 
	 * @throws
	 */
	public void exportDskh(QsdswhForm model, User user, OutputStream os) throws Exception{
		// ����,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("����"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		// ס������Ϣ
		List<HashMap<String,String>> stuList = dao.exportDskh(model, user);
		// ���λ��
		int maxCws = 0;
		for (HashMap<String, String> stuMap : stuList) {
			maxCws = Math.max(maxCws, Integer.parseInt(stuMap.get("cws")));
		}
		int mergeCols = maxCws + 4;
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ʦ����", 0);
		// ���á����ҳ���ϵ��ʽ���п��
		ws.setColumnView(mergeCols + 1, 18); 
		// ���á��༶���п��
		ws.setColumnView(mergeCols + 2, 18); 
		// ���á�ѧԺ���п��
		ws.setColumnView(mergeCols + 3, 18); 
		// ���á����ҵ�ʦ���п��
		ws.setColumnView(mergeCols + 4, 13); 
		// ���á���ʦ��ϵ��ʽ���п��
		ws.setColumnView(mergeCols + 5, 18); 
		// ���á���ʦ���ڵ�λ���п��
		ws.setColumnView(mergeCols + 6, 18); 
		// ���á�ѧ�꡿�п��
		ws.setColumnView(mergeCols + 8, 11); 
		// ��1�� ����,12,CENTRE
		WritableCellFormat k12WHITECenterFormat = new WritableCellFormat();
		WritableFont k12WHITECenterFont = new WritableFont(WritableFont.createFont("����"),12);
		k12WHITECenterFont.setColour(Colour.WHITE);
		k12WHITECenterFont.setBoldStyle(WritableFont.BOLD);
		k12WHITECenterFormat.setFont(k12WHITECenterFont);
		k12WHITECenterFormat.setAlignment(Alignment.CENTRE);
		k12WHITECenterFormat.setBackground(Colour.GREEN);
		ws.addCell(new Label(0, 0, "���", k12WHITECenterFormat));
		ws.addCell(new Label(1, 0, "ѧ��", k12WHITECenterFormat));
		ws.addCell(new Label(2, 0, "¥��", k12WHITECenterFormat));
		ws.addCell(new Label(3, 0, "���Һ�", k12WHITECenterFormat));
		ws.addCell(new Label(4, 0, "���ҳ�Ա", k12WHITECenterFormat));
		ws.mergeCells(4, 0, (mergeCols - 1), 0);
		ws.addCell(new Label(mergeCols, 0, "���ҳ�", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 1, 0, "���ҳ���ϵ��ʽ", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 2, 0, "�༶", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 3, 0, "ѧԺ", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 4, 0, "���ҵ�ʦ", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 5, 0, "��ʦ��ϵ��ʽ", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 6, 0, "��ʦ���ڵ�λ", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 7, 0, "���", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 8, 0, "ѧ��", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 9, 0, "ѧ��", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 10, 0, "���˳ɼ�", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 11, 0, "���ڿ�ʼʱ��", k12WHITECenterFormat));
		ws.addCell(new Label(mergeCols + 12, 0, "���ڽ���ʱ��", k12WHITECenterFormat));
		String temp = "";
		int row = 0;
		int qsxsCol = 4; // ����ѧ����ʼ��
		// ��2�п�ʼ
		for (int m = 0; m < stuList.size(); m++) {
			HashMap<String,String> stuMap = stuList.get(m);
			String pk = stuMap.get("lddm") + "@@" + stuMap.get("qsh");
			if(!temp.equals(pk)){
				row++;
				qsxsCol = 4; // ���� ����ѧ����ʼ��
				ws.addCell(new Label(0, row, String.valueOf(row), s12CentreFormat));
				ws.addCell(new Label(1, row, stuMap.get("yqmc"), s12CentreFormat));
				ws.addCell(new Label(2, row, stuMap.get("ldmc"), s12CentreFormat));
				ws.addCell(new Label(3, row, stuMap.get("qsh"), s12CentreFormat));
				
				ws.addCell(new Label(mergeCols, row, stuMap.get("qszxm"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 1, row, stuMap.get("qszlxdh"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 2, row, stuMap.get("qszbjmc"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 3, row, stuMap.get("qszxymc"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 4, row, stuMap.get("dsxm"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 5, row, stuMap.get("lxdh"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 6, row, stuMap.get("dw"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 7, row, stuMap.get("nd"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 8, row, stuMap.get("xn"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 9, row, stuMap.get("xqmc"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 10, row, stuMap.get("cj"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 11, row, stuMap.get("rqkssj"), s12CentreFormat));
				ws.addCell(new Label(mergeCols + 12, row, stuMap.get("rqjssj"), s12CentreFormat));
			}
			if(m == 0){
				temp = pk;
			}
			if(temp.equals(pk)){
				ws.addCell(new Label(qsxsCol, row, stuMap.get("qxxsxm"), s12CentreFormat));
				qsxsCol++;
			}
			temp = pk;
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
}

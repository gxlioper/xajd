/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����03:11:42 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���˹���
 * @�๦������: ���˽��
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-18 ����03:11:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JgcxService extends SuperServiceImpl<JgcxForm, JgcxDao> {
	
	
	/**
	 * 
	 * @����:���յ�����Ŀ��ѯ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-19 ����02:00:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> xmjgList(JgcxForm form, User user) throws Exception{
		if(KhpfService.YHLX_JS.equals(form.getKhlx())){
			return dao.xmjgToJs(form,user);
		}
		return dao.xmjgToXs(form, user);
	}
	public List<HashMap<String, String>> xmjgListOfSdty(JgcxForm form, User user) throws Exception{
		List<HashMap<String,String>> pfzList = dao.getPfzListByXmid(form.getXmid());
		if(KhpfService.YHLX_JS.equals(form.getKhlx())){
			return dao.xmjgToJsOfSdty(form,user,pfzList);
		}
		return dao.xmjgToXsOfSdty(form, user,pfzList);
	}
	
	public List<HashMap<String, String>> getPfzListByXmid(String xmid) throws Exception{
		return dao.getPfzListByXmid(xmid);
	}
	
	
	/**
	 * 
	 * @����:���ͳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-24 ����01:43:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> dftjList(JgcxForm form, User user) throws Exception{
		
		if(KhpfService.YHLX_JS.equals(form.getKhlx())){
			return dao.jsForKhr(form,user);
		}
		return dao.xsForKhr(form, user);
	}
	
	public List<HashMap<String, String>> lscpjgList(JgcxForm form, User user) throws Exception{
		return dao.lscpjgList(form, user);
	}


	/**
	 * @throws Exception  
	 * @����:����˲鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-24 ����06:05:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDfrList(JgcxForm model, User user) throws Exception {
		
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			return dao.getDfrListJs(model,user);			
		}

		return dao.getDfrListXs(model,user);
		
	}
	
	public void exportXsOfSdty(JgcxForm model,OutputStream os, List<HashMap<String, String>> resultList)
	throws Exception {

		List<HashMap<String,String>> pfzList = dao.getPfzListByXmid(model.getXmid());
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("���˽��", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();

		ws.addCell(new Label(0, 0, "���", wcf2));
		ws.addCell(new Label(1, 0, "ѧ��", wcf2));
		ws.addCell(new Label(2, 0, "����", wcf2));
		ws.addCell(new Label(3, 0, "�Ա�", wcf2));
		ws.addCell(new Label(4, 0, "�꼶", wcf2));
		ws.addCell(new Label(5, 0, "ѧԺ", wcf2));
		ws.addCell(new Label(6, 0, "�༶", wcf2));
		if (pfzList != null && pfzList.size() > 0) {
			for (int i = 0; i < pfzList.size(); i++) {
				HashMap<String, String> map = pfzList.get(i);
				ws.addCell(new Label(6+i+1, 0, map.get("pfzmc"), wcf2));
			}
		}
		ws.addCell(new Label(6+pfzList.size()+1, 0, "�ܷ�", wcf2));
		ws.addCell(new Label(6+pfzList.size()+2, 0, "ѧԺ����", wcf2));
		ws.addCell(new Label(6+pfzList.size()+3, 0, "ȫУ����", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> rsMap = resultList.get(i);
				ws.addCell(new Label(0, 1+i, String.valueOf(i+1), wcf2));
				ws.addCell(new Label(1, 1+i, rsMap.get("xh"), wcf2));
				ws.addCell(new Label(2, 1+i, rsMap.get("xm"), wcf2));
				ws.addCell(new Label(3, 1+i, rsMap.get("xb"), wcf2));
				ws.addCell(new Label(4, 1+i, rsMap.get("nj"), wcf2));
				ws.addCell(new Label(5, 1+i, rsMap.get("xymc"), wcf2));
				ws.addCell(new Label(6, 1+i, rsMap.get("bjmc"), wcf2));
				for (int j = 0; j < pfzList.size(); j++) {
					HashMap<String, String> map = pfzList.get(j);
					ws.addCell(new Label(6+j+1, 1+i, rsMap.get("fs"+j), wcf2));
				}
				ws.addCell(new Label(6+pfzList.size()+1, 1+i, rsMap.get("zf"), wcf2));
				ws.addCell(new Label(6+pfzList.size()+2, 1+i, rsMap.get("xypm"), wcf2));
				ws.addCell(new Label(6+pfzList.size()+3, 1+i, rsMap.get("pm"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
}
	
	public void exportJsOfSdty(JgcxForm model,OutputStream os, List<HashMap<String, String>> resultList)
	throws Exception {

		List<HashMap<String,String>> pfzList = dao.getPfzListByXmid(model.getXmid());
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("���˽��", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();

		ws.addCell(new Label(0, 0, "���", wcf2));
		ws.addCell(new Label(1, 0, "ְ����", wcf2));
		ws.addCell(new Label(2, 0, "����", wcf2));
		ws.addCell(new Label(3, 0, "�Ա�", wcf2));
		ws.addCell(new Label(4, 0, "���ڲ���", wcf2));
		if (pfzList != null && pfzList.size() > 0) {
			for (int i = 0; i < pfzList.size(); i++) {
				HashMap<String, String> map = pfzList.get(i);
				ws.addCell(new Label(4+i+1, 0, map.get("pfzmc"), wcf2));
			}
		}
		ws.addCell(new Label(4+pfzList.size()+1, 0, "�ܷ�", wcf2));
		ws.addCell(new Label(4+pfzList.size()+2, 0, "ѧԺ����", wcf2));
		ws.addCell(new Label(4+pfzList.size()+3, 0, "ȫУ����", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> rsMap = resultList.get(i);
				ws.addCell(new Label(0, 1+i, String.valueOf(i+1), wcf2));
				ws.addCell(new Label(1, 1+i, rsMap.get("zgh"), wcf2));
				ws.addCell(new Label(2, 1+i, rsMap.get("xm"), wcf2));
				ws.addCell(new Label(3, 1+i, rsMap.get("xbmc"), wcf2));
				ws.addCell(new Label(4, 1+i, rsMap.get("bmmc"), wcf2));
				for (int j = 0; j < pfzList.size(); j++) {
					HashMap<String, String> map = pfzList.get(j);
					ws.addCell(new Label(4+j+1, 1+i, rsMap.get("fs"+j), wcf2));
				}
				ws.addCell(new Label(4+pfzList.size()+1, 1+i, rsMap.get("zf"), wcf2));
				ws.addCell(new Label(4+pfzList.size()+2, 1+i, rsMap.get("xypm"), wcf2));
				ws.addCell(new Label(4+pfzList.size()+3, 1+i, rsMap.get("pm"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
}
	/** 
	 * @����:������Ŀid�������Σ�ְ���ţ�List��ѯѧ���԰����εĻ��ܴ�������б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��5�� ����3:43:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @param khdxrs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsdbzrhzList(String xmid, List<String> bzrList) {
		
		return dao.getXsdbzrhzList(xmid,bzrList);
	}
	/** 
	 * @����:����word�ļ���ѧ���԰����δ�ֵĻ��ܴ�ӡ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��5�� ����3:43:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsdbzrhzList
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getXsdbzrhzFile(List<HashMap<String, String>> xsdbzrhzList) {
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		//��list��¼���飬2��Ϊһ�飬�����µ�list
		List<HashMap<String, HashMap<String,String>>> list = new ArrayList<HashMap<String,HashMap<String,String>>>();
		
		int size = xsdbzrhzList.size();
		int i = 1;
		for(;i<size;i+=2){
			HashMap<String,HashMap<String,String>> map = new HashMap<String,HashMap<String,String>>();
			map.put("col1", xsdbzrhzList.get(i-1));
			map.put("col2", xsdbzrhzList.get(i));
			list.add(map);
		}
		if(i==size){
			HashMap<String,HashMap<String,String>> map = new HashMap<String,HashMap<String,String>>();
			map.put("col1", xsdbzrhzList.get(i-1));
			map.put("col2", new HashMap<String, String>());
			list.add(map);
		}
		
		data.put("list", list);
		
		String mbmc = "xsdbzrhzdy_" + Base.xxdm + ".xml";
		String fileName = "ѧ���԰����β������ܱ�_"+System.currentTimeMillis();
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//khgl", mbmc, fileName);
		
		return wordFile;
	}

}

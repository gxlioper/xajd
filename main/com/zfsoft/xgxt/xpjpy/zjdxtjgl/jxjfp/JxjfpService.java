/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-24 ����04:09:45 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.jxjfp;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ��ѧ���������һ����
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2018-7-24 ����04:08:47 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxjfpService extends SuperServiceImpl<JxjfpForm,JxjfpDao>{
	
	private JxjfpDao dao = new JxjfpDao();
	
	public JxjfpService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: ��ͳ����Ŀ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-24 ����05:40:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjxmList(JxjfpForm model){
		return dao.getPjxmList(model);
	}
	
	/**
	 * @����: ��ѧ���������һ�����ѯ�б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-24 ����07:42:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjmefpList(JxjfpForm model, User user)throws Exception{
		/*��ѯ��Ҫͳ�Ƶ�������Ŀ*/
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		return dao.getJxjmefpList(model,user,pjxmList);
	}
	
	/**
	 * @����: ��ѧ�����һ�������ݵ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-25 ����03:22:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File getJxjfpFile(JxjfpForm model, User user) throws Exception{
		
		/*��ͳ����Ŀ*/
		List<HashMap<String, String>> pjxmList = getPjxmList(model);
		/*����������ͷ*/
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("bmmc", "��������");
		
		for (int i = 0 , j = pjxmList.size() ; i < j ; i++){
			map.put("jx"+i, pjxmList.get(i).get("xmmc"));
		}
		
		map.put("jje", "�����");
		map.put("bmtzje", "�������");
		map.put("zrs", "ѧ������");
		
		/*����ҳ*/
		model.getPages().setPageSize(Integer.MAX_VALUE);
		/*�������*/
		List<HashMap<String,String>> dataList = dao.getJxjmefpList(model, user, pjxmList);
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	/**
	 * @����: ���Ż��ܵ���(�͸�ԭ��)
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-25 ����05:47:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File ffhzExport(JxjfpForm model, User user) throws Exception{
		
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
	
	/**
	 * @����: ���ҽ�ѧ����ܵ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-11 ����03:04:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportGjjxjhz(JxjfpForm model,OutputStream os,User user)throws Exception {
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(model.getXn());
		title.append("ѧ����ҽ�ѧ�𷢷Ż���");
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ܱ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		List<HashMap<String, String>> gjjxjList = dao.getGjjxjList(model);
		// ������
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.NONE);
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
}

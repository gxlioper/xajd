/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����05:05:25 
 */  
package com.zfsoft.xgxt.pjpy.tjcx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������� ͳ�Ʋ�ѯ ѧԺ��ͳ�Ʋ�ѯ 
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-5-16 ����05:09:00 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyhjtjAction   extends SuperAction {
	private XyhjtjService service = new XyhjtjService();
	/**
	 * @����:ѧ���񽱽��ͳ��  ��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-17 ����10:52:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getGyjltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		List<List<String>> gylist = service.shxjxyCxdc(user);
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		WritableCellFormat tempCellFormat = service.getTopCellStyle(ws);
		Label label = new Label(0,0,"ѧ���񽱽��ͳ��",tempCellFormat);
		ws.addCell(label);
		ws.mergeCells(0, 0, gylist.get(0).size()-1, 0);
		try {
			for (int i = 1; i < gylist.size()+1; i++) {
				List<String> list = gylist.get(i-1);
				for (int j = 0; j < list.size(); j++) {
					if(i==1){
						ws.setColumnView(j, 18);
						ws.addCell(new Label(j,i,list.get(j),tempCellFormat));
					}else{
						ws.addCell(new Label(j,i,list.get(j)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wwb.write();
			wwb.close();
		}
		return null;
	}
	
	
}

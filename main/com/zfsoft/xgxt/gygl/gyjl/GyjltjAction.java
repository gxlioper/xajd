/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-27 ����04:09:18 
 */  
package com.zfsoft.xgxt.gygl.gyjl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xsgzgl.gygl.gyjlxxglnew.GyjlxxglNewForm;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ��Ԣ������ϢAction
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-4-27 ����04:09:18 
 * @�汾�� V5.9.17
 * @�޸ļ�¼:   
 */

public class GyjltjAction  extends SuperAction {
	
	private GyjltjService service = new GyjltjService();
	
	/**
	 * @����:��Ԣ���� �Ϻ�Ϸ��ѧԺ ���ɻ��ܵ���
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-27 ����03:48:56
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
		GyjltjForm mm  = (GyjltjForm)form;
		List<List<String>> gylist = service.getGyjltj(user, mm.getSearchModel());
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			for (int i = 0; i < gylist.size(); i++) {
				List<String> list = gylist.get(i);
				for (int j = 0; j < list.size(); j++) {
					ws.addCell(new Label(j,i,list.get(j)));
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

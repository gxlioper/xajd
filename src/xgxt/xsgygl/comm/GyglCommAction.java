package xgxt.xsgygl.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.xsgygl.GyglTyForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ͨ�ð汾��Ԣ����-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class GyglCommAction extends BasicAction {

	/**
	 * ѧ������_��Ŀά��_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxfpSsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglCommService service = new GyglCommService();
		GyglTyForm myForm = (GyglTyForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gygl_cxfp_ss";
		// ����
		String realTable = "ssfpb";
		myForm.setTableName(realTable);
		// ����·��
		String path = "gygl_cxfp_ss.do";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ==================ִ�г������� ==================
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = service.delSsfp(myForm);
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ==================ִ�������������� ==================
		if ("plcx".equalsIgnoreCase(doType)) {
			boolean result = service.delSsfpPl(myForm);
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "nj", "xymc", "xqmc", "ldmc", "cs",
					"qsh" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "cxfp_ss");
		// =================end ===================

		return mapping.findForward("cxfpSsManage");
	}

}
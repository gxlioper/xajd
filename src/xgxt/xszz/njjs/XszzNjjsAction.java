package xgxt.xszz.njjs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Ͼ���ʦѧ������-action��
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

public class XszzNjjsAction extends BasicAction {

	/**
	 * ѧ����������ѧ����Ϣά�������޸Ĺ��ܣ����ݴӲ���ͬ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzNjjsService service = new XszzNjjsService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================== ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType =  request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_njjs_xfxx";
		// ����
		String realTable = "njjs_xfxxb";
		// ����·��
		String path = "xszz_xfxx.do";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end ===================
		
		// ==================�ж�ģ��,Ȩ��======================

		// ��½�û�ΪѧԺ
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}

		// =================end ===================
		
		// ==================ͬ������ ==================
		if ("tb".equalsIgnoreCase(doType)) {
			boolean flag = service.tbXfxx();
			message = flag ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "xh", "xm", "xb", "xn", "nd", "nj",
					"xymc", "zymc", "bjmc", "xfsfxm", "xfyjje", "xfsjje", "xfsfqf", };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			StudentInfoService siService = new StudentInfoService();
			String expColumn = request.getParameter("mappingItems");
			String[] expColumns = {};
			if(StringUtils.isNotNull(expColumn)){
				expColumns = siService.getDczd(expColumn).split(",");
			}
			expPageData(request, response, null, tableName, expColumns);
			return mapping.findForward("");
		}
		// =================end ===================
		

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message" };
		String[] qtzdz = new String[] { message };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "xfxx");
		// =================end ===================

		return mapping.findForward("xfxxManage");
	}
}

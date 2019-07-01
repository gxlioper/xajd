package xgxt.xsgygl.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.czxx.PjpyCzxxActionForm;
import xgxt.pjpy.czxx.jxj.JxjModel;
import xgxt.pjpy.czxx.jxj.JxjService;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.rcsw.gzdx.RcswGzdxService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �й��ش�Ԣ����-action��
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

public class GyglZgddAction extends BasicAction {


	/**
	 * ��Ԣ����_��Դ��ά��_ͼ��ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rykTbtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglZgddService service = new GyglZgddService();
		GyglZgddModel model = new GyglZgddModel();

		// ---------------- ����ֵ ----------------
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// -----------------end-----------------------

		// ===================Ȩ�޿���========================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// ========================end ========================

		// ----------------ִ�в�ѯ���� ----------------
		if (search) {
			List<Object> rs = service.setTbtj(myForm);
			request.setAttribute("rs", rs);
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "fyk_tbtj");
		// ----------------end ----------------
		request.setAttribute("flg", request.getParameter("flg"));
		return mapping.findForward("rykTbtj");
	}
}

package xgxt.rcsw.hcyhk.hcyhkff;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.szyqxy.zhszcp.PjpySzyqxyZhszcpDAO;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.xszgl.XszglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class HcyhkffAction extends BasicAction {

	/**
	 * ���Żݿ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		RcswForm myForm = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();
		CommService commService = new CommService();

		String doType = request.getParameter("doType");
		String ffsj = request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		//֮ǰ����Ϊ��ǰ�û�����ѧԺ����һ���û��ٴ�������»��ж��ѧԺ����ѡ��
		//user.setUserDep(myForm.getQueryequals_xydm());
		myForm.setUser(user);
		
		String message = "";

		// -----------ȡ������-------------
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.delHcyhkff(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}

		// -----------��������---------
		if ("plff".equalsIgnoreCase(doType)) {
			boolean flag = service.plHcyhkff(myForm);
			message = flag ? MessageInfo.MESSAGE_EXTEND_SUCCESS
					: MessageInfo.MESSAGE_EXTEND_FALSE;
			request.setAttribute("message", message);
		}

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr("hcyhkff", rForm)
				.size() - 1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);

		myForm.setUser(user);

		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}

		if (Base.isNull(myForm.getNd())) {
			myForm.setNd(Base.currNd);
		}

		rForm.setRsArrList((ArrayList<String[]>) service.queryHcyhkff(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============

		request.setAttribute("path", "hcyhkff.do?method=hcyhkffManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("topTr", service.getTopTr("hcyhkff", rForm));
		return mapping.findForward("hcyhkffManage");
	}

	/**
	 * ѧ��֤ע��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkffUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		User user = getUser(request);
		RcswForm myForm = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);

		// =================ѧ��֤ע�� begin==================
		String message = "";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addHcyhk(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================ѧ��֤ע�� end==================

		request.setAttribute("path", "hcyhkff.do?method=hcyhkffManage");
		FormModleCommon.commonRequestSet(request);

		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xh", xh);
		request.setAttribute("doType", "add");
		return mapping.findForward("hcyhkffUpdate");
	}

	/**
	 * ѧ��֤ע����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkffOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();

		RcswForm myForm = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();

		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		XsxxglService stuService = new XsxxglService();

		HashMap<String, String> rs = new HashMap<String, String>();
		String message = "";
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setPkValue(pkValue);
			boolean flag = service.updateHcyhk(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		// ѧ��֤ע����Ϣ
		myForm.setPkValue(pkValue);
		rs = service.getHcyhkMap(myForm);

		request.setAttribute("path", "hcyhkff.do?method=hcyhkffManage");
		FormModleCommon.commonRequestSet(request);

		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("nd", rs.get("nd"));
		request.setAttribute("xh", rs.get("xh"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		request.setAttribute("pkValue", pkValue);
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		return mapping.findForward("hcyhkffUpdate");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswForm model = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();
		//���ɸ߼���ѯ����
/*		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);*/
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getExportList(model,user);//��ѯ�����м�¼������ҳ
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}

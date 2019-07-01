package xgxt.pjpy.cdfz;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszService;
import xgxt.utils.ExcelMethods;
import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * �ɶ���֯��������ACTION
 * lt 
 * 2011-11-23
 */
public class CdfzPjpyAction extends BasicAction {

	/**
	 * �������Ž������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user=getUser(request);
		CdfzPjpyForm myForm = (CdfzPjpyForm) form;
		CdfzPjpyService service = new CdfzPjpyService();

		// ͳ������
		String tjlx = myForm.getTjlx();
		String xn = myForm.getXn();
		String xq = myForm.getXq();

		// ��һ�η���ʱ��ͳ������Ĭ����ΪXY
		if (Base.isNull(tjlx)) {
			myForm.setTjlx("xy");
		}

		if (Base.isNull(xn)) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(xq)) {
			myForm.setXq(Base.currXq);
		}

		myForm.setUser(user);
		List<String[]> pjhzList = service.getPjhzList(myForm);
		
		request.setAttribute("tjlx", myForm.getTjlx());
		request.setAttribute("xmList", service.getPjpyXmList(myForm));
		request.setAttribute("rs", pjhzList);
		request.setAttribute("path", "cdfzPjpy.do?method=jxhzManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("jxhzManage");
	}

	/**
	 * �������Ÿ��˻���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grhjhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdfzPjpyForm pjForm = (CdfzPjpyForm) form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		String xn = pjForm.getPjxn();
		String xq = pjForm.getPjxq();


		if (Base.isNull(xn)) {
			pjForm.setPjxn(Base.currXn);
		}

		if (Base.isNull(xq)) {
			pjForm.setPjxq(Base.currXq);
		}
		
		if ("xy".equalsIgnoreCase(user.getUserType())) {
			pjForm.setXydm(user.getUserDep());
		}
		
		CdfzPjpyService service = new CdfzPjpyService();
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String, String>>(){};
		List<String[]> rsList = new ArrayList<String[]>(){};
		//��ѯ����
		//if ("query".equalsIgnoreCase(request.getParameter("doType"))) {
			rsList = service.queryXshjmxResult(pjForm, user);
			titList = service.queryXshjmxTitle(rsList);
		//}
		
		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "" + titList.size();
		commSetting.setShowNum(showNum);
		rForm.setColList(service.queryXshjmxTop(rsList));
		rForm.setCommSetting(commSetting);

		// ===============ͨ������ end ============
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		//��Ų�ѯ�����
		request.setAttribute("rsArrList", rsList);
		request.setAttribute("topTr", titList);
		// =================== end ===================
		//ҳ��TITLE  begin
		request.setAttribute("path", "cdfzPjpy.do?method=grhjhzManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xmList", service.getPjpyXmList(pjForm));
		request.setAttribute("showNum", showNum);
		// end
		return mapping.findForward("grhjhzManage");
	}
	
	/** 
	 * Method printGfjyf
	 * �������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public ActionForward printJxhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		CdfzPjpyForm myForm = (CdfzPjpyForm) form;
		CdfzPjpyService service = new CdfzPjpyService();

		// ͳ������
		String tjlx = myForm.getTjlx();
		String xn = myForm.getXn();
		String xq = myForm.getXq();

		// ��һ�η���ʱ��ͳ������Ĭ����ΪXY
		if (Base.isNull(tjlx)) {
			myForm.setTjlx("xy");
		}

		if (Base.isNull(xn)) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(xq)) {
			myForm.setXq(Base.currXq);
		}

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response);
		
		myForm.setUser(user);
		service.printJxhz(myForm, request, wwb);
		return null;
	}
	
	/**
	 * ����ѧ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printGrhjhzManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CdfzPjpyForm pjForm = (CdfzPjpyForm) form;
		User user = getUser(request);
		
		String xn = pjForm.getPjxn();
		String xq = pjForm.getPjxq();


		if (Base.isNull(xn)) {
			pjForm.setPjxn(Base.currXn);
		}

		if (Base.isNull(xq)) {
			pjForm.setPjxq(Base.currXq);
		}
		
		if ("xy".equalsIgnoreCase(user.getUserType())) {
			pjForm.setXydm(user.getUserDep());
		}
		
		CdfzPjpyService service = new CdfzPjpyService();

		List<String[]> rsList = new ArrayList<String[]>() {
		};

		rsList = service.queryXshjmxResultExport(pjForm, user);

		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response);
		ExcelMB mb = new ExcelMB();
		
		WritableSheet ws = wwb.createSheet("ѧ������ϸ���ܱ�", 1);
		
		mb.printTitle(ws, "ѧ������ϸ���ܱ�", rsList.get(0).length, 600);
		
		// ��ȡҪ�������ݵ���ʽ
		WritableCellFormat format = ExcelMB.getWritableCellFormat(10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		mb.writeDataToCellByIterator(ws, rsList, 1, format);
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return null;
	}
}

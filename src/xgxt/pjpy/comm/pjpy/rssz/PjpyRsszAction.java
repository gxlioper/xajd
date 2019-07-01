package xgxt.pjpy.comm.pjpy.rssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */

public class PjpyRsszAction extends BasicExtendAction{
	
	/**
	 * �������������淽�� 
	 */
	public ActionForward rsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		User user = getUser(request);
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		//������������������ó�ʼ����ϵͳ�Զ���ʼ����
		if (rsszService.isRsszCsh()){
			rsszService.initRssz();
		}
		
		if(!rsszService.isTbRyqd()){
			String msg = "��������������δִ�й���ͬ�����š��͡�ͬ����Ա���Ĳ������޷������������ã����ڡ�������Աȷ����ģ��������á���";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		pjForm.setPjxn(PjxtszModel.pjxtszModel.getPjxn());
		pjForm.setPjnd(PjxtszModel.pjxtszModel.getPjnd());
		pjForm.setPjxq(PjxtszModel.pjxtszModel.getPjxq());
		
		// �ܷ���������Ȩ��
		boolean kfsz = false;
		// �������÷�ʽ
		String rsszfs = PjxtszModel.pjxtszModel.getRsszfs();
		
		if("xx".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus());
		}else if("xxxy".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus());
		}
		
		setWriteAbleAndTitle(request, "pjpy_xmsz_rssz.do");
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("kfsz", kfsz);
		request.setAttribute("xmxzList", rsszService.getXmxzList()); // ��Ŀ����
		request.setAttribute("xmfwList", rsszService.getXmfwList()); // ��Ŀ��Χ
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		// �ɷ���������������Ŀ
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("user", user);
		return mapping.findForward("rsszManage");
	}
	/**
	 * �������������淽�� 
	 */
	public ActionForward rsszManageFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		User user = getUser(request);
		
		String rssz = request.getParameter("rssz");
		boolean sfrssz = "��".equalsIgnoreCase(rssz) ? false:true;
		request.setAttribute("sfrssz", sfrssz);
		request.setAttribute("rssz", rssz);
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		//������������������ó�ʼ����ϵͳ�Զ���ʼ����
		if (rsszService.isRsszCsh()){
			rsszService.initRssz();
		}
		
		if(!rsszService.isTbRyqd()){
			String msg = "��������������δִ�й���ͬ�����š��͡�ͬ����Ա���Ĳ������޷������������ã����ڡ�������Աȷ����ģ��������á���";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		pjForm.setPjxn(PjxtszModel.pjxtszModel.getPjxn());
		pjForm.setPjnd(PjxtszModel.pjxtszModel.getPjnd());
		pjForm.setPjxq(PjxtszModel.pjxtszModel.getPjxq());
		
		// �ܷ���������Ȩ��
		boolean kfsz = false;
		// �������÷�ʽ
		String rsszfs = PjxtszModel.pjxtszModel.getRsszfs();
		
		if("xx".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus());
		}else if("xxxy".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus());
		}
		
		setWriteAbleAndTitle(request, "pjpy_xmsz_rssz.do");
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("kfsz", kfsz);
		request.setAttribute("xmxzList", rsszService.getXmxzList()); // ��Ŀ����
		request.setAttribute("xmfwList", rsszService.getXmfwList()); // ��Ŀ��Χ
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		// �ɷ���������������Ŀ
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("user", user);
		return mapping.findForward("rsszManageFlow");
	}
	
	/**
	 * �����������÷�Χ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszForSzfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String szfw = request.getParameter("szfw");
		String flow = request.getParameter("flow");
		PjpyRsszService rsszService = new PjpyRsszService();
		
		//������������������ó�ʼ����ϵͳ�Զ���ʼ����
		if (rsszService.isRsszCsh()){
			rsszService.initRssz();
		}
		
		PjpyRsszForm model = (PjpyRsszForm)form;
		List<HashMap<String, String>> topTr = rsszService.getTopTr(szfw); 
		List<String[]> rs = null;
		
		if("xy".equalsIgnoreCase(szfw)){  // ѧԺ���÷�Χ
			rs = rsszService.searchForXy(model);
		}else if("zy".equalsIgnoreCase(szfw)){  // רҵ���÷�Χ
			rs = rsszService.searchForZy(model);
		}else if("bj".equalsIgnoreCase(szfw)){  // �༶���÷�Χ
			rs = rsszService.searchForBj(model);
		}else{
			rs = rsszService.searchForNj(model);
		}
		
		// ����Ŀ����model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(PjxtszModel.pjxtszModel.getPjxn()+PjxtszModel.pjxtszModel.getPjxq()
								  +PjxtszModel.pjxtszModel.getPjnd()+model.getXmdm());
		
		// ��ѯ���ļ�¼�е�����
		Integer count = 0;
		for(String[] record: rs){
			Integer qdrs = StringUtils.isNull(record[record.length-1]) ? 0 : Integer.parseInt(record[record.length-1]);
			count += qdrs;
		}
		
		request.setAttribute("rssz", "true");
		request.setAttribute("count", count);
		request.setAttribute("syrs", rsszService.getSyrs(xmszModel, szfw));	// ʣ������
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("size", topTr.size()+1);
		request.setAttribute("lastXmdm", model.getXmdm());	// ��һ�εĲ�ѯ��Ŀ
		request.setAttribute("lastSzfw", model.getSzfw());	// ��һ�ε����÷�Χ
		if("flow".equals(flow)){
			return rsszManageFlow(mapping, form, request, response);
		}
		return rsszManage(mapping, form, request, response);
	}
	
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		
		String szfw = pjForm.getSzfw();
		String xmdm = pjForm.getXmdm();
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		// �������÷�Χ������ö���
		List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
		request.setAttribute("fpdxList", fpdxList);
		
//		if("xy".equalsIgnoreCase(szfw)){ //���÷�ΧΪѧԺ
//			request.setAttribute("xyList", rsszService.getAllXy(pjForm));
//		}else if("zy".equalsIgnoreCase(szfw)){ // ���÷�ΧΪרҵ
//			
//		}else if("bj".equalsIgnoreCase(szfw)){ // ���ⷶΧΪ�༶
//			List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
//			request.setAttribute("fpdxList", fpdxList);
//		}else{  // ���÷�ΧΪ�꼶
//			request.setAttribute("njList", rsszService.getAllNj(pjForm));
//		}
		
		// ��ȡ������������
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		pjForm.setPjxn(pjxn);
		pjForm.setPjnd(pjnd);
		pjForm.setPjxq(pjxq);
		
		// ��Ŀ����model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+pjForm.getXmdm());
		// ��������
		String rssx = xmszModel.getRssx();
		
		if(StringUtils.isNotNull(rssx)){
			request.setAttribute("rssx", rssx);
		}
		
		FormModleCommon.setNdXnXqList(request);
		
		// �ɷ���������������Ŀ
		request.setAttribute("user", user);
		request.setAttribute("szfw", szfw);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("title", "������������");
		return mapping.findForward("rsszUpdate");
	}
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		
		String xmdm = pjForm.getXmdm();
		
		
		String szfw = pjForm.getSzfw();
		
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		
		
		// ��ȡ������������
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		pjForm.setPjxn(pjxn);
		pjForm.setPjnd(pjnd);
		pjForm.setPjxq(pjxq);
		// ��Ŀ����model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+pjForm.getXmdm());
		szfw = xmszModel.getKzfw();
		pjForm.setSzfw(szfw);
		// �������÷�Χ������ö���
		List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
		request.setAttribute("fpdxList", fpdxList);
		// ��������
		String rssx = xmszModel.getRssx();
		
		
		if(StringUtils.isNotNull(rssx)){
			request.setAttribute("rssx", rssx);
		}
		
		FormModleCommon.setNdXnXqList(request);
		
		// �ɷ���������������Ŀ
		request.setAttribute("user", user);
		request.setAttribute("szfw", szfw);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("title", "������������");
		return mapping.findForward("rsszFlow");
	}
	
	/**
	 * �������ã���Ҫ���ñ���С��λ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		String doType = request.getParameter("doType");
		
		PjpyRsszService service = new PjpyRsszService();
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveCssz(pjForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("title", "��������");
		pjForm.setBlxs(service.getCssz());
		
		// ��ȡ������������
		request.setAttribute("jbszxx", PjxtszModel.pjxtszModel);
		return mapping.findForward("rsszCssz");
	}
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveRssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		PjpyRsszService rsszService = new PjpyRsszService();
		
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		
		boolean result = rsszService.saveRssz(pkValues,pjForm);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return rsszForSzfw(mapping, pjForm, request, response);
	}
	
	/**
	 * ��ʼ����������
	 */
	public ActionForward initData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm model = (PjpyRsszForm) form;
		PjpyRsszService service = new PjpyRsszService();
		
		String xmdm = model.getXmdm();
		boolean result = false;
		
		if (StringUtils.isNotNull(xmdm)){
			//��ĳ����Ŀ��ʼ����������
			result = service.initRsszByXmdm(xmdm);
		} else {
			//��������ȫ����ʼ��
			result = service.initRssz();
		}
		
		response.setContentType("text/html;charset=gbk"); //ajax���󷵻�����ת�룬�������������
		response.getWriter().print(result);
		
		return null;
	}
	
	/**
	 * ����ȷ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQdrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm) form;
		PjpyRsszService servcie = new PjpyRsszService();
		
		String message = servcie.saveQdrs(pjForm) ? "����ɹ���" : "����ʧ�ܣ�";
		
		request.setAttribute("message", message);
		return rsszForSzfw(mapping, form, request, response);
	}
	
	/**
	 * ����Ĭ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQdrsFromMrrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyRsszService service = new PjpyRsszService();
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		String message = service.saveQdrsFromMrrs(pkValues) ? "�����ɹ���" : "����ʧ�ܣ�";
		
		request.setAttribute("message", message);
		return rsszForSzfw(mapping, form, request, response);
	}
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszFlowUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		
		String xmdm = pjForm.getXmdm();
		
		
		String szfw = pjForm.getSzfw();
		
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		
		
		// ��ȡ������������
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		pjForm.setPjxn(pjxn);
		pjForm.setPjnd(pjnd);
		pjForm.setPjxq(pjxq);
		// ��Ŀ����model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+pjForm.getXmdm());
		szfw = xmszModel.getKzfw();
		pjForm.setSzfw(szfw);
		// �������÷�Χ������ö���
		List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
		request.setAttribute("fpdxList", fpdxList);
		// ��������
		String rssx = xmszModel.getRssx();
		
		
		if(StringUtils.isNotNull(rssx)){
			request.setAttribute("rssx", rssx);
		}
		
		FormModleCommon.setNdXnXqList(request);
		
		// �ɷ���������������Ŀ
		request.setAttribute("user", user);
		request.setAttribute("szfw", szfw);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("title", "������������");
		return new ActionForward("/pjpy_ty_sjsz.do?method=sjszUpdate&doType=''",false);
	}
}

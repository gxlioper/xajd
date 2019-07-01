package xsgzgl.xsxx.general.lsxs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.comm.CommDAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��ʷѧ��_ͨ��_Init��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxLsxsInit {

	/**
	 * ��ʷѧ��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initLsxs(RequestForm rForm, XsxxGeneralForm myForm, User user,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		// ����·��
		String path = "stu_info_query.do?method=stuInfo";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ѧ��
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		// �ж��Ƿ��ѱ�ҵ(������У����ʷѧ��)
		String sfzxk = request.getParameter("sfzxk");
		// �����ֶ�
		String[] qtzd = new String[] { "sfzxk" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { sfzxk };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		if ("detail".equalsIgnoreCase(doType)) {// ��ϸ
			try {
				defaultLsxxDetail(myForm, user, request);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ʷѧ����ϸ
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void defaultLsxxDetail(XsxxGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		BasicService basicService = new BasicService();
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		XsxxglService xsxxglService = new XsxxglService();
		XsxxZxxsModel model = new XsxxZxxsModel();

		model.setXh(myForm.getXh());

		Map<String, String> map = service.getZxxsInfo(model, user);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		List<HashMap<String, String>> pages = xsxxglService.getXsPages();

		map.put("jg", new CommDAO().getSydmc(map.get("jg"), "/", "/"));
		map.put("syd", new CommDAO().getSydmc(map.get("syd"), "/", "/"));

		for (Map.Entry<String, String> entry : map.entrySet()) {

			String paramName = entry.getKey();
			String value = basicService.replaceHtml(entry.getValue());

			paramMap.put(paramName, value);
		}

		request.setAttribute("rs", paramMap);
		request.setAttribute("pages", pages);
	}
}

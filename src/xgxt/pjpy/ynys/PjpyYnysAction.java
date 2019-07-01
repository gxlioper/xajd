
package xgxt.pjpy.ynys;

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

import common.Globals;
import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����������������Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public class PjpyYnysAction extends CommonAction {

	private String xydm = "";
	private  String zydm = "";
	private String nj = "";
	
	/**
	 * �Ƚ��༶����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xxmc = session.getAttribute("xxmc").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjService service = new XjbjService();
		HashMap<String, String> rsMap =service.getJxjsqsj();
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("tit", xxmc + "�Ƚ��༯��ǼǱ�");//��ͷ
		request.setAttribute("xn", rsMap.get("jxjsqxn"));//��ѧ������ѧ��
		return mapping.findForward("xjbjsq");
	}

	/**
	 * �Ƚ��༶����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjModel model = new XjbjModel();//�Ƚ��༶MODEL
		BeanUtils.copyProperties(model, ynysForm);
		XjbjService service = new XjbjService();
		HashMap<String, String> rsMap =service.getJxjsqsj();
		boolean bFlag = service.saveYnys_xjbjb(model, request);//������Ϣ
		if (bFlag) {
			request.setAttribute("inserted", "yes");//����ɹ�
		} else {
			request.setAttribute("inserted", "no");//����ʧ��
		}
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("tit", "��������ѧԺ�Ƚ��༯��ǼǱ�");//��ͷ
		request.setAttribute("xn", rsMap.get("jxjsqxn"));//��ѧ������ѧ��
		ynysForm.setBzr(DealString.toGBK(ynysForm.getBzr()));
		ynysForm.setBzryj(DealString.toGBK(ynysForm.getBzryj()));
		ynysForm.setBjdbqk(DealString.toGBK(ynysForm.getBjdbqk()));
		ynysForm.setFdyyj(DealString.toGBK(ynysForm.getFdyyj()));
		return mapping.findForward("xjbjsq");
	}
	
	/**
	 * �Ƚ��༶�����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printXjbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		/*String bjdm = request.getParameter("bjdm");
		XjbjService service = new XjbjService();
		String modelPath = "";//���ݱ��ģ��
		modelPath = servlet.getServletContext().getRealPath("")+"/print/ynys/ynys_xjbjb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printXjbj(wwb, bjdm);//�Ƚ��༶��ӡ
		 */
		HttpSession session = request.getSession();
		String xxmc = session.getAttribute("xxmc").toString();
		String pkValue = request.getParameter("pkValue");
		XjbjService service = new XjbjService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.printXjbj(pkValue);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("tit", xxmc + "�Ƚ��༯��ǼǱ�");
		return mapping.findForward("xjbjprint");
	}
	
	/**
	 * �Ƚ��༶���Ĭ��ҳ��ֶ������:Ժϵ,ѧУ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("�Ƚ��༶", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("�����ҵ��", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		XjbjService service = new XjbjService();
		List<HashMap<String, String>> shxmList = service.getShxmList();//�����Ŀ�б�
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("shList", shxmList);
		ynysForm.setXn(service.getJxjsqsj().get("jxjsqxn"));
		return mapping.findForward("xjbjsh");
	}
	
	/**
	 * �Ƚ��༶��˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjqry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXn(request.getParameter("xn"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("�Ƚ��༶", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("�����ҵ��", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		XjbjModel model = new XjbjModel();//�Ƚ��༶MODEL
		BeanUtils.copyProperties(model, ynysForm);
		XjbjService service = new XjbjService();
		List<HashMap<String, String>> shxmList = service.getShxmList();//�����Ŀ�б�
		List<HashMap<String, String>> titList = service.getXjbjTitle(userType, realTable);//�Ƚ��༶��ͷ
		List<String[]> rsList = service.getXjbjList(model, userType, realTable);//�Ƚ��༶��ѯ���
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("shList", shxmList);
		appendQryResult(request, titList, rsList);//���ز�ѯ��ͷ,���,��¼��
		ynysForm.setXn(service.getJxjsqsj().get("jxjsqxn"));
		return mapping.findForward("xjbjsh");
	}
	
	/**
	 * �Ƚ��༶��˽���������(Ժϵ,ѧУ)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjShres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		XjbjService service = new XjbjService();
		String tgRes = request.getParameter("param1");//��˱��
		ynysForm.setXn(request.getParameter("xn"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("�Ƚ��༶", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("�����ҵ��", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		String[] cbv = ynysForm.getCbv();//��������б�
		tgRes = service.getShjg(tgRes);
		String sShresult = service.xjbjShResult(cbv, userType, tgRes, request, realTable);//��˽��
		if (StringUtils.isNull(sShresult)) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
			request.setAttribute("failinfo", "��" + sShresult + "����¼���ʧ��!");
		}
		List<HashMap<String, String>> shxmList = service.getShxmList();//�����Ŀ�б�
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("shList", shxmList);
		ynysForm.setXn(service.getJxjsqsj().get("jxjsqxn"));
		return mapping.findForward("xjbjsh");
	}
	
	/**
	 * �Ƚ��༶���������ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjShone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		String realTable = request.getParameter("realTable");
		HashMap<String, String> rsMap = service.viewXjbjshOne(pkValue, userType, realTable);//��ʾ��Ϣ
		if (!StringUtils.isNull(rsMap.get("yesno"))) {
			ynysForm.setYesNo(rsMap.get("yesno"));
		}
		ynysForm.setYj(rsMap.get("yj"));
		ynysForm.setXyyj(rsMap.get("xyyj"));
		ynysForm.setJytyj(rsMap.get("jytyj"));
		request.setAttribute("shList", service.getShList());//����б�
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rsMap);
		request.setAttribute("pkValue", pkValue);
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual("ynys_yxbysb", realTable)) {
			return mapping.findForward("yxbysshone");
		}
		return mapping.findForward("xjbjshone");
	}
	
	/**
	 * �Ƚ��༶������˽��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjShoneres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.saveXjbjshone(pkValue, userType, ynysForm
				.getYesNo(), ynysForm.getYj(), ynysForm.getXyyj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("�Ƚ��༶", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("�����ҵ��", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		request.setAttribute("shList", service.getShList());//����б�
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("userType", userType);
		ynysForm.setYj("");
		ynysForm.setXyyj("");
		
		return mapping.findForward("xjbjshone");
	}
	
	/**
	 * �����ҵ��������˽��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysShoneres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.saveYxbysshone(pkValue, userType, ynysForm
				.getYesNo(), ynysForm.getYj(), ynysForm.getJytyj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("shList", service.getShList());//����б�
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("userType", userType);
		ynysForm.setYj("");
		ynysForm.setJytyj("");
		return mapping.findForward("yxbysshone");
	}
	
	/**
	 * �Ƚ��༶��������ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
//		ѧ���û���Ȩ�����ҳ��
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)) {
			String msg = "����Ȩ���ʸ�ҳ�棬��ȷ�ϣ�";
		    request.setAttribute("yhInfo", msg);
		    return new ActionForward("/yhInfo.do", false);
		}
		
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//�㽭��
			return new ActionForward("/zjlgPjpy.do?method=xjbjDefault",false);
		}
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){ //TODO ������һְҵ����ѧԺ			
			return new ActionForward("/nbtyWmbj.do?method=nbtyResultWmbj",false);
		}
		if(Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)){//����ְҵ����ѧԺ			
			return new ActionForward("/pjpy_hzy_xjch.do?method=xjbjsqQry",false);
		}
		if (StringUtils.isEqual("xy", userType)) {//ѧԺ�û�
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		
		if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			appendTableXx(request, "view_ynys_xjbj", "ynys_xjbjb");//����,��ͼ��
		} else {
			appendTableXx(request, "view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");//����,��ͼ��
		}
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("xjbjsqqry");
	}
	
	/**
	 * �Ƚ��༶��������ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSqqryres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		XjbjService service = new XjbjService();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {//ѧԺ�û�
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjModel model = new XjbjModel();
		BeanUtils.copyProperties(model, ynysForm);
		List<HashMap<String, String>> titList = service.getXjbjQryTitle(xxdm);//��ѯ��ͷ
		List<String[]> rsList = service.getXjbjQryResult(model, xxdm);
		appendQryResult(request, titList, rsList);//����б�
		if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			appendTableXx(request, "view_ynys_xjbj", "ynys_xjbjb");//����,��ͼ��
		} else {
			appendTableXx(request, "view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");//����,��ͼ��
		}
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("xjbjsqqry");
	}
	
	/**
	 * �Ƚ��༶�޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String xxdm = StandardOperation.getXxdm();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		XjbjService service = new XjbjService();
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			rsMap = service.nbzyXjbjxx(pkValue);
		} else if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			rsMap = service.viewXjbjxx(pkValue);// ��ʾ��ϢzjjdXjbjxx
		} else if (Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(xxdm)) {
			rsMap = service.zjjdXjbjxx(pkValue);// ��ʾ��Ϣ
		} else {
			rsMap = service.nbzyXjbjxx(pkValue);
		}
		if (rsMap != null) {
			ynysForm.setBjdbqk(rsMap.get("bjdbqk"));
			ynysForm.setBjrs(rsMap.get("bjrs"));
			ynysForm.setBzr(rsMap.get("bzr"));
			ynysForm.setBzryj(rsMap.get("bzryj"));
			ynysForm.setFdyyj(rsMap.get("fdyyj"));
			ynysForm.setBhgqs(rsMap.get("bhgqs"));
			ynysForm.setBjry(rsMap.get("bjry"));
			ynysForm.setYwcf(rsMap.get("ywcf"));
			ynysForm.setTzs(rsMap.get("tzs"));
			ynysForm.setZysj(rsMap.get("zysj"));
			ynysForm.setXn(rsMap.get("xn"));
			ynysForm.setXsrs(rsMap.get("xsrs"));
			ynysForm.setBzxm(rsMap.get("bzxm"));
			ynysForm.setBz(rsMap.get("bz"));
			ynysForm.setJtch(rsMap.get("jtch"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rsMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xjbjmodi");
	}
	
	/**
	 * �Ƚ����޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		XjbjModel model = new XjbjModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.saveXjbjxx(pkValue, model, request);//�����޸���Ϣ
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		} else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		ynysForm.setBzr("");
		ynysForm.setBjdbqk("");
		ynysForm.setBjrs("");
		ynysForm.setBzryj("");
		ynysForm.setFdyyj("");
		ynysForm.setZysj("");
		ynysForm.setBjry("");
		ynysForm.setBzxm("");
		ynysForm.setBzr("");
		ynysForm.setTzs("");
		ynysForm.setBz("");
		ynysForm.setJtch("");
		request.setAttribute("rs", new HashMap<String, String>());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xjbjmodi");
	}
	
	/**
	 * �Ƚ��༶ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {//ѧԺ�û�
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjService service = new XjbjService();
		String sJg = service.delXjbjxx(ynysForm.getCbv(), request);//ɾ����Ϣ
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "��" + sJg + "����¼ɾ��ʧ��");
		}
		if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			appendTableXx(request, "view_ynys_xjbj", "ynys_xjbjb");//����,��ͼ��
		} else {
			appendTableXx(request, "view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");//����,��ͼ��
		}
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("xjbjsqqry");
	}
	
	/**
	 * �����ҵ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		YxbysService service = new YxbysService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rsMap = service.getJxjsqsj();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (StringUtils.isEqual("stu", userType) || StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		}
		if (!StringUtils.isNull(xh)) {
			rs = service.stuDetailsInfo(xh);//������Ϣ
			ynysForm.setMz(rs.get("mz"));
			ynysForm.setZzmm(rs.get("zzmm"));
			ynysForm.setSyd(rs.get("syd"));
			ynysForm.setJtdz(rs.get("jtdz"));
		}
		request.setAttribute("tit", "����ʡ��ͨ����רѧУ" + rsMap.get("jxjsqnd")
				+ "�������ҵ���ǼǱ�");//��ͷ
		request.setAttribute("xn", rsMap.get("jxjsqxn"));
		appendProperties(request, xydm, zydm, nj);//�����б�
		request.setAttribute("rs", rs);
		return mapping.findForward("yxbyssq");
	}
	
	/**
	 * �����ҵ�����뱣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXn(request.getParameter("xn"));
		YxbysService service = new YxbysService();
		HashMap<String, String> rsMap = service.getJxjsqsj();
		String xh = request.getParameter("xh");
		if (StringUtils.isEqual("stu", userType) || StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		}
		HashMap<String, String> rs = service.stuDetailsInfo(xh);//������Ϣ
		YxbysModel model = new YxbysModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.saveYxbysxx(model, request);//������Ϣ
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		ynysForm.setMz(DealString.toGBK(ynysForm.getMz()));
		ynysForm.setZzmm(DealString.toGBK(ynysForm.getZzmm()));
		ynysForm.setSyd(DealString.toGBK(ynysForm.getSyd()));
		ynysForm.setJtdz(DealString.toGBK(ynysForm.getJtdz()));
		ynysForm.setBjyj(DealString.toGBK(ynysForm.getBjyj()));
		ynysForm.setYxsj(DealString.toGBK(ynysForm.getYxsj()));
		ynysForm.setJkzk(DealString.toGBK(ynysForm.getJkzk()));
		request.setAttribute("tit", "����ʡ��ͨ����רѧУ" + rsMap.get("jxjsqnd")
				+ "�������ҵ���ǼǱ�");//��ͷ
		request.setAttribute("xn", rsMap.get("jxjsqxn"));
		appendProperties(request, xydm, zydm, nj);//�����б�
		request.setAttribute("rs", rs);
		return mapping.findForward("yxbyssq");
	}
	
	/**
	 * �����ҵ�������ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyssqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);//�����б�
		appendTableXx(request, "view_ynys_yxbys", "ynys_yxbysb");//����,��ͼ��
		return mapping.findForward("yxbysqry");
	}
	
	/**
	 * �����ҵ�������ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyssqQryres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		YxbysService service = new YxbysService();
		YxbysModel model = new YxbysModel();
		BeanUtils.copyProperties(model, ynysForm);
		List<HashMap<String, String>> titList = service.getYxbysQryTitle();//��ѯ��ͷ
		List<String[]> rsList = service.yxbysQryRes(model);//��ѯ���
		appendQryResult(request, titList, rsList);//����б�
		appendProperties(request, xydm, zydm, nj);//�����б�
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		appendTableXx(request, "view_ynys_yxbys", "ynys_yxbysb");//����,��ͼ��
		return mapping.findForward("yxbysqry");
	}
	
	/**
	 * �����ҵ����Ϣ�޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		YxbysService service = new YxbysService();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> rsMap = service.viewYxbysxx(pkValue);
		if (rsMap != null) {
			ynysForm.setMz(rsMap.get("mz"));
			ynysForm.setZzmm(rsMap.get("zzmm"));
			ynysForm.setSyd(rsMap.get("syd"));
			ynysForm.setJkzk(rsMap.get("jkzk"));
			ynysForm.setJtdz(rsMap.get("jtdz"));
			ynysForm.setBjyj(rsMap.get("bjyj"));
			ynysForm.setYxsj(rsMap.get("yxsj"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		request.setAttribute("rs", rsMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("yxbysmodi");
	}
	
	/**
	 * �����ҵ����Ϣ�޸Ľ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysModisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		YxbysService service = new YxbysService();
		String pkValue = request.getParameter("pkValue");
		YxbysModel model = new YxbysModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.updateYxbysxx(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		ynysForm.setMz("");
		ynysForm.setZzmm("");
		ynysForm.setSyd("");
		ynysForm.setJkzk("");
		ynysForm.setJtdz("");
		ynysForm.setBjyj("");
		ynysForm.setYxsj("");
		return mapping.findForward("yxbysmodi");
	}
	
	/**
	 * �����ҵ������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		YxbysService service = new YxbysService();
		String sJg = service.delYxbysxx(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "��" + sJg + "����¼ɾ��ʧ��");
		}
		appendProperties(request, xydm, zydm, nj);//�����б�
		appendTableXx(request, "view_ynys_yxbys", "ynys_yxbysb");//����,��ͼ��
		return mapping.findForward("yxbysqry");
	}
	
	/**
	 * �ۺ����ʲ���ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String sUName = session.getAttribute("userName").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		boolean isStu = false;
		if(userType.equalsIgnoreCase("stu")){
			isStu = true;
		}
		Base.chkUPower(sUName, "pjpy_ynys_zhszcpwh.do", isStu);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * �ۺ����ʲ���ά��ҳ���ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		ZhszcpService service = new ZhszcpService();
		List<HashMap<String, String>> topList = service.getZhszcpTitle();
		List<String[]> rsList = service.getZhszcpResult(model);
		appendResult(request, topList, rsList);
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		String sJg = service.zhszcpDel(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "��" + sJg + "����¼ɾ��ʧ��");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * �ۺϲ�����������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZhszcpService service = new ZhszcpService();
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// �û�����
		String userName = (String)session.getAttribute("userName");// �û�����
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			if (StringUtils.isNull(rs.get("xh"))) {
				rs.put("stuExists", "no");
			}
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺϲ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		ynysForm.setZhszcpzf(request.getParameter("zhszcpzf"));
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		ZhszcpService service = new ZhszcpService();
		boolean bFlag = service.saveZhszcp(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺϲ����޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String pkValue = request.getParameter("pkValue");
		ZhszcpService service = new ZhszcpService();
		HashMap<String, String> rsMap = service.viewZhszcp(pkValue);
		if (rsMap != null) {
			ynysForm.setSjlxcxf(rsMap.get("sjlxcxf"));
			ynysForm.setSxlxszf(rsMap.get("sxlxszf"));
			ynysForm.setSxzzddszf(rsMap.get("sxzzddszf"));
			ynysForm.setKxwhszf(rsMap.get("kxwhszf"));
			ynysForm.setZhszcpzf(rsMap.get("zhszcpzf"));
			ynysForm.setXn(rsMap.get("xn"));
		}
		request.setAttribute("rs", rsMap);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * �ۺϲ����޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String pkValue = request.getParameter("pkValue");
		ZhszcpService service = new ZhszcpService();
		ynysForm.setXh(request.getParameter("xh"));
		ynysForm.setZhszcpzf(request.getParameter("zhszcpzf"));
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.updateZhszcp(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * �ۺϲ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpShres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String res = request.getParameter("param1");
		ZhszcpService service = new ZhszcpService();
		res = service.getShjg(res);
		String jg = service.zhszcpShres(ynysForm.getCbv(), res, request);
		if (StringUtils.isNull(jg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "��" + jg + "����¼���ʧ��!");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * ��ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xh = "";
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		String jxjdm = request.getParameter("jxjdm");
		JxjService  service = new JxjService();
		if (StringUtils.isEqual("stu", userType) ||
				StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		List<String[]> cjList = new ArrayList<String[]>();
		HashMap<String, String> cjpm = new HashMap<String, String>();
		HashMap<String, String> zhfpm = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			cjList = service.getCjList(xh);
			cjpm = service.getCjpm(xh);
			zhfpm = service.getZhfpm(xh);
		}
		if (!StringUtils.isNull(jxjdm)) {
			rs = service.getJxjxx(jxjdm, xh);
		}
		HashMap<String, String> tmpMap = service.getJxjsqsj();
		rs.put("xn", tmpMap.get("jxjsqxn"));
		rs.put("nd", tmpMap.get("jxjsqnd"));
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
//		ynysForm.setKclx(null);
//		ynysForm.setCj(null);
//		ynysForm.setKcmc(null);
//		ynysForm.setSfbxk(null);
		ynysForm.setFbkw(null);
		ynysForm.setLwhzpmc(null);
		ynysForm.setHjdj(null);
		ynysForm.setHjjb(null);
		ynysForm.setSjhm(rs.get("sjhm"));
		ynysForm.setWysp(rs.get("wysp"));
		request.setAttribute("cjpm", cjpm);
		request.setAttribute("zhfpm", zhfpm);
		request.setAttribute("cjList", cjList);
		return mapping.findForward("jxjsq");
	}
	
	/**
	 * ��ѧ�����뱣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setKcmc(request.getParameterValues("kcmc"));
		ynysForm.setCj(request.getParameterValues("cj"));
		ynysForm.setKclx(request.getParameterValues("kclx"));
		ynysForm.setSfbxk(request.getParameterValues("sfbxk"));
		ynysForm.setFbkw(request.getParameterValues("fbkw"));
		ynysForm.setLwhzpmc(request.getParameterValues("lwhzpmc"));
		ynysForm.setHjdj(request.getParameterValues("hjdj"));
		ynysForm.setHjjb(request.getParameterValues("hjjb"));
		HttpSession session = request.getSession();
		String xh = "";
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		String jxjdm = request.getParameter("jxjdm");
		JxjService  service = new JxjService();
		if (StringUtils.isEqual("stu", userType) ||
				StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		List<String[]> cjList = new ArrayList<String[]>();
		HashMap<String, String> cjpm = new HashMap<String, String>();
		HashMap<String, String> zhfpm = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			cjList = service.getCjList(xh);
			cjpm = service.getCjpm(xh);
			zhfpm = service.getZhfpm(xh);
		}
		if (!StringUtils.isNull(jxjdm)) {
			rs = service.getJxjxx(jxjdm, xh);
		}
		HashMap<String, String> tmpMap = service.getJxjsqsj();
		rs.put("xn", tmpMap.get("jxjsqxn"));
		rs.put("nd", tmpMap.get("jxjsqnd"));
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, ynysForm);
		model.setXn(rs.get("xn"));
		model.setNd(rs.get("nd"));
		model.setXq(tmpMap.get("jxjsqxq"));
		boolean bFlag = service.saveJxjxx(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
//		ynysForm.setKclx(null);
//		ynysForm.setCj(null);
//		ynysForm.setKcmc(null);
//		ynysForm.setSfbxk(null);
		ynysForm.setFbkw(null);
		ynysForm.setLwhzpmc(null);
		ynysForm.setHjdj(null);
		ynysForm.setHjjb(null);
		request.setAttribute("cjpm", cjpm);
		request.setAttribute("zhfpm", zhfpm);
		request.setAttribute("cjList", cjList);
		ynysForm.setDrzw(DealString.toGBK(ynysForm.getDrzw()));
		ynysForm.setWysp(DealString.toGBK(ynysForm.getWysp()));
		ynysForm.setZysj(DealString.toGBK(ynysForm.getZysj()));
		ynysForm.setEjyxyj(DealString.toGBK(ynysForm.getEjyxyj()));
		ynysForm.setXyyj(DealString.toGBK(ynysForm.getXyyj()));
		return mapping.findForward("jxjsq");
	}
	
	/**
	 * ��ѧ����������ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjsqqry");
	}
	/**
	 * ��ѧ�������ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqqryRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, ynysForm);
		List<HashMap<String, String>> topList = service.getJxjTitle();
		List<String[]> resList = service.getJxjResult(model);
		appendResult(request, topList, resList);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		appendJxjList(request);
		return mapping.findForward("jxjsqqry");
	}
	/**
	 * ��ѧ����Ϣ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		String sJg = service.jxjDel(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "��" + sJg + "����¼ɾ��ʧ��");
		}
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjsqqry");
	}
	
	/**
	 * ��ѧ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService services = new XjbjService();
		JxjService service = new JxjService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.viewJxjsh(pkValue, userType);
		if (rs != null) {
			ynysForm.setYesNo(rs.get("yesno"));
			ynysForm.setYj(rs.get("yj"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shList", services.getShList());//����б�
		appendJxjList(request);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * ��ѧ�𵥸���˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService services = new XjbjService();
		JxjService service = new JxjService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.saveJxjsh(pkValue, userType, ynysForm.getYesNo(), ynysForm.getYj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shList", services.getShList());//����б�
		appendJxjList(request);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * �����ҵ����ӡ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YxbysService service = new YxbysService();
		HashMap<String, String> xnMap = service.getJxjsqsj();
		request.setAttribute("tit", "����ʡ��ͨ����רѧУ"+xnMap.get("jxjsqnd")+"�������ҵ���ǼǱ�");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.yxbysPrint(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("yxbysprint");
	}
	
	/**
	 * ��ѧ�𵥸��޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		String act = request.getParameter("act");
		HashMap<String, String> rs = service.viewJxjxx(pkValue);
		String xh = request.getParameter("xh");
		xh = StringUtils.isNull(xh) ? "" : xh.trim();
		String xn = request.getParameter("xn");
		xn = StringUtils.isNull(xn) ? "" : xn.trim();
		if (rs != null) {
			ynysForm.setJxjdm(rs.get("jxjdm"));
			ynysForm.setDrzw(rs.get("drzw"));
			ynysForm.setDnshjxj(rs.get("dnshjxj"));
			ynysForm.setZysj(rs.get("zysj"));
			ynysForm.setEjyxyj(rs.get("ejyxyj"));
		}
		//List<String[]> kcList = service.getKcList(xh, xn);
		List<String[]> zpList = service.getZpList(xh, xn);
		List<String[]> cjList = new ArrayList<String[]>();
		HashMap<String, String> cjpm = new HashMap<String, String>();
		HashMap<String, String> zhfpm = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			cjList = service.getCjList(xh);
			cjpm = service.getCjpm(xh);
			zhfpm = service.getZhfpm(xh);
		}
		request.setAttribute("rs", rs);
		appendJxjList(request);
		request.setAttribute("pkValue", pkValue);
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		//request.setAttribute("kcList", kcList);
		request.setAttribute("zpList", zpList);
		request.setAttribute("cjpm", cjpm);
		request.setAttribute("zhfpm", zhfpm);
		request.setAttribute("cjList", cjList);
		return mapping.findForward("jxjmodi");
	}
	/**
	 * ��ѧ�𵥸��޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjmodiSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.modiJxjsave(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("kcList", new ArrayList<String[]>());
		request.setAttribute("zpList", new ArrayList<String[]>());
		ynysForm.setDrzw("");
		ynysForm.setZysj("");
		ynysForm.setEjyxyj("");
		request.setAttribute("rs", new HashMap<String, String>());
		appendJxjList(request);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjmodi");
	}
	
	/**
	 * �ۺ����ʲ������Զ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpautoacount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		String xn = request.getParameter("xn");
		String xydms = request.getParameter("xydm");
		if (StringUtils.isNull(xn)) {
			xn = Base.getJxjsqxn();
		}
		if (StringUtils.isEqual("xy", userType)) {
			xydms = userDep;
		}
		boolean bFlag = service.zhszcpAutoCount(xn, xydms);//�Զ������ۺϷ�
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		return mapping.findForward("zhszcpwh");
	}
	
}

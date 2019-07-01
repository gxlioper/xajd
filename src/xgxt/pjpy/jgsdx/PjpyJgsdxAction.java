
package xgxt.pjpy.jgsdx;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxAction extends DispatchAction {
	
	String xydm = "";
	String zydm = "";
	String nj = "";
	
	CommonAction commonAction = new CommonAction();
	
	/**
	 * ���÷���:��REQUEST�д��ҳ����Ҫ���ص�LIST����
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> zyjxjList = service.getZyjxjList();//רҵ��ѧ���б�
		request.setAttribute("zyjxjList", zyjxjList);
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
		request.setAttribute("userType", userType);//�û�����
	}
	
	public void appentProperties1(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String zyKey = "";
		String bjKey = "!!!!";
		if("xy".equalsIgnoreCase(userType)){
			zyKey = userDep;
			bjKey = zyKey+"!!"+""+"!!"+"";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
	
	/**
	 * רҵ��ѧ���ϱ�Ĭ��ҳ��
	 * zyjxjSbDefault ---- רҵ��ѧ���ϱ�Ĭ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjSbDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy 		   = session.getAttribute("isFdy").toString();//�Ƿ��Ǹ���Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		//����Ա�û����߱��Զ���˹���
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * ˢ��ҳ�潱�����
	 * refreshJlje ---- ˢ��ҳ�潱����� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward refreshJlje(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy 		   = session.getAttribute("isFdy").toString();//�Ƿ��Ǹ���Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		
		String jxjdm = jgsdxForm.getJxjdm();
		PjpyJgsdxService service = new PjpyJgsdxService();
		String jlje = service.getJxjJeByJxjdm(jxjdm);//ͨ����ѧ������ȡ��ѧ���
		jgsdxForm.setJlje(jlje);
//		����Ա�û����߱��Զ���˹���
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * רҵ��ѧ���ϱ���ѯ
	 * zyjxjsbSearch ---- רҵ��ѧ���ϱ���ѯ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjsbSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjQryModel zyjxjModel = new ZyjxjQryModel();//רҵ��ѧ���ϱ���ѯMODEL
		
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//�û����ڲ���
//		String userName             = session.getAttribute("userName").toString();
		String userType        		= session.getAttribute("userType").toString();//�û�����
		String isFdy 			    = session.getAttribute("isFdy").toString();//�Ƿ��Ǹ���Ա
//		�û���ѧԺ�����Ա��û����˵�λ�ķ���
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdyxx", "˫��һ�п��Խ����ۺ����ʣ�ѧϰ�ɼ��������ã�������ͷ���Խ�������;");
			request.setAttribute("isFdy", "yes");
		}else {
			request.setAttribute("fdyxx", "������ͷ���Խ�������;");
		}
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> topList = service.getZyjxjSbTitle(isFdy, userType);//רҵ��ѧ���ѯ��ͷ
		List<String[]> resList = service.getZyjxjSbResult(isFdy, userType, zyjxjModel);//רҵ��ѧ���ѯ���
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("topTr", topList);
//		����Ա�û����߱��Զ���˹���
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * �ۺ����ʺ�ѧϰ�ɼ�����
	 * setXhszAndXxcj ---- �ۺ����ʺ�ѧϰ�ɼ����� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXhszAndXxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		if (StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdywritable", "yes");
		}
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdm");
		pkValue = xh + xn + jxjdm;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HashMap<String, String> resMap = service.getZhszandXxcjByPk(pkValue, xh);//ͨ��������ȡ�ۺ����ʺ�ѧϰ�ɼ�
		List shztList = service.getChkList(3);//���״̬
		jgsdxForm.setXh(xh);
		jgsdxForm.setBz(resMap.get("bz"));
		jgsdxForm.setZhszpm(resMap.get("zhszpm"));
		jgsdxForm.setXm(resMap.get("xm"));
		jgsdxForm.setXxcjpm(resMap.get("xxcjpm"));
		jgsdxForm.setSfsf(resMap.get("sfsf"));
		jgsdxForm.setXn(xn);
		jgsdxForm.setXq(resMap.get("xq"));
		jgsdxForm.setShzt(resMap.get("fdysh"));
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("xn", xn);
		request.setAttribute("shztList", shztList);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszandxxcj");
	}
	
	/**
	 * �ۺ����ʺ�ѧϰ�ɼ�����
	 * zhszandxxcjSave ---- �ۺ����ʺ�ѧϰ�ɼ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszandxxcjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZhszandXxcjSaveModel zxsaveModel = new ZhszandXxcjSaveModel();//�ۺ����ʺ�ѧϰ�ɼ�����MODEL
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		if (StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdywritable", "yes");
		}
		BeanUtils.copyProperties(zxsaveModel, jgsdxForm);
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String pkValue = xh + request.getParameter("xn")+request.getParameter("jxjdm");
		zxsaveModel.setXn(xn);
		zxsaveModel.setPkValue(xh+xn+request.getParameter("jxjdm"));
		zxsaveModel.setJxjdm(request.getParameter("jxjdm"));
		PjpyJgsdxService service = new PjpyJgsdxService();
		boolean bFlag = service.zhszandxxcjSave(zxsaveModel, request);
		if (bFlag) {
			request.setAttribute("result", "view");
		} else {
			request.setAttribute("result", "noview");
		}
		HashMap<String, String> resMap = service.getZhszandXxcjByPk(pkValue, xh);//ͨ��������ȡ�ۺ����ʺ�ѧϰ�ɼ�
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		jgsdxForm.setBz("");
		List shztList = service.getChkList(3);//���״̬
		request.setAttribute("shztList", shztList);
		return mapping.findForward("zhszandxxcj");
	}
	
	/**
	 * רҵ��ѧ���Զ����
	 * zyjxjautosh ---- רҵ��ѧ���Զ���� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjAutoSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		ZyjxjAutoshModel zyjxjModel = new ZyjxjAutoshModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		service.zyjxjAutoSh(zyjxjModel, request);//�Զ����
		
//		����Ա�û����߱��Զ���˹���
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		request.setAttribute("result", "noview");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * רҵ��ѧ���ӡ
	 * zyjxjPrint ---- רҵ��ѧ���ӡ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjQryModel zyjxjModel = new ZyjxjQryModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<String[]> resList = service.zyjxjPrint(zyjxjModel);//��ѧ���ӡ
		request.setAttribute("rs", resList);
		request.setAttribute("xn", request.getParameter("xn"));
		return mapping.findForward("zyjxjprint");
	}
	
	/**
	 * רҵ��ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjShModel zyjxjModel = new ZyjxjShModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdyxx", "˫��һ�п��Խ����ۺ����ʣ�ѧϰ�ɼ��������ã�������ͷ���Խ�������;");
			request.setAttribute("isFdy", "yes");
		}else {
			request.setAttribute("fdyxx", "������ͷ���Խ�������;");
		}
		PjpyJgsdxService service = new PjpyJgsdxService();
		String[] cbv = jgsdxForm.getCbv();
		String res = request.getParameter("param1");
		zyjxjModel.setCbv(cbv);//�����б�
		zyjxjModel.setRes(res);//��˽��
		service.zyjxjSh(isFdy, userType, zyjxjModel, request);//��ѧ�����
//		����Ա�û����߱��Զ���˹���
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "view_xszyjxj");
		request.setAttribute("result", "view");
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * רҵ��ѧ������ɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjblDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjShModel zyjxjModel = new ZyjxjShModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
//		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		PjpyJgsdxService service = new PjpyJgsdxService();
		String[] keys = jgsdxForm.getCbv();
		String result = service.zyjxjblDel(keys);//����ɾ��
		if (!StringUtils.isNull(result)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", result);
		}
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * �����ƺ�����Ĭ��ҳ��
	 * rysqdefault ---- ��������Ĭ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rysqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> list = service.getRysqList(1);//�����б�
		request.setAttribute("list", list);
		return mapping.findForward("rysqdefault");
	}
	
	/**
	 * �����༶����Ĭ��ҳ��
	 * wmbjsqdefault ---- �����༶����Ĭ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> titleList = service.getRysqList(2);//�����༶�б�
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		appentProperties1(request, xydm, zydm, nj);
		
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		return mapping.findForward("wmbjsqdefault");
	}
	
	/**
	 * �����༶������,����֤�����Ƿ��ظ�
	 * wmbjsqjg ---- �����༶������ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjsqJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		WmbjSqModel wmbjModel = new WmbjSqModel();
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> titleList = service.getRysqList(2);//�����༶�б�
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		boolean bChk = service.chkDataByWmbj(wmbjModel);
		if (bChk) {//����
			request.setAttribute("result", "haved");
		}else {//������
			boolean bFlag = service.saveWmBjInfo(wmbjModel, request);//��������
			if (bFlag) {
				request.setAttribute("result", "true");
			}else{
				request.setAttribute("result", "false");
			}//end if
		}//end if
		
		jgsdxForm.setBzr(DealString.toGBK(jgsdxForm.getBzr()));
		jgsdxForm.setBzxm(DealString.toGBK(jgsdxForm.getBzxm()));
		jgsdxForm.setZysj(DealString.toGBK(jgsdxForm.getZysj()));
		appentProperties1(request, xydm, zydm, nj);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		return mapping.findForward("wmbjsqdefault");
	}

	/**
	 * �����ƺŲ�ѯĬ��ҳ��
	 * rychcxDefault ---- �����ƺŲ�ѯĬ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychcxDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> list = service.getRysqList(3);//�����б�
		request.setAttribute("list", list);
		return mapping.findForward("rychcxdefault");
	}
	
	/**
	 * �����༶��ѯĬ��
	 * wmbjcxdefault ---- �����༶��ѯĬ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjcxDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");
		request.setAttribute("tableName", "view_pjpy_xjbjandwmsq");
		return mapping.findForward("wmbjcxdefault");
	}
	
	/**
	 * �����༶��ѯ���
	 * wmbjcxjg ---- �����༶��ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjcxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		WmbjSqModel wmbjModel = new WmbjSqModel();
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		wmbjModel.setShxm("xjbj");
		wmbjModel.setXydm(xydm);
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);//��ѯ��Ŀ�б�
		List<HashMap<String, String>> topList = service.wmbjcxTitle();//��ѯ��ͷ
		List<String[]> resList = service.wmbjsqCxJg(wmbjModel);//��ѯ���
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");
		request.setAttribute("tableName", "view_pjpy_xjbjandwmsq");
		return mapping.findForward("wmbjcxdefault");
	}
	
	//�����༶���޸�ͬ���ս�����ҵѧԺ���Ƚ��༶�޸�
	
	/**
	 * �����༶����ɾ��
	 * wmbjsqdel ---- �����༶����ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String[] keys = jgsdxForm.getCbv();
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		String sJg = service.delWmbjXx(keys);//����ɾ��
		if (!StringUtils.isNull(sJg)) {
			request.setAttribute("result", "view");
		} else {
			request.setAttribute("result", sJg);
		}
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");
		request.setAttribute("tableName", "view_pjpy_xjbjandwmsq");
		return mapping.findForward("wmbjcxdefault");
	}
	
	/**
	 * �����༶��ӡ����
	 * wmbjprint ---- �����༶��ӡ���� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> resMap = service.getWmbjByPk(pkValue);
		request.setAttribute("rs", resMap);
		return mapping.findForward("wmbjprint");
	}
	
	/**
	 * �����ƺ����Ĭ��ҳ��
	 * rychshDefault ---- �����ƺ����Ĭ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> list = service.getRysqList(1);//�����б�
		request.setAttribute("list", list);
		return mapping.findForward("rychshdefault");
	}
	
	/**
	 * �����༶���
	 * wmbjsh ---- �����༶���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
//		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("wmbjsh");
	}
	
	/**
	 * �����༶��˲�ѯ���
	 * wmbjshqry ---- �����༶��˲�ѯ��� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		WmbjSqModel wmbjModel = new WmbjSqModel();
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		wmbjModel.setShxm("xjbj");
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		wmbjModel.setXydm(xydm);
		List<HashMap<String, String>> topList = service.wmbjShTitle(isFdy, userType, request);//��ѯ��ͷ
		List<String[]> resList = service.wmbjShResult(isFdy, userType, wmbjModel, request);//��ѯ���
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		return mapping.findForward("wmbjsh");
	}
	
	/**
	 * �����༶�����ϸ��Ϣ��ʾ
	 * wmbjshview ---- �����༶�����ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward wmbjshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");//����
		HashMap<String, String> resMap = service.getWmbjShView(isFdy, userType, pkValue);//���������Ϣ
		List<HashMap<String, String>> shxmList = service.getChkList(3);//����б�
		jgsdxForm.setShxm(resMap.get("sh"));
		jgsdxForm.setShyj(resMap.get("shyj"));
		request.setAttribute("rs", resMap);
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("wmbjshview");
	}
	
	/**
	 * �����༶�������
	 * wmbjshbyone ---- �����༶������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward wmbjShByone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");//����
		WmbjShModel wmbjModel = new WmbjShModel();//�����༶�������MODEL
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		wmbjModel.setPkValue(pkValue);
		HashMap<String, String> resMap = new HashMap<String, String>();
		List<HashMap<String, String>> shxmList = service.getChkList(3);//����б�
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		boolean bFlag = service.wmbjShByOne(isFdy, userType, wmbjModel, request);//�������
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("shxmList", shxmList);
		return mapping.findForward("wmbjshview");
	}
	
	/**
	 * �����༶�������
	 * wmbjjtsh ---- �����༶������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjjtSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		String userName		   = session.getAttribute("userName").toString();//�û���
		String isFdy           = session.getAttribute("isFdy").toString();//����Ա
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		String[] keys = jgsdxForm.getCbv();//�����б�
		String res = request.getParameter("param1");//��˽��
		service.wmbjJtSh(isFdy, userType, keys, res, request);
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("result", "view");
		return mapping.findForward("wmbjsh");
	}
	
	/**
	 * ���������ƺ����Ĭ��ҳ��
	 * grrychshDefault ---- ���������ƺ����Ĭ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		List<HashMap<String, String>> rychList = service.getRychList();//�����ƺ��б�
		String[] jxjsqnxndList = service.getJxjsqXnNd();//��ѧ������ѧ�����
		if (jxjsqnxndList != null && jxjsqnxndList.length == 2) {
			jgsdxForm.setXn(jxjsqnxndList[0]);
			jgsdxForm.setNd(jxjsqnxndList[1]);
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rychList", rychList);
		return mapping.findForward("grrychshdefault");
	}
	
	/**
	 * ���������ƺ���˲�ѯ������Ա��ѧԺ��ѧУ��
	 * grrychshQry ---- ���������ƺ���˲�ѯ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		RychQryModel rychModel = new RychQryModel();//�����ƺŲ�ѯMODEL
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		String isFdy 		   = session.getAttribute("isFdy").toString();//�Ƿ񸨵�Ա
		//�û��Ǹ���Ա,ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		String[] jxjsqnxndList = service.getJxjsqXnNd();//��ѧ������ѧ�����
		if (jxjsqnxndList != null && jxjsqnxndList.length == 2) {
			jgsdxForm.setXn(jxjsqnxndList[0]);
			jgsdxForm.setNd(jxjsqnxndList[1]);
		}
		BeanUtils.copyProperties(rychModel, jgsdxForm);
		rychModel.setXydm(xydm);
		List<HashMap<String, String>> topList = service.getRychQryTitle(isFdy, userType, jgsdxForm.getXmdm());//��ѯ��ͷ
		List<String[]> resList = service.getRychQryResult(isFdy, userType, rychModel, jgsdxForm.getXmdm());//��ѯ���
		List<HashMap<String, String>> rychList = service.getRychList();//�����ƺ��б�
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rychList", rychList);
		return mapping.findForward("grrychshdefault");
	}
	
	/**
	 * ���������ƺ���ˣ�����Ա��ѧԺ��ѧУ��
	 * grrychsh ---- ���������ƺ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		String isFdy 		   = session.getAttribute("isFdy").toString();//�Ƿ񸨵�Ա
		//�û��Ǹ���Ա,ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		String[] jxjsqnxndList = service.getJxjsqXnNd();//��ѧ������ѧ�����
		if (jxjsqnxndList != null && jxjsqnxndList.length == 2) {
			jgsdxForm.setXn(jxjsqnxndList[0]);
			jgsdxForm.setNd(jxjsqnxndList[1]);
		}
		String[] cbv = jgsdxForm.getCbv();//����
		String shjg = request.getParameter("param1");//��˽��
		GrrychShModel grrychModel = new GrrychShModel();
		grrychModel.setCbv(cbv);
		grrychModel.setRychdm(jgsdxForm.getXmdm());
		grrychModel.setShjg(shjg);
		List<HashMap<String, String>> rychList = service.getRychList();//�����ƺ��б�
		service.grrychSh(isFdy, userType, grrychModel, request);//�û����
		request.setAttribute("updated", "yes");
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rychList", rychList);
		return mapping.findForward("grrychshdefault");
	}
	
	/**
	 * ���������ƺű����ӡ
	 * grrychPrint ---- ���������ƺű����ӡ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String rychmc = service.getRychMc(request.getParameter("rychdm"));
		HashMap<String, String>  resMap = new HashMap<String, String>();
		resMap = service.getRychInfo(pkValue);//��ȡ�����ƺ���Ϣ
		request.setAttribute("rs", resMap);
		if (!StringUtils.isNull(rychmc) && StringUtils.isEqual(rychmc, "�����ҵ��")) {//�����ҵ�������ӡ
			return mapping.findForward("yxdxsprint");
		}
		//����ѧ��,������ѧ��,����ѧ�����,����ѧ���ɲ������ӡ
		if (!StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "����ѧ��")
						|| StringUtils.isEqual(rychmc, "������ѧ��")
						|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
						.isEqual(rychmc, "����ѧ���ɲ�"))) {
			request.setAttribute("rychmc", rychmc);
			return mapping.findForward("wmshxsprint");
		}
		//���������ӡ
		request.setAttribute("rychmc", rychmc);
		return mapping.findForward("grrychprint");
	}
	
	/**
	 * ���������ƺ���ʾ��ϸ��Ϣ
	 * grrychview ---- ���������ƺ���ʾ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward grrychView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String userType        = session.getAttribute("userType").toString();//�û�����
		String isFdy 		   = session.getAttribute("isFdy").toString();//�Ƿ񸨵�Ա
		String pkValue = request.getParameter("pkValue");//����
		String rychdm = request.getParameter("rychdm");
		HashMap<String, String> resMap = service.getRychshView(isFdy, userType, rychdm, pkValue);//��ȡ��ϸ��Ϣ
		List<HashMap<String, String>> shList = service.getChkList(3);//����б�
		jgsdxForm.setYesNo(resMap.get("yesno"));
		request.setAttribute("rs", resMap);
		request.setAttribute("chkList", shList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rychdm", rychdm);
		return mapping.findForward("grrychview");
	}
	
	/**
	 * ���������ƺ�������
	 * grrychmodi ---- ���������ƺ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward grrychModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String userType        = session.getAttribute("userType").toString();//�û�����
		String isFdy 		   = session.getAttribute("isFdy").toString();//�Ƿ񸨵�Ա
		String pkValue = request.getParameter("pkValue");
		String rychdm = request.getParameter("rychdm");
		String sh = DealString.toGBK(request.getParameter("yesNo"));
		String shyj = DealString.toGBK(request.getParameter("shyj"));
		GrrychModiModel grrychModel = new GrrychModiModel();//�����ƺŵ������MODEL
		grrychModel.setPkValue(pkValue);
		grrychModel.setRychdm(rychdm);
		grrychModel.setSh(sh);
		grrychModel.setShyj(shyj);
		boolean bFlag = service.grrychModi(isFdy, userType, grrychModel, request);//�������
		List<HashMap<String, String>> shList = service.getChkList(3);//����б�
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());//����ҳ��Ŀս����
		request.setAttribute("chkList", shList);
		return mapping.findForward("grrychview");
	}

	/**
	 * ���������ƺ�������˲�ѯĬ��ҳ��
	 * grrychsjDefault ---- ���������ƺ�������˲�ѯĬ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychsjDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		return mapping.findForward("grrychsjdefault");
	}
	
	/**
	 * ���������ƺ�������˲�ѯ���
	 * grrychsjQry ---- ���������ƺ�������˲�ѯ��� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychsjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		RychSjQryModel rychModel = new RychSjQryModel();
		BeanUtils.copyProperties(rychModel, jgsdxForm);
		List<HashMap<String, String>> topList = service.qryRychTitle();//��ѯ��ͷ
		List<String[]> resList = service.qryRychResult(rychModel);//��ѯ���
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		return mapping.findForward("grrychsjdefault");
	}
	
	/**
	 * ���������ƺ��޸�ҳ��
	 * grrychModiView ---- ���������ƺ��޸�ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychModiView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		HashMap<String, String> resMap = service.rychInfoByPk(pkValue);//�����ƺ���Ϣ
		List<HashMap<String, String>> rychList = service.getRychList();//�����ƺ��б�
		jgsdxForm.setXmdm(resMap.get("rychdm"));
		if (!StringUtils.isNull(type) && StringUtils.isEqual(type, "modi")) {
			request.setAttribute("isupdate", "yes");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("rychList", rychList);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("grrychmodiview");
	}
	
	/**
	 * ���������ƺ��޸�
	 * grrychModi ---- ���������ƺ��޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychModijg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String rychdm = request.getParameter("xmdm");
		String drzw = DealString.toGBK(request.getParameter("drzw"));
		List<HashMap<String, String>> rychList = service.getRychList();
		boolean bFlag = service.rychInfoModi(pkValue, rychdm, drzw, xh, request);//������Ϣ�޸�
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rychList", rychList);
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("grrychmodiview");
	}
	
	/**
	 * ���������ƺ�ɾ��
	 * grrychsqDel ---- ���������ƺ�ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String[] keys = jgsdxForm.getCbv();
		String sJg = service.rychInfoDel(keys, request);//����ɾ��
		request.setAttribute("result", sJg);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		return mapping.findForward("grrychsjdefault");
	}
	
	/**
	 * ����������ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String jxjdm = jgsdxForm.getJxjdm();
		if (!StringUtils.isNull(jxjdm)) {
			List<HashMap<String, String>> topList = service.getJxjsztjTitle();
			List<String[]> jxjsztjList = service.getJxjsztj(jgsdxForm.getXn(), jxjdm);
			String wjtj = service.getJxjTjsz(jgsdxForm.getXn(), jxjdm);//�Ƿ�Υ��
			request.setAttribute("rs", jxjsztjList);
			request.setAttribute("topTr", topList);
			jgsdxForm.setSfwj(wjtj);
		}
		List<HashMap<String, String>> zdList = service.getZdList();//�ֶ��б�
		request.setAttribute("zdList", zdList);
		jgsdxForm.setZdm("");
		request.setAttribute("jxjList", service.getJxjList1());//���ؽ�ѧ���б�
		//commonAction.appendJxjList(request);//���ؽ�ѧ���б�
		commonAction.appendProperties(request, xydm, zydm, nj);//����LIST�б�
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * ��ѧ���������ñ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjtjszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String ysf = request.getParameter("ysf");
		if (StringUtils.isNull(ysf)) {
			ysf = "<=";
		}
		jgsdxForm.setYsf(ysf);
		PjpyJgsdxService service = new PjpyJgsdxService();
		JxjtjszSaveModel tjszModel = new JxjtjszSaveModel();
		BeanUtils.copyProperties(tjszModel, jgsdxForm);
		boolean bFlag = service.jxjsztjSave(tjszModel, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> zdList = service.getZdList();//�ֶ��б�
		request.setAttribute("zdList", zdList);
		//commonAction.appendJxjList(request);//���ؽ�ѧ���б�
		request.setAttribute("jxjList", service.getJxjList1());//���ؽ�ѧ���б�
		jgsdxForm.setZdm("");
		commonAction.appendProperties(request, xydm, zydm, nj);//����LIST�б�
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * ��ѧ�����������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjszcjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String jxjdm = request.getParameter("jxjdm");
		HashMap<String, String> tjMap = service.getTjInfo(pkValue, jxjdm);//��ȡ������Ϣ
		if (tjMap != null) {
			String zdm = tjMap.get("tjzdm");
			String zdcz = tjMap.get("zdcz");
			String sfwj = tjMap.get("sfwj");
			String tj = tjMap.get("tj");
			zdm = service.getZdm(!StringUtils.isNull(zdm) ? zdm.trim() : "");
			//zdcz = service.getZdcz(!StringUtils.isNull(zdcz) ? zdcz.trim() : "");
			String[] tjValue = service.getTjValue(!StringUtils.isNull(tj) ? tj.trim() : "");
			tjMap.put("zdm", zdm);
			jgsdxForm.setZdcz(zdcz);
			jgsdxForm.setSfwj(sfwj);
			if (tjValue != null && tjValue.length == 2) {
				jgsdxForm.setYsf(service.getTj(tjValue[0]));
				jgsdxForm.setVal(tjValue[1]);
			}
		}
		request.setAttribute("rs", tjMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjsztjmodi");
	}
	
	/**
	 * ��ѧ����������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjszcjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.jxjsztjDel(pkValue, request);//����ɾ��
		if (bFlag) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		List<HashMap<String, String>> zdList = service.getZdList();//�ֶ��б�
		request.setAttribute("zdList", zdList);
		//commonAction.appendJxjList(request);//���ؽ�ѧ���б�
		request.setAttribute("jxjList", service.getJxjList1());//���ؽ�ѧ���б�
		jgsdxForm.setZdm("");
		commonAction.appendProperties(request, xydm, zydm, nj);//����LIST�б�
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * ��ѧ�����������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsztjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String ysf = request.getParameter("ysf");
		if (StringUtils.isNull(ysf)) {
			ysf = "<=";
		}
		jgsdxForm.setYsf(ysf);
		PjpyJgsdxService service = new PjpyJgsdxService();
		JxjtjszSaveModel tjszModel = new JxjtjszSaveModel();
		BeanUtils.copyProperties(tjszModel, jgsdxForm);
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.tjEditResult(pkValue, tjszModel, request);//�����޸�
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("jxjsztjmodi");
	}
}


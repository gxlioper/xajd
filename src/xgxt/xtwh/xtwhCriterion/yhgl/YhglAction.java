package xgxt.xtwh.xtwhCriterion.yhgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_�û�����_action��
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
public class YhglAction extends BasicExtendAction{
	
	/**
	 * �û�ά������
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglForm yhglForm = (YhglForm)form;
		
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		
		YhglService service = new YhglService();
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			
			String message = service.delYh(pkValues) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			request.setAttribute("message", message);
			go = "go";
		}
		
		// ��ʼ����
		if("init".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			
			// �����ʼ������
			String message = service.initMm(pkValues) ? "��ʼ���ɹ���" : "��ʼ��ʧ�ܣ�";
			request.setAttribute("message", message);
			go = "go";
		}
		
		// ѧ���û���ʼ��
		if("initXs".equalsIgnoreCase(doType)){
			
		}
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.queryYhList(yhglForm));
		}
		
		// ��ȡjsList
		request.setAttribute("topTr", service.getTopTr("yhgl"));
		request.setAttribute("jsList", service.getRsList("xg_xtwh_jswhb", new HashMap<String, String>(), new String[]{"jsdm", "jsmc"}));
	
		FormModleCommon.requestSetList(new String[] {"bm" }, request);// �Զ���(Ŀǰ�����Ŵ���)
		
		return mapping.findForward("yhwhManage");
	}
	
	/**
	 * �û�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// ��½�û�
		String userName = (String)session.getAttribute("userName");
		String doType = request.getParameter("doType");

		YhglForm yhglForm = (YhglForm)form;	
		
		YhglService service = new YhglService();
		
		// �����û�
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveYh(yhglForm) ? "����ɹ���" : "����ʧ�ܣ�";
			
			request.setAttribute("message", message);
		}
		
		// Ĭ��Ȩ��List
		request.setAttribute("mrqxList", service.getJsczfwList());
		// �����ɫList
		request.setAttribute("jsList", service.getJsListForUserName(userName));
		
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		return mapping.findForward("yhwhUpdate");
	}
	
	/**
	 * �û�ά���鿴ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhwhModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// ����
		String doType = request.getParameter("doType");
		// ��½�û��� 
		String userName = (String)session.getAttribute("userName");
		// �û�������
		String pkValue = request.getParameter("pkValue");
		
		YhglForm myForm = (YhglForm)form;
		
		YhglService service = new YhglService();
		
		// �޸Ĳ���
		if("save".equalsIgnoreCase(doType)){
			String message = service.modiYh(myForm) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		// ��ý�ɫ�б�
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("yhm", pkValue);
		
		List<HashMap<String, String>> jsList = service.getRsList("xg_view_xtwh_yhjs", queryMap, new String[]{"jsdm", "jsmc", "jsqx"});
		// �û���Ϣ 
		Map<String, String> rs = service.getYh(pkValue);
		// ����״̬
		myForm.setKqzt(rs.get("kqzt"));
		
		// ����
		request.setAttribute("doType", doType);
		// ���н�ɫ�б�
		request.setAttribute("yyjsList", jsList);
		// ��½�û�ӵ�н�ɫ
		request.setAttribute("jsList", service.getJsListForUserName(userName));
		// �û���Ϣ
		request.setAttribute("rs", rs);
		// Ĭ��Ȩ��List
		request.setAttribute("mrqxList", service.getJsczfwList());
		
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		return mapping.findForward("yhwhModi");
	}
	
	/**
	 * �û���ɫ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhjsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// �û���
		String userName = (String)session.getAttribute("userName");
		// ��Ҫ��ɫ������û�
		
		YhglService service = new YhglService();
		
		// �û���ɫList
		// request.setAttribute("yhjsMap", service.getYhJsList(pkValues));
		// �����ɫList
		request.setAttribute("jsList", service.getJsListForUserName(userName));

		return mapping.findForward("yhjsUpdate");
	}
	
	/**
	 * �û�Ȩ�޷������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhqxfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// ��½�û�
		String userName = (String)session.getAttribute("userName");
		// ��������
		String doType = request.getParameter("doType");
		YhglService service = new YhglService();
		
		// ��ȡ�û�����
		String yhm = request.getParameter("pkValue");
		
		// ��ȡ�û�ͨ���û���
		if(StringUtils.isNotNull(yhm)){
			request.setAttribute("rs", service.getYh(yhm));
			
			// ��ý�ɫ�б�
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("yhm", yhm);
			
			List<HashMap<String, String>> jsList = service.getRsList("xg_view_xtwh_yhjs", queryMap, new String[]{"jsdm", "jsmc"});
			request.setAttribute("jsList", jsList);
			request.setAttribute("size", jsList.size());
		}
		
		// ������ӵ�Ȩ��
		if("save".equalsIgnoreCase(doType)){
			String[] btns = request.getParameterValues("btns");
			String message = service.saveYhqx(userName, yhm, btns) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		// ���й���ģ��
		List<GnmkModel> list = service.getAllGnmkList(userName, yhm);
		request.setAttribute("allGnmkList", list);
		
		// ����߶�
		int height = (list.size() * 29 + 36) < 800 ? 800 : (list.size() * 29 + 36); 
		request.setAttribute("height", height);
		
		// ��ɫ���ʣ���ɫ������ΧList
		request.setAttribute("jslxList", service.getJslxList());
		request.setAttribute("jsczfwList", service.getJsczfwList());
		
		return mapping.findForward("yhqxfpManage");
	}
	
	/**
	 * �û�ѡ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYhInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String go = request.getParameter("go");
		
		// ��ѯ����
		if ("go".equalsIgnoreCase(go)) {
			YhglForm yhglForm = (YhglForm) form;
			YhglService service = new YhglService();
			
			request.setAttribute("topTr", service.getTopTr("yhqxfp"));
			request.setAttribute("rs", service.queryYhList(yhglForm));
		}
	
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		return mapping.findForward("userInfo");
	}
	
	/**
	 * ��ɫ���������û�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jsBatchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session  = request.getSession();
		
		// ��½�û���
		String userName = (String)session.getAttribute("userName");
		
		String go = request.getParameter("go");
		
		YhglService service = new YhglService();
		
		YhglForm myForm = (YhglForm) form;
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.queryJsList(myForm, userName));
		}
		
		request.setAttribute("topTr", service.getTopTr("js"));
		request.setAttribute("jslxList", service.getJslxList());		// ��ɫ����List
		request.setAttribute("jsczfwList", service.getJsczfwList());	// ��ɫ������ΧList
		
		return mapping.findForward("jsBatchManage");
	}
	
	/**
	 * ��ɫ���������û�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhBatchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService service = new YhglService();
		
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		
		// ��ɫ��������
		String[] jsIds = request.getParameterValues("jsIds");

		// �������
		if("save".equalsIgnoreCase(doType)){
			String[] yhs = request.getParameterValues("yhs");
			String message = service.saveJsForYh(yhs, jsIds) ? "�����ɹ���" : "����ʧ�ܣ�";
			
			request.setAttribute("message", message);
			go = "go";
		}
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){	
			YhglForm yhglForm = (YhglForm)form;
			request.setAttribute("rs", service.queryYhList(yhglForm));
		}
		
		request.setAttribute("jsList", service.getJsListForPk(jsIds, new String[]{"jsdm","jsmc","jslxmc","jscmmc","jssm"}));
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		request.setAttribute("topTr", service.getTopTr("yhjsfp"));
		return mapping.findForward("yhBatchManage");
	}
	
	/**
	 * ѧ��������Ȩ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsBatchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// ��½�û��� 
		String userName = (String)session.getAttribute("userName");
		String go = request.getParameter("go");
		
		YhglService service = new YhglService();
		
		YhglForm myForm = (YhglForm) form;
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.queryJsList(myForm, userName));
		}
		
		request.setAttribute("topTr", service.getTopTr("js"));
		request.setAttribute("jslxList", service.getJslxList());		// ��ɫ����List
		request.setAttribute("jsczfwList", service.getJsczfwList());	// ��ɫ������ΧList
		
		return mapping.findForward("xsBatchManage");
	}
	
	/**
	 * ѧ��������Ȩ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsBatchUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService service = new YhglService();
		// ��������
		String doType = request.getParameter("doType");
		// ��ɫ�����б�
		String[] jsIds = request.getParameterValues("jsIds");
		String go = request.getParameter("go");
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			YhglForm model = (YhglForm) form; 
			// ��Ҫ�����ѧ������
			String[] xss = request.getParameterValues("xss");
			String message = service.saveJsForXs(xss, jsIds, model.getKssj(), model.getJssj()) ? "�����ɹ���" : "����ʧ�ܣ�";
			
			request.setAttribute("message", message);
			go = "go";
		}
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){	
			YhglForm yhglForm = (YhglForm)form;
			
			request.setAttribute("rs", service.queryXslxList(yhglForm));
		}
		
		// ��ȡ��ɫ˵��
		request.setAttribute("jsList", service.getJsListForPk(jsIds, new String[]{"jsdm","jsmc","jslxmc","jscmmc","jssm"}));
		request.setAttribute("xslxlbList", service.getRsList("xg_xtwh_xslxlbb", new HashMap<String, String>(), new String[]{"xslxlbdm", "xslxlbmc"}));
		
		// ��ȡѧ��������ȨtopTr
		request.setAttribute("topTr", service.getTopTr("xsBatch"));
		return mapping.findForward("xsBatchUpdate");
	}
	
	/**
	 * ѧ�������û���Ȩ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqxfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��������
		String doType = request.getParameter("doType");
		// �û�����service
		YhglService service = new YhglService();
		// ��õ�½�û�����
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		// �û��������
//		YhglForm model = (YhglForm) form;
		// ѧ��
		String xh = request.getParameter("xh");
		
		if("save".equalsIgnoreCase(doType)){
//			String[] btns = request.getParameterValues("btns");
		}
		
		// ѧ�Ų�λnull��ȡѧ������
		if(StringUtils.isNotNull(xh)){
		}
		
		request.setAttribute("gnmkList", service.getOneGnmkList(userName, 1));
		
		return mapping.findForward("xsqxfpManage");
	}
}

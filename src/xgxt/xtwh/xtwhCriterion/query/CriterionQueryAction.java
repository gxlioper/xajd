package xgxt.xtwh.xtwhCriterion.query;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.xtwhCriterion.QxwhForm;
import xgxt.xtwh.xtwhCriterion.yhgl.YhglService;

public class CriterionQueryAction extends DispatchAction{
	
	/**
	 * ��ѯ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		// �û���ɫ��ѯ
		if("view".equalsIgnoreCase(doType)){
			QxwhForm myForm = (QxwhForm)form;
			String mk = myForm.getMk();

			if("yhjscx".equalsIgnoreCase(mk)){
				// �û���ɫ��ѯ
				return yhjsManage(mapping, form, request, response);
			}else if("gnjscx".equalsIgnoreCase(mk)){
				// ���ܽ�ɫ��ѯ
				return gnjsManage(mapping, form, request, response);
			}else if("jsqxcx".equalsIgnoreCase(mk)){
				// ��ɫȨ�޲�ѯ
				return jsqxManage(mapping, form, request, response);
			}else if("yhqxcx".equalsIgnoreCase(mk)){
				// �û�Ȩ�޲�ѯ
				return yhqxManage(mapping, form, request, response);
			}
		}
		
		return mapping.findForward("cxglManage");
	}
	
	/**
	 * �û���ɫ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService yhglService = new YhglService();
		CriterionQueryService queryService = new CriterionQueryService();
		
		String go = request.getParameter("go");
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){
			QxwhForm model = (QxwhForm)form; 
			request.setAttribute("rs", queryService.getJsyhList(model));
		}
		 
		request.setAttribute("jslxList", yhglService.getJslxList());
		request.setAttribute("jsszfwList", yhglService.getJsczfwList());
		request.setAttribute("jsList", yhglService.getRsList("xg_xtwh_jswhb", new HashMap<String, String>(), new String[]{"jsdm", "jsmc"}));
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		request.setAttribute("topTr", yhglService.getTopTr("yhjs"));
		
		return mapping.findForward("yhjsManage");
	}
	
	/**
	 * ���ܽ�ɫ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gnjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return mapping.findForward("gnjsManage");
	}
	
	/**
	 * ��ɫȨ�޲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jsqxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService yhglService = new YhglService();
		CriterionQueryService queryService = new CriterionQueryService();
		
		String go = request.getParameter("go");
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){
			QxwhForm model = (QxwhForm)form; 
			request.setAttribute("rs", queryService.getJsqxList(model));
		}
		 
		request.setAttribute("jslxList", yhglService.getJslxList());
		request.setAttribute("jsszfwList", yhglService.getJsczfwList());
		request.setAttribute("jsList", yhglService.getRsList("xg_xtwh_jswhb", new HashMap<String, String>(), new String[]{"jsdm", "jsmc"}));
		
		request.setAttribute("topTr", yhglService.getTopTr("jsqx"));
		return mapping.findForward("jsqxManage");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhqxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService yhglService = new YhglService();
		CriterionQueryService queryService = new CriterionQueryService();
		
		String go = request.getParameter("go");
		
		// ��ѯ����
		if("go".equalsIgnoreCase(go)){
			QxwhForm model = (QxwhForm)form; 
			request.setAttribute("rs", queryService.getYhqxList(model));
		}
		 
		request.setAttribute("jsList", yhglService.getRsList("xg_xtwh_jswhb", new HashMap<String, String>(), new String[]{"jsdm", "jsmc"}));
		request.setAttribute("topTr", yhglService.getTopTr("yhqx"));
		
		FormModleCommon.requestSetList(new String[]{"bm"}, request);

		return mapping.findForward("yhqxManage");
	}
}

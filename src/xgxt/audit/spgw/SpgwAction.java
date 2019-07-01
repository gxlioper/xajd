package xgxt.audit.spgw;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批岗位Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpgwAction extends BasicAction {
	
	public ActionForward spgwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpgwService service = new SpgwService();
		SpgwForm myForm = (SpgwForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		HashMap<String, String> spgwMap = new HashMap<String, String>();
		if( doType != null && !"del".equals(doType) && !"".equals(doType)){
			if ("save".equalsIgnoreCase(doType)) {
				result = service.addSpgw(myForm);
				request.setAttribute("result", result);
			} else if ("modi".equalsIgnoreCase(doType)) {
				myForm.setId(id);
				result = service.modiSpgw(myForm);
				request.setAttribute("result", result);
			} else if ("update".equalsIgnoreCase(doType)) {
				myForm.setId(id);
				spgwMap = service.getSpgw(myForm);
			}
			request.setAttribute("doType", doType);
			request.setAttribute("rs", spgwMap);
			return mapping.findForward("spgwUpdate");
		}
		
		if("del".equalsIgnoreCase(doType)){
			result=service.delSpgw(myForm);
			request.setAttribute("result", result);
		}

		request.setAttribute("rs", service.getSpgwList(myForm));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr(myForm));
		request.setAttribute("title", "审批岗位");
		
		return mapping.findForward("spgwwh");
	}
	
	
	public ActionForward spgwYhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpgwService service = new SpgwService();
		SpgwForm myForm = (SpgwForm) form;
		String doType = request.getParameter("doType");
		boolean result = false;
		HashMap<String, String> spgwMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.delSpgwYh(myForm);
			result = service.addSpgwYh(myForm);
			request.setAttribute("result", result);
		} else if ("view".equalsIgnoreCase(doType)) {
			request.setAttribute("rs", service.getGwyhLists(myForm));
			return mapping.findForward("spgwyhView");
		}
		
		request.setAttribute("yhList", service.getNotExistedYhList(myForm));
		request.setAttribute("gwyhList", service.getGwyhList(myForm));
		request.setAttribute("doType", doType);
		request.setAttribute("spgw", myForm.getSpgw());
		DAO dao = DAO.getInstance();
		String sql = "select distinct zdm,zmc from yhzb where zdm<>'6727'";
		request.setAttribute("zList", dao.getList(sql, new String[] {},
				new String[] { "zdm", "zmc" }));
		return mapping.findForward("spgwyhwh");
	}

}

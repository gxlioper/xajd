package xgxt.pjpy.comm.pjpy.tzfwsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * 	���ĳ������Ŀ��Ϊ���������ޣ���ô�ṩѡ�������ָ����Ŀ��
 *  ����������ÿ��Ե���Ϊ��Щ��Ŀ
 * </p>
 * @author ³��
 */
public class PjpyTzfwszAction extends DispatchAction {
	
	private static final String SAVE = "save";
	private static final String QUERY = "query";
	private static final String DEL = "del";
	private static final String SUCCESS = "����ɹ�!";
	private static final String FAIL = "����ʧ��!";
	private static final String DELSUCCESS = "ɾ���ɹ�!";
	private static final String DELFAIL = "ɾ��ʧ��!";
	

	
	/**
	 * ������Ŀ������Χ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzfwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTzfwszForm myForm = (PjpyTzfwszForm) form;
		PjpyTzfwszService service = new PjpyTzfwszService();
		PjpyXmszService xmszService = new PjpyXmszService();
		String[] topTr = new String[]{"��Ŀ����","������Χ"};
		
		String doType = request.getParameter("doType");
		
		if (DEL.equals(doType)){
			String[] xmdm = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.delTzfwsz(xmdm) ? DELSUCCESS : DELFAIL);
			//doType = QUERY;
		}
		
		//if (QUERY.equals(doType)){
		List<HashMap<String,String>> rs = service.tzfwxmQuery(myForm);
		//}
		
		xmszService.initList(request);
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "pjpy_xmsz_tzfwsz.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tzfwManage");
	}
	
	
	
	/**
	 * ѡ��������Ŀ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xzxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmszService xmszService = new PjpyXmszService();
		PjpyCommService commService = new PjpyCommService();
		
		xmszService.initList(request);
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		request.setAttribute("pjxtszModel",PjxtszModel.pjxtszModel);
		return mapping.findForward("xzxm");
	}
	
	
	/**
	 * ��Ŀ������Χ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzfwsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTzfwszForm myForm = (PjpyTzfwszForm) form;
		PjpyTzfwszService service = new PjpyTzfwszService();
		PjpyCommService commService = new PjpyCommService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			myForm.setXmdm(pkValue);
		}
		
		if (SAVE.equals(doType)){
			request.setAttribute("message", service.saveTzfwsz(myForm) ? SUCCESS : FAIL);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		return mapping.findForward("tzfwsz");
	}
	
	/**
	 * ��Ŀ������Χ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzfwszFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTzfwszForm myForm = (PjpyTzfwszForm) form;
		PjpyTzfwszService service = new PjpyTzfwszService();
		PjpyCommService commService = new PjpyCommService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			myForm.setXmdm(pkValue);
		}
		
		if (SAVE.equals(doType)){
			request.setAttribute("message", service.saveTzfwsz(myForm) ? SUCCESS : FAIL);
			return new ActionForward("/pjpy_ty_tjsz.do?method=tjszFlowAdd&doType=''",false);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		return mapping.findForward("tzfwszFlow");
	}
}

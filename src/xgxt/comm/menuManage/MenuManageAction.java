package xgxt.comm.menuManage;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>�����û����ͣ���˾��Ա</p>
 * <p>��;�����ܲ˵�������ѧУʹ�õĲ˵��Ͱ�ť�Ĺ���</p>
 * @author Penghui Qu
 * @version 1.0
 */
public class MenuManageAction extends BasicAction {

	private final static String QUERY = "query";
	private final static String SAVE = "save";
	private final static String EXP = "expData";
	private final static String SUCCESS = "����ɹ�!";
	private final static String FAIL = "����ʧ��!";
	
	
	
	/**
	 * ��ѯȫ�����ܲ˵�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward menuQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MenuManageForm myForm = (MenuManageForm) form;
		MenuManageService service = new MenuManageService();
		
		String tableName = "gnmkdmb";
		String viewName = "xg_view_gnmkdm";
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "pkValue", "gnmkmc", "gnmkdm","cdjb",
					"yjgnmkmc", "ejgnmkmc", "dyym", "xssx", "sfqy" };
		
		//��ѯ
		if (QUERY.equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (EXP.equals(doType)) {
			expPageData(request, response, tableName, viewName, new BasicService().getTableColumn(viewName));
			return mapping.findForward("");
		}
		
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "menuQuery.do");
		request.setAttribute("gnmkList", service.getTipGnmkList());
		request.setAttribute("topTr", new BasicService().getTopTr(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("menuQuery");
	}
	
	
	/**
	 * ��������˵��Ƿ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward batchSaveSfqy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MenuManageService service = new MenuManageService();
		String doType = request.getParameter("doType");
		
		if (SAVE.equals(doType)) {
			boolean result = false;
			String[] pkValue = request.getParameterValues("pkValue");
			
			if (null != pkValue && pkValue.length > 0) {
			
				String[] sfqy = new String[pkValue.length];
				for (int i = 0 ; i < pkValue.length ; i++) {
					sfqy[i] = request.getParameter("sfqy_"+i);
				}
				result = service.batchUpdateSfqy(pkValue, sfqy);
			}
			
			request.setAttribute("message", result ? SUCCESS : FAIL);
		}
		
		return menuQuery(mapping, form, request, response);
	}
	
	
	/**
	 * ���ܲ˵������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward menuUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MenuManageService service = new MenuManageService();
		MenuManageForm model = (MenuManageForm) form;
		
		String tableName = "gnmkdmb";
		String viewName = "xg_view_gnmkdm";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if (StringUtils.isNotNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		if (SAVE.equals(doType)) {
			//�޸Ŀ�ݷ�ʽͼƬ
			boolean result = service.updateShortcutPic(model);
			
			//�޸Ĺ��ܲ˵�����
			if (result) {
				service.checkExistsYhqx(model.getSave_gnmkdm());
				updateOperation(request, "gnmkdmb");
			} else {
				request.setAttribute("message", FAIL);
			}
		}
		
		request.setAttribute("gnmkList", service.getTipGnmkList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("btnList", service.getBtnList(((HashMap<String,String>)request.getAttribute("rs")).get("dyym")));
		return mapping.findForward("menuUpdate");
	}

	
	
	/**
	 * ��ݷ�ʽ����ͼƬ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setShortcutPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xtwh_kjfs_picb";
		String[] colList = new String[] {"picpath"};
		
		selectPageDataByPagination(request, form, tableName, tableName, colList);
		
		return mapping.findForward("setShortcutPic");
	}


	
	/**
	 * �˵��鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward menuTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MenuManageForm model = (MenuManageForm) form;
		MenuManageService service = new MenuManageService();
		String doType = request.getParameter("doType");
		
		if (SAVE.equals(doType)) {
			//�޸Ŀ�ݷ�ʽͼƬ
			boolean result = service.updateShortcutPic(model);
			
			//�޸Ĺ��ܲ˵�����
			if (result) {
				service.checkExistsYhqx(model.getSave_gnmkdm());
				updateOperation(request, "gnmkdmb");
			} else {
				request.setAttribute("message", FAIL);
			}
		}
		
		request.setAttribute("path", "menuTree.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("picpath", model.getPath());
		//����һ���˵�
		request.setAttribute("yjGnmkList", service.getGnmkList("1"));
		/*request.setAttribute("ejGnmkList", service.getGnmkList("2"));
		request.setAttribute("sjGnmkList", service.getGnmkList("3"));*/
		return mapping.findForward("menuTree");
	}
	
	
}

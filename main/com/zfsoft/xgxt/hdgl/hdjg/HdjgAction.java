/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdjg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;

/**
 * @className	�� HdjgAction
 * @description	�� ����action(��������������)
 * @author 		��lj��1282��
 * @date		�� 2018-4-11 ����02:27:25
 * @version 	V1.0 
 */

public class HdjgAction extends SuperAction<HdxxForm, HdxxService> {
	private static final String url = "hdgl_hdgl_hdjg.do";
	
	/**
	 * @description	�� �����б�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-12 ����04:36:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward hdjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		HdxxService hdxxService = new HdxxService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = hdxxService.getPageListForResult(model, user);

			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "hdgl_hdgl_hdjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdjgList");
	}
	
	/**
	 * @description	�� 100����б�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-12 ����04:36:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = "hdgl_hdgl_hdxjg.do")
	public ActionForward hdxjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		HdxxService hdxxService = new HdxxService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			List<HashMap<String, String>> resultList = hdxxService.getPageListForHdxjgByStu(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "hdgl_hdgl_hdxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdxjgList");
	}

	@SystemAuth(url = "hdgl_hdgl_hdxjg.do")
	public ActionForward hdxjgXqList(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		HdxxService hdxxService = new HdxxService();
		// ��ѯ
		List<HashMap<String, String>> resultList = hdxxService.getPageListForHdxjg(model);
		request.setAttribute("list",resultList);
		return mapping.findForward("hdjgxqList");
	}
	/**
	 * @description	��100�������б�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-12 ����04:36:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = "hdgl_hdgl_jzjg.do")
	public ActionForward jzjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		HdxxService hdxxService = new HdxxService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = hdxxService.getPageListForJzjgByStu(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "hdgl_hdgl_jzjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jzjgList");
	}

	@SystemAuth(url = "hdgl_hdgl_jzjg.do")
	public ActionForward jzjgXqList(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) throws Exception{

		HdxxForm model = (HdxxForm) form;
		HdxxService hdxxService = new HdxxService();
		List<HashMap<String, String>> resultList = hdxxService.getPageListForJzjg(model);
		request.setAttribute("jzlist",resultList);
		return mapping.findForward("jzjgxqList");
	}
}

/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjyyxx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class JspjyyxxAction extends SuperAction{
	private static final String url = "xg_dekt_jspjglyyxx.do";
	private JspjyyxxService service = new JspjyyxxService();
	
	@SystemAuth(url = url)
	public ActionForward jspjyyxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JspjyyxxForm model = (JspjyyxxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
	
		return mapping.findForward("jspjhfcx");
		
	}
	
	/**
	 * @description	�� �鿴
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-19 ����05:17:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JspjyyxxForm model = (JspjyyxxForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		//request.setAttribute("jbxx", xsjbxx);
		//��ѯ������Ϊ��Ϣ���
		//HashMap<String,String> gfqkMap = service.getJspjInfo(model);
		//JspjyyxxService jspjyyxxService = new JspjyyxxService();
		//HashMap<String,String> xsjbxx = service.getXsjbxxMore(model.getXh());
		HashMap<String,String> jshfMap = service.getJspjyyxx(model);
		request.setAttribute("jshf", StringUtils.formatData(jshfMap));
		request.setAttribute("rs", StringUtils.formatData(xsjbxx));
		//ѧ��������Ϣ��ʾ����
		request.setAttribute("model", StringUtils.formatData(model));
		
		
		model.setJssfty(jshfMap.get("jssfty"));
		return mapping.findForward("jspjck");
	}
	
	
	public ActionForward szdwjssfty_10698(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jssfty = request.getParameter("jssfty");
		String jshfxx = request.getParameter("jshfxx"); 
		String sqid = request.getParameter("sqid");
		boolean flag = service.szdwSzSave(jssfty, jshfxx,sqid);
		String message = flag ? "����ɹ���":"����ʧ�ܣ�";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}

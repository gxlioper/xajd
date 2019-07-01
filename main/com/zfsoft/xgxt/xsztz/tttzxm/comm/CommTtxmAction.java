/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-25 ����08:51:27 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.comm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.jyglnew.jygl.cyyqgl.CyyqglForm;
import com.zfsoft.xgxt.jyglnew.jygl.cyyqgl.CyyqglService;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-25 ����08:51:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CommTtxmAction extends SuperAction<TttzxmForm, CommTtxmService> {
	 CommTtxmService service = new CommTtxmService();
	/**
	 * @throws Exception 
	 * 
	 * @����:��Ŀѡ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����09:09:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getXmSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TttzxmForm model = (TttzxmForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// ��ѯ
				List<HashMap<String, String>> resultList = service.getPageList(model, user);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// Ĭ�ϸ߼���ѯ����
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel);
			String path = "ttxm_comm.do?method=getXmSelect";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("ttxmxz");
		
	}
	
	/**
	 * 
	 * @����: ѧ����������ѧ��¼���Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����05:41:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward EnterXh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String xmdm = request.getParameter("xmdm");
		String xhstr = request.getParameter("xhs");
		String[] xhs = null;
		if(StringUtils.isNotNull("xhstr")){
			xhs = xhstr.split(",");
		}
		//��ȡ�ı�����ѧ�ŵ�ѧ����Ϣ
		HashMap<String, String> xsmap = service.getXsxx(xh,xmdm,xhs);
		JSONObject jsonObj = JSONObject.fromObject(xsmap);
		response.getWriter().print(jsonObj);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʦ¼���Ա��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����05:42:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "ttxm_comm.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("xhs", myForm.getXhs());
		return mapping.findForward("getStuSelect");
	}
	
}

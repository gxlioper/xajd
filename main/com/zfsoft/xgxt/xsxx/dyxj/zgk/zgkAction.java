/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-2 ����05:37:20 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zgk;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-2 ����05:37:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class zgkAction extends SuperAction<zgkForm,zgkService> {
	private  zgkService service = new zgkService();
	/**
	 * 
	 * @����:ɾ��
	 * @��ע: �����ɾ���������д������б�ʹ�õ����ݣ�������ɾ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����10:21:50
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
	public ActionForward delJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			String message = "";
			if(!service.checkIsNotUsed(ids)){
				message = "�ʸ�������ѱ�ʹ�ã����ܱ�ɾ����";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: �����ʸ��ѧ������б��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����02:10:39
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
	public ActionForward getDyzgkCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		zgkForm model = (zgkForm) form;
	    zgkService  service = new zgkService();
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
		String path = "xsxx_dyxj_dyzgk.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ���ѧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����02:42:00
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
	public ActionForward addZgk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if(StringUtils.isNotNull(values)){
			String[] xhs = values.split(",");
			boolean result = service.saveAddXsIntoZgk(xhs);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ѡ��ѧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����05:54:29
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
	public ActionForward showStudent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		zgkForm model = (zgkForm) form;
	    zgkService  service = new zgkService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getZgkStuList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "dyxj_dyzgk.do?method=showStudent";
		request.setAttribute("path", path);
		request.setAttribute("xn",request.getParameter("xn") );
		request.setAttribute("xq",request.getParameter("xq") );
		request.setAttribute("gotoPath", request.getParameter("goto"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("showdyStudents");
	}
	
	/**
	 * 
	 * @����: 1.������������ʱѧ����������£���֤ѧ���Ƿ�����ʸ����
	 *    	  2.����������ʱѧ����������£���֤ѧ���Ƿ�����ʸ����
	 *        3.ѧ�Ų�Ϊ�գ�ѧ�꣬ѧ���л�����£���֤ѧ���Ƿ�����ʸ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-6 ����09:20:42
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
	public ActionForward checkXsIsInZgk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		zgkForm model = (zgkForm) form;
		boolean rs = service.checkIsInZgk(model.getXh(), model.getXn(), model.getXq());
		String message = rs ? "true" : "false";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����: �ڶ��ֱ��淽ʽ�����ݸ߼���ѯ�������������ʸ��ѧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-7 ����02:58:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveZgkXsbySeniorTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		zgkForm model = (zgkForm) form;
	    zgkService  service = new zgkService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model, user);
		if(null != resultList && resultList.size() > 0 && StringUtils.isNotNull(resultList.get(0).get("xh")) ){
			boolean result = service.saveAddXsIntoZgkBytj(resultList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			String messageKey = "�߼���ѯ����û��������ѧ�����ݣ�";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
	}
}

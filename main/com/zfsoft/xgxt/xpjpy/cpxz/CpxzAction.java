/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����02:00:09 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.utils.StringUtil;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013-����С�� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����02:00:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpxzAction extends SuperAction {
	
	private static final String url = "pj_cpxz.do";
	
	/**
	 * 
	 * @����: �������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-22 ����10:07:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward viewCpxzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();
		
		if (QUERY.equals(model.getType())){
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
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		String path = "pj_cpxz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewCpxzList");
	}
	
	/**
	 * 
	 * @����: �Զ����ò�����_���³�ʼ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-28 ����03:45:53
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-����������-��ʼ��-CPZCSH��{cpzcsh}")
	public ActionForward zdszCpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzService service = new CpxzService();
		CpxzModel model = (CpxzModel) form;
		
		if ("cxjs".equals((model.getType()))) {
			
			String cpzcsh = request.getParameter("cpzcsh");
			
			boolean result = service.initCpxz(getUser(request),cpzcsh);
			String messageKey = result ? MessageKey.SYS_INIT_SUCCESS
					: MessageKey.SYS_INIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		return mapping.findForward("zdszCpz");
	}
	
	
	/**
	 * 
	 * @����: �Զ����ò�����_���ò�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-28 ����03:45:53
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward szCpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzService service = new CpxzService();
		
		String ids = request.getParameter("ids");
		
		List<HashMap<String, String>> bjInfo = service.getBjInfo(ids);
		request.setAttribute("bjInfo", bjInfo);
		
		return mapping.findForward("szCpz");
	}
	
	
	/**
	 * 
	 * @����:�Զ����ò�����_���ò�����_����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-13 ����09:47:57
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-����������-����-����BJDM��{bjdm}")
	public ActionForward saveCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzService service = new CpxzService();
		CpxzModel model = (CpxzModel) form;
		
		boolean result = service.initCpzsz(model.getBjdm(),model.getPmz());
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null ;
	}

	@SystemAuth(url = "pj_cpzglywh.do")
	public ActionForward viewCpzglyList(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();

		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getCpzglyList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();

		request.setAttribute("cssz", csszModel);
		String path = "pj_cpzglywh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewCpzglyList");
	}

	@SystemAuth(url = "pj_cpzglywh.do")
	public ActionForward fpgly(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)throws Exception{

		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();

		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getGlyList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("cpzdms", model.getCpzdms());
		request.setAttribute("path", "xpj_cpxz.do?method=fpgly");
		request.setAttribute("isShow", model.getCpzdms().split(",").length == 1);
		return mapping.findForward("cpzfpgly");
	}

	public ActionForward saveFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();
		boolean rs = service.saveFp(model);
		String message = rs ? "����ɹ���" :"����ʧ�ܣ�";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	public ActionForward cancelFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();
		boolean rs = service.cancelfp(model);
		String message = rs ? "ȡ���ɹ���" :"ȡ��ʧ�ܣ�";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}



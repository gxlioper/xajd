/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��12�� ����11:02:38 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

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
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ���������춯 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��12�� ����11:02:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PlydAction extends SuperAction<PlydModel, PlydService> {

	private static final String url = "gygl_plyd.do";
	
	/**
	 * 
	 * @����: ���������춯
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��12�� ����1:53:09
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
	@SystemAuth(url = url)
	public ActionForward plydList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		request.setAttribute("path", "gygl_plyd.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("plydList");
	}
	
	
	/**
	 * 
	 * @����: ����ס�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��12�� ����2:44:17
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
	@SystemAuth(url = url)
	public ActionForward getYrzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydModel t = (PlydModel) form;
		User user = getUser(request);
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gygl_plyd.do");
		t.setSearchModel(searchModel);
		
		List<HashMap<String,String>> yrzList = getService().getYrzPageList(t, user);
		JSONArray dataList = JSONArray.fromObject(yrzList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @����: �������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��12�� ����2:44:36
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
	@SystemAuth(url = url)
	public ActionForward getDtzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydModel t = (PlydModel) form;
		User user = getUser(request);
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gygl_plyd.do");
		t.setSearchModel(searchModel);
		
		List<HashMap<String,String>> dtzList = getService().getDtzPageList(t, user);
		JSONArray dataList = JSONArray.fromObject(dtzList);
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	
	/**
	 * 
	 * @����: ȷ�ϵ����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��12�� ����2:44:50
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
	@SystemAuth(url = url)
	public ActionForward getQrtzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydModel t = (PlydModel) form;
		User user = getUser(request);
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gygl_plyd.do");
		t.setSearchModel(searchModel);
		
		List<HashMap<String,String>> qrtzList = getService().getQrtzList(t, user);
		JSONArray dataList = JSONArray.fromObject(qrtzList);
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	
	/**
	 * 
	 * @����: ���ô�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����11:19:35
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-���������춯-����Ϊ������PK:{ids}")
	public ActionForward szDtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		
		boolean result = getService().szDtz(ids);
		response.getWriter().print(result);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ȡ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����01:43:10
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-���������춯-ȡ��������PK:{ids}")
	public ActionForward qxDtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String ids = request.getParameter("ids");
		
		boolean result = getService().qxDtz(ids);
		response.getWriter().print(result);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ȡ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����01:43:10
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-���������춯-ȡ������PK:{ids}")
	public ActionForward qxtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String ids = request.getParameter("ids");
		
		boolean result = getService().qxtz(ids);
		response.getWriter().print(result);
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ������ס
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:07:39
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-���������춯-������סPK:{ids}")
	public ActionForward tzrz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String ids = request.getParameter("ids");
		PlydService service = getService();
		
		List<HashMap<String,String>> xhList = service.getTzxsList(ids);
		
		if (xhList != null && !xhList.isEmpty()){
			HashMap<String,Object> fyxx = service.getCwxxList(xhList.get(0).get("xb"));
			request.setAttribute("fyxx", fyxx);
		}
		
		request.setAttribute("xhList", xhList);
		return mapping.findForward("tzrz");
	}
	
	
	
	/**
	 * 
	 * @����: ������ס
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-19 ����02:55:12
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-���������춯-������סPK:{rzxx}")
	public ActionForward saveXsrz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String rzxx = request.getParameter("rzxx");
		boolean result = getService().saveRzxx(rzxx);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ȷ���ύ 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-24 ����02:26:49
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-���������춯-ȷ���ύ")
	public ActionForward qrtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydService service = getService();
		int bktj = service.getCountByBktj();
		
		if (bktj > 0){
			response.getWriter().print(getJsonMessageByKey(MessageKey.GYGL_PLYD_BKTJ));
		} else {
			boolean result = service.qrtz();
			String messageKey = result ? MessageKey.GYGL_PLYD_QRTZ_SUCCESS : MessageKey.GYGL_PLYD_QRTZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		
		return null;
	}
}

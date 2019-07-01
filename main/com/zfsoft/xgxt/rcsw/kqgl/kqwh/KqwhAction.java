/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-26 ����05:31:13 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-�������-����ģ��
 * @�๦������: ����ά��service
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-26 ����05:31:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqwhAction extends SuperAction<KqwhForm, KqwhService> {
	
	private static final String url = "rcsw_zjsy_kqwh.do";
	
	private KqwhService service = new KqwhService();
	
	/**
	 * 
	 * @����:����ά����ת
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����11:59:12
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
	public ActionForward kqwhKqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setSearch_tj_yf(new String[]{DateUtils.getMonth()});
		String dqzc = service.getDqZc();
		if(!StringUtil.isNull(dqzc)){
			searchModel.setSearch_tj_zjsyzc(new String[]{dqzc});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zjsy_kqwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kqwhKqList");
	}
	
	/**
	 * 
	 * @����:�������list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����11:58:49
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
	public ActionForward getKqjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqwhForm model = (KqwhForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @����:����ά��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����02:12:57
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
	public ActionForward updateKqwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqwhForm model = (KqwhForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			String objStr = request.getParameter("objStr");
			List<KqwhForm> list = JsonUtil.jsonArrToList(objStr,KqwhForm.class);
			model.setJlr(getUser(request).getUserName());
			boolean result = service.saveKqinfo(model, list);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String,String>> kqinfoList= service.getKqinfo(model.getId());
		KqwhForm myForm = service.getModel(model.getId());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("kqinfoList", kqinfoList);
		this.saveToken(request);
		return mapping.findForward("updateKqwh");
	}
	
	
	/**
	 * 
	 * @����:��ѯ�ж�������¼�����ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����07:37:28
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
	public ActionForward checkSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqwhForm kqwhForm = (KqwhForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		kqwhForm.setSearchModel(searchModel);
		
		String values = request.getParameter("values");
		
		List<HashMap<String, String>> isCanSubmit = service.isCanSubmit(values,kqwhForm,user);
		
		response.getWriter().print(isCanSubmit.size());
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����08:29:38
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqwhForm kqwhForm = (KqwhForm) form;
		
		String values = request.getParameter("values");
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		kqwhForm.setSearchModel(searchModel);
		
		int[] result = service.submit(values,kqwhForm,user);
		
		String messageKey = result[1]==0 ? MessageKey.SYS_SUBMIT_SUCCESS
				: "�ύ�ɹ���"+result[0]+"����ʧ�ܣ�"+result[1]+"����";
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @����:����ά������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-28 ����10:27:08
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
//	@SystemLog(description="�ճ�����-�������-����ά��-����VALUES��{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		KqwhForm model = service.getModel(values);
		String splc = model.getSplc();
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(values, splc);
		
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}

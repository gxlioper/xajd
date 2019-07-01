/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-3-21 ����09:18:53 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.task.TaskHandler;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_��������_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-21 ����09:18:53 
 * @�汾��  V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction{
	private static final String url = "xpjpy_jbsz_cssz.do";
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-21 ����11:40:01
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
	public ActionForward getCsszList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszService service = new CsszService();
		CsszForm model = (CsszForm) form;
		CsszForm csszModel = service.getCsszModel();
		model.setXn(csszModel.getXn());
		if (QUERY.equals(model.getType())){
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*����۲���Ŀ�Ƿ�ʹ��*/
		String zcxmCount = service.checkZcxmMade(model.getXn());
		request.setAttribute("zcxmCount", zcxmCount);
		/*�õ����������б�*/
		List<HashMap<String, String>> pjzqList = service.getPjzqList();
		request.setAttribute("pjzqList", pjzqList);
		/*��������{ "1", "����" }, { "0", "�ر�" }*/
		request.setAttribute("pjkgList", new OptionUtil().getOptions(OptionUtil.ONOFF));
		String path = "xpjpy_jbsz_cssz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if (csszModel != null){
			/*��������ֵ*/ 
			BeanUtils.copyProperties(model, csszModel);
		}
		return mapping.findForward("listCssz");
	}
	
	/**
	 * @����: ���������Զ����淢��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-27 ����10:33:53
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
	@SystemLog(description="������������-��������-��������-����ZDKEY:{zdKey} ZDVALUE:{zdValue}")
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zdKey = request.getParameter("zdKey");
		String zdValue = request.getParameter("zdValue");
		
		/*����Ķ�����Ϣ(�������ء��������ڡ���ֹʱ��)��xg_zjdx_pjpy_csszb*/
		CsszService service = new CsszService();
		service.updateCssz(zdKey, zdValue);
		
		/*�ر�����ģ����߳�����*/
		if ("pjkg".equals(zdKey) && "0".equals(zdValue)){
			TaskHandler.getInstance().shutdown("pjpy");
		}
		
		/*ѡ����������(xn),��ʼ������*/
		if("xn".equals(zdKey) && !StringUtil.isNull(zdValue)){
			/*��ʼ���۲�ṹ*/
			service.initZcxmList(zdValue);
			/*��ʼ������С��*/
			service.initCpxz(getUser(request));
			/*�жϵ�ǰ����������Ա���Ƿ�Ϊ�� ? ������У����ʼ�� : ��������*/
			 boolean sfcz = service.getSfcz();
			 if(!sfcz){
				 /*������Ա��ִ�г�ʼ������*/
				 service.init();
			 }
		}
		return null;
	}
	
	/**
	 * @����: �����۲���Ŀ����ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-28 ����08:44:30
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
	public ActionForward addZcxm (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		CsszForm csszModel = service.getCsszModel();
		model.setXn(csszModel.getXn());
		request.setAttribute("yjxmlist", service.getYjxmlist(model.getXn()));
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("addZcxm");
	}
	
	/**
	 * @����: �����۲���Ŀ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-8 ����03:09:42
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
	@SuppressWarnings("unchecked")
	public ActionForward saveForAdd (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm myFrom = (CsszForm)form;
		CsszService service = new CsszService();
		String objStr = request.getParameter("objStr");
		List<CsszForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr,CsszForm.class);
		}
		myFrom.setXn(service.getCsszModel().getXn());
		//�ж�ͬһ��ѧ�ꡢ��Ŀ���Ʋ����ظ�
		boolean isExistXmmc = service.isExistByZcxm(myFrom);
		boolean checkLxcf = service.checkLxcf(jxxxList);
		if(!isExistXmmc && !checkLxcf){
			boolean result = service.saveAddXmlxDj(myFrom,jxxxList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_REPEATING, false));
		}
		return null;
	}
	
	/**
	 * @����: ɾ���۲���Ŀ��Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-1 ����01:59:19
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
	public ActionForward delZcxm (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszService service = new CsszService();
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int numDj = service.numDj(values.split(","));
			int num = service.numFz(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * �޸ķ���ҳ��
	 */
	public ActionForward updateZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xmdm = request.getParameter("xmdm");
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		//�����۲���Ŀ��ԭ�����ݣ����ص�model
		HashMap<String, String> csszForm = service.getZcxmDate(xmdm);
		if(null!=csszForm){
			BeanUtils.copyProperties(model, StringUtils.formatData(csszForm));
			request.setAttribute("csszForm", StringUtils.formatData(csszForm));
		}
		// �����۲���Ŀ_�ȼ�checkBox����
		List<HashMap<String, String>> zcxmDjList = service.getZcxmDjList(xmdm);
		request.setAttribute("zcxmDjList", zcxmDjList);
		String path = "xpjpy_cssz.do?method=updateZcxm";
		request.setAttribute("xmlx", model.getXmlx());
		request.setAttribute("yjxmlist", service.getYjxmlist(model.getXn()));
		request.setAttribute("path", path);
		return mapping.findForward("updateZcxm");
	}
	
	@SystemAuth(url = url)
	@SuppressWarnings("unchecked")
	public ActionForward saveForUpdate (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CsszForm myFrom = (CsszForm)form;
		CsszService service = new CsszService();
		String objStr = request.getParameter("objStr");
		
		List<CsszForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr,CsszForm.class);
		}
		myFrom.setXn(service.getCsszModel().getXn());
		boolean checkLxcf = service.checkLxcf(jxxxList);
		if(!checkLxcf){
			boolean result = service.saveUpdateXmlxDj(myFrom,jxxxList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_REPEATING, false));
		}
		return null;
	}
}

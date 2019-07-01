/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-21 ����05:03:11 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.FbzgForm;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.FbzgService;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.SfbzForm;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.SfbzService;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-21 ����05:03:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction  extends SuperAction<CsszForm, CsszService> {
	/**
	 * @���ԣ� SPL_MKID ģ��ID
	 */
	public static final String SPL_MKID = "jjgl";
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	private static final String PATH = "jjgl_jcsz.do";
	
	/**
	 *  @���ԣ�DMWH_PATH ·��
	 */
	private static final String DMWH_PATH = "jjxt_dmwh.do";

	/**
	 * @���� ����������
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * @���� �ҽ�ѧ�Ʋ�ѯ
	 */
	private JjxkService jjxkService = new JjxkService();
	
	/**
	 * @���� �ҽ��꼶��ѯ
	 */
	private JjnjService jjnjService = new JjnjService();
	
	/**
	 * �շѱ�׼��ѯ
	 */
	private SfbzService sfbzService = new SfbzService();
	
	/**
	 * �����ʸ��ѯ
	 */
	private FbzgService fbzgService = new FbzgService();
	
	private static final String url = "jjgl_jcsz.do";
	
	/**
	 * 
	 * @����:�������ñ�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-22 ����01:04:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		/**
		 * ��ȡ��������
		 */
		Map<String , String> configDataMap = getService().getConfigMap();
		/**
		 * ����modelֵ
		 */
		if(configDataMap != null){
			BeanUtils.populate(form, configDataMap);
		}
		
		List<HashMap<String, String>> kgOptions = getService().getKgOptions();
		request.setAttribute("kgOptions", kgOptions);
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(SPL_MKID));
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}

	/**
	 * 
	 * @����:�������������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����10:20:29
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
	public  ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		CsszForm model = (CsszForm)	form;
		boolean result = false;
		JSONObject message = null;
		result = getService().saveCssz(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	
	
	
	
	
	
	//======================����ά��===========================//
	/**
	 * 
	 * @����:����ά��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-22 ����01:04:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@SystemAuth(url = DMWH_PATH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", DMWH_PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dmwh");
	}
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����01:59:18
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
	@SystemAuth(url = DMWH_PATH)
	public ActionForward queryDataList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)	form;
		List<HashMap<String,String>> resultList = null;
		String qryType = model.getQryType();
		//�ҽ�ѧ��
		if(StringUtils.equals("jjxk", qryType)){
			JjxkForm queryModel = new JjxkForm();
			queryModel.setPages(model.getPages());
			resultList = this.jjxkService.getPageList(queryModel);
		//�ҽ��꼶
		}else if(StringUtils.equals("jjnj", qryType)){
			JjnjForm queryModel = new JjnjForm();
			queryModel.setPages(model.getPages());
			resultList = this.jjnjService.getPageList(queryModel);
		}
		//��ѯ
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����04:03:57
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
	@SystemAuth(url = DMWH_PATH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)	form;
		String qryType = model.getQryType();
		
		if(StringUtils.equals("sfbz", qryType)){
			
			request.setAttribute("jjxkList", jjxkService.getAllList(new JjxkForm()));
			request.setAttribute("jjnjList", jjnjService.getAllList(new JjnjForm()));
		}
		
		
		String forward = "add_" + qryType;
		return mapping.findForward(forward);
	}
	
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����04:03:57
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
	@SystemAuth(url = DMWH_PATH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)	form;
		String qryType = model.getQryType();
		String dm = model.getDm();
		if(StringUtils.equals("jjxk", qryType)){
			JjxkForm dataModel = jjxkService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
		}else if(StringUtils.equals("jjnj", qryType)){
			JjnjForm dataModel = jjnjService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
		}else if(StringUtils.equals("fbzg", qryType)){
			FbzgForm dataModel = fbzgService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
		}else if(StringUtils.equals("sfbz", qryType)){
			SfbzForm dataModel = sfbzService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
			request.setAttribute("jjxkList", jjxkService.getAllList(new JjxkForm()));
			request.setAttribute("jjnjList", jjnjService.getAllList(new JjnjForm()));
		}
		
		
		String forward = "update_" + qryType;
		return mapping.findForward(forward);
	}
	
	/**
	 * @return the shlcService
	 */
	public XtwhShlcService getShlcService() {
		return shlcService;
	}

	/**
	 * @param shlcServiceҪ���õ� shlcService
	 */
	public void setShlcService(XtwhShlcService shlcService) {
		this.shlcService = shlcService;
	}

	/**
	 * @return the jjxkService
	 */
	public JjxkService getJjxkService() {
		return jjxkService;
	}

	/**
	 * @param jjxkServiceҪ���õ� jjxkService
	 */
	public void setJjxkService(JjxkService jjxkService) {
		this.jjxkService = jjxkService;
	}

	/**
	 * @return the jjnjService
	 */
	public JjnjService getJjnjService() {
		return jjnjService;
	}

	/**
	 * @param jjnjServiceҪ���õ� jjnjService
	 */
	public void setJjnjService(JjnjService jjnjService) {
		this.jjnjService = jjnjService;
	}

	/**
	 * @return the sfbzService
	 */
	public SfbzService getSfbzService() {
		return sfbzService;
	}

	/**
	 * @param sfbzServiceҪ���õ� sfbzService
	 */
	public void setSfbzService(SfbzService sfbzService) {
		this.sfbzService = sfbzService;
	}

	/**
	 * @return the fbzgService
	 */
	public FbzgService getFbzgService() {
		return fbzgService;
	}

	/**
	 * @param fbzgServiceҪ���õ� fbzgService
	 */
	public void setFbzgService(FbzgService fbzgService) {
		this.fbzgService = fbzgService;
	}

}

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-22 ����02:46:50 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcxm;

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

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲���Ŀ 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-22 ����02:46:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcxmAction extends SuperAction {

	
	public ActionForward viewZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("viewZcxm");
	}
	
	
	/**
	 * 
	 * @����: ��ȡ�۲���Ŀ�ṹ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����06:53:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward getZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmService service = new ZcxmService();
		Map<String,List<HashMap<String,String>>> map = service.getZcxm();
		
		JSONObject json = JSONObject.fromMap(map);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��������������Ŀҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����09:32:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward addZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel zcxmForm = (ZcxmModel) form;
		zcxmForm.setXmlx(ZcxmService.XMLX_PUSH);
		
		OptionUtil optionUtil = new OptionUtil();
		//������Ŀ����
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.ZHCP_XMLX);
		
		request.setAttribute("xmlxList", xmlxList);
		return mapping.findForward("addZcxm");
	}
	
	
	/**
	 * 
	 * @����: �����޸��۲���Ŀҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����09:50:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward editZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		CsszService csszService = new CsszService();
		ZcxmModel model = service.getModel(zcxmForm);
		boolean isUpdate = false;
		if (model != null){
			BeanUtils.copyProperties(zcxmForm, StringUtils.formatData(model));
		}
		
		OptionUtil optionUtil = new OptionUtil();
		//������Ŀ����
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.ZHCP_XMLX);
		CsszModel csszModel = csszService.getModel();
		
		if (ZcxmService.XMJB_QT != csszModel.getZcxmjb()){
			isUpdate = service.jcBlxg(model.getXmdm());
		}
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("xmlxList", xmlxList);
		request.setAttribute("isUpdate", isUpdate);
		return mapping.findForward("editZcxm");
	}
	
	
	/**
	 * 
	 * @����: �����۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����10:05:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward saveZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		zcxmForm.setXn(csszModel.getXn());
		zcxmForm.setXq(csszModel.getXq());
		
		//�жϵ�ǰѧ�������Ƿ��Ѵ���
		if(service.xmmcExist(zcxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
			
		}else{
			boolean result = service.runInsert(zcxmForm);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		
	}
	
	
	/**
	 * 
	 * @����: �޸��۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����10:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward updateZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		ZcxmModel myForm = service.getModel(zcxmForm);
		
		//�жϵ�ǰѧ�������Ƿ��Ѵ���
		if(!zcxmForm.getXmmc().equalsIgnoreCase(myForm.getXmmc())&&service.xmmcExist(zcxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
		}
		
		boolean result = service.runUpdate(zcxmForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: ɾ���۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����10:42:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward delZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		
		boolean result = service.delZcxm(zcxmForm.getXmdm());
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}



	
	/**
	 * 
	 * @����: ���꼶��ʾ�۲����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����02:43:41
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
	public ActionForward showXxbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmService zcxmService = new ZcxmService();
		//������ϸ�������۲���Ŀ
		List<HashMap<String,Object>> zcxmList = zcxmService.getZcxmListByXxbl();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("zcxmList", zcxmList);
		return mapping.findForward("showXxbl");
	}
	/**
	 * 
	 * @����:�����õ��·��б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-10-22 ����04:05:20
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
	public ActionForward showYf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		CsszService csszService = new CsszService();
		ZcxmService zcxmService = new ZcxmService();
		CsszModel csszModel = csszService.getModel();
		if (SAVE.equalsIgnoreCase(zcxmForm.getType())) {
			String[] zcyf=request.getParameterValues("zcyf");
			boolean result = zcxmService.saveYf(zcyf,csszModel.getXn(),csszModel.getXq());
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//�������·�
		request.setAttribute("yf", zcxmService.getYszYf(csszModel.getXn(),csszModel.getXq()));
		request.setAttribute("csszModel", csszModel);
		return mapping.findForward("showYf");
	}
	//��ȡ�·��б�
	public ActionForward getYf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmService zcxmService = new ZcxmService();
		//�����õ��·�
		List<HashMap<String,String>> szyfList = zcxmService.getSzyfList();
		JSONArray dataList = JSONArray.fromObject(szyfList);
		response.getWriter().print(dataList);
		return null;
	}
	

	/**
	 * 
	 * @����: ������ϸ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����11:33:30
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
	public ActionForward getXxblList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel model = (ZcxmModel) form;
		ZcxmService zcxmService = new ZcxmService();
		
		//��ϸ������ѯ���꼶��ѧԺ֮����service���Ѵ���
		List<HashMap<String,String>> xxblList = zcxmService.getXxblList(model);
//		
		JSONArray dataList = JSONArray.fromObject(xxblList);
		response.getWriter().print(dataList);
		
		return null;
	}

	
	/**
	 * 
	 * @����: �޸���ϸ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����03:50:51
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
	public ActionForward updateXxbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel model = (ZcxmModel) form;
		ZcxmService zcxmService = new ZcxmService();
		
		boolean result = zcxmService.updateXxblByBmdm(model);
		response.getWriter().print(result);
		
		return null;
	}





}

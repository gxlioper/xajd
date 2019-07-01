/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-1 ����02:46:50 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.jsxm;

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
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��羺��������Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-4-1 ����10:28:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BfjsJsxmAction extends SuperAction {
	private BfjsCsszService csszService = new BfjsCsszService();
	
	public ActionForward viewBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("viewBfjsJsxm");
	}
	
	
	/**
	 * 
	 * @����: ��ȡ������Ŀ�ṹ
	 * @���ߣ�xiaxia 1104
	 * @���ڣ�2016-4-1 ����06:53:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward getBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmService service = new BfjsJsxmService();
		Map<String,List<HashMap<String,String>>> map = service.getBfjsJsxm();
		
		JSONObject json = JSONObject.fromMap(map);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��������������Ŀҳ��
	 * @���ߣ�xiaxia [����:1104]
	 * @���ڣ�2016-4-1 ����09:32:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward addBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmForm.setXmlx(BfjsJsxmService.XMLX_PLUS);
		
		OptionUtil optionUtil = new OptionUtil();
		//������Ŀ����
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.BFJS_XMLX);
		
		request.setAttribute("xmlxList", xmlxList);
		return mapping.findForward("addBfjsJsxm");
	}
	
	
	/**
	 * 
	 * @����: �����޸ľ�����Ŀҳ��
	 * @���ߣ�xiaxia [����:1104]
	 * @���ڣ�2016-4-1 ����09:50:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward editBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		BfjsJsxmModel model = service.getModel(BfjsJsxmForm);
		boolean isUpdate = false;
		if (model != null){
			BeanUtils.copyProperties(BfjsJsxmForm, StringUtils.formatData(model));
		}
		
		OptionUtil optionUtil = new OptionUtil();
		//������Ŀ����
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.BFJS_XMLX);
		BfjsCsszModel csszModel = csszService.getModel();
		
		
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("xmlxList", xmlxList);
		request.setAttribute("isUpdate", isUpdate);
		return mapping.findForward("editBfjsJsxm");
	}
	
	
	/**
	 * 
	 * @����: ���Ӿ�����Ŀ
	 * @���ߣ�xiaxia [����:1104]
	 * @���ڣ�2016-4-1 ����10:05:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward saveBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		BfjsCsszService csszService = new BfjsCsszService();
		BfjsCsszModel csszModel = csszService.getModel();
		
		BfjsJsxmForm.setXn(csszModel.getXn());
		BfjsJsxmForm.setXq(csszModel.getXq());
		
		//�жϵ�ǰѧ�������Ƿ��Ѵ���
		if(service.xmmcExist(BfjsJsxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
			
		}else{
			boolean result = service.runInsert(BfjsJsxmForm);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		
	}
	
	
	/**
	 * 
	 * @����: �޸ľ�����Ŀ
	 * @���ߣ�xiaxia [����:1104]
	 * @���ڣ�2016-4-1 ����10:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward updateBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		BfjsJsxmModel myForm = service.getModel(BfjsJsxmForm);
		
		//�жϵ�ǰѧ�������Ƿ��Ѵ���
		if(!BfjsJsxmForm.getXmmc().equalsIgnoreCase(myForm.getXmmc())&&service.xmmcExist(BfjsJsxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
		}
		
		boolean result = service.runUpdate(BfjsJsxmForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: ɾ��������Ŀ
	 * @���ߣ�xiaxia [����:1104]
	 * @���ڣ�2016-4-1 ����10:42:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward delBfjsJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsJsxmModel BfjsJsxmForm = (BfjsJsxmModel) form;
		BfjsJsxmService service = new BfjsJsxmService();
		
		boolean result = service.delBfjsJsxm(BfjsJsxmForm.getXmdm());
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}



	
	/**
	 * 
	 * @����: ���꼶��ʾ�۲����
	 * @���ߣ�xiaxia[1104]
	 * @���ڣ�2016-4-1 ����02:43:41
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
		
		BfjsJsxmService BfjsJsxmService = new BfjsJsxmService();
		//������ϸ�����ľ�����Ŀ
		List<HashMap<String,Object>> BfjsJsxmList = BfjsJsxmService.getBfjsJsxmListByXxbl();
		BfjsCsszModel csszModel = csszService.getModel();
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("BfjsJsxmList", BfjsJsxmList);
		return mapping.findForward("showXxbl");
	}






}

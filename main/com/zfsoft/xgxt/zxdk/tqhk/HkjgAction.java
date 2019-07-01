/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-7 ����03:26:24 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.hkzt.HkztModel;
import com.zfsoft.xgxt.zxdk.hkzt.HkztService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-7 ����03:26:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HkjgAction extends SuperAction<HkjgModel, HkjgService> {

	private static final String url = "zxdk_tqhk_hkjg.do";
	
	/**�������б�**/
	@SystemAuth(url = url)
	public ActionForward hkjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_tqhk_hkjg.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hkjgList");
	}
	
	/**�������б�**/
	@SystemAuth(url = url)
	public ActionForward getHkjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkjgModel model = (HkjgModel) form;
		HkjgService service = new HkjgService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**������ǰ�����¼*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addTqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkjgModel model = (HkjgModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			DkjgService dkjgService = new DkjgService();
			List<HashMap<String,String>> khkList = null;
			if("10511".equals(Base.xxdm)){
				khkList = dkjgService.getKhkListHsd(model.getXh());
			}else{
				khkList = dkjgService.getKhkList(model.getXh());
			}
			request.setAttribute("khkList", khkList);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
		request.setAttribute("jbxxList", jbxxList);
		
		HkztService hkztService = new HkztService();
		List<HashMap<String,String>> hkztList = hkztService.getAllList(new HkztModel());
		request.setAttribute("hkztList", hkztList);
		
		String path = "zxdkHkjg.do?method=addTqhk";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addTqhk");
	}
	
	
	/**�޸���ǰ�����¼*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editTqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkjgModel hkjgForm = (HkjgModel) form;
		HkjgService service = new HkjgService();
		
		HkjgModel model = service.getModel(hkjgForm);
		
		if (model != null){
			BeanUtils.copyProperties(hkjgForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
				
				DkjgService dkjgService = new DkjgService();
				List<HashMap<String,String>> khkList = null;
				if("10511".equals(Base.xxdm)){
					khkList = dkjgService.getKhkListHsd(model.getXh());
				}else{
					khkList = dkjgService.getKhkList(model.getXh());
				}
				request.setAttribute("khkList", khkList);
			}
			
			//ѧ��������Ϣ��ʾ����
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
			request.setAttribute("jbxxList", jbxxList);
		}
		
		HkztService hkztService = new HkztService();
		List<HashMap<String,String>> hkztList = hkztService.getAllList(new HkztModel());
		request.setAttribute("hkztList", hkztList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("editTqhk");
	}
	
	/**ɾ����ǰ����*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delTqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HkjgService service = new HkjgService();
		String ids = request.getParameter("ids");
		
		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.action.SuperAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkjgModel model = (HkjgModel) form;
		HkjgService service = new HkjgService();
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**�鿴��ǰ�����¼*/
	@SystemAuth(url = url)
	public ActionForward viewTqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkjgModel hkjgForm = (HkjgModel) form;
		HkjgService service = new HkjgService();
		
		HkjgModel model = service.getModel(hkjgForm);
		
		if (model != null){
			BeanUtils.copyProperties(hkjgForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
				
				DkjgService dkjgService = new DkjgService();
				List<HashMap<String,String>> khkList = null;
				if("10511".equals(Base.xxdm)){
					khkList = dkjgService.getKhkListHsd(model.getXh());
				}else{
					khkList = dkjgService.getKhkList(model.getXh());
				}
				request.setAttribute("khkList", khkList);
				request.setAttribute("xxdm", Base.xxdm);
;			}
			
			//ѧ��������Ϣ��ʾ����
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
			request.setAttribute("jbxxList", jbxxList);
		}
		
		return mapping.findForward("viewTqhk");
	}
	
	/**
	 * 
	 * @����: ��ǰ������������ 
	 * @���ߣ� yxy[����:1206]
	 * @���ڣ�2014��6��9�� ����9:51:00
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		HkjgModel model = (HkjgModel) form;
		HkjgService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
}

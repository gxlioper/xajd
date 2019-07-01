/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-10 ����02:58:45 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.File;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ΥԼ��¼
 * @���ߣ� �Ƴ���
 * @ʱ�䣺 2015-11-26 ����9:41:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WyjlAction extends SuperAction<WyjlModel, WyjlService> {
	
	private static final String GJZXDK = "gjzxdk";

	private static final String url = "zxdk_gjdk_wyjl.do";
	
	@SystemAuth(url = url)
	public ActionForward wyjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		return mapping.findForward("wyjlList");
	}
	
	@SystemAuth(url = url)
	public ActionForward getWyjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyjlService service = getService();
		WyjlModel model = (WyjlModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ����ΥԼ��¼
	 * @���ߣ� �Ƴ���
	 * @���ڣ� 2015-11-26 ����9:41:41 
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
	@SystemLog(description="��ѧ����(NEW)-������ѧ����-ΥԼ��¼-����-JSON:{json}")
	public ActionForward addWyjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyjlModel model = (WyjlModel) form;
		WyjlService service = new WyjlService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("dqxn", Base.currXn);
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		//ΥԼ״̬ѡ������
		request.setAttribute("wyztList", service.getWyztList());
		
		String path = "zxdkWyjl.do?method=addWyjl";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addWyjl");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="��ѧ����(NEW)-������ѧ����-ΥԼ��¼-�޸�-JSON:{json}")
	public ActionForward editWyjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyjlService service = getService();
		WyjlModel myForm = (WyjlModel) form;
		
		WyjlModel model = service.getModel(myForm.getXh());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		//ΥԼ״̬ѡ������
		request.setAttribute("wyztList", service.getWyztList());
		//���������Ϣ
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("sjhm", myForm.getSjhm());
		request.setAttribute("qqhm", myForm.getQqhm());
		request.setAttribute("dzyx", myForm.getDzyx());
		request.setAttribute("wmhm", myForm.getWxhm());
		request.setAttribute("wyzt", myForm.getWyzt());
		request.setAttribute("wyxq", myForm.getWyxq());
		request.setAttribute("bz", myForm.getBz());
		request.setAttribute("dkxxList", service.getDkxxList(myForm));
		request.setAttribute("fkList", service.getFkList(myForm));
		
		return mapping.findForward("editWyjl");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward ckWyjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyjlService service = getService();
		WyjlModel myForm = (WyjlModel) form;
		
		WyjlModel model = service.getModel(myForm.getXh());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		//ΥԼ״̬ѡ������
		request.setAttribute("wyztList", service.getWyztList());
		//���������Ϣ
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("sjhm", myForm.getSjhm());
		request.setAttribute("qqhm", myForm.getQqhm());
		request.setAttribute("dzyx", myForm.getDzyx());
		request.setAttribute("wmhm", myForm.getWxhm());
		request.setAttribute("wyzt", myForm.getWyzt());
		request.setAttribute("wyxq", myForm.getWyxq());
		request.setAttribute("bz", myForm.getBz());
		request.setAttribute("dkxxList", service.getDkxxList(myForm));
		request.setAttribute("fkList", service.getFkList(myForm));
		request.setAttribute("xxdm", Base.xxdm);
		
		return mapping.findForward("ckWyjl");
	}
	
	/**��ѧ�Ų�ѯ��¼����**/
	public ActionForward getCountByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WyjlService service = getService();
		WyjlModel model = (WyjlModel) form;
		
		String count = service.getCountByXh(model);
		response.getWriter().print(count);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="��ѧ����(NEW)-������ѧ����-ΥԼ��¼-ɾ��-XH:{ids}")
	public ActionForward delWyjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyjlService service = getService();
		String ids = request.getParameter("ids");
		
		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward dcwy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WyjlService service = getService();
		WyjlModel model = (WyjlModel) form;

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
}

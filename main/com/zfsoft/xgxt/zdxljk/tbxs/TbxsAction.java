/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����09:13:40 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zdxljk.ecmm.EcmmModel;
import com.zfsoft.xgxt.zdxljk.ecmm.EcmmService;

/** 
 * @�๦������: ���������--�ر����ѧ�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-2-11 ����09:11:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class TbxsAction extends SuperAction<TbxsModel, TbxsService> {

	private static final String url = "zdxljk_tbxs.do";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		User user = getUser(request);
		String zgh = user.getUserName();
		String ecmm = request.getParameter("ecmm");
		
		if (!StringUtil.isNull(zgh) & !StringUtil.isNull(ecmm)){
			EcmmService ecmmService = new EcmmService();
			EcmmModel ecmmModel = ecmmService.getModelByLogin(zgh,ecmm);
			
			if (ecmmModel != null){
				session.setAttribute("ecdl","yes");
				return super.execute(mapping, form, request, response);
			} else {
				request.setAttribute("ecdl", "fail");
			}
		}
		
		String ecdl = (String) session.getAttribute("ecdl");
			
		if (StringUtil.isNull(ecdl)){
			return mapping.findForward("ecdl");
		}
		
		return super.execute(mapping, form, request, response);
	}


	/**�ر�ѧ���б�**/
	@SystemAuth(url = url)
	public ActionForward tbxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zdxljk_tbxs.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tbxsList");
	}
	
	
	/**�ر�ѧ���б�**/
	@SystemAuth(url = url)
	public ActionForward getTbxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**�ر�ѧ��̸����¼**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> thjlList = service.getThjlByXh(model.getXh());
			if(thjlList.size()>0){
				model.setQxyy(thjlList.get(0).get("qxyy"));
				model.setGzlx(thjlList.get(0).get("gzlx"));
				
			}
			
			request.setAttribute("thjlList", thjlList);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("gzlxList", new OptionUtil().getOptions(OptionUtil.THJL_GZLX));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ftr", user.getRealName());
		request.setAttribute("model", model);
		request.setAttribute("path", "zdxljkTbxs.do?method=addThjl");
		return mapping.findForward("addThjl");
	}
	
	
	/**�ر�ѧ��̸����¼**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		User user = getUser(request);
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> thjlList = service.getThjlByXh(model.getXh());
			if(thjlList.size()>0){
				model.setQxyy(thjlList.get(0).get("qxyy"));
				model.setGzlx(thjlList.get(0).get("gzlx"));
				
			}
			request.setAttribute("thjlList", thjlList);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzlxList", new OptionUtil().getOptions(OptionUtil.THJL_GZLX));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ftr", user.getRealName());
		request.setAttribute("model", model);
		
		return mapping.findForward("editThjl");
	}
	
	@SystemAuth(url = url)
	public ActionForward viewThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> thjlList = service.getThjlByXh(model.getXh());
			if(thjlList.size()>0){
				model.setQxyy(thjlList.get(0).get("qxyy"));
				model.setGzlx(thjlList.get(0).get("gzlx"));
				
			}
			request.setAttribute("thjlList", thjlList);
			
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		return mapping.findForward("viewThjl");
	}
	
	
	/**�ر�ѧ��̸����¼**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����������--�ر��עѧ��-����xh:{xh}")
	public ActionForward saveThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		boolean result = service.save(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����������--�ر��עѧ��-ɾ��ids:{ids}")
	public ActionForward delThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		TbxsService service = new TbxsService();
		
		int num = service.runDelete(ids.split(","));
		boolean result = num > 0;
		String message = result ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, num) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
	
		return null;
	}
	
	
	/**̸����¼����**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model, user);// ��ѯ�����м�¼������ҳ

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
	
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward szgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		TbxsModel model = (TbxsModel) form;
		request.setAttribute("model", model);
		request.setAttribute("gzlxList", new OptionUtil().getOptions(OptionUtil.THJL_GZLX));
		request.setAttribute("ids", ids);
		return mapping.findForward("szgz");
	}
	
	
	/** ���ù�ע **/
	@SystemLog(description="����������--�ر��עѧ��-���ù�עids:{ids}")
	public ActionForward saveSzgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbxsService service = new TbxsService();
		TbxsModel model = (TbxsModel) form;
		String ids = request.getParameter("ids");
		boolean result = service.saveSzgz(model,ids);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
}

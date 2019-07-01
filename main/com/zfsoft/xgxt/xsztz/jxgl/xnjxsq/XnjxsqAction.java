/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-25 ����06:13:41 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsq;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsh.XnjxshService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:1282]
 * @ʱ�䣺 2016-1-25 ����06:13:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxsqAction extends SuperAction<XnjxsqForm, XnjxsqService>{
	private static final String url = "sztz_jxgl_xnjxsq.do";
	private final String TZXMSQ ="tzxmsq";
	XnjxsqService service = new XnjxsqService();
	
	/** 
	 * @����:��ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-26 ����09:26:49
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
	public ActionForward xnjxsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxsqForm model = (XnjxsqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_jxgl_xnjxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xnjxsqList");
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-26 ����09:27:18
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
	public ActionForward xnjxsqAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxsqForm model = (XnjxsqForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("currxn", Base.currXn);
		String path = "jxgl_xnjxsq.do?method=xnjxsqAdd";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("xnjxsqAdd");
	}
	
	/** 
	 * @����:��ȡѧ����������չ��Ŀ��Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-26 ����11:13:43
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
	public ActionForward getXmSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxsqForm model = (XnjxsqForm) form;
		List<HashMap<String,String>> xmsqInfoList = service.getYiShen(model.getXh());
	    request.setAttribute("xmsqInfoList", xmsqInfoList);
		return mapping.findForward("xmselect");
	}
	
	
	public ActionForward getjxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxsqForm model = (XnjxsqForm) form;
		List<HashMap<String, String>> jxxxList = new XmsbService().getXmjxList(model.getXmdm());
		JSONArray dataList = JSONArray.fromObject(jxxxList);
		response.getWriter().print(dataList);
		return null;
	} 
	
	//������
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxsqForm model = (XnjxsqForm) form;
		boolean result = false;
	    User user = getUser(request);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
			result = service.saveSqjg(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ���������޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxsqForm myForm = (XnjxsqForm) form;
		XnjxsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(model.getXmdm());
		request.setAttribute("rs", xmxx);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		//HashMap<String, String> hdmap = service.getHdMap(model.getXmdm(),model.getXn(),model.getXq());//false
		//hdmap.put("sqid", model.getSqid());
		//request.setAttribute("hdmap", StringUtils.formatData(hdmap));
		String path = "jxgl_xnjxsq.do?method=editSqjg";
		request.setAttribute("path", path);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("editSqjg");
	}
	
	//ɾ��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delSqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
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
	
	//����
	public ActionForward cancelXmsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(id, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			XnjxsqForm model = new XnjxsqForm();
			model.setId(id);
			model.setShlc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(id)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//�ύ
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxsqForm myForm = (XnjxsqForm) form;
		String value = request.getParameter("values");
		String messageKey = "";
		myForm.setId(value);
		XnjxsqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
	    messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//��������
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XnjxsqForm model = (XnjxsqForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		
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
	
	
	/** 
	 * @����:�鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-28 ����04:03:27
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
	public ActionForward viewJx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxsqForm myForm = (XnjxsqForm) form;
		XnjxsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(model.getXmdm());
		
		request.setAttribute("rs", xmxx);
		request.setAttribute("id", myForm.getId());
		XnjxshService xnjxshService = new XnjxshService();
		String jxmc = xnjxshService.getJxmc(model.getJxid());
		String jxfs = xnjxshService.getJxfs(model.getJxid());
		String jcxf = xmxx.get("jcxf");
		String zf = String.valueOf((Integer.parseInt(jxfs)+Integer.parseInt(jcxf)));
		request.setAttribute("zf", zf);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		request.setAttribute("shzt", model.getShzt());
		request.setAttribute("jxmc", jxmc);
		return mapping.findForward("viewJx");

	}
	
}

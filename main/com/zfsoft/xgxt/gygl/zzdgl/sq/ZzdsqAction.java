/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-29 ����02:16:40 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sq;

import java.io.File;
import java.util.ArrayList;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.zzdgl.cssz.CsszForm;
import com.zfsoft.xgxt.gygl.zzdgl.cssz.CsszService;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgForm;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-29 ����02:16:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdsqAction extends SuperAction<ZzdsqForm, ZzdsqService>{
	private ZzdsqService service = new ZzdsqService();
	private CsszService csszService = new CsszService();
	private ZzdjgService zzdjgService = new ZzdjgService();
	private static final String ZZDSQ = "zzdsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZZDSQ);
	}
	
	private static final String url = "xgygl_zzdgl_zdsq.do";
	
	/** 
	 * @����:��ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����02:44:05
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
	public ActionForward getZzdsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
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
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "xgygl_zzdgl_zdsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZzdsqList");
	}
	
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����05:01:20
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
	public ActionForward addZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);		
		String path = "xgygl_zdsq.do?method=addZzdsq";
		request.setAttribute("path", path);
		request.setAttribute("xnsr", Base.currXn);
		request.setAttribute("xqsr", service.getXqmc(Base.currXq));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd"));
		return mapping.findForward("addZzdsq");
	}
	
	/** 
	 * @����:������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����05:25:33
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
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		boolean result = false;
	    User user = getUser(request);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			if(service.isHaveRecord(model)){
 				String messageKey = MessageKey.GYGL_ZZDGL_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
 			CsszForm csszForm = csszService.getModel();
 			if(null != csszForm){
 				model.setSplcid(csszForm.getSplcid());
 			}
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
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����09:30:44
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
	public ActionForward editZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm myForm = (ZzdsqForm) form;
		User user = getUser(request);
		ZzdsqForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		String path = "xgygl_zdsq.do?method=editZzdsq";
		request.setAttribute("path", path);
		request.setAttribute("sqsj",model.getSqsj());
		return mapping.findForward("editZzdsq");
	}
	
	/** 
	 * @����:ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����09:31:46
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
	public ActionForward delZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����09:43:52
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
	public ActionForward submitZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		String values = request.getParameter("values");
		model.setZzdid(values);
		boolean result = service.submiZzdsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2016-3-1 ����10:18:13
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
	public ActionForward cancelZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			ZzdsqForm model = new ZzdsqForm();
			model.setZzdid(sqid);
			model.setSplcid(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
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
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����10:19:09
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;	
	}
	
	/** 
	 * @����:�鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����06:11:04
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
	public ActionForward viewZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		request.setAttribute("jbxxList", jbxxList);
		ZzdsqForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		return mapping.findForward("viewZzdsq");

	}
	
	/** 
	 * @����:�����½�û�Ϊѧ���ж��Ƿ�ס��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����06:24:28
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
	public ActionForward isZhusu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		User user = getUser(request);
		boolean result = true;
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
			result = service.iszhusu(model);
			response.getWriter().print(result);
		}else{
			response.getWriter().print(result);
		}
		return null;
	}
	
	/** 
	 * @����:�жϹ���Ա�Ƿ�ȷ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����04:34:52
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
	public ActionForward isGlyqr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		boolean result = service.isGlyqr(model);
		response.getWriter().print(result);		
		return null;
	}
	
	/** 
	 * @����:���������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����06:30:27
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
	public ActionForward printzzdsqb (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxService xsxxService = new XsxxService();
		ZzdsqForm model = (ZzdsqForm) form;
		if(StringUtils.isNotNull(model.getZzdid())){
			List<File> files = new ArrayList<File>();
			String[] ids = model.getZzdid().split(",");
			for (String id : ids) {
				model.setZzdid(id);
				ZzdsqForm myForm = service.getModel(model);
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());				
				String qsxx = service.getQxmForPrint(myForm.getXh());
				xsjbxx.put("qsxx", qsxx);
				xsjbxx.put("sdyy", myForm.getSdyy());
				//xsjbxx.put("jtdz", HtmlUtil.xmlZy(xsjbxx.get("jtdz")));	
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}else{
			List<File> files = new ArrayList<File>();
			String[] jgids = model.getYlzd1().split(",");
			ZzdjgForm ZzdjgForm = new ZzdjgForm();
			for (String jgid : jgids) {
				ZzdjgForm.setJgid(jgid);
				ZzdjgForm = zzdjgService.getModel(ZzdjgForm);
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(ZzdjgForm.getXh());				
				String qsxx = service.getQxmForPrint(ZzdjgForm.getXh());
				xsjbxx.put("qsxx", qsxx);
				xsjbxx.put("sdyy", ZzdjgForm.getSdyy());
				//xsjbxx.put("jtdz", HtmlUtil.xmlZy(xsjbxx.get("jtdz")));	
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}

	
	
}

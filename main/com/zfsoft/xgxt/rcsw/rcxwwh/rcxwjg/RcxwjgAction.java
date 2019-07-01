/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:39:45 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ���
 * @���ߣ�dlq [���ţ�995]
 * @ʱ�䣺 2013-8-7 ����04:39:45
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RcxwjgAction extends SuperAction {
	//�����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "rcswrcxw";
	private static List<HashMap<String, String>> jbxxList = null;

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		
	}
	
	private static final String url = "rcsw_rcxwwh_rcxwjg.do";

	/**
	 * 
	 * ��ѯ�ճ���Ϊ�����
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward rcxwjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ��ȡ�ճ���Ϊ�������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//------------------���ø߼���ѯĬ��ֵ-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		//�㽭����ְҵѧԺ
		if("12869".equals(Base.xxdm) && model != null && model.getXh() != null){
			searchModel.setInput_mhcx(model.getXh());
			searchModel.setSearch_tj_xq(null);
			searchModel.setSearch_tj_xn(null);
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwh_rcxwjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwjgManage");
	}
	/**
	 * 
	 * @����:�ճ���Ϊ����ֻ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-13 ����03:29:34
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
	public ActionForward getXwdlfList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ��ȡ�ճ���Ϊ����ֽ������
			List<HashMap<String, String>> resultList = service.getXwdlfList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		RcxwdmwhService rcxwdmwhService = new RcxwdmwhService();
		List<HashMap<String,String>> xwdlList = rcxwdmwhService.getRcxwdlList();
		//------------------���ø߼���ѯĬ��ֵ-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("xwdlList", xwdlList);
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwh_tjcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXwdlfList");
	}

	/**
	 * 
	 * �����ճ���Ϊ���
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-����XH:{xh},XWDLDMARR:{xwdldmArr},XWLBDMARR:{xwlbdmArr},FZARRAY:{fzArray},FSSJARR:{fssjArr},XN:{xn},XQ:{xq}")
	public ActionForward addXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			// boolean isExist=service.isExistByXwxxwh(model,SAVE);
			boolean isExist = false;
			if (!isExist) {
				// ����ճ���Ϊ���
				model.setJlr(user.getUserName());
				Hashtable files = model.getMultipartRequestHandler().getFileElements();
				String[] xwlbdmArr=model.getXwlbdmArr();
				String warnMessage="";
				for(int i=0;i<xwlbdmArr.length;i++){
					//������
					FormFile file = (FormFile) files.get("lbfj"+i);
					if(null!=file&&file.getFileSize() > 1024*1024*5){
						if(i!=0){
							warnMessage+="��";	
						}
						warnMessage+=file.getFileName();
					}
				}
				if(""!=warnMessage){
					String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,warnMessage);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				super.resetToken(request);
				boolean result = service.saveXwjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {

				response.getWriter().print(
						getJsonMessage(MessageKey.XSZZ_KNSJG_RESULT_REPEAT));
				return null;
			}
		}

		String path = "rcsw_rcxwwh_rcxwjggl.do?method=addXwjg";
		request.setAttribute("path", path);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		//��Ϊ���༯��
		request.setAttribute("xwdlList", service.getXwdlList(request));
		//��Ϊ��𼯺�
		request.setAttribute("xwlbList",
				new ArrayList<HashMap<String, String>>());
		//��ǰѧ��
		model.setXn(Base.currXn);
		//��ǰѧ��
		model.setXq(Base.currXq);
		//��ǰ�ճ���Ϊ��¼ʱ��
		model.setRcxwjlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("nowTime", GetTime.getTimeByFormat("yyyy-mm-dd"));
		this.saveToken(request);
		return mapping.findForward("addXwjg");
	}

	/**
	 * 
	 * �޸��ճ���Ϊ���
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-�޸�ID:{id},XN:{xn},XQ:{xq},RCXWLBDLDM:{rcxwlbdldm},RCXWLBDM:{rcxwlbdm},FZ:{fz},FSSJ:{fssj}")
	public ActionForward updateXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			// boolean isExist=service.isExistByXwxxwh(model,SAVE);
			boolean isExist = false;
			if (!isExist) {
				// �޸��ճ���Ϊ���
				Hashtable files = model.getMultipartRequestHandler().getFileElements();
				FormFile file = (FormFile) files.get("lbfj");
				if(null!=file&&file.getFileSize() > 1024*1024*5){
					String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,file.getFileName());
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				boolean result = service.updateXwjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {

				response.getWriter().print(
						getJsonMessage(MessageKey.XSZZ_KNSJG_RESULT_REPEAT));
				return null;
			}
		}
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		//��Ϊ���༯��
		request.setAttribute("xwdlList", service.getXwdlList(request));
		RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
		List<HashMap<String, String>> xwlbList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> xwlbxxList = rcxwxxwhService.getXwlbxx(request, model.getRcxwlbdm());
		request.setAttribute("xwlbxx", xwlbxxList!=null&&xwlbxxList.size()>0?xwlbxxList.get(0):null);
		//��ȡ��Ϊ��𼯺�
		xwlbList = service.getXwlbList(request.getParameter("rcxwlbdldm"),
				request);
		request.setAttribute("xwlbList", xwlbList);

		RcxwjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updateXwjg");
	}

	/**
	 * 
	 * ɾ����Ϊ��¼
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����11:42:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-ɾ��VALUES:{values}")
	public ActionForward delXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwjgService service = new RcxwjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//ɾ����Ϊ����е�����
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;

			if (result) {
				RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
				//ɾ����Ϊά���еĹ�������
				rcxwxxwhService.delRcxwwhFromRcxwjg(values.split(","));
			}

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
	 * �鿴�����ճ���Ϊ�����Ϣ
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����01:43:48
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
	public ActionForward viewXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�ճ���Ϊ��������
			HashMap<String,String> cspzMap = service.getCspz();
			request.setAttribute("zq",cspzMap.get("zq"));
			//��ѯ������Ϊ��Ϣ���
			request.setAttribute("rs", StringUtils.formatData(service.getOneXwjgList(model.getId())));
			//��ʷ��Ϊ��¼
			request.setAttribute("rsArrList", service.getMoreXwjgList(model,cspzMap));
			//ѧ��������Ϣ
			request.setAttribute("jbxxList", jbxxList);
			if(Base.xxdm.equals("12867")){
				return mapping.findForward("viewXwjgZjly");
			}else{
				return mapping.findForward("viewXwjg");
			}
			
			
		} else {
			return updateXwjg(mapping, form, request, response);
		}
		
	}
	/**
	 * 
	 * @����:��Ϊ�������ϸ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-14 ����07:02:08
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
	public ActionForward viewXwdljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
	
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�ճ���Ϊ��������
			HashMap<String,String> cspzMap = service.getCspz();
			request.setAttribute("zq",cspzMap.get("zq"));
			//��ʷ��Ϊ��¼
			request.setAttribute("rsArrList", service.getMoreXwjgList(model,cspzMap));
			//ѧ��������Ϣ
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewXwdljg");

		
	}
	
	/**
	 * �Զ��嵼������
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����01:43:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();

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
		if(Base.xxdm.equals("10355")){
			File newFile = new File(file.getParent(),"ȫ��ѧ������ѧ���ۺ���������.xls");
			FileUtils.copyFile(file,newFile);
			file.deleteOnExit();
			FileUtil.outputExcel(response, newFile);
		}else{
			FileUtil.outputExcel(response, file);
		}
		return null;
	}
	
	//�й���Ժ�������Ի�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData_10355(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();

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
	 * @����: �ж���Ϣ�Ƿ��ظ�
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-24 ����05:44:20
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
	public ActionForward rcxwxxSfcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xwlbStr = request.getParameter("xwlbStr");
		String fssjStr = request.getParameter("fssjStr");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		//��Ϊ���༯��
		RcxwjgService service = new RcxwjgService();
		String message = service.getRcxwxxSfcf(request,xh,xn,xq,xwlbStr,fssjStr);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @����:�´�����Ʒ��ʵ������������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-12-1 ����02:08:47
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
	public ActionForward xsPxsjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwh_rcxwjggl.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.xsPxsjDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	
	/**���ظ���*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm myForm = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		
		RcxwjgForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(model.getFjmc(),"utf-8")); 
				FileUtil.outputFile(response, file);
			}
		}
		
		return null;
	}
	
	/**ɾ������*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-ɾ������FJLJ:{fjlj}")
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm myForm = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		
		RcxwjgForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				file.delete();
			}
			model.setFjlj("");
			service.runUpdate(model);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ҽ��ר���ʲ����ֵ���
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-20 ����05:12:41
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
	public ActionForward rcxwsjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwh_rcxwjggl.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.rcxwsjDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @����:�ճ���Ϊ��ͳ�ƣ����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-13 ����10:53:43
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
	public ActionForward rcxwtjbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwh_rcxwjggl.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.rcxwtjbDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rcxwdlfDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.rcxwdlfDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	/**
	 * @����:�ൺ����ѧԺ���Ի���˼��Ʒ�³ɼ����ܵ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��30�� ����5:30:46
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
	public ActionForward sxpdcjhzDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getSxpdcjhzList(model, user);//��ѯ�����м�¼������ҳ
		
		File file = service.getSxpdcjhzFile(resultList);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ��������֪ͨ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-14 ����10:38:14
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
	public ActionForward getDykptzdOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RcxwjgForm model = (RcxwjgForm) form;
		/*��ȡurl���������������id*/
		String id = request.getParameter("id");
		/*��ȡ�ļ���Ϣ*/
		File wordFile = getWordForDykptzd(id);
		/*����ļ�*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ��������֪ͨ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-14 ����10:42:15
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
	public ActionForward getDykptzdZip (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		/*��ȡurl��������Value*/
		String value = request.getParameter("value");
		/*�ж�value�Ƿ�Ϊ��*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForDykptzd(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	/**
	 * @����: ��������֪ͨ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-14 ����10:40:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForDykptzd (String id) throws Exception{
		
		RcxwjgService service = new RcxwjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		/*����һ����file*/
		File file = null;
		
		/*������ѡqjsqid��ȡ�����Ϣ*/
		HashMap<String,String> kptzfInfo = service.getKptzsForId(id);
		/*�������Ϣ*/
		data.put("kptzfInfo", kptzfInfo);
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","dykptzs_12869.xml", FreeMarkerUtil.getFileName(kptzfInfo.get("xh")+"-"+kptzfInfo.get("xm")));
		return file;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-2 ����06:55:56
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
	public ActionForward getJlspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*��ȡurl���������ճ���Ϊ���id*/
		String id = request.getParameter("id");
		/*��ȡ�ļ���Ϣ*/
		File wordFile = getWordForJlspb(id);
		/*����ļ�*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-11-2 ����06:57:09
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
	public ActionForward getJlspbZip (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*��ȡurl��������Value*/
		String value = request.getParameter("value");
		/*�ж�value�Ƿ�Ϊ��*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForJlspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: �����������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-2 ����06:58:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForJlspb (String id) throws Exception{
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*����һ����file*/
		File file = null;
		
		/*������ѡid��ȡѧ��Υ����Ϣ*/
		RcxwjgService rcxwjgService = new RcxwjgService();
		HashMap<String, String> rs = rcxwjgService.getKptzsForId(id);
		data.put("rs", rs);
		
		/*����ѧ��������Ϣ*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(rs.get("xh"));
		data.putAll(xsxxMap);
		
		/*ȡѧ�ź�����*/
		String xh = xsxxMap.get("xh");
		String xm = xsxxMap.get("xm");
		
		/*������������,���磺2017��11��*/
		data.put("csny",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));
		/*��ѧ����������,���磺2017��11��02��*/
		data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.day));
		/*����ʱ��������,���磺2017��11��02��*/
		data.put("fssj",DateTranCnDate.fomartDateToCn(rs.get("fssj"),FomartDateType.day));
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","jlspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xm));
		return file;
	}
}

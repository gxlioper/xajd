/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:28:43 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk;

import java.io.File;
import java.net.URLEncoder;
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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh.XlwtlxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����01:28:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlwjyjkAction extends SuperAction {

	private XlwjyjkService service = new XlwjyjkService();
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "xljk_xlwjyjgl_xlwjyjkgl.do";
	
	public static final String url = "xljk_xlwjyjgl_xlwjyjkgl.do";
	
	/**
	 * @���ԣ� BBID ������id
	 */
	private static final String BBID = "xlzx_xlwy"; 
	
	/**
	 * ��������ά��service
	 */
	private XlwtlxwhService xlwtlxwhService = new XlwtlxwhService();
	/**
	 * @���� ����ʼ��ѧ����Ϣ�������б�
	 */
	public XlwjyjkAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(BBID);
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward cx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model = (XlwjyjkForm) form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model = (XlwjyjkForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		//��������
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//��ע�ȼ�
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_xlwjyjkglwh.do?method=xz", "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xz");
	}
	
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xzAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model = (XlwjyjkForm) form;
		boolean isSuccess = service.runInsert(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model = (XlwjyjkForm) form;
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(service.getModel(model.getXh())));
		//��������
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//��ע�ȼ�
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_xlwjyjkglwh.do?method=xg", "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xg");
	}
	
	/**
	 * 
	 * @����:�޸ı���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model = (XlwjyjkForm) form;
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward tjAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model = (XlwjyjkForm) form;
		int isSuccess = service.batchSubmit(model);
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_SUBMIT_SUCCESS_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_SUBMIT_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����03:52:58
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
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xhs = request.getParameter("xhs"); //��ɾ����xhs
		
		if(xhs == null)
			xhs = "";
		
		int isSuccess = service.runDelete(xhs.split(","));
		
		String message = isSuccess >= 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����09:11:20
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjyjkForm model  = (XlwjyjkForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			HashMap<String , String> modelData = service.xlwjyjkxsxx(model.getXh());
			request.setAttribute("modelData", StringUtils.formatData(modelData));
		}

		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_xlwjyjkglwh.do?method=ck", "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("ck");
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-30 ����04:59:15
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
		XlwjyjkForm model = (XlwjyjkForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
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
	
}

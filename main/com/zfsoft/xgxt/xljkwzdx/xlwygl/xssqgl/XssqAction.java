/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����10:02:16 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

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
import xgxt.utils.date.DateUtils;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ȩ����
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����10:02:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XssqAction extends SuperAction {
	
	XssqService service = new XssqService();
	
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private BdpzService bdpzService = new BdpzService();
	
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
	public static final String PATH = "xljk_xlwygl_xssqgl.do";
	
	public static final String url = "xljk_xlwygl_xssqgl.do";
	
	/**
	 * @���ԣ� CDGL_BBID ������id
	 */
	private static final String BBID = "xlzx_xlwy"; 
	
	/**
	 * @���� ����ʼ��ѧ����Ϣ�������б�
	 */
	public XssqAction() {
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
		XssqForm model = (XssqForm) form;
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
		XssqForm model = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		String lx = request.getParameter("lx");
		model.setLx(lx);
		String to = "";
		if(StringUtils.isEqual(lx, "1")){
			to = "xz_tsxs";
			model.setSfxypssb("1");
			request.setAttribute("isnot", new OptionUtil().getOptions(OptionUtil.ISNOT));
		}else{
			model.setRzksrq(DateUtils.getCurrDate());
			to = "xz_bjwy";
		}
		
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xssqglwh.do?method=xz&lx="+model.getLx() , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(to);
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
		XssqForm model = (XssqForm) form;
		boolean isSuccess = false;
		JSONObject message = null;
		
		if(service.checkExist(model.getXh(), model.getLx())){
			message = getJsonMessageByKey(MessageKey.XLZXWZDX_XSSQ_EXIST);
		}else{
			isSuccess = service.runInsert(model);
			message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		}
		
		response.getWriter().print(message);
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
		
		String pks = request.getParameter("pks"); //��ɾ����sqids
		
		if(pks == null)
			pks = "";
		
		int isSuccess = service.del(pks.split(","));
		
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
		XssqForm model  = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		HashMap<String , String> modelData = service.getModelData(model.getXh() , model.getLx());
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		String to = "";
		if(StringUtils.isEqual(model.getLx(), "1")){
			to = "ck_tsxs";
		}else{
			to = "ck_bjwy";
		}
		request.setAttribute("modelData", StringUtils.formatData(modelData)); 
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xssqglwh.do?method=ck&lx="+model.getLx() , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(to);
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
		XssqForm model = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		XssqForm modelData = service.getModel(model.getXh(), model.getLx());
		if(modelData != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(modelData));
		}
		String to = "";
		if(StringUtils.isEqual(model.getLx(), "1")){
			to = "xg_tsxs";
			request.setAttribute("isnot", new OptionUtil().getOptions(OptionUtil.ISNOT));
		}else{
			to = "xg_bjwy";
		}
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xssqglwh.do?method=xg&lx="+model.getLx() , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(to);
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
		XssqForm model = (XssqForm) form;
		boolean isSuccess = false;
		JSONObject message = null;
		isSuccess = service.updateModel(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
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
		XssqForm model = (XssqForm) form;
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

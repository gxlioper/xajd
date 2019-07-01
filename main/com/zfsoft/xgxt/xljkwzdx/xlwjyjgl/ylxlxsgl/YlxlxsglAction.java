/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:28:43 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;

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
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk.XlwjyjkService;
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

public class YlxlxsglAction extends SuperAction {

	private YlxlxsglService service = new YlxlxsglService();
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * Ԥ����service
	 */
	private XlwjyjkService yjkServic = new XlwjyjkService();
	
	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "xljk_xlwjyjgl_ylxlxsgl.do";
	
	public static final String url = "xljk_xlwjyjgl_ylxlxsgl.do";
	
	public static final String PATH_MTLR = "xljk_xlwjyjgl_mtlrglgl.do";

	/**
	 * @���� ����������ӿ�
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
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
	public YlxlxsglAction() {
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
		User user = getUser(request);
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����:��̸ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward mtlrcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH_MTLR);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mtlrcx");
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
		YlxlxsglForm model = (YlxlxsglForm) form;
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
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		model.setMtzgh(user.getUserName());
		model.setMtzghmc(user.getRealName());
		//��������
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//��ע�ȼ�
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_ylxlxsglwh.do?method=xz", "gbk"));
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
		YlxlxsglForm model = (YlxlxsglForm) form;
		
		
		if(service.checkExist(model.getXh())){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XLZXWZDX_XSSQ_EXIST));
			return null;
		}
		
		boolean isSuccess = service.runInsert(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:��̸¼��ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward mtlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		BeanUtils.copyProperties(model, service.getModel(model.getXh()));
		
		//��������
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//��ע�ȼ�
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlr", "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("mtlr");
	}
	
	/**
	 * 
	 * @����:��̸¼��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward mtlrAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		model.setMtzgh(user.getUserName());
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
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
	public ActionForward mtlrck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model  = (YlxlxsglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			HashMap<String , String> yjkxx =  yjkServic.xlwjyjkxsxx(model.getXh());
			HashMap<String , String> ylxlxsxx =  service.ylxlxsxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			request.setAttribute("yjkxx", yjkxx);
			request.setAttribute("ylxlxsxx", ylxlxsxx);
		}
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlrck" , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("mtlrck");
	}
	
	
	/**
	 * 
	 * @����:�ύҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xhs = request.getParameter("xhs"); //���ύ��xhs
		if(xhs == null)
			xhs = "";
		
		//ѧ��
		request.setAttribute("xhList", xhs);
		request.setAttribute("rkrs", xhs.split(",").length);
		//��������
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//��ע�ȼ�
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_wgwtwh.do?method=tj", "gbk"));
		return mapping.findForward("tj");
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
		YlxlxsglForm model = (YlxlxsglForm) form;
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

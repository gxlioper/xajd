/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����10:39:16 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl.XxsbService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl.ZbrcService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����10:39:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbjgAction extends SuperAction {

	private XxsbjgService service = new XxsbjgService();
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "xljk_xlwygl_xxsbjggl.do";
	
	public static final String url = "xljk_xlwygl_xxsbjggl.do";
	
	/**
	 * �ܱ��ճ�service
	 */
	private ZbrcService zbrcService = new ZbrcService();
	
	
	/**
	 * @���ԣ� BBID ������id
	 */
	private static final String BBID = "xlzx_xlwy"; 

	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	public XxsbjgAction(){
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
			String msg = "��ģ���������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
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
		XxsbjgForm model = (XxsbjgForm) form;
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
		XxsbjgForm model  = (XxsbjgForm) form;
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbjgid());
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(sbxxdata.get("xh"));
		
		//�ϱ�����
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
		}
		request.setAttribute("sbxxdata", xgxt.utils.String.StringUtils.formatData(sbxxdata));
		request.setAttribute("path", PATH);
		request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		HashMap<String , String> sbxx = new HashMap<String, String>();
		//�ϱ�����
		String sblx = model.getSblx();
		
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		if(StringUtils.isNotBlank(model.getSblx())){
			List<HashMap<String , String>> zbrcLx = zbrcService.getZbrcListByLx(model.getSblx());
			request.setAttribute("zbrcLxList", zbrcLx);
		}
		sbxx.put("sblx", sblx);
		if(StringUtils.equals("0", sblx)){
			sbxx.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxx.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxx.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
		}
		request.setAttribute("sbxx", xgxt.utils.String.StringUtils.formatData(sbxx));
		if(!"2".equals(sblx)) {
			request.setAttribute("xnList", service.getXnList());
			request.setAttribute("xqList", Base.getXqList());
		}else {
			request.setAttribute("xnList", Base.getXnndList2());
			request.setAttribute("xqList", Base.getXqList());
		}
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xxsbjgglwh.do?method=sb&sblx="+sblx, "gbk"));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("sb");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
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
	public ActionForward saveAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		JSONObject message = new JSONObject();

		String xh = model.getXh();
		String zc = model.getSbzbid();
		String sblx = model.getSblx();
		
		if("0".equals(sblx) || "1".equals(sblx)){
			boolean check = service.checkExist(xh , sblx , zc);
			if(check){
				message = getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL);
				message.put("code", "-2");
				response.getWriter().print(message);
				return null;
			}
		}
		
		
		model.setSbsj(DateUtils.getCurrTime());
		model.setSjly("0");
		model.setSbjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		if(!"2".equals(model.getSblx())) {
			XxsbService Xservice = new XxsbService();
			HashMap<String , String> xnxqData = Xservice.getXnXqmc(model.getSbzbid());
			model.setXn(xnxqData.get("xn"));
			model.setXq(xnxqData.get("xq"));
		}
		boolean isSuccess = service.runInsert(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		message.put("code", isSuccess ? "1" : "0");
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
		
		int isSuccess = service.runDelete(pks.split(","));
		
		String message = isSuccess >= 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
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
		XxsbjgForm model = (XxsbjgForm) form;
		
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbjgid());
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(sbxxdata.get("xh"));
		
		//�ϱ�����
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
		}
		request.setAttribute("sbxxdata", xgxt.utils.String.StringUtils.formatData(sbxxdata));
		request.setAttribute("path", PATH);
		request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xg");
	}
	
	//�޸�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		JSONObject message = new JSONObject();
		boolean isSuccess = service.runUpdate(model);
		
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		message.put("code", isSuccess ? "1" : "0");
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
		XxsbjgForm model = (XxsbjgForm) form;
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
	
	@SystemAuth(url = url)
	public ActionForward getZcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XxsbjgForm model = (XxsbjgForm) form;
		JSONArray json = JSONArray.fromObject(service.getZcList(model.getXn(), model.getXq(),model.getSblx())); 
		response.getWriter().print(json);
		
		return null;	
		
	}
	
	@SystemAuth(url = url)
	public ActionForward getQzrq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		JSONArray json = JSONArray.fromObject(service.getQzrq(model.getXn(), model.getXq(), model.getSbzbid())); 
		response.getWriter().print(json);
		
		return null;	
	}
	
	
}

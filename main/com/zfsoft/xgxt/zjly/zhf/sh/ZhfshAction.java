/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-22 ����10:38:13 
 */  
package com.zfsoft.xgxt.zjly.zhf.sh;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfForm;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �㽭�����ۺϷ����(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-22 ����10:38:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfshAction extends SuperAction<ZhfForm, ZhfshService>{
	private ZhfshService service = new ZhfshService();
	private ZhfService sqService = new ZhfService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String ZJLY_ZHF = "zjly_zhf";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZJLY_ZHF);
	}
	
	private static final String url = "xg_zjly_zhfsh.do";
	
	/** 
	 * @����:��ȡ��ѯ�б�������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����10:00:19
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
	public ActionForward getZhfshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		String lb = request.getParameter("lb");
		model.setLb(lb);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			//���ɸ߼���ѯ����
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
		request.setAttribute("searchTj", searchModel);
		List<HashMap<String, String>> list = service.getXmmkList();
		request.setAttribute("xmmkList", list);
		if ("sh".equals(lb)) {
			request.setAttribute("path", url);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("getZhfshList");
		}else if ("dc".equals(lb)) {
			request.setAttribute("path", "xg_zjly_mkxxdc.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("getMkxxdc");
		}else {
			request.setAttribute("path", "xg_zjly_zhfxg.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("ZhfshListforxg");
		}
		
	}
	
	/** 
	 * @����:����ѧ�ŵõ���ҳ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����10:14:32
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
	public ActionForward getSdByXh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		if(null != model.getXh()){
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jbxxList", jbxxList);
			List<HashMap<String, String>> ysdList = service.getJfxmYsdByXh(model.getXh());//��ȡ���󶨼�¼
			List<HashMap<String, String>> wsdList = service.getJfxmWsdByXh(model.getXh());//��ȡδ�󶨼�¼
			List<HashMap<String, String>> thList = service.getJfxmThByXh(model.getXh());//��ȡδ�󶨼�¼
			request.setAttribute("ysdList", ysdList);
			request.setAttribute("wsdList", wsdList);
			request.setAttribute("thList", thList);
			List<HashMap<String, String>> xmmkList = sqService.getXmmkList();
			request.setAttribute("xmmkList", xmmkList);
			request.setAttribute("xh", model.getXh());
			request.setAttribute("shzt", model.getShzt());
		}
		return mapping.findForward("shZhfsq");
	}
	
	/** 
	 * @����:������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����01:40:50
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
	public ActionForward saveZhfForSd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		User user = getUser(request);
		model.setShr(user.getUserName());
		model.setShsj(GetTime.getTimeByFormat(DATE_FORMAT));
		String ids = request.getParameter("ids");
		String updateIds = request.getParameter("updateIds");
		boolean result = true;
		if (!StringUtil.isNull(ids)) {
			String[] idss = ids.split(",");
		    int i = service.runDelete(idss);
		    result = i>0;
		}
		if(!StringUtil.isNull(updateIds)){
			String[] idsss = updateIds.split(",");
			result = service.saveSdById(idsss, model.getShr(), model.getShsj());
		}
		if(model.getCysjs()!= null && model.getSxsms() != null && model.getXmmkdms() != null && model.getJfxmdms() != null){
			result = service.saveSd(model);
		}	
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/** 
	 * @����:�����˻�(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-10 ����01:44:33
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
	public ActionForward saveBackJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		User user = getUser(request);
		model.setShr(user.getUserName());
		model.setShsj(GetTime.getTimeByFormat(DATE_FORMAT));
		String backIds = request.getParameter("backIds");
		boolean result = true;
		if(!StringUtil.isNull(backIds)){
			String[] idsss = backIds.split(",");
			result = service.saveBack(idsss, model.getShr(), model.getShsj(),model.getThyj());
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:����������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����02:59:51
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
	public ActionForward savePlSd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		User user = getUser(request);
		model.setShr(user.getUserName());
		model.setShsj(GetTime.getTimeByFormat(DATE_FORMAT));
		boolean result = service.savePlSd(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null; 		
	}
	
	/** 
	 * @����:�鿴(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-24 ����10:10:10
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
	public ActionForward viewZhfsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		if(null != model.getXh()){
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jbxxList", jbxxList);
			List<HashMap<String, String>> ysdList = service.getJfxmYsdByXh(model.getXh());//��ȡ���󶨼�¼
			List<HashMap<String, String>> wsdList = service.getJfxmWsdByXh(model.getXh());//��ȡδ�󶨼�¼
			List<HashMap<String, String>> thList = service.getJfxmThByXh(model.getXh());//��ȡδ�󶨼�¼
			request.setAttribute("ysdList", ysdList);
			request.setAttribute("wsdList", wsdList);
			request.setAttribute("thList", thList);
		}
		return mapping.findForward("viewZhfsh");
	}
	
	/**
	 * @����:�޸�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-8 ����03:46:19
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
	public ActionForward viewZhfxg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		if(null != model.getXh()){
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jbxxList", jbxxList);
			List<HashMap<String, String>> ysdList = service.getJfxmYsdByXh(model.getXh());//��ȡ���󶨼�¼
			request.setAttribute("ysdList", ysdList);
		}
		return mapping.findForward("viewZhfxg");
	}
	
	
	
	
	
	
	
	/** 
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-24 ����10:10:40
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
		ZhfForm model = (ZhfForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		File file = service.getZhfFile(model,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/** 
	 * @����:������������ӡѧ���ۺϷֻ��ܱ�word��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-20 ����11:50:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public ActionForward downloadZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfForm model = (ZhfForm)form;
		String[] xhs= request.getParameter("value").split(",");
		User user = getUser(request);
		model.setFilepath(servlet.getServletContext().getRealPath(
		"/temp/zhfhz/"+user.getUserName()+"/")+"/");//���word·����
		model.setDefaultimagepath(servlet.getServletContext().getRealPath("/images/type_pic.png"));
		if(xhs.length == 1){
			model.setXh(xhs[0]);
			File file = service.createWord(model);
			FileUtil.outputWord(response, file);
		}else{
			model.setXhArr(xhs);
			File file = service.createZipWord(model);
			FileUtil.outputZip(response, file);
		}
		return null;
	}
/**
 * 
 * ɾ��
 */
	public ActionForward zhfDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		User user  = getUser(request);
		if (!StringUtil.isNull(values)) {
			//���ɾ����־
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			service.addlog(time,user,values);
			int num = service.runDelete(values.split(","));
			String message;
			boolean result = num > 0;
			if (result) {
				message = MessageUtil.getText(MessageKey.SYS_DEL_NUM, num);
			}else {
				message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				service.dellog(values);
			}
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
				
	
	
	
	
	
	
	
}

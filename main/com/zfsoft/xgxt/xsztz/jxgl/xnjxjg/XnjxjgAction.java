/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-28 ����06:11:51 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxjg;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsh.XnjxshService;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-28 ����06:11:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxjgAction extends SuperAction<XnjxjgForm,XnjxjgService >{
	private static final String url = "sztz_jxgl_xnjxjg.do";
	private final String TZXMSQ ="tzxmsq";
	XnjxjgService service = new XnjxjgService();
	
	/** 
	 * @����:�������б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-29 ����08:54:36
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
	public ActionForward xnjxjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxjgForm model = (XnjxjgForm) form;
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
//		String[] sqshkg = service.getSqShKg();//����
//		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_jxgl_xnjxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xnjxjgList");
	}
	
	
	/** 
	 * @����:���ӽ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-29 ����08:55:20
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
	public ActionForward xnjxjgAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm model = (XnjxjgForm) form;
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
		request.setAttribute("currxn", Base.currXn);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "jxgl_xnjxjg.do?method=xnjxjgAdd";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("xnjxjgAdd");
	}
	
	
//	
//	@SystemAuth(url = url)
//	public ActionForward getjxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//	    XnjxsqForm model = (XnjxsqForm) form;
//		List<HashMap<String, String>> jxxxList = new XmsbService().getXmjxList(model.getXmdm());
//		JSONArray dataList = JSONArray.fromObject(jxxxList);
//		response.getWriter().print(dataList);
//		return null;
//	} 
//	
	
	/** 
	 * @����:�����޸ĺ����ӽ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-29 ����09:29:29
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
	public ActionForward savejg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm model = (XnjxjgForm) form;
		boolean result = false;
//		String message=null;
//	    User user = getUser(request);
//	    XsXmSqForm form1 = new XsXmSqForm();
//		form1.setXh(model.getXh());
 		if(model.getType().equals("save")){
 			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
 			model.setSfqq("0");
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			String oldxmdm = request.getParameter("oldxmdm");
			String oldjgid = request.getParameter("oldjgid");
			if(!model.getXmdm().equals(oldxmdm) && !model.equals(oldjgid)){
				XnjxjgForm delForm = new XnjxjgForm();
				delForm.setXmdm(oldxmdm);
				delForm.setJgid(oldjgid);
				delForm.setXh(model.getXh());
				delForm.setXq(model.getXq());
				delForm.setXn(model.getXn());
				service.runDelforjg(delForm);
			}
			model.setSfqq("0");
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/** 
	 * @����:�������޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-29 ����01:47:53
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
	public ActionForward editjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm myForm = (XnjxjgForm) form;
		XsXmJgService xsXmJgService = new XsXmJgService();
		XsXmJgForm xsXmJgForm = xsXmJgService.getModel(myForm.getJgid());
		if(null!=xsXmJgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(xsXmJgForm));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xsXmJgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(xsXmJgForm.getXmdm());
		request.setAttribute("rs", xmxx);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(xsXmJgForm.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		String path = "jxgl_xnjxjg.do?method=editjg";
		request.setAttribute("path", path);
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("oldxmdm", myForm.getXmdm());
		request.setAttribute("oldjgid", myForm.getJgid());
		return mapping.findForward("editjg");
	}
	
	
	/** 
	 * @����:ɾ��������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-29 ����10:07:35
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
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    XnjxjgForm model = (XnjxjgForm) form;
			String xhs[] = model.getXhss();
			String xqs[] = model.getXqs();
			String xns[] = model.getXns();
			String xmdms[] = model.getXmdms();
			int num = 0;
			for(int i =0;i<xhs.length;i++){
				model.setXh(xhs[i]);
				model.setXq(xqs[i]);
				model.setXn(xns[i]);
				model.setXmdm(xmdms[i]);
				boolean rs = service.runDelforjg(model);
				if(rs){
					num++;
				}
			}
			boolean result = num>0;		
			String message = result ? MessageUtil.getText(
						MessageKey.SYS_DEL_NUM, num) : MessageUtil
						.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
			return null;
	}

	/** 
	 * @����:��������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-1 ����09:00:20
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XnjxjgForm model = (XnjxjgForm) form;

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
		XnjxjgForm myForm = (XnjxjgForm) form;
		XsXmJgForm xsXmJgForm = new XsXmJgService().getModel(myForm.getJgid());
		if(null!=xsXmJgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(xsXmJgForm));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xsXmJgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(xsXmJgForm.getXmdm());		
		request.setAttribute("rs", xmxx);
		XnjxshService xnjxshService = new XnjxshService();
		String jxmc = xnjxshService.getJxmc(myForm.getYlzd1());
		String jxfs = xnjxshService.getJxfs(myForm.getYlzd1());
		String jcxf = xmxx.get("jcxf");
		String zf = String.valueOf((Integer.parseInt(jxfs)+Integer.parseInt(jcxf)));
		request.setAttribute("zf", zf);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(xsXmJgForm.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		request.setAttribute("jxmc", jxmc);
		return mapping.findForward("viewJx");

	}
	
	@SystemAuth(url = url)
	public ActionForward getXmSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxjgForm model = (XnjxjgForm) form;
		List<HashMap<String,String>> xmsqInfoList = service.getYiShen(model.getXh(),model.getXmdm());
	    request.setAttribute("xmsqInfoList", xmsqInfoList);
		return mapping.findForward("xmselect");
	}
}

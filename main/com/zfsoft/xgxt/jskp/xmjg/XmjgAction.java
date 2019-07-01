package com.zfsoft.xgxt.jskp.xmjg;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import common.newp.StringUtil;

public class XmjgAction extends SuperAction<XmjgForm,XmjgService> {
	private XmjgService service = new XmjgService();
	private final String url = "pjpy_jskp_lxjg.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����02:03:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getLxjgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����02:11:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward searchForJgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm model = (XmjgForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����02:42:03
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
	public ActionForward addLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		//ȡ��ϵ��ʽ
		String lxfs = "";
		if("stu".equals(user.getUserType())){
			lxfs = new LxsqService().getFzrxxStu(user.getUserName()).get("sjhm");
		}else{
			lxfs = new LxsqService().getFzrxxTea(user.getUserName()).get("lxdh");
		}
		request.setAttribute("lxfs", lxfs);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����03:18:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����A
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward updateLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm model = (XmjgForm)form;
		XmjgForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		StringBuilder xhforTextarea = new StringBuilder();
	    request.setAttribute("xhs", xhforTextarea.toString());
	    request.setAttribute("bmmc", new LxsqService().getBmmc(lxsq.getZdbm()).get("bmmc"));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����03:44:28
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
	public ActionForward saveForLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm lxjg = (XmjgForm)form;
		boolean rs = true;
		try {
			rs = service.saveForLxjg(lxjg);
		} catch (SystemException e) {
			// TODO �Զ����� catch ��
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����03:49:46
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//��֤�Ƿ�
			if(!service.checkXmIsNotUserd(ids)){
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.DTJS_ZZGXZC_DZBWH_USED,"����Ŀ")));
				return null;
			}
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
	 * 
	 * @����:�鿴������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-14 ����04:02:02
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
	public ActionForward ckLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm model = (XmjgForm)form;
		XmjgForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = new LxsqService().getXmcyryXhs(lxsq.getXmid());
		request.setAttribute("xhList", xhList);
	    request.setAttribute("bmmc", new LxsqService().getBmmc(lxsq.getZdbm()).get("bmmc"));
	    request.setAttribute("xmlbmc",new DmwhService().getModel(lxsq.getXmlb()).getXmlbmc());
		return mapping.findForward("cklxjg");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-18 ����10:25:23
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
		
		XmjgForm model = (XmjgForm) form;

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
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-20 ����05:25:42
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
	public ActionForward jbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XmjgForm model = (XmjgForm) form;
		XmjgForm lxjg = service.getModel(model.getXmid());
		BeanUtils.copyProperties(model, lxjg);
		return mapping.findForward("jbsz");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-20 ����05:31:11
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
	public ActionForward saveJbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XmjgForm model = (XmjgForm) form;
		boolean rs = service.runUpdate(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}

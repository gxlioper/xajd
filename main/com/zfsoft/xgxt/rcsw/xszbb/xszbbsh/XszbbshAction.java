/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2013-12-18 ����08:51:40 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ֤���������ģ��
 * @�๦������: TODO(֤������-�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����08:51:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbshAction extends SuperAction {
	//����֤��������֤�����쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String RCSWXSZBB = "rcswxszbb";
	
	private static final String url = "rcsw_xszbb_bbsh.do";
	
	/**
	 * 
	 * @����:TODO(֤���������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-18 ����10:19:43
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
	public ActionForward xszbbshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbshForm model = (XszbbshForm) form;
		XszbbshService service = new XszbbshService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡ֤�������������
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_xszbb_bbsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszbbshManage");
	}
	
	/**
	 * 
	 * @����:TODO(֤�����쵥�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-18 ����11:02:38
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
	@SystemLog(description="�����ճ�����-֤������-֤���������-���BBSQID:{bbsqid}")
	public ActionForward xszbbDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbshForm model = (XszbbshForm) form;
		XszbbshService service = TransactionControlProxy.newProxy(new XszbbshService());
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//���������Ϣ
			HashMap<String,String> infoList = service.getXszbbshInfo(model);
			request.setAttribute("rs", StringUtils.formatData(infoList));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//���浥�����
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
	
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xszbbDgsh");
	}
	
	/**
	 * 
	 * ����ѧ���������
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-6 ����06:57:12
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
	@SystemLog(description="�����ճ�����-֤������-֤���������-����BBSQID:{bbsqid}")
	public ActionForward cancelXszbbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbshForm model = (XszbbshForm) form;
		String bbsqid = request.getParameter("bbsqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setBbsqid(bbsqid);
		XszbbshService service = new XszbbshService();
		//�����ճ���Ϊ��ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward viewXszbbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbshForm model = (XszbbshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		XszbbshService service = new XszbbshService();
		//��ѯ������Ϊ��Ϣ���
		request.setAttribute("rs", StringUtils.formatData(service.getXszbbshInfo(model)));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewXszbbsh");
		
	}
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:27:45
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
		XszbbshForm model = (XszbbshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		XszbbshService service = new XszbbshService();
		model.setShzt(shlx);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getPageList(model,user);//��ѯ�����м�¼������ҳ
		
		
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
	 * 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-25 ����10:22:16
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
	@SystemLog(description="�����ճ�����-֤������-֤���������-�������ID:{id}")
	public ActionForward zjbbPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbshForm model = (XszbbshForm) form;
		XszbbshService service = TransactionControlProxy.newProxy(new XszbbshService());
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("zjbbPlsh");
		
	}
	
	
}

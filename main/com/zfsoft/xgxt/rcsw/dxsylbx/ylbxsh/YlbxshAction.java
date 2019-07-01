/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2013-12-18 ����08:51:40 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;

import java.io.File;
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
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤����-�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����08:51:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxshAction extends SuperAction {
	//����ѧ��֤������ѧ��֤���쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String DXSYLBX = "dxsylbx";
	
	private static final String url = "rcsw_dxsylbx_ylbxsh.do";
	
	/**
	 * 
	 * @����:TODO(ҽ�Ʊ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-9 ����02:20:24
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
	public ActionForward ylbxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = new YlbxshService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡѧ��֤�����������
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxshManage");
	}
	
	/**
	 * 
	 * @����:TODO(ҽ�Ʊ��յ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-9 ����04:04:49
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ������-���YLSQID:{ylsqid}")
	public ActionForward ylbxDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = TransactionControlProxy.newProxy(new YlbxshService());
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//���������Ϣ
			HashMap<String,String> infoList = service.getYlbxshInfo(model);
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
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", service.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", service.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		return mapping.findForward("ylbxDgsh");
		
	}
	
	/**
	 * 
	 * @����ҽ�Ʊ��յ������
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ������-����YLSQID:{ylsqid}")
	public ActionForward cancelYlbxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		String ylsqid = request.getParameter("ylsqid");
		model.setYlsqid(ylsqid);
		YlbxshService service = new YlbxshService();
		//�����ճ���Ϊ��ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward viewYlbxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		YlbxshService service = new YlbxshService();
		
		//��ѯ������Ϊ��Ϣ���
		request.setAttribute("rs", StringUtils.formatData(service.getYlbxshInfo(model)));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		request.setAttribute("model", model);
		request.setAttribute("shzt", model.getShzt());
		
		YlbxshService ylbxshService = new YlbxshService();
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("viewYlbxsh");
		
	}
	
	/**
	 * 
	 * @����:TODO(�鿴ҽ�Ʊ��յ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����04:13:21
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
	public ActionForward viewCbzk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String xm = request.getParameter("xm");
		if("null".equals(xm)){
			xm = "";
		}
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = new YlbxshService();
		
		model=service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(model));
		
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", service.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", service.getCbzkdmcsList(cbzkdms));
		}
		
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		request.setAttribute("xm",xm);
		return mapping.findForward("viewCbzk");
		
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
		YlbxshForm model = (YlbxshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		YlbxshService service = new YlbxshService();
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
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����09:30:50
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ������-�������ID:{id}")
	public ActionForward ylbxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = TransactionControlProxy.newProxy(new YlbxshService());
		
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("ylbxPlsh");
	}
	
	
}

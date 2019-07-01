/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����08:41:14 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwsh;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.ssyd.ydsh.YdshService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ��� 
 * @���ߣ� Dlq [���ţ�995]
 * @ʱ�䣺 2013-8-5 ����08:41:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwshAction extends SuperAction {
	//�����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String RCSWRCXW = "rcswrcxw";
	
	private static final String url = "rcsw_rcxwwh_rcxwsh.do";
	
	/**
	 * 
	 * �ճ���Ϊ��˲�ѯ
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-6 ����06:56:22
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
	public ActionForward rcxwshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		RcxwshService service = new RcxwshService();
		
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ��ȡ�ճ���Ϊ�������
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_rcxwwh_rcxwsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("rcxwshManage");
	}
	
	
	/**
	 * 
	 * �ճ���Ϊ�������
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-6 ����06:56:51
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
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-���ID:{id}")
	public ActionForward rcxwDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		RcxwshService service = new RcxwshService();
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//���������Ϣ
			HashMap<String,String> infoList = service.getSplcInfo(model);
			
			// ========== ��˷�ֵ���� begin ============
			ShlcDao shlcDao = new ShlcDao();
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getId(), "desc");
			String shfz = infoList.get("sqfz");
			if(shyjList.size() > 0){
				HashMap<String, String> shyj = shyjList.get(0);
				shfz = shyj.get("zd2");
			}
			infoList.put("shfz", shfz);
			// ========== ��˷�ֵ���� end ============

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
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		//�ճ���Ϊ�������
		model.setRcxwlbdldm(request.getParameter("rcxwlbdldm"));
		model.setShid(request.getParameter("shid"));
		//model.setShid(shid);
		request.setAttribute("model", StringUtils.formatData(model));
		RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
		List<HashMap<String,String>> xwlbxx = rcxwxxwhService.getXwlbxx(request,model.getRcxwlbdm());
		request.setAttribute("xwlbxx", xwlbxx);
		
		return mapping.findForward("rcxwDgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward toPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("toPlsh");
	}
	
	/**
	 * 
	 * �����ճ���Ϊ���
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
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-����XXWHID:{xxwhid}")
	public ActionForward cancelRcxwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		String id = request.getParameter("xxwhid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setId(id);
		RcxwshService service = new RcxwshService();
		//User user = getUser(request);
		//�����ճ���Ϊ���
		// ShlcInterface shlc = new CommShlcImpl();
		//boolean isSuccess = service.cancelSh(model, user);
		//boolean isSuccess = shlc.runCancel(user.getUserName(),model.getId(),model.getSplc(),model.getGwid());
		//�����ճ���Ϊ��ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward viewXwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			RcxwxxwhService service = new RcxwxxwhService();
			//��ѯ������Ϊ��Ϣ���
			request.setAttribute("rs", service.getOneXwxxList(model.getId()));
			//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("model", StringUtils.formatData(model));
			request.setAttribute("shzt", model.getShzt());
			return mapping.findForward("viewXwxx");
		} else {
			return rcxwDgsh(mapping, form, request, response);
		}
		
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
		RcxwshForm model = (RcxwshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		RcxwshService service = new RcxwshService();
		model.setShzt(shlx);
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
	
	/**�������**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ���-�������INFO:{info}")
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String shzt = request.getParameter("shzt");
		String shyj = request.getParameter("shyj");
		String info = request.getParameter("info");
		User user = getUser(request);
		RcxwshService service = new RcxwshService();
		boolean isSuccess = service.plsh(shzt, shyj, info, user);
		
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}

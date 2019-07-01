/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:39:45 
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszDao;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx.HcyhklxService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbsq.XszbbsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳˳�����������ģ��
 * @�๦������: TODO(�𳵳˳�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-25 ����01:36:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjjgAction extends SuperAction {
	//�����ճ������л𳵳˳����䳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWXSZBB = "rcswxszbb";
	private static List<HashMap<String, String>> jbxxList = null;

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
	}

	private static final String url = "rcsw_hcyhk_hcccqjjg.do";
	
	/**
	 * 
	 * @����:TODO( ��ѯ�𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����09:00:55
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
	public ActionForward hcccqjjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		if (QUERY.equals(model.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			//��ѯ��ȡ�𳵳˳�����������
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		
		String path = "rcsw_hcyhk_hcccqjjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("hcccqjjgManage");
		
	}

	/**
	 * 
	 * @����:TODO(���ӻ𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����09:52:27
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
	@SystemLog(description="�����ճ�����-���Żݿ�����-�𳵳˳�������-����XH:{xh},XN:{xn},XQ:{xq},CCPDZ:{ccqdz},CCZDZ:{cczdz},TXSJ:{txsj}")
	public ActionForward addHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

	    	boolean isExist = service.isExistByHcccqjjg(model,SAVE);
			if (!isExist) {
				super.resetToken(request);
				// ��ӻ𳵳˳�������
				boolean result = service.saveHcccqjjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_RESULT_REPEAT));
				return null;
			}
		}
		
		 //ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "rcsw_hcyhk_hcccqjjggl.do?method=addHcccqjjg";
		request.setAttribute("path", path);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//��ȡ���л��Żݿ�����
		HcyhklxService hcyhklxService = new HcyhklxService();
		request.setAttribute("hcyhklxList", hcyhklxService.getHcyhklxList());
		request.setAttribute("xxdm", Base.xxdm);
		//�������������õ�Ĭ������õ�ҳ��
		HcyhkJcszForm jcszMrQd = new HcyhkJcszDao().getModel();
		request.setAttribute("ccqdz", jcszMrQd.getCcqdz());
		//��ǰ�ճ���Ϊ��¼ʱ��
		model.setTxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		this.saveToken(request);
		return mapping.findForward("addHcccqjjg");
		
	}

	/**
	 * 
	 * @����:TODO(�޸Ļ𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����09:58:51
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
	@SystemLog(description="�����ճ�����-���Żݿ�����-�𳵳˳�������-�޸�CCQJJGID:{ccqjjgid},XH:{xh},XN:{xn},XQ:{xq},CCPDZ:{ccqdz},CCZDZ:{cczdz},TXSJ:{txsj}")
	public ActionForward updateHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			
				boolean isExist = service.isExistByHcccqjjg(model,UPDATE);
				
				if (!isExist) {
					// ��ӻ𳵳˳�������
					boolean result = service.updateHcccqjjg(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				} else {
					response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_RESULT_REPEAT));
					return null;
				}
				
			
		}
		
		request.setAttribute("jbxxList", jbxxList);
		//��������ά������
		XszbbsqService xszbbsqService = new XszbbsqService();
		request.setAttribute("bblxwhList", xszbbsqService.getBblxwhList());
		
		HcccqjjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		 //ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		//model.setXn(Base.currXn);
		//model.setXq(Base.currXq);
		//��ȡ���л��Żݿ�����
		HcyhklxService hcyhklxService = new HcyhklxService();
		request.setAttribute("hcyhklxList", hcyhklxService.getHcyhklxList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("hcccqjjgInfo", StringUtils.formatData(model));
		return mapping.findForward("updateHcccqjjg");
	}


	
	/**
	 * 
	 * @����:TODO(ɾ���𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����11:23:04
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
	@SystemLog(description="�����ճ�����-���Żݿ�����-�𳵳˳�������-ɾ��VALUES:{values}")
	public ActionForward delHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjjgService service = new HcccqjjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteHcccqjjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(�鿴�����𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����11:35:15
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
	public ActionForward viewHcccqjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//��ѯ������Ϊ��Ϣ���
		request.setAttribute("rsArrList",service.getMoreHcccqjjgList(model));
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewHcccqjjg");
		
	}
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼�����û𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����01:37:23
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
		HcccqjjgForm model = (HcccqjjgForm) form;
		HcccqjjgService service = new HcccqjjgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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

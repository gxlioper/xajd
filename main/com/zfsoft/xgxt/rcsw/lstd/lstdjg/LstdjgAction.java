/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:29:34 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjg;

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
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgService;
import com.zfsoft.xgxt.rcsw.lstd.lstdsq.LstdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:29:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdjgAction extends SuperAction {

	
	//�����ճ���������ɫͨ���������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWXLSTD = "rcswlstd";
	private static List<HashMap<String, String>> jbxxList = null;
	
	LstdjgService service = new LstdjgService();

	private static final String url = "rcsw_lstd_jg.do";
	
	/**
	 * 
	 * @����:�������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:55:06
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
	public ActionForward lstdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LstdjgForm model = (LstdjgForm) form;
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡѧ��֤����������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_lstd_jg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lstdjgManage");
	}

	/**
	 * 
	 * @����:�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����12:01:58
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
	@SystemLog(description = "�����ճ�����-��ɫͨ��-��ɫͨ�����-����XH:{xh},XN:{xn},XQ:{xq},LXDM:{lxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward addLstdsqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdjgForm model = (LstdjgForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ��+ѧ��+ѧ�ڣ�
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				boolean result = service.saveSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_LSTD_LSTDJG_REPEAT));
				return null;
			}
		}

		String path = "rcsw_lstd_jggl.do?method=addLstdsqjg";
		request.setAttribute("path", path);
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		
		
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXLSTD);
		
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//��������ά������
		LstdsqService lstdsqService = new LstdsqService();
		request.setAttribute("lxwhList", lstdsqService.getLxwhList());
		//��ǰ������ʱ��
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("addLstdjg");
	}

	/**
	 * 
	 * @����:�޸Ľ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:21:16
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
	@SystemLog(description = "�����ճ�����-��ɫͨ��-��ɫͨ�����-�޸�JGID:{jgid},XH:{xh},XN:{xn},XQ:{xq},LXDM:{lxdm},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward updateLstdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdjgForm model = (LstdjgForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				// �޸�ѧ��֤������
				boolean result = service.updateSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_LSTD_LSTDJG_REPEAT));
				return null;
			}
		}

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXLSTD);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		//��������ά������
		LstdsqService lstdsqService = new LstdsqService();
		request.setAttribute("lxwhList", lstdsqService.getLxwhList());
		LstdjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updateLstdjg");
	}


	
	/**
	 * 
	 * @����:ɾ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:25:20
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
	@SystemLog(description = "�����ճ�����-��ɫͨ��-��ɫͨ�����-ɾ��VALUES:{values}")
	public ActionForward delLstdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdjgService service = new LstdjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteSqjg(values.split(","));
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
	 * @����:�����鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:28:49
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
	public ActionForward viewOneLstdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdjgForm model = (LstdjgForm) form;
		LstdjgService service = new LstdjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//��ѯ����ѧ��֤������
		request.setAttribute("rs", StringUtils.formatData(service.viewOneLstdjgList(model.getJgid())));

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXLSTD);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewLstdjg");
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:29:51
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
		LstdjgForm model = (LstdjgForm) form;
		LstdjgService service = new LstdjgService();

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

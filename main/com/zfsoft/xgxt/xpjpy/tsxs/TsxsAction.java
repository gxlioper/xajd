/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����10:12:36 
 */  
package com.zfsoft.xgxt.xpjpy.tsxs;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����������ģ��
 * @�๦������: ����ѧ��ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-2 ����10:12:36 
 * @�汾�� V1.0O
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsAction extends SuperAction {
	private String messageKey;
	
	private static final String url = "xpj_tsxs.do?method=viewTsxsTj&mklx=pjpy";
	
	/**
	 * 
	 * @����:����ѧ��ͳ���б���ѧ��ѧ�����ͽ���ͳ����ʾ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����11:24:57
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward viewTsxsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		String mklx = request.getParameter("mklx");
		
		if (QUERY.equals(model.getType())){
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		CsszService csszService = new CsszService();
		String currXnXqmc = csszService.getModel().getZqmc();// ��ǰѧ��ѧ��
		request.setAttribute("currXnXqmc", currXnXqmc);
		
		List<HashMap<String, String>> xnList = service.getXnList();//��ѯ������ѧ��
		request.setAttribute("xnList", xnList);
				
		List<HashMap<String, String>> xqList = Base.getXqList();//��ѯ������ѧ��
		request.setAttribute("xqList", xqList);
		
		String path = "xpj_tsxs.do?method=viewTsxsTj&mklx="+mklx;
		request.setAttribute("path", path);
		request.setAttribute("mklx", model.getMklx());
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewTsxsTj");
	}
	/**
	 * 
	 * @����:����ѧ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-16 ����02:03:15
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward tsxsXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		String xn = request.getParameter("xn");
		request.setAttribute("xn", xn);
		String xq = request.getParameter("xq");
		request.setAttribute("xqmc", service.getXqmc(xq));
		String lxdm = request.getParameter("lxdm");
		request.setAttribute("lxmc", service.getLxmc(lxdm));

		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_tsxs.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsXx");
	}

	/**
	 * 
	 * @����:����ѧ��δ����б�
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-7-11 ����09:04:42
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
	public ActionForward tsxsDtjXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getTsxsDtjPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:����ѧ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����02:44:28
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward tsxsZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsModel myForm = (TsxsModel) form;
		TsxsService service = new TsxsService();
		if (QUERY.equals(myForm.getType())) {
			//��������ѧ������
			List<HashMap<String, String>> tslxList = service.getTslxList();
			JSONArray dataList = JSONArray.fromObject(tslxList);
			response.getWriter().print(dataList);
			return null;
		}
		String mklx = request.getParameter("mklx");
		String currXnXqmc = null;
		String currXn = Base.currXn;
		String currXq = Base.currXq;
		if("pjpy".equals(mklx)){
			CsszService csszService = new CsszService();
			currXnXqmc = csszService.getModel().getZqmc();// ��������
			CsszModel csszModel = csszService.getModel();
			currXn = csszModel.getXn();// ��ǰѧ��
			currXq = csszModel.getXq();// ����ѧ��
		}else{
			 currXnXqmc = Base.currXn+" "+Base.getDqxqmc();
		}
		request.setAttribute("currXn", currXn);
		request.setAttribute("currXq", currXq);
		request.setAttribute("mklx", mklx);
		request.setAttribute("currXnXqmc", currXnXqmc);
		this.saveToken(request);
		return mapping.findForward("tsxsZj");
	}
	
	/**
	 * 
	 * @����:����ѧ������ 
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����03:59:59
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description="������������-����ѧ��-����ѧ��ά��-����-MKLX:{mklx},LXDM:{lxdm},TSXSXH:{tsxsxh}")
	public ActionForward tsxsCreate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		User user = getUser(request);
		
		String mklx = request.getParameter("mklx");
		if("pjpy".equals(mklx)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			String currXn = csszModel.getXn();// ��ǰѧ��
			String currXq = csszModel.getXq();// ����ѧ��
			model.setXn(currXn);
			model.setXq(currXq);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
		}
		boolean result = service.saveTsxs(model, user);
		messageKey = result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}

	/**
	 * ɾ��������Ա��Ϣ
	 */
	@SystemLog(description="������������-����ѧ��-����ѧ��ά��-ɾ��-VALUES:{values}")
	public ActionForward delTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsxsService service = new TsxsService();
		
		String values = request.getParameter("values");
		boolean result = service.delTsxs(values);
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	/**
	 * 
	 * @����:����ѧ��ѧ�����ͣ�ɾ������ѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-16 ����01:58:28
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description="������������-����ѧ��-����ѧ��ά��-ɾ��-JSON:{json}")
	public ActionForward tsxsScForLx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsxsService service = new TsxsService();
		
		String json = request.getParameter("json");
		boolean result = service.tsxsScForLx(json);
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @����:����ѧ��ά��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-6 ����03:08:53
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
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();

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
	
	/**
	 * @throws IOException 
	 * @throws SQLException  
	 * @����:������������ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-26 ����02:35:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward plzjTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String lxdm = request.getParameter("lxdm");
		User user = getUser(request);
		String values = request.getParameter("values");
		TsxsService service = new TsxsService();
		boolean result = service.PlbcTsxs(values, xn, xq, lxdm, user.getUserName());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}

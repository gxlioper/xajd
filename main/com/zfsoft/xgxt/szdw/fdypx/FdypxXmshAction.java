/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:13:59 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyrz.FdyrzsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ���
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-25 ����4:14:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdypxXmshAction extends SuperAction {
	
	private static final String url = "szdw_fdypxxmsh.do?method=fdypxxmList";

	/**
	 * @����:����Ա��ѵ���
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-26 ����2:34:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward fdypxxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmsh.do?method=fdypxxmList");
		return mapping.findForward("list");
	}
	/**
	 * @����:����Ա��ѵ��Ŀ���
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-26 ����2:09:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward fdypxxmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmsqService sqservice = new FdypxXmsqService();
		FdypxXmshService service = new FdypxXmshService();
		//��ȡ����Ա��ѵ������Ϣ
		FdypxXmsqForm model = sqservice.getModel(myForm);
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//������˽��
			myForm.setSplc(model.getSplc());
			boolean result = service.fdypxsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		FdyrzsqService fdyservice = new FdyrzsqService();
		//��ȡ����Ա��Ϣ
		HashMap<String,String> map = fdyservice.getFdyjbxx(model.getSqr());
		request.setAttribute("rs", StringUtils.formatData(map));
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("myForm", myForm);
		if("ck".equalsIgnoreCase(myForm.getType())){
			return mapping.findForward("fdypxck");
		}
		return mapping.findForward("fdypxsh");
	}
	/**
	 * @����:����Ա��ѵ���
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-26 ����2:43:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = "szdw_fdypxxmsh.do?method=fdypxjgList")
	public ActionForward fdypxjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmsh.do?method=fdypxjgList");
		return mapping.findForward("jglist");
	}
	/**
	 * @����:����Ա��ѵ�������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-26 ����4:09:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = "szdw_fdypxxmsh.do?method=fdypxjgList")
	public ActionForward fdypxjgExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdypxXmshForm myForm=(FdypxXmshForm)form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		FdypxXmshService service = new FdypxXmshService();
		List<HashMap<String,String>> resultList = service.getAllList(myForm);
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-10 ����04:22:49
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
	public ActionForward cancelFdyxmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdypxXmshForm model = (FdypxXmshForm) form;
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		FdypxXmshService service = new FdypxXmshService();
		//�����ճ���Ϊ��ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-29 ����04:05:16
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
	public ActionForward fdypxxmPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmshForm model = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		User user = getUser(request);
		
		if("save".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model,user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}

		return mapping.findForward("fdypxxmPlsh");
	}
	
	/**
	 * 
	 * @����:�ѻ�ý���鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-24 ����05:18:02
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
	@SystemAuth(url = "szdw_fdypxxmsh.do?method=fdypxjgList")
	public ActionForward yhdpxxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		List<HashMap<String, String>> sqjgList = service.getSqjg(myForm.getSqid());
		request.setAttribute("sqjgList", sqjgList);
		return mapping.findForward("yhdpxxm");
	}
	
}

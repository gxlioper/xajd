package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz.CsszService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class XsgzzbshAction extends SuperAction {
	
	private static final String url = "rcsw_xsgzzb_xsgzzbsh.do";
	
	/**
	 * @����:�ܱ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xsgzzbshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbshService service = new XsgzzbshService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if(!"xy".equalsIgnoreCase(userStatus) && !"xx".equalsIgnoreCase(userStatus)){
			String msg = "��ģ�������"+Base.YXPZXY_KEY+"��ѧУ�û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ�������
			List<HashMap<String,String>> resultList = null;
			if("bj".equals(gzzblx)){
				resultList = service.getPageListBj(model,user);
			}else{
				resultList = service.getPageList(model,user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_xsgzzb_xsgzzbsh.do";
		if("bj".equals(gzzblx)){
			path = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
		}
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbshManage");
		}
		return mapping.findForward("xsgzzbshManage");
	}
	
	/**
	 * @����:�ܱ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xsgzzbDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbshService service = new XsgzzbshService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if("bj".equals(gzzblx)){
			model=service.getModelBj(model);
		}else{
			model=service.getModel(model);
		}
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbDgsh");
		}
		if(Base.xxdm.equalsIgnoreCase("13815")){
			CsszService cssz = new CsszService();
			List<HashMap<String, String>> yscfjlist = cssz.getYscfjList(model.getSqid());
			request.setAttribute("yscfjlist", yscfjlist);
		}
		return mapping.findForward("xsgzzbDgsh");
	}
	
	/**
	 * @����:����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cancelXsgzzbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		User user = getUser(request);
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		XsgzzbshService service = new XsgzzbshService();
		boolean isSuccess = service.newCancelSh(model, user);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:������˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xsgzzbPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbshService service = new XsgzzbshService();
		
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbPlsh");
		}
		return mapping.findForward("xsgzzbPlsh");
	}
	
	/**
	 * @����:�Զ��嵼��
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
		XsgzzbshForm model = (XsgzzbshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		XsgzzbshService service = new XsgzzbshService();
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
	
}

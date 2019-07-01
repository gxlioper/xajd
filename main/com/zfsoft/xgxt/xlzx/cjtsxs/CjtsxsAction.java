/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.xlzx.cjtsxs;

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
import xgxt.action.base.BaseDAO;
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
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.syddk.SydkSqshService;

/**
 * @className	�� CjtsxsAction
 * @description	����������ѧ��(��������������)
 * @author 		��������1282��
 * @date		�� 2017-11-7 ����03:02:14
 * @version 	V1.0 
 */

public class CjtsxsAction extends SuperAction<CjtsxsForm, CjtsxsService>{
	private CjtsxsService service = new CjtsxsService();
	private static final String CJTSXS = "cjtsxs";
	private static final String url = "xlzx_cjtsxs_cjtsxsgl.do";
	
	/**
	 * @description	�� ��������ѧ���б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����03:16:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getCjtsxsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjtsxsForm model = (CjtsxsForm) form;
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
		request.setAttribute("searchTj", searchModel);		
		String path = "xlzx_cjtsxs_cjtsxsgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getCjtsxsList");
	}
	
	/**
	 * @description	�� ���Ӵ�������ѧ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����03:16:08
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addCjTsxs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjtsxsForm model = (CjtsxsForm)form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			SydkSqshService sqshService = new SydkSqshService();
			sqshService.setXsjbxx(xsjbxx, model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJTSXS);
		request.setAttribute("jbxxList", jbxxList);
		User user = getUser(request);
		String lrsj = GetTime.getTimeByFormat("yyyy-mm-dd");
		String realName = user.getRealName();
		model.setLrr(realName);
		model.setLrsj(lrsj);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(Base.currXq));		
		String path = "xljk_cjtsxs.do?method=addCjTsxs";
		request.setAttribute("path", path);
		return mapping.findForward("addCjTsxs");
	}
	
	/**
	 * @description	���޸Ĵ�������ѧ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����03:25:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editCjTsxs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjtsxsForm model = (CjtsxsForm)form;
		CjtsxsForm editModel = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(editModel));
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(model.getXq()));
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
				.getXh());
		SydkSqshService sqshService = new SydkSqshService();
		sqshService.setXsjbxx(xsjbxx, model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJTSXS);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("editCjtsxs");
	} 
	
	/**
	 * @description	�� ������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����03:22:54
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������ѯ-������������ѧ��-������������ѧ������-����XH:{xh},XN:{xn},XQ:{xq}")
	public ActionForward saveJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjtsxsForm model = (CjtsxsForm)form;
		boolean result;
		if("save".equalsIgnoreCase(model.getType())){
			if(service.isExist(model)){				
				result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));			
			}else{
				String messageKey = MessageKey.XG_DTJS_TSDZB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
		}else{
			if(service.isExist(model)){
				result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				String messageKey = MessageKey.XG_DTJS_TSDZB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}			
		}
		return null;
	}
	
	/**
	 * @description	�� �鿴��������ѧ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����07:32:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewCjTsxs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjtsxsForm model = (CjtsxsForm) form;
		CjtsxsForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(model.getXq()));
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
				.getXh());
		SydkSqshService sqshService = new SydkSqshService();
		sqshService.setXsjbxx(xsjbxx, model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJTSXS);
		request.setAttribute("jbxxList", jbxxList);
		//request.setAttribute("rs", model);
		return mapping.findForward("viewCjTsxs");
	}
	
	/**
	 * @description	��ɾ��������������ѧ�� 
	 * @author 		�� ������1282��
	 * @date 		��2017-11-9 ����03:51:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������ѯ-������������ѧ��-������������ѧ������-ɾ��XH:{xh}")
	public ActionForward delCjtsxs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
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
	
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjtsxsForm model = (CjtsxsForm) form;
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

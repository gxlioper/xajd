/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xfsq.DektxfsqForm;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.upload.FormFile;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.hdgl.hdblsh.HdblshService;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	�� HdbljgAction
 * @description	�� ����action(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-23 ����10:55:47
 * @version 	V1.0 
 */

public class HdbljgAction extends SuperAction<HdbljgForm, HdbljgService> {
	private ZdydrService zdydrService = new ZdydrService();
	private static final String url = "hdgl_hdbl_hdbljg.do";
	//ѧ��������Ϣ����
	private static List<HashMap<String, String>> jbxxList = null;
	
	public static String HDBL = "hdbl";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	
	/**
	 * @description	�� ����list
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����10:57:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdbljgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;		
		HdbljgService service = new HdbljgService();
		
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
		request.setAttribute("searchTj", searchModel);		
		String path = "hdgl_hdbl_hdbljg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdbljgList");
	} 
	
	/**
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����02:12:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		HdbljgService hdbljgService = new HdbljgService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		HdbljgForm view_model = hdbljgService.getModelForJg(model);
		//view_model = (HdbljgForm) StringUtils.formatBean(view_model);
		if(null != view_model){
			BeanUtils.copyProperties(model, StringUtils.formatData(view_model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "hdgl_hdbljg.do?method=addHdbljg";
		request.setAttribute("xh",model.getXh());
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		HdblsqshService hdblsqshService = new HdblsqshService();
		
		//��ȡ��ǰѧ��
		request.setAttribute("currXq", hdblsqshService.getCurrXq());
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		/*������б�*/
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);

		/*������ְ��*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);
		
		/*�ܱ�ǩ�б�*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);
		
		/*�γ������б�*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//��ѡ�γ��б�
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*������ǩ*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);
		
		return mapping.findForward("addHdbljg");
	}
	
	/**
	 * @description	��  �޸�
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����03:43:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		HdbljgService service = new HdbljgService();
		HdbljgForm view_model = service.getModelForJg(model);
		//view_model = (HdbljgForm) StringUtils.formatBean(view_model);
		if(null != view_model){
			BeanUtils.copyProperties(model, StringUtils.formatData(view_model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		String path = "hdgl_hdbljg.do?method=updateHdbljg&jgid=" + model.getJgid();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//������б�
		HdblsqshService hdblsqshService = new HdblsqshService();
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);
		
		/*���ǩ�б�*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = service.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);
		
		/*���������б�*/
		List<HashMap<String,String>> jzlxList = service.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//��ѡ�γ��б�
		List<HashMap<String,String>> zxckclxList = service.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);
		
		/*������ǩ*/
		List<HashMap<String,String>> abilityLabelList = service.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*������ְ��*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);
		
		/*���ػ��ʽ*/
		request.setAttribute("hdxs", model.getHdxs());
		
		return mapping.findForward("updateHdbljg");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����03:07:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		HdbljgService hdbljgService = TransactionControlProxy.newProxy(new HdbljgService());
		boolean result = false;
	    //User user = getUser(request);
 		if(model.getType().equals("save")){
 			String jgid = UniqID.getInstance().getUniqIDHash();
 			model.setJgid(jgid);
			result = hdbljgService.runInsert(model);
		}else if(model.getType().equals("update")){
			result = hdbljgService.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� ɾ��
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����05:23:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			HdbljgService service = TransactionControlProxy.newProxy(new HdbljgService());
			boolean result = service.runDeleteJg(ids);
			int num = ids.length;
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
	 * @description	�� �鿴���
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����05:43:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		request.setAttribute("jbxxList", jbxxList);
		HdbljgService service = new HdbljgService();
		HdbljgForm view_model = service.getModelForJg(model);
		//view_model = (HdbljgForm) StringUtils.formatBean(view_model);
		if(null != view_model){
			BeanUtils.copyProperties(model, view_model);
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		return mapping.findForward("viewHdbljg");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-24 ����10:30:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HdbljgService service = new HdbljgService();
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ
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
	public ActionForward hdbljgImport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");
		//��ѯģ����Ϣ
		HashMap<String,String> drmbxx = zdydrService.getDrmbxx(drmkdm);
		//��ѯ���������Ϣ
		List<HashMap<String,String>>  drgzxxList =  zdydrService.getDrgzxxList(drmkdm);

		request.setAttribute("drmbxx", drmbxx);
		request.setAttribute("drgzxxList", drgzxxList);
		return mapping.findForward("hdbljgImport");
	}

	/**
	 *  ���Ի�����ģ������.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 14:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdbljgService service = new HdbljgService();
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");

		//��Ӧͷ����
		response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(drmkdm+".xls","UTF-8"));
		service.createWwb(response.getOutputStream(),drmkdm);
		return null;
	}

	/**
	 *  ���浼��.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-10 20:13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward saveImport(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

		HdbljgService service = new HdbljgService();
		//��ȡ����ģ�����
		String drmkdm = request.getParameter("drmkdm");

		//�õ�FormFile���󣬶�ȡ�ϴ���Excel�ļ�
		HdbljgForm hdbljgForm = (HdbljgForm)form;
		FormFile formFile = hdbljgForm.getImportFile();

		//���ر�������ģ�����󡢵���ɹ���Ϣ������ʧ����Ϣ
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/");
		HashMap<String,Object> resultMap = service.saveImport(formFile.getInputStream(),path,drmkdm);

		JSONObject resultJson = JSONObject.fromObject(resultMap);
		response.getWriter().print(resultJson);
		return null;
	}
}

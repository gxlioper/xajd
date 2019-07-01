package com.zfsoft.xgxt.gygl.gypynew.gypysq;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.util.ResourceUtils;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.gypynew.cssz.CsszService;
import common.newp.StringUtil;

public class GypySqAction extends SuperAction<GypySqForm, GypySqService> {
	private GypySqService service = new GypySqService();
	private final String url = "gygl_gypynew_gypysq.do";
	/**
	 * 
	 * @����: ��Ԣ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����11:24:44
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
	public ActionForward getGypySqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("splc",new CsszService().getSplc().get("splc"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gypysqcx");
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����11:40:58
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
	public ActionForward searchForSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:��Ԣ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����04:33:46
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
	public ActionForward addSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sqsj = "";
		String nowYearMonth = GetTime.getTimeByFormat("yyyy-mm");
		String nowDay = GetTime.getTimeByFormat("dd");
		int day = Integer.parseInt(nowDay);
		if(day > 15){
			sqsj = nowYearMonth+"-16";
		}else{
			sqsj = nowYearMonth+"-01";
		}
		request.setAttribute("sqsj", sqsj);
		return mapping.findForward("addsq");
	}
	
	/**
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:22:04
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
	public ActionForward editSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		GypySqForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("qsxx",service.getQshxx(model));
		return mapping.findForward("editsq");
	}
	/**
	 * 
	 * @����:��ʼ��¥������ţ����Һ�ȫ�ֱ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-26 ����02:48:01
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
	public ActionForward initLdChQsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String gyglyQx = (String) request.getSession().getAttribute("gyglyQx");
		User user = getUser(request);
		List<HashMap<String,String>> lddmList = service.getLddmList(user, gyglyQx);
		List<HashMap<String,String>> chList = service.getChList(user, gyglyQx);
		List<HashMap<String,String>> qshList = service.getQshList(user, gyglyQx);
		JSONObject json = new JSONObject();
		json.put("ld", lddmList);
		json.put("ch", chList);
		json.put("qs", qshList);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����: ���湫Ԣ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:28:43
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
	public ActionForward saveSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		User user = getUser(request);
		boolean rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:30:08
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
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
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:31:18
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
		GypySqForm model = (GypySqForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

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
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:48:12
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm) form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		GypySqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����03:02:02
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			GypySqForm model = new GypySqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: ����鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-28 ����02:35:41
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
	public ActionForward viewSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		GypySqForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatDataView(model) );
		}
		request.setAttribute("qsxx",service.getQshxx(model));
		return mapping.findForward("viewsq");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-4 ����03:25:30
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
	public ActionForward getQsgxsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypySqForm myForm = (GypySqForm) form;
		File wordFile = getGxsqWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getGxsqWord(GypySqForm myForm,HttpServletRequest request) throws Exception{
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		myForm = service.getModel(myForm.getSqid());
		HashMap<String, String> qsjbxx = service.getXjqsSqJbxx(myForm);
		String sqly = HtmlUtil.xmlZy(qsjbxx.get("sqly"));
		qsjbxx.put("wjcs", service.getWjNum(myForm));
		qsjbxx.put("bjmc", service.getQsssbj(myForm));
		qsjbxx.put("sqly", sqly);
		data.putAll(qsjbxx);
		data.put("wjxx", service.getWjxx(myForm));
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"gygl/gxsqb.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"gygl","gxsqb.xml",FreeMarkerUtil.getFileName(qsjbxx.get("ldmc")+qsjbxx.get("qsh")+"_�Ǽ����������"));
		}catch (Exception e) {
			file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"gygl","gxsqb.xml", FreeMarkerUtil.getFileName(qsjbxx.get("ldmc")+qsjbxx.get("qsh")+"_�Ǽ����������"));
		}
		return file;
	}
	
	public ActionForward getQsgxsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypySqForm myForm = (GypySqForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqid(values[i]);
				File file = getGxsqWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}

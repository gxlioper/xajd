package xsgzgl.gygl.gyccgl.ccdj;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import common.newp.StringUtil;

public class CcdjAction extends SuperAction<CcdjForm, CcdjService> {
	private CcdjService service = new CcdjService();
	private static final String url = "gygl_gyccgl_ccdj.do";
	private static String QJTZTSY = "���Ƚ�����Ʒ�Ʋ�����ά����";
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����11:21:05
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
	public ActionForward searchRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcdjForm myForm = (CcdjForm)form;
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			myForm.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
			myForm.setPath(url);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
	   }
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		/**
		 * ������Ʒ����ά�����Ƿ�ά�����ݣ�������ҳ�����Ȩ��
		 */
		List<HashMap<String, String>> wpList = service.getWpList();
		if(wpList == null || wpList.size() == 0){
			request.setAttribute("message", QJTZTSY);//����
			return mapping.findForward("error");
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: �Ʋ��Ǽ�ɾ��(ɾ�����ű������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����04:49:02
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
	@SystemLog(description = "�ù�Ԣ��������Ʋ������Ʋ��Ǽ�-ɾ��id:{values}")
	public ActionForward delRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		CcdjService transervice = TransactionControlProxy.newProxy(new CcdjService());
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = transervice.delResult(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, ids.length) : MessageUtil
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
	 * @���ڣ�2017-3-8 ����08:42:27
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
	@SystemLog(description = "���ʹ�Ԣ��������Ʋ������Ʋ��Ǽ�-����")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CcdjForm ccdjform = (CcdjForm)form;
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		ccdjform.setXn(Base.currXn);
		ccdjform.setXq(Base.currXq);
		ccdjform.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		ccdjform.setPath(url);
		ccdjform.setUsername(getUser(request).getUserName());
		request.setAttribute("LddmList",service.getLddmList(ccdjform));
		request.setAttribute("wpList",service.getWpList());
		request.setAttribute("shcdList",service.getShcdList());
		return mapping.findForward("addccdj");
	}

	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����08:42:27
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
	@SystemLog(description = "���ʹ�Ԣ��������Ʋ������Ʋ��Ǽ�-�޸�")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CcdjForm ccdjForm = (CcdjForm)form;
		request.setAttribute("rs",service.getCcdjMap(ccdjForm.getId()));
		request.setAttribute("wpList",service.getViewWpList(ccdjForm));
		request.setAttribute("shcdList",service.getShcdList());
		return mapping.findForward("updateccdj");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����08:42:27
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
	public ActionForward viewccdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		request.setAttribute("rs",service.getCcdjMap(id));
		request.setAttribute("shcdList", service.getViewWpShcdList(id));
		return mapping.findForward("viewccdj");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����08:42:27
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
	@SystemLog(description = "���ʹ�Ԣ��������Ʋ������Ʋ��Ǽ�-������ת")
	public ActionForward dr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("dr");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����08:49:31
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
	@SystemLog(description = "���ʹ�Ԣ��������Ʋ������Ʋ��Ǽ�-����")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CcdjForm ccdjForm = (CcdjForm)form;
		User user = getUser(request);
		ccdjForm.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		ccdjForm.setPath(url);
//		List<HashMap<String, String>> dataList = service.getAllList(ccdjForm, user);
		List<HashMap<String, String>> dataList = service.getAllListForDc(ccdjForm, user);
		List<HashMap<String, String>> wpList = service.getWpList();
		//��ȡ��������
		List<HashMap<String,String>> groupList = service.getGroupLddmList(ccdjForm);
       if(groupList != null && groupList.size() > 1){
    	   	List<File> files = service.getFileArryList(groupList, dataList, wpList, user.getUserName());
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}else{
			String ldmc = (groupList!=null && groupList.size()>0)?groupList.get(0).get("ldmc"):"";
			File file = service.createDcFile(dataList,ldmc , user.getUserName(), wpList);
			 response.setHeader("Content-Disposition", "attachment;filename=\""
		               + new String("gygl_ccdj.xls".getBytes(), "GBK") + "\"");
			response.setContentType("application/vnd.ms-excel");
			FileUtil.outputExcel(response, file);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: �������л������ֵ�仯
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-12 ����09:19:20
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
	public ActionForward changeSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String type = request.getParameter("type");
		String lddm = request.getParameter("lddm");
		List<HashMap<String, String>>  dataList = new ArrayList<HashMap<String,String>>();
		if("ld".equals(type)){
			  dataList = service.getChList(lddm);
		}else if("cc".equals(type)){
			String ch = request.getParameter("ch");
			  dataList = service.getQshList(lddm, ch);
		}
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("dataList", dataList);
		dataMap.put("message", "true");
		dataMap.put("size", dataList.size());
		JSONObject dataMapJson = JSONObject.fromObject(dataMap);
		response.getWriter().print(dataMapJson);
		return null;
	}
	
	/**
	 * @throws Throwable 
	 * 
	 * @����: ���淽��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����11:32:06
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
	@SystemLog(description = "���ʹ�Ԣ��������Ʋ������Ʋ��Ǽ�-�����")
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		CcdjForm ccdjform = (CcdjForm)form;
		String[] wpdms = request.getParameterValues("wpdms");
		String[] shcds = request.getParameterValues("shcds");
		ccdjform.setWpdms(wpdms);
		ccdjform.setShcds(shcds);
		CcdjService bcService = TransactionControlProxy.newProxy(new CcdjService());
		String messageKey = bcService.saveForm(ccdjform) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ���Һ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-9 ����09:59:23
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
	public ActionForward qshChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		CcdjForm ccdjform = (CcdjForm)form;
		//����Map
		HashMap<String, Object> Map = new HashMap<String, Object>();
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
		//�Ƿ���ڣ�0(������)��1(����)
		boolean  isexist = service.checkIsExistNotInCcdjb(ccdjform);
		if(isexist){
			resultList = service.getWpList();
			Map.put("isexist", "0");
		}else{
			resultList = service.getViewWpList(ccdjform);
			Map.put("id", resultList.get(0).get("id"));
			Map.put("isexist", "1");
			CcdjForm model = (CcdjForm) StringUtils.formatData(service.getModel(resultList.get(0).get("id")));
			Map.put("zje", model.getZje());
			Map.put("bz", model.getBz());
		}
		List<HashMap<String, String>> shcdList = service.getShcdList();
		Map.put("dataList", JsonUtil.ListToJSON(resultList));
		Map.put("shcdList", JsonUtil.ListToJSON(shcdList));
		response.getWriter().print(JSONObject.fromObject(Map));
		return null;
	}
	
	/**
	 * 
	 * @����: ���浼��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-13 ����09:44:02
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
	@SystemLog(description = "���ʹ�Ԣ��������Ʋ������Ʋ��Ǽ�-���뱣��")
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		CcdjForm model = (CcdjForm) form;
		FormFile file = model.getDrmb();
		model.setPath(url);
		model.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		model.setUsername(getUser(request).getUserName());
		model.setShcdList(service.getShcdList());
		model.setXqList(Base.getXqList());
		if(file != null){
			model.setFilepath(servlet.getServletContext().getRealPath(
			"/temp/importTemp/")+"/");
			CcdjService drService = TransactionControlProxy.newProxy(new CcdjService());
			HashMap<String, Object> resultMap = null;
			/**
			 * �˴���Ҫ�����쳣���в�׽����������ӳ����쳣��Ϣ��
			 * ԭ���Ǵ��ļ��ı��ύ����ģ��ajax���ύ���������ı��ύ��
			 * ����ͷ��x-requested-with���ԣ�����superAction�ж��޷�����ǰ̨�����
			 * �粻����������Ķ�superAction�쳣��׽
			 */
			try {
				resultMap = drService.saveDrExcelInfo(file.getInputStream(),model);
			} catch (SystemException e) {
				// TODO �Զ����� catch ��
				response.getWriter().print(getJsonMessage(e.getMessage()));
				return null;
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_OPERATE_FAIL));
				return null;
			}
			JSONObject jsonObject = getJsonMessageByKey((String)resultMap.get("message"));
			if("false".equals(resultMap.get("result"))){
				jsonObject.put("gid",resultMap.get("gid"));
			}
		    response.getWriter().print(jsonObject);
		}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DR_KFILE));
		}
		
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����09:16:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("gygl_ccdj.xls".getBytes(), "GBK") + "\"");
		CcdjForm ccdj = (CcdjForm)form;
		ccdj.setUsername(getUser(request).getUserName());
		ccdj.setPath(url);
		ccdj.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		service.createWwb(response.getOutputStream(),ccdj);
		return null;
	}
	
	/**
	 * 
	 * @����:���ش�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-14 ����11:20:10
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("��������.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
}

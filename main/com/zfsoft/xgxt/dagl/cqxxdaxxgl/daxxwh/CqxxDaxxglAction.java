/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-17 ����02:05:59 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ͽ��������ģ��
 * @�๦������: ά��������Ϣ
 * @���ߣ� linguoxia[����:1553]
 * @ʱ�䣺
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CqxxDaxxglAction extends SuperAction<CqxxDaxxglForm, CqxxDaxxglService>{
	private static final String url = "cqxxdaxxgl.do";//���ݿ����
	CqxxDaxxglService service = new CqxxDaxxglService();
	
	/**
	 * 
	 * @����:��ѯ����
	 * @���ڣ�2016-8-25 ����06:44:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getdalist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxglForm model = (CqxxDaxxglForm)form;
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
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getdalist");
	}
	
	/**
	 * @����:ά��
	 * @���ڣ�2016-8-25 ����06:46:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cqxxdaxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxglDao dao = new CqxxDaxxglDao();
		CqxxDaxxglForm model = (CqxxDaxxglForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			model.setFlag("gx");
			List<HashMap<String,String>> resultList = service.getXsPageList(model, user);
			JSONArray dataList  = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		HashMap<String, String> bjxxMap = service.getBjxx(model.getBjdms());
		request.setAttribute("xymc",bjxxMap.get("xymc"));
		request.setAttribute("zymc",bjxxMap.get("zymc"));
		request.setAttribute("nj",bjxxMap.get("nj"));
		request.setAttribute("bjmc",bjxxMap.get("bjmc"));
		String count = dao.getCount(model.getBjdms());
		String ywh = dao.getywhCount(model.getBjdms());
		int wwh = Integer.parseInt(count)-Integer.parseInt(ywh);
		request.setAttribute("ywh",ywh);
		request.setAttribute("wwh",wwh);
		request.setAttribute("bjdms",model.getBjdms());
		request.setAttribute("bjid", model.getBjid());
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cqxxdaxxwh");
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ڣ�2016-8-25 ����06:47:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveData(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CqxxDaxxglForm myForm = (CqxxDaxxglForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		boolean flag;  
        boolean isExist = service.isExistQysj(myForm);
    	if (!isExist) {
        	flag = service.saveDataXs(myForm, "insert");
        }else{
        	flag = service.saveDataXs(myForm, "update");
        }
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:ʵʱ��ȡά������
	 * @���ڣ�2016-8-25 ����06:47:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getwhrs(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CqxxDaxxglForm myForm = (CqxxDaxxglForm) form;
		CqxxDaxxglDao dao = new CqxxDaxxglDao();
		
		String count = dao.getCount(myForm.getBjdm());
		String ywh = dao.getywhCount(myForm.getBjdm());
		String wwh = String.valueOf(Integer.parseInt(count)-Integer.parseInt(ywh));
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("ywh", ywh);
		map.put("wwh", wwh);
		JSONObject json = JSONObject.fromObject(map); 
		
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @����:�鿴�༶ѧ��������Ϣ
	 * @���ڣ�2016-8-25 ����06:48:04
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxglDao dao = new CqxxDaxxglDao();
		CqxxDaxxglForm model = (CqxxDaxxglForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			model.setFlag("gx");
			model.setFlag1("ck");
			List<HashMap<String, String>> resultList = service.getXsPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		HashMap<String, String> bjxxMap = service.getBjxx(model.getBjdms());
		request.setAttribute("xymc",bjxxMap.get("xymc"));
		request.setAttribute("zymc",bjxxMap.get("zymc"));
		request.setAttribute("nj",bjxxMap.get("nj"));
		request.setAttribute("bjmc",bjxxMap.get("bjmc"));
		String count = dao.getCount(model.getBjdms());
		String ywh = dao.getywhCount(model.getBjdms());
		int wwh = Integer.parseInt(count)-Integer.parseInt(ywh);
		request.setAttribute("ywh",ywh);
		request.setAttribute("wwh",wwh);
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("bjdms",model.getBjdms());
		request.setAttribute("bjid", model.getBjid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sxdaview");
	}

	/**
	 * @����:����
	 * @���ڣ�2016-8-25 ����06:48:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportDataWH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxglService service=new CqxxDaxxglService();
		CqxxDaxxglForm model=(CqxxDaxxglForm) form;
		
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

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-26 ����09:41:11 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.qgzx.jfgl.QgzxJfglService;
import xsgzgl.qgzx.zjdx.cjff.CjffForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ڹ���ѧ��𷢷�ͳ�Ʋ�ѯ
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-26 ����09:41:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxAction extends SuperAction<TjcxForm, TjcxService> {
	TjcxService service = new TjcxService();
	/**
	 * 
	 * @����: У�����귢��ͳ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-26 ����11:35:26
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
	public ActionForward getXqbcFfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TjcxForm model = (TjcxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getXqbcFfTj(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		model.setNd(Base.currNd);
		String path = "cjff_tjcx_zjdx_xqbcfftj.do";
		request.setAttribute("path", path);
		request.setAttribute("ndList", Base.getXnndList2());
		
		//��֤�Ƿ����ڹ�����Ա
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xqbcfftj");
	}
	
	/**
	 * 
	 * @����:У�����귢��ͳ�Ƶ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-26 ����11:55:58
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
	public ActionForward exportDataXqbcFfTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxForm model = (TjcxForm) form;

		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getXqbcFfTj(model);
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xqcjfftjdc.xls".getBytes(), "GBK") + "\"");
		service.createWwbXqDc(response.getOutputStream(), model, resultList);

		return null;
	}
	
	/**
	 * 
	 * @����:���˵�λ����ͳ�Ʋ�ѯ��������չ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-26 ����05:10:08
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
	public ActionForward getYrdwFfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TjcxForm model = (TjcxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getYrdwFfTj(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		model.setNd(Base.currNd);
		String path = "cjff_tjcx_zjdx_yrdwcjfftj.do";
		request.setAttribute("path", path);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yrbmList", service.getYrbmList(null));
		//��֤�Ƿ����ڹ�����Ա
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yrdwtj");
	}
	
	/**
	 * 
	 * @����: ���˵�λ�ϼƼ����һ�кϼ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-27 ����05:21:28
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
	public ActionForward getHjSum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		HashMap<String, String> resultList = service.getYrdwFfTjSum(model);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: У��ͳ�����һ�кϼ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-28 ����11:35:12
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
	public ActionForward getXqSum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		HashMap<String, String> resultList = service.getXqbcFfTjSum(model);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:��������������л�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-28 ����02:45:39
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
	public ActionForward bmlbChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		List<HashMap<String, String>> resultList = service.getYrbmList(model.getBmlb());
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:У�����귢��ͳ�Ƶ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-26 ����11:55:58
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
	public ActionForward exportDataYrdwFfTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxForm model = (TjcxForm) form;

		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getYrdwFfTj(model);
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xqcjfftjdc.xls".getBytes(), "GBK") + "\"");
		service.createWwbYrdwDc(response.getOutputStream(), model, resultList);

		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ѧ���ڹ���ϸͳ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-29 ����02:36:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getStudentQgDetailTjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ��ѯ
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getStudentQgDetailTjCx(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] { Base.currNd});
		request.setAttribute("searchTj", searchModel);
		String path = "cjff_tjcx_zjdx_stucjfftj.do";
		request.setAttribute("path", path);
		
		//��֤�Ƿ����ڹ�����Ա
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stucjfftj");
	}
	
	/**
	 * 
	 * @����: 
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-29 ����02:55:48
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
	public ActionForward getStudentQgDetailTjCxSum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HashMap<String, String> resultList = service.getStudentQgDetailTjCxSum(model, user);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:������ϸ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-29 ����05:03:04
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
		
		TjcxForm model = (TjcxForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getStudentQgDetailTjCx(model, user);
		

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
	 * @����: ���˱��귢��ͳ�Ʋ�ѯҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-18 ����11:02:03
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
	public ActionForward getStucjffgrtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		TjcxDAO dao = new TjcxDAO();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ��ѯ
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getStucjffgrtj(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] { Base.currNd});
		//Ĭ�ϸ߼���ѯ����-�·�
		String Yf = dao.getCsszYf();
		searchModel.setSearch_tj_yf(new String[]{Yf});
		
		request.setAttribute("searchTj", searchModel);
		String path = "cjff_tjcx_zjdx_stucjffgrtj.do";
		request.setAttribute("path", path);
		
		//��֤�Ƿ����ڹ�����Ա
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stucjffgrtj");
	}
	
	/**
	 * @����: ���˱��귢��ͳ�Ƹ��ݸ߼���ѯ�������㱨�귢������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-18 ����11:08:04
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
	public ActionForward getStucjffgrtjSum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HashMap<String, String> resultList = service.getStucjffgrtjSum(model, user);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ���˱��귢��ͳ�Ƶ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-18 ����11:23:22
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
	public ActionForward exportDataStucjffgrtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjcxForm model = (TjcxForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//����ҳ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.exportDataStucjffgrtj(model, user);
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
}

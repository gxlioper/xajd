package xsgzgl.qgzx.cxtj;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgDao;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
/**
 * �ڹ���ѧ-��ѯͳ��-���ͳ�Ƶ���
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjAction extends BasicAction{
	
	/**
	 * ��λ��Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			List<HashMap<String,String>> list = service.gwxxCx(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_cxtj_gwxx.do");
		return mapping.findForward("gwxxCx");
	}

	
	/**
	 * ��λ��Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gwdm = request.getParameter("gwdm");
		QgzxCxtjService service = new QgzxCxtjService();
		request.setAttribute("model",new WdgwsqService().getGwxxByGwdm(gwdm));
		request.setAttribute("zgrylist",service.getGwRyList("zg",gwdm));
		request.setAttribute("lzrylist",service.getGwRyList("tg",gwdm));
		return mapping.findForward("gwxxCk");
	}
	
	
	/**
	 * ѧ����λ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgwCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			List<HashMap<String,String>> list = service.xsgwCx(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_cxtj_xsgw.do");
		return mapping.findForward("xsgwCx");
	}

	public ActionForward xsgzjlCx(ActionMapping mapping,
								ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			List<HashMap<String,String>> list = service.xsgzjlCx(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_cxtj.do?method=xsgzjlCx");
		return mapping.findForward("xsgzjlCx");
	}

	public ActionForward xsgzjlMxCx(ActionMapping mapping,
								  ActionForm form, HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			List<HashMap<String,String>> list = service.xsgzjlMxCx(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_cxtj.do?method=xsgzjlMxCx");
		request.setAttribute("xh",myForm.getXh());
		return mapping.findForward("xsgzjlMxCx");
	}

	public ActionForward xsgzjlMxCxExportData(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.xsgzjlMxCx(model,user);

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
	public ActionForward dwgzCx(ActionMapping mapping,
									ActionForm form, HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			List<HashMap<String,String>> list = service.dwgzCx(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_cxtj.do?method=dwgzCx");
		return mapping.findForward("dwgzCx");
	}
	public ActionForward dwgzCxExportData(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.dwgzCx(model,user);

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
	public ActionForward xsgzCxExportData(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.xsgzCx(model,user);

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
	public ActionForward xsgzffCk(ActionMapping mapping,
								ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
			// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		List<HashMap<String, String>> jbxxList = new BdpzService().getJbxxpz("cjff");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzffList",service.xsgzffCx(myForm.getXh()));
		return mapping.findForward("xsgzffCk");
	}
	public ActionForward xsgzCx(ActionMapping mapping,
								ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
		if(QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			List<HashMap<String,String>> list = service.xsgzCx(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_cxtj.do?method=xsgzCx");
		request.setAttribute("xh",myForm.getXh());
		return mapping.findForward("xsgzCx");
	}
	/**
	 * ��λ��Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgwCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		HashMap<String,String> rs = service.xsgwCk(model);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		rForm.setPath("qgzx_cxtj_xsgw.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("xsgwCk");
	}
	
	
	/**
	 * ���ѻ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfhbCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setPath("qgzx_cxtj_jfhb.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cxtj_jfhb.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_yf");
			request.setAttribute("jfhbfs", "yf");
		}else{
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_nd");
			request.setAttribute("jfhbfs", "nd");
		}
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_jfhbb");
		if("no".equalsIgnoreCase(qgzxCsszService.getCssz().get("sfjfhb"))){
			String msg = "��ǰ������������ͨ�����ѻ������Ƴ�𷢷ţ���ģ������ά����";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jfhbCx");
		}
	}

	
	/**
	 * ���ѻ����鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfhbCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		HashMap<String,String> rs = service.jfhbCk(model);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		rForm.setPath("qgzx_cxtj_jfhb.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("jfhbCk");
	}
	
	
	/**
	 * ��𷢷Ų�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjffCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setPath("qgzx_cxtj_cjff.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cxtj_cjff.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_cjffb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_cjff");
		request.setAttribute("xxdm", Base.xxdm);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("cjffCx");
		}
	}

	
	/**
	 * ��𷢷Ų鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjffCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		HashMap<String,String> rs = service.cjffCk(model);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		rForm.setPath("qgzx_cxtj_cjff.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("cjffCk");
	}
	
	
	/**
	 * ���ų�𷢷�ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjffTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		model.setUser(user);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cxtj_cjtj.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//���Ĭ�ϲ���
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("bmcjffTj");
		}
	}
	
	
	/**
	 * ���˳�𷢷�ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjffTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		model.setUser(user);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cxtj_cjtj.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//���Ĭ�ϲ���
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("gwList", service.getGwList());
		if(userType.equalsIgnoreCase("stu")){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("grcjffTj");
		}
	}
/**
 * 
 * @����:��λ��𷢷�ͳ��
 * @���ߣ�CP[���ţ�1352]
 * @���ڣ�2017-8-18 ����09:44:52
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
	public ActionForward gwcjffTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		model.setUser(user);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cxtj_cjtj.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//���Ĭ�ϲ���
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("gwList", service.getGwList());
		if(userType.equalsIgnoreCase("stu")){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwcjffTj");
		}
	}
	/**
	 * ���ų�𷢷Ų�ѯ
	 */
	public ActionForward bmcjffCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		model.setUser(user);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_cxtj_bmcjffcx.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//���Ĭ�ϲ���
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("bmcjffCx");
		}
	}
	
	/**
	 * ��ӡWord�ǼǱ�
	 */
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("id");
		if (StringUtils.isNotNull(value)){
			File wordFile = getWord(value);
			FileUtil.outputWord(response, wordFile);
		}
		return null;
	}
	/**
	 * ��ӡWord�ǼǱ�zip
	 */
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	//���ģ����������word�ļ�
	private File getWord(String id) throws Exception {
		String mbmc = "qgzxxsda.xml";
		Map<String,Object> data = new HashMap<String,Object>();
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = new QgzxCxtjForm();
		model.setPkValue(id);
		// ���ڹ���λ
		HashMap<String,String> xsgwxx = service.getXsgwxx(model);
		data.put("xsgwxx", xsgwxx);
		// ��������
		String sqly = HtmlUtil.xmlZy(xsgwxx.get("sqly"));
		data.put("sqly", sqly);
		String xh = xsgwxx.get("xh");
		// ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		data.put("rs", xsjbxx);
		// �����ڹ���ѧ���
		StringBuffer ywqgzxqkBuffer = new StringBuffer();
		List<HashMap<String, String>> xsgwxxList = service.getXsgwxxList(xh);
		String xsgwxxGwdm = xsgwxx.get("gwdm"); // ���ڹ���λ
		for (int i = 0; i < xsgwxxList.size(); i++) {
			HashMap<String, String> xsgwxxMap = xsgwxxList.get(i);
			if(!xsgwxxGwdm.equals(xsgwxxMap.get("gwdm"))){
				ywqgzxqkBuffer.append(xsgwxxMap.get("yrdwmc")).append(xsgwxxMap.get("gwmc")).append("��");
			}
		}
		String ywqgzxqkmc = ywqgzxqkBuffer.reverse().replace(0, 1, "").reverse().toString();
		data.put("ywqgzxqkmc", ywqgzxqkmc);
		// ����ѧ����ͥ������Ϣ
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xh);
		data.put("jtqk", jtqkmodel);
		PjjgService pjjgService = new PjjgService();
		// ����ѧ�������϶����
		KnsjgService knsjgService = new KnsjgService();
		HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, "", "");
		String rddcmc = knsjg.get("dcmc")==null?"":knsjg.get("dcmc");
		data.put("rddcmc", rddcmc);
		//������ũ��ְҵѧԺ���Ի�
		if("12727".equals(Base.xxdm)){
			mbmc = "qgzxxsda_12727.xml";
			// ��ͥ��Ա
			List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
			pjjgService.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
			data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
		}
		File wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx",mbmc,xh+"-"+xsjbxx.get("xm")+"-"+xsgwxx.get("yrdwmc")+xsgwxx.get("gwmc"));
		return wordFile;
	}
	
	//������ҵ��ѧ�ڹ���ѧ��𷢷��걨����
	public ActionForward getCjffSbbDclist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		QgzxCxtjDAO dao = new QgzxCxtjDAO();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*��ʽ�����š���ȡ��͡��·ݡ���Ϊ�ù��ڼ� start*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
		String[] nd = model.getSearchModel().getSearch_tj_nd();
		String[] yf = model.getSearchModel().getSearch_tj_yf();
		Arrays.sort(yf);
		int ndInt = Integer.parseInt(nd[0]);
		int yfBeginInt = Integer.parseInt(yf[0]) - 1;
		int yfEndInt = Integer.parseInt(yf[yf.length - 1]) - 1;
		Calendar c = Calendar.getInstance();
		c.set(ndInt, yfBeginInt, 1);
		String yfBeginStr = format.format(c.getTime());
		c.set(Calendar.MONTH, yfEndInt);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String yfEndStr = format.format(c.getTime());
		/*��ʽ�����š���ȡ��͡��·ݡ���Ϊ�ù��ڼ� end*/
		User user = getUser(request);
		//��ѯҳ��߼���ѯ����ѡ���Ľ����
		List<HashMap<String,String>> resultList = dao.getCjffSbbDclist(model, user);
		//��ѯ���˵�λ����list,�����ж��Ƿ���ڶ����λ�ĳ�𷢷���Ϣ�����У��������ݣ����zip������û���������word�ļ�
		List<HashMap<String,String>>  fzlist = dao.getFzlist(model, user);
		//�ж��Ƿ���ڶ�����˵�λ
        if(fzlist.size() < 2){
        	data.put("cjfflist", resultList);
    		data.put("yfBeginStr", yfBeginStr);
    		data.put("yfEndStr", yfEndStr);
    		if(fzlist != null){
        		data.put("yrdwmc",fzlist.get(0).get("mc"));
    		}
    		File WordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx", "bjlydx_qgzx_word_sqb.xml", "�걨��");
    		FileUtil.outputWord(response, WordFile);
    		return null;
        }
        List<File> files = new ArrayList<File>();
      //�������ݷ��飬�����zip�����
        for(int i=1;i<=fzlist.size();i++){
        	File file = null;
        	String yrdwmc = null;
        	List<HashMap<String, String>> filelist = new ArrayList<HashMap<String,String>>();
        	Map<String,Object> datatemp = new HashMap<String,Object>();
        	for (HashMap<String, String> hashMap : resultList) {
        		if(fzlist.get(i-1).get("dm").equals(hashMap.get("yrdwdm"))){
        			filelist.add(hashMap);
        			yrdwmc = fzlist.get(i-1).get("mc");
        		}
			}
        	data.put("cjfflist", filelist);
        	data.put("yfBeginStr", yfBeginStr);
        	data.put("yfEndStr", yfEndStr);
        	data.put("yrdwmc", yrdwmc);
        	file = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx","bjlydx_qgzx_word_sqb.xml",yrdwmc+"�걨��");
        	files.add(file);
        }
        File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
        FileUtil.outputZip(response, zipFile);
		return null;
	}
	
}

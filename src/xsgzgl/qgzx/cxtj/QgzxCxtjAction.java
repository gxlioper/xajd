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
 * 勤工助学-查询统计-酬金统计导出
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjAction extends BasicAction{
	
	/**
	 * 岗位信息查询
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
			//生成高级查询对象
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
	 * 岗位信息查看
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
	 * 学生岗位查询
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
			//生成高级查询对象
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
			//生成高级查询对象
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
			//生成高级查询对象
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

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.xsgzjlMxCx(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
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
			//生成高级查询对象
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

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.dwgzCx(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	public ActionForward xsgzCxExportData(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.xsgzCx(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	public ActionForward xsgzffCk(ActionMapping mapping,
								ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		User user = getUser(request);
		QgzxCxtjForm myForm = (QgzxCxtjForm)form;
			// 加载学生基本信息
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
			//生成高级查询对象
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
	 * 岗位信息查看
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
	 * 经费划拨查询
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
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setPath("qgzx_cxtj_jfhb.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cxtj_jfhb.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_yf");
			request.setAttribute("jfhbfs", "yf");
		}else{
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_nd");
			request.setAttribute("jfhbfs", "nd");
		}
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_jfhbb");
		if("no".equalsIgnoreCase(qgzxCsszService.getCssz().get("sfjfhb"))){
			String msg = "当前参数设置无需通过经费划拨控制酬金发放，该模块无需维护！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jfhbCx");
		}
	}

	
	/**
	 * 经费划拨查看
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
	 * 酬金发放查询
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
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setPath("qgzx_cxtj_cjff.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cxtj_cjff.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_cjffb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_cjff");
		request.setAttribute("xxdm", Base.xxdm);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("cjffCx");
		}
	}

	
	/**
	 * 酬金发放查看
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
	 * 部门酬金发放统计
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
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cxtj_cjtj.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//获得默认参数
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("bmcjffTj");
		}
	}
	
	
	/**
	 * 个人酬金发放统计
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
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cxtj_cjtj.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//获得默认参数
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("gwList", service.getGwList());
		if(userType.equalsIgnoreCase("stu")){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("grcjffTj");
		}
	}
/**
 * 
 * @描述:岗位酬金发放统计
 * @作者：CP[工号：1352]
 * @日期：2017-8-18 上午09:44:52
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 * ActionForward 返回类型 
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
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cxtj_cjtj.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//获得默认参数
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("gwList", service.getGwList());
		if(userType.equalsIgnoreCase("stu")){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwcjffTj");
		}
	}
	/**
	 * 部门酬金发放查询
	 */
	public ActionForward bmcjffCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		model.setUser(user);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cxtj_bmcjffcx.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		//获得默认参数
		HashMap<String,String> rs = service.getMrCs(model);
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("bmList", service.getBmList());
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("bmcjffCx");
		}
	}
	
	/**
	 * 打印Word登记表
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
	 * 打印Word登记表zip
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
	
	//填充模版数据生成word文件
	private File getWord(String id) throws Exception {
		String mbmc = "qgzxxsda.xml";
		Map<String,Object> data = new HashMap<String,Object>();
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = new QgzxCxtjForm();
		model.setPkValue(id);
		// 现勤工岗位
		HashMap<String,String> xsgwxx = service.getXsgwxx(model);
		data.put("xsgwxx", xsgwxx);
		// 申请理由
		String sqly = HtmlUtil.xmlZy(xsgwxx.get("sqly"));
		data.put("sqly", sqly);
		String xh = xsgwxx.get("xh");
		// 学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		data.put("rs", xsjbxx);
		// 已往勤工助学情况
		StringBuffer ywqgzxqkBuffer = new StringBuffer();
		List<HashMap<String, String>> xsgwxxList = service.getXsgwxxList(xh);
		String xsgwxxGwdm = xsgwxx.get("gwdm"); // 现勤工岗位
		for (int i = 0; i < xsgwxxList.size(); i++) {
			HashMap<String, String> xsgwxxMap = xsgwxxList.get(i);
			if(!xsgwxxGwdm.equals(xsgwxxMap.get("gwdm"))){
				ywqgzxqkBuffer.append(xsgwxxMap.get("yrdwmc")).append(xsgwxxMap.get("gwmc")).append("，");
			}
		}
		String ywqgzxqkmc = ywqgzxqkBuffer.reverse().replace(0, 1, "").reverse().toString();
		data.put("ywqgzxqkmc", ywqgzxqkmc);
		// 加载学生家庭基本信息
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xh);
		data.put("jtqk", jtqkmodel);
		PjjgService pjjgService = new PjjgService();
		// 加载学生困难认定情况
		KnsjgService knsjgService = new KnsjgService();
		HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, "", "");
		String rddcmc = knsjg.get("dcmc")==null?"":knsjg.get("dcmc");
		data.put("rddcmc", rddcmc);
		//黑龙江农垦职业学院个性化
		if("12727".equals(Base.xxdm)){
			mbmc = "qgzxxsda_12727.xml";
			// 家庭成员
			List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
			pjjgService.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
			data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
		}
		File wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx",mbmc,xh+"-"+xsjbxx.get("xm")+"-"+xsgwxx.get("yrdwmc")+xsgwxx.get("gwmc"));
		return wordFile;
	}
	
	//北京林业大学勤工助学酬金发放申报表导出
	public ActionForward getCjffSbbDclist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		QgzxCxtjDAO dao = new QgzxCxtjDAO();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*格式化发放【年度】和【月份】作为用工期间 start*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
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
		/*格式化发放【年度】和【月份】作为用工期间 end*/
		User user = getUser(request);
		//查询页面高级查询条件选择后的结果集
		List<HashMap<String,String>> resultList = dao.getCjffSbbDclist(model, user);
		//查询用人单位分组list,用于判断是否存在多个单位的酬金发放信息，若有，分组数据，打成zip包，若没有输出单个word文件
		List<HashMap<String,String>>  fzlist = dao.getFzlist(model, user);
		//判断是否存在多个用人单位
        if(fzlist.size() < 2){
        	data.put("cjfflist", resultList);
    		data.put("yfBeginStr", yfBeginStr);
    		data.put("yfEndStr", yfEndStr);
    		if(fzlist != null){
        		data.put("yrdwmc",fzlist.get(0).get("mc"));
    		}
    		File WordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx", "bjlydx_qgzx_word_sqb.xml", "申报表");
    		FileUtil.outputWord(response, WordFile);
    		return null;
        }
        List<File> files = new ArrayList<File>();
      //进行数据分组，最后打成zip包输出
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
        	file = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx","bjlydx_qgzx_word_sqb.xml",yrdwmc+"申报表");
        	files.add(file);
        }
        File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
        FileUtil.outputZip(response, zipFile);
		return null;
	}
	
}

package xsgzgl.qgzx.gwglnew;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
import xsgzgl.qgzx.glygl.XqglyService;
import xsgzgl.qgzx.jcdmwh.QgzxJcdmwhService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 */
@SuppressWarnings("unchecked")
public class QgzxGwglAction extends SuperAction {
	
	/**
	 * 岗位申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getGwsqPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String userType=user.getUserType();
		/*if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}*/
		request.setAttribute("splc", new QgzxCsszService().getCssz().get("yrdwsplc"));
		SearchModel searchModel=new SearchModel();
		//searchModel.setSearch_tj_xn(new String[]{Base.currXn});
//		searchModel.setSearch_tj_sf(new String[]{"是"});
		
		
		String path = "qgzx_gwglnew_gwsq.do";
		request.setAttribute("path", path);
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		String sfkfgwsq = cs.get("sfkfgwsq");
		String gwsqkssj = cs.get("gwsqkssj");
		String gwsqjssj = cs.get("gwsqjssj");
		String dqsj = DateUtils.format(new Date(), "yyyyMMdd");
		if("on".equalsIgnoreCase(sfkfgwsq) 
				&& (null==gwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(gwsqkssj))
				&& (null==gwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(gwsqjssj))){
			request.setAttribute("kycz", "true");
		}
		if(StringUtils.isNotNull(cs.get("qgzq")) && "xq".equals(cs.get("qgzq"))){
			request.setAttribute("czpath", "qgzx_gwglnew_gwsq_xq.do");
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("qgzq", cs.get("qgzq"));
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gwsqCx");
	}
	
	
	/**
	 * 岗位申请增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwsq.do");
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		request.setAttribute("xxdm", Base.xxdm);
		//浙江旅游学院个性化    xxdm=12867   
		if(Base.xxdm.equals("12867")){
			//获取系统当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String sqsj = df.format(System.currentTimeMillis());
			request.setAttribute("sqsj", sqsj);
		}
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
		request.setAttribute("yxsszList", yxsszList);

		return mapping.findForward("gwsqZj");

	}
	
	
	/**
	 * 岗位申请修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("xxdm", Base.xxdm);
		rForm.setPath("qgzx_gwgl_gwsq.do");
		HashMap<String,String> rs = service.getGwsqMap(model);
		String bz = rs.get("bz");
		if(!("update").equals(doType)){
			request.setAttribute("rs", StringUtils.formatMap(rs));
		}else{
			request.setAttribute("rs", StringUtils.formatData(rs));
			request.setAttribute("bzs", bz);
		}
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
		request.setAttribute("yxsszList", yxsszList);
		if("10511".equals(Base.xxdm)){
			request.setAttribute("yrdwlblist", service.getYddwLbList());
			request.setAttribute("zjlylist", service.getZjlyList());
		}
		if("10351".equalsIgnoreCase(Base.xxdm)){//温州大学个性化
			request.setAttribute("xyList",Base.getXyNewList());
			service.setGlxyList(model,pkValue);
		}
		//浙江中医药个性化判断
		if("10344".equals(Base.xxdm)){
			request.setAttribute("xqList", new XqglyService().getXqList());
		}
		QgCommUtilf.setCssz(request);
		request.setAttribute("xqmc", Base.getXqmcForXqdm(rs.get("xq")));
		//浙江旅游职业学院个性化
		if("12867".equals(Base.xxdm)){
			return mapping.findForward("gwsqXgForZjlyzy");
		}else{
			return mapping.findForward("gwsqXg");
		}
	}
	
	/**
	 * 岗位审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getGwshPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		SearchModel searchModel=new SearchModel();
		//searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_qgshzt(new String[]{"未审核"});
		searchModel.setSearch_tj_sf(new String[]{"是"});
	
		
		String path = "qgzx_gwglnew_gwsh.do";
		request.setAttribute("path", path);
		QgCommUtilf.setCzpath(request, searchModel);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("gwshCx");
	}
	
	
	/**
	 * 岗位信息单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		rForm.setPath("qgzx_gwglnew_gwsh.do");
		HashMap<String,String> rs = service.getGwsqMap(model);
		rs.put("shid", request.getParameter("shid"));
		model.setSplcid(rs.get("splcid"));
		request.setAttribute("rs", StringUtils.formatDataView(rs));
		//浙江旅游学院个性化
		if("12867".equals(Base.xxdm)){
			//是否是最后一级审核
			boolean isZhgw=service.isZhgw(model);
			
			//学年学期
			DAO dao = DAO.getInstance();
			ArrayList<HashMap<String, String>> gdgList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> xnxqMap = new HashMap<String, String>();
			ArrayList<HashMap<String, String>> sxgList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> xnMap = new HashMap<String, String>();
			String xn = null;
			String nd = null;
			int currentNd = Base.currNd != null ? Integer.parseInt(Base.currNd) : Integer.parseInt(DealString.getDateTime().substring(0,4));
			for (int i = currentNd - 2; i < currentNd + 1; i++) {
				xnxqMap = new HashMap<String, String>();
				xnMap = new HashMap<String, String>();
				nd = String.valueOf(i + 1);
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				xnxqMap.put("xn", xn);
				gdgList.add(xnxqMap);
				xnMap.put("nd", nd);
				sxgList.add(xnMap);
			}
			if(isZhgw){
				request.setAttribute("xnList", gdgList);
				request.setAttribute("xqList", dao.getXqList());
				request.setAttribute("ndList", sxgList);
			}
			
		}
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
		request.setAttribute("yxsszList", yxsszList);
		service.setRequestValue(rForm, user, request);
		QgCommUtilf.setCssz(request);
		//浙江旅游学院个性化
		if("12867".equals(Base.xxdm)){
			return mapping.findForward("gwshDgshForZjlyzy");
		}else{
			return mapping.findForward("gwshDgsh");
		}
	}
	
	
	
	/**
	 * 岗位信息批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		
		rForm.setPath("qgzx_gwgl_gwsh.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwshPlsh");
	}
	
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
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getGwxxPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		SearchModel searchModel=new SearchModel();
		//searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_sf(new String[]{"是"});
		
		
		request.setAttribute("sfQggly", "1");
		if("10351".equals(Base.xxdm)){
			String sfQggly=new QgzxGlyglService().sfQggly(user.getUserName())?"1":"0"; //判断是否勤工管理员
			request.setAttribute("sfQggly", sfQggly);
		}
		
		String path = "qgzx_gwglnew_gwxxgl.do";
		request.setAttribute("path", path);
		QgCommUtilf.setCzpath(request, searchModel);
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gwxxCx");
	}
	
	
	/**
	 * 岗位信息增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		model.setXn(Base.currXn);
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		//浙江旅游学院个性化    xxdm=12867   
		if(Base.xxdm.equals("12867")){
			//获取系统当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String sqsj = df.format(System.currentTimeMillis());
			request.setAttribute("sqsj", sqsj);
			ArrayList<HashMap<String, String>> sxgList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> xnMap = new HashMap<String, String>();
			String nd = null;
			int currentNd = Base.currNd != null ? Integer.parseInt(Base.currNd) : Integer.parseInt(DealString.getDateTime().substring(0,4));
			for (int i = currentNd - 2; i < currentNd + 1; i++) {
				xnMap = new HashMap<String, String>();
				nd = String.valueOf(i + 1);
				xnMap.put("nd", nd);
				sxgList.add(xnMap);
			}
			request.setAttribute("ndList", sxgList);
		}
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yrbmList", service.getYrbm(model));
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
		request.setAttribute("yxsszList", yxsszList);
		if("10511".equals(Base.xxdm)){
			request.setAttribute("yrdwlblist", service.getYddwLbList());
			request.setAttribute("zjlylist", service.getZjlyList());
		}
		if("10351".equalsIgnoreCase(Base.xxdm)) {//温州大学个性化
			request.setAttribute("xyList",Base.getXyNewList());
			String bmdm = user.getUserDep();
			boolean result = service.isYxBm(bmdm);
			request.setAttribute("sfxy", result?"1":"0");//用户所在部门是否为院系部门（1：是,2:不是）
			request.setAttribute("bmdm", bmdm);
			request.setAttribute("xxdm", Base.xxdm);
		}
		//浙江中医药个性化判断
		if("10344".equals(Base.xxdm)){
			request.setAttribute("xqList", new XqglyService().getXqList());
		}
		this.saveToken(request);
		if("12867".equals(Base.xxdm)){
			return mapping.findForward("gwxxZjForZjlyzy");
		}else{
			return mapping.findForward("gwxxZj");
		}
	}
	
	
	/**
	 * 岗位信息操作（修改，查看，添加人员，退岗人员）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		if("10511".equals(Base.xxdm)){
			HashMap<String,String> rs = service.getGwxxMap(myForm);
			request.setAttribute("rs1", rs);
			
		}
		HashMap<String,String> rs = service.getGwryxx(myForm,request);
		if(!("gwxxXg").equals(doType)){
			String zgryHtml = rs.get("zgryHtml");
			//rs = (HashMap<String, String>) StringUtils.formatDataView(rs);
			rs.put("zgryHtml", zgryHtml);
			request.setAttribute("rs", rs);
		}else{
			String zgryHtml = rs.get("zgryHtml");
			rs =(HashMap<String, String>) StringUtils.formatData(rs);
			rs.put("zgryHtml", zgryHtml);
			request.setAttribute("rs", StringUtils.formatData(rs));
			if("10351".equalsIgnoreCase(Base.xxdm)){//温州大学个性化
				request.setAttribute("xyList",Base.getXyNewList());
				request.setAttribute("xxdm", Base.xxdm);
				service.setGlxyList(myForm,pkValue);
			}
			//浙江中医药个性化判断
			if("10344".equals(Base.xxdm)){
				request.setAttribute("xqList", new XqglyService().getXqList());
			}
		}
		
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		service.setRequestValue(rForm, user, request);
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//有效时设置list
		request.setAttribute("yxsszList", yxsszList);
		if("10511".equals(Base.xxdm)){
			request.setAttribute("yrdwlblist", service.getYddwLbList());
			request.setAttribute("zjlylist", service.getZjlyList());
		}
		QgCommUtilf.setCssz(request);
		//浙江旅游职业学院个性化  xxdm=12867
		if(("gwxxXg").equals(doType) &&"12867".equals(Base.xxdm)){
			ArrayList<HashMap<String, String>> sxgList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> xnMap = new HashMap<String, String>();
			String nd = null;
			int currentNd = Base.currNd != null ? Integer.parseInt(Base.currNd) : Integer.parseInt(DealString.getDateTime().substring(0,4));
			for (int i = currentNd - 2; i < currentNd + 1; i++) {
				xnMap = new HashMap<String, String>();
				nd = String.valueOf(i + 1);
				xnMap.put("nd", nd);
				sxgList.add(xnMap);
			}
			request.setAttribute("ndList", sxgList);
			request.setAttribute("xnList", Base.getXnndList());
			request.setAttribute("xqList", Base.getXqList());
			//返回修改岗位页面
			return mapping.findForward("gwxxXgForZjlyzy");
		} else if(("ryxxZj").equals(doType) &&"12867".equals(Base.xxdm)) {
			//返回人员增减页面
			return mapping.findForward("ryxxZjForZjlyzy");
		} else if(("ryxxTg").equals(doType) &&"12867".equals(Base.xxdm)) {
			//返回人员退岗页面
			return mapping.findForward("ryxxTgForZjlyzy");
		} else if(("gwxxCk").equals(doType) &&"12867".equals(Base.xxdm)) {
			//返回查看明细页面
			return mapping.findForward("gwxxCkForZjlyzy");
		}
		return mapping.findForward(doType);
	}
	/**
	 * 人员退岗
	 */
	public ActionForward ryxxTgWin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
//		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		String len = request.getParameter("len");
		request.setAttribute("len", len);
		User user = getUser(request);
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("ryxxTgWin");
	}
	/**
	 * 岗位批量退岗
	 */
	public ActionForward gwxxTgWin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
//		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		String len = request.getParameter("len");
		request.setAttribute("len", len);
		User user = getUser(request);
		service.setRequestValue(rForm, user, request);
		String path = "qgzx_gwglnew_gwxxgl.do";
		request.setAttribute("path", path);
		return mapping.findForward("gwxxTgWin");
	}
	
	
	/**
	 * 获得学生列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getStuPageList(model, user, request.getParameter("sfxyzgsc"));
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		String path = "qgzx_gwglnew_getStu.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("pkValue", request.getParameter("pkValue"));
		//勤工个性化参数
		request.setAttribute("xn", request.getParameter("xn"));
		//资格审查开关开启，只有勤工助学库中的学生可以添加
		if ("yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))){
			request.setAttribute("sfxyzgsc", "yes");
		}
		return mapping.findForward("getStu");
	}
	
	/**
	 * 
	 * @描述:打印岗位申请表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-20 下午07:12:39
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
	public ActionForward getGwsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		File wordFile = getGwsqbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 获取岗位申请表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-20 下午07:12:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getGwsqbWord(QgzxGwglForm myForm,HttpServletRequest request) throws Exception{

		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> gwsqMap = new QgzxGwglDAO().getGwsqMap(myForm);
//		data.put("xyzsjg", myForm);
		gwsqMap.put("gwryyq", HtmlUtil.xmlZy(gwsqMap.get("gwryyq")));
		gwsqMap.put("gzdd", HtmlUtil.xmlZy(gwsqMap.get("gzdd")));
		gwsqMap.put("gzsj", HtmlUtil.xmlZy(gwsqMap.get("gzsj")));
		gwsqMap.put("gznr", HtmlUtil.xmlZy(gwsqMap.get("gznr")));
		gwsqMap.put("bz", HtmlUtil.xmlZy(gwsqMap.get("bz")));
		gwsqMap.put("gwyqryxg", HtmlUtil.xmlZy(gwsqMap.get("gwyqryxg")));
		data.putAll(gwsqMap);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx","gwsqbzjzyy.xml",gwsqMap.get("gwmc"));
		
		return file;
	}
	
	/**
	 * ]
	 * @描述:批量打印岗位申请表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-20 下午07:13:13
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
	public ActionForward getGwsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setPkValue(values[i]);
				File file = getGwsqbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	public ActionForward showFdys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "qgzx_gwglnew.do?method=showFdys";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showFdys");
	}
	
	public ActionForward showFdysDg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String idxh = request.getParameter("idxh");
		String path = "qgzx_gwglnew.do?method=showFdys";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("idxh", idxh);
		return mapping.findForward("showFdysDg");
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-7 下午04:16:01
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String value = request.getParameter("values");
		myForm.setGwdm(value);
		QgzxGwglForm model = new QgzxGwglDAO().getModel(myForm);
		User user = getUser(request);
		boolean result = new QgzxGwglService().submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-7 下午04:19:43
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = new QgzxGwglService().cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			QgzxGwglForm model = new QgzxGwglForm();
			model.setGwdm(sqid);
			model.setSplcid(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			new QgzxGwglDAO().runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:最后一级撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-8 下午05:46:43
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setGwdm(sqid);
		// 最后一级撤销
		boolean isSuccess = new QgzxGwglService().cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-8 下午05:47:02
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm model =new QgzxGwglForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		String cancelFlg = new QgzxGwglService().cxshnew(shxx.get("ywid"),model,user);
		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}


	public ActionForward gwfbCx(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		if(QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);

			List<HashMap<String,String>> list = new QgzxGwglService().getGwPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_gwglnew_gwfb.do");
		FormModleCommon.commonRequestSet(request);
		HashMap<String,String> map = new QgzxCsszService().getKfsqMap();
		request.setAttribute("sfkfsq",map.get("sfkfxndwgwsq"));
		return mapping.findForward("gwfbCx");
	}

	public ActionForward gwfbZj(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		if(SAVE.equals(myForm.getType()) || SUBMIT.equals(myForm.getType())){
			boolean rs = qgzxGwglService.saveGw(myForm,user);
			String message = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		String userName = user.getUserName();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xq",Base.currXq);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
		request.setAttribute("yrdw",qgzxGwglService.getYrdwByUser(userName));
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date tody = new Date();
		request.setAttribute("defalutStart",sdf.format(tody));
		Calendar c = Calendar.getInstance();
		c.setTime(tody);
		c.add(Calendar.WEEK_OF_YEAR,2);
		request.setAttribute("defalutEnd", sdf.format(c.getTime()));
		return mapping.findForward("gwfbZj");
	}

	public ActionForward gwfbXg(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		if(SAVE.equals(myForm.getType()) || SUBMIT.equals(myForm.getType())){
			boolean rs = qgzxGwglService.updateGw(myForm,user);
			String message = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		String userName = user.getUserName();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("yrdw",qgzxGwglService.getYrdwByUser(userName));
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("model",qgzxGwglService.getGwxxByGwdm(myForm.getGwdm()));
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		return mapping.findForward("gwfbXg");
	}
	public ActionForward gwfbCk(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		String userName = user.getUserName();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("yrdw",qgzxGwglService.getYrdwByUser(userName));
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("model",qgzxGwglService.getGwxxByGwdm(myForm.getGwdm()));
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		return mapping.findForward("gwfbCk");
	}

	public ActionForward gwlbChange(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		HashMap<String,String> data = qgzxGwglService.getGwlbById(id);
		response.getWriter().print(JSONObject.fromObject(data));
		return null;
	}
	public ActionForward submitGwfb(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String gwdm = request.getParameter("values");
		myForm.setGwdm(gwdm);
		User user = getUser(request);
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		boolean result = qgzxGwglService.submitGwfb(myForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward cancelGwfb(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splc");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		boolean result = qgzxGwglService.cancelGwfb(sqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			QgzxGwglForm model = new QgzxGwglForm();
			model.setGwdm(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			qgzxGwglService.updateGwfb(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	public ActionForward gwfbshCx(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		if(QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);

			List<HashMap<String,String>> list = new QgzxGwglService().getGwfbshList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_gwglnew_gwfbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gwfbshCx");
	}
	public ActionForward gwfbsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		if(SAVE.equals(myForm.getType())){
			boolean flag = qgzxGwglService.saveSh(myForm,user);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QgzxJcdmwhService qgzxJcdmwhService = new QgzxJcdmwhService();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("yrdwid",myForm.getYrdwid());
		request.setAttribute("yrdwmc",qgzxJcdmwhService.getData(myForm.getYrdwid()).get("yrdwmc"));
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("model",qgzxGwglService.getGwxxByGwdm(myForm.getGwdm()));
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		return mapping.findForward("gwfbsh");
	}
	public ActionForward gwfbshCk(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		QgzxJcdmwhService qgzxJcdmwhService = new QgzxJcdmwhService();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("yrdwid",myForm.getYrdwid());
		request.setAttribute("yrdwmc",qgzxJcdmwhService.getData(myForm.getYrdwid()).get("yrdwmc"));
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("model",qgzxGwglService.getGwxxByGwdm(myForm.getGwdm()));
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		return mapping.findForward("gwfbshCk");
	}

	public ActionForward cancelGwSh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		myForm.setShzt(shzt);
		myForm.setGwdm(sqid);
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		//撤销审核，最后一级。
		boolean isSuccess = qgzxGwglService.cancelSh(myForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	public ActionForward plsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(myForm.getType())){
			QgzxGwglService qgzxGwglService = new QgzxGwglService();
			String message = qgzxGwglService.plshBc(myForm, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("gwfbPlsh");
	}
	//黑名单验证
	public ActionForward hmdyz(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		User u = getUser(request);
		String msg = qgzxGwglService.yz(u);
		boolean flag = "success".equals(msg);
		response.getWriter().print(getJsonMessageResult(msg,flag));
		return null;
	}
	//岗位删除
	public ActionForward gwSc(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gwdms = request.getParameter("values");
		User u = getUser(request);
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		if(!qgzxGwglService.checkIsUsed(gwdms.split(","))){
			boolean flag = qgzxGwglService.gwSc(gwdms,u);
			String msg = flag ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			response.getWriter().print(getJsonResult(msg,flag));
		} else {
			response.getWriter().print(getJsonMessage("数据被使用"));
		}

		return null;
	}

	public ActionForward gwExportData(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = qgzxGwglService.getExportList(model,user);
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

	public ActionForward gwfbjgCx(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		if(QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);

			List<HashMap<String,String>> list = new QgzxGwglService().getJgPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_gwglnew_gwfbjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gwfbjgCx");
	}

	public ActionForward gwfbjgZj(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		if(SAVE.equals(myForm.getType())){
			boolean rs = qgzxGwglService.insertJg(myForm,user);
			String message = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
//		String userName = user.getUserName();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xq",Base.currXq);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
//		request.setAttribute("yrdw",qgzxGwglService.getYrdwByUser(userName));
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date tody = new Date();
		request.setAttribute("defalutStart",sdf.format(tody));
		Calendar c = Calendar.getInstance();
		c.setTime(tody);
		c.add(Calendar.WEEK_OF_YEAR,2);
		request.setAttribute("defalutEnd", sdf.format(c.getTime()));
		return mapping.findForward("gwfbjgZj");
	}

	public ActionForward selectDw(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) throws Exception{

		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String,String>> resultList = qgzxGwglService.getYrdwList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_gwglnew.do?method=selectDw");
		return mapping.findForward("selectDw");
	}

	public ActionForward gwfbjgCk(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		String userName = user.getUserName();
		HashMap<String,String> data = qgzxGwglService.getGwxxByGwdm(myForm.getGwdm());
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("model",data);
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		return mapping.findForward("gwfbjgCk");
	}

	public ActionForward gwfbjgXg(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		QgzxGwglService qgzxGwglService = new QgzxGwglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		User user = getUser(request);
		if(SAVE.equals(myForm.getType())){
			boolean rs = qgzxGwglService.updateJg(myForm,user);
			String message = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		String userName = user.getUserName();
		request.setAttribute("gwlblist",qgzxGwglService.getGwList());
		request.setAttribute("model",qgzxGwglService.getGwxxByGwdm(myForm.getGwdm()));
		request.setAttribute("gsgz",qgzxCsszService.getCssz());
		return mapping.findForward("gwfbjgXg");
	}
}

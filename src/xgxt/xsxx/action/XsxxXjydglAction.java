package xgxt.xsxx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.xginfo.CommXgInfoService;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.service.XsxxXjydglService;
import xgxt.xtwh.common.service.XtlcglService;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/** 
 * Creation date: 01-25-2011
 * author lr 
 */
public class XsxxXjydglAction extends BasicAction {

	/**
	 * 学籍异动申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward xjydsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		StudentInfoForm model = (StudentInfoForm)form;
		//用户信息
		User user = getUser(request);
		//表名
		String tableName = "bks_xjydxx";
		//视图名
		String viewName = "view_xjydjbxx";
		//访问路径
		String path = "xjydsq.do";
		//主键
		String pkValue = request.getParameter("pkValue");
		//学号 
		String xh = "stu".equals(user.getUserType()) //学生用户取用户名
					? user.getUserName() 
					: request.getParameter("xh");		
		//操作类型
		String oper = request.getParameter("oper");
		//操作
		String doType = request.getParameter("doType");
		if("save".equals(doType)){//申请信息保存
			this.insertOperation(request, tableName);
			pkValue = model.getSave_ydxh();//异动序号
			//初始化审核记录
			model.setYdxh(model.getSave_ydxh());
			model.setYdlbdm(request.getParameter("save_ydlbm"));
			service.initXjydshjl(model,user);
		}
		if("modify".equalsIgnoreCase(doType)){//申请信息修改
			//判断是否可修改
			if(service.sfkxgXjydxx(model.getSave_ydxh())){
				this.updateOperation(request, tableName);
			}else{
				request.setAttribute("result", false);
				request.setAttribute("message","已经在审核中，暂时不可修改！");
			}
		}
		
		//查询学生的异动申请信息
		this.selectPageDataByOne(request, tableName, viewName, pkValue);				
		HashMap<String, String> result = new HashMap<String, String>();
		if(StringUtils.isNotNull(pkValue)){
			result = (HashMap<String, String>) request.getAttribute("rs");
		}
		
		//查询学生的基本信息
		HashMap<String, String> rs = new HashMap<String, String>();	
		rs.putAll(xsxxglService.selectStuinfo(xh));
		if(result != null){//部分表字段的名称和视图中字段的名称不一致，需要转换
			result.put("save_ydlbm", result.get("save_ydlbdm"));
			result.put("save_ydqbdm", result.get("save_ydqbdm"));
			result.put("save_ydqbjmc", result.get("ydqbjmc"));
			result.put("save_ydhbdm", result.get("save_ydhbjdm"));
			rs.putAll(result);
		}
		if(StringUtils.isNull(pkValue)){//初始化异动信息	
			rs.putAll(service.initYdxx(rs));
		}
		
		request.setAttribute("rs",rs );
		request.setAttribute("path",path);
		FormModleCommon.commonRequestSet(request);//路径、读写权信息
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("oper", oper); 
		setListData(request);//加载下拉列表的数据
		return mapping.findForward("success");
	}
	
	/**
	 * 学籍异动修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward modiXxjydsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		StudentInfoForm model = (StudentInfoForm)form;
		//用户信息
		User user = getUser(request);
		//表名
		String tableName = "bks_xjydxx";
		//视图名
		String viewName = "view_xjydjbxx";
		//访问路径
		String path = "xjydsq.do";
		//主键
		String pkValue = "";
		//学号 
		String xh = "stu".equals(user.getUserType()) //学生用户取用户名
					? user.getUserName() 
					: request.getParameter("xh");
		//操作
		String doType = request.getParameter("doType");
		if("save".equals(doType)){//申请信息保存
			this.insertOperation(request, tableName);
			pkValue = model.getSave_ydxh();//异动序号
			//初始化审核记录
			model.setYdxh(model.getSave_ydxh());
			model.setYdlbdm(request.getParameter("save_ydlbm"));
			service.initXjydshjl(model,user);
		}
		if("modify".equalsIgnoreCase(doType)){//申请信息修改
			this.updateOperation(request, tableName);
		}
		
		//查询学生的异动申请信息
		this.selectPageDataByOne(request, tableName, viewName, pkValue);				
		HashMap<String, String> result = new HashMap<String, String>();
		if(StringUtils.isNotNull(pkValue)){
			result = (HashMap<String, String>) request.getAttribute("rs");
		}
		
		//查询学生的基本信息
		HashMap<String, String> rs = new HashMap<String, String>();	
		rs.putAll(xsxxglService.selectStuinfo(xh));
		if(result != null){//部分表字段的名称和视图中字段的名称不一致，需要转换
			result.put("save_ydlbm", result.get("save_ydlbdm"));
			result.put("save_ydqbdm", result.get("save_ydqbdm"));
			result.put("save_ydqbjmc", result.get("ydqbjmc"));
			result.put("save_ydhbdm", result.get("save_ydhbjdm"));
			rs.putAll(result);
		}
		if(StringUtils.isNull(pkValue)){//初始化异动信息	
			rs.putAll(service.initYdxx(rs));
		}
		
		request.setAttribute("rs",rs );
		request.setAttribute("path",path);
		FormModleCommon.commonRequestSet(request);//路径、读写权信息
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		setListData(request);//加载下拉列表的数据
		return mapping.findForward("success");
	}
	
	/**
	 * 学籍异动审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxXjydglService service = new XsxxXjydglService();
		XtlcglService xtlcglService = new XtlcglService();
		boolean result = false;
		//用户信息
		User user = getUser(request);
		//访问路径
		String path = "xjydsh.do";
		//操作
		String doType = request.getParameter("doType");
		//异动类别代码
		String ydlbm = model.getYdlbdm();
		
		//查询
		List<HashMap<String, String>> topTr = service.getXjydshToptr(ydlbm);
		List<String[]> rs =  new ArrayList<String[]>();
		
		
		//批量审核
		if ("sh".equals(doType)) {
			//审核结果
			String shjg = request.getParameter("shjg");
			String shyj = request.getParameter("save_shyj");
			model.setShjg(shjg);
			model.setShyj(shyj);
			//批量审核
			result = service.xjydshBatch(model);
			request.setAttribute("message", result ? "操作成功！" : "操作失败！");
			request.setAttribute("result", result);
			doType = "query";
		}
		//取消审核
		if("qxsh".equalsIgnoreCase(doType)){
			result = service.xjydqxsh(model, user);
			request.setAttribute("message", result ? "操作成功！" : "操作失败！");
			request.setAttribute("result", result);
			doType = "query";
		}
		if ("query".equals(doType)) {
			rs = service.queryXjydsh(model,user);
		}	
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		setListData(request);//加载下拉列表的数据
		List<HashMap<String, String>> ydlbList = (List)request.getAttribute("ydlbList");
		model.setYdlbdm(StringUtils.isNull(model.getYdlbdm()) ? ydlbList.get(0).get("dm") : model.getYdlbdm());
		//审核岗位
		request.setAttribute("xtgwidList", service.getXjydshgw(model.getYdlbdm()));
		//值用以还原选择的审核状态条件
		request.setAttribute("shztStr", StringUtils.joinStrByArray(model.getShztArr(), ",") );
		request.setAttribute("now", GetTime.getSystemTime());//当前系统时间
		//查询用户所有岗位
		List<HashMap<String, String>> list = xtlcglService.getYhgwList(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm(), 
				user.getUserName()); 		
		model.setXtgwid(StringUtils.isNull(model.getXtgwid()) ? (list != null && list.size()>0 ? list.get(0).get("xtgwid") : "") : model.getXtgwid());
		request.setAttribute("yhgwList", list);
		return mapping.findForward("xjydsh");
	}
	
	/**
	 * 学籍异动审核查看、单条审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydShOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		//系统岗位id
		String xtgwid = request.getParameter("xtgwid");
		//用户信息
		User user = getUser(request);
		//表名
		String tableName = "bks_xjydxx";
		//视图名
		String viewName = "view_xjydjbxx";
		//访问路径
		String path = "xjydsh.do";
		//操作
		String doType = request.getParameter("doType");
		//主键
		String pkValue = request.getParameter("pkValue");		
		
		//单个审核
		if ("save".equals(doType)) {
			//审核结果
			String shjg = request.getParameter("shjg");
			 
			model.setUserName(user.getUserName());
			model.setShjg(shjg);
			model.setYdlbdm(request.getParameter("ydlbdm"));
			//审核
			boolean result = service.modifyXsxjxx(model,pkValue);
			request.setAttribute("message", result ? "操作成功！" : "操作失败！");
			request.setAttribute("result", result);
		}
		//信息查询
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>)request.getAttribute("rs");
		if(!"view".equalsIgnoreCase(doType)){
			//查询当前用户审核信息
			rs.putAll(service.getXjydshxx(xtgwid, pkValue));
		}else{
			//查看
			request.setAttribute("shxxList", service.getXjydshxxOne(pkValue));
		}
		
		//当前时间
		request.setAttribute("dqsj", GetTime.getSystemTime());
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		//加载审核列表的数据
		request.setAttribute("shList", xsxxglService.getList(GlobalsVariable.XTWH_SH_LIST));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("xjydshOne");
	}
	
	/**
	 * 学籍异动查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxXjydglService service = new XsxxXjydglService();
		//用户信息
		User user = getUser(request);
		//访问路径
		String path = "xjydcx.do";
		//操作
		String doType = request.getParameter("doType");
		//异动类别代码
		String ydlbm = model.getYdlbdm();
		
		//查询
		List<HashMap<String, String>> topTr = service.getXjydshToptr(ydlbm);
		List<String[]> rs =  new ArrayList<String[]>();
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, "xg_xsxx_xjydxx_shb");
			this.deleteOperation(request, "bks_xjydxx");
			doType = "query";
		}
		if ("query".equals(doType)) {
			rs = service.queryXjydsqxx(model,user);
		}
		if("stu".equalsIgnoreCase(user.getUserType())){
			model.setQuerylike_xh(user.getUserName());
		}		
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		setListData(request);//加载下拉列表的数据
		List<HashMap<String, String>> ydlbList = (List)request.getAttribute("ydlbList");
		model.setYdlbdm(StringUtils.isNull(model.getYdlbdm()) ? ydlbList.get(0).get("dm") : model.getYdlbdm());
		//审核岗位
		request.setAttribute("xtgwidList", service.getXjydshgw(model.getYdlbdm()));
		//值用以还原选择的审核状态条件
		request.setAttribute("shztStr", StringUtils.joinStrByArray(model.getShztArr(), ",") );
		request.setAttribute("tableName", "view_xjydjbxx");
		request.setAttribute("realTable", "bks_xjydxx");
		return mapping.findForward("xjydcx");
	}
	
	/**
	 * 学籍异动审核流程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydshlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		CommXgInfoService commService = new CommXgInfoService(); 
		//查询
		List<HashMap<String, String>> topTr = service.getXjydshlcToptr();
		List<String[]> rs =  new ArrayList<String[]>();
		
		//查询学籍异动类别审核流程
		rs = service.queryXjydlbshlc(model);
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ydlbList",xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_YDLBLIST));
		request.setAttribute("xjztmList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("path", "xjydshlc.do");
		//参与方式
		request.setAttribute("cyfsList", commService.getCyfsList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydshlc");
	}
	
	/**
	 * 学籍异动审核流程修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward updateXjydshlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxXjydglService service = new XsxxXjydglService();
		StudentInfoForm model = (StudentInfoForm)form;
		User user = getUser(request);
		//主键
		String pkValue = request.getParameter("pkValue");
		//操作类型
		String doType = request.getParameter("doType");
		//表名
		String tableName = "dm_ydlb";
		
		if("save".equalsIgnoreCase(doType)){
			//保存数据,数据存在，执行修改操作
			this.updateOperation(request, tableName);
			model.setYdlbdm(request.getParameter("save_ydlbm"));
			model.setSave_shlcid(request.getParameter("save_shlcid"));
			
			service.saveShlcdyxxb(model,user);
		}
		
		request.setAttribute("rs", service.getXjydshlcxx(pkValue));
		request.setAttribute("path", "xjydshlc.do");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateXjydshlc");
	}
	
	
	/**
	 * 加载下拉列表的数据
	 * @param reqeust
	 * */
	public void setListData(HttpServletRequest request){
		XsxxglService xsxxglService = new XsxxglService();
		//异动类别
		request.setAttribute("ydlbList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_YDLBLIST));
		//学籍状态
		request.setAttribute("xjztList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		//是否在校
		request.setAttribute("sfzxList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_SFZXLIST));
		//审核列表
		request.setAttribute("shList", xsxxglService.getList(GlobalsVariable.XTWH_SH_LIST));		
		//年级、学院、专业、班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//年度、学年、学期
		FormModleCommon.setNdXnXqList(request);		
	}
}
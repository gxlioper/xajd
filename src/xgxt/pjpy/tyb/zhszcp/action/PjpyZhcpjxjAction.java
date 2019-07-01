package xgxt.pjpy.tyb.zhszcp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.guizhdx.GuizhdxService;
import xgxt.pjpy.lsxy.LsxyPjpyService;
import xgxt.pjpy.tyb.cssz.service.PjpyPjzqszService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZctjszService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhcpjxjService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhszcpService;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import common.GlobalsVariable;

public class PjpyZhcpjxjAction extends CommonAction {
	

	/**
	 * 综合素质测评奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpjxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		GuizhdxService service = new GuizhdxService();
		PjpyZhcpjxjService zhcpjxjService = new PjpyZhcpjxjService();//综合测评奖学金Service
		
		String tableName = "pjpy_ty_zhcpjxjsqb";
		String viewName = "view_pjpy_ty_zhcpjxjsq";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		
		//判断是否开放申请
		controlOnOffToPage(request, GlobalsVariable.GNDM_PJPY_ZHCPJXJSQ);	
		
		//学生用户
		if ("stu".equals(userType)) {
			xh = userName;
		}		
		//保存
		if (!Base.isNull(doType) && "save".equals(doType)) {
			xh = request.getParameter("save_xh");
			//判断条件需要的参数对象
			JxjpdxxModel jxjpdModel = new JxjpdxxModel();
			jxjpdModel.setXh(xh);
			jxjpdModel.setXn(request.getParameter("save_xn"));
			jxjpdModel.setXq(request.getParameter("save_xq"));
			jxjpdModel.setNd(request.getParameter("save_nd"));
			jxjpdModel.setJxjdm(request.getParameter("save_jxjdm"));
			
			String pkValue = xh
							 +jxjpdModel.getXn()
							 +jxjpdModel.getJxjdm()
							 +request.getParameter("save_sqsj")
							 +jxjpdModel.getXq()
							 +jxjpdModel.getNd();
			
			
			boolean result = true;
			//TODO 申请条件判断
			LsxyPjpyService lsxyService = new LsxyPjpyService();
			HashMap<String, String> tjInfo = lsxyService.pdStuTjFlag(jxjpdModel, 
					                                 GlobalsVariable.PJPY_ZHCPJXJ);
			result = "true".equalsIgnoreCase(tjInfo.get("result")) ? true : false;
			if(!result){
				request.setAttribute("message", tjInfo.get("message"));
				request.setAttribute("result", result);
			}
			
			//条件判断通过，将申请信息插入到数据库中
			if(result){
				this.insertOperation(request, tableName);
			}
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			
			HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
			rs.putAll(service.getStuInfo(rs.get("xh")));
			request.setAttribute("rs",rs );
			request.setAttribute("pkValue", pkValue);
			
		}
		
		//综测分及排名、学生基本信息
		if (!Base.isNull(xh)) {
			request.setAttribute("zcpm", service.getZcfPm(xh));		
		
			HashMap<String, String> stuInfo = service.getStuInfo(xh);
			stuInfo.putAll(service.getXsfzxx(xh));
			request.setAttribute("rs",stuInfo );	
		}
		//获取综合测评奖学金代码列表信息
		service.setList(request, GlobalsVariable.PJPY_ZHCPJXJ);
		
		//根据申请周期获取综合测评奖学金申请时间信息
		request.setAttribute("sqsjInfo",zhcpjxjService.getZhcpSqsjMap());
		
		request.setAttribute("path", "zhcpjxjsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zhcpjxjsq");
	}
	
	
	/**
	 * 综合测评奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpjxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		PjpyZhcpjxjActionForm myForm = (PjpyZhcpjxjActionForm) form;
		GuizhdxService service = new GuizhdxService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new  PjpyZjkjxyService();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		//表名
		String tableName = "pjpy_ty_zhcpjxjsqb";
		//视图名
		String viewName = "view_pjpy_ty_zhcpjxjsq";
		//综合测评申请周期
		String zhcpzq = zjkjService.getZhcpSqzq();
		//根据周期获取查询要显示的字段
		String[] zhcpArr = zjkjService.getPjzqzd(zhcpzq);
		
		String[] shOneList = StringUtils.joinStrArr(
							new String[] { "disabled", "bgcolor", "pkValue","xh"}, 
							zhcpArr,
							new String[]{"xm", "nj", "xymc", "bjmc", "jxjmc", "xxsh"});
		String[] fdyshList = StringUtils.joinStrArr(
							new String[] { "disabled", "bgcolor", "pkValue","xh"}, 
							zhcpArr,
							new String[]{"xm", "nj", "xymc", "bjmc", "jxjmc", "fdysh"});
		String[] xyshList = StringUtils.joinStrArr(
							new String[] { "disabled", "bgcolor", "pkValue","xh"}, 
							zhcpArr,
							new String[]{"xm", "nj", "xymc", "bjmc", "jxjmc", "xysh"});
		String[] xxshList = shOneList;
		
		
		// 权限控制
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
		} else if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		//判断是否可审核
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_ZHCPJXJSQ)
				&& zjkjService.checkKgflag()){
			String msg = "审核功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
		}
		
		
		//根据奖学金取审核级别
//		if (!Base.isNull(jxjdm)) {
		//审核级别
		int shjb = 2;//Integer.parseInt(service.getShjb(jxjdm));
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("viewName", viewName);
		map.put("shjb", shjb);
		map.put("shOneList", shOneList);
		map.put("fdyshList", fdyshList);
		map.put("xyshList", xyshList);
		map.put("xxshList", xxshList);
		
		this.typjSh(form, request, map);
//		}
		String bzrSh = service.getBzrshFlag();//班主任是否审核,默认不审核
		request.setAttribute("bzrSh", bzrSh);		
		service.setList(request, GlobalsVariable.PJPY_ZHCPJXJ);
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("path", "zhcpjxjsh.do");
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xq", Base.getJxjsqxq());
		request.setAttribute("pjzq", zjkjService.getZhcpSqzq());//综合测评评奖周期
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zhcpjxjsh");

	}
	
	/**
	 * 审核
	 * @param form
	 * @param request
	 * @param tableName
	 * @param viewName
	 * @param colList
	 */
	public void typjSh(ActionForm form, HttpServletRequest request,
			HashMap<String,Object> values) {
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String isFdy = session.getAttribute("isFdy").toString();
		
		PjpyZhcpjxjActionForm myForm = (PjpyZhcpjxjActionForm) form;
		GuizhdxService service = new GuizhdxService();
		PjpyZhcpjxjService zcjxjService = new PjpyZhcpjxjService();
		
		
		int shjb = (Integer)values.get("shjb");
		String tableName = (String) values.get("tableName");
		String viewName = (String) values.get("viewName");
		String[] shOneList = (String[]) values.get("shOneList");
		String[] fdyshList = (String[]) values.get("fdyshList");
		String[] xyshList = (String[]) values.get("xyshList");
		String[] xxshList = (String[]) values.get("xxshList");
		
		String doType = request.getParameter("doType");
		String zydm = request.getParameter("queryequals_zydm");
		String jxjdm = request.getParameter("queryequals_jxjdm");
		String rychdm = request.getParameter("queryequals_rychdm");
		String nj = request.getParameter("queryequals_nj");
		String xn = Base.getJxjsqxn();
		String yhlx = "";
		String[] colList = null;
		
		myForm.setQueryequals_xn(xn);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (!Base.isNull(jxjdm)) {
			if ("true".equals(isFdy)) {
				map.put("bmlx", "zy");
				map.put("bmdm", zydm);
			} else if("xy".equals(userType)){
				map.put("bmlx", "xy");
				map.put("bmdm", userDep);
			}
		} else if (!Base.isNull(rychdm)) {
			map.put("bmdm", userDep);
		}
		map.put("rychdm", rychdm);
		map.put("jxjdm", jxjdm);
		map.put("xn", xn);
		map.put("nj", nj);
		
		if (1==shjb) {
			colList = shOneList;
			request.setAttribute("shzd", "shzt");
		} else if (2==shjb) {
			if ("xy".equals(userType)) {
				yhlx = "xy";
				xyshList = StringUtils.joinStrArr(xyshList,new String[]{"xxsh"});
				colList = xyshList;
				request.setAttribute("shzd", "xysh");
			} else {
				yhlx = "xx";
				colList = xxshList;
				request.setAttribute("shzd", "xxsh");
			}
		} else if (3==shjb) {
			if ("true".equals(isFdy)) {
				request.setAttribute("shzd", "fdysh");
				fdyshList = StringUtils.joinStrArr(fdyshList, new String[]{"xysh", "xxsh"});
				colList = fdyshList;
				yhlx = "fdy";
			} else if ("xy".equals(userType)) {
				request.setAttribute("shzd", "xysh");
				xyshList = StringUtils.joinStrArr(xyshList,new String[]{"xxsh"});
				colList = xyshList;
				yhlx = "xy";
			} else {
				yhlx = "xx";
				request.setAttribute("shzd", "xxsh");
				colList = xxshList;
			}
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request,yhlx,String.valueOf(shjb));
			this.selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		//批量审核
		if ("sh".equals(doType)) {
			
			String shjg = request.getParameter("shjg");
			if("通过".equalsIgnoreCase(shjg)){
				//判断人数限制是否超出。
				boolean result = false;
				
				//奖学金审核
				String message = zcjxjService.auditingJxj(getValueArrayMap(request, PRIFIX_PRIMARY_KEY),
						                request.getAttribute("shzd").toString(),
						                tableName);
				if(StringUtils.isNull(message)){
					message = "操作成功！";
					result = true;
				}
				request.setAttribute("result", result);
				request.setAttribute("message", message);
			}
			else{
				this.auditingBatchOperation(request, tableName);
			}
		}
		
		request.setAttribute("shjb", shjb);
		map.put("shjb", String.valueOf(shjb));
	}
	
	/**
	 * 综合测评奖学金管理 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpjxjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		PjpyZhcpjxjActionForm myForm = (PjpyZhcpjxjActionForm) form;
		GuizhdxService service = new GuizhdxService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		
		String tableName = "pjpy_ty_zhcpjxjsqb";
		String viewName = "view_pjpy_ty_zhcpjxjsq";
		String pjzq = zjkjService.getZhcpSqzq();//综合测评周期
		String[] sqzqzd = zjkjService.getPjzqzd(pjzq);
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue", "xh"}, 
													   sqzqzd,
				                                       new String[]{"xm", "xymc", "bjmc", "jxjmc", "xysh","xxsh" });
		String[] expColumn = StringUtils.joinStrArr(new String[] { "xh"}, 
						     sqzqzd,
		                     new String[]{"xm", "xydm","xymc","zydm","zymc","bjdm",
				                         "bjmc", "nj", "jxjdm","jxjmc",
				                         "fdysh", "fdyshyj", "xysh", "xyshyj", 
				                         "xxsh", "xxshyj"});
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//删除
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			outputColumn = new String[]{};
			this.expPageData(request, response, tableName, viewName, expColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "zhcpjxj");
		request.setAttribute("path", "zhcpjxjcx.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("pjzq", pjzq);//评奖周期
		FormModleCommon.setNdXnXqList(request);//学年年度学期列表
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zhcpjxjcx");
	}
	
	/**
	 * 综合测评奖学金维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpjxjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GuizhdxService service = new GuizhdxService();
		
		String tableName = "pjpy_ty_zhcpjxjsqb";
		String viewName = "view_pjpy_ty_zhcpjxjsq";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		
		//加载数据
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		String shjb = "2";//service.getShjb(rs.get("jxjdm"));
		
		if (null != rs) {
			//处分、成绩
			//request.setAttribute("cfxx", service.getStuCfxx(rs.get("xh"), rs.get("xn")));
			//request.setAttribute("cjxx", service.getStuCjxx(rs.get("xh"), rs.get("xn")));
			request.setAttribute("zcpm", service.getZcfPm(rs.get("xh"), rs.get("xn"),rs.get("xq")));
//			request.setAttribute("zcpm", service.getZcfPm(xh));
		}
		
		//修改
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			boolean result = false;
			HashMap<String, String> shMap = getShMap(request);
			String shjg = shMap.get("shjg");
			String shzd = shMap.get("shzd");
			
			if("通过".equalsIgnoreCase(shjg)){				
				//判断人数限制是否超出。
				PjpyZhcpjxjService zcjxjService = new PjpyZhcpjxjService();
				HashMap<String, String[]> map = new HashMap<String, String[]>();
				map.put("cbv", new String[]{request.getParameter("pkValue")});
				
				//奖学金审核
				String message = zcjxjService.auditingJxj(map,
						                shzd,
						                tableName);
				if(StringUtils.isNull(message)){
					message = "操作成功！";
					result = true;
				}
				request.setAttribute("result", result);
				request.setAttribute("message", message);
			}else{
				result = true;
			}
			if(result){
				this.updateOperation(request, tableName);
			}
		}
		
		service.setList(request, "zhcpjxj");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shjb", shjb);
		request.setAttribute("doType", doType);
		//request.setAttribute("path", "zhcpjxjsh.do");
		//FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zhcpjxjView");
	}
	
	/**
     * 综测奖学金上报
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zhcpjxjSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	PjpyZhcpjxjActionForm tybForm = (PjpyZhcpjxjActionForm)form;
    	PjpyZhcpjxjService service = new PjpyZhcpjxjService();
    	PjpyPjzqszService pjzqService = new PjpyPjzqszService();
    	PjpyZhszcpService zcService = new PjpyZhszcpService();
    	GuizhdxService gdService = new GuizhdxService();
    	XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
    	String zczq = pjzqService.getZczq();//获取综测周期
    	String go = request.getParameter("act");//查询标记
  
    	List<String[]> list = new ArrayList<String[]>();
    	List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
    	
    	User user = getUser(request);
         
        if (UserTypePd.isXy(user.getUserType())) {
        	tybForm.setQueryequals_xydm(user.getUserDep());
        }
        if("stu".equalsIgnoreCase(user.getUserType())){//学生用户不可访问
        	request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
        }
        //用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJJESZ)
				&& zjkjService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
        if (SB_TG.equalsIgnoreCase(request.getParameter("act"))) {
        	//上报通过
        	String jxjdm = request.getParameter("jxjdm");//上报的奖学金
        	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
    		String[] primaryKeys = {};
    		int i = 0;
    		for(String key : primaryMap.keySet()){
    			if(i++ == 0 ){
    				primaryKeys = primaryMap.get(key);
    			}
    		}
    		boolean result = service.zhcpjxjSb(primaryKeys,jxjdm,user);
    		request.setAttribute("result", result);
    		request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
        	go = "qry";
     	}
        if(SB_QX.equalsIgnoreCase(request.getParameter("act"))){
        	//取消上报
        	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
    		String[] primaryKeys = {};
    		int i = 0;
    		for(String key : primaryMap.keySet()){
    			if(i++ == 0 ){
    				primaryKeys = primaryMap.get(key);
    			}
    		}
    		boolean result = service.zhcpjxjQxsb(primaryKeys,user);
    		request.setAttribute("result", result);
    		request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
        	go = "qry";
        }
    	if (QUERY.equalsIgnoreCase(go)) {
    		HashMap<String, String> map = new HashMap<String, String>();
    		map.put("2", request.getParameter("queryequals_ejdm"));//二级	
    		String jb = tybForm.getQueryequals_jb();
    		
    		HashMap<String, String[]> colMap = service.getZhcpjxjsbTitle(zczq,
    				jb, map, true, tybForm);//获取导出的字段信息
    		String[] columnCN = colMap.get("cn");//表头

    		//查询导出的数据
    		list = service.queryZhcpjxjsb(zczq, jb, map, colMap.get("en"), tybForm, true);

    		topTr = zcService.queryZhszcpTitle(columnCN);
    	}
    	
    	//查询所有的综测代码信息
    	List<HashMap<String, String>> dmList = zcService.queryZhszcpdmList();
    	request.setAttribute("ejdmList", zcService.queryZhszcpdmList(dmList, "2"));//二级
    	
    	//加载学院，专业，班级，年级，学年，学期，年度列表
    	FormModleCommon.setNjXyZyBjListForFdyBzr(request);
    	FormModleCommon.setNdXnXqList(request);
    	
    	//设置综测周期
        request.setAttribute("pjzq", zczq);
        request.setAttribute("pjpyzq", pjzqService.getPjzq());//设置评奖周期
        appendOperQx(request, "zhcpjxjsb.do");
        //综测奖学金代码列表
        gdService.setList(request, "zhcpjxj");
        if(StringUtils.isNotNull(tybForm.getJxjdm())){
	        //奖学金条件       
	        request.setAttribute("tjList", service.getTjxxList(tybForm, GlobalsVariable.PJPY_ZHCPJXJ));
        }
        //设置默认的学年，学期，年度选项
        setDefaultValue(tybForm);
        appendTableXx(request, "view_pjpy_zhcpjxjsb", "pjpy_ty_zhcpjxjsqb");        
        appendQryResult(request, topTr, list);        
    	return mapping.findForward("zhcpjxjSb");
    }
    
    //设置默认的学年，学期，年度选项
	private void setDefaultValue(PjpyZhcpjxjActionForm tybForm) {
		if (StringUtils.isNull(tybForm.getQueryequals_xn())) {
        	tybForm.setQueryequals_xn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_nd())) {
        	tybForm.setQueryequals_nd(Base.getJxjsqnd());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_xq())) {
        	tybForm.setQueryequals_xq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getXn())) {
        	tybForm.setXn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getXq())) {
        	tybForm.setXq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getNd())) {
        	tybForm.setNd(Base.getJxjsqnd());
        }
	}
	
	public HashMap<String, String> getShMap(HttpServletRequest request){
		HashMap<String, String> map = new HashMap<String, String>();
		String shzd = "";//审核字段
		String shjg = "";//审核结果
		
		if(StringUtils.isNotNull(request.getParameter("save_fdysh"))){
			shzd = "fdysh";
			shjg = request.getParameter("save_fdysh");
		}else if(StringUtils.isNotNull(request.getParameter("save_xysh"))){
			shzd = "xysh";
			shjg = request.getParameter("save_xysh");
		}else if(StringUtils.isNotNull(request.getParameter("save_xxsh"))){
			shzd = "xxsh";
			shjg = request.getParameter("save_xxsh");
		}
		map.put("shzd", shzd);
		map.put("shjg", shjg);
		return map;
	}
}

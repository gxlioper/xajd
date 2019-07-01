package xgxt.jxgl.zgdd;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;



public class ZgddJxglAction extends DispatchAction {

	/**
	 * 设置国防生
	 * @throws Exception 
	 */
	public ActionForward setGfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		ZgddJxglUnit unit= new ZgddJxglUnit();
		GfsModel model = new GfsModel();

		String tableName = "view_zgdd_gfsb";
		String realTable = "zgdd_gfs";
		String title = "军训管理 - 国防生管理 - 国防生设置";
		
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;
		
		if (service.isGfb(userName)) {
			userType = "gfb";
		} 
		
		if (!"gfb".equalsIgnoreCase(userType)
				&& !"xx".equalsIgnoreCase(userType)
				&& !"admin".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "此功能只对国防办或学校开放！");
			return new ActionForward("/errMsg.do", false);
		}
		
		BeanUtils.copyProperties(model, myForm);
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {	
			
				result = service.saveGfs(model);
				
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				
				|| result) {
			String[] colList = new String[] {"xh","xm","xb","nj","bjmc","drzw","isgfs"};
			
			topTr = service.getTopTr(tableName, colList);
			rs = service.getXsList(model,colList);

			myForm.setXh(DealString.toGBK(model.getXh()));
			myForm.setXm(DealString.toGBK(model.getXm()));
		}
		
		request.setAttribute("path", "setGfs.do");
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		

		return mapping.findForward("setGfs");
	}

	/**
	 * 成绩管理
	 * @throws Exception 
	 */
	public ActionForward gfscjgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglUnit unit= new ZgddJxglUnit();
		GfsModel model = new GfsModel();
		String act = request.getParameter("act");
		BeanUtils.copyProperties(model, myForm);
		unit.setNjXyZyBjList(request, myForm);
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = service.queryTitle();
		String rsNum = "0";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		boolean fwqx = false;
		
		if("admin".equalsIgnoreCase(userType)){
			request.setAttribute("fwqx","yes");
		}else if(userType.equalsIgnoreCase("stu")){
			fwqx = service.isGfs(userName);
			myForm.setXh(userName);
			if(fwqx){
				request.setAttribute("fwqx","yes");
			}else{
				request.setAttribute("fwqx","no");
			}
		}else{
			fwqx = service.isGfb(userName);
			if(fwqx){
				request.setAttribute("fwqx","yes");
			}else{
				request.setAttribute("fwqx","no");
			}
		}
		if("go".equals(act)){			
			rs = service.getCjtj(myForm,model);
			rsNum = service.getCjtjfy(myForm, model);
		}
		int count = (StringUtils.isNull(rsNum))?0:Integer.parseInt(rsNum);
		myForm.getPages().setMaxRecord(count);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", count);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("gfscjtj");
	}
	/**
	 * 成绩管理导出
	 * @throws Exception 
	 */
	public ActionForward gfscjgldc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglUnit unit= new ZgddJxglUnit();
		GfsModel model = new GfsModel();
		BeanUtils.copyProperties(model, myForm);
		unit.setNjXyZyBjList(request, myForm);
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>> ();
		rs = service.getCjtjexp(myForm,model);
		String modelPath = servlet.getServletContext().getRealPath("")
		+ "/print/zgdzdx_cjtj.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.cjPrint(wwb,rs);
		
		return mapping.findForward("");
	}
	
	/**
	 * 奖学金管理
	 * @throws Exception 
	 */
	public ActionForward gfsjxjgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		ZgddJxglUnit unit= new ZgddJxglUnit();
		GfsModel myModel = new GfsModel();
		String tableName = "view_zgddgfsjxj";
		String realTable = "zgdd_gfsjxjb";
		ArrayList<String[]> rs =new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());

		boolean fwqx = false;
		if("admin".equalsIgnoreCase(userType)){
			request.setAttribute("fwqx","yes");
		}else if(userType.equalsIgnoreCase("stu")){
			fwqx = service.isGfs(userName);
			if(fwqx){
				myForm.setXh(userName);
				request.setAttribute("fwqx","yes");
			}else{
				request.setAttribute("fwqx","no");
			}
		}else{
			fwqx = service.isGfb(userName);
			if(fwqx){
				request.setAttribute("fwqx","yes");
			}else{
				request.setAttribute("fwqx","no");
			}
		}
		BeanUtils.copyProperties(myModel, myForm);
		
		if (request.getParameter("go") != null&&!request.getParameter("go").equalsIgnoreCase("")){
			rs = service.getJxjffList(myModel,tableName);
			topTr = service.getJxjffTopTr(tableName);
		}
		
		if (service.isGfb(userName)) {
			userType = "gfb";
		} else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			if(!service.isGfs(userName)){
				request.setAttribute("errMsg", "此功能只对国防生开放！");
				return new ActionForward("/errMsg.do", false);
			}
			String xh = userName;
			HashMap<String, String> stuMap = service.getStuXxForXh(xh);
//	        myForm.setNj(stuMap.get("nj"));
//			myForm.setXydm(stuMap.get("xydm"));
//			myForm.setZydm(stuMap.get("zydm"));
//			myForm.setBjdm(stuMap.get("bjdm"));
			myForm.setXh(stuMap.get("xh"));
			myForm.setXm(stuMap.get("xm"));
		}
		
		unit.setNjXyZyBjList(request, myForm);
		request.setAttribute("path","gfsjxjgl.do");
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);
		request.setAttribute("userType",userType);
		return mapping.findForward("JxjffManage");
	}	
	/**
	 * 奖学金管理单个查看
	 * @throws Exception 
	 */
	public ActionForward gfsjxjglUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String pk = DealString.toGBK(request.getParameter("pk"));
		ZgddJxglService service = new ZgddJxglService();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String userType = session.getAttribute("userType").toString();
		if (service.isGfb(userName)) {
			userType = "gfb";
		} 
		
		HashMap<String, String> rs = service.getGfsjxjglOne(pk,xh);
		ZgddJxglUnit unit= new ZgddJxglUnit();
		
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		unit.setNjXyZyBjList(request, myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjffUpdata");
	}
	
	/**
	 * 奖学金管理单个保存
	 * @throws Exception 
	 */
	public ActionForward gfsjxjglSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = DealString.toGBK(request.getParameter("pk"));
		ZgddJxglService service = new ZgddJxglService();
		GfsModel myModel = new GfsModel();
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update  = service.GfsjxjglUpdate(pk,myModel,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return mapping.findForward("jxjffUpdata");
	}
	
	/**
	 * 奖学金管理删除
	 * @throws Exception 
	 */
	public ActionForward gfsjxjglDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = DealString.toGBK(request.getParameter("pk"));
		ZgddJxglService service = new ZgddJxglService();
		boolean update  = service.GfsjxjglDelete(pk,request);
		if(update){
			request.setAttribute("update", "yes");
		}else{
			request.setAttribute("update", "no");
		}
		return gfsjxjgl(mapping,form,request,response);
	}
	
	/**
	 * 出勤管理
	 * @throws Exception 
	 */
	public ActionForward gfscqgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZgddJxglForm myForm = (ZgddJxglForm)form;
		ZgddJxglUnit util = new ZgddJxglUnit();
		ZgddJxglService service = new ZgddJxglService();
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userName = session.getAttribute("userName").toString();
		String userOnLine =  session.getAttribute("userOnLine").toString();
		String userDep = (String)session.getAttribute("userDep");

		if (service.isGfb(userName)) {
			userType = "gfb";
		} else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			if(!service.isGfs(userName)){
				request.setAttribute("errMsg", "此功能只对国防生开放！");
				return new ActionForward("/errMsg.do", false);
			}
			String xh = userName;
			HashMap<String, String> stuMap = service.getStuXxForXh(xh);
			myForm.setNj(stuMap.get("nj"));
//			myForm.setXydm(stuMap.get("xydm"));
//			myForm.setZydm(stuMap.get("zydm"));
//			myForm.setBjdm(stuMap.get("bjdm"));
			myForm.setXh(stuMap.get("xh"));
			myForm.setXm(stuMap.get("xm"));
		}
		ArrayList<HashMap<String, String>> topTr = null;
		ArrayList<String[]>  rs = null;
        
        CqglModel model = new CqglModel();
        if("go".equalsIgnoreCase(request.getParameter("go"))){
        	myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
        	myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
        	BeanUtils.copyProperties(model,myForm);
			topTr = service.dao_getSearchTitle();
			rs    = service.serv_cqqkDefault(model);
		}
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);
		
		util.setNjXyZyBjList(request, myForm);			
		request.setAttribute("userType",userType);
		request.setAttribute("realTable","zgdzdx_gfscqb");
//		读写权判断		 			
		request.setAttribute("writeAble",(Base.chkUPower(userName,"gfscqgl.do", userOnLine.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("gfscqgl");
	}
	/**
	 * 国防生出勤信息添加
	 * @throws Exception 
	 */
	public ActionForward gfscqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm)form;
        String xh = request.getParameter("xh");
        String doType = request.getParameter("doType");
        CqglModel model = new CqglModel();
        myForm.setCqqk(DealString.toGBK(myForm.getCqqk()));
        myForm.setBz(DealString.toGBK(myForm.getBz()));
        myForm.setZcqk(DealString.toGBK(myForm.getZcqk()));
        if("save".equalsIgnoreCase(doType)){
        	BeanUtils.copyProperties(model,myForm);
        	Boolean done = service.serv_gfscqAddSave(model);
        	request.setAttribute("done",done);
        }
		request.setAttribute("rs",service.serv_getXsInfo(xh));
        request.setAttribute("ldList",service.serv_getLdList());
        request.setAttribute("zcPfList",service.serv_getZcPfList());
		return mapping.findForward("gfscqAdd");
	}
	/**
	 * 国防生出勤信息修改
	 * @throws Exception 
	 */
	public ActionForward gfscqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm)form;
        String pkValue = request.getParameter("pkValue");
        String doType = request.getParameter("doType");
        CqglModel model = new CqglModel();
        HashMap<String,String> map = new HashMap<String, String>();
        myForm.setCqqk(DealString.toGBK(myForm.getCqqk()));
        myForm.setBz(DealString.toGBK(myForm.getBz()));
        myForm.setZcqk(DealString.toGBK(myForm.getZcqk()));
        if("save".equalsIgnoreCase(doType)){
        	BeanUtils.copyProperties(model,myForm);
        	Boolean done = service.serv_gfscqModiSave(model,pkValue);
        	request.setAttribute("done",done);
        	map = service.serv_getXsCqInfo(pkValue);
        	map.put("lddm",myForm.getLddm());
        	map.put("zcqk",myForm.getCqqk());
        	map.put("cqqk",myForm.getCqqk());
        	map.put("bz",myForm.getBz());
        }else{
        	 map = service.serv_getXsCqInfo(pkValue);
        }
       
		request.setAttribute("rs",map);		
        request.setAttribute("ldList",service.serv_getLdList());
        request.setAttribute("zcPfList",service.serv_getZcPfList());
        request.setAttribute("pkValue",pkValue);
        request.setAttribute("act",request.getParameter("act"));
		return mapping.findForward("gfscqModi");
	}
	/**
	 * 导出数据
	 */
	public ActionForward cqqkExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm)form;
		 CqglModel model = new CqglModel();
        myForm.setXh(DealString.toGBK(myForm.getXh()));
        myForm.setXm(DealString.toGBK(myForm.getXm()));		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		BeanUtils.copyProperties(model,myForm);
		service.serv_expData(model, wwb);
		return null;
	}
	/**
	 * 国防生出勤信息删除
	 * @throws Exception 
	 */
	public ActionForward gfscqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String delPk = request.getParameter("delPk");
		ZgddJxglService service = new ZgddJxglService();
		service.ser_gfscqDelete(delPk);
		return gfscqgl(mapping,form,request,response);
	}	
	/**
	 * 卫生管理
	 * @throws Exception 
	 */
	public ActionForward gfswsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");
		
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		ZgddJxglUnit unit= new ZgddJxglUnit();
		GfsModel model = new GfsModel();
		myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
		myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
		String tableName = "view_zgdd_gfswsb";
		String realTable = "zgdd_gfswsb";
		String title = "军训管理 - 国防生管理 - 卫生检查管理";
		
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;
		
		if (service.isGfb(userName)) {
			userType = "gfb";
		} else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			if(!service.isGfs(userName)){
				request.setAttribute("errMsg", "此功能只对国防生开放！");
				return new ActionForward("/errMsg.do", false);
			}
			String xh = userName;
			HashMap<String, String> stuMap = service.getStuXxForXh(xh);
			//myForm.setNj(stuMap.get("nj"));
			//myForm.setXydm(stuMap.get("xydm"));
			//myForm.setZydm(stuMap.get("zydm"));
			//myForm.setBjdm(stuMap.get("bjdm"));
			myForm.setXh(stuMap.get("xh"));
			myForm.setXm(stuMap.get("xm"));
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {	
			
				String[] key = myForm.getCheckVal();
				result = service.delWs(key);
				
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("print")) {	
			
			String xn = request.getParameter("xn");
			String xq = request.getParameter("xq");
			String zs = request.getParameter("zs");
			String kssj = request.getParameter("kssj");
			String jssj = request.getParameter("jssj");
			String xh = request.getParameter("xh");
			String nj = request.getParameter("nj");
			String xm = request.getParameter("xm");
			String xydm = request.getParameter("xydm");
			String zydm = request.getParameter("zydm");
			String bjdm = request.getParameter("bjdm");
			String lddm = request.getParameter("lddm");
			String wsqk = request.getParameter("wsqk");
			
			String modelPath = servlet.getServletContext().getRealPath("")
					+ "/print/zgdd_jxgl_wsgl.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printWstj(wwb, xn, xq, zs,xh,nj,xm,xydm,zydm,bjdm,lddm,wsqk,kssj,jssj);

			return mapping.findForward("");
		}
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				
				|| result) {
			String[] colList = new String[] {"xh","xm","xb","nj","xn","xqmc","bjmc","ldmc","zs","wsqk","xq"};
			
			rs = service.getWsList(model,colList);
			
			colList = new String[] {"xh","xm","xb","nj","xn","xqmc","bjmc","ldmc","zs","wsqk"};
			topTr = service.getTopTr(tableName, colList);

		}
		
		request.setAttribute("path", "gfswsgl.do");
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ldList",service.serv_getLdList());
        request.setAttribute("wsPfList",service.serv_getZcPfList());
        request.setAttribute("zsList", service.getZsList());
        
		return mapping.findForward("gfswsgl");
	}
	
	/**
	 * 卫生管理修改
	 * @throws Exception 
	 */
	public ActionForward gfswsglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ZgddJxglService service = new ZgddJxglService();
		ZgddJxglForm myForm = (ZgddJxglForm) form;
		GfsModel model = new GfsModel();

		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		boolean result = false;

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("add")) {
			rs.put("xh", "");
		}
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("stuInfo")) {
			rs = service.getWsListXx(xh);
		}
		if ((request.getParameter("doType") != null)
				&& (request.getParameter("doType").equalsIgnoreCase("edit") || request
						.getParameter("doType").equalsIgnoreCase("view"))) {
			String pk = DealString.toGBK(request.getParameter("pk")).replace(" ","");
			pk=pk.replace("第","").replace("周","");
			rs = service.getWsXx(pk);
		}
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {
			
			BeanUtils.copyProperties(model, myForm);
			
			result = service.saveWs(model, request);
			
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		rs.put("xn",Base.currXn);
		rs.put("xq",service.getXq(Base.currXq));
		
		request.setAttribute("rs", rs);
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("ldList",service.serv_getLdList());
        request.setAttribute("wsPfList",service.serv_getZcPfList());
        request.setAttribute("zsList", service.getZsList());
        
		return mapping.findForward("gfswsglUpdate");
	}
}


package xgxt.xsgygl.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDataInfo;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.shgz.model.common.CommonModel;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsgygl.hhgxy.GyglService;
import xgxt.xsgygl.util.XsgyglUtil;

public class XsgyglDispatch extends DispatchAction {	
	String userType = "";
	boolean isStu = true;
	String sUName = "";
	String power = "";
//	private int p = -1;
		@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO 自动生成方法存根
		HttpSession session = request.getSession();
//		/**判断用户读写权*/
//		writeAble = Base.getWriteAble(request);
//		String dxq = request.getParameter("writeAble");
//		if(!"".equalsIgnoreCase(dxq) && dxq != null){
//			writeAble = dxq;
//		}
		/** 在线检测 */
		int i = Base.chkTimeOut(session);
		if (i <= 2) {
			request.setAttribute("errMsg", "登陆超时，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		} 
//		request.setAttribute("writeAble", writeAble);			
		userType = request.getSession().getAttribute("userType").toString();   //得到用户类型
		isStu = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
		sUName = request.getSession().getAttribute("userName").toString();	//得到登录用户名
		return super.execute(mapping, form, request, response);
	}
	/**公寓维修申请*/
	public ActionForward gywxSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.utilGywxsq(request, form);		
		return mapping.findForward("gywxSq");
	}
	/**公寓维修申请审信息查询*/
	public ActionForward gywxSqShXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
		}  
		myUtil.gywxSqShXx(request, form);
		return mapping.findForward("gywxSqShXx");
	}
	/**公寓维修申请审核*/
	public ActionForward gywxSqSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.gywxSqSh(request, form);
		power = "XsgyglDispatch.do?method=gywxSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/
		return mapping.findForward("gywxSqSh");
	}
	/**住宿异动申请*/
	public ActionForward ssYdSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.ssYdSq(request, form);
		return mapping.findForward("ssYdSq");
	}
	/**住宿异动申请审核信息查询*/
	public ActionForward ssYdSqShXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
		}else{
		   myUtil.ssYdSqShxx(request, form,userType);		
		}
		return mapping.findForward("ssYdSqShXx");
	}
	/**住宿异动申请审核*/
	public ActionForward ssYdSqSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		//权限读取开始
		power = "XsgyglDispatch.do?method=ssYdSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/
		//权限读取结束
		myUtil.ssYdSqSh(request,form,userType);			
		return mapping.findForward("ssYdSqSh");
	}
	/**假期留校申请*/
	public ActionForward jqLxSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//XsgyglForm bean = (XsgyglForm)form;
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jqLxSq(request, form);
		return mapping.findForward("jqLxSq");
	}
	/**假期留校申请审核信息查询*/
	public ActionForward jqLxSqShXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
		} 
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jqLxSqShXx(request, form);
		return mapping.findForward("jqLxSqShXx");
	}
	public ActionForward jqLxCwFp(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
		} 
		DAO dao = DAO.getInstance();
		//String userOnline = request.getSession().getAttribute("userOnLine").toString();
		boolean res = dao.createCwxx();//验证床位信息是否正确
		if (res == false){
			request.setAttribute("errINFO","更新床位数据时出现错误！");
		}
		String xn = Base.currXn;
		String xq = Base.getDqxqmc();
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		XsgyglForm dataSearchForm = (XsgyglForm) form;
		GetDataInfo getDataIF   = new GetDataInfo();
		@SuppressWarnings("unused")
		GetDropDownList getData = new GetDropDownList();
		String xxdm =dao.getXxdm();
		String query = "";		
		String newMappingItems = DealString.toGBK(dataSearchForm.getConditionSqlValue());
		String oldMappingItems = DealString.toGBK(dataSearchForm.getOldCondiSqlValue());
		String doType = request.getParameter("doType");
		String xydm = dataSearchForm.getXydm();
		String zydm = dataSearchForm.getZydm();
//		String bjdm = dataSearchForm.getBjdm();
		String xqdm = dataSearchForm.getXqdm();
		String lddm = dataSearchForm.getLddm();		
		String nj = dataSearchForm.getNj();
		String cs = dataSearchForm.getCs();
		String xb = DealString.toGBK(dataSearchForm.getXb());
		dataSearchForm.setXb(xb);
//		String cwfp = DealString.toGBK(request.getParameter("cwfp"));
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
//		String userName = request.getSession().getAttribute("userName").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			dataSearchForm.setXydm(xydm);
		}
				
		if (doType != null && doType.equals("save")) {
			boolean doFlag    = false;
			String[] inserSql  = null;
			String[] updateSql = null;
			if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
				//删除语句拼接
				String delItems1[]     = oldMappingItems.split(",");
				int delNum             =  delItems1.length;
				String delItems2[][]   = new String[delNum][];
				updateSql                 = new String[delNum];
				for (int i = 0; i < delNum; i++) {
					delItems2[i] = delItems1[i].split("/");
					updateSql[i]    = "update gygl_jqlxxxb set ssbh='',cwh='' where xh='" + delItems2[i][0] +"'"; 
					doFlag = dao.runUpdateTab(updateSql[i]);
				}
			}
			if ((newMappingItems != null) && !newMappingItems.equals("")) {	
				//插入语句拼接
				String inserItems1[] = newMappingItems.split(",");
				int num = inserItems1.length;
				String inserItems2[][] = new String[num][];
				inserSql                 = new String[num];
				for(int i=0;i<num;i++){
					inserItems2[i] = inserItems1[i].split("/");
					inserSql[i]  = "update gygl_jqlxxxb set ssbh='" +inserItems2[i][1]+"' ,cwh='" +inserItems2[i][2]+"' where xh='" + inserItems2[i][0] +"'";
					doFlag = dao.runUpdateTab(inserSql[i]);
				}
			}
			if (doFlag == true) {
				request.setAttribute("doFlag", "ok");
				oldMappingItems = newMappingItems;
			} else {
				request.setAttribute("doFlag", "no");
			}
		}
		
		String[] tmp = getDataIF.getWfpcwzsData(xn, xq, nj, xydm);//未分配总数(人)
		if(tmp!=null){
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		}else{
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		//String[] tmpT = getDataIF.getCwYfpZsData(nj, xydm,bjdm);//已分配总数(人)
		String[] tmpT = getDataIF.getYfpcwzsData(xn, xq, nj, xydm);//已分配总数(人)
		if(tmpT!=null){
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);					
		}else{
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");			
		}
		request.setAttribute("oldMappingItems", oldMappingItems);
		query=(Base.isNull(xydm))?"%":"%"+xydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(zydm))?"%":"%"+zydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(nj))?"%":"%"+nj+"%";
		query+="!!-!!";		
		//request.setAttribute("bjList", getData.getBjList(query, userName,Fdypd.isFdy(userName)+""));//班级列表
		request.setAttribute("njList",Base.getNjList());// 年级列表
		request.setAttribute("xyList",Base.getXyList());//学院列表       
		request.setAttribute("xiaoquList", dao.getXiaoQuList());//校区列表
		request.setAttribute("ldList", getDataIF.getLdList(xqdm, xb));//楼栋列表
		request.setAttribute("csList", getDataIF.getLcList(lddm));//层数列表
		request.setAttribute("ssxxList",getDataIF.getWfpcwxxList(xqdm, lddm, xb, cs));//宿舍床位列表
		request.setAttribute("yfpCwList",getDataIF.getYfpqkxxList(xn, xq, nj, xydm, xb));//已分配学生床位列表
		request.setAttribute("xsList",getDataIF.getWfpxsxxList(xn, xq, nj, xydm, xb));//未分配学生列表
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("userType", userType);	
		return mapping.findForward("jqLxCwFp");
	}
	/**假期留校申请审核*/
	public ActionForward jqLxSqSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jqLxSqSh(request, form);
		power = "XsgyglDispatch.do?method=jqLxSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/
		return mapping.findForward("jqLxSqSh");
	}
	/**申请结果查询*/
	public ActionForward sqJgCx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.sqJgCx(request, form);
		return mapping.findForward("sqJgCx");
	}
	/**学生意见*/
	public ActionForward xsYjx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		if(isStu){//学生用户操作
			myUtil.pubYj(request,form,sUName);
			return mapping.findForward("xsYjx_Stu");	
		}else{//教师用户操作
			power = "XsgyglDispatch.do?method=xsYjx";
			request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/
			myUtil.YjXxQur(request,form,sUName);
			return mapping.findForward("xsYjx_Tch");	
		}	       
	}
	/**学生意见内容查看*/
	public ActionForward viewYjXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.viewYjXx(request, form,sUName);
		return mapping.findForward("viewYjXx");
	}
	/**学生意见回复*/
	public ActionForward yjHf(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.yjHf(request, form,sUName);
		return mapping.findForward("yjHf");
	}
	/**学生住宿历史数据查询*/
	public ActionForward xsGyGL_LsSj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
		}else if(userType.equalsIgnoreCase("xy")){
			XsgyglForm xsgyForm = (XsgyglForm)form;
			xsgyForm.setXydm((String)request.getSession().getAttribute("userDep"));
		}
		
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.xsGyGL_LsSj(request, form);
		return mapping.findForward("xsGyGL_LsSjQur");
	}
	/**单个学生住宿历史数据查看*/
	public ActionForward viewGyLsSj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.viewGyLsSj(request, form);
		return mapping.findForward("viewGyLsSj");
	}
	/**西北二民院房源库报表统计*/
	public ActionForward fykBbtj(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.fykBbtj(request, form,response);
		return mapping.findForward("success");
	}
	/**假期留校审批表*/
	public ActionForward jQlXsPb(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jQlXsPb(request, form);
		return mapping.findForward("jqlxspb");
	}
	/**假期留校审批表*/
	public ActionForward sSyDsPb(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.sSyDsPb(request, form);
		return mapping.findForward("ssydspb");
	}
	/**学生住宿信息批量删除
	 * @throws Exception */
	public ActionForward xsZsXxPlDelete(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.plDelete(request);
		return new ActionForward("/dorm_Using_Search.do?go=go&writeAble=yes", false);
	}
	/**学生住宿信息整体转移到住宿历史信息中
	 * @throws Exception */
	public ActionForward xsZsXxToHis(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.xsZsXxToHis(request);
		return new ActionForward("/dorm_Using_Search.do?go=go&writeAble=yes", false);
	}
	
	/**获取新生住宿信息到学工住宿信息库中
	 * @throws Exception */
	public ActionForward xssjTb(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		Boolean res = myUtil.xssjTb();
		if (res) {
			request.setAttribute("initOK", "ok");
		} else {
			request.setAttribute("initOK", "no");
		}
		return new ActionForward("/dorm_Using_Search.do?go=go&writeAble=yes", false);
	}
	
	/**学生走读（外住）申请默认页面*/
	public ActionForward xsZdSq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XsgyglForm xsgyForm = (XsgyglForm)form;
		String xn = xsgyForm.getXn();
		String xq = xsgyForm.getXq();
		HashMap<String,String> map = new HashMap<String,String>();
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		map = myUtil.getUserInfo(userType,sUName);//获取用户相关信息
		//默认学年学期
		if(Base.isNull(xn)){			
			xn = Base.currXn;
			xsgyForm.setXn(xn);
			map.put("xn",xn);
		}
		if(Base.isNull(xq)){
			xq = Base.currXq;
			xsgyForm.setXq(xq);
			map.put("xq",xq);
		}		
		
		String wzksrq = request.getParameter("wzksrq");
    	String jhwzsj = DealString.toGBK(request.getParameter("jhwzsj"));
    	String wzlxdm = DealString.toGBK(request.getParameter("wzlxdm"));
    	String wzdz   = DealString.toGBK(request.getParameter("wzdz"));
    	String wzyy   = DealString.toGBK(request.getParameter("wzyy"));
    	String jzsfty = DealString.toGBK(request.getParameter("jzsfty"));
    	String sqxn = DealString.toGBK(request.getParameter("xn"));
    	xn=Base.isNull(sqxn)?xn:sqxn;
    	
    	map.put("wzksrq", wzksrq);
    	map.put("jhwzsj", jhwzsj);
    	map.put("wzlxdm", wzlxdm);
    	map.put("wzksrq", wzksrq);
    	map.put("wzdz", wzdz);
    	map.put("wzyy", wzyy);
    	map.put("jzsfty", jzsfty);
    	map.put("xn", xn);
    	
		request.setAttribute("rs",map);
	    request.setAttribute("xnList",Base.getXnndList());//学年列表
	    request.setAttribute("xqList",Base.getXqList());//学期列表
	    request.setAttribute("userType",userType); //用户类型
	    request.setAttribute("wzlxList", gyglDao.getWzlxList());//外住类型列表
		return mapping.findForward("xszdsq");
	}
	/**学生走读（外住）申请信息保存
	 * @throws Exception */
	public ActionForward xsZdSqSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		boolean result     = myUtil.xsZdSqSave(request);
		request.setAttribute("result", result);
		return xsZdSq(mapping, form, request, response);//new ActionForward("/XsgyglDispatch.do?method=xsZdSq",false);
	}
	/**学生走读(外住)申请审核查询*/
	public ActionForward xsZdSqShXx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
		    return new ActionForward("/errMsg.do", false);
		} 
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		myUtil.xsZdSqShXx(request,xsgyForm);
		return mapping.findForward("xszdsqshxx");
	}
	/**学生走读(外住)申请审核
	 * @throws Exception */
	public ActionForward xsZdSqSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
	    myUtil.xsZdSqSh(request);	
	    //判断权限开始
		power = "XsgyglDispatch.do?method=xsZdSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/
		//判断权限结束
		return mapping.findForward("xszdsqsh");
	}
	
	/**假期留校信息打印
	 * @throws Exception */
	public ActionForward jqlxPrint(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
	    myUtil.jqlxPrint(request);	
	    //判断权限开始
		power = "XsgyglDispatch.do?method=jqlxPrint";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/
		//判断权限结束
		return mapping.findForward("jqlxPrint");
	}
	/**维修人员派遣单*/
	public ActionForward toWxRypq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XsgyglForm xsgyForm = (XsgyglForm)form;
		String pkValue = request.getParameter("pkValue");
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		request.setAttribute("rs",myUtil.getWxDxx(pkValue));
		request.setAttribute("xxmc",Base.xxmc);
		return mapping.findForward("toWxRypq");
	}
	/**年级入住时间*/
	public ActionForward setNjrzsj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		XsgyglForm xsgyForm = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String rzsj = request.getParameter("rzsj");
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		if("save".equals(doType)){
			request.setAttribute("result",
					myUtil.saveNjrzsj(xsgyForm.getNj(), rzsj));
		}
		if(!Base.isNull(xsgyForm.getNj())){
			ArrayList<String[]> list = myUtil.getNjrzsj(xsgyForm.getNj());
			if(list !=null && list.size() >0){
				request.setAttribute("rzsj",list.get(0)[1]);
			}else{
				request.setAttribute("rzsj","");
			}
		}else{
			request.setAttribute("rzsj","");
		}
		request.setAttribute("rs",myUtil.getNjrzsj(""));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("rsNum",
				myUtil.getNjrzsj("") !=null?myUtil.getNjrzsj("").size():0);
		return mapping.findForward("njrzsj");
	}
	
	/**
	 * 显示寝室评奖申请页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * */
	public ActionForward qspjsq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response){
		
		return mapping.findForward("qspjsqDispatch");
	}
	
	/**
	 * 月度卫生标兵寝室申请
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ydwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){//保存申请信息
			model.setSsbh(StringUtils.joinStr(model.getLddm(), "-", model.getQsh()));
			boolean result = service.saveYdwmqssqb(model,request);
			request.setAttribute("result", result);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setSqr(session.getAttribute("userName").toString());
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		request.setAttribute("yfList", service.getYdwmqssqYfList());
		return mapping.findForward("ydwmqssq");
	}
	
	/**
	 * 月度卫生标兵寝室申请信息修改
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward modiYdwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		if("save".equalsIgnoreCase(doType)){
			//保存申请信息
			model.setSqr(userName);
			boolean result = service.saveYdwmqssqb(model,request);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//获取主键
		request.setAttribute("rs", service.queryYdwmqssqb(model, userType, userName));
		return mapping.findForward("modiYdwmqssq");
	}
	
	/**
	 * 月度文明寝室申请结果查询页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ydwmqssqSearch(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		XsxxglService xsxxService = new XsxxglService();
		
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("stu".equalsIgnoreCase(userType)){//学生用户
			model.setSsbh(xsxxService.selectStuinfo(userName).get("ssbh"));
		}
		if("del".equalsIgnoreCase(doType)){//数据删除
			boolean result = service.delYdwmqssqb(model);
			request.setAttribute("result", result);
			go = "go";
		}
		if(!Base.isNull(go)){//查询数据
			rs = service.queryYdwmqssqxx(model);
			topTr = service.getYdwmqssqToptr();
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		FormModleCommon.commonRequestSet(request, "view_gygl_ydwmqssqb", 
				                         "gygl_ydwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("ydwmqssqSearch");
	}
	
	/**
	 * 月度文明寝室申请结果查询导出数据
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expYdwmqssqxx(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		XsgyglForm model = (XsgyglForm) form;
		GyglService service = new GyglService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setYf(request.getParameter("yf"));
		model.setLddm(request.getParameter("lddm"));
		model.setQsh(request.getParameter("qsh"));
		model.setSqsjks(request.getParameter("sqsjks"));
		model.setSqsjjs(request.getParameter("sqsjjs"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		model.setXxsh(request.getParameter("xxsh"));
		
		String[] colList = new String[] {"xn","xq","xqmc","yf","ssbh","sqsj",
				                         "sqr","bz","fdysh","fdyshyj","fdyshsj",
				                         "xysh","xyshyj","xyshsj","xxsh","xxshyj",
				                         "xxshsj","lddm","ldmc","cs","qsh"};
		String[] colListCN = service.getColCNList("view_gygl_ydwmqssqb",colList);
		//查询月度文明寝室申请结果（不分页）
		List<String[]> rs = service.queryYdwmqssqxxAll(model,colList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 月度文明寝室申请审核页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ydwmqssh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String shjb = "1";//审核级别
		
		model.setShjb(shjb);
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("audi".equalsIgnoreCase(doType)){//数据审核
			String shjg = request.getParameter("shjg");
			boolean result = service.audiYdwmqssqbBatch(model,shjg,userType,userName);
			request.setAttribute("result", result);
			go = "go";
		}
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		if(!Base.isNull(go)){//查询数据			
			rs = service.queryYdwmqssqshxx(model,userType,userName);
			topTr = service.getYdwmqssqshToptr(shjb,userType,userName);
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		FormModleCommon.commonRequestSet(request, "view_gygl_ydwmqssqb", 
				                         "gygl_ydwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("yhType", service.getYhlx(userType, userName, shjb));
		return mapping.findForward("ydwmqssqsh");
	}
	
	/**
	 * 月度卫生标兵寝室申请审核
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward audiYdwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getYdwmqsshjb();//审核级别
		
		if("save".equalsIgnoreCase(doType)){
			//保存申请信息
			model.setSqr(userName);
			boolean result = service.audiYdwmqssqb(model, request, userType, userName);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//获取主键
		request.setAttribute("yhType", service.getYhlx(userType, userName,shjb));//用户类型
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs", service.queryYdwmqssqb(model, userType, userName));
		return mapping.findForward("audiYdwmqssq");
	}
	
	/**
	 * 显示学期文明寝室申请页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xqwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){//保存申请信息
			model.setSsbh(StringUtils.joinStr(model.getLddm(), "-", model.getQsh()));
			boolean result = service.saveXqwmqssqb(model,request);
			request.setAttribute("result", result);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setSqr(session.getAttribute("userName").toString());
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		return mapping.findForward("xqwmqssq");
	}
	
	/**
	 * 学期文明寝室申请信息修改
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward modiXqwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		if("save".equalsIgnoreCase(doType)){
			//保存申请信息
			model.setSqr(userName);
			boolean result = service.saveXqwmqssqb(model,request);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//获取主键
		request.setAttribute("rs", service.queryXqwmqssqb(model,userType,userName));
		return mapping.findForward("modiXqwmqssq");
	}
	
	/**
	 * 学期文明寝室申请结果查询页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xqwmqssqSearch(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		XsxxglService xsxxService = new XsxxglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("stu".equalsIgnoreCase(userType)){//学生用户
			model.setSsbh(xsxxService.selectStuinfo(userName).get("ssbh"));
		}
		if("del".equalsIgnoreCase(doType)){//数据删除
			boolean result = service.delXqwmqssqb(model);
			request.setAttribute("result", result);
			go = "go";
		}
		if(!Base.isNull(go)){//查询数据
			rs = service.queryXqwmqssqxx(model);
			topTr = service.getXqwmqssqToptr();
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		FormModleCommon.commonRequestSet(request, "view_gygl_xqwmqssqb", 
				                         "gygl_xqwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));		
		return mapping.findForward("xqwmqssqSearch");
	}
	
	/**
	 * 学期文明寝室申请结果查询导出数据
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXqwmqssqxx(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		XsgyglForm model = (XsgyglForm) form;
		GyglService service = new GyglService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setLddm(request.getParameter("lddm"));
		model.setQsh(request.getParameter("qsh"));
		model.setSqsjks(request.getParameter("sqsjks"));
		model.setSqsjjs(request.getParameter("sqsjjs"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		model.setXxsh(request.getParameter("xxsh"));
		
		String[] colList = new String[] {"xn","xq","xqmc","ssbh","sqsj",
				                         "sqr","bz","fdysh","fdyshyj","fdyshsj",
				                         "xysh","xyshyj","xyshsj","xxsh","xxshyj",
				                         "xxshsj","lddm","ldmc","cs","qsh"};
		String[] colListCN = service.getColCNList("view_gygl_xqwmqssqb",colList);
		//查询月度文明寝室申请结果（不分页）
		List<String[]> rs = service.queryXqwmqssqxxAll(model,colList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 学期文明寝室申请审核页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xqwmqssh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String shjb = service.getXqwmqsshjb();
		
		model.setShjb(shjb);
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("audi".equalsIgnoreCase(doType)){//数据审核
			String shjg = request.getParameter("shjg");
			boolean result = service.audiXqwmqssqbBatch(model,shjg,userType,userName);
			request.setAttribute("result", result);
			go = "go";
		}
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		if(!Base.isNull(go)){//查询数据			
			rs = service.queryXqwmqssqshxx(model,userType,userName);
			topTr = service.getXqwmqssqshToptr(shjb,userType,userName);
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		FormModleCommon.commonRequestSet(request, "view_gygl_xqwmqssqb", 
				                         "gygl_xqwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("yhType", service.getYhlx(userType, userName,shjb));//用户类型
		return mapping.findForward("xqwmqssqsh");
	}
	
	/**
	 * 学期文明寝室申请审核
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward audiXqwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getXqwmqsshjb();
		
		model.setShjb(shjb);
		if("save".equalsIgnoreCase(doType)){
			//保存申请信息
			model.setSqr(userName);
			boolean result = service.audiXqwmqssqb(model,request,userType,userName);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//获取主键
		request.setAttribute("yhType", service.getYhlx(userType, userName,shjb));//用户类型
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs", service.queryXqwmqssqb(model,userType,userName));
		return mapping.findForward("audiXqwmqssq");
	}
	
	/**
	 * 显示学年卫生标兵寝室申请页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xnwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){//保存申请信息
			model.setSsbh(StringUtils.joinStr(model.getLddm(), "-", model.getQsh()));
			boolean result = service.saveXnwmqssqb(model,request);
			request.setAttribute("result", result);
		}else{
			model.setXn(Base.currXn);
			model.setSqr(session.getAttribute("userName").toString());
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		return mapping.findForward("xnwmqssq");
	}
	
	/**
	 * 显示寝室评奖申请结果查询页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * */
	public ActionForward qspjsqjgcx(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("qspjsqSearchDispatch");
	}
	
	/**
	 * 学年卫生标兵寝室申请信息修改
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward modiXnwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		if("save".equalsIgnoreCase(doType)){
			//保存申请信息
			model.setSqr(userName);
			boolean result = service.saveXnwmqssqb(model,request);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//获取主键
		request.setAttribute("rs", service.queryXnwmqssqb(model,userType,userName));
		return mapping.findForward("modiXnwmqssq");
	}
	
	/**
	 * 学年文明寝室申请结果查询页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xnwmqssqSearch(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		XsxxglService xsxxService = new XsxxglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("stu".equalsIgnoreCase(userType)){//学生用户
			model.setSsbh(xsxxService.selectStuinfo(userName).get("ssbh"));
		}
		if("del".equalsIgnoreCase(doType)){//数据删除
			boolean result = service.delXnwmqssqb(model);
			request.setAttribute("result", result);
			go = "go";
		}
		if(!Base.isNull(go)){//查询数据
			rs = service.queryXnwmqssqxx(model);
			topTr = service.getXnwmqssqToptr();
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		FormModleCommon.commonRequestSet(request, "view_gygl_xnwmqssqb", 
				                         "gygl_xnwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("xnwmqssqSearch");
	}
	
	/**
	 * 学年文明寝室申请结果查询导出数据
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXnwmqssqxx(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		XsgyglForm model = (XsgyglForm) form;
		GyglService service = new GyglService();
		model.setXn(request.getParameter("xn"));
		model.setLddm(request.getParameter("lddm"));
		model.setQsh(request.getParameter("qsh"));
		model.setSqsjks(request.getParameter("sqsjks"));
		model.setSqsjjs(request.getParameter("sqsjjs"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		model.setXxsh(request.getParameter("xxsh"));
		
		String[] colList = new String[] {"xn", "ssbh", "sqsj", "sqr", "bz", 
				                         "fdysh", "fdyshyj", "fdyshsj",
				                         "xysh", "xyshyj", "xyshsj", "xxsh",
				                         "xxshyj", "xxshsj", "lddm", "ldmc",
				                         "cs", "qsh"};
		String[] colListCN = service.getColCNList("view_gygl_xnwmqssqb",colList);
		//查询月度文明寝室申请结果（不分页）
		List<String[]> rs = service.queryXnwmqssqxxAll(model,colList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 学年文明寝室申请审核页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xnwmqssh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String shjb = service.getXnwmqsshjb();
		
		model.setShjb(shjb);
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("audi".equalsIgnoreCase(doType)){//数据审核
			String shjg = request.getParameter("shjg");
			//判断比例是否超过
			String scaleFlag = "";
			boolean result = false;
			if("通过".equalsIgnoreCase(shjg)){
				scaleFlag = service.checkWmqsbl(model);
			}
			if(Base.isNull(scaleFlag)){//比例未超过可继续审核
				result = service.audiXnwmqssqbBatch(model,shjg,userType,userName);
			}else{
				request.setAttribute("mes", scaleFlag);
			}
			request.setAttribute("result", result);
			go = "go";
		}
		
		model.setXn(Base.currXn);//只能审核当前学年数据
		if(!Base.isNull(go)){//查询数据			
			rs = service.queryXnwmqssqshxx(model,userType,userName);
			topTr = service.getXnwmqssqshToptr(shjb,userType,userName);
		}
		
		setXnndList(request);
		setGyxxList(request);//公寓信息中数据列表
		FormModleCommon.commonRequestSet(request, "view_gygl_xnwmqssqb", 
				                         "gygl_xnwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("yhType", service.getYhlx(userType, userName, shjb));
		return mapping.findForward("xnwmqssqsh");
	}
	
	/**
	 * 学年卫生标兵寝室申请审核
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward audiXnwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getXnwmqsshjb();
		String yhlx = service.getYhlx(userType, userName, shjb);//用户类型
		
		model.setShjb(shjb);
		model.setCbv(new String[]{request.getParameter("pk")});//获取主键
		if("save".equalsIgnoreCase(doType)){
			//保存申请信息
			model.setSqr(userName);
			//判断比例是否超过
			String scaleFlag = "";
			boolean result = false;
			
			//审核结果
			String shjg = "xy".equalsIgnoreCase(yhlx) ? model.getXysh() 
						  :model.getXxsh();
			if("通过".equalsIgnoreCase(shjg)){
				scaleFlag = service.checkWmqsbl(model);
			}
			if(Base.isNull(scaleFlag)){//比例未超过可继续审核
				result = service.audiXnwmqssqb(model,request,userType,userName);
			}else{
				request.setAttribute("mes", scaleFlag);
			}
			request.setAttribute("result", result);
		}
		
		request.setAttribute("yhType", yhlx);//用户类型
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs", service.queryXnwmqssqb(model,userType,userName));
		return mapping.findForward("audiXnwmqssq");
	}
	
	/**
	 * 寝室评奖申请审核页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward qspjsqsh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		return mapping.findForward("qspjsqshDispatch");
	}
		
	/**
	 * 设置学年年度列表数据
	 * @param HttpServletRequest request
	 * */
	private void setXnndList(HttpServletRequest request){		
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yfList", Base.getYfList());
	}
	
	/**
	 * 设置公寓信息列表数据
	 * @param HttpServletRequest request
	 * */
	private void setGyxxList(HttpServletRequest request){
		GyglService service = new GyglService();
		request.setAttribute("ldList", service.getList("ld"));//公寓楼栋
		request.setAttribute("qshList", service.getList("qsh"));//公寓寝号
	}
}

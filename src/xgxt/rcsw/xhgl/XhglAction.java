package xgxt.rcsw.xhgl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.szyqxy.zhszcp.PjpySzyqxyZhszcpDAO;
import xgxt.rcsw.xszgl.XszglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

public class XhglAction extends BasicAction {

	
//	 ====================================校徽发放 begin============================================
	/**
	 * 校徽发放管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		XszglService xszglService = new XszglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		
		// -----------取消发放-------------
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.qxxhffBatch(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}
		
		// -----------批量发放---------
		if("plff".equalsIgnoreCase(doType)){
			boolean flag=service.xhffBatch(myForm);
		
			message = flag ? MessageInfo.MESSAGE_EXTEND_SUCCESS
					: MessageInfo.MESSAGE_EXTEND_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// 显示列数
		String showNum = String.valueOf(service.getTopTr("xhff",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(Base.currNd);
		}
		
		rForm.setRsArrList((ArrayList<String[]>)service.queryXhgl(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============通用设置 end ============
		
		
		request.setAttribute("path", "xhgl.do?method=xhffManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("jbrList", xszglService.getJbrList());
		request.setAttribute("topTr", service.getTopTr("xhff",rForm));
		return mapping.findForward("xhffManage");
	}
	
	/**
	 * 校徽发放
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhffUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		XszglService xszglService = new XszglService();
		User user=getUser(request);
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);
		
		// =================学生证注册 begin==================
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================学生证注册 end==================
		
		request.setAttribute("path", "xhgl.do?method=xhffManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============学年、学期、学期名称 begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============学年、学期、学期名称 end====================
		request.setAttribute("jbrList", xszglService.getJbrList());
		request.setAttribute("rs", stuInfo);
		request.setAttribute("doType", "add");
		return mapping.findForward("xhffUpdate");
	}
	
	/**
	 * 校徽发放（查询、修改）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhffOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		XszglService xszglService = new XszglService();
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		
		String doType=request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		
		HashMap<String,String>rs=new HashMap<String,String>();
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setPkValue(pkValue);
			boolean flag = service.updateXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		
		// 学生证注册信息
		myForm.setPkValue(pkValue);
		rs=service.getXhglMap(myForm);
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============学年、学期、学期名称 begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("nd", rs.get("nd"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("jbrList", xszglService.getJbrList());
		// ============学年、学期、学期名称 end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType",doType);
		return mapping.findForward("xhffUpdate");
	}
	
	
	
//	 ====================================校徽发放 end============================================
	/**
	 * 校徽发放管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhbbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		
		// -----------取消发放-------------
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.qxxhffBatch(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
		// -----------批量发放---------
		if("plff".equalsIgnoreCase(doType)){
			boolean flag=service.xhffBatch(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// 显示列数
		String showNum = String.valueOf(service.getTopTr("xhff",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(Base.currNd);
		}
		
		rForm.setRsArrList((ArrayList<String[]>)service.queryXhgl(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============通用设置 end ============
		
		
		request.setAttribute("path", "xhgl.do?method=xhffManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("topTr", service.getTopTr("xhff",rForm));
		return mapping.findForward("xhbbManage");
	}
	
	/**
	 * 校徽发放
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhbbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		User user=getUser(request);
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);
		
		// =================学生证注册 begin==================
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================学生证注册 end==================
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============学年、学期、学期名称 begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============学年、学期、学期名称 end====================
		request.setAttribute("rs", stuInfo);
		request.setAttribute("doType", "add");
		return mapping.findForward("xhbbUpdate");
	}
	
	/**
	 * 校徽发放（查询、修改）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhbbOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
	
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		
		String doType=request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		
		HashMap<String,String>rs=new HashMap<String,String>();
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setPkValue(pkValue);
			boolean flag = service.updateXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		
		// 学生证注册信息
		myForm.setPkValue(pkValue);
		rs=service.getXhglMap(myForm);
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============学年、学期、学期名称 begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("nd", rs.get("nd"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		request.setAttribute("pkValue", pkValue);
		// ============学年、学期、学期名称 end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType",doType);
		return mapping.findForward("xhbbUpdate");
	}
	

//	 ====================================校徽补办 begin============================================
//	 ====================================校徽补办 end============================================
	
	
	
	
//	
//	
//	
//	/**
//	 * 火车优惠卡补办审核
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward yhkAuditing(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		HttpSession session = request.getSession();
//		String userName = (String) session.getAttribute("userName");
//		String userType = (String) session.getAttribute("userType");
//		String userDep = (String) session.getAttribute("userDep");
//		
//		HcyhkForm model = (HcyhkForm) form;
//		HcyhkService service = new HcyhkService();
//		RcswService rcswService = new RcswService();
//		String doType = request.getParameter("doType");
//		
//		String gnmc = AuditGnmc.HCYHKBB;
//		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
//		String[] yygw = AuditUtil.getGwmcByGnmcAndUser(gnmc,userName);
//		String[] topTr = new String[]{"pkValue","学号","姓名","年级",
//				Base.YXPZXY_KEY+"名称","专业名称","班级名称","申请时间"};
//		
//		//批量审核
//		if(PLSH.equals(doType)){
//			model.setShr(userName);
//			model.setShsj(GetTime.getNowTime2());
//			String[] pkValues = request.getParameterValues("primarykey_cbv");
//			request.setAttribute("message", service.plshHcyhkbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
//			
//			doType= QUERY;
//		}
//		
//		//取消审核
//		if(QXSH.equals(doType)){
//			model.setShjg("未审核");
//			model.setShsj("");
//			model.setShr("");
//			model.setShyj("");
//			
//			String[] pkValues = request.getParameterValues("primarykey_cbv");
//			request.setAttribute("message", service.plshHcyhkbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
//			
//			doType= QUERY;
//		}
//		
//		//查询
//		if(QUERY.equals(doType)){
//			if (StringUtils.isNull(model.getShgw())){
//				try{
//					model.setShgw(yygw[0]);
//				}catch(RuntimeException re){
//					catchSystemException(request,new SystemException("Error-1023"));
//				}
//			}
//			
//			User user = service.getUser(request);
//			String[] colList = new String[]{"disabled","isdis","pkValue","xh",
//								"xm","nj","xymc","zymc","bjmc","sqsj"};
//			HashMap<String,Object> map = service.queryHcyhkbbsh(user,model, colList,topTr,gwmc);
//			request.setAttribute("rs", map.get("result"));
//			topTr = (String[]) map.get("topTr");
//		}
//		
//		if ("xy".equals(userType)){
//			model.setXydm(userDep);
//		} else if("stu".equals(userType)){
//			request.setAttribute("message", "对不起，您无权访问此页");
//			return new ActionForward("/prompt.do",false);
//		}
//		
//		request.setAttribute("shjgList", rcswService.getSelectList("shjg"));
//		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
//		request.setAttribute("yygw", yygw);//拥有岗位
//		request.setAttribute("topTr", topTr);
//		request.setAttribute("maxNum", model.getPages().getPageSize());
//		request.setAttribute("path", "hcyhk.do?method=yhkAuditing");
//		FormModleCommon.commonRequestSet(request);
//		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		return mapping.findForward("yhkAuditing");
//	}
//	
//	
//	
//	/**
//	 * 火车优惠卡补办单个审核
//	 * @return ActionForward
//	 */
//	@SuppressWarnings("unchecked")
//	public ActionForward hcyhkDgsh(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		HcyhkForm model = (HcyhkForm) form;
//		HcyhkService service = new HcyhkService();
//		RcswService rcswService = new RcswService();
//		XsxxglService xsxxglService = new XsxxglService();
//		String gnmc =  AuditGnmc.HCYHKBB;
//		String tableName = "xg_rcsw_hcyhkbb";
//		String pkValue = request.getParameter("pkValue");
//		String doType = request.getParameter("doType");
//		
//		if (SAVE.equals(doType)){//单个审核保存
//			request.setAttribute("message", service.plshHcyhkbb(model, new String[]{pkValue}, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
//		}
//		
//		//加载单条记录信息
//		selectPageDataByOne(request, tableName, tableName, pkValue);
//		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
//		//加载学生信息
//		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
//		request.setAttribute("rs", rs);
//		//审核信息
//		List<HashMap<String,String>> shxx = service.getHcyhkbbShxx(pkValue, gnmc);
//		
//		request.setAttribute("shxx", shxx);
//		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
//		FormModleCommon.commonRequestSet(request);
//		//补发类型
//		request.setAttribute("shsj", GetTime.getNowTime2());//审核时间
//		request.setAttribute("doType", doType);
//		return mapping.findForward("hcyhkDgsh");
//	}
//	
//	
//	
//	/**
//	 * 火车优惠卡补办修改
//	 * @return ActionForward
//	 */
//	@SuppressWarnings("unchecked")
//	public ActionForward hcyhkbbUpdate(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		XsxxglService xsxxglService = new XsxxglService();
//		
//		String tableName = "xg_rcsw_hcyhkbb";
//		String doType = request.getParameter("doType");
//		
//		if(SAVE.equalsIgnoreCase(doType)){
//			updateOperation(request, tableName);
//		}
//		
//		String pkValue = request.getParameter("pkValue");			
//		selectPageDataByOne(request, tableName, tableName, pkValue);
//		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
//		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
//		
//		request.setAttribute("rs", rs);
//		return mapping.findForward("hcyhkbbUpdate");
//	}
}

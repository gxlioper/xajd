package xgxt.pjpy.zjcm.rych;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.pjpy.zjcm.PjpyZjcmUnit;
import xgxt.pjpy.zjcm.xnjxj.XnjxjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.zjcm.WjcfZjcmService;

public class RychAction extends DispatchAction {
	/**
	 * 荣誉称号申请(学生)
	 * @throws Exception 
	 */
	public ActionForward rychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RychService service = new RychService();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String xh  = request.getParameter("xh");
		String doType = request.getParameter("doType");	
		HashMap<String,String> map = new HashMap<String, String>();
		HashMap<String,String> stuMap =  new HashMap<String, String>();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		RychModel   model   = new RychModel();
		if("student".equalsIgnoreCase(userOnline)){
			xh = userName;
		}
		stuMap = service.serv_getXsInfo(xh);//获取学生相关基本信息
		myForm.setBz(DealString.toGBK(myForm.getBz()));//
		myForm.setZysj(DealString.toGBK(myForm.getZysj()));
		String xn   = Base.getJxjsqxn();	
		String xq   = Base.getJxjsqxq();	

		map.put("xn",xn);//奖学金申请学年
		map.put("xq",Base.getJxjsqxq());//奖学金申请学期
		map.put("bjrs",CommonQueryDAO.getBjrs(xh));//班级人数
		
		if("save".equalsIgnoreCase(doType)){//数据保存
			BeanUtils.copyProperties(model, myForm);
			HashMap<String,String> isExist =service.serv_rychSqPd(model); 
			if(isExist.size()==0||isExist==null){//判断该记录是否存在且审核通过
				//判断该生是否满足申请条件
				String pm = request.getParameter("pm").trim();
				String msg = service.Rychtj(model, pm);
				
				if(msg.equalsIgnoreCase("")){
					boolean done = false;
					done = service.serv_rychSave(model);
					request.setAttribute("done",done);
				}else{
					request.setAttribute("message",msg);
					request.setAttribute("pass","no");
				}								
			}else{
				request.setAttribute("isExist","no");
			}
		}
		request.setAttribute("rychList",service.serv_getRychList());//荣誉称号列表
		request.setAttribute("rsOther",map);
		request.setAttribute("rs",stuMap);
		request.setAttribute("rsCj",service.getCjPm(xh, xn,xq));//成绩排名map;
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("sqManage");
	}
	
	/**
	 * 荣誉称号审核管理
	 * 
	 * @throws Exception
	 */
	public ActionForward shManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String isFdy = session.getAttribute("fdyQx").toString();

		RychService service = new RychService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit util = new PjpyZjcmUnit();
		RychModel model = new RychModel();

		String tableName = "view_zjcm_rychsq";
		String realTable = "zjcm_rychsqb";
		String title = "评奖评优 - 荣誉称号 - 荣誉称号审核";
		String doType = request.getParameter("go");
		//String shzt = request.getParameter("shzt");

		String[] checkVal = myForm.getCheckVal();

		boolean canWrite = false;
		
		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		myForm.setNd(Base.getJxjsqnd());
		String cpfw = "";
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			
			if (StringUtils.isNotNull(myForm.getRychdm())) {
				BeanUtils.copyProperties(model, myForm);
				model.setXqdm(myForm.getXq());
				//查询学院荣誉称号的参评范围
				cpfw = service.queryRychCpfwByXydm(model);
				request.setAttribute("cpfw", cpfw);
				//如果是该学院同一荣誉称号出现多个参评范围,则予以提示
				request.setAttribute("dis", "2".equalsIgnoreCase(cpfw) ? "disabled='disabled'" : "");
			}
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
//				String shRych=request.getParameter("rychdm");
//				result = service.saveRychsh(checkVal, shRych, shzt, userType);
				result = service.updateRychResult(checkVal, request.getParameter("lb"), userType, isFdy);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			//String[] colList = new String[]{};
//			if ("xy".equalsIgnoreCase(userType)) {
//				colList = new String[] { "pk", "xh", "xm", "xb", "bjmc", "xn",
//						"xqmc", "rychmc","xysh" };
//			}else{
//				colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
//						"xn", "xqmc", "rychmc", "xysh", "xxsh" };
//			}

			topTr = service.queryRychshTitle(userType, isFdy);
			rs = (ArrayList<String[]>)service.queryRychshResult(model, userType, isFdy, userName);
//			myForm.setXh(DealString.toGBK(myForm.getXh()));
//			myForm.setXm(DealString.toGBK(myForm.getXm()));
			//荣誉称号获奖名额
			request.setAttribute("jxjrs", service.queryRychHjrs(model, cpfw));
			if ("xy".equalsIgnoreCase(userType) && 
					StringUtils.isNotNull(myForm.getRychdm()) && 
						StringUtils.isNotNull(cpfw)) {
				//部门通过人数
				request.setAttribute("hdrs", service.queryRychTgrs(model, cpfw, isFdy));
				//是否在页面显示提示信息
				request.setAttribute("view", "yes");
			}
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "credit_check.do");
		request.setAttribute("title", title);
		request.setAttribute("rychList", service.serv_getRychList());
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		util.setNjXyZyBjList(request, myForm);
		FormModleCommon.setNdXnXqList(request);
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		myForm.setXysh(DealString.toGBK(myForm.getXysh()));
		myForm.setXqdm(Base.getJxjsqxq());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		return mapping.findForward("shManage");
	}
	
	/**
	 * 荣誉称号单个审核
	 * @throws Exception 
	 */
	public ActionForward rychshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		RychService service = new RychService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		RychModel model = new RychModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "评奖评优 - 荣誉称号 - 荣誉称号审核";
		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk").trim();
		String jg = request.getParameter("jg");
		String userType = session.getAttribute("userType").toString();
		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		
		BeanUtils.copyProperties(model, myForm);
		
		if ("save".equals(doType)) {
			String shzt = request.getParameter("shzt");
			boolean result = service.saveRychsh(model, pk, shzt, userType,
					request);
			request.setAttribute("result", result);
		}
		map = service.getXsRychXx(pk);
		map.put("bjrs",CommonQueryDAO.getBjrs(map.get("xh")));//班级人数
		request.setAttribute("rsCj",service.getCjPm(map.get("xh"), map.get("xn"),map.get("xqdm")));//成绩排名map;
		List<HashMap<String, String>> wjcfList = service.getWjcfList(map
				.get("xh"));
		request.setAttribute("wjcfList", wjcfList);
		if (wjcfList != null && wjcfList.size() > 0) {
			request.setAttribute("cfqk", wjcfList.size());
		}
		request.setAttribute("title", title);
		request.setAttribute("pk", pk);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", map);
		if (!Base.isNull(jg) && "yes".equalsIgnoreCase(jg)) {
			title = "评奖评优 - 荣誉称号 - 荣誉称号申请结果查看";
			userType = "admin";
			request.setAttribute("jg", jg);
			request.setAttribute("title", title);
			return mapping.findForward("jgOne");
		}
		return mapping.findForward("shOne");
	}
	
	/**
	 * 荣誉称号申请结果
	 * 
	 * @throws Exception
	 */
	public ActionForward sqjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		RychService service = new RychService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit util = new PjpyZjcmUnit();
		RychModel model = new RychModel();

		String tableName = "view_zjcm_rychsq";
		String realTable = "zjcm_rychsqb";
		String title = "评奖评优 - 荣誉称号 - 荣誉称号申请结果";
		String doType = request.getParameter("doType");

		String[] checkVal = myForm.getCheckVal();

		boolean canWrite = false;
		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}

		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delRych(checkVal);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[]{};
			if ("xy".equalsIgnoreCase(userType)) {
				colList = new String[] { "pk", "xh", "xm", "xb", "bjmc", "xn",
						"xqmc", "rychmc","xysh", "xxsh"  };
			}else{
				colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
						"xn", "xqmc", "rychmc", "xysh", "xxsh" };
			}

			topTr = SearchUtils.getTopTr(tableName, colList,null);
			rs = service.getRychSqList(tableName, model, colList, userType, "");
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "credit_result.do");
		request.setAttribute("title", title);
		request.setAttribute("rychList", service.serv_getRychList());
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		util.setNjXyZyBjList(request, myForm);
		FormModleCommon.setNdXnXqList(request);
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		myForm.setXysh(DealString.toGBK(myForm.getXysh()));
		myForm.setXqdm(Base.getJxjsqxq());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		return mapping.findForward("jgManage");
	}
	
	/**
	 * 荣誉称号等记表
	 * 
	 * @throws Exception
	 */
	public ActionForward rychDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		RychService service = new RychService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if(pk!=null&&!pk.equalsIgnoreCase("")){
			rs = service.getXsRychXx(pk);
			rs.put("xmmc", rs.get("rychmc"));
		}else{
			String xmdm = request.getParameter("rychdm");
			rs.put("xmmc",service.getRychmc(xmdm));
		}
		if(rs.get("xh")==null||rs.get("xh").equalsIgnoreCase("")){
			rs.put("xh",xh);
		}
		rs = service.getPrintXx(rs);
		request.setAttribute("rs", rs);
		return mapping.findForward("rychDj");
	}
	
	/**
	 * 荣誉称号单个审核显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		RychService service = new RychService();
		
		//保存数据操作
		if ("save".equalsIgnoreCase(request.getParameter("go"))) {
			RychModel model = new RychModel();
			BeanUtils.copyProperties(model, myForm);
			request.setAttribute("result", service.updateRychshResult(userType, isFdy, model, pkValue));
		} else if ("view".equalsIgnoreCase(request.getParameter("go"))) {
			request.setAttribute("writa", "view");
		}
		//查询显示单个详细信息
		if (StringUtils.isNotNull(pkValue)) {
			rs = service.queryRychDetails(pkValue, userType, isFdy);
		}
		if (!rs.isEmpty()) {
			myForm.setSh(rs.get("sh"));
			myForm.setYj(rs.get("yj"));
			WjcfZjcmService myService = new WjcfZjcmService();
			request.setAttribute("cfList", myService.queryStuCfxx(rs.get("xh")));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.getShList());
		request.setAttribute("pkValue", pkValue);
		setWritable(rs, isFdy, userType, request);
		request.setAttribute("cpfw", request.getParameter("cpfw"));
		return mapping.findForward("rychDetails");
	}
	
	public void setWritable(HashMap<String, String> rs, String isFdy,
			String userType, HttpServletRequest request) {
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				if ("通过".equalsIgnoreCase(rs.get("xysh"))
						|| "通过".equalsIgnoreCase(rs.get("xxsh"))) {
					request.setAttribute("dis", "disabled='disabled'");
				}
			} else {
				if ("通过".equalsIgnoreCase(rs.get("xxsh"))) {
					request.setAttribute("dis", "disabled='disabled'");
				}
			}
		}
	}
}

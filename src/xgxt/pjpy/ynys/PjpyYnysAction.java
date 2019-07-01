
package xgxt.pjpy.ynys;

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

import common.Globals;
import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public class PjpyYnysAction extends CommonAction {

	private String xydm = "";
	private  String zydm = "";
	private String nj = "";
	
	/**
	 * 先进班级申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xxmc = session.getAttribute("xxmc").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjService service = new XjbjService();
		HashMap<String, String> rsMap =service.getJxjsqsj();
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("tit", xxmc + "先进班集体登记表");//表头
		request.setAttribute("xn", rsMap.get("jxjsqxn"));//奖学金申请学年
		return mapping.findForward("xjbjsq");
	}

	/**
	 * 先进班级保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjModel model = new XjbjModel();//先进班级MODEL
		BeanUtils.copyProperties(model, ynysForm);
		XjbjService service = new XjbjService();
		HashMap<String, String> rsMap =service.getJxjsqsj();
		boolean bFlag = service.saveYnys_xjbjb(model, request);//保存信息
		if (bFlag) {
			request.setAttribute("inserted", "yes");//保存成功
		} else {
			request.setAttribute("inserted", "no");//保存失败
		}
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("tit", "云南艺术学院先进班集体登记表");//表头
		request.setAttribute("xn", rsMap.get("jxjsqxn"));//奖学金申请学年
		ynysForm.setBzr(DealString.toGBK(ynysForm.getBzr()));
		ynysForm.setBzryj(DealString.toGBK(ynysForm.getBzryj()));
		ynysForm.setBjdbqk(DealString.toGBK(ynysForm.getBjdbqk()));
		ynysForm.setFdyyj(DealString.toGBK(ynysForm.getFdyyj()));
		return mapping.findForward("xjbjsq");
	}
	
	/**
	 * 先进班级报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printXjbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		/*String bjdm = request.getParameter("bjdm");
		XjbjService service = new XjbjService();
		String modelPath = "";//数据表格模板
		modelPath = servlet.getServletContext().getRealPath("")+"/print/ynys/ynys_xjbjb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printXjbj(wwb, bjdm);//先进班级打印
		 */
		HttpSession session = request.getSession();
		String xxmc = session.getAttribute("xxmc").toString();
		String pkValue = request.getParameter("pkValue");
		XjbjService service = new XjbjService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.printXjbj(pkValue);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("tit", xxmc + "先进班集体登记表");
		return mapping.findForward("xjbjprint");
	}
	
	/**
	 * 先进班级审核默认页面分二级审核:院系,学校
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("先进班级", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("优秀毕业生", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		XjbjService service = new XjbjService();
		List<HashMap<String, String>> shxmList = service.getShxmList();//审核项目列表
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("shList", shxmList);
		ynysForm.setXn(service.getJxjsqsj().get("jxjsqxn"));
		return mapping.findForward("xjbjsh");
	}
	
	/**
	 * 先进班级审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjqry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXn(request.getParameter("xn"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("先进班级", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("优秀毕业生", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		XjbjModel model = new XjbjModel();//先进班级MODEL
		BeanUtils.copyProperties(model, ynysForm);
		XjbjService service = new XjbjService();
		List<HashMap<String, String>> shxmList = service.getShxmList();//审核项目列表
		List<HashMap<String, String>> titList = service.getXjbjTitle(userType, realTable);//先进班级表头
		List<String[]> rsList = service.getXjbjList(model, userType, realTable);//先进班级查询结果
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("shList", shxmList);
		appendQryResult(request, titList, rsList);//加载查询表头,结果,记录数
		ynysForm.setXn(service.getJxjsqsj().get("jxjsqxn"));
		return mapping.findForward("xjbjsh");
	}
	
	/**
	 * 先进班级审核结果二级审核(院系,学校)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjShres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		XjbjService service = new XjbjService();
		String tgRes = request.getParameter("param1");//审核标记
		ynysForm.setXn(request.getParameter("xn"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("先进班级", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("优秀毕业生", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		String[] cbv = ynysForm.getCbv();//审核主键列表
		tgRes = service.getShjg(tgRes);
		String sShresult = service.xjbjShResult(cbv, userType, tgRes, request, realTable);//审核结果
		if (StringUtils.isNull(sShresult)) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
			request.setAttribute("failinfo", "第" + sShresult + "条记录审核失败!");
		}
		List<HashMap<String, String>> shxmList = service.getShxmList();//审核项目列表
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("shList", shxmList);
		ynysForm.setXn(service.getJxjsqsj().get("jxjsqxn"));
		return mapping.findForward("xjbjsh");
	}
	
	/**
	 * 先进班级单个审核显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjShone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		String realTable = request.getParameter("realTable");
		HashMap<String, String> rsMap = service.viewXjbjshOne(pkValue, userType, realTable);//显示信息
		if (!StringUtils.isNull(rsMap.get("yesno"))) {
			ynysForm.setYesNo(rsMap.get("yesno"));
		}
		ynysForm.setYj(rsMap.get("yj"));
		ynysForm.setXyyj(rsMap.get("xyyj"));
		ynysForm.setJytyj(rsMap.get("jytyj"));
		request.setAttribute("shList", service.getShList());//审核列表
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rsMap);
		request.setAttribute("pkValue", pkValue);
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual("ynys_yxbysb", realTable)) {
			return mapping.findForward("yxbysshone");
		}
		return mapping.findForward("xjbjshone");
	}
	
	/**
	 * 先进班级单个审核结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjShoneres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.saveXjbjshone(pkValue, userType, ynysForm
				.getYesNo(), ynysForm.getYj(), ynysForm.getXyyj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String realTable = "";
		if (!StringUtils.isNull(shxm)) {
			if (StringUtils.isEqual("先进班级", shxm)) {
				realTable = "ynys_xjbjb";
			} else if (StringUtils.isEqual("优秀毕业生", shxm)) {
				realTable = "ynys_yxbysb";
			} else {
				realTable = "";
			}
			ynysForm.setShxm(shxm);
		}
		request.setAttribute("realTable", realTable);
		request.setAttribute("shList", service.getShList());//审核列表
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("userType", userType);
		ynysForm.setYj("");
		ynysForm.setXyyj("");
		
		return mapping.findForward("xjbjshone");
	}
	
	/**
	 * 优秀毕业生单个审核结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysShoneres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.saveYxbysshone(pkValue, userType, ynysForm
				.getYesNo(), ynysForm.getYj(), ynysForm.getJytyj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("shList", service.getShList());//审核列表
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("userType", userType);
		ynysForm.setYj("");
		ynysForm.setJytyj("");
		return mapping.findForward("yxbysshone");
	}
	
	/**
	 * 先进班级申请结果查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
//		学生用户无权进入该页面
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)) {
			String msg = "您无权访问该页面，请确认！";
		    request.setAttribute("yhInfo", msg);
		    return new ActionForward("/yhInfo.do", false);
		}
		
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//浙江理工
			return new ActionForward("/zjlgPjpy.do?method=xjbjDefault",false);
		}
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){ //TODO 宁波天一职业技术学院			
			return new ActionForward("/nbtyWmbj.do?method=nbtyResultWmbj",false);
		}
		if(Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)){//杭州职业技术学院			
			return new ActionForward("/pjpy_hzy_xjch.do?method=xjbjsqQry",false);
		}
		if (StringUtils.isEqual("xy", userType)) {//学院用户
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		
		if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			appendTableXx(request, "view_ynys_xjbj", "ynys_xjbjb");//表名,视图名
		} else {
			appendTableXx(request, "view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");//表名,视图名
		}
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		return mapping.findForward("xjbjsqqry");
	}
	
	/**
	 * 先进班级申请结果查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSqqryres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		XjbjService service = new XjbjService();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {//学院用户
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjModel model = new XjbjModel();
		BeanUtils.copyProperties(model, ynysForm);
		List<HashMap<String, String>> titList = service.getXjbjQryTitle(xxdm);//查询表头
		List<String[]> rsList = service.getXjbjQryResult(model, xxdm);
		appendQryResult(request, titList, rsList);//存放列表
		if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			appendTableXx(request, "view_ynys_xjbj", "ynys_xjbjb");//表名,视图名
		} else {
			appendTableXx(request, "view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");//表名,视图名
		}
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		return mapping.findForward("xjbjsqqry");
	}
	
	/**
	 * 先进班级修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String xxdm = StandardOperation.getXxdm();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		XjbjService service = new XjbjService();
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			rsMap = service.nbzyXjbjxx(pkValue);
		} else if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			rsMap = service.viewXjbjxx(pkValue);// 显示信息zjjdXjbjxx
		} else if (Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(xxdm)) {
			rsMap = service.zjjdXjbjxx(pkValue);// 显示信息
		} else {
			rsMap = service.nbzyXjbjxx(pkValue);
		}
		if (rsMap != null) {
			ynysForm.setBjdbqk(rsMap.get("bjdbqk"));
			ynysForm.setBjrs(rsMap.get("bjrs"));
			ynysForm.setBzr(rsMap.get("bzr"));
			ynysForm.setBzryj(rsMap.get("bzryj"));
			ynysForm.setFdyyj(rsMap.get("fdyyj"));
			ynysForm.setBhgqs(rsMap.get("bhgqs"));
			ynysForm.setBjry(rsMap.get("bjry"));
			ynysForm.setYwcf(rsMap.get("ywcf"));
			ynysForm.setTzs(rsMap.get("tzs"));
			ynysForm.setZysj(rsMap.get("zysj"));
			ynysForm.setXn(rsMap.get("xn"));
			ynysForm.setXsrs(rsMap.get("xsrs"));
			ynysForm.setBzxm(rsMap.get("bzxm"));
			ynysForm.setBz(rsMap.get("bz"));
			ynysForm.setJtch(rsMap.get("jtch"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rsMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xjbjmodi");
	}
	
	/**
	 * 先进班修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		XjbjService service = new XjbjService();
		String pkValue = request.getParameter("pkValue");
		XjbjModel model = new XjbjModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.saveXjbjxx(pkValue, model, request);//保存修改信息
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		} else {//失败
			request.setAttribute("inserted", "no");
		}
		ynysForm.setBzr("");
		ynysForm.setBjdbqk("");
		ynysForm.setBjrs("");
		ynysForm.setBzryj("");
		ynysForm.setFdyyj("");
		ynysForm.setZysj("");
		ynysForm.setBjry("");
		ynysForm.setBzxm("");
		ynysForm.setBzr("");
		ynysForm.setTzs("");
		ynysForm.setBz("");
		ynysForm.setJtch("");
		request.setAttribute("rs", new HashMap<String, String>());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xjbjmodi");
	}
	
	/**
	 * 先进班级删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {//学院用户
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		XjbjService service = new XjbjService();
		String sJg = service.delXjbjxx(ynysForm.getCbv(), request);//删除信息
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "第" + sJg + "条记录删除失败");
		}
		if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			appendTableXx(request, "view_ynys_xjbj", "ynys_xjbjb");//表名,视图名
		} else {
			appendTableXx(request, "view_pjpy_xjbjandwmsq", "pjpy_xjbjandwmsqb");//表名,视图名
		}
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		return mapping.findForward("xjbjsqqry");
	}
	
	/**
	 * 优秀毕业生申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		YxbysService service = new YxbysService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rsMap = service.getJxjsqsj();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (StringUtils.isEqual("stu", userType) || StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		}
		if (!StringUtils.isNull(xh)) {
			rs = service.stuDetailsInfo(xh);//辅助信息
			ynysForm.setMz(rs.get("mz"));
			ynysForm.setZzmm(rs.get("zzmm"));
			ynysForm.setSyd(rs.get("syd"));
			ynysForm.setJtdz(rs.get("jtdz"));
		}
		request.setAttribute("tit", "云南省普通大中专学校" + rsMap.get("jxjsqnd")
				+ "年优秀毕业生登记表");//表头
		request.setAttribute("xn", rsMap.get("jxjsqxn"));
		appendProperties(request, xydm, zydm, nj);//加载列表
		request.setAttribute("rs", rs);
		return mapping.findForward("yxbyssq");
	}
	
	/**
	 * 优秀毕业生申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXn(request.getParameter("xn"));
		YxbysService service = new YxbysService();
		HashMap<String, String> rsMap = service.getJxjsqsj();
		String xh = request.getParameter("xh");
		if (StringUtils.isEqual("stu", userType) || StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		}
		HashMap<String, String> rs = service.stuDetailsInfo(xh);//辅助信息
		YxbysModel model = new YxbysModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.saveYxbysxx(model, request);//保存信息
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		ynysForm.setMz(DealString.toGBK(ynysForm.getMz()));
		ynysForm.setZzmm(DealString.toGBK(ynysForm.getZzmm()));
		ynysForm.setSyd(DealString.toGBK(ynysForm.getSyd()));
		ynysForm.setJtdz(DealString.toGBK(ynysForm.getJtdz()));
		ynysForm.setBjyj(DealString.toGBK(ynysForm.getBjyj()));
		ynysForm.setYxsj(DealString.toGBK(ynysForm.getYxsj()));
		ynysForm.setJkzk(DealString.toGBK(ynysForm.getJkzk()));
		request.setAttribute("tit", "云南省普通大中专学校" + rsMap.get("jxjsqnd")
				+ "年优秀毕业生登记表");//表头
		request.setAttribute("xn", rsMap.get("jxjsqxn"));
		appendProperties(request, xydm, zydm, nj);//加载列表
		request.setAttribute("rs", rs);
		return mapping.findForward("yxbyssq");
	}
	
	/**
	 * 优秀毕业生申请查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyssqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, "view_ynys_yxbys", "ynys_yxbysb");//表名,视图名
		return mapping.findForward("yxbysqry");
	}
	
	/**
	 * 优秀毕业生申请查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyssqQryres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		YxbysService service = new YxbysService();
		YxbysModel model = new YxbysModel();
		BeanUtils.copyProperties(model, ynysForm);
		List<HashMap<String, String>> titList = service.getYxbysQryTitle();//查询表头
		List<String[]> rsList = service.yxbysQryRes(model);//查询结果
		appendQryResult(request, titList, rsList);//存放列表
		appendProperties(request, xydm, zydm, nj);//加载列表
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		appendTableXx(request, "view_ynys_yxbys", "ynys_yxbysb");//表名,视图名
		return mapping.findForward("yxbysqry");
	}
	
	/**
	 * 优秀毕业生信息修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		YxbysService service = new YxbysService();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> rsMap = service.viewYxbysxx(pkValue);
		if (rsMap != null) {
			ynysForm.setMz(rsMap.get("mz"));
			ynysForm.setZzmm(rsMap.get("zzmm"));
			ynysForm.setSyd(rsMap.get("syd"));
			ynysForm.setJkzk(rsMap.get("jkzk"));
			ynysForm.setJtdz(rsMap.get("jtdz"));
			ynysForm.setBjyj(rsMap.get("bjyj"));
			ynysForm.setYxsj(rsMap.get("yxsj"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		request.setAttribute("rs", rsMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("yxbysmodi");
	}
	
	/**
	 * 优秀毕业生信息修改结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysModisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		YxbysService service = new YxbysService();
		String pkValue = request.getParameter("pkValue");
		YxbysModel model = new YxbysModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.updateYxbysxx(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		ynysForm.setMz("");
		ynysForm.setZzmm("");
		ynysForm.setSyd("");
		ynysForm.setJkzk("");
		ynysForm.setJtdz("");
		ynysForm.setBjyj("");
		ynysForm.setYxsj("");
		return mapping.findForward("yxbysmodi");
	}
	
	/**
	 * 优秀毕业生批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		YxbysService service = new YxbysService();
		String sJg = service.delYxbysxx(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "第" + sJg + "条记录删除失败");
		}
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, "view_ynys_yxbys", "ynys_yxbysb");//表名,视图名
		return mapping.findForward("yxbysqry");
	}
	
	/**
	 * 综合素质测评维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String sUName = session.getAttribute("userName").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		boolean isStu = false;
		if(userType.equalsIgnoreCase("stu")){
			isStu = true;
		}
		Base.chkUPower(sUName, "pjpy_ynys_zhszcpwh.do", isStu);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 综合素质测评维护页面查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		ZhszcpService service = new ZhszcpService();
		List<HashMap<String, String>> topList = service.getZhszcpTitle();
		List<String[]> rsList = service.getZhszcpResult(model);
		appendResult(request, topList, rsList);
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 综合素质测评批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		String sJg = service.zhszcpDel(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "第" + sJg + "条记录删除失败");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 综合测评单个增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZhszcpService service = new ZhszcpService();
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// 用户类型
		String userName = (String)session.getAttribute("userName");// 用户名称
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			if (StringUtils.isNull(rs.get("xh"))) {
				rs.put("stuExists", "no");
			}
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * 综合测评单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		ynysForm.setZhszcpzf(request.getParameter("zhszcpzf"));
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		ZhszcpService service = new ZhszcpService();
		boolean bFlag = service.saveZhszcp(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * 综合测评修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String pkValue = request.getParameter("pkValue");
		ZhszcpService service = new ZhszcpService();
		HashMap<String, String> rsMap = service.viewZhszcp(pkValue);
		if (rsMap != null) {
			ynysForm.setSjlxcxf(rsMap.get("sjlxcxf"));
			ynysForm.setSxlxszf(rsMap.get("sxlxszf"));
			ynysForm.setSxzzddszf(rsMap.get("sxzzddszf"));
			ynysForm.setKxwhszf(rsMap.get("kxwhszf"));
			ynysForm.setZhszcpzf(rsMap.get("zhszcpzf"));
			ynysForm.setXn(rsMap.get("xn"));
		}
		request.setAttribute("rs", rsMap);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * 综合测评修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String pkValue = request.getParameter("pkValue");
		ZhszcpService service = new ZhszcpService();
		ynysForm.setXh(request.getParameter("xh"));
		ynysForm.setZhszcpzf(request.getParameter("zhszcpzf"));
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.updateZhszcp(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * 综合测评审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpShres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		String res = request.getParameter("param1");
		ZhszcpService service = new ZhszcpService();
		res = service.getShjg(res);
		String jg = service.zhszcpShres(ynysForm.getCbv(), res, request);
		if (StringUtils.isNull(jg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "第" + jg + "条记录审核失败!");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 奖学金申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String xh = "";
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		String jxjdm = request.getParameter("jxjdm");
		JxjService  service = new JxjService();
		if (StringUtils.isEqual("stu", userType) ||
				StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		List<String[]> cjList = new ArrayList<String[]>();
		HashMap<String, String> cjpm = new HashMap<String, String>();
		HashMap<String, String> zhfpm = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			cjList = service.getCjList(xh);
			cjpm = service.getCjpm(xh);
			zhfpm = service.getZhfpm(xh);
		}
		if (!StringUtils.isNull(jxjdm)) {
			rs = service.getJxjxx(jxjdm, xh);
		}
		HashMap<String, String> tmpMap = service.getJxjsqsj();
		rs.put("xn", tmpMap.get("jxjsqxn"));
		rs.put("nd", tmpMap.get("jxjsqnd"));
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
//		ynysForm.setKclx(null);
//		ynysForm.setCj(null);
//		ynysForm.setKcmc(null);
//		ynysForm.setSfbxk(null);
		ynysForm.setFbkw(null);
		ynysForm.setLwhzpmc(null);
		ynysForm.setHjdj(null);
		ynysForm.setHjjb(null);
		ynysForm.setSjhm(rs.get("sjhm"));
		ynysForm.setWysp(rs.get("wysp"));
		request.setAttribute("cjpm", cjpm);
		request.setAttribute("zhfpm", zhfpm);
		request.setAttribute("cjList", cjList);
		return mapping.findForward("jxjsq");
	}
	
	/**
	 * 奖学金申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setKcmc(request.getParameterValues("kcmc"));
		ynysForm.setCj(request.getParameterValues("cj"));
		ynysForm.setKclx(request.getParameterValues("kclx"));
		ynysForm.setSfbxk(request.getParameterValues("sfbxk"));
		ynysForm.setFbkw(request.getParameterValues("fbkw"));
		ynysForm.setLwhzpmc(request.getParameterValues("lwhzpmc"));
		ynysForm.setHjdj(request.getParameterValues("hjdj"));
		ynysForm.setHjjb(request.getParameterValues("hjjb"));
		HttpSession session = request.getSession();
		String xh = "";
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		String jxjdm = request.getParameter("jxjdm");
		JxjService  service = new JxjService();
		if (StringUtils.isEqual("stu", userType) ||
				StringUtils.isEqual("student", userType)) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		List<String[]> cjList = new ArrayList<String[]>();
		HashMap<String, String> cjpm = new HashMap<String, String>();
		HashMap<String, String> zhfpm = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			cjList = service.getCjList(xh);
			cjpm = service.getCjpm(xh);
			zhfpm = service.getZhfpm(xh);
		}
		if (!StringUtils.isNull(jxjdm)) {
			rs = service.getJxjxx(jxjdm, xh);
		}
		HashMap<String, String> tmpMap = service.getJxjsqsj();
		rs.put("xn", tmpMap.get("jxjsqxn"));
		rs.put("nd", tmpMap.get("jxjsqnd"));
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, ynysForm);
		model.setXn(rs.get("xn"));
		model.setNd(rs.get("nd"));
		model.setXq(tmpMap.get("jxjsqxq"));
		boolean bFlag = service.saveJxjxx(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
//		ynysForm.setKclx(null);
//		ynysForm.setCj(null);
//		ynysForm.setKcmc(null);
//		ynysForm.setSfbxk(null);
		ynysForm.setFbkw(null);
		ynysForm.setLwhzpmc(null);
		ynysForm.setHjdj(null);
		ynysForm.setHjjb(null);
		request.setAttribute("cjpm", cjpm);
		request.setAttribute("zhfpm", zhfpm);
		request.setAttribute("cjList", cjList);
		ynysForm.setDrzw(DealString.toGBK(ynysForm.getDrzw()));
		ynysForm.setWysp(DealString.toGBK(ynysForm.getWysp()));
		ynysForm.setZysj(DealString.toGBK(ynysForm.getZysj()));
		ynysForm.setEjyxyj(DealString.toGBK(ynysForm.getEjyxyj()));
		ynysForm.setXyyj(DealString.toGBK(ynysForm.getXyyj()));
		return mapping.findForward("jxjsq");
	}
	
	/**
	 * 奖学金申请结果查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjsqqry");
	}
	/**
	 * 奖学金申请查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqqryRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, ynysForm);
		List<HashMap<String, String>> topList = service.getJxjTitle();
		List<String[]> resList = service.getJxjResult(model);
		appendResult(request, topList, resList);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		appendJxjList(request);
		return mapping.findForward("jxjsqqry");
	}
	/**
	 * 奖学金信息批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		String sJg = service.jxjDel(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sJg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "第" + sJg + "条记录删除失败");
		}
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjsqqry");
	}
	
	/**
	 * 奖学金批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService services = new XjbjService();
		JxjService service = new JxjService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.viewJxjsh(pkValue, userType);
		if (rs != null) {
			ynysForm.setYesNo(rs.get("yesno"));
			ynysForm.setYj(rs.get("yj"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shList", services.getShList());//审核列表
		appendJxjList(request);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 奖学金单个审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		XjbjService services = new XjbjService();
		JxjService service = new JxjService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.saveJxjsh(pkValue, userType, ynysForm.getYesNo(), ynysForm.getYj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shList", services.getShList());//审核列表
		appendJxjList(request);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 优秀毕业生打印报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbysPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YxbysService service = new YxbysService();
		HashMap<String, String> xnMap = service.getJxjsqsj();
		request.setAttribute("tit", "云南省普通大中专学校"+xnMap.get("jxjsqnd")+"年优秀毕业生登记表");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.yxbysPrint(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("yxbysprint");
	}
	
	/**
	 * 奖学金单个修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		String act = request.getParameter("act");
		HashMap<String, String> rs = service.viewJxjxx(pkValue);
		String xh = request.getParameter("xh");
		xh = StringUtils.isNull(xh) ? "" : xh.trim();
		String xn = request.getParameter("xn");
		xn = StringUtils.isNull(xn) ? "" : xn.trim();
		if (rs != null) {
			ynysForm.setJxjdm(rs.get("jxjdm"));
			ynysForm.setDrzw(rs.get("drzw"));
			ynysForm.setDnshjxj(rs.get("dnshjxj"));
			ynysForm.setZysj(rs.get("zysj"));
			ynysForm.setEjyxyj(rs.get("ejyxyj"));
		}
		//List<String[]> kcList = service.getKcList(xh, xn);
		List<String[]> zpList = service.getZpList(xh, xn);
		List<String[]> cjList = new ArrayList<String[]>();
		HashMap<String, String> cjpm = new HashMap<String, String>();
		HashMap<String, String> zhfpm = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			cjList = service.getCjList(xh);
			cjpm = service.getCjpm(xh);
			zhfpm = service.getZhfpm(xh);
		}
		request.setAttribute("rs", rs);
		appendJxjList(request);
		request.setAttribute("pkValue", pkValue);
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		//request.setAttribute("kcList", kcList);
		request.setAttribute("zpList", zpList);
		request.setAttribute("cjpm", cjpm);
		request.setAttribute("zhfpm", zhfpm);
		request.setAttribute("cjList", cjList);
		return mapping.findForward("jxjmodi");
	}
	/**
	 * 奖学金单个修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjmodiSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, ynysForm);
		boolean bFlag = service.modiJxjsave(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("kcList", new ArrayList<String[]>());
		request.setAttribute("zpList", new ArrayList<String[]>());
		ynysForm.setDrzw("");
		ynysForm.setZysj("");
		ynysForm.setEjyxyj("");
		request.setAttribute("rs", new HashMap<String, String>());
		appendJxjList(request);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjmodi");
	}
	
	/**
	 * 综合素质测评分自动计算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpautoacount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysActionForm ynysForm = (PjpyYnysActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		String xn = request.getParameter("xn");
		String xydms = request.getParameter("xydm");
		if (StringUtils.isNull(xn)) {
			xn = Base.getJxjsqxn();
		}
		if (StringUtils.isEqual("xy", userType)) {
			xydms = userDep;
		}
		boolean bFlag = service.zhszcpAutoCount(xn, xydms);//自动计算综合分
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_ynys_zhszcp", "ynys_zhszcpb");
		return mapping.findForward("zhszcpwh");
	}
	
}

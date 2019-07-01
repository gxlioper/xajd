
package xgxt.xszz.jhzyjsxy;

import java.lang.reflect.InvocationTargetException;
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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 金华职业技术学院学生资助Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-25</p>
 */
public class XszzAction extends BaseAction {

	/**
	 * 困难生申请页面
	 * knssq ----- 困难生申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// 得到详细信息

			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}

		String[] knlxJt = { "A、来自老少边山穷地区；", "B、被当地政府列为五保户或特困户家庭；",
				"C、家庭成员中无18—55岁青壮年劳动力；", "D、属于享受最低生活保障的城镇家庭；", "E、烈士子女；",
				"F、孤儿或经济困难单亲家庭；", "G、家庭中有两个及以上成员正接受非义务教育；",
				"H、家庭成员因残疾或疾病而丧失劳动能力；", "I、家庭成员因患重大疾病需支付大额医疗费用；",
				"J、家庭及成员因遭遇突发性灾变（如自然灾害等），造成人身及财产重大损失；",
				"K、因父母离异导致家庭经济收入明显下降； ", "L、残疾学生；", "M、导致家庭经济困难的其它情况;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "1";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);
		request.setAttribute("type", Base.chgNull(request.getParameter("type"),
				"", 1));
		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("sfksq", service.getKnsSqQx("困难生", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}

	/**
	 * 保存困难生申请信息 knssqSave ---- 保存困难生申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		KnssqbModel knsModel = new KnssqbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveKnsSqxx(knsModel, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
			}
		}

		String[] knlxJt = { "A、来自老少边山穷地区；", "B、被当地政府列为五保户或特困户家庭；",
				"C、家庭成员中无18—55岁青壮年劳动力；", "D、属于享受最低生活保障的城镇家庭；", "E、烈士子女；",
				"F、孤儿或经济困难单亲家庭；", "G、家庭中有两个及以上成员正接受非义务教育；",
				"H、家庭成员因残疾或疾病而丧失劳动能力；", "I、家庭成员因患重大疾病需支付大额医疗费用；",
				"J、家庭及成员因遭遇突发性灾变（如自然灾害等），造成人身及财产重大损失；",
				"K、因父母离异导致家庭经济收入明显下降； ", "L、残疾学生；", "M、导致家庭经济困难的其它情况;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "1";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssqSave");
	}

	/**
	 * 困难生申请表页面 knssqb ----- 困难生申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnssqbModel knsModel = new KnssqbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		stuMap = service.getKnsSqb(knsModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knssqb");
	}

	/**
	 * 困难生审核页面 knssh ----- 困难生审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(jhzyjsxyActionForm.getPk(), "困难", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(jhzyjsxyActionForm.getPk(), "特殊困难", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(jhzyjsxyActionForm.getPk(), "不困难", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// 在REQUEST中存放页面加载的列表

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));
		jhzyjsxyActionForm.setFdysh(DealString.toGBK(jhzyjsxyActionForm
				.getFdysh()));
		jhzyjsxyActionForm.setXysh(DealString.toGBK(jhzyjsxyActionForm
				.getXysh()));
		jhzyjsxyActionForm.setXxsh(DealString.toGBK(jhzyjsxyActionForm
				.getXxsh()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_kns");
		request.setAttribute("tableName", "view_xszz_jhzy_kns");
		return mapping.findForward("knssh");
	}

	/**
	 * 困难生信息导出 knsExp ----- 困难生信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		service.getKnsExp(queryModel, response, request);
		return mapping.findForward("knsExp");
	}

	/**
	 * 困难生审核详细页面 knsshOne ----- 困难生审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);

		String[] knlxJt = { "A、来自老少边山穷地区；", "B、被当地政府列为五保户或特困户家庭；",
				"C、家庭成员中无18—55岁青壮年劳动力；", "D、属于享受最低生活保障的城镇家庭；", "E、烈士子女；",
				"F、孤儿或经济困难单亲家庭；", "G、家庭中有两个及以上成员正接受非义务教育；",
				"H、家庭成员因残疾或疾病而丧失劳动能力；", "I、家庭成员因患重大疾病需支付大额医疗费用；",
				"J、家庭及成员因遭遇突发性灾变（如自然灾害等），造成人身及财产重大损失；",
				"K、因父母离异导致家庭经济收入明显下降； ", "L、残疾学生；", "M、导致家庭经济困难的其它情况;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "", "", "", "", "", "", "", "", "", "", "", "",
				"" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "√";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshOne");
	}

	/**
	 * 保存困难生审核信息 knsshSave ---- 保存困难生审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		KnssqbModel knsModel = new KnssqbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveKnsShxx(knsModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);

		String[] knlxJt = { "A、来自老少边山穷地区；", "B、被当地政府列为五保户或特困户家庭；",
				"C、家庭成员中无18—55岁青壮年劳动力；", "D、属于享受最低生活保障的城镇家庭；", "E、烈士子女；",
				"F、孤儿或经济困难单亲家庭；", "G、家庭中有两个及以上成员正接受非义务教育；",
				"H、家庭成员因残疾或疾病而丧失劳动能力；", "I、家庭成员因患重大疾病需支付大额医疗费用；",
				"J、家庭及成员因遭遇突发性灾变（如自然灾害等），造成人身及财产重大损失；",
				"K、因父母离异导致家庭经济收入明显下降； ", "L、残疾学生；", "M、导致家庭经济困难的其它情况;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "", "", "", "", "", "", "", "", "", "", "", "",
				"" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "√";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
	}

	/**
	 * 困难生登记表页面 knsdjb ----- 困难生登记表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			request.setAttribute("isKns", dao.isKns(xh));
			stuMap = service.getKnsdjbxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}

		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("type", Base.chgNull(request.getParameter("type"),
				"", 1));
		request.setAttribute("sfksq", service.getKnsSqQx("困难生登记表", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsdjb");
	}

	/**
	 * 保存困难生登记表信息 knsdjbSave ---- 保存困难生登记表信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		KnsdjbModel knsModel = new KnsdjbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveKnsdjbxx(knsModel, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsdjbxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsdjbSave");
	}

	/**
	 * 困难生登记表页面 knsdjbdy ----- 困难生登记表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsdjbModel knsModel = new KnsdjbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		stuMap = service.getKnsdjbdy(knsModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsdjbdy");
	}

	/**
	 * 困难生登记表数据维护页面 knsdjbsh ----- 困难生登记表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsdjb(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsdjbTit();
		List<String[]> resList = service.getKnsdjbRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// 在REQUEST中存放页面加载的列表

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsdjbResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xg_zz_knsdjb");
		request.setAttribute("tableName", "view_xg_zz_knsdjb");
		return mapping.findForward("knsdjbsh");
	}

	/**
	 * 困难生登记表导出 knsdjbExp ----- 困难生登记表导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		service.getKnsdjbExp(queryModel, response, request);
		return mapping.findForward("knsdjbExp");
	}

	/**
	 * 国家助学金申请页面 gjzxjsq -----国家助学金申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			request.setAttribute("isKns", dao.isKns(xh));
			stuMap = service.getGjzxjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}

		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("type", Base.chgNull(request.getParameter("type"),
				"", 1));
		request.setAttribute("sfksq", service.getKnsSqQx("国家助学金", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsq");
	}

	/**
	 * 保存国家助学金申请信息 gjzxjsqSave ---- 保存国家助学金申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveGjzxjSqxx(model, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// 得到国家助学金详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsqSave");
	}

	/**
	 * 国家助学金申请表页面 gjzxjsqb ----- 国家助学金申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		stuMap = service.getGjzxjSqb(model, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxjsqb");
	}

	/**
	 * 国家助学金审核页面 gjzxjsh ----- 国家助学金审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxj(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj(jhzyjsxyActionForm.getPk(), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj(jhzyjsxyActionForm.getPk(), "不通过", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// 在REQUEST中存放页面加载的列表

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjzxjResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_gjzxj");
		request.setAttribute("tableName", "view_xszz_jhzy_gjzxj");
		return mapping.findForward("gjzxjsh");
	}

	/**
	 * 国家助学金信息导出 gjzxjExp ----- 国家助学金信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		service.getGjzxjExp(queryModel, response, request);
		return mapping.findForward("gjzxjExp");
	}

	/**
	 * 国家助学金审核详细页面 gjzxjshOne ----- 国家助学金审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshOne");
	}

	/**
	 * 保存国家助学金审核信息 gjzxjshSave ---- 保存国家助学金审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveGjzxjShxx(model, request);// 保存国家助学金审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}

	/**
	 * 国家励志奖学金申请
	 */
	public ActionForward gjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String doType = request.getParameter("doType");
		String type = request.getParameter("type");
		XszzGjlzjxjModel model = new XszzGjlzjxjModel();
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);

		jhzyjsxyActionForm.setSqly(DealString.toGBK(jhzyjsxyActionForm
				.getSqly()));
		jhzyjsxyActionForm.setChhzjl(DealString.toGBK(jhzyjsxyActionForm
				.getChhzjl()));
		jhzyjsxyActionForm.setJthk(DealString.toGBK(jhzyjsxyActionForm
				.getJthk()));
		jhzyjsxyActionForm.setJtyzsr(DealString.toGBK(jhzyjsxyActionForm
				.getJtyzsr()));
		jhzyjsxyActionForm.setJtzrks(DealString.toGBK(jhzyjsxyActionForm
				.getJtzrks()));
		jhzyjsxyActionForm.setRjysr(DealString.toGBK(jhzyjsxyActionForm
				.getRjysr()));
		jhzyjsxyActionForm.setSrly(DealString.toGBK(jhzyjsxyActionForm
				.getSrly()));
		jhzyjsxyActionForm.setJtzz(DealString.toGBK(jhzyjsxyActionForm
				.getJtzz()));
		boolean isKns = true;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			isKns = service.isKns(xh);
			stuMap = service.getStu(xh);// 得到学生信息
		}
		if ("save".equalsIgnoreCase(doType)) {// 数据保存
			BeanUtils.copyProperties(model, jhzyjsxyActionForm);
			service.serv_saveGjlzjxjSq(model, request);
		}
		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("sfksq", service.getKnsSqQx("国家励志奖学金", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rsJxj", service.serv_getGjlzJxjInfo(Base.currNd
				+ xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("isKns", isKns);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("type", type);
		return mapping.findForward("gjlzjxjsq");
	}

	/**
	 * 国家励志奖学金申请表页面 gjlzjxjsqb ----- 国家励志奖学金申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XszzGjlzjxjModel model = new XszzGjlzjxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		stuMap = service.getGjlzjxjSqb(model, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxjsqb");
	}

	/**
	 * 国家励志奖学金审核
	 * 
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.serv_delGjlzjxjxx(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.serv_modGjlzJxjsxx(jhzyjsxyActionForm.getPk(), "通过",
					request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.serv_modGjlzJxjsxx(jhzyjsxyActionForm.getPk(), "不通过",
					request);
			queryModel.setGo("go");
		}
		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.ser_getGjlzJxjTit();
		List<String[]> resList = service.serv_getGjlzJxjRes(queryModel,
				request, jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// 在REQUEST中存放页面加载的列表

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));
		jhzyjsxyActionForm.setFdysh(DealString.toGBK(jhzyjsxyActionForm
				.getFdysh()));
		jhzyjsxyActionForm.setXysh(DealString.toGBK(jhzyjsxyActionForm
				.getXysh()));
		jhzyjsxyActionForm.setXxsh(DealString.toGBK(jhzyjsxyActionForm
				.getXxsh()));

		jhzyjsxyActionForm.getPages()
				.setMaxRecord(
						Integer.parseInt(service.getGjlzJxjResNum(queryModel,
								request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_gjlzjxj");
		request.setAttribute("tableName", "view_xszz_jhzy_gjlzjxj");
		return mapping.findForward("gjlzjxjsh");
	}

	/**
	 * 国家励志奖学金审核详细页面
	 */
	public ActionForward gjlzjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		XszzGjlzjxjModel model = new XszzGjlzjxjModel();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String doType = request.getParameter("doType");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ("save".equalsIgnoreCase(doType)) {
			BeanUtils.copyProperties(model, jhzyjsxyActionForm);
			boolean bJg = service.serv_saveGjlzjxjSh(model, request);
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		HashMap<String, String> stuMap = new HashMap<String, String>();
		HashMap<String, String> stuGjlzjxjMap = new HashMap<String, String>();
		stuGjlzjxjMap = service.serv_getGjlzJxjInfo(pkVal);
		;
		stuMap = service.getStu(stuGjlzjxjMap.get("xh"));
		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("rsJxj", stuGjlzjxjMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshOne");
	}

	/**
	 * 帮困助学金申请页面 bkzxjjsq -----帮困助学金申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			if (service.isKns(xh)) {
				stuMap = service.getBkzxjjXx(pkVal);// 得到详细信息

				if (stuMap.size() == 0) {
					stuMap = service.getStu(xh);// 得到学生信息
				}
				if (service.isBjgkm(nd, xh)) {
					String msg = "该生有科目不及格，不能进行申请!";
					request.setAttribute("msg", msg);
				}
			} else {
				String msg = "该生目前不是困难生，不能进行申请!";
				request.setAttribute("msg", msg);
			}
		}

		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("sfksq", service.getKnsSqQx("帮困助学基金", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("rs", stuMap);
		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjsq");
	}

	/**
	 * 保存帮困助学基金申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		BkzxjjModel model = new BkzxjjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveBkzxjjSqxx(model, request);// 帮困助学基金申请信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getBkzxjjXx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
			}
		}

		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjsqSave");
	}

	/**
	 * 帮困助学基金审核页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delBkzxjj(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modBkzxjjxx(jhzyjsxyActionForm.getPk(), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modBkzxjjxx(jhzyjsxyActionForm.getPk(), "不通过", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getBkzzjjRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// 在REQUEST中存放页面加载的列表

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));
		jhzyjsxyActionForm.setFdysh(DealString.toGBK(jhzyjsxyActionForm
				.getFdysh()));
		jhzyjsxyActionForm.setXysh(DealString.toGBK(jhzyjsxyActionForm
				.getXysh()));
		jhzyjsxyActionForm.setXxsh(DealString.toGBK(jhzyjsxyActionForm
				.getXxsh()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getBkzzjjResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_bkzxjj");
		request.setAttribute("tableName", "view_xszz_jhzy_bkzxjj");
		return mapping.findForward("bkzxjjsh");
	}

	/**
	 * 帮困助学审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getBkzxjjXx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjshOne");
	}

	/**
	 * 保存帮困助学基金审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		BkzxjjModel model = new BkzxjjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveBkzxjjShxx(model, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjshSave");
	}

	/**
	 * 帮困助学基金申请表页面 knssqb ----- 困难生申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();

		HttpSession session = request.getSession();

		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名

		BkzxjjModel model = new BkzxjjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		stuMap = service.getBkzxjjXx(pkVal);// 得到详细信息
		if (stuMap.size() == 0) {
			String msg = "该生还未提交申请！！";
			request.setAttribute("msg", msg);
		}
		if (Base.isNull(stuMap.get("nd"))) {
			stuMap.put("nd", "    ");
		}
		String zxjjdm = stuMap.get("zxjjdm");
		String zxjjxx = service.getZxjjXx(zxjjdm);
		stuMap.put("zxjjxx", zxjjxx);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("bkzxjjsqb");
	}
}

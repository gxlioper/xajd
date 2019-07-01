package xgxt.pjpy.zjlg.dycj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.pjpy.zjlg.ZjlgPjpyModel;
import xgxt.pjpy.zjlg.ZjlgPjpyService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class DycjAction extends CommonAction {

	DycjService service = new DycjService();


	/**
	 * 平时分维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward psfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(isBzr)) {
			myForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}

		if (StringUtils.isNull(myForm.getXn())) {
			myForm.setXn(Base.getJxjsqxn());
			
		}
		request.setAttribute("pjxn", myForm.getXn());
		//===============2010.10.14 edit by luojw=============================
		String doType = myForm.getAct();
		if (SAVE.equalsIgnoreCase(doType)) {// 保存数据操作
			HashMap<String, String[]> primaryArrayMap = new HashMap<String, String[]>();
			// this.getValueArrayMap(request, "primarykey_")
			primaryArrayMap.put("xh", request
					.getParameterValues("primary_xh"));
			HashMap<String, String> primaryMap = new HashMap<String, String>();
			primaryMap.put("xn", request.getParameter("xn"));
			HashMap<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("tableName", "zjlg_dypsf");
			tableMap.put("viewName", "view_zjlg_dypsf");

			this.savePageDataBatch(request, primaryArrayMap, primaryMap,
					tableMap);
			
			doType = QUERY;

		} else if ("sh".equalsIgnoreCase(doType)) {// 审核数据操作
			String shjg = "";
			if ("tg".equalsIgnoreCase(request.getParameter("jg"))) {
				shjg = "通过";
			}else if("btg".equalsIgnoreCase(request.getParameter("jg"))){
				shjg = "不通过";
			}else{
				shjg = "未审核";
			}
			request.setAttribute("shzd", "xysh");
			request.setAttribute("shjg", shjg);
			this.auditingBatchOperation(request, "zjlg_dypsf");
			
			doType = QUERY;
		}

		if (QUERY.equalsIgnoreCase(doType)) {// 查询数据操作
			DycjModel model = new DycjModel();
			BeanUtils.copyProperties(model, myForm);
			rs = service.queryPsfResult(model, isBzr, userName);
			topTr = service.queryPsfTitle();

			/* 如果是班主任用户则显示以下信息 */
			if (UserTypePd.isFdy(isBzr)) {
				/* 平时分比例 */
				HashMap<String, String> blrs = CommonQueryDAO.dao_getInfo(
						"zjlg_dypsf_sz", new String[] { "bzrzgh", "isbl",
								"zpfbl", "bjfbl" },
						" a where exists (select 1 from fdybjb b where a.bzrzgh=b.bjdm and b.zgh='"
								+ userName + "') and rownum<2");
				request.setAttribute("blrs", blrs);
			}
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_dypsf", "zjlg_dypsf");
		appendOperQx(request, "pjpy_zjlg_psfwh.do");
		appendQryResult(request, topTr, rs);
		return mapping.findForward("psfwh");
	}
	
	/**
	 * 平时分比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward psfblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String userName = request.getSession().getAttribute("userName").toString();
		
		/* 保存数据操作 */
		if (SAVE.equalsIgnoreCase(myForm.getAct())) {
			DycjModel model = new DycjModel();
			BeanUtils.copyProperties(model, myForm);
			request.setAttribute("result", service.savePsfblxx(userName, model));
		}

		HashMap<String, String> rs = CommonQueryDAO.dao_getInfo("zjlg_dypsf_sz", new String[] { "bzrzgh", "isbl", "zpfbl",
		"bjfbl" }, " a where exists (select 1 from fdybjb b where a.bzrzgh=b.bjdm and b.zgh='"+userName+"') and rownum<2");
		request.setAttribute("rs", rs);
		return mapping.findForward("psfblsz");
	}
	
	/**
	 * 卫生分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(isBzr)) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		
		if (StringUtils.isNull(myForm.getXn())) {
			myForm.setXn(Base.getJxjsqxn());
		}
		
		if (QUERY.equalsIgnoreCase(myForm.getAct())) {//查询数据操作
			DycjModel model = new DycjModel();
			BeanUtils.copyProperties(model, myForm);
			rs = service.queryWsfResult(model, isBzr, userName);
			topTr = service.queryWsfTitle();
			
		} else if (SAVE.equalsIgnoreCase(myForm.getAct())) {//保存数据操作
			HashMap<String, String[]> primaryArrayMap = new HashMap<String, String[]>();
			primaryArrayMap.put("xh", request.getParameterValues("primary_xh"));
			HashMap<String, String> primaryMap = new HashMap<String, String>();
			primaryMap.put("xn", request.getParameter("xn"));
			HashMap<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("tableName", "zjlg_dywsf");
			tableMap.put("viewName", "view_zjlg_dywsf");
			this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
		} else if ("sh".equalsIgnoreCase(myForm.getAct())) {//审核数据操作
			request.setAttribute("shzd", "xysh");
			request.setAttribute("shjg", "tg".equalsIgnoreCase(request
					.getParameter("jg")) ? "通过" : ("btg"
					.equalsIgnoreCase(request.getParameter("jg")) ? "不通过"
					: "未审核"));
			this.auditingBatchOperation(request, "zjlg_dywsf");
		} 
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_dywsf", "zjlg_dywsf");
		appendOperQx(request, "pjpy_zjlg_wsfwh.do");
		appendQryResult(request, topTr, rs);
		
		return mapping.findForward("wsfwh");
	}
	
	/**
	 * 考勤分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(isBzr)) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		
		if (StringUtils.isNull(myForm.getXn())) {
			myForm.setXn(Base.getJxjsqxn());
		}
		
		if (QUERY.equalsIgnoreCase(myForm.getAct())) {//查询数据操作
			DycjModel model = new DycjModel();
			BeanUtils.copyProperties(model, myForm);
			rs = service.queryKqfResult(model, isBzr, userName);
			topTr = service.queryKqfTitle();
			
		} else if (SAVE.equalsIgnoreCase(myForm.getAct())) {//保存数据操作
			HashMap<String, String[]> primaryArrayMap = this.getValueArrayMap(request, "primarykey_");
			HashMap<String, String> primaryMap = new HashMap<String, String>();
			primaryMap.put("xn", request.getParameter("xn"));
			HashMap<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("tableName", "zjlg_dykqf");
			tableMap.put("viewName", "view_zjlg_dykqf");
			this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_dykqf", "zjlg_dykqf");
		appendOperQx(request, "pjpy_zjlg_kqfwh.do");
		appendQryResult(request, topTr, rs);
		return mapping.findForward("kqfwh");
	}
	
	/**
	 * 德育分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(isBzr)) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		
		if (StringUtils.isNull(myForm.getXn())) {
			myForm.setXn(Base.getJxjsqxn());
		}
		
		if (QUERY.equalsIgnoreCase(myForm.getAct())) {
			DycjModel model = new DycjModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.queryDyzfTitle();
			rs = service.queryDyzfResult(model, isBzr, userName);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_dyf", "zjlg_dyf");
		appendOperQx(request, "pjpy_zjlg_dyfwh.do");
		appendQryResult(request, topTr, rs);
		return mapping.findForward("dyfwh");
	}
	
	/**
	 * 综合素质测评总分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		DycjModel model = new DycjModel();
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(isBzr)) {
			myForm.setQueryequals_xydm(request.getSession().getAttribute("userDep").toString());
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
			request.setAttribute("xydm", myForm.getQueryequals_xydm());
		}   
		
		
		if (StringUtils.isNull(myForm.getQueryequals_xn())) {
			myForm.setQueryequals_xn(Base.getJxjsqxn());
		}
		
		BeanUtils.copyProperties(model, myForm);
		myModel.setXn(myForm.getQueryequals_xn());
		myModel.setXydm(myForm.getQueryequals_xydm());
		
		if (QUERY.equalsIgnoreCase(myForm.getAct())) {//查询
			selectPageDataByPagination(request, myForm, "zjlg_zhszcp",
					"view_zjlg_zhszcpf", new String[] { "pkValue", "行号", "xh",
							"xm", "bjmc","xn", "zycpf","pjfs", "dycpf", "tycpf", "zhcpf" ,"pm"});
			
			/* 综合德，智总分比例 */
			HashMap<String, String> zfbl = CommonQueryDAO.commonQueryOne(
					"zjlg_zhszcp_sz", new String[] { "dybl", "zybl" }, "xn",
					myForm.getQueryequals_xn());
			request.setAttribute("zfbl", zfbl);
		} else if (SAVE.equalsIgnoreCase(myForm.getAct())) {//自动计算
			boolean result = service.countZhszcpf(model);
			request.setAttribute("message", result ? "操作成功！" : "操作失败！");
			request.setAttribute("result", result);
		} else if (EXPORT.equalsIgnoreCase(myForm.getAct())) {
			expPageData(request, response, "zjlg_zhszcp", "view_zjlg_zhszcpf",
					new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
							"bjmc", "xn", "zycpf","pjfs", "dycpf", "tycpf", "zhcpf" });
			return mapping.findForward("");
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_zhszcpf", "zjlg_zhszcp");
		appendOperQx(request, "pjpy_zjlg_zhszcp.do");
		ZjlgPjpyService myservice = new ZjlgPjpyService();
		request.setAttribute("cpzList",myservice.getCpzList(myModel));
		return mapping.findForward("zhszcp");
	}
	/**
	 * 浙江理工德育分维护页面
	 * auther lyl
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward otherdyfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DycjActionForm myForm = (DycjActionForm) form;
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(isBzr)) {
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		
		if (StringUtils.isNull(myForm.getXn())) {
			myForm.setXn(Base.getJxjsqxn());
		}
		
		if (QUERY.equalsIgnoreCase(myForm.getAct())) {
			if(request.getParameter("flag").equals("next")){
				HashMap<String, String[]> primaryArrayMap = new HashMap<String, String[]>();
				primaryArrayMap.put("xh", request.getParameterValues("primary_xh"));
				HashMap<String, String> primaryMap = new HashMap<String, String>();
				primaryMap.put("xn", request.getParameter("xn"));
				HashMap<String, String> tableMap = new HashMap<String, String>();
				tableMap.put("tableName", "zjlg_zpf");
				this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
				request.setAttribute("result", null);
			}
			DycjModel model = new DycjModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.queryZjlgDyzfTitle();
			rs = service.queryZjlgDyzfResult(model, isBzr, userName);
		} else if (SAVE.equalsIgnoreCase(myForm.getAct())) {//保存数据操作
			HashMap<String, String[]> primaryArrayMap = new HashMap<String, String[]>();
			primaryArrayMap.put("xh", request.getParameterValues("primary_xh"));
			HashMap<String, String> primaryMap = new HashMap<String, String>();
			primaryMap.put("xn", request.getParameter("xn"));
			HashMap<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("tableName", "zjlg_zpf");
			this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
		}
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_dyf", "zjlg_zpf");
		appendOperQx(request, "pjpy_zjlg_otherdyfwh.do");
		appendQryResult(request, topTr, rs);
		return mapping.findForward("otherdyfwh");
	}
}

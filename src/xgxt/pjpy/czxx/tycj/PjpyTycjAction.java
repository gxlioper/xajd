/*
 * @className: PjpyTycjAction.java
 * 
 * @time: 2010-4-2
 * 
 * @copyRight hz-zf
 * 
 */
package xgxt.pjpy.czxx.tycj;

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

import xgxt.action.Base;
import xgxt.pjpy.czxx.PjpyCzxxActionForm;
import xgxt.pjpy.czxx.dycj.DyjcfModel;
import xgxt.pjpy.zjjj.model.DycjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyTycjAction extends CommonAction {

	/**
	 * 体育课成绩查询页面 包括学生成绩课成绩,体育附加分,体育锻炼信息的维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjkcjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String,String>>();
		
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			TycjService service = new TycjService();
			TycjModel model = new TycjModel();
			BeanUtils.copyProperties(model, czxxForm);
			rs = (ArrayList<String[]>) service.queryTykcjResult(model, request
					.getSession().getAttribute("fdyQx").toString());
			topTr = service.queryTykcjTitle();
		}
		
		request.setAttribute("path", "pjpy_czxx_tjkcjb.do");
		FormModleCommon.commonRequestSet(request, "czxx_tyfjfb", "czxx_tyfjfb",
				rs, topTr);
		return mapping.findForward("tjkcjb");
	}
	
	/**
	 * 体育锻炼，附加分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tydlfjfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = 
										new ArrayList<HashMap<String,String>>();
		TycjService service = new TycjService();
		
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		/* 查询数据 */
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			DyjcfModel model = new DyjcfModel();
			BeanUtils.copyProperties(model, czxxForm);
			model.setUserName(request.getSession().getAttribute("userName")
					.toString());
			topTr = service.queryTyfjfTitle();
			rs = (ArrayList<String[]>) service.queryTyfjfResult(model, request
					.getSession().getAttribute("fdyQx").toString());
		}
		
		request.setAttribute("path", "pjpy_czxx_tydlfjfb.do");
		FormModleCommon.commonRequestSet(request, "view_czxx_tyfjfb", "czxx_tykcjb",
				rs, topTr);
		return mapping.findForward("tydlfjfb");
	}
	
	/**
	 * 增加体育附加分信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTyfjfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String[] xhxnxqArr = StringUtils.isNull(pkValue) ? new String[3] 
		                                                : pkValue.split("!@");
		TycjService service = new TycjService();
		
		HashMap<String, String> rs = CommonQueryDAO
				.getStuInfo(xhxnxqArr[0]);//查询学生的详细信息
		rs.put("xn", xhxnxqArr[1]);
		rs.put("xq", xhxnxqArr[2]);
		
		/* 保存数据 */
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {
			DyjcfModel model = new DyjcfModel();
			BeanUtils.copyProperties(model, czxxForm);
			model.setXh(xhxnxqArr[0]);
			model.setXn(xhxnxqArr[1]);
			model.setXq(xhxnxqArr[2]);
			appendOperResult(request, service.saveTyfjfxx(model));
		}
		
		//将查询出来的德育附加分组装后存放于页面
		appendDyfjsxx(request, pkValue);
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("act", request
				.getParameter("modi"));//操作标志,是保存,还是修改
		return mapping.findForward("addTyfjfxx");
	}
	
	/**
	 * 体育锻炼成绩信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tydlcjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String,String>>();
		TycjService service = new TycjService();
		
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			DyjcfModel model = new DyjcfModel();
			BeanUtils.copyProperties(model, czxxForm);
			topTr = service.queryTydlfTitle();
			rs = (ArrayList<String[]>) service.queryTydlffResult(model, request
					.getSession().getAttribute("fdyQx").toString());
		} else if (DELETE.equalsIgnoreCase(czxxForm.getAct())) {
			appendOperResult(request, service.deleteTydlfxx(czxxForm.getCbv()));
		}
		
		request.setAttribute("path", "pjpy_czxx_tydlcjb.do");
		FormModleCommon.commonRequestSet(request, "view_czxx_tydlcjb", "czxx_tydlcjb",
				rs, topTr);
		return mapping.findForward("tydlcjb");
	}
	
	/**
	 * 保存体育锻炼分成绩信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTydlfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TycjService service = new TycjService();

		String[] pkValues = request.getParameterValues("hiddenVal");//主键列表
		String[] fs = request.getParameterValues("fs");//分数列表
		appendOperResult(request, service.saveTydlfResult(pkValues, fs));
		
		return tydlcjb(mapping, form, request, response);
	}
	
	/**
	 * 体育总成绩查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tycjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String,String>>();
		TycjService service = new TycjService();
		
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			TycjModel model = new TycjModel();
			BeanUtils.copyProperties(model, czxxForm);
			topTr = service.queryTycjTitle();
			rs = (ArrayList<String[]>) service.queryTycjResult(model, request
					.getSession().getAttribute("fdyQx").toString());
		} 
		
		request.setAttribute("path", "pjpy_czxx_tycjb.do");
		FormModleCommon.commonRequestSet(request, "", "",
				rs, topTr);
		
		return mapping.findForward("tycjb");
	}

	/**
	 * 综合素质测评信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = 
			new ArrayList<HashMap<String,String>>();
		TycjService service = new TycjService();
		
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		TycjModel model = new TycjModel();
		BeanUtils.copyProperties(model, czxxForm);
		
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			topTr = service.queryZhcjTitle();
			rs = (ArrayList<String[]>) service.queryZhcjResult(model, request
					.getSession().getAttribute("fdyQx").toString());
		} else if (AUTOACCOUNT.equalsIgnoreCase(czxxForm.getAct())) {
			appendOperResult(request, service.countZhszcpzf(model));
		}
		
		request.setAttribute("path", "data_search.do?act=zhsz");
		FormModleCommon.commonRequestSet(request, "view_czxx_zhszcp", "zhszcp",
				rs, topTr);
		
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 显示综测详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne("view_czxx_zhszcp", new String[] { "xh","xn","xq",
				"xm", "xb", "nj", "xymc", "zymc", "bjmc", "dcj", "zcj", "tcj",
				"zxf", "dpm", "zpm", "tpm", "zfpm" },
				"xh||xn||xq", request.getParameter("pkValue"));
		request.setAttribute("rs", rs);
		return mapping.findForward("viewZhszcpxx");
	}
	
	//设置默认的学年，学期
	public void setDefaultXnXq(PjpyCzxxActionForm czxxForm) {
		if (StringUtils.isNull(czxxForm.getXn())) {
			czxxForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(czxxForm.getXq())) {
			czxxForm.setXq(Base.getJxjsqxq());
		}
	}
	
	//组装查询出来的德育附加分信息，存放于页面加载已评分的信息
	private void appendDyfjsxx(HttpServletRequest request, String pkValue) {
		List<HashMap<String, String>> valList = CommonQueryDAO
				.dao_getInfotoList("czxx_tyfjfb", null,
						" where xh||'!@'||xn||'!@'||xq='" + pkValue + "'");
		if (valList != null && valList.size() > 0) {
			StringBuffer strVal = new StringBuffer();
			for (int i = 0; i < valList.size(); i++) {
				strVal.append(valList.get(i).get("lb")).append("!!");
				strVal.append(valList.get(i).get("yy")).append("!!");
				strVal.append(
						Base.isNull(valList.get(i).get("bz")) ? "" : valList
								.get(i).get("bz")).append("!!");
				strVal.append(
						Base.isNull(valList.get(i).get("fs")) ? "" : valList
								.get(i).get("fs")).append("#");
			}
			if (!Base.isNull(strVal.toString())) {
				strVal.deleteCharAt(strVal.length() - 1);
			}
			request.setAttribute("rsVal", strVal);
		}
	}
	
	/**
	 * 加载学院，年级，专业，班级下拉列表值
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyCzxxActionForm myForm) throws Exception{
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		if ("xy".equalsIgnoreCase(userType) && !"true".equalsIgnoreCase(isFdy)) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if ("true".equalsIgnoreCase(isFdy)) {
			myForm.setXydm(null);
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}
	}
}

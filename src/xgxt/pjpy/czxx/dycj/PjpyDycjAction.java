package xgxt.pjpy.czxx.dycj;

import java.lang.reflect.InvocationTargetException;
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
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyDycjAction extends CommonAction {

	/**
	 * 德育基础分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyjcfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		DyjcfService service = new DyjcfService();
		DyjcfModel model = new DyjcfModel();
		BeanUtils.copyProperties(model, czxxForm);
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {//查询
			model.setUserName(request.getSession().getAttribute("userName")
					.toString());
			List<String[]> rs = service.queryDyjcfResult(model, request
					.getSession().getAttribute("fdyQx").toString());
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.isEmpty() ? 0 : rs.size());
			request.setAttribute("topTr", service.queryDyjcfTitle());
		} else if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {//批量保存
			appendOperResult(request, service.saveDyjcfxx(model));
		} else if (DELETE.equalsIgnoreCase(czxxForm.getAct())) {//删除
			appendOperResult(request, service.deleteDyjcfxx(model));
		}
		appendOperQx(request, "pjpy_czxx_dyjcfb.do");
		
		return mapping.findForward("dyjcfb");
	}
	
	/**
	 * 德育附加分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyfjfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		DyjcfService service = new DyjcfService();
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			DyjcfModel model = new DyjcfModel();
			BeanUtils.copyProperties(model, czxxForm);
			model.setUserName(request.getSession().getAttribute("userName")
					.toString());
			List<String[]> rs = service.queryDyfjfResult(model, request
					.getSession().getAttribute("fdyQx").toString());
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.isEmpty() ? 0 : rs.size());
			request.setAttribute("topTr", service.queryDyfjfTitle());
		}
		
		appendOperQx(request, "pjpy_czxx_dyfjfb.do");
		return mapping.findForward("dyfjfb");
	}
	
	/**
	 * 增加德育附加分信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDyfjfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String[] aPkValue = StringUtils.isNull(pkValue) ? new String[3] 
		                                                : pkValue.split("!@");
		HashMap<String, String> rs = CommonQueryDAO
				.getStuInfo(aPkValue[0]);
		rs.put("xn", aPkValue[1]);
		rs.put("xq", aPkValue[2]);
		DyjcfService service = new DyjcfService();
		
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {
			DyjcfModel model = new DyjcfModel();
			BeanUtils.copyProperties(model, czxxForm);
			model.setXh(aPkValue[0]);
			model.setXn(aPkValue[1]);
			model.setXq(aPkValue[2]);
			appendOperResult(request, service.saveDyfjfxx(model));
		}
		
		//将查询出来的德育附加分组装后存放于页面
		appendDyfjsxx(request, pkValue);
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("act", request
				.getParameter("modi"));
		return mapping.findForward("addDyfjfxx");
	}

	//组装查询出来的德育附加分信息，存放于页面加载已评分的信息
	private void appendDyfjsxx(HttpServletRequest request, String pkValue) {
		List<HashMap<String, String>> valList = CommonQueryDAO
				.dao_getInfotoList("czxx_dyfjfb", null,
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
	 * 德育成绩维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dycjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString()) && !"true".equalsIgnoreCase(request.getSession().getAttribute("isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		//学年，学期为空，则将评奖学年，学期设置进去
		setDefaultXnXq(czxxForm);
		setNjXyZyBjList(request, czxxForm);
		
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			DyjcfService service = new DyjcfService();
			DyjcfModel model = new DyjcfModel();
			BeanUtils.copyProperties(model, czxxForm);
			model.setUserName(request.getSession().getAttribute("userName")
					.toString());
			List<String[]> rs = service.queryDycjResult(model, request
					.getSession().getAttribute("fdyQx").toString());
			request.setAttribute("rsNum", rs.isEmpty() ? 0 : rs.size());
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", service.queryDycjTitle());
		}
		
		appendOperQx(request, "pjpy_czxx_dycjb.do");
		
		return  mapping.findForward("dycjb");
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

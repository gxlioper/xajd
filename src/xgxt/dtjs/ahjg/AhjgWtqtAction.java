package xgxt.dtjs.ahjg;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

/**
 * @author bat_zzj
 */

public class AhjgWtqtAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
//			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
//			userType = session.getAttribute("userOnLine").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();

			if (myAct.equalsIgnoreCase("wtqtgl")) {
				myActFwd = wtqtgl(mapping, form, request, response);
			} else if (myAct.equalsIgnoreCase("wtqttj")) {
				myActFwd = wtqttj(mapping, form, request, response);
			} 
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
		}
		return new ActionForward("/login.jsp", false);
	}

//	private boolean isNull(String str) {
//		return ((str == null) || str.equalsIgnoreCase("") || str
//				.equalsIgnoreCase("all"));
//	}


	private ActionForward wtqtgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm myform = (CommanForm) form;
		Model model = new Model();
		String realTable = "ahjg_wtqt";
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		int count = 0;
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		request.setAttribute("realTable", realTable);
		WtqtService service = new WtqtService();
		String xh = request.getParameter("xh");
		
		request.setAttribute("wtqtList",service.getWtqt());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
		int rows = 0;
		if ("add".equals(act)) {
			// service.yxjzyjs_toGBK(myform);
			
			BeanUtils.copyProperties(model, myform);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			service.formToGBK(model);
			myform.setXsczqk(model.getXsczqk());
			if ("add".equals(doType)) {
				//判断是否存在
				boolean judge = service.issave(model);
				if(!judge){
					judge = service.saveStuxx(model,request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}else{
					request.setAttribute("save", "iscz");
				}
			}
			rs1 = service.getXsxxInfo(xh);
			rs1.put("xn", Base.currXn);
			rs1.put("xq", "01".equals(Base.currXq)?"春":"秋");
			request.setAttribute("rs1", rs1);
			request.setAttribute("rs", rs1);
			
			return mapping.findForward("add");
		}
		if ("query".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			myform.setXm(DealString.toGBK(model.getXm()));
			myform.setXsxh(DealString.toGBK(model.getXsxh()));
			rs = service.getQueryList(model,myform);
			if(rs != null){
			rows = rs.size();
			}
			count = service.queryListNum(model);
			topTr = service.queryTitle();
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("expdata".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			service.getExpList(model,response.getOutputStream());
			if(rs != null){
			rows = rs.size();
			}
			count = service.queryListNum(model);
			topTr = service.queryTitle();
			myform.getPages().setMaxRecord(
					Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
		}
		if ("view".equals(act)) {
			String pk = request.getParameter("pkValue");
			rs1 = service.getViewList(pk);
			String[] wtmc = service.getwtqtList(rs1.get("xh"), rs1.get("xn"), rs1.get("xq"));
			StringBuffer wtbf = new StringBuffer();
			for(int i = 0;i<wtmc.length;i++){
				wtbf.append(i+1+":").append(wtmc[i]);
			}
			rs1.put("wtmc", wtbf.toString());
			request.setAttribute("rs", rs1);
			return mapping.findForward("view");
		}
		if ("update".equals(act)) {
			if ("update".equals(doType)) {
				BeanUtils.copyProperties(model, myform);
//				model.setXn(Base.currXn);
//				model.setXq(Base.currXq);
				service.formToGBK(model);
				myform.setXsczqk(model.getXsczqk());
					boolean judge = service.save_modify(model,request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
			}
			String pkva = request.getParameter("pkValue");
			if(StringUtils.isNull(pkva)){
			StringBuffer buff = new StringBuffer();
			pkva =buff.append(myform.getXh()).append(myform.getXn()).append(myform.getXq()).toString();
			}
			rs1 = service.getViewList(pkva);
			request.setAttribute("rs1", rs2);
			request.setCharacterEncoding("GBK");
			String[] wtmc = service.getwtqtList(rs1.get("xh"), rs1.get("xn"), rs1.get("xq"));
			StringBuffer wtbf = new StringBuffer();
			for(int i = 0;i<wtmc.length;i++){
				wtbf.append(i+1+":").append(wtmc[i]);
			}
			rs1.put("wtmc", wtbf.toString());
			request.setAttribute("rs1", rs2);
			request.setAttribute("rs", rs1);
			return mapping.findForward("update");
		}
		if ("alldel".equals(act)) {
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllDelList(pks, request);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("whichpk", whichpk);
				request.setAttribute("delall", whichpk);
			}
		}
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("zyList", commen_util.get_zyList(xydm));
		request.setAttribute("bjList", commen_util.get_bjList(xydm, zydm));
//		request.setAttribute("who", userType);
		request.setAttribute("rsNum", rows);
		request.setAttribute("rs1", rs);
		request.setAttribute("rs", rs1);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}
	private ActionForward wtqttj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("g");
	}
}

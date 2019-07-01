package xgxt.xljk.zxzx.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.zxzx.form.xljk_zxszy_Form;
import xgxt.xljk.zxzx.model.xljk_zxxxx_zy_model;
import xgxt.xljk.zxzx.util.XLJK_ZXZX_Util;

import common.Globals;

/** 
 * MyEclipse Struts
 * Creation date: 06-18-2007
 */
public class xljk_zxszyAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myform.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			String act = request.getParameter("act");
			String doType = request.getParameter("doType");
			ActionForward myActFwd = null;
			if (act.equals("xljk_zxszygl") && StringUtils.isNotNull(doType)){
				if (doType.equals("Xssq_Zycx")) {    //学生申请 资源查询
					myActFwd = Xssq_Zycx(mapping, form, request, response);
				} else if (doType.equals("Add")) {
					myActFwd = mapping.findForward("Zxszy_Add");
				} else if (doType.equals("update")) {
					myActFwd = Zxszy_Update(mapping, form, request,response);
				} else if (doType.equals("View")) {  //进行咨询师匹配
					myActFwd = Zxszy_View(mapping, form, request, response);
				} else if (doType.equals("Del")) {  //删除预约资源
					myActFwd = Zxszy_Del(mapping, form, request, response);
				} else {
					myActFwd = Index_To_Jsp(mapping, form, request,response);
				}
			} else {
				myActFwd = Index_To_Jsp(mapping, form, request, response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward Index_To_Jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		lrh_commen_util com_uti = new lrh_commen_util();
		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd"); //时间段集合
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");   //地段集合
		myform.validate(mapping, request);
		myform.setPk("XN_ID");
		myform.setTableName("XLJK_ZXSZYB");
		myform.setRealTable("");
		// ---------2010/5/26 edit by luojw --------------
		String xxdm = StandardOperation.getXxdm();
		//批量删除
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {//广州大学
			String doType = request.getParameter("doType");
			if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
				xljk_zxszy_Form myForm = (xljk_zxszy_Form) form;
				String[] checkVal = myForm.getCheckVal();
				if (checkVal != null && checkVal.length > 0) {
					String pk = "xn_id";

					SaveForm saveForm = new SaveForm();
					saveForm.setTableName("XLJK_ZXSZYB");
					saveForm.setPk(pk);
					saveForm.setPkValue(checkVal);

					boolean result = myutil.delXljk(saveForm, myform);
					request.setAttribute("result", result);
				}
			}
		}
		// --------------end ---------------
		List topTr = myutil.Get_xljk_zxszy_Title2();
		List rs = myutil.Zxszy_findby_sql2(myform);
		List zxxList = myutil.get_zxxList();
		String rsNum = String.valueOf(rs.size());
		request.setAttribute("zxxList", zxxList);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", myform.getTableName());
		request.setAttribute("pk", myform.getPk());
		request.setAttribute("realTable", myform.getRealTable());
		request.setAttribute("ddList", ddList);
		request.setAttribute("sjdList", sjdList);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("Return_To_IndexJsp");
	}

	private ActionForward Xssq_Zycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		String userType = session.getAttribute("userType").toString();
		if(!"stu".equalsIgnoreCase(userType)){
			request.setAttribute("yhInfo", "对不起，该页面只有学生用户可以访问!");
			return new ActionForward("/yhInfo.do",false);
		}
		
		lrh_commen_util com_uti = new lrh_commen_util();
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		myform.validate(mapping, request);
		myform.setTableName("xljk_zxszyb");
		myform.setZxxxm(myutil.xljk_zxsxx_findName(myform.getZxxbh()));
		List li = myutil.Zxszy_findby_sql3(myform);
		List sjdList = com_uti.get_dmwhb_dmList("zxszy_sjd");
		List ddList = com_uti.get_dmwhb_dmList("zxszy_dd");
		String rsNum = String.valueOf(li.size());
		String[] zdm = { "rq", "sjd", "dd" };
		List titles2 = myutil.Get_Table_Title("xljk_zxszyb", zdm);
		List rqList = myutil.zxszy_find_canuseDays();
		request.setAttribute("rqList", rqList);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", "xljk_zxszyb");
		request.setAttribute("realTable", "");
		request.setAttribute("pk", myform.getZxxbh());
		request.setAttribute("rs", li);
		request.setAttribute("ddList", ddList);
		request.setAttribute("sjdList", sjdList);
		request.setAttribute("topTr", titles2);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("xssqyycx");
	}

	private ActionForward Zxszy_Update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		myform.validate(mapping, request);
		return this.Index_To_Jsp(mapping, form, request, response);
	}

	private ActionForward Zxszy_View(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn_id = request.getParameter("xn_id");
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		xljk_zxxxx_zy_model mymodel = new xljk_zxxxx_zy_model();
		mymodel = myutil.Zxszy_View(xn_id);
		request.setAttribute("list", mymodel);
		return mapping.findForward("zxszy_view");
	}

	private ActionForward Zxszy_Del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn_id = request.getParameter("xn_id");
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		String message = myutil.zxszy_del(xn_id);   //删除选中的记录
		request.setAttribute("message", message);
		return this.Index_To_Jsp(mapping, form, request, response);
	}
}
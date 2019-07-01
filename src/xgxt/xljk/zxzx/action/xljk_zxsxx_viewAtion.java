package xgxt.xljk.zxzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.zxzx.form.xljk_zxsxx_Form;
import xgxt.xljk.zxzx.form.xljk_zxszy_Form;
import xgxt.xljk.zxzx.util.XLJK_ZXZX_Util;
import xsgzgl.comm.BasicModel;

import java.util.*;

/**
 * MyEclipse Struts Creation date: 06-20-2007
 */
public class xljk_zxsxx_viewAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myform.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = null;
			String doType = request.getParameter("doType");
			if (doType.equals("View_Zxsxx") || doType.equals("View_Xssqzxs")) {
				myActFwd = Zxsxx_View(mapping, form, request, response);
			} else if (doType.equals("View_Zxszy")) { // 查看咨询师信息，进行咨询师匹配
				myActFwd = Zxszy_Ppzxx(mapping, form, request, response);
			} else if (doType.equals("checkExist")){  //查看老师是否被占
				myActFwd = checkExist(mapping, form, request, response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}
	
	public ActionForward checkExist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		xljk_zxsxx_Form myform =(xljk_zxsxx_Form)form;
		BasicModel model = new BasicModel();
		boolean flag = false;
		String xn_id = request.getParameter("xn_id");
		String zxxbh = request.getParameter("zxxbh");
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		flag = myutil.findInfo(xn_id, zxxbh,model);
		if (!"".equals(xn_id)) {
			if (flag == true) {
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print("老师已安排！");
			} else {
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print("老师可用！");
			}
		}
		return null;
	}

	private ActionForward Zxsxx_View(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxsxx_Form myform =(xljk_zxsxx_Form)form;
		String doType = request.getParameter("doType");
		String zxsbh = DealString.toGBK(request.getParameter("zxsbh"));
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		String tableName = "xljk_zxsxxb";
		String pk = "zxxbh";
//		if(StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)){
//			tableName = "xljk_zxsxxb2";
//			pk = "bh";
//		}
		List li = myutil.Find_By_PrimaryKey2(tableName, pk, zxsbh);
		if (doType.equals("View_Zxsxx")) {
			String zxxmm = "1";
			request.setAttribute("mm_visable", zxxmm);
		} /*else if (doType.equals("View_Xssqzxs")) {

		}*/
		
//		if(StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)){
//			DAO dao = DAO.getInstance();
//			String[] outputValues = FormUtils.chgArrayElem2LowerCase(dao
//					.getColumnName(SQL_Util.getNoResultSql(tableName)));
//			HashMap<String,String> rs = dao.getMap(SQL_Util.
//					getQuerySqlByPKValue(tableName, pk, zxsbh), new String[]{}, outputValues);
//			request.setAttribute("rs", rs);
//			rs.put("doType", "view"); //让用户只能关闭窗口，
//			return mapping.findForward("zxsxx2_view");
//			//request.setAttribute("list", li);
//		}else{
			request.setAttribute("list", li);
//		}
		List<HashMap<String,String>>zxszcList=myutil.getZxszcList();
		String xxdm=Base.xxdm;
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			
			request.setAttribute("showZxszc", "showZxszc");
			request.setAttribute("zxszcList", zxszcList);
		}
		return mapping.findForward("zxsxx_view");
	}

	private ActionForward Zxszy_Ppzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		myform.validate(mapping, request);
		String message = request.getParameter("message");
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		String xn_id = request.getParameter("xn_id");
		String search = request.getParameter("search");
		String zxxbh = request.getParameter("zxxbh");
		if (!StringUtils.isNull(search)) {
			if (search.equals("yes")) {
				myform = myutil.Find_By_PrimaryKey("xljk_zxsxxb", "zxxbh",
						zxxbh);
				request.setAttribute("list", myform);
			}
		}
		//TODO
		// ------------2010/5/26 edit by luojw ------------------
		String xxdm = StandardOperation.getXxdm();
		xljk_zxsxx_Form myForm = (xljk_zxsxx_Form) form;
		String[] checkVal = myForm.getCheckVal();
		if (checkVal != null && checkVal.length > 0) {
			
		}
		// ---------------end-----------------------
		List zxxList = myutil.get_zxxList();
		if (!StringUtils.isNull(message)) {
			request.setAttribute("message", message);
		}
		request.setAttribute("zxxbh", myform.getZxxbh());
		request.setAttribute("zxxList", zxxList); // 发送咨询师列表
		request.setAttribute("xn_id", xn_id);
		return mapping.findForward("zxszy_pipei");
	}
}
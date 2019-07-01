package xsgzgl.rcsw.zjbb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.gygl.jqlx.GyglJqlxInit;
import xsgzgl.gygl.jqlx.GyglJqlxService;

import com.zfsoft.basic.BasicAction;

public class RcswZjbbAction extends BasicAction{

	RcswZjbbService service = new RcswZjbbService();
	
	RcswZjbbInit init = new RcswZjbbInit();
	
	// -----------------------假期留校设置 begin---------------------------
	/**
	 * 证件补办设置管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("path", "rcsw_zjbb_jbsz.do");
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bbszManage");
	
	}
	// -----------------------假期留校设置 end ---------------------------
	
	// -----------------------假期留校管理 begin---------------------------
	/**
	 * 假期留校申请管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		String userType=user.getUserType();
		
		// -----------------控制学生用户申请 --------------------
		if(!"stu".equalsIgnoreCase(userType)){
			
			String msg = "本模块只能由<font color='blue'>学生用户</font>进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ----------------显示title，判断读写权----------------
		// ----------------设置PATH begin-----------------------
		rForm.setPath("rcsw_zjbb_bbsq.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		request.setAttribute("zjbbList", service.getZjbbList());
		// ----------------- 导出表设置 ------------------------
		
		return mapping.findForward("bbsqManage");
	}
	
	/**
	 * 假期留校审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		List<HashMap<String,String>>cshXmList= service.getCshXmList(user);
		//String spgwNum=service.countSpgw(user);
		
		if(cshXmList==null || cshXmList.size()==0){
			String msg = "您目前没有需审核的项目，如有疑问请联系管理员!";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("rcsw_zjbb_bbsh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		//service.setJbszInfo(myForm);
		
		request.setAttribute("cshXmList", cshXmList);
//		request.setAttribute("spgwNum", spgwNum);
		return mapping.findForward("bbshManage");
	}

	/**
	 * 假期留校查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjbbCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("sqid");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// 学生申请信息
			stuInfo.putAll(service.getZjbbSqInfo(pkValue));
			
			request.setAttribute("xmshInfo", service.getZjbbShInfo("", pkValue, ""));
		}
		
		request.setAttribute("map", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "rcsw_zjbb_bbjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zjbbCk");
	}
	
	/**
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("rcsw_zjbb_bbjg.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_view_rcsw_zjbb_jgcx");
		return mapping.findForward("bbjgManage");
	}
	
	/**
	 * 假期留校查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("sqid");
		
		String spgw=request.getParameter("spgw");
		
		String xmid=request.getParameter("xmid");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// 学生申请信息
			stuInfo.putAll(service.getZjbbSqInfo(pkValue));
			
			request.setAttribute("xmshInfo", service.getZjbbShInfo(xmid,pkValue,spgw));
		}
		
		request.setAttribute("map", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("spgw", spgw);
		request.setAttribute("xmid", xmid);
		request.setAttribute("path", "rcsw_bbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bbshDetail");
	}
	// ---------------------假期留校管理 end -----------------------------
}

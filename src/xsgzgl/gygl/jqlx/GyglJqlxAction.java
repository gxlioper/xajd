package xsgzgl.gygl.jqlx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-18 上午11:34:03</p>
 */
public class GyglJqlxAction extends BasicAction{
	
	GyglJqlxService service = new GyglJqlxService();
	
	GyglJqlxInit init = new GyglJqlxInit();
	
	// -----------------------假期留校设置 begin---------------------------
	/**
	 * 假期留校设置管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// -----------------相关对象 begin -----------------
		RequestForm rForm = new RequestForm();
		
		GyglJqlxForm  myForm=(GyglJqlxForm )form;
		
		BasicModel model=new BasicModel();
		
		User user=getUser(request);
		// -----------------相关对象 end -----------------

		// ------------假期留校设置初始化 begin--------------		
		init.initLxszManage(rForm, model, request, user);
		
		HashMap<String,String>rs=service.viewInfo(model);
		// ------------假期留校设置初始化 begin--------------		
		
		// ------------基础设置信息加载 begin--------------	
		request.setAttribute("rs", rs);
		
		myForm.setSqkg(Base.isNull(rs.get("sqkg"))? "close" : rs.get("sqkg"));
		
		myForm.setLcid(Base.isNull(rs.get("lcid"))? "no" : rs.get("lcid"));
		// ------------基础设置信息加载 end--------------		
		
		// ------------------留校设置路径权限 begin-----------------------
		request.setAttribute("path", "jqlx_lxsz.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------留校设置路径权限 end-------------------------
		
		return mapping.findForward("lxszManage");
	
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
	public ActionForward lxsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		service.setJbszInfo(myForm);
		// -----------------相关对象 begin ---------------		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// -----------------相关对象 end -----------------	
		
		// 用户名
		String userType=user.getUserType();
		
		// -----------------控制学生拥护申请 -------------------
		if(!"stu".equalsIgnoreCase(userType)){
			
			String msg = "本模块只能由<font color='blue'>学生用户</font>进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(!"open".equalsIgnoreCase(myForm.getSqkg())){
			
			String msg = "留校申请功能已关闭无法进行申请，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ----------------显示title，判断读写权----------------
		
		// ----------------设置PATH begin-----------------------
		rForm.setPath("jqlx_lxsq.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		// 获取基本设置信息
		service.setJbszInfo(myForm);
		
		return mapping.findForward("lxsqManage");
	}
	
	/**
	 * 假期留校审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		// -----------------相关对象 begin ---------------		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// -----------------相关对象 end -----------------	
		
		// -----------获取指定用户审批岗位数 begin--------------
		String spgwNum=service.countSpgw(user);
		
		// 为0则不在审核流中 无法进行审核
		if("0".equalsIgnoreCase(spgwNum)){
			String msg = "您不在审核流程中，如有疑问请联系管理员!";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jqlx_lxsh.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// 获取基本设置信息
		service.setJbszInfo(myForm);
		request.setAttribute("spgwNum", spgwNum);
		return mapping.findForward("lxshManage");
	}

	/**
	 * 假期留校单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 主键值
		String pkValue=request.getParameter("sqid");
		// 审批岗位
		String spgw=request.getParameter("spgw");
		
		// 学生申请、审核信息相关
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// 学生申请信息
			stuInfo.putAll(service.getJqlxSqInfo(pkValue));
			
			// 学生审核信息
			request.setAttribute("xmshInfo", service.getJqlxShInfo(pkValue,spgw));
		}
		
		// 申请信息
		request.setAttribute("map", stuInfo);
		// 主键值
		request.setAttribute("pkValue", pkValue);
		// 审批岗位
		request.setAttribute("spgw", spgw);
		
		// -----------表头、读写权 begin ------------
		request.setAttribute("path", "jqlx_lxsh.do");
		FormModleCommon.commonRequestSet(request);
		// -----------表头、读写权 end ------------
		
		return mapping.findForward("lxshDetail");
	}
	
	/**
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jqlx_lxjg.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_view_gygl_jqlx_jgcx");
		// ----------------- 导入表设置 ------------------------
		
		return mapping.findForward("lxjgManage");
	}
	
	/**
	 * 假期留校查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ----------------申请ID begin--------------------
		String pkValue=request.getParameter("sqid");
		// ----------------申请ID end--------------------
		
		HashMap<String, String> rs=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// 学生申请信息
			rs.putAll(service.getJqlxSqInfo(pkValue));
			
			request.setAttribute("xmshInfo", service.getJqlxShInfo(pkValue,""));
		}
		
		request.setAttribute("map", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "jqlx_lxjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxjgCk");
	}
	
	// ---------------------假期留校管理 end -----------------------------
}

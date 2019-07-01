package xgxt.xsxx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.form.XsxxcshForm;
import xgxt.xsxx.service.XsxxXjydglService;
import xgxt.xsxx.service.XxcshglService;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/** 
 * Creation date: 02-23-2011
 * author lr 
 */
public class XxcshglAction extends BasicAction {
	
	/**
	 * 学校部门信息初始化
	 * */
	public ActionForward xxbmcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxglService xsxxglService = new XsxxglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//操作类型
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_xxbmxxlsb";
		String viewName = "xg_view_xsxx_xxbmxxlsb";
		//输出字段
		String[] outputColumn = {"bmdm","bmmc","bmpyjc","bmfdm","bmjb","bmlbmc"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//连接接口
			message = service.xxbmLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//标记为正确数据
			message = service.xxbmBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//全部标记为正确数据
			message = service.xxbmQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//删除数据
			deleteOperation(request, tableName);
		}
		
		//查询数据
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//要初始化数据的模块
		request.setAttribute("mkList", service.getInitDataModule());
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//部门类别列表
		request.setAttribute("xxbmlbList", xsxxglService.getList(GlobalsVariable.XTWH_XXBMLB_LIST));	
		//是否可初始化数据标志
		request.setAttribute("cshFlag", service.getCshFlag("xxbm"));
		return mapping.findForward("success");
	}
	
	/**
	 * 学校部门信息修改、查看详细
	 * */
	public ActionForward xxbmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxglService xsxxglService = new XsxxglService();
		String tableName = "xg_xsxx_xxbmxxlsb";
		//主键值
		String pkValue = request.getParameter("pkValue");
		//操作类型
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//修改数据
			updateOperation(request, tableName);
		}
		//根据主键查询记录
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		//部门类别列表
		request.setAttribute("xxbmlbList", xsxxglService.getList(GlobalsVariable.XTWH_XXBMLB_LIST));
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxbmUpdate");
	}
	
	
	
	/**
	 * 专业信息初始化
	 * */
	public ActionForward zyxxcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//操作类型
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_zyxxlsb";
		String viewName = "xg_view_xsxx_zyxxlsb";
		//输出字段
		String[] outputColumn = {"zydm","zymc","zyjc","bmdm","bmmc","xkmldm","zyywmc","gjzydm"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//连接接口
			message = service.zyxxLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//标记为正确数据
			message = service.zyxxBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//全部标记为正确数据
			message = service.zyxxQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//删除数据
			deleteOperation(request, tableName);
		}
		
		//查询数据
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//要初始化数据的模块
		request.setAttribute("mkList", service.getInitDataModule());
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//是否可初始化数据标志
		request.setAttribute("cshFlag", service.getCshFlag("zyxx"));
		return mapping.findForward("zyxxcsh");
	}
	
	/**
	 * 专业信息修改、查看详细
	 * */
	public ActionForward zyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String tableName = "xg_xsxx_zyxxlsb";
		//主键值
		String pkValue = request.getParameter("pkValue");
		//操作类型
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//修改数据
			updateOperation(request, tableName);
		}
		//根据主键查询记录
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyxxUpdate");
	}
	
	/**
	 * 班级信息初始化
	 * */
	public ActionForward bjxxcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//操作类型
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_bjxxlsb";
		String viewName = "xg_view_xsxx_bjxxlsb";
		//输出字段
		String[] outputColumn = {"bjdm","bjmc","bjjc","bmdm","bmmc","zydm","zymc","nj"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//连接接口
			message = service.bjxxLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//标记为正确数据
			message = service.bjxxBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//全部标记为正确数据
			message = service.bjxxQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//删除数据
			deleteOperation(request, tableName);
		}
		
		//查询数据
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//要初始化数据的模块
		request.setAttribute("mkList", service.getInitDataModule());
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//是否可初始化数据标志
		request.setAttribute("cshFlag", service.getCshFlag("bjxx"));
		return mapping.findForward("bjxxcsh");
	}
	
	
	/**
	 * 班级信息修改、查看详细
	 * */
	public ActionForward bjxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String tableName = "xg_xsxx_bjxxlsb";
		//主键值
		String pkValue = request.getParameter("pkValue");
		//操作类型
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//修改数据
			updateOperation(request, tableName);
		}
		//根据主键查询记录
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjxxUpdate");
	}
	
	/**
	 * 学生信息初始化
	 * */
	public ActionForward xsxxcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//操作类型
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_xsxxlsb";
		String viewName = "xg_view_xsxx_xsxxlsb";
		//输出字段
		String[] outputColumn = {"xh","xm","xb","xbmc","nj","xz","xydm","xymc","zydm","zymc","xjztm","sfzx"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//连接接口
			message = service.xsxxLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//标记为正确数据
			message = service.xsxxBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//全部标记为正确数据
			message = service.xsxxQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//删除数据
			deleteOperation(request, tableName);
		}
		
		//查询数据
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//要初始化数据的模块
		request.setAttribute("mkList", service.getInitDataModule());
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//是否可初始化数据标志
		request.setAttribute("cshFlag", service.getCshFlag("xsxx"));
		return mapping.findForward("xsxxcsh");
	}
	
	/**
	 * 学生信息修改、查看详细
	 * */
	public ActionForward xsxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxglService xsxxglService = new XsxxglService();
		String tableName = "xg_xsxx_xsxxlsb";
		//主键值		
		String pkValue = request.getParameter("pkValue");
		//操作类型
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//修改数据
			updateOperation(request, tableName);
		}
		//根据主键查询记录
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//路径和读写权信息
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		//列表数据
		request.setAttribute("mzList", xsxxglService.getList(GlobalsVariable.XTWH_MZ_LIST));
		request.setAttribute("zzmmList", xsxxglService.getList(GlobalsVariable.XTWH_ZZMM_LIST));
		request.setAttribute("xsccList", xsxxglService.getList(GlobalsVariable.XTWH_PYCC_LIST));
		request.setAttribute("ssList", xsxxglService.getSsList());
		request.setAttribute("xjztList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		request.setAttribute("yhList", xsxxglService.getList(GlobalsVariable.XTWH_YH_LIST));
		FormModleCommon.setNjXyZyBjList(request);
		
		return mapping.findForward("xsxxUpdate");
	}
}
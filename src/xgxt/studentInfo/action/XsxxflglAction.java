package xgxt.studentInfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.studentInfo.model.XsxxflForm;
import xgxt.studentInfo.service.XsxxflglService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生信息分流管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-04</p>
 */
public class XsxxflglAction extends BasicAction {
	
	/**
	 * 学生信息分流
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xxfl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxflglService service = new XsxxflglService();
		XsxxflForm model = (XsxxflForm)form;
		String go = request.getParameter("go");
		String type = request.getParameter("type");
		
		if("go".equalsIgnoreCase(go)){			
			String[] outputColumn = {"xh","xh", "xm","zrxy","zrzy","zrbjmc","ysbjmc","mkbjmc"};
			selectPageDataByPagination(request, model, "", "bks_xsysxjxxb", outputColumn);
		}
		if("save".equalsIgnoreCase(type)){
			String[] xhArr = {};
			model.setZrbjdm(model.getQueryequals_zrbjdm());
			HashMap<String, String[]> map = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			for(String key : map.keySet()){
				xhArr = map.get(key);
			}
			boolean result = service.saveXsxxflBatch(xhArr,model);
			request.setAttribute("result", result);
			request.setAttribute("message", result == true ? "操作成功！" : "操作失败！");
		}
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request,
				                                 model.getQueryequals_zrxydm(),
				                                 model.getQueryequals_zrzydm(),
				                                 model.getQueryequals_zrbjdm(),
				                                 model.getQueryequals_zrnj());
		return mapping.findForward("xxfl");
	}
	
	/**
	 * 学生信息分流修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xxflModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxflForm model = (XsxxflForm)form;
		XsxxflglService service = new XsxxflglService();
		List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>();
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		String message = "";
		
		if("save".equalsIgnoreCase(type)){//保存数据			
			boolean result = service.saveXsxxflOne(model);
			message = result == true ? "操作成功！" : "操作失败！";
			request.setAttribute("result", result);
		}
		
		selectPageDataByOne(request, "xsxxb", "bks_xsysxjxxb", pkValue);
		HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("rs");
		HashMap<String, String> bjMap = new HashMap<String, String>();
		
		bjMap.put("bjdm", map.get("ysbjdm"));
		bjMap.put("bjmc", map.get("ysbjmc"));
		bjList.add(bjMap);
				
		bjMap = new HashMap<String, String>();
		bjMap.put("bjdm", map.get("mkbjdm"));
		bjMap.put("bjmc", map.get("mkbjmc"));
		bjList.add(bjMap);
		
		bjMap = new HashMap<String, String>();
		bjMap.put("bjdm", map.get("zrbjdm"));
		bjMap.put("bjmc", map.get("zrbjmc"));
		bjList.add(bjMap);
		
		request.setAttribute("bjList", bjList);		
		request.setAttribute("message", message);
		return mapping.findForward("xxflModi");
	}
	
	/**
	 * 学生信息分流导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xxflExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] outputColumn = {"xh","xm","zrxy","zrxydm","zrzy","zrzydm","zrbjmc","zrbjdm","ysnj","ysxy","yszy","ysbjmc","ysxydm","yszydm","ysbjdm","mknj","mkxy","mkxydm","mkzy","mkzydm","mkbjmc","mkbjdm"};
		expPageData(request, response, "xsxxb", "bks_xsysxjxxb", outputColumn);
		return mapping.findForward("");
	}
	
}

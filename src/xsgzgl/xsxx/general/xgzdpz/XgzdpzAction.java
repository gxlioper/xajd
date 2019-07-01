package xsgzgl.xsxx.general.xgzdpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;


import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class XgzdpzAction extends BasicAction  {
	
	/**
	 * 查询修改字段信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cxXgzdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XgzdpzForm myForm = (XgzdpzForm) form;
		if(myForm.getLb()==null){
			myForm.setLb("stu");
		}
		XgzdpzService service = new XgzdpzService();
		List<HashMap<String, String>> zdflList = service.getXgzdflList();//查看一级
		List<HashMap<String, String>> zdList = service.getXgzdList(myForm);//查看二级
		String btzds = service.getBtzd(myForm);
		String zdzds = service.getZdzd(myForm);
		request.setAttribute("zdflList", zdflList);
		request.setAttribute("zdList", zdList);
		request.setAttribute("zdzds", zdzds);
		request.setAttribute("btzds", btzds);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "general_xsxx_xgzdpz.do");
		FormModleCommon.commonRequestSet(request);
		if("stu".equals(myForm.getLb())){
			return mapping.findForward("xsXgzdxx");
		}else{
			return mapping.findForward("jsXgzdxx");
		}
		
	}
	
	/**
	 * 保存修改字段信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SystemLog(description="访问学生信息-基础设置-信息修改字段设置LB:{lb}")
	public ActionForward bcXgzdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XgzdpzForm myForm = (XgzdpzForm) form;
		XgzdpzService service = new XgzdpzService();
		String lb = request.getParameter("lb");
		myForm.setLb(lb);
		boolean flag = service.bcXgzdsz(myForm);
		String message =flag?"操作成功！":"操作失败！";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

}

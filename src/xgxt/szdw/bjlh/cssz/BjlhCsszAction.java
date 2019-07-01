package xgxt.szdw.bjlh.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class BjlhCsszAction extends BasicExtendAction {
	
	public ActionForward fdykhCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//判断必须由辅导员进入该界面
		if(!"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非校级用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhCsszService service = new BjlhCsszService();
		BjlhCsszForm myForm = (BjlhCsszForm) form;	
		
		
		String doType = request.getParameter("doType");
		if (doType!=null && doType.equalsIgnoreCase("save")) {
			boolean isSuccess = service.bjlhCsszSave(myForm,request);
			if(isSuccess) 
				request.setAttribute("message1", "保存成功！");
			else
				request.setAttribute("message1", "保存失败！");
		}
		HashMap<String, String> rs = service.getCssz();
		String xn = rs.get("xn");
		if(xn == null || "".equalsIgnoreCase(xn)){
			xn = Base.currXn;
		}
		rs.put("xn", xn);
		request.setAttribute("rs", rs);		
		 
		FormModleCommon.setNdXnXqList(request);

		// write和titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_cssz.do");
		request.setAttribute("xxdm", Base.xxdm);//学校代码
		return mapping.findForward("cssz");
	}

}

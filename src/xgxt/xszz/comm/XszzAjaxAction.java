package xgxt.xszz.comm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.xszz.XszzTyForm;

import com.zfsoft.basic.BasicAction;


public class XszzAjaxAction extends BasicAction {

	/**
	 * 不可兼得项目
	 */
	public ActionForward getBkjdxm(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XszzTyForm model = (XszzTyForm) form;
		XszzCommService service = new XszzCommService();
		String[] bkjdxm = service.getBkjdxm(model.getXmdm());
		
		JSONArray ja = JSONArray.fromArray(bkjdxm);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(ja);
		return null;
	}

	
	
	/**
	 * 检查是否存在不可兼得项目
	 */
	public ActionForward checkBkjdxm(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XszzTyForm model = (XszzTyForm) form;
		XszzCommService service = new XszzCommService();
		Map<String,String> map = service.getExistsBkjdxm(model.getXh(), model.getXmdm());
		
		JSONObject jo = JSONObject.fromMap(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(jo);
		return null;
	}
}

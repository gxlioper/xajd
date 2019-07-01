package xgxt.jygl.comman;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.String.StringUtils;

/**
 * <p>就业管理</p>
 * <p>
 * 	处理Ajax请求类<br>
 * </p>
 * @author Administrator
 */
public class JyglAjaxAction extends DispatchAction {

	
	/**
	 * 根据用户单位代码获取用人单位详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYrdwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String yrdwdm = request.getParameter("yrdwdm");
		JyglService service = new JyglService();
		
		HashMap<String, String> map = 	service.getYrdw(yrdwdm);
		
		JSONObject yrdwInfo = JSONObject.fromObject(map); //转为json格式
		response.setContentType("text/html;charset=gbk"); //ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(yrdwInfo);
		return null;
	}
	
	
	
	/**
	 * 加载主管单位下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZgdwOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zgdwmc = request.getParameter("zgdw");
		JyglService service = new JyglService();
		
		if (StringUtils.isNotNull(zgdwmc)){
			zgdwmc = URLDecoder.decode(zgdwmc, "UTF-8");//new String(zgdwmc.getBytes("GBK"),"UTF-8");
		}
		
		List<HashMap<String, String>> map = service.getZgdwOption(zgdwmc);
		JSONArray zgdwList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(zgdwList);
		return null;
	}
	
	
	/**
	 * 加载隶属部门下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLsbmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zgdwmc = request.getParameter("lsbm");
		
		if (StringUtils.isNotNull(zgdwmc)){
			zgdwmc = URLDecoder.decode(zgdwmc, "UTF-8");//new String(zgdwmc.getBytes("GBK"),"UTF-8");
		}
		
		JyglService service = new JyglService();
		
		
		List<HashMap<String, String>> map = service.getLsbmOption(zgdwmc);
		JSONArray zgdwList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(zgdwList);
		return null;
	}
	
	
	
	
	/**
	 * 毕业生确认信息
	 */
	public ActionForward getBysxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("xh");
		JyglService service = new JyglService();
		
		HashMap<String,String> map = service.getBysxx(xh);
		JSONObject bysxx = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bysxx);
		return null;
	}
}

package xgxt.pjpy.comm.pjpy;

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

import xgxt.pjpy.comm.pjpy.jdsz.PjpyJdszService;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.rssz.PjpyRsszService;
import xgxt.pjpy.comm.pjpy.tjsz.PjpyTjszService;
import xgxt.pjpy.comm.pjpy.tzfwsz.PjpyTzfwszService;
import xgxt.utils.String.StringUtils;

public class PjpyAjaxAction extends DispatchAction{

	/**
	 * 条件库下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmdm = request.getParameter("xmdm");
		PjpyTjszService service = new PjpyTjszService();

		List<HashMap<String, String>> tjkList = service.getTjk(xmdm);

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(tjkList));

		return null;
	}
	/**
	 * 条件关系下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTjgx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");
		PjpyTjszService service = new PjpyTjszService();
		List<HashMap<String,String>> xmtjList = service.getXmtjList(xmdm, tjdm);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xmtjList));

		return null;
	}
	
	public ActionForward getTjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");
		PjpyTjszService service = new PjpyTjszService();
		List<HashMap<String,String>> tjzList = service.getTjzList(xmdm, tjdm);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(tjzList));

		return null;
	}
	/**
	 * 条件关系下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTjfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCommService commService = new PjpyCommService();
		List<HashMap<String, String>> tjfwList = commService.getTjfw(PjxtszModel.pjxtszModel);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(tjfwList));

		return null;
	}
	/**
	 * 项目条件列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXmtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmdm = request.getParameter("xmdm");
		PjpyTjszService service = new PjpyTjszService();

		List<HashMap<String, String>> xmtjList = service.getXmtj(xmdm);

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xmtjList));

		return null;
	}
	
	
	/**
	 * 非兼得项目代码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFjddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmdm = request.getParameter("xmdm");
		PjpyJdszService service = new PjpyJdszService();
		
		String[] fjddm = service.getFjdxm(xmdm);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(fjddm));

		return null;
	}
	
	
	
	/**
	 * 项目调整范围
	 */
	public ActionForward getTzfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmdm = request.getParameter("xmdm");
		PjpyTzfwszService service = new PjpyTzfwszService();
		
		String[] tzfwxm = service.getTzfwxm(xmdm);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(tzfwxm));

		return null;
	}
	
	/**
	 * 项目调整范围
	 */
	public ActionForward isRsszSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmdm = request.getParameter("xmdm");
		PjpyRsszService rsszService = new PjpyRsszService();
		boolean flag = false;
		
		// 获取基本评奖设置
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+xmdm);
		String szfw = xmszModel.getKzfw();
		if(!StringUtils.isNull(szfw)){
			flag = true;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);

		return null;
	}
	
	
	
	/**
	 * 评奖评优-其它信息
	 */
	public ActionForward getOtherInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmmc = request.getParameter("xmmc");
		String xh = request.getParameter("xh");

		if (StringUtils.isNotNull(xmmc)){
			xmmc = URLDecoder.decode(xmmc, "UTF-8");
		}
		
		PjpyCommService service = new PjpyCommService();
		List<HashMap<String,String>> zdxxList = service.getOtherInfo(xmmc, xh);
		
		JSONArray zdxx = JSONArray.fromObject(zdxxList);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(zdxx);
		return null;
	}
	
}

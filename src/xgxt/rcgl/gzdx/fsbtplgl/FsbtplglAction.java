package xgxt.rcgl.gzdx.fsbtplgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
/**
 * 贵州大学副食补贴批量管理
 * @author yeyipin
 */
public class FsbtplglAction extends BasicExtendAction{
	/**
	 * 副食补贴批量发放管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbtplglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// 用户名
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		FsbtplglService service = new FsbtplglService();
		FsbtplglForm model = (FsbtplglForm)form;
		
		// 批量删除操作
		if("qk".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchDel(pkValues) ? "操作成功" : "操作失败";
			request.setAttribute("message", message);
		//默认查询参数
		}else if(!"qk".equalsIgnoreCase(doType)&&!"go".equalsIgnoreCase(doType)){
			model.setNd(service.getMrxx("nd"));
			model.setYf(service.getMrxx("yf"));
			model.setBtdm(service.getMrxx("btdm"));
		}
		
		if("xy".equalsIgnoreCase(userStatus)){// 学院登陆
			model.setXydm(user.getUserDep());
		}else if("stu".equalsIgnoreCase(userStatus)){
			model.setXueh(user.getUserName());
		}
		
		Map<String, Object> map = service.getTopMap("cx");
		String[] outPutList = (String[])map.get("outputs");
		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getCxList(model, request,user, outPutList));
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		setWriteAbleAndTitle(request, "rcsw_fsbtplgl.do");
		
		request.setAttribute("fsbtList", service.getFsbtList());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("num", model.getPages().getMaxRecord());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("user", user);
		request.setAttribute("realTable", "xg_rcsw_fsbtffb");

		return mapping.findForward("fsbtplglManage");
	}
	
	/**
	 * 副食补贴批量补贴发放页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbtplglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 用户名
		User user = getUser(request);
		
		String doType = request.getParameter("doType");
		String nd = request.getParameter("nd");
		String yf = request.getParameter("yf");
		String btdm = request.getParameter("btdm");
		
		FsbtplglService service = new FsbtplglService();
		HashMap<String,String> rs = new HashMap<String,String>();
		if(nd!=null&&!"".equalsIgnoreCase(nd)){
			rs.put("nd", nd);
		}
		if(yf!=null&&!"".equalsIgnoreCase(yf)){
			rs.put("yf", yf);
		}
		if(btdm!=null&&!"".equalsIgnoreCase(btdm)){
			rs.put("btdm", btdm);
			rs.put("btje", service.getBtxx(btdm).get("btje"));
		}
		
		// 保存副食补贴
		if("save".equalsIgnoreCase(doType)){
			FsbtplglForm myForm = (FsbtplglForm)form;
			String message = service.saveFsbt(myForm,request) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("fsbtList", service.getFsbtList());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("user", user);
		request.setAttribute("rs",rs);
		
		return mapping.findForward("fsbtplglUpdate");
	}
	
	/**
	 * 副食补贴批量补贴查看页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbtplglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		if(!"".equalsIgnoreCase(pkValue)&&pkValue!=null){
			FsbtplglService service = new FsbtplglService();
			Map<String, String> map = service.getFsbtInfo(pkValue);
		
			request.setAttribute("rs", map);
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("fsbtplglView");
	}
	/**
	 * 获得单个学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		FsbtplglService service = new FsbtplglService();

		Map<String, String> map = service.getXsxx(xh,request);
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
}

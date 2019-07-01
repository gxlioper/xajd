package xsgzgl.xtwh.general.cxjgpz;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_查询结果配置
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class CxjgpzAction extends BasicExtendAction{

	/**
	 * 字段查询
	 */
	public ActionForward cxjgpzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
		model.setGnlj("xsxx_tygl_cxzxs.do");
		request.setAttribute("wpzlist", service.getWpzzdlist(model));
		request.setAttribute("ypzlist", service.getYpzzdlist(model));
		request.setAttribute("xsxxlist", service.getXsxxlist(model));
		request.setAttribute("gnlj", "xsxx_tygl_cxzxs.do");
		setWriteAbleAndTitle(request, "xtwh_cxjgpzManage.do");
		return mapping.findForward("cxjgpzManage");
	}
	
	/**
	 * 保存字段配置
	 */
	@SystemLog(description="访问学生信息-基础设置-查询结果设置-保存字段配置ZDS：{zds}")
	public ActionForward cxjgPz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
		//String cxjgs[] = request.getParameterValues("cxjg_ypzzd");
		String cxjgs[] = null;
		String zds = request.getParameter("zds");
		if(!"".equals(zds)){
			 cxjgs = zds.split("!@!");
		}
		model.setCxjg(cxjgs);
		boolean flag = service.bcCxjgpz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag?"true":"false");
		return null;
	}
	
	/**
	 * 重命名字段名称
	 */
	public ActionForward xgZdmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
	//	String doType = request.getParameter("doType");
		String zd = request.getParameter("zd");
		String gnlj = request.getParameter("gnlj");
		model.setZd(zd);
		model.setGnlj(gnlj);
		HashMap<String, String> rs = service.getZd(model);
		//request.setAttribute("rs",rs);
//		if("save".equals(doType)){
//			boolean flag = service.xgZdmc(model);
//			request.setAttribute("message",flag?"修改成功!":"修改失败!");
//		}
		//JSONArray.fromObject(rs);
		request.setAttribute("zd",zd);
		request.setAttribute("gnlj",gnlj);
		request.setAttribute("rs",rs);
		return mapping.findForward("cxjgZdxg");
	}
	
	/**
	 * 保存重命名字段名称
	 */
	@SystemLog(description="访问学生信息-基础设置-查询结果设置-保存重命名字段名称ZD：{zd}")
	public ActionForward bcxgZdmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
		String zd = request.getParameter("zd");
		String gnlj = request.getParameter("gnlj");
		model.setZd(zd);
		model.setGnlj(gnlj);
		HashMap<String, String> rs = service.getZd(model);
		request.setAttribute("rs",rs);
		
		boolean flag = service.xgZdmc(model);
		//request.setAttribute("message",flag?"修改成功!":"修改失败!");
		String message = flag ? "修改成功!":"修改失败!";
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
		
		//return cxjgpzManage( mapping,form,request,response);
		
	}
	


}

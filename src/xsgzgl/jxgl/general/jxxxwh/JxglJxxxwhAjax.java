package xsgzgl.jxgl.general.jxxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * 军训管理-基础信息维护-军训信息维护
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhAjax extends BasicAction{
	
	/**
	 * 军训信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		jxglJxxxwhService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jcxxwh_jxxxwh.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = jxglJxxxwhService.getTopTr("jxxx");
		// 结果集
		ArrayList<String[]> rsArrList = jxglJxxxwhService.jxxxCx(model);
		// 构建结果集
		String spHtml = jxglJxxxwhService.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		jxglJxxxwhService.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 获得参加年级
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCjnj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		List<HashMap<String, String>> list = jxglJxxxwhService.getCjnj();
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * 保存之前验证信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkJxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setJxmc(jxglJxxxwhService.unicode2Gbk(model.getJxmc()));
		boolean result = jxglJxxxwhService.checkJxxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 军训信息保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问军训管理-基础信息维护-军训信息维护-增加或修改保存JXMC:{jxmc},KSSJ:{kssj},JSSJ:{jssj},CJNJ:{cjnj},JXID:{jxid}")
	public ActionForward jxxxSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setJxmc(jxglJxxxwhService.unicode2Gbk(model.getJxmc()));
		String message = jxglJxxxwhService.jxxxSave(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 军训信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		String jxid = request.getParameter("pkValue");
		model.setJxid(jxid);
		HashMap<String, String> map = jxglJxxxwhService.getJxxx(model);
		JSONObject jsonObj = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	
	/**
	 * 删除之前验证信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScJxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		boolean result = jxglJxxxwhService.checkScJxxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}
	
	
	/**
	 * 军训信息删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问军训管理-基础信息维护-军训信息维护-删除PKVALUE:{pkValue}")
	public ActionForward jxxxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxxxwhService.jxxxSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	

	
	/**
	 * 军训信息 操作(运行，停止);
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxxxCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		String message = jxglJxxxwhService.jxxxCz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	
	/**
	 * 军训名单查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		model.setJxid(request.getParameter("pkValue"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		jxglJxxxwhService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxxxwh.do?method=jxmdCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = jxglJxxxwhService.getTopTr("jxmd");
		// 结果集
		ArrayList<String[]> rsArrList = jxglJxxxwhService.jxmdCx(model,request);
		// 构建结果集
		String spHtml = jxglJxxxwhService.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		jxglJxxxwhService.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 获得军训人数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJxrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		model.setCjnj(jxglJxxxwhService.unicode2Gbk(model.getCjnj()));
		HashMap<String, String> map = jxglJxxxwhService.getJxrs(model);
		JSONObject jsonObj = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * 生成军训名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scJxmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setCjnj(jxglJxxxwhService.unicode2Gbk(model.getCjnj()));
		String message = jxglJxxxwhService.scJxmd(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}    

	
	
	/**
	 * 名单操作之前验证信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScJxmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		boolean result = jxglJxxxwhService.checkScJxmd(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 军训名单删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxxxwhService.jxmdSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 参训情况操作(缓训，免训，参训)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxqkCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		//传输乱码问题
		model.setPkValue(jxglJxxxwhService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxxxwhService.cxqkCz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
}

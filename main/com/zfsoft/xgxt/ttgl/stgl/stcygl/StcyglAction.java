/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stcygl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglService;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.szdw.sygl.SyglForm;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglForm;
import com.zfsoft.xgxt.ttgl.stgl.strtsq.StrtsqForm;

public class StcyglAction extends SuperAction{
	private static final String url = "xg_ttgl_stcygl.do";
	StcyglService service = new StcyglService();
	/**
	 * @description	： 查询
	 * @author 		： CP（1352）
	 * @date 		：2018-2-5 下午04:26:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stcyglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm model = (StcyglForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stcyglList");
	}
	
	
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2018-2-7 下午04:57:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm myForm = (StcyglForm) form;
		HashMap<String,String> stxxMap = service.getStxxInfo(myForm);
		myForm.setSjly(stxxMap.get("sjly"));
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("fzrxxInfo", service.getFzrxx(myForm));
        request.setAttribute("tzsxxInfo", service.getTzsxx(myForm));
        //学生组织经费来源
        StglService stglService = new StglService();
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        request.setAttribute("xszzjflyList",xszzjflyList);
		request.setAttribute("filepath", stxxMap.get("filepath"));
		return mapping.findForward("ck");
	}
	
	
	
	/**
	 * @description	： 人员管理菜单
	 * @author 		： CP（1352）
	 * @date 		：2018-2-8 上午10:23:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ryManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm model = (StcyglForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getStRyList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("jgid",model.getJgid());
		String path = "ttgl_stcygl.do?method=ryManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ryManage");
	}
	
	
	/**
	 * @description	： 审核
	 * @author 		： CP（1352）
	 * @date 		：2018-2-8 上午10:35:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward saveSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String shzt= request.getParameter("shzt");
		String guids = request.getParameter("guids");
		String[] guidArr = guids.split(",");
		boolean result = service.saveRysh(shzt, guidArr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2018-2-8 上午11:01:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm myForm = (StcyglForm) form;
		HashMap<String,String> stxxMap = service.getSqxxInfo(myForm);
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		return mapping.findForward("sqxxck");
	}
	
	/**
	 * @description	： 社团转正申请
	 * @author 		： CP（1352）
	 * @date 		：2018-2-8 上午11:22:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StcyglForm model = (StcyglForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
				boolean result = service.saveStzz(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "ttgl_stcygl.do?method=stzz";
		HashMap<String,String> stxxMap = service.getStxxInfo(model);
		//学生组织经费来源
		StglService stglService = new StglService();
		List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
		request.setAttribute("xszzjflyList",xszzjflyList);
		model.setSjly(stxxMap.get("sjly"));
		request.setAttribute("fzrxxInfo", service.getFzrxx(model));
		request.setAttribute("tzsxxInfo", service.getTzsxx(model));
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("jgid", model.getJgid());
		request.setAttribute("path", path);
		return mapping.findForward("stzzsq");
	}
	
	
	
	
	
}

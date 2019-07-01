/**
 * @部门:学工产品事业部
 * @日期：2018-2-8 下午03:43:14 
 */  
package com.zfsoft.xgxt.hdgl.hdjdsh;

import com.zfsoft.ms.mail.util.BooleanUtils;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 活动阶段审核action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-02-08 03:43
 */
public class HdjdshAction extends SuperAction<HdxxForm, HdjdshService> {
	
	private static final String url = "hdgl_hdgl_hdjdsh.do";
	private static final String HDJDMC = "专家评审";

	private HdxxService hdxxService = new HdxxService();
	private HdjdshService hdjdshService = new HdjdshService();
	
	/**
	 * @description	： 获取审核列表
	 * @author 		： GavinShow[1426]
	 * @date 		：2018-1-18 下午04:55:22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdjdshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = hdjdshService.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "hdgl_hdgl_hdjdsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("hdjdshList");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： GavinShow[1426]
	 * @date 		：2018-1-23 上午10:18:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HdjdshService service = new HdjdshService();
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 *  是否专家团成员.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-29 16:15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward isZjtcy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		boolean result = hdjdshService.isZjtcy(model.getHdid(),user.getUserName());
		response.getWriter().print(getJsonMessageResult("该阶段只能由专家团成员审核",result));
		return null;
	}

	/**
	 *  活动阶段审核.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-09 14:18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	@SystemAuth(url = url)
	public ActionForward hdjdsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String,String> hdxxMap = hdxxService.getHdjdInfo(model);
		request.setAttribute("data", hdxxMap);
		HashMap<String,String> hdjdInfo;
		HashMap<String,String> currentHdjdInfo = hdjdshService.getCurrentHdjdInfo(model.getHdid());
		String jdid = model.getJdid();
		if(StringUtils.isNotNull(jdid)){
			hdjdInfo = hdjdshService.getHdjdInfoWithJdid(model.getHdid(),jdid);
		}else {
			hdjdInfo = currentHdjdInfo;
		}
		request.setAttribute("currentJdsx",currentHdjdInfo.get("jdsx"));
		request.setAttribute("hdjdInfo",hdjdInfo);
		List<HashMap<String, String>> hdjdList = hdjdshService.getHdjdList(model,user);
		request.setAttribute("jdList", hdjdList);
		boolean sfdfhd = hdjdshService.isExist(model.getHdid());
		request.setAttribute("sfdfhd",sfdfhd);
		return mapping.findForward("hdjdsh");
	}

	/**
	 *  获取活动阶段审核成员列表，个人或组队.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-02-10 16:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	@SystemAuth(url = url)
	public ActionForward getHdjdshCyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		List<HashMap<String, String>> hdjdshGrList = hdjdshService.getHdjdshCyList(model,user);
		JSONArray dataList = JSONArray.fromObject(hdjdshGrList);
		response.getWriter().print(dataList);
		return null;
	}

	public ActionForward sfwdf(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hdsqid = request.getParameter("hdsqid");
		String jdid = request.getParameter("jdid");
		User user = getUser(request);
		boolean result = hdjdshService.sfwdf(hdsqid,jdid,user.getUserName());
		response.getWriter().print(getJsonMessage(String.valueOf(result)));
		return null;
	}
	/**
	 *  活动成员阶段审核页面.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-06 16:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward hdcyjdshPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String,String> hdxxMap = hdxxService.getHdjdInfo(model);
		HashMap<String,String> hdjdInfo;
		String jdid = model.getJdid();
		if(StringUtils.isNotNull(jdid)){
			hdjdInfo = hdjdshService.getHdjdInfoWithJdid(model.getHdid(),jdid);
		}else {
			hdjdInfo = hdjdshService.getCurrentHdjdInfo(model.getHdid());
		}
		List<HashMap<String, String>> hdjdList = hdjdshService.getHdjdList(model,user);

		//查询已完成阶段信息列表
		String jdsx = hdjdInfo.get("jdsx");
		List<HashMap<String, String>> ywcHdjdList = hdjdshService.getYwcHdjdshCyList(model,jdsx);

		//如果报名类型是组队，获取队伍信息
		String bmlx = model.getBmlx();
		if("0".equals(bmlx)){//组队
			List<HashMap<String,String>> dwList= hdxxService.getDwList(model);
			request.setAttribute("dwList", dwList);
		}else{
			HashMap<String,String> grbmxx= hdxxService.getGrbmxx(model);
			request.setAttribute("grbmxx", grbmxx);
		}

		String sfsljx = hdjdInfo.get("sfsljx");
		if("1".equals(sfsljx)){
			List<HashMap<String,String>> jxList = hdjdshService.getJxList(model.getHdid());
			request.setAttribute("jxList", jxList);
		}
		//是否跳转阶段
 		if("1".equals(hdjdInfo.get("sftj"))){
			List<HashMap<String,String>> nextJdList = hdjdshService.getJdList(model.getHdid(),hdjdInfo.get("jdid"));
			request.setAttribute("nextJdList",nextJdList);
		}
		request.setAttribute("data", hdxxMap);
		request.setAttribute("jdList", hdjdList);
		request.setAttribute("ywcjdList",ywcHdjdList);
		request.setAttribute("hdjdInfo",hdjdInfo);

//		String jdmc = hdjdInfo.get("jdmc");
//		if(HDJDMC.equals(jdmc)){
//			return mapping.findForward("hdcyjdzjphPage");
//		}else {
			return mapping.findForward("hdcyjdshPage");
//		}
	}

	/**
	 *  阶段审核信息查看.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-23 16:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward hdcyjdshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String,String> hdxxMap = hdxxService.getHdjdInfo(model);
		HashMap<String,String> hdjdInfo;
		String jdid = model.getJdid();
		if(StringUtils.isNotNull(jdid)){
			hdjdInfo = hdjdshService.getHdjdInfoWithJdid(model.getHdid(),jdid);
		}else {
			hdjdInfo = hdjdshService.getCurrentHdjdInfo(model.getHdid());
		}
		List<HashMap<String, String>> hdjdList = hdjdshService.getHdjdList(model,user);

		//查询已完成阶段信息列表
		String jdsx = hdjdInfo.get("jdsx");
		List<HashMap<String, String>> ywcHdjdList = hdjdshService.getYwcHdjdshCyList(model,jdsx);

		//如果报名类型是组队，获取队伍信息
		String bmlx = model.getBmlx();
		if("0".equals(bmlx)){//组队
			List<HashMap<String,String>> dwList= hdxxService.getDwList(model);
			request.setAttribute("dwList", dwList);
		}else{
			HashMap<String,String> grbmxx= hdxxService.getGrbmxx(model);
			request.setAttribute("grbmxx", grbmxx);
		}

		String sfsljx = hdjdInfo.get("sfsljx");
		if("1".equals(sfsljx)){
			List<HashMap<String,String>> jxList = hdjdshService.getJxList(model.getHdid());
			request.setAttribute("jxList", jxList);
		}

		//该阶段审核信息
		HashMap<String,String> hdjdshInfo = hdjdshService.getHdjdShInfo(model);
		request.setAttribute("hdjdshInfo",hdjdshInfo);

		request.setAttribute("data", hdxxMap);
		request.setAttribute("jdList", hdjdList);
		request.setAttribute("ywcjdList",ywcHdjdList);
		request.setAttribute("hdjdInfo",hdjdInfo);

		return mapping.findForward("hdcyjdshView");
	}

	public ActionForward saveDf(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		boolean result = hdjdshService.saveDf(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	/**
	 *  阶段审核通过.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-06 16:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward jdshtg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		boolean result = hdjdshService.jdshtg(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}

	/**
	 *  阶段审核退回.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-03-12 17:06
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward jdshth(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		boolean result = hdjdshService.jdshth(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
}

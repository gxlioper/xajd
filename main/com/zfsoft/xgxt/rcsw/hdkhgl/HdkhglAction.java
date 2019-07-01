/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午04:50:20 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-18 下午04:50:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HdkhglAction extends SuperAction<HdkhglForm, HdkhglService> {
	private HdkhglService service = new  HdkhglService();
	private TxhdXmjgService txhdservice  = new TxhdXmjgService();
	private final String HDKHGL ="hdkhgl";
	
	private static final String url = "rcsw_hdkhgl_jlygx.do";
	
	//考勤登记查询
	@SystemAuth(url = url)
	public ActionForward getHddjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HdkhglForm model = (HdkhglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_hdkhgl_hddj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getHddjList");
	}
	
	//记录与感想查询
	@SystemAuth(url = url)
	public ActionForward getJlyGxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HdkhglForm model = (HdkhglForm) form;
	    User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("usertype", user.getUserType());
		String path = "rcsw_hdkhgl_jlygx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getJlyGxList");
	}
	
	//学生考勤登记状态维护
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsKqdjWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		HashMap<String, String> xskqdj = service.getXsHdKhxx(hdgl);
		request.setAttribute("xskqdj", xskqdj);
		request.setAttribute("sfcj", xskqdj.get("sfcj"));
		OptionUtil opt = new OptionUtil();
		request.setAttribute("kqdj",opt.getOptions("KQDJ") );
		return mapping.findForward("kqdj");
	}
	
	//保存学生考勤登记信息
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveKqdj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		boolean result = service.savekqdj(hdgl);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//学生记录与感想维护
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsJlGxWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		HashMap<String, String> xsjlgxxx = service.getXsHdKhxx(hdgl);
		request.setAttribute("xsjlgxxx", xsjlgxxx);
		HashMap<String, String> hdxxMAP = txhdservice.getOneHdjgList(hdgl.getHdjgid());
		request.setAttribute("data", hdxxMAP);
		return mapping.findForward("jlygx");
	}
	
	//保存学生记录与感想维护
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXsjlgx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		boolean result = true;
		result = service.runUpdate(hdgl);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 学生活动考勤结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HdkhglForm model = (HdkhglForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

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
	
	//学生记录与感想维护查看
	@SystemAuth(url = url)
	public ActionForward XsJlGxWhview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		if(null!=hdgl){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(hdgl.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HDKHGL);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> xsjlgxxx = service.getXsHdKhxx(hdgl);
		HashMap<String, String> hdxxMAP = txhdservice.getOneHdjgList(hdgl.getHdjgid());
		request.setAttribute("data", hdxxMAP);
		request.setAttribute("xsjlgxxx", xsjlgxxx);
		return mapping.findForward("viewjlygx");
	}
	
	//学生考勤登记信息查看
	@SystemAuth(url = url)
	public ActionForward kqdjview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		if(null!=hdgl){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(hdgl.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HDKHGL);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> xskqdj = service.getXsHdKhxx(hdgl);
		HashMap<String, String> hdxxMAP = txhdservice.getOneHdjgList(hdgl.getHdjgid());
		request.setAttribute("data", hdxxMAP);
		request.setAttribute("xskqdj", xskqdj);
		request.setAttribute("sfcj", xskqdj.get("sfcj"));
		return mapping.findForward("ck");
	}
	
	//学生考勤登记状态批量维护
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsKqdjPlWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionUtil opt = new OptionUtil();
		request.setAttribute("kqdj",opt.getOptions("KQDJ") );
		request.setAttribute("flag", "dgdj");
		return mapping.findForward("plkqdj");
	}
	
	//保存批量学生考勤登记
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXsKqdjPlWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdkhglForm hdgl = (HdkhglForm) form;
		boolean result = service.savePlsh(hdgl);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}

/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 下午05:21:24 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 计分项目(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 下午05:21:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfJfxmwhAction extends SuperAction<ZhfJfxmwhForm, ZhfJfxmwhService>{
	private static final String url = "xg_zjly_zhfxmwh.do";
	private ZhfJfxmwhService service = new ZhfJfxmwhService();
	private ZhfService zhfService = new ZhfService();
	
	@SystemAuth(url = url)
	public ActionForward getZhfJfxmwhList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
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
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "xg_zjly_zhfxmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZhfXmmkwhList.jsp");
	}
	
	/** 
	 * @描述:计分项目增加(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 上午11:29:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZhfJfxm (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> xmmkList = zhfService.getXmmkList();
		request.setAttribute("xmmkList", xmmkList);
		return mapping.findForward("addZhfJfxm");
	}
	
	/** 
	 * @描述:保存计分项目(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午02:00:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJfxm (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		if(model.getType().equals("save")){
			if(service.isExist(model)){
				String messageKey = MessageKey.ZJLY_ZHF_XMWH_JFXM_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
//				String jfxmdm = UniqID.getInstance().getUniqIDHash();
				String jfxmdm = service.getnewjfxmdm();
				model.setJfxmdm(jfxmdm);
				boolean result = service.runInsert(model);
				String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}else{
			if(service.isExist(model)){
				String messageKey = MessageKey.ZJLY_ZHF_XMWH_JFXM_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				boolean result = service.runUpdate(model);
				String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}
	}
	
	/** 
	 * @描述:计分项目修改跳转页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午02:31:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZhfJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		ZhfJfxmwhForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if(model.getXdfs()!= null){
			request.setAttribute("xdfs", model.getXdfs());
		}
		List<HashMap<String, String>> xmmkList = zhfService.getXmmkList();
		request.setAttribute("xmmkList", xmmkList);
		return mapping.findForward("updateZhfJfxm");
	}
	 
	/** 
	 * @描述:删除计分项目(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午02:52:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delZhfJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("values");
		if (!StringUtil.isNull(ids)) {
			String[] idss = ids.split(",");
				int num = service.runDelete(idss);
				boolean result = num > 0;
				String message = result ? MessageUtil.getText(
						MessageKey.SYS_DEL_NUM, num) : MessageUtil
						.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));			
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/** 
	 * @描述:得到授权部门列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午03:45:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward getBmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
//			CommService comService = new CommService();
//			SearchModel searchModel = comService.getSearchModel(request);
//			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageListForSq(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "zhf_xmsq.do");
		String jfxms = request.getParameter("jfxms");
		request.setAttribute("jfxms", jfxms);
		return mapping.findForward("getBmList");
	}
	
	/** 
	 * @描述:保存授权(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-28 下午06:56:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jfs= request.getParameter("jfxms");
		String bms = request.getParameter("bmdms");
		String[] jfxms = jfs.split(",");
		String[] bmdms = bms.split(",");
		boolean result = service.jfxmSq(bmdms, jfxms);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/** 
	 * @描述:取消授权(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-16 下午02:25:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jfs= request.getParameter("jfxms");
		String bms = request.getParameter("bmdms");
		String[] jfxms = jfs.split(",");
		String[] bmdms = bms.split(",");
		boolean result = service.jfxmQx(bmdms, jfxms);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/** 
	 * @描述:兼得设置(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午08:48:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("jdsz");
	}
	
	/** 
	 * @描述:保存兼得设置(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午09:31:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJdsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		String jdszContent = model.getJdszContent();
		String[] jfxmmcs = jdszContent.split(",");
		if(jfxmmcs.length<2){
			response.getWriter().print(getJsonMessageByKey(MessageKey.ZJLY_ZHF_XMWH_JFXM_JDSZ_ERROR));
		}else{
			boolean result = service.jdsz(jfxmmcs);
			String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.ZJLY_ZHF_XMWH_JFXM_JDSZ_ERROR;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}
	
	//
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-29 上午10:38:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;	
	}
}

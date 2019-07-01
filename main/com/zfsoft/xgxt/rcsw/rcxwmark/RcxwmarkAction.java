/**
 * @部门:学工产品事业部
 * @日期：2016-10-28 下午02:44:12 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-10-28 下午02:44:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwmarkAction extends SuperAction<RcxwmarkForm, RcxwmarkService> {
	
	RcxwmarkService service = new RcxwmarkService();
	/**
	 * 
	 * @描述:未处理查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-31 上午09:03:09
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
	public ActionForward  getWclCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwmarkForm markform = (RcxwmarkForm)form;
		if (QUERY.equalsIgnoreCase(markform.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			markform.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(markform, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("cxlx", "wclcx");
		String path = "xg_rcsw_rcxwmark_wcl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wclcx");
	}
	
	/**
	 * 
	 * @描述:已处理查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-31 上午09:03:16
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
	public ActionForward  getYclCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwmarkForm markform = (RcxwmarkForm)form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(markform.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			markform.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getYclList(markform, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "xg_rcsw_rcxwmark_ycl.do";
		
		request.setAttribute("cxlx", "yclcx");
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		String writeAble = ("1").equals(service.getWriteAble(user.getUserName(), "xg_rcsw_rcxwmark_wcl.do").get("dxq")) ? "yes" : "no";
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("title","评奖评优-综合测评-其他奖项设置" );
		return mapping.findForward("yclcx");
	}
	
	/**
	 * 
	 * @描述: 设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-1 下午06:46:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward Sz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("xnndList",Base.getXnndList());
		request.setAttribute("rcxwjgids", request.getParameter("rcxwjgids"));
		return mapping.findForward("sz");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 修改
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-1 下午06:48:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RcxwmarkForm markform = (RcxwmarkForm)form;
		RcxwmarkForm model = service.getModel(markform);
		request.setAttribute("xnndList",Base.getXnndList());
		request.setAttribute("rcxwjgid",markform.getRcxwjgid());
		BeanUtils.copyProperties(markform, xgxt.utils.String.StringUtils.formatData(model));
		return mapping.findForward("xg");
	}
	
	/**
	 * 
	 * @描述:查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-1 下午06:49:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String type =  request.getParameter("type");
		HashMap<String, String> rs = new HashMap<String, String>();
		if(("yclcx").equals(type)){
			String id  = request.getParameter("id");
			rs = service.getCkData(id);
			
		}else{
			String rcxwjgid = request.getParameter("rcxwjgid");
			rs = service.getCkDataWcl(rcxwjgid);
		}
		request.setAttribute("type", type);
		request.setAttribute("rs", rs);
		return mapping.findForward("ck");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:取消设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-1 下午06:50:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward qxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.PJPY_QTSZ_QXCG) : MessageUtil
					.getText(MessageKey.PJPY_QTSZ_QXSB);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 导出方法
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-2 上午09:05:07
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RcxwmarkForm markform = (RcxwmarkForm)form;
		String type = markform.getType();
		User user = getUser(request);
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		markform.setSearchModel(searchModel);
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>();
		//以标志位type来区分：wclcx:未处理，yclcx:已处理
		if("wclcx".equals(type)){
			 dataList = service.getAllList(markform, user);
		}else{
			 markform.getPages().setPageSize(Integer.MAX_VALUE);
			 dataList = service.getYclList(markform, user);
		}
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = markform.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(dataList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存设置结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-2 上午11:34:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveSzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		RcxwmarkForm markform = (RcxwmarkForm)form;
		String type = markform.getType();
		User user = getUser(request);
		boolean flag = true;
		if("add".equals(type)){
			String[] rcxwjgids = request.getParameter("rcxwjgids").split(",");
			if(null != rcxwjgids ){
				if(rcxwjgids.length == 1){
					markform.setRcxwjgid(rcxwjgids[0]);
					markform.setCzr(user.getUserName());
					flag = service.runInsert(markform);
				}else{
					flag = service.insertData(rcxwjgids, markform.getJxdm(), markform.getPjxn(), markform.getBz(), user.getUserName());
				}
			}
		}else{
			flag = service.runUpdate(markform);
		}
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}

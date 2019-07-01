/**
 * @部门:学工产品(1)部
 * @日期：2017-5-22 下午05:42:24 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 评奖评优-我的评奖-奖项审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-22 下午05:42:24 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmshAction extends SuperAction{
	private static final String url = "xpjpy_wdpj_pjsh.do";
	private XmshService service = new XmshService();
	
	/**
	 * @描述: 跳转到项目申请列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-22 下午08:27:33
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项荣誉审核-查询页面")
	public ActionForward getXmshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*参数设置信息*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		/*默认查询条件 ,当前周期的评奖数据*/
		XmwhService xmwhService = new XmwhService();
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{xmwhService.getCsszMap().get("xn")});
		request.setAttribute("searchTj", searchModel);
		/*返回path*/
		String path = "xpjpy_wdpj_pjsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmshList");
	}
	
	/**
	 * @描述: 获取项目申请列表JSON数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-23 下午12:03:25
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项荣誉审核-查询返回Json数据")
	public ActionForward getXmshListDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmshForm model = (XmshForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		/*查询并返回JSON数据*/
		List<HashMap<String, String>> resultList = service.getAudingListSingle(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 批量审核
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-24 下午01:57:03
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项荣誉审核-审核")
	public ActionForward xmshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { 	 	 
 		request.setAttribute("id", request.getParameter("id"));
		return mapping.findForward("xmshPlsh");
	}
	 
	/** 	
	 * @描述: 审核明细
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-24 下午02:26:33
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项荣誉审核-审核页面明细")
	public ActionForward xmshShmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmshForm model = (XmshForm)form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> viweShmx = service.getXmshShmx(model,user);
		JSONArray dataList = JSONArray.fromObject(viweShmx);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 审核操作，1：通过  2：不通过  3：退回
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-25 下午03:30:44
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项荣誉审核-审核保存")
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmshForm model = (XmshForm)form;
		User user = getUser(request);
		System.out.println("开始时间【"+System.currentTimeMillis()+"】");
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		String message = service.saveZdPlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		System.out.println("结束时间【"+System.currentTimeMillis()+"】");
		return null;
	}
	
	/**
	 * @描述: 奖项审核撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 上午11:26:14
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项审核-撤销保存-SHID:{shid}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmshForm model = new XmshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("splc");
		String xmdm = request.getParameter("xmdm");
		String xh = request.getParameter("xh");
		model.setSplc(splc);
		model.setShid(shid);
		model.setXmdm(xmdm);
		model.setXh(xh);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * @描述: 最后级的撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-26 下午03:25:48
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splcid = request.getParameter("splcid");
		String shid = request.getParameter("shid");
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		// 业务回滚
		boolean result = service.cancel(splcid, shxx.get("ywid"),user);
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @描述: 项目审核导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-16 下午05:57:00
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
	public ActionForward xmshExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmshForm model = (XmshForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getAudingListSingle(model,user);
		/*导出功能代码*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*当前操作员*/
		exportModel.setZgh(user.getUserName());
		/*设置数据*/
		exportModel.setDataList(resultList);
		/*设置当前导出功能编号*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*生成导出文件*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 下午01:36:54 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszService;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmService;

/** 
 * @系统名称: 班级工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 竞赛分数
 * @作者：xiaxia [工号：1104]
 * @时间： 2016-4-19 下午01:36:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfjsFswhAction extends SuperAction {
	
	private static final String url = "xpjpy_bfjs_fswh.do";
	private BfjsCsszService csszService = new BfjsCsszService();
	private BfjsJsxmService jsxmService = new BfjsJsxmService();
	/**
	 * 
	 * @描述: 班级竞赛情况列表
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午10:29:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward viewBjjsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (QUERY.equals(BfjsFswhForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getJsbjList(BfjsFswhForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BfjsCsszModel csszModel = csszService.getModel();
		//加载没有子项目的综测项目
		List<HashMap<String,String>> jsxmList = jsxmService.getEditBfjsJsxmByBjdm();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "xpjpy_bfjs_fswh.do");
		request.setAttribute("jsxmList", jsxmList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBjjsList");
	}
	
	/**
	 * 
	 * @描述: 保存竞赛分数
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 下午03:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-班风竞赛-竞赛维护-录入-更新班级Bjdm：{Bjdm}")
	public ActionForward saveBfjsFswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		User user = getUser(request);
		
		boolean result = service.saveBfjsFswh(BfjsFswhForm, user);
		
		if (!result){
			//如果失败，则提示
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 检测是否可提交竞赛分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 下午06:26:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel bfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		bfjsFswhForm.setSearchModel(searchModel);
		
		String values = request.getParameter("id");
		
		boolean isCanSubmit = service.isCanSubmit(values,bfjsFswhForm,user);
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	
	public ActionForward isHaveSubmitClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsFswhService service = new BfjsFswhService();
		BfjsCsszModel csszModel = csszService.getModel();
		
		boolean isHaveSubmitClass = service.isHaveSubmitClass(csszModel.getXn(), csszModel.getXq());
		response.getWriter().print(isHaveSubmitClass);
		return null;
	}
	
	/**
	 * 
	 * @描述: 提交班级竞赛分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 下午06:53:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-班风竞赛-竞赛维护-提交竞赛分ID：{id}")
	public ActionForward submitBjJsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel bfjsFswhForm = (BfjsFswhModel) form;
		String values = request.getParameter("id");
		String tjzt = request.getParameter("tjzt");
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		bfjsFswhForm.setSearchModel(searchModel);
		
		boolean result = service.submitBfjsFswh(values,tjzt,bfjsFswhForm,user);
		
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 竞赛分查看-导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午09:14:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewJsfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		File file = service.getBjJsfFile(BfjsFswhForm,user);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * 
	 * @描述: 下载导入模版
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午08:54:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		File file = service.createImportTemplate(BfjsFswhForm, user);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 竞赛分导入
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午10:35:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward toImportJsfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		return mapping.findForward("toImportJsfs");
	}

	/**
	 * 
	 * @描述: 竞赛分数导入
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午10:36:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-班风竞赛-竞赛维护-录入-导入ID：{id}")
	public ActionForward importJsfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		
		try {
			File file = service.importBfjsFswh(BfjsFswhForm,user);
			if (file != null){
				FileUtil.outputExcel(response, file);
				return null;
			}
			request.setAttribute("result", true);
			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
		} catch (SystemException e) {
			request.setAttribute("result", false);
			request.setAttribute("message", e.getMessage());
		}
		
		return toImportJsfs(mapping, BfjsFswhForm, request, response);
		
	}

	/**
	 * 
	 * @描述:取消竞赛分查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午11:20:16
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
	public ActionForward viewJsfqxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (QUERY.equals(BfjsFswhForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getJsfqxList(BfjsFswhForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BfjsCsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "xpjpy_bfjs_qxtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJsfqxList");
	}
	
	
			
	/**
	 * 
	 * @描述:改变取消提交状态，取消已提交的记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午11:22:30
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
	@SystemLog(description="访问评奖评优-班风竞赛-取消提交竞赛分-取消提交ID：{values}")
	public ActionForward cancelTj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (UPDATE.equals(BfjsFswhForm.getDoType())){
			BfjsFswhService service = new BfjsFswhService();
			
			User user = getUser(request);
			boolean result = service.cancelTj(user,BfjsFswhForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		
		return mapping.findForward("cancelTj");
		
	}
	
	
	/**
	 * 
	 * @描述:竞赛结果查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 下午04:02:46
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
	@SystemAuth(url = "xpjpy_bfjs_pfjg.do")
	public ActionForward viewJsfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (QUERY.equals(BfjsFswhForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getJsfjgList(BfjsFswhForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BfjsCsszModel csszModel = csszService.getModel();
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		request.setAttribute("searchTj", searchModel);
		BfjsFswhForm.setSearchModel(searchModel);
		//竞赛项目列表
		List<HashMap<String,String>> jsxmList = jsxmService.getFirstAndSecondBfjsJsxm(BfjsFswhForm);
		request.setAttribute("jsxmList", jsxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "xpjpy_bfjs_pfjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJsfjgList");
	}
	
	
	public ActionForward initJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsFswhModel BfjsFswhForm = new BfjsFswhModel();
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		BfjsCsszModel csszModel = csszService.getModel();
		String Jszq= request.getParameter("jszq");
		if(null==xn||"".equals(xn)){
			xn=csszModel.getXn();
		}
			//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setSearch_tj_xn(xn.split("!!@@!!"));
		searchModel.setSearch_tj_xq(xq.split("!!@@!!"));
		BfjsFswhForm.setSearchModel(searchModel);
		BfjsFswhForm.setJszq(Jszq);
		//竞赛项目列表
		List<HashMap<String,String>> JsxmList = jsxmService.getFirstAndSecondBfjsJsxm(BfjsFswhForm);
		JSONArray dataList = JSONArray.fromObject(JsxmList);
		response.getWriter().print(dataList);
		
		
		
		
		
		return null;
	}
	
	/**
	 * 
	 * @描述:竞赛结果导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19 上午11:43:51
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
	@SystemAuth(url = "xpjpy_bfjs_pfjg.do")
	public ActionForward exportJsfjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			
			BfjsFswhService service = new BfjsFswhService();
			BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
			
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			File file = service.getJsfjgFile(BfjsFswhForm,user);
			//导出文件
			FileUtil.outputExcel(response, file);
			return null;
			
	}


	/**
	 * 
	 * @描述:查看是否还有未提交记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-19下午03:31:38
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
	
	public ActionForward checkSubmitInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
	
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		User user = getUser(request);
		boolean isCanSubmit = service.isSubmitInfo(BfjsFswhForm,user );
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	

	
}

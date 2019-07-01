/**
 * @部门:学工产品事业部
 * @日期：2013-7-23 下午01:36:54 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cpxz.CpxzService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分数
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-23 下午01:36:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcfsAction extends SuperAction {
	
	private static final String url = "pj_jgcx.do";
	
	/**
	 * 
	 * @描述: 是否有班级提交了综测分数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午01:50:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward isHaveSubmitClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		boolean isHaveSubmitClass = service.isHaveSubmitClass(csszModel.getXn(), csszModel.getXq());
		response.getWriter().print(isHaveSubmitClass);
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 班级综测情况列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午10:29:06
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
	public ActionForward viewBjzcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			
			//查询
			List<HashMap<String,String>> resultList = service.getZcbjList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcflr.do");
		/*xg_pjpy_new_cspzb szyf = 1 屏蔽提交人，提交状态*/
	    request.setAttribute("szyf", csszService.getCsz("szyf"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBjzcList");
	}
	
	
	
	/**
	 * 
	 * @描述: 综测分数录入
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午11:22:47
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
	@Deprecated
	public ActionForward editZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//将默认项目和接口同步项目分数插入分数记录表
			service.initDefaultZcfs(csszModel.getXn(), csszModel.getXq(), zcfsForm.getId());
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		
		//班级信息
		HashMap<String,String> bjMap = service.getBjxxById(zcfsForm.getId());
		
		//加载没有子项目的综测项目
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdm(bjMap.get("bjdm"));
		
		request.setAttribute("bjxxMap",bjMap);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcflr.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("editZcfs");
	}
	

	/**
	 * 
	 * @描述: 综测分数批量录入
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午11:22:47
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
	public ActionForward editZcfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		if (QUERY.equals(zcfsForm.getDoType())){
			
			//将默认项目和接口同步项目分数插入分数记录表
			service.initDefaultZcfs(zcfsForm, user);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//班级信息
		List<HashMap<String, String>> bjList = service.getBjxxByIds(zcfsForm, user);
		
		//加载没有子项目的综测项目
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdms(bjList,zcfsForm, user);
		
		//上一层高级查询条件
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		List<HashMap<String, String>> djList = service.getDj();// 等级
		
		if("10264".equalsIgnoreCase(Base.xxdm)) {
			service.getIntefaceDataAll(zcfsForm, null, user);
		}
		
		request.setAttribute("djList", JSONArray.fromObject(djList));
		request.setAttribute("bjList",bjList);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		String szyf = csszService.getCsz("szyf");
		request.setAttribute("path", "pj_zcflr.do");
		FormModleCommon.commonRequestSet(request);
		//按月录入综测分
		if("1".equals(szyf)){
			request.setAttribute("szyf", szyf);
			request.setAttribute("zcyfList", zcxmService.getZcYf(csszModel.getXn(), csszModel.getXq()));
			// 按月提交暂时只支持单个班级提交
			request.setAttribute("yftjztlist", service.getYfTjzt(bjList.get(0).get("bjdm"), csszModel.getXn(), csszModel.getXq()));
			request.setAttribute("bjdm", bjList.get(0).get("bjdm"));
			return mapping.findForward("editZcfsOfYf");
		}
		
		return mapping.findForward("editZcfs");
	}
	
	/**
	 * 
	 * @描述: 保存综测分数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午03:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-更新学生XH：{xh}")
	public ActionForward saveZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		boolean result = service.saveZcfs(zcfsForm, user);
		
		if (!result){
			//如果失败，则提示
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 检测是否可提交综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午06:26:25
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
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		boolean isCanSubmit = service.isCanSubmit(zcfsForm);
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 循环调用单个检测 ,以代替批量，后续有空可以考虑优化
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午06:26:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward plCheckIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		if(StringUtils.isBlank(zcfsForm.getId())){
			
			System.out.println("id 为空");
			throw new SystemException(MessageKey.SYS_SUBMIT_FAIL);
			
		}else{
			
			String[] mess = service.isNotCanSubmit(zcfsForm, user);
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 提交班级综测分
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午06:53:22
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-提交综测分ID：{id}")
	public ActionForward submitBjzcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		boolean result = service.submitZcfs(zcfsForm, user);
		
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	

	/**
	 * 
	 * @描述: 查看综测分数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-25 上午09:29:59
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
	public ActionForward viewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//参数设置
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		//班级信息
		HashMap<String,String> bjMap = service.getBjxxById(zcfsForm.getId());
		//综测项目
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdm(bjMap.get("bjdm"));
		
		request.setAttribute("bjxxMap", bjMap);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		String szyf = csszService.getCsz("szyf");
		if("1".equals(szyf)){
		request.setAttribute("szyf", szyf);
		request.setAttribute("zcyfList", zcxmService.getZcYf(csszModel.getXn(), csszModel.getXq()));
		return mapping.findForward("viewZcfsOfYf");
		}
		return mapping.findForward("viewZcfs");
	}
	
	
	/**
	 * 
	 * @描述: 综测分查看-导出
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-6 上午09:14:47
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
	public ActionForward exportViewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		File file = service.getBjZcfFile(zcfsForm,user);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	
	
	
	
	
	

	/**
	 * 
	 * @描述: 下载导入模版
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 上午08:54:42
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
	
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		File file = service.createImportTemplate(zcfsForm, user);
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * 针对所有学生下载导入模板
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadTemplateForAll(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);

		File file = service.createImportTemplateForAll(zcfsForm);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 综测分导入
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 上午10:35:27
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
	public ActionForward toImportZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		request.setAttribute("zcyf", request.getParameter("zcyf"));
		return mapping.findForward("toImportZcfs");
	}

	
	
	/**
	 * 
	 * @描述: 综测分数导入
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 上午10:36:17
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-导入ID：{id}")
	public ActionForward importZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		try {
			File file = service.importZcfs(zcfsForm,user);
			
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
		
		return toImportZcfs(mapping, zcfsForm, request, response);
		
	}


	
	/**
	 * 
	 * @描述: 同步接口数据
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-29 下午06:55:37
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-同步ID：{id}")
	public ActionForward getIntefaceData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		boolean result = false;
		if("10279".equals(Base.xxdm) || "13779".equals(Base.xxdm) || "10868".equals(Base.xxdm) || "13011".equals(Base.xxdm)) {
			result = service.getIntefaceDataS(zcfsForm, zcfsForm.getXmdms(), user);
		}else if("13022".equals(Base.xxdm)) {
			if("1".equals(request.getParameter("nzcs"))){
				result = service.getIntefaceDataXysp(zcfsForm, zcfsForm.getXmdms(), user);
			}else if("2".equals(request.getParameter("nzcs"))) {			
				result = service.getIntefaceDataSxdd(zcfsForm, zcfsForm.getXmdms(), user);
			}
		}
//		else if("13011".equals(Base.xxdm)){
//			result = service.getIntefaceDatazcf(zcfsForm, user);
//		}
		else if("12673".equals(Base.xxdm)){
			result = service.getIntefaceDataNmgdz(zcfsForm, user);
		}else if("12942".equals(Base.xxdm)){
			result = service.getIntefaceDataNmgzc(zcfsForm, user);
		}else if("13431".equals(Base.xxdm)){
			result = service.getIntefaceData_13431(zcfsForm, user);
			//result = service.getIntefaceDataNmgzc(zcfsForm, user);
		}else {
			result = service.getIntefaceData(zcfsForm, zcfsForm.getXmdm(), user);
		}
		
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SYNC_SUCCESS : MessageKey.SYS_SYNC_FAIL));
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:取消综测分查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 上午11:20:16
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
	public ActionForward viewZcfqxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getZcfqxList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcfqx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZcfqxList");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 取消综测分记录查询
	 * @作者：cq [工号：785]
	 * @日期：2013-8-1 下午02:56:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewQxjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询综测分取消记录
			List<HashMap<String,String>> resultList = service.getZcfqxjlList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_qxjl.do");
		request.setAttribute("szyf", csszService.getCsz("szyf"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewQxjlList");
	}
	
			
	/**
	 * 
	 * @描述:改变取消提交状态，取消已提交的记录
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 上午11:22:30
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
	@SystemLog(description="访问评奖评优-综合测评-取消提交综测分-取消提交ID：{id}")
	public ActionForward cancelTj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (UPDATE.equals(zcfsForm.getDoType())){
			ZcfsService service = new ZcfsService();
			
			User user = getUser(request);
			
			boolean result = service.cancelTj(user,zcfsForm);
			String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
					: MessageKey.SYS_QXCP_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		
		return mapping.findForward("cancelTj");
		
	}
	
	
	/**
	 * 
	 * @描述:综测结果查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 下午04:02:46
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
	public ActionForward viewZcfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getZcfjgList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		String zczq = csszService.getCsz("zczq");	//综测周期
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		if(!StringUtil.isNull(zczq)&&CsszService.PJFS_XQ.equals(zczq)){
			searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		}
		request.setAttribute("searchTj", searchModel);
		zcfsForm.setSearchModel(searchModel);
		ZcxmService zcxmService = new ZcxmService();
		//综测项目列表
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(zcfsForm);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_jgcx.do");
		request.setAttribute("zczq", zczq);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZcfjgList");
	}
	
	
	public ActionForward initZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		ZcxmService zcxmService = new ZcxmService();
		ZcfsModel zcfsForm = new ZcfsModel();
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		String zczq= request.getParameter("zczq");
		if(null==xn||"".equals(xn)){
			xn=csszModel.getXn();
		}
			//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setSearch_tj_xn(xn.split("!!@@!!"));
		searchModel.setSearch_tj_xq(xq.split("!!@@!!"));
		zcfsForm.setSearchModel(searchModel);
		zcfsForm.setZczq(zczq);
		//综测项目列表
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(zcfsForm);
		JSONArray dataList = JSONArray.fromObject(zcxmList);
		response.getWriter().print(dataList);
		
		
		
		
		
		return null;
	}
	
	/**
	 * 空的list
	 */
	public void addBlankList(List<HashMap<String,String>> list, int blankSize){
		for (int i = 0 ; i < blankSize ; i++){
			list.add(new HashMap<String, String>());
		}
	}
	
	/**
	 * 打印Word登记表（按学号）
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getDjbWordStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsModel model = (ZcfsModel) form;
		File wordFile = getWordStu(model.getXn(), model.getXq(), model.getXh());
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 登记表ZIP（按学号）
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getDjbZipStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xns = request.getParameter("xns");
		String xqs = request.getParameter("xqs");
		String xhs =request.getParameter("xhs");
		if (StringUtils.isNotBlank(xhs)){
			String[] xnArr = xns.split(",");
			String[] xqArr = xqs.split(",");
			String[] xhArr = xhs.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = xhArr.length ; i < n ; i++){
				File file = getWordStu(xnArr[i],xqArr[i],xhArr[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	// 填充模版数据生成word文件（按学号）
	private File getWordStu(String xn,String xq,String xh) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		ZcfsService service = new ZcfsService();
		PjjgService pjjgService = new PjjgService();
		//获取学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = "";
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xq)){
				xqmc = map.get("xqmc");
				break;
			}
		}
		// 基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		// 上学期所有成绩
		List<HashMap<String,String>> cjb_sxqBxList = service.getCjListByXhXnXq(xh, xn, "01", "");
		// 下学期所有成绩
		List<HashMap<String,String>> cjb_xxqBxList = service.getCjListByXhXnXq(xh, xn, "02", "");
		//获取不及格成绩数量
		String bjgcjs = pjjgService.getBjgcjNum(xh, xn, "");
		// 江苏信息职业技术学院
		if("13108".equals(Base.xxdm)){
			addBlankList(cjb_sxqBxList, 9 - cjb_sxqBxList.size());
			cjb_sxqBxList = cjb_sxqBxList.subList(0, 9);
			addBlankList(cjb_xxqBxList, 9 - cjb_xxqBxList.size());
			cjb_xxqBxList = cjb_xxqBxList.subList(0, 9);
			// 按学年、学期、学号查询所有综测项目分
			List<HashMap<String,String>> zcfListAllXh = service.getZcfListAllByBjdm_13108("", xh, xn, xq);
			if(zcfListAllXh.size() > 0){
				data.putAll(zcfListAllXh.get(0));
			}
		}
		
		data.put("xsxx", xsjbxx);
		data.put("bjgcjs", bjgcjs);
		data.put("xn", xn);
		data.put("xqmc", xqmc);
		data.put("cjb_sxqBxList", cjb_sxqBxList);
		data.put("cjb_xxqBxList", cjb_xxqBxList);
		
		String fileName = xh +"["+xsjbxx.get("xm")+"]" + "-";
		String mbmc = Base.xxdm + ".xml";
		// 江苏信息职业技术学院
		if("13108".equals(Base.xxdm)){
			fileName = fileName + xn + xqmc + "学生综合素质测评汇总表";
			mbmc = "xszhszcphzb_" + mbmc;
		}
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", mbmc, fileName);
		return wordFile;
	}
	
	/**
	 * 打印Word登记表（按班级）
	 */
	public ActionForward getDjbWordBjdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsModel model = (ZcfsModel) form;
		File wordFile = getWordBjdm(model.getXn(), model.getXq(), model.getBjdm());
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	// 填充模版数据生成word文件（按班级）
	private File getWordBjdm(String xn,String xq,String bjdm) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		ZcfsService service = new ZcfsService();
		//获取学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = "";
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xq)){
				xqmc = map.get("xqmc");
				break;
			}
		}
		// 按学年、学期、班级代码查询班级信息
		HashMap<String,String> bjxx = service.getBjxx(bjdm, xn, xq);
		// 江苏信息职业技术学院
		if("13108".equals(Base.xxdm)){
			// 按学年、学期、班级代码查询所有综测项目分
			List<HashMap<String,String>> zcfListAllBjdm = service.getZcfListAllByBjdm_13108(bjdm, "", xn, xq);
			data.put("zcfListAllBjdm", zcfListAllBjdm);
			addBlankList(zcfListAllBjdm, 30 - zcfListAllBjdm.size());
			// 按学年、学期、班级代码查询综测项目平均分
			HashMap<String,String> zcfAvgByBjdm = service.getZcfAvgByBjdm_13108(bjdm, xn, xq);
			data.put("zcfAvgByBjdm", zcfAvgByBjdm);
		}
		
		data.put("xn", xn);
		data.put("xqmc", xqmc);
		data.put("bjxx", bjxx);
		
		String fileName = bjxx.get("bjmc") + "-";
		String mbmc = Base.xxdm + ".xml";
		// 江苏信息职业技术学院
		if("13108".equals(Base.xxdm)){
			fileName = fileName + xn + xqmc + "学生综合素质测评结果汇总表";
			mbmc = "xszhszcpjghzb_" + mbmc;
		}
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", mbmc, fileName);
		return wordFile;
	}
	
	/**
	 * 
	 * @描述:综测结果导出
	 * @作者：cq [工号：785]
	 * @日期：2013-8-17 上午11:43:51
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
	public ActionForward exportViewZcfsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			
			ZcfsService service = new ZcfsService();
			ZcfsModel zcfsForm = (ZcfsModel) form;
			
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			File file = service.getZcfjgFile(zcfsForm,user);
			//导出文件
			FileUtil.outputExcel(response, file);
			return null;
			
	}
	/**
	 * 浙江机电职业技术学院 导出综测详表
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewZcfsjg_12861(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getZcfjgFile_12861(zcfsForm,user);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 *  学生成绩汇总表.
	 *  <p>江苏省徐州医药高等职业学校</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-06 15:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportCjhzb(ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) throws Exception {

		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		User user = getUser(request);
		//查询出所有记录，不分页
		List<HashMap<String,String>> resultList = service.getXscjList(zcfsForm, user);

		BjcjhzModel bjcjhzModel = new BjcjhzModel().addAll(resultList);
		File file = service.getCjhzbFile(bjcjhzModel);
		if(file == null){
			request.setAttribute("message","暂无导出数据！" );
			return new ActionForward("/prompt.do",false);
		}
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:取消综测导出
	 * @作者：cq [工号：785]
	 * @日期：2013-8-15 上午09:18:00
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel model = (ZcfsModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 查询
		List<HashMap<String,String>> resultList = service.getZcfqxjlList(model,user);//查询出所有记录，不分页

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
	 * 
	 * @描述:查看取消提交状态记录
	 * @作者：cq [工号：785]
	 * @日期：2013-8-15 上午09:27:32
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
	public ActionForward qxjlView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel model = (ZcfsModel) form;
		CsszService csszService = new CsszService();
		HashMap<String, String>qxjlInfor = service.getQxjl(model.getId());
		
		request.setAttribute("qxjlInfor", xgxt.utils.String.StringUtils.formatDataView(qxjlInfor));
		request.setAttribute("szyf", csszService.getCsz("szyf"));
		
		return mapping.findForward("qxjlView");
	}
	

	/**
	 * 
	 * @描述:查看是否还有未提交记录
	 * @作者：cq [工号：785]
	 * @日期：2015-2-9 下午03:31:38
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
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
	
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		User user = getUser(request);
		boolean isCanSubmit = service.isSubmitInfo(zcfsForm,user );
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:验证下载条数
	 * @作者：cq [工号：785]
	 * @日期：2015-3-4 上午11:03:07
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
	public ActionForward checkDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		String num = request.getParameter("num");
		String zcyf = request.getParameter("zcyf");
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		
		boolean isCanDownload = service.isCanDownload(zcfsForm, user, num);
		
		response.getWriter().print(isCanDownload);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-24 下午01:46:36
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
	public ActionForward exportDataNeW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		String id = request.getParameter("id");
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		ZcxmDao zcxmDao = new ZcxmDao();
		//根据ID取得班级代码和名称
		List<HashMap<String, String>> bjxxMap = service.getBjxxByIds(zcfsForm, user);
		
		CsszDao csszDao = new CsszDao();
		List<HashMap<String,String>> zcxmList = null;
		
		if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
			//综测项目级别为 年级
			zcxmList = zcxmDao.getEditZcxmByNj(bjxxMap,zcfsForm,user);
		} else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
			//综测项目级别是为 院系
			zcxmList = zcxmDao.getEditZcxmByXy(bjxxMap,zcfsForm,user);
		} else {
			zcxmList = zcxmDao.getNoChildZcfxm();
		}
		// 查询
		ZcfsDao dao = new ZcfsDao();
		zcfsForm.setCxlx("dr");
		zcfsForm.setEditType("editType");
		List<HashMap<String, String>> resultList = dao.getPageList(zcfsForm, zcxmList, user);
		
		// 导出功能代码
		File file = service.createImportTemplateDc(resultList, zcxmList,user);
		FileUtil.outputExcel(response, file);
		return null;
	} 
	
	
	/**
	 * 
	 * @描述: 成都市技师学院按月提交个性化
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-6 上午10:31:46
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
	public ActionForward plCheckIsCanSubmityf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		if(StringUtils.isBlank(zcfsForm.getId())){
			
			System.out.println("id 为空");
			throw new SystemException(MessageKey.SYS_SUBMIT_FAIL);
			
		}else{
			
			String[] mess = service.isNotCanSubmitYf(zcfsForm, user);
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:成都市技师学院综测分取消
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-6 下午01:44:42
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
	public ActionForward viewZcfqxListYf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getZcfqxListYf(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcfqxyf.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZcfqxListYf");
	}
	
	/**
	 * 
	 * @描述: 取消按月提交综测分查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-6 下午03:08:41
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
	public ActionForward viewZcfsOfyf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			
			User user = getUser(request);
			//查询
			zcfsForm.setDoType("aytjzcf");
			List<HashMap<String,String>> resultList = service.getPageListOfYf(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//参数设置
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		//班级信息
		HashMap<String,String> bjMap = service.getBjxxById(zcfsForm.getId());
		//综测项目
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdm(bjMap.get("bjdm"));
		
		request.setAttribute("bjxxMap", bjMap);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		String szyf = csszService.getCsz("szyf");
		request.setAttribute("szyf", szyf);
		request.setAttribute("zcyfList", zcxmService.getZcYf(csszModel.getXn(), csszModel.getXq()));
		request.setAttribute("flag", "aytjzcf");
		return mapping.findForward("viewZcfsOfYf");
	}
	
	/**
	 * 
	 * @描述:取消按月提交综测分
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-6 下午03:38:34
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
	public ActionForward cancelTjofYf(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (UPDATE.equals(zcfsForm.getDoType())){
			ZcfsService service = new ZcfsService();
			
			User user = getUser(request);
			
			boolean result = service.cancelTjOfYf(user,zcfsForm);
			String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
					: MessageKey.SYS_QXCP_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		//参数设置
		CsszService csszService = new CsszService();
		request.setAttribute("szyf",csszService.getCsz("szyf"));
		return mapping.findForward("cancelTj");
		
	}
	
	/**
	    * 
	    * @描述: 成都市技师学院按月提交统计查询
	    * @作者：yxy[工号：1206]
	    * @日期：2016-9-7 上午11:33:19
	    * @修改记录: 修改者名字-修改日期-修改内容
	    * @param xn
	    * @param xq
	    * @return
	    * List<HashMap<String,String>> 返回类型 
	    * @throws
	    */
	public ActionForward getZcfAyTjTjcxList (ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CsszService csszService = new CsszService();
		CsszModel csszmodel = csszService.getModel();
		List<HashMap<String, String>> zcaytjlist = new ZcfsService().getZcfAyTjTjcxList(csszmodel.getXn(), csszmodel.getXq());
		request.setAttribute("zcaytjlist", zcaytjlist);
		request.setAttribute("xn", csszmodel.getXn()+"学年");
		request.setAttribute("xqmc", new DAO().getXqmcForXqdm(csszmodel.getXq()));
		return mapping.findForward("zcfaytjtjcx");
	}
	
	/**
	 * 
	 * @描述:提交统计查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-7 下午03:51:52
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
	public ActionForward viewBjzcListCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		String yf = request.getParameter("yf");
		String tjzt = request.getParameter("tjzt");
		if (QUERY.equals(zcfsForm.getDoType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			
			List<HashMap<String,String>> resultList = service.getZcbjListCk(zcfsForm,user,tjzt,yf);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcflr.do");
		/*xg_pjpy_new_cspzb szyf = 1 屏蔽提交人，提交状态*/
	    request.setAttribute("szyf", csszService.getCsz("szyf"));
	    request.setAttribute("tjzt", tjzt);
	    request.setAttribute("yf", yf);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBjzcListck");
	}
	
	/**
	 * 
	 * @描述: 成都市技师学院提交情况导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-7 下午05:39:03
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
	public ActionForward exportDataDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsModel model = (ZcfsModel) form;
		String yf = request.getParameter("yf");
		String tjzt = request.getParameter("tjzt");

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = new ZcfsService().getZcbjListCk(model, user, tjzt, yf);
		
		//因为学历名称和在外居住原因只能获取代码值，因此这里循环遍历set值

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
	 * @描述：参评组参评名单初始化
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月27日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="评奖评优-综合测评-综测维护-参评名单参评组初始化")
	public ActionForward initCpzCpmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		String messageKey;
		if("zf01".equals(user.getUserName())){
			CpxzService cpxzService = new CpxzService();
			boolean result = cpxzService.initCpzCpmd(user);
			messageKey = result ? MessageKey.SYS_INIT_SUCCESS:MessageKey.SYS_INIT_FAIL;
		}else{
			messageKey = "非管理员用户不允许操作";
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-综合测评-综测维护-同步ID：{id}")
	public ActionForward getIntefaceData_tbf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		boolean result = false;
		String type = request.getParameter("type");
		result = service.getIntefaceData_12688(type, zcfsForm, zcfsForm.getXmdms(), user);
		

		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SYNC_SUCCESS : MessageKey.SYS_SYNC_FAIL));
		
		return null;
	}

	/**
	 * 同步学分成绩
	 */
	public ActionForward tbXfcj(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcfsService service = new ZcfsService();
		if(service.tbXfcj(getUser(request))){
			response.getWriter().print(MESSAGE_SUCCESS);
		}else {
			response.getWriter().print(MESSAGE_FAIL);
		}
		return null;
	}
	
}

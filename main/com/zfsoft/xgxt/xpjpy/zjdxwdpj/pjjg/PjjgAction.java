/**
 * @部门:学工产品1部
 * @日期：2017-3-13 下午01:57:28 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq.RysqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_我的评奖_评奖结果
 * @作者：  Meng.Wei[工号:1186]
 * @时间： 2017-3-13 下午01:57:28 
 * @版本：V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjjgAction extends SuperAction {
	private static final String url = "xpjpy_wdpj_pjjg.do";
	private static final String PJPY = "pjpy";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
		jbxxList = bdpzService.getJbxxpz(PJPY);
	}
	
	/**
	 * @描述: 评奖结果列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-13 下午03:58:14
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
	public ActionForward getPjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjjgService service = new PjjgService();
		PjjgForm model = (PjjgForm) form;
		if(QUERY.equals(model.getType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			/*查询*/
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*默认高级查询条件-参数设置中的学年*/
		XmwhService xmmwService = new XmwhService();
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {xmmwService.getCsszMap().get("xn")});
		request.setAttribute("searchTj", searchModel);
		String path = "xpjpy_wdpj_pjjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listPjjg");
	}
	
	/**
	 * @描述: 增加页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-13 下午05:26:15
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
	public ActionForward addPjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			/*获取该学生最近一次申请信息*/
			HashMap<String,String> latestSqxx = service.getLatestSqxx(model.getXh());
			request.setAttribute("latestSqxx",latestSqxx);
		}
		String path = "xpjpy_pjjg.do?method=addPjjg";
		request.setAttribute("path", path);
		/*学年list*/
		request.setAttribute("xnList", Base.getXnndList());
		/*项目类型list*/
		List<HashMap<String, String>> xmlx = service.getXmlx();
		request.setAttribute("xmlxList", xmlx);
		/*项目性质list*/
		List<HashMap<String, String>> xmxz = service.getXmxz();
		request.setAttribute("xmxzList", xmxz);
		/*当前学年*/
		XmwhService xmmwService = new XmwhService();
		model.setXn(xmmwService.getCsszMap().get("xn"));
		/*当前时间*/
		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		/*学生基本信息显示配置*/
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("addPjjg");
	}
	
	/**
	 * @描述: 评奖数据增加保存
	 * @作者：  Meng.Wei[工号:1186]
	 * @日期：2017-3-14 上午11:27:26
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-增加-XMMC:{xmmc},XH:{xh},XN:{xn}LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward saveFormAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		/*唯一性判断（学号，学年，项目名称不能重复）*/
		boolean isExist = service.isExistByPjjgAdd(model);
		if (!isExist) {
			/*保存时增加用户名*/
			model.setLrr(user.getUserName());
			/*保存时增加数据来源(数据来源   0:导入、1:申请流、 2:后台增加)*/
			model.setSjly("2");
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
		}
		return null;
	}
	
	/**
	 * @描述: 删除评奖结果记录，流程数据不允许删除修改
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2013-8-8 上午10:11:03
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-删除-VALUES:{values}")
	public ActionForward delPjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgService service = new PjjgService();
		/*获得id*/
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @描述: 生成【优秀学生】荣誉称号
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-6 上午11:36:05
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
	public ActionForward scyxxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgService service = new PjjgService();
		boolean result = service.scyxxs();
		String message = result ? "生成优秀学生成功！" : "生成优秀学生失败！";
		getJsonMessage(message);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * @描述: 修改页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午01:44:41
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
	public ActionForward updatePjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		PjjgForm updateForm = service.getModel(model);
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xpjpy_pjjg.do?method=updatePjjg";
		request.setAttribute("path", path);
		/*学年list*/
		request.setAttribute("xnList", Base.getXnndList());
		/*项目类型list*/
		List<HashMap<String, String>> xmlx = service.getXmlx();
		/*项目性质list*/
		List<HashMap<String, String>> xmxz = service.getXmxz();
		request.setAttribute("xmlxList", xmlx);
		request.setAttribute("xmxzList", xmxz);
		/*返回学生基本信息*/
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updatePjjg");
	}
	
	/**
	 * @描述: 修改保存方法
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午01:45:15
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-修改-XMMC:{xmmc},XH:{xh},XN:{xn}LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward saveFormUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		/*唯一性判断（学号，学年，项目名称不能重复）*/
		boolean isExist = service.isExistByPjjgUpdate(model);
		if (!isExist) {
			/*保存时增加用户名*/
			model.setLrr(user.getUserName());
			/*保存时增加数据来源(数据来源   0:导入、1:申请流、 2:后台增加)*/
			model.setSjly("2");
			boolean result = service.runUpdate(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(
					getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
		}
		return null;
	}
	
	/**
	 * @描述: 点击学号单个查看
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:50:18
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
	public ActionForward viewPjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {
			/*加载学生基本信息*/
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(),model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*查询结果*/
			request.setAttribute("rs", StringUtils.formatData(service.getOnePjjgList(model.getId())));
			return mapping.findForward("viewPjjg");
		} else {
			return updatePjjg(mapping, form, request, response);
		}
	}
	
	/**
	 * 
	 * @描述: 评奖结果导出
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2013-8-14 下午03:41:50
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
		
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();

		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
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
	
	/**
	 * @描述: 评奖选择学生
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-22 上午11:03:44
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgForm model = (PjjgForm)form;
		PjjgService service = new PjjgService();
		if (QUERY.equals(model.getType())) {
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			/*查询*/
			List<HashMap<String, String>> resultList = service.getZjXs(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String sqlx = request.getParameter("sqlx");
		String path = "xpjpy_pjjg.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath+"&sqlx="+sqlx);
		return mapping.findForward("showStudents");
	}
}

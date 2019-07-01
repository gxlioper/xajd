/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:28:43 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk.XlwjyjkService;
import com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh.XlwtlxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 下午01:28:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlxlxsglAction extends SuperAction {

	private YlxlxsglService service = new YlxlxsglService();
	/**
	 * @描述 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * 预警库service
	 */
	private XlwjyjkService yjkServic = new XlwjyjkService();
	
	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "xljk_xlwjyjgl_ylxlxsgl.do";
	
	public static final String url = "xljk_xlwjyjgl_ylxlxsgl.do";
	
	public static final String PATH_MTLR = "xljk_xlwjyjgl_mtlrglgl.do";

	/**
	 * @描述 审核流操作接口
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @属性： BBID 表单配置id
	 */
	private static final String BBID = "xlzx_xlwy"; 
	
	/**
	 * 心理类型维护service
	 */
	private XlwtlxwhService xlwtlxwhService = new XlwtlxwhService();
	/**
	 * @描述 ：初始化学生信息表单参数列表
	 */
	public YlxlxsglAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(BBID);
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward cx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述:面谈页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward mtlrcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH_MTLR);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mtlrcx");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		model.setMtzgh(user.getUserName());
		model.setMtzghmc(user.getRealName());
		//问题类型
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//关注等级
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_ylxlxsglwh.do?method=xz", "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xz");
	}
	
	
	/**
	 * 
	 * @描述:新增保存
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xzAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		
		
		if(service.checkExist(model.getXh())){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XLZXWZDX_XSSQ_EXIST));
			return null;
		}
		
		boolean isSuccess = service.runInsert(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:面谈录入页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward mtlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		BeanUtils.copyProperties(model, service.getModel(model.getXh()));
		
		//问题类型
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//关注等级
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlr", "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("mtlr");
	}
	
	/**
	 * 
	 * @描述:面谈录入
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward mtlrAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model = (YlxlxsglForm) form;
		User user = getUser(request);
		model.setMtzgh(user.getUserName());
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午03:52:58
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
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xhs = request.getParameter("xhs"); //带删除的xhs
		
		if(xhs == null)
			xhs = "";
		
		int isSuccess = service.runDelete(xhs.split(","));
		
		String message = isSuccess >= 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:查看
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 上午09:11:20
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
	public ActionForward mtlrck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxlxsglForm model  = (YlxlxsglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			HashMap<String , String> yjkxx =  yjkServic.xlwjyjkxsxx(model.getXh());
			HashMap<String , String> ylxlxsxx =  service.ylxlxsxx(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			request.setAttribute("yjkxx", yjkxx);
			request.setAttribute("ylxlxsxx", ylxlxsxx);
		}
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlrck" , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("mtlrck");
	}
	
	
	/**
	 * 
	 * @描述:提交页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xhs = request.getParameter("xhs"); //带提交的xhs
		if(xhs == null)
			xhs = "";
		
		//学号
		request.setAttribute("xhList", xhs);
		request.setAttribute("rkrs", xhs.split(",").length);
		//问题类型
		request.setAttribute("xlwtList", xlwtlxwhService.getAllXlwtList());
		//关注等级
		request.setAttribute("gzdjList", new OptionUtil().getOptions(OptionUtil.ABC));
		request.setAttribute("path", URLEncoder.encode("xljk_xlwjyjgl_wgwtwh.do?method=tj", "gbk"));
		return mapping.findForward("tj");
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-30 下午04:59:15
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
		YlxlxsglForm model = (YlxlxsglForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
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

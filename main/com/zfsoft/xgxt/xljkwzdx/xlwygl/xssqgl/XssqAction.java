/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午10:02:16 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

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
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生授权管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午10:02:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XssqAction extends SuperAction {
	
	XssqService service = new XssqService();
	
	/**
	 * @描述 学生显示信息表单服务
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "xljk_xlwygl_xssqgl.do";
	
	public static final String url = "xljk_xlwygl_xssqgl.do";
	
	/**
	 * @属性： CDGL_BBID 表单配置id
	 */
	private static final String BBID = "xlzx_xlwy"; 
	
	/**
	 * @描述 ：初始化学生信息表单参数列表
	 */
	public XssqAction() {
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
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
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
		XssqForm model = (XssqForm) form;
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
		XssqForm model = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		String lx = request.getParameter("lx");
		model.setLx(lx);
		String to = "";
		if(StringUtils.isEqual(lx, "1")){
			to = "xz_tsxs";
			model.setSfxypssb("1");
			request.setAttribute("isnot", new OptionUtil().getOptions(OptionUtil.ISNOT));
		}else{
			model.setRzksrq(DateUtils.getCurrDate());
			to = "xz_bjwy";
		}
		
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xssqglwh.do?method=xz&lx="+model.getLx() , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(to);
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
		XssqForm model = (XssqForm) form;
		boolean isSuccess = false;
		JSONObject message = null;
		
		if(service.checkExist(model.getXh(), model.getLx())){
			message = getJsonMessageByKey(MessageKey.XLZXWZDX_XSSQ_EXIST);
		}else{
			isSuccess = service.runInsert(model);
			message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		}
		
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
		
		String pks = request.getParameter("pks"); //带删除的sqids
		
		if(pks == null)
			pks = "";
		
		int isSuccess = service.del(pks.split(","));
		
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssqForm model  = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		HashMap<String , String> modelData = service.getModelData(model.getXh() , model.getLx());
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		String to = "";
		if(StringUtils.isEqual(model.getLx(), "1")){
			to = "ck_tsxs";
		}else{
			to = "ck_bjwy";
		}
		request.setAttribute("modelData", StringUtils.formatData(modelData)); 
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xssqglwh.do?method=ck&lx="+model.getLx() , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(to);
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssqForm model = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		XssqForm modelData = service.getModel(model.getXh(), model.getLx());
		if(modelData != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(modelData));
		}
		String to = "";
		if(StringUtils.isEqual(model.getLx(), "1")){
			to = "xg_tsxs";
			request.setAttribute("isnot", new OptionUtil().getOptions(OptionUtil.ISNOT));
		}else{
			to = "xg_bjwy";
		}
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xssqglwh.do?method=xg&lx="+model.getLx() , "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(to);
	}
	
	
	/**
	 * 
	 * @描述:修改保存
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssqForm model = (XssqForm) form;
		boolean isSuccess = false;
		JSONObject message = null;
		isSuccess = service.updateModel(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
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
		XssqForm model = (XssqForm) form;
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

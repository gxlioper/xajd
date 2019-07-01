/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:39:45 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为结果
 * @作者：dlq [工号：995]
 * @时间： 2013-8-7 下午04:39:45
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RcxwjgAction extends SuperAction {
	//定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "rcswrcxw";
	private static List<HashMap<String, String>> jbxxList = null;

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		
	}
	
	private static final String url = "rcsw_rcxwwh_rcxwjg.do";

	/**
	 * 
	 * 查询日常行为结果集
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward rcxwjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询获取日常行为结果数据
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//------------------设置高级查询默认值-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		//浙江警官职业学院
		if("12869".equals(Base.xxdm) && model != null && model.getXh() != null){
			searchModel.setInput_mhcx(model.getXh());
			searchModel.setSearch_tj_xq(null);
			searchModel.setSearch_tj_xn(null);
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwh_rcxwjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwjgManage");
	}
	/**
	 * 
	 * @描述:日常行为大类分汇总
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-13 下午03:29:34
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
	public ActionForward getXwdlfList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询获取日常行为大类分结果数据
			List<HashMap<String, String>> resultList = service.getXwdlfList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		RcxwdmwhService rcxwdmwhService = new RcxwdmwhService();
		List<HashMap<String,String>> xwdlList = rcxwdmwhService.getRcxwdlList();
		//------------------设置高级查询默认值-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("xwdlList", xwdlList);
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwh_tjcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXwdlfList");
	}

	/**
	 * 
	 * 增加日常行为结果
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为结果-增加XH:{xh},XWDLDMARR:{xwdldmArr},XWLBDMARR:{xwlbdmArr},FZARRAY:{fzArray},FSSJARR:{fssjArr},XN:{xn},XQ:{xq}")
	public ActionForward addXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// 唯一性判断（学号，学年，学期）
			// boolean isExist=service.isExistByXwxxwh(model,SAVE);
			boolean isExist = false;
			if (!isExist) {
				// 添加日常行为结果
				model.setJlr(user.getUserName());
				Hashtable files = model.getMultipartRequestHandler().getFileElements();
				String[] xwlbdmArr=model.getXwlbdmArr();
				String warnMessage="";
				for(int i=0;i<xwlbdmArr.length;i++){
					//处理附件
					FormFile file = (FormFile) files.get("lbfj"+i);
					if(null!=file&&file.getFileSize() > 1024*1024*5){
						if(i!=0){
							warnMessage+="、";	
						}
						warnMessage+=file.getFileName();
					}
				}
				if(""!=warnMessage){
					String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,warnMessage);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				super.resetToken(request);
				boolean result = service.saveXwjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {

				response.getWriter().print(
						getJsonMessage(MessageKey.XSZZ_KNSJG_RESULT_REPEAT));
				return null;
			}
		}

		String path = "rcsw_rcxwwh_rcxwjggl.do?method=addXwjg";
		request.setAttribute("path", path);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		//行为大类集合
		request.setAttribute("xwdlList", service.getXwdlList(request));
		//行为类别集合
		request.setAttribute("xwlbList",
				new ArrayList<HashMap<String, String>>());
		//当前学年
		model.setXn(Base.currXn);
		//当前学期
		model.setXq(Base.currXq);
		//当前日常行为记录时间
		model.setRcxwjlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("nowTime", GetTime.getTimeByFormat("yyyy-mm-dd"));
		this.saveToken(request);
		return mapping.findForward("addXwjg");
	}

	/**
	 * 
	 * 修改日常行为结果
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为结果-修改ID:{id},XN:{xn},XQ:{xq},RCXWLBDLDM:{rcxwlbdldm},RCXWLBDM:{rcxwlbdm},FZ:{fz},FSSJ:{fssj}")
	public ActionForward updateXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号，学年，学期）
			// boolean isExist=service.isExistByXwxxwh(model,SAVE);
			boolean isExist = false;
			if (!isExist) {
				// 修改日常行为结果
				Hashtable files = model.getMultipartRequestHandler().getFileElements();
				FormFile file = (FormFile) files.get("lbfj");
				if(null!=file&&file.getFileSize() > 1024*1024*5){
					String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,file.getFileName());
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				boolean result = service.updateXwjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {

				response.getWriter().print(
						getJsonMessage(MessageKey.XSZZ_KNSJG_RESULT_REPEAT));
				return null;
			}
		}
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		//行为大类集合
		request.setAttribute("xwdlList", service.getXwdlList(request));
		RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
		List<HashMap<String, String>> xwlbList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> xwlbxxList = rcxwxxwhService.getXwlbxx(request, model.getRcxwlbdm());
		request.setAttribute("xwlbxx", xwlbxxList!=null&&xwlbxxList.size()>0?xwlbxxList.get(0):null);
		//获取行为类别集合
		xwlbList = service.getXwlbList(request.getParameter("rcxwlbdldm"),
				request);
		request.setAttribute("xwlbList", xwlbList);

		RcxwjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updateXwjg");
	}

	/**
	 * 
	 * 删除行为记录
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 上午11:42:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为结果-删除VALUES:{values}")
	public ActionForward delXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwjgService service = new RcxwjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//删除行为结果中的数据
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;

			if (result) {
				RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
				//删除行为维护中的关联数据
				rcxwxxwhService.delRcxwwhFromRcxwjg(values.split(","));
			}

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
	 * 查看单条日常行为结果信息
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 下午01:43:48
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
	public ActionForward viewXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//日常行为参数配置
			HashMap<String,String> cspzMap = service.getCspz();
			request.setAttribute("zq",cspzMap.get("zq"));
			//查询单个行为信息结果
			request.setAttribute("rs", StringUtils.formatData(service.getOneXwjgList(model.getId())));
			//历史行为记录
			request.setAttribute("rsArrList", service.getMoreXwjgList(model,cspzMap));
			//学生基本信息
			request.setAttribute("jbxxList", jbxxList);
			if(Base.xxdm.equals("12867")){
				return mapping.findForward("viewXwjgZjly");
			}else{
				return mapping.findForward("viewXwjg");
			}
			
			
		} else {
			return updateXwjg(mapping, form, request, response);
		}
		
	}
	/**
	 * 
	 * @描述:行为大类分明细
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-14 下午07:02:08
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
	public ActionForward viewXwdljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
	
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//日常行为参数配置
			HashMap<String,String> cspzMap = service.getCspz();
			request.setAttribute("zq",cspzMap.get("zq"));
			//历史行为记录
			request.setAttribute("rsArrList", service.getMoreXwjgList(model,cspzMap));
			//学生基本信息
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewXwdljg");

		
	}
	
	/**
	 * 自定义导出设置
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 下午01:43:26
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
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();

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
		if(Base.xxdm.equals("10355")){
			File newFile = new File(file.getParent(),"全部学生本科学生综合素质评价.xls");
			FileUtils.copyFile(file,newFile);
			file.deleteOnExit();
			FileUtil.outputExcel(response, newFile);
		}else{
			FileUtil.outputExcel(response, file);
		}
		return null;
	}
	
	//中国美院导出个性化
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData_10355(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();

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
	/** 
	 * @描述: 判断信息是否重复
	 * @作者：HongLin[工号：707]
	 * @日期：2014-2-24 下午05:44:20
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
	public ActionForward rcxwxxSfcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xwlbStr = request.getParameter("xwlbStr");
		String fssjStr = request.getParameter("fssjStr");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		//行为大类集合
		RcxwjgService service = new RcxwjgService();
		String message = service.getRcxwxxSfcf(request,xh,xn,xq,xwlbStr,fssjStr);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @描述:温大文明品行实践课评定导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-12-1 下午02:08:47
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
	public ActionForward xsPxsjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwh_rcxwjggl.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.xsPxsjDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	
	/**下载附件*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm myForm = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		
		RcxwjgForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(model.getFjmc(),"utf-8")); 
				FileUtil.outputFile(response, file);
			}
		}
		
		return null;
	}
	
	/**删除附件*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为结果-删除附件FJLJ:{fjlj}")
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwjgForm myForm = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		
		RcxwjgForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				file.delete();
			}
			model.setFjlj("");
			service.runUpdate(model);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 医高专素质测评分导出
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-20 下午05:12:41
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
	public ActionForward rcxwsjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwh_rcxwjggl.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.rcxwsjDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @描述:日常行为分统计（天津体育）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-13 上午10:53:43
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
	public ActionForward rcxwtjbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwh_rcxwjggl.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.rcxwtjbDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rcxwdlfDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.rcxwdlfDc(exporModel,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	/**
	 * @描述:青岛滨海学院个性化，思想品德成绩汇总导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月30日 下午5:30:46
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
	public ActionForward sxpdcjhzDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getSxpdcjhzList(model, user);//查询出所有记录，不分页
		
		File file = service.getSxpdcjhzFile(resultList);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——单个打印德育考评通知单
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-14 上午10:38:14
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
	public ActionForward getDykptzdOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RcxwjgForm model = (RcxwjgForm) form;
		/*获取url带过来的请假申请id*/
		String id = request.getParameter("id");
		/*获取文件信息*/
		File wordFile = getWordForDykptzd(id);
		/*输出文件*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——批量打印德育考评通知单
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-14 上午10:42:15
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
	public ActionForward getDykptzdZip (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		/*获取url带过来的Value*/
		String value = request.getParameter("value");
		/*判断value是否为空*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForDykptzd(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	/**
	 * @描述: 德育考评通知单数据输出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-14 上午10:40:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForDykptzd (String id) throws Exception{
		
		RcxwjgService service = new RcxwjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		/*定义一个空file*/
		File file = null;
		
		/*根据所选qjsqid获取请假信息*/
		HashMap<String,String> kptzfInfo = service.getKptzsForId(id);
		/*输出假信息*/
		data.put("kptzfInfo", kptzfInfo);
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","dykptzs_12869.xml", FreeMarkerUtil.getFileName(kptzfInfo.get("xh")+"-"+kptzfInfo.get("xm")));
		return file;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——单个打印奖励审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-2 下午06:55:56
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
	public ActionForward getJlspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*获取url带过来的日常行为结果id*/
		String id = request.getParameter("id");
		/*获取文件信息*/
		File wordFile = getWordForJlspb(id);
		/*输出文件*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——批量打印奖励审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-11-2 下午06:57:09
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
	public ActionForward getJlspbZip (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*获取url带过来的Value*/
		String value = request.getParameter("value");
		/*判断value是否为空*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForJlspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 奖励审批表数据输出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-2 下午06:58:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForJlspb (String id) throws Exception{
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*定义一个空file*/
		File file = null;
		
		/*根据所选id获取学生违纪信息*/
		RcxwjgService rcxwjgService = new RcxwjgService();
		HashMap<String, String> rs = rcxwjgService.getKptzsForId(id);
		data.put("rs", rs);
		
		/*加载学生基本信息*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(rs.get("xh"));
		data.putAll(xsxxMap);
		
		/*取学号和姓名*/
		String xh = xsxxMap.get("xh");
		String xm = xsxxMap.get("xm");
		
		/*出生日期年月,例如：2017年11月*/
		data.put("csny",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));
		/*入学日期年月日,例如：2017年11月02日*/
		data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.day));
		/*发生时间年月日,例如：2017年11月02日*/
		data.put("fssj",DateTranCnDate.fomartDateToCn(rs.get("fssj"),FomartDateType.day));
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","jlspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xm));
		return file;
	}
}

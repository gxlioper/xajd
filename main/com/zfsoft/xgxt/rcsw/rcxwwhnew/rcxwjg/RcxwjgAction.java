
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhForm;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhService;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwxxwh.RcxwxxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;


/**
 * 日常行为结果
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
	
	private static final String url = "rcsw_rcxwwhnew_rcxwjg.do";
	
	/**
	 * 查询日常行为结果集
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
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwhnew_rcxwjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwjgManage");
	}
	/**
	 * 日常行为类别分汇总
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
		List<HashMap<String,String>> xwlbList = rcxwdmwhService.getRcxwlbList(new RcxwdmwhForm());
		//------------------设置高级查询默认值-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		HashMap<String,String> cspzMap = service.getCspz();
//		if("0".equals(cspzMap.get("zq"))){
//			searchModel.setSearch_tj_xq(new String[] {Base.currXq});
//		}
		request.setAttribute("xwlbList", xwlbList);
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwhnew_tjcx.do";
		request.setAttribute("path", path);
		request.setAttribute("zq", cspzMap.get("zq"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXwdlfList");
	}
	/**
	 * 增加日常行为结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为结果-增加XH:{xh},XWLBDMARR:{xwlbdmArr},XWDLDMARR:{xwdldmArr},XWXLDMARR:{xwxldmArr},FZARRAY:{fzArray},FSSJARR:{fssjArr},XN:{xn},XQ:{xq}")
	public ActionForward addXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
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
				String[] xwxldmArr=model.getXwxldmArr();
				String warnMessage="";
				for(int i=0;i<xwxldmArr.length;i++){
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
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessage(MessageKey.SYS_SAVE_FAIL));
				return null;
			}
		}

		String path = "rcsw_rcxwwhnew_rcxwjggl.do?method=addXwjg";
		request.setAttribute("path", path);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
//		//行为大类集合
//		request.setAttribute("xwdlList", service.getXwdlList(request));
//		//行为类别集合
//		request.setAttribute("xwlbList",
//				new ArrayList<HashMap<String, String>>());
		//当前学年
		model.setXn(Base.currXn);
		//当前学期
		model.setXq(Base.currXq);
		//当前日常行为记录时间
		model.setRcxwjlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("nowTime", GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addXwjg");
	}
	/**
	 * 修改日常行为结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为结果-修改ID:{id},XH:{xh},XN:{xn},XQ:{xq},RCXWLBDM:{rcxwlbdm},RCXWLBDLDM:{rcxwlbdldm},RCXWLBXLDM:{rcxwlbxldm},FZ:{fz},FSSJ:{fssj}")
	public ActionForward updateXwjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm model = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();
		User user = getUser(request);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
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
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessage(MessageKey.SYS_SAVE_FAIL));
				return null;
			}
		}
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		//行为大类集合
//		request.setAttribute("xwdlList", service.getXwdlList(request));
//		RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
//		List<HashMap<String, String>> xwlbList = new ArrayList<HashMap<String, String>>();
//		List<HashMap<String,String>> xwlbxxList = rcxwxxwhService.getXwlbxx(request, model.getRcxwlbdm());
//		request.setAttribute("xwlbxx", xwlbxxList!=null&&xwlbxxList.size()>0?xwlbxxList.get(0):null);
//		//获取行为类别集合
//		xwlbList = service.getXwlbList(request.getParameter("rcxwlbdldm"),
//				request);
//		request.setAttribute("xwlbList", xwlbList);
		RcxwdmwhService rcxwdmwhService = new RcxwdmwhService();
		List<HashMap<String,String>> rcxwlbListByYhsq = rcxwdmwhService.getRcxwlbListByYhsq(user);
		request.setAttribute("rcxwlbListByYhsq", rcxwlbListByYhsq);
		RcxwjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXwjg");
	}
	/**
	 * 删除行为记录
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
//			request.setAttribute("rsArrList", service.getMoreXwjgList(model,cspzMap));
			request.setAttribute("rsArrList", service.getrcxwFzxxList(model.getXh()));
			//学生基本信息
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("xxdm", Base.xxdm);
			return mapping.findForward("viewXwjg");
		} else {
			return updateXwjg(mapping, form, request, response);
		}
	}
	/**
	 * 行为大类分明细
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
		request.setAttribute("rsArrList", service.getrcxwFzxxList(model.getXh()));
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewXwdljg");
	}
	/**
	 * 自定义导出设置
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
		FileUtil.outputExcel(response, file);
		return null;
	}
	/** 
	 * 判断信息是否重复
	 */
	public ActionForward rcxwxxSfcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xwxlStr = request.getParameter("xwxlStr");
		String fssjStr = request.getParameter("fssjStr");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		RcxwjgService service = new RcxwjgService();
		String message = service.getRcxwxxSfcf(request,xh,xn,xq,xwxlStr,fssjStr);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 温大文明品行实践课评定导出
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
		searchModel.setPath("rcsw_rcxwwhnew_rcxwjggl.do");
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
	 * 医高专素质测评分导出
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
		searchModel.setPath("rcsw_rcxwwhnew_rcxwjggl.do");
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
	 * 日常行为分统计（天津体育）
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rcxwtjbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwjgForm exporModel = (RcxwjgForm) form;
		RcxwjgService service = new RcxwjgService();	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("rcsw_rcxwwhnew_rcxwjggl.do");
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
	 * 
	 * @描述:日常行为类别明细
	 * @作者：xiaxia
	 * @日期：2015-9-23 下午04:00:13
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
	public ActionForward getRcxwMx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    RcxwjgForm model = (RcxwjgForm) form;
		    RcxwjgService service = new RcxwjgService();
			List<HashMap<String,String>> lbmxlList = service.getRcxwMx(model);
			JSONArray dataList = JSONArray.fromObject(lbmxlList);
			response.getWriter().print(dataList);
			return null;
	}
	
	public ActionForward printXfjlZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<File> files = new ArrayList<File>();
		String[] xhs = request.getParameter("xh").split(",");
		String[] rcxwlbdms = request.getParameter("rcxwlbdm").split(",");
		for (int i = 0 , n = xhs.length; i < n ; i++){
			File file = getXfjlWord(xhs[i],rcxwlbdms[i]);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
	return null;
	}
	public ActionForward printXfjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String rcxwlbdm = request.getParameter("rcxwlbdm");
			File file = getXfjlWord(xh,rcxwlbdm);
			FileUtil.outputWord(response, file);
			return null;
	}
	
	private File getXfjlWord(String xh,String rcxwlbdm) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		RcxwjgService service = new RcxwjgService();
		XsxxglService xsxxService = new XsxxglService();
		HashMap<String, String> xsxxMap = xsxxService.getXsxxByXh(xh);//学生基本信息
		List<HashMap<String,String>> rcxwjlList = service.getrcxwFzxxListForPrint(xh,rcxwlbdm);
		data.putAll(xsxxMap);
		//学生照片
		XsxxService zpService = new XsxxService();
		InputStream is = zpService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		data.put("photo", photo);
		data.put("rcxwjlList", rcxwjlList);
		data.put("xxmc", Base.xxmc);
		data.put("rcxwlbmc", rcxwjlList.get(0).get("rcxwlbmc"));
		File file  = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw", "xfjlb_czyxgdzkxx.xml", xsxxMap.get("xm")+"-"+rcxwjlList.get(0).get("rcxwlbmc"));
		return file;
	}
	
}

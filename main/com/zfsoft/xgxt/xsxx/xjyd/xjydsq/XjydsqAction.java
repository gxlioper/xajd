package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学籍异动申请
 * @作者： Qilm[工号:964]
 * @时间： 2013-11-28 上午09:40:48 
 * @版本： V5.12.20
 */
public class XjydsqAction extends SuperAction {

	//从基本信息表中获取学生信息
	private static final String XJYDSQ = "xjydsq";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private XsxxService xsxxService = new XsxxService();
	
	private static final String url = "xjyd_xjydsq.do";
	
	/**
	 * 
	 * @描述:学籍异动申请列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-11-28 上午10:06:52
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
	public ActionForward xjydsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqForm model = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();
		
		if (QUERY.equals(model.getType())){

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xjyd_xjydsq.do";
		request.setAttribute("path", path);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydsqList");
	}

	/**
	 * 增加学籍异动申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学籍异动申请-增加XH:{xh},YDLBDM:{ydlbdm}")
	public ActionForward xjydsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydsqForm myForm = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			boolean result = false;
			String messageKey = "";
			// 是否可申请判断（若学生有未结束的学籍异动申请则不能申请）
			if(StringUtils.isNotNull(myForm.getXh()) && !service.sfKsq(myForm)){

				super.resetToken(request);
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				myForm.setXn(Base.currXn);
				myForm.setXq(Base.currXq);
				myForm.setYdqnj(xsjbxx.get("nj"));
				myForm.setYdqxydm(xsjbxx.get("xydm"));
				myForm.setYdqzydm(xsjbxx.get("zydm"));
				myForm.setYdqbjdm(xsjbxx.get("bjdm"));

				myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//学籍类别代码
				myForm.setYdqxjlbmc(xsjbxx.get("xjlbmc"));//学籍类别
				myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//是否有学籍
				myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//是否在校
				//华中师范大学个性化字段（是否师范生）
				if("10511".equalsIgnoreCase(Base.xxdm)) {
					myForm.setSfsfs(xsjbxx.get("sfsfs"));//是否师范生
				}
				
				myForm.setSqsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				myForm.setSqr(user.getUserName());

				//设定审核流程
				XjydForm xjydlxForm = new XjydForm();
				xjydlxForm.setXjlbdm(myForm.getYdlbdm());			
				XjydService xjydlxService = new XjydService();
				XjydForm xjydlx = xjydlxService.getModelShpz(xjydlxForm);
				if(xjydlx != null){
					myForm.setSplcid(xjydlx.getShlcid());
				}
				
				result = service.runInsert(myForm);

				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				
				// 提交成功/失败提示
				if(Constants.YW_SHZ.equals(myForm.getShzt())){
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessage("该学生有未结束的学籍异动申请！"));
				return null;
			}
			
		}
		
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		//学生基本信息
		String path = "xjydsq.do?method=xjydsqAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);		
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}else{
			HashMap<String,String> xsjbxx = new HashMap<String,String>();
			xsjbxx.put("xjlb", "");
			xsjbxx.put("xjztm", "");
			xsjbxx.put("sfzx", "");			
			xsjbxx.put("nj", "");
			xsjbxx.put("xymc", "");
			xsjbxx.put("zymc", "");
			xsjbxx.put("bjmc", "");
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//可申请学籍类别LIST
		XjydService xjlbservice = new XjydService();
		List<HashMap<String,String>> xjlbList = xjlbservice.getXjlbList("1","");
		request.setAttribute("xjlbList", xjlbList);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//异动原因
			List<HashMap<String,String>> ydyyList = service.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//维护来源学校/去向学校
			List<HashMap<String,String>> lyqxxxList = service.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		
		FormModleCommon.commonRequestSet(request);
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);
		this.saveToken(request);
		return mapping.findForward("xjydsqAdd");
	}
	
	
	
	/**
	 * 修改学籍异动申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学籍异动申请-修改XJYDSQID:{xjydsqid},XH:{xh},YDLBDM:{ydlbdm}")
	public ActionForward xjydsqUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XjydsqForm myForm = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();

		if (UPDATE.equalsIgnoreCase(myForm.getType())){

			User user = getUser(request);
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			myForm.setYdqnj(xsjbxx.get("nj"));
			myForm.setYdqxydm(xsjbxx.get("xydm"));
			myForm.setYdqzydm(xsjbxx.get("zydm"));
			myForm.setYdqbjdm(xsjbxx.get("bjdm"));
			myForm.setSqr(user.getUserName());
			myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//学籍类别代码
			myForm.setYdqxjlbmc(xsjbxx.get("xjlbmc"));//学籍类别
			myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//是否有学籍
			myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//是否在校
			
			//华中师范大学个性化字段（是否师范生）
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				myForm.setSfsfs(xsjbxx.get("sfsfs"));//是否师范生
			}
			
			// 是否已退回标记
			String sfyth = request.getParameter("sfyth");
			
			// 非已退回记录，则重新取最新的审核流程ID
			if(!Constants.YTH.equals(sfyth)){

				//设定审核流程
				XjydForm xjydlxForm = new XjydForm();
				xjydlxForm.setXjlbdm(myForm.getXjlbdm());			
				XjydService xjydlxService = new XjydService();
				XjydForm xjydlx = xjydlxService.getModelShpz(xjydlxForm);
				if(xjydlx!=null){
					myForm.setSplcid(xjydlx.getShlcid());
				}
				// 保存草稿 且 是已退回记录，则修改后，还是退回
			}if(Constants.YTH.equals(sfyth) && !Constants.YW_SHZ.equals(myForm.getShzt())){
				myForm.setShzt(Constants.YW_YTH);
			}
			boolean result = service.runUpdate(myForm);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			// 提交成功/失败提示
			if(Constants.YW_SHZ.equals(myForm.getShzt())){
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XjydsqForm model = service.getModel(myForm.getXjydsqid());

		model.setXjlbdmold(model.getXjlbdm());

		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);		
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		//可申请学籍类别LIST
		XjydService xjlbservice = new XjydService();
		List<HashMap<String,String>> xjlbList = null;
		if(Constants.YW_YTH.equals(model.getShzt())){
			xjlbList = xjlbservice.getXjlbList("1", model.getYdlbdm());
		}else{
			xjlbList = xjlbservice.getXjlbList("1","");
		}		
		request.setAttribute("xjlbList", xjlbList);	
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {	
			//异动原因
			List<HashMap<String,String>> ydyyList = service.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//维护来源学校/去向学校
			List<HashMap<String,String>> lyqxxxList = service.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		
		FormModleCommon.commonRequestSet(request);
		
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);

		String path = "xjydsq.do?method=xjydsqUpd";
		request.setAttribute("path", path);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("xjydsqUpd");
	}
	
	
	/**
	 * 删除学籍异动申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学籍异动申请-删除VALUES:{values}")
	public ActionForward xjydsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");
		XjydsqService service = new XjydsqService();
		
		if (!StringUtil.isNull(values)){
			
			int num =  service.runDelete(values.split(","));
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 批量提交/取消提交学籍异动申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学籍异动申请-批量提交VALUES:{values}")
	public ActionForward xjydsqPltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqService service = new XjydsqService();
		String values = request.getParameter("values");
		String shzt = request.getParameter("shzt");
		
		// 更新审核状态
		boolean result = service.submitRecord(values ,shzt);
		
		// 更新审核流
		if(result){
			result = service.pltjXjydsq(values, shzt);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;

		// 撤销成功/失败提示
		if(Constants.YW_WTJ.equals(shzt)){
			messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));		
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看学籍异动信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午09:13:39
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
	public ActionForward xjydck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqForm myForm = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();
		XjydsqForm model = service.getModel(myForm.getXjydsqid());

		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);	

		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}else{
			HashMap<String,String> xsjbxx = new HashMap<String,String>();
			xsjbxx.put("xjlb", "");
			xsjbxx.put("xjztm", "");
			xsjbxx.put("sfzx", "");			
			xsjbxx.put("nj", "");
			xsjbxx.put("xymc", "");
			xsjbxx.put("zymc", "");
			xsjbxx.put("bjmc", "");
			request.setAttribute("jbxx", xsjbxx);
		}
		
		XjydjgForm xjydjg = new XjydjgForm();
		XjydjgService xjydjgService = new XjydjgService();
		xjydjg = xjydjgService.getModelBySqid(myForm.getXjydsqid());
		
		request.setAttribute("xjydjg", xjydjg);
		request.setAttribute("shzt", model.getShzt());
		BeanUtils.copyProperties(myForm, StringUtils.formatDataCk(model));
		return mapping.findForward("xjydck");
	}

	/**
	 * 
	 * @描述: 学籍异动申请导出
	 * @作者：qilm
	 * @日期：2013-9-29
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydsqForm model = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页

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
	 * @描述:登记表下载
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-30 下午04:35:21
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
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydsqService service = new XjydsqService();
		String value = request.getParameter("value");
		if(value != null){
			List<File> files = new ArrayList<File>();
			for(String id : value.split(",")){
				HashMap<String , String> modelMap = service.getModelMap(id);
				String dybb = modelMap.get("dybb");
				if(StringUtil.isNull(dybb)){
					String msg = "未设置登记表格，请联系管理员！";
					request.setAttribute("yhInfo", msg);
					return new ActionForward("/yhInfo.do", false);
				}
				HashMap<String , Object> data = printData(modelMap);
				File file = BbdmUtils.getSigleFile(dybb, data);
				files.add(file);
			}
			if(value.split(",").length == 1){
				File file = files.get(0);
				FileUtil.outputWord(response, file);
			}else{
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
		}
		return null;
	}
	
	private HashMap<String , Object> printData(HashMap<String , String> modelMap) throws Exception{
		HashMap<String , Object> data = new HashMap<String, Object>();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(modelMap.get("xh"));
		if(StringUtils.isNotNull(modelMap.get("ydqbjdm"))){
			HashMap<String , String> bzrxx = xsxxService.getBzrxxByBjdm(modelMap.get("ydqbjdm"));
			data.put("ydqbzr" , bzrxx.get(bzrxx));  //班主任信息
		}
		if(StringUtils.isNotNull(modelMap.get("ydhbjdm"))){
			HashMap<String , String> ydhbzrxx = xsxxService.getBzrxxByBjdm(modelMap.get("ydhbjdm"));
			data.put("ydhbzr" , ydhbzrxx.get("bzrxx"));  //班主任信息
		}
		data.putAll(xsjbxx);  //学生信息
		data.putAll(modelMap); //学籍异动
		data.put("xxmc", Base.xxmc); //学校名称
		data.put("nd", Base.currNd);
		//截取填表年月日
		String tbnf = modelMap.get("sqsj").substring(0,4);
		String tbyf = modelMap.get("sqsj").substring(5,7);
		String tbts = modelMap.get("sqsj").substring(8,10);
		data.put("tbnf", tbnf);
		data.put("tbyf", tbyf);
		data.put("tbts", tbts);
		//获取休学原因
		XjydjgService service = new XjydjgService();
		XjydjgForm xjydjg = new XjydjgForm();
		xjydjg.setXh(modelMap.get("xh"));
		HashMap<String, String> xsydInfo = service.getXsydInfo(xjydjg);
		data.put("xsydInfo", xsydInfo);
		
		String sqly = HtmlUtil.xmlZy(data.get("sqly")== null ?  "" : data.get("sqly").toString());
		data.put("sqly", sqly);  //申请理由
		/**
		 * 宁夏建设职业技术学院 个性化
		 */
		if(StringUtils.isEqual(Base.xxdm, "13151")){
			String sfzh = (String) data.get("sfzh");
			if(sfzh != null){
				for (int i = 0; i < sfzh.length(); i++) {
					data.put("sfzh" + (i + 1), org.apache.commons.lang.StringUtils.substring(sfzh, i, i+1));
				}
			}
		}
		return data;
	}

	/**
	 * 
	 * @描述: 验证是否可提交
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydsqForm model = (XjydsqForm) form;
		XjydsqService service = new XjydsqService();
		String ydlbdm = request.getParameter("ydlbdm");
		model.setXjlbdm(ydlbdm);
		// 取得是否存在验证(根据异动类别) true:可提交/false：不可提交
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
}

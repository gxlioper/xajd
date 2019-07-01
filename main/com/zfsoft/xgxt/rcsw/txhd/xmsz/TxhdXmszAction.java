/**
 * @部门:学工产品事业部
 * @日期：2014-6-23 下午03:23:51 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import java.io.File;
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
import xgxt.utils.GetTime;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhForm;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——团学活动
 * @类功能描述: 团学活动Action
 * @作者： cq [工号:785]
 * @时间： 2014-6-23 下午03:23:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXmszAction extends SuperAction {
	
	private TxhdXmszService service = new TxhdXmszService();
	private String messageKey;
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	
	private static final String url = "rcsw_txhd_xmsz.do";
	
	/**
	 * 
	 * @描述:基本查询
	 * @作者：cq [工号：785]
	 * @日期：2014-6-23 下午04:44:10
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
	public ActionForward xmszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		
		if (QUERY.equals(myForm.getType())) {
			
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		String path = "rcsw_txhd_xmsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmszCx");
	}
	
	
	/**
	 * 
	 * @描述:增加项目
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 上午11:10:22
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
	@SystemLog(description="访问日常事务-团学活动-项目设置-增加")
	public ActionForward xmszZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		TxhdDmwhService thxdDmwhservice = new TxhdDmwhService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);

			if (service.isExistByXmwh(myForm)) {// 名称重复验证
				messageKey = MessageKey.RCSW_TXHD_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			boolean result = service.runInsert(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		request.setAttribute("lbList", lbList);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("hdgglist", thxdDmwhservice.getHdggList());
		return mapping.findForward("xmszZj");

	}
	
	
	
	/**
	 * 
	 * @描述:修改项目
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午06:36:24
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
	@SystemLog(description="访问日常事务-团学活动-项目设置-修改XMDM:{xmdm}")
	public ActionForward xmszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		TxhdXmszForm myModel = service.getModel(myForm);
		TxhdDmwhService thxdDmwhservice = new TxhdDmwhService();
		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			
			//判断有没修改项目类别名称，没有则无需判断
			if(!StringUtils.isBlank(myForm.getXmmc())&&!myForm.getXmmc().trim().equals(myModel.getXmmc().trim())&&service.isExistByXmwh(myForm)){// 名称重复验证
				messageKey = MessageKey.RCSW_TXHD_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			//判断申请人数上限是否低于已申请人数
			service.sqrssx(myForm);
				
			boolean result = service.runUpdate(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		if(myForm.getXmdm() != null && !myForm.getXmdm().equals("")){
			// 是否有学生申请
			boolean flag = service.isExistByXmsq(myForm.getXmdm());
			request.setAttribute("spzt", flag);
		}
				
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		request.setAttribute("lbList", lbList);
		request.setAttribute("xxdm", Base.xxdm);
		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(myModel));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("hdgglist", thxdDmwhservice.getHdggList());
		return mapping.findForward("xmszXg");

	}
	
	
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		TxhdXmszForm myModel = service.getModel(myForm);
		if(myModel.getHdggdm() != null){
			TxhdDmwhService dmwhservice = new TxhdDmwhService();
			String hdgg = dmwhservice.getHdggmc(myModel.getHdggdm());
			request.setAttribute("hdgg", hdgg);
		}else{
			request.setAttribute("hdgg", "");
		}
		String sqrssx = myModel.getSqrssx();
		String shrssx = myModel.getShrssx();
		if(null!=sqrssx && !StringUtils.isBlank(sqrssx)){
			myModel.setSqrssx(sqrssx+" 人");
		}
		if(null!=shrssx && !StringUtils.isBlank(shrssx)){
			myModel.setShrssx(shrssx+" 人");
		}
		request.setAttribute("data", xgxt.utils.String.StringUtils.formatData(myModel));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		String hdlbmc=null;
		for(HashMap<String, String> hm:lbList){
			if(myModel.getLbdm().equals(hm.get("lbdm"))){
				hdlbmc=hm.get("lbmc");
			}
		}
		request.setAttribute("hdlbmc", hdlbmc);
		BeanUtils.copyProperties(myForm, myModel);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("spzt", "true");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xmszCk");
	}
	/**
	 * 
	 * @描述:删除项目
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午07:32:37
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
	@SystemLog(description="访问日常事务-团学活动-项目设置-删除VALUES:{values}")
	public ActionForward xmszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// //判断合法性，申请表当中是否已申请////
			if (service.isExistByXmsq(values)) {// 关联性
				messageKey = MessageKey.RCSW_TXHD_XMMC_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;

	}
	
	/**
	 * 
	 * @描述:项目设置时间开关
	 * @作者：cq [工号：785]
	 * @日期：2014-6-24 下午07:52:14
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
	public ActionForward xmszSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		String xmdm = request.getParameter("xmdm");
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		List<HashMap<String, String>> isnotList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);

		FormModleCommon.commonRequestSet(request);

		TxhdXmszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(model));
		if(myForm.getXmdm() != null && !myForm.getXmdm().equals("")){
			// 是否有学生申请
			boolean flag = service.isExistByXmsq(myForm.getXmdm());
			request.setAttribute("spzt", flag);
		}
		return mapping.findForward("xmszSjkg");
	}
	
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：cq [工号：785]
	 * @日期：2014-6-30 上午10:33:50
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
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
   /**
    * 
    * @描述:项目复制
    * @作者：yxy[工号：1206]
    * @日期：2015-9-18 上午11:41:13
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param map
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception
    * ActionForward 返回类型 
    * @throws
    */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward copeOfXmxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TxhdXmszForm model = (TxhdXmszForm)form;
//		//获取被复制社团信息
//		StjgForm stjg = service.getModel(model);
		request.setAttribute("xmmc", model.getXmmc());
		String currxq =  Base.currXq;
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("currxq", currxq);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(hashMap.get("xqdm").equals(currxq)){
				xqmc = hashMap.get("xqmc");
				break;
			}
		}
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xqmc", xqmc);
		return map.findForward("copexmxx");
	}
	
	/**
	 * 
	 * @描述:项目复制保存
	 * @作者：yxy[工号：1206]
	 * @日期：2015-9-18 上午11:41:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param map
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCopeXmxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TxhdXmszForm model = (TxhdXmszForm)form;
		//判断相同学年同学期下有相同名称的记录，直接返回错误信息
		TxhdXmszForm checkform = new TxhdXmszForm();
		checkform.setXmmc(model.getXmmc());
		checkform.setXn(model.getXn());
		checkform.setXq(model.getXq());
		if(service.isExistByXmwh(checkform)){
			messageKey = MessageKey.RCSW_TXHD_XMMC_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		TxhdXmszForm xmjg = null;
		boolean result = false;
		//获取被复制社团信息
		if(model.getXmdm() != null){
			xmjg = service.getModel(model);
		}
		
		TxhdXmszForm newxm = new TxhdXmszForm();
		if(xmjg != null){
			BeanUtils.copyProperties(newxm, xgxt.utils.String.StringUtils.formatData(xmjg));
			String xmdm = UniqID.getInstance().getUniqIDHash();
			xmdm = xmdm.toUpperCase();
			newxm.setXmdm(xmdm);
			newxm.setSjly("0");
			newxm.setXn(model.getXn());
			newxm.setXq(model.getXq());
			newxm.setXmmc(model.getXmmc());
			result=service.runInsert(newxm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
}

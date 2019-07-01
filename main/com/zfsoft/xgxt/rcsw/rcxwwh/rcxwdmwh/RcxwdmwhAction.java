/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:12:54 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为 代码维护
 * @作者： dlq [工号：995]
 * @时间： 2013-7-30 下午04:12:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwdmwhAction extends SuperAction {

	private static final String url = "rcsw_rcxwwh_rcxwdmwh.do";
	
	/**
	 * 
	 * 行为大类
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:10:22
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
	public ActionForward rcxwdlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getXwdlPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_rcxwwh_rcxwdmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("currDate", df.format(new Date()));
		return mapping.findForward("rcxwdlManage");
	}
	
	/**
	 * 时间开关
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为大类-申请开关-RCXWLBDLDM：{rcxwlbdldm}")
	public ActionForward rcxwlbdldmSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		String rcxwlbdldm = request.getParameter("rcxwlbdldm");
		String rcxwlbdlmc = service.getRcxwlbdlmcById(rcxwlbdldm);
		request.setAttribute("rcxwlbdlmc", rcxwlbdlmc);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);

		String path = "rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		RcxwdmwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);

		return mapping.findForward("rcxwlbdldmSjkg");
	}
	
	
	/**
	 * 增加 行为大类 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为大类-增加RCXWLBDLMC:{rcxwlbdlmc},SPLC:{splc}")
	public ActionForward addRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveXwdlInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		//获取日常事务审核流
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("addRcxwdl");
	}
	
	/**
	 * 
	 * @描述:修改行为大类 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:10:58
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为大类-修改RCXWLBDLDM:{rcxwlbdldm},RCXWLBDLMC:{rcxwlbdlmc},SPLC:{splc}")
	public ActionForward updateRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		RcxwdmwhForm model = service.getModel(myForm);
	
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveXwdlInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateRcxwdl");
	}
	
	/**
	 * 
	 * 删除 行为大类 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:11:18
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为大类-删除VALUES:{values}")
	public ActionForward delRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)){
			//判断行为大类是否被行为类别应用
			String checkXwdlForXwlb=service.checkXwdlForXwlb(values);
			
			if(!checkXwdlForXwlb.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_XWDLDEL,checkXwdlForXwlb);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.deleteXwdlInfo(values);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	public ActionForward checkRcxwdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		//判断行为大类是否被行为类别应用
		String checkXwdlForXwlb=service.checkXwdlForXwlb(myForm.getRcxwlbdldm());
		if(!checkXwdlForXwlb.trim().equals("")){
			String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_XWDLUPDATE,checkXwdlForXwlb);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		response.getWriter().print(getJsonMessage(""));
		return null;
	}
	/**
	 * 启用/停用 行为类别
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为类别-启用/停用VALUES:{values}")
	public ActionForward sfqyRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
		String sfqy = request.getParameter("sfqy");
		boolean sfqy_y = "1".equals(sfqy);
		String RCSW_RCXWWH_SFQY_FAIL = MessageKey.RCSW_RCXWWH_SFQY_N_FAIL;
		String RCSW_RCXWWH_SFQY_NUM = MessageKey.RCSW_RCXWWH_SFQY_N_NUM;
		String RCSW_RCXWWH_SFQY_NULL = MessageKey.RCSW_RCXWWH_SFQY_N_NULL;
		if(sfqy_y){
			RCSW_RCXWWH_SFQY_FAIL = MessageKey.RCSW_RCXWWH_SFQY_Y_FAIL;
			RCSW_RCXWWH_SFQY_NUM = MessageKey.RCSW_RCXWWH_SFQY_Y_NUM;
			RCSW_RCXWWH_SFQY_NULL = MessageKey.RCSW_RCXWWH_SFQY_Y_NULL;
		}
		if (!StringUtil.isNull(values)){
			boolean result = true;
			String[] ids = values.split(",");
			int okNum = 0;
			RcxwdmwhForm myForm = new RcxwdmwhForm();
			if(sfqy_y){ // 启用
				for (int i = 0; i < ids.length; i++) {
					myForm.setRcxwlbdm(ids[i]);
					myForm.setSfqy(sfqy);
					if(result){
						result = service.updateRcxwlbSfqy(myForm);
						okNum++;
					}
				}
				String message = result ? MessageUtil.getText(RCSW_RCXWWH_SFQY_NUM, okNum) : MessageUtil.getText(RCSW_RCXWWH_SFQY_FAIL);
				response.getWriter().print(getJsonMessage(message));
			}else{ // 停用
				// 获取审核流未结束的记录
				List<HashMap<String,String>> rcxwlbShwjsList = service.getRcxwlbShwjs(values);
				StringBuilder notOk = new StringBuilder();
				for (int i = 0; i < ids.length; i++) {
					boolean rcxwlbShwjsFlag = false;
					for (HashMap<String,String> rcxwlbShwjsMap : rcxwlbShwjsList) {
						if(rcxwlbShwjsMap.get("rcxwlbdm").equals(ids[i])){
							rcxwlbShwjsFlag = true;
							break;
						}
					}
					if(rcxwlbShwjsFlag){ // 不符合记录
						notOk.append(ids[i]).append(",");
					}else{
						myForm.setRcxwlbdm(ids[i]);
						myForm.setSfqy(sfqy);
						if(result){
							result = service.updateRcxwlbSfqy(myForm);
							okNum++;
						}
					}
				}
				String resultMsg = MessageUtil.getText(RCSW_RCXWWH_SFQY_NUM, okNum);
				if(rcxwlbShwjsList.size() > 0){
					String notOkRs = notOk.toString().substring(0, notOk.toString().length() - 1);
					resultMsg += "代码为<font color='red'>【"+ notOkRs +"】</font>的记录存在未结束的流程，不能停用";
				}
				String message = result ? resultMsg : MessageUtil.getText(RCSW_RCXWWH_SFQY_FAIL);
				response.getWriter().print(getJsonMessage(message));
			}
		} else {
			throw new SystemException(RCSW_RCXWWH_SFQY_NULL);
		}
		return null;
	}

	
	/**
	 * 日常行为类别
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
	public ActionForward rcxwlbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getXwlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_rcxwwh_rcxwdmwh.do";
		request.setAttribute("path", path);
		
		//获取行为大类集合
		request.setAttribute("xwdlList", service.getXwlbListMap(model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwlbManage");
	}
	
	/**
	 * 
	 * 增加行为类别
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:12:42
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为类别-增加RCXWLBMC:{rcxwlbmc},RCXWLBDLDM:{rcxwlbdldm},RCXWFZLX:{rcxwfzlx},RCXWLBZDFZ:{rcxwlbzdfz},RCXWLBZGFZ:{rcxwlbzgfz}")
	public ActionForward addRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveXwlbInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		request.setAttribute("xwdlList", service.getXwlbListMap(model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("addRcxwlb");
	}
	
	/**
	 * 
	 * 修改行为类别
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:13:12
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为类别-修改RCXWLBDM:{rcxwlbdm},RCXWLBMC:{rcxwlbmc},RCXWLBDLDM:{rcxwlbdldm},RCXWFZLX:{rcxwfzlx},RCXWLBZDFZ:{rcxwlbzdfz},RCXWLBZGFZ:{rcxwlbzgfz}")
	public ActionForward updateRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		
		RcxwdmwhForm model = service.getRcxwdmwhForm(myForm,myForm.getRcxwlbdm());
	
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveXwlbInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xwdlList", service.getXwlbListMap(model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateRcxwlb");
	}
	
	
	/**
	 * 
	 * 删除行为类别
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-7 上午09:28:21
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为代码维护-行为类别-删除VALUES:{values}")
	public ActionForward delRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwdmwhService service = new RcxwdmwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)){
			//判断行为类别是否被行为维护应用
			String checkXwlbForXwwh=service.checkXwlbForXwwh(values);
			
			if(!checkXwlbForXwwh.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_XWLBDEL,checkXwlbForXwwh);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.deleteXwlbInfo(values);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	public ActionForward checkRcxwlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm = (RcxwdmwhForm) form;
		//判断行为类别是否被行为维护应用
		String checkXwlbForXwwh=service.checkXwlbForXwwh(myForm.getRcxwlbdm());
		
		if(!checkXwlbForXwwh.trim().equals("")){
			String message=MessageUtil.getText(MessageKey.RCSW_RCXWWH_XWLBUPDATE,checkXwlbForXwwh);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		response.getWriter().print(getJsonMessage(""));
		return null;
	}
	/**
	 * @描述:行为上报时获取行为类别list
	 * @作者：cmj
	 * @日期：2013-12-24 下午02:23:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getXwlbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getXwlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//获取行为大类集合
		request.setAttribute("xwdlList", service.getXwlbListMap(model));
		return mapping.findForward("getXwlbList");
	}
	
	/**
	 * @描述:异步获取分值区间
	 * @作者：陈敏杰
	 * @日期：2013-12-25 下午02:09:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getFzqjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwdmwhForm model = (RcxwdmwhForm) form;
		RcxwdmwhService service = new RcxwdmwhService();
		RcxwdmwhForm myForm;
		if(!StringUtil.isNull(model.getRcxwlbdm())){
			myForm=service.getRcxwdmwhForm(model,model.getRcxwlbdm());
		}else{
			myForm=new RcxwdmwhForm();
		}
		JSONObject json=JSONObject.fromBean(myForm);
		response.getWriter().print(json);
		return null;
	}
	
	
	

}

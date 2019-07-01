package com.zfsoft.xgxt.jskp.lxsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshForm;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshService;
import common.newp.StringUtil;

public class LxsqAction extends SuperAction<LxsqForm,LxsqService> {
	private LxsqService service = new LxsqService();
	private final String url = "pjpy_jskp_lxsq.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【错误数据.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "未上传文件！";
	public static final String EXCELREPEAT = "Excle中存在重复数据(参与人、参与时间、项目名称重复)，请仔细核对！";
	
	/**
	 * 
	 * @描述: 立项申请查询跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午09:48:28
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
	@SystemLog(description = "访问纪实考评-立项申请")
	@SystemAuth(url = url)
	public ActionForward getLxsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		/**只有2017级以后的学生才能申请,老师用户不算*/
		/*当前登录用户*/
		User user = getUser(request); 
		boolean stuTj = service.isStandardStu(user);
		if(!stuTj){
			String msg = "该模块仅允许2017级及以后的学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("path", url);
		request.setAttribute("splc", new CsszService().getSplc("lx").get("splc"));
		/*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxsqcx");
	}
	
	/**
	 * 
	 * @描述: 查询立项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午09:51:08
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
	public ActionForward seachForLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加立项申请跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午09:54:27
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
	public ActionForward addLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		//取联系方式
		String lxfs = "";
		if("stu".equals(user.getUserType())){
			lxfs = service.getFzrxxStu(user.getUserName()).get("sjhm");
		}else{
			lxfs = service.getFzrxxTea(user.getUserName()).get("lxdh");
		}
		request.setAttribute("lxfs", lxfs);
		/*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("addlxsq");
	}
	
	/**
	 * 
	 * @描述: 修改立项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午09:57:48
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
	public ActionForward updateLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		LxsqForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = service.getXmcyryXhs(lxsq.getSqid());
		request.setAttribute("xhList", xhList);
		StringBuilder xhforTextarea = new StringBuilder();
	    for (int i = 0; i < xhList.size(); i++) {
	    	xhforTextarea.append(xhList.get(i).get("xh")+"\n");
		}
	    request.setAttribute("xhs", xhforTextarea.toString());
	    request.setAttribute("bmmc", service.getBmmc(lxsq.getZdbm()).get("bmmc"));
	    /*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("updatelxsq");
	}
	
	/**
	 * 
	 * @描述: 保存立项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午09:59:09
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
	public ActionForward saveForLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm lxsq = (LxsqForm)form;
		String[] xhs = request.getParameterValues("xh");
		lxsq.setXhs(xhs);
		
		/**获取用户*/
		User user = getUser(request);
		
		/*事务现在有问题，直接舍弃掉！*/
		/*LxsqService tranService = TransactionControlProxy.newProxy(new LxsqService());*/
		boolean rs = true;
		try {
			rs = new LxsqService().saveForLxsq(lxsq,user);
		} catch (SystemException e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除立项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午10:01:54
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = service.delRys(ids);
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
	 * 
	 * @描述: 提交立项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午10:04:13
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm myForm = (LxsqForm) form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		LxsqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 批量提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-3 下午04:39:06
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
	public ActionForward batchSubmission (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			boolean result = false;
			int okNum = 0;
			//根据ID查询学生申报项目的信息
			List<HashMap<String,String>> dataList = service.getStuSbDataList(values.split(","));
			
			for(int i = 0; i < dataList.size(); i++){
				HashMap<String,String> dataMap = dataList.get(i);
				String sqid = dataMap.get("sqid");
				String splc = dataMap.get("splcid");
				String fzr = dataMap.get("fzr");
				result = service.plSubmit(sqid,splc,fzr);
				if (result) {
					okNum++;
				}
			}
			String resultMsg = "提交成功"+okNum+"条！";
			response.getWriter().print(getJsonMessage(resultMsg));
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销提交记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午10:05:49
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		/*参数设置为0时需要更新上报审核表中的审核状态，因为参数值为0时我取的是xg_jskp_xmsbb中的审核状态*/
		String sfsh = new CsszService().getSfsh();
		
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		JskpXmsbshService jskpXmsbshService = new JskpXmsbshService();
		if (result) {
			// 更新业务状态为'未提交'
			LxsqForm model = new LxsqForm();
			model.setSqid(sqid);
			model.setSplcid(lcid);
			
			/*参数设置为0时把值塞到form*/
			JskpXmsbshForm jskpXmsbshForm = new JskpXmsbshForm();
			jskpXmsbshForm.setSqid(sqid);
			jskpXmsbshForm.setSplcid(lcid);
			
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
				if("0".equals(sfsh)){
					jskpXmsbshForm.setShzt(Constants.YW_YTH);
				}
			} else {
				model.setShzt(Constants.YW_WTJ);
				if("0".equals(sfsh)){
					jskpXmsbshForm.setShzt(Constants.YW_WTJ);
				}
			}
			service.runUpdate(model);
			if("0".equals(sfsh)){
				jskpXmsbshService.runUpdate(jskpXmsbshForm);
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看立项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午10:10:51
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
	public ActionForward ckLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		LxsqForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = service.getXmcyryXhs(lxsq.getSqid());
		request.setAttribute("xhList", xhList);
	    request.setAttribute("bmmc", service.getBmmc(lxsq.getZdbm()).get("bmmc"));
	    request.setAttribute("xmlbmc",new DmwhService().getModel(lxsq.getXmlb()).getXmlbmc());
	    /*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("cklxsq");
	}
	
	/**
	 * 
	 * @描述: 人员设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午10:35:26
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
	public ActionForward rysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("sqid");
		LxsqForm model = service.getModel(sqid);
		request.setAttribute("rs",model);
		request.setAttribute("xmlbmc",new DmwhService().getModel(model.getXmlb()).getXmlbmc());
		request.setAttribute("shzt", model.getShzt());
		request.setAttribute("sqid", sqid);
		return mapping.findForward("rysz");
	}
	
	/**
	 * 
	 * @描述: 保存人员设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 上午10:39:19
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
	public ActionForward saveRysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm myForm = (LxsqForm) form;
		String[] xhs = request.getParameterValues("xh");
		myForm.setXhs(xhs);
		LxsqService service = TransactionControlProxy.newProxy(new LxsqService());
		boolean rs = service.saveRysz(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	    response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 验证学号
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 下午05:03:30
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
	public ActionForward checkXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String[] xhs = xh.replaceAll("\r", " ").replaceAll("\n",
		" ").split(" ");
		String ryflag = request.getParameter("ryflag");
		String sqid = request.getParameter("sqid");
		xhs = common.newp.ArrayUtil.removeRepeatElementInArray(xhs);
		List<HashMap<String,String>> AvailableXhList = service.getAvailableXhList(xhs,sqid,ryflag);
		List<String> inAvailableXhList = new ArrayList<String>();
		for (int i = 0; i < xhs.length; i++) {
			String tempXh = xhs[i];
			if(StringUtils.isNotNull(tempXh) && (AvailableXhList != null && !AvailableXhList.isEmpty())){
				//相等标识
				boolean flag = false;
				for (int j = 0; j < AvailableXhList.size(); j++) {
					if(tempXh.equals(AvailableXhList.get(j).get("xh"))){
						flag = true;
						break;
					}
				}
				if(!flag){
					inAvailableXhList.add(tempXh);
				}else{
					flag = false;
				}
				
			}else{
				inAvailableXhList.add(tempXh);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("use", AvailableXhList);
		jsonObject.put("nouse", inAvailableXhList);
		response.getWriter().print(jsonObject);
		return null;
	}
	
	/**
	 * 
	 * @描述: 查询人员设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 上午08:55:13
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
	public ActionForward searchRysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		// 查询
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getRyszList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 增加人员设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 上午08:50:33
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
	public ActionForward addRy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		request.setAttribute("sqid", model.getSqid());
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("addry");
	}
	
	/**
	 * 
	 * @描述: 删除人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-20 下午04:15:54
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
	public ActionForward delRy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if(StringUtils.isNotNull(values)){
			String[] sqids = values.split(",");
			if(!service.isNotHaveShjl(sqids)){
				response.getWriter().print(getJsonMessage("有记录已被用户审核，无法被删除！"));
				return null;
			}
			boolean rs = service.delXmry(sqids);
			String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述: 个性化导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-19 下午05:13:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dataImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("dataImport");
	}
	
	/**
	 * @描述: 下载导入模板
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-23 上午11:59:49
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("jskp_lxsq.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @描述: 导入保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-23 下午05:00:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		LxsqForm model = (LxsqForm)form;
		/**request里获取用户*/
		User user = getUser(request);
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setExclePath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model,user);
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}
				else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
				
			}catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				logger.info("导入文件未找到！");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				logger.info("IO异常！");
				e.printStackTrace();
			}
		}else{
			String message = KFILE;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		return null;
	}
	
	/**
	 * @描述: 下载错误数据
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2018-1-26 下午03:18:32
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}

}

/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午09:23:53 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为信息维护 
 * @作者： Dlq [工号：995]
 * @时间： 2013-8-2 上午09:23:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwxxwhAction extends SuperAction {
	//定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "rcswrcxw";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	public static final String SUBMIT = "submit";
	
	private static final String url = "rcsw_rcxwwh_rcxwxxwh.do";
	
	/**
	 * 
	 * @描述:查询获取日常行为维护数据
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:00:13
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
	public ActionForward rcxwxxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())){
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
		
		String path = "rcsw_rcxwwh_rcxwxxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwxxwhManage");
	}

	/**
	 * 
	 * @描述:增加日常行为信息维护
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:00:45
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为信息维护-增加XH:{xh},XWDLDMARR:{xwdldmArr},XWLBDMARR:{xwlbdmArr},FZARRAY:{fzArray},FSSJARR:{fssjArr},XQ:{xq},XN:{xn}")
	public ActionForward addXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
	
        if (SAVE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType()) ){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		}

        	//唯一性判断（学号，学年，学期）
        	boolean isExist= false;
        	
    		//判断日常行为大类中是否需要走审核流
//    		boolean isSplcExist = service.checkForSplc(model);
    		
        	if(!isExist){
	        	//添加日常行为信息维护
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
        		boolean result = service.saveRcww(model);
            	String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(MessageKey.SYS_SAVE_FAIL));
				return null;
        	}
		}
       
		String path = "rcsw_rcxwwh_rcxwxxwhgl.do?method=addXwxx";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		//行为大类集合
		request.setAttribute("xwdlList", service.getXwdlList(request));
		//行为类别集合
		request.setAttribute("xwlbList", new ArrayList<HashMap<String,String>>());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setRcxwjlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("nowTime", GetTime.getTimeByFormat("yyyy-mm-dd"));
		this.saveToken(request);
		return mapping.findForward("addXwxxwh");
	}
	
	
	/**
	 * 
	 * @描述:增加日常行为信息维护
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:00:45
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为信息维护-提交VALUES:{values}")
	public ActionForward submitXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhService service = new RcxwxxwhService();
		String guid = request.getParameter("values");
		String [] ids  = guid.split(",");
		int okNum = 0;
		for (int i = 0; i < ids.length; i++) {
			RcxwxxwhForm model = (RcxwxxwhForm) form;
			model.setId(ids[i]);
			RcxwxxwhForm modelGet = service.getModel(model);
			// 已退回的记录取老的审核流程ID;非已退回记录则再去取审核流程
			if(!Constants.YW_YTH.equals(modelGet.getShzt())){
				RcxwdmwhService rcxwService = new RcxwdmwhService();
				RcxwdmwhForm rcxwdmwh = new RcxwdmwhForm();
				rcxwdmwh.setRcxwlbdldm(modelGet.getRcxwlbdldm());
				rcxwdmwh = rcxwService.getModel(rcxwdmwh);
				if(rcxwdmwh!=null){
					model.setSplc(rcxwdmwh.getSplc());
				}
			}else{
				model.setSplc(modelGet.getSplc());
			}
			model.setXh(modelGet.getXh());
			model.setRcxwlbdldm(modelGet.getRcxwlbdldm());
			boolean result = service.submitRcww(model);
			if (result) {
				okNum++;
			}
		}
		String message = ids.length==okNum ? "提交成功！" : "提交成功："+okNum+"条！失败："+(ids.length - okNum) +"条！";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为信息维护-撤销VALUES:{values}")
	public ActionForward cancelRcxwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		RcxwxxwhService service = new RcxwxxwhService();
		String guid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(guid,lcid);
		if(result){
			//更新业务状态为'未提交'
			RcxwxxwhForm model = new RcxwxxwhForm();
			model.setId(guid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(guid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateRcxwModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @描述:查询获取行为类别集合
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:03:08
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
	public ActionForward getXwlbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jldldm = request.getParameter("rcxwlbdldm");
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwlbList = service.getXwlbList(jldldm, request);
		String json = JSONArray.fromCollection(xwlbList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 修改日常行为信息维护
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 上午09:33:12
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为信息维护-修改ID:{id},RCXWLBDM:{rcxwlbdm},RCXWLBDLDM:{rcxwlbdldm},FZ:{fz},XQ:{xq},XN:{xn}")
	public ActionForward updateXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	Hashtable files = model.getMultipartRequestHandler().getFileElements();
			//处理附件
			FormFile file = (FormFile) files.get("lbfj");
			if(null!=file&&file.getFileSize() > 1024*1024*5){
				String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,file.getFileName());
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
    		RcxwxxwhForm modelGet = service.getModel(model);

    		// 已退回的记录取老的审核流程ID;非已退回记录则再去取审核流程
    		if(!Constants.YW_YTH.equals(modelGet.getShzt())){
    			RcxwdmwhService rcxwService = new RcxwdmwhService();
    			RcxwdmwhForm rcxwdmwh = new RcxwdmwhForm();
    			rcxwdmwh.setRcxwlbdldm(modelGet.getRcxwlbdldm());
    			rcxwdmwh = rcxwService.getModel(rcxwdmwh);
    			if(rcxwdmwh!=null){
    				model.setSplc(rcxwdmwh.getSplc());
    			}
    			
    		}else{
    			model.setSplc(modelGet.getSplc());
    			model.setRcxwlbdldm(modelGet.getRcxwlbdldm());
    			model.setRcxwlbdm(modelGet.getRcxwlbdm());

				//　保存，则审核状态仍未：退回
				if(UPDATE.equalsIgnoreCase(model.getType())){
					model.setShzt(Constants.YW_YTH);
				}
    		}
    		
        	//判断日常行为大类中是否需要走审核流
    		boolean isSplcExist = service.checkForSplc(model);
        	
        	//按照审核状态判断该条信息是否在审核中或者不需要审核
        	//boolean isExist=service.isExistByXwxxwh(model,UPDATE);
    		boolean isExist = false;
        	if(!isExist){
	        	
				boolean result = service.updateRcww(model,isSplcExist);
				boolean rcxwjgResult = true;
				//在修改审核信息中判断是否要往审核结果库中插入或者更改该条数据。
				if(!isSplcExist && SUBMIT.equalsIgnoreCase(model.getType())){
					//天津市经济贸易学校个新华
	        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
	        			model.setShzt("1");
	        			service.runUpdate(model);
	        		}
					RcxwjgForm rcxwjgForm = new RcxwjgForm();
            		RcxwjgService rcxwjgService = new RcxwjgService();
					boolean xwwhForxwjgFlag = rcxwjgService.checkXwwhForxwjg(model.getId());
					RcxwxxwhForm myForm=service.getModel(model);
		    		myForm.setRcxwlbdldm(model.getRcxwlbdldm());
		    		myForm.setMultipartRequestHandler(model.getMultipartRequestHandler());
					BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(myForm));
					if(xwwhForxwjgFlag){
						//如果行为结果表中存在该条数据则更改该数据
						//天津市经济贸易学校个新华
		        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
		        			rcxwjgForm.setSjly("1");
		        		}
						rcxwjgResult = rcxwjgService.updateXwjg(rcxwjgForm);
					}else{
						//行为日常结果表中没有该条数据则增加一条
						//天津市经济贸易学校个新华
		        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
		        			rcxwjgForm.setSjly("1");
		        		}
						rcxwjgResult = rcxwjgService.insertXwjg(rcxwjgForm);
					}	
            	}
				String messageKey = (result && rcxwjgResult) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		//response.getWriter().print(getJsonMessage(MessageKey.RCSW_RCXWWH_XWWHSHZ));
        		throw new SystemException(MessageKey.RCSW_RCXWWH_XWWHSHZ);
        	}
		}
       
		
		String path = "rcsw_rcxwwh_rcxwxxwhgl.do?method=updateXwxx";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		//行为大类集合
		request.setAttribute("xwdlList", service.getXwdlList(request));
		
		List<HashMap<String,String>> xwlbList = new ArrayList<HashMap<String,String>>();
		//获取行为类别集合
		xwlbList = service.getXwlbList(request.getParameter("rcxwlbdldm"), request);
		request.setAttribute("xwlbList", xwlbList);
		
		RcxwxxwhForm updateForm = service.getModel(model);
		request.setAttribute("rcxwlbdm", updateForm.getRcxwlbdm());
		//行为类别信息
		List<HashMap<String,String>> xwlbxxList = service.getXwlbxx(request, updateForm.getRcxwlbdm());
		request.setAttribute("xwlbxx", xwlbxxList!=null&&xwlbxxList.size()>0?xwlbxxList.get(0):null);
		
		updateForm.setRcxwlbdldm(request.getParameter("rcxwlbdldm"));
		model.setRcxwlbdldm(request.getParameter("rcxwlbdldm"));
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXwxxwh");
	}
	
	/**
	 * 
	 * 删除日常行为信息,
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-8 下午05:28:50
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为信息维护-删除VALUES:{values}")
	public ActionForward delXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwxxwhService service = new RcxwxxwhService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//删除日常行为信息维护只能删除未审核状态 
			int num = service.runDeleteXwxx(values.split(","));
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
	 * 
	 * 查看该学生的行为信息
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 上午10:08:17
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
	public ActionForward viewXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//查询单个行为信息结果
			Map<String, String> oneXwxxList = service.getOneXwxxList(model.getId());
			request.setAttribute("rs", StringUtils.formatData(oneXwxxList));

			//学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
			request.setAttribute("jbxxList", jbxxList);
			model.setFjlj(oneXwxxList.get("fjlj"));
			model.setFjmc(oneXwxxList.get("fjmc"));
			request.setAttribute("model", StringUtils.formatData(model));
			//审核状态名称
			request.setAttribute("shztmc", oneXwxxList.get("shztmc"));
			return mapping.findForward("viewXwxx");
		} else {
			return updateXwxx(mapping, form, request, response);
		}
		
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
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();

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
	
	/**
	 * 
	 * @描述:查询获取行为大类集合
	 * @作者：HongLin [工号：707]
	 * @日期：2014-2-21 
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
	public ActionForward getXwdlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//行为大类集合
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwdlList = service.getXwdlList(request);
		String json = JSONArray.fromCollection(xwdlList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:查询获取行为类别信息
	 * @作者：HongLin [工号：707]
	 * @日期：2014-2-21 上午10:51:46
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
	public ActionForward getXwlbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lbdm = request.getParameter("rcxwlbdm");
		//行为大类集合
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwlbxx = service.getXwlbxx(request,lbdm);
		String json = JSONArray.fromCollection(xwlbxx).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
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
		RcxwxxwhService service = new RcxwxxwhService();
		String message = service.getRcxwxxSfcf(request,xh,xn,xq,xwlbStr,fssjStr);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	/**下载附件*/
	@SystemAuth(url = url)
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwxxwhForm myForm = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		
		RcxwxxwhForm model = service.getModel(myForm);
		
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为信息维护-删除附件FJLJ:{fjlj}")
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwxxwhForm myForm = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		RcxwxxwhForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				file.delete();
			}
			model.setFjlj("");
			model.setFjmc("");
			service.runUpdate(model);
		}
		
		return null;
	}
}

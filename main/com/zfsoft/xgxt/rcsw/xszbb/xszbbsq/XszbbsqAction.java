/**
 * 
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午01:36:00
 * 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsq;

import java.io.File;
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
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz.XszbbJcszForm;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz.XszbbJcszService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;



public class XszbbsqAction extends SuperAction {
	//定义学生证补办中学生证补办常量可以从基本信息表中获取学生信息
	private static final String RCSWXSZBB = "rcswxszbb";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_xszbb_bbsq.do";
	
	/**
	 * 学生证代码:"001"
	 */
	private static final String XSZDM = "001";
	
	/**
	 * 
	 * @描述:TODO(学生证补办申请列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午01:36:00
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
	public ActionForward xszbbsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		XszbbJcszService xszbbJcszService = new XszbbJcszService();
		XszbbJcszForm jcszModel = xszbbJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		String path = "rcsw_xszbb_bbsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszbbsqManage");
	}

	/**
	 * 
	 * @描述:TODO(增加学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午03:18:28
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
	@SystemLog(description="访问日常事务-证件补办-证件补办申请-增加XH:{xh},XSZBBLXDM:{xszbblxdm},SFBBHCYHK:{sfbbhcyhk},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward addXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
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
        	isExist = service.isExistByXszbbsq(model);
        	if(!isExist){
        		super.resetToken(request);
        		//非学生证，清空补办优惠卡信息
        		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
        			model.setSfbbhcyhk("");
        		}
	        	//添加学生证补办申请
				String bbsqid = UniqID.getInstance().getUniqIDHash();
				model.setBbsqid(bbsqid);
        		boolean result = service.saveXszbbsq(model);
        		String messageKey = "";
        		
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSZBB_XSZBBSQ_REPEAT));
				return null;
        	}
		}
		String path = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		//补办类型维护集合
		request.setAttribute("bblxwhList", service.getBblxwhList());
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSfbbhcyhk("y");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addXszbbsq");
	}
	
	/**
	 * 
	 * @描述:TODO( 修改学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午04:12:14
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
	@SystemLog(description="访问日常事务-证件补办-证件补办申请-修改BBSQID:{bbsqid},XSZBBLXDM:{xszbblxdm},SFBBHCYHK:{sfbbhcyhk},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward updateXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
	        	//非学生证，清空补办优惠卡信息
	    		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
	    			model.setSfbbhcyhk("");
	    			if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店管理职业技术学院个性化
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}else{
	    			if((Base.xxdm.equals("13011") || Base.xxdm.equals("10695")) && "n".equalsIgnoreCase(model.getSfbbhcyhk())){//青岛酒店管理职业技术学院个性化
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}
				boolean result = service.updateXszbbsq(model);
				String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		}
        String path = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		XszbbJcszService xszbbJcszService = new XszbbJcszService();
		XszbbJcszForm jcszModel = xszbbJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		//补办类型维护集合
		request.setAttribute("bblxwhList", service.getBblxwhList());
		XszbbsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXszbbsq");
	}
	
	
	/**
	 * 
	 * @描述:TODO(删除学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:28:32
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
	@SystemLog(description="访问日常事务-证件补办-证件补办申请-删除VALUES:{values}")
	public ActionForward delXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqService service = new XszbbsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteXszbbsq(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(提交学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:29:03
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
	@SystemLog(description="访问日常事务-证件补办-证件补办申请-提交VALUES:{values}")
	public ActionForward submitXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		String bbsqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		String xszbblxdm = request.getParameter("xszbblxdm");
		model.setBbsqid(bbsqid);
		model.setXh(xh);
		model.setXszbblxdm(xszbblxdm);
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取审批流程
			XszbblxwhForm bblx = new XszbblxwhForm();
			bblx.setXszbblxdm(model.getXszbblxdm());
			String splc = new XszbblxwhDao().getModel(bblx).getShlc();
			model.setSplc(splc);
		}
		model.setShzt(Constants.YW_SHZ);
		XszbbsqService service = new XszbbsqService();
		boolean result = service.submitBbsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(撤销补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:28:42
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
	@SystemLog(description="访问日常事务-证件补办-证件补办申请-撤销VALUES:{values}")
	public ActionForward cancelXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqService service = new XszbbsqService();
		String bbsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(bbsqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			XszbbsqForm model = new XszbbsqForm();
			model.setBbsqid(bbsqid);
			model.setSplc(lcid);
			//查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(bbsqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateBbsq(model);
		}
		
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:TODO(查看补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:29:32
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
	public ActionForward viewXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个行为信息结果
		request.setAttribute("rs", StringUtils.formatData(service.getXszbbsqInfo(model)));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewXszbbsq");
		
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:30:04
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
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
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
	 * @描述:获取火车乘车区间(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-23 下午05:24:55
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
	public ActionForward getHcqjxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		HashMap<String,String> xxmap = service.getHcccqj(model.getXh());
		JSONObject json = JSONObject.fromObject(xxmap);
		response.getWriter().print(json);
		return null;		
	}
	

}

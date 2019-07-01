/**
 * 
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午01:36:00
 * 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx;

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
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz.HcyhkJcszService;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx.HcyhklxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车乘车区间填写管理模块
 * @类功能描述: TODO(火车乘车区间填写) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:38:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjtxAction extends SuperAction {
	//定义火车优惠卡常量可以从基本信息表中获取学生信息
	private static final String RCSWXSZBB = "rcswxszbb";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_hcyhk_hcccqjtx.do";
	
	/**
	 * 
	 * @描述:TODO(火车乘车区间填写申请列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:38:47
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
	public ActionForward hcccqjtxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjtxForm model = (HcccqjtxForm) form;
		HcccqjtxService service = new HcccqjtxService();
		
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
		
		HcyhkJcszService hcyhkJcszService = new HcyhkJcszService();
		HcyhkJcszForm jcszModel = hcyhkJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		String path = "rcsw_hcyhk_hcccqjtx.do";
		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("hcccqjtxManage");
		
	}

	/**
	 * 
	 * @描述:TODO(增加火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:15:07
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间填写-增加XH:{xh},XN:{xn},XQ:{xq},CCQDZ:{ccqdz},CCZDZ:{cczdz},TXSJ:{txsj}")
	public ActionForward addHcccqjtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjtxForm model = (HcccqjtxForm) form;
		HcccqjtxService service = new HcccqjtxService();
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

	    	boolean isExist = service.isExistByHcccqjtx(model,SAVE);
        	if(!isExist){
        		super.resetToken(request);
	        	//添加火车乘车区间填写
				String ccqjtxid = UniqID.getInstance().getUniqIDHash();
				model.setCcqjtxid(ccqjtxid);
        		boolean result = service.saveHcccqjtx(model);
        		String messageKey = "";
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_RESULT_REPEAT));
				return null;
        	}
		}
        //学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		String ccqdz = service.getXxccqdz();
		model.setCcqdz(ccqdz);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXqmc(Base.currXq));
		String path = "rcsw_hcyhk_hcccqjtxgl.do?method=addHcccqjtx";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		//获取所有火车优惠卡类型
		HcyhklxService hcyhklxService = new HcyhklxService();
		request.setAttribute("hcyhklxList", hcyhklxService.getHcyhklxList());
		request.setAttribute("xxdm", Base.xxdm);
		model.setTxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		this.saveToken(request);
		return mapping.findForward("addHcccqjtx");
		
	}
	
	/**
	 * 
	 * @描述:TODO(修改火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:14:23
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间填写-修改CCQJTXID:{ccqjtxid},XH:{xh},XN:{xn},XQ:{xq},CCQDZ:{ccqdz},CCZDZ:{cczdz},TXSJ:{txsj}")
	public ActionForward updateHcccqjtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjtxForm model = (HcccqjtxForm) form;
		HcccqjtxService service = new HcccqjtxService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	
	    	boolean isExist = service.isExistByHcccqjtx(model,model.getType());
	    	if(!isExist){
	    		boolean result = service.updateHcccqjtx(model);
				String messageKey = "";
	    		if(UPDATE.equalsIgnoreCase(model.getType())){
	    			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	    		}else{
	    			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
	    		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
	    	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_RESULT_REPEAT));
				return null;
        	}
			
		}
        String path = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
		request.setAttribute("path", path);
		
		HcyhkJcszService hcyhkJcszService = new HcyhkJcszService();
		HcyhkJcszForm jcszModel = hcyhkJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		HcccqjtxForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		 //学年集合
		request.setAttribute("xnList", Base.getXnndList());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXqmc(Base.currXq));
		
		request.setAttribute("jcszModel", jcszModel);
		
		//获取所有火车优惠卡类型
		HcyhklxService hcyhklxService = new HcyhklxService();
		request.setAttribute("hcyhklxList", hcyhklxService.getHcyhklxList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("hcccqjtxFormInfo", StringUtils.formatData(model));
		return mapping.findForward("updateHcccqjtx");
	}
	
	
	/**
	 * 
	 * @描述:TODO(删除火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:21:07
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间填写-删除VALUES:{values}")
	public ActionForward delHcccqjtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjtxService service = new HcccqjtxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteHcccqjtx(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
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
	 * @描述:TODO(提交火车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:45:56
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间填写-提交VALUES:{values}")
	public ActionForward submitHcccqjtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjtxForm model = (HcccqjtxForm) form;
		String ccqjtxid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setCcqjtxid(ccqjtxid);
		model.setXh(xh);
		HcccqjtxService service = new HcccqjtxService();
		//判断提交时间段是否开放
		boolean result = service.submitHcccqjtx(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(撤销火车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:39:22
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间填写-撤销VALUES:{values}")
	public ActionForward cancelHcccqjtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcccqjtxService service = new HcccqjtxService();
		String ccqjtxid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(ccqjtxid,lcid);
		if(result){
			//更新业务状态为'未提交'
			HcccqjtxForm model = new HcccqjtxForm();
			model.setCcqjtxid(ccqjtxid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(ccqjtxid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateHcccqjtxzt(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:TODO(查看火车区间填写信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:39:44
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
	public ActionForward viewHcccqjtx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjtxForm model = (HcccqjtxForm) form;
		HcccqjtxService service = new HcccqjtxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询单个行为信息结果
		request.setAttribute("rs", StringUtils.formatData(service.getHcccqjtxInfo(model)));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("viewHcccqjtx");
		
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:40:08
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
		HcccqjtxForm model = (HcccqjtxForm) form;
		HcccqjtxService service = new HcccqjtxService();
		
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

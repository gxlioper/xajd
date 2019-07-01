/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 上午10:41:42 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.qsgl.QsglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-假期留校
 * @类功能描述: 假期留校action
 * @作者：945
 * @时间： 2013-12-30 上午10:41:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxAction extends SuperAction {
	
	private static final String RCSWXSZBB = "rcswxszbb";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	

	/**
	 * ****************************申请相关*************************
	 */
	/**
	 * 
	 * @描述:假期留校申请列表
	 * @作者：945
	 * @日期：2013-12-31 下午02:34:48
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
	
	public ActionForward jqlxSqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
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
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jcszModel = jqlxszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		// 默认条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_jqlxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxSqList");
	}

	/**
	 * 
	 * @描述:新增假期留校申请
	 * @作者：945
	 * @日期：2013-12-31 下午03:31:09
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
	
	@SystemLog(description="访问日常事务-假期留校-假期留校申请-增加XH:{xh},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward addJqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if("11647".equals(Base.xxdm)){
				request.setAttribute("defaluevalue", xsjbxx.get("xqdm"));
			}
			if (StringUtil.isNull(model.getRzdz())){
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				String rzdz=wdgwsqService.getQsxxJqlx(model.getXh());
//				model.setRzdz(rzdz);
				model.setYzqs(rzdz);
			}
		}
        if (SAVE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType()) ){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		}

        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		super.resetToken(request);
        		boolean result = service.savaJqlxsq(model);
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_jqlx.do?method=addJqlx";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("jcszModel", jqlxszModel);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList2());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(Base.getDqxqmc());
		request.setAttribute("rs", StringUtils.formatData(model));
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		if("10344".equals(Base.xxdm)){
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//浙江传媒学院个性化（留宿校区）
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//获取留校申请类型列表（温大个性化）
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//浙江中医药
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());		
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			if (!StringUtil.isNull(model.getXh())){
				request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
			}
		}
		this.saveToken(request);
		return mapping.findForward("addJqlx");
	}

	/**
	 * 
	 * @描述:修改假期留校申请
	 * @作者：945
	 * @日期：2013-12-31 下午03:31:31
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
	
	@SystemLog(description="访问日常事务-假期留校-假期留校申请-修改SQID:{sqid},XH:{xh},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward updateJqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		boolean result = service.savaJqlxsq(model);
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
        
        JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jcszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jcszModel", jcszModel);
		
        String path = "rcsw_jqlx.do?method=updateJqlx";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		request.setAttribute("jqlxV", jcszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		
		JqlxModel updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		//学年集合
		request.setAttribute("xnList", Base.getXnndList2());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());

		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("rs", model);
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		//浙江传媒学院个性化（留宿校区）
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//获取留校申请类型列表（温大个性化）
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//浙江中医药
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());	
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
		}
		return mapping.findForward("updateJqlx");
	}

	/**
	 * 
	 * @描述:列表提交工作流
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 下午04:35:30
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
	
	@SystemLog(description="访问日常事务-假期留校-假期留校申请-提交SQID:{sqid}")
	public ActionForward submitJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		boolean result = service.submitSq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:申请的撤销
	 * @作者：945
	 * @日期：2013-12-31 下午04:54:14
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
	
	@SystemLog(description="访问日常事务-假期留校-假期留校申请-撤销SQID:{sqid}")
	public ActionForward cancelJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		boolean result = service.cancleRecord(model.getSqid(),model.getLcid());
		if(result){
			//更新业务状态为'未提交'
			JqlxModel myForm = new JqlxModel();
			myForm.setSqid(model.getSqid());
			
			//查看是否有审核退回记录，是：状态为退回
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
				myForm.setSqzt(Constants.YW_YTH);
			}else{
				myForm.setSqzt(Constants.YW_WTJ);
			}
			service.updateModel(myForm);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:删除假期留校记录集合
	 * @作者：945
	 * @日期：2013-12-31 下午04:18:33
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
	
	@SystemLog(description="访问日常事务-假期留校-假期留校申请-删除VALUES:{values}")
	public ActionForward delJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteJqlxsq(values.split(","));
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
	 * @描述:查看假期留校申请
	 * @作者：945
	 * @日期：2013-12-31 下午05:18:26
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
	
	public ActionForward viewJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//result
		request.setAttribute("rs", service.getModel(model));
		//学生基本信息显示配置
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getModel();
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		
		request.setAttribute("jbxxList", jbxxList);
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
		}
		request.setAttribute("model", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewJqlxsq");
	}

	/**
	 * ****************************审核相关*************************
	 */

	/**
	 * 审核列表
	 */
	@SystemAuth(url = "rcsw_jqlxsh.do")
	public ActionForward jqlxShManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getAuddingList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_jqlxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxShList");
	}
	
	/**
	 * 
	 * @描述:审核过程的撤销
	 * @作者：945
	 * @日期：2014-1-2 下午05:05:04
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校审核-撤销SQID:{sqid}")
	public ActionForward cancelJqlxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		//HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());	
		// 业务回滚
		boolean result = service.cancel(model.getSqid());//shxx.get("ywid")
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}

	/**
	 * 
	 * @描述:假期留校审核
	 * @作者：945
	 * @日期：2014-1-2 下午05:04:47
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校审核-审核SQID:{sqid}")
	public ActionForward toViewShDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel myForm = (JqlxModel) form;
		JqlxService service = TransactionControlProxy.newProxy(new JqlxService());
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			User user = getUser(request);
			//保存单个审核
			boolean result = service.singleSh(myForm,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生基本信息显示配置
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(myForm.getXh()));		
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getModel();
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		JqlxModel model=service.getModel(myForm);
		model.setGwid(myForm.getGwid());
		model.setShid(myForm.getShid());
		request.setAttribute("rs", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("shDetail");
	}
	
	/**
	 * 
	 * @描述:批量审核页
	 * @作者：945
	 * @日期：2014-1-9 下午03:03:33
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jqlxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jqlxPlsh");
	}
	
	/**
	 * 
	 * @描述:保存批量审核
	 * @作者：945
	 * @日期：2014-1-9 下午03:07:29
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校审核-批量审核ID:{id}")
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel myForm = (JqlxModel) form;
		JqlxService service = TransactionControlProxy.newProxy(new JqlxService());
		User user = getUser(request);
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 审核导出
	 */
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportSqshData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getResultAllListSqsh(model, user);//查询出所有记录，不分页
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
	 * ***************************结果相关****************************
	 */
	
	/** 
	 * 床位列表
	 */
	
	public ActionForward selectCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		JqlxModel myForm = (JqlxModel) form;
		JqlxService service = new JqlxService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			// 查询
			if(!Base.xxdm.equals("10344")){				
				List<HashMap<String, String>> resultList = service.getCwxxList(myForm, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
			}
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		myForm.setXh(xh);
		String path = "rcsw_jqlx.do?method=selectCwxx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", xh);
		request.setAttribute("sqid", myForm.getSqid());
		return mapping.findForward("selectCwxx");
	}
	
	@SystemAuth(url = "rcsw_jqlxjg.do")
	public ActionForward jqlxJgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getResultList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jcszModel = jqlxszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		// 默认条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_jqlxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxJgList");
	}
	
	/**
	 * 
	 * @描述:新增结果
	 * @作者：945
	 * @日期：2014-1-3 上午09:59:18
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校结果-增加XH:{xh},XN:{xn},XQ:{xq},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward addJqlxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if("11647".equals(Base.xxdm)){
				request.setAttribute("defaluevalue", xsjbxx.get("yxdm"));
			}
			if (StringUtil.isNull(model.getRzdz())){
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				String rzdz=wdgwsqService.getQsxxJqlx(model.getXh());
//				model.setRzdz(rzdz);
				model.setYzqs(rzdz);
				//床位信息設置
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(model.getXh());
				String lddm = qsxx.get("lddm");
				String qsh = qsxx.get("qsh");
				String cwh = qsxx.get("cwh");
				if (!StringUtil.isNull(lddm)
						&& !StringUtil.isNull(qsh)
						&& !StringUtil.isNull(cwh)) {
					model.setCwxx(lddm + "_" + qsh + "_" + cwh);
				}
			}
		}
        if (SAVE.equalsIgnoreCase(model.getType())){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		}

        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		super.resetToken(request);
        		boolean result = service.savaJqlxJg(model);
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_jqlx.do?method=addJqlxJg";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jcszModel", jqlxszModel);
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		//学年集合
		request.setAttribute("xnList", Base.getXnndList2());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("rs", model);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		//浙江传媒学院个性化（留宿校区）
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		if("10344".equals(Base.xxdm)){
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//获取留校申请类型列表（温大个性化）
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//浙江中医药大学
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());		
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			if (!StringUtil.isNull(model.getXh())){
				request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
			}
		}
		this.saveToken(request);
		return mapping.findForward("addJqlxJg");
	}
	
	/**
	 * 
	 * @描述:更新结果
	 * @作者：945
	 * @日期：2014-1-3 上午09:59:04
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校结果-修改SQID:{sqid},XN:{xn},XQ:{xq},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward updateJqlxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType())){
        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		boolean result = true;
        		if("1".equals(model.getSjlx())){
        			result = service.savaJqlxJgShlc(model);
        		}else{
        			result = service.savaJqlxJg(model);
        		}
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
        String path = "rcsw_jqlx.do?method=updateJqlxJg";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jcszModel", jqlxszModel);
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		JqlxModel updateForm = service.getModel(model);
		//床位信息設置
		if (!StringUtil.isNull(updateForm.getLddm())
				&& !StringUtil.isNull(updateForm.getQsh())
				&& !StringUtil.isNull(updateForm.getCwh())) {
			updateForm.setCwxx(updateForm.getLddm() + "_" + updateForm.getQsh() + "_" + updateForm.getCwh());
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		//学年集合
		request.setAttribute("xnList", Base.getXnndList2());
		//学期集合
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("rs", model);
		//衢州学院，安农大个性化
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		//浙江传媒学院个性化（留宿校区）
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		//北京中医药
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//获取留校申请类型列表（温大个性化）
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//浙江中医药大学
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());	
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//上海体育学院
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
		}
		return mapping.findForward("updateJqlxJg");
	}
	
	/**
	 * 
	 * @描述:结果删除
	 * @作者：945
	 * @日期：2014-1-3 上午09:58:47
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校结果-删除VALUES:{values}")
	public ActionForward delJqlxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteJqlxjg(values.split(","));
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
	 * 下载导入模版
	 */
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadXls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		JqlxModel myForm = (JqlxModel) form;
		User user = getUser(request);
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/rcsw/rcsw_jqlx.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.downloadXls(myForm, user, request, wwb);
		return null;
	}
	
	/** 
	 * 转到数据检测和导入页面
	 */
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校结果-导入TABLENAME:{tableName}")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		String tableName = request.getParameter("tableName");//视图名
		String realTable = request.getParameter("realTable");//表名
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		
		String act=request.getParameter("act");
		//导入数据
		if("import".equals(act)){
			uploadFile(mapping, form, request, response);
			
			JqlxService service = new JqlxService();
			String back;
			if ("10344".equals(Base.xxdm)) {
				back= service.importData_10344(request,response);//导入数据
			}else {
				back= service.importData(request,response);//导入数据
			}
			String sfdcExcel=(String)request.getAttribute("sfdcExcel");
			if("yes".equals(sfdcExcel)){
				return mapping.findForward("");
			}
			request.setAttribute("back", back);
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("importData");
	}
	
	/**
	 * 文件上传 
	 * */
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-假期留校-假期留校结果-上传FNAME:{userName}")
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		//该处需要验证超级管理员权限
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		JqlxModel hff = (JqlxModel) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @描述:结果导出
	 * @作者：945
	 * @日期：2014-1-3 上午09:58:33
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getResultAllList(model, user);//查询出所有记录，不分页
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
	 * @描述:打印申请表
	 * @作者：柳俊[工号：1282]
	 * @日期：2015-12-24 下午03:51:09
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
	public ActionForward printjqlxsqb (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		JqlxModel model = (JqlxModel) form;
		if(StringUtils.isNotNull(model.getSqid())){
			List<File> files = new ArrayList<File>();
			String[] ids = model.getSqid().split(",");
			for (String id : ids) {
				model.setSqid(id);
				JqlxModel myForm = service.getModel(model);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				/*//加载家庭成员
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(myForm.getXh());
				if(jtcyxxList.size()>0 && null != jtcyxxList) {					
					StringBuilder jtcylxdhh = new StringBuilder();
					for(int i =0;i<jtcyxxList.size();i++) {
						if(null != jtcyxxList.get(i).get("cylxdh") && !jtcyxxList.get(i).get("cylxdh").equals("")) {
							if(i != jtcyxxList.size()-1) {
								jtcylxdhh.append(jtcyxxList.get(i).get("cylxdh") + ",");
							} else {
								jtcylxdhh.append(jtcyxxList.get(i).get("cylxdh"));
							}					
						}
					}
					xsjbxx.put("jtcylxdh", jtcylxdhh.toString());
					String jtcylxdh = jtcyxxList.get(0).get("cylxdh");
					xsjbxx.put("jtcylxdh", jtcylxdh);						
				}*/
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(myForm.getXh());
				if(jtcyxxList.size()>0 && null != jtcyxxList) {
					for(int i =0;i<jtcyxxList.size();i++) {
						if(null != jtcyxxList.get(i).get("cylxdh") && !jtcyxxList.get(i).get("cylxdh").equals("")) {
							String jtcylxdh = jtcyxxList.get(i).get("cylxdh");
							xsjbxx.put("jtcylxdh", jtcylxdh);
							break;
						}
					}
					
				}
				//拼接留校时间
				String lxsj=DateTranCnDate.fomartDateToCn(myForm.getLxkssj(),FomartDateType.day);
				lxsj+=" —— "+DateTranCnDate.fomartDateToCn(myForm.getLxjzsj(),FomartDateType.day);
				
				xsjbxx.put("lxsj", lxsj);				
				xsjbxx.put("sqly", HtmlUtil.xmlZy(myForm.getSqly()));
				/*if(myForm.getXqmc().equals("春")) {
					xsjbxx.put("jia", "暑");
				}
				if(myForm.getXqmc().equals("秋")) {
					xsjbxx.put("jia", "寒");
				}*/
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	
	public ActionForward printLstxz (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		XsxxService xsxxService=new XsxxService();
		if(StringUtils.isNull(model.getSqid())){
			return null;
		}
		String defaultPhotoPath=request.getSession().getServletContext().getRealPath("/images/type_pic.gif");
		
		List<HashMap<String,String>> stu4List=new ArrayList<HashMap<String,String>>();//4个学生为一个hashmap的list
		List<HashMap<String,String>> resultList=service.getJgdcList(model.getSqid());
		HashMap<String,String>stu4map=null;//一个map里存放4个学生的信息
		int j=resultList.size()%4;
		while(j<4&&j>0){
			resultList.add(new HashMap<String,String>());
			j++;
		}
		for(int i=0;i<resultList.size();i++){
			j=i%4;
			if(j==0){
				stu4map=new HashMap<String,String>();
			}
			HashMap<String,String>map=resultList.get(i);
			String xh=map.get("xh");
			map.put("photo", xsxxService.getPhotoBase64(StringUtils.isNotNull(xh)?xh:"null",defaultPhotoPath));
			stu4map=jointMap(map,stu4map,Integer.toString(j));
			if(j==3){
				stu4List.add(stu4map);
			}
		}
		HashMap<String,Object> data=new HashMap<String,Object>();
		data.put("stu4List", stu4List);
		data.put("nd", Base.currNd);
		File file = service.getWord(data);
		FileUtil.outputWord(response, file);
		return null;
	}
	
	private HashMap<String,String>jointMap(HashMap<String,String>map,HashMap<String,String>map2,String str){
		for(Entry<String,String> entry:map.entrySet()){
			map2.put(entry.getKey()+str,entry.getValue());
		}
		return map2;
	}
	
}

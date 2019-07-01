/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

import java.io.File;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 宿舍异动管理模块
 * @类功能描述: 宿舍异动申请
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-25 上午10:27:32 
 * @版本： V1.0
 */
public class YdsqAction extends SuperAction {
	
	private static final String url = "ydsqbase.do";
	
	/**
	 * 是否已申請：0[有申請中的未审核完了的申请]
	 */
	private static final String SFYSQ_YES = "0";
	/**
	 * 是否已申請：1[没有申請中的未审核完了的申请]
	 */
	private static final String SFYSQ_NO = "1";
	
	/**
	 *  退宿
	 */
	public static String _YDLX_TS = "00";
	/**
	 *  宿舍调整
	 */
	public static String _YDLX_SSTZ = "01";
	/**
	 *  入住
	 */
	public static String _YDLX_SSRZ = "03";
	
	/**
	 * 定义公寓管理宿舍异动可以从基本信息表中获取学生信息
	 */
	private static final String GYGLSSYD = "gyglssyd";
	private List<HashMap<String,String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();

	/**
	 * 
	 * @描述:宿舍异动列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("ydsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "ydsqbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("list");
	}


	/**
	 * 
	 * @描述:床位调整选择（调整）
	 * @作者：qilm
	 * @日期：2013-9-17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward selectCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdsqForm myForm = (YdsqForm) form;
		YdsqService service = new YdsqService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydsq.do?method=selectCwxx");
			myForm.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getCwxxList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		myForm.setXh(xh);
		String path = "ydsq.do?method=selectCwxx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", xh);
		
		return mapping.findForward("selectCwxx");
	}
	
	/**
	 * 
	 * @描述:床位调整选择（入住）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-14 上午10:49:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward selectRzcwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		YdsqForm myForm = (YdsqForm) form;
		YdsqService service = new YdsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (QUERY.equals(myForm.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydsq.do?method=selectRzcwxx");
			myForm.setSearchModel(searchModel);
			
			if (!StringUtil.isNull(myForm.getRzcwxx())){
				request.setAttribute("qsmcCk", service.getInShz(myForm.getRzcwxx())? "0":"1");
//				request.setAttribute("qsmcCk1", service.getSfjl(myForm.getRzcwxx())? "0":"1");
			}
			
			// 查询
			List<HashMap<String, String>> resultList = service.getRzcwxxList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		myForm.setXh(xh);
		String path = "ydsq.do?method=selectRzcwxx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", xh);
		
		return mapping.findForward("selectRzcwxx");
	}
	
	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动申请-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
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
	 * @描述: 宿舍异动模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动申请-修改PK:{ssydsqid},XH:{xh},SSYDLX:{ssydlx},XN:{xn},XQ:{xq},TSTZYY:{tstzyy},TSTZSJ:{tstzsj}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		
		User user = getUser(request);

		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String result = service.update(myForm,"save");
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("该床位已被使用！"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}else if ("submit".equalsIgnoreCase(myForm.getType())) {
			if (!"stu".equals(user.getUserType())){
				myForm.setTjsqrxm(user.getUserName());
			}		
			String result = service.update(myForm,"submit");
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("该床位已被使用！"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));	
			}
			return null;
		}
		YdsqForm model = service.getModel(myForm);
		//床位信息設置
		if (!StringUtil.isNull(model.getTzhlddm())
				&& !StringUtil.isNull(model.getTzhqsh())
				&& !StringUtil.isNull(model.getTzhcwh())) {
			model.setCwxx(model.getTzhlddm() + "_" + model.getTzhqsh() + "_"
					+ model.getTzhcwh());
		}
		String ssydlx = model.getSsydlx();
		if(ssydlx.equals(YdsqService._YDLX_RZ)){
			//床位信息設置
			if (!StringUtil.isNull(model.getTzqlddm())
					&& !StringUtil.isNull(model.getTzqqsh())
					&& !StringUtil.isNull(model.getTzqcwh())) {
				model.setRzcwxx(model.getTzqlddm() + "_" + model.getTzqqsh() + "_"
						+ model.getTzqcwh());
			}
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		//学生基本信息
		String path = "ydsq.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//退宿原因
		request.setAttribute("tstzyyList", service.getTstzyy());
		//调整原因
		request.setAttribute("tzyyList", service.getTzyy());
		//入住原因
		CwglService cwglService = new CwglService();
		request.setAttribute("rzyyList", cwglService.getRzyyList());
		//床位信息
		request.setAttribute("cwxxData", StringUtils.formatData(service.getCwxx(model.getTzqlddm(),
				model.getTzqqsh(), model.getTzqcwh())));
		//杭州职业技术学院个性化 显示申请的学期学年
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("ydsqxg");
	}
	
	
	/**
	 * 
	 * @描述:工作流的提交
	 * @作者：945
	 * @日期：2013-11-25 下午04:09:04
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
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动申请-提交PK:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		User user = getUser(request);
		String values = request.getParameter("values");
		String ydlx = request.getParameter("ydlx");
		String xh = request.getParameter("xh");
		String ssydlx = request.getParameter("ssydlx");
		String lcid = "";
		
		YdsqForm myModel = service.getModel(values);
		
		// 已退回的状态,取老的审核流
		if(Constants.YW_YTH.equals(myModel.getShzt())){
			lcid = myModel.getSplcid();	
			
		}else{
			//取最新的审核流程
			ShlcForm sf = service.getShlcForm(ydlx);
			//ShlcForm sf = service.getShlcForm();
			if (null == sf) {
				lcid = "";
			}else{
				lcid = sf.getSplcid();
			}
		}
		// 未设定审核流则提示
		if(StringUtils.isNull(lcid)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SELECT_SHLC_FAIL));
			return null;
		}
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		boolean result = service.submitRecord(ydlx,values,lcid,xh);
		if(result){
			//更新业务状态为'审核中'
			YdsqForm model = new YdsqForm();
			if (!"stu".equals(user.getUserType())){
				model.setTjsqrxm(user.getUserName());
			}	
			model.setSsydsqid(values);
			model.setShzt(Constants.YW_SHZ);
			model.setSplcid(lcid);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动申请-撤销PK:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values,lcid);
		if(result){
			//更新业务状态为'未提交'
			YdsqForm model = new YdsqForm();
			model.setSsydsqid(values);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
/**
 * 
 * @描述:增加
 * @作者：张昌路[工号：982]
 * @日期：2013-9-17 上午10:44:10
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return  ActionForward 返回类型 
 * @throws Exception
 *
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动申请-增加XH:{xh},SSYDLX:{ssydlx},XN:{xn},XQ:{xq},TSTZYY:{tstzyy},TSTZSJ:{tstzsj}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			// 设定是否已申请
			request.setAttribute("sfysq", service.getSfysq(myForm.getXh())?SFYSQ_YES : SFYSQ_NO);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			String result = service.save(myForm,"save");
			
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("该床位已被使用！"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
			
		}else if ("submit".equalsIgnoreCase(myForm.getType())) {
			if (!"stu".equals(user.getUserType())){
				myForm.setTjsqrxm(user.getUserName());
			}
			String result = service.save(myForm,"submit");
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("该床位已被使用！"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		//get student detail
		//学生基本信息
		String path = "ydsq.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//退宿原因
		request.setAttribute("tstzyyList", service.getTstzyy());
		//调整原因
		request.setAttribute("tzyyList", service.getTzyy());
		//入住原因
		CwglService cwglService = new CwglService();
		request.setAttribute("rzyyList", cwglService.getRzyyList());
		request.setAttribute("dqxq",Base.getDqxqmc());
		request.setAttribute("dqxn",Base.currXn);
		//床位信息
		request.setAttribute("cwxxData",StringUtils.formatData(service.getCwxxForXh(myForm.getXh())));
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyy-MM-dd"));
		return mapping.findForward("ydsqzj");
	}
	/**
	 * 
	 * @描述:显示具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-17 下午05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		YdsqForm model = service.getModel(myForm);
		
		// 学生信息
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		request.setAttribute("ssydlx", model.getSsydlx());
		
		// 原床位信息
		request.setAttribute("cwxxData", service.getCwxx(model.getTzqlddm(),
				model.getTzqqsh(), model.getTzqcwh()));
		// ======== 查询申请结果 begin ========
		HashMap<String, String> cwxxYdjg = service.getCwxxYdjg(model.getXh(), model.getSsydsqid());
		//如果是宿舍调整调用调整保存
		if(model.getSsydlx().equals(_YDLX_SSTZ)){
			if(!StringUtil.isNull(cwxxYdjg.get("ydqlddm"))){
				model.setTzqlddm(cwxxYdjg.get("ydqlddm")); 
				model.setTzqldmc(cwxxYdjg.get("ydqldmc")); 
				model.setTzqqsh(cwxxYdjg.get("ydqqsh")); 
				model.setTzqcwh(cwxxYdjg.get("ydqcwh")); 
			}
			if(!StringUtil.isNull(cwxxYdjg.get("ydhlddm"))){
				model.setTzhlddm(cwxxYdjg.get("ydhlddm")); 
				model.setTzhldmc(cwxxYdjg.get("ydhldmc")); 
				model.setTzhqsh(cwxxYdjg.get("ydhqsh")); 
				model.setTzhcwh(cwxxYdjg.get("ydhcwh")); 
			}
		}else if(model.getSsydlx().equals(_YDLX_SSRZ)){//入住保存
			if(!StringUtil.isNull(cwxxYdjg.get("ydhlddm"))){
				model.setTzqlddm(cwxxYdjg.get("ydhlddm")); 
				model.setTzqldmc(cwxxYdjg.get("ydhldmc")); 
				model.setTzqqsh(cwxxYdjg.get("ydhqsh")); 
				model.setTzqcwh(cwxxYdjg.get("ydhcwh")); 
			}
		}
		// ======== 查询申请结果 end ========
		return mapping.findForward("ydsqck");
	}
	

	/**
	 * 
	 * @描述: 宿舍异动申请导出
	 * @作者：qilm
	 * @日期：2013-10-12
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
		YdsqForm model = (YdsqForm) form;
		YdsqService service = new YdsqService();

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
	 * @描述 调宿通知单打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-5 下午01:33:31
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
	public ActionForward printTstzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		String ssydsqid = request.getParameter("ssydsqid");
		if(ssydsqid != null && ssydsqid.split(",").length == 1){	/*-->下载单个表格*/
			HashMap<String , String> data = service.getYdsqxx(ssydsqid);
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
			objectData.putAll(data);
			File file = null;
			String xxdm = Base.xxdm;
			String guid = "gygl_tstzd_"+xxdm;
			file = BbdmUtils.getSigleFile(guid, objectData);
			if(null==file){
				//通用登记表
				file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
			}
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String ssydsqids:ssydsqid.split(",")){
				HashMap<String , String> data = service.getYdsqxx(ssydsqids);
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
				objectData.putAll(data);
				File file = null;
				String xxdm = Base.xxdm;
				String guid = "gygl_tstzd_"+xxdm;
				file = BbdmUtils.getSigleFile(guid, objectData);
				if(null==file){
					//通用登记表
					file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
				}
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
}

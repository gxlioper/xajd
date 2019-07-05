/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.hdgl.cssz.CsszForm;
import com.zfsoft.xgxt.hdgl.cssz.CsszService;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	： HdblsqshAction
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-16 下午05:33:56
 * @version 	V1.0 
 */

public class HdblsqshAction extends SuperAction<HdblsqshForm, HdblsqshService>{
	
	private static final String url = "hdgl_hdbl_hdblsq.do";
	//学生基本信息配置
	private static List<HashMap<String, String>> jbxxList = null;
	
	public static String HDBL = "hdbl";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	
	/**
	 * @description	： 补录列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 下午05:46:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdblsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;		
		HdblsqshService service = new HdblsqshService();
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "hdgl_hdbl_hdblsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdblsqList");
	}
	
	/**
	 * @description	： 增加活动补录申请
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-17 下午02:16:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		HdblsqshService service = new HdblsqshService();
		HashMap<String,String> map = service.gethdblInfo(model);
		if(null != map){
			request.setAttribute("rs", map);
			if(StringUtils.isNotNull(model.getLy())){
				HashMap<String,String> map2 = service.gethdInfo(model.getHdid());
				map2.remove("fjpath");//不替换原有附件
				map.putAll(map2);
			}
			BeanUtils.copyProperties(model, StringUtils.formatData(map));
			/*活动标签*/
			if(StringUtils.isNotNull(map.get("hdbq"))){
				model.setHdbq(map.get("hdbq"));
				model.setHdbqs(map.get("hdbq").split(","));
				model.setHdbqmc(map.get("hdbqmc"));
			}
		/*能力标签*/
			if(StringUtils.isNotNull(map.get("nlbq"))){
				model.setNlbq(map.get("nlbq"));
				model.setNlbqs(map.get("nlbq").split(","));
				model.setNlbqmc(map.get("nlbqmc"));
			}
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "hdgl_hdblsq.do?method=addHdblsq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		//获取当前学期
		request.setAttribute("currXq", service.getCurrXq());
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		//活动类型列表
		List<HashMap<String, String>> hdlxList = service.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);
		
		HdbljgService hdbljgService = new HdbljgService();
		/*课程类型列表*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//获取当前学期
		request.setAttribute("currXq", service.getCurrXq());
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));

		/*主讲人职称*/
		List<HashMap<String, String>> zjrzcList = service.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);

		/*能标签列表*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);



		//自选课程列表
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*能力标签*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);
		
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("addHdblsq");
	}
	
	/**
	 * @description	： 修改活动补录
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:13:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		HdblsqshService service = new HdblsqshService();
		HashMap<String,String> map = service.gethdblInfo(model);
		if(null != map){
			request.setAttribute("rs", map);
			if(StringUtils.isNotNull(model.getLy())){
				HashMap<String,String> map2 = service.gethdInfo(model.getHdid());//获取选中的活动信息
				map2.remove("fjpath");//不替换原有附件
				map.putAll(map2);
			}
			BeanUtils.copyProperties(model, StringUtils.formatData(map));
			/*活动标签*/
			if(StringUtils.isNotNull(map.get("hdbq"))){
				model.setHdbq(map.get("hdbq"));
				model.setHdbqs(map.get("hdbq").split(","));
				model.setHdbqmc(map.get("hdbqmc"));
			}
		/*能力标签*/
			if(StringUtils.isNotNull(map.get("nlbq"))){
				model.setNlbq(map.get("nlbq"));
				model.setNlbqs(map.get("nlbq").split(","));
				model.setNlbqmc(map.get("nlbqmc"));
			}
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		String path = "hdgl_hdblsq.do?method=updateHdblsq&sqid=" + model.getSqid();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);

		//活动类型列表
		List<HashMap<String, String>> hdlxList = service.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);

		HdbljgService hdbljgService = new HdbljgService();

		/*活动标签列表*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);

		/*讲座类型列表*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//自选课程列表
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*能力标签*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*主讲人职称*/
		List<HashMap<String, String>> zjrzcList = service.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);

		/*返回活动形式*/
		request.setAttribute("hdxs", model.getHdxs());
		return mapping.findForward("updateHdblsq");
	}
	
	/**
	 * @description	： 保存
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-17 下午04:10:54
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		CsszService csszService = new CsszService();
		CsszForm csszForm = csszService.getCsszForm();
		if(null != csszForm){
			model.setSplc(csszForm.getBl());
		}
		HdblsqshService hdblsqshService = TransactionControlProxy.newProxy(new HdblsqshService());
		boolean result = false;
	    User user = getUser(request);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
			result = hdblsqshService.saveSq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = hdblsqshService.saveSqUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 撤销活动补录申请
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:41:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshService service = new HdblsqshService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			HdblsqshForm model = new HdblsqshForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	：提交活动补录申请
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 上午11:50:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		String values = request.getParameter("values");
		HdblsqshService service = new HdblsqshService();
		model.setSqid(values);
		CsszService csszService = new CsszService();
		CsszForm csszForm = csszService.getCsszForm();
		if(null != csszForm){
			model.setSplc(csszForm.getBl());
		}
		boolean result = service.submitHdblsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 删除活动补录申请
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 下午01:48:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		HdblsqshService service = new HdblsqshService();
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
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
	 * @description	： 查看
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 下午01:56:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewHdblsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		request.setAttribute("jbxxList", jbxxList);
		HdblsqshService service = new HdblsqshService();
		HashMap<String,String> map = service.gethdblInfo(model);
		if(null != map){
			BeanUtils.copyProperties(model, map);
			/*活动标签*/
			if(StringUtils.isNotNull(map.get("hdbq"))){
				model.setHdbq(map.get("hdbq"));
				model.setHdbqs(map.get("hdbq").split(","));
				model.setHdbqmc(map.get("hdbqmc"));
			}
		/*能力标签*/
			if(StringUtils.isNotNull(map.get("nlbq"))){
				model.setNlbq(map.get("nlbq"));
				model.setNlbqs(map.get("nlbq").split(","));
				model.setNlbqmc(map.get("nlbqmc"));
			}
		}
		if(!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		return mapping.findForward("viewHdblsq");

	}

	/**
	 * 获取当前系统已有的活动
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getHdxxList(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm)form;
		HdblsqshService hdblsqshService = new HdblsqshService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = hdblsqshService.getHdxxList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("path","hdgl_hdgl_hdqd_wh.do?method=getHdxxList");
		return mapping.findForward("hdxxList");
	}
	
}

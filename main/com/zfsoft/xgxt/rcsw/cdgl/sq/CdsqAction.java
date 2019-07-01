/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午04:48:28 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import java.io.File;
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
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地申请Action 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 下午04:48:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdsqAction extends SuperAction {

	/**
	 * @描述 获取部门列表
	 */
	private DwwhService dwwhService = new DwwhService();
	
	/**
	 * @描述 学生显示信息表单服务
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @属性： service 场地申请服务类
	 */
	private CdsqService service = new CdsqService();
	
	/**
	 * @属性： cdxxwhService 场地信息维护服务
	 */
	public CdxxwhService cdxxwhService = new CdxxwhService();
	
	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "rcsw_cdgl_cdsqgl.do";
	
	public static final String url = "rcsw_cdgl_cdsqgl.do";
	
	/**
	 * @属性： CDGL_BBID 表单配置id
	 */
	private static final String CDGL_BBID = "rcswcdgl"; 
	
	/**
	 * 
	 * @描述 ：构造方法
	 */
	public CdsqAction(){
		super();
		jbxxList = bdpzService.getJbxxpz(CDGL_BBID);
	}
	
	/**
	 * 
	 * @描述:页面dispatcher 场地申请查询页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
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
	public ActionForward cdsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdsqCx");
	}
	/**
	 * 
	 * @描述:场地申请情况查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-15 上午11:45:12
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
	@SystemAuth(url = "rcsw_cdgl_cdsqck.do")
	public ActionForward cdsqjlCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			//查询
			List<HashMap<String, String>> resultList = service.getPageListOfSqjl(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//在日期控件上匹配当前日期
		model.setSqsjdkssj(GetTime.getTimeByFormat("yyyy-MM-dd"));
		request.setAttribute("path", "rcsw_cdgl_cdsqck.do");
		request.setAttribute("rs", model);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdsqjlCx");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		User user = getUser(request);
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
	
	/**
	 * 
	 * @描述:页面dispatcher 场地申请申请页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 上午10:13:15
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
	public ActionForward cdsqSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pathForXh = "rcsw_cdgl_cdsq.do?method=cdsqSq";
		String pathForCd = "rcsw_cdgl_cdsq.do?method=cdsqSq";
		CdsqForm model = (CdsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			model.setBmlbdm(xsjbxx.get("xydm"));//默认设置学生所在学院
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			pathForCd = pathForCd + (StringUtil.isNull(model.getXh()) ? "" : ("&xh=" + model.getXh()));
		}
		HashMap<String , String> cdxx = new HashMap<String , String>();
		if(!StringUtil.isNull(model.getCdid())){
			cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			pathForXh = pathForXh + (StringUtil.isNull(model.getCdid()) ? "" : ("&cdid=" + model.getCdid()));
		}
		request.setAttribute("cdxx", cdxx); //查询场地信息
		// 部门列表
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", URLEncoder.encode(pathForXh, "gbk"));
		request.setAttribute("pathForCd", URLEncoder.encode(pathForCd, "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cdsqSq");
	}

	/**
	 * 
	 * @描述:新增保存场地申请
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请-增加XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs}," +
			"FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward addCqsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		HashMap<String , Object> result = null;
		JSONObject json = null;
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			boolean checkConditions = service.checkSqSjd(model); //检查申请条件
			
			if(!checkConditions){
				json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				json.put("flag", "checkerror");
				response.getWriter().print(json);
				return null;
			}else{
				super.resetToken(request);
			}
		}

		result = service.saveCdsqNoSubmit(model);
		
		json = getJsonMessageByKey((Boolean)result.get("ISSUCCESS") ? 
				MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:新增保存并提交场地申请
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请-提交SQID:{sqid}")
	public ActionForward addSubmitCqsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		HashMap<String , Object> result = service.saveCdsqWithSubmit(model);
		JSONObject json = null;
		if(result.get("CODE") != null && StringUtils.equals((String) result.get("CODE"), "CHK_ERROR")){
			json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
			json.put("flag", "checkerror");
		}else{
			json = getJsonMessageByKey((Boolean)result.get("ISSUCCESS") ? 
					MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL);
		}
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:提交场地申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 上午08:45:16
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请-提交SQID:{sqid}")
	public ActionForward submitCdsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model  = (CdsqForm) form;
		
		CdsqForm modelGet = service.getModel(model.getSqid());
		
		// 已退回的记录取老的审核流程ID;非已退回记录则再去取审核流程
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){
			CdxxwhService service = new CdxxwhService();
			CdxxwhForm cdxx = new CdxxwhForm();
			cdxx.setCdid(modelGet.getCdid());
			cdxx = service.getModel(cdxx);
			if(cdxx!=null){
				model.setSplcid(cdxx.getSplcid());
				model.setQxfw(cdxx.getQxfw());
			}
			
		}else{
			model.setSplcid(modelGet.getSplcid());
			model.setQxfw(modelGet.getQxfw());
		}
		
		boolean isSuccess = service.submitCdsq(model);
		String message =isSuccess ? MessageUtil.getText(
				MessageKey.SYS_SUBMIT_SUCCESS) : MessageUtil
				.getText(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:删除场地申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午03:52:58
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请-删除SQIDS:{sqids}")
	public ActionForward deleteCdsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sqids = request.getParameter("sqids"); //带删除的sqids
		
		int isSuccess = service.deleteCdsq(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:撤销场地申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午05:50:52
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请-撤销SQID:{sqid}")
	public ActionForward cancelCdsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model  = (CdsqForm) form;
		
		boolean isSuccess = service.cancelCdsq(model.getSqid()); //撤销申请
		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:查看场地申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 上午09:11:20
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
	public ActionForward cdsqCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model  = (CdsqForm) form;
		String sqid = model.getSqid(); //获取申请id
		HashMap<String , String> cdsqxx = service.getCdsqxx(sqid);//获取场地申请信息
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		String path = "rcsw_cdgl_cdsq.do?method=cdsqCk";
		request.setAttribute("cdsqxx", cdsqxx); 
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdsqCk");
	}
	
	/**
	 * 
	 * @描述:页面dispatcher 场地申请修改页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 上午10:13:15
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
	public ActionForward cdsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "rcsw_cdgl_cdsq.do?method=cdsqXg";
		CdsqForm model = (CdsqForm) form;
		String sqid = model.getSqid(); //获取申请id
		if(StringUtils.isNotNull(sqid)){
			CdsqForm dataModel = service.getModel(sqid);
			StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
		}

		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		if(!StringUtil.isNull(model.getCdid())){
			HashMap<String , String> cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			request.setAttribute("cdxx", cdxx); //查询场地信息
		}
		// 部门列表
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cdsqXg");
	}
	
	/**
	 * 
	 * @描述:更新场地申请action
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-29 上午10:04:56
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
	@SystemLog(description="访问日常事务-场地管理-场地使用申请-修改SQID:{sqid},XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs}," +
			"FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward cdsqXgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdsqForm model = (CdsqForm) form;
		model.setSqly(HtmlUtil.xmlZy(model.getSqly()));

		CdsqForm modelGet = service.getModel(model.getSqid());
		
		// 已退回的记录取老的审核流程ID;非已退回记录则再去取审核流程
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){

			CdxxwhService service = new CdxxwhService();
			CdxxwhForm cdxx = new CdxxwhForm();
			cdxx.setCdid(modelGet.getCdid());
			cdxx = service.getModel(cdxx);
			if(cdxx!=null){
				model.setSplcid(cdxx.getSplcid());
				model.setQxfw(cdxx.getQxfw());
			}
			
		}else{
			model.setCdid(modelGet.getCdid());
			model.setSplcid(modelGet.getSplcid());
			model.setQxfw(modelGet.getQxfw());
			//　保存，则审核状态仍未：退回
			if(SAVE.equalsIgnoreCase(request.getParameter("type"))){
				model.setShzt(Constants.YW_YTH);
			}
		}
		
		boolean isSuccess = service.updateCdsqNoSubmit(model); //更新数据库
		
		String type = request.getParameter("type");
		JSONObject json = null;
		if(isSuccess && "submit".equals(type)){
			isSuccess = service.submitCdsq(model);
			if(isSuccess){
				json = getJsonMessageByKey(MessageKey.SYS_SUBMIT_SUCCESS);
			}else{
				json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				json.put("flag", "checkerror");
			}
		}else{
			if(isSuccess){
				json = getJsonMessageByKey(MessageKey.SYS_SAVE_SUCCESS);
			}else{
				json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				json.put("flag", "checkerror");
			}
		}
		response.getWriter().print(json);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 验证是否可提交
	 * @作者：qilm
	 * @日期：2014-5-26
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

		CdsqForm model = (CdsqForm) form;
		String cdid = request.getParameter("cdid");
		model.setCdid(cdid);
		
		// 取得是否存在验证(根据异动类别) true:可提交/false：不可提交
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
	/**
	 * 
	 * @描述 导出场地申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-17 下午04:19:00
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
	public ActionForward exportCdsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sqid = request.getParameter("sqid");
		File wordFile = getWord(sqid,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getWord(String sqid,HttpServletRequest request) throws Exception{

		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		String flag = request.getParameter("flag");
		String tableName = flag.equals("sq") ? "XG_XLJK_CDGL_CDSQB" :"XG_XLJK_CDGL_CDSQJGB";
		//审批流程
		List<HashMap<String,String>> shyjList = cdxxwhService.getShyjList(sqid);
		
		HashMap<String,String> jgMap = cdxxwhService.getCdxx(sqid, tableName);
		data.putAll(jgMap);
		for (int i = 0; i < shyjList.size(); i++) {
			data.put("shyj"+i, shyjList.get(i).get("shyj"));
		}
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"rcsw/cdsqb.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"rcsw","cdsqb.xml",FreeMarkerUtil.getFileName(jgMap.get("bmmc")+"场地申请表"));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return file;
	}
	
	/**
	 * 
	 * @描述 导出场地申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-17 下午04:19:00
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
	public ActionForward exportCdsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}

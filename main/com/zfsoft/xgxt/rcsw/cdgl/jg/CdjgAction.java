/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:38:19 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqService;
import com.zfsoft.xgxt.rcsw.jzbg.JzbgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地结果管理ACTION
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-29 下午03:38:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdjgAction extends SuperAction {
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
	private CdsqService cdsqService = new CdsqService();
	
	/**
	 * @属性： service 场地结果服务类
	 */
	private CdjgService service = new CdjgService();
	
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
	public static final String PATH = "rcsw_cdgl_cdjggl.do";
	
	public static final String url = "rcsw_cdgl_cdjggl.do";
	
	/**
	 * @属性： CDGL_BBID 表单配置id
	 */
	private static final String CDGL_BBID = "rcswcdgl"; 
	
	/**
	 * 
	 * @描述 ：构造方法
	 */
	public CdjgAction(){
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
	public ActionForward cdjgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdjgCx");
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
		CdjgForm model = (CdjgForm) form;
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
	 * @描述:页面dispatcher 场地结果新增页面
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
	public ActionForward cdjgSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pathForXh = "rcsw_cdgl_cdjg.do?method=cdjgSq";
		String pathForCd = "rcsw_cdgl_cdjg.do?method=cdjgSq";
		CdjgForm model = (CdjgForm) form;
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
		request.setAttribute("cdxx", xgxt.utils.String.StringUtils.formatData(cdxx)); //查询场地信息
		// 部门列表
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", URLEncoder.encode(pathForXh, "gbk"));
		request.setAttribute("pathForCd", URLEncoder.encode(pathForCd, "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cdjgSq");
	}
	
	/**
	 * 
	 * @描述:新增保存场地结果
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
	@SystemLog(description="访问日常事务-场地管理-场地使用结果-增加XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs},FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward addCdjgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdjgForm model = (CdjgForm) form;
		JSONObject message;
		
		model.setSjly("0"); //数据来源
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			
			boolean checkConditions = service.checkSqSjd(model);

			if(!checkConditions){
				message =  getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);	
				message.put("flag", "checkerror");
				response.getWriter().print(message);
				return null;
			}else{
				super.resetToken(request);
			}
		}
		
		boolean isSuccess = service.runInsert(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:查看场地结果
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
	public ActionForward cdjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdjgForm model  = (CdjgForm) form;
		String jgid = model.getJgid(); //获取结果id
		HashMap<String , String> cdjgxx = service.getCdjgxx(jgid);//获取场地结果信息
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		String path = "rcsw_cdgl_cdjg.do?method=cdjgCk";
		request.setAttribute("cdjgxx", xgxt.utils.String.StringUtils.formatData(cdjgxx)); 
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdjgCk");
	}
	
	/**
	 * 
	 * @描述:页面dispatcher 场地结果修改页面
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cdjgXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "rcsw_cdgl_cdjg.do?method=cdjgXg";
		CdjgForm model = (CdjgForm) form;
		String jgid = model.getJgid(); //获取申请id
		if(StringUtils.isNotBlank(jgid)){
			CdjgForm dataModel = service.getModel(jgid);
			xgxt.utils.String.StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
		}

		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		if(!StringUtil.isNull(model.getCdid())){
			HashMap<String , String> cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			request.setAttribute("cdxx", xgxt.utils.String.StringUtils.formatData(cdxx)); //查询场地信息
		}
		// 部门列表
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cdjgXg");
	}
	
	/**
	 * 
	 * @描述:更新场地结果action
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
	@SystemLog(description="访问日常事务-场地管理-场地使用结果-修改JGID:{jgid},XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs},FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward cdjgXgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdjgForm model = (CdjgForm) form;
		
		model.setSqly(HtmlUtil.xmlZy(model.getSqly()));
		
		boolean isSuccess = service.runUpdate(model); //更新数据库
		
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:删除场地结果
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-30 上午11:19:23
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
	@SystemLog(description="访问日常事务-场地管理-场地使用结果-删除CDJGIDS:{cdjgids}")
	public ActionForward deleteCdjgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cdjgids = request.getParameter("cdjgids"); //删除的ids
		int isSuccess = 0;
		if(StringUtils.isNotBlank(cdjgids)){
			isSuccess = service.runDelete(cdjgids.split(","));
		}
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);	
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-30 下午04:59:15
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
		CdjgForm model = (CdjgForm) form;
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
	 * @description	： 评价
	 * @author 		： CP（1352）
	 * @date 		：2017-12-18 下午05:57:18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			CdjgForm model = (CdjgForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
				boolean flag = service.updatePjxx(model);
				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_cdgl_cdjg?method=pj";
		request.setAttribute("path", path);
		return mapping.findForward("pj");
	}
/**
 * @description	： 是否已评价
 * @author 		： CP（1352）
 * @date 		：2017-12-18 下午08:07:39
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward sfpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdjgForm model = (CdjgForm) form;
		boolean flag = service.isExistPj(model);
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", flag?"0":"1");
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
}

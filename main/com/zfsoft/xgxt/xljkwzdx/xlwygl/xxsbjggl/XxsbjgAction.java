/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 上午10:39:16 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl.XxsbService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl.ZbrcService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 上午10:39:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbjgAction extends SuperAction {

	private XxsbjgService service = new XxsbjgService();
	/**
	 * @描述 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "xljk_xlwygl_xxsbjggl.do";
	
	public static final String url = "xljk_xlwygl_xxsbjggl.do";
	
	/**
	 * 周报日程service
	 */
	private ZbrcService zbrcService = new ZbrcService();
	
	
	/**
	 * @属性： BBID 表单配置id
	 */
	private static final String BBID = "xlzx_xlwy"; 

	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	public XxsbjgAction(){
		jbxxList = bdpzService.getJbxxpz(BBID);
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward cx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块仅不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
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
	 * @描述:查看
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model  = (XxsbjgForm) form;
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbjgid());
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(sbxxdata.get("xh"));
		
		//上报类型
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "平时信息上报");
		}
		request.setAttribute("sbxxdata", xgxt.utils.String.StringUtils.formatData(sbxxdata));
		request.setAttribute("path", PATH);
		request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		HashMap<String , String> sbxx = new HashMap<String, String>();
		//上报类型
		String sblx = model.getSblx();
		
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		if(StringUtils.isNotBlank(model.getSblx())){
			List<HashMap<String , String>> zbrcLx = zbrcService.getZbrcListByLx(model.getSblx());
			request.setAttribute("zbrcLxList", zbrcLx);
		}
		sbxx.put("sblx", sblx);
		if(StringUtils.equals("0", sblx)){
			sbxx.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxx.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxx.put("sblxmc", "平时信息上报");
		}
		request.setAttribute("sbxx", xgxt.utils.String.StringUtils.formatData(sbxx));
		if(!"2".equals(sblx)) {
			request.setAttribute("xnList", service.getXnList());
			request.setAttribute("xqList", Base.getXqList());
		}else {
			request.setAttribute("xnList", Base.getXnndList2());
			request.setAttribute("xqList", Base.getXqList());
		}
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_xxsbjgglwh.do?method=sb&sblx="+sblx, "gbk"));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("sb");
	}
	
	/**
	 * 
	 * @描述:保存
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
	public ActionForward saveAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		JSONObject message = new JSONObject();

		String xh = model.getXh();
		String zc = model.getSbzbid();
		String sblx = model.getSblx();
		
		if("0".equals(sblx) || "1".equals(sblx)){
			boolean check = service.checkExist(xh , sblx , zc);
			if(check){
				message = getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL);
				message.put("code", "-2");
				response.getWriter().print(message);
				return null;
			}
		}
		
		
		model.setSbsj(DateUtils.getCurrTime());
		model.setSjly("0");
		model.setSbjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		if(!"2".equals(model.getSblx())) {
			XxsbService Xservice = new XxsbService();
			HashMap<String , String> xnxqData = Xservice.getXnXqmc(model.getSbzbid());
			model.setXn(xnxqData.get("xn"));
			model.setXq(xnxqData.get("xq"));
		}
		boolean isSuccess = service.runInsert(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		message.put("code", isSuccess ? "1" : "0");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:删除
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
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pks = request.getParameter("pks"); //带删除的sqids
		
		if(pks == null)
			pks = "";
		
		int isSuccess = service.runDelete(pks.split(","));
		
		String message = isSuccess >= 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbjgid());
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(sbxxdata.get("xh"));
		
		//上报类型
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "平时信息上报");
		}
		request.setAttribute("sbxxdata", xgxt.utils.String.StringUtils.formatData(sbxxdata));
		request.setAttribute("path", PATH);
		request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xg");
	}
	
	//修改
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		JSONObject message = new JSONObject();
		boolean isSuccess = service.runUpdate(model);
		
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		message.put("code", isSuccess ? "1" : "0");
		response.getWriter().print(message);
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
		XxsbjgForm model = (XxsbjgForm) form;
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
	
	@SystemAuth(url = url)
	public ActionForward getZcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XxsbjgForm model = (XxsbjgForm) form;
		JSONArray json = JSONArray.fromObject(service.getZcList(model.getXn(), model.getXq(),model.getSblx())); 
		response.getWriter().print(json);
		
		return null;	
		
	}
	
	@SystemAuth(url = url)
	public ActionForward getQzrq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbjgForm model = (XxsbjgForm) form;
		JSONArray json = JSONArray.fromObject(service.getQzrq(model.getXn(), model.getXq(), model.getSbzbid())); 
		response.getWriter().print(json);
		
		return null;	
	}
	
	
}

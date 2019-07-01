/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 上午10:08:16 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.cdgl.sh.CdshService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地信息维护Action 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 上午10:08:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdxxwhAction extends SuperAction {

	/**
	 * @属性： SPL_MKID 模块ID
	 */
	public static final String SPL_MKID = "rcsw";
	
	/**
	 *  @属性： PATH 路径
	 */
	private static final String PATH = "rcsw_cdgl_cdxxwhgl.do";
	
	private static final String url = "rcsw_cdgl_cdxxwhgl.do";
	
	/**
	 * @属性： service 场地信息维护服务
	 */
	private CdxxwhService service = new CdxxwhService();
	
	/**
	 * @属性： shlc 审核流操作接口
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @属性： shlcService 审核流操作接口
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * 
	 * @描述:页面dispatcher 场地信息维护查询页面
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
	public ActionForward cdxxwhCx(ActionMapping mapping, ActionForm form,
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
		return mapping.findForward("cdxxwhCx");
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
		CdxxwhForm model = (CdxxwhForm) form;
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
	 * @描述:popup 场地信息选择
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 上午10:37:26
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
	public ActionForward showCdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gotoPath = request.getParameter("goto");
		String search_sfkfsq = request.getParameter("search_sfkfsq");
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("path", PATH);
		request.setAttribute("search_sfkfsq", search_sfkfsq);
		return mapping.findForward("showCdxx");
	}
	
	/**
	 * 
	 * @描述:popup 场地信息选择 检索数据列表
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
	public ActionForward showCdxxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		//查询
		List<HashMap<String,String>> resultList = service.getPopupCdxx(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:页面dispatcher 场地信息维护新增页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午02:21:24
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
	public ActionForward cdxxwhXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取开关列表
		request.setAttribute("sfkfsqkgList", service.getKfkgList());
		//获取审批流程列表
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(SPL_MKID));
		//页面转发
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cdxxwhXz");
	}
	
	
	
	/**
	 * 
	 * @描述: 查看使用中的审批流程
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-10-23 下午01:47:43
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
	public ActionForward checkSplc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splcid=request.getParameter("splc");
		String cdid = request.getParameter("cdid");
		CdshService service = new CdshService();
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("message",service.isHaveSplcSj(splcid,cdid).toString());
		response.getWriter().print(JSONObject.fromBean(map));
		return null;
	}
	
	/**
	 * 
	 * @描述:页面dispatcher 场地信息维护修改页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午02:21:24
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
	public ActionForward cdxxwhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		String cdid = model.getCdid();
		//查询model，并复制数据值
		if(StringUtils.isNotBlank(cdid)){
			CdxxwhForm data = service.getModel(cdid);
			if(data != null){
				xgxt.utils.String.StringUtils.formatData(data);
				BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(data));
			}
		}
		HashMap<String,String> rs = service.getCdxxByCdid(cdid);
		request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(rs));
		//获取开关列表
		request.setAttribute("sfkfsqkgList", service.getKfkgList());
		//获取审批流程列表
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(SPL_MKID));
		//页面转发
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cdxxwhXg");
	}
	
	/**
	 * 
	 * @描述:场地信息维护新增 保存操作
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午03:32:01
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
	@SystemLog(description="访问日常事务-场地管理-场地信息维护-增加CDMC:{cdmc},LD:{ld},FJ:{fj},RNRS:{rnrs},LXR:{lxr},LXFS:{lxfs},DWKFSJKSSJ:{dwkfsjkssj},DWKFSJJSSJ:{dwkfsjjssj},SFKFSQ:{sfkfsq},SPLCID:{splcid}")
	public ActionForward cdxxwhXzAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdxxwhForm model = (CdxxwhForm) form;
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			String cdmc = model.getCdmc();
			int cds = service.getCdsByCdmc(cdmc);
			if(cds > 0){
				String messageKsy = MessageKey.RCSW_CDSQ_CHECK_ERROR2;
				JSONObject message = getJsonMessageByKey(messageKsy);
				message.put("flag", "repeat");
				response.getWriter().print(message);
				return null;
			} else {
				super.resetToken(request);
			}
		}
		
		//新增场地信息
		boolean isSuccess = service.runInsert(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:场地信息维护修改 保存操作
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午03:32:01
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
	@SystemLog(description="访问日常事务-场地管理-场地信息维护-修改CDID:{cdid},CDMC:{cdmc},LD:{ld},FJ:{fj},RNRS:{rnrs},LXR:{lxr},LXFS:{lxfs},DWKFSJKSSJ:{dwkfsjkssj},DWKFSJJSSJ:{dwkfsjjssj},SFKFSQ:{sfkfsq},SPLCID:{splcid}")
	public ActionForward cdxxwhXgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		model.setJbsbjs(HtmlUtil.xmlZy(model.getJbsbjs()));
		//更新场地信息
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:页面dispatcher 场地信息维护查看页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 下午02:21:24
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
	public ActionForward cdxxwhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		String cdid = model.getCdid();
		//查询获取场地信息
		if(StringUtils.isNotBlank(cdid)){
			request.setAttribute("cdxx", xgxt.utils.String.StringUtils.formatData(service.getCdxxByCdid(cdid)));
		}
		//页面转发
		return mapping.findForward("cdxxwhCk");
	}
	
	/**
	 * 
	 * @描述:删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-场地管理-场地信息维护-删除CDIDS:{cdids}")
	public ActionForward cdxxwhScAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cdxxids = request.getParameter("cdids"); //删除的ids
		int isSuccess = 0;
		List<String> cdidList = new ArrayList<String>();
		if(StringUtils.isNotBlank(cdxxids)){
			String[] cdidArr = cdxxids.split(",");
			for (String cdid : cdidArr) {
				if(!service.check(cdid))
					cdidList.add(cdid);
			}
			
			if(cdidList.size() >= 1){
				String[] exeCdid = new String[cdidList.size()];
				for (int i = 0; i < cdidList.size(); i++) {
					exeCdid[i] = cdidList.get(i);
				}
				isSuccess = service.runDelete(exeCdid);
			}
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
	 * @作者：张小彬[工号:1036]
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
		CdxxwhForm model = (CdxxwhForm) form;
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
	 * @描述:根据审核流程获取审批级别
	 * @作者：cq [工号：785]
	 * @日期：2014-10-21 下午02:34:04
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
	public ActionForward xmwhShfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid 为选择的审批流程代码值
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}
}

/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午02:27:22 
 */  
package xsgzgl.szdw.jtff.jtmdwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.szdw.jtff.jtff.JtffForm;
import xsgzgl.szdw.jtff.jtff.JtffService;
import xsgzgl.szdw.jtff.util.JtffUtilService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-8 下午02:27:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtmdwhAction extends SuperAction<JtmdwhForm, JtmdwhService> {
	private final String JTFF ="xjtff";
	private JtmdwhService service = new  JtmdwhService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "szdw_jtff_jtmdwh.do";
	
	@SystemAuth(url = url)
	public ActionForward getJtmdwhcx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    JtmdwhForm model = (JtmdwhForm) form;
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
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "szdw_jtff_jtmdwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	@SystemAuth(url = url)
	/**
	 * 增加正常津贴
	 */
	public ActionForward AddZcJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(model.getZgh())) {
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=AddZcJtff";
		request.setAttribute("path", path);
		request.setAttribute("jtlb", "zc");
		return mapping.findForward("addzcjtff");
	}
	
	@SystemAuth(url = url)
	/**
	 * 增加固定津贴
	 */
	public ActionForward AddGdJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(model.getZgh())) {
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=AddGdJtff";
		request.setAttribute("path", path);
		request.setAttribute("jtlb", "gd");
		return mapping.findForward("addgdjtff");
	}
	
	@SystemAuth(url = url)
	public ActionForward saveZcjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		if(model.getType().equals("save")){
			// 判断当前教师是否在表中有记录
			JtffUtilService service1 = new JtffUtilService();
			boolean isExist = service1.isExists(model.getZgh());
			if (isExist) {
				message = MessageUtil.getText(MessageKey.SZDW_JTFF_RYWH_REPEAT,model.getZgh());
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward saveGdjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		if(model.getType().equals("save")){
			// 判断当前教师是否在表中有记录
			JtffUtilService service1 = new JtffUtilService();
			boolean isExist = service1.isExists1(model.getZgh());
			if (isExist) {
				message = MessageUtil.getText(MessageKey.SZDW_JTFF_RYWH_REPEAT,model.getZgh());
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			model.setJtlb("gd");
			result = service.runUpdate(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 修改正常津贴
	 */
	public ActionForward UpdateZcJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		JtmdwhForm myForm = service.getModel(model);
		if (!StringUtil.isNull(model.getZgh())) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=UpdateZcJtff";
		request.setAttribute("path", path);
		return mapping.findForward("updatezcjtff");
	}
	
	@SystemAuth(url = url)
	/**
	 * 修改固定津贴
	 */
	public ActionForward UpdateGdJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		JtmdwhForm myForm = service.getModel(model);
		if (!StringUtil.isNull(model.getZgh())) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=UpdateGdJtff";
		request.setAttribute("path", path);
		return mapping.findForward("updategdjtff");
	}
	
	@SystemAuth(url = url)
	/**
	 * 删除正常津贴
	 */
	public ActionForward DelZcjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获得id
		String values = request.getParameter("values");
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
	 * 删除固定津贴
	 * @throws Exception 
	 */
	public ActionForward DelGdjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDeletegdjtmd(ids);
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
	 * @描述:正常津贴查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-10 下午02:28:14
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
	public ActionForward ZcjtCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JtmdwhForm myForm = (JtmdwhForm) form;
		JtmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> xsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ck", model);
		return mapping.findForward("zcjtck");
	}
	
	/**
	 * 
	 * @描述:固定津贴查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-10 下午02:28:22
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
    public ActionForward GdjtCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	JtmdwhForm myForm = (JtmdwhForm) form;
    	JtmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> xsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ck", model);
		return mapping.findForward("gdjtck");
	}
    
    @SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
    	JtmdwhForm model = (JtmdwhForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

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
}

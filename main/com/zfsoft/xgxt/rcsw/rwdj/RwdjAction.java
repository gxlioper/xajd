/**
 * @部门:学工产品事业部
 * @日期：2014-5-20 下午03:24:49 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

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
import xgxt.utils.String.StringUtils;
import xsgzgl.rwgl.rwtw.RwglRwtwService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 入伍登记管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-20 下午03:24:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RwdjAction extends SuperAction {
	private static final String RCSWRCXW = "rcswqjgl";
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rwdjbase.do";

	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjService service = new RwdjService();
		RwdjForm myForm = (RwdjForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rwdjbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "rwdjbase.do");
		FormModleCommon.commonRequestSet(request);
		// 默认当前学年学期
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("rwdjlist");
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
	@SystemLog(description="访问日常事务-入伍管理-入伍登记-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjService service = new RwdjService();
		String values = request.getParameter("values");
		if (!StringUtils.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
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
	@SystemLog(description="访问日常事务-入伍管理-入伍登记-修改RWDJID:{rwdjid},XH:{xh},RWTJ:{rwtj},RWSJ:{rwsj},XYQK:{xyqk},YWQRWXY:{ywqrwxy},BJGMS:{bjgms},HYZK:{hyzk},CYLB:{cylb},HJLB:{hjlb},BDLXFS:{bdlxfs},FYBD:{fybd}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjService service = new RwdjService();
		RwdjForm myForm = (RwdjForm) form;
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		RwdjForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		//学生基本信息显示配置
		String path = "rwdj.do?method=update";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//入伍途径
		RwglRwtwService rwglRwtwService = new RwglRwtwService();
		List<HashMap<String, String>> rwtjList = rwglRwtwService.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		return mapping.findForward("rwdjupdate");
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
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-入伍管理-入伍登记-增加XH:{xh},RWTJ:{rwtj},RWSJ:{rwsj},XYQK:{xyqk},YWQRWXY:{ywqrwxy},BJGMS:{bjgms},HYZK:{hyzk},CYLB:{cylb},HJLB:{hjlb},BDLXFS:{bdlxfs},FYBD:{fybd}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjService service = new RwdjService();
		RwdjForm myForm = (RwdjForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			if(!service.checkIsNotExist(myForm.getXh())){
				String  message = "该学生已有入伍登记记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			super.resetToken(request);
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生基本信息显示配置
		String path = "rwdj.do?method=add";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//入伍途径
		RwglRwtwService rwglRwtwService = new RwglRwtwService();
		List<HashMap<String, String>> rwtjList = rwglRwtwService.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		this.saveToken(request);
		return mapping.findForward("rwdjadd");
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
		RwdjService service = new RwdjService();
		RwdjForm myForm = (RwdjForm) form;
		myForm = service.getModel(myForm);
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		String path = "rwdj.do?method=showView";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(myForm));
		return mapping.findForward("rwdjview");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjForm model = (RwdjForm) form;
		//根据不同的审核类型 去自定义导出
		RwdjService service = new RwdjService();
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

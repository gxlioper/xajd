/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.twgl.tgb;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.twgl.tzz.TzzModel;
import com.zfsoft.xgxt.twgl.tzz.TzzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	： TgbAction
 * @description	： TODO(描述这个类的作用)
 * @author 		：lj（1282）
 * @date		： 2018-5-15 下午03:37:13
 * @version 	V1.0 
 */

public class TgbAction extends SuperAction<TgbModel, TgbService>{
	private TgbService service = new TgbService();
	private static final String url = "tygl_tgbgl_tgbjgList.do";
	
	private static final String TGB = "tgb";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(TGB);
	}
	
	/**
	 * @description	：  查询列表
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 下午03:40:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward tgbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
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
//		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
//		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "tygl_tgbgl_tgbjgList.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tgbList");
	}
	
	/**
	 * @description	： 增加
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 下午04:13:04
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTgbJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if(!StringUtil.isNull(model.getRzzz())){
			TzzService tzzService = new TzzService();
			TzzModel model2 = tzzService.getModel(model.getRzzz());
			request.setAttribute("rzzzmc", model2.getZzmc());
		}
		// 学年 学期list
		List<HashMap<String, String>> dmList = service.getDmList();
		request.setAttribute("dmList", dmList);
		request.setAttribute("currxn", Base.currXn);
		model.setXn(Base.currXn);
		String path = "tgbgl.do?method=addTgbJg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("addTgb");
	}
	
	/**
	 * @description	： 修改团干部
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 上午11:16:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateTgb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
		String rzzz = model.getRzzz();
		TgbModel model3 = service.getModel(model);
		BeanUtils.copyProperties(model, (StringUtils.formatData(model3)));
		if(!StringUtil.isNull(rzzz)){
			model.setRzzz(rzzz);
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if(!StringUtil.isNull(model.getRzzz())){
			TzzService tzzService = new TzzService();
			TzzModel model2 = tzzService.getModel(model.getRzzz());
			request.setAttribute("rzzzmc", model2.getZzmc());
		}
		// 学年 学期list
		List<HashMap<String, String>> dmList = service.getDmList();
		request.setAttribute("dmList", dmList);
		request.setAttribute("currxn", Base.currXn);
		model.setXn(Base.currXn);
		String path = "tgbgl.do?method=updateTgb";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateTgb");
	}
	
	/**
	 * @description	： 团组织选择
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 下午07:30:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTzz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
		request.setAttribute("xh", model.getXh());
		if(!StringUtil.isNull(model.getJgid())){
			request.setAttribute("gotoPath", request.getParameter("gotopath")+"&xh="+model.getXh()+"&jgid="+model.getJgid());
		}else{
			request.setAttribute("gotoPath", request.getParameter("gotopath")+"&xh="+model.getXh());
		}
		String path = "tygl_tzzgl_tzzList.do";
		request.setAttribute("path", path);
		return mapping.findForward("showTzz");
	}
	
	/**
	 * @description	： 团干部增加保存
	 * @author 		： lj（1282）
	 * @date 		：2018-5-15 下午05:35:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTgbForAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	：修改保存
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 上午11:29:03
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTgbForUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 查看团干部
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 上午11:30:00
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewTgb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TgbModel model = (TgbModel) form;
		TgbModel viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		HashMap<String, String> info = service.getInfo(model);
		request.setAttribute("rs", info);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewTgb");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 下午05:21:40
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TgbModel model = (TgbModel) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
	 * @description	： 删除团干部
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 下午05:22:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delTgb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}

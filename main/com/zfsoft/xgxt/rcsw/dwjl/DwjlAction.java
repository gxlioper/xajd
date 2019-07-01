/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:03:35 
 */  
package com.zfsoft.xgxt.rcsw.dwjl;

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
import xsgzgl.rcsw.wsbz.yyrq.YyrqForm;
import xsgzgl.rcsw.wsbz.yyrq.YyrqService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl.JbfglForm;
import com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl.JbfglService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.rwfbybc.sqsh.RwfbysqshModel;

/**
 * @className	： DwjlAction
 * @description	： 对外交流模块
 * @author 		：CP（1352）
 * @date		： 2017-11-20 上午09:00:46
 * @version 	V1.0
 */

public class DwjlAction extends SuperAction {
	
	//定义日常事务中医疗保险常量可以从基本信息表中获取学生信息
	private static final String RCSWYLBX = "rcswylbx";
	private static List<HashMap<String, String>> jbxxList = null;
	
	DwjlService service = new DwjlService();
	
	private static final String url = "xg_dwjl_dwjljg.do";

	/**
	 * @description	：查询
	 * @author 		： CP（1352）
	 * @date 		：2017-11-20 上午09:03:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@SystemAuth(url = url)
	public ActionForward dwjlcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwjlForm model = (DwjlForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办结果数据
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dwjlcx");
	}
	
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2017-11-20 上午09:48:02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward addDwjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			DwjlForm model = (DwjlForm) form;
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
			// 学生基本信息显示配置
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWYLBX);	
			request.setAttribute("jbxxList", jbxxList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
	    	}
			return null;
		}
		String path = "xg_dwjl_dwjl.do?method=addDwjl";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}

/**
 * 
 * @description	： 修改
 * @author 		： CP（1352）
 * @date 		：2017-11-20 下午01:49:05
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	@SystemAuth(url = url)
	public ActionForward updateDwjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwjlForm model = (DwjlForm) form;
		DwjlForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("id", myForm.getId());
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_TOKEN_VALID, false));
			}

			return null;
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("xg");
	}
/**
 * @description	： 删除
 * @author 		： CP（1352）
 * @date 		：2017-11-20 下午02:43:23
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	public ActionForward dwjlDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
 * @author 		： CP（1352）
 * @date 		：2017-11-20 下午03:38:40
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
		
	@SystemAuth(url = url)
	public ActionForward viewDwjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DwjlForm myForm = (DwjlForm) form;
		DwjlForm model = service.getModel(myForm);
		
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("view");
	}
	
	
/**
 * @description	： 导出
 * @author 		： CP（1352）
 * @date 		：2017-11-20 下午04:10:15
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
		DwjlForm model=(DwjlForm) form;
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

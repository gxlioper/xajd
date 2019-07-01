/**
 * @部门:学工产品事业部
 * @日期：2016-3-17 上午11:00:08 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import org.apache.struts.action.ActionForward;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生处学情月报
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：孟威[工号:1186]
 * @时间： 2016-3-17 上午11:00:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class XscxqybAction  extends SuperAction{
	XscxqybService service = new XscxqybService();
	private static final String url = "rcsw_xscxqyb.do";
/**
 * @描述:TODO(学生处学情月报查询页面)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:17:09
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
	@SystemLog(description="访问日常事务——学生处学情月报——List")
	public ActionForward XscxqybList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm model = (XscxqybForm) form;
		User user = getUser(request);
		if(QUERY.equals(model.getType())){
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_xscxqyb.do";
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("XscxqybList");
	}
/**
 * @描述:增加
 * @作者：孟威[工号：1186]
 * @日期：2016-3-17 下午06:37:16
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
	public ActionForward XscxqybAdd (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XscxqybForm model = (XscxqybForm) form;
		XscxqybService service = new XscxqybService();
		User user = getUser(request);
		//获取当前学年学期
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getCurrentXqmc(model));
		//获取填写人
		model.setTxr(user.getUserName());
		request.setAttribute("txr", user.getRealName());	
		if (SAVE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断
			boolean isExist = service.isExistYf(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.RCSW_XSCXQYB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSCXQYB_REPEAT, false));
			}
			return null;
		}
		String path = "rcsw_xscxqyb.do?method=XscxqybAdd";
		request.setAttribute("path", path);
		return mapping.findForward("XscxqybAdd");
	}
/**
 * @描述:删除
 * @作者：孟威[工号：1186]
 * @日期：2016-3-22 上午10:43:41
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
	public ActionForward XscxqybDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		//获得id
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
 * @描述:修改
 * @作者：孟威[工号：1186]
 * @日期：2016-3-22 下午02:49:36
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
	public ActionForward XscxqybUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm model=(XscxqybForm) form;
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.isExistYf(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSCXQYB_REPEAT, false));
			}	
			return null;
		}
		User user = getUser(request);
		//获取当前学年学期
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getCurrentXqmc(model));
		//获取填写人
		model.setTxr(user.getUserName());
		request.setAttribute("txr", user.getRealName());
		XscxqybForm myForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("XscxqybUpdate");
	}	
/**
 * @描述:查看
 * @作者：孟威[工号：1186]
 * @日期：2016-3-23 下午02:12:04
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
	public ActionForward XscxqybView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm myForm = (XscxqybForm) form;
		if(!StringUtil.isNull(myForm.getJgid())){
			HashMap<String ,String> xxck = service.getXxck(myForm.getJgid());
			request.setAttribute("xxck", xxck);
		}
		String path = "rcsw_xscxqyb.do?method=XscxqybView";
		request.setAttribute("path", path);
		return mapping.findForward("XscxqybView");
	}
/**
 * @描述:导出
 * @作者：孟威[工号：1186]
 * @日期：2016-3-24 下午02:03:02
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm model=(XscxqybForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXscxqybdcList(model,user);//查询出所有记录，不分页
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

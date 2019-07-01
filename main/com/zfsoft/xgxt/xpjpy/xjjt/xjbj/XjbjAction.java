/**
 * @部门:学工产品事业部
 * @日期：2018-1-15 上午09:39:37 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.xjbj;

import java.io.File;
import java.text.SimpleDateFormat;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-1-15 上午09:39:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XjbjAction extends SuperAction {
	private XjbjService service = new XjbjService();
	private static final String url = "xpjpy_yxjt_xjbj.do";
	/**
	 * @描述: 先进班级列表
	 * @作者： Lin.gx[工号:1553]
	 * @日期：
	 * @修改记录: 
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
	public ActionForward getDataList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjForm model = (XjbjForm) form;
		if(QUERY.equals(model.getType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			List<HashMap<String, String>> resultlist=service.getPageList(model, user);
			JSONArray data = JSONArray.fromObject(resultlist);
			response.getWriter().print(data);
			return null;
		}
		CsszService csszService = new CsszService();
		CsszForm csszModel=csszService.getCsszModel();
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		String path = "xpjpy_yxjt_xjbj.do";
		request.setAttribute("path", path);
		return mapping.findForward("listXjbj");
	}
	
	/**
	 * @描述: 增加页面
	 * @作者： Lin.guoxia[工号:1553]
	 * @日期： 2018-01-15
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
	public ActionForward addXjbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjForm model = (XjbjForm) form;
		model.setLrsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		CsszService csszService = new CsszService();
		CsszForm csszModel=csszService.getCsszModel();
		// 学年list
		model.setXn(csszModel.getXn());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("type", "add");
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("addXjbj");
	}
	/**
	 * @描述: 修改页面
	 * @作者： Lin.guoxia[工号:1553]
	 * @日期： 2018-01-15
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
	public ActionForward updateXjbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjForm model = (XjbjForm) form;
		XjbjForm xjbjForm = service.getModel(model.getGuid());
		xjbjForm.setBjmc(service.getBjmc(xjbjForm.getBjdm()));
		request.setAttribute("type", "update");
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xjbjForm", xjbjForm);
		BeanUtils.copyProperties(model, StringUtils.formatData(xjbjForm));
		return mapping.findForward("addXjbj");
	}
	
	/**
	 * @描述: 保存
	 * @作者：  linguoxia[工号:1553]
	 * @日期：2018-01-16上午11:27:26
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
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjForm model = (XjbjForm) form;
		String bjdm = service.getBjdm(model.getBjmc());
		model.setBjdm(bjdm);
		if("add".equals(model.getType())){
			boolean isExist= service.isExistByXjbj(model,"add");
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
		}else if("update".equals(model.getType())){
			boolean isExist = service.isExistByXjbj(model,"update");
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
		}
			
		return null;
	}
	
	
	/**
	 * 
	 * @描述:删除
	 * @作者：lgx [工号：1553]
	 * @日期：
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
	public ActionForward delXjbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * @描述:导出
	 * @日期：2016-8-25 下午06:51:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjForm model = (XjbjForm) form;
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

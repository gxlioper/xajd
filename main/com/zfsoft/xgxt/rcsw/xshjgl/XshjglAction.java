/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午04:59:43 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;

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
import xgxt.utils.String.StringUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2015-9-14 上午09:30:39 
 * @版本： V5.17
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XshjglAction extends SuperAction{
	
	XshjglService service = new XshjglService();
	
	private static final String url = "xshjgl_list.do";
	
	/**
	 * @描述:list页面
	 * @作者：孟威[工号：1186]
	 * @日期：2015-9-14 上午09:31:04
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
	@SystemLog(description="访问日常事务-学生户籍管理——list")
	public ActionForward Xshjgllist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm model=(XshjglForm) form;
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("realTable", "xshjgl_list");
		String path = "xshjgl_list.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("Xshjgllist");
	}
	
/**
 * @描述: 增加
 * @作者：孟威[工号：1186]
 * @日期：2015-9-14 上午09:31:33
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
	@SystemLog(description="访问日常事务-学生户籍管理-增加XH:{xh},QYZT:{qyzt},QYSJ:{qysj}")
	public ActionForward Xshjgladd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglForm myForm = (XshjglForm) form;
		XshjglService service=new XshjglService();
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

			// 唯一性判断
			boolean isExist = service.isExistQysj(myForm);
			if (!isExist) {
				super.resetToken(request);
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		String path = "rcsw_xshjgl.do?method=Xshjgladd";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("Xshjgladd");
	}  

/**
 * @描述: 删除
 * @作者：孟威[工号：1186]
 * @日期：2015-9-14 上午09:31:52
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
	@SystemLog(description="访问日常事务-学生户籍管理-删除VALUES:{values}")
	public ActionForward Xshjgldel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service = new XshjglService();
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
 * @描述:对学生信息进行【修改】
 * @作者：孟威[工号：1186]
 * @日期：2015-10-13 上午10:16:32
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
	@SystemLog(description="访问日常事务-学生户籍管理-修改HJGLID:{hjglid},XH:{xh},QYZT:{qyzt},QYSJ:{qysj}")
	public ActionForward Xshjgledit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm model=(XshjglForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.isExistQysj(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_STUDENT, false));
			}
			
			return null;
		}
		XshjglForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		//BeanUtils.copyProperties(model, myForm);   做换行处理，不会出现换行出现<br/>状态
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("qyzt", model.getQyzt());
		return mapping.findForward("Xshjgledit");
	}
	
/**
 * @描述: 查看
 * @作者：孟威[工号：1186]
 * @日期：2015-9-14 上午09:32:33
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
	@SystemLog(description="访问日常事务-学生户籍管理——查看")
	public ActionForward Xshjglview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm model=(XshjglForm) form;
		
		XshjglForm myForm = service.getModel(model);
		myForm.setQyztmc(myForm.getQyzt().equals("0")?"迁入":"迁出");
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));//做换行处理，不会出现XXX<br/>XXX
		return mapping.findForward("Xshjglview");
		}
	/**
	 * @描述: 导出
	 * @作者：孟威[工号：1186]
	 * @日期：2015-9-14 上午09:32:51
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
	@SystemLog(description="访问日常事务-学生户籍管理——导出")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm myForm = (XshjglForm) form;
		XshjglForm model=(XshjglForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXshjgldcList(model,user);//查询出所有记录，不分页
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

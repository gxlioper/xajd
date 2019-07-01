/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.qgzx.jtff;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：勤工助学-津贴发放 管理模块
 * @类功能描述：TODO(这里用一句话描述这个类的作用) 
 * @作者：卓耐[工号:1391]
 * @时间：2017年4月21日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JtffAction extends SuperAction<JtffForm,JtffService> {
	private JtffService service = new  JtffService();
	private static final String url = "qgzx_jtff_list.do";
	
	/**
	 * @描述：津贴发放页面
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward jtffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtffList");
	}
	
	/**
	 * @描述：津贴发放 列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward getJtffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtffForm model = (JtffForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述：津贴发放 增加
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward jtffAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtffForm myForm = (JtffForm) form;
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", service.getBmList());
		//学生信息选择后重新加载
		request.setAttribute("path", "qgzx_jtff.do?method=jtffAdd");
		return mapping.findForward("jtffAdd");
	}
	
	/**
	 * @描述：津贴发放 编辑
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	//@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	//@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-编辑PXID:{pxid}")
	public ActionForward jtffEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtffForm myForm = (JtffForm) form;
		JtffForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", service.getBmList());
		return mapping.findForward("jtffEdit");
	}
	
	/**
	 * @描述：新增/编辑 保存
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward JtffSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtffForm myForm = (JtffForm) form;
		// 唯一性判断
		if (!service.checkExist(myForm)) {
			boolean result =StringUtils.isNull(myForm.getId())?service.runInsert(myForm):service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_AUD_DOUBLE, false));
		}
		return null;
	}
	
	/**
	 * @描述：津贴发放 导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtffForm model = (JtffForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model);// 查询出所有记录，不分页
		 
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述：删除
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	//@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	//@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-删除:PXID{values}")
	public ActionForward jtffDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
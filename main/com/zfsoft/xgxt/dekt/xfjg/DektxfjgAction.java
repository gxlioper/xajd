/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.dekt.xfjg;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dekt.qnzyry.QnzyryForm;
import com.zfsoft.xgxt.dekt.qnzyry.QnzyryService;
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
public class DektxfjgAction extends SuperAction<DektxfjgForm,DektxfjgService> {
	private DektxfjgService service = new  DektxfjgService();
	private static final String url = "dekt_xfjg_list.do";
	
	/**
	 * @描述：list页面跳转
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward xfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		User user = getUser(request);
		//学生用户登录计算总学分
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			String fs = service.getTotalXf(user.getUserName());
			request.setAttribute("xf", fs);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfjgList");
	}
	
	/**
	 * @描述：list数据
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward getXfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	public ActionForward xfjgView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		XsxxService xsxxService = new XsxxService();
		//学生信息
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//业务信息
		request.setAttribute("model", service.getView(model));
		return mapping.findForward("xfjgView");
	}
	
	/**
	 * @描述：增加页面跳转 //TODO 暂时不用
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward xfjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm myForm = (DektxfjgForm) form;
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//学生信息选择后重新加载
		request.setAttribute("path", "qgzx_jtff.do?method=jtffAdd");
		return mapping.findForward("xfjgAdd");
	}
	
	/**
	 * @描述：修改页面跳转 //TODO 暂时不用
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward xfjgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm myForm = (DektxfjgForm) form;
		DektxfjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xfjgEdit");
	}
	
	/**
	 * @描述：保存 //TODO 暂时不用
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward xfjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm myForm = (DektxfjgForm) form;
		// 唯一性判断
		if (!service.checkExist(myForm)) {
			boolean result =StringUtils.isNull(myForm.getSqid())?service.runInsert(myForm):service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_AUD_DOUBLE, false));
		}
		return null;
	}
	
	/**
	 * @描述：删除 //TODO 暂时不用
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward xfjgDel(ActionMapping mapping, ActionForm form,
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
	
	/**
	 * @描述：导出
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页
		 
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
	 * @description	： 评分
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-18 上午10:27:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		DektxfjgService service = new DektxfjgService();
		DektxfjgForm copyModel = service.getModel(model);
		if(null != copyModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(copyModel));
		}
		return mapping.findForward("pfzhhd");
	} 
	
	/**
	 * @description	： 保存评分
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-18 上午10:30:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		boolean result = service.runUpdate(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	
}
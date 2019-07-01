/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.xstgl.stglzjsr;

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
import com.zfsoft.xgxt.qgzx.jtff.JtffService;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：社团管理-社团管理(浙江树人)-社团管理 
 * @类功能描述：
 * @作者：卓耐[工号:1391]
 * @时间：2017年4月24日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class StglAction extends SuperAction<StglForm,StglService> {
	private StglService service = new  StglService();
	private StlbglService stlbService = new StlbglService();
	private JtffService jtffService=new JtffService();
	private static final String url = "stgl_zjsr_stgl.do";
	
	/**
	 * @描述：社团管理页面跳转
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward stglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stglList");
	}
	
	/**
	 * @描述：社团管理 列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward getStglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm model = (StglForm) form;
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
	 * @描述：增加
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward stglAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("stlbList", stlbService.getStlbList());
		request.setAttribute("bmList", jtffService.getBmList());
		
		return mapping.findForward("stglAdd");
	}
	
	/**
	 * @描述：编辑
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	//@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	//@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-编辑PXID:{pxid}")
	public ActionForward stglEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		HashMap<String,String>stglMap = service.getStgl(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(stglMap));
		request.setAttribute("szxm", stglMap.get("szxm"));
		request.setAttribute("cwfzrxm", stglMap.get("cwfzrxm"));
		
		request.setAttribute("stlbList", stlbService.getStlbList());
		request.setAttribute("bmList", jtffService.getBmList());
		
		return mapping.findForward("stglEdit");
	}
	
	/**
	 * @描述：新增/编辑 保存
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward stglSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
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
	
	public ActionForward stglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		HashMap<String,String>stglMap = service.getStgl(myForm);
		request.setAttribute("stglMap", stglMap);
		
		return mapping.findForward("stglView");
	}
	
	/**
	 * @描述：导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm model = (StglForm) form;
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
	 * @日期：2017年4月24日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	//@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	//@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-删除:PXID{values}")
	public ActionForward stglDel(ActionMapping mapping, ActionForm form,
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
	
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "stglStsq.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("type", request.getParameter("type"));
		return mapping.findForward("getStu");
	}
	
	public ActionForward getStuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	//社团申请增加获取老师列表
	
	public ActionForward getTea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "stglStsq.do?method=getTea";
		request.setAttribute("path", path);
		return mapping.findForward("getTea");
	}
	
	public ActionForward getTeaList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StglForm myForm = (StglForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getTeaxxList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	
}
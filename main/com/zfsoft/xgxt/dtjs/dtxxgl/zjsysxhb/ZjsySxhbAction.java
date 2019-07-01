/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午02:04:16 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-6-25 下午02:04:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

@SuppressWarnings("unchecked")
public class ZjsySxhbAction extends SuperAction {
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String SXHB = "sxhb";
	BdpzService bdpzService = new BdpzService();
	
	private static final String url = "zjsy_sxhbList.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsySxhbService service = new ZjsySxhbService();
		ZjsySxhbForm myForm = (ZjsySxhbForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zjsy_sxhbList.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sxhbjg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addSxhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsySxhbForm model = (ZjsySxhbForm) form;
		ZjsySxhbService service = new ZjsySxhbService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(SXHB);
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// 如学号不为空，加载学生基本信息
			HashMap<String, String> xsjbxx = service.getXsdtxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if (SAVE.equalsIgnoreCase(model.getType())) {
				model.setSxhbid(UniqID.getInstance().getUniqIDHash());
				model.setJddm(xsjbxx.get("jddm"));
				boolean flag = service.isHasExists(model);
				if(!flag&&!StringUtil.isNull(model.getJddm())){
					boolean result = service.runInsert(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
				}else{
					response.getWriter().print(
							getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
				}
			    return null;
		   }
		}
		String path = "zjsy_sxhb.do?method=addSxhb";
		request.setAttribute("path", path);
		return mapping.findForward("addSxhb");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward modSxhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjsySxhbForm model = (ZjsySxhbForm) form;
		ZjsySxhbService service = new ZjsySxhbService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			HashMap<String, String> xsjbxx =service.getXsdtxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
				boolean result = service.runUpdate(model);
				if(result){
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
				}
			    return null;
		   }
		ZjsySxhbForm myForm = service.getModel(model.getSxhbid());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(SXHB);
		request.setAttribute("jbxxList", jbxxList);
		String path = "zjsy_sxhb.do?method=updateSxhb";
		request.setAttribute("path", path);
		return mapping.findForward("modSxhb");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delSxhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZjsySxhbService service = new ZjsySxhbService();
		// 获得id
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
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjsySxhbForm model = (ZjsySxhbForm) form;
		ZjsySxhbService service = new ZjsySxhbService();

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

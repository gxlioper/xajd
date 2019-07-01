/**
 * @部门:学工产品事业部
 * @日期：2017-10-30 下午03:50:02 
 */  
package xgxt.dtjs.tsdzb;

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
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 特色党支部aciton(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-10-30 下午03:50:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsdzbAction extends SuperAction<TsdzbForm, TsdzbService>{
	private static final String url = "dtjs_tsdzbgl_tsdzb.do";
	
	private static final String STU = "stu";
	
	private static final String TEA = "tea";
	
	
	private TsdzbService service = new TsdzbService();
	
	/** 
	 * @描述:获取特色团支部列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 上午08:46:29
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
	public ActionForward getTsdzbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
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
		String path = "dtjs_tsdzbgl_tsdzb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsdzbList");
	}
	
	/** 
	 * @描述:增加特色党支部(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午01:46:55
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
	public ActionForward addTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		String cjsj = GetTime.getTimeByFormat("yyyy-mm-dd");
		String userName = user.getUserName();
		String realName = user.getRealName();
		model.setCjrzgh(userName);
		model.setCjsj(cjsj);
		model.setCjrxm(realName);
		if("stu".equalsIgnoreCase(user.getUserStatus())){
			model.setCjrlx(STU);
		}else{			
			model.setCjrlx(TEA);
		}
		if("xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus())){
			request.setAttribute("njList", service.getNjList());
			request.setAttribute("xyList", service.getXyList(user.getUserStatus(),user.getUserName()));
		}
		List<HashMap<String, String>> bjList = service.getBjList(user,null);
		request.setAttribute("bjList", bjList);
		return mapping.findForward("addTsdzb");
	}
	
	
	/** 
	 * @描述:保存(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午03:34:41
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
	@SystemLog(description="访问党团建设-特色党支部管理-特色党支部维护-增加DZBMC:{dzbmc},FZR:{fzr},CJSJ:{cjsj}")
	public ActionForward saveJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		boolean result;
		if("save".equalsIgnoreCase(model.getType())){
			if(service.isExist(model.getDzbmc(), model.getDzbid())){				
				result = service.addTsdzb(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));			
			}else{
				String messageKey = MessageKey.XG_DTJS_TSDZB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
		}else{
			if(service.isExist(model.getDzbmc(), model.getDzbid())){
				result = service.updateTsdzb(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				String messageKey = MessageKey.XG_DTJS_TSDZB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}			
		}
		return null;
	}
	
	/** 
	 * @描述:修改党支部(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午03:44:57
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
	public ActionForward editTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		TsdzbForm editModel = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(editModel));
		List<String> list = service.getBjListBydzbId(model.getDzbid());
		if(null != list && list.size() > 0){
			model.setBjdms(list.toArray(new String[]{}));
		}
		if("xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus())){
			request.setAttribute("njList", service.getNjList());
			request.setAttribute("xyList", service.getXyList(user.getUserStatus(),user.getUserName()));
		}
		List<HashMap<String, String>> bjList = service.getBjList(user,model.getDzbid());
		request.setAttribute("bjList", bjList);
		return mapping.findForward("editTsdzb");
	}
	
	/** 
	 * @描述:删除党支部(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午07:18:05
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
	public ActionForward delTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			service.delGlByIds(ids);
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
	
	/** 
	 * @描述:查看(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午07:48:52
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
	public ActionForward viewTsdzb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		TsdzbForm editModel = service.getModel(model);
		BeanUtils.copyProperties(model, editModel);
		return mapping.findForward("viewTsdzb");
	}
	
	/**
	 * @description	：导出数据
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-2 上午09:11:37
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
		TsdzbForm model = (TsdzbForm)form;
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
	 * @description	： 获取专业列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午11:49:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		List<HashMap<String,String>> zyList=service.getZyList(model.getXydm(), user.getUserStatus(),user.getUserName());
		JSONArray jsonArr = JSONArray.fromArray(zyList.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;	
	}
	
	/**
	 * @description	： 获取班级列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 下午01:42:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		User user = getUser(request);
		List<HashMap<String,String>> bjList=service.getbjList(model.getDzbid(),model.getXydm(), model.getZydm(), model.getNjdm(), user.getUserStatus(), user.getUserName());
		JSONArray jsonArr = JSONArray.fromArray(bjList.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;	
	}
}

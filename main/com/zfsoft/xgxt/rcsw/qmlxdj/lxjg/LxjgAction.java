/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午09:07:10 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxjg;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjForm;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午09:07:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxjgAction extends SuperAction<LxjgForm, LxjgService> {
	LxjgService service = new LxjgService();
	private final String QMLXDJ="qmlxdj";
	/**
	 * @throws Exception 
	 * 
	 * @描述: 留校结果查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午09:10:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getLxdjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxjgForm lxjgform = (LxjgForm)form;
		User user = getUser(request);
		if(QUERY.equals(lxjgform.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			lxjgform.setSearchModel(searchModel);

			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(lxjgform, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[]{ Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_qmlxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-12 上午09:16:53
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxjgForm lxjgform = (LxjgForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			lxjgform.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(lxjgform.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(lxjgform.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "qmlxjg.do?method=add";
		request.setAttribute("path", path);
		/**
		 * 申请默认当前学年，学期，且写死不可改
		 */
//		request.setAttribute("xn", Base.currXn);
//		request.setAttribute("xq", Base.currXq);
//		request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
		request.setAttribute("xnList",Base.getXnndList());
		lxjgform.setXn(Base.currXn);
		request.setAttribute("xqList", Base.getXqList());
		lxjgform.setXq(Base.currXq);
		LxdjService sqService = new LxdjService();
		request.setAttribute("dmList", sqService.getDmList());
		request.setAttribute("lxlxList", sqService.getLxlxList());
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:26:00
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
	public ActionForward delJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:29:37
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
	public ActionForward editJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxjgForm myForm = (LxjgForm)form;
		LxjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "qmlxjg.do?method=editJg";
		request.setAttribute("path", path);
		request.setAttribute("xn", model.getXn());
		request.setAttribute("xq", model.getXq());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
		LxdjService sqService = new LxdjService();
		request.setAttribute("dmList", sqService.getDmList());
		request.setAttribute("lxlxList", sqService.getLxlxList());
		return mapping.findForward("edit");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:38:52
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
	public ActionForward ckSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxjgForm myForm = (LxjgForm)form;
		if(null!=myForm){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", service.getCkxx(myForm.getJgid()));
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述: 导出申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:01:20
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
		LxjgForm myForm = (LxjgForm)form;
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// 查询出所有记录，不分页
		
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:50:52
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
	public ActionForward saveJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxjgForm myForm = (LxjgForm)form;
//		String type = myForm.getType();
		boolean rs = true;
		User user = getUser(request);
		/**
		 * 如果是增加，需要判断重复性
		 */
		if(StringUtils.isNull(myForm.getJgid())){
			LxdjService sqService = new LxdjService();
			rs = sqService.checkNotExist(myForm.getXh(), myForm.getXn(), myForm.getXq(), "jg");
			if(!rs){
				String message = "本学期已有填写记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		rs = service.saveJg(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}

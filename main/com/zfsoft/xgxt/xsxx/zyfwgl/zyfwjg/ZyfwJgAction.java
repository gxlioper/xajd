/**
 * @部门:学工产品事业部
 * @日期：2017年5月10日 上午8:40:46 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务结果Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月10日 上午8:40:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwJgAction extends SuperAction<ZyfwJgForm,ZyfwJgService>{
	
	private final String ZYFW="zyfw";
	private ZyfwJgService zyfwJgService = new ZyfwJgService();
	private static final String url = "xsxx_zyfwgl_jg.do?method=zyfwJgList";
	
	/**
	 * @描述:跳转到志愿服务结果列表页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月11日 下午5:34:34
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
	public ActionForward zyfwJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwJgList");
	}
	
	/**
	 * @描述:获取志愿服务结果列表JSON数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月11日 下午5:34:34
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
	public ActionForward getZyfwJgListData(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwJgForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = zyfwJgService.getPageList(zyfwJgForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述:志愿服务结果的查看
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 下午4:43:20
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
	public ActionForward zyfwJgShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		ZyfwJgForm model = zyfwJgService.getModel(zyfwJgForm.getFwid());
		if(StringUtils.isNotNull(model.getXh())){
			BeanUtils.copyProperties(zyfwJgForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zyfwJgShow");
	}
	
	/**
	 * @描述:根据学号查询该生志愿服务结果信息列表，返回JSON数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月12日 下午2:53:15
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
	public ActionForward getZyfwJgListDataByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		//该生历史服务信息列表
		List<HashMap<String,String>> zyfwJgList = zyfwJgService.getZyfwJgListByXh(zyfwJgForm.getXh());
		JSONArray dataList = JSONArray.fromObject(zyfwJgList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述:转到志愿服务结果填写弹框页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午2:04:19
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
	public ActionForward zyfwJgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			zyfwJgForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zyfwJgForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zyfwJgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		zyfwJgForm.setXn(Base.currXn);
		zyfwJgForm.setXq(Base.currXq);
		String path = "xsxx_zyfwgl_jg.do?method=zyfwJgAdd";
		request.setAttribute("path", path);
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);

		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zyfwJgAdd");
	}
	
	/**
	 * @描述:志愿服务结果（新增）的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午3:22:06
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务结果-增加XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwJgSaveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		 //判断当前时间是否有申请记录
		boolean isRepeat = zyfwJgService.isRepeat(zyfwJgForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwJgForm.getFwkssj(),zyfwJgForm.getFwjssj()});
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwJgService.runInsert(zyfwJgForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:转到志愿服务结果修改弹框页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午2:04:19
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
	public ActionForward zyfwJgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		ZyfwJgForm model = zyfwJgService.getModel(zyfwJgForm);
		if (StringUtils.isNotNull(model.getXh())) {
			BeanUtils.copyProperties(zyfwJgForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		String path = "xsxx_zyfwgl_jg.do?method=zyfwJgEdit";
		request.setAttribute("path", path);
		return mapping.findForward("zyfwJgEdit");
	}
	
	/**
	 * @描述:志愿服务结果（修改）的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午3:22:06
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务结果-修改FWID:{fwid},XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwJgSaveForEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		//判断当前时间是否有申请记录
		boolean isRepeat = zyfwJgService.isRepeat(zyfwJgForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwJgForm.getFwkssj(),zyfwJgForm.getFwkssj()});;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwJgService.runUpdate(zyfwJgForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:志愿服务结果的删除（可批量）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午9:50:05
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务结果-删除VALUES:{values}")
	public ActionForward zyfwJgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = zyfwJgService.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述:志愿服务结果的导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 下午2:22:44
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ZyfwJgForm zyfwJgForm = (ZyfwJgForm) form;
		String dcclbh = request.getParameter("dcclbh");
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwJgForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = zyfwJgService.getAllList(zyfwJgForm,user);// 查询出所有记录，不分页
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = zyfwJgForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(dcclbh);// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

}

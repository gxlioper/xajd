/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 下午05:13:50 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.jg;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmjxForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;
import com.zfsoft.xgxt.zxdk.ypzl.comm.YpzlUtil;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-24 下午05:13:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzljgAction extends SuperAction<YpzljgForm, YpzljgService>{
	private static final String url = "zxdk_ypzl_ypzljg.do";
	
	private YpzljgService service = new YpzljgService();
	private YpzlSqService sqService = new YpzlSqService();
	
	private static final String YPZLSQ = "ypzlsq";
	private static List<HashMap<String, String>> jbxxList = null;
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(YPZLSQ);
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-24 下午05:17:42
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
	public ActionForward getYpzljgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzljgForm model = (YpzljgForm) form;
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		String path = "zxdk_ypzl_ypzljg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gnmkmc", new YpzlUtil().getAutoGnmkmc(path));
		return mapping.findForward("getYpzljgList");
	}
	
	/** 
	 * @描述:增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 上午09:36:00
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
	public ActionForward addYpzljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzljgForm model = (YpzljgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);		
		String path = "ypzl_jg.do?method=addYpzljg";
		request.setAttribute("path", path);
		request.setAttribute("xnsr", Base.currXn);
		request.setAttribute("xqsr", sqService.getXqmc(Base.currXq));
		request.setAttribute("jbxxList", jbxxList);
		if("10511".equals(Base.xxdm)){
			request.setAttribute("lbList", sqService.getYtlbList());
		}
		return mapping.findForward("addYpzljg");
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 下午02:01:24
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
	public ActionForward saveYpzljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzljgForm myForm = (YpzljgForm) form;
		User user =getUser(request);
		if("10511".equals(Base.xxdm)){
			String sqrq = GetTime.getTimeByFormat("yyyy-mm-dd");
			if(new YpzlUtil().checkIsExistsSameDate(sqrq, myForm.getXh(),"jg")){
				String messageKey = MessageKey.PJPY_YPZL_REPEAT_HSD;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}
		}else{
			if (service.isHaveRecordForjg(myForm)) {
				String message = MessageUtil.getText(MessageKey.PJPY_YPZL_REPEAT);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		
		myForm.setSjly(user.getUserName());
		boolean result = service.saveYpzljg(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 下午02:56:33
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
	public ActionForward editYpzljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzljgForm myForm = (YpzljgForm) form;
		User user = getUser(request);
		YpzljgForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		// 加载项目奖项信息
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", sqService.getXqmc(model.getXq()));
		request.setAttribute("sqje", model.getSqje());
		if("10511".equals(Base.xxdm)){
			request.setAttribute("lbList", sqService.getYtlbList());
		}
		request.setAttribute("xxdm", Base.xxdm);
		String path = "ypzl_jg.do?method=editYpzljg";
		request.setAttribute("path", path);
		return mapping.findForward("editYpzljg");
	}
	
	/** 
	 * @描述:删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 下午03:07:31
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
	public ActionForward delYpzljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
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
	 * @描述:导出数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 下午03:16:15
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YpzljgForm model = (YpzljgForm) form;
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
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 下午03:55:59
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
	public ActionForward viewYpzljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YpzljgForm model = (YpzljgForm) form;
		request.setAttribute("jbxxList", jbxxList);
		YpzljgForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		request.setAttribute("xqsr", sqService.getXqmc(model.getXq()));
		if("10511".equals(Base.xxdm)){
			request.setAttribute("ytmc", sqService.getYtmc(model.getYtlb()));
		}
		return mapping.findForward("viewYpzljg");

	}
	
	
}

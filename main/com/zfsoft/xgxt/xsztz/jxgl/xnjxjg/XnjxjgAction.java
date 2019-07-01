/**
 * @部门:学工产品事业部
 * @日期：2016-1-28 下午06:11:51 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxjg;

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
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsh.XnjxshService;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-28 下午06:11:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnjxjgAction extends SuperAction<XnjxjgForm,XnjxjgService >{
	private static final String url = "sztz_jxgl_xnjxjg.do";
	private final String TZXMSQ ="tzxmsq";
	XnjxjgService service = new XnjxjgService();
	
	/** 
	 * @描述:奖项结果列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 上午08:54:36
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
	public ActionForward xnjxjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxjgForm model = (XnjxjgForm) form;
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
    	searchModel.setSearch_tj_xq(new String[] { Base.currXq });
//		String[] sqshkg = service.getSqShKg();//错误
//		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_jxgl_xnjxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xnjxjgList");
	}
	
	
	/** 
	 * @描述:增加结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 上午08:55:20
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
	public ActionForward xnjxjgAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm model = (XnjxjgForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("currxn", Base.currXn);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "jxgl_xnjxjg.do?method=xnjxjgAdd";
		request.setAttribute("path", path);
		//其他信息配置
		return mapping.findForward("xnjxjgAdd");
	}
	
	
//	
//	@SystemAuth(url = url)
//	public ActionForward getjxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//	    XnjxsqForm model = (XnjxsqForm) form;
//		List<HashMap<String, String>> jxxxList = new XmsbService().getXmjxList(model.getXmdm());
//		JSONArray dataList = JSONArray.fromObject(jxxxList);
//		response.getWriter().print(dataList);
//		return null;
//	} 
//	
	
	/** 
	 * @描述:保存修改和增加结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 上午09:29:29
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
	public ActionForward savejg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm model = (XnjxjgForm) form;
		boolean result = false;
//		String message=null;
//	    User user = getUser(request);
//	    XsXmSqForm form1 = new XsXmSqForm();
//		form1.setXh(model.getXh());
 		if(model.getType().equals("save")){
 			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
 			model.setSfqq("0");
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			String oldxmdm = request.getParameter("oldxmdm");
			String oldjgid = request.getParameter("oldjgid");
			if(!model.getXmdm().equals(oldxmdm) && !model.equals(oldjgid)){
				XnjxjgForm delForm = new XnjxjgForm();
				delForm.setXmdm(oldxmdm);
				delForm.setJgid(oldjgid);
				delForm.setXh(model.getXh());
				delForm.setXq(model.getXq());
				delForm.setXn(model.getXn());
				service.runDelforjg(delForm);
			}
			model.setSfqq("0");
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/** 
	 * @描述:奖项结果修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 下午01:47:53
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
	public ActionForward editjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm myForm = (XnjxjgForm) form;
		XsXmJgService xsXmJgService = new XsXmJgService();
		XsXmJgForm xsXmJgForm = xsXmJgService.getModel(myForm.getJgid());
		if(null!=xsXmJgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(xsXmJgForm));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xsXmJgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(xsXmJgForm.getXmdm());
		request.setAttribute("rs", xmxx);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(xsXmJgForm.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		String path = "jxgl_xnjxjg.do?method=editjg";
		request.setAttribute("path", path);
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("oldxmdm", myForm.getXmdm());
		request.setAttribute("oldjgid", myForm.getJgid());
		return mapping.findForward("editjg");
	}
	
	
	/** 
	 * @描述:删除申请结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 上午10:07:35
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
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    XnjxjgForm model = (XnjxjgForm) form;
			String xhs[] = model.getXhss();
			String xqs[] = model.getXqs();
			String xns[] = model.getXns();
			String xmdms[] = model.getXmdms();
			int num = 0;
			for(int i =0;i<xhs.length;i++){
				model.setXh(xhs[i]);
				model.setXq(xqs[i]);
				model.setXn(xns[i]);
				model.setXmdm(xmdms[i]);
				boolean rs = service.runDelforjg(model);
				if(rs){
					num++;
				}
			}
			boolean result = num>0;		
			String message = result ? MessageUtil.getText(
						MessageKey.SYS_DEL_NUM, num) : MessageUtil
						.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
			return null;
	}

	/** 
	 * @描述:导出数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-1 上午09:00:20
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
		
		XnjxjgForm model = (XnjxjgForm) form;

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
	
	
	/** 
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-28 下午04:03:27
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
	public ActionForward viewJx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxjgForm myForm = (XnjxjgForm) form;
		XsXmJgForm xsXmJgForm = new XsXmJgService().getModel(myForm.getJgid());
		if(null!=xsXmJgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(xsXmJgForm));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xsXmJgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(xsXmJgForm.getXmdm());		
		request.setAttribute("rs", xmxx);
		XnjxshService xnjxshService = new XnjxshService();
		String jxmc = xnjxshService.getJxmc(myForm.getYlzd1());
		String jxfs = xnjxshService.getJxfs(myForm.getYlzd1());
		String jcxf = xmxx.get("jcxf");
		String zf = String.valueOf((Integer.parseInt(jxfs)+Integer.parseInt(jcxf)));
		request.setAttribute("zf", zf);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(xsXmJgForm.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		request.setAttribute("jxmc", jxmc);
		return mapping.findForward("viewJx");

	}
	
	@SystemAuth(url = url)
	public ActionForward getXmSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XnjxjgForm model = (XnjxjgForm) form;
		List<HashMap<String,String>> xmsqInfoList = service.getYiShen(model.getXh(),model.getXmdm());
	    request.setAttribute("xmsqInfoList", xmsqInfoList);
		return mapping.findForward("xmselect");
	}
}

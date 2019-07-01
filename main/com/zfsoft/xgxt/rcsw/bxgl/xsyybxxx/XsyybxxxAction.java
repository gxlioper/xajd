/**
 * @部门:学工产品事业部
 * @日期：2015-1-22 上午10:05:09 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 保险管理-学生预约报销信息 
 * @作者： 沈晓波 [工号:1123]
 * @时间： 2015-1-22 上午10:05:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyybxxxAction extends SuperAction {
	
	private static final String RCSWBXGL = "rcswbxgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_bxgl_xsyybxxx.do";
	
	/**
	 * 
	 * @描述: 学生预约报销信息列表
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-22 上午11:01:27
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
	public ActionForward xsyybxxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyybxxxService service = new XsyybxxxService();
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rcsw_bxgl_xsyybxxx.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_bxgl_xsyybxxx.do";
		request.setAttribute("path", path);
		request.setAttribute("tableName", "XG_RCSW_BXGL_XSYYBXXXB");
		request.setAttribute("realTable", "XG_RCSW_BXGL_XSYYBXXXB");
		FormModleCommon.commonRequestSet(request);
		
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			//获取固定格式系统年月日
			String nowTime = GetTime.getTimeByFormat("yyyymmdd");
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_kssj(new String[]{nowTime});
			searchModel.setSearch_tj_jssj(new String[]{nowTime});
			request.setAttribute("searchTj", searchModel);
		}

		return mapping.findForward("xsyybxxxList");
	}
	
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-26 上午09:37:08
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
	@SystemLog(description = "访问日常事务-保险管理-学生预约报销信息-增加XH:{xh},YYSJ:{yysj},BLNR:{blnr}")
	public ActionForward yyxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyybxxxService service = new XsyybxxxService();
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcsw_bxgl_xsyybx.do?method=yyxxAdd");
		request.setAttribute("model", myForm);
		this.saveToken(request);
		return mapping.findForward("yyxxAdd");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-26 上午09:37:08
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
	@SystemLog(description = "访问日常事务-保险管理-学生预约报销信息-修改YYBXID:{yybxid},XH:{xh},YYSJ:{yysj},BLNR:{blnr}")
	public ActionForward yyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		XsyybxxxService service = new XsyybxxxService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcsw_bxgl_xsyybx.do?method=yyxxUpdate");
//		request.setAttribute("type", "update");
//		XsyybxxxForm model = service.getModel(myForm);
//		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(model));
		XsyybxxxForm model = (XsyybxxxForm)StringUtils.formatData(service.getModel(myForm));
		request.setAttribute("model", model);
		return mapping.findForward("yyxxUpdate");
	}
	
	/**
	 * 
	 * @描述: 学生基本信息加载
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-26 上午09:37:08
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
	private void szXsxx(HttpServletRequest request,String xh) {
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWBXGL);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-26 上午09:37:08
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
	@SystemLog(description = "访问日常事务-保险管理-学生预约报销信息-删除VALUES:{values}")
	public ActionForward yyxxDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyybxxxService service = new XsyybxxxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-26 上午09:37:08
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
	public ActionForward ckYyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		XsyybxxxService service = new XsyybxxxService();
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcsw_bxgl_xsyybx.do?method=ckYyxx");
//		request.setAttribute("type", "update");
		XsyybxxxForm model = service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("ckYyxx");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-26 上午09:37:08
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
	public ActionForward yyxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyybxxxForm model = (XsyybxxxForm)form;
		XsyybxxxService service = new XsyybxxxService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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

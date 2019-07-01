/**
 * @部门:学工产品事业部
 * @日期：2016-2-29 下午02:16:40 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sq;

import java.io.File;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.zzdgl.cssz.CsszForm;
import com.zfsoft.xgxt.gygl.zzdgl.cssz.CsszService;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgForm;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-29 下午02:16:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdsqAction extends SuperAction<ZzdsqForm, ZzdsqService>{
	private ZzdsqService service = new ZzdsqService();
	private CsszService csszService = new CsszService();
	private ZzdjgService zzdjgService = new ZzdjgService();
	private static final String ZZDSQ = "zzdsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZZDSQ);
	}
	
	private static final String url = "xgygl_zzdgl_zdsq.do";
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午02:44:05
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
	public ActionForward getZzdsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
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
		request.setAttribute("searchTj", searchModel);		
		String path = "xgygl_zzdgl_zdsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZzdsqList");
	}
	
	
	/** 
	 * @描述:增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午05:01:20
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
	public ActionForward addZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
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
		String path = "xgygl_zdsq.do?method=addZzdsq";
		request.setAttribute("path", path);
		request.setAttribute("xnsr", Base.currXn);
		request.setAttribute("xqsr", service.getXqmc(Base.currXq));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd"));
		return mapping.findForward("addZzdsq");
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午05:25:33
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
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		boolean result = false;
	    User user = getUser(request);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			if(service.isHaveRecord(model)){
 				String messageKey = MessageKey.GYGL_ZZDGL_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
 			CsszForm csszForm = csszService.getModel();
 			if(null != csszForm){
 				model.setSplcid(csszForm.getSplcid());
 			}
			result = service.saveSqjg(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 上午09:30:44
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
	public ActionForward editZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm myForm = (ZzdsqForm) form;
		User user = getUser(request);
		ZzdsqForm model = service.getModel(myForm);
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
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		String path = "xgygl_zdsq.do?method=editZzdsq";
		request.setAttribute("path", path);
		request.setAttribute("sqsj",model.getSqsj());
		return mapping.findForward("editZzdsq");
	}
	
	/** 
	 * @描述:删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 上午09:31:46
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
	public ActionForward delZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 上午09:43:52
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
	public ActionForward submitZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		String values = request.getParameter("values");
		model.setZzdid(values);
		boolean result = service.submiZzdsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:撤销
	 * @作者：柳俊[工号：982]
	 * @日期：2016-3-1 上午10:18:13
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
	public ActionForward cancelZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			ZzdsqForm model = new ZzdsqForm();
			model.setZzdid(sqid);
			model.setSplcid(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:导出
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 上午10:19:09
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
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
	 * @日期：2016-3-1 下午06:11:04
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
	public ActionForward viewZzdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		request.setAttribute("jbxxList", jbxxList);
		ZzdsqForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		return mapping.findForward("viewZzdsq");

	}
	
	/** 
	 * @描述:如果登陆用户为学生判断是否住宿
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-1 下午06:24:28
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
	public ActionForward isZhusu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		User user = getUser(request);
		boolean result = true;
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
			result = service.iszhusu(model);
			response.getWriter().print(result);
		}else{
			response.getWriter().print(result);
		}
		return null;
	}
	
	/** 
	 * @描述:判断管理员是否确认
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 下午04:34:52
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
	public ActionForward isGlyqr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdsqForm model = (ZzdsqForm) form;
		boolean result = service.isGlyqr(model);
		response.getWriter().print(result);		
		return null;
	}
	
	/** 
	 * @描述:下载申请表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-3 下午06:30:27
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
	public ActionForward printzzdsqb (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxService xsxxService = new XsxxService();
		ZzdsqForm model = (ZzdsqForm) form;
		if(StringUtils.isNotNull(model.getZzdid())){
			List<File> files = new ArrayList<File>();
			String[] ids = model.getZzdid().split(",");
			for (String id : ids) {
				model.setZzdid(id);
				ZzdsqForm myForm = service.getModel(model);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());				
				String qsxx = service.getQxmForPrint(myForm.getXh());
				xsjbxx.put("qsxx", qsxx);
				xsjbxx.put("sdyy", myForm.getSdyy());
				//xsjbxx.put("jtdz", HtmlUtil.xmlZy(xsjbxx.get("jtdz")));	
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}else{
			List<File> files = new ArrayList<File>();
			String[] jgids = model.getYlzd1().split(",");
			ZzdjgForm ZzdjgForm = new ZzdjgForm();
			for (String jgid : jgids) {
				ZzdjgForm.setJgid(jgid);
				ZzdjgForm = zzdjgService.getModel(ZzdjgForm);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(ZzdjgForm.getXh());				
				String qsxx = service.getQxmForPrint(ZzdjgForm.getXh());
				xsjbxx.put("qsxx", qsxx);
				xsjbxx.put("sdyy", ZzdjgForm.getSdyy());
				//xsjbxx.put("jtdz", HtmlUtil.xmlZy(xsjbxx.get("jtdz")));	
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}

	
	
}

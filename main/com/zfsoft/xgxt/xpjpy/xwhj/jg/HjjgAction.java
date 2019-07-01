/**
 * @部门:学工产品事业部
 * @日期：2016-7-27 下午01:20:25 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.jg;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj.DmwhJxdjService;
import com.zfsoft.xgxt.xpjpy.xwhj.sq.HjsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖结果   
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-27 下午01:20:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjgAction extends SuperAction<HjjgForm, HjjgService> {
	private static final String url = "pjpy_hjgl_jg.do";
	private HjjgService service = new HjjgService();
	private HjsqService sqService = new HjsqService();
	private static final String HJSQ = "hjsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(HJSQ);
	}
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-27 下午05:26:23
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
	public ActionForward hjjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjjgForm model = (HjjgForm) form;
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
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		String path = "pjpy_hjgl_jg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hjjgList");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午08:45:41
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
	public ActionForward delHjjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午08:58:14
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
	public ActionForward viewHjjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjjgForm model = (HjjgForm) form;
		request.setAttribute("jbxxList", jbxxList);
		HjjgForm viewForm = service.getModel(model.getJgid());
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}		
		request.setAttribute("rs", model);	
		
		return mapping.findForward("viewHjjg");				
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午11:16:02
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
	public ActionForward addHjjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjjgForm model = (HjjgForm) form;
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
		
		String path = "xpj_hjjg.do?method=addHjjg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		
		return mapping.findForward("addHjjg");
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午11:18:49
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
	public ActionForward saveHjjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjjgForm myForm = (HjjgForm) form;
		if(myForm.getType().equals("save")) {
			if (service.isHaveRecordForjg(myForm)) {
				String message = "该学生当前奖项的申请已存在！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}else {
			if (service.isHaveRecordForjgU(myForm)) {
				String message = "该学生当前奖项的申请已存在！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}	
		}
		boolean result = service.saveHjjg(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;	
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 下午01:33:30
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
	public ActionForward editHjjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjjgForm myForm = (HjjgForm) form;
		HjjgForm model = service.getModel(myForm);
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
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		request.setAttribute("jxdjdm", model.getJxdjdm());
		request.setAttribute("jxmcdm", model.getJxmcdm());
		request.setAttribute("je", model.getJe());
		String path = "xpj_hjjg.do?method=editHjjg";
		request.setAttribute("path", path);
		
		return mapping.findForward("editHjjg");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 下午01:43:10
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
		
		HjjgForm model = (HjjgForm) form;
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
	
}

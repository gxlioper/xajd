/**
 * @部门:学工产品事业部
 * @日期：2014-4-10 上午10:19:47 
 */  
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj.FyfftjService;
import com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm.FyffxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——费用发放——发放结果  管理模块
 * @类功能描述: 发放结果维护
 * @作者： cq [工号:785]
 * @时间： 2014-4-10 上午10:19:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffjgAction extends SuperAction {
	
	private static final String url = "rcsw_fyff_fyffjg.do";
		
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String FYFF = "fyff";
	
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(FYFF);
	}
	
	/**
	 * 
	 * @描述:费用发放结果查看
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:23:25
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
	public ActionForward viewFyffjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	
		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		

		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_fyff_fyffjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewFyffjgList");
		
	}
	
	
	
	/**
	 * 
	 * @描述: 增加费用发放结果
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:29:28
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
	@SystemLog(description="访问日常事务-费用发放-费用发放结果-增加XH:{xh},FFXMDM:{ffxmdm},SFJE:{sfje},FFFS:{fffs},FFTJDM:{fftjdm}")
	public ActionForward addFyffjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// 如学号不为空，加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号，发放时间，发放项目，发放途径）
			boolean isExist = service.isExistByFfjg(model);
			if (!isExist) {
				// 添加评奖信息
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		
		
		//酬金发放项目List
		FyfftjService fyfftjservice = new FyfftjService();
		List<HashMap<String, String>> fftjList = fyfftjservice.getFyfftj();
		request.setAttribute("fftjList",fftjList);
		
		//酬金发放途径List
		FyffxmService fyffxmservice = new FyffxmService();
		List<HashMap<String, String>> ffxmList = fyffxmservice.getFyffxm();
		request.setAttribute("ffxmList", ffxmList);
		

		String path = "rcsw_fyff_ffjg.do?method=addFyffjg";
		request.setAttribute("path", path);

		return mapping.findForward("addFyffjg");
	}
	
	
	/**
	 * 
	 * @描述:修改费用发放结果
	 * @作者：cq [工号：785]
	 * @日期：2014-4-15 下午02:50:16
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
	@SystemLog(description="访问日常事务-费用发放-费用发放结果-修改guid:{guid},XH:{xh},FFXMDM:{ffxmdm},SFJE:{sfje},FFFS:{fffs},FFTJDM:{fftjdm}")
	public ActionForward updateFyffjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
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

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号，发放时间，发放项目，发放途径）
			boolean isExist = service.isExistByFfjg(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		
		//酬金发放项目List
		FyfftjService fyfftjservice = new FyfftjService();
		List<HashMap<String, String>> fftjList = fyfftjservice.getFyfftj();
		request.setAttribute("fftjList",fftjList);
		
		//酬金发放途径List
		FyffxmService fyffxmservice = new FyffxmService();
		List<HashMap<String, String>> ffxmList = fyffxmservice.getFyffxm();
		request.setAttribute("ffxmList", ffxmList);
		

		String path = "xpj_pjjg.do?method=updateFyffjg";
		request.setAttribute("path", path);
		
		FyffjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		request.setAttribute("jbxxList", jbxxList);
		
		//根据项目代码获取发放方式
		
		String fffs = service.getFffs(model.getFfxmdm());
		request.setAttribute("fffs", fffs);
		
		return mapping.findForward("updatePjxmjg");

	}
	
	
	/**
	 * 
	 * @描述: 删除费用发放结果
	 * @作者：cq [工号：785]
	 * @日期：2014-4-15 下午05:40:48
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
	@SystemLog(description="访问日常事务-费用发放-费用发放结果-删除VALUES:{values}")
	public ActionForward deleteFyffjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgService service = new FyffjgService();
		
		//获得id
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
	
	
	/**
	 * 
	 * @描述: 费用发放结果导出
	 * @作者：cq [工号：785]
	 * @日期：2014-4-16 下午02:14:46
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
		
		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		

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
	 * 
	 * @描述:费用发放结果单个查看
	 * @作者：cq [工号：785]
	 * @日期：2014-4-17 上午09:53:34
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
	public ActionForward oneFyffjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			// 查询结果
			request.setAttribute("rs", StringUtils.formatData(service.getOneFyffjgList(model.getGuid())));

			return mapping.findForward("oneFyffjgView");
		} else {
			return updateFyffjg(mapping, form, request, response);
		}

	}
	
	
}

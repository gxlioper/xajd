/**
 * @部门:学工产品事业部
 * @日期：2013-8-27 上午09:08:18 
 */
package com.zfsoft.xgxt.xtwh.bjdl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
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

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 班级大类设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-8-27 上午09:08:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BjdlAction extends SuperAction {

	private final static String ACTION_SCDL_ALL = "szdl_all";
	
	private static final String url = "xg_bjdl.do";

	/**
	 * 
	 * @描述: 班级信息列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-27 上午10:20:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewBjxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();

		if (QUERY.equals(model.getType())) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xg_bjdl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("viewBjxxList");
	}

	/**
	 * 
	 * @描述: 设置班级大类
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-27 下午03:04:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward szBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();
		if (ACTION_SCDL_ALL.equals(model.getSzType())) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			List<HashMap<String, String>> resultList = service.getAllList(
					model, user); // 全部查询

			StringBuilder ids = new StringBuilder();

			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
				HashMap<String, String> hashMap = (HashMap<String, String>) iterator
						.next();
				String bjdm = hashMap.get("bjdm");
				ids.append(bjdm);
				if (iterator.hasNext())
					ids.append(",");

			}

			model.setBjdm(ids.toString());

		}

		List<HashMap<String, String>> bjxxList = service.getBjxxList(model);

		request.setAttribute("bjxxList", bjxxList);
		return mapping.findForward("szBjdl");
	}

	/**
	 * 
	 * @描述: 保存班级大类
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-27 下午04:06:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-班级大类设置-保存BJDM：{bjdm}")
	public ActionForward saveBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();

		String[] bjdm = request.getParameterValues("bjdm");

		boolean result = service.szBjdl(model, bjdm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * 
	 * @描述:取消班级大类设置
	 * @作者：cq [工号：785]
	 * @日期：2014-8-19 上午09:53:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-班级大类设置取消-VALUES：{values}")
	public ActionForward delBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		String bjdm = request.getParameter("values");
		String bjdms[] = StringUtils.isNotNull(bjdm)?bjdms = bjdm.split(","):null;
		boolean result = service.qxBjdl(bjdms,model);

		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;

	}
	
	/**
	 * @描述: 增加导出方法
	 * @作者：MengWei[工号：1186]
	 * @日期：2016-8-18 下午04:43:12
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
		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();
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

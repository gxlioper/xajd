/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.jzbg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.dwjl.DwjlForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

public class JzbgAction extends SuperAction{
private static List<HashMap<String, String>> jbxxList = null;
	
	JzbgService service = new JzbgService();
	
	private static final String url = "jzbg_jzbggl.do";
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2017-12-15 上午09:19:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jzbgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JzbgForm model = (JzbgForm) form;
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
		request.setAttribute("path", url);
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jzbgList");
	}
	
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2017-12-15 上午09:18:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward addJzbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JzbgForm model = (JzbgForm) form;
			User user = getUser(request);
			model.setFbr(user.getUserName());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setFbsj(df.format(new Date()));
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
	    	}
			return null;
		}
		String path = "jzbggl.do?method=addJzbg";
		request.setAttribute("path", path);
		return mapping.findForward("addJzbg");
	}

	/**
	 * @description	： 修改
	 * @author 		： CP（1352）
	 * @date 		：2017-12-15 上午10:26:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward xgJzbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JzbgForm model = (JzbgForm) form;
		JzbgForm myForm = service.getModel(model);
		request.setAttribute("jzid", myForm.getJzid());
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_TOKEN_VALID, false));
			}

			return null;
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("xgJzbg");
	}
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2017-12-15 上午11:27:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	public ActionForward delJzbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean num = service.isExistPjxx(values);
			boolean result =false;
			String message ="";
			if (!num) {
				 result = service.JzbgDelete(values);
				 message = result ? MessageUtil.getText(
						 MessageKey.SYS_DEL_SUCCESS) : MessageUtil
						 .getText(MessageKey.SYS_DEL_FAIL);
			}else {
				message="该讲座已经有人参加，不可删除！";
			}
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	


	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2017-12-15 上午11:31:00
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckJzbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JzbgForm myForm = (JzbgForm) form;
		JzbgService service = new JzbgService();
		HashMap<String, String>  model = service.getModelXX(myForm);
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("ckJzbg");
	}

/**
 * @description	： 导出
 * @author 		： CP（1352）
 * @date 		：2017-12-15 下午01:45:28
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
		JzbgForm model=(JzbgForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
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
 * @description	： TODO
 * @author 		： CP（1352）
 * @date 		：2017-12-15 下午02:03:16
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
	public ActionForward jzpjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JzbgForm model = (JzbgForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPjxxPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "jzbg_jzbgpj.do");
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jzpjList");
	}

/**
 * @description	：参加
 * @author 		： CP（1352）
 * @date 		：2017-12-15 下午05:23:51
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward cjJzbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JzbgForm model = (JzbgForm) form;
			User user = getUser(request);
			boolean result = service.insertPjxx(model, user);
			String messageKey = result ? "参加成功！" : "参加失败！";
			response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}

	public ActionForward pjJzbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JzbgForm model = (JzbgForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
				boolean flag = service.updatePjxx(model);
				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "jzbggl.do?method=pjJzbg";
		request.setAttribute("path", path);
		return mapping.findForward("pjJzbg");
	}
	
	
	/**
	 * @description	： 学生端查看
	 * @author 		： CP（1352）
	 * @date 		：2017-12-18 上午11:17:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckJzbgStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JzbgForm myForm = (JzbgForm) form;
		HashMap<String,String> rs = service.getJzbgXX(myForm);
		request.setAttribute("rs", StringUtils.formatData(rs));
		return mapping.findForward("ckJzbgStu");
	}
}

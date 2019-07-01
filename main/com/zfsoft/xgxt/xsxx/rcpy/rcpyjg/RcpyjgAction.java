/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpyjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.rcpy.rcpysq.RcpysqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
/**
 * @className	： RcpyjgAction
 * @description	：人才培养结果
 * @author 		：CP（1352）
 * @date		： 2018-5-15 上午11:57:07
 * @version 	V1.0
 */
public class RcpyjgAction extends SuperAction{
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String url = "xsxx_rcpy_rcpyjg.do";
	RcpyjgService service = new  RcpyjgService();
	RcpysqService rcpysqService = new RcpysqService();

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2018-5-15 上午11:57:02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcpyjgForm model = (RcpyjgForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("getjgList");
	}
	
	
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2018-5-15 下午01:46:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward zjRcpyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			RcpyjgForm model = (RcpyjgForm) form;
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			List<HashMap<String,String>> pylbList = rcpysqService.getPylbList();
			List<HashMap<String,String>> khzbList = rcpysqService.getKhzbList();
			List<HashMap<String,String>> xztjList = rcpysqService.getXztjList();
			request.setAttribute("pylbList", pylbList);
			request.setAttribute("khzbList", khzbList);
			request.setAttribute("xztjList", xztjList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.runInsert(model);
        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz("rcswqjgl_qjgl");
		request.setAttribute("jbxxList", jbxxList);
		String path = "rcpy_rcpyjg.do?method=zjRcpyjg";
		request.setAttribute("path", path);
		return mapping.findForward("zjRcpyjg");
	}
	
	/**
	 * @description	： 修改
	 * @author 		：CP（1352）
	 * @date 		：2018-5-15 下午02:00:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward xgRcpyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			RcpyjgForm model = (RcpyjgForm) form;
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			List<HashMap<String,String>> pylbList = rcpysqService.getPylbList();
			List<HashMap<String,String>> khzbList = rcpysqService.getKhzbList();
			List<HashMap<String,String>> xztjList = rcpysqService.getXztjList();
			request.setAttribute("pylbList", pylbList);
			request.setAttribute("khzbList", khzbList);
			request.setAttribute("xztjList", xztjList);
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.runUpdate(model);
        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz("rcswqjgl_qjgl");
		request.setAttribute("jbxxList", jbxxList);
		RcpyjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		String path = "rcpy_rcpyjg.do?method=xgRcpyjg";
		request.setAttribute("path", path);
		return mapping.findForward("xgRcpyjg");
	}
	
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-5-15 下午02:06:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delRcpyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delRcpyjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @description	： 查看
	 * @author 		： CP（1352）
	 * @date 		：2018-5-15 下午02:13:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckRcpyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcpyjgForm model = (RcpyjgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		HashMap<String,String> rcpyMap = service.getRcpyjgInfo(model);
		request.setAttribute("rs", StringUtils.formatData(rcpyMap));
		request.setAttribute("filepath", rcpyMap.get("filepath"));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz("rcswqjgl_qjgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("ckRcpyjg");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-5-15 下午02:39:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcpyjgForm model = (RcpyjgForm) form;
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
}
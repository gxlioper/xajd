/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsgljg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class DsgljgAction extends SuperAction{
	private static final String DEKT = "dekt";//rcswbxgl
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String url = "xg_dekt_dsgljg.do";
	DsgljgService service = new DsgljgService();
	
	/**
	 * @description	： 查询
	 * @author 		： CP（1352）
	 * @date 		：2018-1-18 下午12:00:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 
	@SystemAuth(url = url)
	public ActionForward dsglJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsgljgForm model = (DsgljgForm) form;
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
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("dsjgcx");
		
	}
	
	
	
//	
//	/**
//	 * @description	： 增加
//	 * @author 		： CP（1352）
//	 * @date 		：2018-1-18 下午01:49:01
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url)
//	public ActionForward add(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//			DsgljgForm model = (DsgljgForm) form;
//			if (!StringUtil.isNull(model.getXh())){
//				//加载学生基本信息
//				XsxxService xsxxService = new XsxxService();
//				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
//				request.setAttribute("jbxx", xsjbxx);
//			}
//		if (SAVE.equalsIgnoreCase(model.getType())) {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			model.setSqsj(df.format(new Date()));
//			model.setXn(Base.currXn);
//			model.setXq(Base.currXq);
//			boolean isExist = service.isExist(model);
//        	if(!isExist){
//        		boolean result = service.runInsert(model);
//        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
//				response.getWriter().print(getJsonMessageByKey(messageKey));
//        	}else{
//        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//        	}
//			return null;
//		}
//		//学生基本信息显示配置
//		jbxxList = bdpzService.getJbxxpz(DEKT);
//		request.setAttribute("jbxxList", jbxxList);
//		//学年 学期
//		request.setAttribute("dqxn", Base.currXn);
//		request.setAttribute("dqxq", Base.getDqxqmc());
//		String path = "dekt_dsgljg.do?method=add";
//		request.setAttribute("path", path);
//		return mapping.findForward("dsjgzj");
//	}
//	
//	
//	/**
//	 * @description	： 修改
//	 * @author 		：CP（1352）
//	 * @date 		：2018-1-18 下午03:23:02
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url)
//	public ActionForward update(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//			DsgljgForm model = (DsgljgForm) form;
//			if (!StringUtil.isNull(model.getXh())){
//				//加载学生基本信息
//				XsxxService xsxxService = new XsxxService();
//				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
//				request.setAttribute("jbxx", xsjbxx);
//			}
//		if (UPDATE.equalsIgnoreCase(model.getType())) {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			model.setSqsj(df.format(new Date()));
//			model.setXn(Base.currXn);
//			model.setXq(Base.currXq);
//			boolean isExist = service.isExist(model);
//        	if(!isExist){
//        		boolean result = service.updateDsxx(model);
//        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
//				response.getWriter().print(getJsonMessageByKey(messageKey));
//        	}else{
//        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//        	}
//			return null;
//		}
//		//学生基本信息显示配置
//		jbxxList = bdpzService.getJbxxpz(DEKT);
//		request.setAttribute("jbxxList", jbxxList);
//		//学年 学期
//		request.setAttribute("dqxn", Base.currXn);
//		request.setAttribute("dqxq", Base.getDqxqmc());
//		DsgljgForm updateForm = service.getModel(model);
//		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
//		String path = "dekt_dsgljg.do?method=update";
//		request.setAttribute("path", path);
//		return mapping.findForward("dsjgxg");
//	}
//	
//	
//	
//	
//	/**
//	 * @description	： 删除
//	 * @author 		： CP（1352）
//	 * @date 		：2018-1-18 下午03:23:25
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
//	public ActionForward del(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String values = request.getParameter("values");
//		if (!StringUtil.isNull(values)) {
//			String[] mess = service.deleteDsxxjg(values.split(","));
//			if(null==mess||mess.length!=2){
//				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
//				response.getWriter().print(getJsonMessage(message));
//				return null;
//			}
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("num",mess[0]);
//			map.put("nodel",mess[1]);
//			JSONObject json = JSONObject.fromObject(map);
//			response.getWriter().print(json);
//		} else {
//			throw new SystemException(MessageKey.SYS_DEL_NULL);
//		}
//		return null;
//	}
//	
//	
//	/**
//	 * @description	： 查看
//	 * @author 		： CP（1352）
//	 * @date 		：2018-1-18 下午03:23:35
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url)
//	public ActionForward view(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		DsgljgForm model = (DsgljgForm) form;
//		User user = getUser(request);
//		if ("stu".equals(user.getUserType())){
//			model.setXh(user.getUserName());
//		}
//		//加载学生基本信息
//		XsxxService xsxxService = new XsxxService();
//		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
//		request.setAttribute("jbxx", xsjbxx);
//		//查询单个行为信息结果
//		HashMap<String,String> gfqkMap = service.getDsxxInfo(model);
//		request.setAttribute("rs", StringUtils.formatData(gfqkMap));
//		request.setAttribute("gfqkfl", gfqkMap.get("gfqkfl"));
//		request.setAttribute("filepath", gfqkMap.get("filepath"));
//		//学生基本信息显示配置
//		jbxxList = bdpzService.getJbxxpz(DEKT);
//		request.setAttribute("jbxxList", jbxxList);
//		request.setAttribute("model", StringUtils.formatData(model));
//		return mapping.findForward("dsjgck");
//	}
	
	/**
	 * @description	： 阅读详情
	 * @author 		： CP（1352）
	 * @date 		：2018-1-18 下午04:04:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewydxq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsgljgForm model = (DsgljgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//查询所有阅读图书信息详情
		List<String[]> ydlist = service.getYdxqInfo(model.getXh());//获取阅读
		request.setAttribute("list", ydlist);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DEKT);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("dsjgydxqck");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-1-18 下午03:23:52
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
		DsgljgForm model = (DsgljgForm) form;
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

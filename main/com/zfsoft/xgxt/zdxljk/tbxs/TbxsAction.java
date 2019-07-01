/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:13:40 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zdxljk.ecmm.EcmmModel;
import com.zfsoft.xgxt.zdxljk.ecmm.EcmmService;

/** 
 * @类功能描述: 浙大心理健康--特别关心学生 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class TbxsAction extends SuperAction<TbxsModel, TbxsService> {

	private static final String url = "zdxljk_tbxs.do";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		User user = getUser(request);
		String zgh = user.getUserName();
		String ecmm = request.getParameter("ecmm");
		
		if (!StringUtil.isNull(zgh) & !StringUtil.isNull(ecmm)){
			EcmmService ecmmService = new EcmmService();
			EcmmModel ecmmModel = ecmmService.getModelByLogin(zgh,ecmm);
			
			if (ecmmModel != null){
				session.setAttribute("ecdl","yes");
				return super.execute(mapping, form, request, response);
			} else {
				request.setAttribute("ecdl", "fail");
			}
		}
		
		String ecdl = (String) session.getAttribute("ecdl");
			
		if (StringUtil.isNull(ecdl)){
			return mapping.findForward("ecdl");
		}
		
		return super.execute(mapping, form, request, response);
	}


	/**特别学生列表**/
	@SystemAuth(url = url)
	public ActionForward tbxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zdxljk_tbxs.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tbxsList");
	}
	
	
	/**特别学生列表**/
	@SystemAuth(url = url)
	public ActionForward getTbxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**特别学生谈话记录**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> thjlList = service.getThjlByXh(model.getXh());
			if(thjlList.size()>0){
				model.setQxyy(thjlList.get(0).get("qxyy"));
				model.setGzlx(thjlList.get(0).get("gzlx"));
				
			}
			
			request.setAttribute("thjlList", thjlList);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("gzlxList", new OptionUtil().getOptions(OptionUtil.THJL_GZLX));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ftr", user.getRealName());
		request.setAttribute("model", model);
		request.setAttribute("path", "zdxljkTbxs.do?method=addThjl");
		return mapping.findForward("addThjl");
	}
	
	
	/**特别学生谈话记录**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		User user = getUser(request);
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> thjlList = service.getThjlByXh(model.getXh());
			if(thjlList.size()>0){
				model.setQxyy(thjlList.get(0).get("qxyy"));
				model.setGzlx(thjlList.get(0).get("gzlx"));
				
			}
			request.setAttribute("thjlList", thjlList);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzlxList", new OptionUtil().getOptions(OptionUtil.THJL_GZLX));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ftr", user.getRealName());
		request.setAttribute("model", model);
		
		return mapping.findForward("editThjl");
	}
	
	@SystemAuth(url = url)
	public ActionForward viewThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> thjlList = service.getThjlByXh(model.getXh());
			if(thjlList.size()>0){
				model.setQxyy(thjlList.get(0).get("qxyy"));
				model.setGzlx(thjlList.get(0).get("gzlx"));
				
			}
			request.setAttribute("thjlList", thjlList);
			
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		return mapping.findForward("viewThjl");
	}
	
	
	/**特别学生谈话记录**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康--特别关注学生-保存xh:{xh}")
	public ActionForward saveThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		boolean result = service.save(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康--特别关注学生-删除ids:{ids}")
	public ActionForward delThjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		TbxsService service = new TbxsService();
		
		int num = service.runDelete(ids.split(","));
		boolean result = num > 0;
		String message = result ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, num) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
	
		return null;
	}
	
	
	/**谈话记录导出**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbxsModel model = (TbxsModel) form;
		TbxsService service = new TbxsService();
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model, user);// 查询出所有记录，不分页

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
	
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward szgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		TbxsModel model = (TbxsModel) form;
		request.setAttribute("model", model);
		request.setAttribute("gzlxList", new OptionUtil().getOptions(OptionUtil.THJL_GZLX));
		request.setAttribute("ids", ids);
		return mapping.findForward("szgz");
	}
	
	
	/** 设置关注 **/
	@SystemLog(description="访问心理健康--特别关注学生-设置关注ids:{ids}")
	public ActionForward saveSzgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbxsService service = new TbxsService();
		TbxsModel model = (TbxsModel) form;
		String ids = request.getParameter("ids");
		boolean result = service.saveSzgz(model,ids);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
}

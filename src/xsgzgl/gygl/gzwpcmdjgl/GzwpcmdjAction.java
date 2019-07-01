/**
 * @部门:学工产品事业部
 * @日期：2014-8-27 下午04:01:28 
 */  
package xsgzgl.gygl.gzwpcmdjgl;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 贵重物品出门登记管理 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2014-8-27 下午04:01:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzwpcmdjAction extends SuperAction {
	
	private static final String url = "gygl_gzwpcmdjgl.do";
	
	/**
	 * 
	 * @描述: 贵重物品出门登记列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午01:42:21
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
	public ActionForward gzwpcmdjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_gzwpcmdjgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzwpcmdjManage");
	}
	
	/**
	 * 
	 * @描述: 增加跳转
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午01:57:50
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
	public ActionForward gzwpcmdjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		
		HashMap<String, String> gzwpxx = service.getXsxxOne(model.getXh());
		request.setAttribute("gzwpxx", gzwpxx);
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		
		}
		request.setAttribute("path", URLEncoder.encode("gygl_gzwpcmdj.do?method=gzwpcmdjAdd" , "gbk"));
		return mapping.findForward("gzwpcmdjAdd");
	}
	
	/**
	 * 
	 * @描述: 修改跳转
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午02:04:56
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
	public ActionForward gzwpcmdjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		
		String gzwpdjid = model.getGzwpdjid();
		
		if(StringUtils.isNotBlank(gzwpdjid)){

			HashMap<String ,String> data = service.getGzwpcmdjxx(gzwpdjid);
			request.setAttribute("gzwpcmdjxx", data);
		}
				
		
		return mapping.findForward("gzwpcmdjUpdate");
	}
	
	
	/**
	 * 
	 * @描述: 根据学号关联查询出需要的数据并跳转
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午03:16:42
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzwpcmdjForm model = (GzwpcmdjForm) form;
		GzwpcmdjService service = new GzwpcmdjService();
		
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
		
		String gotoPath = request.getParameter("goto");
		String path = "gygl_gzwpcmdj_showStudents.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}
	
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午03:26:59
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
	@SystemLog(description="访问公寓管理-贵重物品出门登记管理-贵重物品出门登记-增加XH:{xh},CMSJ:{cmsj},WPMC:{wpmc},ZBRY:{zbry}")
	public ActionForward gzwpcmdjAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
	
		String wpmc = model.getWpmc();
		String cmsj = model.getCmsj();
		String zbry = model.getZbry();
		String xh = model.getXh();
		
		if(StringUtils.isNotBlank(zbry) && StringUtils.isNotBlank(xh) && StringUtils.isNotBlank(wpmc) && StringUtils.isNotBlank(cmsj)){
			boolean isSuccess = service.saveGzwpcmdjxx(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午03:33:49
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
	@SystemLog(description="访问公寓管理-贵重物品出门登记管理-贵重物品出门登记-修改PK:{gzwpdjid},XH:{xh},CMSJ:{cmsj},WPMC:{wpmc},ZBRY:{zbry}")
	public ActionForward gzwpcmdjUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
		
		boolean isSuccess = service.saveGzwpcmdjxx(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午03:41:13
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
	@SystemLog(description="访问公寓管理-贵重物品出门登记管理-贵重物品出门登记-删除PK:{pks}")
	public ActionForward gzwpcmdjDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		
		GzwpcmdjService service = new GzwpcmdjService();
		
		String pks = request.getParameter("pks"); 
		
		if(StringUtils.isNotBlank(pks)){
			String[] pkArr = pks.split(",");
			List<String[]> pkList = new ArrayList<String[]>();
			for (String string : pkArr) {
				pkList.add(string.split(","));
			}
		service.deleteGzwpcmdjxxPl(pkList);
		String messageKey =MessageKey.SYS_DEL_SUCCESS;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看详细信息（单条）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-28 下午04:50:17
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
	public ActionForward viewWpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
		
		String gzwpdjid = model.getGzwpdjid();
		
		if(StringUtils.isNotBlank(gzwpdjid)){

			HashMap<String ,String> data = service.getGzwpcmdjxx(gzwpdjid);
			request.setAttribute("gzwpcmdjxx", data);
		}
		
		String path = "gygl_gzwpcmdj.do?method=viewWpxx";
		request.setAttribute("path", path);
		return mapping.findForward("viewWpxx");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-29 上午09:34:44
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
		GzwpcmdjForm model = (GzwpcmdjForm) form;	
		GzwpcmdjService service = new GzwpcmdjService();
		
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

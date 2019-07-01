package xsgzgl.gygl.gyglry;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyglryAction extends BasicAction{
	
	private GyglryService service=new GyglryService();
	public static final String KFILE = "没有文件！";
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	/**
	 * 公寓管理人员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-管理人员-公寓管理人员-{doType}维护QSH:{fp_qsh}")
	public ActionForward gyglryManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gyglry_gyglry.do");
		GyglryForm model=(GyglryForm)form;
		User user = getUser(request);
		String doType=request.getParameter("doType");
		if("qxfp".equals(doType)){//取消分配
			String message=service.gyglryQxfp(model);
			request.setAttribute("message", message);
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}
		
		List<String[]> rsList=service.getGyglryList(model,request,user);
		
		request.setAttribute("rsList", rsList);
		request.setAttribute("topTr", service.getTopTr("gyglry"));
		request.setAttribute("searchTj", model.getSearchModel());	//高级查询条件
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tableName", "xg_view_gygl_new_gyglryb");
		request.setAttribute("realTable", "xg_gygl_new_gyglryb");
		
		return mapping.findForward("gyglryManage");
	}
	
	public ActionForward gyglryQxfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oldxh=request.getParameter("oldxh");
		request.setAttribute("oldxh", oldxh);
		
		
		return mapping.findForward("gyglryQxfp");
	}
	
	/**
	 *寝室长维护自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward qszwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GyglryForm model=(GyglryForm)form;		
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		request.setAttribute("path", "gyglnew_gyglry_gyglry.do");
		List<HashMap<String,String>> resultList = service.getGyglryExportList(model,request,user);
		
		
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
	 * 公寓管理人员分配
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-管理人员-公寓管理人员-{doType}维护QSH:{fp_qsh}")
	public ActionForward gyglryFp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String rzksrq=(String)session.getAttribute("temp_gygl_gyglryfp_rzksrq");//入住时间
		GyglryForm model=(GyglryForm)form;
		if(Base.isNull(model.getRzksrq())){
			model.setRzksrq(rzksrq);
		}else{
			session.setAttribute("temp_gygl_gyglryfp_rzksrq", model.getRzksrq());
		}
		if(StringUtils.isNull(request.getParameter("query"))){  //不限制楼层时，查询层号为空时返回依旧为分配楼层的数据。
			if(Base.isNull(model.getCh())){model.setCh(model.getFp_ch());}
		}
		if(Base.isNull(model.getQsh())){model.setQsh(model.getFp_qsh());}
		String doType=Base.chgNull(request.getParameter("doType"), "",0);
		if("fp".equals(doType)){//分配
			String message=service.gyglryFp(model);
			request.setAttribute("message", message);
		}else{
			boolean flag = service.fpFullFlag(model);
			request.setAttribute("gyglryfpczfs",flag);
		}	
			List<String[]> rs=service.getRzxsxxList(model);
			request.setAttribute("rs", rs);
			request.setAttribute("pageSize", rs.size());
			request.setAttribute("topTr", service.getTopTr("cwwh"));
		
			HashMap<String,String> rsp=new HashMap<String, String>();
			rsp.put("fp_ch", model.getFp_ch());
			rsp.put("fp_qsh", model.getFp_qsh());
			request.setAttribute("rsp", rsp);
			
			request.setAttribute("chList", service.getChList(model));
			request.setAttribute("qshList", service.getQshList(model));
		
		return mapping.findForward("gyglryFp"); 
	}
	
	/**
	 * 同步干部信息至思政队伍
	 * jQuery - ajax 调用
	 */
	@SystemLog(description="访问公寓管理-管理人员-公寓管理人员-同步")
	public ActionForward gyglryTbgbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 同步部门
		boolean result = service.tbgbInfo();		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result); 
		return null;
	}
	
	/**
	 * 
	 * @描述: 武昌首义学院公寓管理个性化导入，除非学校要求，否则不要开启成通用功能
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 下午04:04:54
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
	public ActionForward gyglxDr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("gyglydr");
	}
	
	/**
	 * 
	 * @描述: 下载导出模板
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 下午04:18:12
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
	public ActionForward downloadMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		File file = service.downloadMb();
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("xg_gyglydr.xls","utf-8")); 
		FileUtil.outputFile(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述: 下载错误模板
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 下午04:19:52
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
	public ActionForward downloadError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String fileName = request.getParameter("filename");
		File file = new File(TEMP_DIR +"/"+fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8")); 
		FileUtil.outputFile(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述: 导入
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 下午04:22:25
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
	public ActionForward dr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GyglryForm model=(GyglryForm)form;
		FormFile file = model.getFile();
		if(file != null){
			try {
				HashMap<String,String> resultMap = service.getAndSaveDrData(file.getInputStream());
				JSONObject jsonMap = JSONObject.fromObject(resultMap);
				response.getWriter().print(jsonMap);
			}catch (SystemException e){
				response.getWriter().print(getJsonMessage(e.getMessage()));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_OPERATE_FAIL));
				e.printStackTrace();
			}
			
		}else{
			response.getWriter().print(getJsonMessage(KFILE));
		}
		return null;
	}
	
}

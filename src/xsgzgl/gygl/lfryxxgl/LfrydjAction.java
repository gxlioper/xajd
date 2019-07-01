/**
 * @部门:学工产品事业部
 * @日期：2014-8-19 下午05:00:12 
 */  
package xsgzgl.gygl.lfryxxgl;

import java.io.File;
import java.net.URLDecoder;
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
import xsgzgl.gygl.qsgl.QsglService;

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
 * @类功能描述: 来访人员登记管理
 * @作者： 沈晓波[工号:1123]
 * @时间： 2014-8-19 下午05:00:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LfrydjAction extends SuperAction {
	
	private static final String url = "gygl_lfryxxgl.do";
	
	/**
	 * 
	 * @描述: 来访登记管理列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-20 上午11:22:00
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
	public ActionForward lfrydjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service  = new LfrydjService();
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
		request.setAttribute("path", "gygl_lfryxxgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lfrydjManage");
	}
	
	/**
	 * 
	 * @描述: 增加跳转
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-21 上午08:45:59
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
	public ActionForward lfrydjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
//		HashMap<String, String> lfryxx = service.getXsxxOne(model.getXh());
		List<HashMap<String,String>> lfsyList = service.getLfsyList();	//查询来访事由列表，用于select下拉列表的显示
		
//		request.setAttribute("lfryxx", lfryxx);
		request.setAttribute("lfsyList", lfsyList);
		request.setAttribute("ldList", new QsglService().getLdList());
		
		request.setAttribute("path", URLEncoder.encode("gygl_lfrydj.do?method=lfrydjAdd" , "gbk"));
		return mapping.findForward("lfrydjAdd");
	}
	
	/**
	 * 
	 * @描述: 修改跳转
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-25 上午11:49:11
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
	public ActionForward lfrydjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		String lfrdjid = model.getLfrdjid();
		List<HashMap<String,String>> lfsyList = service.getLfsyList();	//查询来访事由列表，用于select下拉列表的显示
		
		request.setAttribute("lfsyList", lfsyList);
		request.setAttribute("ldList", new QsglService().getLdList());
		
		if(StringUtils.isNotBlank(lfrdjid)){

			HashMap<String ,String> data = service.getLfrydjxx(lfrdjid);
			request.setAttribute("lfrydjxx", xgxt.utils.String.StringUtils.formatData(data));
		}
				
		
		return mapping.findForward("lfrydjUpdate");
	}
	
	
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-25 下午03:10:01
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
	@SystemLog(description="访问公寓管理-来访人员登记管理-来访人员登记-增加XH:{xh},LFRXM:{lfrxm},LFRXB:{lfrxb},LFRSFZH:{lfrsfzh},LFSJ:{lfsj},ZBRY:{zbry}")
	public ActionForward lfrydjAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		boolean result = service.runInsert(model);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-25 下午03:10:21
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
	@SystemLog(description="访问公寓管理-来访人员登记管理-来访人员登记-修改PK:{lfrdjid},XH:{xh},LFRXM:{lfrxm},LFRXB:{lfrxb},LFRSFZH:{lfrsfzh},LFSJ:{lfsj},ZBRY:{zbry}")
	public ActionForward lfrydjUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-21 上午10:51:47
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
	@SystemLog(description="访问公寓管理-来访人员登记管理-来访人员登记-删除PK:{pks}")
	public ActionForward lfrydjDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjService service = new LfrydjService();
		
		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			String[] pkArr = pks.split(",");
			List<String[]> pkList = new ArrayList<String[]>();
			for (String string : pkArr) {
				pkList.add(string.split(","));
			}
		service.deleteLfrydjxxPl(pkList);
		String messageKey =MessageKey.SYS_DEL_SUCCESS;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		}
		
		return null;
		
	}
	
	
	
	
	/**
	 * 
	 * @描述: 根据学号关联查询出需要的数据并跳转
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-27 上午09:08:52
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
		
		String elementIds = request.getParameter("elementIds");
		String lddm = request.getParameter("lddm");
		
		String path = "gygl_lfrydj_showStudents.do";
		request.setAttribute("path", path);
		request.setAttribute("elementIds", elementIds);
		request.setAttribute("lddm", lddm);
		return mapping.findForward("showStudents");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-27 下午02:23:57
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
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service  = new LfrydjService();
		
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
	 * 
	 * @描述: 查看详细信息（单条）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-27 下午03:49:25
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
	public ActionForward viewLfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LfrydjForm model = (LfrydjForm) form;
		LfrydjService service = new LfrydjService();
		
		String lfrdjid = model.getLfrdjid();
		
		if(StringUtils.isNotBlank(lfrdjid)){

			HashMap<String ,String> data = service.getLfrydjxx(lfrdjid);
			request.setAttribute("lfrydjxx", data);
		}
		
		String path = "gygl_lfrydj.do?method=viewLfxx";
		request.setAttribute("path", path);
		return mapping.findForward("viewLfxx");
	}
	
	/**
	 * 
	 * @描述:在校生详细信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-2 下午02:38:12
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
	public ActionForward xsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LfrydjForm model = (LfrydjForm) form;
		LfrydjDao dao = new LfrydjDao();
		String pk = URLDecoder.decode(URLDecoder.decode(request.getParameter("pk"),"UTF-8"),"UTF-8");
		String userType = request.getParameter("userType");
		if (QUERY.equals(model.getType())){
			//查询
			List<HashMap<String,String>> resultList = dao.getXsxx(model,pk,userType);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "gygl_lfrydj.do?method=xsxxView";
		request.setAttribute("path", path);
		request.setAttribute("pk", pk);
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xsxxView");
	}
	
}

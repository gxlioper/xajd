/**
 * @部门:学工产品事业部
 * @日期：2018-5-8 下午02:18:03 
 */  
package xsgzgl.gygl.rcjc.xswsf;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-卫生检查-学生卫生分（苏州卫生职业技术学院）
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-5-8 下午02:18:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XswsfAction extends SuperAction<XswsfForm,XswsfService>  {
	private XswsfService service = new XswsfService();
	private static final String url = "gyglnew_xswsf_xswsfgl.do";
	
	
	public ActionForward xswsfManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
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
		// 默认高级查询条件
		/*SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		request.setAttribute("searchTj", searchModel);*/
		String path = "gyglnew_xswsf_xswsfgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xswsfManage");
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xswsfEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm myForm = (XswsfForm) form;
		HashMap<String, String> map = service.getXswsfById(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(map));
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
		request.setAttribute("jbxx", xsjbxx);
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_xswsf");
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", map);
		request.setAttribute("lrrxm",Base.getJsxmForzgh(map.get("lrr")));
		request.setAttribute("bz",map.get("bz"));
		
		return mapping.findForward("xswsjcEdit");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xswsfSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		boolean result =service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printWmqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		List<HashMap<String , String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		return mapping.findForward("printWmqs");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXsmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		List<HashMap<String,String>> xsmcList = service.getWmqsxsMd(model.getXn(),model.getXq());
		User user = getUser(request);
		if(xsmcList.size() > 0){
			boolean result =service.insertXsmc(xsmcList,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey, result));
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("message", model.getXn()+"学年"+model.getXq()+"学期"+"没有学生获得文明寝室");
			map.put("success", String.valueOf(false));
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
		}
		
		return null;
	}
	
	/**
	 * @描述：导出
	 * @作者：lgx[工号:1553]
	 * @日期：2018年5月8日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页
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
	public ActionForward viewXswsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm myForm = (XswsfForm) form;
		HashMap<String, String> map = service.getXswsfById(myForm);
		//加载学生基本信息
		if (!StringUtil.isNull(map.get("xh"))) {
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_xswsf");
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", map);
		return mapping.findForward("xswsjcView");
	}
	
}

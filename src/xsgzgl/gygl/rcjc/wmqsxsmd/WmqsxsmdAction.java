/**
 * @部门:学工产品事业部
 * @日期：2018-5-10 下午03:35:48 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import xsgzgl.gygl.rcjc.xswsf.XswsfService;

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
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-5-10 下午03:35:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WmqsxsmdAction  extends SuperAction<WmqsxsmdForm,WmqsxsmdService> {
	private WmqsxsmdService service = new WmqsxsmdService();
	private static final String url = "gyglnew_wmqsxsmd_wmqsxsmdgl.do";
	
	
	public ActionForward wmqsxsmdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm model = (WmqsxsmdForm) form;
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
		String path = "gyglnew_wmqsxsmd_wmqsxsmdgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wmqsxsmdManage");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wmqsxsmdAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm model = (WmqsxsmdForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//XswsfService xswsfService = new XswsfService();
		List<HashMap<String , String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrsj = df.format(new Date());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setLrr(user.getUserName());
		model.setLrsj(lrsj);
		request.setAttribute("lrsj", lrsj);
		request.setAttribute("lrrxm", user.getRealName());
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_wmqsxsmd");
		String path = "gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("wmqsxsAdd");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wmqsxsmdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm myForm = (WmqsxsmdForm) form;
		WmqsxsmdForm model  = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		User user = getUser(request);
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_wmqsxsmd");
		
		request.setAttribute("jbxxList", jbxxList);
		XswsfService xswsfService = new XswsfService();
		List<HashMap<String , String>> xnList = xswsfService.getRcXnList();
		request.setAttribute("xnList", xnList);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrsj = df.format(new Date());
		model.setLrr(user.getUserName());
		model.setLrsj(lrsj);
		request.setAttribute("lrrxm", user.getRealName());
		request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
		request.setAttribute("model", model);
		return mapping.findForward("wmqsxsEdit");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wmqsxsmdSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm model = (WmqsxsmdForm) form;
		model.setSjly("1");
		String type = request.getParameter("type");
		boolean result = false;
		if("add".equals(type)){
			String count = service.countXs(model);
			if(!"0".equals(count)){
				Map<String,String> map = new HashMap<String,String>();
				map.put("message", "该学生在"+model.getXn()+"学年"+model.getXq()+"学期"+"已获得文明寝室");
				map.put("success", String.valueOf(result));
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			}
			result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey, result));
		}
		if("edit".equals(type)){
			result =service.runUpdate(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey, result));
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward viewWmqsxsmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm myForm = (WmqsxsmdForm) form;
		WmqsxsmdForm model  = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//加载学生基本信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("gyglnew_wmqsxsmd");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		request.setAttribute("lrrxm",Base.getJsxmForzgh(model.getLrr()));
		request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
		return mapping.findForward("wmqsxsView");
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
		WmqsxsmdForm model = (WmqsxsmdForm) form;
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
	
}

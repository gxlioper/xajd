/**
 * @部门:学工产品事业部
 * @日期：2016-9-5 上午10:25:03 
 */  
package xsgzgl.gygl.ydgl;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.utils.String.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.ldgl.LdglModel;
import xsgzgl.gygl.ldgl.LdglServices;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 用电管理模块
 * @类功能描述: 用电管理的增删改查导入导出方法等
 * @作者： caopei[工号:1352]
 * @时间： 2016-9-5 上午10:25:03 
 */
public class YdxxAction extends SuperAction<YdxxForm,YdxxService>{
	private static final String url = "ydxxgl.do";//数据库里的
	YdxxService service = new YdxxService();
	/**
	 * 
	 * @描述:查询操作
	 * @日期：2016-9-5下午10:44:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward getYdlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm model = (YdxxForm)form;
		request.setAttribute("path", url);
		model.setPath(url);
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
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYdlist");
	}
	/**
	 * 
	 * @描述:增加
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-5 下午03:20:59
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	
	public ActionForward ydxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm myForm = (YdxxForm) form;
		if(StringUtils.isNotNull(myForm.getBz())){
			   myForm.setBz(URLDecoder.decode((URLDecoder.decode(myForm.getBz(),"UTF-8")),"UTF-8"));
			}
		User user = getUser(request);
		myForm.setJlr(user.getUserName());
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = service.isExistQysj(myForm);
	    	if (!isExist) {
	    		boolean flag = service.saveDataYdxx(myForm);
	        	String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.GYGL_YDGL_ADD_EXIST));
	    	}
				return null;
		}
		//得到登陆用户权限内的楼栋列表
		List<HashMap<String, String>> ldlist =  service.getLdxxList(myForm);
		request.setAttribute("ldList",ldlist);
		request.setAttribute("path", url);
		return mapping.findForward("ydxxAdd");
	}
	public ActionForward getCws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String cws =(String) service.getCwhForQs(lddm, qsh);
		Map<String,String> map = new HashMap<String, String>();
		map.put("cws", cws);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	/**
	 * @描述:单个寝室查看
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 下午04:32:03
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
	public ActionForward ydxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm model = (YdxxForm) form;
		YdxxForm myForm = service.getModel(model);
		String ldqsxx = myForm.getLddm()+myForm.getQsh();
		Map<String, String> rs = service.getQsForPk(ldqsxx);// 寝室详细信息
		List<String[]> list = service.getCwForQs1(ldqsxx);//获取寝室学生信息
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		request.setAttribute("ydyf", myForm.getYdyf());
		request.setAttribute("lddm", myForm.getLddm());
		request.setAttribute("qsh", myForm.getQsh());
		request.setAttribute("ds", myForm.getDs());
		request.setAttribute("df", myForm.getDf());
		request.setAttribute("dfye", myForm.getDfye());
		request.setAttribute("bz", myForm.getBz());
		return mapping.findForward("ydxxView");
	}
	/**
	 * @描述:跟据页面寝室号，联动取床位，学生信息
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 上午08:37:50
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
	public ActionForward getCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ldqsxx = request.getParameter("ldqsxx");//lddm+qsh
		Map<String, String> rs = service.getQsForPk(ldqsxx);// 寝室详细信息
		List<HashMap<String, String>> list = service.getCwForQs(ldqsxx);//获取寝室学生信息
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @描述:修改界面的信息
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 上午11:52:41
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
	public ActionForward ydxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm model = (YdxxForm) form;
		YdxxForm myForm = service.getModel(model);
		User user = getUser(request);
		myForm.setJlr(user.getUserName());
		request.setAttribute("ydxxid", myForm.getYdxxid());
		String ldqsxx = myForm.getLddm()+myForm.getQsh();
		// 寝室详细信息
		Map<String, String> rs = service.getQsForPk(ldqsxx);
		//获取寝室学生信息
		List<String[]> list = service.getCwForQs1(ldqsxx);

		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
	//--------------------------------------------------------------------	
		//得到登陆用户权限内的楼栋列表
		List<HashMap<String, String>> rs1 =  service.getLdxxList(myForm);
		request.setAttribute("ldList",rs1);
		request.setAttribute("ydyf", myForm.getYdyf());
		request.setAttribute("lddm", myForm.getLddm());
		request.setAttribute("qsh", myForm.getQsh());
		request.setAttribute("ds", myForm.getDs());
		request.setAttribute("df", myForm.getDf());
		request.setAttribute("dfye", myForm.getDfye());
		request.setAttribute("bz", myForm.getBz());
		return mapping.findForward("ydxxUpdate");
		
	}
	
	/**
	 * @描述:修改
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 下午02:35:01
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ydxxUpdatesv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm myForm = (YdxxForm) form;
		if(StringUtils.isNotNull(myForm.getBz())){
			   myForm.setBz(URLDecoder.decode((URLDecoder.decode(myForm.getBz(),"UTF-8")),"UTF-8"));
			}
		User user = getUser(request);
		myForm.setJlr(user.getUserName());
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
				boolean flag = service.updateDataYdxx(myForm);
				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("path", url);
		return mapping.findForward("getYdlist");
		
	}
	
	/**
	 * @描述:删除
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 下午02:45:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	
	public ActionForward ydxxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * @描述:导出
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 下午04:03:42
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
		YdxxForm model=(YdxxForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
	 * @描述:加载楼栋信息
	 * @作者：caopei[工号：1352]
	 * @日期：2016-9-6 下午05:01:48
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
	public ActionForward loadLdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		LdglServices service = new LdglServices();
		LdglModel model = new LdglModel();
		model.setLddm(lddm);
		Map<String, String> map = service.getLdxxOne(model);
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
	
	
	
	/**
	 * 获取寝室号通过楼栋代码，为ajax调用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQshForLddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		List<HashMap<String, String>> list = service.getQshForLd(lddm, ch);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
}

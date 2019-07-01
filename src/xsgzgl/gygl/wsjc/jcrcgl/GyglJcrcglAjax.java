package xsgzgl.gygl.wsjc.jcrcgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.wsjc.fslr.KflrService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyglJcrcglAjax extends BasicAction {
	
	/**
	 * 咨询师查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		service.initJcrcglManage(rForm, request);
		myForm.getSearchModel().setPath("gyglnew_wsjc_jcrcgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getJcrcglList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}

	
	/**
	 *  检查日程管理 自定义设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ActionForward jcrcglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {			
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm model = (GyglJcrcglForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		List<HashMap<String,String>> resultList =   service.getJcrcglExportList(model,user);				
		
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
	 * 新增咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问公寓管理-卫生检查-检查日程管理-增加PK:{guid},XN:{xn},XQ:{xq},MC:{mc},JCLX:{jclx},KSSJ:{kssj},JSSJ:{jssj}")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		// 消息信息
		String message = "";
		// 保存数据方法
		boolean flag = false;
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		//苏州卫生职业技术学院
		if("12688".equals(Base.xxdm)){
			//生成卫生分检查日程时，个性化生成两个检查日程，以个性化字段做为区分，一个为校级检查日程，一个为院级检查日程
			service.initSave(model, request);
			HashMap<String, String> map = model.getValueMap();
			map.put("pfjb", "xj");
			model.setValueMap(map);
			flag = service.saveInfo(model);
			
			service.initSave(model, request);
			map.put("pfjb", "yj");
			model.setValueMap(map);
			flag = service.saveInfo(model);
		}else{
			service.initSave(model, request);
			flag = service.saveInfo(model);
		}
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 修改咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		// 消息信息
		String message = "";
		// 保存数据方法
		boolean flag = false;
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		service.initModi(model, request);
		flag = service.updateInfo(model);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 日程名称不可重复的检查
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkMc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		boolean flag = false;
		String Mc = service.unicode2Gbk(request.getParameter("mc"));
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		service.initModi(model, request);
		flag = service.findInfo(model, Mc);
		if (!"".equals(Mc)) {
			if (flag == true) {
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print("名称已存在！");
			} else {
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print("名称可用！");
			}
		}
		return null;
	}

	/**
	 * 起止时间不可重叠的检查
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkQzsj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		String mess = "";
		String Xn = service.unicode2Gbk(request.getParameter("xn"));
		String Xq = service.unicode2Gbk(request.getParameter("xq"));
		String Kssj = service.unicode2Gbk(request.getParameter("kssj"));
		String Jssj = service.unicode2Gbk(request.getParameter("jssj"));
		String jclx = service.unicode2Gbk(request.getParameter("jclx"));
		
		String pkValue = service.unicode2Gbk(request.getParameter("pkValue"));
		model.setPkValue(new String[]{pkValue});
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		service.initModi(model, request);
		mess = service.findQzsj(model, Kssj, Jssj, Xn, Xq, jclx);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(mess);
		return null;
	}

	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问公寓管理-卫生检查-检查日程管理-删除PK:{guid}")
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		String[] pkValue = pkStr.split("!!array!!");
		// 消息信息
		String message = "";
		// 保存数据方法
		boolean flag = false;
		// --------------表名------------
		model.setTableName("xg_gygl_new_wsjc_jcrcb");
		// 主键
		model.setPkValue(pkValue);
		// 批量删除
		if(Base.xxdm.equals("33333")){
			KflrService kflrService = TransactionControlProxy.newProxy(new KflrService());
			flag = kflrService.plscJcrc(pkValue);
		}else{
			flag = service.batchDelete(model); 
		}
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
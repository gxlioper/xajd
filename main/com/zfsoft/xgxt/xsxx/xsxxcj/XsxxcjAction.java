/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午03:59:30 
 */  
package com.zfsoft.xgxt.xsxx.xsxxcj;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(学生信息采集--天津职业大学) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午03:59:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxcjAction extends SuperAction {
	
	private static final String url = "xsxx_gygl_xsxxcj.do?method=Xsxxcj";
	
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList")
	public ActionForward cxXsxxcjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		if (QUERY.equals(model.getType())){
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
		
		String path = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxXsxxcjList");
	}
	
	
	/**
	 * 
	 * @描述:TODO(增加学生采集信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午07:06:59
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息采集（天津职大）-教师采集学生信息-增加")
	public ActionForward addXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		XsxxYnysService ynysService = new XsxxYnysService();
		request.setAttribute("ssList", ynysService.getSsList());//省列表
		request.setAttribute("hkszdshiList", ynysService.getShiList( "9999999" ).get("shiList"));//市列表
		request.setAttribute("hkszdxianList",  ynysService.getShiListNew("9999999").get("xianList"));//县列表
		
		request.setAttribute("jtdzshiList", ynysService.getShiList( "9999999" ).get("shiList"));//市列表
		request.setAttribute("jtdzxianList",  ynysService.getShiListNew("9999999").get("xianList"));//县列表
		
		request.setAttribute("sydshiList", ynysService.getShiList( "9999999" ).get("shiList"));//市列表
		request.setAttribute("sydxianList",  ynysService.getShiListNew("9999999").get("xianList"));//县列表
		
		request.setAttribute("jgshiList", ynysService.getShiList( "9999999" ).get("shiList"));//市列表
		request.setAttribute("jgxianList",  ynysService.getShiListNew("9999999").get("xianList"));//县列表
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> mzList=dao.getMzList();
		mzList.remove(0);
		mzList.remove(0);
		request.setAttribute("mzList", mzList);
		if (SAVE.equals(model.getType())){
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "xsxx_gygl_xsxxcj.do?method=addXsxxcj";
		request.setAttribute("path", path);
		return mapping.findForward("addXsxxcj");
	}
	
	/**
	 * 
	 * @描述:TODO(查看学生采集信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午07:06:28
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList")
	public ActionForward viewXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		XsxxcjForm model=service.getModel(myForm);
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("viewXsxxcj");
	}
	/**
	 * 
	 * @描述:TODO(修改学生采集信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午07:07:26
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息采集（天津职大）-教师采集学生信息-修改PK:{xh}")
	public ActionForward updateXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		XsxxcjForm model=service.getModel(myForm);
		XsxxYnysService ynysService = new XsxxYnysService();
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("ssList", ynysService.getSsList());//省列表
        request.setAttribute("hkszdshiList", ynysService.getShiList(model.getHkszdshen() == null ? "9999999" : model.getHkszdshen()).get("shiList"));//市列表
		request.setAttribute("hkszdxianList",  ynysService.getShiListNew(model.getHkszdshi() == null ? ( model.getHkszdshen() != null ? model.getHkszdshen().substring(0, 2) :"9999999"): model.getHkszdshi()).get("xianList"));//县列表

        request.setAttribute("jtdzshiList", ynysService.getShiList(model.getJtdzshen() == null ? "9999999" : model.getJtdzshen()).get("shiList"));//市列表
		request.setAttribute("jtdzxianList",  ynysService.getShiListNew(model.getJtdzshi() == null ? ( model.getJtdzshen() != null ? model.getJtdzshen().substring(0, 2) :"9999999"): model.getJtdzshi()).get("xianList"));//县列表

        request.setAttribute("sydshiList", ynysService.getShiList(model.getSydshen() == null ? "9999999" : model.getSydshen()).get("shiList"));//市列表
		request.setAttribute("sydxianList",  ynysService.getShiListNew(model.getSydshi() == null ? ( model.getSydshen() != null ? model.getSydshen().substring(0, 2) :"9999999"): model.getSydshi()).get("xianList"));//县列表

        request.setAttribute("jgshiList", ynysService.getShiList(model.getJgshen() == null ? "9999999" : model.getJgshen()).get("shiList"));//市列表
		request.setAttribute("jgxianList",  ynysService.getShiListNew(model.getJgshi() == null ? ( model.getJgshen() != null ? model.getJgshen().substring(0, 2) :"9999999"): model.getJgshi()).get("xianList"));//县列表
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> mzList=dao.getMzList();
		mzList.remove(0);
		mzList.remove(0);
		request.setAttribute("mzList", mzList);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXsxxcj");
	}
	
	/**
	 * 
	 * @描述:TODO(删除学生采集信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午07:07:54
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生信息采集（天津职大）-教师采集学生信息-删除VALUES:{values}")
	public ActionForward delXsxxcj (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XsxxcjForm myForm = (XsxxcjForm) form;
		XsxxcjService service = new XsxxcjService();
		
		String values = request.getParameter("values");
		
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @描述:TODO(导出学生数据)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午07:08:19
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
	@SystemAuth(url = "xsxx_gygl_xsxxcj.do?method=cxXsxxcjList",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		
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
	 * @描述:TODO(获取选择学生list)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午06:18:53
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
	
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getXsPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		
		String path = "xsxx_xsgl.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}
	/**
	 * 
	 * @描述:TODO(导出学生基本信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 上午10:22:29
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
	
	public ActionForward exportXsjbxxtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		//创建导出文件
		File file = new File( tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis())+".xls");
		file.setWritable(true);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<String[]> resultList = service.getXsjbxxtzList(model,user);//查询出所有记录，不分页
		String[] title=new String[]{"学生总人数","在校生人数","顶岗实习学生数","住宿学生数","入党申请人学生数","少数民族学生数"};//表头数据
		File file1=service.exportXsjbxxtz(response,resultList,file,title);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(导出学生困难信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 上午10:22:29
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
	
	public ActionForward exportXsknxxtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm model=(XsxxcjForm) form;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		//创建导出文件
		File file = new File( tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis())+".xls");
		file.setWritable(true);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<String[]> resultList = service.exportXsknxxtz(model,user);//查询出所有记录，不分页
		String[] title=new String[]{"家庭困扰学生数","心理困扰学生数","经济困难学生数","学习困难学生数","身体疾病学生数","其他困扰学生数（晚归、旷课等）","宗教信仰学生数"};//表头数据
		File file1=service.exportXsjbxxtz(response,resultList,file,title);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述:(学生信息采集)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-8 下午06:17:06
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
	public ActionForward Xsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		User user = getUser(request);
		String userType=user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
		String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			String xh=user.getUserName();
			myForm.setXh(xh);
			XsxxcjForm model=service.getModel(myForm);
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			XsxxYnysService ynysService = new XsxxYnysService();
			request.setAttribute("ssList", ynysService.getSsList());//省列表
	        request.setAttribute("hkszdshiList", ynysService.getShiList(model.getHkszdshen() == null ? "9999999" : model.getHkszdshen()).get("shiList"));//市列表
			request.setAttribute("hkszdxianList",  ynysService.getShiListNew(model.getHkszdshi() == null ? ( model.getHkszdshen() != null ? model.getHkszdshen().substring(0, 2) :"9999999"): model.getHkszdshi()).get("xianList"));//县列表

	        request.setAttribute("jtdzshiList", ynysService.getShiList(model.getJtdzshen() == null ? "9999999" : model.getJtdzshen()).get("shiList"));//市列表
			request.setAttribute("jtdzxianList",  ynysService.getShiListNew(model.getJtdzshi() == null ? ( model.getJtdzshen() != null ? model.getJtdzshen().substring(0, 2) :"9999999"): model.getJtdzshi()).get("xianList"));//县列表

	        request.setAttribute("sydshiList", ynysService.getShiList(model.getSydshen() == null ? "9999999" : model.getSydshen()).get("shiList"));//市列表
			request.setAttribute("sydxianList",  ynysService.getShiListNew(model.getSydshi() == null ? ( model.getSydshen() != null ? model.getSydshen().substring(0, 2) :"9999999"): model.getSydshi()).get("xianList"));//县列表

	        request.setAttribute("jgshiList", ynysService.getShiList(model.getJgshen() == null ? "9999999" : model.getJgshen()).get("shiList"));//市列表
			request.setAttribute("jgxianList",  ynysService.getShiListNew(model.getJgshi() == null ? ( model.getJgshen() != null ? model.getJgshen().substring(0, 2) :"9999999"): model.getJgshi()).get("xianList"));//县列表
			DAO dao=DAO.getInstance();
			List<HashMap<String, String>> mzList=dao.getMzList();
			mzList.remove(0);
			mzList.remove(0);
			request.setAttribute("mzList", mzList);
		}
		return mapping.findForward("Xsxxcj");
	}
	/**
	 * 
	 * @描述:(保存信息填写信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-8 下午06:48:34
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
	@SystemLog(description="访问学生信息-学生信息采集（天津职大）-教师采集学生信息-保存XH:{xh}")
	public ActionForward saveXsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxcjService service=new XsxxcjService();
		XsxxcjForm myForm=(XsxxcjForm) form;
		XsxxcjForm model=service.getModel(myForm);
		boolean result;
		if(model.getXh()!=null&&!"".equalsIgnoreCase(model.getXh())){
			result = service.runUpdate(myForm);
		}else{
			result=service.runInsert(myForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
		

}

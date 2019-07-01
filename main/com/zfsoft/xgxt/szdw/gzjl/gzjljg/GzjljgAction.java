/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:31:00 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjljg;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqService;
import com.zfsoft.xgxt.szdw.gzjl.lbgl.GzjlLbglService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:31:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjljgAction extends SuperAction<GzjljgForm, GzjljgService> {
	private final String GZJL="gzjl";
	private GzjljgService service = new GzjljgService();
	private GzjlLbglService gzlbService = new GzjlLbglService();
	
	private static final String url = "gzjl_gzjljg.do";

	/**
	 * 工作记录结果结果列表
	 */
	@SystemAuth(url = url)
	public ActionForward gzjljgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjljgForm model = (GzjljgForm) form;
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
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "gzjl_gzjljg.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzjljgList");
	}
	/**
	 * 工作记录结果结果增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问职工工作记录信息-工作记录管理-工作记录结果-增加")
	public ActionForward gzjljgZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjljgForm model = (GzjljgForm) form;
		User user = getUser(request);
		//浙江树人学院个性化需求
		if("11842".equals(Base.xxdm)) {
			model.setZgh(user.getUserName());
			GzjlsqService gzjlsqService = new GzjlsqService();
			request.setAttribute("lksList", gzjlsqService.getLks());
			model.setLks("7");
		}
		if (!StringUtil.isNull(model.getZgh())) {
			HashMap<String, String> xsjbxx = service.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		List<HashMap<String, String>> gzlbList = gzlbService.getGzjllbList();
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzlbList", gzlbList);
		request.setAttribute("jlsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		String path = "gzjljg.do?method=gzjljgZj";
		request.setAttribute("path", path);
		return mapping.findForward("gzjljgZj");
	}
	/**
	 * 考核结果查看
	 */
	@SystemAuth(url = url)
	public ActionForward gzjljgCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjljgForm myForm = (GzjljgForm) form;
		GzjljgForm model = service.getModel(myForm);
		//树人大学取出谈话对象
		if("11842".equals(Base.xxdm)){
			if(StringUtils.isNotNull(model.getXh())){
				String[] xhArr = model.getXh().split(",");
				List<HashMap<String,String>> thdxList = service.getThdxList(xhArr);
				request.setAttribute("thdxList", thdxList);
			}
			
		}
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
	
			HashMap<String, String> jsjbxx = service.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzjljg", StringUtils.formatData(model));
		return mapping.findForward("gzjljgCk");
	}
	/**
	 * 考核结果保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-工作记录管理-工作记录结果-增加或修改保存ZGH:{zgh},JGID:{jgid},GZSJ:{gzsj},LBDM:{lbdm},GZZY:{gzzy}")
	public ActionForward savegzjljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjljgForm model = (GzjljgForm) form;
		
		boolean result = false;
		String message=null;
//		// 判断同一时间是否有填写记录
//		boolean isExist = service.checkExistForSave(model);
//		if (isExist) {
//			message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzsj());;
//			response.getWriter().print(getJsonMessage(message));
//			return null;
//		}
		//浙江树人学院个性化
		if("11842".equals(Base.xxdm)){
			String objStr = request.getParameter("objStr");
			if(null != objStr && !"".equals(objStr)) {
				model.setXh(objStr.substring(0, objStr.length()-1));
			}else{
				model.setXh("");
			}			
		}
		result = service.savegzjljg(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 工作记录结果结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问职工工作记录信息-工作记录管理-工作记录结果-修改ZGH:{zgh}")
	public ActionForward gzjljgXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjljgForm myForm = (GzjljgForm) form;
		GzjljgForm model = service.getModel(myForm);
		//树人大学个性化配置
		if("11842".equals(Base.xxdm)){
			GzjlsqService gzjlsqService = new GzjlsqService();
			request.setAttribute("lksList", gzjlsqService.getLks());
			if(StringUtils.isNotNull(model.getXh())){
				String[] xhArr = model.getXh().split(",");
				List<HashMap<String,String>> thdxList = service.getThdxList(xhArr);
				request.setAttribute("thdxList", thdxList);
			}
		}
		if(null!=model){
			model.setLbbh(model.getLbdm());
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			HashMap<String, String> xsjbxx = service.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		List<HashMap<String, String>> gzlbList = gzlbService.getGzjllbList();
		request.setAttribute("jlsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzlbList", gzlbList);
		request.setAttribute("gzjljg", StringUtils.formatData(model));
		String path = "gzjljg.do?method=gzjljgXg";
		request.setAttribute("path", path);
		return mapping.findForward("gzjljgXg");
	}
	/**
	 * 工作记录结果结果删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问职工工作记录信息-工作记录管理-工作记录结果-删除jgid:{values}")
	public ActionForward delGzjljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GzjljgService service = new GzjljgService();
		
		//获得id
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
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
	 * 工作记录结果结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzjljgForm model = (GzjljgForm) form;
		GzjljgService service = new GzjljgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

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
	/**
	 * 
	 * @描述:显示教师信息列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午02:47:26
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
	public ActionForward showTeachers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GzjljgForm model = (GzjljgForm) form;
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getJsxxList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String path = "gzjljg.do?method=showTeachers";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
			return mapping.findForward("showTeachers");
		}
	
	@SystemAuth(url = url)
	public ActionForward gzjltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GzjljgForm exporModel = (GzjljgForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		String type = request.getParameter("type");
		List<HashMap<String,String>> resultList = service.getGzjltjList(exporModel,user);//查询出所有记录，不分页
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		File file =service.gzjltjExport(resultList, response.getOutputStream(), user,type,exporModel);
		FileUtil.outputFile(response, file);
		return null;
	}
	/**
	 * 
	 * @描述:学生事务登记表打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-3 下午01:41:54
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
	public ActionForward printGzjlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzjljgForm myForm = (GzjljgForm) form;
			String guids[]=request.getParameter("guid").split(",");
			//String xhs[]=myForm.getXh().split(",");
			if(null!=guids&&guids.length==1){//一条数据
				myForm.setJgid(guids[0]);
				File file=service.print(myForm);
				FileUtil.outputWord(response, file);
			}else{//多条数据
				List<File> files = new ArrayList<File>();
				for(String guid:guids){
					myForm.setJgid(guid);
					File file=service.print(myForm);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	public ActionForward gzjlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GzjljgForm model = (GzjljgForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		String kssj = request.getParameter("kssj");
		String jssj = request.getParameter("jssj");
		String type = request.getParameter("type");
		model.setType(type);
		if("z".equals(type)){
			List<HashMap<String, String>> zghList = service.getZghList(model,user);//查询时间内的职工号
			if(zghList.size()  > 1){
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = zghList.size() ; i < n ; i++){
					model.setZgh(zghList.get(i).get("zgh"));
					model.setXm(zghList.get(i).get("xm"));
					model.setXymc(zghList.get(i).get("xymc"));
					File file = getword(model,user,kssj,jssj);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else if(zghList.size() == 1){
				model.setZgh(zghList.get(0).get("zgh"));
				model.setXymc(zghList.get(0).get("xymc"));
				model.setXm(zghList.get(0).get("xm"));
				File wordFile = getword(model,user,kssj,jssj);
				FileUtil.outputWord(response, wordFile);
			}else{
				File wordFile = getword(model,user,kssj,jssj);
				FileUtil.outputWord(response, wordFile);
			}
		}else if("y".equals(type)){
			model.setZgh(user.getUserName());
			model.setXymc(user.getUserDepName());
			model.setXm(user.getRealName());
			File wordFile = getword(model,user,kssj,jssj);
			FileUtil.outputWord(response, wordFile);
		}
		
		return null;
	}
	
	private File getword(GzjljgForm model,User user,String kssj,String jssj)
			throws Exception {
		String type = model.getType();
		String	zgh =  model.getZgh();
		SearchModel searchModel = model.getSearchModel();
		String kssjmonth = kssj.substring(4, 6);
		String kssjday = kssj.substring(6, 8);
		String jssjmonth = jssj.substring(4, 6);
		String jssjday = jssj.substring(6, 8);
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String,String>> xsmtList = service.getGzjlListByZgh(zgh,"学生面谈",searchModel,user);
		List<HashMap<String,String>> lxjzList = service.getGzjlListByZgh(zgh,"联系家长",searchModel,user);
		List<HashMap<String,String>> qszfList = service.getGzjlListByZgh(zgh,"寝室走访",searchModel,user);
		List<HashMap<String,String>> tsqkList = service.getGzjlListByZgh(zgh,"特殊情况",searchModel,user);
		List<HashMap<String,String>> gzydList = service.getGzjlListByZgh(zgh,null,searchModel,user);
		for (HashMap<String, String> hashMap : xsmtList) {
			String temp = hashMap.get("gzzy");
			hashMap.put("gzzy", HtmlUtil.xmlZy(temp));
		}
		for (HashMap<String, String> hashMap : lxjzList) {
			String temp = hashMap.get("gzzy");
			hashMap.put("gzzy", HtmlUtil.xmlZy(temp));
		}
		for (HashMap<String, String> hashMap : qszfList) {
			String temp = hashMap.get("gzzy");
			hashMap.put("gzzy", HtmlUtil.xmlZy(temp));
		}
		for (HashMap<String, String> hashMap : tsqkList) {
			String temp = hashMap.get("gzzy");
			hashMap.put("gzzy", HtmlUtil.xmlZy(temp));
		}
		for (HashMap<String, String> hashMap : gzydList) {
			String temp = hashMap.get("gzzy");
			hashMap.put("gzzy", HtmlUtil.xmlZy(temp));
		}
		data.put("xsmtList", xsmtList);
		data.put("lxjzList", lxjzList);
		data.put("qszfList", qszfList);
		data.put("gzydList", gzydList);
		data.put("tsqkList", tsqkList);
		data.put("xn", Base.currXn);
		String xq = "";
		if("01".equals(Base.currXq)){
			xq = "一";
		}else{
			xq = "二";
		}
		data.put("xq",xq);
		data.put("xm", model.getXm());
		data.put("xymc", model.getXymc());
		data.put("kssjmonth",kssjmonth);
		data.put("kssjday",kssjday);
		data.put("jssjmonth",jssjmonth);
		data.put("jssjday",jssjday);
		String filename = "";
		if("z".equals(type)){
			filename = "gzjib_z_11842.xml";
		}else if("y".equals(type)){
			filename = "gzjib_y_11842.xml";
		}
		
		File file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw",filename,zgh+"_"+new SimpleDateFormat("yyyyMMdd").format(new Date()));
		
		return file;
	}
}

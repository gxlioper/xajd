/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:59:06 
 */  
package xsgzgl.rcsw.wsbz.yy;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhDao;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhForm;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:59:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzYyAction extends SuperAction<WsbzYyForm, WsbzYyService> {
	private final String WSBZYY ="wsbz";
	private WsbzYyService service = new  WsbzYyService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private static final String url = "rcsw_wsbz_yy.do";
	
	public ActionForward getWsbzYyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsbzYyForm model = (WsbzYyForm) form;
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
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "rcsw_wsbz_yy.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	//结果查询
	public ActionForward getWsbzJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsbzYyForm model = (WsbzYyForm) form;
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
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "rcsw_wsbz_jg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcx");
	}
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsbzYyForm model = (WsbzYyForm) form;

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
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm model = (WsbzYyForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(WSBZYY);
		//yyzcList 预约周次List
		List<HashMap<String, String>> yyzcList = service.getYyzcb();
		//yyrqList 预约日期List
		List<HashMap<String, String>> yyrqList = service.getYyrqb();
		request.setAttribute("yyzcList", yyzcList);
		request.setAttribute("yyrqList", yyrqList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("fdmclist", service.getFdmcList());
		request.setAttribute("yyrqday", service.produceHdplDay());
		request.setAttribute("zclist", service.getWeekZc());
		request.setAttribute("sqsj", GetTime.getTimeByFormat(DATE_FORMAT));
		String path = "wsbz_yy.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	public ActionForward udpate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm myForm = (WsbzYyForm) form;
		WsbzYyForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(WSBZYY);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("fdmclist", service.getFdmcList());
		request.setAttribute("yyrqday", service.produceHdplDay());
		//yyzcList 预约周次List
		List<HashMap<String, String>> yyzcList = service.getYyzcb();
		//yyrqList 预约日期List
		List<HashMap<String, String>> yyrqList = service.getYyrqb();
		request.setAttribute("yyzcList", yyzcList);
		request.setAttribute("yyrqList", yyrqList);
		String hdpl = new WsbzDmwhService().getModel(model.getFddm()).getHdpl();
		String yyrq = "";
		if("2".equals(hdpl)){
			//yyrq = service.produceHdplDay();
			yyrq =model.getYyzc();
			//request.setAttribute("yyrqzc", model.getYyrq());
			request.setAttribute("flag", "z");
		}else{
			yyrq = model.getYyrq();
			request.setAttribute("flag", "t");
		}
		request.setAttribute("syrs", service.getSyrs(model.getFddm(), yyrq));
		request.setAttribute("fdmcinfo", service.getFdmcInfo(model.getFddm()));
		request.setAttribute("jg", model);
		
		HashMap<String, String> datamap = service.getFdmcInfo(model.getFddm());
		request.setAttribute("syrs", service.getSyrs(model.getFddm(), model.getYyrq()));
		request.setAttribute("fd", datamap);
		String path = "wsbz_yy.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("yyrq", yyrq);
		request.setAttribute("hdpl", hdpl);
		return mapping.findForward("udpae");
	}
	
	public ActionForward saveData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm myForm = (WsbzYyForm) form;
		WsbzDmwhForm dmwhform = new WsbzDmwhService().getModel(myForm.getFddm());
		User user = getUser(request);
		String message = "";
		boolean flag = true;
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		myForm.setSqly(StringEscapeUtils.escapeHtml4(myForm.getSqly()));
		if("add".equals(myForm.getType())){
			if(!service.isNotOverRssx(myForm.getFddm(), dmwhform.getRs(), myForm.getYyrq(),dmwhform.getHdpl())){
				 message = "超出最大人数上限"+dmwhform.getRs()+"!";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			flag = service.isEveryXhTwoRecode(myForm.getXh());
			if(!flag){
				 message = "每个学生只能预约两次！";
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}
			if(!service.isNotSameTwo(myForm.getFddm(), myForm.getYyrq(),myForm.getXh())){
					 message = "您已经在该预约日期预约过该分队！";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
			}else{
				if(StringUtils.isNull(myForm.getYyrq()) || myForm.getYyrq().trim().length() == 0){
					 message = "预约时间不能为空！";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				}else {//
					boolean isflag = service.isExist(myForm.getYyrq());;
					if (!isflag) {
						message = "预约时间不存在！";
						 response.getWriter().print(getJsonMessage(message));
						 return null;
					}
				}
				if(myForm.getYyrq().indexOf("至") != -1){//myForm.getYyrq().indexOf("至") != -1
					myForm.setYyzc(myForm.getYyrq());
					myForm.setYyrq(null);
				}
				flag = service.runInsert(myForm);
			}
		    
		}else{
			//查询数据库原来保存的数据
			WsbzYyForm dbform = service.getModel(myForm.getSqid());
			WsbzDmwhForm dbdmform = new WsbzDmwhDao().getModel(dbform.getFddm());
			//结束
			String fddm = dbform.getFddm();
			String yyrq = "1".equals(dbdmform.getHdpl()) ? dbform.getYyrq():dbform.getYyzc();
			if(!fddm.equals(myForm.getFddm()) || !yyrq.equals(myForm.getYyrq())){
				flag = service.isNotOverRssx(myForm.getFddm(), dmwhform.getRs(), myForm.getYyrq(),dmwhform.getHdpl());
				 if(!flag){
					 message = "超出最大人数上限"+dmwhform.getRs()+"!";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				 }
				 if(!service.isNotSameTwo(myForm.getFddm(), myForm.getYyrq(),myForm.getXh())){
					 message = "您已经在该预约日期预约过该分队！";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				}
			     
			}
//			else{
//				flag = service.isNotOverRssx(myForm.getFddm(), dmwhform.getRs(), myForm.getYyrq(),myForm.getXh());
//			}
			if(StringUtils.isNull(myForm.getYyrq()) || myForm.getYyrq().trim().length() == 0){
				 message = "预约时间不能为空！";
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}else {
				boolean isflag = service.isExist(myForm.getYyrq());;
				if (!isflag) {
					message = "预约时间不存在！";
					 response.getWriter().print(getJsonMessage(message));
					 return null;
				}
			}
			if(myForm.getYyrq().indexOf("至") != -1){
				myForm.setYyzc(myForm.getYyrq());
				myForm.setYyrq(null);
				service.updateYyrqdaynull(myForm.getSqid());
			}else{
				service.updateYyzcnull(myForm.getSqid());
			}
			flag = service.runUpdate(myForm);
		}
		 message = flag ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WsbzYyForm myForm = (WsbzYyForm) form;
		WsbzYyForm model = service.getModel(myForm);
		if(null!=model){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(WSBZYY);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", model);
		WsbzDmwhForm dwmwhform =  new WsbzDmwhService().getModel(model.getFddm());
		request.setAttribute("fd",dwmwhform);
		String yyrq = "";
		if(dwmwhform.getHdpl().equals("1")){
			request.setAttribute("yyrq",model.getYyrq());
			yyrq = model.getYyrq();
		}else{
			request.setAttribute("yyrq",model.getYyzc());
			yyrq = model.getYyzc();
		}
		request.setAttribute("syrs", service.getSyrs(model.getFddm(), yyrq));
		return mapping.findForward("ck");
	}
	
	public ActionForward isHaveQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = getUser(request).getUserName();
		String type = request.getParameter("type");
		//预约日期
		String sqsj = request.getParameter("sqsj");
		boolean flag = true;
		//周次和天为活动频率的分开判断，并且只在修改按钮控制
		flag = service.updateYyTimeCheck(sqsj);
		String message = "";
		if(!flag && "update".equals(type)){
			 message = "超过可修改时间！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}else{
			if(type.equals("add")){
				flag = service.isEveryXhTwoRecode(xh);
				if(!flag){
					message = "超过申请次数！";
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				flag = service.isNowDateHaveYyjl(xh);
				if(!flag){
					message = "您今天已有预约申请！";
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				message = "";
			}
			 message = "1";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}
	}
	
	public ActionForward fdmcChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String fddm = request.getParameter("fddm");
		String yyrq = request.getParameter("yyrq");
		HashMap<String, String> datamap = new HashMap<String, String>();
		datamap = service.getFdmcInfo(fddm);
		if("1".equals(datamap.get("hdpl")) || yyrq.indexOf("至") != -1){
			String syrs = service.getSyrs(fddm, yyrq);
			datamap.put("syrs", syrs);
			datamap.put("syrsflag","1" );
		}else{
			String syrs = service.getSyrs(fddm, yyrq);
			datamap.put("syrs", syrs);
			datamap.put("syrsflag","0" );
		}
		JSONObject json = JSONObject.fromObject(datamap); 
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward zcChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String fddm = request.getParameter("fddm");
		String yyrq = request.getParameter("yyrq");
		HashMap<String, String> datamap = new HashMap<String, String>();
		datamap = service.getFdmcInfo(fddm);
		String syrs = service.getSyrs(fddm, yyrq);
		datamap.put("syrs", syrs);
		JSONObject json = JSONObject.fromObject(datamap); 
		response.getWriter().print(json);
		return null;
	}
	/**
	 * 
	 * @描述：删除
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-10 上午09:54:13
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * 
	 * @描述:学生删除权限判断
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-11 上午11:26:01
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
	public ActionForward isHaveQxDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//预约日期
		String sqsj = request.getParameter("sqsj");
		boolean flag = true;
		//周次和天为活动频率的分开判断，并且只在修改按钮控制
		String message = "true";
		flag = service.updateYyTimeCheck(sqsj);
		if(!flag){
			 message = "超过可删除时间！";
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
}

 
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxfxwh;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszForm;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;




/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：lgx[工号:1553]
 * @时间： 2018-4-20 下午04:02:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("unchecked")
public class JqfxFxwhAction extends SuperAction{
	
	//假期返校维护
	private static final String RCSW_JQFXGL_FXWH = "rcsw_jqfxgl_fxwh";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSW_JQFXGL_FXWH);	
	}			
	private static final String url = "rcsw_jqfxgl_fxwh.do";
	
	public static String JQFXWCL = "未处理";
	public static String JQFXWFX = "未返校";
	public static String JQFXYFX = "已返校";
	

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
 	 * @作者：lgx[工号:1553]
 	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward jqfxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();		
	
		if (QUERY.equals(model.getType())) {			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询获取假期维护结果数据
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		//------------------设置高级查询默认值-------------
		SearchModel searchModel= model.getSearchModel();
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj",  searchModel);
		String path = "rcsw_jqfxgl_fxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		
		//-----------------获取基础设置开关关闭与否-------------------
		JqfxJbszService jqfxJbszService = new JqfxJbszService();
		JqfxJbszForm jbszModel = jqfxJbszService.getModel();
		request.setAttribute("jbszModel", jbszModel);
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", "01".equals(Base.currXq)?"春":"秋");	
		request.setAttribute("fxmc", service.getFxmc()); 
		return mapping.findForward("rcswjqfxwh");
	}
	
	

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward addxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			JqfxFxwhForm model = (JqfxFxwhForm) form;
			JqfxFxwhService service = new JqfxFxwhService();
			if (!StringUtil.isNull(model.getXh())) {
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);	
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// 唯一性判断（学号，学年，学期）
				/*boolean isExist = false;
				if (!isExist) {		*/												
					// 添加日常行为结果
					model.setTbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss") );
					boolean result = service.saveJqFxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				/*} else {					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}*/
			}			
			JqfxFxwhForm jqfxJg = service.getModel(model.getId());
			if(jqfxJg != null){
				BeanUtils.copyProperties(StringUtils.formatData(jqfxJg), model);
			}
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"春":"秋");	
			request.setAttribute("fxztmc",  "1".equals(model.getFxzt())?JQFXYFX:JQFXWCL);
			request.setAttribute("tbsj" , GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss") );
			if("0".equals(model.getFxzt())){
				request.setAttribute("fxztmc",JQFXWFX);
			}								
			return mapping.findForward("addxsJqfx");			
	}
	
	
	/**
	 * 
	 * @描述:TODO(学生假期未返校)
 * @作者：lgx[工号:1553]
 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward addxsJqwfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
			JqfxFxwhForm model = (JqfxFxwhForm) form;
			JqfxFxwhService service = new JqfxFxwhService();
			User user = getUser(request);
			if (!StringUtil.isNull(model.getXh())){
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}	
			request.setAttribute("jbxxList", jbxxList);	
			
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// 唯一性判断（学号，学年，学期）
			/*	boolean isExist = false;
				if (!isExist){	
					*/
					boolean result = service.saveJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
					
				/*}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}*/
			}			
			List<HashMap<String, String>> wfxyyList = service.getAllWfxyy();
			request.setAttribute("wfxyyList", wfxyyList);
			//request.setAttribute("rs", StringUtils.formatData(service.getOneFxgljgList(model.getXh())));
			//获取未返校信息记录
			request.setAttribute("wfxxsrs", StringUtils.formatData(service.getOneWfxxwhjgList(model.getXh())));
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"春":"秋");	
			request.setAttribute("fxztmc",  "0".equals(model.getFxzt())?JQFXWFX:JQFXWCL);	
			if("1".equals(model.getFxzt())){
				request.setAttribute("fxztmc",JQFXYFX);	
			}
			
			//如果该学生已经是未返校把未返校原因备注
			if("0".equals(model.getFxzt())||"1".equals(model.getFxzt())){		
				JqfxFxwhForm updateForm = service.getModel(model);
				BeanUtils.copyProperties( StringUtils.formatData(updateForm), model);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(new Date());
			request.setAttribute("nowDate",dateString);
			return mapping.findForward("addxsJqwfx");			
	}
	
	/**
	 * 
	 * @描述:TODO(批量修改学生假期返校维护)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward plxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		
		if (SAVE.equalsIgnoreCase(model.getType())) {			
			// 唯一性判断（学号，学年，学期）
		/*	boolean isExist = false;
			if (!isExist) {	*/				
				//model.setXq("春".equals(model.getXq())?"01":"02");	
				model.setBz( URLDecoder.decode(model.getBz() , "utf-8"));
				boolean result = service.plSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
		/*	} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}*/
		}
		List<HashMap<String, String>> wfxyyList = service.getAllWfxyy();
		request.setAttribute("wfxyyList", wfxyyList);
		
		//String countNum =  (String) request.getAttribute("countNum");
		request.setAttribute("countNum", model.getCountNum()); 		
		return mapping.findForward("addPlxsJqfx");		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward getCountNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		String countNum =  service.getCountNum(model,user);
		
		response.getWriter().print(countNum);
		//request.setAttribute("countNum",countNum); 		
		return null;		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward pldgxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
						
		
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();						
		
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {				
			// 唯一性判断（学号，学年，学期）
			/*boolean isExist = false;
			if (!isExist) {	*/	
				model.setBz( URLDecoder.decode(model.getBz() , "utf-8"));
				boolean result = service.pldgSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
			/*} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}*/
		}
		
		request.setAttribute("countNum", model.getCountNum()); 	
		request.setAttribute("pldgxsJqfx", "1"); 
		return mapping.findForward("addPlxsJqfx");	
		
		
		
		
	}
	
	

	

	
	/**
	 * 
	 * @描述:TODO(查看学生假期返校)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward viewxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			JqfxFxwhForm model = (JqfxFxwhForm) form;
			JqfxFxwhService service = new JqfxFxwhService();
			User user = getUser(request);
			if (!StringUtil.isNull(model.getXh())) {
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}	
			request.setAttribute("jbxxList", jbxxList);	
			//request.setAttribute("rs", service.getOneFxgljgList(model.getXh()));
			//获取未返校信息记录
			Map<String, String> wfxxx = service.getOneWfxxwhjgList(model.getXh());
			request.setAttribute("wfxxsrs", wfxxx);
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"春":"秋");	
			
			if(!"null".equals(model.getId())){
				JqfxFxwhForm updateForm = service.getModel(model);
				String wfxyymc = "";
				if(StringUtil.isNull(updateForm.getWfxyy()))
					wfxyymc = service.getWfxyyMc(updateForm.getWfxyy());
				model.setWfxyy(wfxyymc);
				BeanUtils.copyProperties( StringUtils.formatData(updateForm), model);
			}
			request.setAttribute("fxsj", model.getFxsj());
			request.setAttribute("fxzt", model.getFxzt());
			String sfqdlx = model.getSfqdlx();
			if( "0".equals(model.getFxzt())){
				if(StringUtil.isNull(sfqdlx))  
					sfqdlx="是";
			}else{
				sfqdlx="";
			}
			
			request.setAttribute("sfqdlx",sfqdlx);
			return mapping.findForward("viewxsJqfx");				
	}	
	
	/**
	 * 
	 * @描述:TODO(自定义导出)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
		//ActionForm
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
	 * @描述:TODO(学生假期批量未返校)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午04:02:43 
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
	public ActionForward addplxsJqwfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
			JqfxFxwhForm model = (JqfxFxwhForm) form;						
			JqfxFxwhService service = new JqfxFxwhService();
			User user = getUser(request);					
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// 唯一性判断（学号，学年，学期）
				/*boolean isExist = false;
				if (!isExist){		*/			
					boolean result = service.saveplxsJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				/*}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}*/
			}		
			List<HashMap<String, String>> wfxyyList = service.getAllWfxyy();
			request.setAttribute("wfxyyList", wfxyyList);
			return mapping.findForward("addplxsJqwfx");			
	}

	/**
	 * @描述:报到情况统计表
	 * @作者：lgx [工号：1553]
	 * @日期：2018/6/19 13:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bdqktjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getBdqktjList(model,user);
		HashMap<String,String> hashMap = new HashMap<String, String>();
		
		File file = service.getBdqktjFile(resultList);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}

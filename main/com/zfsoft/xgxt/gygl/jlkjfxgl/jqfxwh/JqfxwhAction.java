/**
 * @部门:学工产品事业部
 * @日期：2016-2-29 上午09:27:42 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;
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
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszForm;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 假期返校维护管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-2-29 上午09:27:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("unchecked")
public class JqfxwhAction extends SuperAction{
	
	//假期返校维护
	private static final String JLKJXYJQFXWH = "jlkjxyjqfxwh";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(JLKJXYJQFXWH);	
	}			
	private static final String url = "jlkjxyjqfxwh.do";
	
	public static String JQFXWCL = "未处理";
	public static String JQFXWFX = "未返校";
	public static String JQFXYFX = "已返校";
	

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-29 下午04:33:10
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

		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();		
	
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
		String path = "jlkjxyjqfxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		
		//-----------------获取基础设置开关关闭与否-------------------
		JqfxjcszService jqfxjcszservice = new JqfxjcszService();
		JqfxjcszForm jcszModel = jqfxjcszservice.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", "01".equals(Base.currXq)?"春":"秋");	
		request.setAttribute("fxmc", service.getFxmc()); 
		return mapping.findForward("jlkjxyjqfxwh");
	}
	
	

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-4 下午05:25:09
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
		
			JqfxwhForm model = (JqfxwhForm) form;
			JqfxwhService service = new JqfxwhService();
			if (!StringUtil.isNull(model.getXh())) {
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);	
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// 唯一性判断（学号，学年，学期）
				boolean isExist = false;
				if (!isExist) {														
					// 添加日常行为结果
					model.setTbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss") );
					boolean result = service.saveJqFxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				} else {					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}			
			JqfxwhForm jqfxJg = service.getModel(model.getId());
			if(jqfxJg != null){
				BeanUtils.copyProperties(model, jqfxJg);
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
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-4 下午04:16:47
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
			JqfxwhForm model = (JqfxwhForm) form;
			JqfxwhService service = new JqfxwhService();
			@SuppressWarnings("unused")
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
				boolean isExist = false;
				if (!isExist){	
					
					boolean result = service.saveJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
					
				}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}			
			
			request.setAttribute("rs", StringUtils.formatData(service.getOneFxgljgList(model.getXh())));
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
				JqfxwhForm updateForm = service.getModel(model);
				BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
			}			
			return mapping.findForward("addxsJqwfx");			
	}
	
	/**
	 * 
	 * @描述:TODO(批量修改学生假期返校维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-7 上午09:06:19
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
		
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		
		if (SAVE.equalsIgnoreCase(model.getType())) {			
			// 唯一性判断（学号，学年，学期）
			boolean isExist = false;
			if (!isExist) {					
				//model.setXq("春".equals(model.getXq())?"01":"02");				
				boolean result = service.plSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
			} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}
		}
		
		
		//String countNum =  (String) request.getAttribute("countNum");
		request.setAttribute("countNum", model.getCountNum()); 		
		return mapping.findForward("addPlxsJqfx");		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-22 下午12:02:08
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
		
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();
		
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
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-18 下午02:15:19
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
						
		
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();						
		
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {				
			// 唯一性判断（学号，学年，学期）
			boolean isExist = false;
			if (!isExist) {		
				boolean result = service.pldgSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
			} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}
		}
		
			request.setAttribute("countNum", model.getCountNum()); 	
		request.setAttribute("pldgxsJqfx", "1"); 
		return mapping.findForward("addPlxsJqfx");	
		
		
		
		
	}
	
	

	

	
	/**
	 * 
	 * @描述:TODO(查看学生假期返校)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-4 下午04:57:15
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
		
			JqfxwhForm model = (JqfxwhForm) form;
			JqfxwhService service = new JqfxwhService();
			@SuppressWarnings("unused")
			User user = getUser(request);
			if (!StringUtil.isNull(model.getXh())) {
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}	
			request.setAttribute("jbxxList", jbxxList);	
			request.setAttribute("rs", StringUtils.formatData(service.getOneFxgljgList(model.getXh())));
			//获取未返校信息记录
			request.setAttribute("wfxxsrs", StringUtils.formatData(service.getOneWfxxwhjgList(model.getXh())));
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"春":"秋");	
			
			if(!"null".equals(model.getId())){
				JqfxwhForm updateForm = service.getModel(model);
				BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
			}
			request.setAttribute("fxzt", model.getFxzt());
			return mapping.findForward("viewxsJqfx");				
	}	
	
	/**
	 * 
	 * @描述:TODO(自定义导出)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-9 上午10:52:32
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
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();

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
	 * @描述:TODO(C设置常用意见)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-14 下午06:42:22
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
	public ActionForward szCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);	
		JqfxwhService service = new JqfxwhService();
		List<HashMap<String,String>> cyyyList = service.getCyyyList(user);		
		request.setAttribute("cyyyList", cyyyList);
		return mapping.findForward("szCyyy");
		
	}
	
	/**
	 * 
	 * @描述:TODO(获取常用原因集合)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-15 上午11:35:03
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
	public ActionForward getCyyyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);	
		JqfxwhService service = new JqfxwhService();
		List<HashMap<String,String>> cyyjList = service.getCyyyList(user);		
		JSONArray json = JSONArray.fromObject(cyyjList);
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(保存未返校常用原因)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-15 上午11:32:23
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
	public ActionForward saveCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String[] cyyy = request.getParameterValues("cyyy");	
		JqfxwhService service = new JqfxwhService();
		//保存未返校常用原因
		boolean result = service.saveCyyy(user, cyyy);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(学生假期批量未返校)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-4 下午04:16:47
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
		
			JqfxwhForm model = (JqfxwhForm) form;						
			JqfxwhService service = new JqfxwhService();
			@SuppressWarnings("unused")
			User user = getUser(request);					
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// 唯一性判断（学号，学年，学期）
				boolean isExist = false;
				if (!isExist){					
					boolean result = service.saveplxsJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}																
			return mapping.findForward("addplxsJqwfx");			
	}

}

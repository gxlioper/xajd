/**
 * @部门:学工产品事业部
 * @日期：2013-12-16 上午09:59:58 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

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
import com.zfsoft.xgxt.xsxx.bbzc.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学期报到注册ACTION 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-16 上午09:59:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqbdzcAction extends SuperAction {
	
	private static final String url = "xsxx_xqbdzc.do";

	/**
	 * 
	 * @描述:报到注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-16 上午11:09:32
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
	public ActionForward viewBdzcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		CsszService csszService  = new CsszService();
		if(QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			String xn = null;
			String xq = null;
			
			if("stu".equals(user.getUserType())){
				xn = Base.currXn;
				xq = Base.currXq;
			}else{
				xn = searchModel.getSearch_tj_xn()[0];
				xq = searchModel.getSearch_tj_xq()[0];
			}
			
			model.setSearchXn(xn);
			model.setSearchXq(xq);
			
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		model.setSearchXn(Base.currXn);
		model.setSearchXq(Base.currXq);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		
		String path = "xsxx_xqbdzc.do";
		request.setAttribute("zckg", csszService.getCsszParam());
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xqbdzcManage");
	}

	/**
	 * 
	 * @描述:单条学期注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-16 下午05:14:21
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
	public ActionForward dtXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		//String xn = model.getXn();
		//String xq = model.getXq();
		String xh = model.getXh();
		
		User user = getUser(request);
		
		if("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		
		if(!StringUtil.isNull(xh)){
			
			//学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			
			//注册信息
			HashMap<String,String> xqbdxx  =  service.getXqZcXx(xh, Base.currXn, Base.currXq);
			if(StringUtils.isBlank(xqbdxx.get("zcsj"))){
				xqbdxx.put("zcsj", DateUtils.getCurrDate());
			}
			request.setAttribute("xqbdxx", xgxt.utils.String.StringUtils.formatData(xqbdxx));
			
			
			//财务数据
			List<HashMap<String , String>> cwsjList = service.getCwsjList(xh, Base.currXn, Base.currXq);
			request.setAttribute("cwsjList", cwsjList);
		}
		
		String path = "xsxx_xqbdzcgl.do?method=dtXqbdzc";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("tDgzc");
		
	}
	
	/**
	 * 
	 * @描述:查看学期注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午03:01:11
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
	public ActionForward ckXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		
		//学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//注册信息
		HashMap<String,String> xqbdxx  =  service.getXqZcXx(xh, xn, xq);
		request.setAttribute("xqbdxx", xgxt.utils.String.StringUtils.formatData(xqbdxx));
		
		//财务数据
		List<HashMap<String , String>> cwsjList = service.getCwsjList(xh, xn, xq);
		request.setAttribute("cwsjList", cwsjList);
		//获取注册状态
		String zczt = service.getZczt(xh, xn, xq);
		request.setAttribute("zczt", zczt);
		return mapping.findForward("tCkzc");
		
	}
	/**
	 * 
	 * @描述:单个注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 上午10:39:39
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
	@SystemLog(description="访问学生信息-报到注册（华师大）-学期报到注册-保存PK:{id}")
	public ActionForward doDtZc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		model.setZczt("1");
		model.setZcr(getUser(request).getUserName());
		//model.setZcsj(DateUtils.getCurrTime());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		
		boolean isSuccess = service.doDtZc(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 批量注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 上午11:35:53
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
	public ActionForward plXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		String type = model.getType();
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(type != null && "zc_all".equals(type)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			String xn = searchModel.getSearch_tj_xn()[0];
			String xq = searchModel.getSearch_tj_xq()[0];
			
			model.setSearchXn(xn);
			model.setSearchXq(xq);
			
			String rownum_w = service.getWzcListCount(model, user ); //未处理人数
			String rownum_x = service.getWclListCount(model, user ); //未注册人数
			int rownum_wx = Integer.valueOf(rownum_w) + Integer.valueOf(rownum_x);//总人数
			Map<String,String> map = new HashMap<String, String>();
			map.put("rownum_w", rownum_w);
			map.put("rownum_x", rownum_x);
			map.put("rownum_wx", String.valueOf(rownum_wx));
			
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			
//			String length= service.getWzcListCount(model, user); //未注册人数
//			response.getWriter().print(getJsonMessage(length));
			return null;
		
		}else if(type != null && "zc_select".equals(type)){
			
			request.setAttribute("rownum_w", model.getRownum_w());
			
			request.setAttribute("rownum_x", model.getRownum_x());
			
			request.setAttribute("rownum_wx", model.getRownum_wx());
			
		}else if(request.getParameter("rownum_w") !=null){
			
            request.setAttribute("rownum_w", request.getParameter("rownum_w"));
			
			request.setAttribute("rownum_x", request.getParameter("rownum_x"));
			
			request.setAttribute("rownum_wx", request.getParameter("rownum_wx"));

		}
		
		request.setAttribute("plsqsj", DateUtils.getCurrDate());
		this.saveToken(request);
		return mapping.findForward("tPlzc");
	}
	
	/**
	 * 
	 * @描述:批量注册
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午01:00:21
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
	@SystemLog(description="访问学生信息-报到注册（华师大）-学期报到注册-批量修改PK:{plIds}")
	public ActionForward doPlXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String[] plIds = null;
//		String[] wzcxsArr=null;
		User user = getUser(request);
		if(null==model.getPlIds()){
		model.setSearchXn(searchModel.getSearch_tj_xn()[0]);
		model.setSearchXq(searchModel.getSearch_tj_xq()[0]);
//		wzcxsArr= service.getWzcList(model, user); //全部查询
		}else{
		plIds=model.getPlIds().split(",");
		}
		String zcsj = model.getZcsj();
		String xn = Base.currXn;
		String xq = Base.currXq;
		String zcr = user.getUserName();
		boolean isSuccess = service.plXqzc(plIds, xn, xq, zcr, zcsj, model, user);

		request.setAttribute("plsqsj", DateUtils.getCurrDate());
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:批量撤销
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午01:34:37
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
	public ActionForward cxXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		String type = model.getType();
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(type != null && "cx_all".equals(type)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			String xn = searchModel.getSearch_tj_xn()[0];
			String xq = searchModel.getSearch_tj_xq()[0];
			
			model.setSearchXn(xn);
			model.setSearchXq(xq);
			
//			List<HashMap<String,String>> resultList = service.getBdzcList(model, user); //全部查询
//
//			StringBuilder ids = new StringBuilder();
//			
//			int rownum_w = 0; //未注册人数统计
//			int rownum_y = 0; //已注册人数统计
//			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
//				HashMap<String, String> hashMap = (HashMap<String, String>) iterator.next();
//				if(hashMap.get("zczt") != null && "1".equals(hashMap.get("zczt"))){
//					rownum_y ++;
//				}else{
//					rownum_w ++;
//				}
//			}
//			
//			model.setPlIds(ids.toString());
//			
//			request.setAttribute("rownum_w", rownum_w);
//			
//			request.setAttribute("rownum_y", rownum_y);
//			
//			request.setAttribute("rownum_t", resultList.size());
			
			String rownum_w = service.getWzcListCount(model, user); //未处理人数
			String rownum_x = service.getWclListCount(model, user); //未注册人数
		    String rownum_t = service.getBdzcListCount(model, user); //总人数
			int rownum_y = Integer.valueOf(rownum_t) - Integer.valueOf(rownum_w) - Integer.valueOf(rownum_x);
			
			Map<String,String> map = new HashMap<String, String>();
			map.put("rownum_w", rownum_w);
			map.put("rownum_x", rownum_x);
			map.put("rownum_t", rownum_t);
			map.put("rownum_y", String.valueOf(rownum_y));
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
			
		}else if(type != null && "cx_select".equals(type)){
			
			request.setAttribute("rownum_w", model.getRownum_w());
			
			request.setAttribute("rownum_x", model.getRownum_x());
			
			request.setAttribute("rownum_y", model.getRownum_y());
			
			request.setAttribute("rownum_t", model.getRownum_t());
			
		
		}else if(request.getParameter("rownum_w") !=null){
			
			request.setAttribute("rownum_w", request.getParameter("rownum_w"));
			
			request.setAttribute("rownum_x", request.getParameter("rownum_x"));
			
			request.setAttribute("rownum_y", request.getParameter("rownum_y"));
			
			request.setAttribute("rownum_t", request.getParameter("rownum_t"));

		}
		
		return mapping.findForward("tPlcx");
	}
	/**
	 * 
	 * @描述:批量撤销
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午01:39:58
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
	@SystemLog(description="访问学生信息-报到注册（华师大）-学期报到注册-批量撤销PK:{plIds}")
	public ActionForward doCxXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String[] plIds = null;
//		List<HashMap<String,String>> cxxsList=null;
		User user = getUser(request);
		if(null==model.getPlIds()){
		model.setSearchXn(searchModel.getSearch_tj_xn()[0]);
		model.setSearchXq(searchModel.getSearch_tj_xq()[0]);
//		cxxsList = service.getBdzcList(model, user); //全部查询
		}else{
		plIds=model.getPlIds().split(",");
		}
		String xn = Base.currXn;
		String xq = Base.currXq;

		boolean isSuccess = service.plCxzc(plIds, xn , xq, model, user );
		
		request.setAttribute("plsqsj", DateUtils.getCurrDate());
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_AUD_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-17 下午02:22:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

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
	 * @描述:未报到原因维护
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午02:48:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward wbdyywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		request.setAttribute("wbdlbList", service.getWbdlb());
		return mapping.findForward("wbdyywh");
	}
	/**
	 * 
	 * @描述:学生未报到原因保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-24 上午08:55:08
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
	@SystemLog(description="访问学生信息-报到注册-学期报到注册-未报到原因维护PK:{plIds}")
	public ActionForward saveWbdyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		XqbdzcService service = new XqbdzcService();
		boolean isSuccess = service.wbdyywh(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:获取用户常用意见列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午03:56:10
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
	public ActionForward getCyyyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		List<HashMap<String,String>> cyyjList = service.getCyyyList(user);
		JSONArray json = JSONArray.fromObject(cyyjList);
		response.getWriter().print(json);
		
		return null;
	}
	/**
	 * 
	 * @描述:设置常用未报到原因
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午04:09:08
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
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		List<HashMap<String,String>> cyyyList = service.getCyyyList(user);
		
		request.setAttribute("cyyyList", cyyyList);
		return mapping.findForward("szcyyy");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		String[] cyyy = request.getParameterValues("cyyy");
		//保存常用原因
		boolean result = service.saveCyyy(user,cyyy);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
}

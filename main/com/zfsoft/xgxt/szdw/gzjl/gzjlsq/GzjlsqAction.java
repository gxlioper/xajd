/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:38:49 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgService;
import com.zfsoft.xgxt.szdw.gzjl.jcsz.GzjlJcszService;
import com.zfsoft.xgxt.szdw.gzjl.lbgl.GzjlLbglService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:38:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlsqAction extends SuperAction<GzjlsqForm, GzjlsqService> {
	private final String GZJL="gzjl";
	private GzjlsqService service = new GzjlsqService();
	private GzjljgService gzjljgservice = new GzjljgService();
	private GzjlLbglService gzlbService = new GzjlLbglService();

	private static final String url = "gzjl_gzjlsq.do";
	
	/**
	 * 
	 * @描述:工作记录申请列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:05:47
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
	public ActionForward gzjlsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
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
		GzjlJcszService  jcszService = new GzjlJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "gzjl_gzjlsq.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzjlsqList");
	}
	/**
	 * 
	 * @描述:工作记录申请增加
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:05:47
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
	public ActionForward gzjlsqZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
		User user = getUser(request);
		model.setZgh(user.getUserName());
		if (!StringUtil.isNull(model.getZgh())) {
			// 加载教师基本信息
			
			HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		List<HashMap<String, String>> gzlbList = gzlbService.getGzjllbList();
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzlbList", gzlbList);
		request.setAttribute("jlsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("xnList", Base.getXnndList());
		//浙江树人大学取六困生类型
		if("11842".equals(Base.xxdm)){
			request.setAttribute("lksList", service.getLks());
			model.setLks("7");
		}		
		String path = "gzjlsq.do?method=gzjlsqZj";
		request.setAttribute("path", path);
		return mapping.findForward("gzjlsqZj");
	}
	/**
	 * 
	 * @描述:工作记录申请修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:05:47
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
	public ActionForward gzjlsqXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm myForm = (GzjlsqForm) form;
		GzjlsqForm model = service.getModel(myForm);
		if(null!=model){
			model.setLbbh(model.getLbdm());
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载教师基本信息
			
			HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		List<HashMap<String, String>> gzlbList = gzlbService.getGzjllbList();
		request.setAttribute("gzlbList", gzlbList);
		request.setAttribute("jlsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("jbxxList", jbxxList);
		//树人大学个性化配置
		if("11842".equals(Base.xxdm)){
			request.setAttribute("lksList", service.getLks());
			if(StringUtils.isNotNull(model.getXh())){
				String[] xhArr = model.getXh().split(",");
				List<HashMap<String,String>> thdxList = service.getThdxList(xhArr);
				request.setAttribute("thdxList", thdxList);
			}
		}
		request.setAttribute("gzjlsq", model);
		String path = "gzjlsq.do?method=gzjlsqXg";
		request.setAttribute("path", path);
		return mapping.findForward("gzjlsqXg");
	}
	/**
	 * 
	 * @描述:工作记录申请查看
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:05:47
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
	public ActionForward gzjlsqCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm myForm = (GzjlsqForm) form;
		GzjlsqForm model = service.getModel(myForm);
		//树人大学取出谈话对象
		if("11842".equals(Base.xxdm)){
			if(StringUtils.isNotNull(model.getXh())){
				String[] xhArr = model.getXh().split(",");
				List<HashMap<String,String>> thdxList = service.getThdxList(xhArr);
				request.setAttribute("thdxList", thdxList);
			}
			
		}		
		User user = getUser(request);
		
		// 加载教师基本信息
		
		HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
		request.setAttribute("jbxx", xsjbxx);
		
		// 教师基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("gzjlsqCk");

	}
	/**
	 * 
	 * @描述:工作记录申请保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:24:21
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
	@SystemLog(description="访问职工工作记录信息-工作记录管理-工作记录信息-增加ZGH:{zgh},GZSJ:{gzsj},LBDM:{lbdm},GZZY:{gzzy}")
	public ActionForward saveGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
		//浙江树人学院个性化
		if("11842".equals(Base.xxdm)){
			String objStr = request.getParameter("objStr");
			if(null != objStr && !"".equals(objStr)) {
				model.setXh(objStr.substring(0, objStr.length()-1));
			}else{
				model.setXh("");
			}			
		}		
		boolean result = service.saveGzjlsq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:申请修改保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:24:47
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
	@SystemLog(description="访问职工工作记录信息-工作记录管理-工作记录信息-修改SQID:{sqid},ZGH:{zgh},GZSJ:{gzsj},LBDM:{lbdm},GZZY:{gzzy},JLSJ:{jlsj}")
	public ActionForward saveEditGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
		String message=null;
		//树人大学
		if("11842".equals(Base.xxdm)){
			String objStr = request.getParameter("objStr");
			if(null != objStr && !"".equals(objStr)) {
				model.setXh(objStr.substring(0, objStr.length()-1));
			}else{
				model.setXh("");
			}			
		}
		boolean result = service.saveEditGzjlsq(request,model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:申请记录删除
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:32:28
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
	@SystemLog(description="访问职工工作记录信息-工作记录管理-工作记录信息-删除sqid:{values}")
	public ActionForward delGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @描述:撤销
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:32:46
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
	public ActionForward cancelGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			GzjlsqForm model = new GzjlsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:数据导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:33:05
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
		GzjlsqForm model = (GzjlsqForm) form;

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
	 * @描述:增加谈话对象
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午09:49:23
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GzjlsqForm myForm = (GzjlsqForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			//String bjdm = request.getParameter("bjdm");
			
			//myForm.setBjdm(bjdm);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,request);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		String path = "gzjlsq.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}

}

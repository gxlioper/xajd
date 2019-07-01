/**
 * @部门:学工产品事业部
 * @日期：2013-9-10 上午11:11:45 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xlzx.tsxsgl.TsxsService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 特殊学生维护模块(这里用一句话描述这个类的作用) 
  * @作者：wanghj [工号：1004]
 * @时间： 2013-9-10 上午11:10:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ThjlAction extends SuperAction {
	
	private static final String url = "xlzx_thjl_thjl.do";
	
	private static ThjlService service = new ThjlService();
	private static TsxsService tsxsSv = new TsxsService();

	/**
	 * 定义特殊学生可以从基本信息表中获取学生信息
	 */
	private static final String TSXS = "tsxs";
	private List<HashMap<String,String>> jbxxList = null;
	
	/** 
	 * @描述:谈话记录查询列表
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward thjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> thjlInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(thjlInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xlzx_thjl_thjl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("thjlManage");
	}
	
	/**
	 * 
	 * @描述: 谈话记录新增
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-25 上午10:02:58
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
	public ActionForward thjlzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ThjlForm myForm = (ThjlForm) form;
		// 获取职工号
		User user = getUser(request);
		String zgh = user.getUserName();

	 	if(StringUtils.isNotNull(myForm.getXh())){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(myForm.getXh());
 			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xlzx_thjl.do?method=thjlzj";
		
		//学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("thjlzj");
	}
	

	/**
	 * 
	 * @描述: 谈话记录新增
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-25 上午10:02:58
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
	public ActionForward thjlxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ThjlForm myForm = (ThjlForm) form;
		// 获取职工号
		User user = getUser(request);
		String zgh = user.getUserName();

	 	if(StringUtils.isNotNull(myForm.getXh())){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(myForm.getXh());
 			request.setAttribute("jbxx", xsjbxx);
		}
	 	
		if(StringUtils.isNotNull(zgh)){
			HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
			request.setAttribute("thjlInfo", StringUtils.formatData(jsInfoList));
		}
		String path = "xlzx_thjl.do?method=thjlxg";
		
		//学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("thjlxg");
	}
	
	/** 
	 * @描述:谈话记录新增、详情查询
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward thjlDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if(StringUtil.isNull(xh) ){
			xh = myForm.getXh();
		}
		String zgh = request.getParameter("zgh");
		if(StringUtil.isNull(zgh)){
			zgh = myForm.getZgh();
		}
		String doType = request.getParameter("doType");
	 	HashMap<String, String> thjlInfo=new HashMap<String,String>();
		if(doType==null || "add".equals(doType)){//新增谈话记录
			// 学号存在 读取学生信息
			if(user.getUserStatus().equals("stu") && StringUtil.isNull(xh)){
	 			xh = user.getUserName();
 			}
			// 职工号存在 读取教师信息
 			if(StringUtil.isNull(zgh)){
	 			zgh = user.getUserName();
 			}

 			// 职工号信息
 			if(StringUtils.isNotNull(zgh)){
 				HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
 				if(jsInfoList!=null && jsInfoList.size()>0){
 		 			thjlInfo.put("zgh", zgh);
 		 			thjlInfo.put("jsxb", jsInfoList.get("xb"));
 		 			thjlInfo.put("jsxm", jsInfoList.get("xm"));
 		 			thjlInfo.put("jsbmmc", jsInfoList.get("bmmc"));
 				}
 			}
		}else if("update".equals(doType) || "view".equals(doType)){
			if(!StringUtil.isNull(myForm.getId())){
				thjlInfo = service.getThjlListById(myForm.getId());
				if(thjlInfo!=null && thjlInfo.size()>0){
		 			//获取困难类型名称
					if(thjlInfo.get("knlxdm")!=null && !"".equals(thjlInfo.get("knlxdm"))){
						String knlxmc = tsxsSv.getKnlxMc(thjlInfo.get("knlxdm"));
						thjlInfo.put("knlxmc", knlxmc);
			 		}
					
					if(StringUtils.isNotNull(thjlInfo.get("xh"))){
						xh = thjlInfo.get("xh");
					}
				}
				
	 		}
		}
		// 学生信息
	 	if(StringUtils.isNotNull(xh)){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(xh);
 			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xlzx_thjl.do?method=thjlDetail";
		//学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String,String>>  knlxList = tsxsSv.getKnlxList();
		request.setAttribute("knlxList", knlxList);
		request.setAttribute("doType", doType);
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("userStatus", user.getUserStatus());
		return mapping.findForward("thjlDetail");
	}
	
	/** 
	 * @描述:根据学号查询谈话记录
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward thjlDetailByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
 		List<HashMap<String,String>> thjlList = service.getThjlListByXh(myForm.getXh());
 		HashMap<String,String> thjlInfo = new HashMap<String,String>();
 		
		// 学生信息
	 	if(StringUtils.isNotNull(myForm.getXh())){
	 		
 			HashMap<String,String> xsjbxx = tsxsSv.getTsxsDetailByXh(myForm.getXh());
 			request.setAttribute("jbxx", xsjbxx);
		}
	 	
		String path = "xlzx_thjl_thjl.do";
		//学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz(TSXS);
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//获得最新维护的一条谈话记录
		if(thjlList!=null && thjlList.size()>0){
			thjlInfo = thjlList.get(0);
			//获取困难类型名称
			if(thjlInfo.get("knlxdm")!=null && !"".equals(thjlInfo.get("knlxdm"))){
				String knlxmc = tsxsSv.getKnlxMc(thjlInfo.get("knlxdm"));
				thjlInfo.put("knlxmc", knlxmc);
			}
			thjlList.remove(0);//获取历史谈话记录
		}
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("hisThjlList", thjlList);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("thjlDetailByXh");
		
	}
	
	/**
	 * @描述:谈话记录新增
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-26 下午4:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			
			ThjlForm model = (ThjlForm) form;
			try {
				boolean flag = service.saveThjlInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("thjlDetail");
		
	}
	/**
	 * @描述:谈话记录修改
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-26 下午5:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm model = (ThjlForm) form;

		try {
			boolean flag = service.updateThjlInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/**
	 * @描述:是否有谈话记录
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-10-09 上午10:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward haveThjlFlagByXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm model = (ThjlForm) form;
		boolean flag = false;
		try {
			List<HashMap<String,String>> thjlList = service.getThjlListByXh(model.getXh());
			if(thjlList!=null && thjlList.size()>0){
				flag = true;
			}
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @描述:删除谈话记录
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-26 下午5:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteThjlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dealThjl = request.getParameter("dealThjl");
		String[] id = dealThjl.split(",");
		int count = 0;
		try {
			count = service.delThjlById(id);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	/** 
	 * @描述:选择教师
	 * @作者：qilm
	 * @日期：2013-10-24
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward getJsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm = (ThjlForm) form;
		String doType = request.getParameter("doType");
		String gotoPath = request.getParameter("gotoPath");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> jsInfoList = service.getJsInfoList(myForm);
			JSONArray dataList = JSONArray.fromObject(jsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		gotoPath = gotoPath.replaceAll("[$]", "&");
		request.setAttribute("path", "xlzx_thjl.do?method=getJsInfo");
		request.setAttribute("gotoPath", gotoPath);
		
		return mapping.findForward("getJsInfo");
	}
	
	/**
	 * @描述:谈话记录导出
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-26 下午4:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportThjlData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThjlForm myForm=(ThjlForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		
		TsxsService tsxsSv = new TsxsService();
		for(int i=0;i<resultList.size();i++){
			String knlxdm = resultList.get(i).get("knlxdm");
			if(!StringUtil.isNull(knlxdm)){
				resultList.get(i).put("knlxmc", tsxsSv.getKnlxMc(knlxdm));
			}
		}
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2014-7-17 上午10:04:32 
 */
package com.zfsoft.xgxt.szdw.thjl;

import java.io.File;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx.YwxsypxForm;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
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
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.thjl.thlx.SzdwThlxService;
import com.zfsoft.xgxt.xlzx.tsxsgl.TsxsService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述: 谈话记录维护 
 * @作者： cq [工号:785]
 * @时间： 2014-7-17 上午10:04:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThjlAction extends SuperAction {
	
	private static SzdwThjlService service = new SzdwThjlService();
	private static final String SZDWTHJL = "szdwthjl";
	private static List<HashMap<String,String>> jbxxList = null;
	private static List<HashMap<String,String>> thlxList = null;
	private ShlcInterface shlc = new CommShlcImpl();

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(SZDWTHJL);
		SzdwThlxService thlxService = new SzdwThlxService();
		// 查询所有谈话类型
		try {
			thlxList = thlxService.getAllThlxList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String url = "szdw_thjl_thjl.do";
	
	/**
	 * 
	 * @描述:加载学生基本信息
	 * @作者：cq [工号：785]
	 * @日期：2014-7-17 下午03:43:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param xh
	 * void 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//学生基本信息显示配置
		request.setAttribute("jbxxList", jbxxList);
	}
	
	
	
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
		SzdwThjlForm myForm = (SzdwThjlForm) form;
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
		request.setAttribute("userType", user.getUserType());
		request.setAttribute("path", "szdw_thjl_thjl.do");
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

		// 获取职工号
		User user = getUser(request);
		String zgh = user.getUserName();

		String path = "szdw_thjl.do?method=thjlzj";
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

		// 获取职工号
		User user = getUser(request);
		String zgh = user.getUserName();

		if(StringUtils.isNotNull(zgh)){
			HashMap<String, String> jsInfoList = service.getInfoByZgh(zgh);
			request.setAttribute("thjlInfo", StringUtils.formatData(jsInfoList));
		}
		String path = "szdw_thjl.do?method=thjlxg";
		
		//学生基本信息显示配置
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		
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
		SzdwThjlForm myForm = (SzdwThjlForm) form;
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
 			thjlInfo.put("sfzdgz", "0");
			thjlInfo.put("sfsdkt","0");
 			thjlInfo.put("gzdj", "三星");
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
					if("update".equals(doType) && StringUtils.isNull(thjlInfo.get("gzdj"))){
						thjlInfo.put("gzdj", "三星");
					}
					if(StringUtils.isNotNull(thjlInfo.get("xh"))){
						xh = thjlInfo.get("xh");
					}
				}
				
	 		}
		}
		
		String path = "szdw_thjl.do?method=thjlDetail";
		
		//设置学生基本信息
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,xh);
		}
		request.setAttribute("jbxxList", jbxxList);
		SzdwThlxService thlxService = new SzdwThlxService();
		// 查询所有谈话类型
		List<HashMap<String,String>> thlxList = thlxService.getAllThlxList();
		request.setAttribute("thlxList", thlxList);

		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);
		
		request.setAttribute("doType", doType);
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("userStatus", user.getUserStatus());
		OptionUtil optionUtil = new OptionUtil();
		request.setAttribute("gzdjList", optionUtil.getOptions(OptionUtil.THJL_GZDJ));
		request.setAttribute("isnotList", optionUtil.getOptions(OptionUtil.ISNOT));
		request.setAttribute("xh",xh);
		List<HashMap<String,String>> thjlList = null;
		thjlList = service.getThjlListByXh(xh);
		if(thjlList!=null && thjlList.size()>0){
			thjlList.remove(0);//获取历史谈话记录
		}
		request.setAttribute("hisThjlList", thjlList);
		return mapping.findForward("thjlDetail");
	}
	public ActionForward getXjydxx(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		List<HashMap<String,String>> xjydList = service.getAllXjydList(xh);
		response.getWriter().print(JSONArray.fromObject(xjydList));
		return null;
	}
	public ActionForward getBjgcj(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		List<HashMap<String,String>> list  = service.getBjgcj(xh);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
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
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		List<HashMap<String,String>> thjlList = null;

		thjlList = service.getThjlListByXh(myForm.getXh());
		List<HashMap<String,String>> xjydList = service.getAllXjydList(myForm.getXh());
 		HashMap<String,String> thjlInfo = new HashMap<String,String>();
 		//设置学生基本信息
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,myForm.getXh());
		}
		request.setAttribute("jbxxList", jbxxList);
		
		//获得最新维护的一条谈话记录
		if(thjlList!=null && thjlList.size()>0){
			thjlInfo = thjlList.get(0);
			thjlList.remove(0);//获取历史谈话记录
		}
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("xjydList",xjydList);
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
			
			SzdwThjlForm model = (SzdwThjlForm) form;
//			model.setKhhwt(URLDecoder.decode(URLDecoder.decode(model.getKhhwt(),"UTF-8"),"UTF-8"));
//			model.setMtyd(URLDecoder.decode(URLDecoder.decode(model.getMtyd(),"UTF-8"),"UTF-8"));
			if(model.getThnr() != null){
				model.setThnr(URLDecoder.decode(URLDecoder.decode(model.getThnr(),"UTF-8"),"UTF-8"));
			}

			/*if(model.getGzdj() != null) {
				model.setGzdj(URLDecoder.decode(URLDecoder.decode(model.getGzdj(),"UTF-8"),"UTF-8"));
			}
			if(model.getBz() != null) {
				model.setBz(URLDecoder.decode(URLDecoder.decode(model.getBz(),"UTF-8"),"UTF-8"));
			}*/
			if(model.getYyfx() != null){
				model.setYyfx(URLDecoder.decode(URLDecoder.decode(model.getYyfx(),"UTF-8"),"UTF-8"));
			}
			if(model.getGjcs() != null){
				model.setGjcs(URLDecoder.decode(URLDecoder.decode(model.getGjcs(),"UTF-8"),"UTF-8"));
			}
			if(model.getQtjy() != null){
				model.setQtjy(URLDecoder.decode(URLDecoder.decode(model.getQtjy(),"UTF-8"),"UTF-8"));
			}
			/*if("10351".equals(Base.xxdm)){
				model.setGzqx(URLDecoder.decode(URLDecoder.decode(model.getGzqx(),"UTF-8"),"UTF-8"));
			}*/
			Boolean flag;
			try {
				/*if("10351".equals(Base.xxdm)){
					 flag = service.saveThjlInfoForWzdx(model);
				}else{*/
					 flag = service.saveThjlInfo(model);
//				}
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
		SzdwThjlForm model = (SzdwThjlForm) form;
//		model.setKhhwt(URLDecoder.decode(URLDecoder.decode(model.getKhhwt(),"UTF-8"),"UTF-8"));
//		model.setMtyd(URLDecoder.decode(URLDecoder.decode(model.getMtyd(),"UTF-8"),"UTF-8"));

		if(model.getThnr() != null){
			model.setThnr(URLDecoder.decode(URLDecoder.decode(model.getThnr(),"UTF-8"),"UTF-8"));
		}
		/*if(model.getGzdj() != null) {
			model.setGzdj(URLDecoder.decode(URLDecoder.decode(model.getGzdj(),"UTF-8"),"UTF-8"));
		}*/
		/*if(model.getBz() != null) {
			model.setBz(URLDecoder.decode(URLDecoder.decode(model.getBz(),"UTF-8"),"UTF-8"));
		}*/
		if(model.getYyfx() != null){
			model.setYyfx(URLDecoder.decode(URLDecoder.decode(model.getYyfx(),"UTF-8"),"UTF-8"));
		}
		if(model.getGjcs() != null){
			model.setGjcs(URLDecoder.decode(URLDecoder.decode(model.getGjcs(),"UTF-8"),"UTF-8"));
		}
		if(model.getQtjy() != null){
			model.setQtjy(URLDecoder.decode(URLDecoder.decode(model.getQtjy(),"UTF-8"),"UTF-8"));
		}

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
		SzdwThjlForm model = (SzdwThjlForm) form;
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
	@SystemAuth(url = url)
	public ActionForward getJsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
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
		request.setAttribute("path", "szdw_thjl.do?method=getJsInfo");
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
		SzdwThjlForm myForm=(SzdwThjlForm)form;
		
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

	@SystemAuth(url = url)
	public ActionForward sqList(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			//查询
			List<HashMap<String,String>> thjlInfoList = service.getSqList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(thjlInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "szdw_thjl_thjl_sq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sqList");
	}

	/**
	 * 申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zjsq(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
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
			thjlInfo.put("sfzdgz", "0");
			thjlInfo.put("sfsdkt","0");
			thjlInfo.put("gzdj", "三星");
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
			if(!StringUtil.isNull(myForm.getSqid())){

				thjlInfo = service.getSqMap(myForm.getSqid());
				if(thjlInfo!=null && thjlInfo.size()>0){
					if("update".equals(doType) && StringUtils.isNull(thjlInfo.get("gzdj"))){
						thjlInfo.put("gzdj", "三星");
					}
					if(StringUtils.isNotNull(thjlInfo.get("xh"))){
						xh = thjlInfo.get("xh");
					}
				}

			}
		}

		String path = "szdw_thjl.do?method=zjsq";

		//设置学生基本信息
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
			request.setAttribute("type", "update");
		}else{
			szXsxx(request,xh);
		}
		request.setAttribute("jbxxList", jbxxList);
		SzdwThlxService thlxService = new SzdwThlxService();
		// 查询所有谈话类型
		List<HashMap<String,String>> thlxList = thlxService.getAllThlxList();
		request.setAttribute("thlxList", thlxList);

		request.setAttribute("path", path);
		request.setAttribute("zgh", zgh);

		request.setAttribute("doType", doType);
		request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
		request.setAttribute("userStatus", user.getUserStatus());
		OptionUtil optionUtil = new OptionUtil();
		request.setAttribute("gzdjList", optionUtil.getOptions(OptionUtil.THJL_GZDJ));
		request.setAttribute("isnotList", optionUtil.getOptions(OptionUtil.ISNOT));
		request.setAttribute("xh",xh);
		return mapping.findForward("zjsq");
	}

	/**
	 * 申请增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjsqBc(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm myForm = (SzdwThjlForm) form;
		String doType = request.getParameter("doType");
		boolean flag = service.zjsqBc(myForm,doType);
		String key = flag ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(key));
		return null;
	}
	public ActionForward submit(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwThjlForm model = (SzdwThjlForm) form;
		String id = request.getParameter("values");
		model.setSqid(id);
		model.setShzt(Constants.YW_SHZ);
		model.setType(SUBMIT);
		boolean result = service.update(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splc");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销

		boolean result = shlc.firstStepCancle(sqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			SzdwThjlForm model = new SzdwThjlForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			model.setShzt(Constants.YW_WTJ);
			result = service.update(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward del(ActionMapping mapping, ActionForm form,
							 HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("values");
		boolean f = service.del(ids.split(","));
		String msg = f ? MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
}

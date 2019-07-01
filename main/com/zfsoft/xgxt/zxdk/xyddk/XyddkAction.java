/**
 * @部门:学工产品事业部
 * @日期：2014-9-25 下午03:29:36 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.syddk.SydkSqshService;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-国家助学贷款
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-25 下午03:29:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyddkAction extends SuperAuditAction<XyddkModel, XyddkService> {
	 
	private static final String KNSRD = "knsrd";
	private static final String GJZXDK = "gjzxdk";
	private static final String GJZXDK_XAKJ = "gjzxdk_xakj";
	private ShlcInterface shlc = new CommShlcImpl();

	
	protected XyddkAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public XyddkAction(){
		this("zxdk_xyddk", "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
	}
	
	/**
	 * 
	 * @描述: 贷款申请列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:37:15
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward dksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_gjdk_dksq.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		if(Globals.XXDM_ZJJDZYJSXY.equals(Base.xxdm)){
			searchModel.setSearch_tj_xn(new String[]{Base.afterXn});
		}else{
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("dksqList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载贷款申请列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:38:22
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward getDksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 贷款申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:41:01
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel model = (XyddkModel) form;
		ZxdkCsszService csszService = new ZxdkCsszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			ZxdkCssz csszModel = new ZxdkCssz();
			csszModel= csszService.getModel();
			if (csszModel != null && "1".equals(csszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				
				request.setAttribute("openJtqk", jtqkModel == null);
				
			}
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){								
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
				JtqkdcService jtqkService = new JtqkdcService();
				List<HashMap<String,String>> list = jtqkService.getJtcyList(model.getXh());
				xsjbxx.put("cys", String.valueOf(list.size()));
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		
		List<HashMap<String, String>> jbxxList;
		
		//西安科技大学个性化基本信息显示字段
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		List<HashMap<String,String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("afterxn", Base.afterXn);
		request.setAttribute("mkxxForm", model);
		Calendar a=Calendar.getInstance();
		request.setAttribute("qsxn", a.get(Calendar.YEAR)+"");
		String path = "zxdkXyddk.do?method=dksq";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("dksq");
	}
	
	
	
	/**
	 * 
	 * @描述: 修改贷款申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-26 下午02:08:25
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		ZxdkCsszService csszService = new ZxdkCsszService();
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			ZxdkCssz csszModel = new ZxdkCssz();
			csszModel= csszService.getModel();
			if (csszModel != null && "1".equals(csszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){								
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
				
			}
		}
		List<HashMap<String,String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		
		List<HashMap<String, String>> jbxxList;
		//西安科技大学个性化基本信息显示字段
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("mkxxForm", model);
		String path = "zxdkXyddk.do?method=xgDksq";
		request.setAttribute("path", path);
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("afterxn", Base.afterXn);
		request.setAttribute("cssz", csszService.getModel());
		/**
		 * 华师大个性化代码
		 */
		if("10511".equals(Base.xxdm)){
			List<HashMap<String, String>> nddkList = service.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
			HashMap<String, String> jesxMap = service.getXsxxByHsd(model.getXh());
			request.setAttribute("jesx", jesxMap.get("jesx"));
		}
		this.saveToken(request);
		return mapping.findForward("xgDksq");
	}
	
	
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward ckDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		//困难生认定显示配置
		
		List<HashMap<String,String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", myForm);
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		
		List<HashMap<String, String>> jbxxList;
		//西安科技大学个性化基本信息显示字段
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		request.setAttribute("jbxxList", jbxxList);
		/**
		 * 华师大个性化代码
		 */
		if("10511".equals(Base.xxdm)){
			List<HashMap<String, String>> nddkList = service.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
		}
		return mapping.findForward("ckDksq");
	}
	
	
	
	/**
	 * 
	 * @描述: 贷款审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:37:30
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
	@SystemAuth(url = "zxdk_gjdk_dksh.do")
	public ActionForward dkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_gjdk_dksh.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		if(Globals.XXDM_ZJJDZYJSXY.equals(Base.xxdm)){
			searchModel.setSearch_tj_xn(new String[]{Base.afterXn});
		}else{
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("dkshList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载贷款审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:39:06
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
	@SystemAuth(url = "zxdk_gjdk_dksh.do")
	public ActionForward getDkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 贷款审核
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:40:39
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
	@SystemAuth(url = "zxdk_gjdk_dksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		request.setAttribute("sjxx", service.getShlcInfo(myForm.getId(), myForm.getGwid(),  model.getSplcid()));

		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx;
			xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			//西安科技大学
			if("10704".equalsIgnoreCase(Base.xxdm)){
				SydkSqshService sydkSqshService = new SydkSqshService();
				sydkSqshService.setXsjbxx(xsjbxx, model.getXh());
			}
			
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
        //困难生认定显示配置
		List<HashMap<String,String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		
		
		List<HashMap<String, String>> jbxxList;
		//西安科技大学个性化基本信息显示字段
		if(Base.xxdm.equals("10704")){
			jbxxList = bdpzService.getJbxxpz(GJZXDK_XAKJ);
		}else{			
			jbxxList = bdpzService.getJbxxpz(GJZXDK);
		}
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("jbxxList", jbxxList);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("dksh_zjdx");
		}
		/**
		 * 华师大个性化代码
		 */
		if("10511".equals(Base.xxdm)){
			List<HashMap<String, String>> nddkList = service.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
			HashMap<String, String> jesxMap = service.getXsxxByHsd(model.getXh());
			request.setAttribute("jesx", jesxMap.get("jesx"));
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("dksh");
		
	}
	
	
	
	/**
	 * 
	 * @描述: 删除贷款申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-26 下午03:30:45
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "助学贷款-国家助学贷款-贷款申请-删除values:{id}")
	public ActionForward delDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		boolean result = service.runDelete(new String[]{myForm.getId()}) > 0;
		/**
		 * 如果是华中师范大学,删除xg_hsd_zxdk_ndkb中的内容
		 */
		if("10511".equals(Base.xxdm)){
			service.delNdkb(new String[]{myForm.getId()});
			//service.delFdb(new String[]{myForm.getId()});
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**按学号学年查询记录总数**/
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;
		
		String count = service.getCountByXhAndXn(model);
		response.getWriter().print(count);
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 导出申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-26 下午04:05:10
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
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward dcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;

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
	
	/**打印申请表**/
	@SystemAuth(url = "zxdk_gjdk_dksq.do")
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//一条数据
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//多条数据
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	
	
	private synchronized File print(String id,HttpServletRequest request) throws Exception{

		Map<String, Object> data = new HashMap<String, Object>();
		
		XyddkService service = getService();
		XyddkModel model = service.getModel(id);
		if(Base.xxdm.equalsIgnoreCase("10335") && xgxt.utils.String.StringUtils.isNotNull(model.getXzf())){
			model.setZsfdks(Integer.parseInt(model.getZsfdks())*Integer.parseInt(model.getXzf())+"");
			model.setXfdks(Integer.parseInt(model.getXfdks())*Integer.parseInt(model.getXzf())+"");
		}
		data.put("m", model);
		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		if(Base.xxdm.equalsIgnoreCase("10335")){
			DkjgAction dkjg = new DkjgAction();
			data = dkjg.returnData_10335(data, xsjbxx, model);
			dkjg = null;
		}
		data.put("j", xsjbxx);
		if(Base.xxdm.equalsIgnoreCase("10335")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "zxdksqb_10335.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}
		return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "shhy_gjdk.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
	}
	
	
	
	/**
	 * 
	 * @描述:根据ID查询申请信息
	 * @作者：cq [工号：785]
	 * @日期：2014-12-25 下午03:16:14
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
	public ActionForward dkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		XyddkService service = getService();
		
		HashMap<String, String> dkxxMap = service.getDkxxSq(id);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}
	
	/**
	 * 主要为了增加浙大的个性化
	 */
	public ActionForward checkDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkService service = getService();
		XyddkModel model = (XyddkModel) form;
		
		//查看学生是否已申请
		String count = service.getCountByXhAndXn(model);
		
		//验证续贷情况下学生是否已申请
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz cssz = csszService.getModel();
		if("1"==cssz.getXdKg()){
			model.setXn("");
			count = service.getCountByXhAndXn(model);
		}
		
		if("0".equals(count)){
			//浙江大学个性化
			if(StringUtils.isBlank(model.getXz())){
				model.setXz("0");
			}
			//浙江大学个性化 规则：贷款期限=剩余年份（年级-申请年份）+学制+13年     结果不能大于20 大于20取20
			
			//剩余年份 不可能为负数
			 Calendar a=Calendar.getInstance();
			int synf = Integer.parseInt(model.getNj())-Integer.parseInt(Calendar.getInstance().get(Calendar.YEAR)+"");
			int dkqx = (synf+Integer.parseInt(model.getXz())+13)>20?20:(synf+Integer.parseInt(model.getXz())+13);
//			int dkqx = (synf+13)>20?20:(synf+Integer.parseInt(model.getXz())+13);
			if(Globals.XXDM_ZJDX.equals(Base.xxdm)&&Integer.parseInt(model.getDkqx())>dkqx){
				
				response.getWriter().print(getJsonMessageResult("总贷款年限不允许大于“"+dkqx+"”年！", false));
				
			}else{
				response.getWriter().print(getJsonMessageResult("没事儿啦，我总贷款年限没超要求！,我也不会显示的", true));
			}
			
		}else{
			response.getWriter().print(getJsonMessageResult("该学生已经申请过国家助学贷款，请确认！", false));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 下午04:23:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getHsdDkqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String xh = request.getParameter("xh");
		XyddkService service = new XyddkService();
		HashMap<String, String> xsxx = service.getXsxxByHsd(xh);
		String  currnd = Base.currXn.split("-")[0];
	    String nj = xsxx.get("nj");
	    String xz = xsxx.get("xz");
	    String jesx = xsxx.get("jesx");
	    int njNum = 0 ;
	    int xzNum = 0 ;
	    int jesxNum = 0;
	    String message = "";
	    String rs = "true";
	    HashMap<String, String> rsMap = new HashMap<String, String>();
	    /**
	     * 以下代码是为了防止必要条件为空而引起的计算错误
	     */
		try {
			njNum = Integer.parseInt(nj);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				message = "该学生年级为空，不得进行贷款申请！";
				rsMap.put("message", message);
				rs = "false";
				rsMap.put("rs", rs);
				JSONObject json = JSONObject.fromObject(rsMap);
				response.getWriter().print(json);
				
				return null;
			} catch (IOException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			xzNum = Integer.parseInt(xz);
		} catch (NumberFormatException e) {
			// TODO 自动生成 catch 块
			try {
				message = "该学生学制为空，不得进行贷款申请！";
				rsMap.put("message", message);
				rs = "false";
				rsMap.put("rs", rs);
				JSONObject json = JSONObject.fromObject(rsMap);
				response.getWriter().print(json);
				return null;
			} catch (IOException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			jesxNum = Integer.parseInt(jesx);
		} catch (NumberFormatException e) {
			// TODO 自动生成 catch 块
			try {
				message = "该学生学历层次为空，不得进行贷款申请！";
				rsMap.put("message", message);
				rs = "false";
				rsMap.put("rs", rs);
				JSONObject json = JSONObject.fromObject(rsMap);
				response.getWriter().print(json);
				return null;
			} catch (IOException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		int dkqx = xzNum+njNum-Integer.parseInt(currnd);
		rsMap.put("dkqs", dkqx+"");
		rsMap.put("dkqx", (dkqx*12)+"" );
		rsMap.put("jesx", jesx);
		rsMap.put("currxn", Base.currXn);
		JSONObject json = JSONObject.fromObject(rsMap);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 华师大个性化申请保存草稿
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午10:29:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveDksqForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel)form;
		/**
		 * xns:学年数组
		 * nzsfdks:每年住宿费贷款数组
		 * nxfdks:每年学费贷款数组
		 * nshfdks:每年生活费贷款数组
		 * nzsfyjes:每年住宿费应缴额数组
		 * nxfyjes:每年学费应缴额数组
		 * filepath:上传gid
		 * xh:学号
		 * rs:结果
		 * message:错误信息提示字符串
		 */
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * 检验表单里面是否有同名文件
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "存在同名的pdf文件，请更换pdf文件名称！";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				return null;
			}
		}
		
		/**
		 * 是否存在相同文件名,如果存在直接返回
		 */
	    boolean isFileExist = service.getWjmSameRs(xh, filepath);
		if(isFileExist){
			try {
				message = "历史记录存在同名的pdf文件，请更换pdf文件名称！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 是否已有相同学年的申请记录，如果有直接返回
		 */
		boolean isRecordSameXn = service.getXnXhSameRs(xns, xh,null);
		if(isRecordSameXn){
			try {
				message = "已存在相同学年的申请记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		String id =  UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setId(id);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		try {
			rs = service.runInsert(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!rs){
			/**
			 * 结果为false;直接返回
			 */
			try {
				response.getWriter().print(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 *批量插入xg_hsd_zxdk_ndkb表
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
		/**
		 * 保存失败，删除原先保存的记录
		 */
	    if(!rs){
	    	try {
				service.runDelete(new String[]{id});
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
	    }
	    /**
	     * 如果是申请提交保存
	     */
	    if("submit".equals(model.getType())){
	    	if (rs) {
				try {
					rs = shlc.runSubmit(id, model.getSplcid(), model.getXh(), "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
	    }
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 华师大个性化修改保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午10:31:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveAndSubmitForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XyddkModel model = (XyddkModel)form;
		/**
		 * id:主键id
		 * xns:学年数组
		 * nzsfdks:每年住宿费贷款数组
		 * nxfdks:每年学费贷款数组
		 * nshfdks:每年生活费贷款数组
		 * nzsfyjes:每年住宿费应缴额数组
		 * nxfyjes:每年学费应缴额数组
		 * filepath:上传gid
		 * xh:学号
		 * rs:结果
		 * message:错误信息提示字符串
		 */
		String id = request.getParameter("id");
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * 检验表单里面是否有同名文件
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "存在同名的pdf文件，请更换pdf文件名称！";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				return null;
			}
		}
		/**
		 * 是否存在相同文件名,如果存在直接返回
		 */
	    boolean isFileExist = service.getWjmSameRsUpdate(xh, filepath);
		if(isFileExist){
			try {
				message = "历史记录存在同名的pdf文件，请更换pdf文件名称！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		try {
			rs = service.runUpdate(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			try {
				try {
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
				} catch (IOException e1) {
					// TODO 自动生成 catch 块
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
			return null;
		}
		if(!rs){
			/**
			 * 结果为false;直接返回
			 */
			try {
				response.getWriter().print(getJsonMessageByKey(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 * 修改保存前需要先删除原来xg_hsd_zxdk_ndkb中数据,然后再插入表中
		 */
		try {
			rs = service.delRs(id);
			if(!rs){
				message = "删除xg_hsd_zxdk_ndkb失败，删除id="+id+"！";
				response.getWriter().print(getJsonMessage(message));
			}
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		/**
		 *批量插入xg_hsd_zxdk_ndkb表
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
	    /**
	     * 如果是申请提交保存
	     */
	    if("submit".equals(model.getType())){
	    	if (rs) {
				try {
					rs = shlc.runSubmit(id, service.getModel(id).getSplcid(), model.getXh(), "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
	    }
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 父类撤销方法重写
	 */
	@Override
	public ActionForward cancelAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel t  = (XyddkModel) form;
		XyddkService service = getService();
		User user = getUser(request);
		/**
		 * 华师大判断是否有放贷记录，有放贷记录的记录不允许撤销，直接返回
		 */
		XyddkModel jgModel = new DkjgService().getModel(t.getId());
		if("10511".equals(Base.xxdm)  &&  null != jgModel){
			HsdxdService xdservice = new HsdxdService();
			if(!"0".equals(xdservice.getfdNum(jgModel.getHtbh()))){
				String message = "已有放贷记录， 不允许撤销！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		//更新 申请记录对应审核状态为“审核中”
		t.setShzt(Constants.YW_SHZ);
		XyddkModel model = service.getModel( t);
		
		boolean isSuccess = new CommShlcImpl().runCancel(user.getUserName(), t.getId(), model.getSplcid(), t.getGwid());
		
		if(isSuccess){
			isSuccess = service.runUpdate(t); 
			service.deleteResult(t); 
			//国家助学贷款最后一级撤销删除放贷表
			if("10511".equals(Base.xxdm)){
				service.delFdb(new String[]{t.getId()});
			}
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	/**
	 * 华师大审核前保存
	 */
	public ActionForward checkHtbhIsExists(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XyddkModel t  = (XyddkModel) form;
		String message = "true";
		if(!t.getShzt().equals("1")){
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean flag = new DkjgService().checkHtbhIsNotExists(null, t.getHtbh());
		if(!flag){
			message = "合同编号已存在，请确认！";
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:重写保存方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 下午01:08:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel) form;
		XyddkService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:重写提交方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 下午01:08:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel) form;
		XyddkService service = getService();
		boolean isSuccess = false;
		//设置申请记录对应的审核状态“审核中”
		model.setShzt(Constants.YW_SHZ);
		
		//保证申请ID和审核ID的一致性，由系统生成唯一ID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//保存申请记录
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//提交申请到审核流程
			message = submit("zxdk_xyddk" , model.getId(), "zxdk_gjdk_dksq.do", "zxdk_gjdk_dksh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:重写提交方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 下午01:08:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {

		ShlcInterface shlc = new CommShlcImpl();
		
		XyddkService service = getService();
		//查询申请记录
		XyddkModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//提交申请流程
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//更新申请记录状态
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}
	 /**
	 * @描述:下载
	 * @作者：lgx[工号:1553]
	 * @日期：2018-5-15 下午17:33:55
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
	
	public ActionForward printJsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkService service = new XyddkService();
		XyddkModel modelForm = (XyddkModel) form;
		XyddkModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	//填充模版数据生成word文件
	private File getWord(XyddkModel xyddkModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		XyddkModel model = xyddkModel;
		String xh = model.getXh();
		//基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		String rxny = xsjbxx.get("rxrq");
		if(rxny.length() >= 7) 
			rxny = rxny.substring(0, 7);
		String dkqx = model.getDkqx();
		if(StringUtil.isNull(dkqx)) {
			data.put("dknx", "");
		}else{
			double dknx = Double.parseDouble(dkqx)/12.0;
			data.put("dknx", dknx);
		}
		
		xsjbxx.put("rxny", rxny);
		data.put("rs", xsjbxx);
		data.put("model", model);
		//困难类型信息
		KnsjgService knservice = new KnsjgService();
		HashMap<String, String> knmap = knservice.getKnsInfo(model.getXh(),model.getXn());
		data.put("knlx", knmap.get("dcmc"));
		data.put("knyy", knmap.get("sqly"));
		
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "zxdk","jsdkxxcjb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}

}

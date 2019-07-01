/**
 * @部门:学工产品事业部
 * @日期：2016-12-22 下午01:36:03 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.io.File;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-12-22 下午01:36:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxjlAction extends SuperAction<ZxzxjlModel, ZxzxjlService>{
	private ZxzxjlService service = new ZxzxjlService();
	private static List<HashMap<String, String>> jbxxList = null;
	public static String ZXZXJL = "zxzxjl";
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZXZXJL);
	}
	
	private static final String url = "xlzx_zxzx_zxzxjl.do";
	
	/** 
	 * @描述:得到在线咨询列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午01:59:24
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
	public ActionForward getZxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
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
		String path = "xlzx_zxzx_zxzxjl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZxList");
	}
	
	/** 
	 * @描述:增加咨询基本信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:59:18
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
	public ActionForward addZxJbxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		//如果为保存
		if("save".equalsIgnoreCase(model.getType())){
			boolean result = false;
			if(service.isExists(model)){
 				String messageKey = MessageKey.XLZX_ZXZXJL_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
			User user = getUser(request);
			model.setTxr(user.getUserName());
			result = service.addZxjbxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{			
			User user = getUser(request);
			if ("stu".equals(user.getUserType())) {
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())) {
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}		
			String path = "zxzx_zxzxjl.do?method=addZxJbxx&type=add";
			request.setAttribute("path", path);
			request.setAttribute("jbxxList", jbxxList);
			setXxList(request,service);
			return mapping.findForward("addZxJbxx");
		}
	}
	
	/** 
	 * @描述:修改咨询基本信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午04:24:51
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
	public ActionForward updateZxJbxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		if("save".equalsIgnoreCase(model.getType())){			
			boolean result = service.updateZxjbxx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{			
			User user = getUser(request);
			if ("stu".equals(user.getUserType())) {
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())) {
				// 加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			ZxzxjlModel myform = service.getModel(model);
			if (null != myform) {
				BeanUtils.copyProperties(model, StringUtils.formatData(myform));
				if(null != myform.getYzxwt() && !"".equalsIgnoreCase(myform.getYzxwt())){
					String[] wts = myform.getYzxwt().split(",");
					List<String> list = new ArrayList<String>();
					for(int i = 0;i<wts.length;i++){
						list.add(wts[i]);
						request.setAttribute("wtList", list);
					}
					model.setYzxwts(wts);
				}								
			}
			String path = "zxzx_zxzxjl.do?method=updateZxJbxx&type=update";
			request.setAttribute("path", path);
			request.setAttribute("jbxxList", jbxxList);
			setXxList(request,service);
			return mapping.findForward("updateZxJbxx");
		}
	}
	
	/** 
	 * @描述:删除咨询记录基本信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午04:39:16
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
	public ActionForward delZxJbxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.deleteZxjbxx(ids);
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
	 * @描述:维护保存在线咨询记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 上午09:29:06
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
	public ActionForward whbcZxzxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		String[] zjs = model.getZjs();//获取增加数据
		String[] xgs = model.getXgs();//获取修改数据
		String[] delIds = model.getDelIds();//获取删除数据
		ZxzxjlService jlService = new ZxzxjlService();
		User user = getUser(request);
		boolean result = jlService.whZxzxJl(zjs, xgs, delIds, model.getXh(), user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:维护在线咨询记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 下午02:00:38
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
	public ActionForward whZxzxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		ZxzxjlModel myform = service.getModel(model);
		if (null != myform) {
			BeanUtils.copyProperties(model, myform);								
		}
		User user = getUser(request);
		List<HashMap<String,String>> jlList = service.getZxjlList(model.getXh(),user,false);
		if(null != jlList && jlList.size()>0){
			request.setAttribute("jlList", jlList);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("userNameReal", user.getRealName());
		request.setAttribute("rs", model);
		return mapping.findForward("whZxzxjl");
		
	} 
	
	
	/** 
	 * @描述:设置各个选项（增加，修改页面使用）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:49:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param service
	 * void 返回类型 
	 * @throws 
	 */
	public static void setXxList(HttpServletRequest request,ZxzxjlService service){
		List<HashMap<String, String>> sfList = service.getSfList();//是否
		List<HashMap<String, String>> ywList = service.getYwList();//有无
		List<HashMap<String, String>> jjzkList = service.getJjzkList();//经济状况
		List<HashMap<String, String>> jtszdList = service.getJtszdList();//家庭所在地
		List<HashMap<String, String>> hyzkList = service.getHyzkList();//婚姻状况
		List<HashMap<String, String>> zxwtList = service.getZxwtList();//咨询问题
		List<HashMap<String, String>> whcdList = service.getWhcdList();//文化程度
		List<HashMap<String, String>> xhcdList = service.getXhcdList();//喜欢程度
		request.setAttribute("sfList", sfList);
		request.setAttribute("ywList", ywList);
		request.setAttribute("jjzkList", jjzkList);
		request.setAttribute("jtszdList", jtszdList);
		request.setAttribute("hyzkList", hyzkList);
		request.setAttribute("zxwtList", zxwtList);
		request.setAttribute("whcdList", whcdList);
		request.setAttribute("xhcdList", xhcdList);	
	}
	
	/** 
	 * @描述:查看(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-29 下午01:48:39
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
	public ActionForward ckZxzxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZxzxjlModel model = (ZxzxjlModel) form;
		request.setAttribute("jbxxList", jbxxList);
		ZxzxjlModel myform = service.getModel(model);
		BeanUtils.copyProperties(model,myform);
		request.setAttribute("rs", model);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			User user = getUser(request);
			List<HashMap<String,String>> jlList = service.getZxjlList(model.getXh(),user,true);
			if(null != jlList && jlList.size()>0){
				request.setAttribute("jlList", jlList);
			}
		}
		return mapping.findForward("ckZxzxjl");
		
	}
	
	/** 
	 * @描述:导出(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-29 下午04:00:13
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
		ZxzxjlModel model = (ZxzxjlModel) form;
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
	 * @throws Exception 
	 * 
	 * @描述:导出心理咨询登记表【单个】
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-5 上午10:43:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ExportxlzxDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		File wordFile = getDjbWord(xh,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:导出心理咨询登记表【批量】
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-5 上午10:43:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ExportxlzxDjbPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getDjbWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:获取登记表word
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-5 上午11:37:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getDjbWord(String xh,HttpServletRequest request) throws Exception{

		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String, Object>();
		HashMap<String, String> dataMap = service.getExportData(xh, user);
		dataMap.put("wtbc", HtmlUtil.xmlZy(dataMap.get("wtbc")));
		dataMap.put("zxqw", HtmlUtil.xmlZy(dataMap.get("zxqw")));
		data.putAll(dataMap);
		
		//分割欲咨询问题
		List<HashMap<String, String>> zxwtList = service.getZxwtList();
		Map<String,Boolean> zxwtMap = new HashMap<String,Boolean>();
		String[] zxwtlb = new String[zxwtList.size()];
		for(int i=0;i < zxwtList.size();i++){
			zxwtlb[i] = zxwtList.get(i).get("mc");
			zxwtMap.put("zxwt"+i, false);
		}
		String[] zxwts=new String[]{};
		String yzxwt = dataMap.get("yzxwt");
		if(StringUtils.isNotNull(yzxwt)){
			zxwts = yzxwt.split(",");
			for(int i=0;i < zxwts.length;i++){
				for(int j=0;j < zxwtlb.length;j++){
					if(zxwtlb[j].equals(zxwts[i])){
						zxwtMap.put("zxwt"+j, true);
						break;
					}
				}
			}
		}
		data.put("zxwtMap", zxwtMap);
		
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xlzx","xlzxdcb.xml",dataMap.get("xh")+dataMap.get("xm"));
		return file;
	}

	
	/**
	 * 
	 * @描述:打印咨询记录表(单个)
	 * @作者：tgj[工号：1075]
	 * @日期：2017-6-5 下午02:21:40
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
	public ActionForward ExportxlzxjlDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		File wordFile = getZxjlDjbWord(xh,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述:打印咨询记录表(批量)
	 * @作者：tgj[工号：1075]
	 * @日期：2017-6-5 下午02:22:41
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
	public ActionForward ExportxlzxjlDjbPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getZxjlDjbWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:获取咨询记录
	 * @作者：tgj[工号：1075]
	 * @日期：2017-6-5 下午02:23:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getZxjlDjbWord(String xh,HttpServletRequest request) throws Exception{

		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String, Object>();
		
		List<HashMap<String,String>> zxjlList = service.getZxjlList(xh,user,false);

		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> mapp;
		for(HashMap<String, String> map:zxjlList){
			mapp = HtmlUtil.formatXmlMap(map);
			mapList.add(mapp);
		}
		data.put("zxjlList", mapList);
		String fileName = null;
		if(null == zxjlList || zxjlList.size()==0){
			fileName = xh + "无咨询记录";
		} else {
			fileName = zxjlList.get(0).get("xh")+zxjlList.get(0).get("xsxm");
		}
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xlzx","xlzxjldcb.xml", fileName);
		return file;
	}
	
	/** 
	 * @描述:获取记录id(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-27 下午03:04:35
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
	public ActionForward getIds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZxzxjlModel model = (ZxzxjlModel) form;
		List<HashMap<String,String>> list = service.getZxIdList(model.getXh());
		if(null != list && list.size()>0){
			response.getWriter().print(JSONArray.fromObject(list));
		}
		return null;
	}
}

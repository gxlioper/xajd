/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:22:49 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.jg;

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
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.cxdd.comm.CommForm;
import com.zfsoft.xgxt.xsxx.cxdd.comm.CommUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-28 下午05:22:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddJgAction extends SuperAction<CxddJgForm, CxddJgService> {
	private final String CXDD ="cxdd";
	private CxddJgService service = new  CxddJgService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "xsxx_cxdd_pyjg.do";
	public ActionForward getPyjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
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
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnlist", Base.getXnndList());
		request.setAttribute("xqlist", Base.getXqList());
		request.setAttribute("cxdjlist", service.getCxdjList(null));
		String path = "cxdd_pyjg.do?method=add";
		request.setAttribute("path", path);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		return mapping.findForward("add");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		CxddJgForm myForm = service.getModel(model);
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDD);
		request.setAttribute("jbxxList", jbxxList);
//		request.setAttribute("xnlist", Base.getXnndList());
//		request.setAttribute("xqlist", Base.getXqList());
		for (HashMap<String, String> hashMap : Base.getXqList()) {
			if(myForm.getXq().equalsIgnoreCase(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("cxdjlist", service.getCxdjList(null));
		String path = "cxdd_pyjg.do?method=edit";
		request.setAttribute("path", path);
		return mapping.findForward("edit");
	}
	
	public ActionForward saveCxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		boolean flag = true;
		CommForm commform = new CommForm();
		CommUtil util = new CommUtil();
		commform.setXh(model.getXh());
		commform.setXq(model.getXq());
		commform.setXn(model.getXn());
		if("save".equalsIgnoreCase(model.getType())){
			commform.setType("jg");
		}else{
			commform.setType("jgedit");
			commform.setId(model.getXsid());
		}
		
		flag = util.isNotExists(commform);
		if(flag){
			if("save".equalsIgnoreCase(model.getType())){
			     model.setBjdm(service.getbjdm(model.getXh()));
			     flag = service.runInsert(model);
			}else{
		         flag = service.runUpdate(model);
			}
		}else{
			String message = MessageUtil.getText(MessageKey.XSXX_CXDD_REPEAT,model.getXh());;
			response.getWriter().print(getJsonMessage(message));
			  return null;
		}
		
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//删除
	public ActionForward delCxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	
	//导出
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxddJgForm model = (CxddJgForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页
		
		//因为学历名称和在外居住原因只能获取代码值，因此这里循环遍历set值

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
	
	//查看
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm model = (CxddJgForm)form;
		model = service.getModel(model);
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXDD);
		request.setAttribute("jbxxList", jbxxList);
		for (HashMap<String, String> hashMap : Base.getXqList()) {
			if(model.getXq().equalsIgnoreCase(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("cxdj", service.getCxdjList(model.getPj()).get(0));
		request.setAttribute("jg", model);
		String path = "cxdd_pyjg.do?method=view";
		request.setAttribute("path", path);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @描述: 下载成绩报告单
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-19 下午04:21:36
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
	public ActionForward exportCjbgd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CxddJgForm myForm = (CxddJgForm) form;
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getWord(CxddJgForm myForm,HttpServletRequest request) throws Exception{
		File file = null;
		CxddJgForm model = service.getModel(myForm);
		HashMap<String, String> xsxxjbxx = service.getXsjbxx(model.getXh());
		Map<String,Object> data = new HashMap<String,Object>();
		data.putAll(xsxxjbxx);
		String bjrs = service.getBjrs(xsxxjbxx.get("bjdm"));
		data.put("bjrs",bjrs);
		String xnxqStr = service.getXnxqStr(model.getXn(), model.getXq());
		String title = xnxqStr + "  "+xsxxjbxx.get("bjmc");
		data.put("title",title);
		List<HashMap<String, String>> cjList = service.getCjList(model);
		/**
		 * 成绩处理，写死八项课程八项成绩
		 */
		HashMap<String,String> cjMap = new HashMap<String, String>();
		for (int i = 1; i < 11; i++) {
			cjMap.put("kcmc"+i,"");
			cjMap.put("cj"+i,"");
		}
		for (int i = 1;  i< cjList.size()+1; i++) {
			cjMap.put("kcmc"+i,cjList.get(i-1).get("kcmc"));
			cjMap.put("cj"+i,cjList.get(i-1).get("cj"));
		}
		data.putAll(cjMap);
		data.putAll(service.getKxCsszb());
		data.putAll(service.getXfsj(model.getXh()));
		data.put("py", HtmlUtil.xmlZy(model.getPy()));
		List<HashMap<String, String>> pjjglist = service.getPjjgList(model);
		List<HashMap<String, String>> wjcflist = service.getWjcfList(model);
		data.put("pjjglist",pjjglist);
		data.put("wjcflist",wjcflist);
		//data.put("pjcj", service.getZcfsList(model, "平均文化成绩"));
		//修改为全部成绩相加获取平均成绩（徐州医药）
		data.put("pjcj", service.getPjcjAndPm(model));
		data.put("dyxf", service.getZcfsList(model, "德育学分"));
		//修改为平均分0.6+德育学分*0.4=综合分数（徐州医药）
		data.put("zhcj", service.getZcfsTotal(model, "德育学分"));
		//data.put("zhcj", service.getZcfsList(model, "综测总分"));
		data.put("bzr", new XsxxDao().getBzrxxByXh(model.getXh()));
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"xsxx/xscjbgd.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"xsxx","xscjbgd.xml",FreeMarkerUtil.getFileName(xsxxjbxx.get("xm")+"_成绩报告单"));
		}catch (Exception e) {
			file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"xsxx","xscjbgd.xml", FreeMarkerUtil.getFileName(xsxxjbxx.get("xm")+"_成绩报告单"));
		}
		return file;
	}
	
	/**
	 * 
	 * @描述: 下载成绩报告单批量打包
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-19 下午04:24:43
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
	public ActionForward getCjbgdZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddJgForm myForm = (CxddJgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setXsid(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 开学信息设置
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-9 下午02:47:11
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
	public ActionForward kxxxSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("data", service.getKxCsszb());
		return mapping.findForward("kxxxsz");
	}
	
	/**
	 * 
	 * @描述: 保存开学信息设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-9 下午02:51:31
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
	public ActionForward saveKxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CxddJgForm myForm = (CxddJgForm) form;
		CxddJgService tranService = TransactionControlProxy.newProxy(new CxddJgService());
		boolean rs = tranService.saveKxxx(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}

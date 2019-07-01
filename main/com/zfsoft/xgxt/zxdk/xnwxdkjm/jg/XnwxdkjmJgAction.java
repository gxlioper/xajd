/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 下午02:52:40 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.jg;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.xnwxdk.jg.XnwxdkJgModel;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqModel;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqService;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.util.Util;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.util.UtilForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-24 下午02:52:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkjmJgAction extends SuperAction<XnwxdkjmJgModel, XnwxdkjmJgService> {
	private final String XNWXDK="xnwxdk";
	private XnwxdkjmJgService service = new  XnwxdkjmJgService();
	
	private static final String url = "zxdk_xnwxdkjm_jg.do";
	
	/**
	 * 校内无息贷款申请初始化查询界面
	 */
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2016-2-29 下午01:52:46
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
	public ActionForward getXnwxdkjgCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmJgModel model = (XnwxdkjmJgModel) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "zxdk_xnwxdkjm_jg.do";
		request.setAttribute("path", path);
		model.setXh(user.getUserName());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 校内无息贷款减免申请增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmJgModel model = (XnwxdkjmJgModel) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xnwxdkjm_jg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("xn", Base.currXn);
		model.setXq(Base.currXq);
		model.setXn(Base.currXn);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(Base.currXq.equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		Util util = new Util();
		request.setAttribute("jmllist",util.getJml());
//		if( util != null  && util.getJml().size()>0 ){
//			request.setAttribute("jmyjlist",util.getjmyjList(util.getJml().get(0).get("jmbl")));
//		}else{
//			request.setAttribute("jmyjlist","");
//		}
		
		//其他信息配置
		return mapping.findForward("add");
	}
	
	/**
	 * 校内无息贷款减免申请保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveDksq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmJgModel model = (XnwxdkjmJgModel) form;
		String[] items = request.getParameterValues("jmyj");
		String jmyj = StringUtils.joinArr(items);
		model.setJmyj(jmyj);
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		Util util = new Util();
		UtilForm utilform = new UtilForm();
		utilform.setType("jg");
		utilform.setXh(model.getXh());
		if(model.getType().equals("save")||model.getType().equals("submit")){
			boolean isExist = util.isNotExists(utilform);
			// 判断当前学生是否有记录
			if (!isExist) {
				message = MessageUtil.getText(MessageKey.ZXDK_WXDKSQ_REPEATED,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveDksq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
//			utilform.setType("jg");
//			boolean isExist = util.isNotExists(utilform);
//			if (!isExist) {
//				message = MessageUtil.getText(MessageKey.ZXDK_WXDKSQ_REPEATED,model.getXh());;
//				response.getWriter().print(getJsonMessage(message));
//				return null;
//			}
			result = service.saveDksqUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 住宿申请删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delDksq(ActionMapping mapping, ActionForm form,
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
	 * 校内无息贷款申请查看
	 */
	@SystemAuth(url = url)
	public ActionForward DksqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmJgModel myForm = (XnwxdkjmJgModel) form;
		XnwxdkjmJgModel model = service.getModel(myForm);
		Util util = new Util();
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", model);
		request.setAttribute("jmyjlist", util.getJmlCk(model.getJmyj().split(","), model.getJmbl()));
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		String zje = util.getXsxxWxjkzh(model.getXh());
		request.setAttribute("zje", zje);
		return mapping.findForward("view");
	}
	
	/**
	 * 校内无息贷款申请修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDksq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmJgModel myForm = (XnwxdkjmJgModel) form;
		XnwxdkjmJgModel model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XNWXDK);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xnwxdkjm_jg.do?method=editDksq";
		request.setAttribute("path", path);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		Util util = new Util();
		request.setAttribute("jmllist",util.getJml());
		request.setAttribute("jmyjlist",util.getjmyjList(model.getJmbl()));
		request.setAttribute("xn", model.getXn());
		String zje = util.getXsxxWxjkzh(model.getXh());
		request.setAttribute("zje", zje);
		request.setAttribute("checkboxvlaue", model.getJmyj());
		return mapping.findForward("update");
	}
	

	
	
	
	/**
	 * 校外住宿结果导出
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XnwxdkjmJgModel model = (XnwxdkjmJgModel) form;

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
	
	/**
	 * 
	 *申请表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getXnwxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnwxdkjmJgModel myForm = (XnwxdkjmJgModel) form;
		File wordFile = getXnwxdkWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getXnwxdkWord(XnwxdkjmJgModel myForm,HttpServletRequest request) throws Exception{

		User user = getUser(request); 
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
//		HashMap<String, String> XnwxdkMap = null;
		XnwxdkjmJgModel dkxx = service.getModel(myForm.getJgid());
		data.put("dkxx", dkxx);
		String sqbl = dkxx.getJmbl();
		if(StringUtils.isNotNull(dkxx.getSjly())&&dkxx.getSjly().equalsIgnoreCase("1")){
			XnwxdkjmsqModel jmModel = new XnwxdkjmsqService().getModel(dkxx.getJgid());
			sqbl = jmModel.getJmbl();
		}
//		data.put("xyzsjg", myForm);
		data.put("sqbl", sqbl);
		data.put("zzbl", dkxx.getJmbl());
		Util util = new Util();
		HashMap<String, String> xsxx = util.getxsxx(dkxx.getXh());
		data.putAll(xsxx);
		String dkzje = util.getXsxxWxjkzh(dkxx.getXh());
		String jml = dkxx.getJmbl().replace("%", "");
		data.put("dkzje", dkzje);
		data.put("jmje", (Float.parseFloat(dkzje)*Float.parseFloat(jml))/100);
		data.put("jtcylist", util.getjtcyxx(dkxx.getXh()));
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//zxdk","zjdx_wxjm_sq.xml",myForm.getJgid()+dkxx.getXh());
		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gettXnwxdkTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnwxdkjmJgModel myForm = (XnwxdkjmJgModel) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setJgid(values[i]);
				File file = getXnwxdkWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//校内无息贷款汇总表导出
	public ActionForward getHzbexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XnwxdkjmJgModel model = (XnwxdkjmJgModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		List<HashMap<String, String>>  resultList = null;
		String xydm = request.getParameter("xydm");
		String nj = request.getParameter("nj");
		// 查询
		Util util = new Util();
		resultList = util.getWxjmHz(xydm, nj);
		// 查询出所有记录，不分页
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("hzlist",resultList);
		data.put("xymc", request.getParameter("xymc"));
		data.put("nj",request.getParameter("nj"));
		File  file = FreeMarkerUtil.getExcelFile(data, "classpath://templates//zxdk", "zjdx_wxdkjm_hz.xml", "浙江大学无息借款减免汇总表");
		FileUtil.outputExcel(response, file);
		return null;
    }

}

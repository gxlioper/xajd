/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:40:21 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.jg;

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
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszService;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.Util;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.UtilForm;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:40:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkJgAction extends SuperAction<XnwxdkJgModel, XnwxdkJgService> {
	private final String XNWXDK="xnwxdk";
	private XnwxdkJgService service = new  XnwxdkJgService();
	
	private static final String url = "zxdk_xnwxdk_jg.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 校内无息贷款申请初始化查询界面
	 */
	@SystemAuth(url = url)
	public ActionForward getXnwxdkjgCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkJgModel model = (XnwxdkJgModel) form;
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
//		XnwxdkCsszService csszService = new XnwxdkCsszService();
//		String sqkg = csszService.getSqShKg();
//		request.setAttribute("sqkg", sqkg==null?"0":sqkg);
		String path = "zxdk_xnwxdk_jg.do";
		request.setAttribute("path", path);
		model.setXh(user.getUserName());
		UtilForm utilform = new UtilForm();
		Util util = new Util();
		utilform.setXn(Base.currXn);
		utilform.setXq(Base.currXq);
		utilform.setXh(user.getUserName());
		utilform.setType("jg");
		boolean isExist = util.isNotExists(utilform);
		String cfbz = "0";
		if(!isExist){
			cfbz = "1";
		}
		request.setAttribute("cfbz", cfbz);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 校内无息贷款申请增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkJgModel model = (XnwxdkJgModel) form;
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
		String path = "xnwxdk_jg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("xn", Base.currXn);
		model.setXq(Base.currXq);
		model.setXn(Base.currXn);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(Base.currXq.equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("jesx", service.getJesx());
		//其他信息配置
		return mapping.findForward("add");
	}
	
	/**
	 * 校内无息贷款申请保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveDkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkJgModel model = (XnwxdkJgModel) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		Util util = new Util();
		UtilForm utilform = new UtilForm();
		utilform.setType("jg");
		utilform.setXh(model.getXh());
		utilform.setXn(Base.currXn);
		utilform.setXq(Base.currXq);
		
		if(model.getType().equals("save")||model.getType().equals("submit")){
			boolean isExist = util.isNotExists(utilform);
			// 判断当前学生是否有校外住宿结果
			if (!isExist) {
				message = MessageUtil.getText(MessageKey.ZXDK_WXDKSQ_REPEATED,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveDkjg(model, user);
		}else if(model.getType().equals("update")){
			result = service.saveDkjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 贷款申请删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delDkjg(ActionMapping mapping, ActionForm form,
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
	 * 校外住宿申请查看
	 */
	@SystemAuth(url = url)
	public ActionForward DkjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkJgModel myForm = (XnwxdkJgModel) form;
		XnwxdkJgModel model = service.getModel(myForm);
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
//		HashMap<String, String> xl = service.getXlCk(model);
//		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", model);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
//		request.setAttribute("zsjgxx", StringUtils.formatData(model));
//		request.setAttribute("xlxx", xl);
//		request.setAttribute("xyjzyy", StringUtils.formatData(xyjzyy));
		return mapping.findForward("view");
	}
	
	/**
	 * 校外住宿结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkJgModel myForm = (XnwxdkJgModel) form;
		XnwxdkJgModel model = service.getModel(myForm);
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
		String path = "xnwxdk_sq.do?method=editDksq";
		request.setAttribute("path", path);
		XnwxdkCsszService jcszService = new XnwxdkCsszService();
		String sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg);
		for (HashMap<String, String> hashMap : Base.getXqList()){
			if(model.getXq().equals(hashMap.get("xqdm"))){
				request.setAttribute("xqmc", hashMap.get("xqmc"));
			}
		}
		request.setAttribute("xn", model.getXn());
		request.setAttribute("jesx", service.getJesx());
		return mapping.findForward("update");
	}
	
	
	/**
	 * 住宿结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XnwxdkJgModel model = (XnwxdkJgModel) form;

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
		XnwxdkJgModel myForm = (XnwxdkJgModel) form;
		File wordFile = getXnwxdkWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getXnwxdkWord(XnwxdkJgModel myForm,HttpServletRequest request) throws Exception{

		User user = getUser(request); 
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
//		HashMap<String, String> XnwxdkMap = null;
		XnwxdkJgModel dkxx = service.getModel(myForm.getJgid());
		String sqly = HtmlUtil.xmlZy(dkxx.getSqly());
		dkxx.setSqly(sqly);
		data.put("dkxx", dkxx);
//		data.put("xyzsjg", myForm);
		Util util = new Util();
		data.putAll(util.getxsxx(dkxx.getXh()));
		data.put("jtcylist", util.getjtcyxx(dkxx.getXh()));
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//zxdk","zjdx_xnwxdk.xml",myForm.getJgid()+dkxx.getXh());
		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gettXnwxdkTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnwxdkJgModel myForm = (XnwxdkJgModel) form;
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
		XnwxdkJgModel model = (XnwxdkJgModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		List<HashMap<String, String>>  resultList = null;
		// 查询
		 resultList = service.getAllList(model,
				user);
		// 查询出所有记录，不分页
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("hzlist",resultList);
		data.put("tbr", user.getRealName());
		data.put("rows", resultList.size()+4);
		data.put("sysdate", GetTime.getTimeByFormat(DATE_FORMAT));
		File  file = FreeMarkerUtil.getExcelFile(data, "classpath://templates//zxdk", "zjdx_xnwxdkhz.xml", "浙江大学无息贷款汇总表");
		FileUtil.outputExcel(response, file);
		return null;
    }
}

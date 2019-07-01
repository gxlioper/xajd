/**
 * @部门:学工产品事业部
 * @日期：2016-02-18 上午10:40:41 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtjg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgForm;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.ystgl.rtgl.rtjg.YstRtjgForm;
import com.zfsoft.xgxt.ystgl.rtgl.rtsq.YstRtsqForm;
import com.zfsoft.xgxt.ystgl.stzhwh.StzhwhService;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-02-18 上午10:40:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstRtjgAction extends SuperAction<YstRtjgForm, YstRtjgService> {
	private YstRtjgService service = new  YstRtjgService();
	private final String YstRtjg ="rtsq";
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "ystgl_rtgl_rtjg.do";
	/**
	 * 入团结果查询
	 */
	@SystemAuth(url = url)
	public ActionForward getYstRtjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    YstRtjgForm model = (YstRtjgForm) form;
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
		searchModel.setSearch_tj_tnzt(new String[]{"正常"});
		request.setAttribute("searchTj", searchModel);
		String path = "ystgl_rtgl_rtjg.do";
		request.setAttribute("path", path);
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYstRtjgList");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtjgForm model = (YstRtjgForm) form;
	    User user = getUser(request);
	    if(user.getUserType().equalsIgnoreCase("stu")){
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtjg);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("currxn", Base.currXn);
		String path = "ystglRtjg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("usertype",user.getUserType());
		//其他信息配置
		return mapping.findForward("addYstRtjg");
	}
	/**
	 * 
	 * @描述:入团结果修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-2-19 下午03:21:01
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
	public ActionForward editYstRtjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		YstRtjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtjg);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> ystxx = service.getYstxxMap(model);
		request.setAttribute("ystxx", StringUtils.formatData(ystxx));
		String path = "ystglRtjg.do?method=editYstRtjg";
		request.setAttribute("path", path);
		return mapping.findForward("editYstRtjg");
	}
	/**
	 * 
	 * @描述:删除入团结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-2-19 下午03:37:50
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
	@SystemLog(description = "访问艺术团管理-入团结果-删除-rtid:{rtid}")
	public ActionForward delYstRtjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhService stzhwhService = new StzhwhService();
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			stzhwhService.delCycj(ids);
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
	 * 入团结果信息查看
	 */
	@SystemAuth(url = url)
	public ActionForward viewYstRtjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		YstRtjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtjg);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> ystxx = service.getYstxxMap(myForm);
		request.setAttribute("ystxx", ystxx);
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		return mapping.findForward("viewYstRtjg");
	}
	
	/**
	 * 入团结果信息导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YstRtjgForm model = (YstRtjgForm) form;

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
	
	
	//社团结果成员信息修改（维护）--老师
	@SystemLog
	public ActionForward YstRtjgWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String stid = request.getParameter("stid");
			String rtid = request.getParameter("rtid");
			User user = getUser(request);
			YstRtjgForm YstRtjg = new YstRtjgForm();
			YstRtjg.setYstid(stid);
			YstRtjg.setRtid(rtid);
			StjgForm myForm = new StjgForm();
			//myForm.setYstid(stid);
			StjgService stjg = new StjgService();
			StjgForm model = stjg.getModel(myForm);
			HashMap<String,String> stjgMap = stjg.getStjg(model);
			request.setAttribute("rs", StringUtils.formatData(stjgMap));
			request.setAttribute("usertype", user.getUserType());
			return mapping.findForward("viewwh");
	}
	
	//入团结果维护添加学生，学生选择页面
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList=null;
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		String path = "stglYstRtjg.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	
	//结果维护保存
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问艺术团管理-入团结果-保存-rtid:{rtid}")
	public ActionForward saveYstRtjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		boolean result = service.editYstRtjg(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//学生结果维护
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJgwh_XS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		String message = "";
		boolean result = true;
		myForm.setRtxn(Base.currXn);
		myForm.setRtxq(Base.currXq);
		myForm.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		result = service.runUpdate(myForm);
		message = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	
	private File getWord(YstRtjgForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		XsxxService xsxxService = new XsxxService();
		String xh = myForm.getXh();
		User user = getUser(request);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);//学生基本信息
		//学生照片
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("photo", photo);
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","ystbmb_10277.xml",xh+"-"+xsxxMap.get("xm"));
	}
	
	public ActionForward getYstbmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	public ActionForward getYstbmbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YstRtjgForm myForm = (YstRtjgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
}

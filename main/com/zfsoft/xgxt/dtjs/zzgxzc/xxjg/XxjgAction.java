/**
 * @部门:学工产品事业部
 * @日期：2017-1-24 下午03:56:38 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.zzgxzc.gprz.GprzForm;
import com.zfsoft.xgxt.dtjs.zzgxzc.gprz.GprzService;
import com.zfsoft.xgxt.dtjs.zzgxzc.zcsq.ZcsqSERVICE;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设--信息结果模块
 * @类功能描述: 信息结果Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月13日 上午10:37:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XxjgAction extends SuperAction<XxjgForm,XxjgService>{
	private static final String url = "dtjs_xxjg.do?method=xxjgList";//数据库里的
	private final String ZCSQ ="zcsq";
	XxjgService xxjgService = new XxjgService();
	
	/**
	 * @描述:信息结果查询
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 上午10:39:21
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
	public ActionForward xxjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		
		if (QUERY.equalsIgnoreCase(xxjgForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			xxjgForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = xxjgService.getPageList(xxjgForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxjgList");
	}
	
	/**
	 * 
	 * @描述:信息结果增加
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 下午4:53:47
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
	@SystemLog(description="访问党团建设-组织关系转出-信息结果-增加XH:{xh},SZDZB:{szdzb},"
			+ "SFSN:{sfsn},JSDZZ:{jsdzz},SQDW:{sqdw},DFJZRQ:{dfjzrq},SFKJHYZM:{sfkjhyzm},JSXBH:{jsxbh}")
	public ActionForward xxjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XxjgForm xxjgForm = (XxjgForm)form;
		if (SAVE.equalsIgnoreCase(xxjgForm.getType())){
			if(xxjgService.isExist(xxjgForm)){
				//该学生已存在记录
				String messageKey = MessageKey.DTJS_ZZGXZC_XXJG_EXIST;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			if(xxjgService.isJsxbhRepeat(xxjgForm)){
				//介绍信编号重复使用
				String messageKey = MessageKey.DTJS_ZZGXZC_XXJG_JSXBH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			//党费交至日期处理（转为每月的最后一天）
			xxjgForm.setDfjzrq(DateUtils.getLastDayOfMonth(xxjgForm.getDfjzrq()));
			
			boolean result = xxjgService.runInsert(xxjgForm);
			String messageKey = null;
			if(result){
				messageKey = MessageKey.SYS_SAVE_SUCCESS;
				
				//插入改派日志
				GprzService gprzService = new GprzService();
				String xgsj = DateUtils.getCurrDate();
				String xgr = getUser(request).getUserName();
				String xghjl = gprzService.getXgqhjl(xxjgForm);
				GprzForm gprzForm = new GprzForm();
				gprzForm.setXgsj(xgsj);
				gprzForm.setXgr(xgr);
				gprzForm.setXh(xxjgForm.getXh());
				gprzForm.setXghjl(xghjl);
				gprzService.runInsert(gprzForm);
			}else{
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		if (!StringUtil.isNull(xxjgForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xxjgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		String path = "dtjs_xxjg.do?method=xxjgAdd";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",new ZcsqSERVICE().getDzbList());
		return mapping.findForward("xxjgAdd");
	}
	
	/**
	 * 
	 * @描述:信息结果更新
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 下午4:53:47
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
	@SystemLog(description="访问党团建设-组织关系转出-信息结果-修改XH:{xh},SZDZB:{szdzb},"
			+ "SFSN:{sfsn},JSDZZ:{jsdzz},SQDW:{sqdw},DFJZRQ:{dfjzrq},SFKJHYZM:{sfkjhyzm},JSXBH:{jsxbh},GPYY:{gpyy}")
	public ActionForward xxjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		XxjgForm xf = xxjgService.getModel(xxjgForm.getJgid());
		
		if (UPDATE.equalsIgnoreCase(xxjgForm.getType())){
			if(xxjgService.isJsxbhRepeat(xxjgForm)){
				//介绍信编号重复使用
				String messageKey = MessageKey.DTJS_ZZGXZC_XXJG_JSXBH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			//党费交至日期处理（转为每月的最后一天）
			xxjgForm.setDfjzrq(DateUtils.getLastDayOfMonth(xxjgForm.getDfjzrq()));
			
			boolean result = xxjgService.runUpdate(xxjgForm);
			String messageKey = null;
			if(result){
				messageKey = MessageKey.SYS_SAVE_SUCCESS;
				
				//插入改派日志
				GprzService gprzService = new GprzService();
				String xgsj = DateUtils.getCurrDate();
				String xgr = getUser(request).getUserName();
				String [] xgqhjl = gprzService.getXgqhjl(xf,xxjgForm);
				String xgqjl = xgqhjl[0];
				String xghjl = xgqhjl[1];
				
				GprzForm gprzForm = new GprzForm();
				gprzForm.setXgsj(xgsj);
				gprzForm.setXgr(xgr);
				gprzForm.setXh(xxjgForm.getXh());
				gprzForm.setXgqjl(xgqjl);
				gprzForm.setXghjl(xghjl);
				gprzForm.setGpyy(request.getParameter("gpyy"));
				
				if(!StringUtil.isNull(xghjl)){
					gprzService.runInsert(gprzForm);
				}
				
			}else{
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		if (!StringUtil.isNull(xxjgForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xxjgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		BeanUtils.copyProperties(xxjgForm, xf);
		
		//学生基本信息显示配置
		String path = "dtjs_xxjg.do?method=xxjgUpdate";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",new ZcsqSERVICE().getDzbList());
		
		return mapping.findForward("xxjgUpdate");
	}
	
	/**
	 * 
	 * @描述:信息结果详情
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 下午4:53:47
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
	public ActionForward xxjgShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XxjgForm xxjgForm = (XxjgForm)form;
		XxjgForm xf = xxjgService.getModel(xxjgForm.getJgid());
		BeanUtils.copyProperties(xxjgForm, xf);
		
		if (!StringUtil.isNull(xxjgForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xxjgForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		String path = "dtjs_xxjg.do?method=xxjgShow";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("xxjgShow");
	}
	
	/**
	 * 
	 * @描述:信息结果删除
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 下午4:53:47
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
	@SystemLog(description="访问党团建设-组织关系转出-信息结果-删除VALUES:{values}")
	public ActionForward xxjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			String [] ids = values.split(",");
			
			//删除前查询相关信息，用于插入改派日志表
			GprzService gprzService = new GprzService();
			String xgsj = DateUtils.getCurrDate();
			String xgr = getUser(request).getUserName();
			List<XxjgForm> xxjgFormList = xxjgService.getXxjgFormList(ids);
			List<GprzForm> gprzFormList = new ArrayList<GprzForm>();
			for(XxjgForm xxjgForm:xxjgFormList){
				String xgqjl = gprzService.getXgqhjl(xxjgForm);
				GprzForm gprzForm = new GprzForm();
				gprzForm.setXgsj(xgsj);
				gprzForm.setXgr(xgr);
				gprzForm.setXgqjl(xgqjl);
				gprzForm.setXh(xxjgForm.getXh());
				gprzFormList.add(gprzForm );
			}
			//批量 插入
			gprzService.saveGprzFormList(gprzFormList);
			
			int num = xxjgService.runDelete(ids);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:信息结果导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 下午4:57:05
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
		
		XxjgForm xxjgForm = (XxjgForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		xxjgForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = xxjgService.getAllList(xxjgForm,user);//查询出所有记录，不分页
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = xxjgForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述: 团组织关系转出申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 上午08:53:01
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
	public ActionForward zzgxdjbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		File wordFile = getWord(id,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 团组织关系转出申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 上午08:53:01
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
	public ActionForward zzgxdjbDcTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述: getword 获取word
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-19 上午08:58:11
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
	public File getWord(String id,HttpServletRequest request) throws Exception{
		XxjgService service = new XxjgService();
		HashMap<String,String> jgMap = service.getDzcgxJgMap(id);
		Map<String,Object> data = new HashMap<String,Object>();
		data.putAll(jgMap);
		File file = null;
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"dtxx/tzzgxzcb.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"dtxx","tzzgxzcb.xml",FreeMarkerUtil.getFileName(data.get("xm")+"_共青团团员组织关系介绍信"));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return file;
	}
	

}

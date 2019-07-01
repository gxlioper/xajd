package xsgzgl.gyjc.jcjglr;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gyjc.jcrc.JcrcForm;
import xsgzgl.gyjc.jcrc.JcrcService;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;

public class JcjglrAction extends SuperAction<JcjglrForm,JcjglrService> {
	private JcjglrService service = new  JcjglrService();
	private static final String url = "xg_gyjc_jcjglr.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");

	/**
	 * 
	 * @描述: 查看提交数量明细
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-20 下午07:23:25
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
	public ActionForward getJcjgLrcxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		JcjglrForm model = (JcjglrForm) form;
		User user = getUser(request);
		if(StringUtils.isNotNull(request.getParameter("username"))){
			model.setTjr(user.getUserName());
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getCommWpdjList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		if(StringUtils.isNotNull(model.getFlag()) && "jgcx".equals(model.getFlag())){
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		if("jgcx".equals(model.getFlag())){
			request.setAttribute("path", "gyjc_jcjglr.do?method=getJcjgLrcxList&flag=jgcx&tjzt=1");
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "gyjc_jcjglr.do?method=getJcjgLrcxList");
		return mapping.findForward("searchlrcx");
	}
	
	/**
	 * 
	 * @描述: 获取检查结果录入List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 上午09:32:10
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
	public ActionForward getJcjgLrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JcjglrForm model = (JcjglrForm) form;
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
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("today", GetTime.getTimeByFormat(DATE_FORMAT));
		//将用户身份置回页面
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 下午06:01:10
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
	@SystemLog(description = "访问文明寝室-检查结果录入-提交MXIDS:{mxidString}")
	public ActionForward submitRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String mxidString = request.getParameter("mxids");
		boolean rs = false;
		if(StringUtils.isNotNull(mxidString)){
			String[] mxids = mxidString.split(",");
			rs = service.tjRecode(mxids,getUser(request).getUserName());
		}
		String messageKey = (rs) ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 下午06:02:27
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
	@SystemLog(description = "访问文明寝室-检查结果录入-导出")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcjglrForm model = (JcjglrForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getCommWpdjList(model, user);
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
	 * @throws Exception 
	 * 
	 * @描述: 保存录入结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午05:32:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问文明寝室-检查结果录入-保存MXIDS:{mxids),PFIDS:{pfids},MXIDFLAGS:{mxidflags}")
	public ActionForward saveLrjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JcjglrForm model = (JcjglrForm) form;
		model.setTjr(getUser(request).getUserName());
		String[] mxids = request.getParameterValues("mxid");
		model.setMxids(mxids);
		String[] pfids = request.getParameterValues("pfid");
		model.setPfids(pfids);
		String[] mxidflags = request.getParameterValues("mxidflag"); 
		model.setMxidflags(mxidflags);
		String[] indexs = request.getParameterValues("index");
		model.setIndexs(indexs);
		String[] fids = request.getParameterValues("fid");
		model.setFids(fids);
		//JcjglrService tranService = TransactionControlProxy.newProxy(new JcjglrService());
		boolean rs = true;
    	try {
			rs = service.saveJcjglr(model);
		} catch (SystemException e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_OPERATE_FAIL));
			return null;
		}
	    
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:检查结果路人维护
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 下午01:42:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addJcJgLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JcjglrForm model = (JcjglrForm) form;
		//寝室基本信息
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//(卫生检查，安全检查，纪律检查包含几项)
		HashMap<String,String> jcmxMap = service.getJcmxMap(model.getRcid(),model.getLddm(),model.getQsh(),getUser(request).getUserName(),null);
		request.setAttribute("jcmxMap", jcmxMap);
		request.setAttribute("yscwjList", service.getYscfjxx(jcmxMap.get("fjid")));
		//下拉框置入页面
		if(StringUtils.isNotNull(jcmxMap.get("wsjc"))){
			request.setAttribute("wsjcFjSeList",service.getFjSelectList(model.getXydm(),"1",model.getJs()));
			request.setAttribute("wsjcZjSeList",service.getZjSelectList(model.getXydm(), "1", model.getJs()));
			request.setAttribute("wsjcPfList", service.getJcxFxCx(model.getRcid(),model.getLddm(),model.getQsh(),"1"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("aqjc"))){
			request.setAttribute("aqjcFjSeList",service.getFjSelectList(model.getXydm(),"2",model.getJs()));
			request.setAttribute("aqjcZjSeList",service.getZjSelectList(model.getXydm(), "2", model.getJs()));
			request.setAttribute("aqjcPfList", service.getJcxFxCx(model.getRcid(), model.getLddm(), model.getQsh(),"2"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("jljc"))){
			request.setAttribute("jljcFjSeList",service.getFjSelectList(model.getXydm(),"3",model.getJs()));
			request.setAttribute("jljcZjSeList",service.getZjSelectList(model.getXydm(), "3", model.getJs()));
			request.setAttribute("jljcPfList", service.getJcxFxCx(model.getRcid(),  model.getLddm(), model.getQsh(), "3"));
		}
		return mapping.findForward("jcjglr");
	}
	
	/**
	 * 
	 * @描述: 查看检查结果录入
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-26 下午03:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward ckJcJgLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JcjglrForm model = (JcjglrForm) form;
		//寝室基本信息
		HashMap<String, String> qsxx = service.getQsjbxx(model.getLddm(),model.getQsh());
		request.setAttribute("qsxx", qsxx);
		//(卫生检查，安全检查，纪律检查包含几项)
		HashMap<String,String> jcmxMap = service.getJcmxMap(model.getRcid(),model.getLddm(),model.getQsh(),getUser(request).getUserName(),model.getFlag());
		request.setAttribute("jcmxMap", jcmxMap);
		request.setAttribute("djList", service.getFxdjcxForView(model.getRcid(), model.getXydm(), model.getQsh(),model.getLddm(),"jc", null));
		//下拉框置入页面
		if(StringUtils.isNotNull(jcmxMap.get("wsjc"))){
			request.setAttribute("wsjcPfList", service.getJcxFxCx(model.getRcid(),model.getLddm(),model.getQsh(),"1"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("aqjc"))){
			request.setAttribute("aqjcPfList", service.getJcxFxCx(model.getRcid(), model.getLddm(), model.getQsh(),"2"));
		}
		if(StringUtils.isNotNull(jcmxMap.get("jljc"))){
			request.setAttribute("jljcPfList", service.getJcxFxCx(model.getRcid(),  model.getLddm(), model.getQsh(), "3"));
		}
		return mapping.findForward("jcjglrck");
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 下午06:01:10
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
	public ActionForward cxRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String mxidString = request.getParameter("mxids");
		boolean rs = false;
		if(StringUtils.isNotNull(mxidString)){
			String[] mxids = mxidString.split(",");
			rs = service.cxRecode(mxids);
		}
		String messageKey = (rs) ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述: 文件下载
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-28 下午03:08:43
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fid = request.getParameter("fid");
		HashMap<String, String> wjxx = service.getWjxxx(fid);
		String basePath = resource.getString("filesys.local.dir");
		//如果没有给定文件存储路径，就默认给系统用户目录
		if(StringUtils.isNull(basePath)){
			basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
		}
		if (StringUtils.isNotNull(wjxx.get("generatename"))){
			File file = new File(basePath+"/"+wjxx.get("generatename"));
			if (file.exists()){
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(wjxx.get("originalname"),"utf-8")); 
					FileUtil.outputFile(response, file);
				}
			}
		}
		return null;
	}
	
}

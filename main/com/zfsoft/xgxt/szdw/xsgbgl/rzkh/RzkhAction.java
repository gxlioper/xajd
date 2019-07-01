/**
 * @部门:学工产品事业部
 * @日期：2015-6-10 下午05:28:11 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
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
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-6-10 下午05:28:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RzkhAction extends SuperAction<rzkhjgForm,RzkhService> {
	private final String RZKHJG ="rzkhjg";
	private RzkhService service = new  RzkhService();
	private RzkhDao dao = new RzkhDao();
	private DAO Dao = DAO.getInstance();
	
	private static final String url = "szdw_xsgb_rzkhjg.do?method=rzkhjgList";
	
	@SystemAuth(url = url)
	public ActionForward rzkhjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		 rzkhjgForm model = (rzkhjgForm) form;
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
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "szdw_xsgb_rzkhjg.do?method=rzkhjgList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	/**
	 * 学生干部考核结果增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    rzkhjgForm model = (rzkhjgForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RZKHJG);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Dao.getXnndList());
		request.setAttribute("xqList", Dao.getXqList());
		request.setAttribute("zwmclist", dao.getZwxxList());
		String path = "szdw_xsgb_rzkhjg.do?method=add";
		request.setAttribute("path", path);
		//其他信息配置
		return mapping.findForward("add");
	}
	
	/**
	 * 任职考核结果保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部任职考核结果-增加或修改XH:{xh},KHJGID:{khjgid},ZWMC:{zwmc},RZSJ:{rzsj},XN:{xn},XQ:{xq},GRZP:{grzp},ZGDWYJ:{zgdwyj}")
	public ActionForward saveKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		rzkhjgForm model = (rzkhjgForm) form;
		boolean result = false;
		String message=null;
	  //  User user = getUser(request);
		if(model.getType().equals("save")){
			// 判断当前学生是否有校外住宿结果
			boolean isExist = service.checkExistForSave(model);
			if (isExist) {
				message = MessageUtil.getText(MessageKey.SZSW_XSGBGL_KHJG_ADD,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 删除考核结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部任职考核结果-删除KHJGID:{values}")
	public ActionForward delKhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			XyzsglForm myForm = new XyzsglForm();
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
	 * 学生干部考核结果查看
	 */
	@SystemAuth(url = url)
	public ActionForward KhjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		rzkhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RZKHJG);
		HashMap<String, String> zwmc = service.getZwmc(model.getZwmc());
//		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", StringUtils.formatData(jbxxList));
		request.setAttribute("khjg", model);
	    String rzsj = model.getRzsj();
//	    rzsj = rzsj.substring(0, 4)+"-"+rzsj.substring(4, 6)+"-"+rzsj.substring(6, 8);
	    request.setAttribute("rzsj",rzsj);
	    request.setAttribute("zwmc", zwmc);
	    String xqmc = service.getxqdz(model.getXq()).get("xqmc");
	    request.setAttribute("xqmc", xqmc);
		return mapping.findForward("view");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		rzkhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RZKHJG);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("khjg", StringUtils.formatData(model));
		request.setAttribute("xnList", Dao.getXnndList());
		request.setAttribute("xqList", Dao.getXqList());
		request.setAttribute("zwmclist", dao.getZwxxList());
		String path = "szdw_xsgb_rzkhjg.do?method=updateKhjg";
		request.setAttribute("path", path);
		return mapping.findForward("update");
	}
	
	/**
	 * 学生干部考核结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		rzkhjgForm model = (rzkhjgForm) form;

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
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getGbrzkhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		File wordFile = getKhjgWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getKhjgWord(rzkhjgForm myForm,HttpServletRequest request) throws Exception{
		String xh = myForm.getXh();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> KhjgMap = null;
		myForm  = service.getModel(myForm.getKhjgid());
		KhjgMap = service.getKhjgxxMap(myForm, user);
		data.put("qdyjs", HtmlUtil.xmlZy(KhjgMap.get("qdyj")));
		data.put("xsgzcyjs",HtmlUtil.xmlZy(KhjgMap.get("xsgzcyj")));
		data.put("ddyjs", HtmlUtil.xmlZy(KhjgMap.get("ddyj")));
		data.put("szdwyjs", HtmlUtil.xmlZy(KhjgMap.get("szdwyj")));
		data.put("grszs", HtmlUtil.xmlZy(KhjgMap.get("grsz")));
		data.putAll(KhjgMap);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","szdw_xsgb_rzkhjg_11483.xml",myForm.getXn()+myForm.getXq() +"-"+myForm.getXh()+"-"+KhjgMap.get("xm"));
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getKhjgkhbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setKhjgid(values[i]);
				File file = getKhjgWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//考核汇总表导出
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getKhhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		myForm = service.getModel(myForm);
		File wordFile = getKhhzWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	//获取汇总word
	private File getKhhzWord(rzkhjgForm myForm,HttpServletRequest request) throws Exception{
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> Khjglist = null;
		Khjglist = service.getKhjgxxList(myForm);
		HashMap<String, String>  KhjgMap = service.getKhjgxxMap(myForm, user);
		data.put("Khjglist", Khjglist);
		data.putAll(KhjgMap);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","szdw_xsgb_rzkhhz_11483.xml","浙江警察学院学生干部考核汇总表"+myForm.getXh());
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getKhhzbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		rzkhjgForm myForm = (rzkhjgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setKhjgid(values[i]);
				myForm = service.getModel(myForm);
				File file = getKhhzWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
}

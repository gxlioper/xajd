package xsgzgl.jxgl.general.jxxxwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb.ZjsySxhbForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb.ZjsySxhbService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 军训管理-基础信息维护-军训信息维护
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhAction extends SuperAction{
	
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;

	private static final String url = "jxgl_jcxxwh_jxxxwh.do";
	
	/**
	 * 军训信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jxxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jxgl_jcxxwh_jxxxwh.do");
		// ----------------设置PATH end-----------------------
		jxglJxxxwhService.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jxxxCx");
		}
	}
	
	@SystemAuth(url = url)
	public ActionForward jzdjwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		List<HashMap<String,String>> jxnjList = jxglJxxxwhService.getJzdj();
		request.setAttribute("cjnjList", jxnjList);
		String path = "jxgl_jzdjwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jzdjwh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJzdj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		String params = request.getParameter("params");
		//先将取消打钩的置成0，然后把打钩的置成1
		String sql1 = "update xg_jxgl_new_jxjzdjb set sfqy=0";
		String sql2 = "update xg_jxgl_new_jxjzdjb set sfqy=1 where dm in ( " + params + ")" ;
		//将所有的是否叶子节点置为0，然后将最低级的选中的节点置为叶子节点1
		String sql3 = "update xg_jxgl_new_jxjzdjb set sfyzjd=0";
		String sql4 = "update xg_jxgl_new_jxjzdjb set sfyzjd=1 where dm =(select max(dm) from xg_jxgl_new_jxjzdjb where sfqy=1) ";
		
		String[] sql = {sql1,sql3,sql2};
		int[] k = jxglJxxxwhService.serv_batchUpdateJzdj(sql);
		
		boolean result = jxglJxxxwhService.serv_updateJzdj(sql4);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 军训名单查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jxmdCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String cxqk = request.getParameter("cxqk");
		if("cx".equalsIgnoreCase(cxqk)){
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_cxqk(new String[]{"参训"});
			searchModel.setPath("jxgl_jxxxwh.do?method=jxmdCx");
			request.setAttribute("searchTj", searchModel);
		}else if("hx".equalsIgnoreCase(cxqk)){
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_cxqk(new String[]{"缓训"});
			searchModel.setPath("jxgl_jxxxwh.do?method=jxmdCx");
			request.setAttribute("searchTj", searchModel);
		}
		request.setAttribute("pkValue", pkValue);
		model.setJxid(pkValue);
		HashMap<String,String> rs = jxglJxxxwhService.getJxxx(model);
		List<HashMap<String,String>> jxnjList = jxglJxxxwhService.getCjnj();
		request.setAttribute("cjnjList", jxnjList);
		request.setAttribute("rs", rs);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		request.setAttribute("path", "jxgl_jcxxwh_jxxxwh.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxgl_jxxxwh.do?method=jxmdCx");
		// ----------------设置PATH end-----------------------
		jxglJxxxwhService.setRequestValue(rForm, user, request);
		return mapping.findForward("jxmdCx");
		
	}
	
	
	public ActionForward jxxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   String pkValue = request.getParameter("pkValue");
		   request.setAttribute("pkValue", pkValue);
		   return mapping.findForward("jxmdXg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		JxglJxxxwhService service = new JxglJxxxwhService();
        
		model.setJxid(request.getParameter("pkValue"));
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAlljxmdCx(model, request);

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
	 * @描述: 免训，缓训，取消军训弹框及保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-29 下午05:55:59
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
	public ActionForward jxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			JxglJxxxwhDao jxDao = new JxglJxxxwhDao();
			myForm.setJxid(jxglJxxxwhService.unicode2Gbk(myForm.getJxid()));
			myForm.setLy(jxglJxxxwhService.unicode2Gbk(myForm.getLy()));
			myForm.setFj(jxglJxxxwhService.unicode2Gbk(myForm.getFj()));
			boolean result = false;
			if("hx".equalsIgnoreCase(myForm.getCxqk()) || "mx".equalsIgnoreCase(myForm.getCxqk())) {
				result = jxDao.upJxxx(myForm);
			}else {
				jxDao.scJxxx(myForm);
				result = jxDao.insqxJxxx(myForm);
			}
			
			String messageKey = result ? "保存成功！" : "保存失败！";
			response.getWriter().print(messageKey);
			return null;
		}
		
		// 学生基本信息
		String path = "jxgl_jxxxwh.do?method=jxxx";
		request.setAttribute("path", path);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		request.setAttribute("cxz", myForm.getCxz());
		
		return mapping.findForward("jxxx");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-30 上午10:30:10
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
	public ActionForward viewJxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		HashMap<String,String> rs = jxglJxxxwhService.getJxxxView(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("cxz", myForm.getCxqk());
		return mapping.findForward("viewJxxx");
	}
	
	/**
	 * 
	 * @描述: 缓训表打印（北京经管）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-2 下午01:10:41
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
	public ActionForward printHx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		File wordFile = getHxbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 
	 * @描述: 填充缓训值
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-2 下午01:15:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getHxbWord(JxglJxxxwhForm myForm,HttpServletRequest request) throws Exception{
		
		JxglJxxxwhDao jxDao = new JxglJxxxwhDao();
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = jxDao.getJxxh(myForm);
		String xh = xsxxMap.get("xh");
		User user = getUser(request);
		myForm.setUser(user);
		myForm.setXh(xh);
		HashMap<String, String> xsxxMapLy = jxDao.getJxly(myForm);
		String ly = xsxxMapLy.get("ly");
		data.put("ly", ly);
		//基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		data.put("rs", xsjbxx);
			
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//jxgl","hxb_14073.xml",
				xh+"-"+xsjbxx.get("xm")+"缓训表");	
	}
	
	/**
	 * 
	 * @描述: 批量缓训表打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-2 下午01:16:04
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
	public ActionForward getHxbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setPkValue(values[i]);
				File file = getHxbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 免训表打印（北京经管）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-2 下午01:10:41
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
	public ActionForward printMx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		File wordFile = getMxbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 
	 * @描述: 填充免训值
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-2 下午01:15:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getMxbWord(JxglJxxxwhForm myForm,HttpServletRequest request) throws Exception{
		
		JxglJxxxwhDao jxDao = new JxglJxxxwhDao();
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = jxDao.getJxxh(myForm);
		String xh = xsxxMap.get("xh");
		User user = getUser(request);
		myForm.setUser(user);
		myForm.setXh(xh);
		HashMap<String, String> xsxxMapLy = jxDao.getJxly(myForm);
		String ly = xsxxMapLy.get("ly");
		data.put("ly", ly);
		//基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		data.put("rs", xsjbxx);
			
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//jxgl","mxb_14073.xml",
				xh+"-"+xsjbxx.get("xm")+"免训表");	
	}
	
	/**
	 * 
	 * @描述: 批量免训表打印
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-2 下午01:16:04
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
	public ActionForward getMxbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setPkValue(values[i]);
				File file = getMxbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
		
	}
	
	
}

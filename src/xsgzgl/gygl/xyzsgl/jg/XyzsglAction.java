/**
 * @部门:学工产品事业部
 * @日期：2015-5-18 下午07:36:02 
 */  
package xsgzgl.gygl.xyzsgl.jg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

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
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-5-18 下午07:36:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyzsglAction extends SuperAction<XyzsglForm, XyzsglService> {
	private final String XYZSJG ="xyzsjg";
	private XyzsglService service = new  XyzsglService();
	
	private static final String url = "gygl_xyzsjg.do";
	
	/**
	 * 校外住宿结果初始化界面以及查询
	 */
	@SystemAuth(url = url)
	public ActionForward getXyzsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XyzsglForm model = (XyzsglForm) form;
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
		request.setAttribute("searchTj", searchModel);
		String path = "gygl_xyzsjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXyzsList");
	}
	
	/**
	 * 校外住宿结果增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XyzsglForm model = (XyzsglForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSJG);
		request.setAttribute("jbxxList", jbxxList);
		String path = "gygl_xyzsjggl.do?method=add";
		request.setAttribute("path", path);
		List<HashMap<String, String>> zwjzyy = service.getZyjzxx(model);
		request.setAttribute("zwjzyy", zwjzyy);
		List<HashMap<String, String>> xl = service.getXl(model);
		request.setAttribute("xl", xl);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xxdm", Base.xxdm);
		//其他信息配置
		return mapping.findForward("add");
	}
	
	/**
	 * 住宿结果保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问公寓管理-常用功能-校外住宿结果-维护类型{type}-XH:{xh},SQBH:{sqbh},SQSJSTART:{sqsjstart},SQSJEND:{sqsjend},XL:{xl},LXDH:{lxdh},PARENTSLXDY:{parentslxdy},XXDZ:{xxdz},ZWJZYY:{zwjzyy}")
	public ActionForward saveZsjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsglForm model = (XyzsglForm) form;
		
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		if(model.getType().equals("save")){
			// 判断当前学生是否有校外住宿结果
			boolean isExist = service.checkExistForSave(model);
			if (isExist) {
				message = MessageUtil.getText(MessageKey.GYGL_XYZS_REPEAT,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveZsjg(model, user);
		}else if(model.getType().equals("update")){
			result = service.saveZsjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 校外住宿结果查看
	 */
	@SystemAuth(url = url)
	public ActionForward ZsjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		XyzsglForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSJG);
		HashMap<String, String> xl = service.getXlCk(model);
		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		request.setAttribute("xlxx", StringUtils.formatData(xl));
		request.setAttribute("xyjzyy", StringUtils.formatData(xyjzyy));
		return mapping.findForward("view");
	}
	
	/**
	 * 校外住宿结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZsjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		XyzsglForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSJG);
		List<HashMap<String, String>> zwjzyy = service.getZyjzxx(model);
		request.setAttribute("zwjzyy", zwjzyy);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		List<HashMap<String, String>> xl = service.getXl(model);
		request.setAttribute("xl", xl);
		String path = "gygl_xyzsjggl.do?method=editZsjg";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("editZsjg");
	}
	
	/**
	 * 住宿结果删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问公寓管理-常用功能-校外住宿结果-删除SQBH:{values}")
	public ActionForward delZsjg(ActionMapping mapping, ActionForm form,
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
	 * 住宿结果导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyzsglForm model = (XyzsglForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页
		
		//因为学历名称和在外居住原因只能获取代码值，因此这里循环遍历set值
		for (HashMap<String, String> hashMap : resultList) {
		  String sqbh = hashMap.get("sqbh");
		  model.setSqbh(sqbh);
		  HashMap<String, String> zwjzMap= service.getXyZsyy(model);
		  hashMap.put("zwjzyy",zwjzMap.get("mc"));
		  HashMap<String, String> xlMap = service.getXlCk(model);
		  hashMap.put("xl",xlMap.get("xlmc"));
		}

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
	 *住宿结果申请表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getXyzsjgsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		File wordFile = getZsjgWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getZsjgWord(XyzsglForm myForm,HttpServletRequest request) throws Exception{

		String xh = myForm.getXh();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> XyzsjgMap = null;
		if(request.getParameter("flag").equals("sq")){
			 XyzsSqDao xyzsSqDao = new XyzsSqDao();
			 XyzsSqForm xyzsSqForm = xyzsSqDao.getModel(myForm.getSqbh());
			 XyzsjgMap = service.getXyzsxxMap1(myForm, user);
			 data.put("xyzsjg", xyzsSqForm);
		}else{
			myForm  = service.getModel(myForm.getSqbh());
			XyzsjgMap = service.getXyzsxxMap(myForm, user);
			data.put("xyzsjg", myForm);
		}
//		data.put("xyzsjg", myForm);
		data.putAll(XyzsjgMap);
		
		//审批流程
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(XyzsjgMap.get("sqbh"));
		List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(XyzsjgMap.get("splc"));
		for(int i=0;i<infoList.size();i++){
			HashMap<String,String>lcMap=infoList.get(i);
			if(i<gwList.size()){
				lcMap.putAll(gwList.get(i));
			}
		}
		data.put("splcList",infoList);
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"gygl/gygl_xyzsjgsqb_"+Base.xxdm+".xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"gygl","gygl_xyzsjgsqb_"+Base.xxdm+".xml",FreeMarkerUtil.getFileName(XyzsjgMap.get("xm")+"_校外住宿申请表"));
		}catch (Exception e) {
			file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"gygl","gygl_xyzsjgsqb_10530.xml", FreeMarkerUtil.getFileName(XyzsjgMap.get("xm")+"_校外住宿申请表"));
		}
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getXyzsjgsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyzsglForm myForm = (XyzsglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqbh(values[i]);
				File file = getZsjgWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}

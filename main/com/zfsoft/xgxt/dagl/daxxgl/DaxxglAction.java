/**
 * @部门:学工产品事业部
 * @日期：2014-2-18 下午04:42:46 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import java.io.File;
import java.lang.reflect.Method;
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
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dagl.qdcl.DaqdclService;
import com.zfsoft.xgxt.dagl.qdmb.DaqdmbService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @作者： wanghj [工号：1004]
 * @时间： 2014-2-18 下午04:42:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaxxglAction extends SuperAction {
	
	
	private static List<HashMap<String, String>> jbxxList = null;
	/**
	 * 显示毕业去向FLG："1"
	 */
	private static String XSBYQX =  "1";
	/**
	 * 维护状态：已维护
	 */
	private static String YWHWHZT =  "已维护";
	
	private static final String url = "daxxgl.do?method=daxxglList";
	
	/**
	 * 查询操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward daxxglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "daxxgl.do?method=daxxglList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("daxxglList");
	}
	
	
	/**
	 * 新增操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-档案管理-档案信息管理-增加XH:{xh},DAZRSJ:{dazrsj},ZRFSBH:{zrfsbh}")
	public ActionForward addDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		if (SAVE.equalsIgnoreCase(myForm .getType())){
			//新增时主键冲突(xh、dazrsj联合主键)
			String pk = myForm.getXh()+myForm.getDazrsj();
			HashMap<String, String> values = service.getDaxxInfoByPk(pk);
			if(values!=null && values.size()>0){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSXX_DAXX_REPEAT_ERROR));
				return null;
			}
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		User user = getUser(request);
		//设置学生基本信息
		String xh = "";
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			xh = user.getUserName();
		}else{
			xh = myForm.getXh();
		}	
		this.setXsxxInfo(request, xh);
		request.setAttribute("path", "daxxgl.do?method=addDaxxgl");
		return mapping.findForward("addDaxxgl");
	}
	/**
	 * 修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-档案管理-档案信息管理-修改XH:{xh},DAZRSJ:{dazrsj},ZRFSBH:{zrfsbh}")
	public ActionForward updateDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.updateDaxxgl(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String, String> values = service.getDaxxTableByPk(myForm.getPk());
		DaxxglForm model = (DaxxglForm) myForm.getClass().getConstructor().newInstance();
		if (!values.isEmpty()){
			for(Map.Entry<String,String> entry : values.entrySet()){
				String property = entry.getKey();
				String value = entry.getValue();
				Method setMethod = model.getClass().getMethod("set"+property.substring(0, 1).toUpperCase()+property.substring(1),String.class);
				setMethod.invoke(model, value);
			}
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		this.setXsxxInfo(request, myForm.getXh());
		return mapping.findForward("updateDaxxgl");
	}
	
	
	
	/**
	 * 删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-档案管理-档案信息管理-删除PKS:{pks}")
	public ActionForward delDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		
		String pks = request.getParameter("pks");
		
		if (!StringUtil.isNull(pks)){
			int num = service.delDaxxgl(pks.split(","));
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
	 * 查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		// pk:学号+档案转入时间
		String pk = myForm.getPk();
		HashMap<String, String> daxxInfo = service.getDaxxInfoByPk(pk);
		
		// 设定学生基本信息和是否显示毕业去向判断
		request.setAttribute("rs", StringUtils.formatData(daxxInfo));
		String xh = daxxInfo.get("xh");
		setXsxxInfo(request, xh);
		
		String whzt = daxxInfo.get("whzt");
		request.setAttribute("whzt", whzt);
		
		//若已维护档案清单，则取出模板内外的材料
		if(YWHWHZT.equals(whzt)){

			// 档案模板ID
			String daqdmb_id = daxxInfo.get("daqdmb_id");
			
			// 取得模板内外的材料
			List<HashMap<String, String>> mbnclList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> mbwclList = new ArrayList<HashMap<String, String>>();
			if(StringUtils.isNotNull(daqdmb_id)){
				mbnclList = service.getXsdaclListByBmid(daqdmb_id,pk);//学生档案模板内材料
			}
			mbwclList = service.getXsMbwclListByBmid(daqdmb_id,pk);//学生档案内模板外材料
			request.setAttribute("mbnclList", mbnclList);
			request.setAttribute("mbwclList", mbwclList);
		}
		return mapping.findForward("viewDaxxgl");
	}

	/**
	 * 档案清单维护（绑定材料）-未维护的学生
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="访问学生信息-档案管理-档案信息管理-保存清单维护XH:{xh}")
	public ActionForward bandXsqd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		DaqdmbService daqdbmSv = new DaqdmbService();
		DaqdclService daqdclSv = new DaqdclService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			
			String xh = request.getParameter("xh");
			String dazrsj = request.getParameter("dazrsj");
			String daqdmb_id = request.getParameter("daqdmb_id");
			String jsonStr = request.getParameter("json");
			
			JSONArray jsonArray = new JSONArray(jsonStr);
			//更新XG_XSXX_DAGL_DAXXB档案信息表
			boolean result = service.updateDaxxgl(daqdmb_id, xh+dazrsj);
			
			if(result){
				//插入XG_XSXX_DAGL_XSDACLBDB学生档案材料绑定表
				result = service.saveXsdaxxBand(xh,dazrsj,jsonArray);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// pk:学号+档案转入时间
		String pk = myForm.getPk();
		
		// 学生档案信息
		HashMap<String, String> daxxInfo = service.getDaxxInfoByPk(pk);
		
		// 档案信息存在
		if(daxxInfo!=null && daxxInfo.size()>0){
			
			// 取得学生基本信息
			String xh = daxxInfo.get("xh");
			setXsxxInfo(request, xh);
			request.setAttribute("rs", StringUtils.formatData(daxxInfo));		
		}

		// 选择的模板ID
		String daqdmb_id = myForm.getDaqdmb_id();
		List<HashMap<String, String>> mbclList = new ArrayList<HashMap<String, String>>();			
		if(StringUtils.isNotNull(daqdmb_id)){
			mbclList = service.getXsdaclListByBmid(daqdmb_id,pk);//学生档案模板内材料
		}
		request.setAttribute("mbclList", mbclList);
		List<HashMap<String, String>> mbwclList = new ArrayList<HashMap<String, String>>();
		mbwclList = service.getXsMbwclListByBmid(daqdmb_id,pk);//学生档案模板外材料
		request.setAttribute("mbwclList", mbwclList);
		request.setAttribute("daqdmb_id", daqdmb_id);
		
		request.setAttribute("mbList", daqdbmSv.getDaqdmbList());//模板下拉框
		request.setAttribute("clList", daqdclSv.getDaqdclAllList());//材料下拉框
		return mapping.findForward("bandXsqd");
	}
	
	/**
	 * 档案清单批量维护（绑定材料）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="访问学生信息-档案管理-档案信息管理-批量保存清单维护XH:{xh}")
	public ActionForward bandXsqdBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		DaqdmbService daqdbmSv = new DaqdmbService();
		DaqdclService daqdclSv = new DaqdclService();
		User user = getUser(request);

		String selected = request.getParameter("selected");
		request.setAttribute("selected", selected);
		
		// 保存批量操作
		if (SAVE.equalsIgnoreCase(myForm.getType())){

			// 档案材料绑定信息
			String jsonStr = request.getParameter("json");
			String daqdmb_id = request.getParameter("daqdmb_id");

			// 全选的情况
			if("all".equals(selected)){	

				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath("daxxgl.do?method=daxxglList");
				myForm.setSearchModel(searchModel);
			}
			JSONArray jsonArray = new JSONArray(jsonStr);
			
			//更新XG_XSXX_DAGL_DAXXB档案信息表
			boolean  result = service.updateBatchDaxxgl(daqdmb_id, jsonArray, myForm, user);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("daxxgl.do?method=daxxglList");
		myForm.setSearchModel(searchModel);
		
		//设定VALUE和已选择学生数
		String values = request.getParameter("values");
		values = myForm.getValues();
		request.setAttribute("values", values);
		HashMap<String, String> dabdxx = service.getDabdxx(myForm, user);
		
		//已维护档案清单学生数
		request.setAttribute("ywhdaqdxss", dabdxx!=null && dabdxx.size() > 0? dabdxx.get("ywhdaqdxss") : 0);
		
		if(StringUtils.isNotNull(values)){
			
			// 选择学生总数
			request.setAttribute("yxzxss", values.split(",").length);
		}else{			
			// 全选的情况
			request.setAttribute("yxzxss", dabdxx!=null && dabdxx.size() > 0? dabdxx.get("yxzxss") : 0);
		}
		
		String daqdmb_id = myForm.getDaqdmb_id();
		List<HashMap<String, String>> mbclList = new ArrayList<HashMap<String, String>>();
		if(StringUtils.isNotNull(daqdmb_id)){
			mbclList = daqdbmSv.getDaqdclListByMbid(daqdmb_id);
		}
		request.setAttribute("daqdmb_id", daqdmb_id);
		request.setAttribute("mbclList", mbclList);

		request.setAttribute("sysdate", DateUtils.getCurrDate());
		request.setAttribute("mbList", daqdbmSv.getDaqdmbList());//模板下拉框
		request.setAttribute("clList", daqdclSv.getDaqdclAllList());//材料下拉框
		
		return mapping.findForward("bandXsqdBatch");
	}
	
	/**
	 * 
	 * @描述:学生信息显示及是否显示毕业去向的设定
	 * @日期：2014-4-24 上午09:13:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param xh
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void setXsxxInfo(HttpServletRequest request,String xh) throws Exception{
		DaxxglService service = new DaxxglService();
		if(StringUtils.isNotNull(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		String sfxsbyqx = service.getXsszInfo();
		
		if(XSBYQX.equals(sfxsbyqx)){
			request.setAttribute("sfxsbyqx", sfxsbyqx); 
			request.setAttribute("byqxList", service.getByqxList());
		}

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz("daxxgl");
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * @描述:档案信息查询列表导出
	 * @作者：wanghj [工号：1004]
	 * @日期：2014-2-19 下午4:16:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = "zxdk_gjdk_dksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportDaxxData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DaxxglForm myForm=(DaxxglForm)form;
		DaxxglService service = new DaxxglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}

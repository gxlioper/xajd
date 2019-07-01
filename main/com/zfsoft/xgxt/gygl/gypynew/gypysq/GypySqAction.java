package com.zfsoft.xgxt.gygl.gypynew.gypysq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.gypynew.cssz.CsszService;
import common.newp.StringUtil;

public class GypySqAction extends SuperAction<GypySqForm, GypySqService> {
	private GypySqService service = new GypySqService();
	private final String url = "gygl_gypynew_gypysq.do";
	/**
	 * 
	 * @描述: 公寓评优
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 上午11:24:44
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
	public ActionForward getGypySqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("splc",new CsszService().getSplc().get("splc"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gypysqcx");
	}
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 上午11:40:58
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
	public ActionForward searchForSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:公寓评优申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 下午04:33:46
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
	public ActionForward addSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sqsj = "";
		String nowYearMonth = GetTime.getTimeByFormat("yyyy-mm");
		String nowDay = GetTime.getTimeByFormat("dd");
		int day = Integer.parseInt(nowDay);
		if(day > 15){
			sqsj = nowYearMonth+"-16";
		}else{
			sqsj = nowYearMonth+"-01";
		}
		request.setAttribute("sqsj", sqsj);
		return mapping.findForward("addsq");
	}
	
	/**
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:22:04
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
	public ActionForward editSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		GypySqForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("qsxx",service.getQshxx(model));
		return mapping.findForward("editsq");
	}
	/**
	 * 
	 * @描述:初始化楼栋，层号，寝室号全局变量
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-26 下午02:48:01
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
	public ActionForward initLdChQsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String gyglyQx = (String) request.getSession().getAttribute("gyglyQx");
		User user = getUser(request);
		List<HashMap<String,String>> lddmList = service.getLddmList(user, gyglyQx);
		List<HashMap<String,String>> chList = service.getChList(user, gyglyQx);
		List<HashMap<String,String>> qshList = service.getQshList(user, gyglyQx);
		JSONObject json = new JSONObject();
		json.put("ld", lddmList);
		json.put("ch", chList);
		json.put("qs", qshList);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存公寓评优申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:28:43
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
	public ActionForward saveSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		User user = getUser(request);
		boolean rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:30:08
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
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
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:31:18
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm model = (GypySqForm) form;

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
	 * @描述:提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:48:12
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm) form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		GypySqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午03:02:02
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			GypySqForm model = new GypySqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 申请查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-28 下午02:35:41
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
	public ActionForward viewSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypySqForm myForm = (GypySqForm)form;
		GypySqForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatDataView(model) );
		}
		request.setAttribute("qsxx",service.getQshxx(model));
		return mapping.findForward("viewsq");
	}
	
	/**
	 * 
	 * @描述: 单个申请表导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 下午03:25:30
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
	public ActionForward getQsgxsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypySqForm myForm = (GypySqForm) form;
		File wordFile = getGxsqWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getGxsqWord(GypySqForm myForm,HttpServletRequest request) throws Exception{
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		myForm = service.getModel(myForm.getSqid());
		HashMap<String, String> qsjbxx = service.getXjqsSqJbxx(myForm);
		String sqly = HtmlUtil.xmlZy(qsjbxx.get("sqly"));
		qsjbxx.put("wjcs", service.getWjNum(myForm));
		qsjbxx.put("bjmc", service.getQsssbj(myForm));
		qsjbxx.put("sqly", sqly);
		data.putAll(qsjbxx);
		data.put("wjxx", service.getWjxx(myForm));
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"gygl/gxsqb.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"gygl","gxsqb.xml",FreeMarkerUtil.getFileName(qsjbxx.get("ldmc")+qsjbxx.get("qsh")+"_星级宿舍申请表"));
		}catch (Exception e) {
			file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"gygl","gxsqb.xml", FreeMarkerUtil.getFileName(qsjbxx.get("ldmc")+qsjbxx.get("qsh")+"_星级宿舍申请表"));
		}
		return file;
	}
	
	public ActionForward getQsgxsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypySqForm myForm = (GypySqForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqid(values[i]);
				File file = getGxsqWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}

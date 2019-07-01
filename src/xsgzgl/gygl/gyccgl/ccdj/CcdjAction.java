package xsgzgl.gygl.gyccgl.ccdj;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import common.newp.StringUtil;

public class CcdjAction extends SuperAction<CcdjForm, CcdjService> {
	private CcdjService service = new CcdjService();
	private static final String url = "gygl_gyccgl_ccdj.do";
	private static String QJTZTSY = "请先进行物品财产代码维护。";
	/**
	 * 
	 * @描述: 查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 上午11:21:05
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
	public ActionForward searchRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcdjForm myForm = (CcdjForm)form;
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			myForm.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
			myForm.setPath(url);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
	   }
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		/**
		 * 根据物品代码维护表是否维护数据，来决定页面访问权限
		 */
		List<HashMap<String, String>> wpList = service.getWpList();
		if(wpList == null || wpList.size() == 0){
			request.setAttribute("message", QJTZTSY);//常量
			return mapping.findForward("error");
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 财产登记删除(删除两张表的数据)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 下午04:49:02
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
	@SystemLog(description = "访公寓管理—宿舍财产管理—财产登记-删除id:{values}")
	public ActionForward delRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		CcdjService transervice = TransactionControlProxy.newProxy(new CcdjService());
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = transervice.delResult(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, ids.length) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午08:42:27
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
	@SystemLog(description = "访问公寓管理—宿舍财产管理—财产登记-增加")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CcdjForm ccdjform = (CcdjForm)form;
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		ccdjform.setXn(Base.currXn);
		ccdjform.setXq(Base.currXq);
		ccdjform.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		ccdjform.setPath(url);
		ccdjform.setUsername(getUser(request).getUserName());
		request.setAttribute("LddmList",service.getLddmList(ccdjform));
		request.setAttribute("wpList",service.getWpList());
		request.setAttribute("shcdList",service.getShcdList());
		return mapping.findForward("addccdj");
	}

	/**
	 * 
	 * @描述: 修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午08:42:27
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
	@SystemLog(description = "访问公寓管理—宿舍财产管理—财产登记-修改")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CcdjForm ccdjForm = (CcdjForm)form;
		request.setAttribute("rs",service.getCcdjMap(ccdjForm.getId()));
		request.setAttribute("wpList",service.getViewWpList(ccdjForm));
		request.setAttribute("shcdList",service.getShcdList());
		return mapping.findForward("updateccdj");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午08:42:27
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
	public ActionForward viewccdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		request.setAttribute("rs",service.getCcdjMap(id));
		request.setAttribute("shcdList", service.getViewWpShcdList(id));
		return mapping.findForward("viewccdj");
	}
	
	/**
	 * 
	 * @描述: 导入
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午08:42:27
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
	@SystemLog(description = "访问公寓管理—宿舍财产管理—财产登记-导入跳转")
	public ActionForward dr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("dr");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午08:49:31
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
	@SystemLog(description = "访问公寓管理—宿舍财产管理—财产登记-导出")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CcdjForm ccdjForm = (CcdjForm)form;
		User user = getUser(request);
		ccdjForm.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		ccdjForm.setPath(url);
//		List<HashMap<String, String>> dataList = service.getAllList(ccdjForm, user);
		List<HashMap<String, String>> dataList = service.getAllListForDc(ccdjForm, user);
		List<HashMap<String, String>> wpList = service.getWpList();
		//获取分组数据
		List<HashMap<String,String>> groupList = service.getGroupLddmList(ccdjForm);
       if(groupList != null && groupList.size() > 1){
    	   	List<File> files = service.getFileArryList(groupList, dataList, wpList, user.getUserName());
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}else{
			String ldmc = (groupList!=null && groupList.size()>0)?groupList.get(0).get("ldmc"):"";
			File file = service.createDcFile(dataList,ldmc , user.getUserName(), wpList);
			 response.setHeader("Content-Disposition", "attachment;filename=\""
		               + new String("gygl_ccdj.xls".getBytes(), "GBK") + "\"");
			response.setContentType("application/vnd.ms-excel");
			FileUtil.outputExcel(response, file);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 下拉框切换引起的值变化
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-12 上午09:19:20
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
	public ActionForward changeSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String type = request.getParameter("type");
		String lddm = request.getParameter("lddm");
		List<HashMap<String, String>>  dataList = new ArrayList<HashMap<String,String>>();
		if("ld".equals(type)){
			  dataList = service.getChList(lddm);
		}else if("cc".equals(type)){
			String ch = request.getParameter("ch");
			  dataList = service.getQshList(lddm, ch);
		}
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("dataList", dataList);
		dataMap.put("message", "true");
		dataMap.put("size", dataList.size());
		JSONObject dataMapJson = JSONObject.fromObject(dataMap);
		response.getWriter().print(dataMapJson);
		return null;
	}
	
	/**
	 * @throws Throwable 
	 * 
	 * @描述: 保存方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午11:32:06
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
	@SystemLog(description = "访问公寓管理—宿舍财产管理—财产登记-保存表单")
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		CcdjForm ccdjform = (CcdjForm)form;
		String[] wpdms = request.getParameterValues("wpdms");
		String[] shcds = request.getParameterValues("shcds");
		ccdjform.setWpdms(wpdms);
		ccdjform.setShcds(shcds);
		CcdjService bcService = TransactionControlProxy.newProxy(new CcdjService());
		String messageKey = bcService.saveForm(ccdjform) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 寝室号联动
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-9 上午09:59:23
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
	public ActionForward qshChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		CcdjForm ccdjform = (CcdjForm)form;
		//返回Map
		HashMap<String, Object> Map = new HashMap<String, Object>();
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
		//是否存在：0(不存在)，1(存在)
		boolean  isexist = service.checkIsExistNotInCcdjb(ccdjform);
		if(isexist){
			resultList = service.getWpList();
			Map.put("isexist", "0");
		}else{
			resultList = service.getViewWpList(ccdjform);
			Map.put("id", resultList.get(0).get("id"));
			Map.put("isexist", "1");
			CcdjForm model = (CcdjForm) StringUtils.formatData(service.getModel(resultList.get(0).get("id")));
			Map.put("zje", model.getZje());
			Map.put("bz", model.getBz());
		}
		List<HashMap<String, String>> shcdList = service.getShcdList();
		Map.put("dataList", JsonUtil.ListToJSON(resultList));
		Map.put("shcdList", JsonUtil.ListToJSON(shcdList));
		response.getWriter().print(JSONObject.fromObject(Map));
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存导入
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-13 上午09:44:02
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
	@SystemLog(description = "访问公寓管理—宿舍财产管理—财产登记-导入保存")
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		CcdjForm model = (CcdjForm) form;
		FormFile file = model.getDrmb();
		model.setPath(url);
		model.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		model.setUsername(getUser(request).getUserName());
		model.setShcdList(service.getShcdList());
		model.setXqList(Base.getXqList());
		if(file != null){
			model.setFilepath(servlet.getServletContext().getRealPath(
			"/temp/importTemp/")+"/");
			CcdjService drService = TransactionControlProxy.newProxy(new CcdjService());
			HashMap<String, Object> resultMap = null;
			/**
			 * 此次需要进行异常自行捕捉，输出主动扔出的异常信息，
			 * 原因是带文件的表单提交不是模拟ajax表单提交，是真正的表单提交，
			 * 请求头无x-requested-with属性，父类superAction判断无法进行前台输出，
			 * 如不理解请自行阅读superAction异常捕捉
			 */
			try {
				resultMap = drService.saveDrExcelInfo(file.getInputStream(),model);
			} catch (SystemException e) {
				// TODO 自动生成 catch 块
				response.getWriter().print(getJsonMessage(e.getMessage()));
				return null;
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_OPERATE_FAIL));
				return null;
			}
			JSONObject jsonObject = getJsonMessageByKey((String)resultMap.get("message"));
			if("false".equals(resultMap.get("result"))){
				jsonObject.put("gid",resultMap.get("gid"));
			}
		    response.getWriter().print(jsonObject);
		}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DR_KFILE));
		}
		
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 下载导入模板
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午09:16:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("gygl_ccdj.xls".getBytes(), "GBK") + "\"");
		CcdjForm ccdj = (CcdjForm)form;
		ccdj.setUsername(getUser(request).getUserName());
		ccdj.setPath(url);
		ccdj.setGyglyQx((String) request.getSession().getAttribute("gyglyQx"));
		service.createWwb(response.getOutputStream(),ccdj);
		return null;
	}
	
	/**
	 * 
	 * @描述:下载错误数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-14 上午11:20:10
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
}

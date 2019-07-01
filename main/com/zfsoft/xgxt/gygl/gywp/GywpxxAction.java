/**
 * @部门:学工产品事业部
 * @日期：2013-8-6 下午03:36:37 
 */  
package com.zfsoft.xgxt.gygl.gywp;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(公寓物品管理) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-6 下午03:36:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GywpxxAction extends SuperAction {
	
	private static final String url = "gygl_qywpxx.do?method=cxGywpxxList";

	/**
	 * 
	 * @描述:(查询公寓所有楼层的物品维护信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-6 下午04:20:52
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
	@SystemAuth(url = "gygl_qywpxx.do?method=cxGywplrxxList")
	public ActionForward cxGywplrxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getGywplrxxList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "gygl_qywpxx.do?method=cxGywplrxxList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxGywplrxxList");
	}
	/**
	 * 
	 * @描述:(查询维护物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-6 下午04:31:36
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
	public ActionForward cxGywpxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String pk=request.getParameter("pk");
		//根据选中寝室初始查询条件
		if(pk!=null&&!"".equalsIgnoreCase(pk)){
			String[] ldqs=pk.split("@@");
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_ld(new String[]{ldqs[0]});
			searchModel.setSearch_tj_qsh(new String[]{ldqs[1]});
			request.setAttribute("searchTj", searchModel);
		}
		
		String path = "gygl_qywpxx.do?method=cxGywpxxList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxGywpxxList");
	}
	/**
	 * 
	 * @描述:(增加公寓物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-6 下午05:12:39
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
	public ActionForward addGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		String ids=request.getParameter("ids");
		int num=ids.split(",").length;
		List<HashMap<String, String>> wpdlList=service.getWpdlList();
		List<HashMap<String, String>> wplbList=service.getWplbList();
		request.setAttribute("ids", ids);
		request.setAttribute("num", num);
		request.setAttribute("wpdlList", wpdlList);
		request.setAttribute("wplbList", wplbList);
		return mapping.findForward("addGywpxx");
	}
	
	/**
	 * 
	 * @描述:(保存增加公寓物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午10:36:43
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
	@SystemLog(description="访问公寓管理-物品维护-物品登记-增加WPMC:{wpmc},WPDLDM:{wpdldm},WPLBDM:{wplbdm},SL:{sl}")
	public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:(修改公寓物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午10:38:12
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
	public ActionForward updateGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		GywpxxForm myForm=service.getModel(model);
		if(myForm.getHhyy()!=null){
			myForm.setHhyy(myForm.getHhyy().replaceAll("<br/>", "\n"));
		}
		if(myForm.getBz()!=null){
			myForm.setBz(myForm.getBz().replaceAll("<br/>", "\n"));
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		List<HashMap<String, String>> wpdlList=service.getWpdlList();
		List<HashMap<String, String>> wplbList=service.getWplbList();
		request.setAttribute("wpdlList", wpdlList);
		request.setAttribute("wplbList", wplbList);
		return mapping.findForward("updateGywpxx");
	}
	
	/**
	 * 
	 * @描述:(保存修改公寓物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午10:50:11
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
	@SystemLog(description="访问公寓管理-物品维护-物品登记-修改PK:{id},WPDLDM:{wpdldm},WPLBDM:{wplbdm},SL:{sl}")
	public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		boolean result=service.runUpdate(model);
		String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:(查看维护物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午11:25:07
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
	public ActionForward viewGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;
		GywpxxForm myForm=service.getModel(model);
		if(myForm.getHhyy()!=null){
			myForm.setHhyy(myForm.getHhyy().replaceAll("<br/>", "\n"));
		}
		if(myForm.getBz()!=null){
			myForm.setBz(myForm.getBz().replaceAll("<br/>", "\n"));
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("viewGywpxx");
	}
	
	
	
	/**
	 * 
	 * @描述:(删除公寓物品信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午10:49:07
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
	@SystemLog(description="访问公寓管理-物品维护-物品登记-删除VALUES:{values}")
	public ActionForward delGywpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GywpxxService service=new GywpxxService();
		String values=request.getParameter("values");
		int num=service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
								: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:导出功能
	 * @作者：cmj
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录: 
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
		GywpxxService service=new GywpxxService();
		GywpxxForm model=(GywpxxForm) form;

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		for (HashMap<String, String> hashMap : resultList) {
			String hhyy=hashMap.get("hhyy");
			String bz=hashMap.get("bz");
			if(hhyy!=null){
				hashMap.put("hhyy", hhyy.replaceAll("<br/>", "\n"));
			}
			if(bz!=null){
				hashMap.put("bz", bz.replaceAll("<br/>", "\n"));
			}
			
		}
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	

}

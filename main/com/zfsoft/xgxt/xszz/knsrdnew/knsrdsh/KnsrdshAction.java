/**
 * @部门:学工产品事业部
 * @日期： 2014-1-27 上午10:13:33 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难生认定管理模块
 * @类功能描述: TODO(困难生认定审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-27 上午10:13:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdshAction extends SuperAction {
	//定义学生证补办中学生证补办常量可以从基本信息表中获取学生信息
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String KNSRD = "knsrd";
	
	private static final String url = "xg_xszz_knsrd_knsh.do";
	
	/**
	 * 
	 * @描述:TODO(困难生认定审核列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午10:28:40
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
	public ActionForward knsrdshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdshForm model = (KnsrdshForm) form;
		KnsrdshService service = new KnsrdshService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办审核数据
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_xszz_knsrd_knsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knsrdshManage");
	}
	
	/**
	 * 
	 * @描述:TODO(困难生认定单个审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-10 上午10:21:59
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
	@SuppressWarnings("deprecation")
	@SystemLog(description="访问学生资助-困难生认定-困难生认定审核-审核-NRIDS:{nrids}")
	public ActionForward knsrdDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdshForm model = (KnsrdshForm) form;
		KnsrdshService service = new KnsrdshService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//审核流程信息
			//HashMap<String,String> infoList = service.getXszbbshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//保存单个审核
			String jsonStr = request.getParameter("json");
			JSONArray jsonArray = new JSONArray(jsonStr);
			String nrids = request.getParameter("nrids");
			boolean result = service.saveSh(model,user,nrids,jsonArray);
			//boolean result = false;
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		//knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//获取困难生认定指标属性集合
		knsrdzbsxList = service.getKnsrdzbsxList(model);
		//获取困难生认定指标内容集合
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//困难生认定指标内容集合联合困难生认定指标属性集合放到map 集合中 在前台页面回显
		for (HashMap<String, String> hm : knsrdzbsxList) {
			hm=(HashMap<String, String>) StringUtils.formatDataView(hm);
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = service.getKnsrdzbnrsqList(sxid,model.getSqid());
			object.put(hm,knsrdzbnrList);
		}
		String nrids = service.getKnsrdzbsqnrids(model);
		//加载困生认定记录
		request.setAttribute("rdlsjlList", new KnsjgService().getKnsInfoList(model.getXh()));
		request.setAttribute("nrids", nrids);
		request.setAttribute("object", object);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		KnsdcService knsdcSerivce = new KnsdcService();
		//困难生档次列表
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		return mapping.findForward("knsrdzbDgsh");
	}
	/**
	 * 
	 * @描述:批量审核
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-10-8 下午03:30:12
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
	public ActionForward knsrdPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcService knsdcSerivce = new KnsdcService();
		//困难生档次列表
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		
		return mapping.findForward("knsrdPlsh");
	}
	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-10-8 下午05:01:19
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
	@SystemLog(description="访问学生资助-困难生认定-困难生认定审核-批量审核保存-SQIDS:{sqids}")
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdshForm myForm = (KnsrdshForm) form;
		KnsrdshService service = new KnsrdshService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @描述:TODO(撤销困难生认定审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-10 上午10:21:17
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
	@SystemLog(description="访问学生资助-困难生认定-困难生认定审核-撤销-SQID:{sqid}")
	public ActionForward cancelKnsrdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdshForm model = (KnsrdshForm) form;
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		KnsrdshService service = new KnsrdshService();
		//撤销困难生认定审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(查看困难生认定审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 下午02:30:26
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
	public ActionForward viewKnsrdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdshForm model = (KnsrdshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
	
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//审核流程信息
			//HashMap<String,String> infoList = service.getXszbbshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		KnsrdshService service = new KnsrdshService();
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("shzt", model.getShzt());
		
		model=service.getModel(model);
		/*KnsrdzbForm knsrdzbForm = new KnsrdzbForm();*/
		//KnsrdzbService knsrdzbService = new KnsrdzbService();
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		//knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//获取困难生认定指标属性集合
		knsrdzbsxList = service.getKnsrdzbsxList(model);
		//获取困难生认定指标内容集合
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//困难生认定指标内容集合联合困难生认定指标属性集合放到map 集合中 在前台页面回显
		for (HashMap<String, String> hm : knsrdzbsxList) {
			hm=(HashMap<String, String>) StringUtils.formatDataView(hm);
			String sxid = hm.get("sxid"); 
			//knsrdzbnrList = knsrdzbService.getKnsrdzbnrList(sxid);
			knsrdzbnrList = service.getKnsrdzbnrsqList(sxid,model.getSqid());
			object.put(hm,knsrdzbnrList);
		}
		
		String nrids = service.getKnsrdzbsqnrids(model);
		request.setAttribute("nrids", nrids);
		request.setAttribute("object", object);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", model);
		return mapping.findForward("viewKnsrdsh");
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:27:45
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdshForm model = (KnsrdshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		KnsrdshService service = new KnsrdshService();
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
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

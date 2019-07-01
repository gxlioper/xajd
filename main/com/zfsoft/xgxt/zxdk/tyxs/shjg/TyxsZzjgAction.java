/**
 * @部门:学工产品事业部
 * @日期：2015-4-17 下午04:07:33 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.tyxs.cssz.TyxsCsszService;
import com.zfsoft.xgxt.zxdk.tyxs.zzsq.TyxsZzsqService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2015-4-17 下午04:07:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TyxsZzjgAction extends SuperAction<TyxsZzjg, TyxsZzjgService> {
	private static final String ZZPZ = "tyxs";
	private Log logger = LogFactory.getLog(TyxsZzjgAction.class);
	
	private static final String url = "zxdk_tyxs_zzjg.do";

	/**
	 * 
	 * @描述: 贷款结果
	 * @作者：冯兰英[工号：1177]
	 * @日期：2014-9-28 下午02:16:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward tyxsZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsCsszService csszService = new TyxsCsszService();
		request.setAttribute("cssz", csszService.getModel());

		request.setAttribute("path", "zxdk_tyxs_zzjg.do");
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);

		return mapping.findForward("zzjgList");
	}

	/**
	 * 
	 * @描述: ajax加载贷款结果列表
	 * @作者：冯兰英[工号：1177]
	 * @日期：2014-9-25 下午03:38:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getZzjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		TyxsZzjg model = (TyxsZzjg) form;
		User user = getUser(request);

		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		
		response.getWriter().print(dataList);
		return null;

	}

	/**
	 * /**
	 * 
	 * @描述: 贷款申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2014-9-28 下午03:05:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjg model = (TyxsZzjg) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xn", Base.currXn);	
		
		TyxsCsszService csszService = new TyxsCsszService();
		request.setAttribute("cssz", csszService.getModel());
		String path = "tyxs_zzjg.do?method=addZzjg";
		request.setAttribute("path", path);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", new TyxsZzsqService().getYhList());
		}
		this.saveToken(request);
		return mapping.findForward("addZzjg");
	}

	/**
	 * 
	 * @描述:修改
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-18 上午11:52:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		TyxsZzjg myForm = (TyxsZzjg) form;

		TyxsZzjg model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xn", Base.currXn);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", new TyxsZzsqService().getYhList());
		}
		return mapping.findForward("editZzjg");
	}

	/** 按学号学年查询记录总数 **/
	public ActionForward getCountByXhAndXn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TyxsZzjgService service = getService();
		TyxsZzjg model = (TyxsZzjg) form;

		String count = service.getCountByXhAndXn(model);
		response.getWriter().print(count);
		return null;
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-18 下午02:10:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-退役学生入学学费管理-审核结果-删除：values:{ids}")
	public ActionForward delZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		String ids = request.getParameter("ids");

		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * 
	 * @描述:查看
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-18 下午02:11:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward ckZzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzjgService service = getService();
		TyxsZzjg myForm = (TyxsZzjg) form;

		TyxsZzjg model = service.getModel(myForm.getId());
		
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhmc", new TyxsZzsqService().getYhListByYhdm(model.getYhdm()));
		}
		return mapping.findForward("ckZzjg");
	}

	/**
	 * 
	 * @描述:根据ID查询结果信息
	 * @作者：cq [工号：785]
	 * @日期：2014-12-25 下午03:16:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward zzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		TyxsZzjgService service = getService();

		HashMap<String, String> dkxxMap = service.getZzjgById(id);

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}

	/**
	 * 
	 * @描述：导出
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-18 下午04:02:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dcjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzjgService service = getService();
		TyxsZzjg model = (TyxsZzjg) form;

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
	
	

	/**打印申请表**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//一条数据
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//多条数据
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	
	
	private synchronized File print(String id,HttpServletRequest request) throws Exception{

		Map<String, Object> data = new HashMap<String, Object>();
		
		TyxsZzjgService service = getService();
		TyxsZzjg model = service.getModel(id);
		
		HashMap<String, String> models = service.getDjbById(id);
		
		data.put("m", models);		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		data.put("j", xsjbxx);
		if(Base.xxdm.equals("10704")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "tyxs_zzsq_10704.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}else{
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "tyxs_zzsq.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}
	}
	
	/**
	 * 
	 * @描述：新增表单保存方法重写
	 * @作者：yxy[工号：1206]
	 * @日期：2015-4-18 下午04:02:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TyxsZzjg model = (TyxsZzjg) form;
		TyxsZzjgService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	
}

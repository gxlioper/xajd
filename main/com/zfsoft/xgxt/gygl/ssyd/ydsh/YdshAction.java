package com.zfsoft.xgxt.gygl.ssyd.ydsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqForm;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-宿舍异动
 * @类功能描述: 宿舍异动审核
 * @作者： qilm
 * @时间： 2013-9-27
 * @版本： V1.0
 */
public class YdshAction extends SuperAction {
	
	private static final String url = "ydshbase.do";
	
	private YdshService service = TransactionControlProxy.newProxy(new YdshService());
	
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @描述:宿舍异动审核列表
	 * @作者： qilm
	 * @时间： 2013-9-27
	 * @版本： V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdshForm myForm = (YdshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydshbase.do");
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			// ========== 床位设置 begin ============
			for (HashMap<String, String> resultMap : resultList) {
				String ssydlx = resultMap.get("ssydlx");
				String sqshHideCwxx = "";
				// 宿舍调整
				if (YdshService._YDLX_SSTZ.equals(ssydlx)) {
					// 设置调整后床位信息
					sqshHideCwxx = resultMap.get("tzhlddm") + "_"
							+ resultMap.get("tzhqsh") + "_" + resultMap.get("tzhcwh");
				}else if (YdshService._YDLX_SSRZ.equals(ssydlx)) { // 入住
					sqshHideCwxx = resultMap.get("tzqlddm") + "_"
							+ resultMap.get("tzqqsh") + "_" + resultMap.get("tzqcwh");
				}
				// ========== 审核床位设置 begin ============
				ShlcDao shlcDao = new ShlcDao();
				List<HashMap<String, String>> shyjList = shlcDao.getShyjList(resultMap.get("ssydsqid"), "desc");
				if(shyjList.size() > 0){
					HashMap<String, String> shyj = shyjList.get(0);
					sqshHideCwxx = shyj.get("zd7") + "_" + shyj.get("zd9") + "_" + shyj.get("zd10");
				}
				// ========== 审核床位设置 end ============
				resultMap.put("sqshHideCwxx", sqshHideCwxx);
			}
			// ========== 床位设置 end ============
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//最后一级撤销审核后调用的路径
		request.setAttribute("cancelPath", "ydsh.do?method=cancel");
		request.setAttribute("path", "ydshbase.do");
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("list");
	}
	
	
	/**
	 * @描述:职务审核
	 * @作者：zhangjw
	 * @日期：2013-8-9 下午5:02:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动审核-审核PK:{ssydsqid}")
	public ActionForward ydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdshForm myForm = (YdshForm) form;
		YdsqService sqservice = new YdsqService();
		//获取申请信息
		YdsqForm model = sqservice.getModel(myForm);
		
		User user = getUser(request);
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(model.getSsydlx());
		request.setAttribute("zsfxs", shlcXx.getZsfxs());	
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			
			//保存审核结果
			myForm.setSplcid(model.getSplcid());
			myForm.setXh(model.getXh());
			myForm.setSsydsqid(model.getSsydsqid());
			myForm.setSsydlx(model.getSsydlx());
			
			boolean result = service.ydsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生信息显示（可配置）
		szXsxx(request,myForm.getXh());
		request.setAttribute("myForm", myForm);
		//床位信息显示
		if(!"12303".equals(Base.xxdm)){
		request.setAttribute("cwxxData",StringUtils.formatData(sqservice.getCwxx(model.getTzqlddm(),
				model.getTzqqsh(), model.getTzqcwh())));
		}
		// 宿舍调整
		if (YdshService._YDLX_SSTZ.equals(model.getSsydlx())) {
			// 设置调整后床位信息
			model.setCwxx(model.getTzhlddm() + "_"
					+ model.getTzhqsh() + "_" + model.getTzhcwh());
		}else if (YdshService._YDLX_SSRZ.equals(model.getSsydlx())) { // 入住
			model.setCwxx(model.getTzqlddm() + "_"
					+ model.getTzqqsh() + "_" + model.getTzqcwh());
		}
		
		//异动申请信息
		request.setAttribute("data", StringUtils.formatData(model));
		//宿舍異動類型
		request.setAttribute("ssydlx", model.getSsydlx());
		request.setAttribute("xxdm", Base.xxdm);
		// ========== 审核床位设置 begin ============
		ShlcDao shlcDao = new ShlcDao();
		List<HashMap<String, String>> shyjList = shlcDao.getShyjList(myForm.getSsydsqid(), "desc");
		String shCwxx = model.getCwxx();
		if(shyjList.size() > 0){
			HashMap<String, String> shyj = shyjList.get(0);
			shCwxx = shyj.get("zd7") + "_" + shyj.get("zd9") + "_" + shyj.get("zd10");
		}
		if (YdshService._YDLX_SSTZ.equals(model.getSsydlx())) { // 设置调整后床位信息
			myForm.setCwxx(shCwxx);
		}else if (YdshService._YDLX_SSRZ.equals(model.getSsydlx())) { // 入住
			myForm.setRzcwxx(shCwxx);
		}
		// ========== 审核床位设置 end ============
		if("ck".equalsIgnoreCase(myForm.getType())){
			return mapping.findForward("ydck");
		}
		
		return mapping.findForward("ydsh");
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}

		//学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz("gyglssyd");
		request.setAttribute("jbxxList", jbxxList);
	}
	

	/**
	 * @描述:最后一级撤销审核
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdshForm model = (YdshForm) form;
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());	
		// 业务回滚
		boolean result = service.cancel(model.getSplcid(), shxx.get("ywid"));
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @描述:撤销审核操作（需要单独处理业务）
	 * @作者：qilm
	 * @日期：2013-9-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动审核-撤销PK:{shid}")
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdshForm model = (YdshForm) form;
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());
		boolean result = service.runCancel(user.getUserName(), shxx.get("ywid"), model.getSplcid(), shxx.get("gwid"));
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 宿舍异动审核导出
	 * @作者：qilm
	 * @日期：2013-9-29
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
		YdshForm model = (YdshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		YdshService service = new YdshService();
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getPageList(model,user);//查询出所有记录，不分页
		
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
	
	
	
	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 下午05:06:17
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
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动审核-批量审核PK:{id}")
	public ActionForward ydPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdshForm model = (YdshForm) form;
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(model.getSsydlx());
		request.setAttribute("zsfxs", shlcXx.getZsfxs());
		
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("ydPlsh");
	}
	
}

package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-宿舍异动
 * @类功能描述:宿舍异动审核
 * @作者： qilm
 * @时间： 2013-9-27
 * @版本： V1.0
 */
public class YdjgAction extends SuperAction {
	
	private static final String url = "ydjgbase.do";
	
	private static final String zslsurl = "lsxxgl_lsxxgl_zslsxxgl.do";
	/**
	 * 定义公寓管理宿舍异动可以从基本信息表中获取学生信息
	 */
	private static final String GYGLSSYD = "gyglssyd";
	
	private YdjgService service = new YdjgService();

	private static List<HashMap<String, String>> jbxxList = null;

	private BdpzService bdpzService = new BdpzService();
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
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdjgForm myForm = (YdjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydjgbase.do");
			myForm.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "ydjgbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @描述: 查看学生异动信息
	 * @作者：qilm
	 * @日期：2013-10-8 上午09:58:06
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
	public ActionForward ckXsydInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgForm myForm = (YdjgForm) form;
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		request.setAttribute("jbxxList", jbxxList);
	
		// 学生最近的一次宿舍异动信息
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", StringUtils.formatData(xsydInfo));

		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(xsydInfo.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		// 学生最近的更多宿舍异动信息
		List<HashMap<String, String>> xsYdList = service.getXsYdList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("ckXsydInfo");
	}
	

	/**
	 * 
	 * @描述: 查看床位异动信息
	 * @作者：qilm
	 * @日期：2013-10-8 上午09:58:06
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
	public ActionForward ckQsydInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdsqService sqservice = new YdsqService();

		YdjgForm myForm = (YdjgForm) form;

		// 床位信息
		request.setAttribute("cwxxData", StringUtils.formatData(sqservice.getCwxx(myForm.getYdqlddm(),
				myForm.getYdqqsh(),myForm.getYdqcwh())));
		
		//当前学年学期
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);

		// 当前学年学期床位异动信息
		List<HashMap<String, String>> currQsYdList = service.getQsYdList(
				myForm,YdjgService.CURR_XNXQ_FLG_Y);
		
		// 非当前学年学期床位异动信息
		List<HashMap<String, String>> notCurrQsYdList = service.getQsYdList(
				myForm,YdjgService.CURR_XNXQ_FLG_N);
		
		request.setAttribute("currQsYdList", currQsYdList);
		
		request.setAttribute("notCurrQsYdList", notCurrQsYdList);
		
		return mapping.findForward("ckQsydInfo");
	}

	/**
	 * 
	 * @描述:查看宿舍异动信息
	 * @作者：qilm
	 * @日期：2013-10-09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward ydjgck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm myForm = (YdjgForm) form;

		HashMap<String, String> ydjg = service.getYdjg(myForm);

		if (!StringUtil.isNull(ydjg.get("xh"))) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(ydjg.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(ydjg.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		// 学生基本信息
		String path = "ydjg.do?method=ydjgck";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("data", StringUtils.formatData(ydjg));
		
		return mapping.findForward("ydjgck");
	}
	/**
	 * 
	 * @描述:增加異動結果
	 * @作者：qilm
	 * @日期：2013-9-17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-宿舍异动结果-增加XH:{xh},SSYDLX:{ssydlx},XN:{xn},XQ:{xq},TSTZYY:{tstzyy},TSTZSJ:{tstzsj},SFCWCSH:{sfcwcsh}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm myForm = (YdjgForm) form;

		YdsqService ydsqService = new YdsqService();
		
		User user = getUser(request);
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc();
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			myForm.setTjsqrxm(user.getUserName());
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		// 学生基本信息
		String path = "ydjg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		// 退宿原因
		request.setAttribute("tstzyyList", ydsqService.getTstzyy());
		//调整原因
		request.setAttribute("tzyyList", ydsqService.getTzyy());
		//入住原因
		CwglService cwglService = new CwglService();
		request.setAttribute("rzyyList", cwglService.getRzyyList());
		request.setAttribute("dqxq",Base.getDqxqmc());
		request.setAttribute("dqxn",Base.currXn);
		// 床位信息
		request.setAttribute("cwxxData", StringUtils.formatData(ydsqService.getCwxxForXh(myForm.getXh())));
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyy-MM-dd"));
		return mapping.findForward("ydjgzj");
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
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm model = (YdjgForm) form;
		YdjgService service = new YdjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页

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
	 * 下载表格
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printTstzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgService service = new YdjgService();
		String ssydid = request.getParameter("ssydid");
		
		String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
		if(ssydid != null && ssydid.split(",").length == 1){	/*-->下载单个表格*/
			HashMap<String , String> data = service.getYdxx(ssydid);
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
			data.put("dyrq",rq);
			data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
			
			objectData.putAll(data);
			File file = null;
			String xxdm = Base.xxdm;
			String guid = "gygl_tstzd_"+xxdm;
			file = BbdmUtils.getSigleFile(guid, objectData);
			if(null==file){
				//通用登记表
				file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
			}
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String ssydids:ssydid.split(",")){
				HashMap<String , String> data = service.getYdxx(ssydids);
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
				data.put("dyrq",rq);
				data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
				objectData.putAll(data);
				File file = null;
				String xxdm = Base.xxdm;
				String guid = "gygl_tstzd_"+xxdm;
				file = BbdmUtils.getSigleFile(guid, objectData);
				if(null==file){
					//通用登记表
					file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
				}
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	//退宿单打印
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printTsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgService service = new YdjgService();
		String ssydid = request.getParameter("ssydid");
		String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
		if(ssydid != null && ssydid.split(",").length == 1){	/*-->下载单个表格*/
			HashMap<String , String> data = service.getYdxx(ssydid);
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
			data.put("dyrq",rq);
			data.put("rzsj", DateTranCnDate.fomartDateToCn(data.get("ydqqsrzsj")));
			data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
			objectData.putAll(data);
			File file = null;
			String xxdm = Base.xxdm;
			String guid = "gygl_tsd_"+xxdm;
			file = BbdmUtils.getSigleFile(guid, objectData);
			if(null==file){
				//通用登记表
				file = BbdmUtils.getSigleFile("gygl_tsd", objectData);
			}
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String ssydids:ssydid.split(",")){
				HashMap<String , String> data = service.getYdxx(ssydids);
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
				data.put("dyrq",rq);
				data.put("rzsj", DateTranCnDate.fomartDateToCn(data.get("ydqqsrzsj")));
				data.put("zsfjedx", MoneyFormat.format(data.get("zsfje")));
				objectData.putAll(data);
				File file = null;
				String xxdm = Base.xxdm;
				String guid = "gygl_tsd_"+xxdm;
				file = BbdmUtils.getSigleFile(guid, objectData);
				if(null==file){
					//通用登记表
					file = BbdmUtils.getSigleFile("gygl_tsd", objectData);
				}
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @description	： 住宿历史列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-16 下午05:09:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = zslsurl)
	public ActionForward zslslist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdjgForm myForm = (YdjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("lsxxgl_lsxxgl_zslsxxgl.do");
			myForm.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "lsxxgl_lsxxgl_zslsxxgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zslslist");
	}
	
	/**
	 * @description	：住宿信息查看
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-16 下午05:09:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = zslsurl)
	public ActionForward zsxxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdjgForm myForm = (YdjgForm) form;

		HashMap<String, String> ydjg = service.getYdjg(myForm);

		if (!StringUtil.isNull(ydjg.get("xh"))) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(ydjg.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(ydjg.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}
		
		// 学生基本信息
		String path = "ydjg.do?method=ydjgck";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("data", StringUtils.formatData(ydjg));
		
		return mapping.findForward("zsxxck");
	}
	
	/**
	 * @description	： 查看学生住宿历史信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-16 下午05:30:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = zslsurl)
	public ActionForward ckXsydInfoLsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdjgForm myForm = (YdjgForm) form;
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		request.setAttribute("jbxxList", jbxxList);
	
		// 学生最近的一次宿舍异动信息
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", StringUtils.formatData(xsydInfo));
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(xsydInfo.get("ssydlx"));
		if(shlcXx != null){
			request.setAttribute("zsfxs", shlcXx.getZsfxs());
		}

		// 学生最近的更多宿舍异动信息
		List<HashMap<String, String>> xsYdList = service.getXsYdList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("ckXsydInfoLsxx");
	}
	
}

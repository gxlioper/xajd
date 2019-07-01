/**
 * @部门:学工产品事业部
 * @日期：2014-9-28 下午02:13:48 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqService;

import com.zfsoft.utils.StringUtil;
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
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.gygl.gyjl.GyjltjForm;
import com.zfsoft.xgxt.xpjpy.tjcx.TjcxModel;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdDao;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdForm;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdService;

import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-贷款结果
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-28 下午02:13:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkjgAction extends SuperAction<XyddkModel, DkjgService> {

	private static final String GJZXDK = "gjzxdk";
	
	private static final String url = "zxdk_gjdk_dkjg.do";
	
	/**
	 * 
	 * @描述: 贷款结果
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-28 下午02:16:33
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
	public ActionForward dkjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_gjdk_dkjg.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		if(Globals.XXDM_ZJJDZYJSXY.equals(Base.xxdm)){
			searchModel.setSearch_tj_xn(new String[]{Base.afterXn});
		}else{
			searchModel.setSearch_tj_xn(new String[]{Base.currXn});
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xxdm", Base.xxdm);
		
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		return mapping.findForward("dkjgList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载贷款结果列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:38:22
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
	public ActionForward getDkjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @描述: 贷款申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-28 下午03:05:35
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
	public ActionForward addDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyddkModel model = (XyddkModel) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("dqxn", Base.currXn);
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		String path = "zxdkDkjg.do?method=addDkjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addDkjg");
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkjgService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("xxdm", Base.xxdm);
		/**
		 * 华师大个性化代码
		 */
		if("10511".equals(Base.xxdm)){
			XyddkService sqService = new XyddkService();
			List<HashMap<String, String>> nddkList = sqService.getNdkbList(myForm.getId());
			request.setAttribute("nddkList", nddkList);
			HashMap<String, String> jesxMap = sqService.getXsxxByHsd(model.getXh());
			request.setAttribute("jesx", jesxMap.get("jesx"));
			request.setAttribute("mkxxForm", myForm);
		}
		return mapping.findForward("editDkjg");
	}
	
	@SystemAuth(url = url)
	public ActionForward ckDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DkjgService service = getService();
		XyddkModel myForm = (XyddkModel) form;
		
		XyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> fdxxList = null;
			
			if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
				fdxxList = service.getFdxxListForXh(model.getXh());
			}else if("10511".equals(Base.xxdm)){
				fdxxList = service.getFdxxListHsd(model.getHtbh());
			}else{
				fdxxList = service.getFdxxList(model.getHtbh());
			}
			request.setAttribute("fdxxList", fdxxList);
			
			WyxxService wyxxService = new WyxxService();
			request.setAttribute("wyxx", StringUtils.formatData(wyxxService.getModel(model.getHtbh())));
			
			List<HashMap<String,String>> xdsqList = service.getDkjgXdxxTg(model.getHtbh());
			/**
			 * 华师大个性化代码
			 */
			if("10511".equals(Base.xxdm)){
				XyddkService sqService= new XyddkService();
				List<HashMap<String, String>> nddkList = sqService.getNdkbList(myForm.getId());
				request.setAttribute("nddkList", nddkList);
			}
			request.setAttribute("xdsqList", xdsqList);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		//显示续贷信息
//		request.setAttribute("xdxxlist", new DkjgService().getDkjgXdxxTg(model.getHtbh()));
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("ckDkjg_zjdx");
		}
		
		return mapping.findForward("ckDkjg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-国家助学贷款-贷款结果-删除VALUES:{ids}")
	public ActionForward delDkjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkjgService service = getService();
		String ids = request.getParameter("ids");
		XyddkService sqservice = new XyddkService();
		/**
		 * 暂时是单选的，所以以下华师大判断按单选处理
		 * 已有放贷记录的结果不能被删除
		 *
		 */
		if("10511".equals(Base.xxdm)){
			String htbh = service.getModel(ids.split(",")[0]).getHtbh();
			String fdnum = new HsdxdService().getfdNum(htbh);
		
			if(!("0".equals(fdnum))){
				String message = "已有放贷记录， 不允许删除！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		boolean result = service.runDelete(ids.split(",")) > 0;
		/**
		 * 如果是华中师范大学,删除xg_hsd_zxdk_ndkb中的内容
		 */
		if("10511".equals(Base.xxdm)){
			sqservice.delFdb(ids.split(","));
			sqservice.delNdkb(ids.split(","));
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward dcjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

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
	 * @描述: 续贷申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午02:04:36
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
	@SystemAuth(url = "zxdk_gjdk_xdsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "该页面只能学生用户访问。");
			return mapping.findForward("error");
		}
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		if (Constants.CLOSE.equals(csszModel.getXdKg())){
			request.setAttribute("message", "续贷功能未开放。");
			return mapping.findForward("error");
		}
		
		DkjgService service = getService();
		XyddkModel dkxx = service.getModelByXh(user.getUserName());
		
		if (dkxx == null){
			request.setAttribute("message", "您未申请过国家助学贷款。");
			return mapping.findForward("error");
		}
		
		List<HashMap<String,String>> xdsqList = service.getXdsqList(dkxx.getHtbh());
		request.setAttribute("xdsqList", xdsqList);
		request.setAttribute("dkxx", StringUtils.formatData(dkxx));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("path", "zxdk_gjdk_xdsq.do");
		request.setAttribute("xxdm", Base.xxdm);
		//西安科技大学个性化显示上年度单科最低分
//		if("10704".equals(Base.xxdm)){
//			int fs = service.getLowestLastYear(user.getUserName());
//			request.setAttribute("fs", fs);
//		}
	//	request.setAttribute("splc", csszModel.getXydxdshlc());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xdsq");
	}
	
	
	/**
	 * 
	 * @描述: 保存续贷申请
	 * @作者：屈朋辉[工号：445]
	 * @修改者：yxy 修改内容：增加提交类型
	 * @日期：2014-10-11 下午04:11:04
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
	
	public ActionForward saveXdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		boolean result = service.saveXdsq(model, user);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**按学号学年查询记录总数**/
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DkjgService service = getService();
		XyddkModel model = (XyddkModel) form;
		
		String count = service.getCountByXhAndXn(model);
		response.getWriter().print(count);
		return null;
	}
	
	
	
	
	/**打印申请表**/
	@SystemAuth(url = url)
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
	
	
	/**
	 * 
	 * @描述:浙大个性化功能添加了个性化方法 returnData_10335()
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:56:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private synchronized File print(String id,HttpServletRequest request) throws Exception{

		Map<String, Object> data = new HashMap<String, Object>();
		
		DkjgService service = getService();
		XyddkModel model = service.getModel(id);
		if(Base.xxdm.equalsIgnoreCase("10335") && StringUtils.isNotNull(model.getXzf())){
			model.setZsfdks(Integer.parseInt(model.getZsfdks())*Integer.parseInt(model.getXzf())+"");
			model.setXfdks(Integer.parseInt(model.getXfdks())*Integer.parseInt(model.getXzf())+"");
		}
		data.put("m", model);
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		if(Base.xxdm.equalsIgnoreCase("10335")){
			data = this.returnData_10335(data, xsjbxx, model);
		}
		data.put("j", xsjbxx);
		if(Base.xxdm.equalsIgnoreCase("10335")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "zxdksqb_10335.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
		}
		return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "shhy_gjdk.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
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
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward dkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		DkjgService service = getService();
		
		HashMap<String, String> dkxxMap = service.getDkxxSq(id);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:浙大助学金贷款
	 * @作者：张昌路[工号：982]
	 * @日期：2015-11-25 下午01:51:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @param xsjbxx
	 * @param model
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public Map<String, Object> returnData_10335(Map<String, Object> data,HashMap<String,String> xsjbxx,XyddkModel model) throws Exception{
		XsxxService xsxxService = new XsxxService();
		data.putAll(xsxxService.transformYear_Month(xsjbxx.get("csrq")));
		/*学历判断*/
		String xlmc = "";
		if(StringUtils.isNotNull(xsjbxx.get("xlmc"))){
			 xlmc = xsjbxx.get("xlmc");
		}
		if(xlmc.indexOf("本") >= 0){
			xlmc = "本";
			String xz = xsjbxx.get("xz");
			String bkxzpd = "";
			if(xz.indexOf("四") >= 0 || xz.indexOf("4") >= 0){
				bkxzpd = "4";
			}else if(xz.indexOf("五") >= 0 || xz.indexOf("5") >= 0){
				bkxzpd = "5";
			}else if(xz.indexOf("七") >= 0 || xz.indexOf("7") >= 0){
				bkxzpd = "7";
			}else if(xz.indexOf("八") >= 0 || xz.indexOf("8") >= 0){
				bkxzpd = "8";
			}
			data.put("bkxzpd", bkxzpd);
		}else if(xlmc.indexOf("专") >= 0){
			xlmc = "专";
		}else if(xlmc.indexOf("研") >= 0 || xlmc.indexOf("硕") >= 0 ||  xlmc.indexOf("博") >= 0){
			xlmc = "研";			
		}
		data.put("xlmc", xlmc);
		//贷款期限月
		String qxmon = "";
		if(StringUtils.isNotNull(model.getDkqx())){
			qxmon = String.valueOf(Integer.parseInt(model.getDkqx())*12);
		}
		data.put("qxmon",qxmon);
		//2016年10月14日 现浙大申请表贷款期限 = 贷款期限 × 12 已经和年限没有关系了
//		if(StringUtils.isNotNull(model.getXzf())){
//			qxmon = String.valueOf(Integer.parseInt(model.getXzf())*12);
//		}
//		data.put("qxmon",qxmon);
		//父母亲信息
		data.put("fqxx", xsxxService.getFather(xsjbxx.get("xh")));
		data.put("mqxx", xsxxService.getMother(xsjbxx.get("xh")));
		JtqkdcService service = new JtqkdcService();
		JtqkdcForm jtqk = service.getModel(xsjbxx.get("xh"));
		if(jtqk != null  && StringUtils.isNotNull(jtqk.getSnjtsr()) ){
			 data.put("jtrjysr", jtqk.getSnjtsr());
		}else{
			 data.put("jtrjysr", "");
		}
	   
		return data;
	}
	
	/**
	 * @描述:浙江大学国家助学贷款学院（园）汇总表
	 * @作者：黄辰霁
	 * @日期：2015-11-30 下午02:46:56
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
	public ActionForward getDkjgtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkModel myForm = (XyddkModel) form;
		DkjgService service = getService();
		User user = getUser(request);
		List<HashMap<String, String>> dkjglist = service.getDkjgtj(myForm, user);
		
		String filePath = servlet.getServletContext().getRealPath("")+"/WEB-INF/classes/templates/zxdk/excel/dkprint.xls";
		response.setContentType("application/octet-stream");
		response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "浙江大学国家助学贷款学院（园）汇总表.xls".getBytes("gb2312"), "ISO8859-1" ) );
		
		service.exportHzbNew(dkjglist, user, response, filePath);
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 续贷申请提交
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:32:59
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value = request.getParameter("values");
		HashMap<String, String> model = new DkjgService().getXdxx(value);
		User user = getUser(request);
		boolean result = new DkjgService().submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销续贷申请
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:57:17
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
	public ActionForward cancelXdsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = new DkjgService().cancelRecord(id, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			HashMap<String, String> model = new HashMap<String, String>();
			model.put("id", id);
			model.put("splc", lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(id)) > 0) {
				model.put("shzt", Constants.YW_YTH);
			} else {
				model.put("shzt", Constants.YW_WTJ);

			}
			new DkjgDao().updateXdxx(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:删除续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午03:18:37
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
	public ActionForward delXdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
//			if("10511".equals(Base.xxdm) ){
//				String fdNum = new HsdxdDao().getfdNumXd(values.split(","));
//				if(!("0").equals(fdNum)){
//					String message = "已有放贷记录，该续贷记录不允许被删除！";
//					response.getWriter().print(getJsonMessage(message));
//					return null;
//				}
//			}
			boolean result = new DkjgService().delxdxx(ids);
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
	 * @描述: 修改续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午03:21:48
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
	public ActionForward updatedkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		XyddkModel myForm = (XyddkModel) form;
		String id = request.getParameter("id2");
		String xdly = request.getParameter("xdlys");
		String type = request.getParameter("type");
		XyddkModel myForm = new XyddkModel();
		myForm.setId(id);
		myForm.setXdly(xdly);
		myForm.setType(type);
		User user = getUser(request);
		boolean result = new DkjgService().saveXdsqUpdate(myForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
		
	}
	
	/**
	 * 
	 * @描述: 续贷审核查询列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午10:19:23
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
	public ActionForward getXdshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = null;
			if("10511".equals(Base.xxdm)){
				HsdxdForm hsdxdForm = new HsdxdForm();
				HsdxdDao hsdxdDao = new HsdxdDao();
				hsdxdForm.setSearchModel(model.getSearchModel());
				hsdxdForm.setShzt(model.getShzt());
				resultList =  hsdxdDao.getPageList(hsdxdForm, user);
			}else{
				resultList = new DkjgService().getXdshList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "zxdk_gjdk_xdsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xdshlist");
	}
	
	/**
	 * 
	 * @描述: 续贷单个审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午11:00:06
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
	public ActionForward DgshXdxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//HashMap<String, String> infoList = service.getKhshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
//			String message = khsqService.checkZjeAndGs(model.getXh(),model.getXn(),model.getYxgs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
//			if(!"true".equals(message)){
//				 response.getWriter().print(getJsonMessage(message));
//				 return null;
//			}
			User user = getUser(request);
			// 保存单个审核
			boolean result = new DkjgService().saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> xdxx = null;
		if("10511".equals(Base.xxdm)){
			HsdxdDao hsdxddao = new HsdxdDao();
			xdxx = hsdxddao.getViewCk(model.getId());
		}else{
			xdxx = new DkjgService().getViewCk(model.getId());
		}
		xdxx.put("shid", request.getParameter("shid"));
		request.setAttribute("zsjgxx", xdxx);
		return mapping.findForward("xdDgsh");
	}
	
	/**
	 * 
	 * @描述: 续贷批量审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午11:10:33
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
	public ActionForward xdPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = new DkjgService().savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("xdPlsh");
	}
	
	/**
	 * @描述: 最后一级审核撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午11:10:53
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setId(sqid);
		// 最后一级撤销
		boolean isSuccess = new DkjgService().cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 审核撤销加判断
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午11:11:19
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyddkModel model =new XyddkModel();
		if("10511".equals(Base.xxdm)){
			String id = request.getParameter("id");
			String fdNum = new HsdxdDao().getfdNumXd(new String[]{id});
			if(!("0").equals(fdNum)){
				String message = "已有放贷记录，该续贷记录不允许被撤销！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = new DkjgService().cxshnew(shxx.get("ywid"),model,user);
		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 
	 * @描述: 续贷单个审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午11:00:06
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
	public ActionForward viewXdsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyddkModel model = (XyddkModel) form;
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//HashMap<String, String> infoList = service.getKhshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> xdxx = null;
		if("10511".equals(Base.xxdm)){
			HsdxdDao hsdxddao = new HsdxdDao();
			xdxx = hsdxddao.getViewCk(model.getId());
		}else{
			xdxx = new DkjgService().getViewCk(model.getId());
		}
		xdxx.put("shid", request.getParameter("shid"));
		request.setAttribute("zsjgxx", xdxx);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @描述: 华师大个性化申请保存草稿
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午10:29:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveDkjgForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XyddkModel model = (XyddkModel)form;
		/**
		 * xns:学年数组
		 * nzsfdks:每年住宿费贷款数组
		 * nxfdks:每年学费贷款数组
		 * nshfdks:每年生活费贷款数组
		 * nzsfyjes:每年住宿费应缴额数组
		 * nxfyjes:每年学费应缴额数组
		 * filepath:上传gid
		 * xh:学号
		 * rs:结果
		 * message:错误信息提示字符串
		 */
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * 检验表单里面是否有同名文件
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "存在同名的pdf文件，请更换pdf文件名称！";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				return null;
			}
		}
		
		/**
		 * 是否存在相同文件名,如果存在直接返回
		 */
	    boolean isFileExist = service.getWjmSameRs(xh, filepath);
		if(isFileExist){
			try {
				message = "历史记录存在同名的pdf文件，请更换pdf文件名称！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 是否已有相同学年的申请记录，如果有直接返回
		 */
		boolean isRecordSameXn = service.getXnXhSameRs(xns, xh,null);
		if(isRecordSameXn){
			try {
				message = "已存在相同学年的申请记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		boolean htbhIsNotExists = new DkjgService().checkHtbhIsNotExists(null, model.getHtbh());
		if(!htbhIsNotExists){
			try {
				message = "合同编号已存在，请确认！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		String id =  UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setId(id);
		DkjgService dkjgService = new DkjgService();
		try {
			rs = dkjgService.runInsert(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!rs){
			/**
			 * 结果为false;直接返回
			 */
			try {
				response.getWriter().print(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 *批量插入xg_hsd_zxdk_ndkb表
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
		/**
		 * 插入xg_zxdk_hsd_xydfdb表
		 */
		try {
			rs = service.InsertIntoFdb(id);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			message = "插入xg_zxdk_hsd_xydfdb失败，插入id="+id+"！";
			try {
				response.getWriter().print(getJsonMessage(message));
				return null;
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		/**
		 * 保存失败，删除原先保存的记录
		 */
	    if(!rs){
	    	try {
	    		dkjgService.runDelete(new String[]{id});
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
	    }
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 华师大个性化修改保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午10:31:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveUpdateForHsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XyddkModel model = (XyddkModel)form;
		/**
		 * id:主键id
		 * xns:学年数组
		 * nzsfdks:每年住宿费贷款数组
		 * nxfdks:每年学费贷款数组
		 * nshfdks:每年生活费贷款数组
		 * nzsfyjes:每年住宿费应缴额数组
		 * nxfyjes:每年学费应缴额数组
		 * filepath:上传gid
		 * xh:学号
		 * rs:结果
		 * message:错误信息提示字符串
		 */
		String id = request.getParameter("id");
		String[] xns = request.getParameterValues("xn");
		String[] nzsfdks = request.getParameterValues("nzsfdk");
		String[] nxfdks = request.getParameterValues("nxfdk");
		String[] nshfdks = request.getParameterValues("nshfdk");
		String[] nzsfyjes = request.getParameterValues("nzsfyje");
		String[] nxfyjes = request.getParameterValues("nxfyje");
		String filepath = model.getFilepath();
		XyddkService service = new XyddkService();
		
		String xh = model.getXh();
		String message = "";
		boolean rs = true;
		if(xgxt.utils.String.StringUtils.isNotNull(model.getFilepath())){
			/**
			 * 检验表单里面是否有同名文件
			 */
			if(!service.checkWjmIsSame(model.getFilepath())){
				message = "存在同名的pdf文件，请更换pdf文件名称！";
				try {
					response.getWriter().print(getJsonMessage(message));
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				return null;
			}
		}
		/**
		 * 是否存在相同文件名,如果存在直接返回
		 */
	    boolean isFileExist = service.getWjmSameRsUpdate(xh, filepath);
		if(isFileExist){
			try {
				message = "历史记录存在同名的pdf文件，请更换pdf文件名称！";
				response.getWriter().print(getJsonMessage(message));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
		}
		
		DkjgService dkjgService = new DkjgService();
		try {
			rs = dkjgService.runUpdate(model);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			try {
				try {
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
				} catch (IOException e1) {
					// TODO 自动生成 catch 块
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
			return null;
		}
		if(!rs){
			/**
			 * 结果为false;直接返回
			 */
			try {
				response.getWriter().print(getJsonMessageByKey(rs ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL));
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		HashMap<String, String[]> paraMap = new HashMap<String, String[]>();
		paraMap.put("xns", xns);
		paraMap.put("nzsfdks", nzsfdks);
		paraMap.put("nxfdks", nxfdks);
		paraMap.put("nshfdks", nshfdks);
		paraMap.put("nzsfyjes", nzsfyjes);
		paraMap.put("nxfyjes", nxfyjes);
		/**
		 * 修改保存前需要先删除原来xg_hsd_zxdk_ndkb中数据,然后再插入表中
		 */
		try {
			rs = new XyddkService().delFdb(new String[]{id});
			rs = service.delRs(id);
			if(!rs){
				message = "删除xg_hsd_zxdk_ndkb失败，删除id="+id+"！";
				response.getWriter().print(getJsonMessage(message));
			}
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		/**
		 *批量插入xg_hsd_zxdk_ndkb表
		 */
		rs = service.saveRsBatch(paraMap, xh, id);
		/**
		 * 插入xg_zxdk_hsd_xydfdb表
		 */
		try {
			rs = service.InsertIntoFdb(id);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			message = "插入xg_zxdk_hsd_xydfdb失败，插入id="+id+"！";
			try {
				response.getWriter().print(getJsonMessage(message));
				return null;
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	    String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		try {
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:重写保存方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-2-22 下午01:08:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XyddkModel model = (XyddkModel) form;
		DkjgService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @throws IOException  
	 * @描述:是否可续贷(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-19 上午10:07:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward checkSfXd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		User user = getUser(request);
		String xh = user.getUserName();
		DkjgService service = new DkjgService();
		boolean result = service.countBk(xh);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject obj = JSONObject.fromBean(map);
		response.getWriter().print(obj);
		return null;
	}
	
	
	public ActionForward printJsxxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DkjgService service = new DkjgService();
		XyddkModel modelForm = (XyddkModel) form;
		XyddkModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	//填充模版数据生成word文件
	private File getWord(XyddkModel xyddkModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		XyddkModel model = xyddkModel;
		String xh = model.getXh();
		//基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		String rxny = xsjbxx.get("rxrq");
		if(rxny!= null && rxny.length() >= 7){
			rxny = rxny.substring(0, 7);
		}
		String dkqx = model.getDkqx();
		if(StringUtil.isNull(dkqx)) {
			data.put("dknx", "");
		}else{
			double dknx = Double.parseDouble(dkqx)/12.0;
			data.put("dknx", dknx);
		}
		
		xsjbxx.put("rxny", rxny);
		data.put("rs", xsjbxx);
		data.put("model", model);
		//困难类型信息
		KnsjgService knservice = new KnsjgService();
		HashMap<String, String> knmap = knservice.getKnsInfo(model.getXh(),model.getXn());
		data.put("knlx", knmap.get("dcmc"));
		data.put("knyy", knmap.get("sqly"));
		
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "zxdk","jsdkxxcjb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}
}

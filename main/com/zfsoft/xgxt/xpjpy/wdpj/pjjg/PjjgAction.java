/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:34:40 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjjg;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
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
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.export.util.ImportConfig;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.pjpy.xzhcp.sq.ZhcpDjService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.bbwh.BbwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmModel;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq.PjxmsqService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-评奖结果
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:34:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjjgAction extends SuperAction {

	private static final String template_dir = "classpath://templates//pjpy";//模版路径
	private static final String default_template = "default.xml";//默认模版
	private static final String JXSQ = "jxsq";
	// 配置有荣誉证书模板的学校
	private static final String[] RYZS_XXDMS = { "10022","10704" };
	private static List<HashMap<String, String>> jbxxList = null;
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(JXSQ);
	}
	
	private static final String url = "pj_pjjg.do";
	
	/**
	 * 生成优秀奖学金（浙江大学）
	 */
	public ActionForward scyxjxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgService service = new PjjgService();
		User user = getUser(request);
		boolean result = service.scyxjxj(user);
		if(result){
			response.getWriter().print(getJsonMessage("操作成功！"));
		} else {
			response.getWriter().print(getJsonMessageResult("操作失败！", false));
		}
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 评奖结果
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 上午10:59:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = {url,"xpj_pjjg.do?method=viewPjjgList&xzdm=2&sindex=1"})
	public ActionForward viewPjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		//浙江传媒学院【项目性质】除了“国家奖学金”不选中，其余全部默认选中
		if("11647".equals(Base.xxdm)){
			//先取到项目类型list
			XmwhService xmwhservice = new XmwhService();
			//筛选掉“国家奖学金”
			List<String> xmxz = xmwhservice.getXmxzmw();
			searchModel.setSearch_tj_xxmxz((String[])xmxz.toArray(new String[xmxz.size()]));
		}
		
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("pjzq", CsszService.getPjzq().get("cxxq"));
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xzdm",model.getXzdm());
		if("2".equals(model.getXzdm()))
		{
			request.setAttribute("path","xpj_pjjg.do?method=viewPjjgList&xzdm=2&sindex=1");
		}else{
			request.setAttribute("path","pj_pjjg.do");
		}
		String path = "pj_pjjg.do";
		request.setAttribute("czpath", path);
		//TODO 根据性质代码区分czpath

		request.setAttribute("cssz", csszModel);
		request.setAttribute("ryzsFlag", Arrays.asList(RYZS_XXDMS).contains(Base.xxdm));
		request.setAttribute("userType", getUser(request).getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewPjjgList");
	}

	/**
	 * 
	 * @描述:增加评奖项目结果
	 * @作者：cq [工号：785]
	 * @日期：2013-8-7 上午10:58:15
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-增加-XMMC:{xmmc},XH:{xh},XN:{xn},XQ:{xq},LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward addPjxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		if (!StringUtil.isNull(model.getXh())) {
//			// 加载学生基本信息
//			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(), model.getXn(), model.getXq());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// 唯一性判断（学号，学年，学期,项目名称）
			boolean isExist = service.isExistByPjxmjg(model, SAVE);
			if (!isExist) {
				model.setSqr(user.getUserName());
				// 添加评奖信息
				model.setSjly(user.getUserName());
				//华中师范大学个性化证书编号
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setYlzd1(service.getZsbm(model));
					
				}
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}

		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		
		//项目类型项目和项目性质List
		XmwhService xmwhservice = new XmwhService();
		List<HashMap<String, String>> xmlx = xmwhservice.getXmlx(model.getXzdm());
		List<HashMap<String, String>> xmxz = xmwhservice.getXmxz();
		request.setAttribute("xmlxList", xmlx);
		request.setAttribute("xmxzList", xmxz);
		
//		//项目名称List
//		List<HashMap<String, String>> xmmc = service.getxmmc();
//		request.setAttribute("xmmcList", xmmc);
		
		// 学年list
		request.setAttribute("xnList", Base.getXnndList());
		// 学期list
		request.setAttribute("xqList", Base.getXqList());
		// 评奖周期
		CsszService csszService = new CsszService();
		request.setAttribute("pjzq", csszService.getCsz("pjzq"));
		model.setXn(CsszService.getPjzq().get("xn"));
		// 1:学期评奖
		if(CsszService.PJFS_XQ.equals(csszService.getCsz("pjzq"))){
			model.setXq(CsszService.getPjzq().get("xq"));
		}
		
		String path = "xpj_pjjg.do?method=addPjxmjg&xzdm="+model.getXzdm();
		request.setAttribute("path", path);
		request.setAttribute("xzdm",model.getXzdm());
		this.saveToken(request);
		return mapping.findForward("addPjxmjg");
	}
	
	
	
	/**
	 * 
	 * @描述:修改评奖项目结果
	 * @作者：cq [工号：785]
	 * @日期：2013-8-8 上午10:00:42
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-修改-ID:{id},XMMC:{xmmc},XN:{xn},XQ:{xq},LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward updatePjxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);

		PjjgModel updateForm = service.getModel(model);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
//			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(), model.getXn(), model.getXq());
			User u = getUser(updateForm.getSqr());
			xsjbxx.put("sqr",u.getRealName());
			xsjbxx.put("sqrbm",u.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号，学年，学期,项目名称）
			boolean isExist = service.isExistByPjxmjg(model, UPDATE);
			if (!isExist) {
				//华中师范大学个性化证书编号
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setYlzd1(service.getZsbm(model));
				}
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		
		//项目类型项目和项目性质list
		XmwhService xmwhservice = new XmwhService();
		List<HashMap<String, String>> xmlx = xmwhservice.getXmlx(model.getXzdm());
		List<HashMap<String, String>> xmxz = xmwhservice.getXmxz();
		request.setAttribute("xmlxList", xmlx);
		request.setAttribute("xmxzList", xmxz);
		
		// 学年list
		request.setAttribute("xnList", Base.getXnndList());
		// 学期list
		request.setAttribute("xqList", Base.getXqList());
		// 评奖周期
		CsszService csszService = new CsszService();
		request.setAttribute("pjzq", csszService.getCsz("pjzq"));

		String path = "xpj_pjjg.do?method=addPjxmjg";
		request.setAttribute("path", path);
		
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("xzdm",model.getXzdm());
		return mapping.findForward("updatePjxmjg");

	}
	
	
	/**
	 * 
	 * @描述:删除评奖项目结果
	 * @作者：cq [工号：785]
	 * @日期：2013-8-8 上午10:11:03
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-删除-VALUES:{values}")
	public ActionForward delPjxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgService service = new PjjgService();
		
		//获得id
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @描述:评奖结果单个查看
	 * @作者：cq [工号：785]
	 * @日期：2013-8-8 下午02:36:40
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
	public ActionForward pjxmjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",  bdpzService.getJbxxpz(JXSQ));
		if (model != null) {

			// 加载学生基本信息
//			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(), model.getXn(), model.getXq());
			User u = getUser(xsjbxx.get("sqr"));
			xsjbxx.put("sqr",u.getRealName());
			xsjbxx.put("sqrbm",u.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);

			// 查询结果
			request.setAttribute("rs", service.getOnePjxmjgList(model.getId()));

			return mapping.findForward("pjxmjgView");
		} else {
			return updatePjxmjg(mapping, form, request, response);
		}

	}
	
	/**
	 * 
	 * @描述:评奖结果导出
	 * @作者：cq [工号：785]
	 * @日期：2013-8-14 下午03:41:50
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
		
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);
		// 查
		List<HashMap<String,String>> resultList=service.getAllList(model,user);
		//List<HashMap<String, String>> resultList = service.getPjjgExportList(model, user);

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
	 * @描述: 打印Word登记表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-29 上午09:36:06
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
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		PjjgModel model = (PjjgModel) form;
		
		File wordFile = getWord(model.getId());
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	
	
	//填充模版数据生成word文件
	private File getWord(String id)
			throws Exception {
		PjjgService servicePjPy = new PjjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService service = new PjjgService();
		PjjgModel model = service.getModel(id);
		HashMap<String, String> bbMap = null;// 报表
		
		//获取学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(model.getXq())){
				model.setXqmc(map.get("xqmc"));
				break;
			}
		}

		if (model != null){
			
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//查询项目记录
				if(xmMap != null){
					model.setXmdm(xmMap.get("xmdm"));//项目代码
					BbwhService bbwhService = new BbwhService();
					bbMap = bbwhService.getDataById(xmMap.get("dybb"));//查询对应表格 
				}
			}
			if(bbMap == null || bbMap.size() == 0){
				//查询不到相关联报表信息
				throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
			}
			
			String xh = model.getXh();
			
			//基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//综测总分
			ZcfsService zcfService = new ZcfsService();
			
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));
			WjcfCfsbService wjcfcfsbService =  new WjcfCfsbService();
			List<HashMap<String , String>> yscfqk = wjcfcfsbService.getYscfqk(xh);
			data.put("currY", DateUtils.getYear());//当前年
			data.put("currM", DateUtils.getMonth());//当前月
			data.put("currD",DateUtils.getDayOfMonth());//当前日
			PjxmsqService pjxmsqService = new PjxmsqService();
			//审核意见
			List<HashMap<String, String>> shyjList = pjxmsqService.getAllShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
			//沧州医高专
			if("13779".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				PjjgService pjjgService = new PjjgService();
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				data.put("currXn", model.getXn());
				data.put("currXq", model.getXqmc());
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}
				}
				//综测分
				HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
				data.put("zcf", zcf);
				//学习成绩（平均分）
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				if(xxcjMap == null){
					xxcjMap = new HashMap<String,String>();
				}
				data.put("xxcjMap", xxcjMap);
				
				// 本学年学期成绩（课程信息）
				List<HashMap<String, String>> kcxxlist = xsxxglService.getStuCjOfXnXqList(xh, model.getXn(), model.getXq());
				 
			    pjjgService.addBlankList(kcxxlist, 21 - kcxxlist.size());
			    
			    List<HashMap<String, String>> kcxxlistNew = new ArrayList<HashMap<String, String>>();
			    for (int i = 0; i < 21; i = i + 3) {
			    	HashMap<String, String> kcxxMapNew = new HashMap<String, String>();
			    	kcxxMapNew.put("kcmc1", kcxxlist.get(i).get("kcmc"));
			    	kcxxMapNew.put("kcfs1", kcxxlist.get(i).get("cj"));
			    	kcxxMapNew.put("kcmc2", kcxxlist.get(i+1).get("kcmc"));
			    	kcxxMapNew.put("kcfs2", kcxxlist.get(i+1).get("cj"));
			    	kcxxMapNew.put("kcmc3", kcxxlist.get(i+2).get("kcmc"));
			    	kcxxMapNew.put("kcfs3", kcxxlist.get(i+2).get("cj"));
			    	kcxxlistNew.add(kcxxMapNew);
				}
			    data.put("kcxxlist", kcxxlistNew); // 本学年学期成绩（课程信息）
			}
			
			//黑龙江农垦职业学院个性化
			if("12727".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				// ========== 本学年成绩相关 begin ============
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "德育");
				String xndypjcj = bxncjMap.get("xndypjcj");
				data.put("xndypjcj", xndypjcj);
				data.put("sxqdycj", bxncjMap.get("sxqdycj"));
				data.put("xxqdycj", bxncjMap.get("xxqdycj"));
				
				CpmdService cpmdService = new CpmdService();
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // 本学年班级人数
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // 本学年成绩班级排名
				
				List<HashMap<String,String>> xnjxj_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "必修");
				servicePjPy.addBlankList(xnjxj_cjSxqBxList, 16 - xnjxj_cjSxqBxList.size());
				data.put("xnjxj_cjSxqBxList", xnjxj_cjSxqBxList); // 本学年上学期必修课成绩
				List<HashMap<String,String>> xnjxj_cjSxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "选修");
				servicePjPy.addBlankList(xnjxj_cjSxqXxList, 6 - xnjxj_cjSxqXxList.size());
				data.put("xnjxj_cjSxqXxList", xnjxj_cjSxqXxList); // 本学年上学期选修课成绩
				List<HashMap<String,String>> xnjxj_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "必修");
				servicePjPy.addBlankList(xnjxj_cjXxqBxList, 16 - xnjxj_cjXxqBxList.size());
				data.put("xnjxj_cjXxqBxList", xnjxj_cjXxqBxList); // 本学年下学期必修课成绩
				List<HashMap<String,String>> xnjxj_cjXxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "选修");
				servicePjPy.addBlankList(xnjxj_cjXxqXxList, 6 - xnjxj_cjXxqXxList.size());
				data.put("xnjxj_cjXxqXxList", xnjxj_cjXxqXxList); // 本学年下学期选修课成绩
				
				List<HashMap<String,String>> xnjxj_cjSxqSum = xnjxj_cjSxqBxList;
				xnjxj_cjSxqSum.addAll(xnjxj_cjSxqXxList);
				String xnjxj_cjSxqPjf = service.getPjf(xnjxj_cjSxqSum, 2); // 本学年上学期平均成绩
				data.put("xnjxj_cjSxqPjf", xnjxj_cjSxqPjf);
				List<HashMap<String,String>> xnjxj_cjXxqSum = xnjxj_cjXxqBxList;
				xnjxj_cjXxqSum.addAll(xnjxj_cjXxqXxList);
				String xnjxj_cjXxqPjf = service.getPjf(xnjxj_cjXxqSum, 2); // 本学年下学期平均成绩
				data.put("xnjxj_cjXxqPjf", xnjxj_cjXxqPjf);
				String xnjxj_cjXnPjf = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_cjXnPjf = service.getPjf(new String[]{ xnjxj_cjSxqPjf, xnjxj_cjXxqPjf }, 2); // 本学年平均成绩
				}
				data.put("xnjxj_cjXnPjf", xnjxj_cjXnPjf);
				// 学生奖学金评定综合成绩 = 学年学习平均成绩（70％）＋学年德育考核平均成绩（30％）
				BigDecimal xnjxj_cjXnPjf_BD = StringUtils.isNotNull(xnjxj_cjXnPjf) ? new BigDecimal(xnjxj_cjXnPjf) : new BigDecimal("0"); 
				BigDecimal xnjxj_dypjcj_BD = StringUtils.isNotNull(xndypjcj) ? new BigDecimal(xndypjcj) : new BigDecimal("0"); 
				String xnjxj_xsjxjpdzhcj = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_xsjxjpdzhcj = xnjxj_cjXnPjf_BD.multiply(new BigDecimal("0.7")).add(xnjxj_dypjcj_BD.multiply(new BigDecimal("0.3"))).divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP).toString();
				}
				data.put("xnjxj_xsjxjpdzhcj", xnjxj_xsjxjpdzhcj);
				// 本学年有无不及格课程
				String xnjxj_bjgXn = "blank";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_bjgXn = service.getBjgcjNum(xh, model.getXn(), "");
				}
				data.put("xnjxj_bjgXn",xnjxj_bjgXn );
				// ========== 本学年成绩相关 end ============
				// ========== 历年成绩相关 begin ============
				String xnTemp = model.getXn().substring(0,4);
				String diXn = String.valueOf(Integer.parseInt(xnTemp)-2) + "-" + String.valueOf(Integer.parseInt(xnTemp)-1); // 第一学年
				String deXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + xnTemp; // 第二学年
				HashMap<String,String> bxncjMap_diXn = servicePjPy.getDjbZcfListByXhXn(xh, diXn, "德育");
				// 第一学年德育考核平均成绩
				String xndypjcj_diXn = "";
				if(StringUtils.isNotNull(bxncjMap_diXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_diXn.get("xxqdycj"))){
					xndypjcj_diXn = service.getPjf(new String[]{ bxncjMap_diXn.get("sxqdycj"), bxncjMap_diXn.get("xxqdycj") }, 2);
				}
				HashMap<String,String> bxncjMap_deXn = servicePjPy.getDjbZcfListByXhXn(xh, deXn, "德育");
				// 第二学年德育考核平均成绩
				String xndypjcj_deXn = "";
				if(StringUtils.isNotNull(bxncjMap_deXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_deXn.get("xxqdycj"))){
					xndypjcj_deXn = service.getPjf(new String[]{ bxncjMap_deXn.get("sxqdycj"), bxncjMap_deXn.get("xxqdycj") }, 2);
				}
				// 前两学年德育考核总平均成绩
				String xndypjcj_qlXn = "";
				if(StringUtils.isNotNull(xndypjcj_diXn) || 
						StringUtils.isNotNull(xndypjcj_deXn)){
					xndypjcj_qlXn = service.getPjf(new String[]{ xndypjcj_diXn, xndypjcj_deXn }, 2);
				}
				data.put("xndypjcj_diXn", xndypjcj_diXn);
				data.put("xndypjcj_deXn", xndypjcj_deXn);
				data.put("xndypjcj_qlXn", xndypjcj_qlXn);
				// ========== 历年成绩相关 end ============
				// ========== 获奖相关 begin ============
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				String bxnxnjxj = ""; // 本学年校内奖学金
				String bxngjjxj = ""; // 本学年国家奖学金
				String bxngjlzjxj = ""; // 本学年国家励志奖学金
				String dixnxnjxj = ""; // 第一学年校内奖学金
				String dixngjjxj = ""; // 第一学年国家奖学金
				String dexnxnjxj = ""; // 第二学年校内奖学金
				String dexngjjxj = ""; // 第二学年国家奖学金
				String dixnxn1 = ""; // 第一学年校内XXX
				String dixnxn2 = ""; // 第一学年校内XXX
				String dixnsj = ""; // 第一学年省级XXX
				String dexnxn1 = ""; // 第二学年校内XXX
				String dexnxn2 = ""; // 第二学年校内XXX
				String dexnsj = ""; // 第二学年省级XXX
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					String xn = pj.get("xn");
					if(xmmc.contains("等校内奖学金")){
						if(model.getXn().equals(xn)){
							bxnxnjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixnxnjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexnxnjxj = xmmc;
						}
					}else if(xmmc.contains("国家奖学金")){
						if(model.getXn().equals(xn)){
							bxngjjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixngjjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexngjjxj = xmmc;
						}
					}else if(xmmc.contains("国家励志奖学金") && model.getXn().equals(xn)){
						bxngjlzjxj = xmmc;
					}else if(xmmc.startsWith("校内")){
						if(diXn.equals(xn)){
							if("".equals(dixnxn1)){
								dixnxn1 = xmmc;
							}else if(!"".equals(dixnxn1) && "".equals(dixnxn2)){
								dixnxn2 = xmmc;
							}
						}else if(deXn.equals(xn)){
							if("".equals(dexnxn1)){
								dexnxn1 = xmmc;
							}else if(!"".equals(dexnxn1) && "".equals(dexnxn2)){
								dexnxn2 = xmmc;
							}
						}
					}else if(xmmc.startsWith("省级")){
						if(diXn.equals(xn)){
							dixnsj = xmmc;
						}else if(deXn.equals(xn)){
							dexnsj = xmmc;
						}
					}
				}
				data.put("bxnxnjxj", bxnxnjxj);
				data.put("bxngjjxj", bxngjjxj);
				data.put("bxngjlzjxj", bxngjlzjxj);
				data.put("dixnxnjxj", dixnxnjxj);
				data.put("dexnxnjxj", dexnxnjxj);
				data.put("dixngjjxj", dixngjjxj);
				data.put("dexngjjxj", dexngjjxj);
				data.put("dixnxn1", dixnxn1);
				data.put("dixnxn2", dixnxn2);
				data.put("dixnsj", dixnsj);
				data.put("dexnxn1", dexnxn1);
				data.put("dexnxn2", dexnxn2);
				data.put("dexnsj", dexnsj);
				
				String xnjxj_hjxjdj = model.getXmmc().contains("等奖学金") ? model.getXmmc() : ""; // （校内奖学金评审表）获奖学金等级
				data.put("xnjxj_hjxjdj", xnjxj_hjxjdj);
				// ========== 获奖相关 end ============
				String yxtgpsbXmmc = "";
				if("yxtgpsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "优秀团干";
				}else if("yxtypsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "优秀团员";
				}
				data.put("bbMapBbdm", bbMap.get("bbdm"));
				data.put("yxtgpsbXmmc", yxtgpsbXmmc);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
				//获取不及格成绩数量
				String bjgcjs=service.getBjgcjNum(xh, model.getXn(), "");
				// 有无违纪处分
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ // 处分挂科
					xsjbxx.put("ywcfgk", "无");
				}else{
					xsjbxx.put("ywcfgk", "有");
				}
				if(StringUtils.isNull(bjgcjs)){ // 本学年有无不及格课程
					xsjbxx.put("bxnywbjgkc", "无");
				}else{
					xsjbxx.put("bxnywbjgkc", "有");
				}
				DwwhService dwwhService = new DwwhService();
				List<HashMap<String , String>> bxnxsgbzwList = dwwhService.getZwForXhXn(xh, model.getXn()); // 学生干部职务任期时间
				HashMap<String , String> bxnxsgbzw1 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 0){
					bxnxsgbzw1 = bxnxsgbzwList.get(0);
				}
				HashMap<String , String> bxnxsgbzw2 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 1){
					bxnxsgbzw2 = bxnxsgbzwList.get(1);
				}
				data.put("bxnxsgbzw1", bxnxsgbzw1);
				data.put("bxnxsgbzw2", bxnxsgbzw2);
				
				// 家庭情况
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(xh);
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm); 
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel_12727.getJthk();
				boolean jthkCzFlag = false;
				if(jthk != null && jthk.contains("城镇")){
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// 家庭成员
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
			}
			
			//陕西师范大学
			if("10718".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				
			}
			
			//中央民族大学
			if("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
				// 家庭成员
				JtqkdcService jtqkService = new JtqkdcService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// 项目学年学期必修课成绩
				List<HashMap<String,String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "必修");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList); 
				String xmXnxqBxCjPjf = service.getPjf(xmXnxqBxCjList, 2); // 平均成绩
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				// 党团建设
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if(StringUtils.isNotNull(zzmmmc)){
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if((zzmmmc.contains("团员") && "02".equals(jddm))
							|| (zzmmmc.contains("预备党员") && "09".equals(jddm))
							|| (zzmmmc.contains("党员") && !zzmmmc.contains("预备党员") && "11".equals(jddm))){
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj,FomartDateType.day));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("、");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 5 - pjjgList.size());
				data.put("pjjg_10052", pjjgList);
				int size1=(5 - pjjgList.size())<0?0:(5 - pjjgList.size());
				data.put("blank_10052", getBlankList(size1));
				HashMap<String, String> dkfs = pjjgService.getCjfsList(model.getXh(),model.getXn());
				data.put("dkzgf", dkfs.get("max"));
				data.put("dkzdf", dkfs.get("min"));
				data.put("pjcjjdfs", dkfs.get("pjcjjdfs"));
				List<HashMap<String, String>> hjqkList = service.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("rddcmc", knsjg.get("dcmc"));//认定档次
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("品行")){
						data.put("pdfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("学业")){
						data.put("xyfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("文体")){
						data.put("wtfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("综测")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
				}
				//========== 获取上一学年成绩排名、总人数、必修课及及格数 begin ============
				String xnTemp = Base.currXn.substring(0,4);
				String beforXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + String.valueOf(Integer.parseInt(xnTemp)); // 上学年
					//必修课及排名
				// 班级人数
				CpmdService cpmdService = new CpmdService();
				String bjrsForZymzdx = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				data.put("bjrsForZymzdx", bjrsForZymzdx);	
				String cjpm = zcfService.getCjpm(beforXn, xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(beforXn, xh);
				String bxkjgs = zcfService.getBxkjgs(beforXn, xh);
				data.put("cjpmForZymzdx", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// 参评人数
				data.put("cpzrsForZymzdx", cpzrs);
				
				//========== 获取上一学年成绩排名、总人数、必修课及及格数 end ============
				
				/*获取第一级审核意见*/
				data.put("shyj1", new CommShlcImpl().getShyjListByYwid(model.getId()).get(0).get("shyj"));
				data.put("shyj2", new CommShlcImpl().getShyjListByYwid(model.getId()).get(1).get("shyj"));
				data.put("shyj3", new CommShlcImpl().getShyjListByYwid(model.getId()).get(2).get("shyj"));
			}
			
			//涪陵师范学院个性化（长江师范学院）
			if("10647".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // 评奖年度
				data.put("pjnds", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
				data.put("nd", Base.currNd);
				data.put("njzwmc", xsjbxx.get("nj").substring(2, 4) + "级"); // 评奖年度
				// ============ 计算年龄 begin==========
				String nl = "";
				//1994-12-24 19920217 两格式
				String csrq = xsjbxx.get("csrq");//获取出生日期
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				// ============ 计算年龄 end==========
				// ========== 获得校级优秀毕业生荣誉称号的时间 begin ============
				StringBuffer xjyxbysHjsjBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(xmmc.contains("校级优秀毕业生")){
						xjyxbysHjsjBuffer.append(pj.get("sqsjs")).append("，");
					}
				}
				String xjyxbysHjsj = xjyxbysHjsjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("xjyxbysHjsj", xjyxbysHjsj);
				// ========== 获得校级优秀毕业生荣誉称号的时间 end ============
				// ========== 获奖情况个性化 begin ============
				List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				// ========== 获奖情况个性化 end ============
			}
			
			//潍坊学院个性化
			if("11067".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxny = xsjbxx.get("rxny") == null ? "" : xsjbxx.get("rxny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				xsjbxx.put("rxny_num", rxny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				// 分解身份证号begin
				String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
//				// ===============定制申请理由 begin===============
//				// ===========山东省非师范类优秀毕业生评审表、山东省师范类优秀毕业生评审表 begin==========
//				String sqly_11067_1 = sqly;
//				String sqly_11067_2 = "";
//				String bbdm = bbMap.get("bbdm");
//				int sqly_11067_max = 0;
//				if("sdsfsflyxbys_11067".equals(bbdm)){
//					sqly_11067_max = 224;
//				}else if("sdssflyxbys_11067".equals(bbdm)){
//					sqly_11067_max = 646;
//				}
//				if(sqly_11067_1.length() > sqly_11067_max){
//					sqly_11067_1 = sqly.substring(0, sqly_11067_max);
//					sqly_11067_2 = sqly.substring(sqly_11067_max);
//				}
//				data.put("sqly_11067_1", sqly_11067_1);
//				data.put("sqly_11067_2", sqly_11067_2);
//				// ===========山东省非师范类优秀毕业生评审表、山东省师范类优秀毕业生评审表 end==========
//				// ===========山东省高等学校优秀学生登记表、山东省高等学校优秀学生干部登记表 begin==========
//				HashMap<String, String> sqly_11067_map = new HashMap<String, String>();
//				if("sdsgdxxyxxsgb_11067".equals(bbdm) || "sdsgdxxyxxs_11067".equals(bbdm)){
//					int rows = 12; // 行数
//					int words = 35; // 每行最大字数
//					sqly_11067_max = 420;
//					String sqly_11067_d2y = ""; // 第二页
//					if(sqly.length() > sqly_11067_max){
//						sqly_11067_d2y = sqly.substring(sqly_11067_max); 
//					}
//					sqly_11067_map.put("sqly_11067_d2y", sqly_11067_d2y);
//					String temp = sqly;
//					for (int i = 1; i <= rows; i++) {
//						if(temp.length() > words){
//							sqly_11067_map.put("sqly_11067_" + i, temp.substring(0, words));
//							temp = temp.substring(words);
//						}else{
//							sqly_11067_map.put("sqly_11067_" + i, temp);
//							temp = "";
//						}
//					}
//				}
//				data.put("sqly_11067_map", sqly_11067_map);
//				// ===========山东省高等学校优秀学生登记表、山东省高等学校优秀学生干部登记表 end==========
//				// ===============定制申请理由 end===============
				// 通过学号查询综测分数
				List<HashMap<String,String>> zcfNList = zcfService.getZcfsNList(xh);
				data.put("zcfNList_11067", zcfNList);
			}
			
			// 担任职务
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			
			// 分解身份证号begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			
			// 分解身份证号end
			
			//学生照片
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			
			//成都体育学院个性化
			if("10653".equals(Base.xxdm)){
				String nl = "";
				//计算年龄
				//1994-12-24 19920217 两格式
				String csrq = xsjbxx.get("csrq");//获取出生日期
				
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				String[] code = new String[]{"01","02","03"}; //党团员代码 中国共产党01 预备02 团员03
				//判断是否是党团员
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&(ArrayUtil.contains(code, xsjbxx.get("zzmm")))){
					data.put("sfsdty", "是");
				}else{
					data.put("sfsdty", "否");
				}
				//获取同年级同专业人数
				String zyrs = service.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生年月
				
				String[] shyj = new String[]{"","","",""};
				for (int i = 0; i < shyjList.size(); i++) {
					shyj[i] = shyjList.get(i).get("shyj");
				}
				data.put("shyj1", shyj[0]);
				data.put("shyj2", shyj[1]);
				data.put("shyj3", shyj[2]);
				data.put("shyj4", shyj[3]);
				
				//家庭成员信息
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList_10653 = xsxxglService.getJtcyxxXsList(xh);
				
				data.put("jtcyxxList_10653", jtcyxxList_10653);
				int size=(3 - jtcyxxList_10653.size())<0?0:(3 - jtcyxxList_10653.size());
				data.put("blankList_10653", getBlankList(size));
				
				//困难生认定档次名称
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("dcmc", knsjg.get("dcmc")==null?"":knsjg.get("dcmc"));
			}
			
			
			
			//广西医科大学个性化多张报表
//			if("10598".equals(Base.xxdm)){
//				if("‘优利特’优秀学生干部奖学金".equals(model.getXmmc()) 
//						|| "邓洁彬奖学金".equals(model.getXmmc())
//						|| "荣和教育奖学金".equals(model.getXmmc())
//						|| "三好学生".equals(model.getXmmc()) 
//						|| "优秀学生干部".equals(model.getXmmc())
//				){
					//项目类型名称
				if(null!=model && null!=model.getLxdm() && !"".equals(model.getLxdm())){
					PjdmModel pjdmModel = new PjdmModel();
					pjdmModel.setXmlxdm(model.getLxdm());
					PjdmService pjdmService = new PjdmService();
					PjdmModel xmlxModel = pjdmService.getModel(pjdmModel);
					model.setLxdmmc(xmlxModel.getXmlxmc());
				}
//				}
//			}
			//测试使用
			//xh="3060601025"; 
			HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
			
			//按照学号加载学生学年成绩
			List<HashMap<String,String>> cjList = xsxxService.getCjList(xh,model.getXn(),model.getXq());
			//获取平均分数
			String pjfs=service.getAverage(cjList);
			xsjbxx.put("pjfs",pjfs );
			
			//困难生认定档次
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//认定档次
			
			//获取不及格成绩数量
			String bjgcjs=service.getBjgcjNum(xh, model.getXn(), model.getXq());
			xsjbxx.put("bjgcjs",bjgcjs );
			
			//浙江大学宁波理工学院个性化
			if("13022".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				// 有无不及格课程
				if(StringUtils.isNull(bjgcjs)){
					xsjbxx.put("ywbjgmc", "无");
				}else{
					xsjbxx.put("ywbjgmc", "有");
				}
				// 有无违纪处分
				if(yscfqk==null || yscfqk.size() == 0){
					xsjbxx.put("ywwjcfmc", "无");
				}else{
					xsjbxx.put("ywwjcfmc", "有");
				}
				// 已申请项目
				StringBuffer ysqxmBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					// 拼接已申请项目
					if(StringUtils.isNotNull(xmmc)){
						ysqxmBuffer.append(xmmc).append(" ");
					}
				}
				data.put("ysqxmmc", ysqxmBuffer.toString());
				
				// ============ 根据申请的项目名称判断是否选中 begin ============
				String sqxmmc = model.getXmmc();
				// 奖学金 模板是否选中判断
				if(sqxmmc.equals("一等奖学金")){
					data.put("ydjxjflag", "true");
				}else if(sqxmmc.equals("二等奖学金")){
					data.put("edjxjflag", "true");
				}else if(sqxmmc.equals("三等奖学金")){
					data.put("sdjxjflag", "true");
				}else if(sqxmmc.equals("学习优秀奖学金")){
					data.put("xxyxjxjflag", "true");
				}else if(sqxmmc.equals("社会工作优秀奖学金")){
					data.put("shgzyxjxjflag", "true");
				}else if(sqxmmc.equals("文体活动奖学金")){
					data.put("xthdjxjflag", "true");
				}
				// 荣誉称号 模板是否选中判断
				if(sqxmmc.equals("三好学生")){
					data.put("shxsrychflag", "true");
				}else if(sqxmmc.equals("优秀学生干部")){
					data.put("yxxsgbrychflag", "true");
				}
				// ============ 根据申请的项目名称判断是否选中 end ============
				
				// 成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.equals("学业水平")){
						xsjbxx.put("xyspnjzypmdy", zcfMap.get("njzypm"));
					}else if(xmmc.equals("思想品德")){
						xsjbxx.put("sxpdbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("综合能力")){
						xsjbxx.put("zhnlbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("身体素质")){
						xsjbxx.put("stszfsdy", zcfMap.get("fs"));
					}
				}
				// 班级人数
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				xsjbxx.put("bjrs", bjrs);
				//获取同年级同专业人数
				String zyrs = servicePjPy.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
			}
			
			//北京林业大学个性化
			if("10022".equals(Base.xxdm)){
				//获取打印日期
				XsxxglService xsxxglService = new XsxxglService();
				String dyrq = xsxxglService.getDqrq(xh);
				data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				// 班级人数
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				xsjbxx.put("bjrs", bjrs);
				// 已申请项目
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				// 当前项目的学年学期
				String xnDqxm = model.getXn();
				if(xnDqxm != null){
					xnDqxm = xnDqxm.trim();
				}
				String xqmcDqxm = (model.getXqmc() == null) ? "" : model.getXqmc();
				if(xqmcDqxm != null){
					xqmcDqxm = xqmcDqxm.trim();
				}
				String xnXqmcDqxm = xnDqxm + xqmcDqxm;
				List<HashMap<String, String>> pjzqPjjgList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xnTemp = pj.get("xn");
					String xqmcTemp = pj.get("xqmc");
					if(xnTemp != null){
						xnTemp = xnTemp.trim();
					}
					if(xqmcTemp != null){
						xqmcTemp = xqmcTemp.trim();
					}
					// 当前项目的学年学期下，学生所获奖项
					if(xnXqmcDqxm.equals(xnTemp + xqmcTemp)){
						pjzqPjjgList.add(pj);
					}
				}
				// 当前项目的学年下，学生所获奖项
				String xnXqmcDqxn = xnDqxm;
				List<HashMap<String, String>> XnPjjgList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xnTemp = pj.get("xn");
					if(xnTemp != null){
						xnTemp = xnTemp.trim();
					}
					if(xnXqmcDqxn.equals(xnTemp)){
						XnPjjgList.add(pj);
					}
				}
				data.put("XnPjjgList", XnPjjgList);
				data.put("XnPjjgListSize", XnPjjgList.size());
				data.put("pjzqPjjgList", pjzqPjjgList);
				data.put("pjzqPjjgListSize", pjzqPjjgList.size());
			}
			
			//广州铁路职业技术学院个性化
			if("13943".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				servicePjPy.addBlankList(pjjg, 1 - pjjg.size());
				data.put("pjjg", pjjg);
			}
			
			//中国美术学院个性化
			if("10355".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				PjjgService pjjgService = new PjjgService();
//				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjqkByXhMap(xh,4);
//				data.put("pjjgLine1", pjjgL4.get(0));
//				data.put("pjjgLine2", pjjgL4.get(1));
//				data.put("pjjgLine3", pjjgL4.get(2));
//				data.put("pjjgLine4", pjjgL4.get(3));
				String xq = StringUtils.isNotNull(model.getXq()) ? model.getXq() : "on";
				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjxxList(xh,model.getXn(),xq,"4");
				data.put("pjjgLine1", pjjgL4.get(0));
				data.put("pjjgLine2", pjjgL4.get(1));
				data.put("pjjgLine3", pjjgL4.get(2));
				data.put("pjjgLine4", pjjgL4.get(3));
				data.put("pjjgL4", pjjgL4);
				data.put("xxdm", Base.xxdm);
				data.put("ywwj", yscfqk.size()>0);
				String xmmc = model.getXmmc();
				String xmdm = model.getXmdm();
				
				//获取学生学习情况
				CpmdService cpmdService = new CpmdService();
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "德育");
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // 本学年成绩班级排名
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // 本学年班级人数
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// 参评人数
				data.put("cpzrsForZymy", cpzrs);
				//必修课及排名
				String bjrsForZymy = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bxksForZymy", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgsForZymy", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				data.put("cjpmForZymy", cjpm);// 总成绩排名
				data.put("bjrsForZymy", "".equals(cjpm) ? "" : bjrsForZymy);// 班级人数
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String zCxmmc = zcfMap.get("xmmc").trim();
					if(zCxmmc.contains("综测")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
				}
				String cprs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// 参评人数
				data.put("cpzrsForZymy", cprs);
				//由于word里有学院字样，去掉xymc里最后2个字
				String xymc = model.getCpxymc().substring(0,model.getCpxymc().length() -2);
				data.put("xymc", xymc);

				//获取奖项名称
				String jxmc = model.getXmmc();
				data.put("jxmc",jxmc);
				//判断奖项名称是否包含三好学生字段
				if(jxmc.indexOf("三好学生")!= -1){
					data.put("isShxs","1");
				} else {
					data.put("isShxs","0");
				}
				// 有无违纪处分
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "否");
				}else{
					data.put("ywcfmc", "是");
				}
				//补考课数
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				String bkks = xxcjMap.get("bkcjnum");
				//补考结果体能
				String bkjg = xxcjMap.get("xscjstr");
				data.put("bkjg", bkjg);
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "无");
				}else {
					data.put("sfbk", "有");
				}

				//中国美院 学生旷课补考违纪信息
				HashMap<String,String> kkbkxxMap = servicePjPy.getKkbkxx(xh);
				data.put("kkbkxx", kkbkxxMap);

				//体能测试成绩
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("体育分",model.getXn(),model.getXq(),xh).get("fs"));
				
				//时间变量
				HashMap<String,String> sqsj = new HashMap<String, String>();
				HashMap<String,String> shsjfdy = new HashMap<String, String>();//辅导员
				HashMap<String,String> shsjyx = new HashMap<String, String>();//院系
				HashMap<String,String> shsjxx = new HashMap<String, String>();//学校
				shsjxx = pjjgService.getHjshsjList(xmdm, "0");
				if(xmmc.indexOf("国家奖学金") != -1 || xmmc.indexOf("省政府") != -1 ){
					sqsj = pjjgService.getHjshsjList(xmdm, "13");
					shsjfdy = pjjgService.getHjshsjList(xmdm,"11");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
					
				}else if(xmmc.indexOf("国家励志奖学金") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "9");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else if(xmmc.indexOf("国家助学金") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "12");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else {
					
				}
				data.put("sqsjs", sqsj);
				data.put("shsjfdy", shsjfdy);
				data.put("shsjyx", shsjyx);
				data.put("shsjxx", shsjxx);
			}

			//湘潭大学
			if("10530".equals(Base.xxdm)){
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> pjjgL4 =  xsxxglService.getXsxxHjqkNewList(xh,4);
				data.put("pjjgL4", pjjgL4);

				String zzmmmc = xsjbxx.get("zzmmmc");

				if(zzmmmc!=null){
					if (zzmmmc.equals("中国共产党党员")) {
						xsjbxx.put("zzmmmc", "中共党员");
					}else if(zzmmmc.equals("中国共产主义青年团团员")){
						xsjbxx.put("zzmmmc", "共青团员");
					}
				}
			}
			
			//北京联合大学个性化
			if("11417".equals(Base.xxdm)){
				// 设置北京联合大学标题
				data.put("bjlhdxbt", model.getXmmc());
				
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // 评奖年度
				// 已申请项目
				StringBuffer ysqxmBuffer = new StringBuffer();
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					if(StringUtils.isNotNull(pj.get("xmmc"))){
						ysqxmBuffer.append(pj.get("xmmc")).append(" ");
					}
				}
				xsjbxx.put("ysqxmmc", ysqxmBuffer.toString());
				// 获取各种成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("学业")){
						xsjbxx.put("xycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("文体")){
						xsjbxx.put("wtcjfs", zcfMap.get("fs"));
					}
				}
			}
			//永康市职业技术学校
			if("90211".equals(Base.xxdm)) {
				
				data.put("xmmc", model.getXmmc());// 项目名称
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				//判断是否是党团员
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&( "03".equals(xsjbxx.get("zzmm")))){
					data.put("sfty", "是");
				}else{
					data.put("sfty", "否");
				}
				// 获取各种成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("教学")){
						xsjbxx.put("jxcjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("体育")){
						xsjbxx.put("tycjfs", zcfMap.get("fs"));
					}
				}		
				
			}
			
			
			//浙江旅游职业学院个性化
			if("12867".equals(Base.xxdm)){
				// 获取各种成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("智育")){
						xsjbxx.put("zyfbjpm", zcfMap.get("bjpm"));
					}
				}
				// 最高分、最低分、平均分、补考课数、学习成绩
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				if(xxcjMap == null){
					xxcjMap = new HashMap<String,String>();
				}
				data.put("xxcjMap", xxcjMap);
				// 班级人数
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				xsjbxx.put("bjrs", bjrs);
				//浙江旅游国家奖学金
				//评奖取值
				PjjgService pjjgService = new PjjgService();						
				//国家奖学金 取出基本数据
				HashMap<String, String> pjjg12867 =  pjjgService.getZjlyByXhMap(xh,model.getXn());
				data.put("pjjg12867", pjjg12867);
				//综合素质排名
				HashMap<String, String> rspm = pjjgService.getZjlyByPm(xh,model.getXn());
				data.put("rspm", rspm);	
				//浙江旅游励志奖学金中获取字段（家庭情况）
				HashMap<String,String> lzjxj = pjjgService.getZjlylzByXhMap(xh, model.getXn());
				data.put("lzjxj",lzjxj);	
				//浙江旅游学习成绩和排名（这里的成绩可以用在校奖学金）
				HashMap<String,String> zjlyxxcj = pjjgService.getZjlyXxqkCj(xh, model.getXn());
				data.put("zjlyxxcj",zjlyxxcj);
				//浙江省政府奖学金浙江旅游
				HashMap<String, String> zjszf12867 =  pjjgService.getZjszfByXhMap(xh,model.getXn());
				data.put("zjszf12867", zjszf12867);
				//浙江旅游职业学院奖学金
				HashMap<String, String> zjlyzyxy12867 =  pjjgService.getZjlyzyxyfByXhMap(xh,model.getXn());
				data.put("zjlyzyxy12867", zjlyzyxy12867);
				//浙江旅游省级优秀毕业生
			if (model.getXmmc().indexOf("省级优秀毕业生")!=-1) {
				HashMap<String, String> sjbys =  pjjgService.getZjlySjyxbys(xh,model.getXn());
				if (!sjbys.isEmpty()) {
					sjbys.put("f_brjl", HtmlUtil.xmlZy(sjbys.get("f_brjl")));
					sjbys.put("f_zysj", HtmlUtil.xmlZy(sjbys.get("f_zysj")));
					sjbys.put("f_zxqjhjqk", HtmlUtil.xmlZy(sjbys.get("f_zxqjhjqk")));
					sjbys.put("sqsj",  DateUtils.getDayOfCn(sjbys.get("sqsj")));
					sjbys.put("f_usertask2shsj", StringUtil.isNull(sjbys.get("f_usertask2shsj")) ? "" : DateUtils.getDayOfCn(sjbys.get("f_usertask2shsj")));
					sjbys.put("f_usertask3shsj", StringUtil.isNull(sjbys.get("f_usertask3shsj")) ? "" : DateUtils.getDayOfCn(sjbys.get("f_usertask3shsj")));
					sjbys.put("f_usertask4shsj", StringUtil.isNull(sjbys.get("f_usertask4shsj")) ? "" : DateUtils.getDayOfCn(sjbys.get("f_usertask4shsj")));
				}
				data.put("sjbys", sjbys);
			}
				//浙江省旅游学院优秀毕业生
			if (model.getXmmc().indexOf("院级优秀毕业生")!=-1) {
				HashMap<String, String> yjbys =  pjjgService.getZjlyxyyxbys(xh,model.getXn());
				if (!yjbys.isEmpty()) {
					yjbys.put("f_brjl", HtmlUtil.xmlZy(yjbys.get("f_brjl")));
					yjbys.put("f_zysj", HtmlUtil.xmlZy(yjbys.get("f_zysj")));
					yjbys.put("f_zxqjhjqk", HtmlUtil.xmlZy(yjbys.get("f_zxqjhjqk")));
					yjbys.put("sqsj",  DateUtils.getDayOfCn(yjbys.get("sqsj")));
					yjbys.put("f_usertask2shsj", StringUtil.isNull(yjbys.get("f_usertask2shsj")) ? "" : DateUtils.getDayOfCn(yjbys.get("f_usertask2shsj")));
					yjbys.put("f_usertask3shsj", StringUtil.isNull(yjbys.get("f_usertask3shsj")) ? "" : DateUtils.getDayOfCn(yjbys.get("f_usertask3shsj")));
					yjbys.put("f_usertask4shsj", StringUtil.isNull(yjbys.get("f_usertask4shsj")) ? "" : DateUtils.getDayOfCn(yjbys.get("f_usertask4shsj")));
				}
				data.put("yjbys",yjbys);
			}
			
			}
						
			//上海电机学院个性化
			if("11458".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				// 当前项目的学年
				data.put("xn_11458", model.getXn().replace("-", "/"));
			}
			
			List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgAll =  service.getPjpyInfoList(xh);
			
			//华中农业大学
			if("10504".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				//评奖取值
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg10504 =  pjjgService.getHznydxPjpyMap(xh);
				data.put("pjjg10504", pjjg10504);
				int size1=(4 - pjjg10504.size())<0?0:(4 - pjjg10504.size());
				data.put("blankList1", getBlankList(size1));
				
			}
			//浙江中医药大学
			if("10344".equals(Base.xxdm)){
				//申请项目同学年获得奖学金名称（等级）
				PjjgService pjjgService = new PjjgService();
				String jxjdj = pjjgService.getJxjmcByXhXn(xh,model.getXn());
				data.put("jxjdj",jxjdj);
				// 参评组人数
				CpmdService cpmdService = new CpmdService();
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());
				data.put("cpzrs", cpzrs);
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				String bjrs = zcfService.getBjrs(xh);
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// 班级人数
				data.put("cjpm", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
			}
			/*广西工商职业技术学院个性化**/
			if("13828".equals(Base.xxdm)){
				
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){
					xsjbxx.put("cfbjg", "无");
				}else{
					xsjbxx.put("cfbjg", "有");
				}
				
				StringBuffer buffer = new StringBuffer();
				
				if(pjjg != null && pjjg.size() >= 1)
					buffer.append("获奖情况：");
				for (HashMap<String, String> pj : pjjg) {
					if(StringUtils.isNotNull(pj.get("xmmc")))
						buffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append("；");
				}
				
				data.put("hjqk", buffer.toString());
				data.put("bjrs", service.getTbjrs(xh));
			}
			
			//浙江传媒
			if("11647".equals(Base.xxdm)) {
				HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> pm = service.getPm(model.getXh(),model.getXn());
				
				data.put("bxk", bxk);
				data.put("pm", pm);
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				String beforXn = " "+model.getXn().substring(0, 4)+" — "+model.getXn().substring(5, 9);
				data.put("beforXn", beforXn);
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
			}
			//青岛滨海学院
			if("10868".equals(Base.xxdm)){
				/*取【学年综合成绩】*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*取学年综合成绩名次*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				/*根据学号取当前评奖周期的参评班级人数*/
				String xsszcpbjRs = service.getXsszcpbjRsForxh(model.getXh(),model.getXn());
				data.put("xsszcpbjRs", xsszcpbjRs);
			}
			//温州大学
			if("10351".equals(Base.xxdm)) {
				HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("bxk", bxk);
				data.put("pm", pm);
			}	
			//辽宁美术职业学院
			if("13957".equals(Base.xxdm)) {
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
			}		
			
			//重庆三峡医药
			if("14008".equals(Base.xxdm)) {
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				HashMap<String,String> bjxxMap = servicePjPy.getBjxx(xsjbxx.get("bjdm"));
				data.put("xmmc", model.getXmmc());
				data.put("bjrs", bjxxMap.get("bjrs"));
				data.put("bzrxm", bjxxMap.get("bzrxm"));
				data.put("fdyxm", bjxxMap.get("fdyxm"));
				data.put("currXn", Base.currXn);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("中国共产党党员")) {
					data.put("zzmmmc", "中共党员");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
			}
			//浙江警官职业学院
			if("12869".equals(Base.xxdm)) {
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				/*打印日期*/
				data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("中国共产党党员")) {
					data.put("zzmmmc", "中共党员");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
			}
			//浙江警察学院个性化
			if("11483".equals(Base.xxdm)){
				StringBuilder buffer = new StringBuilder();
				if(pjjg != null && pjjg.size() >= 1)
				for (HashMap<String, String> pj : pjjg) {
					if(StringUtils.isNotNull(pj.get("xmmc")))
						buffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append(";");
				}
				data.put("zwmc", dwwhService.getZwForXh(xh));
				data.put("hjqk", buffer.toString());
				DtxxjgService dtxxjgService = new DtxxjgService();
				StringBuilder ber = new StringBuilder();
				String rtrq = dtxxjgService.getGrDtjgxx(xh,"02").get("kssj");
				String dyrq = dtxxjgService.getGrDtjgxx(xh,"09").get("kssj");
				ber.append(rtrq==null?"无":rtrq).append("、").append(dyrq==null?"无":dyrq);
				data.put("zzmmrq", ber.toString().replace(" ",""));
				String zjjcpjcj = xsxxService.getZjjcPjcj(xh, model.getXn(), model.getXq());
				String zjjczdcj = xsxxService.getZjjcZdcj(xh, model.getXn(), model.getXq());
				data.put("zjjcpjcj", StringUtils.isNull(zjjcpjcj)?"0":zjjcpjcj );
				data.put("zjjczdcj", StringUtils.isNull(zjjczdcj)?"0":zjjczdcj );
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						data.put("dyf", zcfMap.get("fs"));
					}
					if(xmmc.contains("体育")){
						data.put("tyf", zcfMap.get("fs"));
					}
					
					
				}
				if(model.getXmmc().indexOf("奖学金")!=-1){
					String xmdj = model.getXmmc().substring(0,model.getXmmc().indexOf("奖学金"));
					data.put("xmdj", xmdj);
				}	
			}
			//华中农业大学
			if("10504".equals(Base.xxdm)){
				
				//获取学生的平均成绩及其排名
				HashMap<String,String> pjcj = xsxxService.getHznyPjcjWithPm(xh,model.getXn(), model.getXq());
				data.put("pjcj", pjcj);
				
				//获取奖学金等级
				List<HashMap<String,String>> hjjx = service.getPjpyInfoMap(xh);
				if (null != hjjx && hjjx.size() >= 1) {
					for (HashMap<String, String> pj : hjjx) {
						if (!StringUtil.isNull(pj.get("xq"))
								&& !StringUtil.isNull(model.getXq())) {
							if (pj.get("xn").equals(model.getXn())
									&& pj.get("xq").equals(model.getXq())
									&& pj.get("xmmc").contains("等奖学金")) {
								String xmdj = pj.get("xmmc").substring(0,
										pj.get("xmmc").indexOf("奖学金"));
								data.put("xmdj", xmdj);
							}
						} else {
							if (model.getXn().equals(pj.get("xn"))
									&& pj.get("xmmc").contains("等奖学金")) {
								String xmdj = pj.get("xmmc").substring(0,
										pj.get("xmmc").indexOf("奖学金"));
								data.put("xmdj", xmdj);
							}
						}
					}
				}
				
				//申请理由
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);
				data.put("crzwmc", dwwhService.getCrZwForXh(xh));
				if(model.getXmmc().contains("标兵")){
					WdgwsqService wdgwsqService = new WdgwsqService();
					HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
					String qsbh=null;
					if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
						qsbh="";
					}else{
						qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
					}
					data.put("qsbh", qsbh);
				}
			}
			
			//浙江商业职业奖学金登记表
			if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)){
					StringBuilder buffer = new StringBuilder();
					if(pjjg != null && pjjg.size() >= 1)
					for (HashMap<String, String> pj : pjjg) {
						if(StringUtils.isNotNull(pj.get("xmmc"))){
							buffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append("; \n ");
						}	
					}
					data.put("hjqk",buffer.toString());
					data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // 评奖年度
					data.put("zwmc", dwwhService.getZwForXh(xh));//职务					
					data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
			}
			
			xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}						
			//加载家庭成员信息			
		
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
			
			data.put("jtcyxxList", jtcyxxList);
			int size=(5 - jtcyxxList.size())<0?0:(5 - jtcyxxList.size());
			data.put("blankList", getBlankList(size));
			
			data.put("jtqk", jtqkmodel);//家庭情况
			
			KnsdcService knsdcService = new KnsdcService();
			data.put("knsdcList", knsdcService.getKnsdcList());// 困难生档次list
			//课程成绩
			data.put("kccjList", xsxxglService.getStuCjOfXnList(xh, model.getXn()));
			//
			//广西师范申请表增加审核信息
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getId());
			if(spxxInfo.isEmpty()){
				spxxInfo=new HashMap<String,String>();
				spxxInfo.put("ejtjdcdm", "");
				spxxInfo.put("sjtjdcdm", "");
			}
			data.putAll(spxxInfo);
			   //山东畜牧兽医职业学院
			if("12947".equals(Base.xxdm)){
				data.put("xn", model.getXn());
				String sfzh = xsjbxx.get("sfzh");
				for(int i = 0;i < sfzh.length();i++){
					data.put("z"+i, sfzh.substring(i, i+1));
				}
				//获取民族名称并放入datamap
				String mzmc = xsjbxx.get("mzmc");
				data.put("mzmc", mzmc);
				//申请理由
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				String  Jtyzsr = Float.parseFloat(jtqkmodel.getJtrjysr())*Integer.parseInt(jtqkmodel.getJtrs())+"";
				data.put("jtyzsr", Jtyzsr);
				List<HashMap<String , String>> bxnxsgbzwList = dwwhService.getZwForXhXn(xh, model.getXn()); // 学生干部职务任期时间
				HashMap<String , String> bxnxsgbzw1 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 0){
					bxnxsgbzw1 = bxnxsgbzwList.get(0);
				}
				HashMap<String , String> bxnxsgbzw2 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 1){
					bxnxsgbzw2 = bxnxsgbzwList.get(1);
				}
				data.put("bxnxsgbzw1", bxnxsgbzw1);
				data.put("bxnxsgbzw2", bxnxsgbzw2);
			    PjjgDao pjjgdao = new PjjgDao();
				HashMap<String, String> zccjmap = pjjgdao.getzccj(model);
//				CpmdService cpmdService = new CpmdService();
//				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("zccjmap", zccjmap);
//				data.put("bjrs", shxs_xn_bjrs);
				List<HashMap<String, String>> zccjlist = pjjgdao.getzccjList(model.getXh());
				data.put("zccjlist", zccjlist);
				data.put("xmmc", model.getXmmc());
			}
			CpmdService cpmdService = new CpmdService();
			String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
			
			
			//陕西师范大学
			if("10718".equals(Base.xxdm)){
				//必修课成绩平均分以及排名
			    HashMap<String, String> pjfpmMap01 = service.getPjfRank(model, "01" ,"必修课");
			    HashMap<String, String> pjfpmMap02 = service.getPjfRank(model, "02" ,"必修课");
			    HashMap<String, String> zcMap= service.getZcPjfRank(model);
			    HashMap<String, String> djksMap= service.getDjksMap(xh);
			    List<HashMap<String, String>> pjjglist = service.getPjjgByxn(model);
			    data.putAll(pjfpmMap01);
			    data.put("pjf02", pjfpmMap02.get("pjf"));
			    data.put("pm02", pjfpmMap02.get("no"));
			    data.put("pjjglist", pjjglist);
			    data.putAll(zcMap);
			    if(djksMap != null){
			    	data.putAll(djksMap);
			    }
			    List<HashMap<String, String>> xnxqlist = service.getXnXqlist(xh,"bxk");
			    HashMap<String, String> tempMap = null;
			    double total = 0.0;
//			    for (HashMap<String, String> hashMap : xnxqlist) {
//			    	model.setXn(hashMap.get("xn"));
//			    	tempMap = service.getPjfRank(model, hashMap.get("xq"), "必修课");
//			    	data.put("no"+hashMap.get("no"),tempMap.get("no") );
//			    	total += Float.parseFloat(tempMap.get("pjf"));
//				}
			    total = total/xnxqlist.size();
			    DecimalFormat decimalFormat=new DecimalFormat(".00");
			    String totalpjf=decimalFormat.format(total);
			    data.put("totalpjf", totalpjf);
			    List<HashMap<String, String>> xnxqlist1 = service.getXnXqlist(xh,"bxk");
			    HashMap<String, String> tempMap1 = null;
			    double total1 = 0;
			    for (HashMap<String, String> hashMap : xnxqlist1) {
			    	model.setXn(hashMap.get("xn"));
			    	tempMap1 = service.getZcPjfRank(model);
			    	data.put("zcmp"+hashMap.get("no"),tempMap1.get("zcpm") );
			    	data.put("zcmp02"+hashMap.get("no"),tempMap1.get("zcpm02") );
			    	total1 += Float.parseFloat(tempMap1.get("zcpjf"))+Float.parseFloat(tempMap1.get("zcpjf02"));
				}
			    total1 = total1/xnxqlist1.size();
			    String totalpjf1=decimalFormat.format(total1);
			    data.put("totalpjf1", totalpjf1);
			    List<HashMap<String, String>> jsjdjkslist = service.getJsjdjkslist(xh, "NCRE", "计算机等级");
			    data.put("jsjdjkslist", jsjdjkslist);
			    List<HashMap<String, String>> yxxslist = service.getHjrycssj(xh, "优秀学生");
			    List<HashMap<String, String>> yxxglist = service.getHjrycssj(xh, "优秀学生干部");
			    data.put("yxxslist", yxxslist);
			    data.put("yxxglist", yxxglist);
			    if(yxxslist.size() != 0 ){
				    data.put("yxxscs", yxxslist.get(0).get("cs"));
			    }
			    if(yxxglist.size() != 0){
			    	 data.put("yxxgcs", yxxslist.get(0).get("cs"));
			    }
			    if(pjjg != null){
			    	 for (HashMap<String, String> hashMap : pjjg) {
			    		    if( hashMap.get("sqsjs") != null ){
			    		    	hashMap.put("year", hashMap.get("sqsjs").substring(0, 4));
			    		    }else{
			    		    	hashMap.put("year","");
			    		    }
			    		    if( hashMap.get("sqsjs") != null ){
			    		    	hashMap.put("mon", hashMap.get("sqsjs").substring(5, 7));
			    		    }else{
			    		    	hashMap.put("mon","");
			    		    }
					    	
						}
			    }
			   
			    data.put("xh",xh);
			    HashMap<String, String> bxkcj = service.getPjfRank(model, null, "必修");
			    data.put("xncj", bxkcj);
			    if(shxs_xn_bjrs != null && bxkcj.get("no") != null){
				    data.put("noper", decimalFormat.format(Double.parseDouble(bxkcj.get("no"))/Double.parseDouble(shxs_xn_bjrs)*100));
			    }
			    String xsdc = xsjbxx.get("pyccmc");
			    String sub="本科";
			    String sub1="硕士";
			    String sub2="博士";
			    int bz1 = -1;
			    int bz2 = -1;
			    int bz3 = -1;
			    if(xsdc != null){
			    	 bz1 = xsdc.indexOf(sub);
				     bz2 = xsdc.indexOf(sub1);
				     bz3 = xsdc.indexOf(sub2);
			    }
			    if(bz1 >= 0){
			    	data.put("xsdc", "本科");
			    }else if(bz2 >= 0){
			    	data.put("xsdc", "硕士");
			    }else if(bz3 >= 0){
			    	data.put("xsdc", "博士");
			    }
			    HashMap<String, String> zycj = service.getZyPjfRank(model, "必修课");
			    HashMap<String, String> zyrs = service.getZyRs(model.getCpzydm());
			    data.put("zycj", zycj);
			    data.put("zyrs", zyrs);
			    if(model.getXmmc().indexOf("华藏")>=0){
			    	data.put("jxlx", "华藏");
			    }else if(model.getXmmc().indexOf("华藏")>=0){
			    	data.put("jxlx", "孝廉");
			    }
			    List<HashMap<String,String>> zzxmlist = service.getzzxmjg(model.getXh(),model.getXn());
			    data.put("zzxmlist",zzxmlist);
			    HashMap<String, String> sqcs = service.getSqcs(model);
			    data.putAll(sqcs);
			    HashMap<String, String> jxcs = service.getJxcs(model);
			    data.putAll(jxcs);
			    List<HashMap<String, String>> xnlist = service.getXnList(xh);
//			    HashMap<String, String> tempMap01 = null;
//			    for (HashMap<String, String> hashMap : xnlist) {
//			    	model.setXn(hashMap.get("xn"));
//			    	tempMap01 = service.getPjfRank(model, null, "必修课");
//			    	data.put("no"+hashMap.get("no"),tempMap.get("no") );
//			    	data.put("pjf"+hashMap.get("no"), hashMap.get("pjf"));
//				}
//			    HashMap<String, String> tempMap02 = null;
//			    for (HashMap<String, String> hashMap : xnlist) {
//			    	model.setXn(hashMap.get("xn"));
//			    	tempMap02 = service.getZyPjfRank(model, "必修课");
//			    	data.put("zyno"+hashMap.get("no"), tempMap02.get("no"));
//				}
			}
			
			if("10277".equals(Base.xxdm)){
				data.put("jd",new PjxmsqService().getJd_10277(xh,model.getXn()));
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("素拓测评分")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("综测总分")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			//徐州医药职业学校
			if("70002".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);
				StringBuilder sb = new StringBuilder();
//				if(pjjg != null && pjjg.size() >= 1)
//					sb.append("获奖情况：");
				for (HashMap<String, String> pj : pjjg) {
					if(StringUtils.isNotNull(pj.get("xmmc")))
						sb.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(pj.get("xmmc")).append("；");
				}
				
				data.put("hjqk", sb.toString());
				Map<String,String> map = CsszService.getPjzq();
				String xn = map.get("xn");
				String xq = map.get("xq");
				String zccjBjpm = new CxddJgService().getZhcjPm(model.getXh(), xn, xq);
				data.put("zccjBjpm", zccjBjpm);
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						data.put("dyxf", zcfMap.get("fs"));
					}
					if(xmmc.contains("综测总分")){
						data.put("zhpm", zcfMap.get("bjpm"));
					}
				}
				data.put("djjl", HtmlUtil.xmlZy(model.getDjjl()));
				data.putAll(pjxmsqService.getMaxOrMinWfkCj(xh, model.getXn(),model.getXq()));
				//审核意见
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
				String rtsj = service.getkssj(model.getXh());
				data.put("rtsj", rtsj);
				String tyrs = service.gettyrs(model.getCpbjdm());
				data.put("tyrs", tyrs);
				//学年拼接，拆分
				String[] xnArr = model.getXn().split("-");
				data.put("qsxn", xnArr[0]);
				data.put("zhxn", xnArr[1]);
				int uqsnx = Integer.parseInt(xnArr[0])-1;//such as:2016->2015
				int uzhxn = Integer.parseInt(xnArr[1])-1;//such as:2017->2016
				StringBuilder usxn = new StringBuilder();
				String upsxn1 = usxn.append(uqsnx+"-"+uzhxn).toString();//such as:2016-2017 ->2015-2016
				String sxnhlw = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);//such as:2016->16
				String zxnhlw = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);//such as:2017->17
				data.put("sxnhlw", sxnhlw);
				data.put("zxnhlw", zxnhlw);
				int ssxnhlw = Integer.parseInt(sxnhlw)-1;//such as:16->15
				int zxxnhlw = Integer.parseInt(zxnhlw)-1;//such as:17->16
				data.put("upqsxn", ssxnhlw);
				data.put("upzhxn", zxxnhlw);
				//学期写死取02 ，01 
				String sxnxq = "02";
				String zxnxq = "01";
				int pjpm =0;
				List<HashMap<String,String>> zcfList2 = zcfService.getZcfListByXh(xh, upsxn1, sxnxq);
				for (HashMap<String, String> zcfMap : zcfList2) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						data.put("dyfs02_70002", zcfMap.get("fs"));
					}
					if(xmmc.contains("综测总分")){
						data.put("zhpm_70002", zcfMap.get("bjpm"));
						if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
							pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
						}
					}
				}
				List<HashMap<String,String>> zcfList3 = zcfService.getZcfListByXh(xh, model.getXn(), zxnxq);
				for (HashMap<String, String> zcfMap : zcfList3) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						data.put("dyfs01_70002", zcfMap.get("fs"));
					}
					if(xmmc.contains("综测总分")){
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
						if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
							pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
						}
					}
				}
				data.put("pjpm_70002", pjpm/2);
			}
			//浙江万里学院
			if("10876".equals(Base.xxdm)){
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("sfwj", "无");
				}else{
					data.put("sfwj", "有");
				}
				//体育成绩
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("体育分",model.getXn(),model.getXq(),xh).get("fs"));
				//志愿者服务小时数
				data.put("zyfwxs",zcfService.getZcfsByXmXnXqXh("志愿服务小时",model.getXn(),model.getXq(),xh).get("fs"));
				//行为规范成绩（德育分）
				data.put("dyf",zcfService.getZcfsByXmXnXqXh("德育分",model.getXn(),model.getXq(),xh).get("fs"));
				//审核意见
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			//西安科技大学
			if("10704".equals(Base.xxdm)){
				//data.put("jd",new PjxmsqService().getJd_10277(xh,model.getXn()));
				//这里主要是国奖奖学金，获取大学期间获奖情况，装入list。
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				int size1=(4 - pjjgList.size())<0?0:(4 - pjjgList.size());
				data.put("blankList1", getBlankList(size1));
				//必修课~这路的专业成绩和专业人数
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				//西安科技大学，优良中及以及必修课门数
				HashMap<String, String> bxkms_10704 = service.getXakjdxylzjbxkms(model.getXh(),model.getXn());
				 //优秀必修课门数
				data.put("yxkc", bxkms_10704.get("yxms"));
				//良好必修课门数
				data.put("lhkc", bxkms_10704.get("lhms"));
				//中等必修课门数
				data.put("zdkc", bxkms_10704.get("zdms"));
				//及格必修课门数
				data.put("jgkc", bxkms_10704.get("jgms"));
				//必修课及格门数
				data.put("bxkjgms", bxkms_10704.get("bxkjgms"));
				//必修课门数
				data.put("bxkms", bxkms_10704.get("bxkms"));
				//本学年获奖情况
				List<HashMap<String, String>> hjqkList = service.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
			    //另一种方法的必修课和成绩排名
			    HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> cjpm = service.getCjPm(model.getXh(),model.getXn());
				data.put("bxk", bxk);
				data.put("cjpm", cjpm);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("素拓测评分")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("综测总分")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			if("11799".equals(Base.xxdm)){
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("智育")){
						data.put("zyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("德育")){
						data.put("dycjfs", zcfMap.get("fs"));
						data.put("dyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("平均成绩")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
					}
				}
				HashMap<String,String> bjxxMap = servicePjPy.getBjxx(xsjbxx.get("bjdm"));
				data.put("fdyxm", bjxxMap.get("fdyxm"));
				PjjgService pjjgservice_11799 = new PjjgService();
				List<HashMap<String, String>> shrList = pjjgservice_11799.getShrList(xh, model.getXn(), model.getXq(), model.getXmdm());

				for (int i = 0; i < shrList.size(); i++) {
					data.put("shr"+i, shrList.get(i).get("xm"));
					data.put("time"+i, DateUtils.getDayOfCn(shrList.get(i).get("shsj")));
					data.put("shyj"+i, shrList.get(i).get("shyj"));
				}
				
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ 
					data.put("ywcfgk", "无");
				}else{
					data.put("ywcfgk", "有");
				}
				if(StringUtils.isNull(bjgcjs)){ // 本学年有无不及格课程
					data.put("bxnywbjgkc", "无");
				}else{
					data.put("bxnywbjgkc", "有");
				}
				
				
				List<HashMap<String, String>> cjList1 = service.getCjsxqList(model.getXn(),model.getXh());
				/**
				 * 成绩处理，写死八项课程八项成绩 第一学期
				 */
				HashMap<String,String> cjMap1 = new HashMap<String, String>();
				for (int i = 0; i < 10; i++) {
					cjMap1.put("kcmc"+i,"");
					cjMap1.put("cj"+i,"");
				}
				for (int i = 0;  i< cjList1.size(); i++) {
					data.put("kcmc"+i,cjList1.get(i).get("kcmc"));
					data.put("cj"+i,cjList1.get(i).get("cj"));
				}
				List<HashMap<String, String>> cjList2 = service.getCjxsqList(model.getXn(),model.getXh());
				/**
				  成绩处理，写死八项课程八项成绩 第二学期
				 */
				HashMap<String,String> cjMap2 = new HashMap<String, String>();
				for (int i = 0; i < 10; i++) {
					cjMap2.put("xsqkcmc"+i,"");
					cjMap2.put("xsqcj"+i,"");
				}
				for (int i = 0;  i< cjList2.size(); i++) {
					data.put("xsqkcmc"+i,cjList2.get(i).get("kcmc"));
					data.put("xsqcj"+i,cjList2.get(i).get("cj"));
				}
			}
			//评奖日期，奖项名称，颁奖单位用的太多，拉出来通用
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
			servicePjPy.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
			data.put("pjjgListhjqk", pjjgListhjqk);
			int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
			data.put("blankListhjqk", getBlankList(size1));
			if(xsjbxx.get("csrq")!=null){
				String[] csArr = xsjbxx.get("csrq").split("\\D");
				if(csArr != null&&csArr.length == 3){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
					data.put("csr1", csArr[2]);
				}else if (csArr != null&&csArr.length ==2){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
				}else if (csArr != null&&csArr.length ==1){
					data.put("csn1", csArr[0]);
				}
				String[] xnArr = model.getXn().split("-");
				if(xnArr.length == 2){
					data.put("qsxn", xnArr[0]);
					data.put("zhxn", xnArr[1]);
				}else if (xnArr.length == 1){
					data.put("qsxn", xnArr[0]);
				}
				//出生年出生月
				if(csArr != null&&csArr.length == 2){
					data.put("csn", csArr[0]);
					data.put("csy", csArr[1]);
				}else if (csArr != null&&csArr.length == 1){
					data.put("csn", csArr[0]);
				}
			}
			data.put("bjrs", shxs_xn_bjrs);
			
				
			
			/**
			 * List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("智育")){
						data.put("zyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("德育")){
						data.put("dycjfs", zcfMap.get("fs"));
						data.put("dyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("平均成绩")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
					}
					
				}
			 */
			//格式化数据
			cjList=service.formatForDjb(cjList);
			String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
			xsjbxx.put("bjrs", bjrs);
			//转换为中文日期格式
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
			String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
			xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", "")); // 2016.12
			data.put("xmmc", model.getXmmc());// 项目名称
			data.put("currXn", model.getXn());
			data.put("pjjg", pjjg);
			data.put("pjjgAll",pjjgAll);
			data.put("cjList", cjList);
			model.setSqly(HtmlUtil.xmlZy(model.getSqly()));
			data.put("xmxx", model);
			data.put("rs", xsjbxx);
			data.put("sqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day));
			data.put("photo", photo);
			data.put("zcf", zcf);
			data.put("xxmc", Base.xxmc);
			
			HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
			if(xxcjMap == null){
				xxcjMap = new HashMap<String,String>();
			}
			data.put("xxcjMap", xxcjMap);
			String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
			List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
			for (HashMap<String, String> zcfMap : zcfList) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("综测总分")){
					data.put("zczf", zcfMap.get("fs"));
					data.put("bjpm", zcfMap.get("bjpm"));
					data.put("njzypm", zcfMap.get("njzypm"));
					data.put("cpzpm", zcfMap.get("cpzpm"));
					break;
				}					
			}
			// ================= 项目性质 begin ==================
			data.put("xmxzmc", "");
			XmwhService xmwhService = new XmwhService();
			List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
			for (HashMap<String, String> xmxzMap : xmxzList) {
				if (xmxzMap.get("dm").equals(model.getXzdm())){
					data.put("xmxzmc", xmxzMap.get("mc"));
					break;
				}
			}
			//河北工业大学
			if("10080".equals(Base.xxdm)){
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				List<HashMap<String,String>> xmXnBxCjList = zcfService.getCjListByXhXn(xh, model.getXn(), "必修");
				data.put("xnpjf", service.getPjf(xmXnBxCjList, 2));// 学年平均分
				String bjzrs = zcfService.getBjrs(xh);
				data.put("bjzrs",bjzrs);// 班级人数
				data.put("cjpm", cjpm);// 成绩排名
				/*取【学年综合成绩】*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*取学年综合成绩名次*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjgAllList =  service.getPjpyInfoList(model.getXh(),model.getXn());
				for (int i = 0; i < pjjgAllList.size(); i++) {
					HashMap<String, String> pj = pjjgAllList.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("、");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				data.put("xmmc", model.getXmmc());// 项目名称
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			{
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				List<HashMap<String,String>> xmXnBxCjList = zcfService.getCjListByXhXn(xh, model.getXn(), "必修");
				data.put("xnpjf", service.getPjf(xmXnBxCjList, 2));// 学年平均分
				String bjzrs = zcfService.getBjrs(xh);
				data.put("bjzrs",bjzrs);// 班级人数
				data.put("cjpm", cjpm);// 成绩排名
				/*取【学年综合成绩】*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*取学年综合成绩名次*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjgAllList =  service.getPjpyInfoList(model.getXh(),model.getXn());
				for (int i = 0; i < pjjgAllList.size(); i++) {
					HashMap<String, String> pj = pjjgAllList.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("、");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				data.put("xmmc", model.getXmmc());// 项目名称
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}

				// 获取各种成绩
				List<HashMap<String,String>> hbzcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> hbzcfMap : hbzcfList) {
					String xmmc = hbzcfMap.get("xmmc").trim();
					if(xmmc.equals("学年学分绩点")){
						data.put("xnxfjd", hbzcfMap.get("fs"));
					}else if(xmmc.equals("学年体育测试成绩")){
						data.put("xntycscj",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("第一课堂学年学分绩点排序")){
						data.put("dyktxnxfpx",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("第二课堂学年学分排序")){
						data.put("dektxnxfpx", hbzcfMap.get("fs"));
					}else if(xmmc.equals("学年排序加权综合评价")){
						data.put("xnpxjqzhpj",  hbzcfMap.get("fs"));
					}
				}
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				data.put("xmje",model.getXmje());//项目金额
			}
			//徐州工程
			if("11998".equals(Base.xxdm)){
				// 有无违纪处分
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "否");
				}else{
					data.put("ywcfmc", "是");
				}
				//补考课数
				String bkks = xxcjMap.get("bkcjnum");
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "无");
				}else {
					data.put("sfbk", "有");
				}
				StringBuffer jxj = new StringBuffer();//奖学金
				StringBuffer qtjx = new StringBuffer();//其他奖项
				for (HashMap<String, String> pj : pjjgAll) {
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						if(xmmc.contains("奖学金")){
							jxj.append(xmmc);
							jxj.append("、");
						}else{
							qtjx.append(xmmc);
							qtjx.append("、");
						}
					}
				}
				if(jxj.length()>0)
				jxj.deleteCharAt(jxj.length() - 1);//删除最后一个、
				if(qtjx.length()>0)
				qtjx.deleteCharAt(qtjx.length() - 1);
				String tbrq = model.getSqsj();// 申请时间
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				data.put("jxj", jxj.toString());
				data.put("qtjx", qtjx.toString());
				List<HashMap<String,String>> zwInfo = dwwhService.getZwxx(xh);//所有职务
				StringBuffer xrzw = new StringBuffer();
				StringBuffer crzw = new StringBuffer();
				for (HashMap<String, String> map : zwInfo) {
					String lzsj = map.get("lzsj");
					if("1".equals(map.get("zzzt")) && map.get("rzsj").length() >= 10){
						xrzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-至今"+"、");
					}else{
						if((lzsj != null || !"".equals(lzsj)) && map.get("rzsj").length() >= 10 && map.get("lzsj").length() >= 10){
							crzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-"+map.get("lzsj").substring(0,10)+"、");
						}
					}
				}
				if(xrzw.length() > 0) xrzw.deleteCharAt(xrzw.length() - 1);
				if(crzw.length() > 0) crzw.deleteCharAt(crzw.length() - 1);
				data.put("xrzw", xrzw.toString());//现任职务
				data.put("crzw", crzw.toString());//曾任职务
				
				List<HashMap<String,String>> cjlist = zcfService.getCjListByXh(xh);
				data.put("pjcj", service.getPjf(cjlist, 2));//平均成绩
				String cjpm = zcfService.getCjpm("", xh, xsjbxx.get("bjmc"));
				data.put("cjpm", cjpm);
			}
			//浙江交通职业技术学院
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getId());
				data.put("bjyj", shyjjtzyList.get(0).get("shyj"));
				data.put("xscyj", shyjjtzyList.get(2).get("shyj"));
				//取综合测评分数
				List<HashMap<String,String>> pmfsList = new ZhcpDjService().getZcfsForDjb(model.getXh(), model.getXn(), model.getXq());
				for (int i = 0; i < pmfsList.size(); i++) {
					HashMap<String, String> temp = pmfsList.get(i);
					String xmmc = temp.get("xmmc");
					if("课程学习记实测评分".equals(xmmc)){
						data.put("kcf", temp.get("fs"));
					}else if("思想品德行为表现记实测评分".equals(xmmc)){
						data.put("pdf", temp.get("fs"));
					}else if("体育表现记实测评分".equals(xmmc)){
						data.put("tyf", temp.get("fs"));
					}else if("综合测评分".equals(xmmc)){
						data.put("zf", temp.get("fs"));
					}
				}
				data.put("nd",new PjxmsqService().getHsbyjs(xh));
				
			}
			
			//通用审核意见1-7级
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(model.getId());
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			// ================= 项目性质 end ==================
			//评奖登记表，判断是否有个性化表单，否则默认。
			String templatePath = template_dir+"//pjdjb_"+Base.xxdm+".xml";
			String templateXmlPath = "pjdjb_"+Base.xxdm+".xml";
			File wordFile = null;
			//广西医科大学个性化多张报表
			/*if("10598".equals(Base.xxdm)){
				if("‘优利特’优秀学生干部奖学金".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
				}else if("邓洁彬奖学金".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_djbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_djbjxj.xml";
				}else if("荣和教育奖学金".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
				}else if("三好学生".equals(model.getXmmc()) || "优秀学生干部".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_shxs.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_shxs.xml";
				}
			}*/
			
			/**
			 * 原先批量导出文件命名方式为 学号[姓名].doc 方式，这中方式在批量导出同一个学生的时候有问题，无论导出几个，都指向的是同一个文件，所以这次把批量改成 学号[姓名]-项目代码.doc 这样就不会重复
				1036
			 */
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), xsjbxx.get("bjmc")+""+xsjbxx.get("xh")+"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}
			
			//判断文件是否存在
			/*try{
				ResourceUtils.getFile(templatePath);
				wordFile = FreeMarkerUtil.getWordFile(data,template_dir,templateXmlPath, FreeMarkerUtil.getFileName(xh+"["+xsjbxx.get("xm")+"]"));
			}catch (Exception e) {
				wordFile = FreeMarkerUtil.getWordFile(data,template_dir,default_template,FreeMarkerUtil.getFileName(xh+"["+xsjbxx.get("xm")+"]"));
			}
			*/
					
			return wordFile;
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 登记 表ZIP
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-8-29 上午11:14:13
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * @描述: 查询荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
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
	public ActionForward cxRyzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		User user = getUser(request);// 用户对象
		PjjgService service = new PjjgService();
		Map<String,String> map = service.cxRyzs(csdm, user);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @描述: 保存荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
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
	@SystemLog(description="访问评奖评优-我的评奖-评奖结果-保存荣誉证书模板-CSDM:{csdm}")
	public ActionForward bcRyzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String csz = request.getParameter("csz");
		// 处理模板
		csz = csz.replaceAll("\\#\\{", "\\\\#\\{");
		User user = getUser(request);// 用户对象
		PjjgService service = new PjjgService();
		boolean result = service.bcRyzs(csdm, csz, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 打印荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
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
	public ActionForward dyRyzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		PjjgService service = new PjjgService();
		XsxxService xsxxService = new XsxxService();
		// 获取原始模板
		User user = getUser(request);// 用户对象
		Map<String,String> map = service.cxRyzs(csdm, user);
		String csz = map.get("csz");
		// 循环生成打印内容
		StringBuilder cszBuilder = new StringBuilder();
		// 处理后的内容
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			// 当前时间
			Calendar now = Calendar.getInstance();
			String year = String.valueOf(now.get(Calendar.YEAR));
			String month = String.valueOf(now.get(Calendar.MONTH) + 1);
			String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				PjjgModel model = service.getModel(values[i]);
				// 处理学年
				String[] xnArr = model.getXn().split("-");
				String ksxn = DateUtils.numToZh(xnArr[0]);
				String jsxn = DateUtils.numToZh(xnArr[1]);
				// 学生信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				cszTemp = csz.replaceAll("\\\\#\\{xm\\}", xsjbxx.get("xm"));
				// ================ 替换模板标签 begin =================
				if("10022".equals(Base.xxdm)){
					// 北京林业大学个性化
					cszTemp = cszTemp.replaceAll("二〇一\\\\#\\{ksxn\\}", ksxn)
						.replaceAll("二〇一\\\\#\\{jsxn\\}", jsxn)
						.replaceAll("\\\\#\\{xxmc\\}", model.getXmmc());
				}
				// ================ 替换模板标签 end =================
				if("10704".equals(Base.xxdm)){
					String ksrq = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);
					String jsrq = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);
					cszTemp = cszTemp.replaceAll("\\\\#\\{ksrq\\}",ksrq)
					.replaceAll("\\\\#\\{jsrq\\}", jsrq)
					.replaceAll("\\\\#\\{xmmc\\}", model.getXmmc());
				}
				cszTemp = cszTemp.replaceAll("\\\\#\\{year\\}", year)
									.replaceAll("\\\\#\\{month\\}", month)
									.replaceAll("\\\\#\\{day\\}", day);
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // 下一页
				}
			}
		}
		// 保存打印内容
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-4 下午01:37:14
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
	private ActionForward configDrSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		ImportConfig config = new ImportConfig();
		config.config("IMPORT_N100102","","XG_PJPY_NEW_PJJGB");
		
		return null;
	}
	
	/**
	 * 空的list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}
	
	/**
	 * @描述: 江西科技师范大学 荣誉证书、奖学金打印
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-27 上午09:45:47
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
	public ActionForward getRyzsJxjZip_11318(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		String dytype_11318 = request.getParameter("dytype_11318");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getRyzsJxjFile_11318(values[i], dytype_11318);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * @描述: 江西科技师范大学 荣誉证书、奖学金打印
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-27 上午09:45:47
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
	public ActionForward getRyzsJxjWord_11318(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		String dytype_11318 = request.getParameter("dytype_11318");
		
		File wordFile = getRyzsJxjFile_11318(model.getId(), dytype_11318);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	/**
	 * @描述: 江西科技师范大学 荣誉证书、奖学金打印
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-27 上午09:45:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param dytype_11318
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getRyzsJxjFile_11318(String id, String dytype_11318)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService service = new PjjgService();
		PjjgModel model = service.getModel(id);
		
		if (model != null){
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//查询项目记录
				if(xmMap != null){
					model.setXn(xmMap.get("xn"));
					model.setXq(xmMap.get("xq"));
				}
			}
			
			//获取学期名称
			List<HashMap<String,String>> xqList = Base.getXqList();
			for (HashMap<String,String> map : xqList){
				if (map.get("xqdm").equals(model.getXq())){
					model.setXqmc(map.get("xqmc"));
					break;
				}
			}
			
			String xh = model.getXh();
			
			//基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			
			String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
			data.put("dyrq",rq);
			
			data.put("xm", xsjbxx.get("xm"));
			data.put("xmmc", model.getXmmc());
			data.put("xn",model.getXn());
			data.put("xqmc",model.getXqmc());
			
			String templateXmlPath = "";
			if("ryzs".equals(dytype_11318)){
				templateXmlPath = "ryzs_11318.xml";
			}else{
				templateXmlPath = "jxj_11318.xml";
			}
			
			File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:上海海洋大学证明打印
	 * @作者：cq [工号：785]
	 * @日期：2015-5-15 下午05:20:43
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
	public ActionForward ryzsJxjDy_10264(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		PjjgService service = new PjjgService();
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			
			files = service.getRyzsJxjFile_10264(values);
			
			//单个打印
			if(files.size()==1){
				FileUtil.outputWord(response, files.get(0));
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}

	//山东畜牧兽医职业学院个性化需求（社会奖学金汇总表）
	@SystemAuth(url = url)
	public ActionForward getSdxm_shjxjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    PjjgModel model = (PjjgModel) form;
		PjjgDao dao = new PjjgDao();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = dao.getDclist(model,user);
		String zrs = "";
		float xmze1 = 0;
		for (HashMap<String, String> hashMap : resultList) {
			if(zrs.equals("")&&hashMap.get("total")!= null){
				zrs = hashMap.get("total");
			}
			if(hashMap.get("xmje")!= null){
				xmze1 = xmze1+ Float.parseFloat(hashMap.get("xmje"));
			}
		}
		if(zrs.equals("")){
			zrs = "0";
		}
		String xmze = xmze1+"";
		data.put("xmze",xmze);
		data.put("zrs", zrs);
		data.put("xsxxlist",resultList);
		String xmlb1 = request.getParameter("value");
		String[] xmlb = xmlb1.split(",");
		data.put("xmlb", xmlb[0]);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "sdxmsy_12947_shjxjhzb.xml", "社会奖学金学生汇总表");
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	//山东畜牧兽医职业学院个性化需求（省励志奖学金汇总表）
	@SystemAuth(url = url)
	public ActionForward getSdxm_slzjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		User user = getUser(request);
		PjjgService pjjgserice = new PjjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_slzjhzexcel(values,user);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	//山东畜牧推荐表打印
	@SystemAuth(url = url)
	public ActionForward getSdxmTjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	    PjjgModel model = (PjjgModel) form;
		PjjgDao dao = new PjjgDao();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = dao.getDclist(model,user);
		data.put("xsxxlist",resultList);
		String xmmc1 = request.getParameter("value");
		String[] xmmc = xmmc1.split(",");
		data.put("xmmc", xmmc[0]);
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", "sdxmsy_12947_yxxsgbtjb.xml", xmmc[0] );
		FileUtil.outputWord(response, wordFile);
		return null;

   }
	
	/**
	 * 
	 * @描述:查找学生
	 * @作者：cq [工号：785]
	 * @日期：2015-11-20 下午06:19:17
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getZjXs(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		String path = "xpj_pjjg.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
		
	}
	/**
	 * @描述: 北京林业大学个性化评优登记表打印
	 * @作者：孟威[工号：1186]
	 * @日期：2016-5-11 下午09:19:01
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
	public ActionForward getPjdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel myForm = (PjjgModel) form;
		File wordFile = getXjkWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	/**
	 * @描述: 北京大学林业大学评优登记表批量打印
	 * @作者：孟威[工号：1186]
	 * @日期：2016-5-12 下午02:55:51
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
	public ActionForward getPjdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel myForm = (PjjgModel) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			List<String> xhList = new ArrayList<String>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				if(xhList.contains(values[i])){
					continue;
				}else{
					xhList.add(values[i]);
				}
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	/**	
	 * @描述: 北京大学林业大学评优登记表数据提取
	 * @作者：孟威[工号：1186]
	 * @日期：2016-5-12 下午02:56:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getXjkWord (PjjgModel myForm,HttpServletRequest request) throws Exception{
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		PjjgService service = new PjjgService();
		String xn = request.getParameter("xn");
		String xh = myForm.getXh();
		Map<String,Object> data = new HashMap<String,Object>();
		// 加载学生基本信息
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		//获得打印日期
		String dyrq = xsxxglService.getDqrq(xh);
		data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
		// 获得学生所获得的奖项{搜索学年}
		List<HashMap<String, String>> pjjgList = service.getPjjgList(xh,xn);
		data.put("pjjgList", pjjgList);
		data.put("xn", xn);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//pjpy","pydjb_10022.xml",xh+"-"+xsxxMap.get("xm"));
		return file;
	}
	
	/**
	 * @描述：导出汇总表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月23日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward getHzbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		File excelFile = service.getHzbPrint(searchModel);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	/**
	 * @描述：评奖结果汇总
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月10日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward pjjghzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPjjghzList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		//searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "xpj_pjjg.do?method=pjjghzList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjjghzList");
	}
	
	public ActionForward getHzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受选中
		String rows =  request.getParameter("rows");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//获取模板内容和数据
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		PjjgService service = new PjjgService();
		List<HashMap<String, String>> hzmdLists = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> hzmdList = new ArrayList<HashMap<String, String>>();
		PjjgModel model = (PjjgModel) form;
		if(rowsList!=null && rowsList.size()>0){
			for (Object object : rowsList) {
				MorphDynaBean rowBean = (MorphDynaBean) object;
				model.setLxdm((String) rowBean.get("lxdm"));
				model.setXn((String) rowBean.get("xn"));
				model.setXq((String) rowBean.get("xq"));
				model.setXmmc((String) rowBean.get("xmmc"));
				model.setXzdm((String) rowBean.get("xzdm"));
				if(rowBean.get("xmmc")!=null){
					if(bbMap==null){
						bbMap = this.getBbMap(model);
					}
				}
				 hzmdList=service.getPjjghzMdList(model);
				for(HashMap<String, String> map:hzmdList){
					hzmdLists.add(map);
				}
			}	
		}
		if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
			throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
		}
		if(hzmdLists!=null && hzmdLists.size()>0){
			File excelFile = getExcel(hzmdLists,bbMap,user,model);
			if(excelFile!=null){
				FileUtil.outputExcel(response, excelFile);
			}
		}
		return null;
	}
	private File getExcel(List<HashMap<String, String>> hzmdLists,HashMap<String, String> bbMap, User user,PjjgModel model) throws Exception {
		String xn = hzmdLists.get(0).get("xn");
		String xmmc = hzmdLists.get(0).get("xmmc");
		Map<String, Object> data = new HashMap<String, Object>();
		PjjgService service = new PjjgService();
		
		data.put("hzmdList", hzmdLists);//资助项目结果列表
		data.put("xn", xn);
		data.put("xmmc", xmmc);
		data.put("today", DateUtils.getLyr());
		data.put("xxmc", Base.xxmc);
		String xnFront = xn.substring(0, 4);
		String xnBehind = xn.substring(5, 9);
		data.put("xnFront", xnFront);
		data.put("xnBehind", xnBehind);
		String xmje = "";
		double zje = 0;
		double excleZje = 0;
		//String excleZje = service.getExcleZje(model);
		for (int i = 0; i < hzmdLists.size(); i++) {
			xmje = hzmdLists.get(i).get("xmje"); 
			if(!"".equals(xmje)){
				if(xmje == null){
					xmje = "0";
				}
			}
			zje = Double.parseDouble(xmje);
			excleZje += zje;
		}
		data.put("excleZje", excleZje);
		String templateDirectory = Constants.TEP_DIR + bbMap.get("mblj");
		String templateName = bbMap.get("mbmc");
		String fileName = FreeMarkerUtil.getFileName(xmmc);
		if(StringUtils.equals("getPjjghzMdListByXy",model.getType())){
			String xymc = service.getXymcBydm(model.getXydm());
			fileName = FreeMarkerUtil.getFileName(xmmc+"_"+xymc);
		}
		File wordFile = FreeMarkerUtil.getExcelFile(data, templateDirectory, templateName, fileName);
		return wordFile;
	}
	public HashMap<String, String> getBbMap(PjjgModel model) throws Exception{
		HashMap<String, String> bbMap = null;// 报表
		
		if (!StringUtil.isNull(model.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(),model.getXn(),model.getXq());//查询项目记录
			if (xmMap != null) {
				BbwhService bbwhService = new BbwhService();
				bbMap = bbwhService.getDataById(xmMap.get("dysbbb"));//获取对应上报报表
			}
		}
		if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
			throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
		}
		return bbMap;
	}

	public ActionForward getpjjghzMd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPjjghzMdList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xqmc", request.getParameter("xqmc").equals("null")?null:request.getParameter("xqmc"));
		String path = "xpj_pjjg.do?method=pjjghzMdList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjjghzMdList");
	}
	
	
	
	/**
	 * @描述: 荣誉证书、奖学金打印
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-27 上午09:45:47
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
	public ActionForward getRyzsJxjZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		String dytype = request.getParameter("dytype");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getRyzsJxjFile(values[i], dytype);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * @描述: 荣誉证书、奖学金打印
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-27 上午09:45:47
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
	public ActionForward getRyzsJxjWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgModel model = (PjjgModel) form;
		String dytype = request.getParameter("dytype");
		
		File wordFile = getRyzsJxjFile(model.getId(), dytype);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	/**
	 * @描述: 荣誉证书、奖学金打印
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-27 上午09:45:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param dytype
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getRyzsJxjFile(String id, String dytype)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService service = new PjjgService();
		PjjgModel model = service.getModel(id);
		
		if (model != null){
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//查询项目记录
				if(xmMap != null){
					model.setXn(xmMap.get("xn"));
					model.setXq(xmMap.get("xq"));
				}
			}
			
			//获取学期名称
			List<HashMap<String,String>> xqList = Base.getXqList();
			for (HashMap<String,String> map : xqList){
				if (map.get("xqdm").equals(model.getXq())){
					model.setXqmc(map.get("xqmc"));
					break;
				}
			}
			
			String xh = model.getXh();
			
			//基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			
			String rq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
			String ny = DateTranCnDate.dateToCnDateSubMonth(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			data.put("dyrq",rq);
			data.put("dyny",ny);
			
			data.put("xm", xsjbxx.get("xm"));
			data.put("xmmc", model.getXmmc());
			data.put("xn",model.getXn());
			data.put("xqmc",model.getXqmc());
			data.put("xh", model.getXh());
			
			String templateXmlPath = "";
			if("ryzs".equals(dytype)){
				templateXmlPath = "ryzs_" + Base.xxdm + ".xml";
			}else{
				templateXmlPath = "jxj_" + Base.xxdm + ".xml";
			}
			
			File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * @description	： 表彰名单导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-27 下午02:45:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bzmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();	
		File file = service.createBzmdWord(model);
		FileUtil.outputWord(response, file);
		return null;		
	}

	/**
	 * @description	： 验证是否有表彰名单能够导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-4 下午02:42:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzbzmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService pjjgService = new PjjgService();
		boolean result = pjjgService.isExportBzMd(model);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject fromBean = JSONObject.fromBean(map);
		response.getWriter().print(fromBean);
		return null;
	}
	
	/**
	 * 
	 * @描述: 奖学金账号汇总表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-24 上午11:35:26
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
	public ActionForward jxjzhhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		User user = getUser(request);
		File file = new PjjgService().getJxjhzzhList(model, user);
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("奖学金账号汇总表.xls","utf-8")); 
		FileUtil.outputFile(response, file);
		return null;
	}
	
	/**
	 * @描述:苏州卫生职业技术学院个性化，奖学金发放财务用表
	 * @作者：lgx[工号：1553]
	 * @日期：2018年3月9日 下午2:30:46
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
	public ActionForward cwybPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgModel model = (PjjgModel) form;
		PjjgService service = new PjjgService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getCwybList(model, user);//查询出所有记录，不分页
		StringBuilder xmmc = new StringBuilder();
		HashMap<String,String> hashMap = new HashMap<String, String>();
		if(searchModel.getSearch_tj_xmmc() != null){
			for (String s : searchModel.getSearch_tj_xmmc()) {
				xmmc.append(s+"、");
			}
			xmmc.deleteCharAt(xmmc.length()-1);
		}
		hashMap.put("xmmc", xmmc.toString());
		hashMap.put("hjje", service.getCwybSum(model, user));
		String xn = searchModel.getSearch_tj_xn()==null ? "" : searchModel.getSearch_tj_xn()[0];
		hashMap.put("xn",xn);
		if(xn != null && xmmc.toString() != null){
			File file = service.getCwybFile(resultList,hashMap);//生成导出文件
			FileUtil.outputExcel(response, file);
		}
		return null;
	}

	/**
	 * @描述:根据学院打印汇总表（浙江交通职业技术学院）
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/27 14:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward getHzbXy(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjjgModel model = (PjjgModel) form;
		model.setType("getPjjghzMdListByXy");
		//接受选中
		String rows =  request.getParameter("rows");
		String[] xydms =  request.getParameter("xydms").split(",");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//获取模板内容和数据
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		PjjgService service = new PjjgService();
		List<File> files = new ArrayList<File>();

		if(rowsList!=null && rowsList.size()>0){
			for (Object object : rowsList) {
				MorphDynaBean rowBean = (MorphDynaBean) object;
				model.setLxdm((String) rowBean.get("lxdm"));
				model.setXn((String) rowBean.get("xn"));
				model.setXq((String) rowBean.get("xq"));
				model.setXmmc((String) rowBean.get("xmmc"));
				model.setXzdm((String) rowBean.get("xzdm"));
				if(rowBean.get("xmmc")!=null){
					if(bbMap==null){
						bbMap = this.getBbMap(model);
					}
				}

			}
		}
		if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
			throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
		}
		for(String xydm : xydms){
			model.setXydm(xydm);
			List<HashMap<String, String>> hzmdList = service.getPjjghzMdList(model);
			if(hzmdList!=null && hzmdList.size()>0){
				File excelFile = getExcel(hzmdList,bbMap,user,model);
				if(excelFile!=null){
					files.add(excelFile);
				}
			}else{
				//获取空的Excel
				String xymc = service.getXymcBydm(xydm);
				String fileName = FreeMarkerUtil.getFileName(model.getXmmc()+"_"+xymc);
				File file = new File(System.getProperty("java.io.tmpdir"),fileName+".xls");
				if(!file.exists()){
					file.createNewFile();
				}
				WritableWorkbook wwb = Workbook.createWorkbook(file);
				WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
				sheetNull.setColumnView(0, 15);
				Label msg = new Label(0, 0,"暂无数据！");
				sheetNull.addCell(msg);
				wwb.write();
				wwb.close();
				files.add(file);
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:32:05 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgForm;
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jglr.JglrService;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszService;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tzsz.TzszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-申请审核 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:32:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SqshAction extends SuperAction {
	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	private static final String JXSQ = "jxsq";
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String PJPY_KEY = "PJPY";
	private static final String Temp = "java.io.tmpdir";
	private static final String KFHJQKXZ = "1";
	private static  String xzdm = "1";
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(JXSQ);
	}
	
	private static final String url = "pj_jxsb.do";

	/**
	 * 
	 * @描述: 奖项上报
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 上午10:55:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward viewJxsbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		if (QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel); 
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getJxsbList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//综测项目列表
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getCurrentZcxmList();
//		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm();
//		List<HashMap<String,String>> zcxmList = zcxmService.getCurrentZfxm();
		
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("path", "pj_jxsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJxsbList");
	}

	
	/**
	 * 
	 * @描述: 奖项上报
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 上午11:04:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward toJxsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		//评奖项目信息
		XmwhService xmwhService = new XmwhService();
		XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
		request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		
		//学生基本信息
//		XsxxService xsxxService = new XsxxService();
//		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
		request.setAttribute("jbxx", xsjbxx);
		
		//已申请评奖项目
		List<HashMap<String,String>> ysqPjxmList = service.getYsqPjxmList(model.getXh(),model.getXmdm());
		request.setAttribute("ysqPjxmList", ysqPjxmList);
		
		//条件
		TjszService tjszService = new TjszService();
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
		
		//校验条件，返回校验结果
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
		request.setAttribute("checkResult", results);
		return mapping.findForward("toJxsb");
	}
	
	
	/**
	 * 
	 * @描述: 奖项上报（保存）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 上午11:44:33
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项上报-上报保存-XMDM:{xmdm}")
	public ActionForward saveJxsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		//保存操作
		boolean result = service.saveJxsb(model, user.getUserName());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 奖项申请
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 上午10:56:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"})
	public ActionForward viewJxsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		xzdm=model.getXzdm();
		SqshService service = new SqshService();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
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
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("xzdm", model.getXzdm());
		if("2".equals(xzdm))
		{
			request.setAttribute("path","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1");
		}else{
			request.setAttribute("path","pj_jxsq.do");
		}
		request.setAttribute("czpath", "pj_jxsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("viewJxsqList");
	}
	
	
	/**
	 * 
	 * @描述: 奖项审核
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 上午10:57:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	
	public ActionForward viewJxshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		xzdm=model.getXzdm();
		SqshService service = new SqshService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getAudingListSingle(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//综测项目列表
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getCurrentZcxmList2();
		request.setAttribute("zcxmList", zcxmList);
		if("2".equals(xzdm))
		{
			request.setAttribute("path","xpj_sqsh.do?method=viewJxshList&xzdm=2&sindex=1");
		}else{
			request.setAttribute("path","pj_jxsh.do");
		}
		request.setAttribute("czpath", "pj_jxsh.do");
		FormModleCommon.commonRequestSet(request);
		//参数设置
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		//默认查询条件 
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xzdm", model.getXzdm());
		
		return mapping.findForward("viewJxshList");
	}
	
	@SystemAuth(url = "pj_jxplsh.do")
	public ActionForward viewJxplshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getAudingList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "pj_jxplsh.do");
		FormModleCommon.commonRequestSet(request);
		//参数设置
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		//默认查询条件 
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("viewJxshList_1202");
	}
	
	/**
	 * 
	 * @描述:跳转至批量审核页
	 * @作者：obq[445]
	 * @日期：2013-12-5 上午09:06:37
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
	public ActionForward toCheckPjpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String xn = request.getParameter("xn");
//		String xq = request.getParameter("xq");
//		String bjdms = request.getParameter("bjdms");
		SqshModel model = (SqshModel) form;
		User user = getUser(request);
		//得到综测项目
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(model.getXn(),model.getXq());
		request.setAttribute("zcxmList", zcxmList);
		//得到评奖项目
		XmwhService xmwhService = new XmwhService();
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm(model.getXn()+model.getXq(),model.getBjdms());
		request.setAttribute("pjxmList", pjxmList);
		//得到所选班级学生综测分
		ZcfsService service = new ZcfsService();
		List<HashMap<String,String>> resultList = service.getZcfjgList(model.getXn(),model.getXq(),model.getBjdms());
		//得到各申报项目的最后级审核状态及岗位
		SqshService sqService = new SqshService();
		request.setAttribute("resultList", resultList);
		List<HashMap<String,String>> shLists = sqService.getLastCheckStatus(user,model.getBjdms(), model.getXn(),model.getXq());
		request.setAttribute("shLists", shLists);
		//request.setAttribute("bjdms", bjdms);
		request.setAttribute("model", model);
		//request.setAttribute("path", "pj_jxsh.do");
		return mapping.findForward("toCheckPjpy");
	}
	
	/**
	 * 
	 * @描述:跳转到批量申请页面
	 * @作者：cq [工号：785]
	 * @日期：2013-12-12 上午09:20:09
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
	public ActionForward toCheckPjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		SqshService sqshService = new SqshService();
		SqshService sqService = new SqshService();
		
		String xn=CsszService.getPjzq().get("xn");
		String xq=CsszService.getPjzq().get("xq");
		
		//得到综测项目
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(csszModel.getXn(),csszModel.getXq());
		request.setAttribute("zcxmList", zcxmList);
		//得到评奖项目
		XmwhService xmwhService = new XmwhService();
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm(xn+xq,model.getBjdms());
		
		if("10279".equals(Base.xxdm)){
			List<HashMap<String,String>> newpjxmList=new ArrayList<HashMap<String,String>>();
			for(HashMap<String,String>map:pjxmList){
				if("bj".equals(map.get("rsfpfs"))){
					map.put("xmmc", map.get("xmmc")+"("+map.get("zzme")+")");
				}
				newpjxmList.add(map);
			}
			pjxmList=newpjxmList;
			request.setAttribute("engScoreList",sqService.getEngScore_10279(xn,xq,model.getBjdms()));
		}
		request.setAttribute("pjxmList", pjxmList);
		
		
		//得到所选班级学生综测分
		ZcfsService service = new ZcfsService();
		List<HashMap<String,String>> resultList = service.getZcfjgList(csszModel.getXn(),csszModel.getXq(),model.getBjdms());
		//得到各申报项目的最后级审核状态及岗位
		
		request.setAttribute("resultList", resultList);
		List<HashMap<String,String>> sqLists = sqService.getLastCheckStatus(model.getBjdms(), xn,xq);
		request.setAttribute("sqLists", sqLists);
		request.setAttribute("model", StringUtils.formatData(model));
		//浙江树人个性化设置
		if("11842".equals(Base.xxdm)){
			request.setAttribute("dgbkjd","true");
			request.setAttribute("bxkcjList", sqshService.getXsdmBxkMinCj(csszModel.getXn(),csszModel.getXq(), model.getBjdms()));
////			//构建验证list
//			ArrayList<Map<String, Object>> checklist = new ArrayList<Map<String,Object>>();
//			//遍历resultlist,进行每个学生的项目是否可以申请的验证
//			for (HashMap<String, String> hashMap : resultList) {
//				String xh = hashMap.get("xh");
//				Map<String,Object> resultMap = new SqshService().getXmsqInfoList(xh);
//				resultMap.put("xh", xh);
//				checklist.add(resultMap);
//				
//			}
//			request.setAttribute("checklist", checklist);
		}else{
			request.setAttribute("dgbkjd","false");
		}
		
		List<HashMap<String,String>> checkResultList = sqshService.getCheckResultList(csszModel.getXn(), csszModel.getXq(), model.getBjdms().split(","));
		request.setAttribute("checkResultList",checkResultList);
		return mapping.findForward("toCheckPjsq");
	}
	
	
	
	/**
	 * 
	 * @描述: 检测评奖条件
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午7:30:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward checkConditions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SqshModel model = (SqshModel) form;
		CpmdService service = new CpmdService();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		String[] bjdmArr = model.getBjdms().split(",");
		List<HashMap<String,String>> cpmdList = service.getCpmdList(bjdmArr, csszModel.getXn(), csszModel.getXq());
		if (cpmdList != null && !cpmdList.isEmpty()){
			CheckConditionsTask task = new CheckConditionsTask(cpmdList);
			
			TaskHandler handler = TaskHandler.getInstance();
			handler.startup(PJPY_KEY);
			handler.addTask(PJPY_KEY, model.getBjdms(), task);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 获取条件检测进度
	 * @作者：屈朋辉[工号：445]
	 * @日期：2016年10月14日 下午7:35:02
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
	public ActionForward getCheckProgress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SqshModel model = (SqshModel) form;
		TaskHandler handler = TaskHandler.getInstance();
		String progress = handler.getTaskProgress(PJPY_KEY, model.getBjdms());
		boolean isComplate = handler.isComplate(PJPY_KEY, model.getBjdms());
		response.getWriter().print(getJsonMessageResult(progress,isComplate));
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @描述:最后级的撤销
	 * @作者：945
	 * @日期：2013-12-5 上午09:24:58
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
		String splcid = request.getParameter("splcid");
		String shid = request.getParameter("shid");
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		// 业务回滚
		SqshService sqService = new SqshService();
		boolean result = sqService.cancel(splcid, shxx.get("ywid"),user);
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * 
	 * @描述: 选择上报奖项
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 下午05:19:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward selectSbjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhService xmwhService = new XmwhService();
		//评奖项目列表
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm();
		
		request.setAttribute("pjxmList", pjxmList);
		
		return mapping.findForward("selectSbjx");
	}


	/**
	 * 
	 * @描述: 修改申请表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 下午02:59:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"},rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		SqshModel model = service.getModel(sqshForm);
		
			if (model != null){
			BeanUtils.copyProperties(sqshForm, StringUtils.formatData(model));
			
			//学生基本信息
//			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
			User user = getUser(model.getSqr());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
			
			//学生参评班级
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			
			//评奖项目信息
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		}
		//中国美术学院个性化
		
		if("10355".equals(Base.xxdm)  ){
			XmwhModel xmForm = new XmwhService().getModel(sqshForm.getXmdm());
			request.setAttribute("hjjgxskg", xmForm.getSfkfhjqkxz());
			if(KFHJQKXZ.equals(xmForm.getSfkfhjqkxz())){
				request.setAttribute("hjjgList",new SqshService().getHjxxList(model.getXh(), model.getXn(),model.getXq()));
			}
			
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		return mapping.findForward("updateSqb");
	}


	/**
	 * 
	 * @描述: 保存修改
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 下午03:40:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-保存修改-XMDM:{xmdm},SQLY:{sqly}")
	public ActionForward saveUpdateSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		TjszService tjszServcie = new TjszService();
		if(SFBJPY_Y){
			// 评议小组人员不能申请！
			JglrService jglrService = new JglrService();
			HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(sqshForm.getXh());
			if(bjpyxzcyMap.get("xh") != null){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		boolean result= false;
		
		//如果提交，插入审核状态
		if("submit".equalsIgnoreCase(sqshForm.getType())||"tj".equalsIgnoreCase(sqshForm.getType())){
			
			//浙大个性化验证人数
			if(Globals.XXDM_ZJDX.equals(Base.xxdm)){
				HashMap<String,String> checkMap = checkRs(sqshForm);
				if("true".equals(checkMap.get("isXmrsOut"))){
					response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
					return null;
				}
				
			}
			// 学生申请提交
			if("tj".equalsIgnoreCase(sqshForm.getType())){
				String values = request.getParameter("values");
				sqshForm.setSqid(values);
			}
			
			if(SFBJPY_Y){
				sqshForm.setShzt(Constants.YW_BJPYZ);
			}else{
				sqshForm.setShzt(Constants.YW_SHZ);
			}
			
			// ========== 验证评奖条件 begin ============
			List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(sqshForm.getXmdm(), sqshForm.getXh());
			// 校验评奖条件，返回不符合条件的项目名称。
			CheckCondition checkCondition = new CheckStudentCondition();
			// 是否满足全部评奖条件
			boolean conditionsAllOk = checkCondition.checkConditionBoolean(sqshForm.getXh(), conditions);
			
			if(!conditionsAllOk){
				response.getWriter().print(getJsonMessage("不符合条件！请确认！"));
				return null;
			}
			// ========== 验证评奖条件 end ============
			
			result = service.runUpdate(sqshForm);
			
			XmwhDao xmwhDao = new XmwhDao();
			XmwhModel xmwhModel = xmwhDao.getModel(sqshForm.getXmdm());
			String splc = xmwhModel.getShlc();
			ShlcInterface shlc = new CommShlcImpl();
			if (!SFBJPY_Y && result) {
				result = shlc.runSubmit(sqshForm.getSqid(), splc,sqshForm.getXh(),"pj_jxsh.do","pj_pjxmsq.do");
			}
		}else{
			 result = service.runUpdate(sqshForm);
		}
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(sqshForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * @描述: 批量提交申请
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-批量提交申请-VALUES:{values}")
	public ActionForward saveUpdateSqbPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel sqshForm = (SqshModel) form;
		SqshService sqshService = new SqshService();
		TjszService tjszServcie = new TjszService();
		ShlcInterface shlcInterface = new CommShlcImpl();
		boolean result = true;
		boolean isXmrsOut=false;//人数超过上限
		String rsOutMsg = "";
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int okNum = 0;
			StringBuilder notOkStu = new StringBuilder(); // 不符合条件的学生
			HashMap<String, List<HashMap<String,String>>> notOkStuMap = new HashMap<String, List<HashMap<String,String>>>();
			List<HashMap<String,String>> dataList = sqshService.getPjxmXsxxList(values.split(","));
			//提交的各学院各项目人数
			List<HashMap<String,String>> xmrsList = sqshService.getPjxmRsxx(values.split(","));
			List<String> xzjxList = sqshService.getXzjx();
			String[] xmArr = new String[xmrsList.size()];
			for (int i = 0; i < xmrsList.size(); i++) {
				xmArr[i]=xmrsList.get(i).get("xmdm")+xmrsList.get(i).get("xydm");
			}
			//浙大个性化提交申请增加人数验证
			if(Globals.XXDM_ZJDX.equals(Base.xxdm)){
				for (int i = 0; i < xmrsList.size(); i++) {
					//提交人数
					if(xzjxList.contains(xmrsList.get(i).get("xmmc"))){
						String xmtjrs = xmrsList.get(i).get("xmtjrs");
						//项目已申请人数
						String ysqrs = sqshService.getYsqXs(xmArr[i]);
						//项目人数上限
						String rssx = sqshService.getPjxmRsxxsx(xmArr[i]);
						if(Integer.parseInt(ysqrs)+Integer.parseInt(xmtjrs)>Integer.parseInt(rssx)){
							isXmrsOut=true;
							rsOutMsg+=xmrsList.get(i).get("xmmc")+" 超过人数上限"+rssx+"人<br/>";
						}
					}
				}
				
				if(isXmrsOut){
					response.getWriter().print(getJsonMessage(rsOutMsg));
					return null;
				}
			}
			//查询提交项目人数
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> dataMap = dataList.get(i);
				String sqid = dataMap.get("sqid");
				String xmdm = dataMap.get("xmdm");
				String xmmc = dataMap.get("xmmc");
				String xh = dataMap.get("xh");
				String xm = dataMap.get("xm");
				String splc = dataMap.get("shlc");
				
				if(SFBJPY_Y){
					// 评议小组人员不能申请！
					JglrService jglrService = new JglrService();
					HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(xh);
					if(bjpyxzcyMap.get("xh") != null){
						response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
						return null;
					}
				}
				
				// ========== 验证评奖条件 begin ============
				List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(xmdm, xh);
				// 校验评奖条件，返回不符合条件的项目名称。
				CheckCondition checkCondition = new CheckStudentCondition();
				// 是否满足全部评奖条件
				boolean conditionsAllOk = checkCondition.checkConditionBoolean(xh, conditions);
				// ========== 验证评奖条件 end ============
				// ========== 不可兼得奖项 begin ============
				JdszService jdszService = new JdszService();
				List<HashMap<String, String>> bjdxmList = jdszService.getBjdxm(xmdm);
				if(conditionsAllOk && bjdxmList.size() > 0){
					// =========== 已申请XX，不能再申请该奖项 begin ==========
					Map<String,Object> xmsqInfoMap = sqshService.getXmsqInfoList(xh,xzdm);
					List<HashMap<String, String>> ysqList = (List<HashMap<String, String>>) xmsqInfoMap.get("ysqList");
					if(ysqList != null && ysqList.size() > 0){
						for (HashMap<String, String> bjdxmMap : bjdxmList) {
							for (HashMap<String, String> ysqMap : ysqList) {
								if(bjdxmMap.get("xmdm").equals(ysqMap.get("xmdm"))){
									conditionsAllOk = false;
									break;
								}
							}
							if(!conditionsAllOk){
								break;
							}
						}
					}
					// =========== 已申请XX，不能再申请该奖项 end ==========
					// =========== 该奖项与XX不能同时申请 begin ==========
					if(conditionsAllOk){
						for (int j = i + 1; j < dataList.size(); j++) {
							HashMap<String, String> dataJ = dataList.get(j);
							String xhJ = dataJ.get("xh");
							String xmdmJ = dataJ.get("xmdm");
							if(xh.equals(xhJ)){
								for (HashMap<String, String> bjdxmMap : bjdxmList) {
									if(bjdxmMap.get("xmdm").equals(xmdmJ)){
										conditionsAllOk = false;
										break;
									}
								}
								if(!conditionsAllOk){
									break;
								}
							}
						}
					}
					// =========== 该奖项与XX不能同时申请 end ==========
				}
				// ========== 不可兼得奖项 end ============
				
				
				if(conditionsAllOk){
					sqshForm.setSqid(sqid);
					if(SFBJPY_Y){
						sqshForm.setShzt(Constants.YW_BJPYZ);
					}else{
						sqshForm.setShzt(Constants.YW_SHZ);
					}
					result = sqshService.runUpdate(sqshForm);
					if (!SFBJPY_Y && result) {
						result = shlcInterface.runSubmit(sqid,splc,xh,"pj_jxsh.do","pj_pjxmsq.do");
					}
					if (result) {
						okNum++;
					}
				}else{
					HashMap<String,String> mapTemp = new HashMap<String,String>();
					mapTemp.put("xh", xh);
					mapTemp.put("xm", xm);
					mapTemp.put("xmmc", xmmc);
					List<HashMap<String,String>> listTemp = notOkStuMap.get(xmdm);
					if(listTemp == null){
						listTemp = new ArrayList<HashMap<String,String>>();
						notOkStuMap.put(xmdm, listTemp);
					}
					listTemp.add(mapTemp);
				}
			}
			Set<String> keys = notOkStuMap.keySet();
			for(String key : keys){
				List<HashMap<String,String>> temp = notOkStuMap.get(key);
				for (int i = 0; i < temp.size(); i++) {
					if(i == 0){
						notOkStu.append(temp.get(0).get("xmmc") + "：<br/>");
					}
					notOkStu.append(temp.get(i).get("xh") + " " + temp.get(i).get("xm") + "<br/>");
				}
			}
			String resultMsg = "提交成功"+okNum+"条！";
			if(dataList.size() - okNum > 0){
				resultMsg += "不符合条件的记录：<br/>" + notOkStu.toString().substring(0, notOkStu.toString().length() - 1);
			}
			String message = result ? resultMsg : MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:评奖申请的撤销
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-16 上午10:29:01
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-撤销VALUES：{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshService service = new SqshService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		if(!SFBJPY_Y){
			result = service.cancleRecord(values,lcid);
		}
		if(result){
			//更新业务状态为'未提交'
			SqshModel model = new SqshModel();
			model.setSqid(values);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
			
			if(SFBJPY_Y){
				SqshModel modelNew = service.getModel(model);
				JgcxDao jgcxDao = new JgcxDao();
				String sqid = modelNew.getSqid();
				String xn = modelNew.getXn();
				String xq = modelNew.getXq();
				String xmdm = modelNew.getXmdm();
				String sqr = modelNew.getXh();
				// 更新班级评议表
				boolean rs = jgcxDao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				if(rs){
					// 更新结果查询表
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(sqid);
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
//	//工作流提交
//	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		SqshModel sqshForm = (SqshModel) form;
//		SqshService service = new SqshService();
//		
//		sqshForm.setShzt(Constants.YW_SHZ);//审核中
//		
//		
//		YdsqService service = new YdsqService();
//		String values = request.getParameter("values");
//		String lcid = request.getParameter("lcid");
//		String ydlx = request.getParameter("ydlx");
//		String xh = request.getParameter("xh");
//		boolean result = service.submitRecord(ydlx,values,lcid,xh);
//		if(result){
//			//更新业务状态为'审核中'
//			YdsqForm model = new YdsqForm();
//			model.setSsydsqid(values);
//			model.setShzt(Constants.YW_SHZ);
//			service.updateModel(model);
//		}
//		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
//				: MessageKey.SYS_SUBMIT_FAIL;
//		response.getWriter().print(getJsonMessageByKey(messageKey));
//		return null;
//	}
	
	
	/**
	 * 
	 * @描述: 查看申请表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 下午02:59:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"})
	public ActionForward viewSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		SqshModel model = service.getModel(sqshForm);
		
		if (model != null){
			BeanUtils.copyProperties(sqshForm, StringUtils.formatBean(model));
			
			//学生基本信息
//			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
//				xsxxService.getXsjbxxMore(model.getXh());
			User user = getUser(model.getSqr());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
			
			//学生参评班级
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			
			//评奖项目信息
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			//审核记录
			List<HashMap<String,String>> spjlList = service.getSpjlList(model.getSqid());
			request.setAttribute("spjlList", spjlList);
			
			//条件
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
			
			//校验条件，返回校验结果
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
			request.setAttribute("checkResult", results);
			
			request.setAttribute("mkxxForm", StringUtils.formatData(model));
			//中国美术学院
			if("10355".equals(Base.xxdm)){
				XmwhModel xmwhForm = new XmwhService().getModel(model.getXmdm());
				request.setAttribute("hjjgxskg", xmwhForm.getSfkfhjqkxz());
				if("1".equals(xmwhForm.getSfkfhjqkxz())){
					request.setAttribute("hjjgList", new SqshService().getHjxxList(model.getXh(),model.getXn(), model.getXq()));
				}
			}
		}
		
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		
		return mapping.findForward("viewSqb");
	}

	
	/**
	 * 
	 * @描述: 填写申请表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 下午04:02:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"},rewritable=ReadWrite.WRITEABLE)
	public ActionForward editSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		User user = getUser(request);
		
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : sqshForm.getXh();
		
		if (!StringUtil.isNull(xh)){
			//学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());

			request.setAttribute("jbxx", xsjbxx);
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		request.setAttribute("pjzq", CsszService.getPjzq().get("pjzqmc"));
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("path", "xpj_sqsh.do?method=editSqb&xzdm="+sqshForm.getXzdm());
		request.setAttribute("xh", xh);
		if (!StringUtil.isNull(xh)){
			//学生参评班级信息
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String,String> cpbjxx = zcfsService.getCpbjListByXh(xh, csszModel.getXn(), csszModel.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
		}
		this.saveToken(request);
		request.setAttribute("xzdm",sqshForm.getXzdm());
		return mapping.findForward("editSqb");
	}
	
	
	/**
	 * 
	 * @描述: 选择评奖项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-31 下午04:36:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	
	public ActionForward selectPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		xzdm = sqshForm.getXzdm();
		SqshService service = new SqshService();
		
		Map<String,Object> resultMap = service.getXmsqInfoList(sqshForm.getXh(),xzdm);
		
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("selectPjxm");
	}
	
	
	/**
	 * 
	 * @描述: 奖项申请保存
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-1 上午09:59:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-申请页面-保存XMDMS:{xmdms}XH:{xh},SQLY:{sqly}")
	public ActionForward saveJxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		if(SFBJPY_Y){
			// 评议小组人员不能申请！
			JglrService jglrService = new JglrService();
			HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(sqshForm.getXh());
			if(bjpyxzcyMap.get("xh") != null){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		
		String[] xmdm = request.getParameterValues("xmdms");
		//浙大个性化验证人数
		if(Globals.XXDM_ZJDX.equals(Base.xxdm)&&"submit".equals(sqshForm.getType())){
			HashMap<String,String> checkMap = new HashMap<String,String>();
			for (int i = 0; i < xmdm.length; i++) {
				sqshForm.setXmdm(xmdm[i]);
				checkMap = checkRs(sqshForm);
			}
			
			if("true".equals(checkMap.get("isXmrsOut"))){
				response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
				return null;
			}
			
		}
		if("dgtj".equals(request.getParameter("flag"))){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			boolean result = service.checkIsNotbkjd(xmdm[0], csszModel.getXn(), csszModel.getXq(), sqshForm.getXh());
			if(!result){
				String bkjdmc = service.getbkjdMc(xmdm[0]);
				String message = "该项目与["+bkjdmc+"]不可兼得！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		User user = getUser(request);
		sqshForm.setSqr(user.getUserName());
		boolean result = service.saveJxsq(xmdm, sqshForm);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(sqshForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 取消申请
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-1 上午11:34:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-删除VALUES：{values}")
	public ActionForward cancelXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshService service = new SqshService();
		
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// 删除
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
	 * @描述: 审核情况统计 
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-2 上午08:46:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 * @deprecated
	 */
	public ActionForward viewShqkList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		if (QUERY.equals(sqshForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			sqshForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> resultList = service.getShqkList(sqshForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//审批岗位列表
		List<HashMap<String,String>> spgwList = service.getSpgwList(user);
		
		if (spgwList == null || spgwList.isEmpty()){
			throw new SystemException(MessageKey.PJPY_AUDING_QXBZ);
		}
		
		request.setAttribute("spgwList", spgwList);
		request.setAttribute("path", "pj_jxsh.do");
		FormModleCommon.commonRequestSet(request);
		
		//参数设置信息
		CsszModel csszModel = new CsszService().getModel();
		request.setAttribute("csszModel", csszModel);
		//高级查询配置标识
		request.setAttribute("path", service.getShqkSearchPath(sqshForm));
		return mapping.findForward("viewShqkList");
	}
	

	/**
	 * 
	 * @描述: 选择统计单位
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-2 上午11:42:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward selectTjdw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		OptionUtil optionUtil = new OptionUtil();
		List<HashMap<String,String>> tjdwList = optionUtil.getOptions(OptionUtil.PJPY_TJDW);
		
		request.setAttribute("tjdwList", tjdwList);
		return mapping.findForward("selectTjdw");
	}


	/**
	 * 
	 * @描述: 审核详细页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:33:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = {"pj_jxsh.do","xpj_sqsh.do?method=viewJxshList&xzdm=2&sindex=1"})
	public ActionForward viewJxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		String shid = sqshForm.getShid(); 
		
		SqshModel model = service.getModel(sqshForm);
		model.setGwid(sqshForm.getGwid());
		if (null != model && !StringUtil.isNull(model.getXh())){
			//学生基本信息
//			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
			User user = getUser(model.getSqr());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
			
			//加载参评班级信息
			
			
			//加载综测成绩
			ZcfsService zcfsServcie = new ZcfsService();
			List<HashMap<String,String>> zcfsList = zcfsServcie.getZcfListByXh(model.getXh(), model.getXn(), model.getXq());
			HashMap<String, String> cpbjxx = zcfsServcie.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			request.setAttribute("zcfsList", zcfsList);
	        if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)){
	        	List<HashMap<String,String>> zcpmAll = zcfsServcie.getAllZcpmWithXh(model.getXh());
	        	request.setAttribute("zcpmAll", zcpmAll);
			}

			// 根据学号查询学籍异动列表
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(model.getXh());
			request.setAttribute("xjydList", xjydList);

			//加载评奖项目信息
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			
			//加载最新审核通过项目名称
			XmwhModel dqxmwhModel = xmwhService.getModel(model.getDqxmdm());
			request.setAttribute("dqxmwhModel", dqxmwhModel);
			
			//加载审批记录
			List<HashMap<String,String>> spjlList = service.getSpjlList(model.getSqid());
			request.setAttribute("spjlList", spjlList);
			
			//可退回岗位
			ShlcInterface shlcService = new CommShlcImpl();
			List<HashMap<String,String>> kthGwList = shlcService.getKthSpgw(model.getSplc(), sqshForm.getShid());
			request.setAttribute("kthGwList", kthGwList);
			//可调整奖项
			TzszService tzszService = new TzszService();
			List<HashMap<String,String>> ktzPjxmList = tzszService.getKtzPjxmList(model.getDqxmdm());
			request.setAttribute("ktzPjxmList", ktzPjxmList);
			
			//条件
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
			
			//校验条件，返回校验结果
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
			request.setAttribute("checkResult", results);
			
			
			BeanUtils.copyProperties(sqshForm, StringUtils.formatBean(model));
			sqshForm.setShid(shid);
			request.setAttribute("mkxxForm", model);
			//中国美术学院个性化
			
			if("10355".equals(Base.xxdm)  ){
				XmwhModel xmForm = new XmwhService().getModel(sqshForm.getXmdm());
				request.setAttribute("hjjgxskg", xmForm.getSfkfhjqkxz());
				if(KFHJQKXZ.equals(xmForm.getSfkfhjqkxz())){
					request.setAttribute("hjjgList",new SqshService().getHjxxList(model.getXh(), model.getXn(),model.getXq()));
				}
				
			}
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		this.saveToken(request);
		return mapping.findForward("viewJxsh");
	}

	
	
	/**
	 * 
	 * @描述: Ajax验证项目条件
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-13 上午10:53:33
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
	public ActionForward checkCondition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		//条件
		TjszService tjszService = new TjszService();
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(sqshForm.getXmdm(),sqshForm.getXh());
		
		//校验条件，返回校验结果
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> results = check.checkCondition(sqshForm.getXh(), conditions);
		
		JSONArray json = JSONArray.fromCollection(results);
		response.getWriter().print(json);
		
		return null;
	}
	
	/** 
	 * @描述:获取项目金额
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-25 上午09:07:40
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
	public ActionForward getXmje(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		SqshModel sqshForm = (SqshModel) form;
		String xmje = new SqshService().getXmje(sqshForm.getXmdm());
		JSONObject json = JSONObject.fromString("{xmje:"+xmje+"}");
		response.getWriter().print(json);		
		return null;
	}
	

	/**
	 * 
	 * @描述: 保存审核操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午10:50:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-我的评奖-奖项审核-保存审核-SQID：{sqid}")
	public ActionForward runAuding(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		String isSuccess = service.runAuding(sqshForm,user);
		String messageKey = "";
		if(isSuccess.equals("true")){
			messageKey = MessageKey.SYS_AUD_SUCCESS;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else if(isSuccess.equals("false")){
			messageKey = MessageKey.SYS_AUD_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			messageKey = isSuccess;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", messageKey);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
		}
		
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 撤消审核操作
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 上午11:12:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问评奖评优-我的评奖-奖项审核-保存撤销-SQID：{sqid}")
	public ActionForward runCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		boolean isSuccess = service.runCancel(sqshForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * 
	 * @描述: 流程跟踪
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-8 上午10:13:17
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
	public ActionForward viewLcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		//单个查询
		SqshModel model = service.getModel(sqshForm);
		
		if (model != null){
			BeanUtils.copyProperties(sqshForm, model);
		}
		
		//审批记录
		List<HashMap<String,String>> spjlList = service.getSpjlList(sqshForm.getSqid());
		request.setAttribute("spjlList", spjlList);
		request.setAttribute("spjlsize", spjlList.size());
		
		//审批岗位
		List<HashMap<String,String>> spgwList = ShlcUtil.getSpbzBySplc(model.getSplc());
		request.setAttribute("spgwList", spgwList);
		
		//待审核岗位
		request.setAttribute("dshGwid", service.getDsgw(sqshForm.getSqid()));
		return mapping.findForward("viewLcgz");
	}


	/**
	 * 
	 * @描述: 展示审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-12 上午11:38:22
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
	public ActionForward viewShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		RsszService rsszService = new RsszService();
		request.setAttribute("rsfpfs", rsszService.getRsfpfs(sqshForm.getXmdm()));

		if (StringUtils.isNotNull(sqshForm.getXmdm())) {
			Map<String,Object> shqkInfo = service.getShqkInfo(user,sqshForm.getXmdm());

			request.setAttribute("shqkInfo", shqkInfo);
		}
		return mapping.findForward("viewShqk");
	}
	
	
	/**
	 * 
	 * @描述: 评奖项目审核统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-12 上午11:41:49
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
	public ActionForward pjxmShtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhService xmwhService = new XmwhService();
		
		List<HashMap<String,String>> pjxmList = xmwhService.getOthers(" ",xzdm);

		request.setAttribute("pjxmList", pjxmList);
		return mapping.findForward("pjxmShtj");
	}




	/**
	 * 
	 * @描述: 审核统计学生列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-12 下午02:28:43
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
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		if (QUERY.equalsIgnoreCase(sqshForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			sqshForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getStudentsFromShtj(sqshForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "pj_shtj_students.do");
		return mapping.findForward("studentsList");
	}
	
	
	/**
	 * 
	 * @描述:评奖审核导出
	 * @作者：cq [工号：785]
	 * @日期：2013-10-26 上午11:14:48
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
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAudingExportList(model,
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
	 * @描述:评奖项目申请导出
	 * @作者：cq [工号：785]
	 * @日期：2013-10-26 下午12:29:43
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
	public ActionForward sqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getSqExportList(model,
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
	 * @描述:批量审核页
	 * @作者：cq [工号：785]
	 * @日期：2013-12-9 上午10:57:33
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
	public ActionForward toPlshy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		//得到评奖项目
		XmwhService xmwhService = new XmwhService();
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm(model.getXn()+model.getXq());
		request.setAttribute("pjxmList", pjxmList);
		request.setAttribute("model", model);
		return mapping.findForward("pjshview");
	}
	
	public ActionForward toPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		SqshModel model = (SqshModel) form;
//		request.setAttribute("model", model);
		request.setAttribute("xxdm",Base.xxdm);
		return mapping.findForward("pjshview_new");
	}
	
	
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel myForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		String message = service.batchSave(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:保存批量审核
	 * @作者：cq [工号：785]
	 * @日期：2013-12-9 上午10:57:58
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项批量审核-保存-XMDMS:{xmdms}")
	public ActionForward doPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel myForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:评奖申请
	 * @作者：cq[工号：785]
	 * @日期：2013-12-10 下午01:47:31
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
	public ActionForward viewJxsqList_new(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getXssqList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "pj_jxsq_new.do");
		FormModleCommon.commonRequestSet(request);
		//参数设置
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		
		return mapping.findForward("viewJxsqList_new");
	}
	
	/**
	 * 
	 * @描述:批量上报奖项选择
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-10 下午07:24:39
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
	public ActionForward plSelectPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		Map<String,Object> resultMap = service.getXmsqInfoList(sqshForm.getXh(),xzdm);
		
		request.setAttribute("xh", sqshForm.getXh());
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("plSelectPjxm");
	}
	
	
	/**
	 * 
	 * @描述:奖项审核撤销
	 * @作者：cq 工号：785
	 * @日期：2013-12-16 下午02:49:46
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项审核-撤销保存-SHID:{shid}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshService sqService = new SqshService();
		//SqshModel model = (SqshModel) form;
		SqshModel model = new SqshModel();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("splc");
		String xmdm = request.getParameter("xmdm");
		String xh = request.getParameter("xh");
		model.setSplc(splc);
		model.setShid(shid);
		model.setXmdm(xmdm);
		model.setXh(xh);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = sqService.cxshnew(shxx.get("ywid"),model,user);
		

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
	 * @描述:浙江大学个性化修改
	 * @作者：cq [工号：785]
	 * @日期：2015-5-25 下午06:47:36
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
	public ActionForward zjdxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("jsonStr", request.getParameter("jsonStr"));
		return mapping.findForward("zjdxPlsh");
	}
	
	/**
	 * 
	 * @描述:浙江理工个性化——审核明细
	 * @作者：cq [工号：785]
	 * @日期：2015-5-26 下午03:56:21
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
	public ActionForward viweShmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> viweShmx = service.getViweShmx(model,user);
		JSONArray dataList = JSONArray.fromObject(viweShmx);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	public ActionForward saveZdPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel myForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		System.out.println("结束时间=================="+System.currentTimeMillis());
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		String message = service.saveZdPlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		System.out.println("结束时间=================="+System.currentTimeMillis());
		return null;
	}
	

	public ActionForward viewAllZcpm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		//学生基本信息
//		XsxxService xsxxService = new XsxxService();
//		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
		request.setAttribute("jbxx", xsjbxx);
		
		ZcfsService zcfsServcie = new ZcfsService();
		List<HashMap<String,String>> zcpmAll = zcfsServcie.getAllZcpmWithXh(model.getXh());
		
    	request.setAttribute("zcpmAll", zcpmAll);
    	request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewAllzcpm");
	}
	
	private HashMap<String, String> checkRs(SqshModel sqshForm) throws Exception {
		// 参数设置信息
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		sqshForm.setXn(csszModel.getXn());
		sqshForm.setXq(CsszService.getPjzq().get("xq"));
		String isXmrsOut = "false";
		String rsOutMsg = "";
		HashMap<String, String> checkMap = new HashMap<String, String>();
		SqshService sqshService = new SqshService();
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> xmMap = xmwhService.getDataById(sqshForm.getXmdm());
		HashMap<String, String> xsxxMap = sqshService.getPjxmXsxxMap(sqshForm);
		List<String> xzjxList = sqshService.getXzjx();
		
			if (xzjxList.contains(xmMap.get("xmmc"))) {
				// 项目已申请人数
				String xmxx=xmMap.get("xmdm")+xsxxMap.get("xydm");
				String ysqrs = sqshService.getYsqXs(xmxx);
				// 项目人数上限
				String rssx = sqshService.getPjxmRsxxsx(xmxx);
				if (Integer.parseInt(ysqrs) + 1 > Integer.parseInt(rssx)) {
					isXmrsOut = "true";
					rsOutMsg += xmMap.get("xmmc") + " 超过人数上限" + rssx + "人<br/>";
				}
			}
		
		checkMap.put("isXmrsOut", isXmrsOut);
		checkMap.put("rsOutMsg", rsOutMsg);
		return checkMap;
	}
	
	//集体评奖项目选择验证
	public ActionForward selectJtpjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		String bjdm = request.getParameter("bjdm");
		User user = getUser(request);
		TjszService tjszServcie = new TjszService();
		List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(xmdm, bjdm);
		// 校验条件，返回不符合条件的项目名称。
		CheckCondition check = new CheckStudentCondition();
		// 校验结果
		List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		if ("10466".equals(Base.xxdm)) {
			results = check.checkCondition(bjdm+"&&"+user.getUserName(),
					conditions);
		}else {
			results = check.checkCondition(bjdm,conditions);
		}
	   JSONArray json = JSONArray.fromObject(results);
	   response.getWriter().print(json);
	   return null;
	}
	
	/**
	 * 
	 * @描述:批量上报奖项选择
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-10 下午07:24:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public void checkIsExists(HttpServletRequest request)
			throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		String xh = (String)request.getAttribute("xh");
		String xmdm = (String)request.getAttribute("xmdm");
		Map<String,Object> resultMap = new SqshService().getXmsqInfoList(xh,xzdm);
		List<HashMap<String, String>> wsqlist = (List<HashMap<String, String>>) resultMap.get("wsqList");
		for (HashMap<String, String> hashMap : wsqlist) {
			if(xmdm.equals(hashMap.get("xmdm"))){
				result = hashMap;
			}
		}
		request.setAttribute("result", result);
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
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		File excelFile = service.getHzbPrint(searchModel);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	
	/**
	 * @描述:浙江同济科技职业学院，学生成绩汇总导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月11日 下午3:40:39
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
	public ActionForward xscjhzdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXscjhzList(model, user);//查询出所有记录，不分页
		
		File file = service.getXscjhzFile(resultList);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述:浙江同济科技职业学院，学生成绩汇总查看
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月11日 下午3:40:39
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
	public ActionForward xscjhzck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXscjhzList(model, user);//查询出所有记录，不分页
		
		Map<String,ClassSummary> classMap = new ResultSummary().setResultMap("bjdm", resultList).getResultMap();
		request.setAttribute("classMap", classMap);
		return mapping.findForward("xscjhzShow");
	}
	
	/** 
	 * @描述:附件打包导出(上海戏剧个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-19 下午05:25:23
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
	public ActionForward fjdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		List<File> files = service.getFjList(model, user);
		if(files.size()>0){
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			FileUtil.delFolder(System.getProperty(Temp) + "\\" + PJPY_KEY);
		}
		return null;
	}
	
	/** 
	 * @描述:验证是否有导出的附件(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-22 下午01:52:57
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
	public ActionForward checkFjExist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<File> files = service.getFjList(model, user);
		if(files.size()<1){
			response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_SHXJ_NOATTACHMENT)); 
		}else{
			response.getWriter().print(true);
		}
		return null;
	}

	/**
	 *  评奖评优奖项申请提交数据异常处理.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 17:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward exceptionDataResolve(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response) throws Exception {

		SqshService service = new SqshService();
		Map<String, String> map = service.exceptionDataResolve();

		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @description	： 学生各类获奖名单导出（南京高等职业技术学校）
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-5 下午04:05:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsglmddc(ActionMapping mapping, ActionForm form,
			  HttpServletRequest request, HttpServletResponse response) throws Exception {
		SqshModel model = (SqshModel) form;
		SearchModel searchModel = model.getSearchModel();
		SearchModel sm = new SearchModel();
		
		sm.setSearch_tj_xn(searchModel.getSearch_tj_xn());//学年查询条件
		sm.setSearch_tj_xq(searchModel.getSearch_tj_xq());//学期查询条件
		sm.setSearch_tj_xy(searchModel.getSearch_tj_xy());//学院查询条件
		sm.setPath(searchModel.getPath());
		model.setSearchModel(sm);
		
		SqshService service = new SqshService();
		List<File> files = service.getXsglmc(model);//获取学生表彰名单exl文件
		if(files.size()>1){
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);					
		}else if(files.size()==1){
			FileUtil.outputExcel(response, files.get(0));
		} 
		return null;
	}
	
	/**
	 * @description	： 验证是否有数据可以导出(南京高等职业技术学院)
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-9 下午02:27:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzxsglmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SqshModel model = (SqshModel) form;
		SearchModel searchModel = model.getSearchModel();
		SearchModel sm = new SearchModel();
		
		sm.setSearch_tj_xn(searchModel.getSearch_tj_xn());//学年查询条件
		sm.setSearch_tj_xq(searchModel.getSearch_tj_xq());//学期查询条件
		sm.setSearch_tj_xy(searchModel.getSearch_tj_xy());//学院查询条件
		sm.setPath(searchModel.getPath());
		model.setSearchModel(sm);
		
		SqshService service = new SqshService();
		boolean result = service.yzxsglmddc(model);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject fromBean = JSONObject.fromBean(map);
		response.getWriter().print(fromBean);
		return null;
	}
	public ActionForward sqlyPdf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("sqlyPdf");
	}
	
	/**
	 *
	 * @描述: 是否开启奖项申请(中国美术学院个性化)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-16 上午11:10:55
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
	public ActionForward checkHjsqCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		XmwhModel model = new XmwhService().getModel(xmdm);
		String checkResult = "false";
		if(model != null && StringUtils.isNotNull(model.getSfkfhjqkxz()) && KFHJQKXZ.equals(model.getSfkfhjqkxz())){
			checkResult = "true";
		}
		response.getWriter().print(getJsonMessage(checkResult));
		return null;
	}
	
	/**
	 * 
	 * @描述: 获奖结果add
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-16 下午01:48:22
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
	public ActionForward getHjjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xpj_sqsh.do?method=getHjjgAdd");
		request.setAttribute("xh",request.getParameter("xh"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hjjgadd");
	}
	
	/**
	 * 
	 * @描述: 查询获奖结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-16 下午02:13:52
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
	public ActionForward searchHjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		HjjgForm hjForm = new HjjgForm();
		BeanUtils.copyProperties(hjForm, model);
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setInput_xh(model.getXh());
		hjForm.setSearchModel(searchModel);  
		User user = getUser(request);
		HjjgService service = new HjjgService();
		// 查询
		List<HashMap<String, String>> resultList = service.getHjxxPageList(hjForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存获奖信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-19 上午11:30:41
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
	public ActionForward saveHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		boolean rs = new SqshService().saveHjxx(model);
		JSONObject jsonRs = getJsonMessage(rs+"");
		if(rs){
			CsszModel cssz = new CsszService().getModel();
			List<HashMap<String,String>> rsList = new SqshService().getHjxxList(model.getXh(), cssz.getXn(),cssz.getXq());
			jsonRs.put("rsList", JSONArray.fromObject(rsList));
		}
		response.getWriter().print(jsonRs);
		return null;
		
	}
	public ActionForward delHjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获得id
		String values = request.getParameter("values");
		HjjgService service = new HjjgService();
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = service.delHjxx(ids);
			SqshModel model = (SqshModel) form;
			JSONObject jsonRs = getJsonMessage(result+"");
			if(result){
				CsszModel cssz = new CsszService().getModel();
				List<HashMap<String,String>> rsList = new SqshService().getHjxxList(model.getXh(), cssz.getXn(),cssz.getXq());
				jsonRs.put("rsList", JSONArray.fromObject(rsList));
			}
			response.getWriter().print(jsonRs);
			/*String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));*/
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @描述: 加载获奖信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-20 上午10:13:17
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
	public ActionForward loadHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("hjjgList",new SqshService().getHjxxList(request.getParameter("xh"), csszModel.getXn(),csszModel.getXq()));
		return mapping.findForward("hjxx");
	}
	
	/**
	 * 
	 * @描述: 获取项目是否开启获奖信息查看开关
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-21 上午09:27:39
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
	public ActionForward getSfkqHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		XmwhModel model = new XmwhService().getModel(xmdm);
		boolean rs = (model != null && StringUtils.isNotNull(model.getSfkfhjqkxz()) && KFHJQKXZ.equals(model.getSfkfhjqkxz())) ? true : false;
		response.getWriter().print(getJsonMessage(rs+""));
		return null;
	}
}

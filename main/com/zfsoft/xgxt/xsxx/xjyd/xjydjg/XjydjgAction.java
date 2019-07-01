package com.zfsoft.xgxt.xsxx.xjyd.xjydjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;

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
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-学籍异动
 * @类功能描述:学籍异动审核
 * @作者： qilm
 * @时间： 2013-9-27
 * @版本： V1.0
 */
public class XjydjgAction extends SuperAction {
	
	/**
	 * 定义公寓管理学籍异动可以从基本信息表中获取学生信息
	 */
	private static final String XJYDSQ = "xjydsq";
	
	/**
	 * 东北石油大学特殊班级调整的异动类别
	 */
	public static final String DBSYDX_TSBJTZ      = "99";
	
	private XjydjgService service = new XjydjgService();

	private XsxxService xsxxService = new XsxxService();
	
	private static List<HashMap<String, String>> jbxxList = null;

	private BdpzService bdpzService = new BdpzService();
	
	private static final String url = "xjyd_xjydjg.do";
	
	/**
	 * @描述:学籍异动审核列表
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
	public ActionForward xjydjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydjgForm myForm = (XjydjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_xjydjg.do");
			myForm.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xjyd_xjydjg.do");

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xxdm", Base.xxdm);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydjgList");
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
		
		XjydjgForm myForm = (XjydjgForm) form;
		
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		// 学生最近的一次学籍异动信息
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", StringUtils.formatData(xsydInfo));

		// 学生最近的更多学籍异动信息
		List<HashMap<String, String>> xsYdList = service.getXsydList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("ckXsydInfo");
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
	@SystemLog(description="访问学生信息-学籍异动-学籍异动结果-增加:XH:{xh},XN:{xn},XQ:{xq},ydlbdm:{ydlbdm},XJYDSJ:{xjydsj},XJYDWH:{xjydwh}")
	public ActionForward xjydjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydjgForm myForm = (XjydjgForm) form;
		User user = getUser(request);
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			boolean isExist = !"".equals(service.queryExistId(myForm, "add"));
			if (isExist) {
				response.getWriter().print(getJsonMessage("学生的学籍异动文号已存在！"));
				return null;
			}

			String guid = UniqID.getInstance().getUniqIDHash();
			myForm.setXjydjgid(guid);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			myForm.setJrsj(date);
			myForm.setSqr(user.getUserName());

			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());

			myForm.setYdqnj(xsjbxx.get("nj"));
			myForm.setYdqxydm(xsjbxx.get("xydm"));
			myForm.setYdqzydm(xsjbxx.get("zydm"));
			myForm.setYdqbjdm(xsjbxx.get("bjdm"));
			myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//学籍类别代码
			myForm.setYdqxjlbmc(xsjbxx.get("xjlbmc"));//学籍类别
			myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//是否有学籍
			myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//是否在校

			myForm.setYdqzybjdm(xsjbxx.get("zybj"));//专业班级
			myForm.setYdqsydm(xsjbxx.get("sydm"));//书院


			//华中师范大学个性化字段（是否师范生）
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				myForm.setSfsfs(xsjbxx.get("sfsfs"));//是否师范生
			}
			
			//数据来源
			myForm.setSjly(Constants.SJLY_JGK);

			// 学校代码
			String xxdm = Base.xxdm;	
			
			// 東北石油大學特殊判斷（是否為特殊異動类别调整【只调班级不调专业】）
			if(DBSYDX_TSBJTZ.equals(myForm.getYdlbdm()) &&  Globals.XXDM_DBSYDX.equals(xxdm)){
				
				// 专业学院不变
				myForm.setYdhxydm(xsjbxx.get("xydm"));
				myForm.setYdhzydm(xsjbxx.get("zydm"));
				
			}
			
			boolean result = service.runInsert(myForm);
			if(result){
				result = service.updateXsxx(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			String message =  MessageUtil.getText(messageKey);
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			
			if("12309".equalsIgnoreCase(Base.xxdm)) {//查询床位信息，武昌首义个性化
				CwglService cwglService=new CwglService();
				Map<String, String> cwglmap = cwglService.getXsxx(myForm.getXh(),request);
				if(cwglmap.get("xh") != null){
					map.put("sfycw", "y");
				}
			}
			
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}

		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}else{
			HashMap<String,String> xsjbxx = new HashMap<String,String>();
			xsjbxx.put("xjlb", "");
			xsjbxx.put("xjztm", "");
			xsjbxx.put("sfzx", "");			
			xsjbxx.put("nj", "");
			xsjbxx.put("xymc", "");
			xsjbxx.put("zymc", "");
			xsjbxx.put("bjmc", "");
			request.setAttribute("jbxx", xsjbxx);
		}
		
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// 学生基本信息
		String path = "xjydjg.do?method=xjydjgAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//学籍类别LIST
		XjydService serviceYd = new XjydService();
		List<HashMap<String,String>> xjlbList = serviceYd.getXjlbList("0","");
		request.setAttribute("xjlbList", xjlbList);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//异动原因
			XjydsqService xjydsqService = new XjydsqService();
			List<HashMap<String,String>> ydyyList = xjydsqService.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//维护来源学校/去向学校
			List<HashMap<String,String>> lyqxxxList = xjydsqService.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xxdm", Base.xxdm);

		FormModleCommon.commonRequestSet(request);
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);
		this.saveToken(request);
		return mapping.findForward("xjydjgAdd");
	}

	/**
	 * 
	 * @描述: 修改加異動結果
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-3 下午03:28:16
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
	@SystemLog(description="访问学生信息-学籍异动-学籍异动结果-修改XJYDJGID:{xjydjgid},XH:{xh},XN:{xn},XQ:{xq},YDLBDM:{ydlbdm},XJYDSJ:{xjydsj},XJYDWH:{xjydwh}")
	public ActionForward xjydjgUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydjgForm myForm = (XjydjgForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = !"".equals(service.queryExistId(myForm, "update"));
			if (isExist) {
				response.getWriter().print(getJsonMessage("学生的学籍异动文号已存在！"));
				return null;
			}

			// 学籍异动结果历史取得
			XjydjgForm jgOld = service.getModel(myForm.getXjydjgid());
			
			//加载学生基本信息
			myForm.setYdqnj(jgOld.getYdqnj());
			myForm.setYdqxydm(jgOld.getYdqxydm());
			myForm.setYdqzydm(jgOld.getYdqzydm());
			myForm.setYdqbjdm(jgOld.getYdqbjdm());
			myForm.setYdqxjlb(jgOld.getYdqxjlb());			
			myForm.setYdqxjlbmc(jgOld.getYdqxjlbmc());
			myForm.setYdqsfyxjmc(jgOld.getYdqsfyxjmc());
			myForm.setYdqsfzxmc(jgOld.getYdqsfzxmc());
			//华中师范大学个性化字段（是否师范生）
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				myForm.setSfsfs(jgOld.getSfsfs());//是否师范生
			}
			
			// 更新
			boolean result = service.runUpdate(myForm);
			if(result){
				// 更新学生信息
				result = service.updateXsxx(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XjydjgForm model = service.getModel(myForm.getXjydjgid());
		
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
				
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// 学生基本信息
		String path = "xjydjg.do?method=xjydjgUpd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//学籍类别LIST
		XjydService serviceYd = new XjydService();
		List<HashMap<String,String>> xjlbList = serviceYd.getXjlbList("0","");
		request.setAttribute("xjlbList", xjlbList);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//异动原因
			XjydsqService xjydsqService = new XjydsqService();
			List<HashMap<String,String>> ydyyList = xjydsqService.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//维护来源学校/去向学校
			List<HashMap<String,String>> lyqxxxList = xjydsqService.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
		}
		
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		FormModleCommon.commonRequestSet(request);
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		return mapping.findForward("xjydjgUpd");
	}
	/**
	 * 
	 * @描述: 删除学籍异动结果
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午08:59:23
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
	@SystemLog(description="访问学生信息-学籍异动-学籍异动结果-删除VALUES:{values}")
	public ActionForward xjydjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = 0;
			boolean bolFlg = service.rollBackXsxx(values);
			if(bolFlg){
				num =  service.runDelete(values.split(","));
			}
			String message = bolFlg && num >0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 学籍异动学生信息查看
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午09:13:39
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
	public ActionForward xjydXsInfoCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydjgForm myForm = (XjydjgForm) form;

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
				
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// 学生基本信息
		String path = "xjydjg.do?method=xjydjgUpd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		// 学生最近的一次学籍异动信息
		HashMap<String, String> xsydInfo = service.getXsydInfo(myForm);
		request.setAttribute("xsydInfo", xsydInfo);

		// 学生最近的更多学籍异动信息
		List<HashMap<String, String>> xsYdList = service.getXsydList(
				myForm);
		request.setAttribute("xsYdList", xsYdList);
		
		return mapping.findForward("xjydXsInfoCk");
	}

	/**
	 * 
	 * @描述: 学籍异动学生信息查看
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午09:13:39
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
	public ActionForward xjydjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydjgForm myForm = (XjydjgForm) form;
		XjydjgForm model = service.getModel(myForm.getXjydjgid());
		
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
				
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XJYDSQ);
		
		// 学生基本信息
		String path = "xjydjg.do?method=xjydjgUpd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("xjydjgCk");
	}
	
	/**
	 * @描述:学籍异动批量处理
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午11:04:03
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
	@SystemLog(description="访问学生信息-学籍异动-学籍异动结果-批量异动XZXSKEY:{xzxskey}")
	public ActionForward xjydPlcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydjgForm myForm = (XjydjgForm) form;
		User user = getUser(request);
		
		// 保存
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
		
			boolean result = service.xjydPlcl(myForm , user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else if ("close".equalsIgnoreCase(myForm.getType())) {
			
			if(StringUtils.isNotNull(myForm.getXzxsKey())){
				// 取得学生列表
				XsxxService xsService = new XsxxService();

				// 删除临时库
				boolean result = xsService.runDelSelectAll(myForm.getXzxsKey());
				String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
						: MessageKey.SYS_DEL_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}

		
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
				
		// 已选择学生
		if(StringUtils.isNotNull(myForm.getXzxsKey())){
			XsxxService service = new XsxxService();
			int counts = service.getSelectedCount(myForm.getXzxsKey());
			//设定已选择学生数
			request.setAttribute("yxzxss", counts+"");
		}else{

			//设定已选择学生数
			request.setAttribute("yxzxss", "0");
		}
		//设定xzxsKey
		request.setAttribute("xzxsKey", myForm.getXzxsKey());
		
		//学籍类别LIST
		XjydService serviceYd = new XjydService();
		List<HashMap<String,String>> xjlbList = serviceYd.getXjlbList("0","");
		request.setAttribute("xjlbList", xjlbList);
		
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		// 学生基本信息
		String path = "xjydjg.do?method=xjydPlcl";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);
		
		return mapping.findForward("xjydPlcl");
	}
	
	/**
	 * 
	 * @描述: 学籍异动审核导出
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
		XjydjgForm model = (XjydjgForm) form;
		XjydjgService service = new XjydjgService();

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
	 * 
	 * @描述:登记表下载
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-30 下午04:35:21
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
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		List<File> files = new ArrayList<File>();
		if(value != null){
			for(String id:value.split(",")){
				HashMap<String , String> modelMap = service.getModelInfoMap(id);
				String dybb = modelMap.get("dybb");
				if(StringUtil.isNull(dybb)){
					String msg = "未设置登记表格，请联系管理员！";
					request.setAttribute("yhInfo", msg);
					return new ActionForward("/yhInfo.do", false);
				}
				Map<String , Object> data = printData(modelMap);
				File file = BbdmUtils.getSigleFile(dybb, data);
				files.add(file);
			}
			if(value.split(",").length == 1){
				FileUtil.outputWord(response, files.get(0));
			}else{
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
		}
		return null;
	}
	
	private HashMap<String , Object> printData(HashMap<String , String> modelMap) throws Exception{
		HashMap<String , Object> data = new HashMap<String, Object>();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(modelMap.get("xh"));
		data.putAll(xsjbxx);  //学生信息
		data.putAll(modelMap); //学籍异动
		//data.put("sqly", HtmlUtil.xmlZy(modelMap.get("sqly")));
		data.put("xxmc", Base.xxmc); //学校名称
		data.put("nd", Base.currNd);
		//截取填表年月日
		String tbnf = modelMap.get("jrsj").substring(0,4);
		String tbyf = modelMap.get("jrsj").substring(5,7);
		String tbts = modelMap.get("jrsj").substring(8,10);
		data.put("tbnf", tbnf);
		data.put("tbyf", tbyf);
		data.put("tbts", tbts);
		//获取休学原因
		XjydjgService service = new XjydjgService();
		XjydjgForm xjydjg = new XjydjgForm();
		xjydjg.setXh(modelMap.get("xh"));
		HashMap<String, String> xsydInfo = service.getXsydInfo(xjydjg);
		data.put("xsydInfo", xsydInfo);
		String sqly = HtmlUtil.xmlZy(data.get("sqly")== null ?  "" : data.get("sqly").toString());
		data.put("sqly", sqly);  //申请理由
		/**
		 * 宁夏建设职业技术学院 个性化
		 */
		if(StringUtils.isEqual(Base.xxdm, "13151")){
			String sfzh = (String) data.get("sfzh");
			if(sfzh != null){
				for (int i = 0; i < sfzh.length(); i++) {
					data.put("sfzh" + (i + 1), org.apache.commons.lang.StringUtils.substring(sfzh, i, i+1));
				}
			}
		}
		return data;
	}
	
}

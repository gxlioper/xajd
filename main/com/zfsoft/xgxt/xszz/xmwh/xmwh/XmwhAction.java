/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.bbwh.BbwhService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmlbwh.XmlbwhService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhAction extends SuperAction {
	private XmwhService service = new XmwhService();
	private String messageKey;
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";

	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm myForm = (XmwhForm) form;
		if (QUERY.equals(myForm.getType())) {
			if (StringUtil.isNull(myForm.getSqzqdm())) {
				myForm.setSqxn(Base.currXn);
				myForm.setSqxq(Base.currXq);
			} else {
				myForm.setSqxn(myForm.getSqzqdm().split(";")[0]);
				myForm.setSqxq(myForm.getSqzqdm().split(";")[1]);
			}

			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String currDate = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		request.setAttribute("currDate", currDate);
		request.setAttribute("XmzqList", service.getXmzqList("Query"));
		
		List<HashMap<String, String>> xmlbList = service.getXmlb();// 类别名称
		request.setAttribute("xmlbList", xmlbList);

		
		myForm.setSqzqdm(Base.currXn + ";" + Base.currXq);
		
		request.setAttribute("currXnXqmc", Base.currXn + " " + Base.getXqmcForXqdm(Base.currXq));
		
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmwhCx");
	}

	/**
	 * 
	 * @描述:增加方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-增加XMMC:{xmmc},LBDM:{lbdm},JE:{je}")
	public ActionForward xmwhZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm myForm = (XmwhForm) form;

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (service.isRepeat(myForm)) {// 名称重复验证
				messageKey = MessageKey.XSZZ_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			// 增加的项目默认为当前学年学期。
			myForm.setSqxn(Base.currXn);
			myForm.setSqxq(Base.currXq);
			boolean result = service.runInsert(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);// 项目类别代码名称

		BbwhService bbwhService = new BbwhService();
		List<HashMap<String, String>> bb = bbwhService.getBb();
		request.setAttribute("bbList", bb);// 报表代码名称
		request.setAttribute("dqzq", Base.currXn + " " + Base.getDqxqmc());

		return mapping.findForward("xmwhZj");
	}

	/**
	 * 
	 * @描述:修改方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-修改XMYXG:{xmyxg},XMDM:{xmdm},XMMC:{xmmc},LBDM:{lbdm},JE:{je}")
	public ActionForward xmwhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;
		String xmmcyxg = request.getParameter("xmyxg");
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if ("true".equals(xmmcyxg)) {
				if (service.isRepeat(myForm)) {// 名称重复验证
					messageKey = MessageKey.XSZZ_XMMC_EXIST;
					response.getWriter()
							.print(getJsonResult(messageKey, false));
					return null;
				}
			}
			if (service.isRalate(myForm)) {// 关联性
				messageKey = MessageKey.XSZZ_XM_NOTUPDATE;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);// 项目类别代码名称

		BbwhService bbwhService = new BbwhService();
		List<HashMap<String, String>> bb = bbwhService.getBb();
		request.setAttribute("bbList", bb);// 报表代码名称
		request.setAttribute("dqzq", Base.currXn + " " + Base.getDqxqmc());

		XmwhForm model = service.getModel(myForm);
		request.setAttribute("sqzq", model.getSqxn() + " "
				+ Base.getXqmcForXqdm(model.getSqxq()));// 申请周期
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xmmcxgq", myForm.getXmmc());
		return mapping.findForward("xmwhXg");
	}

	/**
	 * 
	 * @描述:基本设置
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-基本设置RSKZFWOLD:{rskzfwOld}")
	public ActionForward xmwhJbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String rskzfwOld = request.getParameter("rskzfwOld");
			XmwhRsszService xmwhRsszService = new XmwhRsszService();
			String xmdm = myForm.getXmdm();
			if (rskzfwOld != null && !rskzfwOld.equals(myForm.getRskzfw())) {// 人数控制范围修改
				xmwhRsszService.runDeleteByXmdm(xmdm);
			}

			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XmwhForm model = service.getModel(myForm);
		if(null==model.getXslb()){
			model.setXslb("1");
		}
		BeanUtils.copyProperties(myForm, model);

		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xszz");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> isnotList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);

		FormModleCommon.setNdXnXqList(request);// 年度学年学期

		KnsjgService knsjgService = new KnsjgService();
		List<HashMap<String, String>> xn = knsjgService.getKnsjgXn();
		request.setAttribute("knsrdxnList", xn);// 困难生认定学年

		List<HashMap<String, String>> xq = knsjgService.getKnsjgXq();
		request.setAttribute("knsrdxqList", xq);// 困难生认定学期

		List<HashMap<String, String>> xqList = Base.getXqList();
		String currXn = Base.currXn;// 当前学年
		String currXq = Base.currXq;// 当年学期
		String currXqmc = "";
		for (HashMap<String, String> map : xqList) {
			if (currXq.equals(map.get("xqdm"))) {
				currXqmc = map.get("xqmc");
				break;
			}
		}
		request.setAttribute("currXn", currXn);
		request.setAttribute("currXq", currXq);
		request.setAttribute("currXqmc", currXqmc);
		request.setAttribute("sqzq", model.getSqxn() + " "
				+ Base.getXqmcForXqdm(model.getSqxq()));

		// 是否有学生申请项目
		XszzSqshService xszzSqshService = new XszzSqshService();
		String xmdm = request.getParameter("xmdm");
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		request.setAttribute("xxdm", Base.xxdm);
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		request.setAttribute("pyccList",service.getPyccList());
		return mapping.findForward("xmwhJbsz");
	}

	/**
	 * 
	 * @描述:根据审核流程，获取审批级别
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public ActionForward xmwhShfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid 为选择的审批流程代码值
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}

	/**
	 * 
	 * @描述:根据困难生认定周期，获取认定档次
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public ActionForward xmwhKnsrddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		if (xn != null) {
			KnsdcService knsdcService = new KnsdcService();
			List<HashMap<String, String>> list = knsdcService.getKnsjgRddc(xn,
					xq);
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(list));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @描述:删除方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-删除VALUES:{values}")
	public ActionForward xmwhSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// //判断合法性////
			if (service.isRalate(values)) {// 关联性
				messageKey = MessageKey.XSZZ_XM_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if (result) {
				try {
					service.delRalate(values);// 删除所有的关联表
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;

	}

	/**
	 * 
	 * @描述:判断项目是否有配置报表
	 * @作者：honglin
	 * @日期：2013-5-9 下午03:42:55
	 * @修改记录:
	 * @throws
	 */
	public ActionForward getXszzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmmc = request.getParameter("xmmc");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");

		xmmc = xmmc != null ? xmmc.trim() : "";
		String result = service.getXszzdm(xmmc, xn, xq);
		response.getWriter().print(getJsonMessage(result));

		return null;

	}

	/**
	 * 
	 * @描述:判断是否存在审核流程中的数据
	 * @作者：ligl
	 * @日期：2013-5-10 上午09:24:40
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public ActionForward isExistShlcData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzSqshService xszzSqshService = new XszzSqshService();
		String xmdm = request.getParameter("xmdm");
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		response.getWriter().print(getJsonMessage(String.valueOf(flag)));
		return null;
	}

	/**
	 * 
	 * @描述:资助项目复制
	 * @作者：cq [工号：785]
	 * @日期：2014-1-21 下午04:17:51
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
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-复制XMFZND:{xmfznd}")
	public ActionForward xmwhfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> xmfzList = service.getXmzqList("fz");// 奖项复制列表
			JSONArray dataList = JSONArray.fromObject(xmfzList);
			response.getWriter().print(dataList);
			return null;
		} else if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmfznd = request.getParameter("xmfznd");
			boolean result = service.runXmfz(xmfznd);
			String messageKey = result ? MessageKey.PJPY_JXFZ_SUCCESS
					: MessageKey.PJPY_JXFZ_NOTJL;
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}

		request
				.setAttribute("currXnXqmc", Base.currXn + " "
						+ Base.getDqxqmc());

		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmwhfz");
	}




	/**
	 * 
	 * @描述: 项目维护-经费设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-21 下午03:48:46
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
	public ActionForward xmwhJfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhForm myForm = (XmwhForm) form;
		XmwhForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		List<HashMap<String, String>> others = service.getOthers(model.getXmdm());
		List<HashMap<String, String>> sameGroupList = service.getSameGroupXmList(model.getXmdm());
		Map<String,String> xmjfMap = service.getXyjf(model.getXmdm());
		
		request.setAttribute("xmjfMap", xmjfMap);
		request.setAttribute("others", others);
		request.setAttribute("sameGroupList", sameGroupList);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("xmwhJfsz");
	}


	/**
	 * 
	 * @描述: 保存经费设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 上午09:42:03
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
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-经费设置保存GROUPXMDM:{groupXmdm}")
	public ActionForward saveJfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhForm myForm = (XmwhForm) form;
		String[] groupXmdm = request.getParameterValues("groupXmdm");
		String[] xydm = request.getParameterValues("xydm");
		String[] je = request.getParameterValues("je");
		
		boolean result = service.savJfsz(myForm.getXmdm(), groupXmdm, xydm, je);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}






}

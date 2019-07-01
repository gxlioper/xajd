/**
 * @部门:学工产品事业部
 * @日期：2014-4-28 上午10:54:02 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

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
import xgxt.action.base.BaseDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 集体评奖奖项设置
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:54:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSzAction extends SuperAction {
	public final static String JTPJ_SHLC = "pjpy";
	
	private static final String url = "jtpjszbase.do";

	/**
	 * 
	 * @描述:集体评奖列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjszbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("sqzq", Base.currXn + "," + Base.currXq);
		request.setAttribute("sqzqList", service.getNowSqZqList());
		List<HashMap<String, String>>  list=service.getSqZqListNotIsHave(Base.currXn + "," + Base.currXq);
		request.setAttribute("iscanCopy", null==list||list.size()<=0?"0":"1");
		request.setAttribute("path", "jtpjszbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtpjszlist");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖设置-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断评奖项目当中是否已使用
			String resultjxmc = service.isCheckExistForDel(values);
			if(!resultjxmc.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_JTPJ_JXMC_DEL, resultjxmc);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			String message = (num > 0) ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num)
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述: 集体评奖模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖设置-修改JXMC:{jxmc},JTPJDW:{jtpjdw},PDXN:{pdxn},PDXQ:{pdxq},SFKFSQ:{sfkfsq},JXID:{jxid},SPLCID:{splcid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//集体评奖项目设置重复性判断
			boolean isExist=service.isExistByJxmc(myForm);
			if(isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_JXMC_REPEAT));
				return null;
			}
		}
		JtpjSzForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// 学年 学期
		request.setAttribute("dqxn", myForm.getSqxn());
		request.setAttribute("dqxq", BaseDAO.getInstance().getXqmcForXqdm(
				myForm.getSqxq()));
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		// 职务list
		request.setAttribute("zwList", service.getZwList());
		// 流程
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService
				.getShlcByDjlx(JTPJ_SHLC);
		request.setAttribute("shlcList", shlc);
		//是否温州大学
		
		request.setAttribute("sfkxg", service.checkIsNotExists(myForm.getJxid()) ? "1" : "0");
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		return mapping.findForward("jtpjszupdate");
	}

	/**
	 * 
	 * @描述: 集体评奖模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	public ActionForward copy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		myForm = service.getModel(myForm);
		myForm.setJxid(null);
		// 不可申请
		myForm.setSfkfsq("0");
		boolean result = service.runInsert(myForm);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述: 保存奖项复制
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-7 下午04:43:32
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
	public ActionForward savecopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		String lyxn=request.getParameter("lyxn");
		String lyxq=request.getParameter("lyxq");
		String sqxn=request.getParameter("sqxn");
		String sqxq=request.getParameter("sqxq");

		boolean result = service.copy(lyxn,lyxq,sqxn,sqxq);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述: 奖项复制
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-7 下午04:43:22
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
	public ActionForward jtpjszcopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		request.setAttribute("sqzqList", service.getSqZqListNotIsHave(myForm.getSqxn()+","+myForm.getSqxq()));
		request.setAttribute("data",myForm);
		request.setAttribute("xqmc",service.getXqmc(myForm.getSqxq()));
		return mapping.findForward("jtpjszcopy");
	}
	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:44:10
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖设置-增加JXMC:{jxmc},JTPJDW:{jtpjdw},PDXN:{pdxn},PDXQ:{pdxq},SFKFSQ:{sfkfsq},SPLCID:{splcid}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			myForm.setSqxn(Base.currXn);
			myForm.setSqxq(Base.currXq);
			//集体评奖项目设置重复性判断
			boolean isExist=service.isExistByJxmc(myForm);
			if(isExist){
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_JXMC_REPEAT));
				return null;
			}
		}
		//是否温州大学
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		// 学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		// 职务list
		request.setAttribute("zwList", service.getZwList());
		// 流程
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService
				.getShlcByDjlx(JTPJ_SHLC);
		request.setAttribute("shlcList", shlc);
		// 默认值
		//myForm.setJtpjdw("xy");
		//myForm.setSfkfsq("0");
		this.saveToken(request);
		return mapping.findForward("jtpjszadd");
	}

	/**
	 * 
	 * @描述:显示具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-17 下午05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		JtpjSzForm myForm = (JtpjSzForm) form;
		myForm = service.getModel(myForm);
		// 职务list
		request.setAttribute("zwmc", service.getZwMc(myForm.getKsqxslx()));
		request.setAttribute("sfkfsqmc", service
				.getSfkfsqmc(myForm.getSfkfsq()));
		request.setAttribute("sqxqmc", BaseDAO.getInstance().getXqmcForXqdm(
				myForm.getSqxq()));
		request.setAttribute("pdxqmc", BaseDAO.getInstance().getXqmcForXqdm(
				myForm.getPdxq()));
		// 流程
		request.setAttribute("splcmc", service.getLcxxMc(myForm.getSplcid(),
				JTPJ_SHLC));
		request.setAttribute("data", StringUtils.formatData(myForm));
		//是否温州大学
		request.setAttribute("iswzdx", Globals.XXDM_WZDX.equals(Base.xxdm)?"1":"0");
		return mapping.findForward("jtpjszview");
	}

	/**
	 * 
	 * @描述: 加载奖项信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 上午09:50:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadJxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService service = new JtpjSzService();
		String jxid = request.getParameter("jxid");
		JtpjSzForm myForm = new JtpjSzForm();
		if(StringUtils.isNotNull(jxid) && !"null".equals(jxid)){
			myForm = service.getModel(jxid);
		}
		response.getWriter().print(JSONObject.fromObject(StringUtils.formatData(myForm)));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:通过申请学年学期取得集体评奖列表
	 * @作者：戚立明
	 * @日期：2014-5-19 上午09:39:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward loadJtpjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSzService jss = new JtpjSzService();
		// 申请学年
		String sqxn = request.getParameter("sqxn");

		// 申请学期
		String sqxq = request.getParameter("sqxq");
		
		User user = getUser(request);
		// 集体评奖列表
		List<HashMap<String, String>> jtpjList = jss.getJxList(sqxn,sqxq, "0",user);

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("jtpjList", jtpjList);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
		
	}
	
	/**
	 * @描述:表格列表预览
	 */
	public ActionForward bgylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JtpjSzForm model = (JtpjSzForm)form;
		JtpjSzService service = new JtpjSzService();
		//项目信息
		JtpjSzForm xmxx = service.getModel(model.getJxid());
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xmxx.getSqxq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		//报表图片列表
		List<HashMap<String,String>> bbxxList = service.getBbxxList();
		
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("djblist");
	}
	
	/**
	 * @描述:登记表预览
	 */
	public ActionForward showYlbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtpjSzForm model = (JtpjSzForm)form;
		JtpjSzService service = new JtpjSzService();
		//项目信息
		JtpjSzForm xmxx = service.getModel(model.getJxid());
		String bbdm=request.getParameter("bbdm");
		
		//报表图片列表
		List<HashMap<String,String>> bbxxList = service.getBbxxList(bbdm);
		
		request.setAttribute("bbdm", bbdm);//选中的登记表ID
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("djbview");

	}
	
	/**
	 * @描述:设置项目登记表
	 */
	@SystemLog(description="访问评奖评优-基本设置-项目设置-登记表设置-保存XMDM：{xmdm}")
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtpjSzForm model=new JtpjSzForm();
		model.setDybbid(request.getParameter("bbdm"));
		model.setJxid(request.getParameter("xmdm"));
		JtpjSzService service = new JtpjSzService();
		boolean isSuccess = service.runUpdate(model);
		response.getWriter().print(isSuccess);
		return null;
	}
}

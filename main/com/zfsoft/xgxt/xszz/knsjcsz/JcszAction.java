package com.zfsoft.xgxt.xszz.knsjcsz;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 基本设置管理模块
 * @类功能描述: action 
 * @作者： maxh 
 * @时间： 2013-4-19 上午10:17:52 
 * @版本： V1.0
 * @修改记录: 
 */
public class JcszAction extends SuperAction {
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	
	private static final String url = "xszz_jcsz_wh.do";
	
	/**
	 * 
	 * @描述:基本设置
	 * @作者：maxh
	 * @日期：2013-4-19 上午10:35:11
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
	public ActionForward jcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		//困难生认定学年
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("xxdm", Base.xxdm);
		
		//审核流程列表
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
		request.setAttribute("shlcList", shlc);
		String path = "xszz_jcsz_wh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			myForm.setRdxn(Base.currXn);
		}
		return mapping.findForward("jcsz");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		//困难生认定学年
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("xxdm", Base.xxdm);
		
		//审核流程列表
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
		request.setAttribute("shlcList", shlc);
		String path = "xg_xszz_knsrd_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			myForm.setRdxn(Base.currXn);
		}
		return mapping.findForward("knsrdJcsz");
	}
	
	/**
	 * 
	 * @描述: 保存基础设置
	 * @作者：Penghui.Qu
	 * @日期：2013-5-7 下午01:48:29
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
	@SystemLog(description="访问学生资助-困难生认定-基本设置-保存-RSKZJB:{rskzjbView}")
	public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		
		// 取消审批流验证判断
//		JcszForm oldForm=service.getModel();
		
//		if((oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc()))||Constants.CLOSE.equals(myForm.getSqkg())){
//			KnsrdService  knsrd=new KnsrdService();
//			//判断是否有流程正在审核
//			boolean isUse=knsrd.allowUpdateSetting();
//			if(!isUse){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSJCSZ_SHLC_EXIST));
//				return null;
//			}
//		}
		
		myForm.setRskzjb(request.getParameter("rskzjbView"));
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:根据审核流程，获取审批级别
	 * @作者：陈敏杰
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public ActionForward getShgw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splc = request.getParameter("splc");
		if (!StringUtil.isNull(splc)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(splc);// spgwid 为选择的审批流程代码值
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:困难生人数设置查询
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-9 上午10:35:24
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
	public ActionForward knsRssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm=(JcszForm) form;
		JcszService service = new JcszService();
		
		List<String>  njList = service.getNj();//得到所有包含学生的年级
		JcszForm model=service.getModel();
		if(model==null){
			model=new JcszForm();
		}
		request.setAttribute("njArrList", njList);
		request.setAttribute("rskzfw", myForm.getRskzfw());
		request.setAttribute("rskznj", model.getRskznj());
		//myForm.setXn(Base.currXn);
		//myForm.setXq(Base.currXq);
		if(myForm.getXq() == null || "undefined".equals(myForm.getXq())){
			myForm.setXq("on");
		}
		
		request.setAttribute("knsrsszfs", service.getKnsrsszfs());
		request.setAttribute("knsdcList", new KnsdcService().getKnsdcList());
		
		// 年级学院专业班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("knsRssz");
	}
	
	/**
	 * 
	 * @描述:保存
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-12-9 下午04:05:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-基本设置-查询-困难生人数设置-分配设置-保存-FPFS:{fpfs}")
	public ActionForward knsRsszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<JcszForm> list = JsonUtil.jsonToList(json,JcszForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//总名额方式
				zme = request.getParameter("zme");
			}
			String rskznj = request.getParameter("rskznj");//人数控制年级
			if(service.getKnsrsszfs().equals("1")){
				rskznj="";
			}
			messageKey = service.fpsz(myForm, list, zme,rskznj);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		JcszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	
	}
	
	/**
	 * @描述:人数控制范围字段修改，ajax及时提交保存
	 * @作者：cmj 
	 * @日期：2013-12-11 上午11:46:27
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
	public ActionForward changeRskzfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		boolean result=service.updateRskzfw(myForm);
		String message=result?"true":"false";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * @描述:获取总名额、已设置人数
	 * @作者：陈敏杰
	 * @日期：2013-12-11 下午02:55:22
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
	public ActionForward knsRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		JcszForm jcszForm=service.getModel();
		String yszrs=service.getYszrs(myForm);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("yszrs", yszrs);
		if(jcszForm!=null){
			map.put("zme", jcszForm.getZme());
			if(service.getKnsrsszfs().equals("1")){
				map.put("zme", service.getRddcZme(myForm.getRddc()));
			}
		}else{
			map.put("zme", "0");
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}
	
	/**
	 * @描述:人数设置修改
	 * @作者：cmj 
	 * @日期：2013-12-11 下午03:41:30
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
	@SystemLog(description="访问学生资助-困难生认定-基本设置-查询-困难生人数设置-保存GUIDS:{guids}")
	public ActionForward rsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		String messageKey;
		messageKey = service.setZzrs(myForm);
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	/**
	 * @描述:验证已是否设置人数
	 * @作者：陈敏杰
	 * @日期：2013-12-11 下午04:44:48
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
	public ActionForward yzSfszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		boolean result=service.checkRsSfsz(myForm);
		String messageKey=result?"true":"false";
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	
}

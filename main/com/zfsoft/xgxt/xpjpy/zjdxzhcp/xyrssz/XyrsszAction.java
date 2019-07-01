/**
 * @部门:学工产品事业部
 * @日期：2017-7-18 上午09:29:50 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import java.text.DateFormat;
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

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz.RsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 学院人数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-18 上午09:29:50 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyrsszAction extends SuperAction{
	private static final String url = "xpjpy_zhcp_xyrssz.do";
	
	/**
	 * @描述: 学院人数设置查询列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-18 下午02:00:54
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
	public ActionForward getXyrsszList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
 		throws Exception {
		
		XyrsszForm model = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		
		User user = getUser(request);
		
		/*查询所有数据*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		XmwhService xmwhService = new XmwhService();
		/*获取参数设置信息*/
		HashMap<String, String> csszMap = xmwhService.getCsszMap();
		request.setAttribute("pjzq", csszMap.get("xn"));
		/*获取项目类型*/
		List<HashMap<String, String>> xmlxList = xmwhService.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*获取项目性质*/
		List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*返回path*/
		String path = "xpjpy_zhcp_xyrssz.do";
		request.setAttribute("path", path);
		/*返回系统当前时间*/
		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", xmwhService.getCurrTime(dateFormat));
		
		/*浙大学院人数设置奖学金金额提示*/
		List<HashMap<String,String>> jxjList = service.getJxjze(model, user);
		request.setAttribute("jxjjeMap", jxjList.get(0));
		
		/*加载页面控制权限及表头*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyrsszList");
	}
	
	/**
	 * @描述: 人数设置基本查询方式
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-19 上午09:58:07
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
	public ActionForward xyrszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XyrsszForm model = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		
		User user = getUser(request);
		
		/*获取项目代码，根据项目代码获得名称*/
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		RsszService rsszService = new RsszService();
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		/*人数控制年级*/
		request.setAttribute("rsfpnj", rsfpnj);
		/*得到所有包含学生的年级*/
		List<String>  njList = rsszService.getNj();
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		/*数据查询*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getRsszList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		/*是否有学生申请项目*/
		XmsqService xmsqservice = new XmsqService();
		boolean flag = xmsqservice.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		/*返回path*/
		String path = "xpjpy_zhcp_xyrssz.do";
		request.setAttribute("path", path);
		/*获得参数配置*/
		request.setAttribute("rsjsfs", rsszService.getCsz("rsjsfs"));
		/*项目金额、项目代码*/
		request.setAttribute("xmje", request.getParameter("xmje"));
		request.setAttribute("xmdm", xmdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyrsszCx");
	}
	
	/**
	 * @描述: 学院人数设置，学院老师修改修改最终人数后  保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-20 上午11:52:40
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
	@SystemLog(description="访问评奖评优-基本设置-项目设置-人数设置-保存XMDM：{xmdm}")
	public ActionForward xmwhRsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XyrsszForm myForm = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		
		User user = getUser(request);
		if(SAVE.equalsIgnoreCase(myForm.getType())){
			String messageKey = null;
			
			messageKey = service.setZzrs(myForm,user);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		
		/*保存操作人和时间*/
		myForm.setZd1(user.getUserName());
		/*精确到时分秒*/
		DateFormat date = DateFormat.getDateTimeInstance();
		myForm.setZd2(date+"");
		
		XyrsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}
	
	/**
	 * @描述: ajax验证人数上限和金额上限
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-20 下午04:51:26
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
	public ActionForward rsszCheckAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XyrsszForm myForm = (XyrsszForm)form;
		User user = getUser(request);
		XyrsszService service = new XyrsszService();
		List<HashMap<String, String>> resultList = service.getJxjze(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	public ActionForward xmwhRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyrsszForm model = (XyrsszForm)form;
		XyrsszService service = new XyrsszService();
		/*项目已设置的总人数*/
		int yszrs = service.getYszrs(model);
		String zme = "";
		if(model.getXmdm() != null){
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> hashMap = xmwhService.getDataById(model.getXmdm());
			if (hashMap != null) {
				zme = hashMap.get("rsfpz");
			}
		}
		response.setContentType("text/html;charset=gbk");
		Map<String, String> map = new HashMap<String, String>();
		map.put("yszrs", yszrs + "");
		map.put("zme", zme);
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}
}

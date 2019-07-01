/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 上午09:25:00 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import java.net.URLDecoder;
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
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-项目设置-人数设置
 * @类功能描述: 人数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-1 上午09:25:00 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RsszAction extends SuperAction{
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	
	/**
	 * @描述: 基本查询方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-1 上午11:12:52
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
	public ActionForward xmwhRsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszForm model = (RsszForm)form;
		User user = getUser(request);
		RsszService service = new RsszService();
		/*获取页面的xmdm*/
		String xmdm = request.getParameter("xmdm");
		/*获取评奖项目表 人数分配年级 (控制)*/
		XmwhService xmwhService = new XmwhService();
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		request.setAttribute("rsfpnj", rsfpnj);
		/*得到所有包含学生的年级*/
		List<String> njList = service.getNj();
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
		/*年级学院专业班级*/
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		/*查询*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getRsszList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*获得项目名称*/
		String xmmc = request.getParameter("xmmc");
		xmmc = URLDecoder.decode(xmmc,"utf-8");
		request.setAttribute("xmmc", xmmc);
		/*是否有学生申请项目*/
		XmsqService xmsqservice = new XmsqService();
		boolean flag = xmsqservice.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		/*返回path*/
		String path = "xpjpy_jbsz_xmsz.do";
		request.setAttribute("path", path);
		/*获得参数配置*/
		request.setAttribute("rsjsfs", service.getCsz("rsjsfs"));
		/*项目金额、项目代码*/
		request.setAttribute("xmje", request.getParameter("xmje"));
		request.setAttribute("xmdm", xmdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszCx");
	}
	
	/**
	 * @描述: 查询项目已设置人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-2 上午09:31:49
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
	public ActionForward xmwhRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszForm model = (RsszForm)form;
		RsszService service = new RsszService();
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
	
	/**
	 * @描述: 比例设置方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午09:02:51
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
	@SystemLog(description="访问评奖评优-基本设置-项目设置-人数设置-分配设置-保存XMDM：{xmdm}")
	public ActionForward xmwhRsszBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszForm myForm = (RsszForm)form;
		RsszService service = new RsszService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<RsszForm> list = JsonUtil.jsonToList(json,
					RsszForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//总名额方式
				zme = request.getParameter("zme");
			}
			String rsfpnj = request.getParameter("rsfpnj");//人数控制年级
			messageKey = service.fpsz(myForm, list, zme,rsfpnj,user);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		RsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}
	
	/**
	 * @描述: ajax验证人数上限和金额上限
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 下午05:28:44
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
		RsszForm myForm = (RsszForm)form;
		User user = getUser(request);
		RsszService service = new RsszService();
		List<HashMap<String, String>> resultList = service.getJxjze(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
}

/**
 * @部门:学工产品1部
 * @日期：2017-3-21 上午09:18:53 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.task.TaskHandler;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_基本设置_参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-21 上午09:18:53 
 * @版本：  V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction{
	private static final String url = "xpjpy_jbsz_cssz.do";
	
	/**
	 * @描述: 参数设置
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-21 上午11:40:01
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
	public ActionForward getCsszList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszService service = new CsszService();
		CsszForm model = (CsszForm) form;
		CsszForm csszModel = service.getCsszModel();
		model.setXn(csszModel.getXn());
		if (QUERY.equals(model.getType())){
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*检测综测项目是否被使用*/
		String zcxmCount = service.checkZcxmMade(model.getXn());
		request.setAttribute("zcxmCount", zcxmCount);
		/*得到评奖周期列表*/
		List<HashMap<String, String>> pjzqList = service.getPjzqList();
		request.setAttribute("pjzqList", pjzqList);
		/*评奖开关{ "1", "开启" }, { "0", "关闭" }*/
		request.setAttribute("pjkgList", new OptionUtil().getOptions(OptionUtil.ONOFF));
		String path = "xpjpy_jbsz_cssz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if (csszModel != null){
			/*拷贝属性值*/ 
			BeanUtils.copyProperties(model, csszModel);
		}
		return mapping.findForward("listCssz");
	}
	
	/**
	 * @描述: 参数设置自动保存发放
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-27 上午10:33:53
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
	@SystemLog(description="访问评奖评优-基本设置-参数设置-保存ZDKEY:{zdKey} ZDVALUE:{zdValue}")
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zdKey = request.getParameter("zdKey");
		String zdValue = request.getParameter("zdValue");
		
		/*保存改动的信息(评奖开关、评奖周期、起止时间)到xg_zjdx_pjpy_csszb*/
		CsszService service = new CsszService();
		service.updateCssz(zdKey, zdValue);
		
		/*关闭评奖模块多线程任务*/
		if ("pjkg".equals(zdKey) && "0".equals(zdValue)){
			TaskHandler.getInstance().shutdown("pjpy");
		}
		
		/*选择评奖周期(xn),初始化操作*/
		if("xn".equals(zdKey) && !StringUtil.isNull(zdValue)){
			/*初始化综测结构*/
			service.initZcxmList(zdValue);
			/*初始化参评小组*/
			service.initCpxz(getUser(request));
			/*判断当前评奖名单人员库是否为空 ? 根据在校生初始化 : 不做操作*/
			 boolean sfcz = service.getSfcz();
			 if(!sfcz){
				 /*评奖人员库执行初始化操作*/
				 service.init();
			 }
		}
		return null;
	}
	
	/**
	 * @描述: 增加综测项目返回页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-28 上午08:44:30
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
	public ActionForward addZcxm (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		CsszForm csszModel = service.getCsszModel();
		model.setXn(csszModel.getXn());
		request.setAttribute("yjxmlist", service.getYjxmlist(model.getXn()));
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("addZcxm");
	}
	
	/**
	 * @描述: 增加综测项目保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-8 下午03:09:42
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
	@SuppressWarnings("unchecked")
	public ActionForward saveForAdd (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm myFrom = (CsszForm)form;
		CsszService service = new CsszService();
		String objStr = request.getParameter("objStr");
		List<CsszForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr,CsszForm.class);
		}
		myFrom.setXn(service.getCsszModel().getXn());
		//判断同一个学年、项目名称不能重复
		boolean isExistXmmc = service.isExistByZcxm(myFrom);
		boolean checkLxcf = service.checkLxcf(jxxxList);
		if(!isExistXmmc && !checkLxcf){
			boolean result = service.saveAddXmlxDj(myFrom,jxxxList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_REPEATING, false));
		}
		return null;
	}
	
	/**
	 * @描述: 删除综测项目信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-1 下午01:59:19
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
	public ActionForward delZcxm (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszService service = new CsszService();
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int numDj = service.numDj(values.split(","));
			int num = service.numFz(values.split(","));
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
	 * 修改返回页面
	 */
	public ActionForward updateZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xmdm = request.getParameter("xmdm");
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		//查找综测项目表原有数据，返回到model
		HashMap<String, String> csszForm = service.getZcxmDate(xmdm);
		if(null!=csszForm){
			BeanUtils.copyProperties(model, StringUtils.formatData(csszForm));
			request.setAttribute("csszForm", StringUtils.formatData(csszForm));
		}
		// 加载综测项目_等级checkBox数据
		List<HashMap<String, String>> zcxmDjList = service.getZcxmDjList(xmdm);
		request.setAttribute("zcxmDjList", zcxmDjList);
		String path = "xpjpy_cssz.do?method=updateZcxm";
		request.setAttribute("xmlx", model.getXmlx());
		request.setAttribute("yjxmlist", service.getYjxmlist(model.getXn()));
		request.setAttribute("path", path);
		return mapping.findForward("updateZcxm");
	}
	
	@SystemAuth(url = url)
	@SuppressWarnings("unchecked")
	public ActionForward saveForUpdate (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CsszForm myFrom = (CsszForm)form;
		CsszService service = new CsszService();
		String objStr = request.getParameter("objStr");
		
		List<CsszForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr,CsszForm.class);
		}
		myFrom.setXn(service.getCsszModel().getXn());
		boolean checkLxcf = service.checkLxcf(jxxxList);
		if(!checkLxcf){
			boolean result = service.saveUpdateXmlxDj(myFrom,jxxxList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_REPEATING, false));
		}
		return null;
	}
}

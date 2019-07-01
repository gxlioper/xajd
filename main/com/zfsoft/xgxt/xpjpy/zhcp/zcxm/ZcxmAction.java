/**
 * @部门:学工产品事业部
 * @日期：2013-7-22 下午02:46:50 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcxm;

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

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测项目 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-22 下午02:46:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcxmAction extends SuperAction {

	
	public ActionForward viewZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("viewZcxm");
	}
	
	
	/**
	 * 
	 * @描述: 获取综测项目结构
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 下午06:53:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward getZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmService service = new ZcxmService();
		Map<String,List<HashMap<String,String>>> map = service.getZcxm();
		
		JSONObject json = JSONObject.fromMap(map);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 请求增加资助项目页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午09:32:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward addZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel zcxmForm = (ZcxmModel) form;
		zcxmForm.setXmlx(ZcxmService.XMLX_PUSH);
		
		OptionUtil optionUtil = new OptionUtil();
		//加载项目类型
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.ZHCP_XMLX);
		
		request.setAttribute("xmlxList", xmlxList);
		return mapping.findForward("addZcxm");
	}
	
	
	/**
	 * 
	 * @描述: 请求修改综测项目页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午09:50:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward editZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		CsszService csszService = new CsszService();
		ZcxmModel model = service.getModel(zcxmForm);
		boolean isUpdate = false;
		if (model != null){
			BeanUtils.copyProperties(zcxmForm, StringUtils.formatData(model));
		}
		
		OptionUtil optionUtil = new OptionUtil();
		//加载项目类型
		List<HashMap<String,String>> xmlxList = optionUtil.getOptions(OptionUtil.ZHCP_XMLX);
		CsszModel csszModel = csszService.getModel();
		
		if (ZcxmService.XMJB_QT != csszModel.getZcxmjb()){
			isUpdate = service.jcBlxg(model.getXmdm());
		}
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("xmlxList", xmlxList);
		request.setAttribute("isUpdate", isUpdate);
		return mapping.findForward("editZcxm");
	}
	
	
	/**
	 * 
	 * @描述: 增加综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午10:05:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward saveZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		zcxmForm.setXn(csszModel.getXn());
		zcxmForm.setXq(csszModel.getXq());
		
		//判断当前学期名称是否已存在
		if(service.xmmcExist(zcxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
			
		}else{
			boolean result = service.runInsert(zcxmForm);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		
	}
	
	
	/**
	 * 
	 * @描述: 修改综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午10:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward updateZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		ZcxmModel myForm = service.getModel(zcxmForm);
		
		//判断当前学期名称是否已存在
		if(!zcxmForm.getXmmc().equalsIgnoreCase(myForm.getXmmc())&&service.xmmcExist(zcxmForm)){
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			return null;
		}
		
		boolean result = service.runUpdate(zcxmForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 删除综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午10:42:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward delZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		ZcxmService service = new ZcxmService();
		
		boolean result = service.delZcxm(zcxmForm.getXmdm());
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}



	
	/**
	 * 
	 * @描述: 按年级显示综测比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午02:43:41
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
	public ActionForward showXxbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmService zcxmService = new ZcxmService();
		//可设详细比例的综测项目
		List<HashMap<String,Object>> zcxmList = zcxmService.getZcxmListByXxbl();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("csszModel", csszModel);
		request.setAttribute("zcxmList", zcxmList);
		return mapping.findForward("showXxbl");
	}
	/**
	 * 
	 * @描述:可设置的月份列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-10-22 下午04:05:20
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
	public ActionForward showYf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmModel zcxmForm = (ZcxmModel) form;
		CsszService csszService = new CsszService();
		ZcxmService zcxmService = new ZcxmService();
		CsszModel csszModel = csszService.getModel();
		if (SAVE.equalsIgnoreCase(zcxmForm.getType())) {
			String[] zcyf=request.getParameterValues("zcyf");
			boolean result = zcxmService.saveYf(zcyf,csszModel.getXn(),csszModel.getXq());
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//已设置月份
		request.setAttribute("yf", zcxmService.getYszYf(csszModel.getXn(),csszModel.getXq()));
		request.setAttribute("csszModel", csszModel);
		return mapping.findForward("showYf");
	}
	//获取月份列表
	public ActionForward getYf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcxmService zcxmService = new ZcxmService();
		//可设置的月份
		List<HashMap<String,String>> szyfList = zcxmService.getSzyfList();
		JSONArray dataList = JSONArray.fromObject(szyfList);
		response.getWriter().print(dataList);
		return null;
	}
	

	/**
	 * 
	 * @描述: 加载详细比例设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 上午11:33:30
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
	public ActionForward getXxblList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel model = (ZcxmModel) form;
		ZcxmService zcxmService = new ZcxmService();
		
		//详细比例查询，年级、学院之分在service层已处理
		List<HashMap<String,String>> xxblList = zcxmService.getXxblList(model);
//		
		JSONArray dataList = JSONArray.fromObject(xxblList);
		response.getWriter().print(dataList);
		
		return null;
	}

	
	/**
	 * 
	 * @描述: 修改详细比例设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 下午03:50:51
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
	public ActionForward updateXxbl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcxmModel model = (ZcxmModel) form;
		ZcxmService zcxmService = new ZcxmService();
		
		boolean result = zcxmService.updateXxblByBmdm(model);
		response.getWriter().print(result);
		
		return null;
	}





}

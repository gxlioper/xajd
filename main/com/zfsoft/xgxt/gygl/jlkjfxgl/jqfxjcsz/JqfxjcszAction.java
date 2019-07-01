package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;


import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理假期返校管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-11 上午09:29:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
@SuppressWarnings("unchecked")
public class JqfxjcszAction extends SuperAction {

	/**
	 * 
	 * @描述:TODO(假期返校基础设置)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-25 下午04:29:07
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
	public ActionForward jqfxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxjcszForm myForm = (JqfxjcszForm) form;
		JqfxjcszService service = new JqfxjcszService();		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		
		//返校类别名称集合
		JqfxjcszService jqfxjcszService = new JqfxjcszService();
		List<HashMap<String,String>> jqfxmcList = jqfxjcszService.getFxmcList();
		request.setAttribute("jqfxmcList", jqfxmcList);
			
		String path = "jlkjxyjqfxjcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JqfxjcszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jlkjfxgljcsz");
		
	}
	
	/**
	 * 
	 * @描述:TODO(保存假期返校类别)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-25 下午03:52:31
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
	public ActionForward saveJqfxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxjcszForm myForm = (JqfxjcszForm) form;
		JqfxjcszService service = new JqfxjcszService();		
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}

}

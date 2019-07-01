/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:31:50 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.OptionUtil;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 班风竞赛管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-3-31 下午04:05:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BfjsCsszAction extends SuperAction {

	private static final String url = "xpjpy_bfjs_cssz.do";
	
	/**
	 * 
	 * @描述:参数设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-3-31 下午04:06:20
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
	public ActionForward viewBfjsCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsCsszService service = new BfjsCsszService();
		BfjsCsszModel BfjsCsszForm = (BfjsCsszModel) form;
		BfjsCsszModel BfjsCsszModel = service.getModel();
		
		if (BfjsCsszModel != null){
			//拷贝属性值 
			BeanUtils.copyProperties(BfjsCsszForm, BfjsCsszModel);
		}
		
		request.setAttribute("sqkgList", new OptionUtil().getOptions(OptionUtil.ONOFF));
		request.setAttribute("pdzqList", service.getPdzqList());
		request.setAttribute("path", url);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBfjsCssz");
	}
	
	

	
	
	/**
	 * 
	 * @描述: 保存参数设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-3-31 下午04:06:20
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
	@SystemLog(description="访问班风竞赛-基本设置-参数设置-保存ZDKEY:{zdKey} ZDVALUE:{zdValue}")
	public ActionForward saveBfjsCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zdKey = request.getParameter("zdKey");
		String zdValue = request.getParameter("zdValue");
		BfjsCsszService service = new BfjsCsszService();
		User user =getUser(request);
		service.updateBfjsCssz(zdKey, zdValue);
		if ("pdzq".equals(zdKey) && !StringUtil.isNull(zdValue)){
			//初始化竞赛项目结构
			BfjsJsxmService jsxmService = new BfjsJsxmService();
			jsxmService.initBfjsJsxmList(zdValue);
			//初始化竞赛班级
			service.initJsbj(user);
		}
		return null;
	}
}

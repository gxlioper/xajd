/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-13 上午10:24:39 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.jdsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 不可兼得设置
 * @作者： MengWei[工号:1186]
 * @时间： 2018-7-13 上午10:24:14 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdszAction extends SuperAction {
	private JdszService service = new JdszService();
	private String messageKey;
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	/**
	 * @描述: 兼得设置查询
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 上午11:01:55
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
	@SystemLog(description = "访问新评奖评优-基本设置-项目设置-不可兼得设置页面")
	public ActionForward xmwhJdszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JdszForm model = (JdszForm)form;
		String xmdm = request.getParameter("xmdm");
		
		/*根据项目代码获取项目名称*/
		String xmmc = service.getXmmcByXmdm(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getByXmdm(xmdm,"");
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*根据xmdm查询此项目是否被申请*/
		boolean flag = service.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		
		/*返回path*/
		String path = "xpjpy_jbsz_xmsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhJdszcx");
	}
	
	/**
	 * @描述: 获取勾选项目以外的所有项目(相同流程内的)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午05:31:05
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
	@SystemLog(description = "访问新评奖评优-基本设置-项目设置-获取勾选项目以外的所有项目(相同流程内的)")
	public ActionForward xmwhJdszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xmdm = request.getParameter("xmdm");
		/*获取勾选项目以外的所有项目(相同流程内的)*/
		List<HashMap<String, String>> resultList = service.getOthers(xmdm);
		response.getWriter().print(JSONArray.fromObject(resultList));
		return null;
	}
	
	/**
	 * @描述: 不可兼得设置保存 
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-14 上午11:29:59
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
	@SystemLog(description="访问新评奖评优-基本设置-项目设置-兼得设置-保存XMDM：{xmdm}")
	public ActionForward xmwhJdszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		JdszForm myForm = (JdszForm)form;
		String xmdm = request.getParameter("xmdm");
		String xmdms = request.getParameter("xmdms");

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = false;
			try {
				result = service.jdsz(xmdm,xmdms);
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			} catch (Exception e) {
				e.printStackTrace();// //异常打印。。。。。////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		JdszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}
}

/**
 * @部门:学工产品（1）部
 * @日期：2017-4-18 下午08:17:53 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优-项目设置-条件设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-18 下午08:17:53 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszAction extends SuperAction{
	private TjszService service = new TjszService();
	private String messageKey;
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	/**
	 * @描述: 查询发放
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午09:30:52
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
	public ActionForward xmwhTjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		/*是否有学生申请项目*/
		/*现在学生即使申请了某个项目，此项目的条件也可被修改*/
		//boolean flag = service.isExistsFlowData(xmdm);
		request.setAttribute("flagpath","");
		request.setAttribute("xmmc", xmmc);
		//request.setAttribute("spzt", flag);
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszCx");
	}
	
	/**
	 * @描述: 查询所有记录，返回json格式
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 上午10:24:02
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
	public ActionForward xmwhTjszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String flagpath = request.getParameter("flagpath");
		TjszViewForm viewForm = new TjszViewForm();
		if(StringUtils.isNull(flagpath)){
			 viewForm = service.getAll(xmdm);
		}
		JSONObject json = JSONObject.fromBean(viewForm);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @描述: 查询学年学期记录，返回json格式
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 下午02:01:59
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
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm)form;
		if (QUERY.equals(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			TjszViewForm viewForm = service.getXn(xmdm);
			JSONObject json = JSONObject.fromBean(viewForm);
			response.getWriter().print(json);
			return null;
		}
		String xnVal = request.getParameter("xnVal");
		request.setAttribute("xnVal", xnVal);
		String zqlx = request.getParameter("zqlx");
		request.setAttribute("zqlx", zqlx);
		//XmwhService xmwhService = new XmwhService();
		
		//得到评奖周期,2为学年评奖
		CsszService csszService = new CsszService();
		request.setAttribute("pjzq", csszService.getCsz("pjzq"));
		
		request.setAttribute("knszq", MessageUtil.getText("xszz.knsrd.sqzq"));
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszXn");
	}
	
	/**
	 * @描述: 条件值弹层选择方式
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 下午02:02:55
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
	public ActionForward xmwhTjszTjzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("xmwhTjszTjzDiv");
	}
	
	/**
	 * 
	 * @描述:修改方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-条件设置-保存XMDM：{xmdm}")
	public ActionForward xmwhTjszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			String json = request.getParameter("json");
			boolean result = false;
			try {
				List<TjszForm> list = JsonUtil.jsonToList(json,
						TjszForm.class);
				if (list != null && list.size() > 0) {
					result = service.saveOrUpdate(xmdm, list);
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = MessageKey.SYS_SAVE_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();// //异常打印。。。。。////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			String message =  MessageUtil.getText(messageKey);
			Map<String,String> map = new HashMap<String,String>();
			map.put("message", message);
			map.put("success", String.valueOf(result));
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}

		TjszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * @描述: 删除
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-19 下午02:10:23
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
	@SystemLog(description="访问评奖评优-基本设置-项目设置-条件设置-删除XMDM：{xmdm}")
	public ActionForward xmwhTjszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");
		if (!StringUtil.isNull(xmdm) && !StringUtil.isNull(tjdm)) {
			service.delDeal(xmdm,tjdm);//删除所有的关联表
		} 
		return null;
	}
}

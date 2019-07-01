/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.jdsz;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-兼得设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class JdszAction extends SuperAction {
	private JdszService service = new JdszService();
	private String messageKey;
	
	private static final String urlJxj = "xpj_xmwh.do?method=xmwhCx&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_xmwh.do?method=xmwhCx&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhJdszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JdszModel myForm = (JdszModel) form;
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getByXmdm(xmdm,"","");
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//是否有学生申请项目
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		String path= null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhJdszCx");
	}

	/**
	 * 
	 * @描述:兼得设置方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-项目设置-兼得设置-保存XMDM：{xmdm}")
	public ActionForward xmwhJdszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JdszModel myForm = (JdszModel) form;
		String xmdm = request.getParameter("xmdm");
		String xmdms = request.getParameter("xmdms");

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = false;
			try {
				result = service.jdsz(xmdm,xmdms);
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			} catch (Exception e) {
				e.printStackTrace();// //异常打印。。。。。////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		JdszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @描述:查询所有项目，不包含当前项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhJdszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		// 获取所有其他的项目列表
		List<HashMap<String, String>> resultList = xmwhService
				.getOthers(xmdm);
		response.getWriter().print(JSONArray.fromObject(resultList));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:根据项目代码，获取已设置的不可兼得项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public ActionForward getBjdxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JdszService service = new JdszService();
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> resultList = service.getBjdxm(xmdm);
		JSONArray data = JSONArray.fromObject(resultList);
		response.getWriter().print(data);
		return null;
	}
	
	/**
	 * 
	 * @描述:根据项目代码，获取不可兼得已申请的奖项。
	 * @作者：cq [工号：785]
	 * @日期：2014-3-3 上午10:36:00
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
	public ActionForward getJdysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JdszService service = new JdszService();
		String xmdm = request.getParameter("xmdm");
		User user = getUser(request);
		
		List<HashMap<String, String>> resultList = service.getJdysq(user,xmdm);
		JSONArray data = JSONArray.fromObject(resultList);
		response.getWriter().print(data);
		return null;
	}
}

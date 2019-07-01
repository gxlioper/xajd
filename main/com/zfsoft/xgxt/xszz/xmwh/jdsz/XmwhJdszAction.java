/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.jdsz;

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

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-兼得设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhJdszAction extends SuperAction {
	private XmwhJdszService service = new XmwhJdszService();
	private String messageKey;
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";
	
	/**
	 * 
	 * @描述:基本查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhJdszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhJdszForm myForm = (XmwhJdszForm) form;
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getByXmdm(xmdm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//是否有学生申请项目
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhJdszCx");
	}

	/**
	 * 
	 * @描述:兼得设置方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-兼得设置-修改保存XMDM:{xmdm}")
	public ActionForward xmwhJdszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhJdszForm myForm = (XmwhJdszForm) form;
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

		XmwhJdszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @描述:查询所有项目，不包含当前项目
	 * @作者：ligl
	 * @日期：2013-4-24 下午02:20:21
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
	public ActionForward xmwhJdszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		// 获取所有其他的项目列表
		try {
			List<HashMap<String, String>> resultList = xmwhService
					.getOthers(xmdm);
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(resultList));
		} catch (Exception e) {
			// TODO 自动生成 catch 块........异常设置
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @描述:根据项目代码，获取已设置的不可兼得项目
	 * @作者：ligl
	 * @日期：2013-4-24 上午10:14:13
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public ActionForward getKjdxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> resultList = service.getKjdxm(xmdm);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", resultList);
		
		XmwhDao xmwhDao = new XmwhDao();
		HashMap<String, String> data = xmwhDao.getDataById(xmdm);
		String jdkg = "";
		if(data != null){
			jdkg = data.get("jdkg");
		}
		map.put("jdkg", jdkg);		
		JSONObject dataMap = JSONObject.fromMap(map);
		
		response.getWriter().print(dataMap);
		return null;
	}
}

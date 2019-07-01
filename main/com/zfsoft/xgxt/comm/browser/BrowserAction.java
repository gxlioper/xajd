/**
 * @部门:学工产品事业部
 * @日期：2013-8-26 上午10:18:53 
 */
package com.zfsoft.xgxt.comm.browser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 弹出层公共处理action
 * @类功能描述: 用于弹出层替换填出新页面跳转action
 * @作者： 张昌路[工号:982]
 * @时间： 2013-8-26 上午10:18:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BrowserAction extends SuperAction {
	public ActionForward divshow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BrowserForm bf = new BrowserForm();
		// 要弹出的内容
		String content = request.getParameter("content");
		// 弹出页面所学参数
		String param = request.getParameter("param");
		bf.setContent(content);
		bf.setParam(param);
		request.setAttribute("rs", bf);
		return mapping.findForward("show");
	}

	/**
	 * 
	 * @描述: 弹出提示框
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-27 下午05:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward showMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer btnH = 22;
		// 弹出内容
		String height = request.getParameter("height");
		String html = request.getParameter("html");
		Integer heightI = Integer.parseInt(height) - btnH;
		request.setAttribute("height", heightI);
		request.setAttribute("html", html);
		request.setAttribute("btnH", btnH);
		return mapping.findForward("message");
	}

	/**
	 * 
	 * @描述: 获取当前进度
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-18 下午04:26:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward getProgressBar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String key = request.getParameter("barkey");
		ProgressBar pb = BarSorce.getProgressBar(key);
		request.setAttribute("bar", pb);
		response.getWriter().print(JSONObject.fromObject(pb));
		if (pb.isFinish()) {
			pb.reset();
			BarSorce.finishBar(key);
		}
		// return mapping.findForward("progressbar");
		return null;
	}

	/**
	 * 
	 * @描述: 常用进度条示例
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-25 上午09:58:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward barDemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String key = request.getParameter("barkey");
		//情况key对应进度条，实际应用不建议使用。
	//	BarSorce.finishBar(key);
		ProgressBar pb = BarSorce.initProgressBar(key, 1000);
		for (int i = 0; i < 1000; i++) {
			// 进度增加1
			pb.change();
			// 进度增加 5
			// pb.change(5);
			// 模仿业务操作，延迟100豪秒
			Thread.sleep(100);
		}
		return null;
	}

	/**
	 * 
	 * @描述: 自动执行进度条示例
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-25 上午09:58:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward autoBarDemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String key = request.getParameter("barkey");
		ProgressBar pb = BarSorce.initAutoProgressBar(key);
		for (int i = 0; i < 1000; i++) {
			Thread.sleep(10);
		}
		//业务完成
		pb.autoFinish();
		return null;
	}
	/**
	 * 
	 * @描述: 进度条示例
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-6 上午11:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward demoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("demolist");
	}
}

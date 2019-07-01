package xgxt.pjpy.comm.pjpy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优页面跳转-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * 
 * @version 1.0
 */

public class PjpyDispatchAction extends DispatchAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 模块类型
		String mklx = mapping.getParameter();
		// 学校类型
		String xxdm = Base.xxdm;
		// 跳转路径
		String forward = "";

		if ("pjpy_pjjbsz".equalsIgnoreCase(mklx)) {// 评奖基本设置
			forward = getPjjbszForward(xxdm);
		} else if ("pjpy_pjlcsz".equalsIgnoreCase(mklx)) {// 评奖流程设置
			forward = getPjlcszForward(xxdm);
		} else if ("pjpy_pjryqd".equalsIgnoreCase(mklx)) {// 评奖人员确定
			forward = getPjryqdForward(xxdm);
		} else if ("pjpy_pjxmsz".equalsIgnoreCase(mklx)) {// 评奖项目设置
			forward = getPjxmszForward(xxdm);
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 获得评奖基本设置跳转路径
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author 伟大的骆
	 */
	private String getPjjbszForward(String xxdm) {

		String forward = "";
		// 获得个性化学校跳转
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjjbszPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// 通用跳转
			forward = PjpyGlobalsData.PJJBSZ_PATH;
		}

		return forward;
	}

	/**
	 * 获得评奖流程设置跳转路径
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author 伟大的骆
	 */
	private String getPjlcszForward(String xxdm) {

		String forward = "";
		// 获得个性化学校跳转
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjlcszPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// 通用跳转
			forward = PjpyGlobalsData.PJLCSZ_PATH;
		}

		return forward;
	}
	
	/**
	 * 获得评奖人员确定跳转路径
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author Qiu
	 */
	private String getPjryqdForward(String xxdm) {

		String forward = "";
		// 获得个性化学校跳转
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjryqdPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// 通用跳转
			forward = PjpyGlobalsData.PJRYQD_PATH;
		}

		return forward;
	}
	
	/**
	 * 获得评奖项目设置跳转路径
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author Qiu
	 */
	private String getPjxmszForward(String xxdm) {

		String forward = "";
		// 获得个性化学校跳转
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjxmszPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// 通用跳转
			forward = PjpyGlobalsData.PJXMSZ_PATH;
		}

		return forward;
	}
}

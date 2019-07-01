package xsgzgl.pjpy.szgyyq.pjhz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_评奖汇总_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyPjhzAction extends BasicAction {

	/**
	 * 评奖评优_评奖汇总_成绩汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyPjhzService service=new PjpyPjhzService();
		PjpyPjhzForm myForm=(PjpyPjhzForm)form;
		String go=request.getParameter("go");
		if("go".equals(go)){//成绩查询
			service.getPjhzList(request, myForm);
		}else if("export".equals(go)){//成绩导出
			String back=service.exportPjhzList(response, myForm);
			if(back==null){
				return null;
			}else{
				request.setAttribute("yhInfo", back);
				return new ActionForward("/yhInfo.do");
			}
		}
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		// 学期名称
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("cjhzManage");
	}
	
}

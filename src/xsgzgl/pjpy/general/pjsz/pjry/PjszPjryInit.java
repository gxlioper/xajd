package xsgzgl.pjpy.general.pjsz.pjry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖人员_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjszPjryInit extends BasicInit {

	/**
	 * 评奖人员_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initPjry(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		// 访问路径
		String path = "general_pjpy.do?method=pjszPjry";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 用户类型
		String userType = user.getUserType();
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 页面跳转
		String forward = request.getParameter("forward");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "userType", "forward" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, userType, forward };

		// ==================能否操作检测 begin==================
		String message = "";
		String operation = myService.getOperation("102");
		if ("later".equalsIgnoreCase(operation)) {
			message = "本步骤操作已经被相关用户提交，您不能再进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}else if("early".equalsIgnoreCase(operation)){
			message = "本步骤操作的前一步尚未被相关用户提交，您不能对其进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}
		// =================能否操作检测 end ===================
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setUserType(userType);
	}
}

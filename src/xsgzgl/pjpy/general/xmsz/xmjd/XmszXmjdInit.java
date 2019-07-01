package xsgzgl.pjpy.general.xmsz.xmjd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_项目兼得_通用_Init类
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

public class XmszXmjdInit {

	/**
	 * 项目兼得_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initXmjd(RequestForm rForm, PjpyGeneralForm model, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? model.getXmdm() : xmdm;
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
	}
}

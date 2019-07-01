package xsgzgl.pjpy.general.tjcx.tjmdhz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.tjcx.TjcxZcbjmdInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class TjcxTjmdhzInit extends BasicInit {
	
	/**
	 * 推荐名单汇总
	 * 
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void init(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxZcbjmdInterface service = myService.getTjcxZcbjmdService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_tjmdhz.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}

package xsgzgl.pjpy.general.djbg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;
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
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyDjbgInit extends BasicInit {

	/**
	 * 初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void init(RequestForm rForm, PjpyGeneralForm myForm,
			PjpyDjbgModel model, User user, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyDjbgInterface service = myService.getPjpyDjbgService(myForm);

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 学号
		String[] xh = model.getPrint_xh();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		// 登记表格内容
		if (xh != null && xh.length > 0) {
			List<HashMap<String, String>> list = service.getDjbgInfoList(model);
			request.setAttribute("rsList", list);
		} else {
			HashMap<String, Object> map = service.getDjbgInfo(model);
			request.setAttribute("rs", map);
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
	}
}

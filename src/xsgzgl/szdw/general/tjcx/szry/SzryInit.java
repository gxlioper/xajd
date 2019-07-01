package xsgzgl.szdw.general.tjcx.szry;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_统计查询_思政人员_Init类
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

public class SzryInit extends BasicInit {

	/**
	 * 思政人员【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSzrySearch(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "szdw_general_tjcx_szry.do";
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

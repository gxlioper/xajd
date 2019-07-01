package xsgzgl.szdw.general.szbb;

import java.util.HashMap;
import java.util.List;

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
 * Description: 思政队伍_思政队伍编班_Init类
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

public class SzbbInit extends BasicInit {

	/**
	 * 思政队伍编班【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSzbbSearch(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 年级
		String nj=request.getParameter("nj");
		// 学院
		String xydm=request.getParameter("xydm");
		// 专业
		String zydm=request.getParameter("zydm");
		// 班级
		String bjdm=request.getParameter("bjdm");
		// 访问路径
		String path = "szdw_general_szbb.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// ------------------默认加载 年级、学院、专业、班级 信息 begin--------------
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		// ------------------默认加载 年级、学院、专业、班级 信息 end--------------
		
	}
}

package xsgzgl.szdw.general.dwwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_思政队伍维护_Init类
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

public class DwwhInit extends BasicInit {

	/**
	 * 思政队伍维护【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initDwwhSearch(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		
		String path = myForm.getPath();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
	}

	/**
	 * 思政人员编班【设置】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initRybbSetting(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "szdw_general_dwwh.do";
		// 类型
		String lx = request.getParameter("lx");
		// 类型名称
		String lxmc ;
		if (Base.xxdm.equals("12834")) {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "大队长" : "中队长";
		}else {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "辅导员" : "班主任";
		}
		// 职工号
		String zgh = request.getParameter("zgh");
		// 其他字段
		String[] qtzd = new String[] { "lx", "zgh", "lxmc" };
		// 其他字段值
		String[] qtzdz = new String[] { lx, zgh, lxmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		//================初始化数据  begin=====================
		
		DwwhModel model = new DwwhModel();
		model.setLx(lx);
		model.setZgh(zgh);
		
		HashMap<String, String> map = service.getDwwh(model, user);
		request.setAttribute("rs", map);
		
		// 部门列表
		List<HashMap<String, String>> bmList = service.getBmList();
		request.setAttribute("bmpyList", bmList);
	
		//================初始化数据  end =====================
	}

	/**
	 * 初始化参数
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initParameter(SzdwGeneralForm myForm) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		service.initParameter();
	}
}

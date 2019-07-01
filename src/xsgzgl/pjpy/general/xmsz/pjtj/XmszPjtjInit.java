package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlService;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_评奖条件_通用_Init类
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

public class XmszPjtjInit extends BasicInit {

	/**
	 * 评奖条件_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initPjtj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		XmszPjtjService pjtjService = new XmszPjtjService();
		PjszBjdlService bjdlService = new PjszBjdlService();
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		
		// 访问路径
		String path = "pjpy_pjsz_pjxm.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		

		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		boolean checkXssq=pjxmService.checkXssq(pjxmModel, user);
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm","checkXssq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm ,String.valueOf(checkXssq)};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// 评奖条件列表
		List<HashMap<String, String>> pjtjList = pjtjService.getPjtjList();
		request.setAttribute("pjtjList", pjtjList);

		// 班级大类列表
		List<HashMap<String, String>> bjdlList = bjdlService.getBjdlList();
		request.setAttribute("bjdlList", bjdlList);
	}
}

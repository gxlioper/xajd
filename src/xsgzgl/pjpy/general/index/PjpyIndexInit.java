package xsgzgl.pjpy.general.index;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_Init类
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

public class PjpyIndexInit extends BasicInit{

	/**
	 * 评奖首页_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initIndex(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		
		// 访问路径
		String path = "pjpy_general_index.do";
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
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);
		
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * 结束本次评奖_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initPjpyEnd(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		
		// 访问路径
		String path = "pjpy_general_index.do";
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
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq };
		
		List<HashMap<String,String>>bcpjtjInfo= service.getBcpjtjInfo(user);
		
		request.setAttribute("bcpjtjInfo", bcpjtjInfo);
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
	}
}

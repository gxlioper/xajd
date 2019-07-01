package xsgzgl.pjpy.general.xmsz.rssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
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
 * Description: 评奖评优_项目设置_人数设置_通用_Init类
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

public class XmszRsszInit extends BasicInit {

	/**
	 * 人数设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initRssz(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszService service = new XmszRsszService();
		XmszRsszInterface rsszService = myService.getXmszRsszService(myForm);
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
	
		// 访问路径
		String path = "pjpy_xmsz_rssz.do";
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
		xmdm = Base.isNull(xmdm) ? myForm.getXmdm() : xmdm;
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		PjszPjxmModel pjxmModel = pjxmService.getPjxmModel(xmdm, user);
		PjpyGeneralForm.setPjxmModel(pjxmModel);
		
		boolean checkXssq=pjxmService.checkXssq(pjxmModel, user);

		// 控制范围
		String szfw = pjxmModel.getKzfw();
		// 特殊人群
		String tsrq = pjxmModel.getTsrq();
		// 控制范围
		String kzfw = pjxmModel.getKzfw();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "searchType","checkXssq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, kzfw,String.valueOf(checkXssq) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// 该项目是否初始化过
		boolean isExists = service.isExists("xg_pjpy_rsszb", "xmdm", xmdm);

		if (!isExists) {
			rsszService.initRsszb(xmdm, szfw, tsrq, user);
		}
	}
}

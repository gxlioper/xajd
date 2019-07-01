package xsgzgl.pjpy.general.pjsz.cpxz;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_参评小组_通用_Init类
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

public class PjszCpxzInit extends BasicInit {

	/**
	 * 参评小组_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initCpxz(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// 访问路径
		String path = "pjpy_pjsz_cpxz.do";
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
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		List<HashMap<String, String>> cpzList = getCpzList();
		request.setAttribute("cpzList", cpzList);
	}

	/**
	 * 获得参评组列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getCpzList() {

		DAO dao = DAO.getInstance();
		String sql = "select distinct cpzmc dm,cpzmc mc from xg_pjpy_cpzb order by cpzmc ";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
}

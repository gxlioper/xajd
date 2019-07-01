package xsgzgl.pjpy.general.pjsz.zcxm;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_综测项目_通用_Init类
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

public class PjszZcxmInit extends BasicInit {

	/**
	 * 评奖首页_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initZcxm(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// 访问路径
		String path = "pjpy_pjsz_zcxm.do";
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
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 页面跳转
		String forward = request.getParameter("forward");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "forward" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, forward };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// 综测项目
		List<HashMap<String, String>> zcxmList = getZcxmList();
		request.setAttribute("zcxmList", zcxmList);
		// 综测项目比例
		List<HashMap<String, String>> zcblList = getZcblList();
		request.setAttribute("zcblList", zcblList);
		// 上级代码
		List<HashMap<String, String>> sjdmList = getSjdmList();
		request.setAttribute("sjdmList", sjdmList);
		// 比例代码
		List<HashMap<String, String>> bldmList = getBldmList();
		request.setAttribute("bldmList", bldmList);
	}

	/**
	 * 获得综测项目列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getZcxmList() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select xmdm,xmmc,xmjb,sjdm,lyb, ");
		sql.append(" mrxm,jjf ");
		sql.append(" from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" order by to_number(xmjb),a.xmdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "xmdm",
						"xmmc", "xmjb", "sjdm", "lyb", "mrxm", "jjf" });

		return list;
	}

	/**
	 * 获得综测项目比例列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getZcblList() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select xn,xq,nd,xmdm,xmmc,xmjb,sjdm,bldm,blmc,bl ");
		sql.append(" from xg_view_pjpy_zcxm a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" order by to_number(xmjb),a.xmdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "xn", "xq",
						"nd", "xmdm", "xmmc", "xmjb", "sjdm", "bldm", "blmc",
						"bl" });
		return list;
	}

	/**
	 * 获取上级代码列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSjdmList() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// sql
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct(sjdm)sjdm from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1  ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");

		sql.append("  order by sjdm nulls first ");

		String[] colList = { "sjdm" };
		return dao.getList(sql.toString(), new String[] { pjxn, pjxq, pjnd },
				colList);
	}

	/**
	 * 获得综测比例列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getBldmList() {
		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append("select bldm,a.blmc ");
		sql.append("from xg_pjpy_zcbldmb a ");
		sql.append("where 1=1 ");
		sql.append("and a.xn=? ");
		sql.append("and a.xq=? ");
		sql.append("and a.nd=? ");
		sql.append("order by to_number(bldm) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd }, new String[] { "bldm",
						"blmc" });
		return list;
	}
}

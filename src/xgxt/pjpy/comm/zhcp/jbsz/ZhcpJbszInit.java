package xgxt.pjpy.comm.zhcp.jbsz;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_基本设置_init类
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

public class ZhcpJbszInit {

	/**
	 * 德育评分设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getDypfszRForm(RequestForm rForm, ZhcpJbszForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 功能模块
		String gnmk = "pjpy";
		// 评奖基本设置
		String menu = "dypfsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_jbsz_dypf.do";

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 综测周期
		String zczq = model.getZczq();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc", "zczq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}

	/**
	 * 综测加分设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getZcjfszRForm(RequestForm rForm, ZhcpJbszForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 功能模块
		String gnmk = "pjpy";
		// 评奖基本设置
		String menu = "zcjfsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zhcp_jbsz_zcjf.do";

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();
		// 综测周期
		String zczq = model.getZczq();
		// 综测加分类型
		String zcjflx = model.getZcjflx();
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc",
				"zczq", "zcjflx" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, zczq, zcjflx };

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
}

package xsgzgl.pjpy.general.pjsz.bjdl;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_班级大类_通用_Init类
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

public class PjszBjdlInit extends BasicInit {

	/**
	 * 班级大类_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initBjdl(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);

		// 访问路径
		String path = "general_pjpy.do?method=pjszBjdl";
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

		// ==================能否操作检测 begin==================
		String message = "";
		String operation = myService.getOperation("115");
		if ("later".equalsIgnoreCase(operation)) {
			message = "本步骤操作已经被相关用户提交，您不能再进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "本步骤操作的前一步尚未被相关用户提交，您不能对其进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}
		// =================能否操作检测 end ===================

		// 班级大类列表
		List<HashMap<String, String>> bjdlList = getBjdlList();
		request.setAttribute("bjdlList", bjdlList);

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * 获得参评组列表
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getBjdlList() {

		DAO dao = DAO.getInstance();
		String sql = "select bjdldm dm,bjdlmc mc from xg_pjpy_bjdldmb order by bjdldm ";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
}

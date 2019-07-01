package xsgzgl.xszz.jhzy.gjzxj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家助学金_金华职业_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class GjzxjService extends CommService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GjzxjDAO dao = new GjzxjDAO();

	// ===============国家助学金申请begin=====================

	/**
	 * 获得国家助学金申请表头
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZxjsqTop(GjzxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "shzt" };
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称", "申请时间", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得国家助学金申请结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getZxjsqList(GjzxjForm myForm, GjzxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZxjsqList(myForm, user);
	}

	/**
	 * 获得国家助学金申请学生基本信息
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getGjzxjInfo(String xh, String xn) {
		return dao.getGjzxjInfo(xh, xn);
	}

	/**
	 * 保存国家助学金申请
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public Boolean saveGjzxjSq(GjzxjModel model, User user) {

		boolean flag = false;

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// 学年
		String xn = csszModel.getXn();

		String xh = model.getXh();// 学号

		String sqly = model.getSqly();// 申请理由

		boolean isExists = isExists("xg_xszz_jhzy_gjzxjsqb", "xn||xh", xn + xh);

		try {
			if (isExists) {
				flag = dao.updateZxjsqb(xh, xn, sqly, user);
			} else {
				flag = dao.insertZxjsqb(xh, xn, sqly, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 删除国家助学金申请
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public Boolean deleteGjzxjSq(GjzxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();
		try {

			flag = dao.deleteZxjsqb(pkValue, user);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 创建流程跟踪HTML
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createLcgzHtml(GjzxjModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		String pk = model.getPk();
		String xn = pk.split("luojw")[0];
		String xh = pk.split("luojw")[1];

		HashMap<String, String> map = getGjzxjInfo(xh, xn);
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"6\">	");
		html.append("<span>国家助学金认定审核流程跟踪</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th>");
		html.append("序号");
		html.append("</th>");
		html.append("<th>");
		html.append("用户类型");
		html.append("</th>");
		html.append("<th>");
		html.append("申请（审核）人");
		html.append("</th>");
		html.append("<th>");
		html.append("处理阶段");
		html.append("</th>");
		html.append("<th>");
		html.append("处理时间");
		html.append("</th>");
		html.append("<th>");
		html.append("推荐档次");
		html.append("</th>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("1");
		html.append("</td>");
		html.append("<td>");
		html.append("学生型");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("xm"));
		html.append("</td>");
		html.append("<td>");
		html.append("申请");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("sqsj"));
		html.append("</td>");
		html.append("<td>");
		html.append("");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("2");
		html.append("</td>");
		html.append("<td>");
		html.append("班主任");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("bzrxm")) ? map.get("bzrxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("bzrsh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("bzrshsj")) ? map.get("bzrshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("bzrtjdc")) ? map.get("bzrtjdc") : "");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("3");
		html.append("</td>");
		html.append("<td>");
		html.append("辅导员");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("fdyxm")) ? map.get("fdyxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("fdysh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("fdyshsj")) ? map.get("fdyshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("fdytjdc")) ? map.get("fdytjdc") : "");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("4");
		html.append("</td>");
		html.append("<td>");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		html.append(Base.YXPZXY_KEY);
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xyxm")) ? map.get("xyxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("xysh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xyshsj")) ? map.get("xyshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xytjdc")) ? map.get("xytjdc") : "");
		html.append("</td>");
		html.append("</tr>");

		html.append("<tr>");
		html.append("<td>");
		html.append("5");
		html.append("</td>");
		html.append("<td>");
		html.append("学校");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xxxm")) ? map.get("xxxm") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(map.get("xxsh"));
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xxshsj")) ? map.get("xxshsj") : "");
		html.append("</td>");
		html.append("<td>");
		html.append(!Base.isNull(map.get("xxtjdc")) ? map.get("xxtjdc") : "");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	// ===============国家助学金申请end=====================

	// ===============国家助学金审核begin=====================

	/**
	 * 获得国家助学金审核表头
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZxjshTop(GjzxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "knsdj", "sqsj", "shzt", "tjdc" };
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称", "困难生等级", "申请时间", "审核状态", "推荐档次" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得国家助学金审核结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getZxjshList(GjzxjForm myForm, GjzxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZxjshList(myForm, user);
	}

	/**
	 * 保存国家助学金审核
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public Boolean saveGjzxjSh(GjzxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();// 主键

		String xn = model.getXn();// 学年

		String xh = model.getXh();// 学号

		String shyj = model.getShyj();// 审核意见

		String shzt = model.getShzt();// 审核状态

		String userStatus = user.getUserStatus();// 用户身份

		String shsj = dao.getNowTime("YYYYMMDD");// 审核时间

		String tjdc = model.getTjdc();// 推荐档次

		try {
			if (pkValue != null && pkValue.length > 0) {
				flag = dao.updateZxjsqb(pkValue, shzt, shsj, shyj, tjdc,
						userStatus, user);
			} else {
				flag = dao.updateZxjsqb(xh, xn, shzt, shsj, shyj, tjdc,
						userStatus, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	// ===============国家助学金审核end=====================

	// ===============国家助学金结果begin=====================

	/**
	 * 获得国家助学金结果表头
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZxjjgTop(GjzxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "knstjdc", "sqsj", "tjdc" };
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称", "困难生级别", "申请时间", "推荐档次" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得国家助学金结果结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getZxjjgList(GjzxjForm myForm, GjzxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZxjjgList(myForm, user);
	}

	// ===============国家助学金结果end=====================
}

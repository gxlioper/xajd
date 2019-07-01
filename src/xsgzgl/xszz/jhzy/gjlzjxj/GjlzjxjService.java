package xsgzgl.xszz.jhzy.gjlzjxj;

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
import xgxt.utils.date.DateUtils;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家励志奖学金_金华职业_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */

public class GjlzjxjService extends CommService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	GjlzjxjDAO dao = new GjlzjxjDAO();

	// ===============困难生申请begin=====================

	/**
	 * 获得国家励志奖学金申请表头
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public List<HashMap<String, String>> getGjlzjxjTop(GjlzjxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "rddc", "sqsj", "shzt" };
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称", "困难生认定档次", "申请时间", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得国家励志奖学金申请结果集
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public ArrayList<String[]> getGjlzjxjsqList(GjlzjxjForm myForm, GjlzjxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGjlzjxjsq(myForm, user);
	}


	/**
	 * 获得国家励志奖学金申请学生基本信息
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public HashMap<String, String> getGjlzjxjInfo(String xh, String xn) {
		return dao.getGjlzjxjInfo(xh, xn);
	}

	/**
	 * 保存国家励志奖学金申请
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public Boolean saveGjlzjxjSq(GjlzjxjModel model, User user) {

		boolean flag = false;

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// 学年
		String xn = csszModel.getXn();

		String xh = model.getXh();// 学号

		List<String[]> params = new ArrayList<String[]>(){};
		String[] value = {DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDayOfMonth(),model.getBxkms(),model.getJgms(),
				model.getCjpm(),model.getSxzhkppm(),model.getZhkppm(),model.getSqly(),
				model.getHjsj1(),model.getHjmc1(),model.getBjdw1(),model.getHjsj2(),
				model.getHjmc2(),model.getBjdw2(),model.getHjsj3(),model.getHjmc3(),
				model.getBjdw3(),model.getHjsj4(),model.getHjmc4(),model.getBjdw4(),model.getXh(),model.getXn()};
		params.add(value);

		boolean isExists = isExists("xg_xszz_jhzy_gjlzjxjsqb", "xn||xh", xn + xh);

		try {
			if (isExists) {
				flag = dao.updateGjlzjxjsqb(params, user);
			} else {
				flag = dao.insertGjlzjxjsqb(params, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 删除国家励志奖学金申请
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public Boolean deleteGjlzjxjSq(GjlzjxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();
		try {

			flag = dao.deleteGjlzjxjsqb(pkValue, user);

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 创建流程跟踪HTML
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws IOException
	 */
	public void createLcgzHtml(GjlzjxjModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		String pk = model.getPk();
		String xn = pk.split("!!luojw!!")[0];
		String xh = pk.split("!!luojw!!")[1];

		HashMap<String, String> map = getGjlzjxjInfo(xh, xn);
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"6\">	");
		html.append("<span>国家励志奖学金审核流程跟踪</span>");
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
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	// ===============国家励志奖学金申请end=====================

	// ===============国家励志奖学金审核begin=====================
	/**
	 * 获得国家励志奖学金审核表头
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public List<HashMap<String, String>> getKnsshTop(GjlzjxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "shzt", "tjdc" };
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称", "申请时间", "审核状态", "推荐档次" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得国家励志奖学金审核结果集
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public ArrayList<String[]> getGjlzjxjshList(GjlzjxjForm myForm, GjlzjxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGjlzjxjshList(myForm, user);
	}

	/**
	 * 保存国家励志奖学金审核
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public Boolean saveGjlzjxjSh(GjlzjxjModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();// 主键

		String xn = model.getXn();// 学年

		String xh = model.getXh();// 学号

		String shyj = model.getShyj();// 审核意见

		String shzt = model.getShzt();// 审核状态

		String userStatus = user.getUserStatus();// 用户身份

		String shsj = dao.getNowTime("YYYYMMDD");// 审核时间


		try {
			if (pkValue != null && pkValue.length > 0) {
				flag = dao.updateGjlzjxjsh(pkValue, shzt, shsj, shyj,
						userStatus, user);
			} else {
				flag = dao.updateGjlzjxjsh(xh, xn, shzt, shsj, shyj,
						userStatus, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	// ===============困难生审核end=====================

	// ===============困难生结果begin=====================

	/**
	 * 获得国家励志奖学金结果表头
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public List<HashMap<String, String>> getGjlzjxjjgTop(GjlzjxjModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "tjdc", ""};
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称","困难生认定档次", "申请时间", "奖项名称" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得国家励志奖学金结果结果集
	 * 
	 * @date 2012-12-05
	 * @author lt
	 */
	public ArrayList<String[]> getGjlzjxjjgList(GjlzjxjForm myForm, GjlzjxjModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGjlzjxjjgList(myForm, user);
	}
	
	public String cxBjrs(String xh) {
		return dao.cxBjrs(xh);
	}
	/**
	 * 判断学生是否国家励志奖学金
	 * @param xh
	 * @param xn
	 * @return
	 */
	public boolean isKns(String xh, String xn) {
		return "0".equalsIgnoreCase(dao.isKns(xh, xn)) ? false : true;
	}
	
}

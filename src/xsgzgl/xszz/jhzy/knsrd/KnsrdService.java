package xsgzgl.xszz.jhzy.knsrd;

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
 * Description: 学生资助_困难生认定_金华职业_Service类
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

public class KnsrdService extends CommService {


	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	KnsrdDAO dao = new KnsrdDAO();

	// ===============困难生申请begin=====================

	/**
	 * 获得困难生申请表头
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKnssqTop(KnsrdModel model, User user) {
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
	 * 获得困难生申请结果集
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getKnssqList(KnsrdForm myForm, KnsrdModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getKnssqList(myForm, user);
	}

	/**
	 * 获得困难生类别列表
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKnslbList(KnsrdModel model,
			User user) {
		List<HashMap<String, String>> list = dao.getKnslbList();
		return list;
	}

	/**
	 * 创建困难生类别HTML
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createKnslbHtml(KnsrdModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// 困难生类别列表
		List<HashMap<String, String>> knslbList = getKnslbList(model, user);

		StringBuilder html = new StringBuilder();

		if (knslbList != null && knslbList.size() > 0) {
			int line_num = 2;
			knslbList = getKnslbTableList(knslbList, model, line_num);
			int row_num = knslbList.size() / line_num;

			html.append("<table width=\"100%\">");
			for (int i = 0; i < row_num; i++) {
				html.append("<tr>");
				for (int j = 0; j < knslbList.size(); j++) {
					String dm = knslbList.get(j).get("dm");
					String mc = knslbList.get(j).get("mc");
					String checked = knslbList.get(j).get("checked");
					String num = knslbList.get(j).get("num");

					if (i + 1 == Integer.parseInt(num)) {
						html.append("<td>");
						if (!Base.isNull(dm)) {
							html.append("<input type=\"checkbox\" ");
							html.append("name=\"checkbox_sqlb\"");
							if ("true".equalsIgnoreCase(checked)) {
								html.append("checked=\"checked\"");
							}
							html.append("value=\"" + dm + "\"");
							html.append("/>");
						}
						html.append(mc);
						html.append("</td>");
					}
				}
				html.append("</tr>");
			}
			html.append("</table>");
		}

		response.getWriter().print(html.toString());
	}

	/**
	 * 处理困难生类别数据
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKnslbTableList(
			List<HashMap<String, String>> knslbList, KnsrdModel model,
			int line_num) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		int space_num = knslbList.size() % line_num == 0 ? 0 : line_num
				- knslbList.size() % line_num;
		int row_num = 1;

		String[] sqlb = null;
		if (!Base.isNull(model.getSqlb())
				&& model.getSqlb().split("luojw").length > 0) {
			sqlb = model.getSqlb().split("luojw");
		}
		for (int i = 0; i < knslbList.size(); i++) {
			HashMap<String, String> map = knslbList.get(i);
			if (i != 0 && i % line_num == 0) {
				row_num++;
			}
			map.put("num", String.valueOf(row_num));
			if (sqlb != null && sqlb.length > 0) {
				for (int j = 0; j < sqlb.length; j++) {
					if (sqlb[j].equalsIgnoreCase(map.get("dm"))) {
						map.put("checked", "true");
					}
				}
			}
			list.add(map);
		}

		for (int i = 0; i < space_num; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", " ");
			map.put("num", String.valueOf(row_num));
			list.add(map);
		}

		return list;
	}

	/**
	 * 获得困难生申请学生基本信息
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getKnsrdInfo(String xh, String xn) {
		return dao.getKnsrdInfo(xh, xn);
	}

	/**
	 * 保存困难生申请
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public Boolean saveKnsrdSq(KnsrdModel model, User user) {

		boolean flag = false;

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// 学年
		String xn = csszModel.getXn();

		String xh = model.getXh();// 学号

		String sqlb = model.getSqlb();// 申请类别

		String sqly = model.getSqly();// 申请理由

		boolean isExists = isExists("xg_xszz_jhzy_knssqb", "xn||xh", xn + xh);

		try {
			if (isExists) {
				flag = dao.updateKnssqb(xh, xn, sqlb, sqly, user);
			} else {
				flag = dao.insertKnssqb(xh, xn, sqlb, sqly, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 删除困难生申请
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public Boolean deleteKnsrdSq(KnsrdModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();
		try {

			flag = dao.deleteKnssqb(pkValue, user);

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
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public void createLcgzHtml(KnsrdModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		String pk = model.getPk();
		String xn = pk.split("luojw")[0];
		String xh = pk.split("luojw")[1];

		HashMap<String, String> map = getKnsrdInfo(xh, xn);
		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"6\">	");
		html.append("<span>困难生认定审核流程跟踪</span>");
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

	// ===============困难生申请end=====================

	// ===============困难生审核begin=====================

	/**
	 * 获得困难生审核表头
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKnsshTop(KnsrdModel model, User user) {
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
	 * 获得困难生审核结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getKnsshList(KnsrdForm myForm, KnsrdModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getKnsshList(myForm, user);
	}

	/**
	 * 获得困难生类别列表
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKnslbList(String[] dm) {
		return dao.getKnslbList(dm);
	}

	/**
	 * 保存困难生审核
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public Boolean saveKnsrdSh(KnsrdModel model, User user) {

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
				flag = dao.updateKnssqb(pkValue, shzt, shsj, shyj, tjdc,
						userStatus, user);
			} else {
				flag = dao.updateKnssqb(xh, xn, shzt, shsj, shyj, tjdc,
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
	 * 获得困难生结果表头
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKnsjgTop(KnsrdModel model, User user) {
		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "tjdc" };
		String[] cn = new String[] { "", "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
				"班级名称", "申请时间", "推荐档次" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得困难生结果结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getKnsjgList(KnsrdForm myForm, KnsrdModel model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getKnsjgList(myForm, user);
	}

	/**
	 * 保存困难生推荐档次
	 * 
	 * @date 2012-12-06
	 * @author 伟大的骆
	 */
	public Boolean saveKnsrdTjdc(KnsrdModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();// 主键

		String shsj = dao.getNowTime("YYYYMMDD");// 审核时间

		String tjdc = model.getTjdc();// 推荐档次

		try {
				flag = dao.updateKnssqb(pkValue,  shsj, tjdc, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	// ===============困难生结果end=====================
}

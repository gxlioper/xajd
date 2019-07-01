package xsgzgl.xsxx.general.dljc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxDljcInterface;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生信息_登录检测_通用_Service类
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

public class XsxxDljcService extends CommService implements XsxxDljcInterface {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	XsxxDljcDAO dao = new XsxxDljcDAO();

	// ===================查询页面 begin=============================

	/**
	 * 获得登录检测表头
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsxxDljclTop(XsxxDljcModel model,
			User user) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "nj", "xymc", "zymc",
				"bjmc", "xxwsmc" };
		String[] cn = new String[] { "", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
				"班级名称", "信息完善" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得登录检测结果集
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXsxxDljcList(XsxxGeneralForm myForm,
			XsxxDljcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxDljcList(myForm, model, user);
	}

	/**
	 * 构造登录检测HTML
	 * 
	 * @date 2012-12-04
	 * @author 伟大的骆
	 */
	public String createXsxxDljcHTML(SearchRsModel rsModel,
			XsxxDljcModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 重置登录检测
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public boolean resetDljc(XsxxGeneralForm myForm, XsxxDljcModel model,
			User user) {

		// 主键
		String[] pkValue = model.getPkValue();
		// 勾选与否
		String checked = model.getChecked();

		// 年级
		String[] nj = model.getNj();
		if (nj != null && nj.length > 0 && Base.isNull(nj[0])) {
			nj = null;
		}

		// 学院
		String[] xy = model.getXy();
		if (xy != null && xy.length > 0 && Base.isNull(xy[0])) {
			xy = null;
		}

		// 专业
		String[] zy = model.getZy();
		if (zy != null && zy.length > 0 && Base.isNull(zy[0])) {
			zy = null;
		}

		// 班级
		String[] bj = model.getBj();
		if (bj != null && bj.length > 0 && Base.isNull(bj[0])) {
			bj = null;
		}

		boolean flag = false;

		try {
			if ("yes".equalsIgnoreCase(checked)) {// 【勾选】
				flag = dao.deleteDljcb(pkValue, user);
				if (flag) {
					flag = dao.insertDljcb(pkValue, user);
				}
			} else if ("no".equalsIgnoreCase(checked)) {// 【未勾选】
				flag = dao.deleteDljcb(nj, xy, zy, bj, user);
				if (flag) {
					flag = dao.insertDljcb(nj, xy, zy, bj, user);
				}
			}

			if (flag) {
				dao.initXsfzxxb(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// ===================查询页面 end=============================

	// ===================设置页面 begin=============================

	/**
	 * 创建字段设置Div
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public void createZdszDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		// 所属类型
		List<HashMap<String, String>> sslxList = dao.getSslxList();
		// 字段设置
		List<HashMap<String, String>> zdList = dao.getZdszList();

		for (int i = 0; i < sslxList.size(); i++) {

			HashMap<String, String> map = sslxList.get(i);
			String sslx = map.get("sslx");

			List<HashMap<String, String>> zdInfoList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> zdInfo = new HashMap<String, String>();
			zdInfo.put("zd", "all_" + i);
			zdInfo.put("zdm", "全选");
			zdInfo.put("zdlx", "all");

			zdInfoList.add(zdInfo);

			for (int j = 0; j < zdList.size(); j++) {

				zdInfo = zdList.get(j);

				if (sslx.equalsIgnoreCase(zdInfo.get("sslx"))) {
					zdInfoList.add(zdInfo);
				}
			}

			int space = 5 - zdInfoList.size() % 5;
			if (space != 0) {
				for (int j = 0; j < space; j++) {
					zdInfo = new HashMap<String, String>();
					zdInfo.put("zd", "");
					zdInfo.put("zdm", "");

					zdInfoList.add(zdInfo);
				}
			}

			// 计数器
			int num = 0;
			boolean br = true;

			html.append("<table class=\"formlist\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<th colspan=\"5\">");
			html.append("<span>" + sslx + "</span>");
			html.append("</th>");
			html.append("</tr>");

			for (int j = 0; j < zdInfoList.size(); j++) {

				zdInfo = zdInfoList.get(j);

				// 字段
				String zd = zdInfo.get("zd");
				// 字段名
				String zdm = zdInfo.get("zdm");
				// 字段类型
				String zdlx = zdInfo.get("zdlx");
				// 标志位
				String flag = zdInfo.get("flag");

				// 是否选中
				String checked = "";

				if ("yes".equalsIgnoreCase(flag)) {
					checked = "checked";
				}

				if (br) {
					html.append("<tr>");
					br = false;
				}

				html.append("<td style=\"width:15%\">");

				if ("all".equalsIgnoreCase(zdlx)) {
					html.append("<input type=\"checkbox\" id=\"cb_all_" + i
							+ "\" value=\"all\" onclick=\"checkAll('" + i
							+ "')\"/>");
				} else if (Base.isNull(zdlx)) {

				} else if ("disabled".equalsIgnoreCase(zdlx)) {
					html.append("<input type=\"checkbox\" value=\"" + zd
							+ "\" disabled=\"disabled\"/>");
				} else {
					html.append("<input type=\"checkbox\" name=\"cb_" + i
							+ "\" value=\"" + zd + "\" " + checked + "/>");
				}
				html.append(zdm);
				html.append("</td>");

				num++;

				if (num >= 5) {
					br = true;
					num = 0;
				}

				if (br) {
					html.append("</tr>");
				}

			}

			html.append("</thead>");

			html.append("</table>");
		}

		response.getWriter().print(html.toString());
	}

	/**
	 * 保存字段设置
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public boolean saveZdsz(XsxxDljcModel model, User user) {

		String[] zd = model.getZd();

		boolean flag = false;

		try {
			flag = dao.deleteDljcszb(zd, user);

			if (flag) {
				flag = dao.insertDljcszb(zd, user);
			}

			if (flag) {

				flag = dao.deleteDljcb(null, null, null, null, user);

				if (flag) {
					flag = dao.insertDljcb(null, null, null, null, user);
				}
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	// ===================设置页面 end=============================

	// ===================信息完善 begin=============================

	/**
	 * 是否完善学生信息
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public boolean isXxws(String xh) {

		String xxws = dao.getXxws(xh);

		boolean flag = true;

		if ("no".equalsIgnoreCase(xxws)) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 创建信息完善Div
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public void createXxwsDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		String xh = user.getUserName();

		// 字段设置信息
		List<HashMap<String, String>> list = getWszdList(user);

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"4\">");
		html.append("<span><font color=\"red\">请准确填写以下信息。（所有信息不能为空）</font></span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		if (list != null && list.size() > 0) {

			int row_num = list.size() / 2;

			html.append("<tbody>");

			for (int i = 0; i < row_num; i++) {
				html.append("<tr>");

				HashMap<String, String> left = list.get(i * 2);

				html.append("<th width=\"20%\">");
				if (!Base.isNull(left.get("zdm"))) {
					html.append("<span ");
					html.append("id=\"display_" + left.get("zd") + "\" >");
					html.append(left.get("zdm"));
					html.append("</span>");
				}
				html.append("</th>");
				html.append("<td width=\"30%\">");
				if (!Base.isNull(left.get("zdm"))) {
					html.append(createObjectHtml(left));
				}
				html.append("</td>");

				HashMap<String, String> right = list.get(i * 2 + 1);

				html.append("<th width=\"20%\">");
				if (!Base.isNull(right.get("zdm"))) {
					html.append("<span ");
					html.append("id=\"display_" + right.get("zd") + "\" >");
					html.append(right.get("zdm"));
					html.append("</span>");
				}
				html.append("</th>");
				html.append("<td>");
				if (!Base.isNull(right.get("zdm"))) {
					html.append(createObjectHtml(right));
				}
				html.append("</td>");
				html.append("</tr>");
			}

			html.append("</tbody>");

			html.append("<tfoot>");
			html.append("<tr>");
			html.append("<td colspan=\"4\">");
			html.append("<div class=\"btn\">");
			html
					.append("<button type=\"button\"  id=\"btn_save\"  onclick=\"checksSaveXxws();\">");
			html.append("保 存</button>");
			html.append("</div>");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tfoot>");

		} else {
			html.append("<tbody>");
			html.append("<tr>");
			html.append("<td colspan=\"4\">");
			html.append("管理员尚未配置好相应信息");
			html.append("<br/>");
			html.append("请联系管理员");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tbody>");
		}
		html.append("</table>");
		
		html.append("<font color=\"red\">");
		html.append("&nbsp;&nbsp;&nbsp;&nbsp;以上信息是学校联系你的重要途径，");
		html.append("以后将通过联系电话发送评优、评奖、重要活动等重要信息。");
		html.append("为保护你的个人权益，请确保以上信息准确，若有变动，及时更新。");
		html.append("<font>");
		
		response.getWriter().print(html.toString());

	}

	/**
	 * 获得完善信息列表
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getWszdList(User user) {

		// 字段设置信息
		List<HashMap<String, String>> zdList = dao.getWszdList();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			ArrayList<String> xsxxb_column = new ArrayList<String>();
			ArrayList<String> xsfzxxb_column = new ArrayList<String>();

			for (int i = 0; i < zdList.size(); i++) {

				if ("xsxxb".equalsIgnoreCase(zdList.get(i).get("ssb"))) {
					xsxxb_column.add(zdList.get(i).get("zd"));
				} else if ("xsfzxxb".equalsIgnoreCase(zdList.get(i).get("ssb"))) {
					xsfzxxb_column.add(zdList.get(i).get("zd"));
				}
			}

			HashMap<String, String> map = new HashMap<String, String>();

			HashMap<String, String> xsxxb = new HashMap<String, String>();
			if (xsxxb_column != null && xsxxb_column.size() > 0) {

				xsxxb = getRsInfo("xsxxb", "xh", user.getUserName(),
						xsxxb_column.toArray(new String[] {}));
			}

			HashMap<String, String> xsfzxxb = new HashMap<String, String>();
			if (xsfzxxb_column != null && xsfzxxb_column.size() > 0) {
				xsfzxxb = getRsInfo("xsfzxxb", "xh", user.getUserName(),
						xsfzxxb_column.toArray(new String[] {}));
			}

			map.putAll(xsxxb);
			map.putAll(xsfzxxb);

			for (int i = 0; i < zdList.size(); i++) {
				HashMap<String, String> zdInfo = zdList.get(i);

				String content = map.get(zdInfo.get("zd"));

				if (Base.isNull(content)) {
					content = "";
				}
				zdInfo.put("content", content);
				list.add(zdInfo);
			}
		}

		if (list != null && list.size() > 0 && list.size() % 2 == 1) {
			HashMap<String, String> zdInfo = new HashMap<String, String>();
			list.add(zdInfo);
		}

		return list;
	}

	/**
	 * 创建对象HTML
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	private String createObjectHtml(HashMap<String, String> map) {

		StringBuilder html = new StringBuilder();

		// 字段类型
		String zdlx = map.get("zdlx");

		if ("text".equalsIgnoreCase(zdlx)) {// 输入框(TEXT)
			html.append(createTextHtml(map));
		} else if ("textarea".equalsIgnoreCase(zdlx)) {// 文本域(TEXTAREA)
			// html.append(createTextAreaHtml(map));
		} else if ("calendar".equalsIgnoreCase(zdlx)) {// 日期控件(CALENDAR)
			html.append(createCalendarHtml(map));
		} else if ("select".equalsIgnoreCase(zdlx)) {// 下拉列表(SELECT)
			html.append(createSelectHtml(map));
		} else if ("area".equalsIgnoreCase(zdlx)) {// 行政地区(AREA)
			// html.append(createAreaHtml(map));
		} else if ("display".equalsIgnoreCase(zdlx)) {// 显示(DISPLAY)
			// html.append(createDisplayHtml(map));
		} else if ("department".equalsIgnoreCase(zdlx)) {// 部门(DEPARTMENT)
			// html.append(createStuPicHtml(map));
		} else if ("class".equalsIgnoreCase(zdlx)) {// 班级(CLASS)
			// html.append(createClassHtml(map));
		} else if ("stu_pic".equalsIgnoreCase(zdlx)) {// 学生照片(STUPIC)
			// html.append(createStuPicHtml(map));
		} else if ("stu_choose".equalsIgnoreCase(zdlx)) {// 学生选择(STUCHOOSE)
			// html.append(createStuChooseHtml(map));
		}

		return html.toString();
	}

	/**
	 * 加載Text對象
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	private String createTextHtml(HashMap<String, String> map) {

		String zd = map.get("zd");// '字段';
		String checked = map.get("checked");// '验证';
		String maxLength = "";// 最大长度

		if (!Base.isNull(checked) && checked.split("!!luojw!!").length > 0) {
			maxLength = checked.split("!!luojw!!")[0];
		}

		String content = map.get("content");// '内容';

		StringBuilder html = new StringBuilder();

		// 是否有最大長度
		boolean bool_maxlength = (!Base.isNull(maxLength)) ? true : false;

		String width = "150px";
		String maxlength = "";

		if (bool_maxlength) {
			maxlength = "maxLength=\"" + maxLength + "\" ";
		}

		html.append("<input type=\"text\" ");
		html.append("name=\"str_" + zd + "\" ");
		html.append("id=\"text_" + zd + "\" ");
		html.append("style=\"" + width + "\" ");
		html.append(maxlength);
		html.append(" value=\"" + content + "\" ");

		if (!Base.isNull(checked) && checked.split("!!luojw!!").length > 0) {
			for (int i = 0; i < checked.split("!!luojw!!").length; i++) {
				if ("sfzh".equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// 身份证号
					html.append("onblur=\"checkSfzh(this)\" ");
				} else if ("num"
						.equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// 数字验证
					html.append("onkeydown=\"return onlyNum(this,"
							+ checked.split("!!luojw!!")[0] + ")\" ");
					html.append("onmousedown=\"return onlyNum(this,"
							+ checked.split("!!luojw!!")[0] + ")\" ");
					html.append("style=\"ime-mode:disabled\" ");
				} else if ("dzyx"
						.equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// 电子邮箱
					html
							.append("onblur=\"if(isEmail(this.value)){}else{this.value='';alertInfo('电子邮箱格式不正确，请确认')}\" ");
				} else if ("money"
						.equalsIgnoreCase(checked.split("!!luojw!!")[i])) {// 金额验证
					html.append("onkeyup=\"checkInputNum(this)\"");
					html.append("onblur=\"checkInputNum(this)\"");
					html.append("style=\"ime-mode:disabled;\"");
				}
			}
		}

		html.append("/>");

		return html.toString();
	}

	/**
	 * 加載Calendar對象
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	private String createCalendarHtml(HashMap<String, String> map) {

		String zd = map.get("zd");// '字段';
		String input_width = "150px";// '宽度';
		String content = map.get("content");// '内容';

		StringBuilder html = new StringBuilder();

		// 是否有寬度
		boolean bool_width = (!Base.isNull(input_width)) ? true : false;

		String width = "";

		if (bool_width) {
			width = "width:" + input_width + "px";
		}

		html.append("<input type=\"text\" ");
		html.append("name=\"str_" + zd + "\" ");
		html.append("id=\"calendar_" + zd + "\" ");
		html.append("onblur=\"dateFormatChg(this)\" ");
		html.append("style=\"cursor:hand;");
		html.append(width);
		html.append("\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append(" value=\"" + content + "\" ");
		html.append("readOnly=\"true\"/> ");

		return html.toString();
	}

	/**
	 * 加載Select對象
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	private String createSelectHtml(HashMap<String, String> map) {

		String zd = map.get("zd");// '字段';
		String input_width = "";// '宽度';
		String input_postfix = "";// '后缀';
		String content = map.get("content");// '内容';

		String source_table = map.get("source_table");// '下拉列表代码';
		String option_dm = map.get("option_dm");// '下拉列表代码';
		String option_mc = map.get("option_mc");// '下拉列表名称';

		StringBuilder html = new StringBuilder();

		// 是否有後綴
		boolean bool_postfix = (!Base.isNull(input_postfix)) ? true : false;
		// 是否有數據源
		boolean bool_table = (!Base.isNull(source_table)) ? true : false;

		String postfix = "";

		if (bool_postfix) {
			postfix = input_postfix;
		}

		html.append("<select ");
		html.append("name=\"str_" + zd + "\" ");
		html.append("id=\"select_" + zd + "\" ");
		html.append("style=\"width: 150px\" ");
		html.append(">");
		html.append("<option value=\"\"></option>");

		if (!bool_table) {// 无数据源

			String[] dm = option_dm.split("!!luojw!!");
			String[] mc = option_mc.split("!!luojw!!");

			for (int i = 0; i < dm.length; i++) {
				html.append("<option ");
				if (dm[i].equalsIgnoreCase(content)) {
					html.append(" selected=\"selected\" ");
				}
				html.append("value=\"" + dm[i] + "\">");
				html.append(mc[i]);
				html.append("</option>");
			}
		} else {
			if (!Base.isNull(option_dm)) {

				List<HashMap<String, String>> optionList = dao.getListBySource(
						source_table, option_dm, option_mc);

				if (optionList != null && optionList.size() > 0) {

					for (int m = 0; m < optionList.size(); m++) {
						String dm = optionList.get(m).get("dm");
						String mc = optionList.get(m).get("mc");

						html.append("<option ");
						if (dm.equalsIgnoreCase(content)) {
							html.append(" selected=\"selected\" ");
						}
						html.append("value=\"" + dm + "\">");
						html.append(mc);
						html.append("</option>");
					}
				}
			}
		}

		html.append("</select>");
		html.append(postfix);

		return html.toString();
	}

	/**
	 * 保存信息完善
	 * 
	 * @date 2012-12-20
	 * @author 伟大的骆
	 */
	public boolean saveXxws(User user, HttpServletRequest request) {

		// 字段设置信息
		List<HashMap<String, String>> zdList = dao.getWszdList();

		ArrayList<String> xsxxb_column = new ArrayList<String>();
		ArrayList<String> xsxxb_value = new ArrayList<String>();
		ArrayList<String> xsfzxxb_column = new ArrayList<String>();
		ArrayList<String> xsfzxxb_value = new ArrayList<String>();

		for (int i = 0; i < zdList.size(); i++) {
			HashMap<String, String> zdInfo = zdList.get(i);
			String zd = zdInfo.get("zd");
			String ssb = zdInfo.get("ssb");
			String id = "str_" + zd;
			String value = unicode2Gbk(request.getParameter(id));

			if ("xsxxb".equalsIgnoreCase(ssb)) {
				xsxxb_column.add(zd);
				xsxxb_value.add(value);
			} else if ("xsfzxxb".equalsIgnoreCase(ssb)) {
				xsfzxxb_column.add(zd);
				xsfzxxb_value.add(value);
			}
		}

		boolean flag = false;

		try {
			flag = dao.updateXsxxb(xsxxb_column.toArray(new String[] {}),
					xsxxb_value.toArray(new String[] {}), user.getUserName(),
					user);

			if (flag && xsfzxxb_column != null && xsfzxxb_column.size() > 0) {
				flag = dao.updateXsfzxxb(xsfzxxb_column
						.toArray(new String[] {}), xsfzxxb_value
						.toArray(new String[] {}), user.getUserName(), user);
			}

			if (flag) {

				boolean isExists = isExists("xg_xsxx_dljcb", "xh", user
						.getUserName());

				if (isExists) {
					dao.updateDljcb(user.getUserName(), user);
				} else {
					dao.insertDljcb(new String[] { user.getUserName() }, user);
					dao.updateDljcb(user.getUserName(), user);
				}
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	// ===================信息完善 end=============================

}

package xsgzgl.szdw.general.dwwh;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;

;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_队伍维护_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DwwhService extends CommService implements SzdwDwwhInterface {

	private DwwhDAO dao = new DwwhDAO();

	/**
	 * 获得思政隊伍維護表头
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getDwwhTop(DwwhModel model, User user) {

		DAO dao = DAO.getInstance();
		String[] en;
		String[] cn;
		if(Base.xxdm.equals("12834")){
			en = new String[] { "pk", "zgh", "xm", "xb", "lxdh", "bmmc", "fdydbs", "bzrdbs", "yhsf","dls"};
			cn = new String[] { "", "职工号", "姓名", "性别", "联系电话", "所属部门", "大队长带班数", "中队长带班数", "用户身份","系统登录次数" };
		}else if(Base.xxdm.equals("10026")){
			en = new String[] { "pk", "zgh"  , "xm"  , "xb"  , "lxdh"    , "bmmc"   , "fdydbs"     , "bzrdbs"     , "yhsf"    ,"dls"        ,"jssf"};
			cn = new String[] { ""  , "职工号", "姓名", "性别", "联系电话", "所属部门", "辅导员带班数", "班主任带班数", "用户身份","系统登录次数","教师身份" };
		}else {
			en = new String[] { "pk", "zgh", "xm", "xb", "lxdh", "bmmc", "fdydbs", "bzrdbs", "yhsf","dls"};
			cn = new String[] { "", "职工号", "姓名", "性别", "联系电话", "所属部门", "辅导员带班数", "班主任带班数", "用户身份","系统登录次数" };
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得获得思政隊伍維護结果集
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getDwwhList(SzdwGeneralForm myForm, DwwhModel model, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getDwwhList(myForm, user);
	}

	/**
	 * 构建思政隊伍維護HTML
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public String createDwwhHTML(SearchRsModel rsModel, DwwhModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String pk = rs[0];

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");

				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}

				html.append("><a class='name' href='#' onclick='viewjgz(\"" + rs[1] + "\")'>");

				html.append(rs[1]);

				html.append("</a></td>");
				int length = rs.length-5;
				if("10026".equals(Base.xxdm)) length = rs.length-6;
				for (int j = 2; j < length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(rs[j]);
					html.append("</td>");
				}

				

				// 所属部门
				html.append("<td nowrap=\"nowrap\" title=\"" + rs[5] + "\">");
				if (rs[5].length() > 8) {
					html.append(rs[5].substring(0, 8) + "...");
				} else {
					html.append(rs[5]);
				}
				html.append("</td>");

				// 辅导员带班数
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<input type='hidden' name='fdyDbs' value='").append(rs[6]).append("'/>");
				html.append("<a href=\"#\" onclick=\"showBjxx('" + pk + "','fdy','" + rs[6] + "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[6]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");

				// 班主任带班数
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<input type='hidden' name='bzrDbs' value='").append(rs[7]).append("'/>");
				html.append("<a href=\"#\" onclick=\"showBjxx('" + pk + "','bzr','" + rs[7] + "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(rs[7]);
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");

				// 是否用户
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[8]);
				html.append("</td>");
				
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[9]);
				html.append("</td>");
				if("10026".equals(Base.xxdm)){
					html.append("<td nowrap=\"nowrap\" title=\"" + rs[10] + "\">");
					if (rs[10].length() > 8) {
						html.append(rs[10].substring(0, 8) + "...");
					} else {
						html.append(rs[10]);
					}
					html.append("</td>");
				}
				

				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 统计辅导员带班级信息
	 * 
	 * @param zgh
	 * @author qlj
	 * @time 2013-1-24
	 * @return
	 */
	public String countFdyDbj(String zgh) {

		return dao.countFdyDbj(zgh);
	}

	/**
	 * 统计辅导员带班级信息
	 * 
	 * @param zgh
	 * @author qlj
	 * @time 2013-1-24
	 * @return
	 */
	public String countBzrDbj(String zgh) {

		return dao.countBzrDbj(zgh);
	}

	/**
	 * 创建队伍维护DIV html(用于更改弹出方式)
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public String createDwwhDivStr(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// 类型
		String lx = model.getLx();
		// 职工号
		String zgh = model.getZgh();
		// 思政队伍信息
		HashMap<String, String> dwwhMap = dao.getDwxx(zgh);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>教师信息维护</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<input type=\"hidden\" id=\"xxdm\" value=\"" + Base.xxdm + "\"> ");
		html.append("<tr>");
		html.append("<th width=\"30%\">");
		html.append("<font color=\"red\">*</font>职工号");
		html.append("</th>");
		html.append("<td>");
		if ("insert".equalsIgnoreCase(lx)) {
			html.append("<input type=\"text\" id=\"text_zgh\" onkeyup=\"this.value=this.value.replace(/[^\\w]/g,'')\" ");
			html.append("maxlength=\"20\" style=\"width:200px\"/>");
		} else if ("update".equalsIgnoreCase(lx)) {
			html.append("<input type=\"hidden\" id=\"text_zgh\" ");
			html.append("value=\"" + dwwhMap.get("zgh") + "\"/> ");
			html.append(dwwhMap.get("zgh"));
		}
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("<font color=\"red\">*</font>姓名");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" id=\"text_xm\" style=\"width:200px\"");

		String xm = Base.isNull(dwwhMap.get("xm")) ? "" : dwwhMap.get("xm");

		html.append("value=\"" + xm + "\" ");
		html.append("maxlength=\"20\" />");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("<font color=\"red\">*</font>性别");
		html.append("</th>");
		html.append("<td>");
		html.append("<select id=\"select_xb\" style=\"width:200px\">");
		html.append("<option value=\"1\" ");
		if ("1".equalsIgnoreCase(dwwhMap.get("xb"))) {
			html.append("selected=\"selected\"");
		}
		html.append(">男</option>");
		html.append("<option value=\"2\" ");
		if ("2".equalsIgnoreCase(dwwhMap.get("xb"))) {
			html.append("selected=\"selected\"");
		}
		html.append(">女</option>");
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("<font color=\"red\">*</font>所属部门");
		html.append("</th>");
		html.append("<td>");

		// 部门列表
		List<HashMap<String, String>> bmList = dao.getBmList();
		List<HashMap<String, String>> syList =null;
		if ("10698".equals(Base.xxdm)) {
			syList = dao.getSyList();
		}
		
		
		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if ("xy".equalsIgnoreCase(userType)) {
			html.append(" <select id=\"\" style=\"width:200px\" ");
			html.append(" disabled='true' value='" + userDep + "' ");
			html.append(" >");
			if (bmList != null && bmList.size() > 0) {
				for (int i = 0; i < bmList.size(); i++) {
					HashMap<String, String> bmMap = bmList.get(i);

					html.append("<option value=\"" + bmMap.get("bmdm") + "\"");
					if (bmMap.get("bmdm").equalsIgnoreCase(dwwhMap.get("bmdm"))) {
						html.append("selected=\"selected\"");
					}
					if (bmMap.get("bmdm").equalsIgnoreCase(userDep) && "xy".equalsIgnoreCase(userType)) {
						html.append("selected=\"selected\"");
					}
					html.append(">");
					html.append(bmMap.get("bmpy") + bmMap.get("bmmc"));
					html.append("</option>");
				}
			}
			html.append("</select>");
			html.append(" <input type=\"hidden\" name=\"select_bmdm\" id=\"select_bmdm\" value=\"" + userDep + "\">");
		} else {

			html.append(" <select id=\"select_bmdm\" style=\"width:200px\" ");
			html.append(" >");
			if (bmList != null && bmList.size() > 0) {
				for (int i = 0; i < bmList.size(); i++) {
					HashMap<String, String> bmMap = bmList.get(i);

					html.append("<option value=\"" + bmMap.get("bmdm") + "\"");
					if (bmMap.get("bmdm").equalsIgnoreCase(dwwhMap.get("bmdm"))) {
						html.append("selected=\"selected\"");
					}
					html.append(">");
					html.append(bmMap.get("bmpy") + bmMap.get("bmmc"));
					html.append("</option>");
				}
			}
			html.append("</select>");
		}

		html.append("</td>");
		html.append("</tr>");
		
		if ("10698".equals(Base.xxdm)) {//增加书院的选择
			html.append("<tr>");
			html.append("<th>");
			html.append("书院");
			html.append("</th>");
			html.append("<td>");
			html.append(" <select id=\"select_sydm\" style=\"width:200px\" >");
			if (syList != null && syList.size() > 0) {
				for (int i = 0; i < syList.size(); i++) {
					HashMap<String, String> syMap = syList.get(i);

					html.append("<option value=\"" + syMap.get("sydm") + "\"");
					if (syMap.get("sydm").equalsIgnoreCase(dwwhMap.get("sydm"))) {
						html.append("selected=\"selected\"");
					}
					html.append(">");
					html.append(syMap.get("symc"));
					html.append("</option>");
				}
			}
			html.append("</select>");
			html.append("</td>");
			html.append("</tr>");
		}
		
		html.append("<tr>");
		html.append("<th>");
		html.append("联系电话");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"text\" id=\"text_lxdh\" ");
		// html.append("onkeydown=\"return onlyNum(this,20)\"");
		// html.append("onmousedown=\"return onlyNum(this,20)\"");
		html.append("onkeyup=\"checkInputData(this)\"");
		html.append("maxlength=\"20\" ");

		String lxdh = Base.isNull(dwwhMap.get("lxdh")) ? "" : dwwhMap.get("lxdh");

		html.append("value=\"" + lxdh + "\" ");
		html.append("style=\"width:200px;ime-mode:disabled\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"bz\">\"<span class=\"red\">*</span>\"为必填项</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"保存\" onclick=\"saveDwwh('" + lx + "');return false;\" id=\"buttonSave\">");
		html.append("保 存");
		html.append("</button>");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"iFClose();return false;\" id=\"buttonClose\">关 闭</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");
		html.append("<script language=\"javascript\" src=\"js/xsgzgl/szdw/dwwh.js\"></script>");
		return html.toString();
	}

	/**
	 * 创建队伍维护DIV
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public void createDwwhDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {
		response.getWriter().print(createDwwhDivStr(model, user, response));
	}

	/**
	 * 保存队伍维护
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public boolean saveDwwh(DwwhModel model, User user) {

		// 职工号
		String zgh = model.getZgh();
		// 姓名
		String xm = model.getXm();
		// 性别
		String xb = model.getXb();
		// 部门代码
		String bmdm = model.getBmdm();
		// 书院代码
		String sydm ="";
		if ("10698".equals(Base.xxdm)) {
			 sydm = model.getSydm();
		}
		// 联系电话
		String lxdh = model.getLxdh();

		boolean isExists = isExists("fdyxxb", "zgh", zgh);

		boolean flag = false;

		try {
			if (isExists) {
				flag = dao.updateFdyxxb(zgh, xm, xb, bmdm, lxdh, user,sydm);
			} else {
				flag = dao.insertFdyxxb(zgh, xm, xb, bmdm, lxdh, user,sydm);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 删除队伍维护
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public boolean deleteDwwh(DwwhModel model, User user) {

		boolean flag = false;

		String[] pkValue = model.getPkValue();

		try {

			flag = dao.deleteFdyxxb(pkValue, user);

			if (flag) {
				dao.deleteFdybjb(pkValue, user);
				dao.deleteBzrbbb(pkValue, user);
			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 创建用户库DIV
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public void createYhkDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>添加到用户库</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"30%\">");
		html.append("<font color=\"red\">*</font>所属组");
		html.append("</th>");
		html.append("<td>");

		// 用户组列表
		List<HashMap<String, String>> yhzList = dao.getTableListInfo("yhzb", new String[] { "zdm", "zmc" }, "", "", "");

		html.append("<select id=\"select_zdm\" style=\"width:200px\">");
		if (yhzList != null && yhzList.size() > 0) {
			for (int i = 0; i < yhzList.size(); i++) {
				HashMap<String, String> bmMap = yhzList.get(i);
				html.append("<option value=\"" + bmMap.get("zdm") + "\"");
				html.append(">");
				html.append(bmMap.get("zmc"));
				html.append("</option>");
			}
		}
		html.append("</select>");

		html.append("</td>");
		html.append("</tr>");

		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\">\"<span class=\"red\">*</span>\"为必填项</div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"保存\" onclick=\"saveYhk();return false;\" id=\"buttonSave\">");
		html.append("保 存");
		html.append("</button>");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">关 闭</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 保存用户到用户库
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public boolean saveYhk(DwwhModel model, User user) {

		// 主键
		String[] pkValue = model.getPkValue();
		// 组代码
		String zdm = model.getZdm();
		// 已存在的职工号
		String[] isExistsZgh = null;

		boolean flag = true;

		try {

			isExistsZgh = dao.getYh(pkValue);

			// 不存在的职工号
			String[] noExistsZgh = getNoRepeatArrValue(pkValue, isExistsZgh);

			if (isExistsZgh == null || isExistsZgh.length == 0) {
				noExistsZgh = pkValue;
			}

			if (noExistsZgh != null && noExistsZgh.length > 0) {
				flag = dao.insertYhb(noExistsZgh, zdm, user);
			}

			if (isExistsZgh != null && isExistsZgh.length > 0) {
				flag = dao.updateYhb(isExistsZgh, zdm, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 创建用户库DIV
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public String createYxjrDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {
		String pkValue = "";
		for (String str : model.getPkValue()) {
			pkValue += str + "!!array!!";
		}
		pkValue = pkValue.substring(0, pkValue.lastIndexOf("!!array!!"));
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");
		html.append("<input type=\"hidden\" id=\"selectId\" value=" + pkValue + ">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>兼任情况设置【注：只有当该老师带班的情况下，本设置才启作用】</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width=\"20%\">");
		html.append("兼任情况");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" name=\"radio_yxjr\" value=\"false\" checked=\"checked\"/>");
		html.append("不兼任院系/学校(仅以带班老师身份操作数据)");
		html.append("<br>");
		html.append("<input type=\"radio\" name=\"radio_yxjr\" value=\"true\"/>");
		html.append("兼任院系/学校(可以切换为院系/学校身份操作数据)");
		html.append("</td>");
		html.append("</tr>");

		html.append("</tbody>");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"保存\" onclick=\"saveYxjr();return false;\" id=\"buttonSave\">");
		html.append("保 存");
		html.append("</button>");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"iFClose();return false;\" id=\"buttonClose\">关 闭</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		// response.getWriter().print(html.toString());
		return html.toString();
	}

	/**
	 * 保存院校兼任
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public boolean saveYxjr(DwwhModel model, User user) {

		// 是否兼任院校
		String sfjryx = model.getSfjryx();
		// 主键
		String[] pkValue = model.getPkValue();

		boolean flag = false;

		try {
			flag = dao.updateFdyxxb(pkValue, sfjryx, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 创建班级信息DIV
	 * 
	 * @date 2013-01-15
	 * @author 伟大的骆
	 */
	public void createBjxxDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// 类型
		String lx = model.getLx();
		// 类型名称
		String lxmc;
		if (Base.xxdm.equals("12834")) {
			 lxmc = "fdy".equalsIgnoreCase(lx) ? "大队长" : "中队长";
		}else {
			 lxmc = "fdy".equalsIgnoreCase(lx) ? "辅导员" : "班主任";
		}
		// 职工号
		String zgh = model.getZgh();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		HashMap<String, String> map = getDwwh(model, user);

		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"25%\">职工号</th>");
		html.append("<td width=\"25%\">" + map.get("zgh") + "</td>");
		html.append("<th width=\"25%\">姓名</th>");
		html.append("<td width=\"25%\">" + map.get("xm") + "</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th width=\"25%\">所属部门</th>");
		html.append("<td width=\"25%\">" + map.get("bmmc") + "</td>");
		html.append("<th width=\"25%\">" + lxmc + "带班数</th>");
		html.append("<td width=\"25%\">" + map.get("num") + "</td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"10%\" ><div align='left'>年级</div></th>");
		html.append("<th width=\"30%\"><div align='left'>院系</div></th>");
		html.append("<th width=\"30%\"><div align='left'>专业</div></th>");
		html.append("<th width=\"30%\"><div align='left'>班级</div></th>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		List<HashMap<String, String>> bjList = null;

		if ("fdy".equalsIgnoreCase(lx)) {
			bjList = dao.getFdybjList(zgh);
		} else {
			bjList = dao.getBzrbjList(zgh);
		}

		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table class=\"formlist\">");

		for (int i = 0; i < bjList.size(); i++) {
			html.append("<tr>");
			html.append("<td width=\"10%\">" + bjList.get(i).get("nj") + "</td>");
			html.append("<td width=\"30%\">" + bjList.get(i).get("xymc") + "</td>");
			html.append("<td width=\"30%\">" + bjList.get(i).get("zymc") + "</td>");
			html.append("<td width=\"30%\">" + bjList.get(i).get("bjmc") + "</td>");
			html.append("</tr>");
		}

		if (8 - bjList.size() > 0) {
			for (int i = 0; i < 8 - bjList.size(); i++) {
				html.append("<tr>");
				html.append("<td width=\"10%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("</tr>");
			}
		}

		html.append("</table>");
		html.append("</div>");

		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">关 闭</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 获取思政队伍信息
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getDwwh(DwwhModel model, User user) {

		// 用户类型
		String lx = model.getLx();
		// 职工号
		String zgh = model.getZgh();

		if ("fdy".equalsIgnoreCase(lx)) {
			return dao.getFdyInfo(zgh);
		} else if ("bzr".equalsIgnoreCase(lx)) {
			return dao.getBzrInfo(zgh);
		}

		return null;
	}

	/**
	 * 创建年级Div
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public void createNjLvDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// 类型
		String lx = model.getLx();
		// 职工号
		String zgh = model.getZgh();

		// 获得年级列表
		List<HashMap<String, String>> njList = dao.getNjList(lx, zgh);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		html.append("<h3><span>年级</span></h3>");
		html.append("<ul>");

		if (njList != null && njList.size() > 0) {

			boolean flag = false;

			for (int i = 0; i < njList.size(); i++) {
				HashMap<String, String> map = njList.get(i);

				// 年级
				String nj = map.get("nj");
				// 是否编班
				String sfbb = map.get("sfbb");

				if (flag) {
					flag = false;
				} else {
					html.append("<li>");
				}

				html.append("<input type=\"checkbox\" ");
				html.append("onclick=\"clickNj(this,'" + nj + "','" + i + "','checkbox');\" ");
				html.append("value=\"" + nj + "\" ");
				html.append("id=\"checkbox_nj_" + nj + "\" ");
				// if("yes".equalsIgnoreCase(sfbb)){
				// html.append("checked=\"checked\" ");
				// }
				html.append("/> ");

				html.append("<a href=\"#\" id=\"a_nj_" + i + "\" ");
				html.append("onclick=\"clickNj(this,'" + nj + "','" + i + "','a');return false;\" >");
				html.append(nj);
				if ("yes".equalsIgnoreCase(sfbb)) {
					html.append("<font color=\"red\">【含】</font> ");
				}
				html.append("</a>");
				html.append("</li>");
			}
		}

		html.append("</ul>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 创建其他级别Div
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public void createOtherLvDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// 类型
		String lx = model.getLx();
		// 职工号
		String zgh = model.getZgh();
		// 类型
		String type = model.getType();

		List<HashMap<String, String>> bmList = dao.getBmList(lx, zgh);
		// 最大等级编制
		String maxBz = "1";
		// 第二等级编制
		String secBz = "2";
		// 第三等级编制
		String thiBz = "3";
		// 第四等级编制
		String fouBz = "4";

		// 二级建制列表
		List<HashMap<String, String>> secList = new ArrayList<HashMap<String, String>>();
		// 三级建制列表
		List<HashMap<String, String>> thiList = new ArrayList<HashMap<String, String>>();
		// 四级建制列表
		List<HashMap<String, String>> fouList = new ArrayList<HashMap<String, String>>();

		// 选中的年级
		String checkedBzdm = model.getNjV();
		// 年级是否选中
		String checked_nj = model.getChecked_nj();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		if (bmList != null && bmList.size() > 0) {
			html.append("<div id=\"div_fpbj_old\" style=\"display:none\">");
			for (int i = 0; i < bmList.size(); i++) {
				HashMap<String, String> map = bmList.get(i);
				// 编制等级
				String bzdj = map.get("bzdj");
				// 上级代码
				String sjdm = map.get("sjdm");

				if ("2".equalsIgnoreCase(bzdj)) {
					secList.add(map);
				} else if ("3".equalsIgnoreCase(bzdj)) {
					thiList.add(map);
				} else if ("4".equalsIgnoreCase(bzdj)) {
					fouList.add(map);

					if ("checkbox".equalsIgnoreCase(type)) {
						if ("true".equalsIgnoreCase(checked_nj) && sjdm.substring(0, 4).equalsIgnoreCase(checkedBzdm)) {
							html.append("<input type=\"text\" ");
							html.append("name=\"hidden_bjdm\"");
							html.append("id=\"hidden_bjdm_" + map.get("bzdm") + "\"");
							html.append("value=\"" + map.get("bzdm") + "\"");
							html.append("/> ");
						}
					} else {
						if ("yes".equalsIgnoreCase(map.get("sfbb"))) {
							html.append("<input type=\"text\" ");
							html.append("name=\"hidden_bjdm\"");
							html.append("id=\"hidden_bjdm_" + map.get("bzdm") + "\"");
							html.append("value=\"" + map.get("bzdm") + "\"");
							html.append("/> ");
						}
					}
				}
			}
			html.append("</div>");
		}

		html.append("<table width=\"100%\" border=\"0\">");

		if (bmList != null && bmList.size() > 0) {

			for (int i = 0; i < bmList.size(); i++) {
				HashMap<String, String> map = bmList.get(i);
				// 编制等级
				String bzdj = map.get("bzdj");
				// 编制代码
				String bzdm = map.get("bzdm");
				// 编制名称
				String bzmc = map.get("bzmc");
				// 上级代码
				String sjdm = map.get("sjdm");
				// 是否编班
				String sfbb = map.get("sfbb");
				// 下级编制数量
				int num = Integer.parseInt(map.get("num"));

				if (secBz.equalsIgnoreCase(bzdj) && checkedBzdm.equalsIgnoreCase(sjdm)) {

					html.append("<tbody id=\"tbody_" + bzdm + "\">");

					html.append("<tr id=\"" + bzdm + "\" ");
					html.append("class=\"tr_02\">");

					// 二级编制
					html.append("<td style=\"width:140px\" class=\"list_02\"");
					html.append("rowspan=\"" + num + "\" ");
					html.append(">");

					html.append("<input type=\"checkbox\" ");
					html.append("onclick=\"clickXyCheckBox('" + bzdm + "')\" ");
					if ("true".equalsIgnoreCase(checked_nj)) {
						html.append("checked=\"checked\" ");
					}
					html.append("value=\"" + bzdm + "\" ");
					html.append("name=\"checkbox_xy_" + sjdm + "\" ");
					html.append("id=\"checkbox_xy_" + bzdm + "\" ");
					html.append("/> ");

					html.append("<label>");
					html.append("<a href=\"#\" name=\"jxbz\" id=\"" + bzdj + "_" + bzdm + "\" title=\"" + bzmc + "\" onclick=\"return false;\">");
					if (bzmc.length() > 7) {
						html.append(bzmc.substring(0, 7) + "...");
					} else {
						html.append(bzmc);
					}
					if ("yes".equalsIgnoreCase(sfbb)) {
						html.append("<font color=\"red\">【含】</font> ");
					}
					html.append("</a></label>");
					html.append("</td>");

					boolean thi_flag = true;
					boolean had_thi = false;
					boolean had_fou = false;

					if (thiList != null && thiList.size() > 0) {
						// 三级编制
						for (int j = 0; j < thiList.size(); j++) {
							HashMap<String, String> thiMap = thiList.get(j);
							// 编制等级
							String thi_bzdj = thiMap.get("bzdj");
							// 编制代码
							String thi_bzdm = thiMap.get("bzdm");
							// 编制名称
							String thi_bzmc = thiMap.get("bzmc");
							// 上级代码
							String thi_sjdm = thiMap.get("sjdm");
							// 是否编班
							String thi_sfbb = thiMap.get("sfbb");
							if (thi_sjdm.equalsIgnoreCase(bzdm)) {

								had_thi = true;
								boolean li_flag = false;

								if (thi_flag) {
									html.append("<td style=\"width:150px\" class=\"list_03\">");

									html.append("<input type=\"checkbox\" ");
									html.append("onclick=\"clickZyCheckBox('" + thi_bzdm + "','" + thi_sjdm + "')\" ");
									if ("true".equalsIgnoreCase(checked_nj)) {
										html.append("checked=\"checked\" ");
									}
									html.append("value=\"" + thi_bzdm + "\" ");
									html.append("name=\"checkbox_zy_" + thi_sjdm + "\" ");
									html.append("id=\"checkbox_zy_" + thi_bzdm + "\" ");
									html.append("/> ");

									html.append("<label>");
									html.append("<a href=\"#\" title=\"" + thi_bzmc + "\"name=\"jxbz\" id=\"" + thi_bzdj + "_" + thi_bzdm + "\" onclick=\"return false;\">");

									if (thi_bzmc.length() > 5) {
										html.append(thi_bzmc.substring(0, 5) + "...");
									} else {
										html.append(thi_bzmc);
									}
									if ("yes".equalsIgnoreCase(thi_sfbb)) {
										html.append("<font color=\"red\">【含】</font> ");
									}
									html.append("</a></label>");
									html.append("</td>");

									li_flag = true;
									thi_flag = false;
								}

								int four_num = 0;
								boolean four_flag = true;

								// 四级编制
								if (fouList != null && fouList.size() > 0) {
									had_fou = false;
									for (int k = 0; k < fouList.size(); k++) {
										HashMap<String, String> fouMap = fouList.get(k);
										// 编制等级
										String fou_bzdj = fouMap.get("bzdj");
										// 编制代码
										String fou_bzdm = fouMap.get("bzdm");
										// 编制名称
										String fou_bzmc = fouMap.get("bzmc");
										// 上级代码
										String fou_sjdm = fouMap.get("sjdm");
										// 是否编班
										String fou_sfbb = fouMap.get("sfbb");

										if (fou_sjdm.equalsIgnoreCase(thi_bzdm) && li_flag) {

											had_fou = true;

											if (four_flag) {
												html.append("<td>");
												html.append("<ul>");

												four_flag = false;
											}

											// if(four_num<8){
											html.append("<li>");

											html.append("<input type=\"checkbox\" ");
											html.append("onclick=\"clickBjCheckBox('" + fou_bzdm + "')\" ");

											if ("checkbox".equalsIgnoreCase(type)) {
												if ("true".equalsIgnoreCase(checked_nj)) {
													html.append("checked=\"checked\" ");
												}
											} else {
												if ("yes".equalsIgnoreCase(fou_sfbb)) {
													html.append("checked=\"checked\" ");
												}
											}

											html.append("value=\"" + fou_bzdm + "\" ");
											html.append("name=\"checkbox_bj_" + fou_sjdm + "\" ");
											html.append("id=\"checkbox_bj_" + fou_bzdm + "\" ");
											html.append("/> ");

											html.append("<a href=\"#\" name=\"minbz\" id=\"" + fou_bzdj + "_" + fou_bzdm + "\" onclick=\"return false;\" title=\"" + fou_bzmc + "\">");
											if (fou_bzmc.length() > 18) {
												html.append(fou_bzmc.substring(0, 18) + "...");
											} else {
												html.append(fou_bzmc);
											}
											html.append("</a>");
											html.append("</li>");
											// }

											four_num++;
										}
									}
								}

								if (!four_flag) {
									html.append("</ul>");
									html.append("</td>");
								}
							}
						}
					}

					if (!had_thi) {
						// 空行
						html.append("<td style=\"\">");
						html.append("<label>");
						html.append("<a>");
						html.append("</a></label>");
						html.append("</td>");
					}

					if (!had_fou) {
						// 空行
						html.append("<td style=\"\">");
						html.append("<label>");
						html.append("<a>");
						html.append("</a></label>");
						html.append("</td>");
					}

					html.append("</tr>");

					if (had_thi) {

						boolean thi_next_flag = false;

						if (thiList != null && thiList.size() > 0) {
							// 三级编制
							for (int j = 0; j < thiList.size(); j++) {
								HashMap<String, String> thiMap = thiList.get(j);
								// 编制等级
								String thi_bzdj = thiMap.get("bzdj");
								// 编制代码
								String thi_bzdm = thiMap.get("bzdm");
								// 编制名称
								String thi_bzmc = thiMap.get("bzmc");
								// 上级代码
								String thi_sjdm = thiMap.get("sjdm");
								// 是否编班
								String thi_sfbb = thiMap.get("sfbb");

								if (thi_sjdm.equalsIgnoreCase(bzdm)) {

									if (thi_next_flag) {

										html.append("<tr id=\"" + bzdm + "\" ");
										html.append("class=\"tr_02\">");

										html.append("<td style=\"width:150px\" class=\"list_03\">");

										html.append("<input type=\"checkbox\" ");
										html.append("onclick=\"clickZyCheckBox('" + thi_bzdm + "','" + thi_sjdm + "')\" ");
										if ("true".equalsIgnoreCase(checked_nj)) {
											html.append("checked=\"checked\" ");
										}
										html.append("value=\"" + thi_bzdm + "\" ");
										html.append("name=\"checkbox_zy_" + thi_sjdm + "\" ");
										html.append("id=\"checkbox_zy_" + thi_bzdm + "\" ");
										html.append("/> ");

										html.append("<label>");
										html.append("<a href=\"#\" title=\"" + thi_bzmc + "\"name=\"jxbz\" id=\"" + thi_bzdj + "_" + thi_bzdm + "\" onclick=\"return false;\">");

										if (thi_bzmc.length() > 5) {
											html.append(thi_bzmc.substring(0, 5) + "...");
										} else {
											html.append(thi_bzmc);
										}
										if ("yes".equalsIgnoreCase(thi_sfbb)) {
											html.append("<font color=\"red\">【含】</font> ");
										}
										html.append("</a>");
										html.append("</label>");
										html.append("</td>");

										int four_num = 0;
										boolean four_flag = true;

										// 四级编制
										if (fouList != null && fouList.size() > 0) {
											for (int k = 0; k < fouList.size(); k++) {
												HashMap<String, String> fouMap = fouList.get(k);
												// 编制等级
												String fou_bzdj = fouMap.get("bzdj");
												// 编制代码
												String fou_bzdm = fouMap.get("bzdm");
												// 编制名称
												String fou_bzmc = fouMap.get("bzmc");
												// 上级代码
												String fou_sjdm = fouMap.get("sjdm");
												// 是否编班
												String fou_sfbb = fouMap.get("sfbb");

												if (fou_sjdm.equalsIgnoreCase(thi_bzdm)) {

													if (four_flag) {
														html.append("<td>");
														html.append("<ul>");

														four_flag = false;
													}

													// if(four_num<8){
													html.append("<li>");

													html.append("<input type=\"checkbox\" ");
													html.append("onclick=\"clickBjCheckBox('" + fou_bzdm + "')\" ");

													if ("checkbox".equalsIgnoreCase(type)) {
														if ("true".equalsIgnoreCase(checked_nj)) {
															html.append("checked=\"checked\" ");
														}
													} else {
														if ("yes".equalsIgnoreCase(fou_sfbb)) {
															html.append("checked=\"checked\" ");
														}
													}

													html.append("value=\"" + fou_bzdm + "\" ");
													html.append("name=\"checkbox_bj_" + fou_sjdm + "\" ");
													html.append("id=\"checkbox_bj_" + fou_bzdm + "\" ");
													html.append("/> ");

													html.append("<a href=\"#\" name=\"minbz\" id=\"" + fou_bzdj + "_" + fou_bzdm + "\" onclick=\"return false;\" title=\"" + fou_bzmc + "\">");
													if (fou_bzmc.length() > 18) {
														html.append(fou_bzmc.substring(0, 18) + "...");
													} else {
														html.append(fou_bzmc);
													}
													html.append("</a>");
													html.append("</li>");
													// }

													four_num++;
												}
											}
										}

										if (!four_flag) {
											html.append("</ul>");
											html.append("</td>");
										}

										if (!had_fou) {
											html.append("<td style=\"\">");
											html.append("<label>");
											html.append("<a>");
											html.append("</a></label>");
											html.append("</td>");
										}

										html.append("</tr>");
									}

									thi_next_flag = true;
								}

							}
						}
					}

					html.append("</tbody>");

				}
			}
		}

		html.append("</table>");
		// System.out.print(html);
		response.getWriter().print(html.toString());
	}

	/**
	 * 保存分配班级
	 * 
	 * @date 2013-01-14
	 * @author 伟大的骆
	 */
	public boolean saveFpbj(DwwhModel model, User user) {

		DAO commDAO = DAO.getInstance();

		// 主键
		String[] bjdm = model.getBjdm();
		// 职工号
		String zgh = model.getZgh();
		// 类型
		String lx = model.getLx();

		boolean flag = false;

		String[] bjdm_other = model.getBjdm_other();

		String[] bjdm_my = model.getBjdm_my();

		try {
			
			for (int i = 0; i < bjdm_other.length; i++) {
				bjdm_other[i] = URLDecoder.decode(bjdm_other[i],"UTF-8");
			}
			
			for (int i = 0; i < bjdm_my.length; i++) {
				bjdm_my[i] = URLDecoder.decode(bjdm_my[i],"UTF-8");
			}
			
			if ("fdy".equalsIgnoreCase(lx)) {
				flag = dao.deleteFdybjb(zgh, bjdm_my, user);
				// dao.deleteFdybjb(zgh, user);

				if (bjdm_my != null && bjdm_my.length > 0 && !Base.isNull(bjdm_my[0]) && flag) {
					flag = dao.insertFdybjb(zgh, bjdm_my, user);
					if(flag){
						dao.saveBbls(zgh, bjdm_my, "save", true);
					}
				}

			} else if ("bzr".equalsIgnoreCase(lx)) {
				flag = dao.deleteBzrbbb(zgh, bjdm_my, user);
				// dao.deleteBzrbbb(zgh, user);

				if (bjdm_my != null && bjdm_my.length > 0 && !Base.isNull(bjdm_my[0]) && flag) {
					flag = dao.insertBzrbbb(zgh, bjdm_my, user);
					if(flag){
						dao.saveBbls(zgh, bjdm_my, "save", false);
					}
				}
			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存分配班级
	 * 
	 * @date 2013-01-24
	 * @author qlj
	 */
	public boolean disfrockFpbj(DwwhModel model, User user) {

		// 职工号
		String zgh = model.getZgh();
		// 类型
		String lx = model.getLx();

		boolean flag = false;

		String[] bjdm_my = model.getBjdm_my();

		try {

			for (int i = 0; i < bjdm_my.length; i++) {
				bjdm_my[i] = URLDecoder.decode(bjdm_my[i],"UTF-8");
			}
			
			// 撤销辅导员编班
			if ("fdy".equalsIgnoreCase(lx)) {

				flag = dao.disfrockFdybjb(zgh, bjdm_my, user);
				if(flag){
					dao.saveBbls(zgh, bjdm_my, "update", true);
				}

				// 撤销班主任编班
			} else if ("bzr".equalsIgnoreCase(lx)) {

				flag = dao.disfrockBzrbbb(zgh, bjdm_my, user);
				if(flag){
					dao.saveBbls(zgh, bjdm_my, "update", false);
				}
			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 初始化功能参数
	 * 
	 * @date 2013-01-14
	 * @author 伟大的骆
	 */
	public void initParameter() {

		String path = "szdw_general_dwwh.do";

		// 高级查询是否配置
		boolean isSearch = isExists("xg_search_szb", "path", path);

		if (!isSearch) {
			initSearch();
		}
	}

	/**
	 * 初始化查询条件
	 * 
	 * @date 2013-01-14
	 * @author 伟大的骆
	 */
	private void initSearch() {

		List<String[]> params = new ArrayList<String[]>();

		String path = "szdw_general_dwwh.do";
		String[] tj = new String[] { "zgh", "xm", "bm", "xb", "sf", "sflx", "yhsf" };
		String[] mc = new String[] { "职工号", "姓名", "所属部门", "性别", "是否辅导员", "是否班主任", "用户身份" };
		String[] lx = new String[] { "mhcx", "mhcx", "mhcx", "djcx", "djcx", "djcx", "djcx" };
		String[] zd = new String[] { "zgh", "xm", "bmmc", "xb", "sffdy", "sfbzr", "yhsf" };
		String[] ssmk = new String[] { "szdw", "szdw", "szdw", "szdw", "szdw", "szdw", "szdw", };
		String[] xssx = new String[] { "1", "2", "3", "1", "2", "3", "4" };

		for (int i = 0; i < tj.length; i++) {
			String[] value = new String[] { path, tj[i], mc[i], lx[i], zd[i], ssmk[i], xssx[i] };
			params.add(value);
		}

		try {
			dao.initSearch(params);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 获得部门列表
	 * 
	 * @date 2013-01-15
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}

	/**
	 *创建教职工Option
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public String createJzgOption(DwwhModel model) throws Exception {

		// 类型
		String lx = model.getLx();
		// 职工号
		String zgh = model.getZgh();
		// 姓名
		String xm = model.getXm();
		// 部门代码
		String bmdm = model.getBmdm();
		// 带班情况
		String dbqk = model.getDbqk();

		StringBuilder html = new StringBuilder();

		List<HashMap<String, String>> jzgList = dao.getJzgxxList(zgh, xm, bmdm, dbqk, lx);

		if (jzgList != null && jzgList.size() > 0) {
			for (int i = 0; i < jzgList.size(); i++) {
				html.append("<option ");
				html.append("value=\"" + jzgList.get(i).get("zgh") + "\" ");

				if (zgh.equalsIgnoreCase(jzgList.get(i).get("zgh"))) {
					html.append("selected=\"selected\"");
				}
				html.append(">");
				html.append(jzgList.get(i).get("xm"));
				html.append("(");
				html.append(jzgList.get(i).get("zgh"));
				html.append(")");
				html.append("</option>");
			}
		}

		return html.toString();

	}

	/**
	 * 获得思政隊伍编班表头
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSetupTop(DwwhModel model, User user) {

		DAO dao = DAO.getInstance();

		String lx = model.getLx();
		String lxmc ;
		if (Base.xxdm.equals("12834")) {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "大队长" : "中队长";
		}else {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "辅导员" : "班主任";
		}
		String[] en = new String[] { "nj", "xymc","symc", "bjmc", "lx" };
		String[] cn = new String[] { "年级", "院系", "书院","班级", lxmc + "数" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得获得思政隊伍编班结果集
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getSetupList(SzdwGeneralForm myForm, DwwhModel model, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getSetupList(myForm, model, user);
	}

	/**
	 * 构建思政隊伍编班HTML
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public String createSetupHTML(SearchRsModel rsModel, DwwhModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		String lx = model.getLx();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				String brdb = rs[rs.length - 1];

				html.append("<tr onclick=\"rowOnClick(this);\"  ");
				if ("yes".equalsIgnoreCase(brdb)) {
					html.append(" style=\"background:#7bb9e3\"");
				}
				html.append(" > ");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"checkVal\" ");
				html.append("value=\"" + pk + "\" ");

				html.append("/>");
				html.append("</td>");

				String nj = rs[1];
				String xy = rs[2];
				String zy = rs[3];
				String bj = rs[4];
				String sy = rs[7];

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append(nj);
				html.append("</td>");

				// 学院
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + xy + "\"");
				html.append(">");
				html.append(xy);
				html.append("</td>");

				//书院
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + xy + "\"");
				html.append(">");
				html.append(sy);
				html.append("</td>");
				// 专业
				// html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + zy + "\"");
				// html.append(">");
				// if (!Base.isNull(zy) && zy.length() > 10) {
				// html.append(zy.substring(0, 10) + "...");
				// } else {
				// html.append(zy);
				// }
				// html.append("</td>");

				// 班级
				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				// html.append("title=\"" + bj + "\"");
				html.append(">");
				html.append(bj);
				html.append("</td>");

				// 带班数
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<a href=\"#\" onclick=\"showJzgxx('" + pk + "','" + lx + "','" + rs[5] + "');return false;\">");
				html.append("<font color=\"blue\">");
				html.append("<U>" + rs[5] + "</u>");
				html.append("</font>");
				html.append("</a>");
				html.append("</td>");
			}
		}

		String jsdbs = "0";
		if ("fdy".equalsIgnoreCase(lx)) {
			jsdbs = countFdyDbj(model.getZgh());
		} else if ("bzr".equalsIgnoreCase(lx)) {
			jsdbs = countBzrDbj(model.getZgh());
		}

		html.append("<div style=\"display:none\" >");
		html.append("<input type=\"text\" name=\"hid_dbs\" id=\"hid_dbs\" value=\"" + jsdbs + "\" >");
		html.append("<div style=\"display:none\" >");

		return html.toString();
	}

	/**
	 * 创建教职工信息DIV
	 * 
	 * @date 2013-01-15
	 * @author 伟大的骆
	 */
	public void createJzgxxDiv(DwwhModel model, User user, HttpServletResponse response) throws Exception {

		// 类型
		String lx = model.getLx();
		// 类型名称
		String lxmc = "fdy".equalsIgnoreCase(lx) ? "辅导员" : "班主任";
		// 班级代码
		String bjdm = model.getBjV();

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		HashMap<String, String> map = dao.getRsInfo("view_njxyzybj", "bjdm", bjdm, new String[] { "nj", "xymc", "zymc", "bjmc" });

		html.append("<div>");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"15%\">年级</td>");
		html.append("<td width=\"35%\">" + map.get("nj") + "</td>");
		html.append("<th width=\"15%\">院系</td>");
		html.append("<td width=\"35%\">" + map.get("xymc") + "</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>专业</td>");
		html.append("<td>" + map.get("zymc") + "</td>");
		html.append("<th>班级</td>");
		html.append("<td>" + map.get("bjmc") + "</td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
		html.append("<table align=\"center\" class=\"formlist\">");
		html.append("<tr>");
		html.append("<th width=\"10%\" style='text-align: center;'>职工号</th>");
		html.append("<th width=\"30%\" style='text-align: center;'>姓名</th>");
		html.append("<th width=\"30%\" style='text-align: center;'>性别</th>");
		html.append("<th width=\"30%\" style='text-align: center;'>所属部门</th>");
		html.append("</tr>");
//		html.append("</table>");
//		html.append("</div>");

		List<HashMap<String, String>> jzgList = dao.getJzgList(bjdm, lx);

//		html.append("<div style=\"width:100%;height:230px;overflow-x:hidden;overflow-y:auto;\">");
//		html.append("<table class=\"formlist\">");

		for (int i = 0; i < jzgList.size(); i++) {
			html.append("<tr>");
			html.append("<td width=\"10%\" style='text-align: center;'>" + jzgList.get(i).get("zgh") + "</td>");
			html.append("<td width=\"30%\" style='text-align: center;'>" + jzgList.get(i).get("xm") + "</td>");
			html.append("<td width=\"30%\" style='text-align: center;'>" + jzgList.get(i).get("xb") + "</td>");
			html.append("<td width=\"30%\" style='text-align: center;'>" + jzgList.get(i).get("bmmc") + "</td>");
			html.append("</tr>");
		}

		if (8 - jzgList.size() > 0) {
			for (int i = 0; i < 8 - jzgList.size(); i++) {
				html.append("<tr>");
				html.append("<td width=\"10%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("<td width=\"30%\">&nbsp;</td>");
				html.append("</tr>");
			}
		}

		html.append("</table>");
		html.append("</div>");

		html.append("<div>");
		html.append("<table class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"4\">");
		html.append("<div class=\"bz\"></div>");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  name=\"关闭\" onclick=\"closeWindown();return false;\" id=\"buttonClose\">关 闭</button>	");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		html.append("</div>");

		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 查询职工信息
	 */
	public DwwhJzgModle cxJzgxx(DwwhJzgModle model, User user, HttpServletRequest request) throws Exception {
		DAO d = new DAO();
		Map<String, String> map = dao.getSzDwxxByZgh(model.getZgh());
		// 并初始化页面查询
		request.setAttribute("mzList", d.getMzList());
		request.setAttribute("zzmmList", d.getZzmmList());

		request.setAttribute("bmList", d.getYjbmList());
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("ssList", stuDao.getSsList());// 获取省市列表
		return mapToModle(map, model);
	}

	/*
	 * public static void main(String[] args) { Map<String,String> map = new
	 * HashMap<String,String>(); map.put("ZGH", "1"); map.put("XM", "222");
	 * map.put("XB", "LXDH"); DwwhJzgModle modle = new DwwhJzgModle(); modle =
	 * new DwwhService().mapToModle(map, modle);
	 * System.out.println(modle.getZgh()+"="+modle.getXm()+modle.getLxdh()); }
	 */
	/**
	 * 修改教职工信息
	 */
	public Boolean updateJzgxx(HttpServletRequest request) {
		DwwhJzgModle model = new DwwhJzgModle();
		model = requestToModle(request, model);
		DwwhJzgDAO superDao = new DwwhJzgDAO();
		try {
			return superDao.updateJzgxx(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Map<String，String> 转换成Modle类型 重载 getModelValue
	 * 
	 * @param <T>
	 * @author zhangjw
	 * @date 2013-4-13
	 * @param map
	 * @param t
	 * @return
	 */
	public <T> T mapToModle(Map<String, String> map, T t) {
		try {
			if (map != null && t != null) {
				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(t, map.get(field.getName()));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 把request值存入Model 重载 getModelValue 方法
	 * 
	 * @author zhangjw
	 * @date 2013-4-13
	 * @param <T>
	 * @param request
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T requestToModle(HttpServletRequest request, T t) {
		Map<String, String[]> map = request.getParameterMap();
		try {
			if (map != null && t != null) {
				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String[] values = map.get(field.getName());
					if (values == null) {
						values = new String[1];
						values[0] = null;
					}
					field.set(t, values[0]);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 辅导员信息维护自定义导出
	 */
	public List<HashMap<String, String>> getDwwhExportList(SzdwGeneralForm myForm, User user) throws Exception {

		return dao.getDwwhExportList(myForm, user);
	}

	/**
	 * 修改弹出层页面
	 */
	public ArrayList<String[]> createBjxx(DwwhModel model, User user) throws Exception {

		// 类型
		String lx = model.getLx();
		// 类型名称
		String lxmc = "fdy".equalsIgnoreCase(lx) ? "辅导员" : "班主任";
		// 职工号
		String zgh = model.getZgh();
		ArrayList<String[]> bjList = null;

		if ("fdy".equalsIgnoreCase(lx)) {
			bjList = dao.getFdybjList1(zgh);
		} else {
			bjList = dao.getBzrbjList1(zgh);
		}
		return bjList;
	}

	public HashMap<String, String> ckJzgxx(String zgh) {

		return dao.getJzgxx(zgh);
	}
	/** 
	 * (查看教职工信息)浙江医学高等专科学校
	 */
	public HashMap<String, String> ckJzgxx_13023(String zgh) {
		return dao.ckJzgxx_13023(zgh);
	}
	public HashMap<String, String> ckJzgxxJxsf(String zgh) {

		return dao.getJzgxxJxsf(zgh);
	}

	/**
	 * 
	 * @描述:江西科技师范大学思政队伍辅导员信息导出个性化
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午02:25:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<File> 返回类型
	 * @throws
	 */
	public void fdyxxwhExportJxsf(SzdwGeneralForm model, OutputStream os, User user) throws Exception {
		// 获取教职工信息
		List<HashMap<String, String>> fdyList = dao.getJzgxxList(model, user);
		fdyxxwhExportJxsf(fdyList, os, user);

	}

	private HashMap<String,String> getSdbj(HashMap<String, String> fdyMap) {

		HashMap<String,String> sdbjMap=new HashMap<String,String>();
		List<HashMap<String,String>> fdyBjList = dao.getFdyBj(fdyMap.get("zgh"));
		List<HashMap<String,String>> bzrBjList = dao.getBzrBj(fdyMap.get("zgh"));
		StringBuffer sdbj = new StringBuffer();
		StringBuffer bjrs = new StringBuffer();
		int zrs=0;
		int bjgs=0;
		for (int i = 0; i < fdyBjList.size(); i++) {
			if(i!=0){
				sdbj.append(";");
				bjrs.append(";");
			}
			sdbj.append(fdyBjList.get(i).get("sdbj"));
			bjrs.append(fdyBjList.get(i).get("bjrs"));
			zrs+=Integer.parseInt(fdyBjList.get(i).get("zrs"));
			bjgs+=Integer.parseInt(fdyBjList.get(i).get("bjgs"));
		}
		for (int i = 0; i < bzrBjList.size(); i++) {
			if(StringUtils.isNotNull(sdbj.toString())){
				sdbj.append(";").append(bzrBjList.get(i).get("sdbj"));
				bjrs.append(";").append(bzrBjList.get(i).get("bjrs"));
			}
			else {
				if(i!=0){
				sdbj.append(";");
				bjrs.append(";");
				}
				sdbj.append(bzrBjList.get(i).get("sdbj"));
				bjrs.append(bzrBjList.get(i).get("bjrs"));
				
			}
			zrs+=Integer.parseInt(bzrBjList.get(i).get("zrs"));
			bjgs+=Integer.parseInt(bzrBjList.get(i).get("bjgs"));
		}
		sdbjMap.put("sdbj", sdbj.toString());
		sdbjMap.put("bjrs", bjrs.toString());
		sdbjMap.put("zrs", String.valueOf(zrs));
		sdbjMap.put("bjgs",String.valueOf(bjgs));
		return sdbjMap;
		
	}

	/**
	 * 
	 * @描述:创建文件
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午02:27:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return File 返回类型
	 * @throws
	 */
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// 创建导出文件
		File file = new File(tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis()) + ".xls");
		file.setWritable(true);
		return file;
	}

	/**
	 * 
	 * @描述:构建导出模板
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午02:26:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param user
	 * @return
	 * @throws Exception
	 *             File 返回类型
	 * @throws
	 */
	private void fdyxxwhExportJxsf(List<HashMap<String, String>> list, OutputStream os, User user) throws Exception {
		StringBuffer title = new StringBuffer();
		title.append("江西科技师范大学");
		title.append(Base.currXn).append("学年");
		title.append("辅导员（班主任）档案");
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("档案表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 26, 1);
		// ws.mergeCells(0, 2, 7, 3);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 15, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "部门", wcf2));
		ws.addCell(new Label(2, 2, "姓名", wcf2));
		ws.addCell(new Label(3, 2, "身份", wcf2));
		ws.addCell(new Label(4, 2, "编制", wcf2));
		ws.addCell(new Label(5, 2, "证件", wcf2));
		ws.addCell(new Label(6, 2, "身份证号码", wcf2));
		ws.addCell(new Label(7, 2, "农行卡号", wcf2));
		ws.addCell(new Label(8, 2, "带班津贴", wcf2));
		ws.addCell(new Label(9, 2, "所带班级", wcf2));
		ws.addCell(new Label(10, 2, "性别", wcf2));
		ws.addCell(new Label(11, 2, "出生年月", wcf2));
		ws.addCell(new Label(12, 2, "政治面貌", wcf2));
		ws.addCell(new Label(13, 2, "最高学历", wcf2));
		ws.addCell(new Label(14, 2, "最高学历获得年份", wcf2));
		ws.addCell(new Label(15, 2, "最高学位", wcf2));
		ws.addCell(new Label(16, 2, "最高学位获得年份", wcf2));
		ws.addCell(new Label(17, 2, "现任职务", wcf2));
		ws.addCell(new Label(18, 2, "任职时间", wcf2));
		ws.addCell(new Label(19, 2, "任同职级时间", wcf2));
		ws.addCell(new Label(20, 2, "职称", wcf2));
		ws.addCell(new Label(21, 2, "评聘时间", wcf2));
		ws.addCell(new Label(22, 2, "参加工作时间", wcf2));
		ws.addCell(new Label(23, 2, "从事学生工作时间", wcf2));
		ws.addCell(new Label(24, 2, "移动电话", wcf2));
		ws.addCell(new Label(25, 2, "参加培训", wcf2));
		ws.addCell(new Label(26, 2, "评奖评优", wcf2));
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);
				// 获取所带班级
				HashMap<String,String> sdbj = getSdbj(map);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 14);
				ws.setColumnView(4, 14);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 20);
				ws.setColumnView(10, 5);
				ws.setColumnView(11, 20);
				ws.setColumnView(12, 25);
				ws.setColumnView(13, 10);
				ws.setColumnView(14, 15);
				ws.setColumnView(15, 10);
				ws.setColumnView(16, 15);
				ws.setColumnView(17, 15);
				ws.setColumnView(18, 13);
				ws.setColumnView(19, 13);
				ws.setColumnView(20, 10);
				ws.setColumnView(21, 13);
				ws.setColumnView(22, 13);
				ws.setColumnView(23, 13);
				ws.setColumnView(24, 18);
				ws.setColumnView(25, 20);
				ws.setColumnView(26, 20);
				ws.addCell(new Label(0, 3 + i, String.valueOf(i+1), wcf2));// 序号
				ws.addCell(new Label(1, 3 + i, map.get("bmmc"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("kzzd16"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("bzmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, "身份证", wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(7, 3 + i, "", wcf2));
				ws.addCell(new Label(8, 3 + i, "", wcf2));
				ws.addCell(new Label(9, 3 + i, sdbj.get("sdbj"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("xbmc"), wcf2));
				ws.addCell(new Label(11, 3 + i, map.get("csrq"), wcf2));
				ws.addCell(new Label(12, 3 + i, map.get("zzmmmc"), wcf2));
				ws.addCell(new Label(13, 3 + i, map.get("xl"), wcf2));
				ws.addCell(new Label(14, 3 + i, map.get("kzzd20"), wcf2));
				ws.addCell(new Label(15, 3 + i, map.get("xw"), wcf2));
				ws.addCell(new Label(16, 3 + i, map.get("kzzd13"), wcf2));
				ws.addCell(new Label(17, 3 + i, map.get("zwmc"), wcf2));
				ws.addCell(new Label(18, 3 + i, map.get("kzzd7"), wcf2));
				ws.addCell(new Label(19, 3 + i, map.get("kzzd8"), wcf2));
				ws.addCell(new Label(20, 3 + i, map.get("zcmc"), wcf2));
				ws.addCell(new Label(21, 3 + i, map.get("kzzd6"), wcf2));
				ws.addCell(new Label(22, 3 + i, map.get("cjgzrq"), wcf2));
				ws.addCell(new Label(23, 3 + i, map.get("szgzsj"), wcf2));
				ws.addCell(new Label(24, 3 + i, map.get("yddh"), wcf2));
				ws.addCell(new Label(25, 3 + i, map.get("pxqk"), wcf2));
				ws.addCell(new Label(26, 3 + i, map.get("kzzd4"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void dbqkbData(SzdwGeneralForm model, OutputStream os, User user) throws Exception {

		List<HashMap<String, String>> dbqkList = dao.getDbxx(model, user);
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(Base.currXn).append("学年").append(Base.dqxqmc);
		title.append("班主任带班情况一览表");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("带班情况一览表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);

		ws.addCell(new Label(0, 3, "序号", wcf2));
		ws.addCell(new Label(1, 3, "部门", wcf2));
		ws.addCell(new Label(2, 3, "姓名", wcf2));
		ws.addCell(new Label(3, 3, "身份", wcf2));
		ws.addCell(new Label(4, 3, "所带班级", wcf2));
		ws.addCell(new Label(5, 3, "学生数", wcf2));
		ws.addCell(new Label(6, 3, "所带学生总数", wcf2));
		ws.addCell(new Label(7, 3, "带班数(个)", wcf2));
		ws.addCell(new Label(8, 3, "年龄", wcf2));
		ws.addCell(new Label(9, 3, "性别", wcf2));
		ws.addCell(new Label(10, 3, "电话号码", wcf2));
		int r1=0,r2=3;
		if (dbqkList != null && dbqkList.size() > 0) {
			for (int i = 0; i < dbqkList.size(); i++) {
				HashMap<String, String> map = dbqkList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 25);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 18);
				ws.setColumnView(4, 30);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 15);
				ws.addCell(new Label(0, 4 + i, map.get("rn"), wcf2));// 序号
				ws.addCell(new Label(1, 4 + i, map.get("bmmc"), wcf2));
				ws.addCell(new Label(2, 4 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(3, 4 + i, map.get("kzzd16"), wcf2));
				ws.addCell(new Label(4, 4 + i, map.get("bjmc"), wcf2));
				ws.addCell(new Label(5, 4 + i, map.get("bjrs"), wcf2));
				ws.addCell(new Label(6, 4 + i, map.get("zrs"), wcf2));
				ws.addCell(new Label(7, 4 + i, map.get("bjgs"), wcf2));
				ws.addCell(new Label(8, 4 + i, map.get("nl"), wcf2));
				ws.addCell(new Label(9, 4 + i, map.get("xbmc"), wcf2));
				ws.addCell(new Label(10, 4 + i, map.get("yddh"), wcf2));
				if(r1!=Integer.parseInt(map.get("rn"))){//rn不一致时对上一个rn一致的行进行合并
					r1=Integer.parseInt(map.get("rn"));
					//if(i+4-r2>1){
						ws.mergeCells(0, r2, 0, i+3);//序号
						ws.mergeCells(1, r2, 1, i+3);//部门
						ws.mergeCells(2, r2, 2, i+3);//姓名
						ws.mergeCells(3, r2, 3, i+3);//身份
						ws.mergeCells(6, r2, 6, i+3);//所在学生总数
						ws.mergeCells(7, r2, 7, i+3);//带班数
						ws.mergeCells(8, r2, 8, i+3);//金额
						ws.mergeCells(9, r2, 9, i+3);//性别
						ws.mergeCells(10 ,r2, 10, i+3);//电话号码
					//}
					r2=i+4;
				}
			}
		}
		//最后一个rn合并
		ws.mergeCells(0, r2, 0, dbqkList.size()+3);//序号
		ws.mergeCells(1, r2, 1, dbqkList.size()+3);//部门
		ws.mergeCells(2, r2, 2, dbqkList.size()+3);//姓名
		ws.mergeCells(3, r2, 3, dbqkList.size()+3);//身份
		ws.mergeCells(6, r2, 6, dbqkList.size()+3);//所在学生总数
		ws.mergeCells(7, r2, 7, dbqkList.size()+3);//带班数
		ws.mergeCells(8, r2, 8, dbqkList.size()+3);//金额
		ws.mergeCells(9, r2, 9, dbqkList.size()+3);//性别
		ws.mergeCells(10 ,r2, 10, dbqkList.size()+3);//电话号码
		ws.mergeCells(0, 2, 10, 2);
		ws.setRowView(0, 500);
		ex.printToOneCell_multy(ws, "____________学院（党委盖章）：                 分管领导签字：     党委负责人签字：      ；班级数：    个；学生人数：  人", 0, 2, 10, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void pbbExportData(SzdwGeneralForm model, OutputStream os, User user) throws Exception {
		List<HashMap<String, String>> dmList = dao.getFdySflx();
		List<HashMap<String, String>> pbqkList = dao.getPbxxList1(model, user,dmList);
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(Base.currXn).append("学年").append(Base.dqxqmc);
		title.append("各学院辅导员、班主任配备情况一览");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("配备情况一览表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 15, 1);
		ws.mergeCells(2, 2, 2+dmList.size()-1, 2);
		
		ws.mergeCells(0, 2, 0, 3);
		ws.mergeCells(1, 2, 1, 3);
		ws.mergeCells(2+dmList.size(), 2, 2+dmList.size(), 3);
		ws.mergeCells(3+dmList.size(), 2, 3+dmList.size(), 3);
		ws.mergeCells(4+dmList.size(), 2, 4+dmList.size(), 3);
		ws.mergeCells(5+dmList.size(), 2, 5+dmList.size(), 3);
		ws.mergeCells(6+dmList.size(), 2, 6+dmList.size(), 3);
		ws.mergeCells(7+dmList.size(), 2, 7+dmList.size(), 3);
		ws.mergeCells(8+dmList.size(), 2, 8+dmList.size(), 3);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ex.printToOneCell_multy(ws, "专职辅导员", 2, 2, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ex.printToOneCell_multy(ws, "序号", 0, 2, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ws.addCell(new Label(1, 2, "学院", wcf2));
		for (int i = 0; i < dmList.size(); i++) {
			ws.addCell(new Label(i+2, 3, dmList.get(i).get("lxmc"), wcf2));	
		}
		ws.addCell(new Label(dmList.size()+2, 2, "兼职辅导员", wcf2));
		ws.addCell(new Label(dmList.size()+3, 2, "学生人数", wcf2));
		ws.addCell(new Label(dmList.size()+4, 2, "班级数", wcf2));
		ws.addCell(new Label(dmList.size()+5, 2, "班主任", wcf2));
		ws.addCell(new Label(dmList.size()+6, 2, "专职辅导员总人数", wcf2));
		ws.addCell(new Label(dmList.size()+7, 2, "各学院兼职辅导员总人数", wcf2));
		ws.addCell(new Label(dmList.size()+8, 2, "各学院班主任总人数", wcf2));
		if (pbqkList != null && pbqkList.size() > 0) {
			
			for (int i = 0; i < pbqkList.size(); i++) {
				HashMap<String, String> map = pbqkList.get(i);
				//获取班主任人数
				int bzrrs=0;
				if(null!=map.get("bzr")&&""!=map.get("bzr")){
					bzrrs=map.get("bzr").split(";").length;
				}
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				for (int j = 0; j < dmList.size(); j++) {
					ws.setColumnView(j+2, 10);
				}
				ws.setColumnView(dmList.size()+2, 20);
				ws.setColumnView(dmList.size()+3, 10);
				ws.setColumnView(dmList.size()+4, 10);
				ws.setColumnView(dmList.size()+5, 20);
				ws.setColumnView(dmList.size()+6, 10);
				ws.setColumnView(dmList.size()+7, 10);
				ws.setColumnView(dmList.size()+8, 10);
				ws.addCell(new Label(0, 4 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 4 + i, map.get("xymc"), wcf2));
				int zzfdyrs=0;
				for (int j = 0; j < dmList.size(); j++) {
					ws.addCell(new Label(j+2, 4 + i, map.get("zzfdy"+j), wcf2));
					if(null!=map.get("zzfdy"+String.valueOf(j))){
						zzfdyrs=zzfdyrs+map.get("zzfdy"+String.valueOf(j)).split(";").length;
					}
				}
				int jzfdyrs=0;
				if(null!=map.get("jzfdy")){
					 jzfdyrs=map.get("jzfdy").split(";").length;
				}
				ws.addCell(new Label(dmList.size()+2, 4 + i, map.get("jzfdy"), wcf2));
				ws.addCell(new Label(dmList.size()+3, 4 + i, map.get("sumxs"), wcf2));
				ws.addCell(new Label(dmList.size()+4, 4 + i, map.get("sumbj"), wcf2));
				ws.addCell(new Label(dmList.size()+5, 4 + i, map.get("bzr"), wcf2));
				ws.addCell(new Label(dmList.size()+6, 4 + i, String.valueOf(zzfdyrs), wcf2));
				ws.addCell(new Label(dmList.size()+7, 4 + i, String.valueOf(jzfdyrs), wcf2));
				ws.addCell(new Label(dmList.size()+8, 4 + i, bzrrs+"", wcf2));
				
		
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @描述: 思政队伍设置保存
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-10 下午02:39:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param sf 是否思政队伍
	 * @return
	 * @throws Exception
	 */
	public boolean szdwSzSave(String ids, String sf) throws Exception {
		return dao.szdwSzSave(ids, sf);
	}
	
	/**
	 * 
	 * @描述: 导出带班学生信息(江西航空)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-10-12 下午02:12:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> exportDbStu(String zgh,String lx){
		return dao.exportDbStu(zgh, lx);
	}
}
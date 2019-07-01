package xsgzgl.pjpy.general.xmsz.sjsz;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.inter.xmsz.XmszSjszInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_时间设置_通用_Service类
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

public class XmszSjszService extends CommService implements XmszSjszInterface {

	XmszSjszDAO dao = new XmszSjszDAO();

	/**
	 * 初始化项目时间设置
	 * 
	 * @author 伟大的骆
	 */
	public void defaultSjszSetting(XmszSjszModel model, User user,
			HttpServletResponse response) throws IOException {
		
		// 项目代码
		String xmdm = model.getXmdm();
		// 时间控制信息
		HashMap<String, String> map = dao.getSjszInfo(xmdm);
		String sqkzkg = map.get("sqkzkg");// 申请控制开关
		String shkzkg = map.get("shkzkg");// 审核控制开关
		String sqkssj = map.get("sqkssj");// 申请开始时间
		sqkssj = Base.isNull(sqkssj) ? "" : sqkssj;
		String sqjssj = map.get("sqjssj");// 申请结束时间
		sqjssj = Base.isNull(sqjssj) ? "" : sqjssj;
		String shkssj = map.get("shkssj");// 审核开始时间
		shkssj = Base.isNull(shkssj) ? "" : shkssj;
		String shjssj = map.get("shjssj");// 审核结束时间
		shjssj = Base.isNull(shjssj) ? "" : shjssj;
		String bz = map.get("bz");// 备注
		bz = Base.isNull(bz) ? "" : bz;
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr style=\"height:22px\">");
		html.append("<th colspan=\"2\">	");
		html.append("<span>项目时间控制</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");
		
		html.append("<tbody>");
		//申请相关
		html.append("<tr>");
		html.append("<th width=\"30%\">开放申请时间</th>");
		html.append("<td>");
		html.append("<input type=\"text\" name=\"str_sqkssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+sqkssj+"\" ");
		html.append("id=\"sqkssj\"/>");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("至");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("<input type=\"text\" name=\"str_sqjssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+sqjssj+"\" ");
		html.append("id=\"sqjssj\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("申请开关");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" name=\"sqkzkg\" ");
		html.append("value=\"yes\" ");
		html.append("onclick=\"setCheckedValue(this);\" ");
		if("yes".equalsIgnoreCase(sqkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("id=\"sqkzkg_yes\"/>开放");
		html.append("<input type=\"radio\" name=\"sqkzkg\" ");
		html.append("value=\"no\" ");
		html.append("onclick=\"setCheckedValue(this);\" ");
		if(Base.isNull(sqkzkg) || "no".equalsIgnoreCase(sqkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("id=\"sqkzkg_no\"/>关闭");
		html.append("<input type=\"hidden\" name=\"str_sqkzkg\" id=\"sqkzkg_check\" ");
		if ("yes".equalsIgnoreCase(sqkzkg)) {
			html.append("value=\"yes\" ");
		} else if (Base.isNull(sqkzkg) || "no".equalsIgnoreCase(sqkzkg)) {
			html.append("value=\"no\" ");
		}
		html.append("/>");
		html.append("</td>");
		html.append("</tr>");
		
		html.append("<tr>");
		html.append("<th>");
		html.append("申请开关备注");
		html.append("<br/>");
		html.append("<font color=\"blue\">(限100字)</font>");
		html.append("</th>");
		html.append("<td>");
		html.append("<textarea name=\"str_bz\"");
		html.append("style=\"width:90%;word-break:break-all;\" ");
		html.append("onblur=\"chLeng(this,100)\" ");
		html.append(">");
		html.append(bz);
		html.append("</textarea>");
		html.append("</td>");
		html.append("</tr>");
		
		//审核相关
		html.append("<tr>");
		html.append("<th width=\"30%\">开放审核时间</th>");
		html.append("<td>");
		html.append("<input type=\"text\" name=\"str_shkssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+shkssj+"\" ");
		html.append("id=\"shkssj\"/>");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("至");
		html.append("&nbsp;&nbsp;&nbsp;");
		html.append("<input type=\"text\" name=\"str_shjssj\" ");
		html.append("onblur=\"dateFormatChg(this)\" style=\"cursor:hand;\" ");
		html.append("onclick=\"return showCalendar(this.id,'yyyyMMdd');\" ");
		html.append("style=\"width:100px;\" readOnly=\"true\" ");
		html.append("value=\""+shjssj+"\" ");
		html.append("id=\"shjssj\"/>");
		html.append("</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<th>");
		html.append("审核开关");
		html.append("</th>");
		html.append("<td>");
		html.append("<input type=\"radio\" name=\"shkzkg\" ");
		html.append("value=\"yes\" ");
		if("yes".equalsIgnoreCase(shkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("onclick=\"setCheckedValue(this);\" ");
		html.append("id=\"shkzkg_yes\"/>开放");
		html.append("<input type=\"radio\" name=\"shkzkg\" ");
		html.append("value=\"no\" ");
		if(Base.isNull(shkzkg) || "no".equalsIgnoreCase(shkzkg)){
			html.append("checked=\"true\" ");
		}
		html.append("onclick=\"setCheckedValue(this);\" ");
		html.append("id=\"shkzkg_no\"/>关闭");
		html.append("<input type=\"hidden\" name=\"str_shkzkg\" id=\"shkzkg_check\" ");
		if ("yes".equalsIgnoreCase(shkzkg)) {
			html.append("value=\"yes\" ");
		} else if (Base.isNull(shkzkg) || "no".equalsIgnoreCase(shkzkg)) {
			html.append("value=\"no\" ");
		}
		html.append("</td>");
		html.append("</tr>");
		
		html.append("</tbody>");
		// -------------------------按钮---------------------------
		html.append("<table width=\"100%\" border=\"0\" class=\"formlist\">");
		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  onclick=\"checkSaveSjsz();return false;\">保 存</button>");
		html.append("<button type=\"button\"  onclick=\"closeWindown();return false;\">关 闭</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");
		html.append("</table>");
		// -------------------------按钮 end---------------------------
		
		response.getWriter().print(html.toString());
		
	}

	/**
	 * 删除时间设置
	 * 
	 * @author 伟大的骆
	 */
	public Boolean deleteSjsz(XmszSjszModel model, User user) {

		boolean flag = false;
		// 项目代码
		String xmdm = model.getXmdm();
		
		try {
			flag = dao.deleteSjszb(xmdm, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 保存时间设置
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveSjsz(XmszSjszModel model, User user) {

		String xmdm = model.getXmdm();// 项目代码
		String sqkzkg = model.getSqkzkg();// 申请控制开关
		String shkzkg = model.getShkzkg();// 审核控制开关
		String sqkssj = model.getSqkssj();// 申请开始时间
		String sqjssj = model.getSqjssj();// 申请结束时间
		String shkssj = model.getShkssj();// 审核开始时间
		String shjssj = model.getShjssj();// 审核结束时间
		String bz = model.getBz();// 备注
		
		boolean flag = deleteSjsz(model, user);

		if (flag) {
			try {
				flag = dao.insertSjszb(xmdm, sqkzkg, shkzkg, sqkssj, sqjssj,
						shkssj, shjssj, bz, user);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		
		return flag;
	}
}

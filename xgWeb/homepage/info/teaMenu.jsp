<jsp:directive.page import="xgxt.comm.xtwh.CommXtwhService" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<jsp:directive.page import="xgxt.DAO.DAO" />
<jsp:directive.page import="xgxt.action.CommanAction" />
<jsp:directive.page import="xgxt.xtwh.xtwhOther.XtwhOtherService" />
<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="navbar_index" id="navbar_index">
<div class="container">
	<nav id="menuNav" class="navbar navbar-default navbar-static" role="navigation">
		<ul class="nav navbar-nav" id="nav_ul">
			<%
				String xstsType = null;
				try {
				String userName = (String)session.getAttribute("userName");
				String userType = (String)session.getAttribute("userOnLine");
				String xxdm = (String)session.getAttribute("xxdm");
				String userChange = (String)session.getAttribute("userChange");
				String type = (String)session.getAttribute("type");
				String yhm = userName;
				String sql = "";
				DAO dao = DAO.getInstance();
				if (userType.equalsIgnoreCase("teacher")) {
					sql = "select gnmkdm,gnmkmc from view_yhqx_dj where yhm=? and length(gnmkdm)=3 order by to_number(xssx)";
					CommanAction ca = new CommanAction();
					String[] returnRs = ca.getUsrSQL(sql, userName, xxdm, true,
					Boolean.parseBoolean(session.getAttribute("isFdy")
					.toString()));
					ca = null;
					sql = returnRs[0];
					userName = returnRs[1];
				} else if (userType.equalsIgnoreCase("student")) {
					XtwhOtherService xtwhService = new XtwhOtherService();
					String [] returnMes = xtwhService.getTopMenuForStu(Base.xxdm, userName);
					sql = returnMes[0];
					xstsType = returnMes[1];
					userName = "6727";
				}

				String[] inputValue = { userName };
				String[] outputValue = { "gnmkdm", "gnmkmc" };
				String menuListTop = dao.getStringToSplit(sql, inputValue,
				outputValue);
				String[] printOut = menuListTop.split("!!SplitSignOne!!");
				out.print(" <li class='dropdown open' id=\"li-" + 0
				+ "\"><a href=\"teaPage.jsp\"' >");
				out.print("首页");
				out.println("</a></li>");	
				for (int i = 1; i < printOut.length; i++) {
					String[] gnmkcf = printOut[i].split("!!SplitSignTwo!!");
					out.print(" <li class='dropdown' id='li-"+i+"'><a  href=\"main1.jsp?act=left&menuTop=");
					out.print(gnmkcf[1]);
					out.print("\" title='");
					out.print(gnmkcf[2]);
					out.print("' id='");
					out.print(gnmkcf[1]);
					out.print("'>");
					out.print(gnmkcf[2]);
					out.println("</a></li>");
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
			<!-- 更多菜单 -->
			<li class="dropdown dropdown-list" id="moreMenuLi">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<img class="menu-icon" src="images/menu_icon.png"></a>
				<ul class="dropdown-menu" id="dropdown-menu">
				</ul>
			</li>
			<!-- 更多菜单 end-->
		</ul>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.action.Base"/>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Pragma" http-equiv="no-cache" />
<meta http-equiv="Cache-Control" http-equiv="no-cache" />
<meta http-equiv="Expires" http-equiv="0" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
<body onselectstart="return false" ondragstart="return false" onbeforecopy="return false" oncopy="document.selection.empty()" onselect="document.selection.empty()"> 
<html:form action="/initMenu"> 
<input type="hidden" value="<bean:write name="menuList"/>" name="menuTop" /> 
<div style="background:url(images/headbg.gif)"> <img src=images/school_logo.gif> <img src=images/system_xsgz.gif> (
 			 <%
			String tmp = session.getAttribute("userType").toString();
			if(Base.isNull(tmp)){
				out.print("�д�����");
			}else if(tmp.equalsIgnoreCase("admin")){
				out.print("����Ա��");
			}else if(tmp.equalsIgnoreCase("xy")){
				out.print("<bean:message key="lable.xsgzyxpzxy" />��");
			}else if(tmp.equalsIgnoreCase("stu")){
				out.print("ѧ����");
			}else{
				out.print("ͨ�ð�");
			}
			%> 
  ) </div> 
<div class="topframe"> 
  <div class="menubg"></div> 
    <div class="menu_link" style="width:92%;"> 
      <div id="menuCont" style="position:absolute; left:0px; top:0px;line-height:23px;"> 
        <script language="javascript">
						    var menuText = document.forms[0].menuTop.value;
						 	InitTopMenu(menuText);
						</script> 
      </div> 
  </div> 
  <ul class="tool" style="vertical-align:middle;"> 
    <li style="margin-top:5px" class="menu_out_l" title="�˵��ҹ�" onmouseover="setInterval('menuMove(0)',5)" onmouseout="setInterval('menuMove(1)',5)"></li> 
    <li style="margin-top:5px" class="menu_out_r" title="�˵����" onmouseover="setInterval('menuMove(1)',5)" onmouseout="setInterval('menuMove(0)',5)"></li> 
    <li class="tool_0" title="�л����" onclick="window.top.location='main1.jsp'"></li> 
    <li class="tool_1" title="�˳�ϵͳ" onclick="if(confirm('ȷ��Ҫ�˳���')){window.top.location='chkLogout.do';return true;}return false;"></li> 
    <li class="tool_2" title="�ر�ҳ��" onclick="window.top.close()"></li> 
    <li class="tool_3" title="ϵͳ����" onclick="window.open('help.htm')"></li> 
  </ul> 
</div> 
</html:form> 
</body>
</html>

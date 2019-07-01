<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="lyjszxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />

		<table width="100%" id="theTable">
		<tr>
		<td>
		<p align="center" style="font-size:20px"><strong>西南民族大学&nbsp;</strong><strong><bean:write name="rs" property="nj" /></strong><strong>&nbsp;级绿色通道新生报到单 </strong>
</p>
<p align="center">
<strong>
姓名：&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
性别：&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xb" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
<bean:message key="lable.xsgzyxpzxy" />：&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xymc" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
专业：&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xmc" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
民族：&nbsp;
</strong>
<strong>
<bean:write name="rs" property="mzmc" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<br>
</p>
<p align="left"><strong>请按以下项目报到 </strong><strong></strong></p>
<table width="100%" class="tbstyle">
  <tr>
    <td scope="col" width="30%" height="30"><p align="center"><strong>单位</strong></p></td>
    <td scope="col" width="40%"><p align="center"> <strong>项 目</strong></p></td>
    <td scope="col" width="30%"><p align="center"> <strong>签 字</strong></p></td>
  </tr>
  <tr>
    <td scope="row" width="30%" height="30"><div align="center"> <strong>学生工作部（处）</strong></div></td>
    <td> 送关爱卡，接受咨询，协助办理国家助学贷款、人生意外保险、医疗住院保险</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>宣传部</strong></div></td>
    <td> 办理赠报手续 </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>网络管理中心</strong></div></td>
    <td> 办理一卡通，空卡充值，照像 </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>档案馆</strong></div></td>
    <td> 交本人档案（此项仅限于自带档案的学生） </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>招生就业处</strong></div></td>
    <td> 交中学信息采集表 </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>保卫处</strong></div></td>
    <td> 办理户籍手续，领取军训服装等 </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>校医院</strong></div></td>
    <td> 体检（仅限新校区新生） </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>计防科</strong></div></td>
    <td> 办理预防接种（仅限新校区新生） </td>
    <td>&nbsp;</td>
  </tr>
</table>
<p align="left"><strong>注：以上项目完毕后，请将此单交回学生工作部（处）并领取学生手册、校徽及入学完毕通知单。 </strong></p>
		</td>
		</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable',' ')" />
	</div>
</body>
</html:html>

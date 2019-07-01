<%@ page language="java" contentType="text/html; charset=GBK"%>
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
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
</head>
<body>
	<div class=Section1 style='layout-grid:15.6pt' align="center">
		<b>金华职业技术<bean:message key="lable.xsgzyxpzxy" />“二十佳层长”评选申请表</b>
		<table border=1 cellspacing=0 cellpadding=0 width=584 align=center>
			<tr>
				<td width=55 class="Normal"align=center>
					<p align=center style='text-align:center;line-height:150%'>
						姓名
					</p>
				</td>
				<td width=136 class="Normal" align="center">
					<bean:write name="rs" property="xm" />&nbsp;
				</td>
				<td width=104 class="Normal" align=center>
					<p align=center style='text-align:center;line-height:150%'>
						所在<bean:message key="lable.xsgzyxpzxy" />
					</p>
				</td>
				<td width=289 colspan=3 class="Normal" align="center">
					<bean:write name="rs" property="xymc" />&nbsp;
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align=center>
					楼幢
				
				</td>
				<td width=136 class="Normal" align="center">
					<bean:write name="ldmc" />&nbsp;
				</td>
				<td width=104 class="Normal" align=center>
					寝室
				
				</td>
				<td width=72 class="Normal">
					<bean:write name="qsh" />&nbsp;
				</td>
				<td width=85 class="Normal" align=center>
					联系电话
				
				</td>
				<td width=132 class="Normal" align="center">
					<bean:write name="lxdh" />&nbsp;
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					本
					<br>
					楼
					<br>
					层
					<br>
					情
					<br>
					况
				</td>
				<td width=529 colspan=5 class="Normal" valign="bottom">
					<p align="left">
						<bean:write name="szlcqk" />
					</p>

					<p align="right">
						申请人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					楼					
					<br>
					长
					<br>
					意
					<br>
					见
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom">
					<p align="right">	
						签 名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;
						月&nbsp;&nbsp; 日
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					辅
					<br>
					导
					<br>
					员
					<br>
					意
					<br>
					见
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom" >
					<p align="right">
						
						签 名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					学
					<br>
					工
					<br>
					办
					<br>
					意
					<br>
					见
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom" >
					<p align="right">
					
						（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width=55 class="Normal" align="center">
					<br>
					宿舍
					<br>
					管理
					<br>
					中心
					<br>
					意见
				</td>
				<td width=529 colspan=5  class="Normal" valign="bottom" >
					<p align="right">
						（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>		
	</div>
					<br>
				<div class="buttontool noprint" align="center">
					<input type="button" class="button2" value="页面设置"
						onclick="WebBrowser.ExecWB(8,1);">
					<input type="button" class="button2" value="打印预览"
						onclick="WebBrowser.ExecWB(7,1)">
					<input type="button" class="button2" value="直接打印"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
	
</body>

</html:html>

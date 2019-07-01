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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
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
	<body>
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<html:form action="/jhzy_rych" method="post">
				<div class=Section1 style='layout-grid:15.6pt;font-size: 20'>
					${xxmc}优秀毕业生登记表 &nbsp;
				</div>
				<div>
					院（系）：${rs.xymc}&nbsp;&nbsp;&nbsp;&nbsp;
					专业：${rs.zymc}&nbsp;&nbsp;&nbsp;&nbsp; 班级：${rs.bjmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;日
				</div>
				<table width="100%" class="tbstyle" align="center">
					<tr align="center">
						<td width="10%">
							姓 名
						</td>
						<td>
							${rs.xm }
						</td>
						<td width="8%">
							性 别
						</td>
						<td>
							${rs.xb }
						</td>
						<td colspan=2 width="10%">
							出生年月
						</td>
						<td>
							${rs.csrq }
						</td>
						<td width="8%">
							民 族
						</td>
						<td>
							${rs.mzmc }
						</td>
					</tr>
					<tr align="center">
						<td>
							生源地
						</td>
						<td colspan=3>
							${rs.syd }
						</td>
						<td colspan=2>
							政治面貌
						</td>
						<td>
							${rs.zzmmmc }
						</td>
						<td>
							职 务
						</td>
						<td>
							${rs.zw }
						</td>
					</tr>
					<tr align="center">
						<td>
							家庭地址
						</td>
						<td colspan=6>
							${rs.jtdz }
						</td>
						<td>
							联系电话
						</td>
						<td>
							${rs.sjhm }
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle">
					<tr align="center">
						<td width="10%" align="center" rowspan="2">
							本
							<br>
							人
							<br>
							基
							<br>
							本
							<br>
							情
							<br>
							况
						</td>
						<td width="15%">
							英语等级
						</td>
						<td width="30%">

						</td>
						<td width="15%">
							计算机等级
						</td>
						<td>

						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" class="tbstyle">
								<tr align="center">
								<td  width="20%">
							       学年/学期
					            </td>
					            <td width="15%">
							       学习成绩名次
					            </td>
					            <td width="15%">
							       德育素质分名次
					            </td>
					            <td width="15%">
							       综合测评分名次
					            </td>
					            <td width="35%">
							       评优情况
					            </td>
								</tr>
								<logic:iterate id="s" name="cjList">
								<tr align="center">
								<td  >
							       <bean:write name="s" property="xn"/>/<bean:write name="s" property="xqmc"/>
					            </td>
					            <td >
							      <bean:write name="s" property="zypm"/>
					            </td>
					            <td >
							      <bean:write name="s" property="dypm"/>
					            </td>
					            <td >
							       <bean:write name="s" property="zhpm"/>
					            </td>
					            <td >
							      
					            </td>
								</tr>
								
								</logic:iterate>								
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle">
					<tr>
						<td width="10%" align="center">
							本
							<br>
							人
							<br>
							简
							<br>
							历
						</td>
						<td>
							${brjl }
						</td>
					</tr>
					<tr>
						<td align="center">
							主要
							<br>
							事迹
							<br>
							及
							<br>
							获奖
							<br>
							情况
						</td>
						<td>
							${zysj }
						</td>
					</tr>

				</table>
				<table width="100%" class="tbstyle" >
					<tr >
						<td width="10%" align="center">
							班
							<br>
							主
							<br>
							任
							<br>
							意
							<br>
							见
						</td>
						<td valign="bottom" width="20%">
							
							 <div align="right">
							 
							 年 &nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp; 日
							 </div>
                             
							
						</td>
						<td   width="10%" align="center">
							学
							<br>
							院
							<br>
							意
							<br>
							见
						</td>
						<td valign="bottom" width="20%">
							${rsShyj.xyshyj }
							 <div align="right">
							 
							 年 &nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp; 日
							 </div>
						</td>
						<td align="center" >
							学
							<br>
							校							
							<br>
							意
							<br>
							见
						</td>
						<td valign="bottom" width="20%">
							 ${rsShyj.xxshyj }
							 <div align="right">
							 
							 年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日
							 </div>
						</td>
					</tr>					
					<tr >
						<td valign=top align="center">
							备<br><br>注
						</td>
						<td colspan=5 valign=top>
							&nbsp;
						</td>
					</tr>
				</table>
				<div align="right">
					&nbsp;
				</div>
				<div align="left">
					注：1.此表一式三份：本人档案、<bean:message key="lable.xsgzyxpzxy" />、学校各一份。
					<br>
					&nbsp; &nbsp; &nbsp; 2.本表可用打印或钢笔填写，字迹清楚，经<bean:message key="lable.xsgzyxpzxy" />、学校盖章，领导签字方有效。
				</div>
				<br>
				<br>
				<div class="buttontool noprint" align="center">
					<input  class="button2" value="页面设置"
						onclick="WebBrowser.ExecWB(8,1);">
					<input  class="button2" value="打印预览"
						onclick="WebBrowser.ExecWB(7,1)">
					<input  class="button2" value="直接打印"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>

</html>

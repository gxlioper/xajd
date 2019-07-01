<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<html:form action="/dxjxjsp">
		<p align="center" style="font-size:18px;font:'黑体' ">
			<b>浙江省普通高等学校优秀毕业生登记表</b>
		</p>
      
		<table width="600px" align="center" class="printstyle">
			&nbsp;&nbsp;&nbsp;学校：&nbsp;${xxmc }&nbsp;院（系）：&nbsp;${rs.xymc }&nbsp;专业：&nbsp;${rs.zymc }&nbsp; <br />
     &nbsp;&nbsp;&nbsp;班级：&nbsp;${rs.bjmc }&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;${rs.y }&nbsp;年 &nbsp;${rs.m }&nbsp; 月 &nbsp;${rs.r }&nbsp; 日
			<tr>
				<td width="72px">
					<div align="center">
						姓&nbsp;&nbsp;&nbsp;&nbsp;名
					</div>
				</td>
				<td width="108px">
					<div align="center">
						${rs.xm }
					</div>
				</td>
				<td width="66px">
					<div align="center">
						性&nbsp;&nbsp;&nbsp;&nbsp;别
					</div>
				</td>
				<td width="66px">
					<div align="center">
						${rs.xb }
					</div>
				</td>
				<td width="66px">
					<div align="center">
						出生年月
					</div>
				</td>
				<td width="66px">
					<div align="center">
						${rs.csrq }
					</div>
				</td>
				<td width="66px">
					<div align="center">
						民族
					</div>
				</td>
				<td width="90px">
					<div align="center">
						${rs.mzmc }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						生&nbsp;源&nbsp;地
					</div>
				</td>
				<td>
					<div align="center">
						${rs.syd }
					</div>
				</td>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.zzmmmc }
					</div>
					<div align="center"></div>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						职务
					</div>
				</td>
				<td>
					<div align="center">
						${rs.drzw }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭
						<br />
						地址
					</div>
				</td>
				<td colspan="4">
					<div align="center">
						${rs.jtdz }
					</div>
					<div align="center"></div>
					<div align="center"></div>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						${rs.lxdh }
					</div>
					<div align="center"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						本
						<br />
						人
						<br />
						简
						<br />
						历
					</div>
					<br />
				</td>
				<td colspan="7">
					
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.brjl }
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						主
						<br />
						要
						<br />
						事
						<br />
						迹
					</div>
					<br />
				</td>
				<td colspan="7">
					
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.zysj }
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
		</table>
		<p align="center">
注：1．此表一式两份：学生本人档案、学校各一份。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
2．本表内容可打印或用钢笔填写，字迹清楚，经院（系）、学校盖章，领导签字方有效。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
		  
<%--		  <p>&nbsp;</p><p>&nbsp;</p>--%>
		<%--		  <p>&nbsp;</p><p>&nbsp;</p>--%>
		<div style='page-break-before:always;'>
			&nbsp;
		</div>
		<table width="600px" align="center" class="printstyle">
			<tr>
				<td td width="72px">
					<div align="center">
						<br />
						在
						<br />
						校
						<br />
						期
						<br />
						间
						<br />
						获
						<br />
						奖
						<br />
						情
						<br />
						况
					</div>
					<br />
				</td>
				<td colspan="3">
				
					<p>
						&nbsp;
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
					</p>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
			<tr>
				<td td width="72px">
					<div align="center">
						<br />
						院
						<br />
						系
						<br />
						意
						<br />
						见
					</div>
					<br />
				</td>
				<td width="200px">
					&nbsp;
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
					</div>
				</td>
				<td td width="72px">
					<div align="center">
						学
						<br />
						校
						<br />
						意
						<br />
						见
					</div>
				</td>
				<td width="200px">
					
					
					<p>
						&nbsp;
					</p>
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
					</div>
					<div align="center"></div>
					<div align="center"></div>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					
					
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						省
						<br />
						教
						<br />
						育
						<br />
						厅
						<br />
						意
						<br />
						见
					</div>
					<br />
				</td>
				<td width="200px"></td>
				<td>
					<div align="center">
						毕
						<br />
						业
						<br />
						生
						<br />
						就
						<br />
						业
						<br />
						去
						<br />
						向
					</div>
				</td>
				<td width="200px">
					
					<p>
						&nbsp;
					</p>
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.byjyqx }
					</div>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						备
						<br />
						注
						<br />
					</div>
					<br />
				</td>
				<td colspan="3">
					<div align="center"></div>
					<br />
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<p align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;浙江省教育厅制表
		</p>
		<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
		<!-- 注：此表一式两份，系（院）、院学生处各一份 -->
	</html:form>
</body>
</html:html>

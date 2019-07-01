<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
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
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
		.noPrin{display:none;}
	</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<p align="center"><font size="3">${xxmc}优秀学生社团干部登记表</font></p>
		<span ><font size="1">学年：${xn}</font></span>
		<table width="100%" id="rsT" class="printstyle">
			<tr height="55px">
				<td colspan="2" align="center" valign="center">
					姓名
				</td>
				<td colspan="2" >
					&nbsp;${rs.xm}
				</td>
				<td colspan="2" align="center" valign="center">
					性别
				</td>
				<td >
					&nbsp;${rs.xb}
				</td>
				<td colspan="2" align="center" valign="center">
					年龄
				</td>
				<td colspan="2" >
					&nbsp;${rs.nl}
				</td>
				<td colspan="2" align="center" valign="center">
					政治面貌
				</td>
				<td  >
					&nbsp;${rs.zzmmmc }
				</td>
				
			</tr>
			<tr height="55px">
				<td  colspan="3" align="center" valign="center">
					系、级、班
				</td>
				<td colspan="2" >
					&nbsp;${rs.xymc }${rs.nj}年级${rs.bjmc }
				</td>
				<td colspan="2" align="center" valign="center">
					学号
				</td>
				<td colspan="3" >
					&nbsp;${rs.xh }
				</td>
				<td colspan="2" align="center" valign="center">
					社团内职务
				</td>
				<td colspan="2" >
					&nbsp;${rs.zwmc }
				</td>
				
			</tr>
			<tr  height="230px">
				<td  align="center" valign="center">
					主<br/>
					&nbsp;<br/>
					要<br/>
					&nbsp;<br/>
					先<br/>
					&nbsp;<br/>
					进<br/>
					&nbsp;<br/>
					事<br/>
					&nbsp;<br/>
					迹
				</td>
				<td colspan="13">
						&nbsp;
				</td>
			</tr>
			<tr  height="230px">
				<td align="center" valign="center" >
					社<br/>
					&nbsp;<br/>
					团<br/>
					&nbsp;<br/>
					联<br/>
					&nbsp;<br/>
					合<br/>
					&nbsp;<br/>
					会<br/>
					&nbsp;<br/>
					意<br/>
					&nbsp;<br/>
					见
				</td>
				<td  align="right" valign="bottom" colspan="6">
					<br/><br/><br/><br/><br/><br/>
							签字（章）<br/>
                      年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
				</td>
				<td align="center" valign="center">
					团<br/>
					&nbsp;<br/>
					总<br/>
					&nbsp;<br/>
					支<br/>
					&nbsp;<br/>
					意<br/>
					&nbsp;<br/>
					见<br/>
				</td>
				<td  align="right" valign="bottom" colspan="6">
					<br/><br/><br/><br/><br/><br/>
							签字（章）<br/>
                      年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
				</td>
			</tr>
			<tr  height="230px">
				<td align="center" valign="center">
					院<br/>
					&nbsp;<br/>
					团<br/>
					&nbsp;<br/>
					委<br/>
					&nbsp;<br/>
					意<br/>
					&nbsp;<br/>
					见<br/>
				</td>
				<td  align="right" valign="bottom" colspan="6">
					<br/><br/><br/><br/><br/><br/>
							签字（章）<br/>
                      年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
				</td>
				<td align="center" valign="center">
					院<br/>
					&nbsp;<br/>
					党<br/>
					&nbsp;<br/>
					委<br/>
					&nbsp;<br/>
					意<br/>
					&nbsp;<br/>
					见
				</td>
				<td align="right" valign="bottom" colspan="6">
					<br/><br/><br/><br/><br/><br/>
							签字（章）<br/>
                      年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
				</td>
			</tr>
			<tr>
				<td align="center" valign="center" >
					备
					<br/>
					注
				</td>
				<td colspan="13">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td width="30px" ></td>
				<td width="20px" ></td>
				<td width="20px" ></td>
				<td width="70px" ></td>
				<td width="50px" ></td>
				<td width="25px" ></td>
				<td width="50px" ></td>
				<td width="30px" ></td>
				<td width="30px" ></td>
				<td width="30px" ></td>
				<td width="50px" ></td>
				<td width="50px" ></td>
				<td width="20px" ></td>
				<td width="50px" ></td>
			</tr>
		</table>
			<p align="left">此表由组织填写，一式两份。</p>
			<p align="right">共青团成都体育学院委员会</p>
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
		
	</body>
</html>

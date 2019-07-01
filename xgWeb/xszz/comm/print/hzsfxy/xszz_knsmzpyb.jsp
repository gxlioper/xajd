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
		<title><bean:message key="lable.title" />
		</title>
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
		
	<script Language="javascript">
function a(x,y,color)
{
document.write("<img border='0' style='position: absolute; left: "+(x+parseInt((document.body.clientWidth*0.01)*4.1))+"; top: "+(y+parseInt((document.body.clientHeight*0.01)*19.4))+";background-color: "+color+"' src='px.gif' width=1 height=1>")}
</script>	
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

		<html:form action="/stu_info_add" method="post">
		<table width="85%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/>
					<b>
						<span style='font-size:16.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xxmc }<logic:equal value="求真学院" property="xymc" name="rs">${rs.xymc }</logic:equal>家庭经济困难学生民主评议表
						</span>
						</b>
						<br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							填表时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td width="6%"></td>
				<td width="6%"></td>
				<td width="2%"></td>
				<td width="14%"></td>
				<td width="3%"></td>
				<td width="2%"></td>
				<td width="2%"></td>
				<td width="2%"></td>
				<td width="3%"></td>
				<td width="7%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="4%"></td>
				<td width="2%"></td>
				<td width="4%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="7%"></td>
				<td width="7"></td>
				<td width="7%"></td>
				<td width="6%"></td>
			</tr>
			<!-- 第一行 -->
			<tr style="height:30px">
				<td align="center"  colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						申请人姓名
					</span>
				</td>
				<td  align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xm }
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						性别
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xb }
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						民族
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.mzmc }
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						生源地
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.syd }
					</span>
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:30px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					<logic:equal value="求真学院" property="xymc" name="rs">系（部）</logic:equal>
					<logic:notEqual value="求真学院" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xymc }
					</span>
				</td>
				<td align="center"colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						专业
					</span>
				</td>
				<td align="center"colspan="5">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.zymc }
					</span>
				</td>
				<td align="center"colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						学号
					</span>
				</td>
				<td align="center"colspan="4">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xh }
					</span>
				</td>
				<td align="center"colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						联系电话
					</span>
				</td>
				<td align="center"colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.sjhm }
					</span>
				</td>
			</tr>
			<!-- 本学年受资助情况 -->
			<tr style="height:30px">
				<td rowspan="${xszzNum+2 }" align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						本学年受<br/>资助情况
					</span>
				</td>
				<td align="center" colspan="5">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						受资助项目名称
					</span>
				</td>
				<td align="center" colspan="6">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						受助金额
					</span>
				</td>
				<td align="center" colspan="9">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						使用情况（用途）
					</span>
				</td>
			</tr>
			<logic:iterate name="xszzList" id="xszz">
			<tr style="height:30px">
				<td align="center" colspan="5">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${xszz.xmmc }&nbsp;&nbsp;
					</span>
				</td>
				<td align="center" colspan="6">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${xszz.xmzzje }&nbsp;&nbsp;
					</span>
				</td>
				<td align="center" colspan="9">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;
					</span>
				</td>
			</tr>
			</logic:iterate>
			<tr style="height:30px">
				<td align="left" colspan="20">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						注：受资助项目含国家助学贷款、国家助学金、国家励志奖学金、学校奖学金、社会爱心助学、困难补助等。
					</span>
				</td>
			</tr>
			<!-- 本学年参加公益活动情况 -->
			<tr style="height:30px">
				<td align="center" colspan="4">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						本学年参加公益活动情况
					</span>
				</td>
				<td align="left" colspan="18">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						义务劳动：&nbsp;&nbsp;&nbsp;小时，公益活动：&nbsp;&nbsp;&nbsp;次，无偿献血：&nbsp;&nbsp;&nbsp;ml
					</span>
				</td>
			</tr>
			<!-- 班级评议小组 -->
			<!-- 班级评议小组第1行 -->
			<tr style="height:20px">
				<td id="xmxx" align="center" >
					<%-- background="<%=request.getContextPath()%>/xszz/comm/print/hzsfxy/bjxx.png" style="width:6%;height:20" --%>
					项目<%-- <img src="<%=request.getContextPath()%>/xszz/comm/print/hzsfxy/bjxx.png" border="0" align="absmiddle" style="width:100%;height:20" /> --%>
				</td>
				<td align="center" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						观&nbsp;&nbsp;测&nbsp;&nbsp;点
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						计分
					</span>
				</td>
				<td align="center" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						参考计分点
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第2行 -->
			<tr style="height:30px">
				<td align="center" rowspan="7">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						班<br/>
						级<br/>
						评<br/>
						议<br/>
						小<br/>
						组
					</span>
				</td>
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						月消费较非家庭经济困难学生
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0〈差不多〈10〈困难〈20〈特困〈30
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第3行 -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						主观学习态度
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0〈一般〈5〈良好〈10〈很好〈15
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第4行 -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						参与公益活动情况
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0〈不好〈5〈还可以10〈很好〈 20
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第5行 -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						遵守校规校纪情况
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0〈一般〈5〈良好〈10〈很好〈15
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第6行 -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						诚实守信的程度
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						-10〈不诚信〈0〈一般〈5〈诚信〈10
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第7行 -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						为人处事方面
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
				<td align="left" colspan="11">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						0&lt;感觉有点不合群&nbsp;5&lt;与其他同学一样&nbsp;10&lt;更自立自强
					</span>
				</td>
			</tr>
			<!-- 班级评议小组第8行 -->
			<tr style="height:30px">
				<td align="left" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						计分小计
					</span>
				</td>
				<td align="center" colspan="13">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;
					</span>
				</td>
			</tr>
			<!-- 困难等级 -->
			<tr style="height:30px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						困难<br/>等级
					</span>
				</td>
				<td align="center" colspan="4">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						家庭经济特别困难（A）
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<%--  
						<logic:equal value="特殊困难" property="knjb" name="rs">
							√
						</logic:equal>
						<logic:notEqual value="特殊困难" property="knjb" name="rs">
						&nbsp;
						</logic:notEqual>
						 --%>
					</span>
				</td>
				<td align="center" colspan="7">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						家庭经济一般困难（B）
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<%--  
						<logic:equal value="一般困难" property="knjb" name="rs">
							√
						</logic:equal>
						<logic:notEqual value="一般困难" property="knjb" name="rs">
						&nbsp;
						</logic:notEqual>
						 --%>
					</span>
				</td>
				<td align="center" colspan="4">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						家庭经济不困难（C）
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<%--  
						<logic:present name="rs" property="knjb">
							&nbsp;
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							√
						</logic:notPresent>
						 --%>
					</span>
				</td>
			</tr>
			<!--  -->
			<tr style="height:80px">
				<td align="center" colspan="22">
					<p style="height:60px" align="left">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						评议综述：<br/>
					</span>
					</p>
					<div align="center">
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							团支书签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系方式：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班主任签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系方式：
						</span>
					</div>
					<br/>
				</td>
			</tr>
			<tr style="height:80px">
				<td align="center">
					<logic:equal value="求真学院" property="xymc" name="rs">系<br/>⌒<br/>部<br/>︶<br/></logic:equal>
					<logic:notEqual value="求真学院" property="xymc" name="rs">学<br/>院<br/></logic:notEqual>
					意<br/>
					见
				</td>
				<td align="center" colspan="21">
					<p style="height:60px" align="left">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</span>
					</p>
					<div align="right">
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							经办人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal value="求真学院" property="xymc" name="rs">系（部）</logic:equal>
							<logic:notEqual value="求真学院" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>盖章：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
					<br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							年&nbsp;&nbsp;&nbsp;&nbsp;
							月&nbsp;&nbsp;&nbsp;&nbsp;
							日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
					<br/>
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
		<br>
		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
<script>
function line(x1,y1,x2,y2,color)
{
var tmp
if(x1>=x2)
{
tmp=x1;
x1=x2;
x2=tmp;
tmp=y1;
y1=y2;
y2=tmp;
}
for(var i=x1;i<=x2;i++)
{
x = i;
y = (y2 - y1) / (x2 - x1) * (x - x1) + y1;
a(x,y,color);
}
}
//left:0,top:331,width:71,heigth:23
//x1:1,y1:331,x2:71,y2:354
//页面高：1412，页面宽：737
//line(0,331,37,354,"#000000");//正确的
//alert("宽公式："+parseInt(((document.body.clientWidth*0.85)*0.06)/2));
//alert("x公式："+parseInt((document.body.clientWidth*0.01)*4.1));
//alert("高："+document.body.clientHeight);
//alert("left："+xmxx.offsetLeft+"top："+xmxx.offsetTop+"宽："+xmxx.offsetWidth+"高："+xmxx.offsetHeight);
//line(xmxx.offsetLeft,xmxx.offsetTop,xmxx.offsetLeft+xmxx.offsetWidth,xmxx.offsetTop+xmxx.offsetHeight,'#000000')//原
//最后
//line(xmxx.offsetLeft,xmxx.offsetTop,parseInt(((document.body.clientWidth*0.85)*0.06)/2),xmxx.offsetTop+xmxx.offsetHeight,'#000000')
</script>
		</html:form>
	</body>
</html>

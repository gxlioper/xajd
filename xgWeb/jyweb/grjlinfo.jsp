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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function huifu(){
		  var xh =document.getElementById("xsxh").value;
		  var xm =document.getElementById("name").value;
		  var yhm =document.getElementById("name").value;
		  
		  url = "dwhf.do?method=dwhf&jytype=jyweb&xh="+xh+"&xm="+xm+"&yhm="+yhm+"&r="+Math.random();
		  
		  showTopWin(url, 400, 300);
	
		}
		
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:hidden name="rs" property="xsxh" />
		<html:hidden name="rs" property="name" />
		<html:hidden name="rs" property="yhm" />
		<div class="mainframe">
			<div class="jy_midframe">
				<h1>
					个人简历
				</h1>
				<table class="tbborder" width="90%" align="center">

					<tr height="25">
						<td colspan="9">

							<logic:equal name="idsee" value="yes">
							身份证号：
							<bean:write name="rs" property="id" />
							</logic:equal>
							<logic:equal name="idsee" value="no">
							身份证号（隐藏）
							</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							入学年份:<bean:write name="rs" property="rxnf" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">*</font>学号：
							<bean:write property="xsxh" name="rs" />
						</td>
					</tr>

					<tr height="25">
						<td rowspan="3" align="center" width="30">
							<b>个<br>人<br>资<br>料</b>
						</td>
						<td align="right" width="70">
							姓名：
						</td>
						<td width="150">
							&nbsp;
							<bean:write name="rs" property="name" />
							&nbsp;
						</td>
						<td align="right" width="70">
							性别：
						</td>
						<td width="150" colspan="2" >
							&nbsp;
							<bean:write name="rs" property="xb" />
							&nbsp;
						</td>
						<td align="right" width="70">
							出生年月：
						</td>
						<td width="150" colspan="2">
							&nbsp;
							<bean:write name="rs" property="csny" />
							&nbsp;
						</td>
					</tr>
					<tr height="25">
						<td align="right">
							民族：
						</td>
						<td>
							&nbsp;
							<bean:write name="rs" property="mz" />
							&nbsp;
						</td>
						<td align="right">
							学历：
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="xl" />
							&nbsp;
						</td>
						<td align="right">
							政治面貌：
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="zzmm" />
							&nbsp;
						</td>
					</tr>
					<tr height="25">
						<td align="right">
							专业名称：
						</td>
						<td>
							&nbsp;
							<bean:write name="rs" property="zymc" />
							&nbsp;
						</td>
						<td align="right">
							辅修专业：
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="fxzymc" />
							&nbsp;
						</td>
						<td align="right">
							生源地区：
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="sydq" />
							&nbsp;
						</td>
					</tr>
					<tr>
						<td rowspan="2" align="center">
							<b>联<br>系<br>方<br>法</b>
						</td>
						<td align="right">
							联系地址：
						</td>
						<td colspan="2">
							<html:textarea name="rs" property="lxdz" rows="2"
								style="width=100%" readonly="true" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="lxdh" rows="2"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr height="25">
						<td align="right">
							邮政编码：
						</td>
						<td colspan="2">
							&nbsp;
							<bean:write name="rs" property="yzbm" />
							&nbsp;
						</td>
						<td align="right">
							电子邮箱：
						</td>
						<td colspan="4">
							&nbsp;
							<bean:write name="rs" property="email" />
							&nbsp;
						</td>
					</tr>
					<tr>
						<td rowspan="5" align="center">
							<b>学<br>生<br>综<br>合<br>情<br>况</b>
						</td>
						<td align="center">
							获奖情况
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="hjqk" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							学习情况
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="xxqk" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							校级以
							<br>
							上奖励
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="xjysjl" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							社会实
							<br>
							践情况
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="shsjqk" rows="8"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							工作经历
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="gzjl" rows="8"
								style="width=100%" readonly="true" />
						</td>
						<td align="center">
							个人特长
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="grtc" rows="8"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<b>自<br>我<br>推<br>荐</b>
						</td>
						<td colspan="8">
							<html:textarea name="rs" property="zwtj" rows="20"
								style="width=100%" readonly="true" />
						</td>
					</tr>
				</table>
				<logic:equal name="usertype" value="dw" scope="session">
					<div align="center">
						<button onclick="huifu()">
							单位回复
						</button>
					</div>
				</logic:equal>
				<div>
					<h3>
					</h3>
				</div>
			</div>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>
	</body>
</html>

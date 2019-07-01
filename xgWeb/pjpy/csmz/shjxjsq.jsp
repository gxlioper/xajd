
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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">

</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="pjpy/csmz/csmzJs/csmzjs.js"></script>
		<html:form action="/pjpycsmzwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="wjcf_csmz_shjxjsq" bundle="pjpycsmz"/>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/shjxjsqdefault.do" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" >
							年度：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" >
							学年：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xn" />
							<input type="hidden" id="xn" name="xn" value="<bean:write name='rs' property="xn" />">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" >
							奖学金：
						</td>
						<td align="left" colspan="4">
							<bean:write name="rs" property="jxjmc"/>
							<input type="hidden" name="jxjdm" id="jxjdm" value="<bean:write name="rs" property="jxjdm"/>">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" >
							奖学金类别：
						</td>
						<td align="left" colspan="4">
							<bean:write name="rs" property="jxjlb"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" >
							奖励金额：
						</td>
						<td align="left" colspan="4">
							<bean:write name="rs" property="jlje"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" >
							担任职务：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="drzw" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" >
							
						</td>
						<td align="left" colspan="4">
							
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							外语水平：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="wysp" styleId="a"  />
						</td>
						<td align="right" >
							手机号码：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="sjhm" styleId="a"  />
						</td>
					</tr>	
  <tr>
    <td rowspan="2" height="35"></td>
    <td rowspan="2" align="center">奖学金</td>
    <td rowspan="2" align="center">三好<br>或<br>优干</td>
    <td rowspan="2" align="center" >德等级</td>
    <td rowspan="2" align="center">必修课<br>平均<br>成绩</td>
    <td colspan="2" align="center">学习成绩排序</td>
    <td rowspan="2" align="center">体育<br>合格<br>标准</td>
  </tr>
  <tr>
    <td width="6%" align="center" >班级</td>
    <td width="6%" align="center">专业</td>
  </tr>
  <tr>
    <td align="center" height="35">第一学年</td>
    <td><html:text name='rs' property="jxj1" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg1" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj1" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj1" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx1" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx1" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz1" style="width:98%" /></td>

  </tr>
  <tr>
    <td align="center" height="35">第二学年</td>
    <td><html:text name='rs' property="jxj2" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg2" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj2" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj2" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx2" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx2" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz2" style="width:98%" /></td>
  </tr>
  <tr>
    <td align="center" height="35">第三学年</td>
    <td><html:text name='rs' property="jxj3" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg3" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj3" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj3" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx3" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx3" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz3" style="width:98%" /></td>
  </tr>
  <tr>
    <td align="center" height="35">第四学年</td>
    <td><html:text name='rs' property="jxj4" style="width:98%" /></td>
    <td><html:text name='rs' property="shyg4" style="width:98%" /></td>
    <td><html:text name='rs' property="ddj4" style="width:98%" /></td>
    <td><html:text name='rs' property="bxkpjcj4" style="width:98%" /></td>
    <td><html:text name='rs' property="bjcjpx4" style="width:98%" /></td>
    <td><html:text name='rs' property="zycjpx4" style="width:98%" /></td>
    <td><html:text name='rs' property="tyhgbz4" style="width:98%" /></td>
  </tr>
	<tr align="left" style="height:22px">
						<td align="right">
							基本情况简介：
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" style="width:80px"
						onclick="savejxjdata1('saveshjxjsq.do')">
						提交申请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="showhzy">
					<button class="button2" style="width:80px"
					 onclick="window.open('jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value+'&tab=shjxj')">
						打印报表
					</button>
					</logic:notPresent>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="ISCF">
				<logic:equal value="yes" name="ISCF">
									<script>
    alert('数据重复，该生本学年已申请过社会奖学金！');
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

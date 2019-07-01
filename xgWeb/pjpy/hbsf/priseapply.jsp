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
		<script language="javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<html:form action="/pjpyhbsfwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="pjpy_hbsf_priseapply" bundle="pjpyhbsf"/>
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
				<input type="hidden" id="url" name="url" value="/pjpy_hbsf_apply.do" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="13" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" width="10%">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="4">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" width="6%">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="4">
								<bean:write name='rs' property="xh" /><html:hidden name='rs' property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" colspan="3">
							年度：
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" colspan="3">
							学年：
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" colspan="3">
							<font color="red">*</font>奖学金：
						</td>
						<td align="left" colspan="5">
							<html:select property="jxjdm" styleId="jxjdm"
								onchange="refreshForm('pjpy_hbsf_apply.do')">
								<option value=""></option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" colspan="3">
							奖学金类别：
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property="jxjlb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" colspan="3">
							奖励金额：
						</td>
						<td align="left" colspan="5">
							<bean:write name='rs' property='jlje' />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" colspan="3">
							担任职务：
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="drzw" styleId="a" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" colspan="3">
							&nbsp;
						</td>
						<td align="left" colspan="5">
							&nbsp;
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							外语水平：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="wysp" styleId="a"  style="width:98%"/>
						</td>
						<td align="right" colspan="3">
							手机号码：
						</td>
						<td align="left" colspan="5">
							<html:text name='rs' property="sjhm" styleId="a" style="width:98%" />
						</td>
					</tr>
  <tr>
    <td rowspan="2"><div align="center"><font color="red">*</font>学年度</div></td>
    <td rowspan="2" width="12%"> <div align="center">奖学金 </div></td>
    <td rowspan="2" width="12%"> <div align="center">三好 <br/>
  或 <br/>
  优干 </div></td>
    <td rowspan="2" width="6%"> <p align="center">思想<br>道德<br>素质分 </p>    </td>
    <td rowspan="2" width="6%"> <div align="center">科学<br>
      文化<br>
    素质分 </div></td>
    <td rowspan="2" width="6%"> <div align="center">身心<br>
      健康<br>
    素质分 </div></td>
    <td rowspan="2" width="6%"> <div align="center">课程<br>
      加权<br>
    平均分 </div></td>
    <td rowspan="2" width="6%"> <div align="center">排名 </div></td>
    <td colspan="2"> <div align="center">学习成绩排序 </div></td>
    <td rowspan="2" width="6%"> <div align="center">不及格<br>
      缓考<br>
    门数 </div></td>
    <td rowspan="2"> <div align="center">处理<br>
    记载 </div></td>
    <td rowspan="2"> <div align="center">体育 <br>
  合格 <br>
  标准 </div></td>
  </tr>
  <tr>
    <td width="6%"> <div align="center">综合测评总分 </div></td>
    <td width="6%"> <div align="center">综合排名 </div></td>
  </tr>
  <tr>
    <td><div align="right">
    	<html:select property="xn" styleId="xn" style="width:87px">
    		<html:options collection="xnList" property="xn" labelProperty="xn"/>
    	</html:select>
    </div></td>
    <td><div align="center"><html:text property="jxj1" style="width:98%"></html:text></div></td>
    <td><div align="center"><html:text property="shyg1" style="width:98%"></html:text></div></td>
    <td><div align="center"><html:text property="sxddf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="kxwhf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="sxjkf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="kcjqf" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="pm" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="zhkpzf1" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="zhkppm" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="bjghkms" style="width:50px"></html:text></div></td>
    <td><div align="center"><html:text property="cljz" style="width:98%"></html:text></div></td>
    <td><div align="center"><html:text property="tyhgbz1" style="width:98%"></html:text></div></td>
  </tr>
					<tr align="left" style="height:22px">
						<td align="right">
							学习简历：
						</td>
						<td colspan="12">
							<html:textarea name='rs' property='xxjl' style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								科研项目：
							</td>
							<td colspan="12">
							<html:textarea name='rs' property='kyxm' style="width:99%"
								rows='5' />
							</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							申请理由：
						</td>
						<td colspan="12">
							<html:textarea name='rs' property='sqly' style="width:99%"
								rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="jxjsqapply('hbsf_jxjsqsave.do','jxjdm-xn-xh')">
						提 交 申 请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value)">
						打 印 报 表
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="yes">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="exists">
					<script>
    alert("该生奖学金本学年已申请，请重新核对！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

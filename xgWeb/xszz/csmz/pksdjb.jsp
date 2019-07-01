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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
	<input type="hidden" id="nd" name="nd"
			value="<bean:write name="rs" property="nd" />">
	<input type="hidden" id="xh" name="xh"
			value="<bean:write name="rs" property="xh" />">
		<table width="100%" id="theTable" border="0">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" />贫困学生登记表</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						申报类别：
						<logic:equal name="xxsh" value="未审核">
						贫困生 口 特困生 口
						</logic:equal>
						<logic:equal name="xxsh" value="不通过">
						贫困生 口 特困生 口
						</logic:equal>
						<logic:equal name="xxsh" value="贫困生">
						贫困生 √ 特困生 口
						</logic:equal>
						<logic:equal name="xxsh" value="特困生">
						贫困生 口 特困生 √
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编号：
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="6">
								<div align="center">
									个人资料
								</div>
							</td>
						</tr>
						<tr>
							<td width="13%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									身份证号
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									出生日期
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td>
								<div align="center">
									民族
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									特长
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="tc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									何时参加过勤工俭学
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="hscjgqgzx" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									所在院、系、班级
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="xymc" />
								院(系)&nbsp;
								<bean:write name='rs' property="zymc" />
								院(系)&nbsp;
								<bean:write name='rs' property="bjmc" />
								班级
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									所在寝室
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="szqs" />
								</div>
							</td>
							<td>
								<div align="center">
									寝室电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qsdh" />
								</div>
							</td>
							<td>
								<div align="center">
									就餐卡卡号
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jckkh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									担任学生干部情况
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="drxsgbqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									在校期间曾何时获过何种奖学金
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="zxqjhschghzjxj" />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									家庭资料
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭地址
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="xzjtxxdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									是否贫困县
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<logic:equal name="pkx" value="否">
									√ 否   口 是 （ 口 国家级贫困县  口 省级贫困县 ）
									</logic:equal>
									<logic:equal name="pkx" value="国家级贫困县">
									口 否   √ 是 （ √ 国家级贫困县  口 省级贫困县 ）
									</logic:equal>
									<logic:equal name="pkx" value="省级贫困县">
									口 否   √ 是 （ 口 国家级贫困县  √ 省级贫困县 ）
									</logic:equal>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									上年家庭收入
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="snjtsr" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭成员数
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									父亲职业
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="fqzy" />
								</div>
							</td>
							<td>
								<div align="center">
									母亲职业
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="mqzy" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭经济来源
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtjjly" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭每月提供生活费(元)
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtmytgshf" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家中是否有欠债
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="jzsfyqz" value="有">
									有√ / 无口
									</logic:equal>
									<logic:equal name="jzsfyqz" value="无">
									有口 / 无√
									</logic:equal>
								</div>
							</td>
							<td>
								<div align="center">
									父母是否有
									<br />
									病或残疾
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="fmsfycj" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									父母是否健在
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="fmsfjz" />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									申请理由
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="left">
									<br />
									<br />
									<br />
									<bean:write name='rs' property="sqly" />
									<br />
									<strong>本人保证以上所填内容真实有效。</strong>
									<br />
								</div>
								<div align="right">
									签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									日期：
									<bean:write name='rs' property="sqsj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									班
									长签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;辅导员签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="fdyshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="fdyshsj">  
									<bean:write name='rs' property="fdyshsj" />
									&nbsp;&nbsp;
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									所在系审核意见
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<div align="right">
									党总支负责人签字（盖公章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									日 期：
									<logic:empty name="rs" property="xyshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="xyshsj">  
									<bean:write name='rs' property="xyshsj" />
									&nbsp;&nbsp;
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									学生处审核意见
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								<div align="right">
									学生处
									（盖公章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									日 期：
									<logic:empty name="rs" property="xxshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="xxshsj">  
									<bean:write name='rs' property="xxshsj" />
									&nbsp;&nbsp;
									</logic:notEmpty>
								</div>

							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left">
						<strong>填表说明：1、此表系部存档。2、此空表必须双面打印，内容必须亲笔填写。<br />3、所有项目填写应实事求是、认真负责，不留空白。4、存档的编号顺序须和花名册保持一致。</strong>
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回"
			onclick="back();" />
	</div>
</body>
</html:html>

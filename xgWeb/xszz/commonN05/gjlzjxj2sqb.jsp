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
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjlzjxj1sq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> 普通本科高校、高等职业学校国家励志奖学金申请表 </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="5" width="4%">
								<div align="center">
									本
									<br />
									人
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td width="10%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									出生
									<br />
									年月
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="20%" rowspan="4">
								<div align="center">
									照&nbsp;片
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									政治
									<br />
									面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
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
									入学
									<br />
									时间
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证
									<br />
									号码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									联系
									<br />
									电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="xxmc" />
									&nbsp;学校
									<logic:empty name="rs" property="xymc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xymc">
										&nbsp;<bean:write name='rs' property="xymc" />&nbsp;
									</logic:notEmpty>
									<bean:message key="lable.xsgzyxpzxy" />
									<logic:empty name="rs" property="zymc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="zymc">
										&nbsp;<bean:write name='rs' property="zymc" />&nbsp;
									</logic:notEmpty>
									专业
									<logic:empty name="rs" property="bjmc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bjmc">
										&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;
									</logic:notEmpty>
									班级
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									曾获何种奖励
								</div>
							</td>
							<td colspan="5">
								<logic:empty name="rs" property="chhzjl">
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="chhzjl">
									<bean:write name='rs' property="chhzjl" />
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									家
									<br />
									庭
									<br />
									经
									<br />
									济
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td>
								<div align="center">
									家庭
									<br />
									户口
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:equal name='rs' property="jthk" value="城镇">
										√、
									</logic:equal>
									<logic:notEqual name='rs' property="jthk" value="城镇">
										A、
									</logic:notEqual>
									城镇&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="jthk" value="农村">
										√、
									</logic:equal>
									<logic:notEqual name='rs' property="jthk" value="农村">
										B、
									</logic:notEqual>
									农村
								</div>
							</td>
							<td>
								<div align="center">
									家庭人口总数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭月
									<br />
									总收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									人均月收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									收入来源
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭
									<br />
									住址
								</div>
							</td>
							<td colspan="4">
								<bean:write name='rs' property="jtzz" />
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								&nbsp;申请理由
								<br />
								<logic:empty name='rs' property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								&nbsp;学校审核意见：
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>

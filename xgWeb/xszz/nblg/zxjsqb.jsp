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
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=zxjsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="nblg_xszz.do?method=zxjsqb" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								助学金申请表
						</strong>
							</h3>
					</div>
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
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									入学时间
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<div align="right">
									<bean:write name="rs" property="xymc" />
									&nbsp;分院&nbsp;
									<logic:empty name="rs" property="zymc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="zymc">
										<bean:write name="rs" property="zymc" />
									</logic:notEmpty>
									&nbsp;系&nbsp;
									<logic:empty name="rs" property="bjmc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bjmc">
										<bean:write name="rs" property="bjmc" />
									</logic:notEmpty>
									&nbsp;班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									曾获何种
									<br />
									奖励
								</div>
							</td>
							<td colspan="7">
								<bean:write name="rs" property="chhzjl" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									申请助学
									<br />
									金类型
								</div>
							</td>
							<td colspan="7">
								<bean:write name="rs" property="sqzxjlx" />
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
									家庭户口
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:empty name="rs" property="xh">
										A、城镇&nbsp;&nbsp;&nbsp;&nbsp;B、农村
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="jthk" value="城镇">
											√、城镇&nbsp;&nbsp;&nbsp;&nbsp;B、农村
										</logic:equal>
										<logic:equal name="rs" property="jthk" value="农村">
											A、城镇&nbsp;&nbsp;&nbsp;&nbsp;√、农村
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									家庭人口
									<br />
									总数
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtzrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭月总
									<br />
									收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtyzsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									人均月收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="rjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									收入来源
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="jtzz" />
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
								<div align="center">
									家
									<br />
									庭
									<br />
									成
									<br />
									员
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									年龄
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									与本人关系
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									工作或学习单位
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy1_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy2_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy3_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy4_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy5_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<br />
								申请理由:
								<br />
								<logic:empty name="rs" property="sqly">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="9">
								分院审核意见:
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name="rs" property="xyshyj" />
								</logic:notEmpty>
								<div align="right">
									负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<bean:message key="lable.xsgzyxpzxy" />审核意见:
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name="rs" property="xxshyj" />
								</logic:notEmpty>
								<div align="right">
									（公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
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

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
	<title><bean:message key="lable.title" /></title>
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
<body>
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>普通高等学校国家助学金申请表</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="80%">
						<tr>
							<td rowspan="4" width="6%">
								<div align="center">
									本人
									<br />
									情况
								</div>
							</td>
							<td width="14%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xh" />
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
									入学时间
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									家庭
									<br />
									经济
									<br />
									情况
								</div>
							</td>
							<td>
								<div align="center">
									家庭户口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
									A、城镇&nbsp;&nbsp;B、农村
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="jthk" value="城镇">
											√、
										</logic:equal>
										<logic:notEqual name="rs" property="jthk" value="城镇">
											A、
										</logic:notEqual>
										城镇&nbsp;&nbsp;
										<logic:equal name="rs" property="jthk" value="农村">
											√、
										</logic:equal>
										<logic:notEqual name="rs" property="jthk" value="农村">
											B、
										</logic:notEqual>
										农村
									</logic:notEmpty>
								</div>
							</td>
							<td colspan="2">
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
									家庭月总收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
								</div>
							</td>
							<td>
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
									家庭住址
								</div>
							</td>
							<td colspan="3">
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
							<td rowspan="6">
								<div align="center">
									家庭
									<br />
									成员
									<br />
									情况
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
							<td>
								<div align="center">
									与本人关系
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									工作或学习单位
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gzdw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								&nbsp;申请理由：
								<br />
								<br />
								<logic:empty name="rs" property="sqly">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="33%">
								<bean:message key="lable.xsgzyxpzxy" />意见
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
								<div align="right">
									(<bean:message key="lable.xsgzyxpzxy" />盖章)&nbsp;&nbsp;
								</div>
							</td>
							<td width="34%">
								学校资助机构意见
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
								<div align="right">
									(学校资助机构盖章)&nbsp;&nbsp;
								</div>
							</td>
							<td width="33%">
								学校意见
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
								<div align="right">
									(学校盖章)&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>

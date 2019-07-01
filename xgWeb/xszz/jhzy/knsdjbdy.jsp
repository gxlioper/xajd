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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=knssq";
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
							<strong> 金华职业技术<bean:message key="lable.xsgzyxpzxy" />家庭经济困难学生登记表 </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						填表日期:&nbsp;&nbsp;
						<logic:equal name='rs' property="sqsjyear" value="null">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual name='rs' property="sqsjyear" value="null">
							<bean:write name='rs' property="sqsjyear" />
						</logic:notEqual>
						年&nbsp;&nbsp;
						<logic:equal name='rs' property="sqsjmon" value="null">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual name='rs' property="sqsjmon" value="null">
							<bean:write name='rs' property="sqsjmon" />
						</logic:notEqual>
						月&nbsp;&nbsp;
						<logic:equal name='rs' property="sqsjday" value="null">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual name='rs' property="sqsjday" value="null">
							<bean:write name='rs' property="sqsjday" />
						</logic:notEqual>
						日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="6%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="6%">
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
									身份证号
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									入学年月
								</div>
							</td>
							<td width="16%">
								<div align="center">
									<bean:write name='rs' property="rxrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
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
									毕业时间
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="byrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									银行卡号(学校发)
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yhkh" />
								</div>
							</td>
							<td>
								<div align="center">
									一卡通号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="ykthm" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭住址及邮编
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtzzjyb" />
								</div>
							</td>
							<td>
								<div align="center">
									寝室号
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="qsh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学习总体情况
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									优&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="优">
										√
									</logic:equal>
									&nbsp;)&nbsp;&nbsp;良&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="良">
										√
									</logic:equal>
									&nbsp;)&nbsp;&nbsp;一般&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="一般">
										√
									</logic:equal>
									&nbsp;)&nbsp;&nbsp;较差&nbsp;(&nbsp;
									<logic:equal name='rs' property="xxztqk" value="较差">
										√
									</logic:equal>
									&nbsp;)
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
							<td colspan="2">
								<div align="center">
									获何种奖励
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="hhzjl" />
							</td>
							<td>
								<div align="center">
									曾受何种处分
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="cshzcf" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									有无助学贷款
									<br />
									及金额
								</div>
							</td>
							<td colspan="2">
								<bean:write name='rs' property="ywzxdkjje" />
							</td>
							<td>
								<div align="center">
									有无受社会
									<br />
									(单位)资助
									<br />
									及金额
								</div>
							</td>
							<td>
								<bean:write name='rs' property="ywsshzzjhe" />
							</td>
							<td>
								<div align="center">
									学费交纳
									<br />
									情况
								</div>
							</td>
							<td>
								<bean:write name='rs' property="xfjnqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									现在消费情况
								</div>
							</td>
							<td colspan="2">
								<div align="right">
									<bean:write name='rs' property="xzxfqk" />&nbsp;
									元/月&nbsp;
								</div>
							</td>
							<td colspan="4">
								&nbsp;(1)&nbsp;伙食费&nbsp;&nbsp;
								<logic:empty name='rs' property="hsf">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="hsf">
									<bean:write name='rs' property="hsf" />
								</logic:notEmpty>
								元/月；&nbsp;(2)&nbsp;其它费用&nbsp;&nbsp;
								<logic:empty name='rs' property="qtfy">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="qtfy">
									<bean:write name='rs' property="qtfy" />
								</logic:notEmpty>
								元/月
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									生活费来源
								</div>
							</td>
							<td colspan="6">
								&nbsp;家庭供给&nbsp;&nbsp;
								<logic:empty name='rs' property="shf_jtgg">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="shf_jtgg">
									<bean:write name='rs' property="shf_jtgg" />
								</logic:notEmpty>
								元/月；&nbsp;其它来源:&nbsp;&nbsp;
								<logic:empty name='rs' property="shf_qtly">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name='rs' property="shf_qtly">
									<bean:write name='rs' property="shf_qtly" />
								</logic:notEmpty>
								元/月
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="6" width="4%">
								<div align="center">
									家
									<br />
									庭
									<br />
									成
									<br />
									员
									<br />
									基
									<br />
									本
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td width="10%">
								<div align="center">
									称谓
								</div>
							</td>
							<td width="16%">
								<div align="center">
									姓&nbsp;&nbsp;名
								</div>
							</td>
							<td width="10%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="20%">
								<div align="center">
									月收入(元)
								</div>
							</td>
							<td width="40%">
								<div align="center">
									单&nbsp;&nbsp;位
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy1_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy2_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy3_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy4_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jicy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jicy5_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									家庭人口总数
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
							<td>
								<div align="center">
									人均年收入(元/人.年)
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rjnsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									家庭类型(在括号内打“√”)
								</div>
							</td>
							<td colspan="3">
								&nbsp;双亲&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx1" value="1">
									√
								</logic:equal>
								&nbsp;)、单亲&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx2" value="1">
									√
								</logic:equal>
								&nbsp;)、离异&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx3" value="1">
									√
								</logic:equal>
								&nbsp;)、烈属&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx4" value="1">
									√
								</logic:equal>
								&nbsp;)、孤儿&nbsp;(&nbsp;
								<logic:equal name='rs' property="jtlx5" value="1">
									√
								</logic:equal>
								&nbsp;)
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家
									<br />
									庭
									<br />
									困
									<br />
									难
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtknqk" />
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

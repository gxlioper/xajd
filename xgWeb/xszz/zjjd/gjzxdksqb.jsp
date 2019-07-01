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
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjzxdksq";
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
						<strong>
								国家助学贷款申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<logic:empty name="rs" property="sqsj">
						<div align="right">
							填表日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
						</div>
					</logic:empty>
					<logic:notEmpty name="rs" property="sqsj">
						<div align="right">
							填表日期:&nbsp;&nbsp;
							<bean:write name='rs' property="sqsj" />
							&nbsp;&nbsp;
						</div>
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="9%">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="11%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									学制
								</div>
							</td>
							<td width="17%">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									性别
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									入学年月
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
							<td>
								<div align="center">
									毕业年月
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="byny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									有效家庭
									<br />
									地址
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="yxjtzz" />
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
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
							<td>
								<div align="center">
									家长姓名
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jz1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jz1_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									家长姓名
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jz2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jz2_lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭经济情况
									<br />
									(请详细填写)
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="jtjjqk">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="jtjjqk">
									<bean:write name='rs' property="jtjjqk" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									申请贷款金额
									<br />
									及缴纳学费
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									贷款金额
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xfdk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									应交学费
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yjxf" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									入学以来不及格科目
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxlbjgkm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									入学以来补考通过科目
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxlbktgkm" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									班主任意见
								</div>
							</td>
							<td colspan="8">
								<logic:equal name="rs" property="fdysh" value="未审核">
									审核结果：
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="未审核">
									审核结果：<bean:write name='rs' property="fdysh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="fdyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									系意见
								</div>
							</td>
							<td colspan="8">
								<logic:equal name="rs" property="xysh" value="未审核">
									审核结果：
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="未审核">
									审核结果：<bean:write name='rs' property="xysh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />意见
								</div>
							</td>
							<td colspan="8">
								<logic:equal name="rs" property="xxsh" value="未审核">
									审核结果：
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="未审核">
									审核结果：<bean:write name='rs' property="xxsh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									领导签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;填表说明: 1.学生必须保证以上信息真实无误，不得涂改，一式两份。
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.“家庭经济情况”要注明家庭人口情况、收入来源、月人均收入。
					</div>
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

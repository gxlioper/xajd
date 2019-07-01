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
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjzxj2sq";
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
							<strong>普通高校国家助学金申请审批表</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						<br />
						(&nbsp;
						<logic:empty name="rs" property="xn">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xn">
							<bean:write name='rs' property="xn" />
						</logic:notEmpty>
						&nbsp;学年)
						<br />
						<br />
						学校：&nbsp;
						<logic:empty name="rs" property="xxmc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xxmc">
							<bean:write name='rs' property="xxmc" />
						</logic:notEmpty>
						&nbsp; <bean:message key="lable.xsgzyxpzxy" />：&nbsp;
						<logic:empty name="rs" property="xymc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<bean:write name='rs' property="xymc" />
						</logic:notEmpty>
						&nbsp; 专业：&nbsp;
						<logic:empty name="rs" property="zymc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="zymc">
							<bean:write name='rs' property="zymc" />
						</logic:notEmpty>
						&nbsp; 班级：&nbsp;
						<logic:empty name="rs" property="bjmc">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="bjmc">
							<bean:write name='rs' property="bjmc" />
						</logic:notEmpty>
						&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" width="8%">
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
							<td width="20%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									民族
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									入学时间
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="rxrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="4">
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
							<td colspan="7">
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
							<td colspan="4">
								<div align="center">
									收入来源
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭月总收入
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									家庭人口总数
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="jtzz" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									认定情况
								</div>
							</td>
							<td colspan="18">
								<div align="center">
									<logic:equal name='rs' property="rdqk" value="特别困难">
										√、
									</logic:equal>
									<logic:notEqual name='rs' property="rdqk" value="特别困难">
										A、
									</logic:notEqual>
									家庭经济特别困难&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="rdqk" value="困难">
										√、
									</logic:equal>
									<logic:notEqual name='rs' property="rdqk" value="困难">
										B、
									</logic:notEqual>
									家庭经济一般困难
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
							<td colspan="3">
								<div align="center">
									年龄
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									与本人关系
								</div>
							</td>
							<td colspan="11">
								<div align="center">
									工作或学习单位
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy1_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="11">
								<div align="center">
									<bean:write name='rs' property="jtcy1_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy2_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="11">
								<div align="center">
									<bean:write name='rs' property="jtcy2_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy3_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="11">
								<div align="center">
									<bean:write name='rs' property="jtcy3_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy4_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="11">
								<div align="center">
									<bean:write name='rs' property="jtcy4_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy5_nl" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="11">
								<div align="center">
									<bean:write name='rs' property="jtcy5_dw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									申
									<br />
									请
									<br />
									理
									<br />
									由
								</div>
							</td>
							<td colspan="19">
								<br />
								<logic:empty name='rs' property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
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
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									院
									<br />
									(系)
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="19">
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学
									<br />
									校
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="19">
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
									(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
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

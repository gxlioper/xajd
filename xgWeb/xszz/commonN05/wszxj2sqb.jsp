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
			var zxjdm = document.getElementById('zxjdm').value;
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj2sq&xmdm="+zxjdm;
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" id="zxjdm" name="zxjdm"
			value="<bean:write name="rs" property="zxjdm" />">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong><bean:write name='rs' property="xxmc" />家庭经济困难学生（奖）助学金申请表</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;（奖）助学金名称：<bean:write name='rs' property="zxjmc" />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									学号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
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
									专业
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="nj">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="nj">
										<bean:write name='rs' property="nj" />
									</logic:notEmpty>
									级
									<logic:empty name="rs" property="bjmc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bjmc">
										<bean:write name='rs' property="bjmc" />
									</logic:notEmpty>
									班
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭地址
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtdz" />
								</div>
							</td>
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
						</tr>
						<tr>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									本学年获得何种资助
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bxnhdhzzz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									补考科数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bkks" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									上学年班级综合测评排名
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sxnbjzhcppm" />
								</div>
							</td>
							<td width="17%">
								<div align="center">
									在校是否受过处分
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="zxfscf" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭人口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭人均月收入
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrjysr" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="6" width="4%">
								<div align="center">
									家<br />庭<br />主<br />要<br />成<br />员
								</div>
							</td>
							<td width="20%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="11%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="20%">
								<div align="center">
									与本人关系
								</div>
							</td>
							<td width="20%">
								<div align="center">
									职业
								</div>
							</td>
							<td width="25%">
								<div align="center">
									月收入(元)
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
									<bean:write name='rs' property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_sr" />&nbsp;
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
									<bean:write name='rs' property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_sr" />&nbsp;
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
									<bean:write name='rs' property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_sr" />&nbsp;
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
									<bean:write name='rs' property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_sr" />&nbsp;
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
									<bean:write name='rs' property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_sr" />&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;申请人申请资助原因（填写要求字迹清晰，情况属实，涂改无效）：
								<br />
								<logic:empty name='rs' property="sqyy">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqyy">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="sqyy" />
								</logic:notEmpty>
								<br />
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									申请日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />审核意见：
								<br />
								&nbsp;&nbsp;1、申请学生是否有家庭经济困难证明：
								<u>
									<logic:empty name='rs' property="xy_sfyknzm">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_sfyknzm">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_sfyknzm" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>。<br />
								&nbsp;&nbsp;2、贫困生档案库中是否有申请学生的相关数据：
								<u>
									<logic:empty name='rs' property="xy_sfkns">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_sfkns">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_sfkns" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>。<br />
								&nbsp;&nbsp;3、该生上学年班级综合测评排名为
								<u>
									<logic:empty name='rs' property="xy_sxnzhcppm">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_sxnzhcppm">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_sxnzhcppm" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>%。<br />
								&nbsp;&nbsp;4、班级民主评议通过率
								<u>
									<logic:empty name='rs' property="xy_bjmzpytgl">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="xy_bjmzpytgl">
										&nbsp;&nbsp;<bean:write name='rs' property="xy_bjmzpytgl" />&nbsp;&nbsp;
									</logic:notEmpty>
								</u>%。<br />
								&nbsp;&nbsp;5、是否同意该生申请：是
								<logic:equal name='rs' property="xy_sftjsq" value="是">
									&nbsp;√
								</logic:equal>
								//否
								<logic:equal name='rs' property="xy_sftjsq" value="否">
									&nbsp;√
								</logic:equal>
								<br />
								&nbsp;&nbsp;6、其他情况说明：<br />
								<logic:empty name='rs' property="qtsm">
									<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="qtsm">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="qtsm" />
								</logic:notEmpty>
								<br />
								<div align="right">
									单位公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									班主任或年级辅导员签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="6">
								&nbsp;&nbsp;学生处（或助学金管理委员会）意见：
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;注明：要求附家庭经济状况证明复印件
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

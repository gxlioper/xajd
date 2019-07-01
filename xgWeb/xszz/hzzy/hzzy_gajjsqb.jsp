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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable" class="tableprint">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								关爱基金申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%">
						<tr>
							<td scope="col" width="15%">
								<div align="center">
									姓名
								</div>
							</td>
							<td scope="col" width="15%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="15%">
								<div align="center">
									民族
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td width="15%" rowspan="5" scope="col">
								<div align="center">
									照片
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									班级
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									班级综合排名
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjzhpm" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
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
									联系电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭人数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									家庭人均年收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtrjnsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭详细通讯地址
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="xxtxdz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<logic:empty name='rs' property="jtlxdh">
									(区号) - 
									</logic:empty>
									<logic:notEmpty name='rs' property="jtlxdh">
									<bean:write name='rs' property="jtlxdh" />
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									已获资助情况
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="yhzzqk" />
							</td>
						</tr>
						<tr>
							<td colspan="7" scope="row">
								申请基金原因:
								<br />
								<logic:empty name='rs' property="sqjjyy">
								<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqjjyy">
								<bean:write name='rs' property="sqjjyy" />
								</logic:notEmpty>
								<br /><br /><br />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									系(院)
									<br />
									负责
									<br />
									助困
									<br />
									辅导员
									<br />
									意见
								</div>
							</td>
							<td colspan="3">
								<br />
								<br />
								<br /><br />
								<div align="center">
									签名:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
							<td>
								<div align="center">
									系(院)
									<br />
									<br />
									总辅导
									<br />
									<br />
									员审核
									<br />
									<br />
									意见
								</div>
							</td>
							<td colspan="2">
								<br />
								<br /><br /><br />
								<div align="center">
									签名:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									系(院)
									<br />
									<br />
									党总支
									<br />
									<br />
									书记
									<br />
									<br />
									意见
								</div>
							</td>
							<td colspan="3">
								<br />
								<br /><br /><br />
								<div align="center">
									签名:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									<br />
									意见
								</div>
							</td>
							<td colspan="2">
								<br />
								<br /><br /><br />
								<div align="center">
									签名:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<div align="left">
						备注:困难证明必须附后,此表一式两份,一份交系(院)留存,一份交学生处(学生资助中心)备案。
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>

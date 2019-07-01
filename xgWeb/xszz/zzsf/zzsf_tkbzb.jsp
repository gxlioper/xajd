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
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								<bean:write name='rs' property="nd" />
								&nbsp;年度校内特困补助
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td scope="col" width="10%">
								<div align="center">
									姓名
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="8%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
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
									专业
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									系别
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									类别
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xslb" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="9" scope="row">
								<div align="center">
									家
									<br />
									庭
									<br />
									经
									<br />
									济
									<br />
									及
									<br />
									主
									<br />
									要
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
									家庭户口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jthk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭人口总数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									月总收入
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
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
								<div align="center">
									<bean:write name='rs' property="jtzz" />
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
							<td width="8%">
								<div align="center">
									电话
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭主要经济来源
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="jtzyjjly" />
							</td>
						</tr>
						<tr>
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
									称谓
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									所在单位
								</div>
							</td>
							<td>
								<div align="center">
									月收入
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
									<bean:write name='rs' property="jtcy1_cw" />
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtcy1_szdw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy1_ysr" />
									&nbsp;
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
									<bean:write name='rs' property="jtcy2_cw" />
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtcy2_szdw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy2_ysr" />
									&nbsp;
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
									<bean:write name='rs' property="jtcy3_cw" />
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtcy3_szdw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy3_ysr" />
									&nbsp;
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
									<bean:write name='rs' property="jtcy4_cw" />
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtcy4_szdw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy4_ysr" />
									&nbsp;
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
									<bean:write name='rs' property="jtcy5_cw" />
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtcy5_szdw" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy5_ysr" />
									&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									本学期奖惩情况
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="bxqjcqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									本学期受资助情况
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="bxqszzqk" />
							</td>
						</tr>
						<tr>
							<td colspan="10" scope="row">
								<br />
								<div align="center">
									本人保证以上所填情况真实有效。
								</div>
								<div align="right">
									申请人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									辅导员意见
								</div>
							</td>
							<td colspan="8">
								<br />
								<br />
								<div align="right">
									签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									系党总支意见
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="xyshyj" />
								<br />
								<div align="right">
									盖公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									学生处意见
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="xxshyj" />
								<br />
								<div align="right">
									盖公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
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
	</div>
</body>
</html:html>

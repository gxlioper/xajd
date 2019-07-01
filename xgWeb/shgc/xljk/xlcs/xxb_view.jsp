<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body
		onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_tkwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理测试 - 题库维护 - 选项维护</a>
				</p>
			</div>
			<div id="main" style="heigth:100px;">
				<div id="search_m" style="heigth:100px;">
					<div id="result">
						<div class="searchcontent">
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									有错误发生！
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
							<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>查看选项信息</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
										<div align="right">
										<button class="" onclick="Close();return false;"
											id="buttonClose">
											关闭
										</button>
										</div>
									</div>
								</td>
							</tr>
						</tfoot>	
									<tbody>
										<tr>
											<th align="right">
												<font color="red">*</font>试题流水号
											</th>
											<td align="left">
												<html:text name='rs' property="stlsh" style="width: 120px"
													styleId="stlsh"  readonly="true"/>
											</td>
											<th align="right">
												<font color="red">*</font>选项序号
											</th>
											<td align="left">
												<html:text name='rs' property="xxxh" style="width: 120px"
													styleId="xxxh" readonly="true"/>
											</td>
										</tr>
										<tr>
											<th align="right">
												选项分值
											</th>
											<td align="left">
												<html:text name='rs' property="xxfz" style="width: 120px"
													styleId="xxfz" readonly="true"/>
											</td>
											<th align="right">
												是否显示
											</th>
											<td align="left">
												<html:text name='rs' property="xxxsbj" style="width: 120px"
													styleId="xxxsbj" readonly="true"/>
											</td>
										</tr>
										<tr>
											<th align="right">
												<font color="red">*</font>选项内容
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='xxnr' style="width:95%"
													rows='4' readonly="true"/>
											</td>
										</tr>
										</tbody>
									</table>
							</logic:notEmpty>
						</div>
				</html:form>
			</body>
	</html>

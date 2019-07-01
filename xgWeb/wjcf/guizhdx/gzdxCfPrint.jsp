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
</script>
<body>
	<html:form action="/wjcfnblgwh" method="post">
		<table width="90%" border="0" id="theTable" align="center">
			<tr>
				<td scope="col">
					<div align="center">
						<h1>
							<strong> 贵州大学学生违纪处分联单</strong>
						</h1>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" align="center">
					<table width="100%" border="0"  cellpadding="0" cellspacing="0">
						<tr height="600px">
							<td width="50%">
								<table class="tbstyle" style="border:0px" width="100%" cellpadding="0" cellspacing="0">
									<tr height="60px">
										<td>
											姓名<u>&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;</u>学号<u>&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp; </u>性别<u>&nbsp;&nbsp;${rs.xb }&nbsp;&nbsp;</u>
										</td>
									</tr>
									<tr height="60px">
										<td>
											<u>${rs.xymc }&nbsp;&nbsp;</u> <bean:message key="lable.xsgzyxpzxy" /><u>&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;  </u>专业<u>&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp; </u>级
										</td>
									</tr>
									<tr>
										<td width="100%">
											违纪事由：
											<p style="height:265px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }</p>
											填写人签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											年&nbsp;&nbsp;&nbsp;&nbsp;    月&nbsp;&nbsp;&nbsp;&nbsp;    日
										</td>
									</tr>
									<tr>
										<td>&nbsp;
											<h2>以上情况属实</h2>
										<p style="height:70px"/>
											<div align="right">
											学生本人签字:
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
												 &nbsp;&nbsp;&nbsp;&nbsp;年
												 &nbsp;&nbsp;&nbsp;&nbsp;月
												 &nbsp;&nbsp;&nbsp;&nbsp;日
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td >
											附件材料（证据目录）：
											<p style="height:134px"/>
										</td>
									</tr>
									<tr>
										<td>
											<strong>处分依据：</strong><br/>
											1、《普通高等学校学生管理规定》第&nbsp;&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;第&nbsp;&nbsp;&nbsp;&nbsp;款<br/>
											2、《国家教育考试违规处理办法》第&nbsp;&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;第&nbsp;&nbsp;&nbsp;&nbsp;款<br/>
											3、《贵州大学学生违纪处分条例》第&nbsp;&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;第&nbsp;&nbsp;&nbsp;&nbsp;款<br/>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table class="tbstyle" style="border:0px" width="100%"  cellpadding="0" cellspacing="0">
									<tr>
										<td>
												院长会议研究意见：
												<p style="height:120px">&nbsp;</p>
												<div align="center">(<bean:message key="lable.xsgzyxpzxy" />盖章）负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
												<br/>
												<br/>
										</td>
									</tr>
									<tr>
										<td>
											其它相关部门认定意见：
											<p style="height:132px"></p>
											<div align="right">
												签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
											&nbsp;&nbsp;&nbsp;&nbsp;年
											&nbsp;&nbsp;&nbsp;&nbsp;月
											&nbsp;&nbsp;&nbsp;&nbsp;日
											&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											学生处处分意见：
											<p style="height:130px">&nbsp; </p>
											<div align="right">
											签字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<p/>
											&nbsp;&nbsp;&nbsp;&nbsp;年
											&nbsp;&nbsp;&nbsp;&nbsp;月
											&nbsp;&nbsp;&nbsp;&nbsp;日
											&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</td>
									</tr>
									<tr>
										<td>
											分管校领导意见
											<p style="height:115 px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxclyj }</p>
											<div align="right">
												分管校领导字：&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;<br/>
												&nbsp;&nbsp;&nbsp;&nbsp;年
												&nbsp;&nbsp;&nbsp;&nbsp;月
												&nbsp;&nbsp;&nbsp;&nbsp;日
												&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
					</table>
					<center>
						<div style="width:90%">
						<div align="left">此联单一式两份 一份装入学生档案，一份学校留存，各项均可另附纸</div>
						<div align="right">贵州大学学生处制</div>
						</div>
					</center>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>

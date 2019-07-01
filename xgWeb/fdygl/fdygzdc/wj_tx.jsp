<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<body onload="loadTestSelectItem2();">
		<html:form action="/wj_view" method="post">
			<input type="hidden" id="xxStr" name="xxStr"
				value="<bean:write name="xxStr"/>" />
			<input type="hidden" id="stLen" name="stLen"
				value="<bean:write name="stLen"/>" />
			<input type="hidden" id="xh||xn||xq" name="xh||xn||xq"
				value="<bean:write name="xh||xn||xq"/>" />
			<input type="hidden" id="zgh" name="zgh"
				value="<bean:write name="zgh"/>" />			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：辅导员管理 - 思政考核 - 辅导员工作调查 - 问卷填写
				</div>
			</div>
			<logic:notEqual value="student" name="userType">
			</logic:notEqual>
			<logic:empty name="stList">
            <div align="center"><font color="red"><br/><br/><br/>暂无问题!</font></div>
            </logic:empty>
			<logic:notEmpty name="stList">
				<fieldset>
					<table width="99%" class="tbstyle">
						<tr>
							<td align="center">
								<B><font size="5"> 辅导员 <bean:write name="fdyxm" /> 工作情况学生问卷 </font> </B>
							</td>
						</tr>
						<tr>
							<logic:notEqual value="student" name="userType">
								<td align="center">
									<bean:message key="lable.xsgzyxpzxy" />___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									性别___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年级___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</logic:notEqual>
							<logic:equal value="student" name="userType">
								<td align="center">
									<bean:message key="lable.xsgzyxpzxy" />：
									<bean:write name="rs" property="xymc" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 性别：
									<bean:write name="rs" property="xb" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年级：
									<bean:write name="rs" property="nj" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</logic:equal>
						</tr>
						<tr>
							<td align="left">
								<B>同学您好！<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了了解辅导员工作状况，我们进行这次抽样调查，所有数据均用于统计研究，请按实际情况和真实想法回答问题。感谢您对本次调查的大力支持。祝您学习进步！<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									学生工作部（处）</B>
							</td>
						</tr>
						<tr>
							<td align="left">
								<logic:notEmpty name="stList">
									<table width="100%" class="tbstyle" id="tp">
										<logic:iterate id="stList" name="stList">
											<tr>
												<td align="left"
													id="<bean:write name="stList" property="id"/>" width="100%">
													<B><bean:write name="stList" property="id" /> .</B>&nbsp;
													<bean:write name="stList" property="stmc" />
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;问卷到此结束，再次感谢您的耐心与真诚合作！
							</td>
						</tr>
					</table>
					<logic:equal value="student" name="userType">
						<div class="buttontool" id="btn" align="center">
							<button class="button2" onclick="saveTestSelectItem()"
								style="width:80px" id="buttonSave">
								提&nbsp;&nbsp;交
							</button>
						</div>
					</logic:equal>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:equal value="yes" name="done">
	    <script language="javascript">
	      alert("提交成功！");
	    </script>
	</logic:equal>
	<logic:equal value="no" name="done">
	    <script language="javascript">
	      alert("提交失败！");
	    </script>
	</logic:equal>
</html>

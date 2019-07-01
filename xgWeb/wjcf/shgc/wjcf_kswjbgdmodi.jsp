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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function chkValue(doType)
		{
			
				refreshForm('wjcf_shgc_bgdmodisave.do?pkValue='+document.getElementById('pkValue').value);
				BatAlert.showTips('正在操作，请等待...');
			
		}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
		
		<html:form action="/shgcwjcfwh" method="post">
			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">违纪处分 - 数据维护 - 考试违纪处分不归档</span>
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							违纪处分不归档审批
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
					</td>
					<td align="right">
						姓名：
					</td>
					<td align="left">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						${rs.xymc}
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
						${rs.zymc}
					</td>
				</tr>
				<tr>
					<td align="right">
						班级：
					</td>
					<td align="left">
						${rs.bjmc }
					</td>
					<td align="right">
						年级：
					</td>
					<td align="left">
						${rs.nj }
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						${rs.xb }
					</td>
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr>
					<td align="right">
						民族：
					</td>
					<td align="left">
						${rs.mzmc}
					</td>
					<td align="right">
						籍贯：
					</td>
					<td align="left">
						${rs.jg }
					</td>
				</tr>
				<tr>
					<td align="right">
						出生年月：
					</td>
					<td align="left" colspan="3">
						${rs.csrq }
					</td>
				</tr>
				<tr>
					<td align="right">
						处分等级：
					</td>
					<td align="left">
						${rs.cfjb }
					</td>
					<td align="right">
						处分原因：
					</td>
					<td align="left">
						${rs.cfyymc }
					</td>
				</tr>
				<tr>
					<td align="right">
						处分后现实表现：
					</td>
					<td colspan="3">
						<html:textarea property="cfhbx" styleId="cfhbx" style="width:95%" rows="3">
						</html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						其它奖惩记录：
					</td>
					<td colspan="3">
						<html:textarea property="jcjl" styleId="jcjl" style="width:95%" rows="3"></html:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
							<table style="width:99%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>意见与评语</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none">
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													辅导员意见及评语
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="bzryj" styleId="bzryj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													<bean:message key="lable.xsgzyxpzxy" />审查意见
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="xyyj" styleId="xyyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													会办部门审查意见
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="kbbmyj" styleId="kbbmyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													学生处审查意见
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="xscyj" styleId="xscyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													校审查意见
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="xxyj" styleId="xxyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
					<td align="right">
						备注：
					</td>
					<td colspan="3">
						<html:textarea property="bz" styleId="bz" style="width:95%" rows="3"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:notPresent name="act">
					<button type="button" class="button2" onclick="chkValue('save')"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notPresent>
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>

		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
alert("操作成功！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
  alert("操作失败！原因可能是该信息在数据库中已存在! \n （请仔细核对!）");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
	</body>
</html>

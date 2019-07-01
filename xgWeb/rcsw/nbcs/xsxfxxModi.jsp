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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function save(){
			refreshForm('xfxx.do?method=saveXsxfxx&doType=modi');
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/xfxx.do">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 - 学费催缴 - 学费信息维护 - 修改
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								学费信息
							</td>
						</tr>
					</thead>
					
					<tr>
						<td align="right">
							学号：
						</td>
						<td>							
							${rs.xh}
							<html:hidden property="xh" name="rs"/>
						</td>
						<td>
							<div align="right">
								年度：
							</div>
						</td>
						<td>
							${rs.nd}
							<html:hidden property="nd" name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>							
							${rs.xm}
						</td>	
						<td align="right">
							欠缴学费：
						</td>
						<td>							
							<html:text property="qjxf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"></html:text>
						</td>						
					</tr>	
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>							
							${rs.xymc}
						</td>	
						<td align="right">
							发生其他费：
						</td>
						<td>			
							<html:text property="qtf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>							
							${rs.bjmc}
						</td>	
						<td align="right">
							欠缴代管费：
						</td>
						<td>							
							<html:text property="qjdgf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							培养层次：
						</td>
						<td>							
							<html:text property="pycc" maxlength="15" name="rs"></html:text>
						</td>	
						<td align="right">
							经费状态：
						</td>
						<td>							
							<html:text property="jfzt" maxlength="15" name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							学习形式：
						</td>
						<td>							
							<html:text property="xxxs" maxlength="15" name="rs"></html:text>
						</td>	
						<td align="right">
							准考证号：
						</td>
						<td>							
							<html:text property="zkzh" maxlength="150" name="rs"></html:text>
						</td>						
					</tr>
				</table>
				<logic:notEqual value="stu" name="userType">
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
					</div>
				</center>
				</logic:notEqual>
			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

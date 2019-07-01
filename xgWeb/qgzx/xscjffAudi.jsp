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
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>	
	<script>
		
	</script>
	<base target="_self">
	<body>
		<html:form action="/qgzxcjff.do">
			<input type="hidden" name="pkV" id="pkV" value="${pkV}">
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 酬金发放- 酬金发放审核
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								学生酬金发放信息
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							学号：
						</td>
						<td>
							${rs.xh}
						</td>
						<td>
							<div align="right">
								岗位：
							</div>
						</td>
						<td>
							${rs.gwdm}
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>
							${rs.xm}
						</td>
						<td>
							<div align="right">
								年度：
							</div>
						</td>
						<td>
							${rs.nd}
						</td>						
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							${rs.xb}
						</td>
						<td align="right">
							月份：
						</td>
						<td>
							${rs.yf}
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							年级：
						</td>
						<td>
							${rs.nj}
						</td>
						<td align="right" >
							工作时间：
						</td>
						<td> 
							${rs.gzsj}
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							${rs.xymc}
						</td>
						<td align="right" nowrap="nowrap">
							酬金金额：
						</td>
						<td>
							${rs.cjje}
						</td>						
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							${rs.zymc}
						</td>
						<td align="right">
							银行名称：
						</td>
						<td>
							${rs.yhmc}
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
							银行卡号：
						</td>
						<td>
							${rs.yhkh}
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3">
							${rs.bz}
						</td>
					</tr>
					<tr>
						<td align="right">
							审核结果：
						</td>
						<td colspan="3">
							<html:select property="xxsh" name="rs">
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
								<html:option value="未审核">未审核</html:option>
							</html:select>
						</td>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="refreshForm('qgzxcjff.do?method=xscjffAudiOne')"
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

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();		
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

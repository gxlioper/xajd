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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
	function jyglByqxSh() {
		 	document.forms[0].action = "/xgxt/jyglByqxSh.do?act=shenhe&doType=shenghe";
		 	document.forms[0].submit();
    }
	</script>
	<body>
		<fieldset>
			<legend>
				毕业去向审核
			</legend>
			<html:form action="/data_search" method="post">
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr>
							<td align="left" colspan="4">
								学生类别:
								<html:text property="xslb" name="rs" style="width:45px"
									readonly="true" />
								&nbsp;&nbsp;毕业年度:
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								&nbsp;&nbsp;提交时间：
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right" width="17%">
							学号:
						</td>
						<td align="left" width="33%">
							<html:text name="rs" property="xsxh" style="width=100%"
								readonly="true" />
						</td>
						<td align="right" width="15%">
							姓名：
						</td>
						<td align="left" width="35%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							班级名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							生源地区：
						</td>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>
						<td align="right">						
						</td>
						<td align="left">
						
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							毕业去向：
						</td>
						<td align="left">
							<bean:write name="rs" property="byqx" />
						</td>
						<td align="right">
							联系地址：
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdz" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdh" />
						</td>
						<td align="right">
							邮政编码：
						</td>
						<td align="left">
							<bean:write name="rs" property="yzbm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							移动电话：
						</td>
						<td align="left">
							<bean:write name="rs" property="yddh" />
						</td>
						<td align="right">
							电子邮箱：
						</td>
						<td align="left">
							<bean:write name="rs" property="email" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							档案邮寄地址：
						</td>
						<td align="left">
							<bean:write name="rs" property="dayjdz"/>
						</td>
						<td align="right">
							档案机要单号：
						</td>
						<td align="left">
							<bean:write name="rs" property="dajydh" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							邮电局邮戳日期：
						</td>
						<td align="left">
							<bean:write name="rs" property="ydjycrq" />
						</td>
						<td align="right">
							报到证号：
						</td>
						<td align="left">
							<bean:write name="rs" property="bdzh" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							协议书编号：
						</td>
						<td align="left">
							<bean:write name="rs" property="xysbh" />
						</td>
						<td align="right">	
						</td>
						<td align="left">			
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学校审核：
						</td>
						<td align="left">
							<html:select name="rs" property="xxsh" style="width:100xp">
								<html:option value="未审核">未审核</html:option>
								<html:option value="未通过X">未通过X</html:option>
								<html:option value="已通过√">已通过√</html:option>
							</html:select>
						</td>
						<td align="right">
							审核时间：
						</td>
						<td align="left">
							<html:text name="rs" property="shsj" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:55px">
						<td align="right">
							修改意见：
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xgyj" rows="3"
								style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							审核人：
						</td>
						<td align="left">
							<html:text name="rs" property="shperson" style="width=100%"
								readonly="true" />
						</td>
						<td align="right">

						</td>
						<td align="center">

						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="jyglByqxSh()">
						提 交 审 核
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
						关 闭 窗 口
					</button>
				</div>
			</html:form>
		</fieldset>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                     alert("操作成功！");
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("操作失败！");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>


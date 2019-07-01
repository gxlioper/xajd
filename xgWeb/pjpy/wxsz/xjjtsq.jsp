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
	<script language="javascript">
		function sq(url){
			var jtmc = document.getElementById("jtmc").value;
			var jtrs = document.getElementById("jtrs").value;
			var fzls = document.getElementById("fzls").value;
			var xydm = document.getElementById("xydm").value;
			
			if(jtmc.length>50){
				alert("集体名称长度不能大于50");
			}
			if(null == jtmc || " "== jtmc
			|| null == jtrs || " " == jtrs
			|| null == xydm || " "== xydm
			|| null == fzls || "" == fzls){
				alert("请把带*项填写完整！")
				return false;
			}
			document.forms[0].action=url;
			document.forms[0].submit();	
					
		}
		
		function check(obj){
			obj.value = obj.value.replace("%","")
												  .replace("#","")
												  .replace("@","")
												  .replace("!","")
												  .replace("^","")
												  .replace("~","")
												  .replace("&","")
												  .replace("*","")
												  .replace("$","");
		}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post" styleId="form1">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：${tip }
				</div>
			</div>
			<table width="99%" id="rsT" class="tbstyle">
				<tr height="30">
					<td align="center" width="25%">
						<font color="red">*</font>推荐集体名称
					</td>
					<td colspan="3" width="40%">
						<input type="text"  name="jtmc" id="jtmc"  onblur="check(this)"
						value="<bean:write property="jtmc" name="pj"/>">
					</td>
					<td align="center">
						<font color="red">*</font>集体人数
					</td>
					<td width="20%">
						<html:text property="jtrs" name="pj"
							style="height:23px;width:150px"
							styleId="jtrs"
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr height="30">
					<td colspan="1" align="center" width="20%">
						<font color="red">*</font>所在院、系（部）
					</td>
					<td colspan="5">
						<html:select property="xydm" name="pj" styleId="xydm">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
										  labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="1" align="center">
						<font color="red">*</font>现任班主任（负责老师）姓名
					</td>
					<td colspan="5">
						<html:text property="fzls" name="pj"
							styleId="fzls"
							style="height:23px;width:300px" maxlength="30" />
					</td>
				</tr>
				<tr>
					<td colspan="1" align="center">
						<br />
						主
						<p />
							要
						<p />
							事
						<p />
							迹
						<p />
					</td>
					<td colspan="5">
						<html:textarea rows="9" cols="100" property="zysj" name="pj" />
					</td>
				</tr>
				<tr>
					<td colspan="1" align="center">
						<p>
							<br />
							院、系（部）
						</p>
						<p>
							意见
							<br />
						</p>
					</td>
					<td colspan="5">
						<html:textarea rows="6" cols="100" property="yxyj" name="pj" />
					</td>
				</tr>
				<tr>
					<td colspan="1" align="center">
						学
						<br />
						工
						<br />
						处
						<br />
						意
						<br />
						见
						<br />
					</td>
					<td colspan="5">
						<html:textarea rows="6" cols="100" property="xgcyj" name="pj" />
					</td>
				</tr>
				<tr>
					<td colspan="1" align="center">
						学
						<br />
						院
						<br />
						意
						<br />
						见
						<br />
					</td>
					<td colspan="5">
						<html:textarea rows="6" cols="100" property="xyyj" name="pj" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:equal value="query" name="doType" scope="request">
					<button class="button2" onclick="window.close();return false;">
						关&nbsp;&nbsp;闭
					</button>
				</logic:equal>
				<logic:notEqual value="query" name="doType" scope="request">
					<logic:notEqual value="modi" name="doType" scope="request">
						<button class="button2"
							onclick="sq('/xgxt/wxsz_xjjt_sq.do?doType=save');">
							提 交 申 请
						</button>
					</logic:notEqual>
					<logic:equal value="modi" name="doType" scope="request">
						<button class="button2"
							onclick="sq('/xgxt/wxsz_xjjt_sq.do?doType=save');">
							修&nbsp;&nbsp;改
						</button>
					</logic:equal>
				</logic:notEqual>
			</div>
		</html:form>
		<script type="text/javascript">
			var jtmc = document.getElementById("jtmc");
			if(jtmc.value != null && jtmc.value != ""){
				jtmc.readOnly='readOnly';
			}
		</script>
		<logic:equal value="true" name="result" scope="request">
			<script>
			alert('提交成功');
			if(window.dialogArguments){
				Close();
				dialogArgumentsQueryChick();
			}
		</script>
		</logic:equal>
	</body>
</html>

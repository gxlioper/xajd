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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script>
			function commit(mustFill){
				var ele = mustFill.split('-');
				for(var i=0; i<ele.length;i++){
					if(document.getElementById(ele[i]).value==''){
						alert('请将带\*号的项目填写完整！');
						return false;
					}
				}
				BatAlert.showTips('正在执行操作，请等待...');
				refreshForm('qgzxLogic.do?method=saveBatchPay');
			}
		</script>
		<html:form action="/data_search" method="post">
			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"> 勤工助学 - 酬金发放 - 酬金发放 - 酬金发放详单 </span>
				</div>
			</div>						
			<fieldset>			
				<table width="100%" class="tbstyle" id="rsT">
					<tr>
						<td height="27" align="center">
							<font color="red">*</font>月份
						</td>
						<td align="center">
							<html:select property="yf" styleId="dqyf" style="width:120px">
								<html:option value=""></html:option>
								<html:option value="01">01</html:option>
								<html:option value="02">02</html:option>
								<html:option value="03">03</html:option>
								<html:option value="04">04</html:option>
								<html:option value="05">05</html:option>
								<html:option value="06">06</html:option>
								<html:option value="07">07</html:option>
								<html:option value="08">08</html:option>
								<html:option value="09">09</html:option>
								<html:option value="10">10</html:option>
								<html:option value="11">11</html:option>
								<html:option value="12">12</html:option>
							</html:select>
						</td>
					</tr>	
					<tr>
						<td height="27" align="center">
							<font color="red">*</font>发放时间
						</td>
						<td align="center">
							<html:text property="ffsj" onclick="return showCalendar('ffsj','y-mm-dd');" styleId="ffsj"/>
						</td>
					</tr>					
				</table>
			</fieldset>
			<div class="buttontool">				
				<button type="button" class="button2" onclick="commit('dqyf-ffsj');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;				
				<button type="button" class="button2" onclick="Close();return false;">
					关 闭
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>	
					alert("操作成功！");
					Close();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>	
						alert("操作失败！");
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>


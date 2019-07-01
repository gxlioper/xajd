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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function SaveAdd(){
		   document.forms[0].action = "/xgxt/xssfzbb.do?method=updatesfzbbxx&doType=save";
		   document.forms[0].submit();
		}		
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		function changeView(obj){
			var hkzt = document.getElementById('bbzt');
			if('已补办好' == hkzt.value){
				$('sfxs').style.display = 'block';
			}else{
				$('sfxs').style.display = 'none';
			}
		}
		
		function disabledEle(value){
			if("否" == value){
				ele('div_lqrxm').disabled = true;
				ele('txt_lqrxm').disabled = true;
				ele('lqrxm').readOnly = true;
				ele('txt_lqrq').disabled = true;
				ele('div_lqrq').disabled = true;
				setVal('lqrxm','');
				setVal('lqrq','');
			}else{
				ele('div_lqrxm').disabled = false;
				ele('txt_lqrxm').disabled = false;
				ele('txt_lqrq').disabled = false;
				ele('div_lqrq').disabled = false;
				ele('lqrxm').readOnly = false;
			}
		}
	</script>
<body onload="disabledEle(val('sflq'))">

	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：日常事务 - 学生身份正管理 - 补办申请修改
		</div>
	</div>
	<html:form action="/xssfzbb" method="post">
		<input type="hidden" name="sqsj"
			value="<bean:write name="rs" property="sqsj" />" />
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="15%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="35%">
					<html:text name='rs' property="xh" readonly="readonly" styleId="xh" />
				</td>
				<td align="right" width="15%">
					姓名：
				</td>
				<td align="left" width="35%">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />名称：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					专业名称：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr>
				<td align="right">
					班级名称：

				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					学年：

				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学期：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xqmc" />
				</td>
				<td align="right">
					补办状态：
				</td>
				<td align="left">
					<logic:notEqual value="yes" name="view">
						<html:select name="rs" property="bbzt" styleId="bbzt"
							style="width:150px" onchange="changeView(this);">
							<html:option value="">--请选择--</html:option>
							<html:option value="正在补办中">正在补办中</html:option>
							<html:option value="已补办好">已补办好</html:option>
						</html:select>
					</logic:notEqual>
					<logic:equal value="yes" name="view">
						<bean:write name="rs" property="bbzt" />
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						申报理由
						<br>
					</div>
				</td>
				<td colspan="3">
						<bean:write name="rs" property="sqly" />
				</td>
			</tr>
		</table>
		<div id="sfxs"
			style="display: 
			<logic:notEqual value="已补办好" name="rs" property="bbzt">
				none
			</logic:notEqual>
			<logic:equal value="已补办好" name="rs" property="bbzt">
				block
			</logic:equal>
		">

			<table class="tbstyle" width="100%">
				<tr>
					<td align="right">
						是否领取：
					</td>
					<td align="left">
						<html:select name="rs" property="sflq" styleId="sflq" onchange="disabledEle(this.value)">
							<html:option value="">--请选择--</html:option>
							<html:option value="可领取">可领取</html:option>
							<html:option value="已领取">已领取</html:option>
						</html:select>
					</td>
					<td align="right" id="">
						<div id="div_lqrxm">
						领取人姓名：
						</div>
					</td>
					<td align="left">
						<div id='txt_lqrxm'>
						<html:text name="rs" property="lqrxm" maxlength="40"
							style="width:150px"></html:text>
						</div>
					</td>
				</tr>
				<tr>

					<td align="right">
						<div id="div_lqrq">
						领取日期：
						</div>
					</td>
					<td align="left">
						<div id="txt_lqrq">
						<html:text name="rs" property="lqrq" styleId="lqrq"
							readonly="true" style="width:150px"
							onclick="this.value='';return showCalendar('lqrq','y-mm-dd');"
							onblur="getRqVal('lqrq')"></html:text>
						</div>
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>

			</table>
		</div>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="yes" name="view">
				<button type="button" class="button2" onClick="SaveAdd()" id="buttonSave">
					修 改
				</button>
			</logic:notEqual>
			&nbsp;&nbsp;
			<button type="button" class="button2" onClick="Close()" id="buttonSave">
				关 闭
			</button>
		</div>
	</html:form>
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('操作成功！');
			 window.dialogArguments.document.getElementById('search_go').onclick();
			 window.close();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			 alert('操作失败！');
		</script>
	</logic:equal>
</body>
</html>

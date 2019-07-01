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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(hkqx,sfgdhkfs){
			if(!isNumber(hkqx)){
				alert('最大还款期限必须为整数!');
				return false;
			}
			if(sfgdhkfs == "1"){
				var gdhkfsdm = document.getElementById('hkfsdm').value;
				if(gdhkfsdm == null){
					alert("请选者需要固定的还款方式");
					return false;
				}
			}
			document.forms[0].action = "/xgxt/hkxysd.do?doType=add";
			document.forms[0].submit();
			
		}
		
		function gd1(){
			document.forms[0].action = "/xgxt/hkxysd.do?doType=read&sfgdhkfs=1";
			document.forms[0].submit();
		}
		
		function gd2(){
			document.forms[0].action = "/xgxt/hkxysd.do?doType=read&sfgdhkfs=0";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 还款协议设定
		</div>
	</div>
		<html:form action="hkxysd.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/hkxysd.do" />


			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			
			<logic:notEmpty name="isNULL">
				<logic:equal name="isNULL" value="is">
				<script language="javascript">
	         	alert("没找到该学生信息，请确认是否在助学贷款审核中已导入该学生信息！");
	         	</script>
				</logic:equal>
			</logic:notEmpty>

			<table width="100%" border="1" class="tbstyle">
				<tr>
						<td align="center" scope="col" colspan="4">
							<strong>还款方式设定</strong>
						</td>
				</tr>
				<tr>
					<td scope="row" width="16%">
						<div align="right">
							还款方式名称1：
						</div>
					</td>
					<td width="34%">
						<div align="left">
							<input type="text" id="hkfsmc1" name="hkfsmc1"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc1" />" >
						</div>
					</td>
					<td width="16%">
						<div align="right">
							还款方式名称2：
						</div>
					</td>
					<td width="34%">
						<div align="left">
							<input type="text" id="hkfsmc2" name="hkfsmc2"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc2" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							还款方式名称3：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc3" name="hkfsmc3"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc3" />" >
						</div>
					</td>
					<td>
						<div align="right">
							还款方式名称4：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc4" name="hkfsmc4"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc4" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							还款方式名称5：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc5" name="hkfsmc5"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc5" />" >
						</div>
					</td>
					<td>
						<div align="right">
							还款方式名称6：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc6" name="hkfsmc6"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc6" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							还款方式名称7：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc7" name="hkfsmc7"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc7" />" >
						</div>
					</td>
					<td>
						<div align="right">
							还款方式名称8：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc8" name="hkfsmc8"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc8" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							还款方式名称9：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc9" name="hkfsmc9"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc9" />" >
						</div>
					</td>
					<td>
						<div align="right">
							还款方式名称10：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkfsmc10" name="hkfsmc10"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hkfsmc10" />" >
						</div>
					</td>
				</tr>
				
				<tr>
					<td scope="row">
						<div align="right">
							是否固定还款方式：
						</div>
					</td>
					<td>
						<div align="center">
							<logic:present name="rs" property="sfgdhkfs">
							         <logic:match value="1" name="rs" property="sfgdhkfs" >
							         	<input type="radio" name="sfgdhkfs" value="1" onmousedown="gd1();" checked>&nbsp;&nbsp;固定
							         	<input type="radio" name="sfgdhkfs" onmousedown="gd2();" value="0">&nbsp;&nbsp;不固定
							         </logic:match>
							         <logic:match value="0" name="rs" property="sfgdhkfs">
							         	<input type="radio" name="sfgdhkfs" onmousedown="gd1();" value="1">&nbsp;&nbsp;固定
							         	<input type="radio" name="sfgdhkfs" onmousedown="gd2();" value="0" checked>&nbsp;&nbsp;不固定
							         </logic:match>		
						         </logic:present>
						         <logic:notPresent name="rs" property="sfgdhkfs">						        
						         	<input type="radio" name="sfgdhkfs" onmousedown="gd1();" value="1">&nbsp;&nbsp;固定 
							         <input type="radio" name="sfgdhkfs" onmousedown="gd2();" value="0" checked>&nbsp;&nbsp;不固定
						         </logic:notPresent>
						</div>
						<input type="hidden" id="sfgdhkfsT" name="sfgdhkfsT"
								value="<bean:write name='rs' property="sfgdhkfs" />" >
					</td>
					<logic:notEqual name="isGD" value="is">
					<td>
						<div align="center">
						</div>
					</td>
					<td>
						<div align="left">
						</div>
					</td>
					</logic:notEqual>
					<logic:notEqual name="isGD" value="no">
					<td>
						<div align="right">
						需要固定的还款方式：
						</div>
					</td>
					<td>
						<div align="left">
						<html:select name="rs" property="hkfsdm" style="width:100%">
								<html:options collection="hkList" property="hkfsdm" labelProperty="hkfs"></html:options>
							</html:select>
						</div>
					</td>
					</logic:notEqual>
				</tr>
				<tr>
						<td align="center" width="16%" scope="col" colspan="4">
							<strong>其它设定</strong>
						</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							银行名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="yhmc" name="yhmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="yhmc" />" >
						</div>
					</td>
					<td>
						<div align="right">
							支行名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zhmc" name="zhmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zhmc" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							最大还款期限：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkqx" name="hkqx"
								style="width:60%;heigh:100%"
								value="<bean:write name='rs' property="hkqx" />" 
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">&nbsp;&nbsp;&nbsp;&nbsp;月
						</div>
					</td>
					<td>
						<div align="right">
						</div>
					</td>
					<td>
						<div align="left">
						</div>
					</td>
				</tr>
			</table>
			
			<div class="buttontool" id="btn" style="position: absolute;width:100%" >
				<button class="button2"
					onClick="yz(document.getElementById('hkqx').value,document.getElementById('sfgdhkfsT').value);">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button class="button2" type="reset" >
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>

		</html:form>
</body>
</html:html>

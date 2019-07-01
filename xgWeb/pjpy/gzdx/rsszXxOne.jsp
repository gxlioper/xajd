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
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript'>
	function checkRs(obj){
				var xyzrs = $("xyzrs").innerText;
				if(parseInt(obj.value)> parseInt(xyzrs)){
					obj.value = "";
					alert("人数不能超过<bean:message key="lable.xsgzyxpzxy" />总人数！");
//					obj.focus();
				}
			}

</script>
</head>
<body onload="loadrs()">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评奖评优 - 参数设置 - 学校审核调整人数
		</div>
	</div>

	<html:form action="/gzdxPjpy" method="post">
		<input type="hidden" name="pk" value="${pk}">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					<bean:message key="lable.xsgzyxpzxy" />名称：
				</td>
				<td align="left" width="34%">
					<bean:write name='rs' property="xymc" />
				</td>
				<td align="right" width="16%">
						项目名称：
				</td>
				<td width="34%">
					<bean:write name='rs' property="mc" />
				</td>
			</tr>
			<tr>
				<td align="right" width="16%" >
					<bean:message key="lable.xsgzyxpzxy" />总人数：
				</td>
				<td align="left" width="34%" id="xyzrs">
					<bean:write name='rs' property="xyzrs" />
				</td>
				<td align="right" width="16%">
						建议人数：
				</td>
				<td width="34%">
					<html:text name='rs' property="jyrs" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="5" onblur="checkRs(this);"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						申请调整人数：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xysqtzrs"/>
					<input type="hidden" name="xysqtzrs" id="xysqtzrs" value="${rs.xysqtzrs }"/>
				</td>
				<td align="right">
								审核状态：
				</td>
				<td align="left">
					<html:select name='rs' property="xxsh" style="width:90px"
											styleId="xxsh" onchange="loadrs()">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						最终调整人数：
					</div>
				</td>
				<td>
					<html:text name='rs' property="tzrs" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="5" onblur="checkRs(this);"/>
				</td>
				<td align="right">
				</td>
				<td align="left">
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						申请调整&nbsp;&nbsp;&nbsp;<br/>人数原因：
						<br/>
						<font color="red">(1000字以内)</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name='rs' property="xysqtzyy" disabled="true" rows="8" style="width:100%" onblur="chLeng(this,1000)" readonly="readonly"/>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onClick="refreshForm('gzdxPjpy.do?method=dgrsUpdate')" style="width:80px" id="buttonSave">
				保 存
			</button>
			&nbsp;&nbsp;
			<button class="button2" onClick="Close()" style="width:80px">
				关 闭
			</button>
		</div>
	</html:form>
	<logic:equal value="true" name="updata">
		<script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
	</logic:equal>
	<logic:equal value="false" name="updata">
		<script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
	</logic:equal>
	<script>
		function loadrs() {
		var sh = document.getElementById('xxsh').value;
		if (sh == '修改') {
			document.getElementById('tzrs').disabled = false;
		} else {
			document.getElementById('tzrs').value='';
			if (sh=='通过') {
				document.getElementById('tzrs').value=document.getElementById('xysqtzrs').value;
			}
			document.getElementById('tzrs').disabled = true;
		}
	}
	</script>
</body>

</html:html>

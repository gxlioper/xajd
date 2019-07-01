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


	<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var bbdm = document.getElementById('bbdm').value;
			var bbwbgs = document.getElementById('bbwbgs').value;
			if(bbwbgs.trim()==0){
				document.forms[0].bbwbgs.value = bbwbgs;
			}
			if((bbdm == null) || (bbdm == "")){
				alert("奖学金项目代码不能为空!");
				return false;
			}
			if((bbdm == "优秀奖学金") || (bbdm == "自强奖学金")){
				alert("优秀奖学金和自强奖学金是特定奖学金，不能保存此奖学金代码!");
				return false;
			}
			document.forms[0].action = "/xgxt/shgc_pjpy.do?method=jxjxmEdit&act=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评奖评优 - 基础数据维护 - 奖学金项目
		</div>
	</div>
		<html:form action="shgc_kns.do" method="post">

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
			<table class="tbstyle" width="90%">
				<tr>
					<td width="15%">
						<div align="center">
							奖学金项目代码
						</div>
					</td>
					<td width="85%">
						<logic:equal name="act" value="add">
						<input type="text" id="bbdm" name="bbdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bbdm"/>" maxlength="20">
						</logic:equal>
						<logic:notEqual name="act" value="add">
						<input type="text" id="bbdm" name="bbdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bbdm"/>" readonly="readonly">
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							奖学金项目名称
						</div>
					</td>
					<td>
						<input type="text" id="bbmc" name="bbmc" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bbmc"/>">
					</td>
				</tr>
				<tr>
					<td valign="middle">
						<div align="center">
							报表格式
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="bbwbgs" rows='20' style="width:100%" onblur="yzdx(this,'bbwbgs')"/>
						<input type="hidden" id="bbwbgs" name="bbwbgs" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>
		</html:form>
</body>
</html:html>

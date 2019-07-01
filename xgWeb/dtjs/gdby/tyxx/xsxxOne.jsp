
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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
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
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="dwr/interface/nbtyxszz.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	
	<script language="javascript">	
     function rychSqSave(){
     	showTips("保存中，请稍等...");
        refreshForm("/xgxt/gdbyTyxx.do?method=tyxxOne&doType=modi");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
    function rychSqPrint(){
        window.open('nbtyJtjjkns.do?method=jtjjknsPrint&pkValue=${pkValue}');
        }	
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/gdbyTyxx" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/gdbyTyxx.do?method=tyxxOne&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：党团建设 - 团员信息 - 团员信息维护
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						<logic:equal name="notSqsj" value="no">
							<b>学生信息</b>
						</logic:equal>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
					<html:text property="xh" readonly="true"  styleId="xh" value="${xh}"/>
					<html:hidden property="save_xh" value="${xh}"/>
					</td>
					<td align="right" style="width: 10%">
						姓名：
					</td>
					<td align="left" style="width: 40%">
					<html:text name="rs" property="xm" readonly="true"  styleId="xh" value="${rs.xm}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font>性别：
					</td>
					<td align="left">
					<html:text name="rs" property="xb" readonly="true"  styleId="xb" value="${rs.xb}"/>
					</td>
					<td align="right" style="width: 10%">
						年级：
					</td>
					<td align="left" style="width: 40%">
					<html:text name="rs" property="nj" readonly="true"  styleId="nj" value="${rs.nj}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
					<html:text name="rs" property="xymc" readonly="true"  styleId="xy" value="${rs.xymc}"/>
					</td>
					<td align="right" style="width: 10%">
						专业：
					</td>
					<td align="left" style="width: 40%">
					<html:text name="rs" property="zymc" readonly="true"  styleId="nj" value="${rs.zymc}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font> 班级：
					</td>
					<td align="left">
					<html:text name="rs" property="bjmc" readonly="true"  styleId="xy" value="${rs.bjmc}"/>
					</td>
					<td align="right" style="width: 10%">
						
					</td>
					<td align="left" style="width: 40%">
						 
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;" style="width:80px">
						关闭 
					</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			    </script>
			</logic:equal>
		</html:form>
	</body>


</html>


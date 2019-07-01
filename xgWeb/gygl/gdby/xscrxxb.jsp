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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
    <base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
				<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/XsGyGlLogic.do?method=xsGyXsCr_Modi&doType=add&pkValue=" />		
				<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 信息维护 - 学生非常规出入登记 - 出入信息维护
				</div>
			</div>

			<fieldset>
				<legend>
					出入信息维护
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>填写信息</b>
							</td>
						</tr>
					</thead>
					<tr valign="middle">
						<td align="right" width="15%">
							<font color="red">*</font>学号：
						</td>
						<td align="left">						
						<logic:notEqual value="add" name="doType">					
						 <html:text name='rs' property="xh" readonly="true"
								styleId="xh"/>
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onblur="dctStuXh()" />
							<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
								class="btn_01" id="buttonFindStu" >
								选择
							</button>
						</logic:equal>						
						</td>
						<td align="right" width="25%">
							<font color="red">*</font>楼栋名称：
						</td>
						<td align="left">
						<logic:notEqual value="add" name="doType">
						 <html:hidden name='rs' property="lddm" />
						 <html:text name='rs' property="ldmc" readonly="true"></html:text>
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:select name="rs" property="lddm" style="width:90px" styleId="lddm"
								onchange="GetQshList()">
								<html:option value=""></html:option>
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
						</logic:equal>	
						</td>
					</tr>
					<tr valign="middle">
					<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
						<td align="right" width="25%">
							<font color="red">*</font>寝室号：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name='rs' property="qsh" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<input type="hidden" name="qshV" id="qshV" />
								<html:select name="rs" property="qsh" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</logic:equal>
						</td>
					</tr>
					<tr valign="middle">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right">
						<font color="red">*</font>携带品：
						</td>
						<td align="left">
                        <html:text name="rs" property="xdp" maxlength="25"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
						   <bean:write name='rs' property="nj" />
						</td>
						<td align="right">
						<font color="red">*</font>数量：
						</td>
						<td align="left">
						<html:text name="rs" property="sl" maxlength="15"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
							<font color="red">*</font>日期：
							</td>
							<td align="left">
								<html:text name="rs" property="rq" onblur="dateFormatChg(this)" readonly="true"
						   onclick="return showCalendar('rq','y-mm-dd');" style="width:100px;cursor:hand "></html:text>
							</td>
						</tr>
					<tr style="height:22px">
							<td align="right">
								班级：
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
								带进时间：
							</td>
							<td align="left">
								<html:text name="rs" property="djsj" maxlength="10"></html:text>
							</td>
						</tr>				
					<tr valign="middle">
						<td align="right">
							
						</td>
						<td align="left">
						</td>
						<td align="right">
							带出时间：
						</td>
						<td align="left">
						<html:text name="rs" property="dcsj" maxlength="10"></html:text>
						</td>
					</tr>
					<tr style="height:32px">
						<td align="right">
							备注：
						</td>
						<td align="left" colspan="4">
						<html:textarea name='rs' property='bz' style="width:500px"  rows='5'/>
 						</td>
					</tr>
				</table>
				<div class="buttontool" id="btn" align="center">
					<logic:notEqual value="view"  name="doType">
					<button class="button2"
						onclick="xscrsave()"
						style="width:80px" id="buttonSave">
						保存
					</button>
					&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonSave">
						关闭
					</button>
				</div>
			</fieldset>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "100%";
window.setInterval("initBTNTool('btn')",1);
</script>
<logic:equal value="ok" name="done">
<script language="javascript">
alert("操作成功！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("操作失败！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
</body>
<script type="text/javascript">
function xscrsave(){
   if($("bz").value.length>200){
       alert("备注字数不能大于200字！");
       return false;
   }
   if(IsNoEmpty('xh-lddm-qsh-xdp-sl-rq')){
     refreshForm('/xgxt/XsGyGlLogic.do?method=xsGyXsCr_Modi&doType=save');
     $("buttonSave").disabled=true;	
   }
  
}

</script>
</html>


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
	function check_user()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			document.getElementById('xydm').disabled=true;
		}
		else if("xx"==user)
		{
			document.getElementById('xydm').disabled=false;
		}
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="check_user()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xsgbForNblg" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
				<fieldset>
					<legend>
						学生组织信息上报
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>组织代码:
							</td>
							<td align="left">
								<html:text name = "rs" property="zzdm" />
							</td>
							<td align="right">
								<font color="red">*</font>组织名称:
							</td>
							<td align="left">
								<html:text name = "rs" property="zzmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								主要负责人： 
							</td>
							<td align="left">
								<html:text name = "rs" property="zyfzr" />
							</td>
							<td align="right">
								负责人联系方式:
							</td>
							<td align="left">
	        					<html:text name = "rs" property="fzrlxfs" />
							</td>
						</tr>
						<tr>
							<td align="right">
								指导老师： 
							</td>
							<td align="left">
								<html:text name = "rs" property="zdls" />
							</td>
							<td align="right">
								指导老师联系方式:
							</td>
							<td align="left">
	        					<html:text name = "rs" property="lslxfs" />
							</td>
						</tr>
						<tr>
							<td align="right">
								所属部门:
							</td>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:230px" styleId="bmdm"> 
	          					<html:option value=""></html:option> 
	          					<html:options collection="bmList" property="bmdm" labelProperty="bmmc" /> 
	          					</html:select> 
							</td>
							<td align="right">
								所属科目:
							</td>
							<td align="left">
	        						<html:select name = "rs" property="xmdm" style="width:230px" styleId="xmdm"> 
	          						<html:option value=""></html:option> 
	          						<html:options collection="sukmList" property="xmdm" labelProperty="xmmc" /> 
	        						</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								项目级别： 
							</td>
							<td align="left">
								<html:select name = "rs" property="xmjb" style="width:230px" styleId="xmjb"> 
								<logic:equal name = 'isXy'value="no">
	          					<html:option value="<bean:message key="lable.xsgzyxpzxy" />"><bean:message key="lable.xsgzyxpzxy" /></html:option>
	          					</logic:equal>
	          					<html:option value="分院">分院</html:option>
	          					<html:option value="班级">班级</html:option>
	          					</html:select> 
							</td>
							<td align="right">
								组织成员数:
							</td>
							<td align="left">
	        					<html:text name = "rs" property="zzcys" />
							</td>
						</tr>
						<tr>
							<td align="right">
								拥有班干部种类:
							</td>
							<td align="left" colspan = "3">
								<logic:iterate id="bgbzl" name="xsgbzlList" offset="0">
									<input type="checkbox" name = "bgbdmList" value="<bean:write name = "bgbzl" property="bgbdm"/>" <bean:write name = "bgbzl" property="checks"/> ><bean:write name = "bgbzl" property="bgbmc"/>					
								</logic:iterate>
							</td>
						</tr>
						<logic:equal name = 'isXy'value="no">
							<td align="right">
								审核状态： 
							</td>
							<td align="left">
								<html:select name = "rs" property="shzt" style="width:230px" styleId="shzt"> 
	          					<html:option value="未审核">未审核</html:option>
	          					<html:option value="通过">通过</html:option>
	          					<html:option value="不通过">不通过</html:option>
	          					</html:select> 
							</td>
							<td align="right">
							</td>
							<td align="left"> 
							</td>
						</logic:equal>
						<logic:equal name = 'isXy' value="yes">
							<td align="right">
								审核状态： 
							</td>
							<td align="left">
								<bean:write name = "rs"  property="shzt" />
							</td>
							<td align="right">
							</td>
							<td align="left"> 
							</td>
						</logic:equal>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2"
						onclick="szsxDataDoSave('xsgbForNblg.do?method=saveXshzzxxOne','zzdm-zzmc');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					<logic:equal name = 'isXy'value="no">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="window.open('xsgbForNblg.do?method=printXshzz&pk='+document.getElementById('pk').value);"
						style="width:80px" id="buttonSave">
						打 印 报 表
					</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close();
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

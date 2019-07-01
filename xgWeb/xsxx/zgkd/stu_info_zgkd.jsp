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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgkd/zgkd_stu_modinfo.jsp">
			<input type="hidden" name="redirect" id="redirect" value="">
			<input type="hidden" name="variable" id="variable" value="">
			<input type="hidden" name="xyV" id="xyV" value="">
			<input type="hidden" name="zyV" id="zyV" value="">
			<input type="hidden" name="bjV" id="bjV" value="">
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：学生信息 - 信息修改 - 修改个人信息
				</div>
			</div>
			
			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("不在设定时间范围内,暂不开放该功能!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			<table class="tbstyle" id="rsTable" width="100%">
			<thead>
			<tr>
			<td align="center" colspan="5">
				个人信息修改
			</td>
			</tr>
			</thead>
			<tr>
			<td align="right">
				<font color="red">*</font>学号：
			</td>
			<td>
				<html:text property="xh" name="rs" styleId="xh" maxlength="20"/>
			</td>
			<td align="right">
				姓名：
			</td>
			<td> <html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/> </td>
			<td rowspan="6">
				<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
			</td>
			</tr>
			
			<tr>
			<td align="right">
				<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
			</td>
			<td>
				<html:select property="xydm" name="rs" disabled="true" styleId="xy">
					<html:option value=""></html:option>
					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
				</html:select>
			</td>
			<td align="right">
				<font color="red">*</font>专业：
			</td>
			<td> <html:select property="zydm" name="rs" disabled="true" styleId="zy"> <html:option value=""></html:option> <html:options collection="zyList" property="zydm" labelProperty="zymc"/> </html:select> </td>
			</tr>
			
			<tr>
			<td align="right">
				年级：
			</td>
			<td>
				<html:select property="nj" name="rs" disabled="true" styleId="nj">
					<html:option value=""></html:option>
					<html:options collection="njList" property="nj" labelProperty="nj"/>
				</html:select>
			</td>
			<td align="right">
				<font color="red">*</font>班级：
			</td>
			<td> <html:select property="bjdm" name="rs" disabled="true" styleId="bj"> <html:option value=""></html:option> <html:options collection="bjList" property="bjdm" labelProperty="bjmc"/> </html:select> </td>
			</tr>
			
			<tr>
			<td align="right">
				性别：
			</td>
			<td>
				<html:radio property="xb" value="男" name="rs" disabled="true" styleId="xbn">男</html:radio>
				<html:radio property="xb" value="女" name="rs" disabled="true"  styleId="xbv">女</html:radio>
			</td>
			<td align="right">
				学制：
			</td>
			<td> <html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>年 </td>
			</tr>
			
			<tr>
			<td align="right">
				民族：
			</td>
			<td>
				<html:select property="mz" name="rs" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td align="right">
				政治面貌：
			</td>
			<td> <html:select property="zzmm" name="rs" disabled="true" styleId="zzmm"> <html:option value=""></html:option> <html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/> </html:select> </td>
			</tr>
			
			<tr>
			<td align="right">
				学籍状态：
			</td>
			<td>
				<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm">
					<html:option value=""></html:option>
					<html:option value="有学籍">有学籍</html:option>
					<html:option value="无学籍">无学籍</html:option>
				</html:select>
			</td>
			<td align="right">
				出生日期：
			</td>
			<td> <html:text property="csrq" name="rs" disabled="true" readonly="" onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				姓名拼音：
			</td>
			<td>
				<html:text property="xmpy" name="rs" disabled="true" styleId="xmpy" maxlength="32"/>
			</td>
			<td align="right">
				曾用名：
			</td>
			<td colspan="2"> <html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				身高：
			</td>
			<td>
				<html:text property="sg" name="rs" disabled="true" styleId="sg" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> 厘米
			</td>
			<td align="right">
				体重：
			</td>
			<td colspan="2"> <html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> 千克 </td>
			</tr>
			
			<tr>
			<td align="right">
				身份证号：
			</td>
			<td>
				<html:text property="sfzh" name="rs" disabled="true" styleId="sfzh" maxlength="18"/>
			</td>
			<td align="right">
				特长：
			</td>
			<td colspan="2"> <html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				培养方式：
			</td>
			<td>
				<html:text property="pyfs" name="rs" disabled="true" styleId="pyfs" maxlength="32"/>
			</td>
			<td align="right">
				培养层次：
			</td>
			<td colspan="2"> <html:text property="pycc" name="rs" disabled="true" styleId="pycc" maxlength="32"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				入学方式：
			</td>
			<td>
				<html:text property="rxfs" name="rs" disabled="true" styleId="rxfs" maxlength="32"/>
			</td>
			<td align="right">
				考生类别：
			</td>
			<td colspan="2"> 
				<html:text property="kslb" name="rs" disabled="true" styleId="kslb" maxlength="32"/> 
			</td>
			</tr>
			
			<tr>
			<td align="right">
				来源地区：
			</td>
			<td>
				<html:text property="syd" name="rs" disabled="true" styleId="syd" maxlength="25"/>
			</td>
			<td align="right">
				籍贯：
			</td>
			<td colspan="2"> <html:text property="jg" name="rs" disabled="true" styleId="jg" maxlength="10"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				电子邮箱：
			</td>
			<td>
				<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32"/>
			</td>
			<td align="right">
				联系电话：
			</td>
			<td colspan="2"> <html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				手机号码：
			</td>
			<td colspan="">
				<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</td>	
			<td>
				宿舍编号：
			</td>
			<td colspan="2">
				<html:text property="ssbh" name="rs" disabled="true" styleId="ssbh" maxlength="20"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				贫困生等级：
			</td>
			<td colspan="">
				<bean:write name="pksdj"/>
			</td>
			<td>
				寝室电话：
			</td>
			<td colspan="2">
				<html:text property="qsdh" name="rs" disabled="true" styleId="qsdh" maxlength="10"/>
			</td>
			</tr>
			</table>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="refreshForm('xsxx_zgkd.do?method=saveStuinfo')">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="Close();return false;">
						关 闭
					</button>
				</div>
			</logic:equal>
			
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");	
					Close();	
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>

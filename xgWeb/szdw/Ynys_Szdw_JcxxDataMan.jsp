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
function saveFdyxx(mustFill){
	var eles = mustFill.split("-");
	var url = "/xgxt/jcxxOne.do?doType=save&pk="
	    url += document.getElementById("pkValue").value;
	for(i = 0;i<eles.length;i++){
		if(document.getElementById(eles[i]).value == ""){
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	refreshForm(url);	
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body  onload="">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/gzxxOne" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：辅导员管理 - 信息维护 - 思政队伍 - 奖惩信息 - 
					<bean:write name="tips" scope="request"/>
				</div>
			</div>
			<logic:present name="done">
				<logic:equal value="ok" name="done">
					<script>
          alert("操作成功！");
		  dialogArgumentsQueryChick();
          Close();
        </script>
				</logic:equal>
				<logic:equal value="no" name="done">
					<script>
          alert("操作失败！");
          Close();
		  dialogArgumentsQueryChick();        
        </script>
				</logic:equal>
			</logic:present>
			<logic:notPresent name="done">
				<logic:empty name="rs">
					<p align="center">
						有错误发生！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
                        alert("您输入的学号无效!");
                        </script>
					</logic:equal>					    
					    <input type="hidden" id="userType" name="userType"
						    value="<bean:write name="userType" scope="request"/>" />
						<input type="hidden" id="doType" name="doType"
							value="<bean:write name="doType" scope="request"/>" />    
					    <input type="hidden" id="pkValue" name="pkValue"
						    value="<bean:write name="pkValue" scope="request"/>" />
						<input type="hidden" id="writeAble" name="writeAble"
						    value="<bean:write name="writeAble" scope="request"/>" />						
					<fieldset>
						<legend>
							培训信息维护
						</legend>
						<table width="100%" class="tbstyle">
							<tr>
								<td align="right" width="15%" > 
									工号：
								</td>
								<td align="left">
								    <bean:write name="rs" property="zgh"/>								    									
								</td>
								<td align="right" width="15%">
									<font color="red">*</font>奖惩文件号：
								</td>
								<td align="left">
								   <html:text name="rs" property="jcwjh" styleId="jcwjh" maxlength="25" ></html:text>								    									
								</td>									
							</tr>
							<tr>
								<td align="right">
									姓名：
								</td>
								<td align="left">
								   <bean:write name="rs" property="xm"/>								    									
								</td>
								<td align="right" width="15%">
									<font color="red">*</font>文件名：
								</td>
								<td align="left">
								   <html:text name="rs" property="jcwjm" styleId="jcwjm" maxlength="25" ></html:text>								    									
								</td>									
							</tr>
							<tr>
								<td align="right">
									姓别：
								</td>
								<td align="left">
								   <bean:write name="rs" property="xb"/>							    									
								</td>
								<td align="right" width="15%">
									<font color="red">*</font>奖惩时间：
								</td>
								<td align="left">
								   <html:text name="rs" property="jcsj" styleId="jcsj" 
								   		 onblur="dateFormatChg(this)" style="cursor:hand;"
								   		 onclick="return showCalendar('jcsj','y-mm-dd');"/>						    									
								</td>	 								
							</tr>
							<tr>
								<td align="right">
									出生日期：
								</td>
								<td align="left">
								   <bean:write name='rs' property="csrq" />						    									
								</td>
								<td align="right" width="15%">
									<font color="red">*</font>奖惩类别：
								</td>
								<td align="left">
								   <html:select name="rs" property="jclb">
								         <html:option value=""></html:option> 
								         <html:options collection="jclbList" property="jclb" 
								               labelProperty="jclb"/> 
								   </html:select>              						    									
								</td>	 								
							</tr>
							<tr>
							    <td align="right">
		 						    入校工作日期：
		 						</td>
		    					<td >
		    					<bean:write name='rs' property="lxgzsj"/>			
								</td>																								
								<td align="right">
									学历：
								</td>
								<td align="left">
								    <bean:write name="rs" property="xl" />								    									
								</td>							
							</tr>
							<tr>
							    <td align="right">
									政治面貌：
								</td>
								<td align="left">
									<bean:write name="rs" property="zzmm" />	
								</td>								
								<td align="right">
									所在系别：
								</td>
								<td>
									<logic:present name="fdyzyList">
									<logic:iterate id="s" name="fdyzyList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      							</logic:present>
								</td>												
							</tr>
							<tr>
							    <td align="right">
									所在部门：
								</td>
								<td align="left">		    
									<html:select name="rs" property="bmdm" style="width:140px"
										styleId="szbm" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<td align="right">
									职称：
								</td>
								<td align="left">
								    <bean:write name="rs" property="zc"/>
								</td>								    
							</tr>
							<tr align="left">
							    <td align="right">
									职务：
								</td>
								<td align="left">
									<html:select name="rs" property="zw" style="width:100px"
										styleId="zwdm" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="zwList" property="zwdm"
											labelProperty="zwmc" />
									</html:select>
								</td>
							    <td align="right">									
								</td>
								<td align="left">								    
								</td>	
							</tr>																					
							<tr align="left">
								<td align="right">
									负责班级：
								</td>
								<td colspan="3">
								<logic:present name="fdybjList">
									<logic:iterate id="s" name="fdybjList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      						</logic:present>
								</td>
							</tr> 
							<tr>
							    <td align="right">
									曾获奖情况：
								</td>
								<td colspan="3">
								<bean:write name="rs" property="grhjqk"/>
								</td>									
							</tr>
							<tr>
							    <td align="right">
									工作经历：
								</td>
								<td colspan="3">
								<bean:write name="rs" property="gzjl"/>
								</td>									
							</tr>
							<tr>
							    <td align="right">
									科研经历：
								</td>
								<td colspan="3">
								<bean:write name="rs" property="kyjl"/>
								</td>									
							</tr>
							<tr>
							    <td align="right">
									发表论文：
								</td>
								<td colspan="3">
								<bean:write name="rs" property="fblw"/>
								</td>									
							</tr>	   	
							<tr align="left">
								<td align="right">
									奖惩原因：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='jcyy' style="width:99%"
										rows='8' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									奖惩事由：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='jcsy' style="width:99%"
										rows='4' />
								</td>
							</tr>	
							<tr align="left">
								<td align="right">
									备注：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='bz' style="width:99%"
										rows='4' />
								</td>
							</tr>						
						</table>
					</fieldset>
					<div class="buttontool">					
						<button type="button" class="button2" onclick="saveFdyxx('jcwjh-jcwjm-jcsj-jclb','dzyx');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</logic:notEmpty>
			</logic:notPresent>
		</html:form>
	</body>
</html>

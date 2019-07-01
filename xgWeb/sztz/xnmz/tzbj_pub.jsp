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
	<base target="_self"/>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/tzbj_pub" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 信息发布 - 班级信息发布
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<input type="hidden" id="pkValue" name="pkValue"
			   value="<bean:write name="pkValue" scope="request"/>" />
			 <logic:notEmpty name="rs">  
				<fieldset>
					<legend>
						信息发布
					</legend>
					<table width="100%" class="tbstyle">
						<tr valign="middle">
							<td width="15%" align="right" nowrap >
								学年：
							</td>
							<td width="35%" align="left">
								<html:text name="rs" property="xn" styleId="xn" readonly="true"/>
							</td>
							<td width="15%" align="right" nowrap>
								学期：
							</td>
							<td width="35%" align="left">
								<html:text name="rs" property="xq" styleId="xq" readonly="true"/>
							</td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								年度：
							</td>
							<td align="left">
							    <html:text name="rs" property="nd" styleId="nd" readonly="true"/>
							</td>
							<td align="right" nowrap>
								<font color="red">*</font>素质拓展班级：
							</td>
							<td align="left">
								<html:select name="rs" property="dm" styleId="dm"
									style="width=150px;background-color:#FFFFFF;">
									<option value=""></option>
									<html:options collection="tzBjList" property="dm"
										labelProperty="mc"></html:options>
								</html:select>
							</td>
						</tr>
						
						<tr valign="middle">							
							<td align="right" nowrap>
								<font color="red">*</font>开课时间：
							</td>
							<td align="left">
								<html:text name="rs" property="kssj" readonly="true"
									onclick="return showCalendar('kssj','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " />
							</td>
							<td align="right" nowrap>
								<font color="red">*</font>结业时间：
							</td>
							<td align="left"><html:text name="rs" property="jssj" readonly="true"
									onclick="return showCalendar('jssj','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " />
							</td>
						</tr>						
						<tr align="left" valign="top">
							<td align="right">
								 内容：
							</td>
							<td colspan="3" >
								<html:textarea name="rs" property="nr" rows="6" 
									style="width:95% "></html:textarea>
							</td>
						</tr>						
					</table>
					<div class="buttontool" id="btn" align="center">
						<button class="button2"
							onclick="sztzSavePub('/xgxt/tzbj_pub.do?doType=save','dm-kssj-jssj');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
	<logic:equal value="" name="pkValue">
	    <logic:equal value="ok" name="done">
		<script type="text/javascript">
	     alert("发布成功！");
	    </script>
	    </logic:equal>
     </logic:equal> 
    <logic:equal value="" name="pkValue"> 	    
	    <logic:equal value="no" name="done">
		<script type="text/javascript">
	     alert("发布失败！");
	    </script>
	    </logic:equal>
	</logic:equal>	
	<logic:notEqual value="" name="pkValue">
		<logic:equal value="ok" name="done">
		<script type="text/javascript">
	      alert("修改成功！");
		  Close();
		  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
		<logic:equal value="no" name="done">
		<script type="text/javascript">
	      alert("修改失败！");
		  Close();
		  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
	</logic:notEqual>
</html>


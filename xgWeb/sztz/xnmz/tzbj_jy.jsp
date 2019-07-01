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
<html:form action="/tzbj_jy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 信息维护 - 拓展班级结业信息
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<input type="hidden" id="realTable" name="realTable"
			   value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
			   value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="doType" name="doType"
			   value="<bean:write name="doType" scope="request"/>"/>   
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />   
			<input type="hidden" id="url" name="url" value="/tzbj_jy.do" />     
			<logic:notEmpty name="rs">   
				<fieldset>
					<legend>
					<logic:equal value="modi" name="doType">
					    修改信息
					</logic:equal>
					<logic:notEqual value="modi" name="doType">
					    添加信息
					</logic:notEqual>	
					</legend>
					<table width="100%" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
									<b>
									<logic:equal value="modi" name="doType">
					                  修改信息
					                 </logic:equal> 
					               <logic:notEqual value="modi" name="doType">
					                    添加信息
					               </logic:notEqual> 
					               </b>
								</td>
								</tr>
							</thead>
							<tr valign="middle">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right" width="10%" >
										<font color="red">*</font>学号：
									</td>
									<td align="left">
										<html:text name='rs' property="xh" styleId="xh" readonly="true"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:notEqual value="modi" name="doType">	
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											选择										
										</button>
									</logic:notEqual>	
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right" width="15%">
										<font color="red">*</font>学号：
									</td>
									<td align="left">
										<input type="hidden" name="xh" id="xh" readonly="true"
											value="<bean:write name='rs' property="xh" />">
										<bean:write name='rs' property="xh" />
									</td>
								</logic:equal>
								<td align="right">
									<font color="red">*</font>学年：
								</td>
								<td align="left">
									<html:select property="xn" style="width:100px" styleId="xn"
										onchange="refreshForm('/xgxt/tzbj_jy.do')">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>								
						</tr>
						<tr valign="middle">
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
								<td align="right" nowrap>
									<font color="red">*</font>学期：
								</td>
								<td align="left">
									<html:select property="xq" style="width:100px" styleId="xq"
										onchange="refreshForm('/xgxt/tzbj_jy.do')">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
						<tr style="height:22px">
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
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
						<tr style="height:22px">
								<td align="right">
									年级：
								</td>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
								<td align="right" nowrap>
									结业时间：
								</td>
								<td align="left">
								<html:text name='rs' property="jysj" styleId="jysj" readonly="true"
									onclick="return showCalendar('jysj','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand "/>
								</td>							
						</tr>
						<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right" nowrap>
									结业分数：
								</td>
								<td align="left">
								<html:text name='rs' property="jyfs" styleId="jyfs"onkeypress="return sztzNumInputValue(this,5,event)"  onblur="chkInput(this,event)"/>
								</td>
						</tr>
						<tr style="height:22px">
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name='rs' property="zymc" />
								</td>								
								<td align="right" nowrap>
								    是否结业： 
								</td>
								<td align="left">
								<html:select property="SF">
									<html:options collection="sfList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<td align="right" nowrap>
									
								</td>
								<td align="left">
									
								</td>
						</tr>																																		
					</table>
					<div class="buttontool" id="btn" align="center">
						<button class="button2"
							onclick="if(IsNoEmpty('xn-xq-xh-dm')){refreshForm('/xgxt/tzbj_jy.do?doType=save')}"
							style="width:80px" id="buttonSave">
							保存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="Close();return false;"
							style="width:80px" id="buttonSave">
							关闭
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
  </body>
		<logic:equal value="ok" name="done">
		<script type="text/javascript">
	      alert("操作成功！");
		  Close();
		  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
		<logic:equal value="no" name="done">
		<script type="text/javascript">
	      alert("操作失败！");
		  Close();
		  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
	</logic:equal>
</html>

		
		
		
		

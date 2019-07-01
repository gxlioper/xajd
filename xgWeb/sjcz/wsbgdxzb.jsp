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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>		
		<html:form action="/topic_object" method="post">
		
			<logic:notPresent name="rs">
			
			
			<logic:present name="yhxx">
				<script type="text/javascript">
					alert("${yhxx}");
				</script>		
			</logic:present>
			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			
			<table width="100%" class="tbstyle">
				<tr>
					<td valign="top"><font color="red">*</font>对象组名称:</td>
					<td><input type="text" name="dxzmc" id="dxzmc" value="${dxzmc}" size="50"/></td>
				</tr>
				<tr>
					<td valign="top">&nbsp;&nbsp;对象组描述:</td>
					<td><textarea rows="5" cols="100" id="dxzms" name="dxzms" type="_moz">${dxzms}</textarea></td>
				</tr>
				<tr>
					<td valign="top"><font color="red">*</font>对象组用户:</td>
					<td>
						<html:radio property="dxzsx" value="group" onclick="submit()" >权限组</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio property="dxzsx" value="bm" onclick="submit()" >部门</html:radio>
					</td>
				</tr>
				
				<logic:present name="rsList">				
				<logic:iterate id="rs" name="rsList">
				<tr>
					<td valign="top" colspan="2">
						<html:multibox property="rscb" value="${rs.val}"/>${rs.lab}
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td valign="top" colspan="2">
						<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="checkRscb()" style="width:80px" id="buttonShow">
							显示用户
						</button>
						</div>
					</td>
				</tr>				
				</logic:present>
				
				
				<logic:present name="yhList">
				
				<tr>
					<td valign="top" colspan="2">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr align="center">
								<td>选种用户列表:</td>
								<td></td>
								<td>对象用户列表:</td>
							</tr>
							<tr align="center">
								<td width="45%">
									
									<select id="yh" multiple="multiple" name="yh" size="20" >										
										<logic:iterate id="yhxx" name="yhList">										
										<option id="${yhxx.yhm}" label="${yhxx.xm}" value="${yhxx.yhm}" title="${yhxx.yhm}">${yhxx.yhm}</option>
										
										</logic:iterate>
									</select>
									
								</td>
								<td width="10%">
								<div class="buttontool" align="center">
									<table>										
										<tr>
											<td>
											<button type="button" class="button2" onclick="doSelAll('yh','dx')" style="width:80px" id="btnAddAllYh">
												选择
											</button>
											</td>
										</tr>
										<tr>
											<td>
											<button type="button" class="button2" onclick="doSelSome('yh','dx')" style="width:80px" id="btnAddSelYh">
												&gt;
											</button>
											</td>
										</tr>
										<tr>
											<td>
											<button type="button" class="button2" onclick="doSelSome('dx','yh')" style="width:80px" id="btnDelSelYh">
												&lt;
											</button>
											</td>
										</tr>
										<tr>
											<td>
											<button type="button" class="button2" onclick="doSelAll('dx','yh')" style="width:80px" id="btnDelAllYh">
												&lt;&lt;
											</button>
											</td>
										</tr>										
									</table>
								</div>
								</td>
								<td width="45%">
									<select id="dx" multiple="multiple" name="dx" size="20"></select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2">
					<div class="buttontool" align="center">					
						<button type="button" class="button2" onclick="saveDxz()" style="width:80px" id="buttonSave">
							保 存
						</button>
					</div>
					</td>
				</tr>
				</logic:present>
				
			</table>
			
				
				
			</logic:notPresent>
			
			<logic:present name="rs">
			<logic:present name="dxzyhList">
				<table width="100%" class="tbstyle">
					<tr>
						<td valign="top" width="20%">&nbsp;&nbsp;对象组名称:</td>
						<td>${rs.dxzmc}</td>
					</tr>
					<tr>
						<td valign="top" width="20%">&nbsp;&nbsp;对象组创建人:</td>
						<td>${rs.cjr}</td>
					</tr>
					<tr>
						<td valign="top" width="20%">&nbsp;&nbsp;对象组描述:</td>
						<td><textarea rows="5" cols="100" id="dxzms" name="dxzms" readonly="true" type="_moz">${rs.dxzms}</textarea></td>
					</tr>
					
					<tr>
						<td valign="top" width="20%">&nbsp;&nbsp;对象组用户列表:</td>
						<td>
							<logic:iterate id="dxzyh" name="dxzyhList">
							${dxzyh}
							</logic:iterate>
						</td>
					</tr>
				</table>
			</logic:present>				
			</logic:present>	
				
		</html:form>
		
		
		
	</body>
</html>



















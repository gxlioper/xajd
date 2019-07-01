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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	<body>
	
		<center>
			<html:form action="/xsxxgl" method="post">
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
				<div class="noprint" align="left">
					学年：
					<html:select property="xn" styleClass="select"
						onchange="refreshForm('/xgxt/xsxxgl.do?method=czxxCjdPrint&pkV='+$('pkValue').value)"
						styleId="xn">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
					&nbsp;&nbsp;学期：
					<html:select property="xq" styleClass="select"
						onchange="refreshForm('/xgxt/xsxxgl.do?method=czxxCjdPrint&pkV='+$('pkValue').value)"
						styleId="xq">
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>
				</div>
				<br>
				<logic:notEmpty name="rs">
				<table>
				<logic:iterate id="v" name="rs" indexId="index">
				<tr><td>
					<span align="left">
						<logic:iterate id="list" name="v" offset="0" length="1">
							<strong>${list.bjmc}&nbsp;&nbsp;&nbsp;&nbsp;
							${list.xm}&nbsp;&nbsp;&nbsp;&nbsp;
							${list.xn}-
							${list.xq}学期成绩单
							</strong>	
						</logic:iterate>
					</span>					
					<table class="tbstyle" align="left" width="1024px">
						<tr>
							<td>课程名称</td>
							<td>获得学分</td>
							<td>成绩</td>
							<td>课程名称</td>
							<td>获得学分</td>
							<td>成绩</td>
							<td>课程名称</td>
							<td>获得学分</td>
							<td>成绩</td>
						</tr>	
						<% int num = 0;%>					
						<logic:iterate id="cj" name="v" offset="0" indexId="index">
							<%	num = index.intValue()+1;
								if((index.intValue()+1)%3==1){%>
                                  <tr   class="alt">     
                                  <%}%>                                         
                                    <td nowrap="nowrap">${cj.kcmc}</td>
									<td nowrap="nowrap">${cj.xf}</td>
									<td nowrap="nowrap">${cj.cj}</td>  
                          			<%if((index.intValue()+1)%3==0){%>   
                                  </tr>     
                                  <%}%>
						</logic:iterate>
						<% 
							if(num % 3 !=0){
								for(int i=0; i<3-num%3; i++){
						%>
									<td></td>
									<td></td>
									<td></td>
						<%			
								}
						%>
								</tr>
						<%
							}
						%>
					</table>	
				</p>
				</td></tr>
				<tr height="10px"></tr>
				</logic:iterate>
				</table>
				</logic:notEmpty>
				<br><br><br><br>
				<div class="noprint" align="center" >
					<input type='button' class='button2' value='页面设置'
						onclick="WebBrowser.ExecWB(8,1);return false;">
					<input type='button' class='button2' value='打印预览'
						onclick="WebBrowser.ExecWB(7,1);return false;">
					<input type='button' class='button2' value='直接打印'
						onclick="WebBrowser.ExecWB(6,6);return false;">
				</div>
			</html:form>
		</center>
		
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

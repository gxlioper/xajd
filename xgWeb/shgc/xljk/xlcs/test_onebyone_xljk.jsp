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
<title>在线普测</title>
<base target="_self" />
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript">
			function beginExer(){
				if(checkBoxOk() == false){
					alert("请选择一个选项");
					return;
				}
				var sjlsh = document.getElementById("sjlsh").value;
				var stxh = document.getElementById("stxh").value;
				var xx = document.getElementById("xx").value;
				refreshForm('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=getstOneByOne_xljk&sjlsh='
						+sjlsh + "&stxh=" + stxh + "&xx=" + xx,450,300);	

			}
			
			function checkBoxOk(){
				var stxhOld = document.getElementById("stxhOld").value;
				var box = document.getElementsByName(stxhOld);
				for(i=0;i<box.length;i++){
					if(box[i].checked == true){
						document.getElementById("xx").value = box[i].value;
						return true;
					}
				}
				return false;
			}
		</script>
</head>	
<body bgcolor="DOEOEE">
	<html:form action="/xljk_xlcs_zxpc.do">
		<input type="hidden" name="sjlsh" value="<bean:write name="sjlsh" scope="request" />" id="sjlsh"/>
		<input type="hidden" name="stxh" value="<bean:write name="stxh" scope="request" />" id="stxh"/>
		<input type="hidden" name="stxhOld" value="<bean:write name="stxhOld"/>" id="stxhOld"/>
		<input type="hidden" name="xx" value=""/>
		<input type="hidden" name="totalNum" value="<bean:write name="totalNum"/>" id="totalNum"/>
		<table align="left" width="100%">
			<tr><td>
			<logic:iterate id="stin" name="stinfo" offset="1" length="1">
				<bean:write name="stxhOld"/>:
				<bean:write name="stin" property="stnr"/>
			</logic:iterate>	
			</td></tr>
			<tr>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="<bean:write name="stxhOld"/>" value="0"/>:没有
				</td>
			</tr>	
			<tr>
				<td>
					<input type="radio" name="<bean:write name="stxhOld"/>" value="1"/>:很轻
					</td>
			</tr>
			<tr>
				<td>	
					<input type="radio" name="<bean:write name="stxhOld"/>" value="2"/>:中等
				</td>
			</tr>
			<tr>
				<td>		
					<input type="radio" name="<bean:write name="stxhOld"/>" value="3"/>:偏重
				</td>
			</tr>
			<tr>
				<td>		
					<input type="radio" name="<bean:write name="stxhOld"/>" value="4"/>:严重
				</td>
			</tr>
			<tr bgcolor="DOEOEE">
				<td align="center" >
				<div align="center">
					<button class="button2"
						 onclick="beginExer()" 
						 style="width:100px"
						id="buttonClose">
						下一题
					</button>
				</div>	
				</td>
			</tr>
	</table>	
	</html:form>
			
</body>
</html>
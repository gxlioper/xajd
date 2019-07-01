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
		
   	function sumpd(){
   			var xxdm = document.getElementById("xxdm").value;
			var sum=document.forms[0].ysj.value;			
			var myzgxs=document.forms[0].myzgxs.value;
			var xxdm = document.getElementById("xxdm").value;
			if(xxdm == "10856"){
				sum = document.getElementById("gzsj").value;
			}
			if(parseInt(sum)>parseInt(myzgxs)){
				alert("总工作时间超过每月最大工作时间"+myzgxs+"小时!");
				return false;
			}			
			return true;
		}
		
		function initValue(){
			 var kssj = document.getElementById("kssj").value;
			 var jssj = document.getElementById("jssj").value;
			 if(kssj!=null && kssj!=""){
			 	 document.getElementById("kshour").value = kssj.substring(0,kssj.indexOf('点'));
			 	 document.getElementById("ksminute").value = kssj.substring(kssj.indexOf('点')+1,jssj.indexOf('分'));
			 	 document.getElementById("jshour").value = jssj.substring(0,jssj.indexOf('点'));
			 	 document.getElementById("jsminute").value = jssj.substring(jssj.indexOf('点')+1,jssj.indexOf('分'));
			 }
		}
	  function saveInfo(url){
	  	document.forms
	  }
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();document.all('buttonClose').focus();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
			<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 考核 - 学生工作记录 - 单个学生工作情况记录
			</div>
			</div>
						<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
						<input type="hidden" name="doType" value="<bean:write name="doType"/>" />
						<input type="hidden" name="xh" value="<bean:write name="rs" property="xh" />" />
						<input type="hidden" id="mxsbc" name="mxsbc" value="<bean:write name="mxsbc" scope="request"/>"/>
						<input type="hidden" id="myzgxs" name="myzgxs" value="<bean:write name="myzgxs" scope="request"/>"/>
						<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="request"/>"/>
						<input type="hidden" id="ysj" name="ysj" value=""/>
						<input type="hidden" id="yje" name="yje" value=""/>
						<table width="100%" align="center" class="tbstyle" id="tb">
						<tr><td>
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										单个学生工作情况记录
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<td align="right">
									学号：
								</td>
								<td align="left">
									<input type="hidden" id="xh" name="xh" value="<bean:write name="rs" property="xh" />"/>
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right">
									年度：
								</td>
								<td align="left">
									<bean:write name="rs" property="nd" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									学年：
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									岗位名称：
								</td>
								<td align="left">
									<bean:write name="rs" property="gwdm" />
									<input type="hidden"  id="gwmc" name="gwmc" value="${rs.gwdm}"/>
									<input type="hidden"  id="gwdm" name="gwdm" value="${rs.gwdm}"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									年级：
								</td>
								<td align="left">
									<bean:write name="rs" property="nj" />
								</td>
								<td align="right">
									申请时间：
								</td>
								<td align="left">
									<bean:write name="rs" property="sqsj" />
									<input type="hidden" id="sbsj" name="sbsj" value="<bean:write name="rs" property="sqsj" />"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
								<td align="right">
									是否困难生：
								</td>
								<td align="left">
									<bean:write name="rs" property="sfpks" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
								<td align="right">
									联系电话：
								</td>
								<td align="left">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
								<td align="right">
									审核：
								</td>
								<td align="left">
									<bean:write name="rs" property="yesNo" />
								</td>
							</tr>	
							<tr style="height:22px">
								<td align="right">
									月份：
								</td>
								<td align="left">
									<bean:write name="yf"/>
									<input type="hidden"  id="yf" name="yf" value="<bean:write name="yf"/>"/>
								</td>
								<td align="right">
									本月总工作时间：
								</td>
								<td align="left">
									<html:text property="gzsj" name="rs" styleId="gzsj">									
									</html:text>小时
								</td>				
							</tr>										
							<logic:present name="xskh">
								<tr>
									<td align="right">
										工作表现：
									</td>
									<td align="left">
										<html:select name="rs" property="gzbx" style="width:120px"
											styleId="gzbx">
										<html:option value="优"> 优 </html:option>
										<html:option value="良"> 良 </html:option>
										<html:option value="差"> 差 </html:option>
										</html:select>
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>
							</logic:present>
							<logic:present name="gzjl">
								<td align="right">
									工作情况：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='xsgzqk' rows="4" styleId="xsgzqk" style="width:99%" />
								</td>
							</logic:present>
							<logic:present name="xskh">
								<td align="right">
									工作情况：
								</td>
								<td colspan="3"><html:textarea name='rs' property='xsgzqk' rows="4" styleId="xsgzqk" readonly="true" style="width:99%" />
								</td>
							</logic:present>
								</table>
								</td></tr>
								<tr>
								  <td>&nbsp;
								  <logic:present name="allow">
								 <font color="red"><bean:write name="allow"/></font>
								  </logic:present>
								  </td>
								</tr>
						</table>
						<div class="buttontool" align="center">
							<logic:notPresent name="allow">
							<button type="button" class="button2"
								onclick="if(sumpd()) {refreshForm('/xgxt/stu_work_info.do?act=save&doType=' + document.getElementById('doType').value);Close();window.dialogArguments.document.getElementById('search_go').click();}"
								style="width:80px" id="buttonSave">
								保 存
							</button>	
							</logic:notPresent>						
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="expTab('tb','','')"
								style="width:80px" id="buttonPrint">
								打印列表
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
								id="buttonClose">
								关 闭
							</button>
					</div>
		</html:form>
	</body>
</html>

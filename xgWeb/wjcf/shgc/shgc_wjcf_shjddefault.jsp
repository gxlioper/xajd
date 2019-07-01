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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/wjcfFuction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	<body>	   
	    <html:form action="/shgcwjcfwh" method="post">
			<input type="hidden" name="pkVal" id="pkVal" value="<bean:write name="pkVal" scope="request"/>"/>					
	       <div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：违纪处分 - 申诉申请审核 - 申诉决定
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>	
			<logic:notEmpty name="rs">
			<fieldset>               			    
			      <legend> &nbsp;&nbsp;材 料 或 证 明 附 件 列 表&nbsp;&nbsp; </legend> 	
			       <logic:notEmpty name="rswj"> 			
  					<table border="0" id="rsTable" width="100%"> 
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="downloadfile.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"  target="_blank">下载     							
      							</a> </td> 
      							<td > <bean:write name="list" property="cfwh" /> </td> 
      							<td > <bean:write name="list" property="cflbmc" /> </td> 
       							<td > <bean:write name="list" property="cfyymc" /> </td>
       							<td > <bean:write name="list" property="sssj" /> </td>
<%--       							<td>--%>
<%--        							<a href="#" onclick="if(confirm('确实要删除当前文件吗？'))location='fileDel.do?wjh=<bean:write name="list" property="wjh"/>';" >删除</a> </td>--%>
    						</tr> 
    					</logic:iterate> 
  					</table> 
  				</logic:notEmpty> 
  				<logic:empty name="rswj"> 
  				<center>
    			 暂无材料或证明附件 
  				</center> 
  				</logic:empty> 
			</fieldset>
				<fieldset>
					<legend>
						&nbsp;&nbsp;单个申诉申请决定&nbsp;&nbsp;
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									单个申诉申请决定
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" width="20%">
								学号：
							</td>
							<td align="left" width="30%">
								<bean:write name="rs" property="xh" />
							</td>
							<td align="right" width="18%">
								处分文件号：
							</td>
							<td align="left">
								<bean:write name="rs" property="cfwh" />
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<td align="right">
								学年：
							</td>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<td align="right">
								处分时间：
							</td>
							<td align="left">
								<bean:write name="rs" property="cfsj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<td align="right">
								处分类别：
							</td>
							<td align="left" colspan="">
								<bean:write name="rs" property="cflbmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<td align="right">
								处分事由：
							</td>
							<td align="left" colspan="">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								复查日期：
							</td>
							<td align="left" colspan="">
								<html:text property="fcrq" styleId="slrq" onblur="dateFormatChg(this)" style="cursor:hand;inputtext"
								onclick="return showCalendar('fcrq','y-mm-dd');"></html:text>
							</td>
							<td align="right">
								日前状态：
							</td>
							<td align="left" colspan="">
								<html:select property="mqzt" style="width:100px"
									styleId="mqzt">
									<html:options collection="mqztList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								(解除/更改)<br>日期：
							</td>
							<td align="left" colspan="">
								<html:text property="jdsj" styleId="jdsj" onblur="dateFormatChg(this)" style="cursor:hand;inputtext"
								onclick="return showCalendar('jdsj','y-mm-dd');" readonly="true"></html:text>
							</td>
							<td align="right">
								(解除/更改)<br>文号：
							</td>
							<td align="left" colspan="">
								<html:text property="jdwh" styleId="jdwh" style="inputtext;" ></html:text>
							</td>
						</tr>
						<tr>
							<td align="right">
					       申诉时间：
							</td>
							<td align="left" colspan="3">
								<bean:write name="rs" property="sssj" />
							</td>
						</tr>
						<tr>
							<td align="right" rowspan="">
								申诉理由：
							</td>
							<td align="left" colspan="3" rowspan="" >
								<html:textarea property="yq" name="rs" rows="3" style="width:98%" disabled="true" readonly="true"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								初审情况：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="csqk" styleId="csqk" style="inputtext;width:98%" rows="3"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								复查情况：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="fcqk" styleId="fcqk" style="inputtext;width:98%" rows="3"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								复查通知书：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="fctzs" styleId="fctzs" style="inputtext;width:98%" rows="4"></html:textarea>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2"
							onclick="refreshForm('savessjdxx.do');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
		<logic:present name="done">
		  <logic:equal value="yes" name="done">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:notEqual value="yes" name="done">
			<script>
				alert("操作失败!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:notEqual>
		  </logic:present>
	  </html:form>	
	</body>
</html>

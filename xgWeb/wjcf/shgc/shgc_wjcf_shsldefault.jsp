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
					当前所在位置：违纪处分 - 申诉申请审核 - 申诉受理
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
						&nbsp;&nbsp;单个申诉申请受理&nbsp;&nbsp;
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									单个申诉申请受理
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
								受理情况：
							</td>
							<td align="left" colspan="">
								<html:select property="slqk" style="width:100px"
									styleId="sh">
									<html:options collection="slqkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<td align="right">
								(不)受理日期：
							</td>
							<td align="left" colspan="">
								<html:text property="slrq" styleId="slrq" onblur="dateFormatChg(this)" style="cursor:hand;inputtext"
								onclick="return showCalendar('slrq','y-mm-dd');"></html:text>
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
								<html:textarea property="yq" name="rs" rows="5" style="width:98%" disabled="true" readonly="true"></html:textarea>
							</td>
						</tr>
						
						<%--
						<tr>
							<td align="right">
								讨论结果、&nbsp;&nbsp;&nbsp;
								<br>
								内容或理由：
							</td>
							<td align="left" colspan="4">
								<html:textarea name="rs" property="tlly" rows="3"
									style="width:98%" styleId="tlly">
								</html:textarea>
							</td>
						</tr>
						--%><tr>
							<td align="right">
								(不)受理&nbsp;&nbsp;
								<br>通知书：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="sltzs" styleId="sltzs" rows="7" style="width:98%"></html:textarea>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2"
							onclick="refreshForm('savessslxx.do');"
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

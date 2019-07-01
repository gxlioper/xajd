<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showRych.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <html:form action="nbty_rych">
   <input type =hidden name="method" value="rychSh">
   <input type =hidden name="doType" value="save">
   	<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 荣誉称号审核 - 单个审核
				</div>
			</div>


			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>荣誉称号审核</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<td>
						<bean:write name="rs" property="xh" />	
				    </td>
					<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="xn" />	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xm" />
						</logic:notEmpty>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<logic:notEmpty name="rs">
						<bean:write name='rs' property="xqmc" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xb" />
					</logic:notEmpty>
					</td>
					<td align="right">
						<font color="red">*</font>荣誉称号：
					</td>
					<td align="left">
						<logic:notEmpty name="rs">
						<bean:write name='rs' property="rychmc" />
					</logic:notEmpty>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						担任职务：
					</td>
					<td align="left">
						<bean:write name="rs"  property="xrzw"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zymc"/>
					</logic:notEmpty>
					</td>
					<td align="right">
						学年平均成绩
					</td>
					<td align="left">
						<bean:write name="rs" property="xnpjcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						任现职时间：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
					   <bean:write name='rs' property="rxzsj"/>
					 </logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td align="right">
						审核
					</td>
					<td>
						<html:select property="primarykey_sh">
							<option value=""></option>
							<option value="未通过">未通过</option>
							<option value="已通过">已通过</option>
							<option value="没通过">没通过</option>
						</html:select>
					</td>
					<td align="right">
						
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
				<td colspan=2 align="center">
					<input type=submit onclick="window.close();return false;"  value="保存">
				</td>
				<td  colspan=2 align="center">
					<input type=button onclick="window.close();return false;" value="关闭">
				</td>
				</tr>
				</table>
   </html:form>
  </body>
</html>

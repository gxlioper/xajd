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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位信息查询 - 岗位详细信息
					</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							岗位详细信息
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="24%" align="right">
						岗位名称：
					</td>
					<td width="20%" align="left">
						<bean:write name="rs" property="gwdm" />
					</td>
					<td width="31%" align="right">
						所属单位：
					</td>
					<td width="25%" align="left">
						<bean:write name="rs" property="yrdwmc" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						岗位性质：
					</td>
					<td align="left">
						<bean:write name="rs" property="gwxzmc" />
					</td>
					<td align="right">
						校区：
					</td>
					<td align="left">
						<bean:write name="rs" property="xq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
					<td align="right">
						工作开始时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="gzksrq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
					<td align="right">
						工作结束时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="gzjsrq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						需要人数：
					</td>
					<td align="left">
						<bean:write name="rs" property="xyrs" />
					</td>
					<td align="right">审批使用人数：
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsyrs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						需要困难生数：
					</td>
					<td align="left">
						<bean:write name="rs" property="syknss" />
					</td>
					<td align="right">审批使用困难生人数：
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsyknss" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						计酬方式：
					</td>
					<td align="left">
						<bean:write name="rs" property="jcfsmc" />
					</td>
					<td align="right">审批计酬标准：
					</td>
					<td>
						<bean:write name='rs' property="spbcbz" />
					</td>
				</tr>				
				<tr style="height:22px">				 	
			      <td align="left"><div align="right">联系电话：</div></td>
			      <td align="left" colspan="3"><bean:write name="rs" property="lxdh" /></td>
			  </tr>
			   <tr style="height:22px">				 	
			      <td align="left"><div align="right">工作地点：</div></td>
			      <td align="left" colspan="3">
			      <bean:write name="rs" property="gzdd" />
					</td>
			  </tr>
			  <tr style="height:22px">
                  <td height="17" align="right"> 工作内容：</td>
                  <td align="left" colspan="3"> <bean:write name="rs" property="gznr" /> </td>
			  </tr>
			  <tr style="height:22px">				 	
			      <td align="left"><div align="right">工作时间：</div></td>
			      <td align="left" colspan="3">
			      <bean:write name="rs" property="gzsj" />
					</td>
			  </tr>
			  <tr style="height:22px">				 	
			      <td align="left"><div align="right">人员要求：</div></td>
			      <td align="left" colspan="3">
			      <bean:write name="rs" property="ryyq" />
					</td>
			  </tr>			   
				<tr style="height:22px">
					<td height="17" align="right">
						<FONT color="red">已申请人数：</FONT></td>
					<td align="left">
						<FONT color="red"><bean:write name="rs" property="sqrs" /></FONT>
					</td>
				    <td align="left"><div align="right"><FONT color="red">已录用人数：</FONT></div></td>
				    <td align="left">
				    	<FONT color="red"><bean:write name="rs" property="tgrs" /></FONT>
				    </td>
				</tr>				
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>					
		</html:form>
	</body>
</html>

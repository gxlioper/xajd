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
			<html:form action="/n05_xszz.do" method="post">
				<p align="center"><h3>${xxmc}学生住宿费缓交申请表 </h3></p>
				<div align="center">
				  <table cellspacing="0" cellpadding="0" class="tbstyle" width="80%">
				    <tr>
				      <td nowrap="nowrap"><p align="center"><strong>姓 名 </strong></p></td>
				      <td width="100px"><p align="center">${rs.xm}&nbsp;</p></td>
				      <td nowrap="nowrap"><p align="center"><strong>性别 </strong></p></td>
				      <td width="100px"><p align="center">${rs.xb}&nbsp;</p></td>
				      <td colspan="3"><p align="center"><strong>年级专业班级 </strong></p></td>
				      <td width="200px"><p align="center">${rs.nj}${rs.zymc}${rs.bjmc}</p></td>
				    </tr>
				    <tr>
				      <td><p align="center"><strong>缓交金额 </strong><strong></strong></p></td>
				      <td colspan="2"><p align="center">${rs.hjje}&nbsp; </p></td>
				      <td colspan="4"><p align="center"><strong>预计交费时间、金额 </strong></p></td>
				      <td >
						<p align="center">${rs.yjjfsj}${rs.yjjfje}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td><p align="center"><strong>本人电话 </strong></p></td>
				      <td colspan="7"><p align="center">${rs.sjhm}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td  nowrap="nowrap"><p align="center"><strong>申请缓交<br/>主要原因 </strong></p></td>
				      <td colspan="7"><p align="center">${rs.sqyy}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td  nowrap="nowrap">
						<p align="center"><strong>班主任 </strong></p>
				        <p align="center"><strong>意见 </strong></p></td>
				      <td  colspan="3" >
						  <p align="center"><strong>&nbsp; </strong></p>
				          <p align="center">${rs.fdyshyj}&nbsp;</p>
				          <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： </strong></p>
				          <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： </strong></p>
					  </td>
				      <td nowrap="nowrap">
						  <p align="center"><strong>分院 / 系 </strong></p>
				          <p align="center"><strong>意见 </strong></p>
					  </td>
				      <td  colspan="3" width="40%">
						  <p align="center"><strong>&nbsp; </strong></p>
				          <p align="center"><span>${rs.xyshyj}</span>&nbsp;</p>
				          <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： </strong></p>
				          <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： </strong></p></td>
				    </tr>
				    <tr>
				      <td  nowrap="nowrap"><p align="center"><strong>久通后勤<br/>服务公司<br/>意见 </strong></p></td>
				      <td width="" colspan="7"><p align="center">${rs.xxshyj}&nbsp;</p>
				          <p align="right"><strong>签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</strong></p>
				          <p align="right"><strong>日期： 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</strong></p>
					</td>
				    </tr>
				  </table>
				</div>
		</html:form>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

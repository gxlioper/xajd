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
<jsp:directive.page import="xgxt.action.Base"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="../../skin1/style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<style media='print'>
		.noPrin{
		display:none;}
	</style>
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>	
	<body>
		<p align="center"><h3 align="center">宁波天一职业技术<bean:message key="lable.xsgzyxpzxy" /> </h3></p>
		<p align="center"><h3 align="center"><%= Base.currXn%>学年学生勤工助学上岗审批表 </h3></p>
		<p align="center"><strong>&nbsp; </strong></p>
		<div align="center">
		  <table width="85%" cellpadding="0" cellspacing="0" class="tbstyle">  
			  <tr>
			    <td width="103" rowspan="5" class="xl31">个<br>人<br>基<br>本<br>情<br>况 </td>
			    <td colspan="2" class="xl22">姓 名 </td>
			    <td colspan="2" class="xl22"></td>
			    <td colspan="2" class="xl22">性 别 </td>
			    <td colspan="3" class="xl28"></td>
			  </tr>
			  <tr>
			    <td colspan="2" class="xl22">生源地 </td>
			    <td colspan="2" class="xl22"></td>
			    <td colspan="2" class="xl22">联系方式 </td>
			    <td colspan="3" class="xl28"></td>
			  </tr>
			  <tr>
			    <td width="128" class="xl23">年级、专业、班级 </td>
			    <td width="96" class="xl23"></td>
			    <td colspan="2" class="xl22"></td>
			    <td colspan="2" class="xl22">申请日期 </td>
			    <td colspan="3" class="xl28"></td>
			  </tr>
			  <tr>
			    <td colspan="2" class="xl26">享受过何种资助 <br>
			      (写明资助金额及时间) </td>
			    <td colspan="7" class="xl22"></td>
			  </tr>
			  <tr>
			    <td colspan="9" class="xl36">申请理由： <br>
			        <br>
			        <br>
			        <br>
			     <p align="right"> 本人签名：                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       年          &nbsp;&nbsp;&nbsp;&nbsp;   月              &nbsp;&nbsp;&nbsp;&nbsp;  日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</p></td>
			  </tr>
			  <tr>
			    <td rowspan="4" class="xl31">岗<br>位<br>意<br>见 </td>
			    <td height="14" class="xl23">行政助理 </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">图书馆管理员 </td>
			    <td width="83" class="xl23"></td>
			    <td colspan="2" class="xl22">教学楼保洁员 </td>
			    <td colspan="2" class="xl28"></td>
			  </tr>
			  <tr>
			    <td class="xl22">打字员 </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">机房管理员 </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">体育馆保洁员 </td>
			    <td colspan="2" class="xl28"></td>
			  </tr>
			  <tr>
			    <td class="xl22">抄写员 </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">实验楼管理员 </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl28">实验室保洁员 </td>
			    <td colspan="2" class="xl28"></td>
			  </tr>
			  <tr>
			    <td class="xl22">食堂 </td>
			    <td class="xl23"></td>
			    <td width="117" class="xl23">是否愿意去其它区的岗位 </td>
			    <td class="xl23"></td>
			    <td class="xl25"></td>
			    <td colspan="4" class="xl25"></td>
			  </tr>
			  <tr>
			    <td rowspan="20" class="xl31">课<br>程<br>表 </td>
			    <td class="xl27"></td>
			    <td class="xl23">一 </td>
			    <td class="xl23">二 </td>
			    <td class="xl23">三 </td>
			    <td class="xl23">四 </td>
			    <td width="83" class="xl23">五 </td>
			    <td width="79" class="xl23">六 </td>
			    <td width="80" class="xl23">日 </td>
			    <td width="90" class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">1 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">2 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">3 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">4 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">5 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">6 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">7 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">8 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">9 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">10 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">11 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">12 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">13 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">14 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">15 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">16 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">17 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">18 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td colspan="9" class="xl44">在有课的空格里打“√” </td>
			  </tr>
			  <tr>
			    <td class="xl24" align="center">院<br>系<br>意<br>见 </td>
			    <td colspan="3">
					<br>
					<br>
			    <p align="right"> 签字: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;</p> </td>
			    <td class="xl26" width="112" align="center">
			    	 学生<br>资助<br>中心<br>意见 </td>
			    <td colspan="5">
			        <br>
			        <br>
			    <p align="right"> 签字: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;</p> </td>
			  </tr>
			</table>
		</div>
		<div align=center class='noPrin'>
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center;'><b><span style='font-size:18.0pt;font-family:
宋体;color:black'>心理困惑学生动态信息</span></b></p> 
  <p align=right style='text-align:right'><span
style='font-size:15.0pt;font-family:楷体_GB2312'>编号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
  <div align=center> 
  <table width="100%" id="rsT" class="printstyle">
      <tr> 
        <td width=75 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>姓名</span></p></td> 
        <td width=96 class="Normal">&nbsp;${rs.xm } </td> 
        <td width=60 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>性别</span></p></td> 
        <td width=96 colspan=2 class="Normal">&nbsp; ${rs.xb }</td> 
        <td width=72 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>年龄</span></p></td> 
        <td width=82 class="Normal">&nbsp;${rs.nl } </td> 
        <td width=62 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>年级</span></p></td> 
        <td width=108 class="Normal">&nbsp; ${rs.nj }</td> 
      </tr> 
      <tr> 
        <td width=75 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>系别</span></p></td> 
        <td width=96 class="Normal">&nbsp;${rs.xymc } </td> 
        <td width=108 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>联系电话</span></p></td> 
        <td width=372 colspan=5 class="Normal">&nbsp; ${rs.lxdh }</td> 
      </tr> 
      <tr> 
        <td width=75 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>专业</span></p></td> 
        <td width=96 class="Normal">&nbsp; ${rs.zymc }</td> 
        <td width=108 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>通讯地址</span></p></td> 
        <td width=372 colspan=5 class="Normal">&nbsp; ${rs.lxdz }</td> 
      </tr> 
      <tr> 
        <td width=75 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>学生咨询问题</span></p></td> 
        <td width=576 colspan=8 class="Normal"> <p align=center style='text-align:center;'><span
  style='font-family:宋体;'>&nbsp;${rs.zxwt }</span></p></td> 
      </tr> 
      <logic:notEmpty name="khxsjl">
      <tr> 
        <td width=75  class="Normal" rowspan="${len}"> <p align=center style='text-align:center'><span
  				style='font-family:宋体;'>咨询情况登记</span></p></td> 
		<td>次序</td>
  		<td>时间</td>
  		<td>咨询老师</td>
  		<td colspan="4">咨询记录</td>
  		<td >是否持续咨询</td>
      </tr> 
      	
			     <logic:iterate name="khxsjl" id="s">
			     <tr>
			     		<td>${s.zxcx }</td>
			     		<td>${s.zxsj }</td>
			     		<td>${s.zxls }</td>
			     		<td colspan="4">${s.zxjl }</td>
			     		<td >${s.sfcxzx }</td>
			     </tr>
			     </logic:iterate>
      </logic:notEmpty>
      <tr>
        <td width=75 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体;'>日常学习、工作、生活情况登记</span></p></td> 
        <td width=576 colspan=8 class="Normal">&nbsp; ${rs.jbqk }</td> 
      </tr> 
      <tr height=0> 
        <td width=75 class="Normal"></td> 
        <td width=96 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=48 class="Normal"></td> 
        <td width=48 class="Normal"></td> 
        <td width=72 class="Normal"></td> 
        <td width=82 class="Normal"></td> 
        <td width=62 class="Normal"></td> 
        <td width=108 class="Normal"></td> 
      </tr> 
    </table> 
  </div> 
</div> 
</body>
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
</html>


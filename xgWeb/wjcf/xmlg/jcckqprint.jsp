<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  <body bgcolor="#FFFFFF" class="Normal" style='text-justify-trim:punctuation' lang=ZH-CN>
    <html:form action="/wjcfxmlgwh">
	<div class=Section1 style='layout-grid:15.6pt'> 
  <p align="center" class="style1" >厦门理工<bean:message key="lable.xsgzyxpzxy" />学生解除留校察看期申请表 </p>
  <p align="right">填表时间<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>年<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>月<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>日&nbsp;&nbsp;
  </p>
  <div align=center> 
    <table align="center" class="printstyle" width="98%"> 
      <tr> 
        <td width=85 colspan=2 class="Normal"> <div align="center">学生姓名</div></td> 
        <td width=84 class="Normal">&nbsp; </td> 
        <td width=60 class="Normal"> <div align="center">性&nbsp; 别</div></td> 
        <td width=48 class="Normal">&nbsp; </td> 
        <td width=60 class="Normal"> <div align="center">学&nbsp; 号</div></td> 
        <td width=72 colspan=2 class="Normal">&nbsp; </td> 
        <td width=72 colspan=2 class="Normal"> <div align="center">政治面貌</div></td> 
        <td width=75 class="Normal">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=85 colspan=2 class="Normal"> <div align="center">院&nbsp;&nbsp;&nbsp; 系</div></td> 
        <td width=84 class="Normal">&nbsp; </td> 
        <td width=60 class="Normal"> <div align="center">班&nbsp; 级</div></td> 
        <td width=108 colspan=2 class="Normal">&nbsp; </td> 
        <td width=72 colspan=2 class="Normal"> <div align="center">籍&nbsp; 贯</div></td> 
        <td width=147 colspan=3 class="Normal">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=169 colspan=3 class="Normal"> <div align="center">原处分文件(文件号)</div></td> 
        <td width=387 colspan=8 class="Normal">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=85 colspan=2 class="Normal"> <div align="center">受处分事由</div></td> 
        <td width=288 colspan=5 class="Normal">&nbsp; </td> 
        <td width=96 colspan=2 class="Normal"> <div align="center">受处分时间</div></td> 
        <td width=87 colspan=2 class="Normal">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=85 colspan=2 class="Normal"> <div align="center">留校察看期</div></td> 
        <td width=471 colspan=9 class="Normal"> <div align="center">＿＿＿年＿＿月＿＿日到＿＿＿年＿＿月＿＿日</div></td> 
      </tr> 
      <tr> 
        <td width=52 class="Normal"><div align="center"><br>
            <br> 
            <br>
            <br>
            学<br/>
            生<br/>
            申<br/>
            请<br/>
            理<br/>
            由<br/>
            要<br/>
            点<br>
            <br>
            <br>
            <br>
            <br> 
        </div></td> 
        <td width=503 colspan=10 valign=top class="Normal"> （说明受处分以来各方面的表现情况，取得的进步，阐述申请理由） <br/>（个人申请书另附后面） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		 <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		  <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		   <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		    <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		<p align="right" ><div align="right">本人签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><p align="right"><div align="right">年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</div></td> 
      </tr> 
	  </table>
	  <div style='page-break-before:always;'>&nbsp;</div>
	  <table align="center" class="printstyle" width="98%"> 
      <tr> 
        <td width=52 class="Normal"><div align="center">班<br/> 
          级 <br/>
          评<br/>
          议 <br/>
          意<br/> 
        见</div></td> 
        <td width=503 colspan=10 valign=top class="Normal"> （该生接受处分一年来的班级表现、学业表现情况鉴定） 
		  <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		  <p align="right">
		<p align="right" ><div align="right">班主任签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div align="right">年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</div></td> 
      </tr> 
      <tr> 
        <td width=52 class="Normal"><div align="center">辅 <br/>
          导 <br/>
          员<br/> 
          鉴<br/> 
          定<br/> 
          意<br/>
        见</div></td> 
        <td width=503 colspan=10 class="Normal"> （该生接受处分一年来的各方面表现情况鉴定）
		  <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		  <p align="right">
		<p align="right" ><div align="right">辅导员签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div align="right">年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</div></td> 
      </tr> 
      <tr> 
        <td width=52 class="Normal"><div align="center">院<br/>
          系<br/> 
          意<br/>
        见</div></td> 
        <td width=503 colspan=10 class="Normal"> （对班主任意见与辅导员意见进行审核,鉴定该生表现是否良好，是否符合按期解除察看的条件，提出建议意见） 
		  <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
		  <p align="right" ><div align="right">负责人签名（<bean:message key="lable.xsgzyxpzxy" />公章）： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div align="right">年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</div></td> 
      </tr> 
      <tr> 
        <td width=52 class="Normal"> <div align="center">学<br/>
          工<br/>
          处<br/>
          意<br/>
        见</div></td> 
        <td width=503 colspan=10 class="Normal"> 根据《厦门理工<bean:message key="lable.xsgzyxpzxy" />学生违纪处分管理规定》第六条之规定,同意解除<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 同学的留校察看期。 
		  <p align="right" ><div align="right">负责人签名(公章)： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div align="right">年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</div></td> 
      </tr> 
      <tr height=0> 
        <td width=52 class="Normal"></td> 
        <td width=33 class="Normal"></td> 
        <td width=84 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=48 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=36 class="Normal"></td> 
        <td width=36 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=12 class="Normal"></td> 
        <td width=75 class="Normal"></td> 
      </tr> 
    </table> 
  </div> 
  备注:1、本表一式两份,学工处、<bean:message key="lable.xsgzyxpzxy" />各留一份存档;2、后附学生个人申请书一份,交学工处存档.</div> 
	<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
    </html:form>
  </body>
</html:html>

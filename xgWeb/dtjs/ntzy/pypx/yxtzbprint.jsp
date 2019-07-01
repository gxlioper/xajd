<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div align="center"> 
  <p align=center>
  	<span style='font-size:18.0pt;font-family:华文中宋;color:black;'>
  		南通职业大学“优秀团支部”申报表
  	</span>
  </p> 
  <table class="tbstyle" width="90%" height="700px"> 
    <tr> 
      <td width="30%"> <p align=center>团支部名称</p></td> 
      <td width="70%">${rs.tzbmc } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>所在院系团总支</p></td> 
      <td>
      <p style="height:80px">
      	${rs.szyxtzz }
      </p></td> 
    </tr> 
    <tr> 
      <td><p align=center>主要<br/>工作</p></td> 
      <td>
      	<p style="height: 170px">${rs.zygz }</p>
      	<p align="center">（附1000字左右事迹介绍材料）</p>
       </td> 
    </tr> 
    <tr> 
      <td> <p align=center>所在院系<br/>党组织意见</p></td> 
      <td> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td>   
    </tr>
    <tr> 
      <td> <p align=center>团委意见</p></td> 
      <td> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td>   
    </tr>
 
  </table>
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
</div> 
</body>
</html>

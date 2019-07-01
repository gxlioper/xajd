<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.Map"%>
<html>
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style  media="print">
		.noPrin{display:none;}
	</style>
	<style type="text/css">
		.title{font-size:25px;font:'黑体';font-weight: 800;width: 100%;}		
	</style>
	<!-- end -->	
  </head>
  <body>
      <div class="title" align="center">请&nbsp;&nbsp;假&nbsp;&nbsp;条</div>
		<div>
			<span style="font-size:15px;">系：${rs.xymc }</span>
			<span style="font-size:15px;position:relative;left:150px;">专业：${rs.zymc }</span>
			<span style="font-size:15px;position:relative;left:300px;">班级：${rs.bjmc }</span>
			<span style="font-size:15px;position:relative;left:450px;">学号：${rs.xh }</span>
		</div>
		<table width="95%" class=formlist>
		  <tr align="center">
		    <td width="10%">姓&nbsp;&nbsp;名</td>
		    <td style="width:20%">${rs.xm }</td>
		    <td width="10%">性别</td>
		    <td style="width:10%">${rs.xb }</td>
		    <td width="20%">家庭住址</td>
		    <td width="30%">${rs.jtdz }</td>
		  </tr>
		  <tr>
		  	<td colspan="6" height="200px;">
		  		<span style="position: relative;top: -65px;left: 0px;">请假事由及去向：</span>
		  		<p style="text-indent: 20px;position: relative;top: -65px;left: 0px;">${rs.qjsy }</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td align="center">联系方式</td>
		  	<td colspan="5">
		  		${rs.sjhm }
				<logic:notEqual value="" name="rs" property="lxdh">
					<logic:notEqual value="" name="rs" property="sjhm">
					/
					</logic:notEqual>
				</logic:notEqual>
				${rs.lxdh }
		  	</td>
		  </tr>
		  <tr align="center">
		  	<td>请假时间</td>
		  	<td colspan="5"> 
		  	<%
		 		Map<String, String> rs = (Map<String, String>)request.getAttribute("rs"); 
		 		String kssj = rs.get("qjkssj");
		  		String jssj = rs.get("qjjssj");
		  	%>
		  		<%=kssj.substring(4,6) %>月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=kssj.substring(6,8) %>日
		  	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	<%=jssj.substring(4,6) %>月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	<%=jssj.substring(6,8) %>日
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;,共：&nbsp;&nbsp;&nbsp;${rs.qjts } 天 </td>
		  </tr>
		  
		  <tr align="left">
		  	<td rowspan="4">审批<br/>单位<br/>意见</td>
		  	<td align="center">班主任意见</td>
		  	<td colspan="4" height="100px">
		  		<span style="position: relative;left:700px;top:25px">班主任签字：<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
		  	</td>
		  </tr>
		  
		  <tr align="left">
		  	<td align="center" height="100px">学生管理办公室意见</td>
		  	<td colspan="4">
				<span style="position: relative;left:700px;top:25px">负责人签字：<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
			</td>
		  </tr>
		  
		   <tr align="left">
		  	<td align="center" height="100px">系部意见</td>
		  	<td colspan="4">
		  		<span style="position: relative;left:700px;top:25px">负责人签字：<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
			</td>
		  </tr>
		  
		   <tr align="left">
		  	<td align="center" height="100px">学生处意见</td>
		  	<td colspan="4">
		  		<span style="position: relative;left:700px;top:25px">负责人签字：<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
			</td>
		  </tr>
		 
		  <tr align="center">
		  	<td height="200px">声明</td>
		  	<td colspan="5">
				<span style="position:relative;left:-400px;top:-50px;">请假期间自行注意安全。如发生事故，概由本人负责。</span><br/>
					<span style="position:relative;left:400px;top:50px;">请假人：</span><br/>
					<span style="position:relative;left:400px;top:50px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span><br/>
			</td>
		  </tr>
		</table>
		<div align="center" class='noPrin'>
			<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
		</div>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.Map"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<html>
	<head>
		<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
		<style>
		.radic {
		
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
		</style>
	</head>
  <body>
 <div align="center"> 
<p align=center><b><span
	style='font-size:20.0pt;font-family:宋体;letter-spacing:1.0pt'>请&nbsp;&nbsp;假&nbsp;&nbsp;条</span></b></p> 
  <br/><br/>
		 <table style="font-size:15px;" width ="95%">
		 	<tr>
		 		<td>
		 			系：${rs.xymc } 专业：${rs.zymc } 班级：${rs.bjmc } 学号：${rs.xh }
		 		</td>
		 	</tr>
		 </table>
		 <table class="printtab" style="font-family:仿宋_GB2312;font-size:15px;width:95%;">
		  <tr align="center">
		    <td width="15%">姓&nbsp;&nbsp;名</td>
		    <td style="width:17%">${rs.xm }</td>
		    <td width="8%">性别</td>
		    <td style="width:10%">${rs.xb }</td>
		    <td width="15%">家庭住址</td>
		    <td width="35%">${rs.jtdz }</td>
		  </tr>
		  <tr>
		  	<td colspan="6" height="250px;" style="vertical-align: top">
		  		<p>请假事由及去向：</p>
		  		<br/><br/><br/>
		  		<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td align="center">联系方式</td>
		  	<td colspan="5">
		  		${rs.sjhm }<logic:notEqual value="" name="rs" property="lxdh">
		  		<logic:notEqual value="" name="rs" property="sjhm">/</logic:notEqual>
		  		</logic:notEqual>
		  		${rs.lxdh }
		  	</td>
		  </tr>
		  <tr align="center">
		  	<td>请假时间</td>
		  	<td colspan="5"> 
		  	<%
		 		Map<String, String> rs = (Map<String, String>)request.getAttribute("rs"); 
		 		String kssj = rs.get("kssj");
		  		String jssj = rs.get("jssj");
		  	%>
		  		<%=kssj.substring(4,6) %>月
		  		<logic:empty name="rs" property="kssj">
		  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		</logic:empty>
		  		<%=kssj.substring(6,8) %>日
		  		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	至
		  	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	
		  	 	<%=jssj.substring(4,6) %>月
		  	 	<logic:empty name="rs" property="jssj">
		  	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	</logic:empty>
		  	 	<%=jssj.substring(6,8) %>日
		  	 	<logic:empty name="rs" property="jssj">
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		</logic:empty>,共：&nbsp;&nbsp;&nbsp;${rs.sqts } 天 </td>
		  </tr>
		 
		 <logic:iterate id="qjsh" name = "qjshList" offset="0" length="1">
					<tr align="right">
						<td rowspan="${qjshlength}" align="center" height="400px">审批<br/>单位<br/>意见</td>
					  	<td align="center" > ${qjsh.gwmc }意见</td>
					  	<td colspan="4" >
					  	<p align="left">${qjsh.shyj}</p>
					  		<br><br>
				  		<p >负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
								<p >年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
						</td>
					  </tr>
			 </logic:iterate>
			 
			  <logic:iterate id="qjsh" name = "qjshList" offset="1" >
			  	 
					<tr align="right">
					  	<td align="center" height="100px"> ${qjsh.gwmc }意见</td>
					  	<td colspan="4">
					  	<p align="left">${qjsh.shyj}</p>
					  		<br><br>
				  		<p >负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
								<p >年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
						</td>
					  </tr>
			 </logic:iterate>
		
		  
		 
		  <tr align="right">
		  	<td height="200px" align="center">声明</td>
		  	<td colspan="5">
				<p align="left">请假期间自行注意安全。如发生事故，概由本人负责。</p><br/><br/><br/>
					<p >请假人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
					<p >年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p><br/>
			</td>
		  </tr>
		</table>
		
		<!-- 
		<div align="center" class='noPrin'>
			<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
		</div>
		 -->
			</div>
  </body>
</html>

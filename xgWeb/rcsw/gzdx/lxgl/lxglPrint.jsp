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
      <div class="title" align="center">贵州大学学生假期留校申请表</div>
		<div>
			<span style="font-size:15px;">学号：${rs.xh }</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size:15px;">姓名：${rs.xm }</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size:15px;">联系电话：
				${rs.sjhm }
				<logic:notEqual value="" name="rs" property="sjhm">
					<logic:notEqual value="" name="rs" property="lxdh">
					/
					</logic:notEqual>
				</logic:notEqual>
				${rs.lxdh }
			</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size:15px;">原寝室号：${rs.qsh }</span>
			<br/>
			<span style="font-size:15px;">是否吃年夜饭：${rs.sfnyf }</span>
		</div>
		<table style="align:center" class=formlist>
			<tr align="center">
			  	<td>申请留校时间</td>
			  	<td colspan="5"> 
			  	<%
			 		Map<String, String> rs = (Map<String, String>)request.getAttribute("rs"); 
			 		String kssj = rs.get("kssj");
			  		String jssj = rs.get("jssj");
			  	%>
			  		<%=kssj.substring(0,4) %>年&nbsp;&nbsp;&nbsp;&nbsp;
			  		<%=kssj.substring(4,6) %>月&nbsp;&nbsp;&nbsp;&nbsp;<%=kssj.substring(6,8) %>日
			  	 	&nbsp;&nbsp;至&nbsp;&nbsp;
			  	 	<%=jssj.substring(0,4) %>年&nbsp;&nbsp;&nbsp;&nbsp;
			  	 	<%=jssj.substring(4,6) %>月&nbsp;&nbsp;&nbsp;&nbsp;
			  	 	<%=jssj.substring(6,8) %>日
			  	 &nbsp;&nbsp;&nbsp;&nbsp;共&nbsp;&nbsp;${rs.ts } 天 </td>
			  </tr>
		  <tr>
		  	<td align="center" width="15%">申请留校原因</td>
		  	<td colspan="5" height="150px;" align="left">
		  		<p style="text-indent: 20px;position: relative;top: -40px;left: 0px;">${rs.lxyy }</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td colspan="6" height="150px;">
		  		<p style="position:relative;top: -30px;">
		  			<b>&nbsp;&nbsp;&nbsp;&nbsp;学校规定在寒假期间不赞成学生留校。我因各种原因坚持留校，必须遵守学校的一切规章制度，无条件服从学校管理。我提出书面申请并经家长认可，同时家长和本人承诺承担全部安全、管理责任。</b>
		  		</p>
		  		<p>
		  			学生签字：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> 
		  			&nbsp;&nbsp;&nbsp;&nbsp;
		  			时间：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
		  		</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td align="center">学生家长联系方式</td>
		  	<td colspan="5"  align="left">
		  		${rs.jzlxfs }
		  	</td>
		  </tr>  
		  <tr align="left">
		  	<td colspan="6" height="200px">
		  		<p style="position:relative;top: -60px;"><b>辅导员与家长确认情况：   □家长同意            □家长不同意，拒绝留校申请</b></p>
				<p style="position:relative;left:100px;top:-30px"><b>【请附学生必需留校证明材料】</b></p>
				<p style="position:relative;top: 60px;">辅导员签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		        日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                   
		  		        联系电话：</p>
		  		
		  	</td>
		  </tr>
		  
		  <tr align="left" height="200px">
		  	<td colspan="3" width="45%">
		  		<p style="position: relative;top: -50px;"><bean:message key="lable.xb" />（部）意见:<br/>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj1 }
		  		</p>
		  		<p style="position: relative;top: 63px;">签章:(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期:</p>
		  	</td>
		  	<td colspan="3">
				<p style="position: relative;top: -50px;">学生处意见:<br/>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj2 }
		  		</p>
		  		<p style="position: relative;top: 63px;">签章:(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期:</p>
			</td>
		  </tr>
		</table>
		<div style="font-size:15px;">
		学生公寓综合管理科安排意见：<br/><br/>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据实际情况、安排_________________同学留住______栋_____房间。<br/>
		学生公寓综合管理科联系电话：<br/><br/>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审批人签字（盖章）:　      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      
		年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日   
		
		</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
		</div>
  </body>
  
</html>

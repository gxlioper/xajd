<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.Map"%>
<html>
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style  media="print">
		.noPrin{display:none;}
	</style>
	<style type="text/css">
		.title{font-size:25px;font:'����';font-weight: 800;width: 100%;}		
	</style>
	<!-- end -->	
  </head>
  <body>
      <div class="title" align="center">��&nbsp;&nbsp;��&nbsp;&nbsp;��</div>
		<div>
			<span style="font-size:15px;">ϵ��${rs.xymc }</span>
			<span style="font-size:15px;position:relative;left:150px;">רҵ��${rs.zymc }</span>
			<span style="font-size:15px;position:relative;left:300px;">�༶��${rs.bjmc }</span>
			<span style="font-size:15px;position:relative;left:450px;">ѧ�ţ�${rs.xh }</span>
		</div>
		<table width="95%" class=formlist>
		  <tr align="center">
		    <td width="10%">��&nbsp;&nbsp;��</td>
		    <td style="width:20%">${rs.xm }</td>
		    <td width="10%">�Ա�</td>
		    <td style="width:10%">${rs.xb }</td>
		    <td width="20%">��ͥסַ</td>
		    <td width="30%">${rs.jtdz }</td>
		  </tr>
		  <tr>
		  	<td colspan="6" height="200px;">
		  		<span style="position: relative;top: -65px;left: 0px;">������ɼ�ȥ��</span>
		  		<p style="text-indent: 20px;position: relative;top: -65px;left: 0px;">${rs.qjsy }</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td align="center">��ϵ��ʽ</td>
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
		  	<td>���ʱ��</td>
		  	<td colspan="5"> 
		  	<%
		 		Map<String, String> rs = (Map<String, String>)request.getAttribute("rs"); 
		 		String kssj = rs.get("qjkssj");
		  		String jssj = rs.get("qjjssj");
		  	%>
		  		<%=kssj.substring(4,6) %>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=kssj.substring(6,8) %>��
		  	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	<%=jssj.substring(4,6) %>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	<%=jssj.substring(6,8) %>��
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;,����&nbsp;&nbsp;&nbsp;${rs.qjts } �� </td>
		  </tr>
		  
		  <tr align="left">
		  	<td rowspan="4">����<br/>��λ<br/>���</td>
		  	<td align="center">���������</td>
		  	<td colspan="4" height="100px">
		  		<span style="position: relative;left:700px;top:25px">������ǩ�֣�<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
		  	</td>
		  </tr>
		  
		  <tr align="left">
		  	<td align="center" height="100px">ѧ������칫�����</td>
		  	<td colspan="4">
				<span style="position: relative;left:700px;top:25px">������ǩ�֣�<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
			</td>
		  </tr>
		  
		   <tr align="left">
		  	<td align="center" height="100px">ϵ�����</td>
		  	<td colspan="4">
		  		<span style="position: relative;left:700px;top:25px">������ǩ�֣�<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
			</td>
		  </tr>
		  
		   <tr align="left">
		  	<td align="center" height="100px">ѧ�������</td>
		  	<td colspan="4">
		  		<span style="position: relative;left:700px;top:25px">������ǩ�֣�<br/></span>
				<span style="position: relative;left:700px;top:25px">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
			</td>
		  </tr>
		 
		  <tr align="center">
		  	<td height="200px">����</td>
		  	<td colspan="5">
				<span style="position:relative;left:-400px;top:-50px;">����ڼ�����ע�ⰲȫ���緢���¹ʣ����ɱ��˸���</span><br/>
					<span style="position:relative;left:400px;top:50px;">����ˣ�</span><br/>
					<span style="position:relative;left:400px;top:50px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span><br/>
			</td>
		  </tr>
		</table>
		<div align="center" class='noPrin'>
			<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button> 
		</div>
  </body>
</html>

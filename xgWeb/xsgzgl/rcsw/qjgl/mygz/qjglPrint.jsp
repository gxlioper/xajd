<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.Map"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<html>
	<head>
		<!-- ��ӡ�ؼ�begin -->
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
	style='font-size:20.0pt;font-family:����;letter-spacing:1.0pt'>��&nbsp;&nbsp;��&nbsp;&nbsp;��</span></b></p> 
  <br/><br/>
		 <table style="font-size:15px;" width ="95%">
		 	<tr>
		 		<td>
		 			ϵ��${rs.xymc } רҵ��${rs.zymc } �༶��${rs.bjmc } ѧ�ţ�${rs.xh }
		 		</td>
		 	</tr>
		 </table>
		 <table class="printtab" style="font-family:����_GB2312;font-size:15px;width:95%;">
		  <tr align="center">
		    <td width="15%">��&nbsp;&nbsp;��</td>
		    <td style="width:17%">${rs.xm }</td>
		    <td width="8%">�Ա�</td>
		    <td style="width:10%">${rs.xb }</td>
		    <td width="15%">��ͥסַ</td>
		    <td width="35%">${rs.jtdz }</td>
		  </tr>
		  <tr>
		  	<td colspan="6" height="250px;" style="vertical-align: top">
		  		<p>������ɼ�ȥ��</p>
		  		<br/><br/><br/>
		  		<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td align="center">��ϵ��ʽ</td>
		  	<td colspan="5">
		  		${rs.sjhm }<logic:notEqual value="" name="rs" property="lxdh">
		  		<logic:notEqual value="" name="rs" property="sjhm">/</logic:notEqual>
		  		</logic:notEqual>
		  		${rs.lxdh }
		  	</td>
		  </tr>
		  <tr align="center">
		  	<td>���ʱ��</td>
		  	<td colspan="5"> 
		  	<%
		 		Map<String, String> rs = (Map<String, String>)request.getAttribute("rs"); 
		 		String kssj = rs.get("kssj");
		  		String jssj = rs.get("jssj");
		  	%>
		  		<%=kssj.substring(4,6) %>��
		  		<logic:empty name="rs" property="kssj">
		  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		</logic:empty>
		  		<%=kssj.substring(6,8) %>��
		  		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	��
		  	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	
		  	 	<%=jssj.substring(4,6) %>��
		  	 	<logic:empty name="rs" property="jssj">
		  	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	 	</logic:empty>
		  	 	<%=jssj.substring(6,8) %>��
		  	 	<logic:empty name="rs" property="jssj">
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		</logic:empty>,����&nbsp;&nbsp;&nbsp;${rs.sqts } �� </td>
		  </tr>
		 
		 <logic:iterate id="qjsh" name = "qjshList" offset="0" length="1">
					<tr align="right">
						<td rowspan="${qjshlength}" align="center" height="400px">����<br/>��λ<br/>���</td>
					  	<td align="center" > ${qjsh.gwmc }���</td>
					  	<td colspan="4" >
					  	<p align="left">${qjsh.shyj}</p>
					  		<br><br>
				  		<p >������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
								<p >��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
						</td>
					  </tr>
			 </logic:iterate>
			 
			  <logic:iterate id="qjsh" name = "qjshList" offset="1" >
			  	 
					<tr align="right">
					  	<td align="center" height="100px"> ${qjsh.gwmc }���</td>
					  	<td colspan="4">
					  	<p align="left">${qjsh.shyj}</p>
					  		<br><br>
				  		<p >������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
								<p >��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
						</td>
					  </tr>
			 </logic:iterate>
		
		  
		 
		  <tr align="right">
		  	<td height="200px" align="center">����</td>
		  	<td colspan="5">
				<p align="left">����ڼ�����ע�ⰲȫ���緢���¹ʣ����ɱ��˸���</p><br/><br/><br/>
					<p >����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
					<p >��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p><br/>
			</td>
		  </tr>
		</table>
		
		<!-- 
		<div align="center" class='noPrin'>
			<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button> 
		</div>
		 -->
			</div>
  </body>
</html>

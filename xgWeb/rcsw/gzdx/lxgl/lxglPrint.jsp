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
      <div class="title" align="center">���ݴ�ѧѧ��������У�����</div>
		<div>
			<span style="font-size:15px;">ѧ�ţ�${rs.xh }</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size:15px;">������${rs.xm }</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size:15px;">��ϵ�绰��
				${rs.sjhm }
				<logic:notEqual value="" name="rs" property="sjhm">
					<logic:notEqual value="" name="rs" property="lxdh">
					/
					</logic:notEqual>
				</logic:notEqual>
				${rs.lxdh }
			</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size:15px;">ԭ���Һţ�${rs.qsh }</span>
			<br/>
			<span style="font-size:15px;">�Ƿ����ҹ����${rs.sfnyf }</span>
		</div>
		<table style="align:center" class=formlist>
			<tr align="center">
			  	<td>������Уʱ��</td>
			  	<td colspan="5"> 
			  	<%
			 		Map<String, String> rs = (Map<String, String>)request.getAttribute("rs"); 
			 		String kssj = rs.get("kssj");
			  		String jssj = rs.get("jssj");
			  	%>
			  		<%=kssj.substring(0,4) %>��&nbsp;&nbsp;&nbsp;&nbsp;
			  		<%=kssj.substring(4,6) %>��&nbsp;&nbsp;&nbsp;&nbsp;<%=kssj.substring(6,8) %>��
			  	 	&nbsp;&nbsp;��&nbsp;&nbsp;
			  	 	<%=jssj.substring(0,4) %>��&nbsp;&nbsp;&nbsp;&nbsp;
			  	 	<%=jssj.substring(4,6) %>��&nbsp;&nbsp;&nbsp;&nbsp;
			  	 	<%=jssj.substring(6,8) %>��
			  	 &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;${rs.ts } �� </td>
			  </tr>
		  <tr>
		  	<td align="center" width="15%">������Уԭ��</td>
		  	<td colspan="5" height="150px;" align="left">
		  		<p style="text-indent: 20px;position: relative;top: -40px;left: 0px;">${rs.lxyy }</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td colspan="6" height="150px;">
		  		<p style="position:relative;top: -30px;">
		  			<b>&nbsp;&nbsp;&nbsp;&nbsp;ѧУ�涨�ں����ڼ䲻�޳�ѧ����У���������ԭ������У����������ѧУ��һ�й����ƶȣ�����������ѧУ����������������벢���ҳ��Ͽɣ�ͬʱ�ҳ��ͱ��˳�ŵ�е�ȫ����ȫ���������Ρ�</b>
		  		</p>
		  		<p>
		  			ѧ��ǩ�֣�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> 
		  			&nbsp;&nbsp;&nbsp;&nbsp;
		  			ʱ�䣺<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
		  		</p>
		  	</td>
		  </tr>
		  <tr>
		  	<td align="center">ѧ���ҳ���ϵ��ʽ</td>
		  	<td colspan="5"  align="left">
		  		${rs.jzlxfs }
		  	</td>
		  </tr>  
		  <tr align="left">
		  	<td colspan="6" height="200px">
		  		<p style="position:relative;top: -60px;"><b>����Ա��ҳ�ȷ�������   ���ҳ�ͬ��            ���ҳ���ͬ�⣬�ܾ���У����</b></p>
				<p style="position:relative;left:100px;top:-30px"><b>���븽ѧ��������У֤�����ϡ�</b></p>
				<p style="position:relative;top: 60px;">����Աǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		        ���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                   
		  		        ��ϵ�绰��</p>
		  		
		  	</td>
		  </tr>
		  
		  <tr align="left" height="200px">
		  	<td colspan="3" width="45%">
		  		<p style="position: relative;top: -50px;"><bean:message key="lable.xb" />���������:<br/>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj1 }
		  		</p>
		  		<p style="position: relative;top: 63px;">ǩ��:(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����:</p>
		  	</td>
		  	<td colspan="3">
				<p style="position: relative;top: -50px;">ѧ�������:<br/>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj2 }
		  		</p>
		  		<p style="position: relative;top: 63px;">ǩ��:(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����:</p>
			</td>
		  </tr>
		</table>
		<div style="font-size:15px;">
		ѧ����Ԣ�ۺϹ���ư��������<br/><br/>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ʵ�����������_________________ͬѧ��ס______��_____���䡣<br/>
		ѧ����Ԣ�ۺϹ������ϵ�绰��<br/><br/>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ǩ�֣����£�:��      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      
		��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��   
		
		</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
		    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button> 
		</div>
  </body>
  
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		
		<script	type="text/javascript">
		</script>
	</head>
	<body>
		<div style='width:100%;'>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr >
						<th colspan="4">
							<span>����¥������Ϣͼ</span>
						</th>
					</tr>
				</thead>
			</table>
			<table width="100%" >
				<tr>
					<logic:iterate id="ssl" name="SslInfo" indexId="i">
						<%
						    boolean flag=false;
							if(i%2==0 && i != 0){
						 %>
						 	</tr>
							<tr>
						 <%} %>
						<td valign=bottom>
							<table width="99%" border="1">
								<tr style="height: 40px">
<%--									��--%>
									<logic:equal value="��" name="ssl" property="ldxb">
								   	 <td width='40%'colspan="2" align="center" bgcolor="#0070C0" >
								    </logic:equal>
<%--									Ů--%>
									<logic:equal value="Ů" name="ssl" property="ldxb">
								   	 <td width='40%'colspan="2" align="center" bgcolor="#FF0000" >
								    </logic:equal>
<%--									��ס--%>
									<logic:equal value="��ס" name="ssl" property="ldxb">
								   	 	<td width='40%'colspan="2" align="center" bgcolor="#FFFF00" >
								    </logic:equal>
								    	<font style="font-size: 20px">${ssl.ldmc }(${ssl.ldxb })</font>
								    </td>
								  </tr>
								  <tr style="height: 40px">
								    <td width='10%'rowspan=${ssl.hbh } bgcolor="#92D050">
								   
								    	����:${ssl.cs }��${ssl.qss }������${ssl.cw }����λ</br>
								    	��ס�ʣ�${ssl.rzl }%</br>�մ�λ��${ssl.kcw }
								    </td>
								    <logic:iterate id="sslc" name="SslcInfo" >
								    	<logic:equal value="${ssl.lddm}" name="sslc" property="lddm">
								    	<%
											if(flag){
										 %>
										 	<tr style="height: 40px">
										 <%
											}  flag=true;%>
											<td width='30%'bgcolor="#F9C499">${sslc.ch }��:${sslc.ss}������${sslc.cw}����λ&nbsp;&nbsp;
												<logic:equal value="��" name="sslc" property="qsxb">
													<font style="color:#4F8AB4">�մ�λ${sslc.kcw}��(${sslc.qsxb})</font>
												</logic:equal>
												<logic:equal value="Ů" name="sslc" property="qsxb">
													<font style="color:#FF0000">�մ�λ${sslc.kcw}��(${sslc.qsxb})</font>
												</logic:equal>
											</td>
											</tr>
										 </logic:equal>
							    	</logic:iterate>
							</table>
						</td>
					</logic:iterate>
				</tr>
			</table>
		</div>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>


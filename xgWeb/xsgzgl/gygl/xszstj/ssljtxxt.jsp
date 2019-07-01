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
							<span>宿舍楼具体信息图</span>
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
<%--									男--%>
									<logic:equal value="男" name="ssl" property="ldxb">
								   	 <td width='40%'colspan="2" align="center" bgcolor="#0070C0" >
								    </logic:equal>
<%--									女--%>
									<logic:equal value="女" name="ssl" property="ldxb">
								   	 <td width='40%'colspan="2" align="center" bgcolor="#FF0000" >
								    </logic:equal>
<%--									混住--%>
									<logic:equal value="混住" name="ssl" property="ldxb">
								   	 	<td width='40%'colspan="2" align="center" bgcolor="#FFFF00" >
								    </logic:equal>
								    	<font style="font-size: 20px">${ssl.ldmc }(${ssl.ldxb })</font>
								    </td>
								  </tr>
								  <tr style="height: 40px">
								    <td width='10%'rowspan=${ssl.hbh } bgcolor="#92D050">
								   
								    	容量:${ssl.cs }层${ssl.qss }个宿舍${ssl.cw }个床位</br>
								    	入住率：${ssl.rzl }%</br>空床位：${ssl.kcw }
								    </td>
								    <logic:iterate id="sslc" name="SslcInfo" >
								    	<logic:equal value="${ssl.lddm}" name="sslc" property="lddm">
								    	<%
											if(flag){
										 %>
										 	<tr style="height: 40px">
										 <%
											}  flag=true;%>
											<td width='30%'bgcolor="#F9C499">${sslc.ch }层:${sslc.ss}个宿舍${sslc.cw}个床位&nbsp;&nbsp;
												<logic:equal value="男" name="sslc" property="qsxb">
													<font style="color:#4F8AB4">空床位${sslc.kcw}个(${sslc.qsxb})</font>
												</logic:equal>
												<logic:equal value="女" name="sslc" property="qsxb">
													<font style="color:#FF0000">空床位${sslc.kcw}个(${sslc.qsxb})</font>
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


<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<style media="print">
		.noprint{
			display:none
		}
		.print{
			display:block
		}
	</style>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>		
<body>	
		<center>
			<html:form action="/pjpyLsxy" method="post">
			<p align="left">���� 1 ��</p>
			<p align="center"> 
			<h3 align="center">${xxmc}${rs.xn}ѧ�꽱ѧ�������</h3></p>
			<p align="center"><strong>&nbsp; </strong></p>
			<p align="left">
			����<bean:message key="lable.xb" />��${rs.xymc} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			רҵ��${rs.zymc } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			�༶�� ${rs.bjmc}</p>
			
			<div align="left">
			  <table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			    <tr>
			      <td width="55"><p align="center">���� </p></td>
			      <td width="72" colspan="2"><p align="center">${rs.xm } </p></td>
			      <td width="72" colspan="2"><p align="center">�Ա� </p></td>
			      <td width="48" colspan="2"><p align="center">${rs.xb } </p></td>
			      <td width="72" colspan="3"><p align="center">���� </p>
			          <p align="center">���� </p></td>
			      <td width="60" colspan="3"><p align="center">${rs.csrq } </p></td>
			      <td width="60" colspan="2"><p align="center">�� �� </p></td>
			      <td width="60" colspan="3"><p align="center">${rs.jg } </p></td>
			      <td width="96" colspan="3"><p align="center">����Ա </p></td>
			      <td width="36"><p align="center">${rs.zzmmmc }</p></td>
			    </tr>
			    <tr>
			      <td width="55"><p align="center">�༶���� </p></td>
			      <td width="72" colspan="2"><p align="center">${rs.bjrs } </p></td>
			      <td width="72" colspan="2"><p align="center">�ۺϲ������� </p></td>
			      <td width="48" colspan="2"><p align="center">${rs.zfpm } </p></td>
			      <td width="72" colspan="3"><p align="center">ѧϰ�ɼ����� </p></td>
			      <td width="60" colspan="3"><p align="center">${rs.cjpm } </p></td>
			      <td width="60" colspan="2"><p align="center">�� </p>
			          <p align="center">��� </p></td>
			      <td width="60" colspan="3"><p align="center">${jxjRs.jfqk} </p></td>
			      <td width="96" colspan="3"><p align="center">��ѧ�����ʽ����ɼ� </p></td>
			      <td width="36"><p align="center">${rs.tzjkcj }</p></td>
			    </tr>
			    <tr>
			      <td width="127" colspan="3"><p align="center">�Ƿ���Υ�� </p>
			          <p align="center">�ܴ��� </p></td>
			      <td width="192" colspan="7"><p align="center">${rs.sfwj} </p></td>
			      <td width="120" colspan="5"><p align="center">�Ƿ��п��� </p></td>
			      <td width="192" colspan="7"><p align="center">&nbsp; </p></td>
			    </tr>
			    <tr>
			      <td width="55" rowspan="5"><p align="center">һ </p>
			          <p align="center">ѧ </p>
			          <p align="center">�� </p>
			          <p align="center">�� </p>
			          <p align="center">�� </p>
			          <p align="center">�� </p>
			          <p align="center">�� </p>
			          <p align="center">&nbsp; </p></td>
			      <td width="72" height="30" colspan="2"><p align="center">&nbsp; </p></td>
			      <td colspan="20" rowspan="5">
					<table class="tbstyle" width="100%" height="100%">
					<tr height="30">
						<td colspan="7">
							���Կ�Ŀ��${kscj1Len} �ţ�
						</td>
						<td colspan="7">
							�����Ŀ��${kccj1Len} �ţ���ʵϰ 
						</td>						
					</tr>
					<tr height="120">
						<logic:iterate id="s" name="kscjRs1" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kscjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">							
						</td>
						<%} %>
						</logic:greaterEqual>
		
						<logic:iterate id="s" name="kccjRs1" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>	
						</logic:greaterEqual>		
					</tr>
					<tr height="40">
						<logic:iterate id="s" name="kscjRs1" length="7">
						<td width="60px">
							${s.cj}
						</td>
						</logic:iterate>

						<logic:greaterEqual value="0" name="kscjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>		
						</logic:greaterEqual>

						<logic:iterate id="s" name="kccjRs1" length="7">
						<td width="60px">
							${s.cj }
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>			
						</logic:greaterEqual>		
					</tr>
					<tr height="120">
						<logic:iterate id="s" name="kscjRs2" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>

						<logic:greaterEqual value="0" name="kscjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>		
						</logic:greaterEqual>
						<logic:iterate id="s" name="kccjRs2" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>			
						</logic:greaterEqual>
					</tr>
					<tr height="40">
						<logic:iterate id="s" name="kscjRs2" length="7">
						<td width="60px">
							${s.cj}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kscjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>	
						</logic:greaterEqual>	
						<logic:iterate id="s" name="kccjRs2" length="7">
						<td width="60px">
							${s.cj}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>
						</logic:greaterEqual>						
					</tr>
					</table>
				</td>			     
			    </tr>
			    <tr>
			      <td width="36" rowspan="2"><p align="center">��һѧ�� </p></td>
			      <td width="36" height="120"><p align="center">�� </p>
			          <p align="center">&nbsp; </p>
			          <p align="center">Ŀ </p></td>
			      
			    </tr>
			    <tr>
			      <td width="36" height="40"><p align="center">�ɼ� </p></td>
			     
			    </tr>
			    <tr>
			      <td width="36" rowspan="2"><p align="center">�ڶ�ѧ�� </p></td>
			      <td width="36" height="120"><p align="center">�� </p>
			          <p align="center">&nbsp; </p>
			      <p align="center">Ŀ </p></td>
			      
			    </tr>
			    <tr>
			      <td width="36" height="40"><p align="center">�ɼ� </p></td>
			      
			    </tr>
			    <tr>
			      <td width="55" height="70">		
					<p align="center">�༶��� </p>
				  </td>
			      <td colspan="21">
						<p>
							<logic:notEmpty name="jxjRs" property="jxjmc">
								�Ƽ�Ϊ ${jxjRs.jxjmc}
							</logic:notEmpty>
							<logic:empty name="jxjRs" property="jxjmc">
								�Ƽ�Ϊ  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�Ƚ�ѧ�� 
							</logic:empty>
							
						</p>
			          <p align="right" style="width:100%"> 
						������ǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
				 </td>
			    </tr>
			    <tr>
			      <td width="55" height="70"><p align="center"><bean:message key="lable.xb" />��� </p></td>
			      <td colspan="21" valign="top" >
						<p>${rs.jxjRs.xyyj}</p>
						<p>&nbsp;</p>
						<p style="width:100%" align="right">						   
							�쵼ǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
			          <p align="right">
						
							<bean:message key="lable.xb" />���£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
				</td>
			    </tr>
			    <tr>
			      <td width="55" height="70"><p align="center">ѧУ��� </p></td>
			      <td width="577" colspan="21"><p align="center">${jxjRs.xxyj} </p></td>
			    </tr>
			  </table>
			  ע���˱�һʽ���ݣ�ѧ������һ�ݣ�ѧ����һ�ݡ�</div>	
			<div class="noprint" align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
			</html:form>
        </center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
</body>


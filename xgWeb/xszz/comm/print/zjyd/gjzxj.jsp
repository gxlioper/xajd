<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="��ѧ��.files/editdata.mso">
<title>������ѧ������������</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body bgcolor="#FFFFFF" lang=ZH-CN> 
<div align="center"> 
  <p align=center><b><span
style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>��${rs.xn }ѧ�꣩������ѧ������������</span></b></p> 
  <div align="left"><b>ѧУ��</b><b>${xxmc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>Ժϵ��</b><b>${rs.xymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>רҵ��</b><b>${rs.zymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>�༶��</b><b>${rs.bjmc }</b></div> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="tbstyle" border="0" align="center" style="width: 100%"> 
    <tr align="center" > 
      <th width=7% rowspan=4 height="35px"><p align=center>��<br/>��<br/>��<br/>��</p></th> 
      <td width=11% height="35px"> <p align=center>����</p></td> 
      <td width=20% colspan="4" height="35px">${rs.xm } </td> 
      <td width=12% colspan="4" height="35px"> <p align=center>�Ա�</p></td> 
      <td width=12% colspan="2" height="35px">${rs.xb } </td> 
      <td width=12% colspan="3" height="35px"> <p align=center>��������</p></td> 
      <td width=16% colspan="5" height="35px">${rs.csrq } </td> 
    </tr> 
    <tr align="center" height="35px"> 
      <td> <p align=center>ѧ��</p></td> 
      <td colspan="4">${rs.xh } </td> 
      <td colspan="4"> <p align=center>����</p></td> 
      <td colspan="2">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>��ѧʱ��</p></td> 
      <td colspan="5">${rs.rxrq } </td> 
    </tr> 
    <tr align="center" height="35px"> 
      <td> <p>������ò</p></td> 
      <td colspan="4">${rs.zzmmmc } </td> 
      <td colspan="4"> <p align=center>��ϵ�绰</p></td> 
      <td colspan="10">${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="35px"> 
      <td> <p>���֤��</p></td>
      <td id="s0" width="4.5%">&nbsp; </td>  
      <td id="s1" width="4.5%">&nbsp; </td> 
      <td id="s2" width="4.5%">&nbsp; </td> 
      <td id="s3" width="4.5%">&nbsp; </td> 
      <td id="s4" width="4.5%">&nbsp; </td> 
      <td id="s5" width="4.5%">&nbsp; </td> 
      <td id="s6" width="4.5%">&nbsp; </td> 
      <td id="s7" width="4.5%">&nbsp; </td> 
      <td id="s8" width="4.5%">&nbsp; </td> 
      <td id="s9" width="4.5%">&nbsp; </td> 
      <td id="s10" width="4.5%">&nbsp; </td> 
      <td id="s11" width="4.5%">&nbsp; </td> 
      <td id="s12" width="4.5%">&nbsp; </td> 
      <td id="s13" width="4.5%">&nbsp; </td> 
      <td id="s14" width="4.5%">&nbsp; </td> 
      <td id="s15" width="4.5%">&nbsp; </td> 
      <td id="s16" width="4.5%">&nbsp; </td> 
      <td id="s17" width="4.5%">&nbsp; </td> 
   	</tr>
   	<tr align="center" height="35px">
   		<td width=7% rowspan=4 ><b>��<br/>ͥ<br/>��<br/>��<br/>��<br/>��</b></td> 
   		<td>��ͥ����</td>
   		<td colspan="10">
   			A��<logic:equal value="����" property="jthk" name="rs">
				��
			</logic:equal>����
			&nbsp;&nbsp;
			B��<logic:equal value="ũ��" property="jthk" name="rs">
				��
			</logic:equal>
			ũ��
   		</td>
   		<td colspan="3">������Դ</td>
   		<td colspan="5">${rs.srly }</td>
   	</tr>
   	<tr align="center" height="35px">
   		<td>��ͥ����<br/>����</td>
   		<td colspan="10">${rs.jtyzsr }</td>
   		<td colspan="3">��ͥ�˿�<br/>����</td>
   		<td colspan="5">${rs.jtrs }</td>
   	</tr>
   	<tr align="center" height="35px">
   		<td>��ͥסַ</td>
   		<td colspan="10">${rs.jtdz }</td>
   		<td colspan="3">��������</td>
   		<td colspan="5">${rs.jtyb }</td>
   	</tr>
   	<tr align="center" height="35px">
   		<td>�϶����</td>
   		<td colspan="18">
	   		 A����ͥ�����ر�����
	   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    
	   		 B����ͥ����һ������
   		 </td>
   	</tr>
   	
   <logic:lessEqual value="3" name="cyNum">
	<tr style="height:22px" height="35px">
		<th width="7%" rowspan="${cyNum+6}">
			<div align="center">
				��<br>
				ͥ<br>
				��<br>
				Ա<br>
				��<br>
				��
			</div>
		</th>
		<td width="10%" height="35px">
			<div align="center">
				����
			</div>
		</td>
		<td width="10%" colspan="5">
			<div align="center">
				����
			</div>
		</td>
		<td width="10%" colspan="3">
			<div align="center">
				�뱾�˹�ϵ
			</div>
		</td>
		<td width="10%" colspan="10">
			<div align="center">
				������ѧϰ��λ
			</div>
		</td>
	</tr>
	<logic:iterate name="cyList" id="cy">
		<tr height="35px">
			<td width="10%">
				<div align="center">
					${cy.cyxm }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="5">
				<div align="center">
					${cy.cynl }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="3">
				<div align="center">
					${cy.mc }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="10">
				<div align="center">
					${cy.cygzdw }&nbsp;&nbsp;
				</div>
			</td>
		</tr>
	</logic:iterate>
	<%	
		for(int i=0;i<5;i++){
	%>
		<tr height="35px">
			<td width="10%">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="5">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="3">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="10">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
		</tr>
	<%} %>
	</logic:lessEqual>
   	
   	<logic:greaterThan value="3" name="cyNum">
	<tr style="height:22px">
		<th width="7%" rowspan="${cyNum+1 }">
			<div align="center">
				��<br>
				ͥ<br>
				��<br>
				Ա<br>
				��<br>
				��
			</div>
		</th>
		<td width="10%">
			<div align="center">
				����
			</div>
		</td>
		<td width="10%" colspan="5">
			<div align="center">
				����
			</div>
		</td>
		<td width="10%" colspan="3">
			<div align="center">
				�뱾�˹�ϵ
			</div>
		</td>
		<td width="10%" colspan="10">
			<div align="center">
				������ѧϰ��λ
			</div>
		</td>
	</tr>
	<logic:iterate name="cyList" id="cy">
		<tr>
			<td width="10%">
				<div align="center">
					${cy.cyxm }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="5">
				<div align="center">
					${cy.cynl }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="3">
				<div align="center">
					${cy.mc }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="10">
				<div align="center">
					${cy.cygzdw }&nbsp;&nbsp;
				</div>
			</td>
		</tr>
	</logic:iterate>
	</logic:greaterThan>
    <tr height="110px"> 
      <th width="7%"> <p align=center>
      	        ����<br/> 
		        ����<br/> 
		</p></th> 
     	 <td colspan="19">
      		<p style="height: 50px">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }</p> 
            <p align=right>������ǩ������ǩ����
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p>
      </td> 
    </tr> 
       <tr height="110px">
         <th>
           <p align=center>Ժ<br/>(ϵ)<br/>��<br/>��</p>
         </th>
         <td colspan="19">
      		<p style="height: 50px">&nbsp;&nbsp;&nbsp;&nbsp; </p> 
            <p align=right>��Ժϵ���£�
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p>
     	 </td> 
     	</tr>
       <tr height="110px">
         <th width="7%">
           <p align=center>ѧ<br/>У<br/>��<br/>��</p></th>
        <td colspan="19">
      		<p style="height: 50px">&nbsp;&nbsp;&nbsp;&nbsp;</p> 
            <p align=right>��ѧУ���£�
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p>
     	 </td> 
       </tr>
     </table>
     <br/>
     <div align="right">�Ʊ��㽭ʡѧ�������������ġ�2010���</div>
      	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
    <script type="text/javascript">
    	var sfzh = $('sfzh').value;
    	for(var i=0;i<sfzh.length;i++){
    	var id = "s" + i;
    	var sfzhs = sfzh.substring(i,i+1);
    	if($(id)){
    		$(id).innerHTML = sfzhs; 
    	}
    	}
    
    </script>
    </div>
    <p>&nbsp;</p>
</body>
</html>

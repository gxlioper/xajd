<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="��ѧ��.files/editdata.mso">
<title>���ҽ�ѧ������������</title>
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
style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>(${rs.xn}ѧ��)���ҽ�ѧ������������</span></b></p> 
  
  <p align=center><b>ѧУ��</b>${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Ժϵ��</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>ѧ�ţ�</b>${rs.xh }</p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="printstyle" border="0" align="center" style="width: 90%"> 
    <tr height="50px"  align=center> 
      <th  width="10%" rowspan=4><p align=center>����<br/>���</p></th> 
      <td width=14%> <p align=center>����</p></td> 
      <td width=20% colspan="5">${rs.xm } </td> 
      <td width=12% colspan="3"> <p align=center>�Ա�</p></td> 
      <td width=12% colspan="3">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>��������</p></td> 
      <td width=16% colspan="4">${rs.csrq } </td> 
    </tr> 
    <tr align="center" height="50px"> 
      <td> <p align=center>������ò</p></td> 
      <td colspan="5">${rs.zzmmmc } </td> 
      <td colspan="3"> <p align=center>����</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>��ѧʱ��</p></td> 
      <td colspan="4">${rs.rxrq } </td> 
    </tr> 
    <tr align="center" height="50px"> 
      <td> <p>רҵ</p></td> 
      <td colspan="5">${rs.zymc }</td> 
      <td colspan="3"> <p>ѧ��</p></td> 
      <td colspan="3">${rs.xz } </td> 
      <td colspan="3"> <p align=center>��ϵ�绰</p></td> 
      <td colspan="4">${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="50px"> 
      <td> <p>���֤��</p></td>
      <td id="s0">&nbsp; </td>  
      <td id="s1">&nbsp; </td> 
      <td id="s2">&nbsp; </td> 
      <td id="s3">&nbsp; </td> 
      <td id="s4">&nbsp; </td> 
      <td id="s5">&nbsp; </td> 
      <td id="s6">&nbsp; </td> 
      <td id="s7">&nbsp; </td> 
      <td id="s8">&nbsp; </td> 
      <td id="s9">&nbsp; </td> 
      <td id="s10">&nbsp; </td> 
      <td id="s11">&nbsp; </td> 
      <td id="s12">&nbsp; </td> 
      <td id="s13">&nbsp; </td> 
      <td id="s14">&nbsp; </td> 
      <td id="s15">&nbsp; </td> 
      <td id="s16">&nbsp; </td> 
      <td id="s17">&nbsp; </td> 
   	</tr>
    
    <tr  align="center" height="50px"> 
      <th rowspan="2">
      	<p align=center>ѧϰ<br/>���</p> 
	  </th> 
      <td colspan="10"> <p>�ɼ�������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>/<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> ������/��������</p> </td>
      <td colspan="9"> <p>ʵ���ۺϿ����������ǡ������</p></td> 
    </tr> 
    <tr  align="center" height="50px"> 
      <td colspan="10"> <p>���޿�<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>�ţ����м�������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��</p> </td>
      <td colspan="9"> <p>���ǣ�������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>/<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>������/��������</p></td> 
    </tr> 
    
    <tr  align="center" height="50px"> 
      <th rowspan=5> <p align=center>��ѧ<br/>�ڼ�<br/>��Ҫ<br/>��<br/>���</p> 
      </th> 
      <td colspan="2">����</td>
      <td colspan="10">��������</td>
      <td colspan="7">�佱��λ</td>
    </tr>
    <%
    for(int i=0;i<4;i++){
    %>
    <tr  align="center" height="50px">  
      <td colspan="2">&nbsp;</td>
      <td colspan="10">&nbsp;</td>
      <td colspan="7">&nbsp;</td>
    </tr> 
    <%
    }
    %>
    <tr height="350px"> 
      <th > <p align=center >
      	        ����<br/>
		        ����<br/> 
		        ��200�֣�
		        <br/></p></th> 
     	 <td colspan="19" style="vertical-align: bottom" align=right> ������ǩ��(��ǩ)��&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
            <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
      </td> 
    </tr> 
    </table>
    <br/><br/>
     <table  class="printstyle" border="0" align="center" style="width: 90%"> 
       <tr  height="300px">
         <th  width="10%" >
           <p align=center>�Ƽ�<br/>����<br/>(100��)</p>
         </th>
         <td colspan="19" >
           <p style="height: 220px">${rs.shzt1yj } </p>
           <p align=right style="vertical-align: bottom"> �Ƽ��ˣ�����Ա������Σ�ǩ����&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
           <p align=right style="vertical-align: bottom">��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p></td>
     	  
     	</tr>
       <tr  height="300px">
         <th>
           <p align=center>Ժ<br/>��ϵ��<br/>��<br/>��</p></th>
           <td colspan="19">
           <p style="height: 220px">${rs.shzt2yj }</p>
           <p align=right style="vertical-align: bottom">&nbsp;&nbsp;&nbsp;&nbsp; Ժϵ����ѧ�������쵼ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
           <p align="right" style="vertical-align: bottom">��Ժϵ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right style="vertical-align: bottom">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p></td>
       </tr>
       
       <tr  height="300px">
         <th><p align=center>ѧ<br/>У<br/>��<br/>��</p>
	   </th>
       <td colspan="19">
			
	         <p>&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>�������գ������飬�ֱ�����׼��ͬѧ��ù��ҽ�ѧ��</p>
			 <p style="height: 110px">${rs.shzt3yj}</p>
			 <p align="right" style="vertical-align: bottom">��ѧУ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	         <p align="right" style="vertical-align: bottom">��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p></td>
	      	
	       </tr>
       </table>
      </div>
      
      
      
     	<p align="right">�Ʊ�ȫ��ѧ�������������ġ�2010���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
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

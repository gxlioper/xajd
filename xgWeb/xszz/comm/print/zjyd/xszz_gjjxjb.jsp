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
style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>��${rs.xn }ѧ�꣩���ҽ�ѧ������������</span></b></p> 
  <br/><br/>
  <div align="left"><b>ѧУ��</b><b>${xxmc }</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<b>Ժϵ��</b><b>${rs.xymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 		<b>ѧ�ţ�</b><b>${rs.xh }</b></div> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="tbstyle" border="0" align="center" style="width: 100%;"> 
    <tr align="center" height="40"> 
      <th width=14% rowspan=4><p align=center>����<br/>���</p></th> 
      <td width=14%> <p align=center>����</p></td> 
      <td width=20% colspan="4">${rs.xm } </td> 
      <td width=12% colspan="2"> <p align=center>�Ա�</p></td> 
      <td width=12% colspan="3">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>��������</p></td> 
      <td width=16% colspan="6">${rs.csrq } </td> 
    </tr> 
    <tr align="center" height="40"> 
      <td> <p align=center>������ò</p></td> 
      <td colspan="4">${rs.zzmmmc } </td> 
      <td colspan="2"> <p align=center>����</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>��ѧʱ��</p></td> 
      <td colspan="6">${rs.rxrq } </td> 
    </tr> 
    <tr align="center" height="40"> 
      <td> <p>רҵ</p></td> 
      <td colspan="4">${rs.zymc } </td> 
      <td colspan="2"> <p align=center>ѧ��</p></td> 
      <td colspan="3">${rs.xz } </td> 
      <td colspan="3"> <p align=center>��ϵ�绰</p></td> 
      <td colspan="6">${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="40"> 
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
   	
   	 <tr align="center" height="40"> 
      <th rowspan="2"> <p align=center>ѧϰ<br/>���<br/></p> 
      </th> 
      <td colspan="8">�ɼ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������</td>
      <td colspan="11">ʵ���ۺϿ����������ǡ� ��&nbsp;&nbsp;���</td>
    </tr>
    <tr align="center" height="40">
      <td colspan="8">���޿�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ����м�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��</td>
      <td colspan="11">���ǣ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������</td>
    </tr>
    
    <tr align="center" height="40"> 
      <th rowspan="5"> <p align=center>��ѧ<br/>�ڼ�<br/>��Ҫ<br/>��<br/>���</p> 
      </th>
      <td>����</td> 
      <td colspan="9">��������</td>
      <td colspan="9">�佱��λ</td>
    </tr>
    <tr align="center" height="40">
      <td>&nbsp;${rs.hjsj1}&nbsp;</td>
      <td colspan="9">&nbsp;${rs.hjmc1 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw1 }&nbsp;</td>
    </tr>
     <tr align="center" height="40">
      <td>&nbsp;${rs.hjsj2}&nbsp;</td>
      <td colspan="9">&nbsp;${rs.hjmc2 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw2 }&nbsp;</td>
    </tr>
     <tr align="center" height="40">
      <td>${rs.hjsj3}</td>
      <td colspan="9">&nbsp;${rs.hjmc3 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw3 }&nbsp;</td>
    </tr>
     <tr align="center" height="40">
      <td>&nbsp;${rs.hjsj4}&nbsp;</td>
      <td colspan="9">&nbsp;${rs.hjmc4 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw4 }&nbsp;</td>
    </tr>
    
    <tr height="350"> 
      <th> <p align=center>
      	        ����<br/> 
		        ����<br/> 
		    (200�֣�
		   <br/></p></th> 
     	 <td colspan="19">
      		<p style="height: 300">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }</p> 
      		<p align="right">
			������ǩ��(��ǩ)��
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
            <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p>
      </td> 
    </tr>
    </table>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/></br><br/><br/><br/><br/><br/>
    <table  class="tbstyle" border="0" align="center" style="width: 100%;"> 
     <tr height="250"> 
      <th width="14%"> <p align=center>
      	        �Ƽ�<br/> 
		        ����<br/> 
		        ��100�֣�
		   <br/></p></th> 
     	 <td colspan="19">
      		<p style="height: 200">&nbsp;&nbsp;&nbsp;&nbsp; </p> 
      		<p align="right">
			 �Ƽ��ˣ�����Ա������Σ�ǩ����
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
            <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p>
      </td> 
    </tr>  
       <tr height="250">
         <th>
           <p align=center>Ժ<br/>��ϵ��<br/>��<br/>��</p></th>
         <td colspan="19">
         	<p style="height: 90">&nbsp;&nbsp;&nbsp;&nbsp;</p>
         	  <p align="right"> Ժϵ����ѧ�������쵼ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align="right">��Ժϵ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p></td>
       </tr>
       <tr height="250">
         <th>
            <p align=center>ѧ<br/>У<br/>��<br/>��</p>
	  </th>
         <td colspan="19">
           <p style="height: 100">
           <br/><br/>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�������գ������飬�ֱ�����׼��ͬѧ��ù��ҽ�ѧ��</p>
           <p align="right">��ѧУ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;</p></td>
       </tr>
     </table>
      <div align="right">
      <br/>
     	�Ʊ�ȫ��ѧ��������������&nbsp;&nbsp;&nbsp;&nbsp;2010���
     </div>
     
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

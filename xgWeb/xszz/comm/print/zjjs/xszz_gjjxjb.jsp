<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<html:html>
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
		position:relative;
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
style='font-size:20.0pt;font-family:����;letter-spacing:1.0pt'>��${rs.xn }ѧ�꣩���ҽ�ѧ������������</span></b></p> 
  <br/><br/>
  <div align="left" style="font-size:15px;"><b>ѧУ��</b><b>${xxmc }</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<b>Ժϵ��</b><b>${rs.xymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 		<b>ѧ�ţ�</b><b>${rs.xh }</b></div> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table class="printtab" style="font-family:����_GB2312;font-size:15px;width:100%;">
    <tr align="center" height="40"> 
      <th width=14% rowspan=4><p align=center>����<br/>���</p></th> 
      <td width=16%> <p align=center>����</p></td> 
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
      <td id="s0" width="5%">&nbsp; </td>  
      <td id="s1" width="5%">&nbsp; </td> 
      <td id="s2" width="5%">&nbsp; </td> 
      <td id="s3" width="5%">&nbsp; </td> 
      <td id="s4" width="5%">&nbsp; </td> 
      <td id="s5" width="5%">&nbsp; </td> 
      <td id="s6" width="5%">&nbsp; </td> 
      <td id="s7" width="5%">&nbsp; </td> 
      <td id="s8" width="5%">&nbsp; </td> 
      <td id="s9" width="5%">&nbsp; </td> 
      <td id="s10" width="5%">&nbsp; </td> 
      <td id="s11" width="5%">&nbsp; </td> 
      <td id="s12" width="5%">&nbsp; </td> 
      <td id="s13" width="5%">&nbsp; </td> 
      <td id="s14" width="5%">&nbsp; </td> 
      <td id="s15" width="5%">&nbsp; </td> 
      <td id="s16" width="5%">&nbsp; </td> 
      <td id="s17" width="5%">&nbsp; </td> 
   	</tr>
   	
   	 <tr align="center" height="40"> 
      <th rowspan="2"> <p align=center>ѧϰ<br/>���<br/></p> 
      </th> 
      <td colspan="8">�ɼ�������<u>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</u>������/��������</td>
      <td colspan="11">ʵ���ۺϿ����������ǡ� �����</td>
    </tr>
    <tr align="center" height="40">
      <td colspan="8">���޿�<u>&nbsp;&nbsp;&nbsp;</u>�ţ����м�������<u>&nbsp;&nbsp;&nbsp;</u>��</td>
      <td colspan="11">���ǣ�������<u>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</u>������/��������</td>
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
      <th> <p align=center >
      	        ����<br/> 
		        ����<br/> 
		    (200�֣�
		   <br/></p></th> 
     	 <td colspan="19">
      		<p style="height: 300">&nbsp;&nbsp;&nbsp;&nbsp; </p><!--${rs.sqly }  --> 
      		<p align="right" style="font-size:17px;">
			������ǩ��(��ǩ)��
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<br/>
            <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
      </td> 
    </tr>
    </table>
    <br/><br/><br/><br/>
    <table class="printtab" style="font-family:����_GB2312;font-size:17px;width:100%;">
     <tr height="250"> 
      <th width="14%"> <p align=center>
      	        �Ƽ�<br/> 
		        ����<br/> 
		        <p style="font-size:15px">��100�֣�</p>
		   <br/></p></th> 
     	 <td colspan="19">
      		<p style="height: 260">&nbsp;&nbsp;&nbsp;&nbsp; </p> 
      		<p align="right">
			 �Ƽ��ˣ�����Ա������Σ�ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
            <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p>
      </td> 
    </tr>  
       <tr height="260">
         <th>
           <p align=center>Ժ<br/><br/>��ϵ��<br/><br/>��<br/><br/>��</p></th>
         <td colspan="19">
         	<p style="height: 90">&nbsp;&nbsp;&nbsp;&nbsp;</p>
         	  <p align="right"> Ժϵ����ѧ�������쵼ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
           <p align="right">��Ժϵ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p></td>
       </tr>
       <tr height="260">
         <th>
            <p align=center>ѧ<br/><br/>У<br/><br/>��<br/><br/>��</p>
	  </th>
         <td colspan="19">
           <p style="height: 100">
           <br/><br/>
           &nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�������գ������飬�ֱ�����׼��ͬѧ��ù��ҽ�ѧ��</p>
           <br/><br/><p align="right">��ѧУ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br/>
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
</html:html>

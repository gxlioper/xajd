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
style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>���ҽ�ѧ������������ ${rs.xn } ѧ�꣩</span></b></p> 
  <div align=left><b>ѧУ��</b>${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;<b>Ժϵ��</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;<b>רҵ��</b>${rs.zymc }&nbsp;&nbsp;&nbsp;&nbsp;
  <b>�༶��</b>${rs.bjmc }</div> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="tbstyle" border="0" align="center" style="width: 100%"> 
    <tr align="center"> 
      <th width=14% rowspan=4><p align=center>��<br/>��<br/>��<br/>��</p></th> 
      <td width=14%> <p align=center>����</p></td> 
      <td width=20% colspan="5">${rs.xm } </td> 
      <td width=12% colspan="3"> <p align=center>�Ա�</p></td> 
      <td width=12% colspan="3">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>��������</p></td> 
      <td width=16% colspan="4">${rs.csrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>ѧ��</p></td> 
      <td colspan="5">${rs.xh } </td> 
      <td colspan="3"> <p align=center>����</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>��ѧʱ��</p></td> 
      <td colspan="4">${rs.rxrq } </td> 
    </tr> 
    <tr align="center"> 
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
    <tr align="center"> 
      <td> <p>������ò</p></td> 
      <td colspan="5">${rs.zzmmmc } </td> 
      <td colspan="6"> <p align=center>��ϵ�绰</p></td> 
      <td colspan="7">${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <th>
      	<p align=center>ѧ<br/>ϰ<br/>��<br/>��<br/>��<br/></p> 
	  </th> 
      <td colspan="19"> <p>��ѧ����޿γ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ����У�����<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</u>�ţ�����<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</u>�ųɼ�������רҵ���꼶����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������</p> 
        <p>�ۺϿ����ɼ�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�֣�����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </u>������/�������������޴����д���ޡ���</p>
      </td> 
    </tr> 
    <tr> 
      <th> <p align=center>��<br/>��<br/>��<br/>��<br/></p> 
      </th> 
      <td colspan="19"> <p>��Ҫ���</p> 
		<p>${rs.hjqk}</p>
        <p>���У�Ժ������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.yjjlcs} </u>�У������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${rs.xjjlcs}</u>�ʡ�����Ͻ���<u>&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sjjlcs}</u>�</p></td> 
    </tr> 
    <tr height="230"> 
      <th> <p align=center>
      	        ��<br/>
       	        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		        ȫ<br/> 
		        ��<br/> 
		        ��<br/> 
		        ӳ<br/> 
		        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		        ��<br/></p></th> 
     	 <td colspan="19">
      		<p style="heigth:200">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqsm }</p> 
      		<p align="right">
			������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			
            <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp; </p>
      </td> 
    </tr> 
       <tr height="130">
         <th>
           <p align=center>��&nbsp; ��<br/>��רҵ��<br/>  ��&nbsp; ��<br/>��&nbsp; ��</p>
         </th>
         <td colspan="19">
           <p style="height: 110px">&nbsp;&nbsp;&nbsp;</p>
           <p align=left>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �Ƽ��ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����ְ��</p>
           <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;</p></td>
     	</tr>
       <tr height="120">
         <th>
           <p align=center>Ժ<br/>��ϵ��<br/>��<br/>��</p></th>
         <td colspan="19">
         	<p style="width: 100">&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align="right">����&nbsp; �£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </p></td>
       </tr>
       <tr>
         <th>
            <p align=center>ѧ<br/>У<br/>��<br/>��</p>
	  </th>
         <td colspan="19">
		<logic:notEmpty name="rs" property="xxyj">
			${rs.xxyj}
		</logic:notEmpty>
		<logic:empty name="rs" property="xxyj">
		<br/>
           <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>��Χ�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>�죬�����飬�ֱ���ͬ���ͬѧ��<u>&nbsp;${rs.xn}&nbsp;</u>ѧ��ȹ��ҽ�ѧ��</p>
		</logic:empty>
           <p align="right">����&nbsp; �£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;</p></td>
       </tr>
     </table>
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

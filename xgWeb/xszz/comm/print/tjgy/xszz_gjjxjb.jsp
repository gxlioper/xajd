<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
<body>
<div align="center"> 
<br/>
<br/>
<br/>
<br/>
  <p align=center><b><span style='font-size:30px;font-family:����;font-weight:bold'>(${rs.xn }ѧ��)���ҽ�ѧ������������</span></b></p> 
  <br/>
  <br/>
  <table width="800px" style="font-size:20px;font-family:����;font-weight:bold">
  <tr align="left">
  <td>ѧУ��${xxmc }</td>
  <td>Ժϵ��${rs.xymc }</td>
  <td>ѧ�ţ�${rs.xh }</td>
  </tr>
  </table>
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table width="800px"  class="printtab" style="font-size:20px">
    <tr> 
      <th width=12% rowspan=4><p align=center>����<br/>���</p></th> 
      <td width=14%> <p align=center>����</p></td> 
      <td width=10% colspan="5" align="center">${rs.xm } </td> 
      <td width=12% colspan="3"> <p align=center>�Ա�</p></td> 
      <td width=12% colspan="3" align="center">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>��������</p></td> 
      <td width=16% colspan="4" align="center">${rs.csrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>������ò</p></td> 
      <td colspan="5">${rs.zzmmmc} </td> 
      <td colspan="3"> <p align=center>����</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>��ѧʱ��</p></td> 
      <td colspan="4">${rs.rxrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>רҵ</p></td> 
      <td colspan="5">${rs.zymc } </td> 
      <td colspan="3"> <p align=center>ѧ��</p></td> 
      <td colspan="3">${rs.xz } </td> 
      <td colspan="3"> <p align=center>��ϵ�绰</p></td> 
      <td colspan="4">${rs.lxdh } </td> 
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
    <tr> 
      <th rowspan="2">
      	<p align=center>ѧϰ<br/>���<br/></p> 
	  </th> 
      <td colspan="9">�ɼ�������
      <logic:equal name="rs" property="bjpm" value="">
       <u>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</u>
      </logic:equal>
      <logic:notEqual name="rs" property="bjpm" value="">
      <u>&nbsp;&nbsp;&nbsp;${rs.bjpm }&nbsp;&nbsp;&nbsp;</u>
      </logic:notEqual>
             ������/��������</td>
      <td colspan="10">ʵ���ۺϿ���������
      <logic:notEqual name="rs" property="sxzhpm" value="">
      <logic:equal value="��" property="sxzhpm" name="rs"> ��<input type="checkbox"/>����<input type="checkbox" checked="checked"/></logic:equal>
      <logic:equal value="��" property="sxzhpm" name="rs"> ��<input type="checkbox" checked="checked"/>����<input type="checkbox" /></logic:equal>
      </logic:notEqual >
      <logic:equal name="rs" property="sxzhpm" value="">
		��<input type="checkbox"/>����<input type="checkbox" />
	  </logic:equal>
       </td>
    </tr> 
    <tr>
    <td colspan="9"> ���޿�<u>&nbsp;&nbsp;&nbsp;&nbsp; ${rs.bxkms}&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ����м�������<u>&nbsp;&nbsp;${rs.jgysms }&nbsp;&nbsp;</u>��
    </td> 
    <td colspan="10">
                     ���ǣ�������
       <logic:equal name="rs" property="zcbjpm" value="">
       <u>&nbsp;&nbsp;/&nbsp;&nbsp;</u>������/��������
      </logic:equal>
      <logic:notEqual name="rs" property="zcbjpm" value="">
                     <u>&nbsp;&nbsp;&nbsp;${rs.zcbjpm }&nbsp;&nbsp;&nbsp; </u>������/��������
       </logic:notEqual>
    </td>
    </tr>
    <tr align="center"> 
      <th rowspan=5"> <p align=center>��ѧ<br>�ڼ�<br>��Ҫ<br>��<br>���</p> 
      </th> 
    <td>����</td>
    <td colspan="10">��������</td>
    <td colspan="8">�佱��λ</td>
    </tr> 
    <tr align="center">
    <td>${rs.hjrq1 }</td>
    <td colspan="10">${rs.hjmc1 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw1 }&nbsp;&nbsp;</td>
    </tr>
    <tr align="center">
    <td>${rs.hjrq2 }</td>
    <td colspan="10">${rs.hjmc2 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw2 }&nbsp;&nbsp;</td>
    </tr>
    <tr align="center">
    <td>${rs.hjrq3 }</td>
    <td colspan="10">${rs.hjmc3 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw3 }&nbsp;&nbsp;</td>
    </tr>
     <tr align="center">
    <td>${rs.hjrq4 }</td>
    <td colspan="10">${rs.hjmc4 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw4 }&nbsp;&nbsp;</td>
    </tr>
    <tr height="420px"> 
      <th> <p align=center>
      	        ����<br/> 
		        ����<br/> 
		 <span style="font-size:10px;font-weight:normal">(200��)</span>
		  </p></th> 
     	 <td colspan="19">
      		<p style="height:330px">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }</p> 
      		<p align="right">
			������ǩ��(��ǩ)��
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			 &nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p style="height:20px"></p>
            <p align="right">��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
            </p>
            <p style="height:15px"></p>
       </td> 
      </tr> 
    </table>
    
   <p style="height:60px"></p>
   
   
    <table width="800px"  class="printtab" style="font-size:20px">
  
       <tr height="350px">
         <th width=12%>
           <p align=center>�Ƽ�<br/>����<br><span style="font-size:10px;font-weight:normal">(100��)</span> </p>
         </th>
         <td colspan="19">
           <p style="height:300px">&nbsp;&nbsp;&nbsp;</p>
           <p></p>
           <p align=right>
		        �Ƽ��ˣ�����Ա������Σ�ǩ����
		   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		   &nbsp;&nbsp;&nbsp;&nbsp;
		 </p>
		<p style="height:20px"></p>
        <p align=right>��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        </p>
        <p style="height:15px"></p>
        </td>
     	</tr>
     
    	
       <tr height="210px">
         <th>
           <p align=center>Ժ<br/>(ϵ)<br/>��<br/>��</p></th>
         <td colspan="19">
           <p style="height:160px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj}</p>
           <p></p>
           <p align="right"> Ժϵ����ѧ�������쵼ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p style="height:15px"></p>
           <p align="right">��Ժϵ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p style="height:15px"></p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; 
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        </p>
        <p style="height:15px"></p></td>
       </tr>
       <tr height="340px">
         <th>
            <p align=center>ѧ<br/>У<br/>��<br/>��</p>
	  </th>
         <td colspan="19">
		<logic:notEmpty name="rs" property="xxyj">
			&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj}
		</logic:notEmpty>
		<logic:empty name="rs" property="xxyj">
		<br/>
		<br/>
           <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ<u>&nbsp;&nbsp;��&nbsp;&nbsp; </u>�������գ������飬	�ֱ�����׼��ͬѧ��ù��ҽ�ѧ��</p>
		</logic:empty>
		   <p style="height:120px"></p>
           <p align="right">��ѧУ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p style="height:30px"></p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;  
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        </p>
        <p style="height:20px"></p></td>
       </tr>
     </table>
     <center><p style="width:800px;font-size:16px" align="right">�Ʊ�ȫ��ѧ�������������ġ�2010���</p></center>
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

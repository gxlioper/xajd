<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
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
  <p align=center>
  	<span style='font-size:18.0pt;font-family:��������;color:black;'>
  		<logic:equal value="yxty" name="mk">
  			��ְͨҵ��ѧ�����㹲����Ա��������ѡ�걨��
  		</logic:equal>
  		<logic:equal value="yxtgb" name="mk">
  			��ְͨҵ��ѧ�����㹲���Ÿɲ����걨��
  		</logic:equal>
  	</span>
  </p> 
  <table class="tbstyle" width="95%" height="800px"> 
    <tr> 
      <td width="14%"><p align=center>��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td width="26%">${rs.xm }</td> 
      <td width="13%" colspan=2> <p align=center>��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td width="14%" colspan=2>${rs.xb }</td> 
      <td width="14%"> <p align=center>��������</p></td> 
      <td width="17%">${rs.csrq } </td> 
    </tr> 
    <tr> 
      <td width="14%"><p align=center>������ò</p></td> 
      <td width="26%">${rs.zzmmmc }</td> 
      <td width="13%" colspan=2> <p align=center>����ʱ��</p></td> 
      <td width="16%" colspan=2>${rs.rtrq } </td> 
      <td width="16%"> <p align=center>ѧ��</p></td> 
      <td width="13%">${rs.whcd }</td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>
      <logic:equal value="yxtgb" name="mk">
      	����Ժϵ���༶��ְ��
      </logic:equal>
       <logic:equal value="yxty" name="mk">
      	����Ժϵ���༶
      </logic:equal>
      </p></td> 
      <td width="26%" colspan=7>${rs.xymc } &nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;
	       <logic:equal value="yxtgb" name="mk">
	      	${zw }
	      </logic:equal>
       </td> 
    </tr> 

    <tr> 
      <td width="14%"> <p align=center>��<br/>��<br/>��<br/>��</p></td> 
      <td width="85%" colspan=7>${rs.grjl } </td> 
    </tr> 
    
     <tr> 
      <td width="14%"> <p align=center>��<br/>��<br/>��<br/>��</p></td> 
      <td width="85%" colspan=7>${rs.hjqk } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>��<br/>��<br/>��<br/>��</p></td> 
      <td width="85%" colspan=7>
      <p style="height:80px">
      	${rs.sqsm }
      </p>
      <p align="right">
      	����1000���¼����ϣ�
      </p> 
     </td> 
    </tr>
    <tr> 
      <td width="14%"> <p align=center>��֧<br/>����<br/>��</p></td> 
      <td width="34%" colspan=2> 
      	<p style="height: 80px"> &nbsp;</p> 
        	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ˣ�ǩ����</p>
        <p align="right">
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width="8%" colspan=2> <p align=center>Ժϵ<br/>����<br/>֧��<br/>��</p></td> 
      <td width="42%" colspan=3>
        <p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	�����£�&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr>  
    <tr> 
      <td width="14%"> <p align=center>Ժϵ<br/>����<br/>֯��<br/>��</p></td> 
      <td width="34%" colspan=2> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	�����£�&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width="8%" colspan=2> <p align=center>��ί<br/>���</p></td> 
      <td width="42%" colspan=3>
        <p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	�����£�&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
  </table>
  <div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div> 
</div> 
</body>
</html>

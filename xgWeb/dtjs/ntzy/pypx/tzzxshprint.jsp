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
  		<logic:equal value="tzzgb" name="mk">
  			��������֧�ɲ���������Ƽ���
  		</logic:equal>
  		<logic:equal value="xshgb" name="mk">
  			����ѧ����ɲ���������Ƽ���
  		</logic:equal>
  	</span>
  </p> 
  <table class="tbstyle" width="95%" height="800px"> 
    <tr> 
      <td width="14%"><p align=center>��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td width="26%">${rs.xm }</td> 
      <td width="13%" colspan=2> <p align=center>��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td width="14%" colspan=2>${rs.xb }</td> 
      <td width="14%"> <p align=center>��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td width="17%">${rs.mzmc } </td> 
    </tr> 
    <tr> 
      <td width="14%"><p align=center>��������</p></td> 
      <td width="26%">${rs.csrq }</td> 
      <td width="13%" colspan=2> <p align=center>��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td width="16%" colspan=2>${rs.jg } </td> 
      <td width="16%"> <p align=center>������ò</p></td> 
      <td width="13%">${rs.zzmmmc }</td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>Ժ&nbsp;&nbsp;&nbsp; ϵ</p></td> 
      <td width="26%">${rs.xymc } </td> 
      <td width="13%" colspan=2> <p align=center>���ڰ༶</p></td> 
      <td width="45%" colspan=4>${rs.bjmc } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>��&nbsp;&nbsp;&nbsp; ��</p></td> 
      <td width="26%">${rs.sjhm } </td> 
      <td width="13%" colspan=2> <p align=center>����ʱ��</p></td> 
      <td width="16%" colspan=2>${rs.rtrq } </td> 
      <td width="16%"> <p align=center>�뵳ʱ��</p></td> 
      <td width="13%">${rs.rdsj } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>����ְ��</p></td> 
      <td width="85%" colspan=7>${zw } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>��<br/>��</p></td> 
      <td width="85%" colspan=7>${rs.grjl } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>��<br/>Ҫ<br/>��<br/>��</p></td> 
      <td width="85%" colspan=7>
      <p style="height:80px">
      	${rs.sqsm }
      </p></td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>����<br/>���</p></td> 
      <td width="85%" colspan=7>${rs.hjqk} </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>��<br/>��<br/>Ժ<br/>ϵ<br/>��<br/>��</p></td> 
      <td width="34%" colspan=2> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	�����£�&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width="8%" colspan=2> <p align=center>��<br/>ί<br/>��<br/>��</p></td> 
      <td width="42%" colspan=3>
        <p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	�����£�&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>��ע</p></td> 
      <td width="85%" colspan=7>
      	<p style="height: 60px">${rs.bz }</p>
      </td> 
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

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
  		��ְͨҵ��ѧ��������֧�����걨��
  	</span>
  </p> 
  <table class="tbstyle" width="90%" height="700px"> 
    <tr> 
      <td width="30%"> <p align=center>��֧������</p></td> 
      <td width="70%">${rs.tzbmc } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>����Ժϵ����֧</p></td> 
      <td>
      <p style="height:80px">
      	${rs.szyxtzz }
      </p></td> 
    </tr> 
    <tr> 
      <td><p align=center>��Ҫ<br/>����</p></td> 
      <td>
      	<p style="height: 170px">${rs.zygz }</p>
      	<p align="center">����1000�������¼����ܲ��ϣ�</p>
       </td> 
    </tr> 
    <tr> 
      <td> <p align=center>����Ժϵ<br/>����֯���</p></td> 
      <td> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	�����£�&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</p></td>   
    </tr>
    <tr> 
      <td> <p align=center>��ί���</p></td> 
      <td> 
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

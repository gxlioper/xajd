<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
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
<html:form action="/data_search" method="post">
<div class=Section1 style='layout-grid:15.6pt' align="center"> 
  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;font-family:����'>����ʦ��ѧԺ����ѧ����ģ���ɲ�<br/>�� �� �� �� ��
</span></b></p> 
  <table class="printstyle" width="90%"> 
    <tr align="center"> 
      <th width="8%">�� ��</th> 
      <th width="15%">${map.xm } </th> 
      <th width="8%">�� ��</th> 
      <th width="15%">${map.xb }</th> 
      <th width="8%">ѧ��</th> 
      <th width="19%">${map.xh }</th> 
      <th width="8%">������ò</th> 
      <th width="19%">${zzmmmc }</th> 
    </tr> 
    <tr align="center"> 
      <th>����<bean:message key="lable.xb" />�༶</th> 
      <th colspan="3">${map.xymc} &nbsp;&nbsp;${map.bjmc }</th> 
      <th>�걨���</th> 
      <th colspan="3">${rych }</th>
    </tr> 
    <tr> 
    <th>��<br/>Ҫ<br/>��<br/>��</th>
      <th colspan=7>
    	<p style="height:225px" align="left">&nbsp;&nbsp;&nbsp;&nbsp;${zysj }</p>
    	
  	  </th>
    </tr> 
   <tr> 
      <th colspan=8>
    	<p style="height:50px" align="left">
    	�����������
    	</p>
    	<p align="right">
    		ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
    <tr> 
      <th colspan=8>
    	<p style="height:50px" align="left"><bean:message key="lable.xb" />��������</p>
    	<p align="right">
    		�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>
    <tr> 
      <th colspan=8>
    	<p style="height:50px" align="left">ѧУ��������</p>
    	<p align="right">
    		�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>  
  </table> 
  <table width="90%" border="0"> 
  	<tr align="left"><th>˵�������걨���һ����д������ѧ������ģ���ɲ���</th></tr>
  </table>
</div>
<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
	</html:form>
</body>
</html>

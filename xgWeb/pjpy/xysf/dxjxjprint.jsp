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
<div class=Section1 style='layout-grid:15.6pt' align="center"> 
  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;font-family:����'>����ʦ��ѧԺ${map.jxjmc }������</span></b></p> 
  <table class="printstyle" width="90%"> 
    <tr align="left">
    	<th colspan="8">&nbsp;&nbsp;��Ŀ���ƣ�${map.jxjmc }</th>
    </tr>
    <tr align="left">
    	<th colspan="8">&nbsp;&nbsp;��&nbsp;&nbsp;Ŀ��&nbsp;&nbsp;����� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ˡ�</th>
    </tr>
    <tr align="left">
    	<th colspan="8">&nbsp;&nbsp;�񽱼���&nbsp;&nbsp;���ҡ� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ʡ��������
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;У����</th>
    </tr>
    
    <tr align="center">
    	<th colspan="8">�����˻������</th>
    </tr>
    <tr align="center"> 
      <th width="12%">������</th> 
      <th width="13%"></th> 
      <th width="12%"><bean:message key="lable.xb" /></th> 
      <th width="13%"></th> 
      <th width="13%">רҵ</th> 
      <th width="13%"></th> 
      <th width="12%">�༶</th> 
      <th width="13%"></th> 
    </tr>
     <tr align="center"> 
      <th>������</th> 
      <th></th> 
      <th><bean:message key="lable.xb" /></th> 
      <th></th> 
      <th>רҵ</th> 
      <th></th> 
      <th>�༶</th> 
      <th></th> 
    </tr> 
     <tr align="center"> 
      <th>������</th> 
      <th></th> 
      <th><bean:message key="lable.xb" /></th> 
      <th></th> 
      <th>רҵ</th> 
      <th></th> 
      <th>�༶</th> 
      <th></th> 
    </tr> 
     <tr align="center"> 
      <th>������</th> 
      <th></th> 
      <th><bean:message key="lable.xb" /></th> 
      <th></th> 
      <th>רҵ</th> 
      <th></th> 
      <th>�༶</th> 
      <th></th> 
    </tr> 
    <tr> 
    <th>��<br/>��<br/>��<br/>��</th>
      <th colspan=7>
    	<p style="height:150px"></p>
    	<p align="right">
    		������ǩ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
   <tr> 
    <th><bean:message key="lable.xb" /><br/>����<br/>���</th>
      <th colspan=7>
    	<p style="height:50px"></p>
    	<p align="right">
    		��ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
       <tr> 
    <th>ѧУ<br/>����<br/>���</th>
      <th colspan=7>
    	<p style="height:50px"></p>
    	<p align="right">
    		��ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>
  </table>
  <table width="90%" class="" border="0">
  	<tr align="left"><th>˵����������Ŀ�걨������Ŀ�������걨����������������ɸ���������<bean:message key="lable.xb" />��д�����ý����ɸ�����
����ַ��������ߡ�
  	</th></tr>
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
</body>
</html>

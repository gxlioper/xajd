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
style='font-size:18.0pt;font-family:����'>����ʦ��ѧԺ�Ƚ��༯���Ƽ�������
</span></b></p> 
  <table class="printstyle"> 
    <tr align="center"> 
      <th width="15%">�༶����</th> 
      <th width="25%">${bjmc } </th> 
      <th width="15%">ѧ������</th> 
      <th width="15%">${xsrs }</th> 
      <th width="15%">������Ա����</th> 
      <th width="15%"></th>
    </tr> 
    <tr align="center"> 
      <th>�����λ򸨵�Ա����</th> 
      <th>${bzr }</th> 
      <th>�೤����</th> 
      <th colspan="3">${bzxm }</th>
    </tr> 
    <tr> 
    <th>��<br/>Ҫ<br/>��<br/>��</th>
      <th colspan=5>
    	<p style="height:400px" align="left">${zysj }</p>
  	  </th>
    </tr> 
   
    <tr> 
      <th colspan=6>
    	<p style="height:80px" align="left"><bean:message key="lable.xb" />��������</p>
    	<p align="right">
    		�����£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>
    <tr> 
      <th colspan=6>
    	<p style="height:80px" align="left">ѧУ��������</p>
    	<p align="right">
    		�����£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>  
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

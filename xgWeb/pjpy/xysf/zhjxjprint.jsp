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
style='font-size:18.0pt;font-family:����'>����ʦ��ѧԺ�ۺϽ�ѧ��������</span></b></p> 
  <table class="printstyle"> 
    <tr align="center"> 
      <th width="8%">�� ��</th> 
      <th width="15%">${map.xm } </th> 
      <th width="8%">�� ��</th> 
      <th width="15%">${map.xb }</th> 
      <th width="8%"><bean:message key="lable.xb" /></th> 
      <th width="19%">${map.xymc }</th> 
      <th width="8%">רҵ</th> 
      <th width="19%">${map.zymc }</th> 
    </tr> 
    <tr align="center"> 
      <th>�༶</th> 
      <th>${map.bjmc }</th> 
      <th colspan="2">�ۺϲ�������</th> 
      <th>${pm.zhszcpzfpm }</th>
      <th>ѧ��</th>
      <th colspan="2">${map.xh }</th> 
    </tr> 
    <tr> 
      <th>��ѧ��ȴ�</th> 
      <th colspan=7>
      	<p style="heght:60px">
      		${map.jxjmc }
      	</p>
      </th> 
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
    <th>ѧ<br/>Ժ<br/>��<br/>��<br/>��<br/>��</th>
      <th colspan=7>
    	<p style="height:100px"></p>
    	<p align="right">
    		��ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
    <tr> 
    <th>ѧ<br/>У<br/>��<br/>��<br/>��<br/>��</th>
      <th colspan=7>
    	<p style="height:100px"></p>
    	<p align="right">
    		��ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
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

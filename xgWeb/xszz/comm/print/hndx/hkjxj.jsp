<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div align=center> 
	<p align=center style='text-align: center'>
		<b><span style='font-size: 18.0pt; font-family: ����;'>�ο�ũҵ�������л������������</span>
		</b>
	</p>
  <table class="tbstyle" width="90%"> 
    <tr align="center"> 
      <td width="12.5%">����</td> 
      <td colspan=2 width="25%">${rs.xm }</td> 
      <td width="12.5%">�Ա�</td> 
      <td width="12.5%">${rs.xb }</td> 
      <td width="12.5%">����</td> 
      <td colspan=2 width="25%">${rs.mzmc }��</td> 
    </tr> 
    <tr align="center"> 
      <td>ԺУ</td> 
      <td colspan=4>${xxmc }${rs.xymc }</td> 
      <td>���֤����</td> 
      <td colspan=2>${rs.sfzh }</td> 
    </tr> 
    <tr align="center"> 
      <td>רҵ</td> 
      <td colspan=4>${rs.zymc }</td> 
      <td>��ѧʱ��</td> 
      <td colspan=2>${rs.rxrq }</td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3>ԺУͨѶ��ַ�����ʱࣩ</td> 
      <td colspan=5></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2>ԺУ��ϵ��</td> 
      <td colspan=2></td> 
      <td colspan=2>��ϵ�绰�������ţ�</td> 
      <td colspan=2></td> 
    </tr> 
    <tr align="center"> 
    	<td>
    		<br/>��<br/>��<br/>��<br/>Ʒ<br/>ѧ<br/>��<br/>��<br/>��<br/>��<br/>
    	</td>
    	<td colspan="7">
    		<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			����ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
    		</p>
    	</td>
    </tr>
    	
    <tr align="center"> 
    	<td>
    		<p align="center"><br/>Ժ<br/>ϵ<br/>��<br/>ר<br/>ҵ<br/>��<br/>��<br/>��<br/></p>
    	</td>
    	<td colspan="7">
    		<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
    		</p>
    	</td>
    </tr> 
    
    <tr align="center"> 
 		<td>
 			<br/>ѧ<br/>У<br/>��<br/>��<br/>
 		</td>
 		<td colspan="7">
 		
 			<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			У����ǩ�֣���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
    		</p>
 		</td>
    </tr> 
    
    <tr align="center">
    	<td>
    		<br/>��<br/>��<br/>��<br/>��<br/>��<br/>
    	</td>
    	<td colspan="7">
    	
    		<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
    		</p>
    	</td>
    </tr> 
  </table>
  <br/><br/><br/>
  
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
</div> 
</body>
</html>

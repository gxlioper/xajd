<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="��ѧ��.files/editdata.mso">
<title>����Ա����ͳ�Ʊ�</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<style>
			.tbstyle tr th{
				line-height: 20px;
				font-size: 14px;
				border: 1px solid #000000;
			}
			.tbstyle tr td{
				line-height: 20px;
				font-size: 14px;
				border: 1px solid #000000;
			}
			.tbstyle tr{
				height: 33px;
			}
			body{
				font-size: 12px;
			}
		</style>
		<!-- end -->
</head>
<body bgcolor="#000000" lang=ZH-CN> 
<div align="center"> 
  <p align=center><b style="font-size: 14px;font-weight: normal;"><span
style='font-size:20.0pt;font-family:����;letter-spacing:1.0pt; font-weight: bold;'>�������ϴ�ѧ����Ա����ͳ�Ʊ�</span>������Ա��д��</b></p> 
  <p align="center" ><b style="font-size: 14px;font-weight: normal;"><bean:message key="lable.xb" />��</b>${rs.bmmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style="font-size: 14px;font-weight: normal;">����Ա������</b>${rs.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <table  class="tbstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <th width=33%>������Ŀ</th> 
      <th width=33%>������</th> 
      <th width=34%>��ע</th> 
    </tr> 
    <logic:present name="xmList">
    <logic:iterate id="xm" name="xmList">
	    <tr align="center"> 
	      <td>${xm.khxm}
	      	  <logic:equal name="xm" property="xmlx" value="��ѡ��">
	      	  	��
	      	  </logic:equal>
	      	  <logic:equal name="xm" property="xmlx" value="�ӷ���">
	      	  	��
	      	  </logic:equal>
	      	  <logic:equal name="xm" property="xmlx" value="��ѡ��">
	      	  	*
	      	  </logic:equal>
	      </td>
	      <td>${xm.wcqk}</td>
	      <td>${xm.bz}</td>
	    </tr> 
    </logic:iterate>
    </logic:present>
     </table>
     <div style="width:80%;text-align: left; line-height: 20px; margin-top: 5px; margin-bottom: 5px;">
     	<p style="float: left; width: 10px;">ע��</p>
     	<p style="float:right;text-align: left;">1�����ʵ���������Ҫ��д�ñ�ע���йع��������������ɸ������ݼ�ҳ��<br/>
    		   2�ο��˾���ѧ��2009��85�����и���2�ĸ���Ա���������������ˡ��ĸ�һ��Ҫ��ÿѧ��ָ��һ�����ʵ�����š����һƪ���鱨�桢����һƪ�о����ġ����Ͻ�ʦ��ְ�����ĸ���Ա�е�һ�ŵ����γ̽�ѧ���ο��������ʣ���ҵ�ʡ������ʡ������ʡ�<br/>
			   3������Ŀ�ķ��ű�ʾ�����ѡ��  *��ѡ��    ���ӷ���
     	</p>
     </div>
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

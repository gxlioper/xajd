<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.HashMap"%>
<%@page import="xgxt.utils.String.StringUtils"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>����ά˹��ѧ����ѧ����</title>
<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
</head>
<body> 
<p><span style='font-size:14.0pt;font-family:����_GB2312;color:black'>��1��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p> 
<div align="center"> 
  <p><b>����ά˹���������</b></p> 
  <table class="tbstyle" width="85%" height="800px"> 
    <tr align="center"> 
      <td colspan=2 width="15%"> ��&nbsp; ��</td> 
      <td colspan=3 width="35%">${rs.xm } </td> 
      <td colspan=3 width="10%">��&nbsp; ��</td> 
      <td colspan=3 width="20%">${rs.xb }</td> 
      <td colspan=2 rowspan=3 width="30%"> 
      	<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }" border="0" align="absmiddle"  />
  		</td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2>��������ƴ��</td> 
      <td colspan=9>${rs.zwpy } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2>��������</td> 
      <td colspan=3>
     
		<!-- ���������    -->
      <%
     		HashMap<String,String> map = (HashMap<String, String>)request.getAttribute("rs");
     		String csrq = map.get("csrq");
     		if(StringUtils.isNotNull(csrq) && csrq.length() == 8 ){
 				String year = csrq.substring(0,4);
 				String month = csrq.substring(4,6);
 				String day = csrq.substring(6);
 				pageContext.setAttribute("year",year);
 				pageContext.setAttribute("month",month);
 				pageContext.setAttribute("day",day);    		
     		}
       %>
      <u>&nbsp;&nbsp;${year }&nbsp;&nbsp; </u>��
      <u>&nbsp;&nbsp;${month }&nbsp;&nbsp; </u>��
      <u>&nbsp;&nbsp;${day }&nbsp;&nbsp; </u>��
      </td> 
      <td colspan=3><p align=center>��&nbsp; ��</p></td> 
      <td colspan=3>${rs.mzmc } </td>
    </tr> 
    <tr align="center"> 
      <td colspan=2> <p align=center>��ͥ��ַ</p></td> 
      <td colspan=6>${rs.jtdz } </td> 
      <td colspan=3> <p align=center>��������</p></td> 
      <td colspan=2>${rs.jtyb } </td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=4> 
	  	<p align=center>��<br/>��<br/>ѧ<br/>У</p></td> 
      <td colspan=2> <p align=center>ԺУ����</p></td> 
      <td colspan=5>${xxmc } </td> 
      <td colspan=2> <p align=center>ϵ</p></td> 
      <td colspan=3>${rs.xymc } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2> <p align=center>ר&nbsp;&nbsp;&nbsp; ҵ</p></td> 
      <td colspan=5>${rs.zymc } </td> 
      <td colspan=2> <p align=center>��&nbsp;&nbsp; ��</p></td> 
      <td colspan=3>${rs.bjmc } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2> <p align=center>ѧУͨѶ��ַ</p></td> 
      <td colspan=6>${rs.xxdz } </td> 
      <td colspan=3> <p align=center>��������</p></td> 
      <td>${rs.xxyb } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3> <p align=center>��ϵ�绰�����ţ�</p></td> 
      <td colspan=3>${rs.xxlxdh } </td> 
      <td colspan=3> <p align=center>ѧ������绰</p></td> 
      <td colspan=3>${rs.qsdh } </td> 
    </tr> 
    <tr align="center" height="150px"> 
      <td> <p align=center>��<br/>��<br/>��<br/>��</p></td> 
      <td colspan=12 valign=top> <p class=MsoBodyText2 style='height: 100px'><b>������ϸ˵����ĸְҵ����ͥ�˿ڡ�������Դ���������ע��ѧ����Դ������ѧ�Ѽ��⼰��ý�ѧ����ѧ��״������������ҳ��д��</b></p> 
        <p align=center>������ǩ����
        <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </u>&nbsp;&nbsp; ʱ�䣺&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��</p></td> 
    </tr> 
    <tr align="center" height="150px"> 
      <td> <p align=center>��<br/>��<br/>��<br/>��<br/>ŵ</p></td> 
      <td colspan=12 valign=top align="left" >
      	<p style="height: 100px">1���μ���ά˹���̡����㡱ʵϰ������ÿ�β�����8��Сʱ����һѧ��ÿѧ�겻����40��Сʱ�����������ѧ��ÿѧ�겻����80��Сʱ;<br/>
      		ԭ���ϰ����ڡ���һ������ʮһ�������ٺ���ٵȽڼ��ռ�����ɣ����������������������ά˹��Э��ȷ�����ڹ���ѧ�ڼ�������ά˹���������ƶȣ�ͬʱ������ͨ��ʱ�����������
      		2��ѧϰ�ڼ�����μӸ�����ṫ������ҵ��������Ը�����칫���ṩ���緽ʽ,��������ʱ��Ը��ϣ�����̾��ʣ�����������Ҫ������ѧ����</p> 
        <p align=center>��ŵ��ǩ����
		 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </u>
		&nbsp;&nbsp; ʱ�䣺&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��</p>  
        </td> 
    </tr> 
    <tr align="center" height="120px"> 
      <td> <p align=center>��<br/>��<br/>��<br/>��</p></td> 
      <td colspan=12> 
      <p align=center style="height: 80px"></p>
      <p> Ժϵ�����ˣ�
       <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </u>
     	&nbsp;&nbsp; ʱ�䣺&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</p></td> 
    </tr> 
    <tr align="center" height="100px"> 
      <td> <p align=center>��<br/>��<br/>��<br/>��</p></td> 
      <td colspan=6> 
      <p style="height: 60px">&nbsp;&nbsp;</p>
      <p align="left">�����ˣ�<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>����ѧ��ί��ѧ�������£�<br/>
      	 ʱ�䣺&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��
      </p>
     </td> 
      <td colspan=6 valign=top> 
      <p style="height: 60px">&nbsp;&nbsp;</p>
      <p align="left">�����ˣ�<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��ʡ���������£�<br/>
      	 ʱ�䣺&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��
      </p></td> 
    </tr> 
  
  </table> 
  
  	<table border="0" width="85%">
  	<tr>
  	<td align="left">
  	<p><font size="3">ע���˱���Ը��ƣ�һʽ���ݣ���ѧ����ά˹���̺��й���������һ��ԭ����ʡ�����������ӡ����</font></p> 
  	</td>
  	</tr>
  	</table>
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

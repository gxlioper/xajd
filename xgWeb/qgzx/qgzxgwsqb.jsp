<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.utils.String.StringUtils"/>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="��ѧ��.files/editdata.mso">
<title>�ڹ���ѧ��λ�����</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>

<body bgcolor="#FFFFFF" lang=ZH-CN> 
<div align="center"> 
<table width="95%" border="0" id="theTable" align="center">
<tr>
  <td align="center">
  <p align=center style='text-align:center'><b><span
style='font-size:16.0pt;font-family:����'>${xxmc}ѧ���ڹ���ѧ��λ�����</span></b></p> 
  <div align=center> 
    <table  class="printstyle" border="0" align="center" style="width: 95%">
      <tr height="40px">
        <td width=96 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����</span></p></td> 
        <td width=113 colspan=4>&nbsp;${rs.xm }</td> 
        <td width=79 colspan=3> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>ѧ��</span></p></td> 
        <td width=116 colspan=2>&nbsp;${rs.xh }</td> 
        <td width=79> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>רҵ�༶</span></p></td> 
        <td width=90>&nbsp;${rs.zymc }&nbsp;${rs.bjmc }</td> 
        <td width=110 rowspan=3> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
									border="0" align="absmiddle" style="width:125;height:150" />
		</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=96 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>�Ա�</span></p></td> 
        <td width=66 colspan=2>&nbsp;${rs.xb } </td> 
        <td width=47 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����</span></p></td> 
        <td width=79 colspan=3>&nbsp; ${rs.mzmc } </td> 
        <td width=116 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>��������</span></p></td> 
        <td width=169 colspan=2> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����'>
  			<%HashMap<String,String>rs=(HashMap<String,String>)request.getAttribute("rs");
  			if(StringUtils.isNull(rs.get("csrq"))){
  			 %>
  			 &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
  			<%
  			}else{
  			%>
  			<%=rs.get("csrq").substring(0,4)%>��
  			<%=rs.get("csrq").substring(4,6)%>��
  			<%=rs.get("csrq").substring(6,8)%>��
  			<%} %>
  		</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>������ò</span></p></td> 
        <td width=126 colspan=5>&nbsp; ${rs.zzmmmc }</td> 
        <td width=116 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>�κ�ѧ���ɲ�ְ��</span></p></td> 
        <td width=169 colspan=2>&nbsp; </td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4 rowspan=2> <p align=center style='text-align:center;line-height:110%;
  layout-grid-mode:char'><span style='font-size:12.0pt;line-height:110%;
  font-family:����'>��Ժ�ڲμӹ������ڹ���ѧ��λ</span></p></td> 
        <td width=242 colspan=7 rowspan=2>&nbsp; </td> 
        <td width=79> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>��ϵ�绰</span></p></td> 
        <td width=199 colspan=2>&nbsp;</td> 
      </tr> 
      <tr height="40px"> 
        <td width=79> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>�Ƿ��϶�Ϊ��ͥ��������ѧ��</span></p></td> 
        <td width=199 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����<span lang=EN-US>&nbsp; </span>����</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>���ü��س�</span></p></td> 
        <td width=521 colspan=10>&nbsp; </td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>�������ڹ���ѧ��λ��Ժ�ڣ�</span></p></td> 
        <td width=321 colspan=8> <p><span lang=EN-US style='font-size:12.0pt;font-family:����'>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3.</span></p></td> 
        <td width=90> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>�Ƿ���ӵ���</span></p></td> 
        <td width=110> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����<span lang=EN-US>&nbsp; </span>����</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=404 colspan=11> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>���ڿ���ʱ��󻮡��̡�</span></p></td> 
        <td width=278 colspan=3 rowspan=6 valign=top> <p><b><span style='font-size:14.0pt;
  font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;�����ڲμ��ڹ���ѧ�����У��ϸ����ء�������ѧԺ¹ɽѧԺ�ڹ���ѧ����취����<bean:message key="lable.xb" />��������ع涨��ͬʱ�������и�λְ��</span></b></p> 
          <p align="right"><span style='font-size:14.0pt;
  font-family:����_GB2312'>ѧ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="right" style='word-break:break-all'><span
  style='font-size:14.0pt;font-family:����_GB2312'>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp; </span>��&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr> 
        <td width=44>&nbsp; </td> 
        <td width=58 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>��һ</span></p></td> 
        <td width=60 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>�ܶ�</span></p></td> 
        <td width=60 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����</span></p></td> 
        <td width=60> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����</span></p></td> 
        <td width=60 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����</span></p></td> 
        <td width=60> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����'>����</span></p></td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:����'>��һ����</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:����'>�����Ľ�</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:����'>��������</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:����'>���߰˽�</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=683 colspan=14 valign=top> <p><span style='font-size:12.0pt;font-family:����'>����ϵ�����</span></p> 
          <p  align="right"><span style='font-size:12.0pt;
  font-family:����'>ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p style='word-break:break-all'  align="right"><span
  style='font-size:12.0pt;font-family:����'>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span
  lang=EN-US>&nbsp;&nbsp; </span>��&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr> 
        <td width=683 colspan=14 valign=top> <p><span style='font-size:12.0pt;font-family:����'>�ù���λ�����</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:����'>���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:����'>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp; </span>��&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr> 
        <td width=683 colspan=14 valign=top> <p><span style='font-size:12.0pt;font-family:����'>ѧ�����������������</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:����'>���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:����'>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp; </span>��&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr height=0> 
        <td width=44></td> 
        <td width=52></td> 
        <td width=6></td> 
        <td width=59></td> 
        <td width=1></td> 
        <td width=46></td> 
        <td width=14></td> 
        <td width=60></td> 
        <td width=5></td> 
        <td width=56></td> 
        <td width=60></td> 
        <td width=79></td> 
        <td width=90></td> 
        <td width=110></td> 
      </tr> 
    </table> 
  </div> 
  </td>
 </tr>
 <tr>
 	<td align="center" >
 		<table width="90%" border="0" id="theTable" align="center">
 		<tr>
 		  <td>
 		  <p align="left"><b><span style='font-family:
			����'>��ע��1.</span>�˱���ȡ��ӦƸ�ʸ�ͬѧ�μ�˫ѡ���ƾ֤��2.</span>ȡ�ô˱��ͬѧ�����Ʊ��ܣ�ӦƸʱ�����˵�λͶ��<u>�˱�</u>��<span
			lang=EN-US>3.</span>���ڴ˴�˫ѡ����δ��¼���ߣ��뼰ʱ���˱�ԭ������Ժѧ�����������������浵��һ�и�λ��ȱ�������Ƽ���</span></b></p> 
			</td>
		</tr>
		</table>
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
    <p>&nbsp;</p>
</body>
</html>


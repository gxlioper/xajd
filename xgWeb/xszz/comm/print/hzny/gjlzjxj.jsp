<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	<html:form action="/typj" method="post">
		<p align=center style='text-align:center;layout-grid-mode:char'><b><span style='font-size:18.0pt;font-family:
����'>������־��ѧ������������</span></b></p> 
  <span style='font-size:12.0pt;
font-family:����'>ѧУ��${xxmc }&nbsp;&nbsp;<bean:message key="lable.xb" />��${rs.xymc }&nbsp;&nbsp;�༶��${rs.bjmc }&nbsp;&nbsp;ѧ�ţ�${rs.xh }</span> 
 <table width="100%" class="printstyle" align="center">
    <tr> 
      <td width="7%" rowspan=4 style="height: 70px"> <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>����<br/>���</span></b></p></td> 
      <td width="11%" > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>����</span></p></td> 
      <td width="15%" colspan=2 align=center>&nbsp;${rs.xm }</td> 
      <td width="10%" > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>�Ա�</span></p></td> 
      <td width="10%" align=center>&nbsp;${rs.xb }</td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��������</span></p></td> 
      <td width="18%" colspan=2 align=center>&nbsp; ${rs.csrq }</td> 
      <td    rowspan=4 align=center> <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'><img
												src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
												width="100" height="100" /></span></b></p></td> 
    </tr> 
    <tr> 
      <td width=84 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>����</span></p></td> 
      <td width=100 colspan=2 align=center>&nbsp;${rs.mzmc } </td> 
      <td width=75 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>������ò</span></p></td> 
      <td width=69 align=center>&nbsp;${rs.zzmmmc } </td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ѧʱ��</span></p></td> 
      <td width=116 colspan=2 align=center>&nbsp; ${rs.rxrq }</td> 
    </tr> 
    <tr> 
      <td width=84 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ͥסַ</span></p></td> 
      <td width=244 colspan=4 align=center>&nbsp;${rs.jtdz } </td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>�������</span></p></td> 
      <td width=116 colspan=2 align=center>&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=84 > <p align=center style='text-align:center'><span
  style='font-size:9.5pt;font-family:����;"Times New Roman";"Times New Roman"'>���֤����</span></p></td> 
      <td width=244 colspan=4 align=center>&nbsp;${rs.sfzh } </td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ϵ�绰</span></p></td> 
      <td width=116 colspan=2 align=center>&nbsp;${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <td width=48 rowspan=3 > <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>ѧϰ<br/>���</span></b></p></td> 
      <td width=623 colspan=9 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ѧ����޿γ�</span><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:����;"Times New Roman"'>�ţ���������</span><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:����;"Times New Roman"'>�ţ�����</span><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:����;"Times New Roman"'>��</span></p></td> 
    </tr> 
    <tr> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ѧ�����������ƺ�</span></p></td> 
      <td colspan=7 >&nbsp; </td> 
    </tr> 
    <tr> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>ѧϰ�ɼ�������������/<br/>��������</span></p></td> 
      <td colspan=3 >&nbsp; </td> 
      <td colspan=2 align="center">�ۺϲ����ɼ�������<br/>������/��������</td> 
      <td colspan=2 >&nbsp; </td> 
    </tr> 
    <tr> 
      <td rowspan=2 > <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>��ͥ<br/>����<br/>���</span></b></p></td> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ͥ����</span></p></td> 
      <td colspan=3 align="center">
		<logic:present name="rs" property="jthk">
			<logic:equal value="����" property="jthk" name="rs">
				����
			</logic:equal>
			<logic:equal value="ũ��" property="jthk" name="rs">
				ũ��
			</logic:equal>
		</logic:present>
		<logic:notPresent name="rs" property="jthk">
			A������ B��ũ��
		</logic:notPresent>
      </td> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ͥ�˿�����</span></p></td> 
      <td align="center" colspan="2">
		${rs.jtrs }
	</td>
    </tr> 
    <tr> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��ͥ��������</span></p></td> 
      <td align="center" colspan="3">
		${rs.jtyzsr }
	</td>
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>�˾�������</span></p></td> 
      <td align="center" colspan="2">
		${rs.jtrjysr }
	</td>
    </tr> 
    <tr> 
      <td  rowspan=5 > <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>��ͥ<br/>��Ա<br/>���</span></b></p></td> 
      <td  colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>����</span></p></td> 
      <td  colspan=3 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>����</span></p></td> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>�뱾�˹�ϵ</span></p></td> 
      <td  colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:����;"Times New Roman"'>��������ѧϰ����λ</span></p></td> 
    </tr> 
		<%
		ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
		int len=0;
		if(cyList!=null && cyList.size()>0){
			len=cyList.size();
		}
		
		for(int i=0;i<len;i++){
			HashMap<String,String>cyMap=cyList.get(i);
		%>
		<tr>	
			<td colspan=2 align=center>
				<div align="center">
					<%=cyMap.get("cyxm")%>
				</div>
			</td>
			<td align=center  colspan=3>
				<div align="center">
					<%=cyMap.get("cynl")%>
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					<%=cyMap.get("mc")%>
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					<%=cyMap.get("cygzdw")%>
				</div>
			</td>
		</tr>
		<%
		}
		%> 
		 
		 
		<%
		
		for(int i=0;i<4-len;i++){
		%>
		<tr>
			 <td colspan=2 align=center>
				<div align="center">
					&nbsp;
				</div>
			</td>
			<td align=center  colspan=3>
				<div align="center">
					&nbsp;
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					&nbsp;
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					&nbsp;
				</div>
			</td>
		</tr>
		<%
		}
		%>
    <tr> 
      <td width=671 colspan=10 > <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>���˱�֤�������������ʵ��Ч��</span></b></p> 
        <p align=center style='text-align:center'><b><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
  style='font-family:����;"Times New Roman"'>������ǩ����</span></p> 
        <p align=center style='text-align:center;'><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��</span><span lang=EN-US>&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��</span></p></td> 
    </tr> 
    <tr> 
      <td style="height:135px"> <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>�༶<br/>���</span></b></p></td> 
      <td colspan=9 style="text-align: right;vertical-align: bottom">�೤ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
        <p  style='text-align:right;
  line-height:23.0pt;'>��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td style="height:135px"> <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'><bean:message key="lable.xb" /><br/>���</span></b></p></td> 
      <td  colspan=9 > <p style='  
  line-height:25.0pt;'><span
  style='font-size:12.0pt;font-family:����;text-indent: 35px'>ͨ����飬�����������ʵ����<bean:message key="lable.xb" />��ѡ������ȫԺ��Χ�ڹ�ʾ<u><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u>�죬�����飬����ȷ����ͬѧΪ<span
  lang=EN-US>2010-2011</span>ѧ�������־��ѧ���ѡ�ˡ�</span></p> 
        <p align="right">�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> 
        <p align="right" >��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td style="height:135px"> <p align=center style='text-align:center'><b><span style='font-family:����;"Times New Roman";"Times New Roman"'>ѧУ<br/>���</span></b></p></td> 
      <td  colspan=9 > <p style='  
  line-height:25.0pt;'><span
  style='font-size:12.0pt;font-family:����;text-indent: 35px'>�����󣬲���ȫУ��Χ�ڹ�ʾ<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u>�죬�����飬�ֱ���ͬ���ͬѧ��<span lang=EN-US>2010-2011</span>ѧ��ȹ�����־��ѧ��</span></p> 
        <p align="right"><span style='font-family:
  ����;&quot;Times New Roman&quot;'>����ũҵ��ѧ�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
        <p align="right"><span style='font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
    </tr> 
    <tr height=0> 
      <td width=48 ></td> 
      <td width=84 ></td> 
      <td width=76 ></td> 
      <td width=24 ></td> 
      <td width=75 ></td> 
      <td width=69 ></td> 
      <td width=72 ></td> 
      <td width=68 ></td> 
      <td width=48 ></td> 
      <td width=107 ></td> 
    </tr> 
  </table> 
  <p><span style='font-family:����;
&quot;Times New Roman&quot;'>˵����</span><span lang=EN-US>1.</span><span
style='font-family:����;"Times New Roman"'>�γ̳ɼ��綨&#8212;���㣺</span><span lang=EN-US>85</span><span
style='font-family:����;"Times New Roman"'>������&#8212;</span><span lang=EN-US>100</span><span
style='font-family:����;"Times New Roman"'>�֣����ã�</span><span lang=EN-US>75</span><span
style='font-family:����;"Times New Roman"'>������&#8212;</span><span lang=EN-US>85</span><span style='font-family:
����;&quot;Times New Roman&quot;'>�֡�</span></p> 
  <p><span
lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.</span><span style='font-family:����;
&quot;Times New Roman&quot;'>ѧϰ�ɼ����㷽��ͬ�ۺϲ����������޿κ�רҵѡ�޿�ƽ���ɼ�</span><span
lang=EN-US>=</span><span style='font-family:����;
&quot;Times New Roman&quot;'>�ƿγ̳ɼ���ѧ��</span><span lang=EN-US>/</span><span
style='font-family:����;"Times New Roman"'>�ƿγ�ѧ�֡�</span></p> 
	</html:form>
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
</body>
</html:html>

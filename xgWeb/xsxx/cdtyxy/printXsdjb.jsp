<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
  
</style>
<style>
*{font-family:����!important;}
</style>
<body> 
<html:form action="/stu_info_add.do" method="post">
<center>
  <table width="90%" align="center" border="0" class="printStyle"> 
   <div class=Section1 style='layout-grid:15.6pt'>
  <h3 align="center" style="">�ɶ�����ѧԺ������������ǼǱ�</h3>
  <p align="center">
	  <span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
		ϵ(��):<bean:write name="rs" property="xymc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		רҵ:<bean:write name="rs" property="zymc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		�༶:<bean:write name="rs" property="bjmc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		ѧ��:<bean:write name="rs" property="xh"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		��Դ��:<bean:write name="rs" property="syd"/>
	  </span>
  </p>
  <div align="center">
    <table class="printStyle" border=0 cellspacing=0 cellpadding=0 >
   	 <tr>
		<td width="10%"></td>
		<td width="10%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="15%"></td>
	 </tr>
      <tr height="28px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="xm"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�Ա�</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="xb"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="jgs"/><bean:write name="rs" property="jgshi"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="mzmc"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��������</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="csrq"/></span></td>
        <td align="center" rowspan="6">
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
							border="0" style="width:160;height:200"/>
		</td>
      </tr>
      <tr height="28px">
        <td align="center" colspan="2" rowspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ѧǰѧϰ<br/>(����)��λ</span></td>
        <td align="center" colspan="4" rowspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="rxqxxdw"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>Ӧ��</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="yj"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="cz"/></span></td>
      </tr>
      <tr height="28px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="wj"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>ũ��</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="nc"/></span></td>
      </tr>
      <tr height="39px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ѧǰ<br/>�������ڵ�</span></td>
        <td align="center" colspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="rxqhkszd"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ͥ��ϸ<br/>ͨѶ��ַ</span></td>
        <td align="center" colspan="4"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="jtszd"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�ʱ�</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="yb"/></span></td>
      </tr>
      <tr height="39px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ͥ<br/>��ϵ��</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="jtlxr"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ϵ<br/>�绰</span></td>
        <td colspan="5"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�̶��绰��<bean:write name="rs" property="gddh"/><br/>�ƶ��绰��<bean:write name="rs" property="yddh"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�뵳(��)ʱ��</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
        	<logic:notEmpty name="rs" property="rdsj"><bean:write name="rs" property="rdsj"/></logic:notEmpty>
        	<logic:empty name="rs" property="rdsj"><bean:write name="rs" property="rtsj"/></logic:empty>
        	</span></td>
      </tr>
      <tr height="28px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�س�</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="tc"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>ѧУ���Һ���</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="ssbh"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���֤����</span></td>
        <td align="center" colspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="sfzh"/></span></td>
      </tr>
      <tr height="40px">
        <td align="center" colspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ѧǰ��ȡ�õ��˶��ɼ����ȼ�<br/>�ƺ��Լ��������</span></td>
        <td align="center" colspan="10"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="qk"/></span></td>
      </tr>
      <tr height="28px">
        <td align="center" rowspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���˼���</span></td>
        <td align="center" rowspan="3" colspan="4"><bean:write name="rs" property="grjl"/></td>
        <td align="center" rowspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�߿�<br/>�ɼ�</span></td>
        <td align="center" rowspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�Ŀ�</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�ܷ�</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ѧ</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�ۺ�</span></td>
      </tr>
      <tr height="28px">
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
      </tr>
      <tr height="39px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�忼</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�ܷ�</span></td>
        <td align="center"></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>ר<br/>��</span></td>
        <td align="center" colspan="3"></td>
      </tr>
      <tr height="39px">
        <td align="center" rowspan="5"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ͥ��Ҫ��<br/>Ա������<br/>ϵ</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>�뱾��<br/>��ϵ</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>������<br/>(Ԫ)</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>����<br/>��ò</span></td>
        <td align="center" colspan="5"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���ںεغ͵�λ�κ�ְ��</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��ϵ�绰</span></td>
      </tr>
      <tr height="28px">
        <td align="center">${rs.jtcy1_xm }</td>
        <td align="center">${rs.jtcy1_nlxx }</td>
        <td align="center">${rs.jtcy1_gx}</td>
        <td align="center">&nbsp;</td>
        <td align="center">${rs.jtcy1_zzmmmc}</td>
        <td align="center" colspan="5">${rs.jtcy1_gzdz}${rs.jtcy1_zw}</td>
        <td align="center" colspan="2">${rs.jtcy1_lxdh1}</td>
      </tr>
      <tr height="28px">
        <td align="center">${rs.jtcy2_xm }</td>
        <td align="center">${rs.jtcy2_nlxx }</td>
        <td align="center">${rs.jtcy2_gx}</td>
        <td align="center">&nbsp;</td>
        <td align="center">${rs.jtcy2_zzmmmc}</td>
        <td align="center" colspan="5">${rs.jtcy2_gzdz}${rs.jtcy2_zw}</td>
        <td align="center" colspan="2">${rs.jtcy2_lxdh1}</td>
      </tr>
      <tr height="28px">
        <td align="center">${rs.jtcy3_xm }</td>
        <td align="center">${rs.jtcy3_nlxx }</td>
        <td align="center">${rs.jtcy3_gx}</td>
        <td align="center">&nbsp;</td>
        <td align="center">${rs.jtcy3_zzmmmc}</td>
        <td align="center" colspan="5">${rs.jtcy3_gzdz}${rs.jtcy3_zw}</td>
        <td align="center" colspan="2">${rs.jtcy3_lxdh1}</td>
      </tr>
      <tr height="28px">
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center" colspan="5">&nbsp;</td>
        <td align="center" colspan="2">&nbsp;</td>
      </tr>
    </table>
  </div>
  <p align="left"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
  				ע��1��������ѧ�������øֱ���д��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				�ҳ�ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��<br/>
  				&nbsp;&nbsp;&nbsp;&nbsp;2�����������УУ԰��ѧ������ҳ�����ء�</span>
</div>  
  </table>  
</center> 
<%--<div class='noPrin' align="center">--%>
<%--	 <input type='button' value='ҳ������' onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">--%>
<%--	 <input type='button' value='��ӡԤ��' onclick="try{window.onresize = doResize;WebBrowser.ExecWB(7,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">--%>
<%--	 <input type='button' value='ֱ�Ӵ�ӡ' onclick="try{window.onresize = doResize;WebBrowser.ExecWB(6,6)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">--%>
<%--</div>--%>
</html:form>

</body>
</html>

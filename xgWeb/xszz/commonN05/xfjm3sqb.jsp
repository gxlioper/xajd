<%@ page language="java" contentType="text/html; charset=GBK"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	<body>	
		<center>
			<html:form action="/n05_xszz.do" method="post">
				<p align="left">������ </p>
				<p align="center"><h4>${xxmc}</h4></p>
				<p align="center"><h4><u>&nbsp;${rs.xn}&nbsp;</u>ѧ���ͥ��������ѧ��ѧ�Ѽ���������</h4></p>
				<div align="center">
				  <table cellspacing="0" cellpadding="0" class="tbstyle">
				    <tr>
				      <td width="31" rowspan="4">
						  <p align="center">�� </p>
				          <p align="center">�� </p>
				          <p align="center">�� </p>
				          <p align="center">�� </p></td>
				      <td width="72"><p align="center">���� </p></td>
				      <td width="72" colspan="2"><p align="center">${rs.xm} </p></td>
				      <td width="96"><p align="center">�Ա� </p></td>
				      <td width="72"><p align="center">${rs.xb} </p></td>
				      <td width="83" colspan="2"><p align="center">�������� </p></td>
				      <td width="59" colspan="2"><p align="center">${rs.csrq} </p></td>
				      <td width="48"><p align="center">���� </p></td>
				      <td width="62"><p align="center">${rs.mzmc}</p></td>
				    </tr>
				    <tr>
				      <td width="72" ><p align="center">ѧ�� </p></td>
				      <td width="72" colspan="2" ><p align="center">${rs.xh} </p></td>
				      <td width="96"><p align="center">�뵵��� </p></td>
				      <td width="72"><p align="center">${rs.rdbh}</p></td>
				      <td width="132" colspan="3"><p align="center">רҵ�꼶�༶ </p></td>
				      <td width="120" colspan="3"><p align="center">${rs.zymc}${rs.nj}${rs.bjmc} </p></td>
				    </tr>
				    <tr>
				      <td width="144" colspan="3"><p align="center">����ε���ѧ�Ѽ��� </p></td>
				      <td width="204" colspan="3" align="center">
						<p> 
						<logic:equal value="ȫ��" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">ȫ��
						</logic:equal>
						<logic:notEqual value="ȫ��" name="rs" property="sqdc">
							<input type="checkbox">ȫ��
						</logic:notEqual>
						&nbsp;&nbsp;
						<logic:equal value="����" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">����
						</logic:equal>
						<logic:notEqual value="����" name="rs" property="sqdc">
							<input type="checkbox">����
						</logic:notEqual>
						&nbsp;&nbsp;
						<logic:equal value="���ּ���" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">���ּ���
						</logic:equal>
						<logic:notEqual value="���ּ���" name="rs" property="sqdc">
							<input type="checkbox">���ּ���
						</logic:notEqual>
						</p></td>
				      <td width="96" colspan="2"><p align="center">Ʒ�µȵ� </p></td>
				      <td width="120" colspan="3"><p align="center">${rs.pddd} </p></td>
				    </tr>
				    <tr>
				      <td width="108" colspan="2" align="center">��ѧ����<br/>��������д��<br/>������</td>
				      <td width="240" colspan="4" align="center"><p>${rs.szqk } </p></td>
				      <td width="96" colspan="2" align="center">
				       	           ��ʱ�μ���<br/> 
				                                ����ѧ����<br/>
				          	����ѧ���� </td>
				      <td width="120" colspan="3" align="center">
						<p>&nbsp; <logic:notEqual value="13742" name="xxdm">�μ��ڹ���ѧʱ�䣺</logic:notEqual>
							${rs.cjqgzxsj}<br/>
							<logic:notEqual value="13742" name="xxdm">������ѧ����ʱ�䣺</logic:notEqual>
							${rs.sqzxdksj }
                        </p>
                      </td>
				    </tr>
				    <tr>
				      <td width="139" colspan="3"><p align="center">��ͥ����<br/> ����֤�� </p></td>
				      <td width="456" colspan="9" valign="top"><p align="left">
						<logic:equal value="�²�֤��" name="rs" property="gczm">
							��1���²�֤��<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="�²�֤��" name="rs" property="gczm">
							��1���²�֤��<input type="checkbox">
						</logic:notEqual>
				
						<logic:equal value="��������֤��" name="rs" property="ssmzzm">
							��2����������֤��<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="��������֤��" name="rs" property="ssmzzm">
							��2����������֤��<input type="checkbox">
						</logic:notEqual>
						<br/>
						<logic:equal value="��ʿ��Ů֤��" name="rs" property="lsznzm">
							��3����ʿ��Ů֤��<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="��ʿ��Ů֤��" name="rs" property="lsznzm">
							��3����ʿ��Ů֤��<input type="checkbox">
						</logic:notEqual>

						<logic:equal value="�Ÿ���֤ͥ��" name="rs" property="yfjtzm">
							��4���Ÿ���֤ͥ��<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="�Ÿ���֤ͥ��" name="rs" property="yfjtzm">
							��4���Ÿ���֤ͥ��<input type="checkbox">
						</logic:notEqual>

						<logic:equal value="����֤�� " name="rs" property="qtzm">
							��5������֤��<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="����֤�� " name="rs" property="qtzm">
							��5������֤��<input type="checkbox">
						</logic:notEqual>
					</p> </td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p>�������ɣ� ��д����ͥ����������������˼���ͥ��Ա����״����Ŀǰ��ѧϰ������״̬���� </p>
				          <p>${rs.sqyy}</p>
				          <p>&nbsp; </p>
				          <p>&nbsp; </p>
				          <p>���˱�֤������������ʵ����� </p>
				          <p align="right">������ǩ�������������������ꡡ���¡����� </p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p>�༶���飺 ��д��Ʒ�б��֡�ѧϰ��������״�ͺ�ʵ��ļ�ͥ�������ѳ̶Ⱥ���У������������� </p>
				          <p>${rs.bjpy} </p>
				          <p>&nbsp; </p>
				          <p>&nbsp; </p>
				          <p align="right">ǩ�����������������ꡡ���¡����� </p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />�������ʾ����� </p>
				          <p>${rs.xyshyj}</p>
				          <p>${rs.xygsjg}</p>          
                          <p>&nbsp; </p>
				          <p>&nbsp; </p>
				          <p align="right">ǩ���� �������������ꡡ���¡����� </p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p>ѧ���������������������� </p>
				          <p align="right">�����ʵ���������ѧ�Ѽ��⣺
				         	(1)
				         <logic:equal value="ȫ��" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">ȫ��
						</logic:equal>
						<logic:notEqual value="ȫ��" name="rs" property="sqdc">
							<input type="checkbox">ȫ��
						</logic:notEqual>
						(2)
						<logic:equal value="����" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">����
						</logic:equal>
						<logic:notEqual value="����" name="rs" property="sqdc">
							<input type="checkbox">����
						</logic:notEqual>
						(3)
						<logic:equal value="���ּ���" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">���ּ���
						</logic:equal>
						<logic:notEqual value="���ּ���" name="rs" property="sqdc">
							<input type="checkbox">���ּ���
						</logic:notEqual>������ѧ��<u>&nbsp;${rs.jmje}&nbsp;</u> Ԫ�� </p>
				          <p align="right">ǩ���� �������������ꡡ���¡�����</p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />���������쵼С���������� </p>
				          <p>${rs.xxshyj} </p>
				          <p align="right">ǩ���� �������������ꡡ���¡����� </p></td>
				    </tr>
				  </table>
				</div>
				<p align="center">&nbsp; </p>
		</html:form>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

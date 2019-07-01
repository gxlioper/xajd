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
			<html:form action="/n05_xszz.do" method="post">
				 <div align="center" style="font-size:20px;font:'����' "><b>${xxmc}ѧ��ѧ�ѻ��������</b></div>
				<p align="right"><strong>ѧ��: </strong><strong>${rs.xh}
				<logic:empty name="rs" property="xh">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:empty></strong></p>
				<div align="center">
				  <table cellspacing="0" cellpadding="0" class="tbstyle" width="95%">
				    <tr>
				      <td colspan="4" width="10%"><p align="center"><strong>�� �� </strong></p></td>
				      <td colspan="4" width="13%"><p align="center">${rs.xm}&nbsp;</p></td>
				      <td colspan="4" width="10%"><p align="center"><strong>�Ա� </strong></p></td>
				      <td colspan="2" width="6%"><p align="center">&nbsp;${rs.xb}</p></td>
				      <td colspan="6" width="20%"><p align="center"><strong>�꼶רҵ�༶ </strong></p></td>
				      <td colspan="4" width="20%"><p>${rs.nj}${rs.zymc}${rs.bjmc}</p></td>
				      <td colspan="2" width="7%"><p><strong>���֤�� </strong></p></td>
				      <td colspan="4" width="25%"><p>&nbsp; ${rs.sfzh}</p></td>
				    </tr>
				    <tr>
				      <td colspan="4"><p align="center"><strong>������ò </strong></p></td>
				      <td colspan="4"><p align="center">${rs.zzmmmc}&nbsp;</p></td>
				      <td colspan="6"><p align="center"><strong>�������ڵ� </strong></p></td>
				      <td colspan="10"><p align="center">${rs.hkszd}</p></td>
				      <td colspan="2"><p><strong>���˵绰 </strong></p></td>
				      <td colspan="4" valign="top"><p>&nbsp; ${rs.sjhm}</p></td>
				    </tr>
				    <tr>
				      <td colspan="4"><p align="center"><strong>�����ȵ� </strong></p></td>
				      <td colspan="4"><p align="center">${rs.dydd}&nbsp;</p></td>
				      <td colspan="13"><p align="center"><strong>��ͥסַ���������� </strong></p></td>
				      <td colspan="9"><p>&nbsp; ��ַ��${rs.jtdz}�ʱࣺ${rs.jtyb}</p></td>
				    </tr>
				    <tr>
				      <td height="16" colspan="6"><p align="center"><strong>������ֽ��� </strong></p></td>
				      <td colspan="15"><p>&nbsp; ${rs.hjjl}</p></td>
				      <td colspan="5"><p align="center"><strong>����ϵ�ҳ��绰 </strong></p></td>
				      <td colspan="4"><p>&nbsp; ${rs.lxdh1}</p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center"><strong>����Υ�ʹ��� </strong></p></td>
				      <td colspan="7"><p align="center">&nbsp; ${rs.sfywjxx}</p></td>
				      <td colspan="15"><p align="center"><strong>�Ͷ��ڼ��ۼƲ�������޿����� </strong></p></td>
				      <td colspan="2"><p>&nbsp; ${rs.ljbjgbxkms}</p></td>
				    </tr>
				    <tr>
				      <td colspan="2" rowspan="2"><p align="center"><strong>��ͥ�� </strong></p>
				          <p align="center"><strong>����Դ </strong></p></td>
				      <td colspan="11" rowspan="2"><p align="center">&nbsp; ${rs.jtsrly}</p></td>
				      <td colspan="9" rowspan="2"><p align="center"><strong>ȫ���ͥ�� </strong></p>
				          <p align="center"><strong>�������� </strong></p></td>
				      <td width="95" rowspan="2"><p align="center">&nbsp; ${rs.jtnsr} </p></td>
				      <td colspan="5"><p align="center"><strong>��ͥ�˿��� </strong></p></td>
				      <td colspan="2"><p>&nbsp; ${rs.jtrks}</p></td>
				    </tr>
				    <tr>
				      <td colspan="5"><p align="center"><strong>�˾������� </strong></p></td>
				      <td colspan="2"><p>&nbsp; ${rs.jtrjysr}</p></td>
				    </tr>
				    <tr>
				      <td colspan="13"><p><strong>�� ͥ �� �� </strong></p></td>
				      <td colspan="17">
						<p align="center">
						<strong>
						˫�ף� ��
						<logic:equal value="��" name="sfdq">
						 ���ף� �� ��  
						</logic:equal>
						<logic:notEqual value="��" name="sfdq">
						 ���ף� ��  
						</logic:notEqual>
						���죨 ��
						<logic:equal value="��" name="sflszn">
						  �������� ��  
						</logic:equal>
						<logic:notEqual value="��" name="sflszn">
						  ������ ��  
						</logic:notEqual>
						<logic:equal value="��" name="sfgc">
						   �¶����� ��
						</logic:equal>
						<logic:notEqual value="��" name="sfgc">
						  �¶��� �� 
						</logic:notEqual>
						 </strong>  
					
					  </p></td>
				    </tr>
				    <tr>
				      <td width="56" rowspan="6"><p align="center"><strong>��</strong><strong>ͥ</strong><strong>��Ҫ��Ա </strong></p>
				          </td>
				      <td colspan="6"><p align="center"><strong>���� </strong></p></td>
				      <td colspan="6"><p align="center"><strong>��ν </strong></p></td>
				      <td colspan="4"><p align="center"><strong>������ </strong></p></td>
				      <td colspan="13"><p align="center"><strong>������ѧϰ��λ </strong></p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center">${rs.jtcy1_xm}&nbsp;</p></td>
				      <td colspan="6"><p align="center">${rs.jtcy1_gx}&nbsp;</p></td>
				      <td colspan="4"><p align="center">${rs.jtcy1_ysr}&nbsp;</p></td>
				      <td colspan="13"><p align="center">${rs.jtcy1_gzdwmc}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center">${rs.jtcy2_xm}</p></td>
				      <td colspan="6"><p align="center">${rs.jtcy2_gx}&nbsp;</p></td>
				      <td colspan="4"><p align="center">${rs.jtcy2_ysr}&nbsp;</p></td>
				      <td colspan="13"><p align="center">${rs.jtcy2_gzdwmc}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center">${rs.jtcy3_xm}&nbsp;</p></td>
				      <td colspan="6"><p align="center">${rs.jtcy3_gx}&nbsp;</p></td>
				      <td colspan="4"><p align="center">${rs.jtcy3_ysr}&nbsp;</p></td>
				      <td colspan="13"><p align="center">${rs.jtcy3_gzdwmc}&nbsp;</p></td>
				    </tr>
					<tr>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="4"><p align="center">&nbsp;</p></td>
				      <td colspan="13"><p align="center">&nbsp;</p></td>
				    </tr>
					<tr>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="4"><p align="center">&nbsp;</p></td>
				      <td colspan="13"><p align="center">&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td width="56" rowspan="9"><p align="center"><strong>�� </strong></p>
				          <p align="center"><strong>�� </strong></p>
				          <p align="center"><strong>�� </strong></p>
				          <p align="center"><strong>�� </strong></p>
				          <p align="center"><strong>�� </strong></p>
				          <p align="center"><strong>&nbsp; </strong></p></td>
				      <td colspan="12"><p align="center"><strong>������Ŀ </strong></p></td>
				      <td colspan="9"><p align="center"><strong>��һѧ���� </strong></p></td>
				      <td colspan="3"><p align="center"><strong>�ڶ�ѧ���� </strong></p></td>
				      <td colspan="4" nowrap="nowrap"><p align="center"><strong>����ѧ���� </strong></p></td>
				      <td width="267"><p align="center"><strong>��������� </strong></p></td>
				    </tr>
				    <tr>
				      <td height="25" colspan="12"><p align="center">����/��Դ����ѧ����</p></td>
				      <td colspan="9"><p align="center">${rs.zxdkxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.zxdkxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.zxdkxn3}</p></td>
				      <td width="267"><p align="center">${rs.zxdkzje}</p></td>
				    </tr>
				    <tr>
				      <td height="25" colspan="12"><p align="center">��ѧ��</p></td>
				      <td colspan="9"><p align="center">${rs.zxjxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.zxjxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.zxjxn3}</p></td>
				      <td width="267"><p align="center">${rs.zxjzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">��ѧ��</p></td>
				      <td colspan="9"><p align="center">${rs.jxjxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.jxjxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.jxjxn3}</p></td>
				      <td width="267"><p align="center">${rs.jxjzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">�ڹ���ѧ</p></td>
				      <td colspan="9"><p align="center">${rs.qgzxxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.qgzxxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.qgzxxn3}</p></td>
				      <td width="267"><p align="center">${rs.qgzxzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">ѧ�Ѽ���</p></td>
				      <td colspan="9"><p align="center">${rs.xfjmxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.xfjmxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.xfjmxn3}</p></td>
				      <td width="267"><p align="center">${rs.xfjmzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">��ʱ���Ѳ��� </p></td>
				      <td colspan="9"><p align="center">${rs.lsknbzxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.lsknbzxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.lsknbzxn3}</p></td>
				      <td width="267"><p align="center">${rs.lsknbzzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">������ѧ��</p></td>
				      <td colspan="9"><p align="center">${rs.cszxjxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.cszxjxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.cszxjxn3}</p></td>
				      <td width="267"><p align="center">${rs.cszxjzje}</p></td>
				    </tr>
				    <tr>
				      <td height="25" colspan="29"><p><strong>1. </strong><b>����/��Դ����ѧ���� 2.��ѧ�� 3.��ѧ�� 4.�ڹ���ѧ 5.ѧ�Ѽ��� 6.��ʱ���Ѳ��� 7.������ѧ�� </b></td>
				    </tr>
				    <tr>
				      <td colspan="5" height="20"><b>����ѧ�ѻ�����Ҫԭ�� </b></td>
				      <td colspan="25"><p>&nbsp; ${rs.sqyy}<strong>&nbsp; </strong></p>
				          <p><strong>&nbsp; </strong></p></td>
				    </tr>
				    <tr>
				      <td colspan="5" valign="top"><p align="center"><strong>������� </strong></p></td>
				      <td colspan="5"><p>&nbsp; ${rs.hjje}</p></td>
				      <td colspan="12"><p align="center"><b>Ԥ�ƽ���ʱ�䡢��� </b></p></td>
				      <logic:empty name="rs" property="yjdycjfsj">
				      <td colspan="14"><p><b>��һ�Σ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� �ڶ��Σ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</b></p></td>
				      </logic:empty>
				      <logic:notEmpty name="rs" property="yjdycjfsj">
				      <td colspan="14"><p><b>��һ�Σ�</b>ʱ�䣺${rs.yjdycjfsj}��${rs.yjdycjfje} <b>�� �ڶ��Σ�</b>ʱ�䣺${rs.yjdecjfsj}��${rs.yjdecjfje} <b>��</b></p></td>
				      </logic:notEmpty>
				    </tr>
				    <tr>
				      <td colspan="15"><b>��ͥ�Ƿ�����������֤��</b>(�����ʵ�����,���ұߵ��ĸ�ѡ����ѡ��)</td>
				      <td colspan="15" rowspan="2">
							<b>
							<logic:equal value="����֤" name="rs" property="tkz">
							1.������֤������ ��
							</logic:equal>
							<logic:notEqual value="����֤" name="rs" property="tkz">
							1.������֤����  ��
							</logic:notEqual>

							<logic:equal value="��������֤" name="rs" property="zdshbzz">
							 2.����������֤���� �̣�
							</logic:equal>
							<logic:notEqual value="��������֤" name="rs" property="zdshbzz">
							 2.����������֤����  ��
							</logic:notEqual>
							<logic:equal value="������֤" name="rs" property="shfzz">
							3.��������֤������ 
							</logic:equal>
							<logic:notEqual value="������֤" name="rs" property="shfzz">
							3.��������֤������
							</logic:notEqual>
							4.��<logic:equal value="�أ��С�����������֤��" name="rs" property="zxzm">
										C
							</logic:equal>
							<logic:equal value="�ؽֵ�֤��" name="rs" property="zxzm">
										B
							</logic:equal>
							<logic:equal value=" ���磩֤��" name="rs" property="zxzm">
										A
							</logic:equal> ��֤����A. ���磩 B. �ֵ�  C. �أ��С�����������
							

				      		</br>
							</b>
				       		 ����֤����֤���븽��ظ�ӡ��
					  </td>
				    </tr>
				    <tr>
				      <td colspan="11"><p><strong>��ͥ���ڵ����������� </strong></p></td>
				      <td colspan="4"><p>&nbsp;${rs.jtszdzdshbzx}</p></td>
				    </tr>
				    <tr>
				      <td colspan="15">
						<p align="center"><b>�ڵ����������ܹ��κξ������Ѳ�</b><br>
				          		<b>��������˵���������ʡ����� </b></p>
					  </td>
				      <td colspan="15">
						  <p>&nbsp; ${rs.xsjjknbzqk} </p>
                      </td>
				    </tr>
				    <tr>
				      <td colspan="6">
						 <p><strong>��ĸ�Ƿ�֪��ѧ�ѻ����� </strong><br></p>
				          <p><strong>&nbsp; </strong></p>
				          <p>&nbsp; ${rs.fmsfzxxfhj}</p>
				          <p><strong>&nbsp; </strong></p>
					  </td>
				      <td colspan="10">
						  <p><strong>�����γ������ : </strong></p>
				          <p>${rs.fdyshyj}</p>
						  <p><strong>&nbsp; </strong></p>				    
				          <p><strong>������ǩ���� </strong></p>
				          <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong></p>        
					  </td>
				      <td colspan="9">
						<p><strong>��Ժ / ϵ������ : </strong><br></p>
				        <p>${rs.xyshyj}</p>
				        <p><strong>&nbsp; </strong></p>
				        <p><strong>��Ժ / ϵ���� : </strong></p>
				        <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></p>        
					  </td>
				      <td colspan="5">
						<p><strong>ѧ���������� ��</strong></p>
				        <p>${rs.xxshyj}</p>
				        <p><strong>&nbsp; </strong></p>
				        <p ><strong>ѧ�������£� </strong></p>
				        <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></p>
					</td>
				    </tr>
				    <tr>
				      <td colspan="3"><p align="center"><strong>��ע </strong></p></td>
				      <td colspan="27"><p><strong>ѧ�ѱ�����ѧ����ҵ��Уǰ���塣 </strong></p></td>
				    </tr>
				  </table>
				</div>
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

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
			<html:form action="/jtqkdc" method="post">
				<p align="left" style=""><strong>����1�� </strong></p>
				<p align="center"><h3>�ߵ�ѧУѧ������ͥ��������</h3></p>
				<p align="center"><h5>&nbsp;ѧУ��<u>${xxmc }</u>&nbsp;&nbsp;Ժ��ϵ����<u>${rs.xymc}</u>&nbsp;&nbsp;רҵ�� <u>${rs.zymc}</u>&nbsp;&nbsp;�꼶��<u>${rs.nj}</u></h5></p>
				<table cellspacing="0" cellpadding="0" class="tbstyle" width="90%">
				  <tr>
				    <td width="8%" rowspan="4"><p align="center"><strong>ѧ�����˻������ </strong></p></td>
				    <td colspan="2" width="10%"><p align="center">�� �� </p></td>
				    <td colspan="5" width="12%"><p align="center">${rs.xm}</p></td>
				    <td width="10%"><p align="center">�� �� </p></td>
				    <td colspan="2" width="10%"><p align="center">${rs.xb} </p></td>
				    <td colspan="3" width="12%"><p align="center">�������� </p></td>
				    <td colspan="3" width="18%"><p align="center">${rs.csrq} </p></td>
				    <td colspan="2" width="10%"><p align="center">�� �� </p></td>
				    <td width="10%"><p align="center">${rs.mzmc} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">���֤���� </p></td>
				    <td colspan="6"><p align="center">${rs.sfzh}</p></td>
				    <td colspan="2"><p align="center">������ò </p></td>
				    <td colspan="3"><p align="center">${rs.zzmmmc}</p></td>
				    <td colspan="3"><p align="center">��ѧǰ���� </p></td>
				    <td colspan="3"><p align="center">
				    <logic:equal value="����" name="rs" property="rxqhkxz">
					<input type="checkbox" value="����" checked="checked">����
					<input type="checkbox" value="ũ��">ũ��
				    </logic:equal>
				    <logic:equal value="ũ��" name="rs" property="rxqhkxz">
					<input type="checkbox" value="����">����
					<input type="checkbox" value="ũ��" checked="checked">ũ��
				    </logic:equal>
					<logic:empty name="rs" property="rxqhkxz">
				            ������&nbsp;&nbsp;��ũ�� 
                   </logic:empty>
					</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">��ͥ�˿��� </p></td>
				    <td colspan="6"><p align="center">${rs.jtrks} </p></td>
				    <td colspan="2"><p align="center">��ҵѧУ </p></td>
				    <td colspan="3"><p align="center">${rs.byxx} </p></td>
				    <td colspan="3"><p align="center">�����س� </p></td>
				    <td colspan="3"><p align="center">${rs.tc}</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">�� �� </p></td>
				    <td colspan="3" nowrap="nowrap"><p align="center">
				    <logic:equal value="��" name="rs" property="sfgc">
					<input type="checkbox" value="��" checked="checked">��
					<input type="checkbox" value="��">��
				    </logic:equal>
				    <logic:equal value="��" name="rs" property="sfgc">
					<input type="checkbox" value="��">��
					<input type="checkbox" value="��" checked="checked">��
				    </logic:equal>
					<logic:empty name="rs" property="sfgc">
				            ����&nbsp;&nbsp; ���� 
                   </logic:empty>
					</p></td>
				    <td colspan="3"><p align="center">�� �� </p></td>
				    <td colspan="5" nowrap="nowrap"><p align="center"> 
				    <logic:equal value="��" name="rs" property="sfdq">
					<input type="checkbox" value="��" checked="checked">��
					<input type="checkbox" value="��">��
				    </logic:equal>
				    <logic:equal value="��" name="rs" property="sfdq">
					<input type="checkbox" value="��">��
					<input type="checkbox" value="��" checked="checked">��
				    </logic:equal>
					<logic:empty name="rs" property="sfdq">
				             ����&nbsp;&nbsp; ���� 
                   </logic:empty>
					</p></td>
				    <td colspan="3"><p align="center">��ʿ��Ů </p></td>
				    <td colspan="3"><p align="center">
				    <logic:equal value="��" name="rs" property="sflszn">
					<input type="checkbox" value="��" checked="checked">��
					<input type="checkbox" value="��">��
				    </logic:equal>
				    <logic:equal value="��" name="rs" property="sflszn">
					<input type="checkbox" value="��">��
					<input type="checkbox" value="��" checked="checked">��
				    </logic:equal>
					<logic:empty name="rs" property="sflszn">
				             ����&nbsp;&nbsp; ���� 
                   </logic:empty>
					</p></td>
				  </tr>
				  <tr>
				    <td width="43" rowspan="2"><p align="center"><strong>��ͥͨѶ��Ϣ </strong></p></td>
				    <td colspan="4"><p align="center">��ϸͨѶ��ַ </p></td>
				    <td colspan="15"><p align="center">${rs.jtdz} </p></td>
				  </tr>
				  <tr>
				    <td colspan="4"><p align="center">�������� </p></td>
				    <td colspan="4"><p align="center">${rs.jtyb} </p></td>
				    <td colspan="3"><p align="center">��ϵ�绰 </p></td>
				    <td colspan="8"><p align="center">${rs.lxdh1qh}�����ţ���${rs.lxdh1} </p></td>
				  </tr>
				  <tr>
				    <td width="43" rowspan="7"><p align="center"><strong>��ͥ��Ա��� </strong></p></td>
				    <td colspan="2"><p align="center">���� </p></td>
				    <td colspan="2"><p align="center">����</p></td>
				    <td colspan="3"><p align="center">��ѧ�� </p>
				        <p align="center">��ϵ </p></td>
				    <td colspan="7"><p align="center">������ѧϰ����λ </p></td>
				    <td width="98"><p align="center">ְҵ </p></td>
				    <td colspan="2"><p align="center">�����루Ԫ�� </p></td>
				    <td colspan="2"><p align="center">����״�� </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">${rs.jtcy1_xm}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy1_csrq}&nbsp;&nbsp;</p></td>
				    <td colspan="3"><p align="center">${rs.jtcy1_gx}&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">${rs.jtcy1_gzdwmc}&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">${rs.jtcy1_zy}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy1_nsr}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy1_jkzk}&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">${rs.jtcy2_xm}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy2_csrq}&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">${rs.jtcy2_gx}&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">${rs.jtcy2_gzdwmc}&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">${rs.jtcy2_zy}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy2_nsr}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy2_jkzk}&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">${rs.jtcy3_xm}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy3_csrq}&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">${rs.jtcy3_gx}&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">${rs.jtcy3_gzdwmc}&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">${rs.jtcy3_zy}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy3_nsr}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy3_jkzk}&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				  </tr>				  
				  <tr>
				    <td width="43"><p align="center"><strong>Ӱ���ͥ���� </strong></p>
				        <p align="center"><strong>״���й���Ϣ </strong></p></td>
				    <td colspan="19" valign="top">
						<p style="width:100%">��ͥ�˾������� <u>&nbsp;&nbsp;${rs.jtrjnsr}&nbsp;&nbsp;&nbsp;&nbsp;</u>��Ԫ����ѧ����ѧ���ѻ��������<u>&nbsp;&nbsp;${rs.yhzzqk}
						<logic:empty name="rs" property="yhzzqk">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty></u> �� </p>
				        <p>��ͥ������Ȼ�ֺ������ <u>&nbsp;&nbsp;${rs.zszrzhqk}
						<logic:empty name="rs" property="zszrzhqk">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u>����ͥ����ͻ�������¼��� <u>&nbsp;&nbsp;${rs.zstfywsj}
						<logic:empty name="rs" property="zstfywsj">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> �� </p>
				        <p>��ͥ��Ա��м����������Ͷ������������<u>&nbsp;&nbsp;${rs.ldnlrqk}
						<logic:empty name="rs" property="ldnlrqk">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> �� </p>
				        <p>��ͥ��Աʧҵ�����<u>&nbsp;&nbsp;${rs.jtcysyqk}
							<logic:empty name="rs" property="jtcysyqk">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:empty>
						</u> ����ͥǷծ�����<u>&nbsp;&nbsp;${rs.jtqzqk}
						<logic:empty name="rs" property="jtqzqk">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> �� </p>
				        <p>���������<u>&nbsp;&nbsp;${rs.jtqtqk}
						<logic:empty name="rs" property="jtqtqk">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> �� </p></td>
				  </tr>
				  <tr>
				    <td width="43" ><p align="center"><strong>ǩ�� </strong></p></td>
				    <td><p align="center">ѧ<br/>��<br/>��<br/>�� </p></td>
				    <td colspan="6"><p align="center">&nbsp; </p></td>
				    <td colspan="2"><p align="center">ѧ��<br/>�ҳ�<br/>���<br/>���� </p></td>
				    <td colspan="5"><p align="center">&nbsp; </p></td>
				    <td colspan="2"><p align="center">ѧ����ͥ<br/>���ڵ���<br/>���ֵ�<br/>�������� </p></td>
				    <td colspan="3" valign="top" >
						<br/>
				        <p>������ǩ�֣� </p>
				        <br/>
				        <p>��λ���ƣ� </p>
				        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���Ӹǹ��£� </p>
				        <p align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;</u> �� <u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��&nbsp;&nbsp; </p>
					</td>
				  </tr>
				  <tr>
				    <td width="43" rowspan="2"><p align="center"><strong>����������Ϣ </strong></p></td>
				    <td colspan="3"><p align="center">��ϸͨѶ��ַ </p></td>
				    <td colspan="16"><p align="center">${rs.mzbtxdz} </p></td>
				  </tr>
				  <tr>
				    <td colspan="3"><p align="center">�������� </p></td>
				    <td colspan="6"><p align="center">${rs.mzbyb} </p></td>
				    <td colspan="3"><p align="center">��ϵ�绰 </p></td>
				    <td colspan="7"><p align="center">${rs.mzblxdhqh}�����ţ���${rs.mzblxdh}</p></td>
				  </tr>
				</table>
				<p align="left" style="width:85%"><strong>ע����������ο��� </strong></p>
			<div class="noprint" align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	
			</html:form>
        </center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

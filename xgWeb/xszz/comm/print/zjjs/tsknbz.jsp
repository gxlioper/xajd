<%@ page language="java" pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<br/><br/>
	<p align="center" style="font-size:23px;font-family:����">
		�㽭����ְҵ����ѧԺѧ���������Ѳ��������
	</p>
	<p align="center">
		&nbsp;
	</p>
	<table cellspacing="0" cellpadding="0" class="printtab" align="center">
		<tr class="nowrap">
			<td width="30" rowspan="4" align="center">
				ѧ�� ���� ���
			</td>
			<td width="90" align="center">
				�� ��
			</td>
			<td width="102" align="center">
				${rs.xm }
			</td>
			<td width="53" align="center">
				�Ա�
			</td>
			<td width="73" colspan="2" align="center">
				${rs.xb }
			</td>
			<td width="102" align="center">
				��������
			</td>
			<td width="139" align="center">
				${rs.csrq }
			</td>
			<td width="53" align="center">
				����
			</td>
			<td width="58" align="center">
				${rs.mzmc }
			</td>
		</tr>
		<tr>
			<td align="center">
					��������
			</td>
			<td align="center" colspan="2">
				<logic:present name="rs" property="jthk">
					<logic:equal value="����" property="jthk" name="rs">
						<span class="radic">��<em>&radic;</em></span>����&nbsp;&nbsp;&nbsp;&nbsp;��ũ��
					</logic:equal>
					<logic:equal value="ũ��" property="jthk" name="rs">
						������&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">��<em>&radic;</em></span>ũ��
					</logic:equal>
					<logic:equal value="" property="jthk" name="rs">
						������ ��ũ��
					</logic:equal>
				</logic:present>
				<logic:notPresent name="rs" property="jthk">
					������ ��ũ��
				</logic:notPresent>
			</td>
			<td align="center" colspan="2"  class="nowrap">
					������ò
			</td>
			<td align="center">
				${rs.zzmmmc }
			</td>
			<td align="center">
					�������������
			</td>
			<td align="center" colspan="2">
			</td>
		</tr>
		<tr>
			<td align="center">
					ϵ��
			</td>
			<td align="center">
				${rs.xymc }
			</td>
			<td align="center">
					�༶
			</td>
			<td align="center" colspan="3">
				${rs.bjmc }
			</td>
			<td align="center">
					������ϵ�绰
			</td>
			<td align="center" colspan="2">
				${rs.lxdh }
			</td>
		</tr>
		<tr class="nowrap">
			<td align="center">
					��ͥסַ
			</td>
			<td align="center" colspan="2">
				${rs.jtdz }
			</td>
			<td align="center" colspan="3">
					��ͥ�˾�������
			</td>
			<td align="center" colspan="3">
				${rs.jtrjysr }&nbsp;&nbsp;	Ԫ
			</td>
		</tr>
		<tr>
			<td width="30">
				<p>
					���� ����
				</p>
			</td>
			<td colspan="9" valign="bottom">
				<p align="left" style="height:110px">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
				</p>
				<p align="right">
					ѧ��ǩ�� :
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<br/>
				<p align="right">
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p align="center">
					��������ϸ���˵����
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="5" valign="top">
				<p align="left">
					����Ա�����
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
				<p>
					����Ա��ǩ�֣���
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp; ��&nbsp;&nbsp; ��&nbsp;&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
			</td>
			<td colspan="5" valign="top">
				<p align="left">
					ϵ��������
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					ϵ�쵼��ǩ�֣���
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="5" valign="top">
				<p align="left">
					ѧ������������������
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					�����������д����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ԫ
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					���Σ�ǩ�֣���
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
			</td>
			<td colspan="5" valign="top">
				<p align="left">
					ѧ������������
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					������ǩ�֣���
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="10" rowspan="2" valign="top">
				<p align="left">
					<bean:message key="lable.xb" />�ֹ��쵼���������
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="left">
					&nbsp;
				</p>
				<p align="right">
					ǩ����
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp; ��&nbsp;&nbsp; ��
				</p>
				<p align="left">
					&nbsp;
				</p>
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
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
			id="printButton">
			ֱ�Ӵ�ӡ
		</button>
	</div>

	</body>
</html>

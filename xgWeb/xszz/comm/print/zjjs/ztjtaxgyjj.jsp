<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<head>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<style>
		.radic {
		position:relative;
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
		</style>
</head>
<body>

	<p align=center>
		<b><span
			style='font-size:19.0pt;font-family:����;letter-spacing:1.0pt'>�����켯�Ű��Ĺ��������ѧ�������</span>
		</b>
	</p>
	<br />
	<br />
	<table class="printtab"
		style="font-family:����_GB2312;font-size:16px;width:100%">
		<tr style="">
			<td align="center" valign="center">
				����
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.xm }
			</td>
			<td align="center" valign="center">
				�Ա�
			</td>
			<td align="center" valign="center" >
				&nbsp;${rs.xb }
			</td>
			<td align="center" valign="center" >
				����
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.jg }
			</td>
			<td align="center" valign="center">
				����<br/>��ò
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.zzmmmc }
			</td>
		</tr>
		<tr style="">
			<td align="center" valign="center">
				<bean:message key="lable.xb" />
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.xymc}
			</td>
			<td align="center" valign="center">
				רҵ<br/>�꼶
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.nj}&nbsp;${rs.zymc}
			</td>
			<td align="center" valign="center" >
				��ϵ<br/>�绰
			</td>
			<td align="center" valign="center" colspan=3>
				&nbsp;${rs.lxdh}
			</td>
		</tr>
		<tr style="">
			<td align="center" valign="center">
				����<br/>�ɼ�
			</td>
			<td align="center" valign="center">
				&nbsp;
			</td>
			<td align="center" valign="center">
				�ۺϲ�<br/>������
			</td>
			<td align="center" valign="center">
				&nbsp;
			</td>
			<td align="center" valign="center" >
				�߿�<br/>�ɼ�
			</td>
			<td align="center" valign="center" colspan=3>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center" valign="center" height="270px">
				��ͥ
				<br />
				����
				<br />
				����
				<br />
				�����
				<br />
				��Ҫ
				<br />
				����
				<br />
				����
			</td>
			<td colspan=8>
				<p style="height: 200px">������ͥ��Ա����ͥ�����롢��ͥ����ƶ������ȣ�</p>
				<p align="right">����ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<br/>
				<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td align="center" valign="center">
				������
				<br />
				����Ҫ
				<br />
				������
				<br />
				��Ŀ��
				<br />
				���
			</td>
			<td colspan=8>
				<br/>
				��&nbsp;��
				<br />
				��&nbsp;��
				<br />
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;1����������Ŀ����<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>Ԫ
				</p>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;2����������Ŀ����<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>��������<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>Ԫ
				</p>
				<br/>
			</td>
		</tr>
		<tr height="100px">
			<td align="center" valign="center">
				ѧ Ժ
				<br />
				�� ��
			</td>
			<td colspan=8>
				<p style="height:80px"></p>
				<p align="right">�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p align="right"> ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</td>
		</tr>
		<tr height="100px">
			<td align="center" valign="center">
				ѧ У
				<br />
				�� ��
			</td>
			<td colspan=8>
				<p style="height: 60px"></p>
				<p align="right">�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p align="right"> ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</td>
		</tr>
		<tr height=0 >
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
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
</body>
</html:html>

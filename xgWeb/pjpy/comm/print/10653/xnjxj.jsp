<%@ page language="java" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<p align="center">
			<strong style="font-size:24px;font-family:����">�ɶ�����ѧԺѧ����ѧ��ǼǱ�
			</strong>
		</p>
		<p align="center" style="font-size:14px;">
			${rs.zymc }&nbsp;ϵ&nbsp;${rs.nj }&nbsp;�� ��&nbsp;${rs.pjxn
			}&nbsp;ѧ���&nbsp;${rs.pjxqmc }&nbsp;ѧ�� ��
		</p>
		<table class="printtab" width="100%" style="font-size:14px;">
			<tr>
				<td width="8%" colspan="2" align="center">
					����
				</td>
				<td width="13%" colspan="2" align="center">
					${rs.xm }
				</td>
				<td width="6%" rowspan="2" align="center">
					��<br/>
					��
				</td>
				<td width="7%" rowspan="2" align="center">
					${rs.xb }
				</td>
				<td width="6%" rowspan="2" align="center">
					��
				</td>
				<td width="12%" colspan="2" rowspan="2" align="center">
					${rs.bjmc }
				</td>
				<td width="10%" colspan="3" rowspan="2" align="center">
					��&nbsp;&nbsp;��<br/>����Ա
				</td>
				<td width="13%" colspan="3" rowspan="2" align="center">
					${rs.�Ƿ���Ա }
				</td>
				<td width="13%" colspan="2" rowspan="2" align="center">
					���κ�<br/>�ָɲ�
				</td>
				<td width="12%" colspan="2" rowspan="2" align="center">
					${rs.���κ��ָɲ� }
				</td>
			</tr>
			<tr>
				<td colspan="2"  align="center">
					ѧ��
				</td>
				<td colspan="2"  align="center">
					${rs.xh }
				</td>
			</tr>
			<tr height="50px">
				<td colspan="3"  align="center" valign="top">
					ͬ��רҵ����
				</td>
				<td colspan="5"  align="center">
					${rs.ͬ��רҵ���� }
				</td>
				<td colspan="5"  align="center">
					�ۺϲ�������
				</td>
				<td colspan="6"  align="center">
					${rs.zcpm }
				</td>
			</tr>
			<tr height="50px">
				<td colspan="4"  align="center">
					��ѧ�����
				</td>
				<td colspan="2"  align="center">
					${rs.xmxz }
				</td>
				<td colspan="5"  align="center">
					��ѧ��ȼ�
				</td>
				<td colspan="3"  align="center">
				</td>
				<td colspan="2"  align="center">
					�������
				</td>
				<td colspan="3"  align="center">
					${rs.xmje }
				</td>
			</tr>
			<tr height="220px">
				<td align="center">
					��<br/>Ҫ<br/>��<br/>��
				</td>
				<td colspan="18">
					<p valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.��Ҫ�¼� }
					</p>
				</td>
			</tr>
			<tr  height="220px">
				<td align="center">
					��<br/>��<br/>��<br/>��<br/>��<br/>��
				</td>
				<td colspan="18">
					<p valign="top" style="height:220px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj1 }
					</p>
					<p align="right">
						�꼶����ǩ�֣��£� &nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr height="220px">
				<td align="center">
					ϵ<br/>��<br/>��<br/>��
				</td>
				<td colspan="9">
					<p valign="top" style="height:200px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj2 }
					</p>
					<p align="center" valign="bottom">
						�����£� �� �� ��
					</p>
				</td>
				<td colspan="9">
					<p valign="top" style="height:10px" align="center">
						ѧ�����������
					</p>
					<p style="height:210px"  align="center">
					<br/><br/>
						${rs.shyj3 }
					</p>
					<p align="center" valign="bottom">
						�����£� �� �� ��
					</p>
				</td>
			</tr>
		</table>
		<p style="font-size:12px;">
			ע���˱�����֯��д��һʽ���ݡ� 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			ѧ������
		</p>



		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>

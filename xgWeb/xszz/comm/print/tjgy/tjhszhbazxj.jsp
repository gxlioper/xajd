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
		<center>
			<br />
			<br />
			<span style="font-size:28px;font-family:����">${rs.nd
				}������к�ʮ�ֻᲩ����ѧ��ǼǱ�</span>
			<br />
			<br />
		</center>
		<table width="100%"  class="printtab" style="font-family:����;font-weight:bold;font-size:17px">
			<tr height="40px">
				<td width="19%" align="center">
					<bean:message key="lable.xb" />���ƣ�
				</td>
				<td colspan="4" align="center"  style="font-size:14px">
					${rs.xymc }
				</td>
				<td align="center">
						���<br/>����
				</td>
				<td>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" width="15%">
						�� ��
				</td>
				<td align="center"  width="10%" style="font-size:14px">
						${rs.xm }
				</td>
				<td align="center" width="100px" width="12%" >
						�� ��
				</td>
				<td align="center"   style="font-size:14px">
						${rs.xb }
				</td>
				<td align="center" width="15%">
						��������
				</td>
				<td align="center"  style="font-size:14px">
						${rs.csrq }
				</td>
				<td rowspan="4" width="11%">
					<img
						src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
						border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
						�� ��
				</td>
				<td align="center" style="font-size:14px">
						${rs.mzmc }
				</td>
				<td align="center">
						����<br/>��ò
				</td>
				<td align="center" style="font-size:14px">
						${rs.zzmmmc }
				</td>
				<td align="center">
						��Դ��
				</td>
				<td align="center" style="font-size:14px">
						${rs.syd }
				</td>

			</tr>
			<tr height="30px">
				<td align="center">
						���֤��
				</td>
				<td colspan="2" align="center" style="font-size:14px">
						${rs.sfzh }
				</td>
				<td align="center">
						רҵ
				</td>
				<td colspan="2" align="center" style="font-size:14px">
						${rs.zymc }
				</td>
			</tr>
			<tr height="40px">
				<td align="center">
						�꼶
				</td>
				<td align="center" style="font-size:14px">
						${rs.nj }
				</td>
				<td align="center">
						���
				</td>
				<td align="center" style="font-size:14px">
						${rs.pycc }
				</td>
				<td align="center">
						��ͥ�绰
				</td>
				<td align="center" style="font-size:14px">
						${rs.jtdh }
				</td>
			</tr>
			<tr>
				<td align="center">
						�ʱ�
				</td>
				<td align="center" style="font-size:14px">
						${rs.jtyb }
				</td>
				<td align="center">
						��ͥ<br/>��ַ
				</td>
				<td colspan="4" align="center" style="font-size:14px">
						${rs.jtdz }
				</td>
			</tr>
			<tr height="120px">
				<td align="center">
					��
					<br />
					ͥ
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
				</td>
				<td colspan="7" valign="top"
					style="word-break:break-all;font-size:14px" >
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.jtjbjjqk }
				</td>
			</tr>
			<tr height="140px">
				<td align="center">
					ѧ
					<br />
					ҵ
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
				</td>
				<td colspan="7" valign="top"
					style="word-break:break-all;font-size:14px">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyjbx }
				</td>
			</tr>
			<tr height="120px">
				<td align="center">
					��
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
				</td>
				<td colspan="7" valign="top"
					style="word-break:break-all;font-size:14px">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.hghzjl }
				</td>
			</tr>
			<tr height="120px">
				<td align="center">
					ѧ
					<br />
					Ժ
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
					��
					<br />
				</td>
				<td colspan="3">
					<p style="height:90px;font-size:14px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="center">
						ѧ�������
					</p>
					<p align="center">

						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
					</p>
				</td>
				<td>
					<p align="center">
						ѧ
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
					</p>
				</td>
				<td colspan="4">
					<p align="center" style="height:90px;font-size:14px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="right">
						����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>



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

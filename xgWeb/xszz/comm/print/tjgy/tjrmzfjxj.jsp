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
		<br/>
		<br/>
		<br/>
			<span style="font-size:22px;font-family:����">���������������ѧ�������</span>
			<br />
			<br />
		</center>
		<table style="font-family:����_GB2312;font-size:14px;" width="100%"
			border="0" align="center">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;ѧУ�����ҵ��ѧ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ڣ�
				</td>
			</tr>
		</table>
		<table class="printtab" style="font-family:����_GB2312;font-size:14px;">
			<tr height="30px">
				<td rowspan="5" align="center" width="7%">
					��
					<br />
					��
					<br />
					��
					<br />
					��
				</td>
				<td align="center" width="12.5%">
					����
				</td>
				<td align="center" width="13.5%">
					${rs.xm }
				</td>
				<td align="center" width="10%">
					�Ա�
				</td>
				<td align="center" width="7%">
					${rs.xb }
				</td>
				<td align="center" width="11%">
					����
					<br />
					����
				</td>
				<td align="center" width="18%">
					${rs.csrq }
				</td>
				<td align="center" width="10%">
					����
				</td>
				<td align="center" width="14%">
					${rs.mzmc }
				</td>
				<td rowspan="3" align="center">
					<img
						src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
						border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="center" colspan="2">
					������ݺ���
				</td>
				<td align="center" colspan="3">
					${rs.sfzh }
				</td>
				<td align="center">
					��ѧʱ��
				</td>
				<td align="center" colspan="2">
					${rs.rxrq }
				</td>
			</tr>
			<tr height="30px">
				<td align="center" colspan="5">
					${xxmc }&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;
				</td>
				<td align="center">
					ѧ��
				</td>
				<td align="center" colspan="2">
					${rs.xh }
				</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">
					������ֽ���
				</td>
				<td colspan="7">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.hghzjl }
				</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">
					�������п���
				</td>
				<td colspan="7">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.yhkh }
				</td>
			</tr>
			<tr align="center" height="30px">
				<td colspan="3">
					��ѧ���ۺϲ����༶��רҵ����
				</td>
				<td colspan="3">
					${rs.zhcpbjhzypm }
				</td>
				<td colspan="2">
					��ѧ��ɼ��༶��רҵ����
				</td>
				<td colspan="2">
					${rs.cjbjhzypm }
				</td>
			</tr>
			<tr height="150px">
				<td align="center" colspan="2">
					��ѧ��ɼ�
					<br />
					���γ�����
					<br />
					��������
				</td>
				<td colspan="8" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxcj }
				</td>
			</tr>
			<tr>
				<td colspan="11" valign="top">
				<br/>
					ѧУ��������������������
					<p style="height:30px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="left" style="height:30px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
						<span style="font-size:17px">�����󣬸�ͬѧ�������������������ѧ������������ͬ���Ƽ�!</span>
					</p>
					<p style="height:30px">
					<p align="right">
						���£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="11" valign="top">
				<br/>
					ѧУ��������
					<p style="height:60px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="left" style="height:70px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
						<span style="font-size:17px">�����󣬲���
						<u>ȫУ</u>��Χ�ڹ�ʾ
						<U>&nbsp;&nbsp;��&nbsp;&nbsp;</U> �죬�����飬�ֱ���ͬ���ͬѧ���
						<u>&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp;</u> ѧ������������������ѧ��</span>
					</p>
					<p align="right">
						���£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ��
						&nbsp;&nbsp; �� &nbsp;&nbsp; ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

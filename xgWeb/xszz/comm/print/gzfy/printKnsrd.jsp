<%@ page language="java"  pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title>��ͥ��������ѧ���϶������</title>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
			<span style="font-size:22px;font-family:����">���ݷ�خְҵ����ѧԺ��ͥ��������ѧ���϶���</span>
			<br />
			<br />
		</center>
		<table class="printtab">
			<logic:iterate id="k" name="knsList">
			<tr>
				<td width="20px" rowspan="4" valign="center">
					<p valign="center" style="width:12px;word-wrap:break-word;">
						<strong>${k.xn }</strong>
					</p>
				</td>
				<td width="55" rowspan="3">
					<p align="center">
						����<br/>����
					</p>
				</td>
				<td width="54" rowspan="3">
					<p align="center">
						����<br/>�μ�
					</p>
				</td>
				<td width="235" height="37">
					<p>
						<logic:equal value="��ͥ����һ������" name="k" property="xmzzjb">
							��
						</logic:equal>
						<logic:notEqual value="��ͥ����һ������" name="k" property="xmzzjb">
							��
						</logic:notEqual>
						 A ����ͥ����һ������
					</p>
				</td>
				<td width="35" rowspan="3">
					<p>
						��������
					</p>
				</td>
				<td colspan="3" rowspan="3" valign="bottom"> 
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						����С���鳤ǩ�֣�
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="235" height="39">
					<p>
						<logic:equal value="��ͥ������������" name="k" property="xmzzjb">
							��
						</logic:equal>
						<logic:notEqual value="��ͥ������������" name="k" property="xmzzjb">
							��
						</logic:notEqual>
						 B ����ͥ������������
					</p>
				</td>
			</tr>
			<tr>
				<td width="235" height="35">
					<p>
						<logic:equal value="��ͥ���ò�����" name="k" property="xmzzjb">
							��
						</logic:equal>
						<logic:notEqual value="��ͥ���ò�����" name="k" property="xmzzjb">
							��
						</logic:notEqual>
						 C ����ͥ���ò�����
					</p>
				</td>
			</tr>
			<tr>
				<td width="55">
					<p align="center">
						��<br/>��<br/>��<br/>��
					</p>
				</td>
				<td width="54">
					<p align="center">
						ϵ<br/>��Ժ��<br/>��<br/>��
					</p>
				</td>
				<td colspan="3" valign="top" >
					<p>
						������С���Ƽ�����ϵ��Ժ��������˺�
					</p>
					<p>
						�� ͬ������С�������
					</p>
					<p>
						�� ��ͬ������С�����������Ϊ��
					</p>
					<p>
						___________________________��
					</p>
					<p>
						��ѧ����С����
					</p>
					<p>
						��ǩ�֣�����£���
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td width="33">
					<p align="center">
						ѧ������ѧ�����
					</p>
				</td>
				<td width="270" valign="top">
					<p>
						��ѧ������ϵ��Ժ����ˣ������������ʵ��
					</p>
					<p>
						��ͬ����ѧ����С�������С�������
					</p>
					<p>
						����ͬ����ѧ����С�����������Ϊ��
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="center">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						���Ӹǲ��Ź��£�
					</p>
				</td>
			</tr>
			</logic:iterate>
		</table>
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

<%@ page language="java"  pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
			<span style="font-size:22px;font-family:����">����ά˹���������</span>
			<br />
			<br />
		</center>
		<table class="printtab">
			<tr>
				<td width="91" colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="203" colspan="3">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="70" colspan="3">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="119" colspan="3">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td width="117" colspan="2" rowspan="3">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr>
				<td width="91" colspan="2">
					<p align="center">
						��������ƴ��
					</p>
				</td>
				<td width="392" colspan="9">
					<p align="center">
						${rs.xmpy }
					</p>
				</td>
			</tr>
			<tr>
				<td width="91" colspan="2">
					<p align="center">
						��������
					</p>
				</td>
				<td width="203" colspan="3">
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td width="70" colspan="3">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="119" colspan="3">
					<p align="center">
						${rs.mzmc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="91" colspan="2">
					<p align="center">
						��ͥ��ַ
					</p>
				</td>
				<td width="273" colspan="6">
					<p align="center">
						${rs.jtdz }
					</p>
				</td>
				<td width="119" colspan="3">
					<p align="center">
						��������
					</p>
				</td>
				<td width="117" colspan="2">
					<p align="center">
						${rs.jtyb }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="36" rowspan="4">
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						ѧ
					</p>
					<p align="center">
						У
					</p>
				</td>
				<td width="120" colspan="2">
					<p align="center">
						ԺУ����
					</p>
				</td>
				<td width="208" colspan="5">
					<p align="center">
						${xxmc }
					</p>
				</td>
				<td width="63" colspan="2">
					<p align="center">
						ϵ
					</p>
				</td>
				<td width="173" colspan="3">
					<p align="center">
						${rs.xymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="120" colspan="2">
					<p align="center">
						ר ҵ
					</p>
				</td>
				<td width="208" colspan="5">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
				<td width="63" colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="173" colspan="3">
					<p align="center">
						${rs.bjmc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="120" colspan="2">
					<p align="center">
						ѧУͨѶ��ַ
					</p>
				</td>
				<td width="250" colspan="6">
					<p align="center">
						${rs.xxdz }
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						��������
					</p>
				</td>
				<td width="110">
					<p align="center">
						${rs.xxyb }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="160" colspan="3">
					<p align="center">
						��ϵ�绰�����ţ�
					</p>
				</td>
				<td width="126" colspan="3">
					<p align="center">
						${rs.xxlxdh }
					</p>
				</td>
				<td width="105" colspan="3">
					<p align="center">
						ѧ������绰
					</p>
				</td>
				<td width="173" colspan="3">
					<p>
						${rs.qsdh }
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						��������
					</p>
				</td>
				<td width="564" colspan="12" valign="top">
					<p>
						<strong>������ϸ˵����ĸְҵ����ͥ�˿ڡ�������Դ���������ע��ѧ����Դ������ѧ�Ѽ��⼰��ý�ѧ����ѧ��״������������ҳ��д��
						</strong>
					</p>
					<p style="height:100px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					</p>
					<p align="center">
						������ǩ����<u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
						&nbsp;&nbsp;ʱ�䣺
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						�����˳�ŵ
					</p>
				</td>
				<td width="564" colspan="12" valign="top">
					<p>
						1 ���μ���ά˹���̡����㡱ʵϰ������ÿ�β����� 8 ��Сʱ����һѧ��ÿѧ�겻���� 40 ��Сʱ�����������ѧ��ÿѧ�겻���� 80
						��Сʱ�� ԭ���ϰ����ڡ���һ������ʮһ�������ٺ���ٵȽڼ��ռ�����ɣ����������������������ά˹��Э��ȷ����
						�ڹ���ѧ�ڼ�������ά˹���������ƶȣ�ͬʱ������ͨ��ʱ�����������
					</p>
					<p>
						2 �� ѧϰ�ڼ�����μӸ�����ṫ������ҵ��������Ը�����칫���ṩ���緽ʽ ,
						��������ʱ��Ը��ϣ�����̾��ʣ�����������Ҫ������ѧ����
					</p>
					<p>
						&nbsp;
					</p>
					<p align="center">
						��ŵ��ǩ���� <u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
						&nbsp;&nbsp;ʱ�䣺
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						�������
					</p>
				</td>
				<td width="564" colspan="12" valign="top">
					<p align="center" style="height:80px">
						&nbsp;
						&nbsp;
					</p>
					<p align="center">
						Ժϵ�����ˣ� <u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
						&nbsp;&nbsp;ʱ�䣺
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						�������
					</p>
				</td>
				<td width="265" colspan="5" valign="top">
					<p align="center" style="height:80px">
						&nbsp;
					</p>
					<p>
						�����ˣ�<u>
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;
							</u> ����ѧ��ί��ѧ�������£�
					</p>
					<p>
						ʱ�䣺
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
					</p>
				</td>
				<td width="299" colspan="7" valign="top">
					<p align="center" style="height:80px">
						&nbsp;
					</p>
					<p>
						�����ˣ�<u>
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;
							</u> ��ʡ���������£�
					</p>
					<p align="left">
						ʱ�䣺
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 ��
					</p>
				</td>
			</tr>
		</table>
		<table width="685px" border="0" align="center">
			<tr>
				<td>
					<p>ע���˱���Ը��ƣ�һʽ���ݣ���ѧ�� ��ά˹���̺� �й���������һ��ԭ����ʡ�����������</p>
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

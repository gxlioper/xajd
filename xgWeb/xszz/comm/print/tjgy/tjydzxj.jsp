<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
			<span style="font-size:22px;font-family:����">������ƶ���ѧ����ѧ��������ѧ���ǼǱ�</span>
			<br />
			<br />
		</center>
		<table width="685px" border="0" align="center">
			<tr>
				<td>
					ѧУ���ƣ����ҵ��ѧ
				</td>
				<td>
					������ڣ�
				</td>
			</tr>
		</table>
		<table class="printtab">
			<tr height="40px">
				<td width="85" colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="73">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td width="87" colspan="2">
					<p>
						�� ��
					</p>
				</td>
				<td width="73" colspan="3">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td width="87" colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="60">
					<p align="center">
						${rs.jg }
					</p>
				</td>
				<td width="113" rowspan="4">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="40px">
				<td width="85" colspan="2">
					<p align="center">
						ѧ ��
					</p>
				</td>
				<td width="73">
					<p>
						${rs.xh }
					</p>
				</td>
				<td width="87" colspan="2">
					<p>
						������ò
					</p>
				</td>
				<td width="113" colspan="4">
					<p>
						${rs.zzmmmc }
					</p>
				</td>
				<td width="87">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="60">
					<p>
						${rs.mzmc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="85" colspan="2">
					<p>
						��ϵ�绰
					</p>
				</td>
				<td width="73">
					<p>
						${rs.lxdh }
					</p>
				</td>
				<td width="87" colspan="2">
					<p>
						��ͥ��ַ
					</p>
				</td>
				<td width="220" colspan="6">
					<p align="center">
						${rs.jtdz }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="185" colspan="4">
					<p>
						����Ժϵ��רҵ���꼶
					</p>
				</td>
				<td width="280" colspan="7">
					<p>
						${rs.xymc }��${rs.zymc }��${rs.nj }��
					</p>
				</td>
			</tr>
			<tr>
				<td width="38" align="center">
					<br/>
					��
					<br/><br/>
					��
					<br/><br/>
					��
					<br/><br/>
					��
					<br/>
				</td>
				<td width="540" colspan="11" valign="top">
					<p>
						������ϸ˵����ĸְҵ����ͥ�˿ڡ�������Դ���������ע��ѧ����Դ��
					</p>
					<p style="height:100px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					</p>
					<p align="center">
						������ǩ����
						<u>
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
				<td width="38" align="center">
					<br/>
					��<br/><br/>
					��<br/><br/>
					��<br/><br/>
					��<br/><br/>
					ŵ<br/>
				</td>
				<td width="540" colspan="11">
					<p>
						1 �����ع��ҷ��ɣ����ظߵ�ԺУѧ����Ϊ׼���ѧУ�ĸ�������ƶȣ�
					</p>
					<p>	<br/>
						2 ��������ӣ��ڷ�ѧϰ��
					</p>
					<p><br/>
						3 ����ŵ�μ�����ƶ����ʵ�����
					</p>
					<p><br/>
						4 ����ŵ��У�ڼ����ѧУ�Ĺ�����ѧ�����ţ����빫�滥�������ʵ�����
					</p>
					<p align="center"><br/>
						��ŵ��ǩ����
						<u>
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
				<td width="38" align="center">
					<br/>
					ѧ<br/><br/>
					Ժ<br/><br/>
					��<br/><br/>
					��<br/>
				</td>
				<td width="540" colspan="11" valign="top">
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="center">
						<bean:message key="lable.xb" />�����ˣ�
						<u>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</u>
						��<bean:message key="lable.xb" />ѧ����ǩ�£�
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
				<td width="38" align="center">
					<br/>
					ѧ<br/><br/>
					У<br/><br/>
					��<br/><br/>
					��<br/>
				</td>
				<td width="226" colspan="5">
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p>
						���£�ѧ�������£�
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
				<td width="36">
					<p align="center">
						���������
					</p>
				</td>
				<td width="278" colspan="5">
					<p align="center" style="height:100px">
						&nbsp;&nbsp;
					</p>
					<p>
						����
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>��ְͨҵ��ѧУ԰�������</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	</head>
	<body>
		<div align="center">
			<span style='font-size: 18.0pt; font-family: ��������'>��ְͨҵ��ѧУ԰�����������</span>
			<p align=center>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span style='font-size: 14.0pt; font-family: ����'>�������ڣ�
				
				<logic:notEqual value="" name="rs" property="sqsj">
					 ${rs.sqsj }
				</logic:notEqual>
				
				<logic:equal value="" name="rs" property="sqsj">
				<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</u>��<u>&nbsp;&nbsp;&nbsp; </u>��<u>&nbsp; &nbsp;&nbsp;&nbsp;</u>��
				</logic:equal>
				</span>
			</p>
			<table class="tbstyle" width="85%" height="700px">
				<tr>
					<td align="center" width="20%">
						���뵥λ
					</td>
					<td colspan=3 align="center" width="40%">
						${rs.sqdw }
					</td>
					<td colspan=2 align="center" width="20%">
						�ܸ�����
					</td>
					<td align="center" width="20%">
						${rs.zfzr }
					</td>
				</tr>
				<tr>
					<td align="center">
						��⿪ʼʱ��
					
					</td>
					<td colspan=3 align="center">${rs.kssj }</td>
					
					<td colspan=2 align="center">
						�ص�
					</td>
					<td align="center">
						${rs.dd }
					</td>
				</tr>
				<tr>
					<td align="center">
						�����
					</td>
					<td colspan=3 align="center">
						${rs.hdnr }
					</td>
					<td colspan=2 align="center">
						��������
						
					</td>
					<td align="center">
						${rs.cyrs }
					</td>
				</tr>
				<tr>
					<td align="center">
						�ֳ�������һ
						
					</td>
					<td colspan=2 align="center">
						${rs.xcfzr1 }
					</td>
					<td colspan=2 align="center">
						��ϵ�绰
					</td>
					<td colspan=2 align="center">
						${rs.fzr1dh }
					</td>
				</tr>
				<tr>
					<td align="center">
						�ֳ������˶�
						
					</td>
					<td colspan=2 align="center">
						${rs.xcfzr2 }
					</td>
					<td colspan=2 align="center">
						��ϵ�绰
					</td>
					<td colspan=2 align="center">
						${rs.fzr2dh }
					</td>
				</tr>
				<tr height="200px">
					<td colspan=7 align="left" valign="top">
						�������Ҫ������
						<p style="height: 140px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.hdfa }
						</p>
					</td>
				</tr>
				<tr height="160px">
					<td colspan=3 align="left" valign="top">
						<p style="height: 140px">���뵥λ�����<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqdwyj }
						</p>
						<p align="right">
						�ܸ����ˣ�ǩ�֣�
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br/>
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	��					
						<br/>
						</p>
					</td>
					<td colspan=4 align="left" valign="top">
						<p style="height: 120px">�������������</p>
						<p align="right">
						�����ˣ�ǩ�֣�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						<br/>
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	��					
						<br/>
						</p>
					</td>
				</tr>
			</table>
			<table border="0" width="85%">
			<tr>
			<td>
			<span style='font-family: ����;'>
			���˵����<br/>
			&nbsp;&nbsp;&nbsp;&nbsp; 1������������ڲ��루�ֳ���������10�����ϡ���ѧУ�������Ͼ��еĸ�����<br/>
			&nbsp;&nbsp;&nbsp;&nbsp; 2�����ܸ����ˡ�һ��Ϊ��Ժϵ�����ţ��ֹ�ѧ�������ĸ����ˣ����ֳ�������һ��һ��Ϊ��Ժϵ�����ţ�����Ա�����ֳ������˶���һ��Ϊʼ���ڻ�ֳ�������֧������ǡ�ѧ���ֻ���ϯһ��ѧ���ɲ�������д����ϵ�绰Ϊһֱ����ͨѶ��ͨ���ƶ��绰���롣<br/>
			&nbsp;&nbsp;&nbsp;&nbsp; 3������һʽ���ݣ�һ�����汣������һ��������ί��һ�������뵥λ���档</span>
			
			</td>
			</tr>
			</table>
		</div>
		<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
	</body>
</html>

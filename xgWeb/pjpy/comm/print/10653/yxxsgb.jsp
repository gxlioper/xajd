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
			<strong style="font-size:24px;font-family:����">�ɶ�����ѧԺ����ѧ���ɲ��ǼǱ�</strong>
		</p>
		<p align="right">
			<strong>��ѧ�꣺&nbsp;${rs.pjxn }&nbsp; �� </strong>
		</p>
		<table class="printtab" width="100%" style="font-family:����;font-weight:bold;font-size:14px;">
			<tr>
				<td width="14%" colspan="3" align="center">
					�� �� 
				</td>
				<td width="14%" colspan="2" align="center">
					${rs.xm }
				</td>
				<td width="9%" align="center">
					�Ա� 
				</td>
				<td width="14%" align="center">
					${rs.xb }
				</td>
				<td width="8%" align="center">
					���� 
				</td>
				<td width="13%" colspan="2" align="center">
					${rs.���� }
				</td>
				<td width="13%" colspan="2" align="center">
					���� 
					<br/>
					��ò 
				</td>
				<td width="15%" align="center">
					${rs.zzmmmc }
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center" width="8%">
					ϵ�������� 
				</td>
				<td colspan="5" align="center">
					${rs.zymc } &nbsp;&nbsp; ${rs.nj } &nbsp;&nbsp; ${rs.bjmc }
				</td>
				<td colspan="2" align="center">
						ѧ�� 
				</td>
				<td colspan="2" align="center">
					${rs.xh }
				</td>
			</tr>
			<tr height="120px">
				<td width="6%" align="center">
					��<br/>Ҫ<br/>��<br/>��
				</td>
				<td colspan="12" valign="top">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.��Ҫ�¼� }
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					�� �� 
					<br/>
					�� �� 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj1 }   
					</p>
					<p align="right">
						ǩ�֣��£�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					�� �� 
					<br/>
					�� �� 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj2 }   
					</p>
					<p align="right">
						ǩ�֣��£�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					ϵ ��
					<br/>
					�� �� 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj3 }   
					</p>
					<p align="right">
						ǩ�֣��£�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					ѧ Ժ
					<br/>
					�� �� 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj4 }   
					</p>
					<p align="right">
						ǩ�֣��£�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					��ע 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }   
					</p>
					<p align="right">
						ǩ�֣��£�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;�� 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<p style="font-family:����;font-weight:bold;font-size:14px;" align="right">
			
			�˱�����֯��д��һʽ���ݡ�
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

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
		<logic:iterate name="rsList" id="rs">
		<center>
			<p>
				<span style="font-size:18.0pt;font-family:����"><B>�������ϴ�ѧ${rs.xmmc}�ǼǱ�</B>
				</span>
			</p>
		</center>
		<table class="printtab" width="90%">
			<tr style="height: 45px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>����</span>
					</p>
				</td>
				<td width=11% align=center>
					&nbsp; ${rs.xm }
				</td>
				<td width=11% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��</span> &nbsp;
						<span style='font-family:����;"Times New Roman"'>��</span>
					</p>
				</td>
				<td width=11% align=center>
					&nbsp;${rs.xb }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center;'>
						<span style='font-family:����;"Times New Roman"'>רҵ</span>
					</p>
				</td>
				<td width=28% colspan=2 align=center>
					&nbsp;${rs.zymc }
				</td>
			</tr>
			<tr style="height: 45px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>����</span>
					</p>
				</td>
				<td align=center>
					&nbsp;${rs.mzmc }
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>������ò</span>
					</p>
				</td>
				<td align=center>
					&nbsp; ${rs.zzmmmc }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>ѧ��</span>
					</p>
				</td>
				<td width=28% colspan=2 align=center>
					&nbsp;${rs.xh }
				</td>
			</tr>
			<tr style="height: 45px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>����ѧ��</span>
					</p>
				</td>
				<td align=center>
					&nbsp;${rs.pjxn }
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��ϵ��ʽ</span>
					</p>
				</td>
				<td align=center style="word-break:break-all;">
					${rs.sjhm }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>�༶</span>
					</p>
				</td>
				<td width=28% align=center colspan=2>
					&nbsp;${rs.bjmc }
				</td>
			</tr>
			<tr style="height: 45px">
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>����<bean:message key="lable.xb" /></span>
					</p>
				</td>
				<td  align=center colspan=3>
					&nbsp;${rs.xymc }
				</td>
				<td width=14% align=center>
					<p>
						<span style='font-family:����;
  &quot;Times New Roman&quot;'>�Ƽ��ȼ�</span>
					</p>
				</td>
				<td width=28% align=center colspan=2>
					&nbsp;${rs.tjdj}
				</td>
			</tr>
			<tr style="height: 45px">
				<td  align=center rowspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>�ۺϲ�����Ϣ</span>
					</p>
				</td>
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>�����ɼ�</span>
					</p>
				</td>
				<td align=center>
					&nbsp;${rs.zd2 }
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>ѧҵ�ɼ�</span>
					</p>
				</td>
				<td width=14% align=center>
					&nbsp;${rs.zd3}
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>����ɼ�</span>
					</p>
				</td>
				<td width=14% align=center>
					&nbsp;${rs.zd4 }
				</td>
			</tr>
			<tr style="height: 45px">
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>�ۺϲ���<br/>�ܷ�</span>
					</p>
				</td>
				<td align=center colspan=3>
					&nbsp;${rs.zd1 }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>�ۺϲ����༶����</span>
					</p>
				</td>
				<td width=14% align=center>
					&nbsp;${rs.zcfbjpm }
				</td>
			</tr>
			<tr style="height: 450px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��</span>
					</p>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��</span>
					</p>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��</span>
					</p>
					<p align=center style='text-align:center'>
						<span style='font-family:����;"Times New Roman"'>��</span>
					</p>
				</td>
				<td  colspan=6 valign=top>
					<p>
						<span style='font-family:����;
  &quot;Times New Roman&quot;'>������Ҫ�¼���<br />&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly} </span>
					</p>
				</td>
			</tr>
			<tr style="height:150px">
				<td colspan=4>
					<p align="left" style="vertical-align: top;height: 80px">
						<span><bean:message key="lable.xb" />���<br /> &nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj} </span>
					</p>

					<p align="right" style="vertical-align: bottom;">
						<span style='font-family:����'>��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</p>
				</td>

				<td colspan=3>
					<p align="left" style="vertical-align: top;height: 80px">
						<span>ѧУ���<br /> &nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj } </span>
					</p>
					<p align="right" style="vertical-align: bottom;">
						<span style='font-family:����'>��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</p>
				</td>
			</tr>
		</table>
		<p>
			<span style='font-family:����;&quot;Times New Roman&quot;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע���˱�һʽ���ݣ��ɸ��ơ���ӡ��</span>
		</p>
			<logic:equal name="rs" property="isEnd" value="no">
				<div style='page-break-before:always;'>&nbsp;</div>
			</logic:equal>

		</logic:iterate>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- ͷ�ļ� -->
<html:html>
<body>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<p align=center style="line-height:10px">
			<span style='font-size:18.0pt;color:black;'>ѧ�Ѻ͹�����ѧ������������</span>
		</p>
		<p align="left" style="line-height:0px">
			<span
				style='font-size:12.0pt;font-family:����;color:black;text-indent:300px '>����ڣ�&nbsp;&nbsp;
				&nbsp;&nbsp;��&nbsp;&nbsp;�� &nbsp;&nbsp;��</span>
		</p>
		<table width="100%" class="printstyle" align="center">
			<tr style="height:45px">
				<td align="center">
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>��<span
							lang=EN-US>&nbsp;&nbsp;&nbsp; </span>��</span>
					</p>
				</td>
				<td align="center" colspan=2>
					${rs.xm }
				</td>
				<td align="center">
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>��<br />��</span>
					</p>
				</td>
				<td align="center" colspan=3>
					${rs.xb }
				</td>
				<td align="center" colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>������ò</span>
					</p>
				</td>
				<td align="center" colspan=4>
					${rs.zzmmmc }
				</td>
				<td align="center" colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>����<br />����</span>
					</p>
				</td>
				<td align="center">
					${rs.csrq }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ҵѧУ</span>
					</p>
				</td>
				<td align="center" colspan=6>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>����ũҵ��ѧ</span>
					</p>
				</td>
				<td align="center" colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ѧרҵ</span>
					</p>
				</td>
				<td align="center" colspan=8>
					${rs.zymc }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ҵʱ��</span>
					</p>
				</td>
				<td align="center" colspan=2>
					${rs.byrq }
				</td>
				<td align="center" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>���֤��</span>
					</p>
				</td>
				<td align="center" colspan=7>
					${rs.sfzh }
				</td>
				<td align="center" colspan=4>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ǩ��<br />�ķ���<br />����</span>
					</p>
				</td>
				<td align="center" colspan=2>
					${rs.kzzd1 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>������ϵ<br />�绰</span>
					</p>
				</td>
				<td align="center" colspan=4>
					${rs.lxdh }
				</td>
				<td align="center" colspan=4>
					<p align=left style='text-align:left'>
						<span style='font-size:
  12.0pt;font-family:����;color:black'>�����ʼ���ַ</span>
					</p>
				</td>
				<td align="center" colspan=10>
					${rs.dzyx }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>�����˻�<br />��Ϣ</span>
					</p>
				</td>
				<td align="" colspan=4 valign="top">
					<span style='font-size:12.0pt;font-family:����;color:black;text-indent: 10px;'>��������${rs.kzzd2 }</span>
				</td>
				<td colspan=4>
					<span
						style='font-size:12.0pt;font-family:����;color:black;text-align: left'>�������У�</span>
					<br />
					<b><span
						style='font-size:12.0pt;font-family:����;
  						color:black;text-align:center;text-indent:50px'>�й������人<br />&nbsp;&nbsp;��ũ֧��</span>
					</b>
				</td>
				<td align="left" colspan=10 valign="top">
					<span style='font-size:12.0pt;font-family:����;color:black;text-indent: 10px;'>�����˺ţ�${rs.kzzd3 }</span>
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ͥ��ַ���ʱ�</span>
					</p>
				</td>
				<td align="center" colspan=16>
					&nbsp;${rs.jtyb }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ҵ��λ����</span>
					</p>
				</td>
				<td align="center" colspan=16>
					&nbsp;${rs.kzzd4 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ҵ��λ��ϸ��ַ</span>
					</p>
				</td>
				<td align="center" colspan=16>
					&nbsp;${rs.kzzd5 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��ҵ��λ��ϵ�绰</span>
					</p>
				</td>
				<td align="center" colspan=7>
					&nbsp;${rs.kzzd6 }
				</td>
				<td align="center"  colspan=4>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>��&nbsp;&nbsp;λ<br />��&nbsp;&nbsp;��</span>
					</p>
				</td>
				<td align="center" colspan=5>
					&nbsp;${rs.kzzd7 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" colspan=2 valign=top>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black;'>&nbsp;ʵ�ʽ�ѧ��<br />&nbsp;�����ѷ�<br />&nbsp;���ܶ<span
							lang=EN-US>*</span>
						</span>
					</p>
				</td>
				<td align="center" colspan=2>
					&nbsp;${rs.kzzd8 }
				</td>
				<td align="center" colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:����;color:black'>�����<br />�ܶ�</span>
					</p>
				</td>
				<td align="center" colspan=4>
					&nbsp;${rs.kzzd9 }
				</td>
				<td align="center" colspan=4>
					<p>
						<span style='font-size:12.0pt;font-family:����;color:black'>�������<br />���</span>
					</p>
				</td>
				<td align="center" colspan=3>
					&nbsp;${rs.kzzd10 }
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:����;color:black;height: 50px'><bean:message key="lable.xb" />��������</span>
					
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  							font-family:����;color:black'>��λ���£�<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;
						</span>��</span>
					</p>
				</td>
			</tr>
			<tr style="height:75px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:����;color:black;height: 30px'>��ҵѧУ�����Ŷ�ʵ�ʽ���ѧ�Ѽ���ù�����ѧ�������������</span>
					<p align="left" style="text-indent: 230px" >
						<span style='font-size:12.0pt;
  font-family:����;color:black'>��λ���£�<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;
						</span>��</span>
					</p>
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:����;color:black;height: 50px'>��ҵѧУѧ����������������������</span>
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  font-family:����;color:black'>��λ���£�<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;
						</span>��</span>
					</p>
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:����;color:black;height: 50px'>��ҵѧУ��������</span>
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  font-family:����;color:black'>��λ���£�<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;
						</span>��</span>
					</p>
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:����;color:black;height: 20px'>ȫ��ѧ����������������������</span>
					<p align="left" style="text-indent: 130px">
						<span style='font-size:12.0pt;
  font-family:����;color:black'>����ˣ�ͬ�����������������պ˶��������Ϊ�����<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>Ԫ��<br/></span>
					</p>
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  font-family:����;color:black'>��λ���£�<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>��<span lang=EN-US>&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;
						</span>��</span>
					</p>
				</td>
			</tr>
			<tr height=0>
				<td width=84></td>
				<td width=24></td>
				<td width=60></td>
				<td width=37></td>
				<td width=23></td>
				<td width=24></td>
				<td width=36></td>
				<td width=9></td>
				<td width=51></td>
				<td width=12></td>
				<td width=12></td>
				<td width=16></td>
				<td width=32></td>
				<td width=12></td>
				<td width=12></td>
				<td width=24></td>
				<td width=24></td>
				<td width=12></td>
				<td width=66></td>
			</tr>
		</table>
		<p>
			<span style='font-family:����;color:black'>ע��<b>��</b>�˴����Ϊ���������ѧ����Ӧѧ�ƹ涨�����ڵ�ѧ�ѽ��ʹ����</span>
		</p>
	</html:form>
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<!-- ��ӡ�ؼ�begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<html:form action="/pjpyzjcmwh" method="post">
		<div align="left" style="font-size:18px;">����:</div>
		<div align="center" style="font-size:20px;font:'����' "><b>${xxmc }"ƮƼ"��ѧ������������</b></div>
		<div align="center">(${nd }��)</div>
		<bean:message key="lable.xsgzyxpzxy" />����:${rs.xymc }
		<table width="100%" class="printstyle">
			<tr>
				<td width="5%" rowspan="6">
					<p align="center">
						<strong>��<br /> ��<br /> ��<br /> ��</strong>
					</p>
				</td>
				<td width="11%">
					<div align="center">
						����
					</div>
				</td>
				<td width="14%" align="center">
					${rs.xm }
				</td>
				<td width="12%" height="">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td width="15%" align="center">
					${rs.xb }
				</td>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.csrq }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td align="center">
					${rs.xh }
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td align="center">
					${rs.mzmc }
				</td>
				<td colspan="2">
					<div align="center">
						��ѧʱ��
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.rxrq }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td align="center">
					${rs.zymc }
				</td>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td align="center">
					${rs.xz }
				</td>
				<td colspan="2">
					<div align="center">
						��ҵʱ��
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.bysj }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td align="center">
					${rs.bjmc }
				</td>
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td align="center">
					${rs.zzmmmc }
				</td>
				<td colspan="2">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="3" align="center">
					${rs.lxdh }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="8" align="left">
					${rs.sfzh }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<p>
							��ͥ��ϸ��ַ
						</p>
					</div>
				</td>
				<td colspan="8">
					${rs.jtdz }
				</td>
			</tr>
			<tr>
				<td rowspan="3">
					<div align="center">
						<strong>ѧ<br /> ϰ<br /> ��<br /> ��<br /> ��</strong>
					</div>
				</td>
				<td height="60" colspan="9">
					<div align="left">
						��ѧ����޿γ���
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��.����,����
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��,����
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��,
						<br/>
						Ӣ��ɼ�:&nbsp;
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>,�ڶ�������ɼ�:&nbsp;
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>.
						<br>
						(�������޴���,��д&quot;��&quot;)
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��һѧ��
					</div>
				</td>
				<td>
					<p align="center">
						��������<br />����/������
					</p>
				</td>
				<td >
					<div align="center"></div>
				</td>
				<td>
					<div align="center">��������<br />����/������
					</div>
				</td>
				<td width="40px">
					&nbsp;
				</td>
				<td width="15%">
					<div align="center">
						�ۺ�����<br />����/������
					</div>
				</td>
				<td width="">
					&nbsp;
				</td>
				<td width="15%">
					<div align="center">
						��ѧ��<br />�ȼ�
					</div>
				</td>
				<td width="">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�ڶ�ѧ��
					</div>
				</td>
				<td>
					<div align="center">
						��������<br />����/������
					</div>
				</td>
				<td>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						��������<br />����/������
					</div>
				</td>
				<td>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						�ۺ�����<br />����/������
					</div>
				</td>
				<td>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						��ѧ��<br />�ȼ�
					</div>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					
					<div align="center">
						<strong>��<br /> ͥ<br /> ��<br /> ��<br /> ��<br /> ��</strong>
					</div>
				
				</td>
				<td colspan="9">
					<div align="center"></div>
				</td>
			</tr>
			<tr>
				<td>
					
					<div align="center">
						<strong>��<br /> ��<br /> ��<br /> ��<br /> </strong>
						<div align="center">
							<strong>��</strong>
						</div>
						<strong>ȫ<br /> ��<br /> ��<br /> ӳ<br /> ��<br />��<br />��<br />
							��<br /> ��<br /> �� </strong>
						<div align="center">
							<strong>��</strong>
							<br />
						</div>
					</div>
					
				</td>
				<td colspan="9">
					<div align="center">
					<br/><br/><br/><br/><br/>
						<p align="right">
							������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					
					<div align="center">
						<strong>ѧ<br /> Ժ<br /> ��<br /> ��</strong>
					</div>
					
				</td>
				<td colspan="9">
					<div align="center">
					<br/>
						<p align="right">
							ǩ��(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					<div align="center">
						<strong>ѧ<br /> У<br /> ��<br /> ��</strong>
					</div>
				</td>
				<td colspan="9">
					<div align="center">
						<br/>
						<p align="right">
							ǩ��(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
				</td>
			</tr>
		</table>
(�ṩ���񴦸��³ɼ���,��ز��Ͽ���ҳ)
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>

	</html:form>
</body>

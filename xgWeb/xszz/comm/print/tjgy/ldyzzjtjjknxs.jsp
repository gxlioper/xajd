<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
			<span style="font-size:22px;font-family:����">�ϵ�Ա������ͥ��������ѧ����������ǼǱ�</span>
			<br />
			<br />
		</center>
		<table width="100%" class="printtab" style="font-weight:bold;font-size:18px">
			<tr height="40px">
				<td align="center" width="18%">
					ѧУ���ƣ�
				</td>
				<td colspan="4" align="center" style="font-size:14px">
				
				${xxmc }
				</td>
				<td align="center">
					���<br/>����
				</td>
				<td >
				</td>
			</tr>
			<tr height="40px">
				<td>
					<p align="center" width="15%">
						�� ��
					</p>
				</td>
				<td>
					<p align="center" width="10%" style="font-size:14px">
						${rs.xm }
					</p>
				</td>
				<td align="center" width="12%">
						�� ��
				</td>
				<td>
					<p align="center" style="font-size:14px">
						${rs.xb }
					</p>
				</td>
				<td align="center" width="15%">
						��������
				</td>
				<td>
					<p align="center" style="font-size:14px">
						${rs.csrq }
					</p>
				</td>
				<td width="113" rowspan="4" width="11%">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="40px">
			   <td width="15%">
					<p align="center">
						�� ��
					</p>
				</td>
				<td>
					<p align="center" width="15%" style="font-size:14px">
						${rs.mzmc }
					</p>
				</td>
				<td >
					<p align="center" width="15%">
						����<br/>��ò
					</p>
				</td>
				<td>
					<p align="center" width="15%" style="font-size:14px">
						${rs.zzmmmc }
					</p>
				</td>
				<td>
					<p align="center" width="15%">
						����
					</p>
				</td>
				<td >
					<p align="center" width="15%" style="font-size:14px">
						${rs.jg }
					</p>
				</td>
				
			</tr>
			<tr height="40px">
			<td align="center">
					<p align="center">
						Ժϵ
					</p>
				</td>
				<td  colspan="2" style="font-size:14px">
					<p align="center" >
						${rs.xymc }
					</p>
				</td>
				<td>
					<p align="center">
						רҵ
					</p>
				</td>
				<td colspan="2" style="font-size:14px">
					<p align="center" >
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td>
					<p align="center">
						�꼶
					</p>
				</td>
				<td style="font-size:14px">
					<p align="center">
						${rs.nj }
					</p>
				</td>
				<td>
					<p align="center">
						���
					</p>
				</td>
				<td style="font-size:14px">
					<p align="center">
						${rs.pycc }
					</p>
				</td>
				<td>
					<p align="center">
						��ͥ�绰
					</p>
				</td>
				<td style="font-size:14px">
					<p align="center">
						${rs.jtdh }
					</p>
				</td>
			</tr>
			<tr height="40px">
			<td >
					<p align="center">
						�ʱ�
					</p>
				</td>
				<td >
					<p align="center">
						${rs.jtyb }
					</p>
				</td>
				<td >
					<p align="center">
						��ͥ<br/>��ַ
					</p>
				</td>
				<td  colspan="4">
					<p align="center">
						${rs.jtdz }
					</p>
				</td>
			</tr>
			<tr height="160px">
				<td  align="center">
					��
					<br/>
					ͥ
					<br/>
					��
					<br/>
					��
					<br/>
					��
					<br/>
					��
					<br/>
				</td>
				<td colspan="7" valign="top" style="word-break:break-all">
				    &nbsp;&nbsp;&nbsp;&nbsp; ${rs.jtjbjjqk }
				</td>
			</tr>
			<tr  height="160px">
				<td  align="center">
					ѧ<br/>
					ҵ<br/>
					��<br/>
					��<br/>
					��<br/>
				</td>
				<td colspan="7" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyjbx }
				</td>
			</tr>
			<tr  height="160px">
				<td  align="center">
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
				</td>
				<td colspan="7" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.hghzjl }
				</td>
			</tr>
			<tr  height="22px">
				<td  align="center">
					ѧ<br/>
					У<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
				</td>
				<td colspan="3">
					<p style="height:80px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="center">
						ѧУ����
					</p>
					<p align="center">
						
					</p>
				</td>
				<td>
					<p align="center">
						��<br/>
					          ��<br/>
					          ί<br/>
					          ��<br/>
					          ��<br/>
					</p>
				</td>
				<td colspan="4">
					<p align="center" style="height:80px">
						&nbsp;&nbsp;&nbsp;&nbsp;
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

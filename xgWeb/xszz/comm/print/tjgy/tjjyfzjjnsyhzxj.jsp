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
	<br/>
		<center>
			<span style="font-size:28px;font-family:����">����н�����չ���� ���ũ��������ѧ�������</span>
		</center>
		<br/>
		<table width="800px" class="printtab" style="font-size:18px;font-weight:bold">
			<tr >
				<td width="100px">
				<p align="center">
					ѧУ<br/>����
				</p>	
				</td>
				<td colspan="6"><p align="center">
				${xxmc }
				</p> 
			    </td>
			</tr>
			<tr height="62px">
				<td width="100px" >
					<p align="center">
						�� ��
					</p>
				</td>
				<td >
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td >
					<p align="center">
						�� ��
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td align="center">
						�� ��
				</td>
				<td >
					<p align="center" >
						${rs.mzmc }
					</p>
				</td>
			</tr>
			<tr>
				<td width="100px" >
					<p align="center">
						����<br/>��ò
					</p>
				</td>
				<td >
					<p align="center">
						${rs.zzmmmc }
					</p>
				</td>
				<td >
					<p align="center">
						����<br/>����
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td rowspan="4" colspan="2"  align="center">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
				
			</tr>
			<tr height="62px">
			<td width="100px">
					<p align="center">
						���
					</p>
				</td>
				<td >
					<p align="center">
						${rs.pycc }
					</p>
				</td>
				<td >
					<p align="center">
						רҵ
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr>
				<td width="100px">
					<p align="center">
						�꼶
					</p>
				</td>
				<td >
					<p align="center">
						${rs.nj }
					</p>
				</td>
				
				<td >
					<p align="center">
						��ϵ<br/>��ʽ
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.lxdh }
					</p>
				</td>
			</tr>
			<tr>
			<td width="100px">
			<p align="center">���<br/>֤��</p>
			</td>
			<td colspan="4">
			<p align="center" >
			${rs.sfzh }
			</p>
			</td>
			</tr>
			<tr  height="40px">
							<td rowspan="2" align="center">
								��ͥ
								<br />
								����
								<br />
								���
								<br />
							</td>
							<td align="center" >
								��ͥ�˿�����
							</td>
							<td align="center">
								${rs.jtrs }
							</td>
							<td align="center">
								���˾�����
							</td>
							<td align="center">
								
							</td>
							<td align="center" >
								��������
							</td>
							<td align="center">
								${rs.jtyb }
							</td>
			</tr>
			<tr>
			<td align="center">
								��ͥסַ
							</td>
							<td align="center" colspan="5">
								${rs.jtdz }
							</td>
			</tr>
			<tr  height="180">
				<td  align="center">
					ѧϰ<br/>
					�ɼ�<br/>
					����<br/>
					����<br/>
					����<br/>
					����<br/>
					ע��<br/>
					�߿�<br/>
					�ɼ���<br/>
				</td>
				<td width="800" colspan="7" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxcj }
				</td>
			</tr>
			<tr  height="140">
				<td  align="center">
					����<br/>����
				</td>
				<td width="800" colspan="7" valign="top" style="word-break:break-all">
				<p style="height:130px">
				&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
				</p>
						<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
						</div>
				</td>
			</tr>
			<tr  height="190">
				<td  align="center">
					ѧ<br/>
					У<br/>
					��<br/>
					��<br/>
					��<br/>
					��<br/>
				</td>
				<td>
					<p style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					</p>
					<p align="right">
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          ��
					</p>
				</td>
				<td>
					<p align="center">
						��<br/>
						��<br/>
						ũ<br/>
						��<br/>
						��<br/>
						��<br/>
					</p>
				</td>
				<td>
					<p align="center" style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					</p>
					<p align="right">
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          ��
					</p>
				</td>
				<td>
					<p align="center">
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						��<br/>
						չ<br/>
						��<br/>
						��<br/>
						��<br/>
					</p>
				</td>
				<td colspan="2">
					<p align="center" style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					</p>
					<p align="right">
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          ��
					</p>
				</td>
			</tr>
		</table>
			<table width="800px" border="0" align="center">
			<tr>
				<td>
					<p style="font-size:15px">��ע���˱�һʽ���ݣ��ֱ���ѧУ�����ũ�����С�����н�����չ�����浵��</p>
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

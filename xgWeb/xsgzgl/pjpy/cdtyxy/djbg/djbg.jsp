<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
<!-- author��qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<table width="80%" border="0" id="theTable" align="center">
			<tr>
				<td align="center" style="height: 50px;vertical-align: bottom">
					
					<span style="font-size: 24px;font-family: ����"><B>�ɶ�����ѧԺ${rs.xmmc }�ǼǱ�</B></span>	
				</td>
			</tr>
			<tr>
				<td align="right" style="height: 30px;vertical-align: center">
					<span style="font-size:15px;margin-right: 15px">��ѧ��:&nbsp;&nbsp;<span lang=EN-US>${rs.xn}</span>&nbsp;&nbsp;��</span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" id="rsT" class="printstyle">
						<tr style="height: 70px" align=center valign="middle">
							<td  colspan=3>
								����
							</td>
							<td colspan=2>
								${rs.xm }
							</td>
							<td >
								�Ա�
							</td>
							<td >
								${rs.xb }
							</td>
							<td >
								����
							</td>
							<td colspan=2 >
								${rs.nl }
							</td>
							<td colspan=2>
								����<br/>��ò
							</td>
							<td >
								${rs.zzmmmc }
							</td>
						</tr>
						<tr style="height: 45px">
							<td colspan=4>
								ϵ��������
							</td>
							<td colspan=5 >
								${rs.xymc}${rs.nj}${rs.bjmc}
							</td>
							<td colspan=2>
								ѧ��
							</td>
							<td colspan=2 >
								${rs.xh}
							</td>
						</tr>
						<tr>
							<td style="height: 260px;width:7%" >
								��<br/><br/>Ҫ<br/><br/>��<br/><br/>��
							</td>
							<td  colspan=12 >
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px">
								��&nbsp;��<br/>��&nbsp;��
							</td>
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:����;margin-right: 50px;'>ǩ�֣��£�</p><br/>
								<span style="margin-right: 30px">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px" >
								��&nbsp;��<br/>��&nbsp;��
							</td>
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:����;margin-right: 50px;'>ǩ�֣��£�</p><br/>
								<span style="margin-right: 30px">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px">
								ϵ&nbsp;��<br/>��&nbsp;��
							</td>
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:����;margin-right: 50px;'>ǩ�֣��£�</p><br/>
								<span style="margin-right: 30px">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px">
								ѧ&nbsp;Ժ<br/>��&nbsp;��
							</td>
							<td colspan=11 valign=bottom align="right" style="height: 80px">
								<p style='font-family:����;margin-right: 50px;'>ǩ�֣��£�</p><br/>
								<span style="margin-right: 30px">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px" >
								��&nbsp;ע
							</td>
							
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:����;margin-right: 50px;'>ǩ�֣��£�</p><br/>
								<span style="margin-right: 30px">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</span>
							</td>
						</tr>
						<tr height=0>
							<td width=6%></td>
							<td width=2%></td>
							<td width=4%></td>
							<td width=5%></td>
							<td width=5%></td>
							<td width=11%></td>
							<td width=12%></td>
							<td width=11%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=18%></td>
						</tr>
					</table>
					<span style="float: left;margin-left: 10px">�˱�����֯��д��һʽ���ݡ�</span><span style="float: right">ѧ������</span>
				</td>
			</tr>
		</table>
	</body>
</html>



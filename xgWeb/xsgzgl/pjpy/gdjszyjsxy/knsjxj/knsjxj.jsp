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
	<body bgcolor="#FFFFFF" 
		style='text-justify-trim:punctuation' lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt'>
			<p align=center style='text-align:center'>
				<b><span style='font-size:18.0pt;font-family:����'>�����ͥ��������ѧ����ѧ������������</span>
				</b><span style='font-size:15.0pt;font-family:����'> ��<u><span
						lang=EN-US>${rs.xn}</span>
				</u>ѧ�꣩</span>
			</p>
			<p>
				<b><span style='font-size:12.0pt;font-family:����'>ϵ��</span>
				</b><b><span lang=EN-US style='font-size:12.0pt'>${rs.xymc }</span>
				</b><b><span style='font-size:12.0pt;font-family:����'>רҵ��</span>
				</b><b><span style='font-size:12.0pt'> <span lang=EN-US>${rs.zymc }</span>
				</span>
				</b><b><span style='font-size:12.0pt;font-family:����'>�༶��${rs.bjmc }</span>
				</b>
			</p>
			<table width="100%" id="rsT" class="printstyle" >
				<tr>
					<td width=67 rowspan=3  align="center">
						<p>
							<b><span style='font-size:12.0pt;font-family:����' >��<br/>��<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=84  height="40px" align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>����</span>
						</p>
					</td>
					<td width=96  align="center">
						${rs.xm }
					</td>
					<td width=84 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>�Ա�</span>
						</p>
					</td>
					<td width=84 colspan=2  align="center">
						${rs.xb }
					</td>
					<td width=84  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>��������</span>
						</p>
					</td>
					<td width=156  align="center">
						${rs.csrq }
					</td>
				</tr>
				<tr>
					<td width=84  height="40px" align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>ѧ��</span>
						</p>
					</td>
					<td width=96  align="center">
						${rs.xh }
					</td>
					<td width=84 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>����</span>
						</p>
					</td>
					<td width=84 colspan=2  align="center">
						${rs.mzmc }
					</td>
					<td width=84  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>��ѧʱ��</span>
						</p>
					</td>
					<td width=156  align="center">
						${rs.rxrq }
					</td>
				</tr>
				<tr>
					<td width=84  height="40px" align="center">
						<p>
							<span style='font-size:12.0pt;font-family:����'>����ȼ�</span>
						</p>
					</td>
					<td width=96 align="center">
						${rs.sqdj }
					</td>
					<td width=84 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:����'>������ò</span>
						</p>
					</td>
					<td width=84 colspan=2  align="center">
						${rs.zzmmmc }
					</td>
					<td width=84  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size: 12.0pt;font-family:����'>��ϵ�绰</span>
						</p>
					</td>
					<td width=156  align="center">
						${rs.lxdh }
					</td>
				</tr>
				<tr>
					<td width=67  align="center" height="140px">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:����'>ѧ<br/>ϰ<br/>��<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=588 colspan=8  style="line-height: 20px" >
						<p style='line-height:150%;layout-grid-mode:char'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:����;margin-left: 10px'>ƶ���϶��ȼ���</span>
								<span style='font-size:12.0pt;line-height:150%;margin-left: 10px'> <u><span lang=EN-US>${rs.xmzzjb }</span>
							</u>
							</span>
						</p>
						<p style='line-height:150%;layout-grid-mode:char'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:����;margin-left: 10px'>���޿Ρ���ѡ����ͷ� ��</span>
								<span style='font-size:12.0pt;line-height:150%;margin-left: 10px'> <u><span lang=EN-US>${rs.mincj }</span>
							</u>
							</span>
						</p>
						<p style='line-height:150%;layout-grid-mode:char;margin-left:10px;'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:����'>�ۺϲ����ɼ�</span><u><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'>${rs.zcf }
							</span>
							</u><span style='font-size:12.0pt;line-height:150%;font-family:����'>�֣�����</span><u><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'>${rs.zcfbjpm }/${rs.bjrs }
							</span>
							</u><span style='font-size:12.0pt;line-height:150%;font-family:����'>������</span><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'>/</span><span
								style='font-size:12.0pt;line-height:150%;font-family:����'>�༶��������</span>
						</p>
						<p style='line-height:150%;layout-grid-mode:char;margin-left:10px;'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:����'>˼��Ʒ�²���</span><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'><u>${rs.dyf }</u></span><span
								style='font-size:12.0pt;line-height:150%;font-family:����'>��</span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=67  align="center" height="180px">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:����'>��<br/>��<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=588 colspan=8 align="left">
						<p style="margin-top: 10px;margin-left: 10px;height: 120px">
							<span style='font-size:12.0pt;font-family:����'>��Ҫ���${rs.sqly }</span>
						</p>
						
						<p style="margin-bottom: 10px;margin-left: 10px">
							<span style='font-size:12.0pt;font-family:����'>���У�ϵ������</span><u><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;</span>
							</u><span style='font-size:12.0pt;font-family:����'>�</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp; </span><span
								style='font-size:12.0pt; font-family:����'>Ժ������</span><u><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;</span>
							</u><span style='font-size:12.0pt;font-family:����'>�</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp; </span><span
								style='font-size:12.0pt;font-family:����'>ʡ�����Ͻ���</span><u><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;
									&nbsp;&nbsp;&nbsp;</span>
							</u><span style='font-size:12.0pt;font-family:����'>��</span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=655 colspan=9  height="60px">
						<p>
							<span style='font-size:12.0pt;font-family:����'>���˱�֤�������������ʵ��Ч��</span>
						</p>
						<br/>
						<p align="right" >
							<span style='font-size:12.0pt;font-family:����'>����ǩ����<span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;
							</span>��</span>
						</p>
					</td>
				</tr>
				<tr  height="180px">
					<td width=67  align="center" >
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:����'>��<br/>ί<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=252 colspan=3 valign=bottom  >
						<p >
							<span style='font-family:����;margin-left: 100px'>�೤ǩ����</span>
						</p>
						<br/>
						<p align=right style='text-align:right;'>
							<span style='font-family:����'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;��<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>��</span>
						</p>
					</td>
					<td width=72 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:����'>��<br/>��<br/>��<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=264 colspan=3  valign=bottom>
						<p>
							<span style='font-family:����;margin-left: 100px'>������ǩ����</span>
						</p>
						<br/>
						<p align=right style='text-align:right;'>
							<span style='font-family:����'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;</span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>��</span>
						</p>
					</td>
				</tr>
				<tr  height="180px">
					<td width=67  align="center">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:����'>ϵ<br/>��<br/>��<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=252 colspan=3  valign=bottom>
						<p>
							<span style='font-family:����;margin-left: 100px'>ϵ����ǩ����</span>
						</p>
						<p>
							<span style='font-family:����;margin-left: 140px'>����</span>
						</p>
						<p align=right style='text-align:right;'>
							<span style='font-family:����'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;</span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>��</span>
						</p>
					</td>
					<td width=72 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:����'>ѧ<br/>Ժ<br/>��<br/>��</span>
							</b>
						</p>
					</td>
					<td width=264 colspan=3  valign=bottom>
						<p>
							<span style='font-family:����;margin-left: 100px'>����</span>
						</p>
						<br/>
						<p align=right style='text-align:right;'>
							<span style='font-family:����'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;</span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>��</span>
						</p>
					</td>
				</tr>
				<tr height=0>
					<td width=67 ></td>
					<td width=84 ></td>
					<td width=96 ></td>
					<td width=72 ></td>
					<td width=12 ></td>
					<td width=60 ></td>
					<td width=24 ></td>
					<td width=84 ></td>
					<td width=156 ></td>
				</tr>
			</table>
			
		</div>
	</body>
</html>



<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body>
		<p style="text-align: center;" align="center">
			<span style="font-size: 16.0pt; font-family: ����_GB2312;">&nbsp;</span>
		</p>
		<p style="text-align: center;" align="center">
			<span style="font-size: 18.0pt; font-family: ��������μ���;">${rs.pjnd }</span><span
				style="font-size: 18.0pt; font-family: ��������μ���;">�����ʡ��ͨ��У�����ѧ��ҵ��������<span></span>
			</span>
		</p>
		<p style="text-indent: 216.0pt;">
			<span style="font-size: 15.0pt; font-family: ����;">�ʱ�䣺
				<span>${rs.nowTime }</span>
			</span>
		</p>
		<table border="1" cellpadding="0" cellspacing="0" class="printtab" 
			   style="text-align: center;font-size: 12.0pt; font-family: ����_GB2312;" width="99%">
			<tbody>
				<tr class="nowrap">
					<td width="60">
						����
					</td>
					<td width="84">
						${rs.xm }
					</td>
					<td width="60">
						�Ա�
					</td>
					<td width="48">
						${rs.xb }
					</td>
					<td width="60">
						����<br/>����
					</td>
					<td width="96">
						${rs.csrq }
					</td>
					<td width="60">
						����
					</td>
					<td width="60">
						${rs.mzmc }
					</td>
					<td rowspan="3" width="120">
						<div id="stuImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
							<img style="width:100px;height:130px;" 
								src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
								border="0">
						</div>
					</td>
				</tr>
				<tr class="nowrap">
					<td width="60">
						����<br/>��ò
					</td>
					<td width="84">
						${rs.zzmmmc }
					</td>
					<td colspan="2" width="108">
						�������<br/>����ְ��
					</td>
					<td colspan="2" width="156">
						${rs.zw }
					</td>
					<td width="60">
						ѧ��
					</td>
					<td width="60">
						${rs.xlmc }
					</td>
				</tr>
				<tr>
					<td width="60">
						��ҵ<br/>ԺУ
					</td>
					<td colspan="3" width="192">
						���ݴ�ѧ
					</td>
					<td width="60">
						רҵ<br/>����
					</td>
					<td colspan="3" width="216">
						${rs.zymc }
					</td>
				</tr>
				<tr style="height:220px">
					<td colspan="9" valign="top" width="648" align="left">
						���˼�����
					</td>
				</tr>
				<tr style="height:220px">
					<td colspan="9" align="left" valign="top" width="648">
						�������
					</td>
				</tr>
				<tr style="height:280px">
					<td colspan="9" align="left" valign="top" width="648">
						��Ҫ�¼���
					</td>
				</tr>
			</tbody>
		</table>
		<p style="height:2px;">&nbsp;</p>
		<table border="1" class="printtab" 
			   style="text-align: center;font-size: 12.0pt;font-family: ����_GB2312;" width="99%">
			<tbody>
				<tr>
					<td width="104" style="Writing-mode:tb-rl;">
						�༶�Ƽ����
					</td>
					<td valign="top" width="544">
						<p style="height:160px;" align="left">
							${rs.xyyj }
						</p>
						<p style="text-indent: 30.0pt;">
							<span>����Ա�������ǩ����
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
							</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>
								<span>&nbsp;</span>2013
							</span>
							<span>��<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</span>��<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</span>��<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						Ժ(ϵ)��ѡ���
					</td>
					<td valign="top" width="544">
						<p style="height: 160px">
							${rs.xyyj }
						</p>
						<p style="text-indent: 30.0pt;">
							<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</span><span>�����£�
							</span>
							</span>
						</p>
						<p>
							<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;&nbsp;&nbsp;</span>
							<span style="">2013</span>
							</span><span>��<span><span>&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
							</span>��<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</span>��<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						ѧУ�������
					</td>
					<td valign="top" width="544">
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>�����󣬲���У�ڹ�ʾ<span>5</span>�������գ������飬�ֱ�����׼��ͬѧΪ
							<span>${rs.pjnd }</span>�����ʡ�����ѧ��ҵ����<span></span>
							</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p>
							<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</span>
							<span>�����£�</span>
						</p>
						<p>
							<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span>2013</span>
							</span>
							<span>��<span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp; </span>
								</span>��<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
								</span>��<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						ʡ�������������
					</td>
					<td valign="top" width="544">
						<p style="height: 160px;">
						</p>
						<p style="text-indent: 294.0pt;">
							<span>�����£�<span></span>
							</span>
						</p>
						<p>
							<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span>&nbsp;&nbsp;</span><span>&nbsp;&nbsp;</span>
							</span>
							<span>��<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</span>��<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</span>��<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						��&nbsp;&nbsp;ע
					</td>
					<td style="height: 120px;" width="544">
					</td>
				</tr>
			</tbody>
		</table>
	</body>

</html>
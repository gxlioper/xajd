<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body>
		<p style="text-align: center;" align="center">
			<b><span
				style="font-size: 16.0pt; line-height: 200%; font-family: ����;">${rs.pjnd }</span>
			</b><b><span
				style="font-size: 16.0pt; line-height: 200%; font-family: ����;">����ݴ�ѧ�����ѧ��ҵ��������<span></span>
			</span> </b>
		</p>
		<p>
			<span style="font-size: 12.0pt; line-height: 200%; font-family: ����;">ѧ��</span><span
				style="font-size: 12.0pt; line-height: 200%;"> 
				<span>
					<span>&nbsp;&nbsp;</span>${rs.xh } <span>&nbsp;&nbsp;&nbsp;&nbsp;
				</span>
			</span><span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</span> </span><span style="font-size: 14.0pt; line-height: 200%;"><span>&nbsp;&nbsp;&nbsp;</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;</span>
			</span><span style="font-size: 12.0pt; line-height: 200%; font-family: ����;">�ʱ�䣺</span>
			<span style="font-size: 12.0pt; line-height: 200%;">${rs.nowTime } </span>
		</p>
		<table border="1" cellpadding="0" cellspacing="0" class="printtab" 
			   style="text-align: center;font-size: 12.0pt; font-family: ����;" width="99%">
			<tbody>
				<tr class="nowrap" style="height:30px">
					<td width="8%">
						����
					</td>
					<td colspan="3" width="16%">
						${rs.xm }
					</td>
					<td width="9%">
						�Ա�
					</td>
					<td colspan="2" width="9%">
						${rs.xb }
					</td>
					<td colspan="3" width="9%">
						����<br/>����
					</td>
					<td width="11%">
						${rs.csrq }
					</td>
					<td colspan="2" width="8%">
						����
					</td>
					<td colspan="3" width="8%">
						${rs.mzmc }
					</td>
					<td colspan="2" rowspan="3" width="17%">
						<div id="stuImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
							<img style="width:100px;height:130px;" 
								src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
								border="0">
						</div>
					</td>
				</tr>
				<tr>
					<td width="8%">
						����<br/>��ò
					</td>
					<td colspan="3" width="16%">
						${rs.zzmmmc }
					</td>
					<td width="9%">
						����<br/>ְ��
					</td>
					<td colspan="6" width="30%">
						${rs.zw }
					</td>
					<td colspan="2" width="8%">
						ѧ��
					</td>
					<td colspan="3" width="8%">
						${rs.xlmc }
					</td>
				</tr>
				<tr style="height:50px">
					<td width="8%">
						<bean:message key="lable.xb" />
					</td>
					<td colspan="3" width="16%">
						${rs.xymc }
					</td>
					<td width="9%">
						רҵ
					</td>
					<td colspan="6" width="30%">
						${rs.zymc }
					</td>
					<td colspan="2" width="8%">
						ѧ��
					</td>
					<td colspan="3" width="8%">
						${rs.xz }
					</td>
				</tr>
				<tr>
					<td width="8%">
						<br/>
						<br/>
						<br/>
						��
						<br/>
						<br/>
						<br/>
						��
						<br/>
						<br/>
						<br/>
						��
						<br/>
						<br/>
						<br/>
						��
						<br/>
						<br/>
						<br/>
					</td>
					<td colspan="17" valign="top" width="91%">
					</td>
				</tr>
				<tr>
					<td width="8%">
						��
						<br/>
						Ҫ
						<br/>
						��
						<br/>
						��
						<br/>
						��
						<br/>
						��
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						 ��
						 <br/>
						 ��
						 <br/>
						 ��
						 <br/>
						 ��
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
					</td>
					<td colspan="17" valign="top" width="91%">
					</td>
				</tr>
				<tr class="nowrap">
					<td colspan="2" width="12%">
						�༶����
					</td>
					<td width="6%">
						${rs.bjrs }
					</td>
					<td colspan="2" width="15%">
						��У�ڼ���<br/>���ܹ�����
					</td>
					<td colspan="3" width="10%">
						${rs.sfwj }
					</td>
					<td colspan="4" width="24%">
						�۲�����
					</td>
					<td colspan="2" width="7%">
						${rs.zcfbjpm }
					</td>
					<td colspan="3" width="12%">
						�۲�ɼ�
					</td>
					<td width="10%">
						${rs.zcf }
					</td>
				</tr>
				</table>
				
				<br/>
				<br/>
				<br/>
				
				<table border="1" width="99%" cellpadding="0" cellspacing="0" class="printtab" 
				style="text-align: center;font-size: 12.0pt; font-family: ����;">
				<tr style="height:50px;">
					<td colspan="6" width="37%">
						��У�ڼ䲹����Ŀ��
					</td>
					<td colspan="3" width="15%">
						${rs.bjg }
					</td>
					<td colspan="6" width="30%">
						δȡ��ѧ�ֵĿ�Ŀ��
					</td>
					<td colspan="3" width="17%">
					</td>
				</tr>
				<tr>
					<td colspan="2" valign="top" width="12%">
						
						<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/><br/><br/>
					
					</td>
					<td colspan="16" style="border: solid windowtext 1.0pt;"
						valign="bottom" width="87%">
						<p style="text-align: right;" align="right">
							<span style="font-size: 12.0pt;">&nbsp;&nbsp;${rs.xyyj }</span>
						</p>
						<p style="text-indent: 300.0pt;">
							<span style="font-size: 12.0pt; font-family: ����;">������ǩ����</span><span
								style="font-size: 12.0pt;"></span>
						</p>
						<br/>
						<p style="text-align: right; text-indent: 300.0pt;" align="right">
							<span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"><span>&nbsp;&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"><span>&nbsp;&nbsp;&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"></span>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="border: solid windowtext 1.0pt;"
						valign="top" width="12%">
						<br/><br/>Ժ<br/>ϵ<br/>��<br/>��<br/>��<br/>��<br/><br/><br/><br/>
					</td>
					<td colspan="16" style="border: solid windowtext 1.0pt;"
						width="87%">
						<p style="text-align: center; text-indent: 336.0pt;"
							align="center">
							<span style="font-size: 12.0pt;">&nbsp;&nbsp;${rs.xyyj }</span>
						</p>
						<p style="text-indent: 324.0pt;">
							<span style="font-size: 12.0pt; font-family: ����;">�����£� </span><span
								style="font-size: 12.0pt; color: blue;"></span>
						</p>
						<br/>
						<p style="text-align: center;" align="center">
							<span style="font-size: 12.0pt;"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"><span>&nbsp;&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"><span>&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"></span>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" valign="top" width="12%">
						<br/><br/><br/>
						У<br/>��<br/>��<br/>��<br/>��<br/><br/><br/><br/>
						
					</td>
					<td colspan="16" style="border: solid windowtext 1.0pt;"
						valign="top" width="87%">
						<p style="height:100px;">
							<span style="font-size: 12.0pt;">&nbsp;&nbsp;${rs.xxyj }</span>
						</p>
						<p>
							<span style="font-size: 12.0pt;"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </span>
							<span style="font-size: 12.0pt; font-family: ����;">�����£�</span><span
								style="font-size: 12.0pt; color: blue;"></span>
						</p>
						<br/>
						<p>
							<span style="font-size: 12.0pt;"><span>&nbsp;</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style="color: red;"><span>&nbsp;&nbsp;</span> </span> </span><span
								style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"><span>&nbsp;&nbsp;&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"><span>&nbsp;&nbsp;&nbsp;
							</span> </span><span style="font-size: 12.0pt; font-family: ����;">��</span><span
								style="font-size: 12.0pt; color: red;"></span>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="12%">
						<br/>
						<br/>
						<br/>
						��
						<br/>
						<br/>
						<br/>
						<br/>
						ע
						<br/>
						<br/>
						<br/>
						<br/>
					</td>
					<td colspan="16" valign="top" width="87%">
					</td>
				</tr>
			</tbody>
		</table>
		<p style="margin-left: 27.0pt; text-indent: -27.0pt;">
			<span style="font-size: 12.0pt; font-family: ����;">ע</span><span
				style="font-size: 12.0pt;">: 1.</span><span
				style="font-size: 12.0pt; font-family: ����;">�����˽�У�����ĳɼ�����ӡ������Ժ���£�һʽһ�ݣ�</span><span
				style="font-size: 12.0pt;">2.</span><span
				style="font-size: 12.0pt; font-family: ����;">�˱���һʽһ�ݣ������ҵ��������</span><span
				style="font-size: 12.0pt;"></span>
		</p>
	</body>

</html>
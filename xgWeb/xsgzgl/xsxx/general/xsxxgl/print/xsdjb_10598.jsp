<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<title>��������ѧԺѧ���ǼǱ�</title>
		<style>
.font_style td {
	font-size: 14px;
	font-family: ����;
}
</style>

	</head>

	<body lang=ZH-CN style='text-justify-trim: punctuation'>

		<div align=center>
			<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
				<table width="652px" border="0" id="theTable" align="center">
					<tr>
						<td align="center">
							<br />
							<b> <span
								style='font-size: 18.0pt; font-family: ����С���μ���; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>
									<u>${xxmc }��ҵ���嵥</u> </span> <br /> </b>
							<br />
						</td>
					</tr>
					<tr>
						<td align="center">
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id="tab_jbxx">
								<!-- ѧ��������Ϣ begin-->

								<thead>
									<tr onclick="" style="cursor: hand;">
										<th colspan="5">
											<span>������Ϣ</span>
										</th>
									</tr>
								</thead>

								<tbody id="hi_jbxx">
									<tr>
										<th width="13%">
											ѧ��
										</th>
										<td width="30%">
											${rs.xh }
											<input type="hidden" name="xh" id="xh" value="${rs.xh }" />
										</td>
										<th width="13%">
											����
										</th>
										<td>
											${rs.xm }
										</td>
										<td rowspan="5" class="nohover"
											style="vertical-align: middle; text-align: left; width: 115px;">
											<div id="stuImg" class="open_img"
												style="margin-left: 0px; margin-top: 0px; height: 130px">
												<img style="width: 100px; height: 130px;"
													src="xsxx_xsgl.do?method=showPhoto&xh=${rs.xh}" border="0">
											</div>
										</td>
									</tr>
									<tr>
										<th width="13%">
											�Ա�
										</th>
										<td width="30%">
											${rs.xb }
										</td>
										<th width="13%">
											��������
										</th>
										<td>
											${rs.csrq }
										</td>
									</tr>

									<tr>
										<th width="13%">
											�꼶
										</th>
										<td width="30%">
											${rs.nj }
										</td>
										<th>
											ѧ��(��)
										</th>
										<td colspan="">
											${rs.xz }
										</td>
									</tr>
									<tr>
										<th>

											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											${rs.xymc }
										</td>
										<th>
											������ò
										</th>
										<td>
											${rs.zzmmmc }
										</td>
									</tr>
									<tr>
										<th>
											רҵ
										</th>
										<td>
											${rs.zymc }
										</td>

										<th>
											����
										</th>
										<td>
											${rs.mzmc }
										</td>

									</tr>
									<tr>
										<th>
											�༶
										</th>
										<td colspan="">

											${rs.bjmc }
										</td>
										<th>
											ѧ��
										</th>
										<td align="left" colspan="2">
											${rs.xjztm }
										</td>
									</tr>

									<tr>
										<th>
											��ѧʱ��
										</th>
										<td colspan="">
											${rs.rxrq }

										</td>
										<th>
											���֤��
										</th>
										<td align="left" colspan="2">
											${rs.sfzh}
										</td>
									</tr>

									<tr>
										<th>
											����
										</th>
										<td colspan="4">
											${rs.jgmc }
										</td>
									</tr>

									<tr>
										<th>
											�������ڵ�
										</th>
										<td colspan="4">
											${rs.hkszdmc }
										</td>
									</tr>
									<tr>
										<th>
											��Դ��(�߿�ʱ�������ڵ�)
										</th>
										<td colspan="4">
											${rs.sydqmc }
										</td>
									</tr>
								</tbody>

							</table>
						</td>
					</tr>
					<%--<logic:iterate id="ejmk" name="wjlist">
						<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_��ѧ��'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='4'>
											<span>${ejmk.mkmc} </span>
										</th>
									</tr>
								</thead>
					</logic:iterate>--%>
					<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_��ѧ��'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='4'>
											<span>��ѧ��</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="jxj">
										<tr>
										<td colspan='4'>
											<div align='center'>
												�������ݣ�
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="jxj">
										
									<logic:iterate id="temp" name="jxj">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_�����ƺ�'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='4'>
											<span>�����ƺ�</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="rych">
										<tr>
										<td colspan='4'>
											<div align='center'>
												�������ݣ�
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rych">
										
									<logic:iterate id="temp" name="rych">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
					<%--<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_�ۺϲ���'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='7'>
											<span>�ۺϲ���</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="zcf">
										<tr>
										<td colspan='4'>
											<div align='center'>
												�������ݣ�
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="zcf">
										
									<logic:iterate id="temp" name="zcf">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>--%>
					<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" >
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='7'>
											<span>Υ�ʹ���</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="wjlist">
										<tr>
										<td colspan='4'>
											<div align='center'>
												�������ݣ�
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="wjlist">
										
									<logic:iterate id="temp" name="wjlist">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td align="right">
							<br />
							<span
								style='font-size: 12.0pt; font-family: ����; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>
								<br/>ѧ���������£�<br/><br/><br/></span>
						</td>
					</tr>
				</table>
			</html:form>
		</div>
	</body>
</html>
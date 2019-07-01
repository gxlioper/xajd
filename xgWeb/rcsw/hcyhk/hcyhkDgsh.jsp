<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<html:form action="/hcyhk" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="${rs.pkValue}" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button type="button" class="button2"
											onclick="saveData('hcyhk.do?method=hcyhkDgsh&doType=save','')">
											�� ��
										</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
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
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${rs.mzmc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								������ò
							</th>
							<td>
								${rs.zzmmmc }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								${rs.csrq }
							</td>
							<th>
								��ѧʱ��
							</th>
							<td>
								${rs.rxrq }
							</td>
						</tr>
						<tr>
							<th>
								���֤��
							</th>
							<td>
								${rs.sfzh }
							</td>
							<th>
								�ֻ�����
							</th>
							<td>
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								${rs.sqsj}
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<logic:equal name="xxdm" value="12703">
						<tr>
							<th>
								�˳���
							</th>
							<td colspan="3">
								<logic:empty name="rs" property="qdz">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="qdz">
									${rs.qdz }&nbsp;&nbsp;
								</logic:notEmpty>
								��
								<logic:empty name="rs" property="zdz">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="zdz">
									&nbsp;&nbsp;${rs.zdz }
								</logic:notEmpty>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly}
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="shxx">
							<logic:iterate id="s" name="shxx">

								<logic:equal value="${hcyhkForm.shgw }" name="s" property="mc">
									<tr>
										<th>
											��˲���
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
											<html:hidden property="shgw" value="${s.mc }" />
										</th>
										<td>
											<html:select property="shjg" value="${s.shzt }">
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									<tr>
										<th>
											���ʱ��
										</th>
										<td>
											<html:text property="shsj" value="${shsj }"></html:text>
										</td>
										<th>
											�����
										</th>
										<td>
											<html:text property="shr" value="${userName }"
												readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											������
										</th>
										<td colspan="3" style="word-break:break-all;">
											<html:textarea property="shyj" value="${s.shyj }"
												style="width:95%" rows="5"></html:textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual value="${hcyhkForm.shgw }" name="s"
									property="mc">
									<tr>
										<th>
											��˲���
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
										</th>
										<td>
											${s.shzt }
										</td>
									</tr>
									<tr>
										<th>
											���ʱ��
										</th>
										<td>
											${s.shsj }
										</td>
										<th>
											�����
										</th>
										<td>
											${s.shr }
										</td>
									</tr>
									<tr>
										<th>
											������
										</th>
										<td colspan="3" style="word-break:break-all;">
											${s.shyj }
										</td>
									</tr>
								</logic:notEqual>
							</logic:iterate>
						</logic:present>
					</tbody>
				</table>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		dialogArgumentsQueryChick();
			 		window.close();
			 	}
			</script>
		</logic:present>
	</body>
</html>

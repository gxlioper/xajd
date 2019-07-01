<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<html:form action="/njjsXjbj" method="post">
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
										<button class="button2"
											onclick="saveData('njjsXjbj.do?method=xjbjDgsh&doType=save','')">
											�� ��
										</button>
									</logic:notEqual>
									<button class="button2" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>����༯������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								�༶����
							</th>
							<td width="34%">
								<html:select property="save_bjdm" styleId="bj" 
											 style="width:200px" value="${rs.bjdm }" 
											 disabled="true">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
								</html:select>
							</td>
							<th width="16%">
								����༶����
							</th>
							<td width="34%">
								<html:select property="save_yxlx" 
											 styleId="yxlx" 
											 value="${rs.yxlx }" 
											 disabled="true">
									<html:options collection="xjlxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�༶����
							</th>
							<td>
								${rs.bjrs }
							</td>
							<th>
								�೤ѧ��
							</th>
							<td>
								${rs.bzxh }
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								${rs.bzrzgh }
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								${rs.sqr }
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								${rs.sqsj }
							</td>
						</tr>
						<tr>
							<th>
								��Ҫ�¼�
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.zysj }
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
								
								<logic:equal value="${xjbjForm.shgw }" name="s" property="mc">
									<tr>
										<th>
											��˲���
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
											<html:hidden property="shgw" value="${s.mc }"/>
										</th>
										<td>
											<html:select property="shjg" value="${s.shzt }">
												<html:options collection="shztList" property="en" labelProperty="cn"/>
											</html:select>
										</td>
										<tr>
											<th>���ʱ��</th>
											<td>
												<html:text property="shsj" value="${shsj }"></html:text>
											</td>
											<th>�����</th>
											<td><html:text property="shr" value="${userNameReal }" readonly="true"></html:text></td>
										</tr>
										<tr>
											<th>������</th>
											<td colspan="3" style="word-break:break-all;">
												<html:textarea property="shyj" value="${s.shyj }" style="width:95%" rows="5"></html:textarea>
											</td>
										</tr>
								</logic:equal>
								<logic:notEqual value="${xjbjForm.shgw }" name="s" property="mc">
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
										<th>���ʱ��</th>
										<td>${s.shsj }</td>
										<th>�����</th>
										<td>${s.shr }</td>
									</tr>
									<tr>
										<th>������</th>
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

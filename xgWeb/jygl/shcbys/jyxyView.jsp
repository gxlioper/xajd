<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵЭ����ϸ��Ϣ</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />



			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��һ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td align="left" width="34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%" align="right">
								�Ա�
							</th>
							<td width="34%">
								${rs.xb}
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								���֤��
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								ѧУ
							</th>
							<td>
								${rs.xxmc }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
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
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xl }
							</td>
							<th>
								��Դ��
							</th>
							<td>
								${rs.sydq }
							</td>
						</tr>
						<tr>
							<th>
								������ַ
							</th>
							<td colspan="3">
								${rs.syds }${rs.sydx }
							</td>
						</tr>
						<tr>
							<th>
								��ѧ���
							</th>
							<td>
								${rs.rxnf }
							</td>
							<th>
								��ҵ���
							</th>
							<td>
								${rs.bynf }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>�ڶ�����</span>
							</td>
						</tr>
					</thead>
					<tbody>

						<tr>
							<th>
								��ҵ����
							</th>
							<td width="34%">
								${rs.jybzmc }
							</td>
							<th>
								������ò
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								��ȡѧУ
							</th>
							<td>
								${rs.kqxx }
							</td>
							<th>
								���ܲ���
							</th>
							<td>
								${rs.zgdwdm }
							</td>
						</tr>
						<tr>
							<th>
								ʵ�ʾ�ҵ��λ
							</th>
							<td>
								${rs.dwmc }
							</td>
							<th>
								����֤��λ����
							</th>
							<td>
								${rs.bdzdw }
							</td>
						</tr>
						<tr>
							<th>
								�Ƽ�����
							</th>
							<td>
								${rs.tjlxmc }
							</td>
							<th>
								��Լ���
							</th>
							<td>
								${rs.jyqk }
							</td>
						</tr>
						<tr>
							<th>
								δ��ҵ��־
							</th>
							<td>
								${rs.wjybz }
							</td>
							<th>
								δ��ҵԭ��
							</th>
							<td>
								${rs.wjyyy }
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ��Լ
							</th>
							<td>
								${rs.sfjy }
							</td>
							<th>
								�Ƿ�ΥԼ
							</th>
							<td>
								${rs.sfwy }
							</td>
						</tr>
						<tr>
							<th>����֤��<br/>��������</th>
							<td>
								${rs.dtshqk }
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td>
								${rs.lxdz }
							</td>
							<th>
								�ʱ�
							</th>
							<td>
								${rs.yzbh }
							</td>
						</tr>
						<tr>
							<th>
								�ֻ�����
							</th>
							<td>
								${rs.sjhm }
							</td>
							<th>
								Email
							</th>
							<td>
								${rs.dzyx }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>��������</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<logic:equal value="��ͨ��" name="rs" property="xxsh">
									<html:hidden property="save_xxsh" value="δ���"/>
								</logic:equal>
							
								��֯��������
							</th>
							<td width="34%">
								${rs.zzjgdm }
							</td>
							<th>
								��λ��ַ
							</th>
							<td width="34%" style="word-break:break-all;">
								${rs.dwszd }
							</td>
						</tr>
						<tr>
							<th>
								��λ�绰
							</th>
							<td>
								${rs.dwdh }
							</td>
							<th>
								��λ�ʱ�
							</th>
							<td>
								${rs.dwyb }
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								${rs.lsbmmc }
							</td>
							<th>
								��λ���
							</th>
							<td>
								${rs.dwxzmc }
							</td>
						</tr>
						<tr>
							<th>
								��λ��ҵ
							</th>
							<td>
								${rs.jyhymc }
							</td>
							<th>
								ʵ�ʹ������ڵ�
							</th>
							<td>
								${rs.sjdwszdmc }
							</td>
						</tr>
						<tr>
							<th>
								�������յ�
							</th>
							<td style="word-break:break-all;">
								${rs.dajsd }
							</td>
							<th>
								�������յص�ַ
							</th>
							<td style="word-break:break-all;"> 
								${rs.dajsddz }
							</td>
						</tr>
						<tr>
							<th>
								�������յ��ʱ�
							</th>
							<td>
								${rs.dajsdyb }
							</td>
							<th>
								����Ǩ�Ƶ�ַ
							</th>
							<td style="word-break:break-all;">
								${rs.hkqydz }
							</td>
						</tr>
						<tr>
							<th>
								�����ڹ���
							</th>
							<td>
								${rs.syqgz }
							</td>
							<th>
								ת������
							</th>
							<td>
								${rs.zzhgz }
							</td>
						</tr>
						<tr>
							<th>
								רҵ�Կ�
							</th>
							<td>
								${rs.sfdk }
							</td>
							<th>
								�Ǽ�����
							</th>
							<td>
								${rs.djrq }
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									����Ա���
								</th>
								<td>
									${rs.xysh }
								</td>
								<th>
									ѧУ���
								</th>
								<td>
									${rs.xxsh }
								</td>
							</tr>
							<tr>
								<th>
									����Ա������
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									ѧУ������
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.xxshyj }
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="sh" name="doType">
							<logic:equal value="xy" name="userType">
								<tr>
									<th>
										<font color="red">*</font>����Ա���
									</th>
									<td>
										<html:select property="save_xysh" name="rs">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<td>
										<html:hidden property="save_xyshr" value="${userName }" />
										<html:hidden property="save_xyshsj" value="${now }" />
									</td>
									<td></td>
								</tr>
								<tr>
									<th>
										����Ա���
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<tr>
									<th>
										<font color="red">*</font>ѧУ���
									</th>
									<td>
										<html:select property="save_xxsh" name="rs">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<td>
										<html:hidden property="save_xxshr" value="${userName }" />
										<html:hidden property="save_xxshsj" value="${now }" />
									</td>
									<td></td>
								</tr>
								<tr>
									<th>
										ѧУ���
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xxshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal value="sh" name="doType">
										<logic:equal value="xy" name="userType">
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save','save_xysh');">
												����
											</button>
										</logic:equal>
										<logic:notEqual value="xy" name="userType">
											<button 
												onclick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save','save_xxsh ');">
												����
											</button>
										</logic:notEqual>
									</logic:equal>
									<button onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
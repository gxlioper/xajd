<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/checkUtils.js"></script>
<body>
	<html:form action="/jygl" method="post">
		<input type="hidden" id="url" value="/jygl.do?method=jyxywh">
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="userName" name="userName"
			value="${userName }" />
		<input type="hidden" id="message" value="${message }">
		<input type="hidden" name="pkValue" value="${pkValue }">
		<input type="hidden" name="njV" id="njV">
		<input type="hidden" name="xyV" id="xyV">
		<input type="hidden" name="zyV" id="zyV">
		<input type="hidden" name="bjV" id="bjV">

		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<span class="red">*</span>ѧ��
						</th>
						<td width="30%">
							${rs.xh }
							<html:hidden property="save_xh" value="${rs.xh }" />
						</td>
						<th width="16%">
							�Ա�
						</th>
						<td width="30%">
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
							${rs.sydq }${rs.syds }${rs.sydx }
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
					<tr>
						<th>
							����
						</th>
						<td>
							${rs.mzmc }
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
							��ϵ�绰
						</th>
						<td>
							${rs.lxdh }
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
							��ϵ��ַ
						</th>
						<td colspan="3">
							${rs.lxdz }
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>��ҵЭ��</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>��ҵ��־
						</th>
						<td>
							<bean:write property="save_jybzmc" name="rs" />
						</td>
						<th>
							<font color="red">*</font>���˵�λ����
						</th>
						<td>
							<bean:write property="save_dwmc" name="rs" />
						</td>
						
					</tr>
					<tr>
						<th>
							<font color="red">*</font>���˵�λ����
						</th>
						<td>
							<bean:write property="save_dwdm" name="rs" />
						</td>
						<th>
							<font color="red">*</font>���˵�λ����
						</th>
						<td>
							<bean:write property="save_dwxzmc" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>���ܵ�λ����
						</th>
						<td>
							<bean:write property="save_zgdwmc" name="rs" />
						</td>
						<th>
							<font color="red">*</font>���ܵ�λ����
						</th>
						<td>
							<bean:write property="save_zgdwdm" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��λ��������
						</th>
						<td>
							<bean:write property="save_lsbmmc" name="rs" />
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>���˵�λ���ڵ�
						</th>
						<td colspan="3">
							<bean:write property="save_yrdwszd" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��������
						</th>
						<td colspan="3">
							<bean:write property="save_bddq" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>������ʼʱ��
						</th>
						<td>
							<bean:write property="save_bdkssj" name="rs" />
						</td>
						<th>
							<font color="red">*</font>��������ʱ��
						</th>
						<td>
							<bean:write property="save_bdjssj" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							�»������
						</th>
						<td>
							<bean:write property="save_xjcqkmc" name="rs" />
						</td>
						<th>
							<font color="red">*</font>����Ͷ�ݵ�λ
						</th>
						<td>
							<bean:write property="save_datddw" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							����Ͷ�ݵ�ַ
						</th>
						<td colspan="3">
							<bean:write property="save_datddz" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							����Ǩ�Ƶ�ַ
						</th>
						<td colspan="3">
							<bean:write property="save_hkqydz" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							��λ��ϵ��
						</th>
						<td>
							<bean:write property="save_dwlxr" name="rs" />
						</td>
						<th>
							��λ��ϵ�绰
						</th>
						<td>
							<bean:write property="save_dwlxdh" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							רҵ�Ƿ�Կ�
						</th>
						<td>
							<bean:write property="save_sfdk" name="rs" />
						</td>
						<th>
							��ҵ��ҵ
						</th>
						<td>
							<bean:write property="save_jyhymc" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							��ҵ��עһ
						</th>
						<td colspan="3">
							<bean:write property="save_jybz1" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							��ҵ��ע��
						</th>
						<td colspan="3">
							<bean:write property="save_jybz2" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							��ҵ��ע��
						</th>
						<td colspan="3">
							<bean:write property="save_jybz3" name="rs" />
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
					<logic:equal value="view" name="doType">
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								���
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
								<bean:message key="lable.xsgzyxpzxy" />
								������
							</th>
							<td colspan="3">
								${rs.xyshyj }
							</td>
						</tr>
						<tr>
							<th>
								ѧУ������
							</th>
							<td colspan="3">
								${rs.xxshyj }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="sh" name="doType">
						<logic:equal value="xy" name="userType">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									���
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property="save_xyshyj" name="rs"
										style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${userNameReal }
									<html:hidden property="save_xyshr" value="${userName }" />
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${now }
									<html:hidden property="save_xyshsj" value="${now }" />
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<tr>
								<th>
									ѧУ���
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property="save_xxshyj" name="rs"
										style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${userNameReal }
									<html:hidden property="save_xxshr" value="${userName }" />
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${now }
									<html:hidden property="save_xxshsj" value="${now }" />
								</td>
							</tr>
						</logic:notEqual>
					</logic:equal>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<logic:equal value="sh" name="doType">
									<logic:equal value="xy" name="userType">
										<logic:equal value="�˻�" name="rs" property="xxsh">
											<button id="buttonSave"
												onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=ͨ��&save_xxsh=������&doType=save','');">
												ͨ��
											</button>
										</logic:equal>
										<logic:notEqual value="�˻�" name="rs" property="xxsh">
											<button id="buttonSave"
												onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=ͨ��&doType=save','');">
												ͨ��
											</button>
										</logic:notEqual>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=��ͨ��&doType=save','');">
											��ͨ��
										</button>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=�˻�&doType=save','');">
											�˻�
										</button>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xxsh=ͨ��&doType=save',' ');">
											ͨ��
										</button>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xxsh=��ͨ��&doType=save',' ');">
											��ͨ��
										</button>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xxsh=�˻�&doType=save',' ');">
											�˻�
										</button>
									</logic:notEqual>
								</logic:equal>
								<button name="�ر�" onClick="window.close();">
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

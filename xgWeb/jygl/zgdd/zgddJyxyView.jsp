<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵЭ��</a>
			</p>
		</div>


		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=zgddJyxywh" />
			<input type="hidden" id="xxdm" value="${xxdm }" />
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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:equal value="xy" name="userType">
											<logic:equal value="�˻�" name="rs" property="xxsh">
												<button id="buttonSave"
													onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=ͨ��&save_xxsh=������&doType=save','');">
													ͨ��
												</button>
											</logic:equal>
											<logic:notEqual value="�˻�" name="rs" property="xxsh">
												<button id="buttonSave"
													onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=ͨ��&doType=save','');">
													ͨ��
												</button>
											</logic:notEqual>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=��ͨ��&doType=save','');">
												��ͨ��
											</button>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=�˻�&doType=save','');">
												�˻�
											</button>
										</logic:equal>
										<logic:notEqual value="xy" name="userType">
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xxsh=ͨ��&doType=save','');">
												ͨ��
											</button>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xxsh=��ͨ��&doType=save','');">
												��ͨ��
											</button>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xxsh=�˻�&doType=save','');">
												�˻�
											</button>
										</logic:notEqual>
									</logic:notEqual>
										<button id="buttonSave" onclick="window.close();return false;">
											�ر�
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td align="left" width="34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%">
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
								Ժϵ
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
								�̶��绰
							</th>
							<td>
								${rs.lxdh }
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
								QQ
							</th>
							<td>
								${rs.qq }
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
								��ͥ��ַ
							</th>
							<td>
								${map.jtdz }
							</td>
							<th>
								��ͥ�ʱ�
							</th>
							<td>
								${map.jtyb }
							</td>
						</tr>
						<tr>
							<th>
								ͨѸ��ַ
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
							<th width="16%">
								��ҵ���
							</th>
							<td width="34%">
								${rs.jybzmc }
							</td>
							<th width="16%">
								��ҵȥ��
							</th>
							<td width="34%">
								${rs.byqxmc }
							</td>

						</tr>
						<tr>
							<th>
								���˵�λ����
							</th>
							<td width="34%">
								${rs.dwxzmc }
							</td>
							<th>
								���˵�λ
							</th>
							<td>
								${rs.dwmc }
							</td>

						</tr>
						<tr>
							<th>
								��λ��������
							</th>
							<td>
								${rs.lsbmmc }
							</td>
							<th>
								���˵�λ���ڵ�
							</th>
							<td>
								${rs.dwszd }
							</td>
						</tr>
						<tr>
							<th>
								ʵ�����ڵ�
							</th>
							<td>
								${rs.sjdwszdmc }
							</td>
							<th>
								����֤��
							</th>
							<td>
								${rs.bdzh }
							</td>
						</tr>
						<tr>
							<th>
								��ǲʱ��
							</th>
							<td>
								${rs.pqsj }
							</td>
							<th>
								�����������
							</th>
							<td>
								${rs.daclqk }
							</td>
						</tr>
						<tr>
							<th>�Ƿ����</th>
							<td>${rs.isgp }</td>
							<th>רҵ�Ƿ�Կ�</th>
							<td>${rs.sfdk }</td>
						</tr>

						<logic:equal value="��" name="rs" property="isgp">
						<tr>
							<th>
								���ɴ���
							</th>
							<td>
								${rs.gpcs }
							</td>
							<th>
								����ԭ��
							</th>
							<td>
								${rs.gpyy }
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								ԭ��λ����
							</th>
							<td>
								${rs.yrdwmc }
							</td>
							<th>
								ԭ����֤��
							</th>
							<td>
								${rs.ybdzh }
							</td>
						</tr>
						
						<tr>
							<th>
								��ҵ��ҵ
							</th>
							<td>
								${rs.jyhymc }
							</td>
							<th>
								����ȥ��
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.hkqydz }
							</td>
						</tr>
						<tr>
							<th>��չ��1</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx1 }</td>
						</tr>
						<tr>
							<th>��չ��2</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx2 }</td>
						</tr>
						<tr>
							<th>��չ��3</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx3 }</td>
						</tr>
						<tr>
							<th>��չ��4</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx4 }</td>
						</tr>
						<tr>
							<th>��չ��5</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx5 }</td>
						</tr>
						<tr>
							<th>��չ��6</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx6 }</td>
						</tr>
						<tr>
							<th>��չ��7</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx7 }</td>
						</tr>
						<tr>
							<th>��չ��8</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx8 }</td>
						</tr>
						<tr>
							<th>��չ��9</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx9 }</td>
						</tr>
						
						
						<tr>
							<th>
								<font color="red"></font>��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.jybz1 }
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									<bean:message key="lable.xb" />���
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
									<bean:message key="lable.xb" />������
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
										<bean:message key="lable.xb" />���
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
							  		<th>�����</th>
							  		<td>
							  			${userNameReal }
							  			<html:hidden property="save_xyshr" value="${userName }"/>
							  		</td>
							  		<th>���ʱ��</th>
							  		<td>
							  			${now }
							  			<html:hidden property="save_xyshsj" value="${now }"/>
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
							  		<th>�����</th>
							  		<td>
							  			${userNameReal }
							  			<html:hidden property="save_xxshr" value="${userName }"/>
							  		</td>
							  		<th>���ʱ��</th>
							  		<td>
							  			${now }
							  			<html:hidden property="save_xxshsj" value="${now }"/>
							  		</td>
							  	</tr>
							</logic:notEqual>
						</logic:equal>
					</tbody>
				</table>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				close();
				dialogArgumentsQueryChick();
			</script>
		</logic:present>
	</body>
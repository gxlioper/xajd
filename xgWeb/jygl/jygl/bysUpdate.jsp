<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jygl" method="post">
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

			<!-- ���״̬Ϊͨ����ͨ���ģ�ѧ���û�������ȷ�� -->
			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							���ı�ҵ��Ϣ��ǰ���״̬Ϊ��${rs.shzt }����
						</p>
						<a class="close" title="����"
						   onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:equal value="true" name="flg">
						<script defer="defer">
							$('buttonSave').disabled = true;
						</script>
					</logic:equal>
				</logic:notEmpty>	
				<logic:empty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							�Բ�����δ���ϱ�Ϊ��ҵ�� ��
						</p>
						<a class="close" title="����"
						   onclick="this.parentNode.style.display='none'"></a>
					</div>
					
					<script defer="defer">
						$('buttonSave').disabled = true;
					</script>
				</logic:empty>
			</logic:equal>


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
										<!-- ȷ�� -->
										
										<logic:equal value="update" name="doType">
											<logic:equal value="�˻�" name="rs" property="shzt">
												<html:hidden property="save_shzt" value="������" />
											</logic:equal>
										
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=bysUpdate&doType=save',
												'save_xm-save_xbdm-save_sfzh-save_xz-save_xlccdm-save_pyfsdm-save_sfzz-save_sfsf-save_sfdl-save_sydshen');">
												ȷ��
											</button>
										</logic:equal>

										<!-- ��� -->
										<logic:equal value="sh" name="doType">
											<button 
												onclick="saveUpdate('/xgxt/jygl.do?method=bysUpdate&save_shzt=ͨ��&doType=save','');">
												ͨ��
											</button>
											<button 
												onclick="saveUpdate('/xgxt/jygl.do?method=bysUpdate&save_shzt=��ͨ��&doType=save','');">
												��ͨ��
											</button>
											<button 
												onclick="saveUpdate('/xgxt/jygl.do?method=bysUpdate&save_shzt=�˻�&doType=save','');">
												�˻�
											</button>
										</logic:equal>
										
										<logic:equal value="stu" name="userType">
							            	<button type="reset">����</button>
							            </logic:equal>
							            
										<logic:notEqual value="stu" name="userType">
											<button id="buttonSave" onclick="window.close();return false;">
												�ر�
											</button>
										</logic:notEqual>
									</div>
								</td>
							</tr>
						</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��ѧ��
							</th>
							<td width="34%">
								<html:text property="save_xh" maxlength="20" name="rs"
									readonly="true"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>ѧ������
							</th>
							<td width="34%">
								<html:text property="save_xm" maxlength="20" name="rs"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ա�
							</th>
							<td>
								<html:radio property="save_xbdm" value="1" name="rs">��</html:radio>
								<html:radio property="save_xbdm" value="2" name="rs">Ů</html:radio>
							</td>
							<th>
								<font color="red">*</font>���֤��
							</th>
							<td>
								<html:text property="save_sfzh" maxlength="20" name="rs" onblur="chkSfzh(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����רҵ
							</th>
							<td>
								<html:hidden property="save_gbzydm" name="rs" />
								<html:select property="save_gbzydm" name="rs" disabled="true">
									<html:options collection="gbzyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								����ѧУ
							</th>
							<td>
								<html:hidden property="save_xxdm" name="rs" />
								<html:select property="save_xxdm" name="rs" style="width:200px"
									disabled="true">
									<html:options collection="xxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								����
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td>
								<html:hidden property="save_xydm" name="rs" />
								<html:select property="save_xydm" name="rs" disabled="true">
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								����רҵ
							</th>
							<td>
								<html:hidden property="save_zydm" name="rs" />
								<html:select property="save_zydm" name="rs" disabled="true">
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								�����༶
							</th>
							<td>
								<html:hidden property="save_bjdm" name="rs" />
								<html:select property="save_bjdm" name="rs" disabled="true">
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text property="save_csrq" maxlength="20" name="rs"
									styleId="csrq" onclick="return showCalendar('csrq','y-mm-dd');"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:select property="save_mzdm" name="rs">
									<html:options collection="mzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								������ò
							</th>
							<td>
								<html:select property="save_zzmm" name="rs">
									<html:options collection="zzmmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ��ַ
							</th>
							<td>
								<html:text property="save_lxdz" maxlength="100" name="rs"></html:text>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text property="save_yzbh" maxlength="6" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td>
								<html:text property="save_lxdh" maxlength="20" name="rs"
									onblur="checkPhone(this)"></html:text>
							</td>
							<th>
								�ֻ�
							</th>
							<td>
								<html:text property="save_sjhm" maxlength="20" name="rs"
									onblur="checkPhone(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								QQ����
							</th>
							<td>
								<html:text property="save_qq" maxlength="11" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								Email����
							</th>
							<td>
								<html:text property="save_dzyx" maxlength="50" name="rs"
									onblur="if ('' != this.value)if (! isEmail(this.value) ){alert('�Ƿ������ַ!');this.focus();}"></html:text>
							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>ȷ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>
							��ѧʱ��
						</th>
						<td>
							<html:text property="save_rxnf" name="rs" styleId="rxnf"
								onclick="return showCalendar(this.id,'y-mm-dd');"
								onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
						<th>
							��ҵʱ��
						</th>
						<td>
							<html:text property="save_bynf" name="rs" styleId="bynf"
								onclick="return showCalendar(this.id,'y-mm-dd');"
								onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							�������
						</th>
						<td>
							<html:select property="save_zslbdm" name="rs">
								<html:options collection="zsList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							�����ί�൥λ
						</th>
						<td>
							<html:text property="save_dxhwp" maxlength="50" name="rs"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��ҵ����
						</th>
						<td>
							<html:text property="save_xz" maxlength="1" name="rs"
								onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
						</td>

						<th>
							<font color="red">*</font>ѧ�����
						</th>
						<td>
							<html:select property="save_xlccdm" name="rs">
								<html:options collection="xlList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>������ʽ
						</th>
						<td>
							<html:select property="save_pyfsdm" name="rs">
								<html:options collection="pyfsList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							<font color="red">*</font>�Ƿ���ְ
						</th>
						<td>
							<html:radio property="save_sfzz" value="��" name="rs">��</html:radio>
							<html:radio property="save_sfzz" value="��" name="rs">��</html:radio>
						</td>

					</tr>
					<tr>
						<th>
							<font color="red">*</font>�Ƿ�ʦ��
						</th>
						<td>
							<html:radio property="save_sfsf" value="��" name="rs">��</html:radio>
							<html:radio property="save_sfsf" value="��" name="rs">��</html:radio>
						</td>
						<th>
							<font color="red">*</font>�Ƿ����
						</th>
						<td>
							<html:radio property="save_sfdl" value="��" name="rs">��</html:radio>
							<html:radio property="save_sfdl" value="��" name="rs">��</html:radio>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��Դ��
						</th>
						<td colspan="3">
							<html:select property="save_sydshen" styleId="syshen"
								onchange="loadShi('syshen','syshi','syxian')" name="rs">
								<html:options collection="sydqList" property="dm"
									labelProperty="mc" />
							</html:select>
							<html:select property="save_sydshi" styleId="syshi" name="rs"
								onchange="loadXian('syshi','syxian')">
								<html:options collection="sydsList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select property="save_sydxian" styleId="syxian" name="rs">
								<html:options collection="sydxList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
						</td>
					</tr>

					<!-- ȷ������Ϣ -->
					<logic:equal value="update" name="doType">
						<tr>
							<th>
								ȷ����
							</th>
							<td>
								${userNameReal }
								<html:hidden property="save_sfqr" value="��" />
							</td>
							<th>
								ȷ��ʱ��
							</th>
							<td>
								${now }
								<html:hidden property="save_qrrbh" value="${userName }" />
								<html:hidden property="save_qrsj" value="${now }" />
							</td>
						</tr>
					</logic:equal>
					<logic:notEqual value="update" name="doType">
						<tr>
							<th>
								ȷ����
							</th>
							<td>
								${rs.qrr }
							</td>
							<th>
								ȷ��ʱ��
							</th>
							<td>
								${rs.qrsj }
							</td>
						</tr>
					</logic:notEqual>
					</tbody>
					<logic:equal value="sh" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									������
									<br />
									<font color="red"><��500��> </font>
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }"
										onblur="checkLen(this,500)" style="width:80%" rows="5"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${userNameReal }
									<html:hidden property="save_shr" value="${userName }" />
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${now }
									<html:hidden property="save_shsj" value="${now }" />
								</td>
							</tr>
						</tbody>
					</logic:equal>
					
					
					
					
					
					<logic:equal value="view" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									������
								</th>
								<td colspan="3">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${rs.shsj }
								</td>
							</tr>
						</tbody>
					</logic:equal>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alertInfo(''+$('message').value,function(t){
					if (t=="ok") {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			</script>
		</logic:present>
	</body>
</html>

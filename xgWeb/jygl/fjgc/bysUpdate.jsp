<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- ͷ�ļ� -->
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ����Ϣȷ��</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" name="url"
				value="/jygl.do?method=bysdgsb" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-sfzh-xydm-xymc-zydm-zymc-bjdm-bjmc-nj-xz-rxrq-byny-csrq-mz-zzmm" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ����ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal value="update" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=bysUpdate&doType=save',
					'save_xxdm-save_xxmc-save_xh-save_xm-save_xbdm-save_sfzh-save_xydm-save_xymc-save_zydm-save_zymc-save_bjdm-save_bjmc-save_xlccdm-save_xz-save_rxnf-save_bynf-save_sydshen-save_csrq');">
											����
										</button>
									</logic:equal>
									<logic:equal value="sh" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=bysUpdate&doType=save',
					'save_xxdm-save_xxmc-save_xh-save_xm-save_xbdm-save_sfzh-save_xydm-save_xymc-save_zydm-save_zymc-save_bjdm-save_bjmc-save_xlccdm-save_xz-save_rxnf-save_bynf-save_sydshen-save_csrq');">
											����
										</button>
									</logic:equal>
									<button id="buttonSave"
											onclick="window.close();return false;">
											�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>ѧУ����
							</th>
							<td style="width:34%">
								<html:text property="save_xxdm" value="${xxdm }" readonly="true"></html:text>
							</td>
							<th style="width:16%">
								<font color="red">*</font>ѧУ����
							</th>
							<td style="width:34%">
								<html:text property="save_xxmc" value="${xxmc }" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<html:text property="save_xh" styleId="xh" value="${rs.xh}"
									readonly="true" />
							</td>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<html:text property="save_xm" value="${rs.xm }" readonly="true"></html:text>
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
								<html:text property="save_sfzh" maxlength="20" name="rs"
									onblur="if ('' != this.value)chkSfzh(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xb" />����
							</th>
							<td>
								<html:text property="save_xydm" value="${rs.xydm }"
									styleId="xydm" readonly="true"></html:text>
							</td>
							<th>
								<font color="red">*</font><bean:message key="lable.xb" />����
							</th>
							<td>
								<html:text property="save_xymc" value="${rs.xymc }"
									styleId="xymc" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>רҵ����
							</th>
							<td>
								<html:text property="save_zydm" value="${rs.zydm }"
									styleId="zydm" readonly="true"></html:text>
							</td>
							<th>
								<font color="red">*</font>רҵ����
							</th>
							<td>
								<html:text property="save_zymc" value="${rs.zymc }"
									styleId="zymc" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�༶����
							</th>
							<td>
								<html:text property="save_bjdm" value="${rs.bjdm }"
									styleId="bjdm" readonly="true"></html:text>
							</td>
							<th>
								<font color="red">*</font>�༶����
							</th>
							<td>
								<html:text property="save_bjmc" value="${rs.bjmc }"
									styleId="bjmc" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>ѧ�����
							</th>
							<td>
								<html:select property="save_xlccdm" value="${rs.xlccdm }">
									<html:options collection="xlList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<html:text property="save_xz" value="${rs.xz }" styleId="xz"
									maxlength="1" onkeyup="value=value.replace(/[^3-7]/g,'')"></html:text>
							</td>
						</tr>

						<tr>
							<th>
								<font color="red">*</font>��ѧʱ��
							</th>
							<td>
								<html:text property="save_rxnf" name="rs" styleId="rxnf"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>��ҵʱ��
							</th>
							<td>
								<html:select property="save_bynf" value="${rs.bynf }"
									styleId="bynf">
									<html:option value="δ��ҵ">δ��ҵ</html:option>
									<html:options collection="nfList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								������ʽ
							</th>
							<td>
								<html:select property="save_pyfsdm" name="rs">
									<html:options collection="pyfsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text property="save_csrq" value="${rs.csrq }"
									styleId="csrq"  onclick="return showCalendar('csrq','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:select property="save_mzdm" value="${rs.mzdm }"
									styleId="mz">
									<html:options collection="mzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								������ò
							</th>
							<td>
								<html:select property="save_zzmm" value="${rs.zzmm }"
									styleId="zzmm">
									<html:options collection="zzmmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>

						<tr>
							<th>
								�Ƿ���У����
							</th>
							<td>
								<html:select property="save_sflxdk" value="${rs.sflxdk }">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								��У����
							</th>
							<td>
								<html:text property="save_lxdk" maxlength="25"
									value="${rs.lxdk }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ѧ���䶯���
							</th>
							<td>
								<html:text property="save_xjbdqk" maxlength="25"
									value="${rs.xjbdqk }"></html:text>
							</td>
							<th>
								ѧ���ɲ�
							</th>
							<td>
								<html:text property="save_xsgb" maxlength="25"
									value="${rs.xsgb }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								Ӣ��ȼ�
							</th>
							<td>
								<html:text property="save_yydj" maxlength="25"
									value="${rs.yydj }"></html:text>
							</td>
							<th>
								��ϵ��ʽ
							</th>
							<td>
								<html:text property="save_lxdh" maxlength="25"
									value="${rs.lxdh }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������ҳ��
							</th>
							<td>
								<html:text property="save_hmcym" maxlength="20"
									value="${rs.hmcym }" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								������˶����
							</th>
							<td>
								<html:text property="save_hmcqk" maxlength="20"
									value="${rs.hmcqk }"></html:text>
							</td>
						</tr>
						<tr>
							<th>�Ƿ��ҵ��</th>
							<td>
								<html:select property="save_isjys" value="${rs.isjys }">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th></th>
							<td></td>
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
						<tr>
							<th>
								<font color="red"><��500��> </font>��ע
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" value="${rs.bz }"
									style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<logic:equal value="sh" name="doType">
							<tr>
								<th>
									<font color="red">*</font>���״̬
								</th>
								<td>
									<html:select property="save_shzt" name="rs">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									<html:hidden property="save_shsj" value="${now }" />
									<html:hidden property="save_shr" value="${userName }" />
								</th>
								<td></td>
							</tr>
							<tr>
								<th>
									������
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" style="width:85%" rows="5"
										onblur="checkLen(this,500)" name="rs"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						</tbody>
					</table>
				</div>
		</html:form>
		<logic:present name="messge">
			<script>
				alert('$(message)');
				if (window.dialogArguments) {
					Close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>
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
		<script type="text/javascript">
			function setGp(text) {
				if ("��"==text) {
					$('gp1').style.display='';
				} else {
					$('gp1').style.display='none';
				}
			
			}
		</script>
	</head>
	<body onload="setGp($('isGp').value)">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵЭ��ά��</a>
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

			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							���ľ�ҵЭ�鵱ǰ���״̬Ϊ<bean:message key="lable.xb" />���"${rs.xysh }"��ѧУ���"${rs.xxsh }"��
						</p>
						<a class="close" title="����"
							onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:present name="flg">
						<logic:equal value="true" name="flg">
							<script defer="defer">
								$('buttonSave').disabled = true;
							</script>
						</logic:equal>
					</logic:present>
				</logic:notEmpty>

				<logic:present name="isBys">
					<logic:notEmpty name="isBys">
						<div class="prompt" id="div_help">
							<h3>
								<span>��ʾ��</span>
							</h3>
							<p>
								${isBys}
							</p>
							<a class="close" title="����"
								onclick="this.parentNode.style.display='none'"></a>
						</div>
						<script defer="defer">
						$('buttonSave').disabled = true;
					</script>
					</logic:notEmpty>
				</logic:present>
			</logic:equal>

			<!-- ѧԺ���״̬Ϊ�˻أ��޸ĺ�״̬��Ϊ������ -->
			<logic:equal value="�˻�" name="rs" property="xysh">
				<html:hidden property="save_xysh" value="������" />
			</logic:equal>


			<div class="tab">
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
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&doType=save','save_xh-save_jybz-save_byqx-save_dwxz-save_dwmc-save_lsbm-save_yrdwszd');">
												����
											</button>
										</logic:notEqual>
										<logic:equal value="stu" name="userType">
											<button type="reset">
												����
											</button>
										</logic:equal>
										<logic:notEqual value="stu" name="userType">
											<button onclick="window.close();return false;">
												�ر�
											</button>
										</logic:notEqual>
									</div>
								</td>
							</tr>
						</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
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
							</td>
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
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��ҵ״��
							</th>
							<td width="34%">
								<html:select property="save_jybz" styleId="jybz"
									style="width:90%" name="rs">
									<html:options collection="jyqkList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>��ҵȥ��
							</th>
							<td width="34%">
								<html:select property="save_byqx" name="rs" styleId="byqx"
									style="width:90%" onchange="setGp(this)">
									<html:options collection="byqxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>���˵�λ����
							</th>
							<td width="34%">
								<html:select property="save_dwxz" styleId="dwxz"
									style="width:90%" name="rs">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���˵�λ
							</th>
							<td>
								<html:hidden property="save_dwdm" styleId="dwdm" name="rs" />
								<html:text property="save_dwmc" readonly="readonly"
									styleId="dwmc" style="width:65%" name="rs"></html:text>


								<button
									onclick="showTopWin('/xgxt/jygl.do?method=compayData&flg=dw',800,500);"
									id="buttonFindStu" class="btn_01">
									ѡ��
								</button>


							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ��������
							</th>
							<td>
								<html:select property="save_lsbm" style="width:90%" name="rs">
									<html:options collection="lsbmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���˵�λ���ڵ�
							</th>
							<td>
								<html:select property="save_yrdwszd" styleId="yrdwszddm" style="width:90%" name="rs">
									<html:options collection="dwszdList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ʵ�����ڵ�
							</th>
							<td>
								<html:select property="save_sjdwszd" style="width:90%" name="rs">
									<html:options collection="dwszdList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								����֤��
							</th>
							<td>
								<html:text property="save_bdzh"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"
									style="width:90%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ǲʱ��
							</th>
							<td>
								<html:text property="save_pqsj" style="width:90%" styleId="pqsj"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" maxlength="20" name="rs"></html:text>
							</td>
							<th>
								�����������
							</th>
							<td>
								<html:text property="save_daclqk" maxlength="50"
									style="width:90%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ����
							</th>
							<td>
								<html:select property="save_isgp" onchange="setGp(this.value)"
									styleId="isGp" style="width:90%" name="rs">
									<html:option value="">----��ѡ��----</html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								רҵ�Ƿ�Կ�
							</th>
							<td>
								<html:select property="save_sfdk" style="width:90%" name="rs">
									<html:option value="">----��ѡ��----</html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>




						<tr style="display:none" id="gp1">
							<th>
								���ɴ���
							</th>
							<td>
								<html:text property="save_gpcs" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="3"
									style="width:90%"></html:text>
							</td>
							<th>
								����ԭ��
							</th>
							<td>
								<html:text property="save_gpyy" maxlength="100"
									style="width:90%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ԭ��λ����
							</th>
							<td>

								<html:hidden property="save_yyrdw" styleId="ydw" name="rs" />
								<input type="text" readonly="readonly" id="ydwmc"
									value="${rs.yrdwmc }" />

								<button
									onclick="showTopWin('/xgxt/jygl.do?method=compayData&flg=ydw',800,500);"
									id="buttonFindStu" class="btn_01">
									ѡ��
								</button>

							</td>
							<th>
								ԭ����֤��
							</th>
							<td>
								<html:text property="save_ybdzh" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ��ҵ
							</th>
							<td>
								<html:select property="save_jyhy" style="width:90%" name="rs">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								����ȥ��
							</th>
							<td>
								<html:text property="save_hkqydz" maxlength="100" name="rs"
									style="width:80%" style="width:90%"></html:text>
							</td>
						</tr>

						<tr>
							<th>
								��չ��1
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx1" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								��չ��2
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx2" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								��չ��3
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx3" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								��չ��4
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx4" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								��չ��5
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx5" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��չ��6
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx6" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��չ��7
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx7" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								��չ��8
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx8" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								��չ��9
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx9" name="rs" rows="3"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>

						<tr>
							<th>
								<font color="red"><��150��> </font>��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_jybz1" name="rs" rows="4"
									style="width:90%" onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
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
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
	<body onload="setGp($('isgp').value)">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
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

			<logic:equal value="stu" name="userType" scope="session">
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


			<div class="tab" style="margin-top: 0px; padding-top: 0px">
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
								<html:text property="save_xh" value="${rs.xh }" readonly="true"></html:text>
								<logic:notEqual value="stu" name="userType">
									<button
										onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
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
							<td colspan="4">
								<span>��ҵЭ��</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��ҵ״��
							</th>
							<td width="34%">
								<html:select property="save_jybz" styleId="jybz"
									style="width:90%">
									<html:options collection="jyqkList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>��ҵȥ��
							</th>
							<td width="34%">
								<html:select property="save_byqx" styleId="jybz"
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
									style="width:90%">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���˵�λ
							</th>
							<td>
								<html:hidden property="save_dwdm" styleId="dwdm"/>
								<html:text property="save_dwmc" readonly="true" styleId="dwmc"  style="width:65%"></html:text>
								
								
								<button onclick="showTopWin('/xgxt/jygl.do?method=compayData&flg=dw',800,500);"
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
								<html:select property="save_lsbm" style="width:90%">
									<html:options collection="lsbmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���˵�λ���ڵ�
							</th>
							<td>
								<html:select property="save_yrdwszd" style="width:90%" styleId="yrdwszddm">
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
								<html:select property="save_sjdwszd" style="width:90%">
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
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ǲʱ��
							</th>
							<td>
								<html:text property="save_pqsj" style="width:90%" styleId="pqsj"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" maxlength="20"></html:text>
							</td>
							<th>
								�����������
							</th>
							<td>
								<html:text property="save_daclqk" maxlength="50"
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>�Ƿ����</th>
							<td>
								<html:select property="save_isgp" onchange="setGp(this.value)" styleId="isgp" style="width:90%">
									<html:option value="">----��ѡ��----</html:option>
									<html:options collection="isNot" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								רҵ�Ƿ�Կ�
							</th>
							<td>
								<html:select property="save_sfdk" style="width:90%">
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
								<html:text property="save_gpcs"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="3"
									style="width:90%"></html:text>
							</td>
							<th>
								����ԭ��
							</th>
							<td>
								<html:text property="save_gpyy" maxlength="100"
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ԭ��λ����
							</th>
							<td>
								<html:hidden property="save_yyrdw" styleId="ydw"/>
								<input type="text" readonly="readonly" id="ydwmc"/>
							
								<button onclick="showTopWin('/xgxt/jygl.do?method=compayData&flg=ydw',800,500);"
										id="buttonFindStu" class="btn_01">
										ѡ��
								</button>
								
							</td>
							<th>
								ԭ����֤��
							</th>
							<td>
								<html:text property="save_ybdzh"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							
							<th>
								��ҵ��ҵ
							</th>
							<td>
								<html:select property="save_jyhy" style="width:90%">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								����ȥ��
							</th>
							<td>
								<html:text property="save_hkqydz" maxlength="100"
									style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th>��չ��1</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx1" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>��չ��2</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx2" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>��չ��3</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx3" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>��չ��4</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx4" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>��չ��5</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx5" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>��չ��6</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx6" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>��չ��7</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx7" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>��չ��8</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx8" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>��չ��9</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_kzx9" rows="3" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_jybz1" rows="4" style="width:90%"
									onblur="checkLen(this,150)"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave"
										onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxywh&doType=save','save_xh-jybz-save_byqx-save_dwxz-save_dwmc-save_lsbm-save_yrdwszd');">
										�� ��
									</button>
									<button type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
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
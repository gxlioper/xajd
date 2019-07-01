<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
<!--
	function setKqxx(text) {
		if ('4'==text){
			$('kqxx').disabled=false;
		} else {
			$('kqxx').disabled=true;
		}
	}
//-->
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh"/>
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

			<div class="tab" >
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
								<span class="red">*</span>ѧ��
							</th>
							<td width="30%">
								<html:text property="save_xh" value="${rs.xh }" readonly="true"></html:text>
								<logic:notEqual value="stu" name="userType">
									<button
										onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);"
										id="buttonFindStu">
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
								ѧ��
							</th>
							<td>
								${rs.xz }
							</td>
							<th>
								������ʽ
							</th>
							<td>
								${rs.pyfs }
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
							<th colspan="4">
								<span>�ڶ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��ҵ����
							</th>
							<td width="34%">
								<html:select property="save_jybz" styleId="jybz"
									style="width:85%" onchange="setKqxx(this.value);">
									<html:options collection="jybzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th width="16%">
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
								<html:text property="save_kqxx" maxlength="50" style="width:85%"
									styleId="kqxx"></html:text>
							</td>
							<th>
								���ܲ���
							</th>
							<td>
								
								<html:text property="save_zgdwdm" style="width:85%" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ʵ�ʾ�ҵ��λ
							</th>
							<td>
								<html:hidden property="save_dwdm" styleId="dwdm" />
								
								<html:text property="save_dwmc"  styleId="dwmc" readonly="true" />
								<button onclick="showTopWin('/xgxt/jygl.do?method=compayData',800,500);"
										id="buttonFindStu">
										ѡ��
								</button>
								
							</td>
							<th>
								����֤��λ����
							</th>
							<td>
								<html:text property="save_bdzdw" style="width:85%" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ƽ�����
							</th>
							<td>
								<html:select property="save_tjlx" style="width:85%">
									<html:options collection="tjlxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								��Լ���
							</th>
							<td>
								<html:text property="save_jyqk" maxlength="50" style="width:85%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								δ��ҵ��־
							</th>
							<td>
								<html:text property="save_wjybz" maxlength="25"
									style="width:85%"></html:text>
							</td>
							<th>
								δ��ҵԭ��
							</th>
							<td>
								<html:text property="save_wjyyy" maxlength="50"
									style="width:85%"></html:text>
							</td>
						</tr>

						<tr>
							<th>
								�Ƿ��Լ
							</th>
							<td>
								<html:select property="save_sfjy" value="${rs.sfjy }"
									style="width:85%">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								�Ƿ�ΥԼ
							</th>
							<td>
								<html:select property="save_sfwy" value="${rs.sfwy }"
									style="width:85%">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>����֤��<br/>��������</th>
							<td>
								<html:text property="save_dtshqk" maxlength="50" style="width:85%"></html:text>
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
							</td>
							<td>
								${rs.sjhm }
							</td>
							<th>
								Email
							</td>
							<td>
								${rs.dzyx }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								��֯��������
							</th>
							<td width="34%">
								<html:text property="save_zzjgdm" maxlength="20"
									style="width:85%" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th width="16%">
								��λ��ַ
							</th>
							<td width="34%" style="word-break:break-all;">
								<html:text property="save_yrdwszd" styleId="yrdwszd" maxlength="100" style="width:85%" />
							</td>
						</tr>
						<tr>
							<th>
								��λ�绰
							</th>
							<td>
								<div class="pos" style="z-index:2">
									<html:text property="save_dwlxdh" maxlength="20"
										style="width:85%" styleId="dwlxdh"  
										onblur="checkPhoneV4(this)"></html:text>
									<div id="phoneErrow" class="hide" >
								       <p>�绰��ʽ����ȷ</p>
								    </div>
								 </div>
							</td>
							<th>
								��λ�ʱ�
							</th>
							<td>
								<html:text property="dwyb" styleId="dwyb" style="width:85%"
									maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								<html:select property="save_lsbm" style="width:85%">
									<html:options collection="lsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								��λ���
							</th>
							<td>
								<html:select property="save_dwxz" style="width:85%" 
									styleId="dwxz">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��λ��ҵ
							</th>
							<td>
								<html:select property="save_jyhy" style="width:85%"
									styleId="hyfl">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								ʵ�ʹ������ڵ�
							</th>
							<td>
								<html:select property="save_sjdwszd" style="width:85%" >
									<html:options collection="dwdzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th >
								�������յ�
							</th>
							<td style="word-break:break-all;">
								<html:text property="save_dajsd" maxlength="100" style="width:85%"></html:text>
							</td>
							<th>
								�������յص�ַ
							</th>
							<td style="word-break:break-all;">
								<html:text property="save_dajsddz" maxlength="50"
									style="width:85%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�������յ��ʱ�
							</th>
							<td>
								<html:text property="save_dajsdyb" maxlength="6"
									style="width:85%" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								����Ǩ�Ƶ�ַ
							</th>
							<td style="word-break:break-all;">
								<html:text property="save_hkqydz" maxlength="100"
									style="width:85%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�����ڹ���
							</th>
							<td>
								<html:text property="save_syqgz" maxlength="15"
									style="width:85%" onblur="checkMoney(this)"></html:text>
							</td>
							<th>
								ת������
							</th>
							<td>
								<html:text property="save_zzhgz" maxlength="15"
									style="width:85%" onblur="checkMoney(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								רҵ�Կ�
							</th>
							<td>
								<html:select property="save_sfdk" style="width:85%">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								�Ǽ�����
							</th>
							<td>
								<html:text property="save_djrq" maxlength="20" style="width:85%"
									styleId="djrq" onblur='dateFormatChg(this)'
									onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
							</td>
						</tr>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="saveButton"
										onclick="saveUpdate('/xgxt/jygl.do?method=jyxywh&doType=save','save_xh-save_jybz');">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
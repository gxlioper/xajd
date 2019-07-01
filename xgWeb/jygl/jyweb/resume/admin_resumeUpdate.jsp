<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function loadStu(code,obj){
				if (13==code){
					refreshForm("jywebUseCheckSession.do?method=resumeUpdate&mklx=admin&pkValue="+obj.value);
				}
			}
		</script>
	</head>
	<body>

		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="userName" id="userName" value="${jyweb_userName }" />
			<input type="hidden" id="url" name="url" value="/jywebUseCheckSession.do?method=resumeUpdate&mklx=admin" />
		    <input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />

			<div class="tab" id="table1">
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
							<th width="14%">
								<span class="red">*</span> ѧ��
							</th>
							<td width="86%" colspan="3">
								<p class="choose">
									<logic:notEqual value="update" name="doType">
										<html:text property="save_xh" value="${rs.xh }" styleId="xh"
										onkeypress="loadStu(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')"></html:text>
										<button onclick="showTopWin('/xgxt/stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
										</logic:notEqual>
										<logic:equal value="update" name="doType">
											<html:text property="save_xh" value="${rs.xh }" styleId="xh"
											readonly="true"
										></html:text>
									</logic:equal>
										
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<html:hidden property="save_zdyrdw" value="${rs.zdyrdw }" />
									<input type="checkbox" name="zdyrdw"
										onclick="if(this.checked){$('save_zdyrdw').value='��'}else{$('save_zdyrdw').value='��'}"
										checked="true" />
									ֻ�����˵�λ����
								</p>
							</td>
						</tr>
						<tr>
							<th width="14%">
								<span class="red">*</span> ���֤��
							</th>
							<td width="36%">
								<html:hidden property="save_xh" value="${rs.xh }" />
								<html:text property="save_sfzh" value="${rs.sfzh }"></html:text>
								<span class="bm"> 
									<html:hidden property="save_sfzhbm" value="${rs.sfzhbm }" /> 
									<input type="checkbox" name="sfzhbm"
										   onclick="if(this.checked){$('save_sfzhbm').value='��'}else{$('save_sfzhbm').value='��'}"/> ���� </span>
							</td>
							<th width="14%">
								<font color="red">*</font>����
							</th>
							<td width="36%">
								<html:text property="save_xm" value="${rs.xm }" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ա�
							</th>
							<td>
								<html:select property="save_xb" value="${rs.xb }">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<html:select property="save_mzmc" value="${rs.mzmc }">
									<html:options collection="mzList" property="mc"
										labelProperty="mc" />
								</html:select>

							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text property="save_csrq" value="${rs.csrq }"
									onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="save_csrq"
													onblur="dateFormatChg(this)"
								></html:text>
							</td>
							<th>
								<font color="red">*</font>��Դ����
							</th>
							<td>
								<html:text property="save_sydq"
									value="${rs.sydq }${rs.syds }${rs.sydx }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������ò
							</th>
							<td>
								<html:select property="save_zzmmmc" value="${rs.zzmmmc }" styleId="save_zzmmmc">
									<html:options collection="zzmmList" property="dm"
										labelProperty="mc" />
								</html:select>

							</td>
							<th>
								<font color="red">*</font>����״��
							</th>
							<td>
								<html:text property="save_jkzk" maxlength="20"
									value="${rs.jkzk }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ͥ��ַ
							</th>
							<td colspan="3">
								<html:text property="save_jtdz" maxlength="50" style="width:90%"
									value="${rs.jtdz }"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="5">
								<span>��������</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��ҵ<bean:message key="lable.xb" />
							</th>
							<td>
								<html:text property="save_xymc" value="${rs.xymc }"
									readonly="true"></html:text>
							</td>
							<th>
								��ѧ����
							</th>
							<td>
								<html:text property="save_rxnd" value="${rs.rxnd }${rs.rxrq }"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����רҵ
							</th>
							<td>
								<html:text property="save_zymc" readonly="true"
									value="${rs.zymc }"></html:text>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text property="save_xz" value="${rs.xz }" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����רҵ
							</th>
							<td>
								<html:text property="save_fxzy" maxlength="50"
									value="${rs.fxzy }"></html:text>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text property="save_xl" value="${rs.xl }" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�����ˮƽ
							</th>
							<td>
								<html:text property="save_jsjsp" maxlength="20"
									value="${rs.jsjsp }"></html:text>
							</td>
							<th>
								����ˮƽ
							</th>
							<td>
								<html:text property="save_wysp" maxlength="20"
									value="${rs.wysp }"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="5">
								<span>��ϵ��ʽ</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��ϵ��ַ
							</th>
							<td>
								<html:text property="save_lxdz" value="${rs.lxdz }"
									maxlength="50"></html:text>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text property="save_yzbh" value="${rs.yzbh }"
									maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text property="save_dzyx" value="${rs.dzyx }"
									maxlength="25"></html:text>
							</td>
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
								<html:text property="save_lxdh" value="${rs.lxdh }"
									maxlength="20"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>��ְ����</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td colspan="3">
								<html:text property="save_gzxz" value="${rs.gzxz }"
									maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>Ŀ���λ
							</th>
							<td colspan="3">
								<html:text property="save_mbgw" value="${rs.mbgw }"
									maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>Ŀ��ص�
							</th>
							<td colspan="3">
								<html:text property="save_gzdd" value="${rs.gzdd }"
									maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����нˮ
							</th>
							<td colspan="3">
								<html:text property="save_qwxs" maxlength="20"
									onblur="checkMoney(this)" value="${rs.qwxs }"></html:text>
							</td>
						</tr>
						<tr>
							<th valign="top">
								Ŀ��ְ��
							</th>
							<td colspan="3">
								<html:textarea property="save_mbzn" rows="7"
									style="float:left;width:90%"  value="${rs.mbzn }"/>
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
									<button name="��һҳ"
										onclick="$('table1').className='hide';$('table2').className='tab'">
										��һҳ
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

				</table>

			</div>
			<div class="hide" id="table2">

				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td colspan="3">
								<span>��Ṥ�����</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center" style="width:30%">
								��ֹʱ��
							</td>
							<td align="center" style="width:40%">
								������λ����������֯��
							</td>
							<td align="center" style="width:30%">
								����ְ��
							</td>
						</tr>
						<tr>
							<td align="center">
								<p class="choose">
									<html:text property="save_shgzkssj1" 
										styleId="shgzkssj1"
										value="${rs.shgzkssj1 }" 
										style="width:75px"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
									<html:text property="save_shgzjssj1" 
										styleId="shgzjssj1"
										value="${rs.shgzjssj1 }" 
										style="width:75px"
										onblur='dateFormatChg(this)'
										onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
								</p>
							</td>
							<td align="center">
								<html:text property="save_gzdw1" maxlength="50"
									style="width:90%" value="${rs.gzdw1 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_srzw1" maxlength="20"
									style="width:90%" value="${rs.srzw1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<p class="choose">
									<html:text property="save_shgzkssj2" 
										styleId="shgzkssj2"
										value="${rs.shgzkssj2 }" 
										style="width:75px"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)' />
									<html:text property="save_shgzjssj2" 
										styleId="shgzjssj2"
										value="${rs.shgzjssj2 }" 
										style="width:75px"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)'></html:text>
								</p>
							</td>
							<td align="center">
								<html:text property="save_gzdw2" maxlength="50"
									style="width:90%" value="${rs.gzdw2 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_srzw2" maxlength="20"
									style="width:90%" value="${rs.srzw2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<p class="choose">
									<html:text property="save_shgzkssj3" 
										styleId="shgzkssj3"
										value="${rs.shgzkssj3 }" 
										style="width:75px"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur='dateFormatChg(this)'></html:text>
									<html:text property="save_shgzjssj3" 
										styleId="shgzjssj3"
										onclick="showCalendar(this.id,'y-mm-dd')"
										value="${rs.shgzjssj3 }" style="width:75px"
										onblur='dateFormatChg(this)'></html:text>
								</p>
							</td>
							<td align="center">
								<html:text property="save_gzdw3" maxlength="50"
									style="width:90%" value="${rs.gzdw3 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_srzw3" maxlength="20"
									style="width:90%" value="${rs.srzw3 }"></html:text>
							</td>
						</tr>
					</tbody>

					<thead>
						<tr>
							<td colspan="2">
								<span>���ʵ�����</span>
							<td>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td width="30%" align="center">
								��ֹʱ��
							</td>
							<td width="40%" align="center">
								������λ��ʵ������
							</td>
							<td width="30%" align="center">
								ʵ�����Ч
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_shsjkssj1" 
									styleId="shsjkssj1"
									onclick="showCalendar(this.id,'y-mm-dd')"
									style="width:75px" value="${rs.shsjkssj1 }"
									onblur='dateFormatChg(this)'></html:text>
								<html:text property="save_shsjjssj1" 
									styleId="shsjjssj1"
									onclick="showCalendar(this.id,'y-mm-dd')"
									style="width:75px" value="${rs.shsjjssj1 }"
									onblur='dateFormatChg(this)'></html:text>
							</td>
							<td align="center">
								<html:text property="save_shsjdw1" maxlength="50"
									style="width:90%" value="${rs.shsjdw1 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_shsjcx1" maxlength="50"
									style="width:90%" value="${rs.shsjcx1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_shsjkssj2" 
									styleId="shsjkssj2"
									onclick="showCalendar(this.id,'y-mm-dd')"
									style="width:75px" value="${rs.shsjkssj2 }"
									onblur='dateFormatChg(this)'></html:text>
								<html:text property="save_shsjjssj2" 
									styleId="shsjjssj2"
									onclick="showCalendar(this.id,'y-mm-dd')"
									style="width:75px" value="${rs.shsjjssj2 }"
									onblur='dateFormatChg(this)'></html:text>
							</td>
							<td align="center">
								<html:text property="save_shsjdw2" maxlength="50"
									style="width:90%" value="${rs.shsjdw2 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_shsjcx2" maxlength="50"
									style="width:90%" value="${rs.shsjcx2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_shsjkssj3" 
									styleId="shsjkssj3"
									onclick="showCalendar(this.id,'y-mm-dd')"
									style="width:75px" value="${rs.shsjkssj3 }"
									onblur='dateFormatChg(this)'></html:text>
								<html:text property="save_shsjjssj3" 
									styleId="shsjjssj3"
									onclick="showCalendar(this.id,'y-mm-dd')"
									style="width:75px" value="${rs.shsjjssj3 }"
									onblur='dateFormatChg(this)'></html:text>
							</td>
							<td align="center">
								<html:text property="save_shsjdw3" maxlength="50"
									style="width:90%" value="${rs.shsjdw3 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_shsjcx3" maxlength="50"
									style="width:90%" value="${rs.shsjcx3 }"></html:text>
							</td>
						</tr>
					</tbody>

					<thead>
						<tr>
							<td colspan="3">
								<span>�����</span>
							</td>
						</tr>
					</thead>
					<tbody">
						<tr>
							<td width="30%" align="center">
								ʱ��
							</td>
							<td width="40%" align="center">
								�����ƺ�
							</td>
							<td width="30%" align="center">
								��������
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_hjsj1" 
									styleId="hjsj1"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur='dateFormatChg(this)' style="width:150px"
									value="${rs.hjsj1 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_rych1" maxlength="50"
									style="width:90%" value="${rs.rych1 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_bzjg1" maxlength="50"
									style="width:90%" value="${rs.bzjg1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_hjsj2" 
									styleId="hjsj2"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur='dateFormatChg(this)' style="width:150px"
									value="${rs.hjsj2 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_rych2" maxlength="50"
									style="width:90%" value="${rs.rych2 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_bzjg2" maxlength="50"
									style="width:90%" value="${rs.bzjg2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_hjsj3" 
									styleId="hjsj3"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur='dateFormatChg(this)' style="width:150px"
									value="${rs.hjsj3 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_rych3" maxlength="50"
									style="width:90%" value="${rs.rych3 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_bzjg3" maxlength="50"
									style="width:90%" value="${rs.bzjg3 }"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="3">
								<span>����֤��</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="30%" align="center">
								ʱ��
							</td>
							<td width="40%" align="center">
								֤������
							</td>
							<td width="30%" align="center">
								��֤����
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_jnzssj1" value="${rs.jnzssj1 }"
									styleId="save_jnzssj1"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur='dateFormatChg(this)' style="width:150px"></html:text>
							</td>
							<td align="center">
								<html:text property="save_jnzsmc1" maxlength="50"
									style="width:90%" value="${rs.jnzsmc1 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_jnzsbzjg1" maxlength="50"
									style="width:90%" value="${rs.jnzsbzjg1 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_jnzssj2" value="${rs.jnzssj2 }"
									styleId="save_jnzssj2"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur='dateFormatChg(this)' style="width:150px"></html:text>
							</td>
							<td align="center">
								<html:text property="save_jnzsmc2" maxlength="50"
									style="width:90%" value="${rs.jnzsmc2 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_jnzsbzjg2" maxlength="50"
									style="width:90%" value="${rs.jnzsbzjg2 }"></html:text>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text property="save_jnzssj3" value="${rs.jnzssj3 }"
									styleId="save_jnzssj3"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur='dateFormatChg(this)' style="width:150px"></html:text>
							</td>
							<td align="center">
								<html:text property="save_jnzsmc3" maxlength="50"
									style="width:90%" value="${rs.jnzsmc3 }"></html:text>
							</td>
							<td align="center">
								<html:text property="save_jnzsbzjg3" maxlength="50"
									style="width:90%" value="${rs.jnzsbzjg3 }"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="3">
								<span>�����Ƽ�</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_zwtj" value="${rs.zwtj }"
									style="width:95%" rows="8" onblur="checkLen(this,2000)"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="��һҳ"
										onclick="$('table2').className='hide';$('table1').className='tab'">
										��һҳ
									</button>
									<logic:notPresent name="doType">
										<button
											onclick="saveUpdate('jywebUseCheckSession.do?method=resumeUpdate&doType=save&mklx=admin',
		              		'save_sfzh-save_xm-save_xb-save_mzmc-save_csrq-save_sydq-save_zzmmmc-save_jkzk-save_jtdz-save_dzyx-save_lxdh-save_gzxz-save_gzdd-save_qwxs')">
											����
										</button>

									</logic:notPresent>
									<logic:present name="doType">
										<button
											onclick="saveUpdate('jywebUseCheckSession.do?method=resumeUpdate&doType=modify&mklx=admin',
		              		'save_sfzh-save_xm-save_xb-save_mzmc-save_csrq-save_sydq-save_zzmmmc-save_jkzk-save_jtdz-save_dzyx-save_lxdh-save_gzxz-save_gzdd-save_qwxs')">
											����
										</button>
									</logic:present>
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

		<logic:present name="message">
			<script>
 			alert("${message}");
 			window.close();
 			dialogArgumentsQueryChick();
 			
 		</script>
		</logic:present>

	</body>
</html>

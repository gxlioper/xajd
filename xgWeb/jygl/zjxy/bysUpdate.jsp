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
		<script>
			function setReadOnly(){
				jQuery('input',jQuery('#xsxxTab')).attr('readonly',true);
				jQuery('input',jQuery('#xsxxTab')).unbind('click').removeAttr('onclick');
				jQuery('input[type=radio]',jQuery('#xsxxTab')).attr('disabled',true);
				jQuery('select',jQuery('#xsxxTab')).attr('disabled',true);
			}
		
		
			function saveForm(id){
				var flg = true;
				var key = new Array();
				if(id!=""){
					key=id.split("-");
				}
			
				if(key.length > 0){
					for(var i=0;i<key.length;i++){
						if(jQuery('#'+key[i])){
							if(jQuery('#'+key[i]).val() == ""){
								alert('��"*"���Ϊ�գ���ȷ��');
								flg = false;
								break;
							}
						}
					}
				}
				if (flg){
				   //���ȷ����Ϣ���Ƿ����޸Ĺ���
					var yzbh = jQuery('#yzbh').val();
					var lxdz = jQuery('#lxdz').val();
					var zzmm = jQuery('#zzmm').val();
					var lxdh = jQuery('#lxdh').val();
					var sjhm = jQuery('#sjhm').val();
					var qq = jQuery('#qq').val();
					var dzyx = jQuery('#dzyx').val();
					var syshen = jQuery('#syshen').val();
					var syshi = jQuery('#syshi').val();
					var syxian = jQuery('#syxian').val();
				
					jQuery.ajaxSetup({async: false});
					jQuery.post('jyglAjax.do?method=getBysxx',{xh:jQuery('#xh').val()},function(data){
					
						if (data.yzbh.trim() != yzbh.trim() || data.lxdz.trim() != lxdz.trim() 
						 || data.zzmm.trim() != zzmm.trim() || data.lxdh.trim() != lxdh.trim() 
						 || data.sjhm.trim() != sjhm.trim() || data.dzyx.trim() != dzyx.trim()
						 || data.qq.trim() != qq.trim() || data.sydshen.trim() != syshen.trim()
						 || data.sydshi.trim() != syshi.trim() || data.sydxian.trim() != syxian.trim()){
						 	jQuery('#sfxg').val('��');
						 }
					},'json');
					refreshForm('jygl.do?method=bysUpdate&doType=save');
					jQuery.ajaxSetup({async: true});
				}
			}
		</script>
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
			<input type="hidden" name="pkValue" value="${rs.pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<html:hidden property="save_xh" name="rs" styleId="xh" />
			<html:hidden property="save_sfxg" name="rs" styleId="sfxg" />

			<!-- ���״̬Ϊͨ����ͨ���ģ�ѧ���û�������ȷ�� -->
			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>��ʾ��</span>
						</h3>

						<logic:equal name="isQrsj" value="false">
							<p>
								<font color="red"> ���ڲ��Ǳ�ҵ����Ϣȷ��ʱ��,����${cssz.bysqrkssj
									}��&nbsp;��&nbsp;${cssz.bysqrjssj }������дȷ����Ϣ��</font>
							</p>
							<script defer="defer">
								jQuery('#buttonSave').attr('disabled',true);
								jQuery('#buttonSave').attr('class','disabled');
							</script>
						</logic:equal>
						<logic:notEqual name="isQrsj" value="false">
							<p>
								1:
								<font color="red"> ����ȷ�ϵ���Ϣ�ڱ�ҵ��ҵ�����зǳ���Ҫ���������д׼ȷ;</font>
								<br />
								2:
								<font color="red"> "ȷ����Ϣһ"�����޸ģ����г����뼰ʱ���ҵ����Ա��ϵ;</font>
								<br />
								3: ���ı�ҵ��Ϣ��ǰ��˽����<bean:message key="lable.xb" />���(${rs.xyshzt }),ѧУ���(${rs.shzt })��
							</p>
						</logic:notEqual>
						<a class="close" title="����"
							onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:equal value="true" name="flg">
						<script defer="defer">
							jQuery('#buttonSave').attr('disabled',true);
							jQuery('#buttonSave').attr('class','disabled');
						</script>
					</logic:equal>
				</logic:notEmpty>
				<logic:empty name="rs">
					<div class="prompt">
						<h3>
							<span>ϵͳ��ʾ��</span>
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
											onclick="saveForm('yzbh-lxdz-zzmm-lxdh-sjhm-qq-dzyx-syshen');">
											ȷ��
										</button>
									</logic:equal>

									<!-- ��� -->
									<logic:equal value="sh" name="doType">
										<logic:equal value="xy" name="userType" scope="session">
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_xyshzt=ͨ��&doType=save','');">
												ͨ��
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_xyshzt=��ͨ��&doType=save','');">
												��ͨ��
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_xyshzt=�˻�&doType=save','');">
												�˻�
											</button>
										</logic:equal>
										<logic:notEqual value="stu" name="userType" scope="session">
										<logic:notEqual value="xy" name="userType" scope="session">
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_shzt=ͨ��&doType=save','');">
												ͨ��
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_shzt=��ͨ��&doType=save','');">
												��ͨ��
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_shzt=�˻�&doType=save','');">
												�˻�
											</button>
										</logic:notEqual>
										</logic:notEqual>
									</logic:equal>

									<logic:equal value="stu" name="userType">
										<button type="reset">
											����
										</button>
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
								<span>ȷ����Ϣһ(ѧ���������)</span>
							</th>
						</tr>
					</thead>
					<tbody id="xsxxTab">
						<tr>
							<th width="16%">
								ѧ��ѧ��
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								ѧ������
							</th>
							<td width="34%">
								<html:text property="save_xm" maxlength="20" name="rs"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:radio property="save_xbdm" value="1" name="rs">��</html:radio>
								<html:radio property="save_xbdm" value="2" name="rs">Ů</html:radio>
							</td>
							<th>
								���֤��
							</th>
							<td>
								<html:text property="save_sfzh" maxlength="20" name="rs"
									onblur="chkSfzh(this)"></html:text>
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
								��ѧʱ��
							</th>
							<td>
								<html:text property="save_rxnf" name="rs" styleId="rxnf"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" readonly="true"></html:text>
							</td>
						</tr>

						<tr>

							<th>
								��ҵʱ��
							</th>
							<td>
								<html:text property="save_bynf" name="rs" styleId="bynf"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" readonly="true"></html:text>
							</td>
							<th>
								�������
							</th>
							<td>
								<html:select property="save_zslbdm" name="rs">
									<html:options collection="zsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>

							<th>
								�����ί�൥λ
							</th>
							<td>
								<html:text property="save_dxhwp" maxlength="50" name="rs"></html:text>
							</td>
							<th>
								��ҵ����
							</th>
							<td>
								<html:text property="save_xz" maxlength="1" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								ѧ�����
							</th>
							<td>
								<html:select property="save_xlccdm" name="rs">
									<html:options collection="xlList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								������ʽ
							</th>
							<td>
								<html:select property="save_pyfsdm" name="rs">
									<html:options collection="pyfsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>

							<th>
								�Ƿ���ְ
							</th>
							<td>
								<html:radio property="save_sfzz" value="��" name="rs">��</html:radio>
								<html:radio property="save_sfzz" value="��" name="rs">��</html:radio>
							</td>
							<th>
								�Ƿ�ʦ��
							</th>
							<td>
								<html:radio property="save_sfsf" value="��" name="rs">��</html:radio>
								<html:radio property="save_sfsf" value="��" name="rs">��</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ����
							</th>
							<td>
								<html:radio property="save_sfdl" value="��" name="rs">��</html:radio>
								<html:radio property="save_sfdl" value="��" name="rs">��</html:radio>
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>ȷ����Ϣ��(��ҵ��ҵ�����Ϣ)</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text property="save_yzbh" maxlength="6" name="rs"
									styleId="yzbh" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								<font color="red">*</font>��ϵ��ַ
							</th>
							<td>
								<html:text property="save_lxdz" maxlength="100" name="rs"
									styleId="lxdz"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������ò
							</th>
							<td>
								<html:select property="save_zzmm" name="rs" styleId="zzmm">
									<html:options collection="zzmmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
								<html:text property="save_lxdh" maxlength="20" name="rs"
									onblur="checkPhone(this)" styleId="lxdh"></html:text>
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>�ֻ�
							</th>
							<td>
								<html:text property="save_sjhm" maxlength="20" name="rs"
									onblur="checkPhone(this)" styleId="sjhm"></html:text>
							</td>
							<th>
								<font color="red">*</font>QQ����
							</th>
							<td>
								<html:text property="save_qq" maxlength="11" name="rs"
									styleId="qq" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>Email����
							</th>
							<td>
								<html:text property="save_dzyx" maxlength="50" name="rs"
									styleId="dzyx"
									onblur="if ('' != this.value)if (! isEmail(this.value) ){alert('�Ƿ������ַ!');this.focus();}"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th rowspan="2">
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
							<td colspan="3">
								ȷ��ǰ��Դ��:${rs.ysyd };
								<font color="red">˵��:"��Դ��"ָѧ���μӸ߿�ʱ�������ڵء�</font>
							</td>
						</tr>
					</tbody>
					<tbody>
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
						<logic:equal value="xy" name="userType" scope="session">
							<tbody>
								<tr>
									<th>
										������
										<br />
										<font color="red"><��500��>
										</font>
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
											onblur="checkLen(this,500)" style="width:80%" rows="5"></html:textarea>
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
							</tbody>
						</logic:equal>

						<logic:notEqual value="xy" name="userType" scope="session">
							<tbody>
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
										<bean:message key="lable.xb" />�����
									</th>
									<td>
										${rs.xyshr }
									</td>
									<th>
										<bean:message key="lable.xb" />���ʱ��
									</th>
									<td>
										${rs.xyshsj }
									</td>
								</tr>
							</tbody>
							<tbody>
								<tr>
									<th>
										ѧУ������
										<br />
										<font color="red"><��500��>
										</font>
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_shyj" value="${rs.shyj }"
											onblur="checkLen(this,500)" style="width:80%" rows="5"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										ѧУ�����
									</th>
									<td>
										${userNameReal }
										<html:hidden property="save_shr" value="${userName }" />
									</td>
									<th>
										ѧУ���ʱ��
									</th>
									<td>
										${now }
										<html:hidden property="save_shsj" value="${now }" />
									</td>
								</tr>
							</tbody>
						</logic:notEqual>
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
									<bean:message key="lable.xb" />������
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />�����
								</th>
								<td>
									${rs.xyshr }
								</td>
								<th>
									<bean:message key="lable.xb" />���ʱ��
								</th>
								<td>
									${rs.xyshsj }
								</td>
							</tr>
						</tbody>
						<tbody>
							<tr>
								<th>
									ѧУ������
								</th>
								<td colspan="3">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									ѧУ�����
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									ѧУ���ʱ��
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
		<logic:equal value="stu" name="userType">
			<script defer>
				setReadOnly();
			</script>
		</logic:equal>
		<logic:equal value="xy" name="userType">
			<script defer>
				setReadOnly();
			</script>
		</logic:equal>
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>

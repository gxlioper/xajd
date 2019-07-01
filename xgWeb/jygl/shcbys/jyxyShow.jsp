<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var lsbm = jQuery('#lsbm');
				var zgdw = jQuery('#zgdw');
				
				lsbm.combobox({
					valueField:'dm',
  					textField:'mc',
					url:'jyglAjax.do?method=getLsbmOption',
					data:{url:'jyglAjax.do?method=getLsbmOption'}
				});
				
				zgdw.combobox({
					valueField:'dm',
						textField:'mc',
					url:'jyglAjax.do?method=getZgdwOption',
					data:{url:'jyglAjax.do?method=getZgdwOption'},
					onChange:function(n,o){
						setTimeout('setZgdw()',500);
					}
				});
			});
		
		
			//�������ܵ�λҪ�����ֵ ������
			function setZgdw(){	
				var text = jQuery('#zgdw').combobox('getText');
				var value = jQuery('#zgdw').combobox('getValue');
				
				jQuery('#zgdwmc').val(text);
				jQuery('#zgdwdm').val(value);
			}
			
			function saveForm(){
				var lsbm = jQuery('#lsbm').combobox('getValue');
				var dwxz = jQuery('#dwxz').combobox('getValue');
				if ('' == lsbm || '' == dwxz){
					alert("��\"*\"��Ŀ����Ϊ�գ���ȷ��");
					return false;
				}
			
				if (jQuery('#bdkssj').value > jQuery('#bdjssj').value){
					alert('������ʼʱ�䲻�����ڽ���ʱ��')
				}else {
		            saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save',
		            'xh-jybz-dwdm-dwmc-zgdwdm-zgdwmc-yrdwszd-bdkssj-bdjssj-datddw');
		        }
			}
			
			
			function setJybz(text) {
				var xxdm = jQuery('#xxdm').val();
					
				if ( text <=21 || (22<text && text<=29 )|| (30<text && text<=56)) {
					jQuery('#jybz').val(2);
				}else  if (text == 70 || text==71) {
					jQuery('#jybz').val(5);
				}else  if (text == 72) {
					jQuery('#jybz').val(6);
				} else if ((72<text && text<=77) || text==90){
					jQuery('#jybz').val(3);
				} else if (text==80 || text==85) {
					jQuery('#jybz').val(4);
				} else {
					jQuery('#jybz').val(1);
				}
				
				if (xxdm == '13275') {
					if (text == 46){
						jQuery('#jybz').val(2);
					} else if (text == 75) {
						jQuery('#jybz').val(3);
					}
				}
			}
			
			function getYrdw(text){
				jQuery.ajax({
				  type:"POST",
				  dataType:"json",
				  url:"jyglAjax.do?method=getYrdwInfo&yrdwdm="+text,
				  success:function(data){
					if (null != data.dm){
						if (jQuery('#dwxz') && null != data.dwxzdm){
							jQuery('#dwxz').val(data.dwxzdm);
						}
						if (jQuery('#dwdm') && null != data.dm) {
							jQuery('#dwdm').val(data.dm);
						}
						if (jQuery('#dwmc') && null != data.mc) {
							jQuery('#dwmc').val(data.mc);
						}
						if (jQuery('#dwlxr') && null != data.dwlxr){
							jQuery('#dwlxr').val(data.dwlxr);
						}
						if (jQuery('#dwlxdh') && null != data.dwdh) {
							jQuery('#dwlxdh').val(data.dwdh);
						}
						if (jQuery('#hyfl') && null != data.hyfldm) {
							jQuery('#hyfl').val(data.hyfldm);
						}
						if (jQuery('#dwyb') && null != data.dwyb){
							jQuery('#dwyb').val(data.dwyb);
						}
						
						jQuery('#tempDwdm').val(text);
						//�㽭ʡ-��ҳ�ľ;�ҵ��־ҲҪ�䶯
						setJybz(data.dwxzdm);
						
					} else {
						alert("û��ά���ĵ�λ���룬��ȷ��!");
						
						var text =jQuery('#tempDwdm').val();
						jQuery('#dwdm').val(text);
					}				      
				  }
			   });
		    }
		    
		  
		</script>
	</head>
	<body>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh"/>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<input type="hidden" name="njV" id="njV"/>
			<input type="hidden" name="xyV" id="xyV"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" id="tempDwdm" value="${rs.save_dwdm }" />


			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							���ľ�ҵЭ�鵱ǰ���״̬Ϊ<bean:message key="lable.xb" />��ˡ�${rs.xysh }����ѧУ��ˡ�${rs.xxsh }����
						</p>
						<a class="close" title="����"
							onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:present name="flg">
						<logic:equal value="true" name="flg">
							<script defer="defer">
							$('#buttonSave').disabled = true;
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
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave"
										onclick="saveForm()">
										����
									</button>

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
								ѧ��
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
								��ͥ��ϵ�绰
							</th>
							<td>
								<logic:notEmpty name="rs">
								<logic:empty name="rs" property="kzx1">
									<html:text   property="save_kzx1" styleId="save_kzx1" value="${jtInfo.lxdh1 }" />
								</logic:empty>
								<logic:notEmpty name="rs" property="kzx1">
									<html:text  name="rs" property="save_kzx1" styleId="save_kzx1"  value="${rs.kzx1}" />
								</logic:notEmpty>
								</logic:notEmpty>
								<logic:empty name="rs">
									<html:text  property="save_kzx1" styleId="save_kzx1" value="${rs.lxdh1 }"/>
								</logic:empty>
							</td>
							<th>
								��ͥ��ϵ��ַ
							</th>
							<td>
								<logic:notEmpty name="rs">
								<logic:empty name="rs" property="kzx2">
									<html:text  property="save_kzx2" styleId="save_kzx2"  value="${jtInfo.jtdz }"/>
								</logic:empty>
								<logic:notEmpty name="rs" property="kzx2">
									<html:text  property="save_kzx2" styleId="save_kzx2"  value="${rs.kzx2}"/>
								</logic:notEmpty>
								</logic:notEmpty>
								<logic:empty name="rs">
									<html:text  property="save_kzx2" styleId="save_kzx2" value="${rs.jtdz }"/>
								</logic:empty>
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
								<font color="red">*</font>��ҵ����
							</th>
							<td>
								<html:select property="save_jybz" name="rs" styleId="jybz"
									onchange="$('dwxz').value=''">
									<html:options collection="jybzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���˵�λ����
							</th>
							<td>
								<html:text property="save_dwmc" styleId="dwmc" name="rs"
									readonly="true" />
								<button
									onclick=
										"showTopWin('/xgxt/jygl.do?method=compayData', 800, 500);"
										id="buttonFindStu" class="btn_01">
									ѡ��
								</button>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���˵�λ����
							</th>
							<td>
								<html:text property="save_dwdm" maxlength="20"
									onkeyup="if(13==event.keyCode){getYrdw(this.value);}" name="rs"
									styleId="dwdm"></html:text>
							</td>
							<th>
								<font color="red">*</font>���˵�λ����
							</th>
							<td>
								<html:select property="save_dwxz" name="rs" styleId="dwxz" styleClass="easyui-combobox"
									onchange="setJybz(this.value);" style="width:200px">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���ܵ�λ����
							</th>
							<td>
								<html:hidden property="save_zgdwmc" styleId="zgdwmc" name="rs" />
								<html:select property="zgdw" 
											 value="${rs.zgdwdm }" 
											 styleId="zgdw" 
											 style="width: 200px;" 
											  >
									<html:options collection="zgdwList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���ܵ�λ����
							</th>
							<td>
								<html:text property="save_zgdwdm" name="rs" maxlength="10"
									readonly="true" styleId="zgdwdm"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ��������
							</th>
							<td>
								<html:select property="save_lsbm" 
											 name="rs" 
											 styleId="lsbm" 
											 style="width: 200px;" 
											 >
									<html:options collection="lsbmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								������λ
							</th>
							<td>
								<html:text name="rs"  property="save_kzx5" styleId="save_kzx3"  />
							</td>
						</tr>
						<tr>
							<th>
								����֤
							</th>
							<td>
								<html:select name="rs" property="save_kzx7">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
								
							</td>
							<th>
								н��/��
							</th>
							<td>
								<html:text property="save_kzx8" name="rs" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��Ϣ�ǼǺ�
							</th>
							<td>
								<html:text property="save_kzx4" name="rs" maxlength="50"></html:text>
							</td>
							<th>
								ְҵ����֤��
							</th>
							<td>
								<html:text property="save_kzx6" name="rs" maxlength="50"></html:text>
							</td>
						</tr>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���˵�λ���ڵ�
							</th>
							<td colspan="3">
								<html:text property="save_yrdwszd" maxlength="100"
									styleId="yrdwszd" style="width:80%" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td colspan="3">
								<html:text property="save_bddq" name="rs" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������ʼʱ��
							</th>
							<td>
								<html:text property="save_bdkssj" name="rs" styleId="bdkssj"
									readonly="true"
									onclick="return showCalendar('bdkssj','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>��������ʱ��
							</th>
							<td>
								<html:text property="save_bdjssj" name="rs" styleId="bdjssj"
									readonly="true"
									onclick="return showCalendar('bdjssj','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�»������
							</th>
							<td>
								<html:select property="save_xjcqk" name="rs" style="width:200px">
									<html:options collection="xjcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����Ͷ�ݵ�λ
							</th>
							<td>
								<html:text property="save_datddw" name="rs" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����Ͷ�ݵ�ַ
							</th>
							<td colspan="3">
								<html:text property="save_datddz" name="rs" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������ַ
							</th>
							<td colspan="3">
								<html:text property="save_kzx3" name="rs" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����Ǩ�Ƶ�ַ
							</th>
							<td colspan="3">
								<html:text property="save_hkqydz" name="rs" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��λ��ϵ��
							</th>
							<td>
								<html:text property="save_dwlxr" styleId="dwlxr" name="rs"
									maxlength="25"></html:text>
							</td>
							<th>
								��λ��ϵ�绰
							</th>
							<td>
								<html:text property="save_dwlxdh" styleId="dwlxdh" name="rs"
									maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								רҵ�Ƿ�Կ�
							</th>
							<td>
								<html:select property="save_sfdk" value="��">
									<html:option value="">----��ѡ��----</html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								��ҵ��ҵ
							</th>
							<td>
								<html:select property="save_jyhy" styleId="hyfl"
									style="width:200px" value="${rs.jyhy }">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ��עһ
							</th>
							<td colspan="3">
								<html:text property="save_jybz1" style="width:80%"
									maxlength="150" value="${rs.jybz1 }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ��ע��
							</th>
							<td colspan="3">
								<html:text property="save_jybz2" style="width:80%"
									maxlength="150" value="${rs.jybz2 }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ��ע��
							</th>
							<td colspan="3">
								<html:text property="save_jybz3" style="width:80%"
									maxlength="150" value="${rs.jybz3 }"></html:text>
							</td>
						</tr>
					</tbody>
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
</html>

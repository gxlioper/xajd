<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
				
				jQuery(function(){
				
					var lsbm = jQuery('#lsbm');
					var zgdw = jQuery('#zgdw')
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
				
				changeSSX();
				
				if (jQuery('#bdkssj').value > jQuery('#bdjssj').value){
					alert('������ʼʱ�䲻�����ڽ���ʱ��')
				}else {
		            saveUpdate('/xgxt/jygl.do?method=jyxywh&doType=save',
		            'xh-jybz-dwdm-dwmc-zgdwdm-zgdwmc-yrdwszd-bdkssj-bdjssj-datddw');
		        }
			}
			
			function changeSSX(){
				var yrdwshen=jQuery("#yrdwshen option:selected").text();
				var yrdwshi=jQuery("#yrdwshi option:selected").text();
				var yrdwxian=jQuery("#yrdwxian option:selected").text();
				
				var bddqshen=jQuery("#bddqshen option:selected").text();
				var bddqshi=jQuery("#bddqshi option:selected").text();
				var bddqxian=jQuery("#bddqxian option:selected").text();
				
				if(yrdwshen=="" || yrdwshen=="----��ѡ��----"){
					yrdwshen="��"
				}
				
				if(yrdwshi=="" || yrdwshi=="----��ѡ��----"){
					yrdwshi="��"
				}
				
				if(yrdwxian=="" || yrdwxian=="----��ѡ��----"){
					yrdwxian="��"
				}
				
				if(bddqshen=="" || bddqshen=="----��ѡ��----"){
					bddqshen="��"
				}
				
				if(bddqshi=="" || bddqshi=="----��ѡ��----"){
					bddqshi="��"
				}
				
				if(bddqxian=="" || bddqxian=="----��ѡ��----"){
					bddqxian="��"
				}
				
				
				jQuery("#yrdwszd").val(yrdwshen+" "+yrdwshi+" "+yrdwxian);
				jQuery("#bddq").val(bddqshen+" "+bddqshi+" "+bddqxian);
				
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
						
						var text = jQuery('#tempDwdm').val();
						jQuery('#dwdm').val(text);
					}				      
				  }
			   });
		    }
			
			
			jQuery(function(){
				//yrdwshen+" "+yrdwshi+" "+yrdwxian
				var yrdwszd=jQuery("#yrdwszd").val();
				//bddqshen+" "+bddqshi+" "+bddqxian
				var bddq=jQuery("#bddq").val();
				
				var yrdwarray=new Array();
				
				var yrdwshen=yrdwszd.split(" ")[0];
				var yrdwshi=yrdwszd.split(" ")[1];
				var yrdwxian=yrdwszd.split(" ")[2];
				
				var bddqshen=bddq.split(" ")[0];
				var bddqshi=bddq.split(" ")[1];
				var bddqxian=bddq.split(" ")[2];
				
				jQuery("#yrdwshen").val(jQuery("#yrdwshen option[text="+yrdwshen+"]").val());
				loadShi('yrdwshen','yrdwshi','yrdwxian');
				jQuery("#bddqshen").val(jQuery("#bddqshen option[text="+bddqshen+"]").val());
				loadShi('bddqshen','bddqshi','bddqxian');
				
				setTimeout("shiOut('"+yrdwshi+"','"+bddqshi+"')","500");
				
				loadXian('yrdwshi','yrdwxian');
				loadXian('bddqshi','bddqxian');
				
				setTimeout("xianOut('"+yrdwxian+"','"+bddqxian+"')","500");
				
				
			})
			
			function shiOut(yrdwshi,bddqshi){
				jQuery("#yrdwshi").val(jQuery("#yrdwshi option[text="+yrdwshi+"]").val());
				
				jQuery("#bddqshi").val(jQuery("#bddqshi option[text="+bddqshi+"]").val());
				
			}
			
			function xianOut(yrdwxian,bddqxian){
			
				jQuery("#yrdwxian").val(jQuery("#yrdwxian option[text="+yrdwxian+"]").val());

				jQuery("#bddqxian").val(jQuery("#bddqxian option[text="+bddqxian+"]").val());
			}
		</script>
	</head>
	<body>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh" />
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
			<input type="hidden" name="dm" id="dm" value="${dm }" />
			<input type="hidden" id="tempDwdm"/>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

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
						$('#buttonSave').disabled = true;
					</script>
					</logic:notEmpty>
				</logic:present>
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
					<tbody>
						<tr>
							<th width="16%">
								<span class="red">*</span>ѧ��
							</th>
							<td width="30%">
								<html:text property="save_xh" value="${rs.xh }" readonly="true" styleId="xh"></html:text>
								<logic:notEqual value="stu" name="userType">
									<button
										onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);"
										id="buttonFindStu" class="btn_01">
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
								<html:select property="save_jybz" styleId="jybz"
									onchange="$('dwxz').value=''" value="1">
									<html:options collection="jybzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>���˵�λ����
							</th>
							<td>
								<html:text property="save_dwmc" styleId="dwmc" readonly="true" />
								<button
									onclick="showTopWin('/xgxt/jygl.do?method=compayData',800,500);"
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
									onkeyup="if(13==event.keyCode){getYrdw(this.value)}"
									styleId="dwdm"
									></html:text>
							</td>
							<th>
								<font color="red">*</font>���˵�λ����
							</th>
							<td>
								<html:select property="save_dwxz" styleClass="easyui-combobox"
									onchange="setJybz(this);" styleId="dwxz"
									style="width:200px">
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
								<html:hidden property="save_zgdwmc" styleId="zgdwmc" />

								<html:select property="zgdw" 
											 styleClass="easyui-combobox"
											 style="width: 200px;"
											 styleId="zgdw"
											 >
									
								</html:select>

							</td>
							<th>
								<font color="red">*</font>���ܵ�λ����
							</th>
							<td>
								<html:text property="save_zgdwdm" maxlength="10"
									styleId="zgdwdm" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ��������
							</th>
							<td>
								<input type="hidden" id="team_lsbm" />
								<html:select property="save_lsbm" 
											 styleId="lsbm"
											 style="width: 200px;" 
											 >
								</html:select>
							</td>
							<th>
							</th>
							<td></td>
						</tr>
						<tr>
							<th>
								���˵�λ���ڵ�
							</th>
<%--							<td colspan="3">--%>
<%--								<html:text property="save_yrdwszd" maxlength="100"--%>
<%--									styleId="yrdwszd" style="width:80%"></html:text>--%>
<%--							</td>--%>
							<td colspan="3">
								<!--��ַ��Ϣȡ��׼��-->
								<logic:present name="yrdwssList">
									<html:select  property="yrdwshen" styleId="yrdwshen"
										onchange="loadShi('yrdwshen','yrdwshi','yrdwxian');">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="yrdwssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select  property="yrdwshi" styleId="yrdwshi"
												onchange="loadXian('yrdwshi','yrdwxian')">
												<html:options collection="yrdwshiList" property="shidm"
													labelProperty="shimc" />
											</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select  property="yrdwxian" styleId="yrdwxian">
										<html:options collection="yrdwxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
									
										<html:hidden  property="save_yrdwszd" 
											styleId="yrdwszd" styleClass="text_nor" style="width:90%" />
								
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
<%--							<td colspan="3">--%>
<%--								<html:text property="save_bddq" maxlength="100" styleId="bddq"--%>
<%--									style="width:80%"></html:text>--%>
<%--							</td>--%>
							<td colspan="3">
								<!--��ַ��Ϣȡ��׼��-->
								<logic:present name="bddqssList">
									<html:select  property="bddqshen" styleId="bddqshen"
										onchange="loadShi('bddqshen','bddqshi','bddqxian');">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="bddqssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select  property="bddqshi" styleId="bddqshi"
										onchange="loadXian('bddqshi','bddqxian')">
										<html:options collection="bddqshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select  property="bddqxian" styleId="bddqxian">
										<html:options collection="bddqxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
								
									<html:hidden  property="save_bddq"
										styleId="bddq" styleClass="text_nor" style="width:90%" />
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������ʼʱ��
							</th>
							<td>
								<html:text property="save_bdkssj" styleId="bdkssj"
									readonly="true"
									onclick="return showCalendar('bdkssj','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>��������ʱ��
							</th>
							<td>
								<html:text property="save_bdjssj" styleId="bdjssj"
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
								<html:select property="save_xjcqk" style="width:200px">
									<html:options collection="xjcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����Ͷ�ݵ�λ
							</th>
							<td>
								<html:text property="save_datddw" maxlength="50" styleId="datddw"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����Ͷ�ݵ�ַ
							</th>
							<td colspan="3">
								<html:text property="save_datddz" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����Ǩ�Ƶ�ַ
							</th>
							<td colspan="3">
								<html:text property="save_hkqydz" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��λ��ϵ��
							</th>
							<td>
								<html:text property="save_dwlxr" styleId="dwlxr" maxlength="25"></html:text>
							</td>
							<th>
								��λ��ϵ�绰
							</th>
							<td>
								<html:text property="save_dwlxdh" styleId="dwlxdh"
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
									style="width:200px">
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
									maxlength="150"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ��ע��
							</th>
							<td colspan="3">
								<html:text property="save_jybz2" style="width:80%"
									maxlength="150"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ��ע��
							</th>
							<td colspan="3"><html:text property="save_jybz3" style="width:80%"
									maxlength="150"></html:text>
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
										onclick="saveForm()">
										����
									</button>
									<button type="reset">
										����
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
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>

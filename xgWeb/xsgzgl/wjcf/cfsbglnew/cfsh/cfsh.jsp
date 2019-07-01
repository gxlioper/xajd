<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsh/js/cfsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${map.cfid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${cfshForm.splcid}&shid=${cfshForm.shid}",function(){
					jQuery("#shjg").change(function(){
						if(jQuery(this).val()=='1')
							jQuery("#cffw_tr1,#cffw_tr2,#cffw_tr3").show();
						else
							jQuery("#cffw_tr1,#cffw_tr2,#cffw_tr3").hide();
					});
				});
			var cflbdm ='${map.cflbdm }';
			
			showCfqxFlag(cflbdm);
			showCfqxFlagSh(cflbdm);
			
			defaultCfdqsj();
			
			jQuery("#cflbdm").change(function(){
				defaultCfdqsj();
			});
			
			jQuery("#cfsj").change(function(){
				defaultCfdqsj();
			});
			
		});

		//����
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function save_sh(){
			
			var shzt = jQuery("#shjg").val();
			var cfid = jQuery("#cfid").val();
			var cfwh = jQuery("#cfwh").val();
			jQuery("#shzt").val(shzt);

			if(jQuery("#xxdm").val() == "14008"){
				if (shzt != "1" && jQuery("#shyj").val() == ""){
					showAlertDivLayer("����д��������");
					return false;
				}
			}else{
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("����д��������");
					return false;
				}
			}
			if (jQuery("#shyj").val().length>200){
				showAlertDivLayer("���������ܳ���200��");
				return false;
			}
			//�����һ�����ͨ��ʱ��Ȩ�޸�λ���ͨ��ʱ���ж��Ƿ���д�����ĺż�����ʱ��
			if((shzt=="1"||shzt==1)){
				if(jQuery("#isZhgw").val()=="true"||${cffwqxPd==2}){
					if(jQuery("#xxdm").val() != "70002"){
						if(jQuery("#cfwh").val()==""){
							showAlertDivLayer("����д�����ĺţ�");
							return false;
						}
					}
					if(jQuery("#cfsj").val()==""){
						showAlertDivLayer("����д����ʱ�䣡");
						return false;
					}
					if(jQuery("#cflbdm").val()==""){
						showAlertDivLayer("��ѡ�񴦷����");
						return false;
					}
					
					var cfdqsj = jQuery("#cfdqsj");
					if(cfdqsj.length>0){
						if(cfdqsj.val()==""){
							showAlertDivLayer("����д���ֵ���ʱ�䣡");
							return false;
						}
					}
					
					var wjsj = jQuery("#wjsj").val();
					var cfsj = jQuery("#cfsj").val();
					
					if(""!=wjsj&&cfsj!=""){
						if(new Date(cfsj)<(new Date(wjsj))){
							showAlertDivLayer("����ʱ�䲻��С��Υ��ʱ�䣡");
							return false;
						}
					}
					
				}
			}
			var message;
			if(jQuery("#shjg").val() == "1"){
				message = "ͨ��";
			}
			if(jQuery("#shjg").val() == "2"){
				message = "��ͨ��";
			}
			if(jQuery("#shjg").val() == "3"){
				message = "�˻�";
			}
			if((${cffwqxPd==2}||"true"==jQuery("#isZhgw").val())&&"12686"==jQuery("#xxdm").val()){
				var flag=true;
				jQuery.ajaxSetup({async:false});
				jQuery.post("wjcf_cfsbgl.do?method=checkExistCfwh", {
					cfid:cfid,
					cfwh:cfwh
				}, function(data) {
					if(data ==null || data){
						flag=false;
					}
				},"json");
				jQuery.ajaxSetup({async:true});
				if(!flag){
					showAlert("�ô����ĺ��Ѵ��ڣ����޸ģ�");
					return false;
					}
				}
			//�ύ���
			showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
				// ���һ����� �������ͨ��ʱ�ж�
				if((jQuery("#isZhgw").val()=="true"||${cffwqxPd==2}) && jQuery("#shjg").val() == "1"){
					var xh = jQuery("#xh").val();
					var cflbdm = jQuery("#cflbdm").val();
					var wjsj = jQuery("#wjsj").val();
					
					// ��֤�����ڽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
					jQuery.post("wjcf_cfsbgl.do?method=checkExistCfjg", {
						xh:xh,
						cflbdm:cflbdm,
						wjsj:wjsj
					}, function(data) {
						if(data ==null || data){
							showAlert("��ѧ����"+wjsj+"��Υ�����ڴ��ֽ���д��ڣ�");
							return false;
						}else{
							var url = "wjcf_cfsh.do?method=cfsh&type=save";
							ajaxSubFormWithFun("cfshForm",url,function(data){
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									refershParent();
								}});
							});
						}
					},"json");
					
				}else{

					var url = "wjcf_cfsh.do?method=cfsh&type=save";
					ajaxSubFormWithFun("cfshForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				}
			}});
		}
		
		//��ʾ��������
		function showCfqxFlagSh(cflbdm){
			//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqxsh").html(data["message"]);
			},'json');
		}
		
		function showCfqxFlag(cflbdm){
			//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
			
		}
		
		//��ʼ�����ֵ���ʱ�䣬��ô��ֵ���ʱ��Ĭ��ֵ������ʱ��+�������ޣ����û�д�����������ʾ���ֵ���ʱ�䣩
		function defaultCfdqsj(){
			var cfsj = jQuery("#cfsj").val();
			var cflbdm = jQuery("#cflbdm").val();
			
			jQuery.post("wjcf_cfsh.do?method=defaultCfdqsj",{cfsj:cfsj,cflbdm:cflbdm},function(data){
				//jQuery("#cfdqsj").val(data["message"]);
				if(data["message"]!="hidden"){
					var html = "<th><font color=\"red\">*</font>���ֵ���ʱ�䣺</th><td colspan=\"3\"><input name=\"cfdqsj\" id=\"cfdqsj\" "
					+" style=\"cursor:hand;\" onclick=\"return showCalendar('cfdqsj','y-mm-dd',false,'cfsj');\" value=\""+data["message"]+"\"/></td>";
					jQuery("#cffw_tr3").html(html);
				}else{
					jQuery("#cffw_tr3").html("");
				}
				
			},'json');
		}
		</script>
	</head>
	
	<body>
		<html:form method="post" styleId="cfshForm" action="/wjcf_cfsh" >
		<html:hidden property="ywid" name="cfshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfshForm" styleId="splcid"/>
		<html:hidden property="kzzd1" name="kzzd1" styleId="kzzd1" value="${map.cflbdm }"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="cfid" id="cfid" value="${cfshForm.ywid }"/>
		<input type="hidden" name="wjsj" id="wjsj" value="${map.wjsj }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
			<div
				style=' overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								ѧ��ѧ��
							</th>
							<td align="left" width="30%">
								${map.xn } ${map.xqmc }
							</td>
							<th align="right" width="20%">
								Υ��ʱ��
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								����ԭ��
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								�������
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								�ϱ���
							</th>
							<td align="left" colspan="3">
								${map.sbbxm }
							</td>
						</tr>
						<tr>
							<th align="right">
								���ֽ���
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								ѧ��������
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø���
									jQuery(function(){
										var gid = "${map.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								����Υ�ͼ�¼��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-2" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-2'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�д�ֽ��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-3" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath3}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-3'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�������¼
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-4" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath4}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-4'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								Υ����ʵ����
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- ԭ�߼� ������˵����һ��ʱ��ʾ���ַ�����Ϣ�����޸ģ���������ʾ-->
						<!-- �ָ�Ϊ �������ʱ���д��ַ���Ȩ�޵���˼�����ʾ�����޸ģ��ϵͼ�����ʾ���ϸ߼�����ʾ�������޸ģ�
						�����������������δ��ַ���Ȩ���ֶΣ���ԭ�߼�����-->
						<!-- ����Ѿ������һ���������һ���Ȩ����ĳЩԭ���紦������޸ģ��ȼ���Ȼ����Ȩ�޸�λ
							   �����һ�����Բ鿴���޸Ĵ��ַ�����Ϣ -->
						<!-- ���ַ���Ȩ���ж� 0��δ��д��1���ȼ�����Ȩ�޸�λ��2���ȼ�����Ȩ�޸�λ��3�ȼ�����Ȩ�޸�λ -->
						
						<!-- 0��1������� -->
						<logic:notEqual value="2" name="cffwqxPd">
							<logic:notEqual value="3" name="cffwqxPd">
								<logic:equal value="true" name="isZhgw">
									<tr id="cffw_tr1">
										<th align="right">
											<font color="red">*</font>�����ĺţ�
										</th>
										<td align="left"  >
											<input name="cfwh" id="cfwh" maxlength="30" value="${map.kzzd2}"/>
										<th align="right">
											<font color="red">*</font>����ʱ�䣺
										</th>
										<td align="left">
											<input name="cfsj" id="cfsj" style="cursor:hand;" 
											onclick="return showCalendar('cfsj','y-mm-dd','','','','',defaultCfdqsj);" 
											value="${map.kzzd3 }"/>
										</td>
									</tr>
									<tr id="cffw_tr2">
										<th align="right">
											<font color="red">*</font>�������
										</th>
										<td align="left" colspan="3">
											<html:select property="cflbdm" styleId="cflbdm"
												style="width:100px" onchange="showCfqxFlagSh(this.value);">
												<html:option value=""></html:option>
												<html:options collection="cflbList" property="dm"
													labelProperty="mc" />
											</html:select>
											&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
										</td>
									</tr>
									<tr id="cffw_tr3">
									</tr>
									
								</logic:equal>
							</logic:notEqual>
						</logic:notEqual>
						
						<!-- 2��� -->
						<logic:equal value="2" name="cffwqxPd">
							<tr id="cffw_tr1">
							
								<th align="right">
									<logic:notEqual value="70002" name="xxdm"><font color="red">*</font></logic:notEqual>�����ĺţ�
								</th>
								<td align="left"  >
									<input name="cfwh" id="cfwh" maxlength="30" value="${map.kzzd2}"/>
								</td>
								<th align="right">
									<font color="red">*</font>����ʱ�䣺
								</th>
								<td align="left">
									<input name="cfsj" id="cfsj" style="cursor:hand;" 
									onclick="return showCalendar('cfsj','y-mm-dd','','','','',defaultCfdqsj);" 
									value="${map.kzzd3 }"/>
								</td>
							</tr>
							<tr id="cffw_tr2">
								<th align="right">
									<font color="red">*</font>�������
								</th>
								<td align="left" colspan="3">
									<html:select property="cflbdm" styleId="cflbdm"
										style="width:100px" onchange="showCfqxFlagSh(this.value);">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
								</td>
								
							</tr>
							<tr id="cffw_tr3">
							</tr>
						</logic:equal>
						
						<!-- 3��� -->
						<logic:equal value="3" name="cffwqxPd">
							<tr>
								<th align="right">
									�����ĺţ�
								</th>
								<td align="left"  >
									${map.kzzd2}
									<input type="hidden" name="cfwh"  value="${map.kzzd2}"/>
								</td>
								<th align="right">
									����ʱ�䣺
								</th>
								<td align="left">
									${map.kzzd3 }
									<input type="hidden" name="cfsj"  value="${map.kzzd3 }"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									�������
								</th>
								<td align="left" colspan="3">
									${map.cflbmc}
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
									<input type="hidden" name="cflbdm"  value="${map.cflbdm }"/>
								</td>
							</tr>
							<logic:present name="map" property="kzzd5">
								<tr>
									<th align="right">
										���ֵ���ʱ�䣺
									</th>
									<td align="left" colspan="3">
										${map.kzzd5 }
										<input type="hidden" name="cfdqsj"  value="${map.kzzd5 }"/>
									</td>
								</tr>
							</logic:present>
						</logic:equal>
						
						
						<tr>
							<tr>
								<th>
									<font color="red">*</font>��˽��
								</th>
								<td colspan="3" id="shjgSpan">
									
								</td>
							</tr>
						</tr>
						<tr>
						
       						<th >
				        		<logic:notEqual name="xxdm" value="14008"><font color="red">*</font></logic:notEqual>������
				        		<br/>
								<font color="red"><B>(��200��)</B>
								</font>
				       		</th>
				     	    <td width="34%" colspan="3">
				     	    	<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cfsb&id=shyj" />
				        		<textarea rows="5" style="width: 90%;margin-top: 5px" id="shyj" name="shyj" onblur="checkLen(this,200)"></textarea>
				       		</td>
				        </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>���ܴ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
									<thead align="center">
										<tr align="center">
											<td>
												<b>ѧ��</b>
											</td>
											<td>
												<b>ѧ��</b>
											</td>
											<td>
												<b>�������</b>
											</td>
											<td>
												<b>����ԭ��</b>
											</td>
											<td>
												<b>����ʱ��</b>
											</td>
											<td>
												<b>�����ĺ�</b>
											</td>
										</tr>
									</thead>
									<tbody align="center">
										<logic:notEmpty name="yscfqkList">
											<logic:iterate name="yscfqkList" id="s">
												<tr style="cursor: hand">
													<td>
														${s.xn}
													</td>
													<td>
														${s.xqmc}
													</td>
													<td>
														${s.cflbmc}
													</td>
													<td>
														${s.cfyymc}
													</td>
													<td>
														${s.cfsj}
													</td>
													<td>
														${s.cfwh}
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<div class="btn">
										<button type="button" name="����" id="buttonClose" onclick="save_sh();return false;">
											����
										</button>
										&nbsp;&nbsp;
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</html:form>
	</body>
</html>
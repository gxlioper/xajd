<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
		function saveForm(shzt){
			var xxdm = jQuery("#xxdm").val();	
			var checkData = "ydlbdm";
	
			if(!jQuery("#tzbj").is(":hidden")){
				checkData = "ydlbdm-nj-xydm-zydm";
			}		

			if(!jQuery("#lrqzsj").is(":hidden")){
				checkData += "-sqkssj-sqjssj";
			}

			if("10511" == xxdm) {
				if(!jQuery("#xzpd").is(":hidden")){
					checkData += "-xz";
				}
				if(!jQuery("#xxpd").is(":hidden")){
					checkData += "-lyqxxxdm";
				}
				checkData += "-ydyydm";
			}
			
			if(!check(checkData)){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			  
		     var url = "xjydsq.do?method=xjydsqUpd&type=update&shzt="+shzt;

		     // ԭ���״̬
		     var shztGet = jQuery("#shzt").val();
		     // �ύ����
			if("5" == shzt){
				confirmInfo("��ȷ���ύ��?",function(ty){
					if(ty=="ok"){
						// ԭ���״̬Ϊ�˻�,��δ����춯���ͣ�����Ҫ��֤�Ƿ���ύ��ֱ���ύ
						if(shztGet == '3'&& jQuery("#ydlbdm").val() == jQuery("#ydlbdmOld").val()){
							// �Ƿ����˻أ�1[�˻�]
							url += "&sfyth=1";
							ajaxSubFormWithFun("xjydsqForm",url,function(data){
					    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
					    				if (parent.window){
					    					refershParent();
					    				}
					    			}});
					    	 }else{
					    		 showAlert(data["message"]);
					    	 }
							});
						}else{
							// ��֤�Ƿ���ύ
							jQuery.post("xjydsq.do?method=checkSfktj", {
								ydlbdm:jQuery("#ydlbdm").val()
							}, function(data) {
								if(data ==null || data=="false"){
									showAlertDivLayer("��������춯�ѹر����룬�޷��ύ����������ϵ����Ա��");
									return false;
								}else{
									ajaxSubFormWithFun("xjydsqForm",url,function(data){
							    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
							    		 showAlert(data["message"],{},{"clkFun":function(){
							    				if (parent.window){
							    					refershParent();
							    				}
							    			}});
							    	 }else{
							    		 showAlert(data["message"]);
							    	 }
									});
								}
							});
						}
					}
				 });
				 
			// ����ݸ�
			}else{
				// ԭ���״̬Ϊ�˻�
				if(shztGet == '3'&& jQuery("#ydlbdm").val() == jQuery("#ydlbdmOld").val()){
					// �Ƿ����˻أ�1[�˻�]
					url += "&sfyth=1";

				}
			      ajaxSubFormWithFun("xjydsqForm",url,function(data){
				    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
				  });
			}
		  }

		function initShow(){
			var xxdm = jQuery("#xxdm").val();
			var xjlbdm = jQuery("#ydlbdm").val();
			if(xjlbdm == ""){
				jQuery("#xjlbmc").html("");
				jQuery("#sfyxj").html("");
				jQuery("#sfzx").html("");
				jQuery("#tzbj").hide();
				jQuery("#lrqzsj").hide();
				jQuery("#xzpd").hide();
				jQuery("#xxpd").hide();
			}else{
				jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
					if(data != null){
						jQuery("#xjlbmc").html(data["xjlbmc"]);
						jQuery("#sfyxj").html(data["sfyxjmc"]);
						jQuery("#sfzx").html(data["sfzxmc"]);

						if(data["lrqzsj"]=='1'){
							jQuery("#lrqzsj").show();
						}else{
							jQuery("#lrqzsj").hide();
							jQuery("#sqkssj").val("");
							jQuery("#sqjssj").val("");
							
						}
						
						if(data["sftjbj"]=='0'){
							jQuery("#tzbj").show();
						}else{
							jQuery("#tzbj").hide();
							jQuery("#nj").val("");
							jQuery("#xydm").val("");
							jQuery("#zydm").val("");
							jQuery("#bjdm").val("");
						}

						if("10511" == xxdm) {
							if(data["xzsfkq"]=='1'){
								jQuery("#xzpd").show();
							}else{
								jQuery("#xzpd").hide();
								jQuery("#xz").val("");
							}
							if(data["xxsfkq"]=='1') {
								jQuery("#xxpd").show();
							}else{
								jQuery("#xxpd").hide();
								jQuery("#lyqxxxdm").val("");				
							}
						}
						
					}
				},'json');
			}
		}
		jQuery(function(){
			initShow();
			if(jQuery("#shzt").val()=='3'){
				jQuery("#ydlbdm").attr("disabled","disabled");
			}
			onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');
		});
		
		</script>
	</head>
	<body>
		<html:form action="/xjydsq" method="post" styleId="xjydsqForm" onsubmit="return false;">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xjydsqid"/>
			<input type="hidden" id="ydlbdmOld" value="${xjydsqForm.ydlbdm}" />
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="splcid" styleId="splcid"/>
			<html:hidden property="xh"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���춯������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>ѧ���춯���
						</th>
						<td align="left">
							<html:select property="ydlbdm" styleId="ydlbdm" 
							 	onchange="initShow();">
								<html:option value=""></html:option>
								<html:options collection="xjlbList" property="xjlbdm"
									labelProperty="xjlbmc" />
							</html:select>
						</td>
						<th align="right">
							ѧ��/ѧ��
						</th>
						<td align="left">
							${dqxn} ${dqxq}
						</td>
					</tr>
					<tr>
						<th>ѧ��״̬[�춯]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:100px">ԭѧ�����</th>
										<td style="width:180px">&nbsp;<bean:write name="jbxx" property="xjlbmc"/></td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td>&nbsp;<bean:write name="jbxx" property="xjztm"/></td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td>&nbsp;<bean:write name="jbxx" property="sfzx"/></td>
									</tr>
								</table>
								<img style="float:left;margin:33px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">�춯��ѧ�����</th>
										<td style="width:187px" id="xjlbmc">&nbsp;</td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td id="sfyxj">&nbsp;</td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td id="sfzx">&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th><span class="red">*</span>����רҵ/�༶</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:100px;height:20px;">ԭ�꼶</th>
										<td style="width:180px" id="ydqnj">&nbsp;<bean:write name="jbxx" property="nj"/></td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;<bean:write name="jbxx" property="xymc"/></td>
									</tr>
									<tr>
										<th style="height:20px;">ԭרҵ</th>
										<td id="ydqzy">&nbsp;<bean:write name="jbxx" property="zymc"/></td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ�༶</th>
										<td id="ydqbj">&nbsp;<bean:write name="jbxx" property="bjmc"/></td>
									</tr>
								</table>
								<img style="float:left;margin:70px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:80px;height:20px;"><span class="red">*</span>�춯���꼶</th>
										<td style="width:205px">
											<html:select property="ydhnj" styleId="nj" 
												onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');" style="width:100px">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>�춯��<bean:message key="lable.xb" /></th>
										<td>											
											<html:select property="ydhxydm" styleId="xydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"  style="width:180px;">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
							
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>�춯��רҵ</th>
										<td>
											<html:select property="ydhzydm" styleId="zydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;">�춯��༶</th>
										<td>
											<html:select property="ydhbjdm" styleId="bjdm" style="width:180px;">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<tr id="lrqzsj">
						<th><span class="red">*</span>�����춯��ֹʱ��</th>
						<td colspan="3">
							<html:text property="sqkssj" styleId="sqkssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqjssj');" />
						    &nbsp;��&nbsp;
						    <html:text property="sqjssj" styleId="sqjssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkssj');" />
						</td>
					</tr>
					
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								<span class="red">*</span>ѧ��
							</th>
							<td align="left" colspan="3">
								<html:text property="xz" styleId="xz" maxlength="1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right" width="18%">
								<span class="red">*</span>��ԴѧУ/ȥ��ѧУ
							</th>
							<td align="left" colspan="3">
								<html:select property="lyqxxxdm" styleId="lyqxxxdm" disabled="false">
									<html:option value=""></html:option>
									<html:options collection="lyqxxxList" property="lyqxxxdm"
										labelProperty="lyqxxxmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�춯ԭ��
							</th>
							<td align="left" colspan="3">
								<html:select property="ydyydm" styleId="ydyydm" disabled="false">
									<html:option value=""></html:option>
									<html:options collection="ydyyList" property="ydyydm"
										labelProperty="ydyymc" />
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="16%">
							������Ϣ
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<input type="file" id="filepath_f" name="filepath" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery('#filepath_f').multiUploader({
										maxcount : 3,
										//��׺
										accept : 'png|gif|jpg|zip|rar|doc|docx',
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'filepath',

										eid : 'filepath_f'
										});
								});
							</script>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��������&nbsp;
							<br />
							<font color="red">(��400��)</font>						
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,400);"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="height:35px"></div>			
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="saveForm('0');return false;" id="buttonSave">
									����ݸ�
								</button>
								<button type="button"  onclick="saveForm('5');return false;" id="buttonSubmit">
									�ύ����
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
			
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//ͨ��
		function tongguo(){
			confirmInfo("ȷ��ͨ����",function(ta){
				if(ta=="ok"){
					var cfId = $("cfId").value;
					var sfZgj = $("sfZgj").value; 
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					
					var shyj = $("shyj").value;
					if(sfZgj=="true"){
						var cfwh = $("cfwh").value;
						var cfsj = $("cfsj").value; 
						if(cfwh==""||cfsj==""){
							alertError("�뽫��*����Ŀ��д������");
							return false;
						}else{
							var url = "general_wjcf_cfsh_ajax.do?method=plSaveCfsh&doType=tg";
							//����
						 	var parameter = {
						 		"cfId":cfId,
						 		"spgwId":spgwId,
						 		"cflbdm":cflbdm,
						 		"cfwh":escape(cfwh),
						 		"cfsj":cfsj,
						 		"shyj":escape(shyj)
							};
							jQuery.post(url,
								parameter,
								function(result){
								 showAlert("�����ɹ�",{},{"clkFun":function(){
					    				if (parent.window){
					    					refershParent();
					    				}
					    			}});
							});
							}
					}else{
						var url = "general_wjcf_cfsh_ajax.do?method=plSaveCfsh&doType=tg";
						//����
					 	var parameter = {
					 		"cfId":cfId,
					 		"spgwId":spgwId,
					 		"cflbdm":cflbdm,
					 		"shyj":escape(shyj)
						};
						jQuery.post(url,
								parameter,
								function(result){
							 	showAlert("�����ɹ�",{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
							});
						}
						}
			});
		}

		//ͨ�����ύ
		function tongguoBtj(){
			confirmInfo("ȷ��ͨ����",function(ta){
				if(ta=="ok"){
					var cfId = $("cfId").value;
					var sfZgj = $("sfZgj").value; 
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var shyj = $("shyj").value;
					var cfwh = $("cfwh").value;
					var cfsj = $("cfsj").value; 
						if(cfwh==""||cfsj==""){
							alertError("�뽫��*����Ŀ��д������");
							return false;
						}else{
							var url = "general_wjcf_cfsh_ajax.do?method=plSaveCfsh&doType=tg";
							//����
						 	var parameter = {
						 		"cfId":cfId,
						 		"spgwId":spgwId,
						 		"cflbdm":cflbdm,
						 		"cfwh":escape(cfwh),
						 		"cfsj":cfsj,
						 		"shyj":escape(shyj)
							};
							jQuery.post(url,
								parameter,
								function(result){
									var url = "general_wjcf_cfsh_ajax.do?method=zjtjSh";
									//����
									var parameter = {
										 "cfId":cfId
									};
									jQuery.post(url,
											parameter,
											function(result){
											showAlert("�����ɹ�",{},{"clkFun":function(){
							    				if (parent.window){
							    					refershParent();
							    				}
							    			}});
											}
									);
									
							});
							}
						}
			});
		}
		
		//��ͨ��
		function butongguo(){
			confirmInfo("ȷ����ͨ����",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfsh_ajax.do?method=plSaveCfsh&doType=btg";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
							parameter,
							function(result){
						 	showAlert("�����ɹ�",{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
						});
				}
			});
		}

		//�˻�
		function tuihui(){
			confirmInfo("ȷ���˻���",function(tag){
				if(tag=="ok"){
					var cfId = $("cfId").value;
					var spgwId = $("spgwId").value;
					var cflbdm = $("cflbdm").value;
					var shyj = $("shyj").value;
					var url = "general_wjcf_cfsh_ajax.do?method=plSaveCfsh&doType=th";
					//����
				 	var parameter = {
				 		"cfId":cfId,
				 		"spgwId":spgwId,
				 		"cflbdm":cflbdm,
				 		"shyj":escape(shyj)
					};
					jQuery.post(url,
							parameter,
							function(result){
							showAlert("�����ɹ�",{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
						});
				}
			});
		}
		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfsb_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">								
					<input type="hidden" name="spgwId" id="spgwId"  value="${spgw}">	
					<input type="hidden" name="cflbdm" id="cflbdm"  value="${cflbdm}">	
					<input type="hidden" name="cfId" id="cfId"  value="${cfId}">	

					<html:hidden property="primarykey_checkVal" value="${primarykey_checkVal}" />
					<table width="100%" border="0" class="formlist">
					<tbody>
					<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<logic:equal name ="sfZgj" value="true">
							<tr>
								<td align="right">
									<font color="red">*</font>�����ĺţ�
								</td>
								<td align="left"  >
									<html:text property='cfwh' styleId='cfwh' maxlength="30"/>
								</td>
								<td align="right">
									<font color="red">*</font>����ʱ�䣺
								</td>
								<td align="left">
								<html:text property="cfsj" styleId="cfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cfsj','y-mm-dd');" />
							</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="right" width="15%">
								��������<br/>
							<font color="red"><B>(��1000��)</B></font>
							</td>
							<td align="left" colspan="3" >
								<textarea  id="shyj" name="shyj"  value="${cfsh.shyj}" rows="5" onblur="checkLen(this,1000)" style="width:450px"></textarea>
								
							</td>
						</tr>
					</tbody>
						
					<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
								<logic:equal name ="sfKyxg" value="false">
								<span class="red">(�¼�����û��Ѿ���ˣ��������޸ģ�)</span>
								</logic:equal>
							</div>
							<div class="btn">
									<button type="button"  id="btn_tg" onclick="tongguo();return false;" id="buttonSave">
											ͨ��
										</button>
									<logic:equal name ="sfZgj" value="true">
									<button type="button"  id="btn_tg" onclick="tongguoBtj();return false;" id="buttonSave">
											ͨ�����ύ
										</button>
									</logic:equal>	
									<button type="button"  id="btn_btg"  onclick="butongguo();return false;" id="buttonSave">
										��ͨ��
									</button>
									
									<logic:equal name ="sfDyj" value="false">
										<button type="button"  id="btn_th"  onclick="tuihui();return false;" id="buttonSave">
											�˻�
										</button>
									</logic:equal>
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</table>
				</input>
			</div>
			
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

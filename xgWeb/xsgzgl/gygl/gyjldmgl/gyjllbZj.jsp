<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		

		function jllbDivSave(){
			var xxdm = jQuery("#xxdm").val();
			
			var doType = jQuery("#doType").val();
			
			if(doType!="update"){
				if($("jllbdm").value==""){
					if(xxdm == '11799'){
						alertInfo("�����뽱�������룡");
					}else{
						alertInfo("��������������룡");
					}
					return false;
				}
			}
			if($("jllbmc").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("�����뽱��������ƣ�");
				}else{
					alertInfo("���������������ƣ�");
				}
				return false;
			}
			if($("jllbkf").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("�����뽱�����۷�����");
				}else{
					alertInfo("������������۷�����");
				}
				return false;
			}
			if($("jldldm").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("��ѡ�񽱳ʹ��࣡");
				}else{
					alertInfo("��ѡ����ɴ��࣡");
				}
				return false;
			}
			//allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType='+doType);
			 var url = "gyglnew_gyjldmgl.do?method=gyjllbManage&doType="+doType;
		      ajaxSubFormWithFun("gyjldmglForm",url,function(data){
		    	 if(data["message"]=="�����ɹ���"){
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

		

		jQuery(function(){
			var doType = jQuery("#doType").val();
			if(doType == "update"){
				jQuery("#jllbdm").readOnly="true";
				jQuery("#jllbdm").css("color","grey");
				jQuery("#jllbdm").focus(function(){
					  this.blur();
				});
			}
			jQuery("#jllbdm").bind("change",function(){
				var inValue=jQuery(this).val();
			    if(inValue!=""&&inValue.match(/^\d+\.{0,1}\d{0,3}$/)==null){			
			    	showAlert("����ֻ��Ϊ��Ч����!");
			    	jQuery(this).val("");
			 	}
			});
		});
		</script>
	</head>
	<body >

		<html:form styleId="gyjldmglForm" action="/gyglnew_gyjldmgl" method="post">
			<input type="hidden" id="doType" name="doType" value="${doType}"/>
			<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										����������
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										����������
									</logic:notEqual>
								</th>
								<td>
									<%--<input type="text" id="jllbdm" name="jllbdm" maxlength="100"/>
									--%><html:text property="jllbdm"   styleId="jllbdm" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										�����������
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										�����������
									</logic:notEqual>
								</th>
								<td>
									<%--<input type="text" id="jllbmc" name="jllbmc" maxlength="100"/>
									--%><html:text property="jllbmc" onkeypress="if(event.keyCode==13 ||event.keyCode==8 ){return false;}" styleId="jllbmc" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										�������۷�
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										�������۷�
									</logic:notEqual>
								</th>
								<td>
									
									<html:text property="jllbkf"  styleId="jllbkf" onkeyup="clearNoNum(this)" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										�������ʹ���
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">									
										�������ɴ���
									</logic:notEqual>
								</th>
								<td>
									<html:select property="jldldm" styleId="jldldm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="jldlList" property="jldldm" labelProperty="jldlmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="jllbDivSave()">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="Close();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
		</html:form>
	</body>
</html>

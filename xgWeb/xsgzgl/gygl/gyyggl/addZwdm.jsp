<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript" >
			function saveForm(){
				  var zwdm=jQuery("#zwdm").val();
				  if(zwdm==""){
					  showAlert("�뽫��*����Ŀ��д������");
						return false;
				  }
				  var zwmc=jQuery("#zwmc").val();
				  if(zwmc==""){
					  showAlert("�뽫��*����Ŀ��д������");
						return false;
				  }
				     var url = "gyglnew_gyygzwdmgl.do?method=addZwdm&type=save";
				      ajaxSubFormWithFun("gyygzwdmglForm",url,function(data){
				    	 if(data["message"]=="����ɹ���"){
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
		</script>
		
	</head>
	<body >
		<html:form action="/gyglnew_gyygzwdmgl" styleId="gyygzwdmglForm" method="post"  onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>����ְλ����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ְλ����
							</th>
							<td width="34%" >
								<html:text property="zwdm" styleId="zwdm" maxlength="5"  style="width:98%" styleClass="text_nor" onkeyup="checkInputData(this)"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>ְλ����
							</th>
							<td width="34%" >
								<html:text property="zwmc" styleId="zwmc"  style="width:98%" maxlength="15"  />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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


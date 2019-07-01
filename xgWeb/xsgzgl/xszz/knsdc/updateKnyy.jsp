<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveForm(){
			  var yymc=jQuery("#yymc").val();
			  if(yymc==""){
				  showAlert("�뽫��*����Ŀ��д������");
				  return false;
			  }
			  if(yymc.length>200){
				  showAlert("�������200�֣�");
				  return false;
			  }
		     var url = "xszz_knsdc.do?method=updateKnyy&type=update";
		      ajaxSubFormWithFun("knsdcForm",url,function(data){
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
		function checkxuhao(obj) {
			var text = obj.value;
			if (null !== text && ''!= text) {
				if (/[^\d]/.test(text)) {
					showAlert("����������ֲ����Ϲ���!",{},{"clkFun":function(){
						obj.focus();
					}});
				}
			}
		}
		</script>
	</head>
	<body >
		<html:form action="/xszz_knsdc" method="post" styleId="knsdcForm" onsubmit="return false;">
		<html:hidden property="yydm" styleId="yydm" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�޸�����ԭ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								���
							</th>
							<td width="34%">
								<html:text property="xh" maxlength="4" styleId="xh" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="checkxuhao(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>ԭ������
							</th>
							<td width="34%">
								<html:textarea property="yymc" styleId="yymc"  style="width:99%;"  onkeypress="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
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


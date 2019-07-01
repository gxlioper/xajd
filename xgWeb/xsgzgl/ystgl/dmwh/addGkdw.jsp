<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function saveForm(){	  
				  var mc=jQuery("#gkdwmc").val();
				  var dm=jQuery("#gkdwdm").val();
				  if(jQuery.trim(dm)==""){
					  showAlert("������ҿ���λ���룡");
						return false;
				  }
				  if(jQuery.trim(mc)==""){
					  showAlert("������ҿ���λ���ƣ�");
						return false;
				  }
			   var url = "ystglDmwh.do?method=addGkdw&type=save";
			    ajaxSubFormWithFun("YstglDmwhForm",url,function(data){
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
	<body>
		<html:form action="/ystglDmwh" method="post"
			styleId="YstglDmwhForm" onsubmit="return false;">

			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>�ҿ���λ����
							</th>
							<td>
								<html:text property="gkdwdm" styleId="gkdwdm" maxlength="10" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ҿ���λ����
							</th>
							<td>
								<html:text property="gkdwmc" styleId="gkdwmc" maxlength="20" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"
									styleClass="text_nor" />
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>


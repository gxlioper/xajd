<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		function saveForm(){
			 // var rcxwlbdm=jQuery("#rcxwlbdm").val();
			 // if(rcxwlbdm==""){
				//  showAlert("��������Ϊ�����룡");
				//	return false;
			 // }
			  
			  var rcxwlbmc=jQuery("#rcxwlbmc").val();
			  if(jQuery.trim(rcxwlbmc)==""){
				  if(jQuery("#xxdm").val() == '10704'){
					  showAlert("�������ۺϲ���������ƣ�");
				  }else{
					  showAlert("��������Ϊ������ƣ�");
				  }
					return false;
			  }
			  
		     var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwlb&type=save";
		      ajaxSubFormWithFun("rcxwdmwhForm",url,function(data){
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
		<input type="hidden" id="xxdm" value="${xxdm}"/>
		<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="10704" name="xxdm">
											�ۺϲ����������
										</logic:equal>
										<logic:notEqual value="10704" name="xxdm">								
											��Ϊ�������
										</logic:notEqual>									
								</th>
								<td colspan="3">
									<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxlength="50"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveForm();return false;">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="iFClose();return false;">
											ȡ ��
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


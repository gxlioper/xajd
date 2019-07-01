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
			function updateForm(){
				var splc=jQuery("#splc").val();
				if(splc==null||splc==""){
					showAlert("��ѡ����������");
					return false;
				}
				 var url = "dekt_xmwh.do?method=splcSave&type=update";
			      ajaxSubFormWithFun("dektxmwhForm",url,function(data){
			    	 if(data["success"]=="true"){
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
		<html:form action="/dekt_xmwh" method="post" styleId="dektxmwhForm" onsubmit="return false;">
			 <html:hidden property="xmid"  styleId="xmid"/>
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										��������ά��
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th><span class="red">*</span>��������</th>
								<td>
									<html:select property="splc" styleId="splc" style="width:280px;">
										<html:option value=""></html:option>
										<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
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
										<button type="button" name="����" onclick="updateForm();return false;">
											�� ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();return false;">
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


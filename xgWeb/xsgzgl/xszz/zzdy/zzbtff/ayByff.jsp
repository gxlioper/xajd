<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		jQuery(function(){
		});
	
		function saveForm(){
		    var flag="";
			var ffyf=jQuery("#ffyf").val();  
			jQuery.ajaxSetup({async:false});
			jQuery.post("xszz_zzdyzzbtff.do?method=checkFfzt",{ffyf:ffyf},function(data){
				flag = data["message"];
			
		},'json');
			if("true"==flag){
				showConfirm("���·�����ѧ�����ţ��Ƿ����·��ţ�",{"okFun":function(){
				tj();
			}});
			}else{
				tj();
				}
			jQuery.ajaxSetup({async:true});
		}
		   	 
		function tj(){
			 var url = "xszz_zzdyzzbtff.do?method=btff&type=save";
		    	 ajaxSubFormWithFun("ZzdyBtffForm",url,function(data){
			    	 if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }
				});
		  }
		</script>
	</head>
	<body>
		<html:form action="/xszz_zzdyzzbtff" method="post" styleId="ZzdyBtffForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
						<th>�����·�</th>
							<td>
								<html:select property="ffyf" styleId="ffyf" style="width:90px">
								<html:options collection="ffyfList" labelProperty="ffyf" property="ffyf"/>
								</html:select>
							</td>
						</tr>
					
					</tbody>
					</table>
			      </div>

	<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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


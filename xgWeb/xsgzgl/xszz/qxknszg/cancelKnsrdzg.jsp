<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/function.js"></script>

		<script type="text/javascript">
		
	

		function saveForm(){					

			var qxyy = jQuery("#qxyy").val();	
			
			if (jQuery.trim(qxyy) == ""){
				
				showAlert(" ��������˵��ȡ���϶�ԭ��");
				return false;
			}	

			var qxtype = jQuery("#qxtype").val();						
			if (jQuery.trim(qxtype) == "more"){	
															
				var api = frameElement.api,W = api.opener;
				W.saveMorePlqxrd(qxyy,qxtype);
				closeDialog();	
																	
			}else if(jQuery.trim(qxtype) == "one"){		
											
				var url = "xszz_qxknszggl.do?method=cancelKnsrdzg&type=save";
			    ajaxSubFormWithFun("qxknszgForm",url,function(data){
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
							
				return false;	
																															
			}else{	
											
				var api = frameElement.api,W = api.opener;
				W.saveAllPlqxrd(qxyy,qxtype);
				closeDialog();	
								
			}
			
		  }

		
	
			
		</script>

	</head>
	<body>

		<html:form action="/xszz_qxknszggl" method="post" styleId="qxknszgForm" >
			<input type="hidden" name="countNum" id="countNum" value="${countNum}"/>
			<html:hidden property="qxtype" styleId="qxtype"/>	
			<html:hidden property="guid" styleId="guid"/>
			
			
			<div style="overflow-x:hidden;overflow-y:auto;">								
			     <table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th align="center">
								<span>��˵��ȡ���϶�ԭ��<font color="red"><��50��></font></span>
							</th>
						</tr>
					
					</thead>
					
					<tbody>	
						<tr >
							
							<td>							
								��ʾ<span class="red">:</span>��ǰ��&nbsp;${countNum}&nbsp;��ѧ����������ȡ������						 
							</td>
					    </tr>					
														
						<tr>      						
				     	    <td>			     	    				        						        	
				        		<html:textarea property='qxyy' style="width:98%" styleId="qxyy" rows='10' onblur="chLeng(this,50);"/>				        		
				       		</td>
				        </tr>
					</tbody>
				</table>
				
																	
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
									�ύ
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


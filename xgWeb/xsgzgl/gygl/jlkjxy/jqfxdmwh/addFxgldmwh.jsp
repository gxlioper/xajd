<%@ page language="java" contentType="text/html; charset=GBK"%>
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
		<script language="javascript">
		function saveForm(){	

				

			  var fxdm=jQuery("#fxdm").val();
			  if(jQuery.trim(fxdm)==""){
				  showAlert("�����뷵У�����룡");
				  return false;
			  }
			
			 
			    
			  var fxmc = jQuery("#fxmc").val();
			  if(jQuery.trim(fxmc)==""){
				  showAlert("�����뷵У������ƣ�");
				  return false;
			  }
		     var url = "jlkjxy_jqfxdmwh.do?method=addFxglDmwh&type=save";
		     
		      ajaxSubFormWithFun("jlkjxy_jqfxdmwhForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 	var api = frameElement.api,W = api.opener;
		    			 	W.jQuery('#dataTable',W.document).reloadGrid();
		    				closeDialog();
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
				
		  }

		
		function titleLoad(id){
			if(jQuery("#"+id)){
				jQuery("#"+id).children("option").each(function(){
					jQuery(this).attr("title",jQuery(this).text());
				});
			}
		}
		</script>
	</head>
	<body >
		<html:form action="/jlkjxy_jqfxdmwh" method="post" styleId="jlkjxy_jqfxdmwhForm" onsubmit="return false;">
			
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>���ӷ�У���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>��У������
								</th>
								<td>
									<html:text property="fxdm" styleId="fxdm" maxlength="6" style="width:273px;" styleClass="text_nor" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>	��У�������
									<br/><font color="red">&lt;��200��&gt;</font>
								</th>
								<td colspan="3">
									<html:textarea property='fxmc' style="width:98%" styleId="fxmc" rows='5' onblur="checkLen(this,200);"/>
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


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

		<script type="text/javascript">
		function saveForm(){					

			var fxsj = jQuery("#fxsj").val();
					
			if (jQuery.trim(fxsj) == ""){
				showAlert("����ѡ��Уʱ�䣡");
				return false;
			}
			
			var pldgxsJqfx = jQuery("#pldgxsJqfx").val();
			
			if (jQuery.trim(pldgxsJqfx) == "1"){
				
				var api = frameElement.api,W = api.opener;
				W.savedgPlsh(fxsj);
				closeDialog();	
					
			}else{	
				
				var api = frameElement.api,W = api.opener;
				W.savePlfxsh(fxsj);
				closeDialog();							
								
			}
			
		  }

		
		</script>

	</head>
	<body>

		<html:form action="/jlkjxy_jqfxwh" method="post"
			styleId="jlkjxy_jqfxwhForm">	
			<input type="hidden" name="pldgxsJqfx" id="pldgxsJqfx" value=" ${pldgxsJqfx}"/>				
			<div style="height:168px;overflow-x:hidden;overflow-y:auto;">	
			<table width="100%" border="0" class="formlist">																						
					<tbody>
					    <tr>
							<th><span class="red"></span>��ʾ</th>
							<td>							
								��ǰ��  <span style="color: blue">${countNum}</span>��ѧ������������У����						 
							</td>
					    </tr>
					    
					    <tr>
							<th><span class="red">*</span>��Уʱ��</th>
							<td >
							<html:text property="fxsj" styleId="fxsj" style="width:130px" onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
							</td>
							
					    </tr>					   					  
					</tbody>
			</table>	
													
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
								<td colspan="4">
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


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
			var url = "jlkjxy_jqfxwh.do?method=addxsJqfx&type=save";
		    ajaxSubFormWithFun("jlkjxy_jqfxwhForm",url,function(data){
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

		<html:form action="/jlkjxy_jqfxwh" method="post"
			styleId="jlkjxy_jqfxwhForm">
			<input type="hidden" id="xn" name="xn" value="${xn}"/>
			<input type="hidden" id="xq" name="xq" value="${xq}"/>
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="fxzt" styleId="fxzt"/>		
			<div style="height:320px;overflow-x:hidden;overflow-y:auto;">	
			<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>										
				
					<thead>
						<tr>
							<th colspan="4">
								<span>δ��У��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
					    <tr>
							<th><span class="red"></span>ѧ��/ѧ��</th>
							<td>							
								${xn}/${xq}								 
							</td>
							<th><span class="red"></span>��У״̬</th>
							<td>
								${fxztmc}
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>��Уʱ��</th>
							<td >
							<html:text property="fxsj" styleId="fxsj" style="width:130px" onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
							</td>
							<th><span class="red"></span></th>
							<td>
							
							</td>
					    </tr>					   					  
					</tbody>
					
					
				
				</table>	
				</div>							
				<table width="100%" border="0" class="formlist">
							<tfoot>
								<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										<logic:equal name="xxdm" value="13100">
											<input type="hidden" name="tbsj" id="tbsj" value="${tbsj}" />
										</logic:equal>
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
		</html:form>
	</body>
</html>


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
		
		function saveForm1111(){
												
			var wfxyy = jQuery("#wfxyy").val();		
			if (jQuery.trim(wfxyy) == ""){
				showAlert("请先输入未返校原因！");
				return false;
			}
						
			var url = "jlkjxy_jqfxwh.do?method=addxsJqwfx&type=save";			
		    ajaxSubFormWithFun("jlkjxy_jqfxwhForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
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


		function saveForm(){
				
			var wfxyy = jQuery("#wfxyy").val();		
			if (jQuery.trim(wfxyy) == ""){
				showAlert("请先输入未返校原因！");
				return false;
			}	
			
			var api = frameElement.api,W = api.opener;
			W.savedgWfxplsh(wfxyy);
			closeDialog();							
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
			<div style="height:168px;overflow-x:hidden;overflow-y:auto;">										
			     <table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5" >
								<span>未返校信息</span>
							</th>
						</tr>
					</thead>					
					<tbody>											
						<tr>
       						<th >
				        		<font color="red">*</font>未返校原因
				       		</th>
				     	    <td width="80%" colspan="3">
				     	    	<jsp:include page="cyyy.jsp?id=wfxyy" />				        						        	
				        		<html:textarea property='wfxyy' style="width:98%;margin-top:5px;" styleId="wfxyy" rows='5' onblur="chLeng(this,500);"/>
				        		
				       		</td>
				        </tr>
					</tbody>
					</table>
			</div>																	
			<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
									提交
									</button>				
									<button type="button" type="button" onclick="iFClose();">
									关 闭
									</button>
								</div>
							</td>
							</tr>
						</tfoot>
			</table>
		</html:form>
	</body>
</html>


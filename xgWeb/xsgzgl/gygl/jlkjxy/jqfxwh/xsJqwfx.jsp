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
						
			//����֤ѧ��
			var wfxyy = jQuery("#wfxyy").val();		
			if (jQuery.trim(wfxyy) == ""){
				showAlert("��������δ��Уԭ��");
				return false;
			}

			var url = "jlkjxy_jqfxwh.do?method=addxsJqwfx&type=save";
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
			<div style="height:480px;overflow-x:hidden;overflow-y:auto;">
				<table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>										
				</table>
				
				<table width="95%" border="0" class="formlist">
					<thead>						
							<th colspan="5">
								<span>��Ԣ��Ϣ</span>
							</th>					
					</thead>	
						
					<tbody>
						<tr>
							<th style="text-align: left">¥�� </th>
							<th style="text-align: left">���</th>
							<th style="text-align: left">���Һ�</th>
							<th style="text-align: left">��λ��</th>
							<th style="text-align: left">���ҵ绰</th>
						</tr>
						<tr>
							<td>${rs.ldmc}</td>
							<td>${rs.ch}</td>
							<td>${rs.qsh}</td>
							<td>${rs.cwh}</td>
							<td>${rs.xm}</td>
						</tr>
					</tbody>
			     </table>	
			     <table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5" >
								<span>δ��У��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>					
						<tr>
								<tr>
									<th>
										<font color="red"></font>��У״̬
									</th>
									<td colspan="1" id="shjgSpan">
										 ${fxztmc}
									</td>
								</tr>
						</tr>
						<tr>
       						<th >
				        		<font color="red">*</font>δ��Уԭ��
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
		</html:form>
	</body>
</html>


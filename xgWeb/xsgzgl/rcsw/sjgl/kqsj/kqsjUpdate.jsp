<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript">
		function saveForm(){
			var xn = jQuery("#xn").val();
			var kkxs = jQuery("#kkxs").val();
			var cdztcs = jQuery("#cdztcs").val();
			var id = jQuery("#id").val();
			if (jQuery.trim(kkxs) == ""){
				showAlert("���������ѧʱ��");
				return false;
			}	
			if (jQuery.trim(cdztcs) == ""){
				showAlert("������ٵ����˴�����");
				return false;
			}	
			var url = "kqsj.do?method=updateKqsj&type=update";
	      	ajaxSubFormWithFun("KqsjForm",url,function(data){
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
	<body style="width: 100%">
		<html:form action="/kqsj" method="post" styleId="KqsjForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type}" />
			<html:hidden property="id"  styleId="id"/>
			<html:hidden property="xh"  styleId="xh"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/rcsw/hcyhkgl/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>��������ά�� </span>
							</th>
						</tr>
					</thead>					
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td colspan="3">
								<html:select  property="xn" styleId="xn" style="width:200px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>����ѧʱ</th>
							<td>
								<html:text property="kkxs" styleId="kkxs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th><span class="red">*</span>�ٵ����˴���</th>
							<td>
								<html:text property="cdztcs" styleId="cdztcs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>
						<tr>
							<th>��һѧ���¼�����</th>
							<td>
								<html:text property="dyxqsjts" styleId="dyxqsjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th>�ڶ�ѧ���¼�����</th>
							<td>
								<html:text property="dexqsjts" styleId="dexqsjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>						
						<tr>
							<th>��һѧ�ڲ�������</th>
							<td>
								<html:text property="dyxqbjts" styleId="dyxqbjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th>�ڶ�ѧ�ڲ�������</th>
							<td>
								<html:text property="dexqbjts" styleId="dexqbjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>	
				</table>
			</div>
			<div>	
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="save_button" type="button"
										onclick="saveForm();">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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


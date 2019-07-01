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
			var xq = jQuery("#xq").val();
			var xh = jQuery("#xh").val();
			var qszlccs = jQuery("#qszlccs").val();
			var yxqscs = jQuery("#yxqscs").val();
			var yxqsjf = jQuery("#yxqsjf").val();
			var qsz = jQuery("#qsz").val();
			var sywjdqcs = jQuery("#sywjdqcs").val();
			var ybgscs = jQuery("#ybgscs").val();
			if (jQuery.trim(xh) == ""){
				showAlert("������ѧ�ţ�");
				return false;
			}
			if (jQuery.trim(xq) == ""){
				showAlert("������ѧ�ڣ�");
				return false;
			}	
			if (jQuery.trim(xn) == ""){
				showAlert("������ѧ�꣡");
				return false;
			}
			if (jQuery.trim(qszlccs) == ""){
				showAlert("�������������Ҳ������");
				return false;
			}
			if (jQuery.trim(yxqscs) == ""){
				showAlert("�������������Ҵ�����");
				return false;
			}
			if (jQuery.trim(yxqsjf) == ""){
				showAlert("�������������Ҽӷ֣�");
				return false;
			}
			if (jQuery.trim(qsz) == ""){
				showAlert("�������Ƿ����ҳ���");
				return false;
			}
			if (jQuery.trim(sywjdqcs) == ""){
				showAlert("������ʹ��Υ������������");
				return false;
			}
			if (jQuery.trim(ybgscs) == ""){
				showAlert("������ҹ�����޴�����");
				return false;
			}	
			var url = "hqsj.do?method=hqsjUpdate&type=update";
	      	ajaxSubFormWithFun("HqsjForm",url,function(data){
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
		<html:form action="/hqsj" method="post" styleId="HqsjForm" onsubmit="return false;">
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
							<td>
								<html:select  property="xn" styleId="xn" style="width:200px;">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:200px;">
									<html:options collection="xqList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�������Ҳ����</th>
							<td>
								<html:text property="qszlccs" styleId="qszlccs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th><span class="red">*</span>�������Ҵ���</th>
							<td>
								<html:text property="yxqscs" styleId="yxqscs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�������Ҽӷ�</th>
							<td>
								<html:text property="yxqsjf" styleId="yxqsjf" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th><span class="red">*</span>ʹ��Υ����������</th>
							<td>
								<html:text property="sywjdqcs" styleId="sywjdqcs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>						
						<tr>
							<th><span class="red">*</span>ҹ�����޴���</th>
							<td>
								<html:text property="ybgscs" styleId="ybgscs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th><span class="red">*</span>�Ƿ����ҳ�</th>
							<td>
								<html:select  property="qsz" styleId="qsz" style="width:200px;">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>				
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


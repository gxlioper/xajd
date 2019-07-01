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
			var hdsc = jQuery("#hdsc").val();
			var xh = jQuery("#xh").val();
			if (jQuery.trim(xh) == ""){
				showAlert("������ѧ�ţ�");
				return false;
			}
			if (jQuery.trim(hdsc) == ""){
				showAlert("������ʱ����");
				return false;
			}	
			if (jQuery.trim(xh) == ""){
				showAlert("������ѧ�ţ�");
				return false;
			}
			var url = "twsj.do?method=addTwsj&type=save";
	      	ajaxSubFormWithFun("TwsjForm",url,function(data){
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
		<html:form action="/twsj" method="post" styleId="TwsjForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type }" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>��ί����ά�� </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:200px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>־Ը�߻ʱ��</th>
							<td>
								<html:text property="hdsc" styleId="hdsc" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>��				
							</td>
						</tr>						
						<tr>
							<th>
								��ע
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='9' onblur="checkLen(this,500);"/>
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


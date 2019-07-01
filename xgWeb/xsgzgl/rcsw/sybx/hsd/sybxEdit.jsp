<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var url = "hsd_sybx.do?method=update";
				
				if (jQuery("#xn").val() == "" ||
					jQuery("#bdh").val() == "" || jQuery("#bdyxq").val() == "" ||
					jQuery("#be").val() == "" || jQuery("#gmsj").val() == "" ||
					jQuery("#je").val() == "" || jQuery("#bxgs").val() == ""
					){
					showAlert("�뽫��������д������");
					return false;
				}
				
				ajaxSubFormWithFun("hsdSybxForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/hsd_sybx" method="post" styleId="hsdSybxForm">
			<html:hidden property="id" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>������</th>
							<td>
								<html:text property="bdh" maxlength="20" styleId="bdh" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>������Ч��</th>
							<td>
								<html:text property="bdyxq" maxlength="2" styleId="bdyxq" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>���£�
							</td>
							<th><span class="red">*</span>����</th>
							<td>
								<html:text property="be" maxlength="10" styleId="be" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>����ʱ��</th>
							<td>
								<html:text property="gmsj" maxlength="20" styleId="gmsj"
									onfocus="showCalendar('gmsj','y-mm-dd');"  readonly="readonly"
								></html:text>
							</td>
							<th><span class="red">*</span>���</th>
							<td>
								<html:text property="je" maxlength="10" styleId="je" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>���չ�˾</th>
							<td colspan="3">
								<html:text property="bxgs" maxlength="25" styleId="bxgs"></html:text>
							</td>
						</tr>
					</tbody>
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


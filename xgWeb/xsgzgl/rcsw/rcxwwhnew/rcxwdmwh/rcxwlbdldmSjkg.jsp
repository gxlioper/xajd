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
			jQuery(function() {
			});
	
			function saveForm() {
				var sqkssj = jQuery("#sqkssj").val();
				var sqjssj = jQuery("#sqjssj").val();
				
				var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbdldmSjkg&type=update";
				ajaxSubFormWithFun("form1", url, function(data) {
					showAlert(data["message"],{},{"clkFun":  function(tag) {
						if (tag == "ok") {
							refershParent();
						}
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post" styleId="form1">
		<html:hidden property="rcxwlbdldm" styleId="rcxwlbdldm" />
		<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ���õ���Ϊ����Ϊ��<font color="red">${rcxwlbdlmc} &nbsp;	
				</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
			<div style='' id ="kgszDiv">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ʱ������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								���뿪��
							</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								��������ʱ��
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj" style="width: 135px;"
								onclick="return showCalendar('sqkssj','yyyy-MM-dd',true,'sqjssj','245','0');" />
								&nbsp;��
								<html:text property="sqjssj" styleId="sqjssj" style="width: 135px;"
								onclick="return showCalendar('sqjssj','yyyy-MM-dd',false,'sqkssj','410','0');" />
							</td>
						</tr>		

						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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


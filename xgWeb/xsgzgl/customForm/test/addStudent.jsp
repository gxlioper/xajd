<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var url = "demo.do?method=addStudent&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					alertInfo(data["message"]);
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/demo" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>����ѧ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" styleId="xh" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%" >
								<html:text property="xm" styleId="xm" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								�༶����
							</th>
							<td width="34%" >
								<html:text property="bjdm" styleId="bjdm" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
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


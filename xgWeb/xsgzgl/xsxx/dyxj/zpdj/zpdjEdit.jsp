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
				if (jQuery.trim(jQuery("#djmc").val()) == ""){
					showAlertx("�뽫��������д������");
					return false;
				}
				
				var _fun = function(){
					var url = "xsxxDyxjZpdj.do?method=update";
					ajaxSubFormWithFun("dmwhForm",url,function(data){
						showAlertx(data["message"]);
						refershParent();
					});
				};
				
				if (jQuery("#ydjmc").val() != jQuery("#djmc").val()){
					jQuery.post("xsxxDyxjZpdj.do?method=getCountByDjmc",{djmc:jQuery("#djmc").val()},function(data){
						if (Number(data) == 0){
							_fun();
						} else {
							showAlertx("�������ȼ��Ѵ��ڡ�");
						}
						
					},"json");
				} else {
					_fun();
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxxDyxjZpdj" method="post" styleId="dmwhForm">
			<html:hidden property="djdm" />
			<input type="hidden" value="${dyxjZpdjModel.djmc }" id="ydjmc"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�޸�</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>�����ȼ�
							</th>
							<td width="34%">
								<html:text property="djmc" styleId="djmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ˵��
							</th>
							<td>
								<html:text property="xmsm" styleId="xmsm" maxlength="50" styleClass="text_nor" />
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


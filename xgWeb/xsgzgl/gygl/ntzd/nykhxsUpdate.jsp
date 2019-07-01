<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function yanzheng(){
				var khxs = jQuery("#khxs").val();
				var dysskhfz = jQuery("#dysskhfz").val();
				var reg=/^(([0-9])|([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
				if(khxs!=null && khxs!=""){
					if(!reg.test(khxs)){

						alertInfo("����ϵ������������Ч���֣�",function(tag){
							if(tag == "ok"){
								jQuery("#khxs").focus();
								}
							});
						return false;
					}					
				}else {
					alertInfo("����ϵ������Ϊ�գ�",function(tag){
						if(tag == "ok"){
							jQuery("#khxs").focus();
							}
						});					
					return false;
				}
				if(dysskhfz!=null && dysskhfz!=""){
					
					if(!reg.test(dysskhfz)){
						alertInfo("�������ῼ�˷�ֵ����������Ч���֣�",function(tag){
							if(tag == "ok"){
								jQuery("#dysskhfz").focus();
								}
							});
						return false;
					}	
				}else{
					alertInfo("�������ῼ�˷�ֵ����Ϊ�գ�",function(tag){
						if(tag == "ok"){
							jQuery("#dysskhfz").focus();
							}
						});	
					return false;
				}
				return true;
			}
			function save(method){
				if(yanzheng()){
					var url = "gygl_ntzd.do?method="+method+"&type=save";
					ajaxSubFormWithFun("demoForm",url,function(data){
						alertInfo(data["message"],function(ty){
							if(ty=="ok"){
								if (parent.window){
									refershParent();
								}
							}
						});
					});
				}
			}
			jQuery(function(){
				//Ϊbuttonע���¼�
				jQuery("#but_save").click(function(){save("nykhxsUpdate")});
				//jQuery("#but_close").click();
				
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/gygl_ntzd" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�޸��¿���ϵ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>							
							<th width="30%">
								����
							</th>
							<td >
								${model.ny }
								<html:hidden property="ny" styleId="ny" name="model"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ϵ��
							</th>
							<td >
								<html:text property="khxs" styleId="khxs" name="model" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������ῼ�˷�ֵ
							</th>
							<td >
								<html:text property="dysskhfz" styleId="dysskhfz" name="model" maxlength="10"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" >
										�� ��
									</button>
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										�ر�
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


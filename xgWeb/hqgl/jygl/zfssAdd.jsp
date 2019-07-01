<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="hqgl/jygl/js/zfss.js"></script>
		
		<script type="text/javascript">
			function saveForm(){
				if(jQuery("#zgh").val()==''||jQuery("#jrsj").val()==''||jQuery("#zbr").val()==''||jQuery("#fwly").val()==''){
					showAlert("�뽫������Ϣ����д������");
					return false;
				}

				
				//if(checkAllInput('zgh!!zbr!!fwly')){
					var url = "zfss_zfss.do?method=addZfss&type=save";
					ajaxSubFormWithFun("zfssForm",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
				//}
			}
			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/zfss_zfss" method="post" styleId="zfssForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����߷õǼ�</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ְ����
							</th>
							<td width="34%">
								<html:text property="zgh" styleId="zgh" maxlength="20" styleClass="text_nor" readonly="true" />
								<button class="btn_01" type="button" onclick="setTrValue();">ѡ��</button>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" >
								<span id="xmsp"></span>
							</td>
						</tr>
						<tr>
							<th >�Ա�</th><td>	<span id="xb"></span></td>
							<th >����</th><td>	<span id="bmmc"></span></td>
						</tr>
						
						<tr>
							<th width="16%">
								<font color="red">*</font>ֵ����
							</th>
							<td width="84%" colspan="3">
								<html:text property="zbr" styleId="zbr" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>����ʱ��
							</th>
							<td width="84%" colspan="3">
								<html:text property="jrsj" styleId="jrsj" style="width:120px;" onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm','','',250,10);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>�߷����
							</th>
							<td width="84%" colspan="3">
								<html:textarea property="fwly" style="width:98%;" rows="4"  onblur="checkLen(this,128);" styleId="fwly" style="word-break:break-all;width:99%"></html:textarea>
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


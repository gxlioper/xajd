<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		function saveForm(url){	
			if (!checkNull("mc-sj-dd-zbdw-zjr-cyrs")) {
				 return false;
			 }
				var url = "jzbggl.do?method=addJzbg&type=save";
				ajaxSubFormWithFun("jzbgForm", url, function(data) {
					if (data["message"] == "����ɹ���") {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								if (parent.window) {
									refershParent();
								}
							}
						});
					} else {
						showAlert(data["message"]);
					}
				});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jzbggl" method="post" styleId="jzbgForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��������</th>
							<td>
								<html:text styleId="mc" property="mc"  maxlength="50"/>
							</td>
							<th><font color="red">*</font>�����ص�</th>
							<td>
								<html:text styleId="dd" property="dd"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>����ʱ��</th>
							<td>
							<html:text property="sj" styleId="sj" style="width:120px;"
								onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss',true,'','','','');" />
							</td>
							<th><font color="red">*</font>���쵥λ</th>
								<td>
								<html:text styleId="zbdw" property="zbdw"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������</th>
							<td>
								<html:text styleId="zjr" property="zjr"  maxlength="50"/>
							</td>
							<th><font color="red">*</font>��������</th>
							<td>
								<html:text styleId="cyrs" property="cyrs"  maxlength="4" onblur="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th>����<br><font color="red">����������200��</font><br/></th>
							<td colspan="3">
								<html:textarea property="zt" styleId="zt" onblur="chLengs(this,200);"
								   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										��    ��
									</button>
									<button type="button" onclick="iFClose();">
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
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
function save(url){
	var symc = jQuery("#symc").val();
	if (jQuery.trim(symc) == "" || jQuery.trim(symc) == null){
		showAlert("����д��*�ı����");
		return false;
	}
	ajaxSubFormWithFun("syglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
		
		showConfirmDivLayer("����ɹ�,��Ҫ���������༶��", {
			"okFun" : function() {
				var url = 'xtwh_syglwh.do?method=bjManage&sydm='+ data["sydm"];
				showDialog("�༶����",800,500,url);
				refershParent();
			},
			"cancelFun" : function() {
				refershParent();
			}
		});
			
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}
		</script>
	</head>
	<body>
		<html:form action="/xtwh_syglwh" styleId="syglForm" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ժ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								��Ժ����
							</th>
							<td style="width:70%" >
								<input type="text" name="symc" style="width:235px" id="symc" maxlength="20" />
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<font color="red">*</font>"Ϊ������
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="save('xtwh_syglwh.do?method=syadd&type=save')">
										�� ��
									</button>
									<button class="button2" type="button" onclick="iFClose()">
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

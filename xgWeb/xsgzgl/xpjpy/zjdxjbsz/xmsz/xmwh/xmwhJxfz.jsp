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
				onShow();
			});
	
			function onShow() {
				var url = 'xpjpy_xmwh.do?method=xmwhJxfz&type=query';
				jQuery.post(url, {
				}, function(data) {
					/*���ó�ֵ*/
					setInit(data);
				}, 'json');
			}
	
			function setInit(data){
				if(data == null || data.length == 0	){
					return ;
				}
				var sHtml = "";
				for ( var i = 0; i < data.length; i++) {
					var o = data[i];
					var dm = o.dm;
					var mc = o.mc;
					if(dm != null && dm != ""){
						sHtml += "<option value='"+dm+"'>"+mc+"</option>";
					}
				}
				jQuery("#jxfznd").html(sHtml);
			}
			/*�������*/
			function saveForm() {
				var jxfznd = jQuery("#jxfznd").val();
				if(jxfznd == null || jxfznd == ""){
					showAlert("��ѡ������Դ��ȣ�");
					return false;
				}
				var url = 'xpjpy_xmwh.do?method=saveFormCopy';
				ajaxSubFormWithFun("jxfzForm", url, function(data) {
					if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
						showAlert(data["message"]);
					} else {
						showAlert(data["message"], {}, {"clkFun" : function(tag) {
								if (tag == "ok") {
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
		<html:form action="/xpjpy_xmwh" method="post" styleId="jxfzForm">
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>"<font color="red">��������</font>"���ɸ���</p>
				<p>
					���ƽ������<font color="red">ע���޸�</font>�������"<font color="red">��������</font>"��"<font color="red">�������</font>"��"<font color="red">�����������</font>"��
					�ر���"<font color="red">�������á�</font>"�и�������<font color="red">������Χ</font>����޸�
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>������Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>������Դ���</th>
							<td>
								<select id="jxfznd" name="jxfznd" style="width:100px"></select>
							</td>
						</tr>
						<tr>
							<th>����Ŀ�����</th>
							<td>
								${pjzq}
							</td>
						</tr>

					</tbody>
				</table>
			</div>	
			<div style="height:15px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
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
		</html:form>
	</body>
</html>
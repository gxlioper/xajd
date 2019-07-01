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
		<script type="text/javascript" defer="defer">

			function addAction(){
				var checkids = "xn-yf";
				
				if(!checkNotNull(checkids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
					return false;
				}
				
				var url = "xlzxnew_zqrcgl.do?method=saveYzqsz";
					ajaxSubFormWithFun("ZqszForm",url,function(data){
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
	</head>
	<body>
		<html:form action="/xlzxnew_zqrcgl" method="post" styleId="ZqszForm">
		<html:hidden property="ybid" styleId="ybid"/>
			<div style='tab;width:100%;height:330px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ܱ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								<span class="red">*</span>ѧ��
							</th>
							<td align="left">
								<html:select property="xn" styleId="xn" disabled="false" 
										style="width:125px;">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
							</td>
							<th align="right">
								<span class="red">*</span>�·�
							</th>
							<td align="left">
								<html:select property="yf" styleId="yf" disabled="false" 
										style="width:125px;">
										<html:option value="01">1��</html:option>
										<html:option value="02">2��</html:option>
										<html:option value="03">3��</html:option>
										<html:option value="04">4��</html:option>
										<html:option value="05">5��</html:option>
										<html:option value="06">6��</html:option>
										<html:option value="07">7��</html:option>
										<html:option value="08">8��</html:option>
										<html:option value="09">9��</html:option>
										<html:option value="10">10��</html:option>
										<html:option value="11">11��</html:option>
										<html:option value="12">12��</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="addAction();">
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


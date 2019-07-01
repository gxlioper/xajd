<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(url){

				var xxdm = jQuery("#xxdm").val();
				
				if("13943" == xxdm) {
					if (jQuery("#xh").val() == "" || jQuery("#xn").val() == "" 
						|| jQuery("#cxdj").val() == "" || jQuery("#cxpy").val() == "" 
						|| jQuery("#sqsj").val() == "" ){
						showAlert("�뽫��������д������");
						return false;
					}
				}else {
					if (jQuery("#xh").val() == "" || jQuery("#xn").val() == "" 
						|| jQuery("#cxdj").val() == "" || jQuery("#cxpy").val() == "" 
						|| jQuery("#xq").val() == "" ){
						showAlert("�뽫��������д������");
						return false;
					}
				}
				
				
				var xh = jQuery("#xh").val();
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				
				jQuery.post("cxpdSqsh.do?method=getExistsCount",{xh:xh,xn:xn,xq:xq},function(data){
					if (Number(data) == 0){
						ajaxSubFormWithFun("cxpdForm",url,function(data){
							showAlert(data["message"]);
							refershParent();
						});
					} else {
						if("13943" == xxdm) {
							showAlertDivLayer("��ѧ���Ѿ������������������ȷ�ϣ�");
						}else {
							showAlertDivLayer("��ѧ���Ѿ������������������ȷ�ϣ�");
						}
					}
				},"json");
			}
		</script>
	</head>
	<body>
		<html:form action="/cxpdSqsh" method="post" styleId="cxpdForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="splcid" value="${cssz.splc }"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���еȼ�����</span>
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
							<logic:notEqual name="xxdm" value="13943">
								<th><span class="red">*</span>ѧ��</th>
								<td>
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13943">
								<th><span class="red">*</span>��������</th>
								<td>
									<html:text property="sqsj" styleId="sqsj" onfocus="showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" value = "${nowTime}"></html:text>
								</td>
							</logic:equal>
						</tr>
						<tr>
							<th><span class="red">*</span>���еȼ�</th>
							<td>
								<html:select property="cxdj" styleId="cxdj">
									<html:option value=""></html:option>
									<html:options collection="cxdjList" property="cxdjdm" labelProperty="cxdjmc"/>
								</html:select>
							</td>
							<th>������</th>
							<td>
								${userNameReal }
								<input type="hidden" name="bzr" value="${userNameReal }"/>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��������<br/>
								<span class="red">(��150��)</span>
							</th>
							<td colspan="3">
								<html:textarea property="cxpy" styleId="cxpy" 
											   onblur="checkLen(this,150);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm('cxpdSqsh.do?method=save');">
										����ݸ�
									</button>
									<button type="button" onclick="saveForm('cxpdSqsh.do?method=saveAndSubmit');">
										�ύ����
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


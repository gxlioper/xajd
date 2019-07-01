<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				
				if (jQuery("#xh").val() == "" || jQuery("#xn").val() == "" 
					|| jQuery("#xmdm").val() == "" || jQuery("#jbdm").val() == ""
					|| jQuery("#xq").val() == "" ){
					showAlert("�뽫��������д������");
					return false;
				}
				
				ajaxSubFormWithFun("jdqkForm",url,function(data){
					showAlert(data["message"]);
					refershParent();
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/jddj_sqsh" method="post" styleId="jdqkForm">
			<html:hidden property="id" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ȼ��������</span>
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
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��Ŀ</th>
							<td>
								<html:select property="xmdm" styleId="xmdm">
									<html:option value=""></html:option>
									<html:options collection="xmdmList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th><span class="red">*</span>����</th>
							<td>
								<html:select property="jbdm" styleId="jbdm">
									<html:option value=""></html:option>
									<html:options collection="xmjbList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>֤����</th>
							<td colspan="3">
								<html:text property="zsbh" maxlength="20" styleId="zsbh"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm('jddj_sqsh.do?method=update');">
										��    ��
									</button>
									<button type="button" onclick="saveForm('jddj_sqsh.do?method=saveAndSubmit');">
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


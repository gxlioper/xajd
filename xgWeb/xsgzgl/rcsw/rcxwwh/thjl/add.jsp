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
			function saveForm(){
				var url = "rcsw_thjl.do?method=save";
				
				if (jQuery("#xh").val() == "" || jQuery("#xn").val() == "" 
					|| jQuery("#xq").val() == "" || jQuery("#thsj").val() == ""){
					showAlert("�뽫��������д������");
					return false;
				}
				
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"]);
					refershParent();
				});
			}
			
			jQuery(function(){
				jQuery.MultiUploader({
					maxcount : 3,
					//��׺
					accept : 'png|gif|jpg|jpeg|doc|docx|xls|xlsx|zip|rar',
					//����ļ���С��λM
					maxsize: 10,
					//��Ÿ������������id
					elementid : 'thid'
				});
			});

		</script>
	</head>
	<body>
		<html:form action="/rcsw_thjl" method="post" styleId="form">
			<html:hidden property="thid" styleId="thid" />
		
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
								<span≯����¼��Ϣ</span>
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
							<th><span class="red">*</span≯��ʱ��</th>
							<td >
								<html:text property="thsj" maxlength="20" styleId="thsj"
								onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"
								></html:text>
							</td>
							<th≯����ʦ</th>
							<td>
								${userNameReal }
								<input type="hidden" value="${userName }" name="thjs"/>
							</td>
						</tr>
						<tr>
							<th>
								̸������
								<br/><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='thnr' style="width:98%" styleId="thnr" rows='5' onblur="checkLen(this,500);"/>
							</td>
						</tr>
						<tr>
							<th>
								̸�����ݸ���
							</th>
							<td colspan="3">
								<%@includefile="/xsgzgl/comm/fileUpload/f.jsp"%>
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


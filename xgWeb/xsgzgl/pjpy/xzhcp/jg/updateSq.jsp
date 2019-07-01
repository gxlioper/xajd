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
		<script type="text/javascript">
			//�����
			function saveForm(type){
				if(jQuery.trim(jQuery("#grpc").val()) == ""){
					return showAlert("����������");
				}
				var url = "xzhcp_zcjg.do?method=save";
				ajaxSubFormWithFun("ZhcpJgForm", url, function(data) {
					 if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xzhcp_zcjg" method="post" styleId="ZhcpJgForm">
			<html:hidden property="sqid" styleId="sqid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:notEqual value="10364" name="xxdm">
								<th>ѧ��</th>
								<td>${xn}</td>
								<th>ѧ��</th>
								<td>${xqmc}</td>
							</logic:notEqual>
							<logic:equal value="10364" name="xxdm">
								<th>ѧ��</th>
								<td colspan="3">${xn}</td>
							</logic:equal>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="fjpath" styleId="fjpath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'fjpath'
											});
									});
								</script>						
							</td>
						</tr>
						<tr>
						<logic:notEqual value="10364" name="xxdm">
							<th><font class='red'>*</font>���˲���
								</br><font color="red">(�޲�����500�֣�������2000��)</font></th>
							<td colspan="3">
								<html:textarea property="grpc" styleId="grpc" 
								   onblur="checkLenBtwX('���˲���',this,500,2000);" 
								   style="width:99%;" rows="7">
								</html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="10364" name="xxdm">
								<th><font class='red'>*</font>���˲���
								</br><font color="red">(������200�֣�������2000��)</font></th>
							<td colspan="3">
								<html:textarea property="grpc" styleId="grpc" 
								   onblur="checkLenBtwX('���˲���',this,200,2000);" 
								   style="width:99%;" rows="7">
								</html:textarea>
							</td>
							</logic:equal>
						</tr>
					
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
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
										����
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
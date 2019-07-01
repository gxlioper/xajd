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
				if(!checkNotNull("hjmc-hjsj-fjdw")){
					return showAlert("�뽫��<font class='red'>*</font>�ı�������д������");
				}
				var url = "jxsq.do?method=save&type=" + type;
				ajaxSubFormWithFun("HjsqForm", url, function(data) {
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
		<html:form action="/jxsq" method="post" styleId="HjsqForm">
		<html:hidden property="id" styleId="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th>ѧ��</th>
							<td>${xn}</td>
							<th>ѧ��</th>
							<td>${xqmc}</td>
						</tr>
						<tr>
							<th><font class="red">*</font>��������</th>
							<td>
								<html:text property="hjmc" styleId="hjmc" maxlength="50"/>
							</td>
							<th><font class="red">*</font>������</th>
							<td>
								<html:text property="hjsj" styleId="hjsj" onclick="return showCalendar('hjsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>�佱��λ</th>
							<td colspan="3">
								<html:text property="fjdw" styleId="fjdw" style="width:70%" maxlength="50"/>
							</td>
						</tr>
					
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="fj" styleId="fj"/>
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
											elementid : 'fj'
											});
									});
								</script>						
							</td>
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
									<button type="button" onclick="saveForm('save');">
										����ݸ�
									</button>
									<button type="button" onclick="saveForm('submit');">
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
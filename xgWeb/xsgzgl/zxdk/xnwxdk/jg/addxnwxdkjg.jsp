<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/jg/js/xnwxdkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xnwxdk_jg" method="post" styleId="XnwxdkJgModel">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
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
								<span>�����˾����������ѧ�꣩</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${xn}
								<html:hidden property="xn" styleId="xn"/>
								
							</td>
							<th>ѧ��</th>
							<td>
							    ${xqmc}
								<html:hidden property="xq" styleId="xq"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��ͥ�ṩ��Ԫ��</th>
							<td>
								<html:text property="jttg" styleId="jttg" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>��ѧ��Ԫ��</th>
							<td>
								<html:text property="zxj" styleId="zxj" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��ѧ��Ԫ��</th>
							<td>
							    <html:text property="jxj" styleId="jxj" onkeyup="checkInput(this)" onblur="ismoney(this)"    maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>�ڹ���ѧ���루Ԫ��</th>
							<td>
								<html:text property="qgzxsr" styleId="qgzxsr" onkeyup="checkInput(this)" onblur="ismoney(this)"   maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>У����Ϣ��� ��Ԫ�� </th>
							<td>
							    <html:text property="xnwxjk" styleId="xnwxjk" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>�������루Ԫ�� </th>
							<td>
								<html:text property="qtsr" styleId="qtsr" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��ѧ�����Ԫ��</th>
							<td>
							    <html:text property="zxdksqje" styleId="zxdksqje" onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>��ѧ����ʱ�� </th>
							<td>
								<html:text property="zxdksqsj" styleId="zxdksqsj" onclick="return showCalendar('zxdksqsj','y-mm-dd');"  maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���Ž�Ԫ��</th>
							<td>
							    <html:text property="ffje" styleId="ffje" onkeyup="checkInput(this)" onblur="ismoney(this)" maxlength="10"></html:text>
							</td>
							<th><font color="red">*</font>����ʱ�� </th>
							<td>
								<html:text property="ffsj" styleId="ffsj" onclick="return showCalendar('ffsj','y-mm-dd');"  maxlength="10" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���������Ԫ��</th>
							<td>
							    <html:text property="sqje" styleId="sqje"  onkeyup="checkInput(this)" onblur="ismoney(this)"  maxlength="10"></html:text>
							    <html:hidden property="jesx" value="${jesx}" styleId="jesx"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��������
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onkeyup="checkzs();"
											   style="width:99%;" rows="5" ></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
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
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveDkjg('save');">
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
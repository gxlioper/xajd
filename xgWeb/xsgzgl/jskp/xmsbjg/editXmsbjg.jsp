<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/jskpXmjg" method="post" styleId="jskpXmsbjgForm">
			<html:hidden property="xmid" styleId="xmid"/>
			<html:hidden property="jgid" styleId="jgid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>�걨��Ŀ</th>
							<td>
								<input type="text" name="xmmc" id="xmmc" value="${xmsbjgModel.xmmc}" style="width:120px;"/>
								<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ����Ŀ',800,500,'jskpXmjg.do?method=showXmList');">ѡ��</button>
							</td>
							<th >
							<font color="red">*</font>����
							</th>
							<td colspan="3">
								<html:text style="width:100px" property="fs" styleId="fs" value="${xmsbjgModel.fs}" maxlength="10" onkeyup="checkInputNum(this)"
			                          ></html:text>
			                          		��������${rs.fs}~${rs.fs}
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��ʱ��</th>
							<td colspan="3">
								<html:text  property="hjsj" styleId="hjsj" value="${xmsbjgModel.hjsj}" onfocus="showCalendar('hjsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�걨����</th>
							<td colspan="3">
								<html:textarea property="sbly" styleId="sbly" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<html:hidden property="fjid" styleId="fjid" />
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
												elementid : 'fjid'
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
									<button type="button" onclick="saveXmsbjg('update');">
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
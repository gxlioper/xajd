<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsb/js/xmsb.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/jskpXmsb" method="post" styleId="jskpXmsbForm">
			<html:hidden property="xmid" styleId="xmid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">��Ŀ����</th>
							<td style="width:35%">
								${xmxx.xmmc}
							</td>
							<th style="width:15%">ָ����λ</th>
							<td style="width:35%">
								${xmxx.zddwmc}
							</td>
						</tr>	
						<tr>
							<th>��Ŀ����</th>
							<td>
								${xmxx.xmdlmc}
							</td>
							<th>��Ŀ���</th>
							<td>
								${xmxx.xmlbmc}
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td>
								${xmxx.fzrxm}
							</td>
							<th>��������ϵ��ʽ</th>
							<td>
								${xmxx.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td colspan="3">
								${xmxx.lxsj}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�걨��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">�걨��</th>
							<td width="30%">
								${userNameReal }
							</td>
							<th>ѧ��</th>
							<td>
								${userName }
							</td>
						</tr>
						<tr>
						<th><span class="red">*</span>��ʱ��</th>
							<td colspan="3">
								<html:text  property="hjsj" styleId="hjsj" onfocus="showCalendar('hjsj','y-mm-dd');" />
								<span style="color:red;margin-left:10px;">��ʾ������д֤���ϵĻ�ʱ��</span>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�걨����</th>
							<td colspan="3">
								<html:textarea property="sbly" styleId="sbly" 
											   onkeypress="checkLen(this,500);" onblur="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>������Ϣ
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
									<button type="button" onclick="saveXmsb();">
										�걨
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
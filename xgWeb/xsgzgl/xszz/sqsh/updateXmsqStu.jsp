<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//��������ѡ��
				loadMkxxSelectOptions();
				//����radioѡ��
				loadMkxxRadioOptions();
			});
			//��֤ѧ���������Ƿ񳬹��������
			function checkJesxSh(obj){
				var ylzd1= jQuery(obj).val();
				var jesx = jQuery("#jesx").val();
				if(parseFloat(ylzd1)>parseFloat(jesx)){
					showAlertDivLayer("���������Ŀ������ޣ�");
					jQuery(obj).val('');
				}
			}
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<html:hidden property="xmdm"/>
			<html:hidden property="xn"/>
			<html:hidden property="xq"/>
			<input type="hidden" name="jesx" id="jesx" value="${mkxxForm.je}"/>
		
			<div style='tab;width:100%;height:345px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="4">
								<span>
									������Ŀ������Ϣ
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>${mkxxForm.xn }</td>
							<th>ѧ��</th>
							<td>${mkxxForm.xqmc }</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>${mkxxForm.xmmc }</td>
							<th>���</th>
							<td>${mkxxForm.je }</td>
						</tr>
						<logic:equal value="1" name="xmwhForm" property="jesfxssq">
						<tr>
							<th>������</th>
							<td colspan="3">
								<html:text onkeyup="checkInputData(this);checkJesxSh(this)" maxlength="7" property="ylzd1" styleId="ylzd1" style="width:100px"></html:text>
								<font id="message"  color="blue">���޽�${mkxxForm.je }</font>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<logic:equal value="10335" name="xxdm">
									<span style="color: blue; line-height:20px;display:block;">
										����ѧԺ(԰)֪ͨҪ������ȷ�����ϴ�������Ҫ���ϴ������޸���Ҫ���������ϴ���
									</span>
									&nbsp;
								</logic:equal>
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:notEqual value="11799" name="xxdm">
								<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf|mp4',
													//����ļ���С ��λM
													maxsize: 300,
													//��Ÿ������������id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveSqxg();">
										�� ��
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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


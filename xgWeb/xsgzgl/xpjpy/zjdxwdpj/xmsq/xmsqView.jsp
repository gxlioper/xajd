<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/xpjpy_xmsq" method="post" styleId="xmsqForm">
			<html:hidden property="xh" />
			<html:hidden property="xmdm" />
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���뽱��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<bean:write property="xn" name="xmsqForm"/>
							</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>${xmwhModel.xmmc}</td>
							<th>��Ŀ���</th>
							<td>${xmwhModel.xmje}</td>
						</tr>
						<tr>
							<th>����ˮƽ</th>
							<td>${mkxxForm.wysp}</td>
							<th>����绰</th>
							<td>${mkxxForm.ssdh}</td>
						</tr>
						<tr>
							<th>������Ṥ��ְ��</th>
							<td colspan="3">${mkxxForm.gzzw}</td>
						</tr>
						<tr>
							<th>����ѧϰ����</th>
							<td colspan="3">${mkxxForm.grxxjl}</td>
						</tr>
						<tr>
							<th>�μӿ������</th>
							<td colspan="3">${mkxxForm.cjkyqk}</td>
						</tr>
						<tr>
							<th>���轱��λ����ʶ</th>
							<td colspan="3">${mkxxForm.dwrs}</td>
						</tr>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fjxx" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
										});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write property="sqly" name="xmsqForm" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" id="checkTd">
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='��������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='����������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>����������</font>
								</logic:empty>								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">���</th>
										<th style="text-align: center;">��˸�λ</th>
										<th style="text-align: center;">�����</th>
										<th style="text-align: center;">��˽��</th>
										<th style="text-align: center;">���ʱ��</th>
										<th style="text-align: center;" width="20%">������</th>
										<th style="text-align: center;">���/��������</th>
									</tr>
									<logic:present name="spjlList">
										<logic:iterate id="s" name="spjlList" indexId="i">
											<tr>
												<td>${i+1 }</td>
												<td>${s.gwmc }</td>
												<td>${s.xm }</td>
												<td>${s.shztmc }</td>
												<td>${s.shsj }</td>
												<td>${s.shyj }</td>
												<td>${s.xmmc }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="spjlList">
											<tr>
												<td colspan="7">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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
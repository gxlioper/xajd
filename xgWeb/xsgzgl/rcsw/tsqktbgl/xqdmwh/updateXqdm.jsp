<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/tsqktbgl/xqdmwh/js/xqdm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	<body >
		<html:form action="/xqdmwh" method="post" styleId="xqdmForm" onsubmit="return false;">
		   <input type="hidden" name="oldxqdm" id="oldxqdm" value="${oldxqdm}">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�޸�ѧ����� ά��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ����� 
							</th>
							<td width="34%">
								<html:text property="xqdm" styleId="xqdm" maxlength="10" styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ������
							</th>
							<td width="34%">
								<html:text property="xqmc" styleId="xqmc" maxlength="10" styleClass="text_nor" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="updSaveForm();">
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


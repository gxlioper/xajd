<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/bysdzbwh/js/bysdzbwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body >
		<html:form action="/dtjs_bysdzbwh" method="post" styleId="bysdzbwhForm" onsubmit="return false;">
			<div class="style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���ӵ�֧��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>��֧������
							</th>
							<td width="34%">
								<html:text property="dzbdm" styleId="dzbdm" maxlength="10" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��֧������
							</th>
							<td width="34%">
								<html:text property="dzbmc" styleId="dzbmc" maxlength="32" styleClass="text_nor" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('dtjs_bysdzbwh.do?method=dzbwhAdd&type=save');return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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


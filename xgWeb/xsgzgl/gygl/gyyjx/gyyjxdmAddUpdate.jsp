<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
	</head>
	<body>
		<html:form action="/gygl_gyyjxdmwh" method="post" styleId="gyyjxForm">
			
			<html:hidden property="yjfldm"/>
			<div style=''>
				<table width="98%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >
								<font color="red">* </font> ������ࣺ
							</th>
							<td>
								<html:text property="yjflmc" styleId="yjflmc"  maxlength="25" onkeypress="if(pressEnter(event)){return false;}"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEmpty name="actionType" >
										<logic:equal value="add" name="actionType">
											<button id="save_button" type="button"onclick="submitAction_dmwh('add');">����</button>
										</logic:equal>
										<logic:equal value="update" name="actionType">
											<button id="save_button" type="button"onclick="submitAction_dmwh('update');">����</button>
										</logic:equal>
									</logic:notEmpty>
									<button type="button" name="�� ��" onclick="iFClose();">
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


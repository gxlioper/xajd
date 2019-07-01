<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsz">
			<div>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="40%">
								<span class="red">*</span>�������Դ
							</th>
							<td>
							<html:select property="jxsm" styleId="lyzq"	>
								<html:options collection="sqzqList" property="dm" labelProperty="mc" />
							</html:select>
							</td>
						</tr>
						<tr>
							<th width="">
								�����Ŀ��
							</th>
							<td>
								${data.sqxn}  ${xqmc}
								<input type="hidden" id="sqzq" value="${data.sqxn},${data.sqxq}"/>
							</td>
						</tr>
				</table>
			</div>
			<div>
				<table width="" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="savecopy();return false;"
										id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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

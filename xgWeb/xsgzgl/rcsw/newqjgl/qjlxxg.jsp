<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/js/qjlx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
	jQuery(function() {
	});
</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/qjlx">
		<div>
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<td>
							<font color="red">* </font>���ƣ�
						</td>
						<td>
							<html:hidden property="qjlxid" value="${data.qjlxid}" styleId="qjlxid" />
							<html:text property="qjlxmc" styleId="qjlxmc" value="${data.qjlxmc}" maxlength="10"></html:text>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save('qjlx.do?method=update&type=save','qjlxmc');return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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

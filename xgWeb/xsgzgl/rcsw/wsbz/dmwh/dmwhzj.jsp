<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/dmwh/js/dmwh.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/wsbz_dmwh" method="post"
			styleId="WsbzDmwhForm" onsubmit="return false;">

			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>С�ֶ�����
							</th>
							<td>
								<html:text property="fdmc" styleId="fdmc" maxlength="50"
									styleClass="text_nor"  />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�Ƶ��
							</th>
							<td>
								<html:radio styleId="hdpl"  property="hdpl" value="1">��</html:radio>
								<html:radio  property="hdpl" value="2">��(����ĩ)</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ලʱ��
							</th>
							<td>
								<html:text styleId="sj" property="sj"  maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ල�ص�
							</th>
							<td>
								<html:text property="dd" styleId="dd" maxlength="50"
									styleClass="text_nor"  />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����
							</th>
							<td>
								<html:text property="rs" styleId="rs" maxlength="8"
									styleClass="text_nor" onkeyup="checkInput(this)" />
							</td>
						</tr>
						<tr>
							<th>
								����ְ��
							</th>
							<td>
							    <html:textarea property="gzzz" styleId="gzzz" style="width:99%" rows="3"/>
							</td>
						</tr>
						<tr>
							<th>
								����Ҫ��
							</th>
							<td>
								<html:textarea property="fwyq" styleId="fwyq" style="width:99%" rows="3" />
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <div style="height:30px"></div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveFormjldm('add');return false;">
										�� ��
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
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


<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="hidden" name="czlx" value="�ظ�" />
			<input type="hidden" name="xh" value="${rs.xh }" />
			<input type="hidden" name="dwmc" value="${jyweb_realName }" />
			<input type="hidden" name="gwmc" value="${rs.gwmc }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ϣ�ظ�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="�ύ"
										onclick="saveRever();">
										�� ��
									</button>
									<button name="����" type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>�ظ�����
							</th>
							<td>
								<html:textarea property="hfxx" style="width:95%" rows="12" onblur="checkLen(this,800)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
 			alert("${message}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 			
 		</script>
		</logic:present>

	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<script type="text/javascript" src="js/check.js"></script>
		<html:form action="/jyweb" method="post" enctype="multipart/form-data">

			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="file" value="${rs.tpdz }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ר��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button onclick="saveUpdate('jywebUseCheckSession.do?method=fileUpdate&doType=save','wjsm');">
										����
									</button>
									<button onclick="window.close();return false;">�ر�</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>�ļ�
							</th>
							<td width="80%">
								<html:file property="file" style="width:85%" />
								<span class="red">(��10M)</span>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ļ�˵��
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td>
								<html:textarea property="wjsm" style="width:95%" rows="8"
									onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
 			alert("${message}${errMsg}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 		</script>
		</logic:present>

	</body>
</html>

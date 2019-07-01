<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>

	<body>
		<html:form action="/tsbj" method="post">
			
			<input type="hidden" name="pkValue" value="${rs.pkValue }"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="reset" onclick="saveUpdate('tsbj.do?method=tsbjUpdate&doType=save','tsbjdm-tsbjmc')">
										����
									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="2">
								<span>
									��ɫ�༶ά��
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��ɫ�༶����
							</th>
							<td>
								<html:text property="save_tsbjdm" maxlength="10" 
										   styleId="tsbjdm" value="${rs.tsbjdm }" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ɫ�༶����
							</th>
							<td>
								<html:text property="save_tsbjmc" maxlength="25" styleId="tsbjmc" value="${rs.tsbjmc }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ɫ�༶˵��
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td style="word-break:break-all;">
								<html:textarea property="save_tsbjsm" onblur="checkLen(this,200)" 
											   style="width:90%" rows="5" value="${rs.tsbjsm }"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>
</html>

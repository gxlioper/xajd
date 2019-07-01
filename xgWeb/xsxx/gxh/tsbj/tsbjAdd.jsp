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
			
			<html:hidden property="save_cjr" value="${userName }"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveUpdate('tsbj.do?method=tsbjAdd&doType=save','tsbjdm-tsbjmc')">
										保存
									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="2">
								<span>
									特色班级维护
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>特色班级代码
							</th>
							<td>
								<html:text property="save_tsbjdm" maxlength="10" styleId="tsbjdm"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>特色班级名称
							</th>
							<td>
								<html:text property="save_tsbjmc" maxlength="25" styleId="tsbjmc"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								特色班级说明
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<html:textarea property="save_tsbjsm" onblur="checkLen(this,200)" style="width:90%" rows="5"></html:textarea>
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

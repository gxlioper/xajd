<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript"
			src="xsgzgl/rcsw/newqjgl/qjgz/js/qjgz.js"></script>
		<script type="text/javascript">
	jQuery(function() {
		jQuery("#div_help").hide();
	});
</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjgz">
		<div id="div_help" class="prompt">
		<p>
			<span id="lable"></span>
		</p>
			<a class="close" onclick="this.parentNode.style.display='none';" title="����"></a>
		</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="100%">
								����ѧԺ
							</th>
							<td>
								${ssxymc}
								<html:hidden property="ssxydm" styleId = "ssxydm" value="${ssxydm}"/>
							</td>
						</tr>
						<tr>
							<th width="100%">
								<span class="red">*</span>�������(��)
							</th>
							<td>
								<html:text property="kssj" styleId="kssj" style="width:35%" maxlength="4" onkeyup="autoShowLable();"></html:text>
								<span style="width: 25%;">&nbsp;&nbsp;~&nbsp;&nbsp;</span>
								<html:text property="jssj" styleId="jssj" style="width:35%" maxlength="4" onkeyup="autoShowLable();"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td>
								<html:select property="qjlxid" styleId="qjlxid" style="width:99%">
									<html:option value=""></html:option>
									<html:options collection="qjlxList" property="qjlxid" labelProperty="qjlxmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�������
							</th>
							<td>
								<html:select property="splcid" styleId="splcid" disabled="false"
									style="width:280px;">
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
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
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('qjgz.do?method=add&type=save','kssj-jssj-splcid');return false;"
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

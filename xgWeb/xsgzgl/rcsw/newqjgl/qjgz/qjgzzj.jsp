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
			<a class="close" onclick="this.parentNode.style.display='none';" title="隐藏"></a>
		</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="100%">
								所属学院
							</th>
							<td>
								${ssxymc}
								<html:hidden property="ssxydm" styleId = "ssxydm" value="${ssxydm}"/>
							</td>
						</tr>
						<tr>
							<th width="100%">
								<span class="red">*</span>请假区间(天)
							</th>
							<td>
								<html:text property="kssj" styleId="kssj" style="width:35%" maxlength="4" onkeyup="autoShowLable();"></html:text>
								<span style="width: 25%;">&nbsp;&nbsp;~&nbsp;&nbsp;</span>
								<html:text property="jssj" styleId="jssj" style="width:35%" maxlength="4" onkeyup="autoShowLable();"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								请假类型
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
								<span class="red">*</span>审核流程
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
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('qjgz.do?method=add&type=save','kssj-jssj-splcid');return false;"
										id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
										关 闭
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

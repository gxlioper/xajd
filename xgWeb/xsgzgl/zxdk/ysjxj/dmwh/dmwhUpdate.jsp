<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ysjxj/dmwh/js/dmwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body >
		<html:form action="/ysjxj_dmwh" method="post" styleId="dmwhForm" onsubmit="return false;">
		   <html:hidden property="zjlydm"  styleId="zjlydm"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改资金来源名称</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<tr>
								<th width="22%">
									<font color="red">*</font>资金来源名称
								</th>
								<td width="34%">
									<html:text property="zjlymc" styleId="zjlymc" maxlength="10" styleClass="text_nor" />
								</td>
							</tr>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="updSaveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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


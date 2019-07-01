<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/fyff/dmwh/fftj/js/fyfftj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body >
		<html:form action="/rcsw_fyff_fftj" method="post" styleId="FyfftjForm" onsubmit="return false;">
		   <html:hidden property="fftjdm"  styleId="fftjdm"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改发放途径</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<tr>
								<th width="16%">
									<font color="red">*</font>发放途径
								</th>
								<td width="34%">
									<html:text property="fftj" styleId="fftj" maxlength="10" styleClass="text_nor" />
								</td>
							</tr>
							<tr>
								<th width="16%">
									发放账号
								</th>
								<td width="34%">
									<html:radio  property="ffzh" styleId="ffzh0" value="0" ><label for="ffzh0" style="cursor:pointer">需填写</label></html:radio>
									<html:radio  property="ffzh" styleId="ffzh1" value="1" ><label for="ffzh1" style="cursor:pointer">无需填写</label></html:radio>
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


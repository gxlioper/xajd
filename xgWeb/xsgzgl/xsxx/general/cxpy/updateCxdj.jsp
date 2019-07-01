<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/general/cxpy/js/cxdj.js"></script>
	</head>
	<body >
		<html:form action="/xsxx_gygl_cxcxdj" styleId="cxdjForm" method="post"  onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改操行等级</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>等级代码
							</th>
							<td width="34%" >
								<bean:write name="cxdjForm" property="cxdjdm" />
								<input type="hidden"　id="cxdjdm" name="cxdjdm" value="<bean:write name="cxdjForm" property="cxdjdm" />"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>等级名称
							</th>
							<td width="34%" >
								<html:text property="cxdjmc" styleId="cxdjmc"  style="width:98%" maxlength="15"  />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveUpdate();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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


<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsdc/js/updateKnsdc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body >
		<html:form action="/xszz_knsdc" method="post" styleId="knsdcForm" onsubmit="return false;">
		   <input type="hidden" id="oldDcdm" value="${oldDcdm}"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改档次</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
					 	<tr>
							<th width="16%">
								<font color="red">*</font>档次代码
							</th>
							<td width="34%" >
								<html:text property="dcdm" styleId="dcdm" maxlength="2" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>档次名称
							</th>
							<td width="34%">
								<html:text property="dcmc" styleId="dcmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								项目说明
								<br /><font color="red">(限制在500字内)</font>
							</th>
							<td width="34%" style="word-break:break-all;">
								<html:textarea property="xmsm" styleId="xmsm" style="width:98%" rows="6" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
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


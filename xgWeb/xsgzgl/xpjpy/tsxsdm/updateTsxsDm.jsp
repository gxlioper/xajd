<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/tsxsdm/js/tsxsdm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body >
		<html:form action="/xpj_tsxsdm" method="post" styleId="TsxsDmForm" onsubmit="return false;">
		<html:hidden property="lxdm"  styleId="lxdm"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改类型</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>类型名称
							</th>
							<td width="34%" >
								<html:text property="lxmc" styleId="lxmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>类型属性
							</th>
							<td width="34%">
								<html:select property="lxsx" styleId="lxsx" style="width:100px">
									<html:option value="1">条件</html:option>
									<html:option value="2">范围</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								类型说明
								<br /><font color="red">(限制在200字内)</font>
							</th>
							<td width="34%" height="100">
								<html:textarea property="lxsm" styleId="lxsm"  style="width:98%" rows="4"   onblur="checkLen(this,200);"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="delsaveForm();return false;">
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


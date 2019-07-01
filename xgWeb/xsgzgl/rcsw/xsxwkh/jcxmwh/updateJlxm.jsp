<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jcxmwh/js/jcxmwh.js"></script>
		<script language="javascript">
		</script>
	</head>
	<body >
		<html:form action="/xsxwkhJcxmwh" method="post" styleId="xsxwJcxmwhForm" onsubmit="return false;">
		<html:hidden property="lx" styleId="lx" value="1"/>
		<html:hidden property="dm" styleId="dm"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
								<th>
									<span class="red">*</span>项目名称
								</th>
								<td>
									<html:text property="mc" styleId="mc" maxlength="100" styleClass="text_nor" style="width:250px"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>分值
								</th>
								<td>
									<html:text property="fz" styleId="fz" maxlength="5" styleClass="text_nor" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"/>&nbsp;分
								</td>
							</tr>
							<tr><th width="20%">备注
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
						</tbody>
						</table>
						</div>
						<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveUpdateForm();return false;">
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
		</html:form>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/xmwh.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/zhf_xmmkwh" styleId="myForm" method="post">
		<html:hidden property="xmmkdm" styleId="xmmkdm"></html:hidden>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目模块维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								项目模块代码
							</th>
							<td style="width:70%" >
								${rs.xmmkdm}
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								项目模块名称
							</th>
							<td style="width:70%" >
								<html:text property="xmmkmc" styleId="xmmkmc" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								总分
							</th>
							<td style="width:70%" >
								<html:text property="xf" styleId="xf" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								合格分
							</th>
							<td style="width:70%" >
								<html:text property="hgf" maxlength="2" styleId="hgf" onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<font color="red">*</font>"为必填项
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="saveXmmk('update')">
										保 存
									</button>
									<button class="button2" type="button" onclick="Close()">
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhfz.js"></script>
	</head>
	<body>
		<html:form action="/xpj_xmwh" method="post" styleId="form1">
		<html:hidden property="xmdm" styleId="xmdm" />
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>资助项目复制</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th><font color="red">*</font>复制来源年度</th>
							<td>
								<select id="xmfznd" name="xmfznd" style="width:155px"></select>
							</td>
						</tr>
						<tr>
							<th>复制目标年度</th>
							<td>
								${currXnXqmc }
							</td>
						</tr>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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


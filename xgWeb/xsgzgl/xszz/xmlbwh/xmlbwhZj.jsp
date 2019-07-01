<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmlbwh/js/xmlbwhZj.js"></script>
	</head>
	<body >
		<html:form action="/xszz_xmlbwh" method="post" styleId="form1" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>项目类别代码信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="30%">
								<font color="red">*</font>名称
							</th>
							<td width="70%" >
								<html:text property="lbmc" styleId="lbmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								项目说明<br/>
								<font color="red">(限制在200字内)</font>
							</th>
							<td>
								<html:textarea property="lbsm" styleId="lbsm"  style="width:98%"  rows="5"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
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


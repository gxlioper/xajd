<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>

	</head>
	<body>

	<html:form method="post" styleId="scbzpfmmForm" action="/khglKhpf">
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th width="40%">班组评分用户名</th>
						<th width="60%">班组评分密码</th>
					</tr>
				</thead>
				<tbody>
				<logic:notEmpty name="bzpfmmList">
					<logic:iterate id="bzpfmm" name="bzpfmmList">
						<tr>
							<td>
									${bzpfmm.username}
								<input type="hidden" class="username" name="username" value="${bzpfmm.username}"/>
							</td>
							<td>
								<input type="text" class="password" name="password" maxlength="20" value="${bzpfmm.password}" onkeyup=""/>
							</td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="bzpfmmList">
					<tr>
						<td colspan="2" align="center">暂无班级</td>
					</tr>
				</logic:empty>

				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
				<tr>
					<td colspan="4">
						<div class="bz">
							"<span class="red">*</span>"为必填项
						</div>
						<div class="btn">
							<logic:notEmpty name="bzpfmmList">
								<button type="button"  onclick="scbzpfmmSave();return false;" id="buttonSave">
									保 存
								</button>
							</logic:notEmpty>
							<button type="button"  onclick="iFClose();" id="buttonClose">
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		<script language="javascript" src="xsgzgl/znxgl/wdznx/js/wdznx.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
			
		</script>
		</head>
		<%--学生写信页面  --by yxy--%> 
	<body>
		<html:form action="/wdznx"  method="post" styleId="WdznxForm">
		<div class="tab" style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>主题类别
						</th>
						<td colspan="3">
							<html:select property="ztlb" styleId="ztlb">
								<html:options property="dm" labelProperty="mc"
										collection="ztlblist" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>收件人
						</th>
						<td colspan="3">
							系统管理员
							<html:hidden property="jsrbh" value="系统管理员" />
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>信件主题</th>
						<td colspan="3">
							<html:text property="xjzt" maxlength="50" styleId="xjzt"
									style="width:650px;"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>发送内容<br/><font color="red">(限1000之内)</font>
						</th>
						<td colspan="3">
							<textarea id="editorid" name="editorid" style="width:700px;height:280px;">
							</textarea>
					    </td>
					</tr>
				</tbody>
			</table>
		</div>
		<table width="100%" border="0" class="formlist" style=" margin-bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="发送  " onclick="saveXsXXForm();return false;">
									发送 
								</button>
								<button type="button" name="关闭" onclick="iFClose();">
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
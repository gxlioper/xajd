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
		<script language="javascript" src="xsgzgl/rcsw/newqjgl/qjxysz/js/qjxysz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			jQuery(function(){
		    })
		</script>
		</head>
	<body>
		<html:form action="/qjxysz"  method="post" styleId="QjXySzForm">
		<input type="hidden" name="id" id="id" value="${data.id}"/>
		<div class="tab" style="width:100%;height:400px;overflow-x:hidden;overflow-y:hidden;">
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>Çë¼Ù³ÐÅµÊé</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th width="10%">
							<font color="red">*</font>±à¼­ÄÚÈÝ
						</th>
						<td colspan="3">
							<textarea id="editorid" name="editorid" style="width:700px;height:350px;">
							  ${data.content}
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
								<button type="button" name="±£´æ " onclick="save_xyszData();return false;">
									±£´æ
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>
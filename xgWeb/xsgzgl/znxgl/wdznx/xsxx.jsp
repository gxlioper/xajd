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
		<%--ѧ��д��ҳ��  --by yxy--%> 
	<body>
		<html:form action="/wdznx"  method="post" styleId="WdznxForm">
		<div class="tab" style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>�������
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
							<font color="red">*</font>�ռ���
						</th>
						<td colspan="3">
							ϵͳ����Ա
							<html:hidden property="jsrbh" value="ϵͳ����Ա" />
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>�ż�����</th>
						<td colspan="3">
							<html:text property="xjzt" maxlength="50" styleId="xjzt"
									style="width:650px;"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��������<br/><font color="red">(��1000֮��)</font>
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
								<button type="button" name="����  " onclick="saveXsXXForm();return false;">
									���� 
								</button>
								<button type="button" name="�ر�" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>
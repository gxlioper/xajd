<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
		function submitXg(){
			editor.sync();
			if(document.getElementById("title").value.replace(/(\s*)/g, "") == ""){
				alertInfo("����д���ű��⣡");
				document.getElementById("newsTitle").focus();
				return false;
			}
			var html=editor.html();
			if(html==null||html==""){
				alertInfo("����д�������ģ�");
				return false;
			}
			//�ύ���
			showConfirmDivLayer("��ȷ��Ҫ���棿",{"okFun" : function(){
				var url = "jjgl_tzgggl.do?method=submitXg";
				ajaxSubFormWithFun("jjglTzggForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
		</script>
		</head>

	<body>
		<html:form action="/jjgl_tzgggl" method="post" styleId="jjglTzggForm">
		<html:hidden property="sid"/>
		<html:hidden property="zgh"/>
		<div class="tab" style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>֪ͨ����</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>���ű���
						</th>
						<td colspan="3">
							<html:text property="title" styleId="title" style="width:100%" maxlength="100"  onkeypress="if(pressEnter(event)){return false;}"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>�༭����
						</th>
						<td colspan="3">
						<html:textarea property="contents" styleId="editorid"  style="width:700px;height:280px;" ></html:textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">
							�Ƿ񷢲�
						</th>
						<td width="35%">
							<html:radio property="sffb" value="1"></html:radio>&nbsp;��
							<html:radio property="sffb" value="0"></html:radio>&nbsp;��
						</td>
						<th  width="15%">
							�Ƿ��ö�
						</th>
						<td  width="35%">
							<html:radio property="priority" value="1"></html:radio>&nbsp;��
							<html:radio property="priority" value="0"></html:radio>&nbsp;��
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
								<button type="button" name="���� " onclick="submitXg();return false;">
									�� ��
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
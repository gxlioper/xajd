<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/Basic.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript" src="comm/editor/kindeditor.js"></script>
	<script language="javascript" src="comm/editor/zh_CN.js"></script>
	<script language="javascript" src="comm/editor/editor.js"></script>
	<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
	<script type="text/javascript">
		function submitXz(){
			editor.sync();
			if(document.getElementById("gzmc").value.replace(/(\s*)/g, "") == ""){
				alertInfo("请填写家教规则名称！");
				document.getElementById("gzmc").focus();
				return false;
			}
			var html=editor.html();
			if(html==null||html==""){
				alertInfo("请填写家教规则名称！");
				return false;
			}
			//提交审核
			showConfirmDivLayer("您确定要提交？",{"okFun" : function(){
				var url = "jjgl_jjgzgl.do?method=submit";
				ajaxSubFormWithFun("jjglJJgzForm",url,function(data){
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
		<html:form action="/jjgl_jjgzgl" method="post" styleId="jjglJJgzForm">
			<div class='tab' style='tab;width:100%;height:350px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>家教案例详情</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th  width="16%"><span class="red">*</span>家教规则名称</th>
					    	<td colspan="3">
					    		<input type="text" name="gzmc" id="gzmc" maxlength="100" style="width: 100%"/>
					    	</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>家教规则内容</th>
					    	<td colspan="3">
					    		<textarea rows="10" style="width:95%;" name="gznr" id="editorid"></textarea>
					    	</td>
					    </tr>
					    <tr>
						<th>
							是否直接发布
						</th>
						<td>
							<input type="radio" name="sffb" value="1" checked="true"/>&nbsp;是
							<input type="radio" name="sffb" value="0"/>&nbsp;否
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="submitXz();">
										保存
									</button>
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
										关闭
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

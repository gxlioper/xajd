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
		function submitXg(){
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
				var url = "jjgl_jjgzgl.do?method=submitXg";
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
			<html:hidden property="sid"/>
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
					    		<html:text property="gzmc" styleId="gzmc" maxlength="100" style="width: 100%"></html:text>
					    	</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>家教规则内容</th>
					    	<td colspan="3">
					    		<html:textarea property="gznr" styleId="editorid" rows="10" style="width:95%;"></html:textarea>
					    	</td>
					    </tr>
					     <tr>
					    	<th width="15%">
							是否发布
							</th>
							<td width="35%">
								<html:radio property="sffb" value="1"></html:radio>&nbsp;是
								<html:radio property="sffb" value="0"></html:radio>&nbsp;否
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
									<button type="button" onclick="submitXg();">
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

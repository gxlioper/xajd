<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<% response.addHeader("X-XSS-Protection","0"); %>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				if (jQuery("#message").val() != ""){
					showAlert(jQuery("#message").val(),{},{"clkFun":function(){
						if (parent.window){
							var api = frameElement.api,W = api.opener;
							jQuery(W.document).find('#search_go').click();
							
							if (jQuery("#result").val() == "true"){
								api.close();
							}
						}
					}});
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/xpjpy_zcwh" method="post" styleId="zcwhForm" enctype="multipart/form-data" >
			<input type="hidden" id="message" name="message" value="${message}"/>
			<input type="hidden" id="result" name="result" value="${result}"/>
			<input type="hidden" id="jsonStr" name="jsonStr" value='${jsonStr}'/>
			<input type="hidden" value='${zcxmdmForTop}' id='zcxmdmForTop' name ='zcxmdmForTop'/>
			<html:hidden property="id" styleId="id"/>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="12%">说明</th>
							<td>
								①  综测分导入请使用系统提供的模版;<br/><br/>
								②  请严格按照模版提供的格式填写综测分;<br/><br/>
								③ 各项综测<u><font color="red">分数不能为空</font></u>;<br/><br/>
								④ 分数必须为<u><font color="red">数字格式（且最大长度为10位）</font></u>;<br/><br/>
								⑤ 使用者请勿修改导入模版（<font color="red">修改模版可能导致无法导入或导入的分数不准确！</font>）<br/><br/>
								
								<span class="bold">
									<a href="javascript:downloadTemplate();" style="color:blue;">下载模版</a>
								</span>
							</td>
						</tr>
						
						<tr>
							<th>导入</th>
							<td>
								<input type="file" name="importFile" id="importFile" style="width:80%"/>
								<label class="btn_01" onclick="uploadZcfs();">导入</label>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
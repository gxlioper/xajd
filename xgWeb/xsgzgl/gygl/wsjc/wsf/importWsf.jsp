<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<% response.addHeader("X-XSS-Protection","0"); %>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
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
			
			function addSuperSearchParams(url,map){
				
				if(url.indexOf("?") > -1){
					url += "&";
				}else{
					url += "?";
				}
				url += "path=" + map.path + "&mhcx_lx=" + map.mhcx_lx;
				jQuery("input[name=searchLx],input[name=searchTj],input[name=searchTjz],input[name=input_mhcx]").remove();
				
				var html = "<input type = 'hidden' name='searchLx' value='"+map.searchLx+"'>";
				html += "<input type = 'hidden' name='searchTj' value='"+map.searchTj+"'>";
				html += "<input type = 'hidden' name='searchTjz' value='"+map.searchTjz+"'>";
				if (map.input_mhcx){
					html += "<input type = 'hidden' name='input_mhcx' value='"+map.input_mhcx+"'>";
				}
				jQuery("#wsfForm").append(html);
				return url;
			}
			
			function downloadTemplate(){
				var rcid = jQuery("#rcid").val();
				var jcdx = jQuery("#jcdx").val();
				var map = JSON.parse('${jsonStr }');
				var url = addSuperSearchParams("wsjcWsflr.do?method=downloadTemplate&rcid="+rcid+"&jcdx="+jcdx,map);
				jQuery("#wsfForm").attr("target","_blank");
				jQuery("#wsfForm").attr("action",url);
				jQuery("#wsfForm").submit();
			}
			
			function uploadWsf(){
				var file = jQuery("#importFile").val();
				
				if (file == "")
					return false;
				
				if (file.substring(file.length-4,file.length) != ".xls"){
					showAlert("导入文件只能为.xls格式,请确认！");
					return false;
				}
				
				var url = "wsjcWsflr.do?method=saveImportWsf";
				jQuery("#wsfForm").attr("target","");
				jQuery("#wsfForm").attr("action",url).submit();
			}
			
		</script>
	</head>
	<body>
		<html:form action="/wsjcWsflr" method="post" styleId="wsfForm" enctype="multipart/form-data" >
			<html:hidden property="rcid" styleId="rcid"/>
			<html:hidden property="jcdx" styleId="jcdx"/>
			<html:hidden property="fslx" styleId="fslx"/>
			<input type="hidden" name="message" id="message" value="${message}">
			<input type="hidden" name="result" id="result" value="${result}">
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
								①  卫生分导入请使用系统提供的模版;<br/><br/>
								②  请严格按照模版提供的格式填写卫生分/等级/星级 ;<br/><br/>
								③ 各项<u><font color="red">分数/等级/星级不能为空</font></u>;<br/><br/>
								④ 分数必须为<u><font color="red">数字格式（且最大长度为10位）</font></u>;<br/><br/>
								⑤ 等级/星级必须为<u><font color="red">限定选项内容</font></u>;<br/><br/>
								⑥ 使用者请勿修改导入模版（<font color="red">修改模版可能导致无法导入或导入的结果不准确！</font>）<br/><br/>
								<span class="bold">
									<a href="javascript:downloadTemplate();" style="color:blue;">下载模版</a>
								</span>
							</td>
						</tr>
						
						<tr>
							<th>导入</th>
							<td>
								<input type="file" name="importFile" id="importFile" style="width:80%"/>
								<label class="btn_01" onclick="uploadWsf();">导入</label>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>


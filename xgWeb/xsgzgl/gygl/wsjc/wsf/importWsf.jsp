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
					showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
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
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="12%">˵��</th>
							<td>
								��  �����ֵ�����ʹ��ϵͳ�ṩ��ģ��;<br/><br/>
								��  ���ϸ���ģ���ṩ�ĸ�ʽ��д������/�ȼ�/�Ǽ� ;<br/><br/>
								�� ����<u><font color="red">����/�ȼ�/�Ǽ�����Ϊ��</font></u>;<br/><br/>
								�� ��������Ϊ<u><font color="red">���ָ�ʽ������󳤶�Ϊ10λ��</font></u>;<br/><br/>
								�� �ȼ�/�Ǽ�����Ϊ<u><font color="red">�޶�ѡ������</font></u>;<br/><br/>
								�� ʹ���������޸ĵ���ģ�棨<font color="red">�޸�ģ����ܵ����޷��������Ľ����׼ȷ��</font>��<br/><br/>
								<span class="bold">
									<a href="javascript:downloadTemplate();" style="color:blue;">����ģ��</a>
								</span>
							</td>
						</tr>
						
						<tr>
							<th>����</th>
							<td>
								<input type="file" name="importFile" id="importFile" style="width:80%"/>
								<label class="btn_01" onclick="uploadWsf();">����</label>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>


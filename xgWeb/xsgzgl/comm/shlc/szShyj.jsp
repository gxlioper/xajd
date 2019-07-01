<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
		<script type="text/javascript">
			function saveShyj(){
				
				var input = jQuery("input[name=shyj]");
				var textFlg = true;
				jQuery.each(input,function(i,e){
					var textVal = jQuery(e).val();
					if (textVal != null && textVal != ''){
						textFlg = false;
						return;
					}
				});
				if (textFlg){
					showAlert("请至少填写一项审核意见！");
					return false;
				}
				
				var url = "comm_spl.do?method=saveCyyj";
				ajaxSubFormWithFun("shyjForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						var api = frameElement.api;
						
						if (api.get('childDialog')){
							api.get('parentDialog').setCyyj();
						} else {
							var W = api.opener;
							W.setCyyj();
						}
						
						iFClose();
					}});
				});
				
			}
		</script>
	</head>

	<body>
		<form action="" id="shyjForm">
			<input type="hidden" value="${gnid }" name="gnid"/>
			<div class='tab' >
				<table class="formlist" width="100%">
					<thead>
						<tr>
							<td>
								审核意见
							</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="s" name="shyjList">
							<tr>
								<td>
									<input type="text" name="shyj" value="${s.shyj }" style="width: 100%" maxlength="200"/>
								</td>
							</tr>
						</logic:iterate>
						
						<%
							List<HashMap<String,String>> shyjList = (List<HashMap<String,String>>)request.getAttribute("shyjList");
							
							if (shyjList.size() < 10){
								for (int i = 0; i < 10 - shyjList.size(); i++ ){
									%>
										<tr>
											<td>
												<input type="text" name="shyj" style="width: 100%" maxlength="200"/>
											</td>
										</tr>
									<%	
								}
							}
							
						%>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button type="button" type="button" onclick="saveShyj();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
	</body>
</html>

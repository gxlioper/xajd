<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
		<script type="text/javascript">
			function saveCyyy(){				
				var input = jQuery("input[name=cyyy]");
				var textFlg = true;
				jQuery.each(input,function(i,e){
					var textVal = jQuery(e).val();
					if (textVal != null && textVal != ''){
						textFlg = false;
						return;
					}
				});
				if (textFlg){
					showAlert("请至少填写一项常用原因！");
					return false;
				}				
				var url = "jlkjxy_jqfxwh.do?method=saveCyyy";
				ajaxSubFormWithFun("jlkjxy_jqfxwhForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						var api = frameElement.api;
						
						if (api.get('childDialog')){						
							api.get('parentDialog').setCyyy();
						} else {
							var W = api.opener;
							W.setCyyy();	
						}						
						iFClose();
					}});
				});
				
			}
		</script>
	</head>

	<body>
		<form action="" id="jlkjxy_jqfxwhForm">
			<div class='tab' >
				<table class="formlist" width="100%">
					<thead>
						<tr>
							<td>
								<span>未返校常用原因</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="s" name="cyyyList">
							<tr>
								<td>
									<input type="text" name="cyyy" value="${s.cyyy}" style="width: 100%" maxlength="200"/>
								</td>
							</tr>
						</logic:iterate>						
						<%
							//设置常用原因
							List<HashMap<String,String>> cyyyList = (List<HashMap<String,String>>)request.getAttribute("cyyyList");
							if (cyyyList.size() < 10){
								for (int i = 0; i < 10 - cyyyList.size(); i++ ){
									%>
										<tr>
											<td>
												<input type="text" name="cyyy" style="width: 100%" maxlength="200"/>
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
									<button type="button" type="button" onclick="saveCyyy();">
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

<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function confirm(){
				var selChk = jQuery("[name='sel']:checked");
				if(selChk == null && selChk.length == 0){
					return showAlert("请先选择楼栋！");
				}
				var W;
				var api = frameElement.api;
				if (api) {
					if (api.get('childDialog')) {
						W = api.get('parentDialog')
					} else {
						W = api.opener;
					}
				} else if (parent.window) {
					W = parent.window;
				}
				var id = "#"+jQuery("#xydm").val();
				jQuery(id, W.document).empty();
				for(var i=0;i < selChk.length;i++){
					jQuery(id, W.document).append(jQuery("<tr></tr>").append(jQuery(selChk[i]).parent().parent().find("[name='seltd']")));
				}
				iFClose();
			}
		</script>
	</head>
	<body>
		<html:form action="/gygl_xyzsjggl" method="post" styleId="XyzsglForm">
			<input type="hidden" value="${xydm}" id="xydm" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<td colspan="4">
								<table>
									<tbody>
										<logic:iterate name="LdList" id="i">
											<tr>
												<td width="10%">
												<input  
													<logic:iterate id="show" name="ldArray" >
														<logic:equal value="${show}" property="lddm" name="i">
															checked
														</logic:equal>
													</logic:iterate> type="checkbox" name="sel"/>
													
												</td>
												<td width="90%" name="seltd">
													${i.ldmc}&nbsp;共<font color="red">${i.cnt}</font>个寝室
													<input type="hidden"  value="${i.lddm}-${i.xydm}" name="lddm"/>
													
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="confirm();">
										确定
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
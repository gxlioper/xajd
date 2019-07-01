<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			jQuery(function($) {
				var api = frameElement.api;
				W = api.get('parentDialog');
				$("#html").html(W.jQuery("#html").html());
			});
			function messageClose(){
				var api = frameElement.api;
				api.close();
			}
		</script>
	</head>
	<body style="width: 100%;height: ${height}px;">
		<table width="100%" height="100%">
			<tr>
				<td style="width: 20%;" align="right">
					<img src="/xgxt/comm/message/info.gif" />
				</td>
				<td style="width: 80%" align="left">
					<div id="html"
						style="margin-left: 20px;max-height:${height}px;overflow-x:hidden;overflow-y:auto;">
						${html}
					</div>
				</td>
			</tr>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="btn" style="text-align: center;">
							<button type="button" onclick="messageClose();" id="buttonSave">
									È·¶¨
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>


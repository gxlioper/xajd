<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/znxgl/znxgl/js/znxgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//页面刷新
				if(parent.window){
					if(frameElement.api){
						var api = frameElement.api,W = api.opener;
						jQuery(W.document).find('#search_go').click();
					}
			    }
			});
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/znxgl" method="post" styleId="ZnxglForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="25%">主题类别</th>
							<td colspan="3">${ztlb}</td>
						</tr>
						<tr>
							<th width="25%">发件人</th>
							<td colspan="3">${fsrxm}</td>
							<input type="hidden" id="fsrxm" value="${fsrxm}"/>
							<input type="hidden" id="xjbh" value="${xjbh}"/>
							<input type="hidden" id="jsrbh" value="${jsrbh}"/>
						</tr>
						<tr>
							<th width="25%">信件主题</th>
							<td colspan="3">${xjzt}</td>
						</tr>
						<tr>
							<th width="25%">发送内容</th>
							<td colspan="3">${fsnr}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="zjhf();">
										回复
									</button>
									<button type="button" onclick="iFClose();">
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
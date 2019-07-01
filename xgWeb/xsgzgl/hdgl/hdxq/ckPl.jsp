<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								活动名称
							</th>
							<td colspan="3">
								${plxx.hdmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<logic:empty name="plxx" property="hfrxm">
									${plxx.plrxm}评论
								</logic:empty>
								<logic:notEmpty name="plxx" property="hfrxm">
									${plxx.plrxm}回复:${plxx.hfrxm}
								</logic:notEmpty>								
							</th>
							<td colspan="3">
								${plxx.plnr}
							</td>
						</tr>						
					</tbody>
				 </table>			
			</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
										<button type="button" onclick="iFClose();">
											关闭
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


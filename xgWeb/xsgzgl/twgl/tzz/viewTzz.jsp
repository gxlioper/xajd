<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/twgl/js/tzz.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/tzzgl" method="post" styleId="tzzForm" onsubmit="return false;">
			<html:hidden property="zzid" styleId="zzid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								组织名称
							</th>
							<td width="30%">
								${tzzForm.zzmc}
							</td>
							<th width="20%">指导单位</th>
							<td width="30%">
								${tzzForm.zddw}
							</td>
						</tr>
						<tr>
							<th width="20%">
								组织地址
							</th>
							<td width="30%">
								${tzzForm.zzdz}
							</td>
							<th>负责人</th>
							<td>
								${tzzForm.fzr}
							</td>
						</tr>
						<tr>
							<th>
								组织简介
							</th>
							<td colspan="3">
								${tzzForm.zzjj}
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


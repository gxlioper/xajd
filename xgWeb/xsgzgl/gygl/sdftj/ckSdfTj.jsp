<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/sdftj/js/sdftj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gygl_sdftj" method="post" styleId="sdfTjForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ˮ���ͳ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>���</th>
							<td>
								${rs.nd}
							</td>
							<th>����</th>
							<td>
								${rs.jd}
							</td>
						</tr>
						<tr>
							<th>¥������</th>
							<td>
								${ldmc}
							</td>
							<th>���Һ�</th>
							<td>
								${rs.qsh}
							</td>
						</tr>
						<tr>
							<th>ˮ�Ѻϼ�(Ԫ)</th>
							<td>
								${rs.sf}								
							</td>
							<th>��Ѻϼ�(Ԫ)</th>
							<td>
								${rs.df}	
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
									<button type="button" onclick="iFClose();">
										�ر�
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
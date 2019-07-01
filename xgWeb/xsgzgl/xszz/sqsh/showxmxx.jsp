<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function confirm(){
			showDialog("资助项目申请",715,430,"xszz_sqsh.do?method=xszzXmsqStu");
			iFClose();
		} 
		</script>
	</head>
	<body>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;height:520px;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th>项目名称</th>
							<td colspan="3">
								${xmxx.xmmc}
							</td>
						</tr>
						<tr>
							<th>项目说明</th>
							<td colspan="3">
								${xmxx.xmsm}
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="confirm();">
										确认
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
	</body>
	
</html>
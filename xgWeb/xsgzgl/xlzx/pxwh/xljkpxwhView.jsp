<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/zjly_ylbx" method="post" styleId="form">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>培训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  width="20%">
								培训主题
							</th>
							<td colspan="3">
								${pxwhForm.pxzt}
							</td>
						</tr>
						<tr>
						<tr>
							<th  width="20%">
								培训地点
							</th>
							<td colspan="3">
								${pxwhForm.pxdd}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								培训时间
							</th>
							<td width="30%">
								${pxwhForm.pxsj}
							</td>
							<th  width="20%">
								讲师
							</th>
							<td width="30%">
								${pxwhForm.js}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								培训上限人数
							</th>
							<td width="30%">
								${pxwhForm.sxrs}
							</td>
							<th  width="20%">
								已报名人数
							</th>
							<td width="30%">
								${pxwhForm.ybmrs}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								培训开关
							</th>
							<td colspan="3">
								${pxwhForm.bmkg}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								报名开放时间
							</th>
							<td colspan="3">
								${pxwhForm.kssj}
							至
								${pxwhForm.jssj}
							</td>
						</tr>
						<tr>
							<th >
								培训内容
								<br><font color="red">限500字以内</br></font>
							</th>
							<td colspan="3" style="height:150px">
								${pxwhForm.pxnr}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
										关 闭
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
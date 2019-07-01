<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxdj/js/lxdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				proviceCiyyLocalMain({type:"view",id:"mddssx",flag:"wxxdz"});
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/qmlxjg" method="post" styleId="LxjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>离校趋向信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${rs.xn}
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>是否离校</th>
							<td>
									${rs.sflxdm}
							</td>
							<th>离校类型</th>
							<td>
									${rs.lxlxmc}
							</td>
						</tr>
						<tr>
							<th>监护人姓名</th>
							<td>
								${rs.jhrxm}
							</td>
							<th>监护人联系方式</th>
							<td>
								${rs.jhrlxfs}
							</td>
						</tr>
						<tr>
							<th>是否与监护人联系</th>
							<td>
								${rs.sflx}
							</td>
							<th>离校时间</th>
							<td>
								${rs.lxsj}
							</td>
						</tr>
						<tr>
							<th>离校交通工具</th>
							<td>
								${rs.lxgj}
							</td>
							<th>离校车次/航班</th>
							<td>
								${rs.lxcchb}
							</td>
						</tr>
						<tr>
							<th>目的地</th>
							<td colspan="3">
								<html:hidden name="rs"  property="mddssx" styleId="mddssx"/>
							</td>
						</tr>
						<tr>
							<th>返校时间</th>
							<td>
								${rs.fxsj}
							</td>
							<th>返校交通工具</th>
							<td>
								${rs.fxgj}
							</td>
						</tr>
						<tr>
							<th>返校车次/航班</th>
							<td>
								${rs.fxcchb}
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3">
								${rs.bz}
							</td>
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
		</html:form>
	</body>
	
</html>